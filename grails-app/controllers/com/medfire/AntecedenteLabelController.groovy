package com.medfire

class AntecedenteLabelController {

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
        def antecedenteLabelInstance = new AntecedenteLabel(params)
        if (antecedenteLabelInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'antecedenteLabel.label', default: 'AntecedenteLabel'), antecedenteLabelInstance.id])}"
            redirect(action: "show", id: antecedenteLabelInstance.id)
        }
        else {
            render(view: "create", model: [antecedenteLabelInstance: antecedenteLabelInstance])
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
            try {
                antecedenteLabelInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'antecedenteLabel.label', default: 'AntecedenteLabel'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'antecedenteLabel.label', default: 'AntecedenteLabel'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'antecedenteLabel.label', default: 'AntecedenteLabel'), params.id])}"
            redirect(action: "list")
        }
    }
}
