package com.medfire

import com.medfire.util.GUtilDomainClass
import grails.converters.JSON

class EspecialidadMedicaController {
	def grailsApplication

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    } 

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [especialidadMedicaInstanceList: EspecialidadMedica.list(params), especialidadMedicaInstanceTotal: EspecialidadMedica.count()]
    }

    def create = {
        def especialidadMedicaInstance = new EspecialidadMedica()
        especialidadMedicaInstance.properties = params
        return [especialidadMedicaInstance: especialidadMedicaInstance]
    }

    def save = {
        def especialidadMedicaInstance = new EspecialidadMedica(params)
        if (especialidadMedicaInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'especialidadMedica.label', default: 'EspecialidadMedica'), especialidadMedicaInstance.id])}"
            redirect(action: "show", id: especialidadMedicaInstance.id)
        }
        else {
            render(view: "create", model: [especialidadMedicaInstance: especialidadMedicaInstance])
        }
    }

    def show = {
        def especialidadMedicaInstance = EspecialidadMedica.get(params.id)
        if (!especialidadMedicaInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'especialidadMedica.label', default: 'EspecialidadMedica'), params.id])}"
            redirect(action: "list")
        }
        else {
            [especialidadMedicaInstance: especialidadMedicaInstance]
        }
    }

    def edit = {
        def especialidadMedicaInstance = EspecialidadMedica.get(params.id)
        if (!especialidadMedicaInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'especialidadMedica.label', default: 'EspecialidadMedica'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [especialidadMedicaInstance: especialidadMedicaInstance]
        }
    }

    def update = {
        def especialidadMedicaInstance = EspecialidadMedica.get(params.id)
        if (especialidadMedicaInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (especialidadMedicaInstance.version > version) {
                    
                    especialidadMedicaInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'especialidadMedica.label', default: 'EspecialidadMedica')] as Object[], "Another user has updated this EspecialidadMedica while you were editing")
                    render(view: "edit", model: [especialidadMedicaInstance: especialidadMedicaInstance])
                    return
                }
            }
            especialidadMedicaInstance.properties = params
            if (!especialidadMedicaInstance.hasErrors() && especialidadMedicaInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'especialidadMedica.label', default: 'EspecialidadMedica'), especialidadMedicaInstance.id])}"
                redirect(action: "show", id: especialidadMedicaInstance.id)
            }
            else {
                render(view: "edit", model: [especialidadMedicaInstance: especialidadMedicaInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'especialidadMedica.label', default: 'EspecialidadMedica'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def especialidadMedicaInstance = EspecialidadMedica.get(params.id)
        if (especialidadMedicaInstance) {
            try {
                especialidadMedicaInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'especialidadMedica.label', default: 'EspecialidadMedica'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'especialidadMedica.label', default: 'EspecialidadMedica'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'especialidadMedica.label', default: 'EspecialidadMedica'), params.id])}"
            redirect(action: "list")
        }
    }
	
	def listjson={
		log.info "INGRESANDO AL CLOSURE listjson DEL CONTROLLER EspecialidadMedica"
		log.info "PARAMETROS: ${params}"
		def filtersJson
		if(params.filters)
			 filtersJson = JSON.parse(params.filters)
		
		log.debug "JSON GENERADO: ${filtersJson}"
		
		def list
		def gudc = new GUtilDomainClass(EspecialidadMedica,params,grailsApplication)
		list = gudc.listrefactor(false)
		def totalregistros = gudc.listrefactor(true)
		
			
		def totalpaginas=new Float(totalregistros/Integer.parseInt(params.rows))
		if (totalpaginas>0 && totalpaginas<1)
			totalpaginas=1;
		totalpaginas=totalpaginas.intValue()

		
		log.debug "TOTAL ESPECIALIDADES: "+list.size()
		
		
		def result='{"page":'+params.page+',"total":"'+totalpaginas+'","records":"'+totalregistros+'","rows":['
		log.debug "CONSULTA DE ESPECIALIDADES: $list"
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
	
	def listjsonautocomplete={
		log.info "INGRESANDO AL CLOSURE listjsonautocomplete DEL CONTROLLER EspecialidadMedica"
		log.info "PARAMETROS: ${params}"
		def list = EspecialidadMedica.createCriteria().list(){
				like('descripcion','%'+params.term+'%')
		}
		log.debug "ESPECIALIDADES LISTADAS: "+list.size()
		render(contentType:"text/json"){
			array{
				for (esp in list){
					especialidad id:esp.id,label:esp.descripcion
				}
			}
			
		}
	}
}
