package com.medfire

class ProvinciaController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [provinciaInstanceList: Provincia.list(params), provinciaInstanceTotal: Provincia.count()]
    }

    def create = {
        def provinciaInstance = new Provincia()
        provinciaInstance.properties = params
        return [provinciaInstance: provinciaInstance]
    }

    def save = {
        def provinciaInstance = new Provincia(params)
        if (provinciaInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'provincia.label', default: 'Provincia'), provinciaInstance.id])}"
            redirect(action: "show", id: provinciaInstance.id)
        }
        else {
            render(view: "create", model: [provinciaInstance: provinciaInstance])
        }
    }

    def show = {
        def provinciaInstance = Provincia.get(params.id)
        if (!provinciaInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'provincia.label', default: 'Provincia'), params.id])}"
            redirect(action: "list")
        }
        else {
            [provinciaInstance: provinciaInstance]
        }
    }

    def edit = {
        def provinciaInstance = Provincia.get(params.id)
        if (!provinciaInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'provincia.label', default: 'Provincia'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [provinciaInstance: provinciaInstance]
        }
    }

    def update = {
        def provinciaInstance = Provincia.get(params.id)
        if (provinciaInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (provinciaInstance.version > version) {
                    
                    provinciaInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'provincia.label', default: 'Provincia')] as Object[], "Another user has updated this Provincia while you were editing")
                    render(view: "edit", model: [provinciaInstance: provinciaInstance])
                    return
                }
            }
            provinciaInstance.properties = params
            if (!provinciaInstance.hasErrors() && provinciaInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'provincia.label', default: 'Provincia'), provinciaInstance.id])}"
                redirect(action: "show", id: provinciaInstance.id)
            }
            else {
                render(view: "edit", model: [provinciaInstance: provinciaInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'provincia.label', default: 'Provincia'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def provinciaInstance = Provincia.get(params.id)
        if (provinciaInstance) {
            try {
                provinciaInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'provincia.label', default: 'Provincia'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'provincia.label', default: 'Provincia'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'provincia.label', default: 'Provincia'), params.id])}"
            redirect(action: "list")
        }
    }
	
	def listjsonautocomplete = {
		log.info "INGRESANDO AL METODO listjsonautocomplete"
		log.info "PARAMETROS: ${params}"
		def list
		if(params.term){
			list = Provincia.createCriteria().list({
				if(params.term)
					ilike("nombre",params.term+"%")
			})
		}
		
		render (contentType:"text/json"){
			array{
				for (prov in list){
					provincia id:prov.id,label:prov.nombre,value:prov.nombre
				}
			}
		}

	}
}
