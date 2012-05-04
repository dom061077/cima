package com.medfire
//
import com.medfire.util.GUtilDomainClass


class ObraSocialController {
	def grailsApplication
	def authenticateService
	
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [obraSocialInstanceList: ObraSocial.list(params), obraSocialInstanceTotal: ObraSocial.count()]
    }

    def create = {
        def obraSocialInstance = new ObraSocial()
        obraSocialInstance.properties = params
        return [obraSocialInstance: obraSocialInstance]
    }

    def save = {
		log.info "INGRESANDO AL CLOSURE save DEL CONTROLLER ObraSocialController"
		log.info "PARAMETROS: $params"
        def obraSocialInstance = new ObraSocial(params)
		obraSocialInstance.institucion = authenticateService.userDomain().institucion
        if (obraSocialInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'obraSocial.label', default: 'ObraSocial'), obraSocialInstance.id])}"
            redirect(action: "show", id: obraSocialInstance.id)
        }
        else {
            render(view: "create", model: [obraSocialInstance: obraSocialInstance])
        }
    }

    def show = {
        def obraSocialInstance = ObraSocial.get(params.id)
        if (!obraSocialInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'obraSocial.label', default: 'ObraSocial'), params.id])}"
            redirect(action: "list")
        }
        else {
            [obraSocialInstance: obraSocialInstance]
        }
    }

    def edit = {
        def obraSocialInstance = ObraSocial.get(params.id)
        if (!obraSocialInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'obraSocial.label', default: 'ObraSocial'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [obraSocialInstance: obraSocialInstance]
        }
    }

    def update = {
        def obraSocialInstance = ObraSocial.get(params.id)
        if (obraSocialInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (obraSocialInstance.version > version) {
                    
                    obraSocialInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'obraSocial.label', default: 'ObraSocial')] as Object[], "Another user has updated this ObraSocial while you were editing")
                    render(view: "edit", model: [obraSocialInstance: obraSocialInstance])
                    return
                }
            }
            obraSocialInstance.properties = params
            if (!obraSocialInstance.hasErrors() && obraSocialInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'obraSocial.label', default: 'ObraSocial'), obraSocialInstance.id])}"
                redirect(action: "show", id: obraSocialInstance.id)
            }
            else {
                render(view: "edit", model: [obraSocialInstance: obraSocialInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'obraSocial.label', default: 'ObraSocial'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def obraSocialInstance = ObraSocial.get(params.id)
        if (obraSocialInstance) {
            try {
                obraSocialInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'obraSocial.label', default: 'ObraSocial'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'obraSocial.label', default: 'ObraSocial'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'obraSocial.label', default: 'ObraSocial'), params.id])}"
            redirect(action: "list")
        }
    }
	
	def listsearchjson = {
		log.info "INGRESANDO AL METODO listsearchjson DEL CONTROLLER ObraSocialController"
		log.info "PARAMETROS: ${params}"
		
		def institucionInstance = authenticateService.userDomain().institucion
		params.altfilters = """{'groupOp':'AND','rules':[{'field':'institucion_id','op':'eq','data':'${institucionInstance.id}'}]}"""
		params._search = "true"

		
		def gud=new GUtilDomainClass(ObraSocial,params,grailsApplication)
		list=gud.listrefactor(false)
		def totalregistros=gud.listrefactor(true)
		
		def totalpaginas=new Float(totalregistros/Integer.parseInt(params.rows))
		if (totalpaginas>0 && totalpaginas<1)
			totalpaginas=1;
		totalpaginas=totalpaginas.intValue()

		
		 
		def result='{"page":'+params.page+',"total":"'+totalpaginas+'","records":"'+totalregistros+'","rows":['
		log.debug "CONSULTA DE OBRA SOCIALES: $list"
		def flagaddcomilla=false
		list.each{
			
			if (flagaddcomilla)
				result=result+','
			
			result=result+'{"id":"'+it.id+'","cell":["'+it.id+'","'+it.descripcion+'"]}'
			 
			flagaddcomilla=true
		}
		result=result+']}'
		render result

	}
    
    def listjson = {
    	log.info "INGRESANDO AL METODO listjson EN EL CONTROLLER ObraSocialController"
    	log.info "PARAMETROS: $params"
    	def list
    	
		def institucionInstance = authenticateService.userDomain().institucion
		params.altfilters = """{'groupOp':'AND','rules':[{'field':'institucion_id','op':'eq','data':'${institucionInstance.id}'}]}"""
		params._search = "true"

		
		def gud=new GUtilDomainClass(ObraSocial,params,grailsApplication)
    	list=gud.listrefactor(false)	
		def totalregistros=gud.listrefactor(true)
		
		def totalpaginas=new Float(totalregistros/Integer.parseInt(params.rows))
		if (totalpaginas>0 && totalpaginas<1)
			totalpaginas=1;
		totalpaginas=totalpaginas.intValue()

    	
    	
    	def result='{"page":'+params.page+',"total":"'+totalpaginas+'","records":"'+totalregistros+'","rows":['
    	log.debug "CONSULTA DE OBRA SOCIALES: $list"
    	def flagaddcomilla=false
    	list.each{
    		
    		if (flagaddcomilla)
    			result=result+','
    		
    		result=result+'{"id":"'+it.id+'","cell":["'+it.id+'","'+it.cuit+'","'+it.descripcion+'","'+it.razonSocial+'","'+(it.telefono==null?"":it.telefono)+'","'+it.codigoPostal+'","'+it.domicilio+'","'+it.contacto+'"]}'
    		 
    		flagaddcomilla=true
    	}
    	result=result+']}'
    	render result
    	
    	
    }
	
	def listjsonautocomplete = {
		log.debug "INGRESANDO AL METODO listjsonautocomplete EN EL CONTROLLER ObraSocialController"
		log.debug "PARAMETROS: $params"
		
		
		
		def list = ObraSocial.createCriteria().list(){
			and{
				like('descripcion','%'+params.term+'%')
				institucion{
					eq("id",authenticateService.userDomain().institucion.id)
				}
			}
		}
		log.debug "CONSULTA DE OBRA SOCIALES: $list"

//		list.each{
//			log.debug "ITERANDO POR LAS OBRAS SOCIALES"
//			result=result+"${it.descripcion}|${it.id}\n"
//		}
		
		render (contentType:"text/json"){
			 array{
						for(os in list){
							obrasocial id:os.id,label:os.descripcion, value:os.descripcion	
						}
					}
		}
	}
	
	def listjsonprovincia={
		log.debug "INGRESANDO AL METODO listjsonprovincia EN EL CONTROLLER ObraSocialController"
		log.debug "PARAMETROS: $params"
		def list = Provincia.list()
		render (contentType:"text/json"){
			array{
				for (prov in list){
					provincia id:prov.id,label:prov.nombre,value:prov.nombre
				}
			}
		}
	}
	
	
	
	def excelexport = {
		log.info "INGRESANDO AL METODO excelexport EN EL CONTROLLER ObraSocialController"
		log.info "PARAMETROS: $params"	
	}
	

	def listado = {
		log.info "INGRESANDO AL CLOSURE listado DEL CONTROLADOR ObraSocialController"
		log.info "PARAMAMETROS: $params"

		def obassociales = ObraSocial.list()
		chain(controller:'jasper',action:'index',model:[data:obassociales],params:params)
	}
	
}
