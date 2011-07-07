package com.medfire

class LaboratorioController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [laboratorioInstanceList: Laboratorio.list(params), laboratorioInstanceTotal: Laboratorio.count()]
    }

    def create = {
        def laboratorioInstance = new Laboratorio()
        laboratorioInstance.properties = params
        return [laboratorioInstance: laboratorioInstance]
    }

    def save = {
        def laboratorioInstance = new Laboratorio(params)
        if (laboratorioInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'laboratorio.label', default: 'Laboratorio'), laboratorioInstance.id])}"
            redirect(action: "show", id: laboratorioInstance.id)
        }
        else {
            render(view: "create", model: [laboratorioInstance: laboratorioInstance])
        }
    }

    def show = {
        def laboratorioInstance = Laboratorio.get(params.id)
        if (!laboratorioInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'laboratorio.label', default: 'Laboratorio'), params.id])}"
            redirect(action: "list")
        }
        else {
            [laboratorioInstance: laboratorioInstance]
        }
    }

    def edit = {
        def laboratorioInstance = Laboratorio.get(params.id)
        if (!laboratorioInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'laboratorio.label', default: 'Laboratorio'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [laboratorioInstance: laboratorioInstance]
        }
    }

    def update = {
        def laboratorioInstance = Laboratorio.get(params.id)
        if (laboratorioInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (laboratorioInstance.version > version) {
                    
                    laboratorioInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'laboratorio.label', default: 'Laboratorio')] as Object[], "Another user has updated this Laboratorio while you were editing")
                    render(view: "edit", model: [laboratorioInstance: laboratorioInstance])
                    return
                }
            }
            laboratorioInstance.properties = params
            if (!laboratorioInstance.hasErrors() && laboratorioInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'laboratorio.label', default: 'Laboratorio'), laboratorioInstance.id])}"
                redirect(action: "show", id: laboratorioInstance.id)
            }
            else {
                render(view: "edit", model: [laboratorioInstance: laboratorioInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'laboratorio.label', default: 'Laboratorio'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def laboratorioInstance = Laboratorio.get(params.id)
        if (laboratorioInstance) {
            try {
                laboratorioInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'laboratorio.label', default: 'Laboratorio'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'laboratorio.label', default: 'Laboratorio'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'laboratorio.label', default: 'Laboratorio'), params.id])}"
            redirect(action: "list")
        }
    }
}
