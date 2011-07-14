package com.medfire

import com.medfire.util.GUtilDomainClass
import java.text.SimpleDateFormat
import java.text.DateFormat
import java.text.ParseException


class HistoriaClinicaController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }
 
    def list = {
		log.info "INGRESANDO AL CLOSURE iist DEL CONTROLLER HistoriaClinicaController"
		log.info "SOLO RENDERIZA LA PAGINA DE LIST"
        //params.max = Math.min(params.max ? params.int('max') : 10, 100)
       // [historiaClinicaInstanceList: HistoriaClinica.list(params), historiaClinicaInstanceTotal: HistoriaClinica.count()]
		
    }

    def create = {
		log.info "INGRESANDO AL CLOSURE create DEL CONTROLLER HistoriaClinicaController"
		log.info "PARAMETROS $params"
		def pacienteInstance = Paciente.load(params.paciente.id.toLong())
		def consultaInstance = new Consulta()
        return [pacienteInstance: pacienteInstance, consultaInsttance:consultaInstance]
    }

    def save = {
		log.info "INGRESANDO AL CLOSURE save DEL CONTROLLER HistoriaClinicaController"
		log.info "PARAMETROS $params"
		def consultaInstance = new Consulta(params.consulta)
		
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

		def pacienteInstance = new Paciente(params)
		historiaClinicaInstance.addToConsultas(consultaInstance)
				
        if (historiaClinicaInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'historiaClinica.label', default: 'HistoriaClinica'), historiaClinicaInstance.id])}"
            redirect(action: "show", id: historiaClinicaInstance.id)
        }
        else {
			
            render(view: "create", model: [historiaClinicaInstance: historiaClinicaInstance,consultaInstace:consultaInstance])
        }
    }

    def show = {
        def historiaClinicaInstance = HistoriaClinica.get(params.id)
        if (!historiaClinicaInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'historiaClinica.label', default: 'HistoriaClinica'), params.id])}"
            redirect(action: "list")
        }
        else {
            [historiaClinicaInstance: historiaClinicaInstance]
        }
    }

    def edit = {
        def historiaClinicaInstance = HistoriaClinica.get(params.id)
        if (!historiaClinicaInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'historiaClinica.label', default: 'HistoriaClinica'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [historiaClinicaInstance: historiaClinicaInstance]
        }
    }

    def update = {
        def historiaClinicaInstance = HistoriaClinica.get(params.id)
        if (historiaClinicaInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (historiaClinicaInstance.version > version) {
                    
                    historiaClinicaInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'historiaClinica.label', default: 'HistoriaClinica')] as Object[], "Another user has updated this HistoriaClinica while you were editing")
                    render(view: "edit", model: [historiaClinicaInstance: historiaClinicaInstance])
                    return
                }
            }
            historiaClinicaInstance.properties = params
            if (!historiaClinicaInstance.hasErrors() && historiaClinicaInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'historiaClinica.label', default: 'HistoriaClinica'), historiaClinicaInstance.id])}"
                redirect(action: "show", id: historiaClinicaInstance.id)
            }
            else {
                render(view: "edit", model: [historiaClinicaInstance: historiaClinicaInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'historiaClinica.label', default: 'HistoriaClinica'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def historiaClinicaInstance = HistoriaClinica.get(params.id)
        if (historiaClinicaInstance) {
            try {
                historiaClinicaInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'historiaClinica.label', default: 'HistoriaClinica'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'historiaClinica.label', default: 'HistoriaClinica'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'historiaClinica.label', default: 'HistoriaClinica'), params.id])}"
            redirect(action: "list")
        }
    }
	
	def listjson = {
		log.info "INGRESANDO AL CLOSURE listjson DEL CONTROLLER HistoriaClinicaController"
		log.info "PARAMETROS ${params}"	
		def list
		def gud = new GUtilDomainClass(Paciente,params,grailsApplication)
		list=gud.listrefactor(false)
		def totalregistros=gud.listrefactor(true)
		
			
		def totalpaginas=new Float(totalregistros/Integer.parseInt(params.rows))
		if (totalpaginas>0 && totalpaginas<1)
			totalpaginas=1;
		totalpaginas=totalpaginas.intValue()

		
		
		
		def result='{"page":'+params.page+',"total":"'+totalpaginas+'","records":"'+totalregistros+'","rows":['

		def flagaddcomilla=false
		
		
		list.each{
			
			if (flagaddcomilla)
				result=result+','
			
			result=result+'{"id":"'+it.id+'","cell":["'+it.id+'","'+(it.apellido!=null?it.apellido:"")+'","'+(it.nombre!=null?it.nombre:"")+'","'+(it.id!=null?it.id:"")+'"]}'
			 
			flagaddcomilla=true
		}
		result=result+']}'
		render result

		
	}
}
