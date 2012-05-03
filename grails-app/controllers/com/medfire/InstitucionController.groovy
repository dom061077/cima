package com.medfire

import com.medfire.util.GUtilDomainClass

class InstitucionController {
	def imageUploadService
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [institucionInstanceList: Institucion.list(params), institucionInstanceTotal: Institucion.count()]
    }

    def create = {
        def institucionInstance = new Institucion()
        institucionInstance.properties = params
        return [institucionInstance: institucionInstance]
    }

    def save = {
        def institucionInstance = new Institucion(params)
        if (institucionInstance.save(flush: true)) {
			
			if(!institucionInstance.imagen.isEmpty())
				imageUploadService.save(institucionInstance)
			
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'institucion.label', default: 'Institucion'), institucionInstance.id])}"
            redirect(action: "show", id: institucionInstance.id)
        }
        else {
            render(view: "create", model: [institucionInstance: institucionInstance])
        }
    }

    def show = {
        def institucionInstance = Institucion.get(params.id)
        if (!institucionInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'institucion.label', default: 'Institucion'), params.id])}"
            redirect(action: "list")
        }
        else {
            [institucionInstance: institucionInstance]
        }
    }

    def edit = {
        def institucionInstance = Institucion.get(params.id)
        if (!institucionInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'institucion.label', default: 'Institucion'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [institucionInstance: institucionInstance]
        }
    }

    def update = {
        def institucionInstance = Institucion.get(params.id)
        if (institucionInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (institucionInstance.version > version) {
                    
                    institucionInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'institucion.label', default: 'Institucion')] as Object[], "Another user has updated this Institucion while you were editing")
                    render(view: "edit", model: [institucionInstance: institucionInstance])
                    return
                }
            }
            institucionInstance.properties = params
            if (!institucionInstance.hasErrors() && institucionInstance.save(flush: true)) {
				if (!params.imagen.isEmpty()){
					imageUploadService.save(institucionInstance)
				}
				
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'institucion.label', default: 'Institucion'), institucionInstance.id])}"
                redirect(action: "show", id: institucionInstance.id)
            }
            else {
                render(view: "edit", model: [institucionInstance: institucionInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'institucion.label', default: 'Institucion'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def institucionInstance = Institucion.get(params.id)
        if (institucionInstance) {
            try {
                institucionInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'institucion.label', default: 'Institucion'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'institucion.label', default: 'Institucion'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'institucion.label', default: 'Institucion'), params.id])}"
            redirect(action: "list")
        }
    }
	
	def redirectaction = {
		log.info "INGRESANDO AL CLOSURE redirecaction"
		log.info "PARAMETROS: $params"
		def list = Institucion.findAll()
		if(list.size()>0){
			def institucionInstance = list.getAt(0)
			redirect(action:"edit",params:[id:institucionInstance.id])	
		}else{
			redirect(action:"create")
		}
		
		
	}
	
	def listjson = {
		log.info "INGRESANDO AL CLOSURE listjson"
		log.info "PARAMETROS: $params"
		def gud=new GUtilDomainClass(Institucion,params,grailsApplication)
		def list=gud.listrefactor(false)
		def totalregistros=gud.listrefactor(true)
		
		def totalpaginas=new Float(totalregistros/Integer.parseInt(params.rows))
		if (totalpaginas>0 && totalpaginas<1)
			totalpaginas=1;
		totalpaginas=totalpaginas.intValue()

		
		
		def result='{"page":'+params.page+',"total":"'+totalpaginas+'","records":"'+totalregistros+'","rows":['

		def flagaddcomilla=false
		def urlimg
		def urllargeimg
		list.each{
			
			if (flagaddcomilla)
				result=result+','
				
			urlimg = bi.resource(size:'small',bean:it)
			urllargeimg = bi.resource(size:'large',bean:it)
			if(urlimg.contains(".null")){
				urlimg = g.resource(dir:'images',file:'noDisponible.jpg')
				urllargeimg = g.resource(dir:'images',file:'noDisponibleLarge.jpg')
			}
			result=result+'{"id":"'+it.id+'","cell":["'+it.id+'","'+(it.nombre==null?"":it.nombre)+'","'+urllargeimg+'","'+urlimg+'"]}'
			 
			flagaddcomilla=true
		}
		result=result+']}'
		render result

	}
	
	def listjsonautocomplete = {
		log.info "INGRESANDO AL CLOSURE listjsonautocomplete"
		log.info "PARAMETROS: $params"
		def instituciones = Institucion.createCriteria().list(){
			like('nombre','%'+params.term+'%')
		}
		render(contentType:"text/json"){
			array{
				for (inst in instituciones){
					institucion id:inst.id,label:inst.nombre,value:inst.nombre
				}
			}
		}
	}
	
}
