package com.medfire

import org.springframework.transaction.TransactionStatus

class AntecedenteLabelController {
	def authenticateService
	
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [antecedenteLabelInstanceList: AntecedenteLabel.list(params), antecedenteLabelInstanceTotal: AntecedenteLabel.count()]
    }

    def create = {
        def antecedenteLabelInstance = new AntecedenteLabel()
        antecedenteLabelInstance.properties = params
        return [antecedenteLabelInstance: antecedenteLabelInstance]
    }

    def save = {
		log.info "INGRESANDO AL CLOSURE save"
		
        def antecedenteLabelInstance = new AntecedenteLabel(params)
		def profesionalInstance = User.load(authenticateService.userDomain().id).profesionalAsignado
		profesionalInstance.antecedenteLabel=antecedenteLabelInstance
		log.debug "PROFESIONAL: ASIGNADO "+profesionalInstance
		log.debug "PROFESIONAL ASIGNADO DESDE EL USUARIO: "+antecedenteLabelInstance.profesional
        if (profesionalInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'antecedenteLabel.label', default: 'AntecedenteLabel'), antecedenteLabelInstance.id])}"
            redirect(action: "show", id: antecedenteLabelInstance.id)
        }
        else {
            render(view: "createprof", model: [antecedenteLabelInstance: antecedenteLabelInstance])
        }
    }

    def show = {
        def antecedenteLabelInstance = AntecedenteLabel.get(params.id)
        if (!antecedenteLabelInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'antecedenteLabel.label', default: 'AntecedenteLabel'), params.id])}"
            redirect(action: "list")
        }
        else {
            [antecedenteLabelInstance: antecedenteLabelInstance]
        }
    }

    def edit = {
        def antecedenteLabelInstance = AntecedenteLabel.get(params.id)
        if (!antecedenteLabelInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'antecedenteLabel.label', default: 'AntecedenteLabel'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [antecedenteLabelInstance: antecedenteLabelInstance]
        }
    }

    def update = {
        def antecedenteLabelInstance = AntecedenteLabel.get(params.id)
        if (antecedenteLabelInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (antecedenteLabelInstance.version > version) {
                    
                    antecedenteLabelInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'antecedenteLabel.label', default: 'AntecedenteLabel')] as Object[], "Another user has updated this AntecedenteLabel while you were editing")
                    render(view: "edit", model: [antecedenteLabelInstance: antecedenteLabelInstance])
                    return
                }
            }
            antecedenteLabelInstance.properties = params
            if (!antecedenteLabelInstance.hasErrors() && antecedenteLabelInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'antecedenteLabel.label', default: 'AntecedenteLabel'), antecedenteLabelInstance.id])}"
                redirect(action: "show", id: antecedenteLabelInstance.id)
            }
            else {
                render(view: "edit", model: [antecedenteLabelInstance: antecedenteLabelInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'antecedenteLabel.label', default: 'AntecedenteLabel'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def antecedenteLabelInstance = AntecedenteLabel.get(params.id)
        if (antecedenteLabelInstance) {
			Profesional.withTransaction{TransactionStatus status ->
	            try {
					antecedenteLabelInstance.profesional.antecedenteLabel=null
						antecedenteLabelInstance.profesional.save(flush:true)
						antecedenteLabelInstance.delete(flush: true)
						flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'antecedenteLabel.label', default: 'AntecedenteLabel'), params.id])}"
						redirect(action: "list")
		
	            }
	            catch (org.springframework.dao.DataIntegrityViolationException e) {
					status.setRollbackOnly()
	                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'antecedenteLabel.label', default: 'AntecedenteLabel'), params.id])}"
	                redirect(action: "show", id: params.id)
	            }
			}
			
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'antecedenteLabel.label', default: 'AntecedenteLabel'), params.id])}"
            redirect(action: "list")
        }
    }
	
	def redirect = {
		log.info "INGRESANDO AL CLOSURE redirect"
		log.info "PARAMETROS: $params"
		def userInstance = User.load(authenticateService.userDomain().id)  
		//def profesionalInstance = Profesional.load(authenticateService.userDomain().profesionalAsignado?.id)
		if(!userInstance.profesionalAsignado){
			flash.message="No tiene un profesional asignado a su usuario"
			render(view:"/index")
			return
		}
		
		if (userInstance.profesionalAsignado?.antecedenteLabel){
			redirect(action:"show",params:[id:userInstance.profesionalAsignado.antecedenteLabel.id])
		} else{
			redirect(action:"createprof")
		}
	}
	
	def createprof = {
		log.info "INGRESANDO AL CLOSURE createprof"
		log.info "PARAMETROS: $params"
		def userInstance = User.load(authenticateService.userDomain().id)
		if(!userInstance.profesionalAsignado){
			flash.message="Su usuario no tiene un profesional asignado"
			render(view:"/index")
			return
		}
		def antecedenteLabelInstance = new AntecedenteLabel()
		antecedenteLabelInstance.properties = params
		antecedenteLabelInstance.profesional = userInstance.profesionalAsignado
		return [antecedenteLabelInstance:antecedenteLabelInstance]
	}
	
}
