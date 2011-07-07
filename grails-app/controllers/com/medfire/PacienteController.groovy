package com.medfire

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Calendar;
import com.medfire.util.GUtilDomainClass
import grails.converters.JSON
import org.springframework.transaction.TransactionStatus

class PacienteController {
	def grailsApplication	

	
	
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [pacienteInstanceList: Paciente.list(params), pacienteInstanceTotal: Paciente.count()]
    }

    def create = {
		log.debug  "INGRESANDO AL METODO create DEL CONTROLLER PacienteController"
		log.debug "PARAMETROS: $params"
		java.util.Date fecha = new java.util.Date(1296615600)
		log.debug "FECHA $fecha"
        def pacienteInstance = new Paciente()
        pacienteInstance.properties = params
        return [pacienteInstance: pacienteInstance,eventId:params.eventId,eventVersion:params.eventVersion]
    }

    def save = {
    	log.debug "INGRESANDO AL METODO save DEL CONTROLLER PacienteController"
    	log.debug "PARAMETROS $params"

        
    	
    	if (params.fechaNacimiento){
	    	DateFormat df = new SimpleDateFormat("dd/MM/yyyy")
	    	log.debug "FECHA REEMPLAZADA: "+params.fechaNacimiento
	    	def fecha
	    	try{ 
	    		fecha = df.parse(params.fechaNacimiento)
				log.debug "LA FECHA SE PARSEO BIEN"    		
	    	}catch(ParseException e){
	    		log.debug "LA FECHA NO SE PARSEO BIEN"
	    	}
	    	def gc = Calendar.getInstance()
			gc.setTime(fecha)
			log.debug "ANIO: "+gc.get(Calendar.YEAR).toString()+", MES "+gc.get(Calendar.MONTH+1).toString()+" DIA "+gc.get(Calendar.DATE).toString()						
			params.fechaNacimiento_year=gc.get(Calendar.YEAR).toString()
			params.fechaNacimiento_month=(gc.get(Calendar.MONTH)+1).toString()
			params.fechaNacimiento_day=gc.get(Calendar.DATE).toString()
		}
		def pacienteInstance = new Paciente(params)
		if(params.obraSocial?.id)
			pacienteInstance.obraSocial=ObraSocial.load(new Long(params.obraSocial.id))
		params.each{field,value->
			if(value instanceof String){
				value = value.toUpperCase()
			}
		}
		
		if (params.eventId){
			Event.withTransaction(){TransactionStatus status ->
				def eventInstance = Event.get(params.eventId)
				if(eventInstance){
					if (params.eventVersion) {
						def version = params.eventVersion.toLong()
						if (eventInstance.version > version) {
							
							//eventInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'event.label', default: 'Event')] as Object[], "Another user has updated this Event while you were editing")
							//render(view: "edit", model: [eventInstance: eventInstance])
							status.setRollbackOnly()
							flash.message = "Al turno seleccionado no se le puede asociar un nuevo paciente porque esta siendo modificado por otro usurio"
							render(view:"create",model:[pacienteInstance])
							return
						}
						if (pacienteInstance.save(flush: true)) {
							eventInstance.paciente=pacienteInstance
							eventInstance.titulo=pacienteInstance.apellido+'-'+pacienteInstance.nombre
							eventInstance.save()
							flash.message = "${message(code: 'default.created.message', args: [message(code: 'paciente.label', default: 'Paciente'), pacienteInstance.id])}"
							redirect(action: "show", id: pacienteInstance.id)
							return
						}else {
							log.debug "ERRORES: "+pacienteInstance.errors.allErrors
							def listlocalidades
							if(params.localidad?.id)
								listlocalidades = Localidad.createCriteria().list(){
									eq("id",new Long(params.localidad.id))
								}
							render(view: "create", model: [pacienteInstance: pacienteInstance,localidades:listlocalidades])
							return
						}
			
					}
					
				}else{
					status.setRollbackOnly()
					flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'event.label', default: 'Event'), params.eventId])}"
					render(view:"create",model:[pacienteInstance])
				}
			}
			
		}else{
	        if (pacienteInstance.save(flush: true)) {
	            flash.message = "${message(code: 'default.created.message', args: [message(code: 'paciente.label', default: 'Paciente'), pacienteInstance.id])}"
	            redirect(action: "show", id: pacienteInstance.id)
	        }
	        else {
				log.debug "ERRORES: "+pacienteInstance.errors.allErrors
				def listlocalidades
				if(params.localidad?.id)
					listlocalidades = Localidad.createCriteria().list(){
						eq("id",new Long(params.localidad.id))
					}
	            render(view: "create", model: [pacienteInstance: pacienteInstance,localidades:listlocalidades])
	        }
		}
    }

    def show = {
        def pacienteInstance = Paciente.get(params.id)
        if (!pacienteInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'paciente.label', default: 'Paciente'), params.id])}"
            redirect(action: "list")
        }
        else {
            [pacienteInstance: pacienteInstance]
        }
    }

    def edit = {
        def pacienteInstance = Paciente.get(params.id)
        if (!pacienteInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'paciente.label', default: 'Paciente'), params.id])}"
            redirect(action: "list")
        }
        else {
			def listlocalidades
			if(pacienteInstance.localidad)
				listlocalidades = Localidad.createCriteria().list(){
					eq("id",new Long(pacienteInstance.localidad.id))
				}
            return [pacienteInstance: pacienteInstance,localidades:listlocalidades]
        }
    }

    def update = {
    	log.debug "INGRESANDO AL METODO UPDATE DEL CONTROLLER PacienteController"
    	log.debug "PARAMETROS $params"
        def pacienteInstance = Paciente.get(params.id)
        if (pacienteInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (pacienteInstance.version > version) {
                    
                    pacienteInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'paciente.label', default: 'Paciente')] as Object[], "Otro usuario ha modificado el registro del Paciente mientras usted estuvo editando")
                    render(view: "edit", model: [pacienteInstance: pacienteInstance])
                    return
                }
            }
			
			if (params.fechaNacimiento){
				DateFormat df = new SimpleDateFormat("dd/MM/yyyy")
				log.debug "FECHA REEMPLAZADA: "+params.fechaNacimiento
				def fecha
				try{
					fecha = df.parse(params.fechaNacimiento)
					log.debug "LA FECHA SE PARSEO BIEN"
				}catch(ParseException e){
					log.debug "LA FECHA NO SE PARSEO BIEN"
				}
				def gc = Calendar.getInstance()
				gc.setTime(fecha)
				log.debug "ANIO: "+gc.get(Calendar.YEAR).toString()+", MES "+gc.get(Calendar.MONTH+1).toString()+" DIA "+gc.get(Calendar.DATE).toString()
				params.fechaNacimiento_year=gc.get(Calendar.YEAR).toString()
				params.fechaNacimiento_month=(gc.get(Calendar.MONTH)+1).toString()
				params.fechaNacimiento_day=gc.get(Calendar.DATE).toString()
			}
			
            pacienteInstance.properties = params
            if (!pacienteInstance.hasErrors() && pacienteInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'paciente.label', default: 'Paciente'), pacienteInstance.id])}"
                redirect(action: "show", id: pacienteInstance.id)
            }
            else {
                render(view: "edit", model: [pacienteInstance: pacienteInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'paciente.label', default: 'Paciente'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def pacienteInstance = Paciente.get(params.id)
        if (pacienteInstance) {
            try {
                pacienteInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'paciente.label', default: 'Paciente'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'paciente.label', default: 'Paciente'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'paciente.label', default: 'Paciente'), params.id])}"
            redirect(action: "list")
        }
    }
	
	def listjson = {
		log.info "INGRESANDO AL CLOSURE listjson DEL CONTROLLER PacienteController"
		log.info "PARAMETROS $params"
		def filtersJson
		if(params.filters)
			 filtersJson = JSON.parse(params.filters)
		
		log.debug "JSON GENERADO: ${filtersJson}"
		
		def list
		def gud = new GUtilDomainClass(Paciente,params,grailsApplication) 
		list=gud.listrefactor(false)
		def totalregistros=gud.listrefactor(true)
		
			
		def totalpaginas=new Float(totalregistros/Integer.parseInt(params.rows))
		if (totalpaginas>0 && totalpaginas<1)
			totalpaginas=1;
		totalpaginas=totalpaginas.intValue()

		
		log.debug "TOTAL PACIENTES: "+list.size()
		
		
		def result='{"page":'+params.page+',"total":"'+totalpaginas+'","records":"'+totalregistros+'","rows":['
		log.debug "CONSULTA DE PACIENTES: $list"
		def flagaddcomilla=false
		
		
		list.each{
			
			if (flagaddcomilla)
				result=result+','
			
			result=result+'{"id":"'+it.id+'","cell":["'+it.id+'","'+(it.dni!=null?it.dni:"")+'","'+(it.apellido!=null?it.apellido:"")+'","'+(it.nombre!=null?it.nombre:"")+'","'+(it.domicilio!=null?it.domicilio:"")+'","'+(it.telefono!=null?it.telefono:"")+'","'+(it.codigoPostal!=null?it.codigoPostal:"")+'"]}'
			 
			flagaddcomilla=true
		}
		result=result+']}'
		render result

	}
	
	def listjsonautocomplete={
		log.info "INGRESANDO AL METODO listjsonautocomplete DEL CONTROLLER PacienteController"
		log.info "PARAMETROS $params"
		
		def pacientes = Paciente.createCriteria().list(){
			or{
				like('apellido','%'+params.term+'%')
				like('nombre','%'+params.term+'%')
			}
		}
		log.debug "PACIENTES LISTADOS: "+pacientes.size()
		render(contentType:"text/json"){
			array{
				for (pac in pacientes){
					paciente id:pac.id,label:pac.apellido+'-'+pac.nombre,value:pac.apellido+'-'+pac.nombre
				}
			}
			
		}
	}
	
	
}
