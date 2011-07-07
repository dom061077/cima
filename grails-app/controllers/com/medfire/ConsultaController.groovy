package com.medfire

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.text.ParseException

class ConsultaController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [consultaInstanceList: Consulta.list(params), consultaInstanceTotal: Consulta.count()]
    }
 
    def create = {
		log.info "INGREANDO AL CLOSURE create DEL CONTROLLER ConsultaController"
		log.info "PARAMETROS $params"
		def paciente = Paciente.load(params.paciente.id.toLong())
        def consultaInstance = new Consulta()
        consultaInstance.properties = params
        return [consultaInstance: consultaInstance,pacienteId:paciente.id,pacienteNombre:paciente.apellido+"-"+paciente.nombre]
    }

    def save = {
		log.info "INGRESANDO AL CLOSURE save DEL CONTROLLER ConsultaController"
		log.info "PARAMETROS $params"
		
		if (params.fechaConsulta){
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy")
			def fecha
			try{
				fecha = df.parse(params.fechaConsulta)
				log.debug "LA FECHA SE PARSEO BIEN"
			}catch(ParseException e){
				log.debug "LA FECHA NO SE PARSEO BIEN" 
			}
			def gc = Calendar.getInstance()
			gc.setTime(fecha)
			log.debug "ANIO: "+gc.get(Calendar.YEAR).toString()+", MES "+gc.get(Calendar.MONTH+1).toString()+" DIA "+gc.get(Calendar.DATE).toString()
			params.fechaConsulta_year=gc.get(Calendar.YEAR).toString()
			params.fechaConsulta_month=(gc.get(Calendar.MONTH)+1).toString()
			params.fechaConsulta_day=gc.get(Calendar.DATE).toString()
		}

		
        def consultaInstance = new Consulta(params)
		consultaInstance.fechaAlta= new java.sql.Date((new Date()).getTime())
        if (consultaInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'consulta.label', default: 'Consulta'), consultaInstance.id])}"
            redirect(action: "show", id: consultaInstance.id)
        }
        else {
            render(view: "create", model: [consultaInstance: consultaInstance])
        }
    }

    def show = {
        def consultaInstance = Consulta.get(params.id)
        if (!consultaInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'consulta.label', default: 'Consulta'), params.id])}"
            redirect(action: "list")
        }
        else {
            [consultaInstance: consultaInstance]
        }
    }

    def edit = {
        def consultaInstance = Consulta.get(params.id)
        if (!consultaInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'consulta.label', default: 'Consulta'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [consultaInstance: consultaInstance]
        }
    }

    def update = {
        def consultaInstance = Consulta.get(params.id)
        if (consultaInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (consultaInstance.version > version) {
                    
                    consultaInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'consulta.label', default: 'Consulta')] as Object[], "Another user has updated this Consulta while you were editing")
                    render(view: "edit", model: [consultaInstance: consultaInstance])
                    return
                }
            }
            consultaInstance.properties = params
            if (!consultaInstance.hasErrors() && consultaInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'consulta.label', default: 'Consulta'), consultaInstance.id])}"
                redirect(action: "show", id: consultaInstance.id)
            }
            else {
                render(view: "edit", model: [consultaInstance: consultaInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'consulta.label', default: 'Consulta'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def consultaInstance = Consulta.get(params.id)
        if (consultaInstance) {
            try {
                consultaInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'consulta.label', default: 'Consulta'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'consulta.label', default: 'Consulta'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'consulta.label', default: 'Consulta'), params.id])}"
            redirect(action: "list")
        }
    }
}
