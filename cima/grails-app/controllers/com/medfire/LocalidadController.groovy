package com.medfire

class LocalidadController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [localidadInstanceList: Localidad.list(params), localidadInstanceTotal: Localidad.count()]
    }

    def create = {
        def localidadInstance = new Localidad()
        localidadInstance.properties = params
        return [localidadInstance: localidadInstance]
    }

    def save = {
        def localidadInstance = new Localidad(params)
        if (localidadInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'localidad.label', default: 'Localidad'), localidadInstance.id])}"
            redirect(action: "show", id: localidadInstance.id)
        }
        else {
            render(view: "create", model: [localidadInstance: localidadInstance])
        }
    }

    def show = {
        def localidadInstance = Localidad.get(params.id)
        if (!localidadInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'localidad.label', default: 'Localidad'), params.id])}"
            redirect(action: "list")
        }
        else {
            [localidadInstance: localidadInstance]
        }
    }

    def edit = {
        def localidadInstance = Localidad.get(params.id)
        if (!localidadInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'localidad.label', default: 'Localidad'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [localidadInstance: localidadInstance]
        }
    }

    def update = {
        def localidadInstance = Localidad.get(params.id)
        if (localidadInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (localidadInstance.version > version) {
                    
                    localidadInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'localidad.label', default: 'Localidad')] as Object[], "Another user has updated this Localidad while you were editing")
                    render(view: "edit", model: [localidadInstance: localidadInstance])
                    return
                }
            }
            localidadInstance.properties = params
            if (!localidadInstance.hasErrors() && localidadInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'localidad.label', default: 'Localidad'), localidadInstance.id])}"
                redirect(action: "show", id: localidadInstance.id)
            }
            else {
                render(view: "edit", model: [localidadInstance: localidadInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'localidad.label', default: 'Localidad'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def localidadInstance = Localidad.get(params.id)
        if (localidadInstance) {
            try {
                localidadInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'localidad.label', default: 'Localidad'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'localidad.label', default: 'Localidad'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'localidad.label', default: 'Localidad'), params.id])}"
            redirect(action: "list")
        }
    }
	
	def listjsonautocomplete = {
		log.info "INGRESANDO AL CLOSURE listjsonautocomplete DEL CONTROLLER LocalidadController"
		log.info "PARAMETROS: ${params}"
		def list = []
		if(params.provinciaid && params.term){
			list = Localidad.createCriteria().list({
				if(params.provinciaid)
					provincia{
						eq("id",params.provinciaid.toLong())
					}
				if(params.term)
					ilike("nombre",params.term+"%")		
			})
		}
		render (contentType:"text/json"){
			array{
				for (loc in list){
					localidad id:loc.id,label:loc.nombre,value:loc.nombre
				}
			}
		}

	}
	
	def listjsonlocalidad={
		log.info "INGREANDO AL METODO listjsonlocalidad EN EL CONTROLLER ObraSocialController"
		log.info "PARAMETROS: $params"
		def pagingConfig=[max:100]
		def list
		if (params.provinciaid){
			list = Localidad.createCriteria().list(pagingConfig){
				provincia{
					eq("id",new Long(params.provinciaid))
				}
				if(params.term)
					like('nombre','%'+params.term+'%')
					
			}
		}
		log.debug "cantidad de localidades: "+list.size()
		render (contentType:"text/json"){
			array{
				for (loc in list){
					localidad id:loc.id,label:loc.nombre,value:loc.nombre
				}
			}
		}
	}
}
