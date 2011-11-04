package com.medfire

import com.medfire.util.GUtilDomainClass
import pl.burningice.plugins.image.container.ContainerUtils
import net.sf.jasperreports.engine.JRExporterParameter
import net.sf.jasperreports.engine.export.JRHtmlExporterParameter

class VademecumController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [vademecumInstanceList: Vademecum.list(params), vademecumInstanceTotal: Vademecum.count()]
    }

/*    def create = {
        def vademecumInstance = new Vademecum()
        vademecumInstance.properties = params
        return [vademecumInstance: vademecumInstance]
    }

    def save = {
        def vademecumInstance = new Vademecum(params)
        if (vademecumInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'vademecum.label', default: 'Vademecum'), vademecumInstance.id])}"
            redirect(action: "show", id: vademecumInstance.id)
        }
        else {
            render(view: "create", model: [vademecumInstance: vademecumInstance])
        }
    }

    def show = {
        def vademecumInstance = Vademecum.get(params.id)
        if (!vademecumInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'vademecum.label', default: 'Vademecum'), params.id])}"
            redirect(action: "list")
        }
        else {
            [vademecumInstance: vademecumInstance]
        }
    }

    def edit = {
        def vademecumInstance = Vademecum.get(params.id)
        if (!vademecumInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'vademecum.label', default: 'Vademecum'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [vademecumInstance: vademecumInstance]
        }
    }

    def update = {
        def vademecumInstance = Vademecum.get(params.id)
        if (vademecumInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (vademecumInstance.version > version) {
                    
                    vademecumInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'vademecum.label', default: 'Vademecum')] as Object[], "Another user has updated this Vademecum while you were editing")
                    render(view: "edit", model: [vademecumInstance: vademecumInstance])
                    return
                }
            }
            vademecumInstance.properties = params
            if (!vademecumInstance.hasErrors() && vademecumInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'vademecum.label', default: 'Vademecum'), vademecumInstance.id])}"
                redirect(action: "show", id: vademecumInstance.id)
            }
            else {
                render(view: "edit", model: [vademecumInstance: vademecumInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'vademecum.label', default: 'Vademecum'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def vademecumInstance = Vademecum.get(params.id)
        if (vademecumInstance) {
            try {
                vademecumInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'vademecum.label', default: 'Vademecum'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'vademecum.label', default: 'Vademecum'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'vademecum.label', default: 'Vademecum'), params.id])}"
            redirect(action: "list")
        }
    }*/
	
	def listjson={
		log.info "INGRESANDO AL CLOSURE listjson DEL CONTROLLER VademecumController"
		log.info "PARAMETROS: ${params}"
		def gud = new GUtilDomainClass(Vademecum,params,grailsApplication)
		def list = gud.listrefactor(false)
		def totalregistros = gud.listrefactor(true)
		def totalpaginas=new Float(totalregistros/Integer.parseInt(params.rows))
		if (totalpaginas>0 && totalpaginas<1)
			totalpaginas=1;
		totalpaginas=totalpaginas.intValue()

		def result='{"page":'+params.page+',"total":"'+totalpaginas+'","records":"'+totalregistros+'","rows":['
		log.debug "TOTAL DE REGISTROS: ${totalregistros}, total paginas: ${totalpaginas}, pagina actual: ${params.page}"
		log.debug "TOTAL EN LIST: "+list.size()
		def flagaddcomilla=false
		list.each{
			
			if (flagaddcomilla)
				result=result+','
			
			result=result+'{"id":"'+it.id+'","cell":["'+it.id+'","'+it.nombreComercial+'","'+it.principio.principioActivo+'","'+it.laboratorio.nombre+'","'+it.presentacion+'","'+it.grupo.nombre+'"]}'
			 
			flagaddcomilla=true
		}
		result=result+']}'
		render result

	}
	
	def vademecumdetalle = {
		log.info "INGREANDO AL CLOSURE vademecumdetalle"
		log.info "PARAMETROS: $params"
		
		def list = Institucion.findAll()
		def institucionInstance = list.getAt(0)
		String pathimage
		String nameimage
		def config
		if(institucionInstance){
			pathimage = bi.resource(size:'large',bean:institucionInstance)
			if(pathimage.contains(".null")){
				pathimage = servletContext.getRealPath("/images")
				nameimage = "noDisponibleLarge.jpg"
			}else{
				config = ContainerUtils.getConfig(institucionInstance)
				pathimage = servletContext.getRealPath("/institucional")
				nameimage = ContainerUtils.getFullName("large", institucionInstance, config)
			}
		
		}
		params.put("pathimage", pathimage);
		params.put("nameimage", nameimage)
		params.put("nombreInstitucion", institucionInstance.nombre);
		params.put("telefonos", institucionInstance.telefonos);
		params.put("email", institucionInstance.email);
		params.put("direccion", institucionInstance.direccion);
		params.put("_format","PDF")
		params.put("_name","vademecumdetalle")
		params.put("_file","vademecumdetalle")
		params.put(JRExporterParameter.PROPERTY_CHARACTER_ENCODING  ,"ISO-8859-1")
		//params.put(JRHtmlExporterParameter.IMAGES_DIR_NAME ,servletContext.getRealPath("/images"))
		//params.put(JRHtmlExporterParameter.IMAGES_URI  ,servletContext.getRealPath("/images")+"/") 
		

		def listvademecum = Vademecum.createCriteria().list(){
			eq("id",params.id.toLong())
		}
		
		chain(controller:'jasper',action:'index',model:[data:listvademecum],params:params)
		
		
	}
	
}
