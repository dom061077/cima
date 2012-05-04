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
	def authenticateService	

	
	
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
		def eventInstance 
		if(params.eventId){
			eventInstance = Event.load(params.eventId.toLong())
		}
        return [pacienteInstance: pacienteInstance,eventInstance:eventInstance]
    }

    def save = {
    	log.debug "INGRESANDO AL METODO save DEL CONTROLLER PacienteController"
    	log.debug "PARAMETROS $params"
		def pacienteSalvado
		def fechaNacimientoError=false
		def fechaIngresoError=false

    	
    	if (params.fechaNacimiento){
			if (params.fechaNacimiento.length()<10){
				fechaNacimientoError=true
			}else{
				//log.debug "Fecha Nacimiento length: "+params.fechaNacimiento.length()
				params.fechaNacimiento_year=params.fechaNacimiento.substring(6,10)
				params.fechaNacimiento_month=params.fechaNacimiento.substring(3,5)
				params.fechaNacimiento_day=params.fechaNacimiento.substring(0,2)
				try{
					if(params.fechaNacimiento_month.toInteger()>12)
						fechaNacimientoError=true
					if(params.fechaNacimiento_day.toInteger()>31){
						fechaNacimientoError=true
					}
				}catch(NumberFormatException e){
					fechaNacimientoError=true
				}
			}
		}
		def eventInstance 
		if(params.eventId)
			eventInstance = Event.get(params.eventId)
		
		def pacienteInstance = new Paciente(params)
		
		pacienteInstance.institucion = authenticateService.userDomain().institucion
		
		if(fechaNacimientoError){
			pacienteInstance.validate()
			pacienteInstance.errors.rejectValue("fechaNacimiento","com.medfire.Profesional.fechaNacimiento.date.error","Ingrese una fecha correcta, se sugiere una correci�n")
			log.debug "ERROR EN FECHA DE NACIMIENTO SEGUN BANDERA"
			render(view: "create", model: [pacienteInstance: pacienteInstance,eventInstance:eventInstance,localidades:listlocalidades])
			return
		}

		if(params.obraSocial?.id)
			pacienteInstance.obraSocial=ObraSocial.load(new Long(params.obraSocial.id))
		params.each{field,value->
			if(value instanceof String){
				value = value.toUpperCase()
			}
		}
		
		if (params.eventId){
			log.debug "INGRESANDO POR LA TRANSACCION DEL EVENTO"
			Event.withTransaction(){TransactionStatus status ->
				log.debug "DENTRO DE LA TRANSACCION"
				
				if(eventInstance){
					if (params.eventVersion) {
						def version = params.eventVersion.toLong()
						log.debug "ANTES DE LA VALIDACION DE VERSION"
						if (eventInstance.version > version) {
							log.debug "DETECCION DE DIFERENCIA DE VERSION"
							status.setRollbackOnly()
							flash.message = "Al turno seleccionado no se le puede asociar un nuevo paciente porque esta siendo modificado por otro usuario"
							render(view:"create",model:[pacienteInstance:pacienteInstance,eventInstance:eventInstance,localidades:listlocalidades])
							return
						}
						log.debug "ANTES DE SALVAR AL PACIENTE"
						pacienteSalvado = pacienteInstance.save(flush: true) 
						log.debug "PACIENTE SALVADO"
						if (pacienteSalvado) {
							log.debug "PACIENTE GUARDADO"
							eventInstance.paciente=pacienteSalvado
							eventInstance.titulo=pacienteSalvado.apellido+'-'+pacienteSalvado.nombre
							eventInstance.save()
							flash.message = "${message(code: 'default.created.message', args: [message(code: 'paciente.label', default: 'Paciente'), pacienteInstance.id])}"
							redirect(action: "show", id: pacienteInstance.id)
							log.debug "REDIRECCIONANDO..."
							return
						}else {
							log.debug "ERRORES: "+pacienteInstance.errors.allErrors
							def listlocalidades
							if(params.localidad?.id)
								listlocalidades = Localidad.createCriteria().list(){
									eq("id",new Long(params.localidad.id))
								}
							//redirect(controller:"paciente",action: "create",params:[eventId:eventInstance.id,eventVersion:eventInstance.version])
							render(view: "create", model: [pacienteInstance: pacienteInstance,eventInstance:eventInstance,localidades:listlocalidades])
							return
						}
					}else{
						pacienteInstance.errors.rejectValue("version", "", [message(code: 'paciente.label', default: 'Paciente')] as Object[], "No se puede determinar la última versión del turno seleccionado")
						render(view:"create",model:[pacienteInstance:pacienteInstance,eventInstance:eventInstance,localidades:listlocalidades])
						return
					}
				}else{
					log.debug "ROLLBACK DE LA TRANSACCION"
					status.setRollbackOnly()
					flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'event.label', default: 'Event'), params.eventId])}"
					render(view:"create",model:[pacienteInstance:pacienteInstance,eventInstance:eventInstance,localidades:listlocalidades])
					return
				}
				log.debug "DESPUES DE IF Y ELSE DE VALIDACION"
			}
			log.debug "SALE DE LA TRANSACCION..."
		}else{
	        if (pacienteInstance.save(flush: true)) {
	            flash.message = "${message(code: 'default.created.message', args: [message(code: 'paciente.label', default: 'Paciente'), pacienteInstance.id])}"
	            redirect(action: "show", id: pacienteInstance.id)
				return
	        }
	        else {
				log.debug "ERRORES: "+pacienteInstance.errors.allErrors
				def listlocalidades
				if(params.localidad?.id)
					listlocalidades = Localidad.createCriteria().list(){
						eq("id",new Long(params.localidad.id))
					}
	            render(view: "create", model: [pacienteInstance: pacienteInstance,eventInstance:eventInstance,localidades:listlocalidades])
				return
	        }
		}
		log.debug "RETORNO SIN REGISTRAR DESDE EL SAVE"
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
		def fechaNacimientoError = false
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
				if (params.fechaNacimiento.length()<10){
					fechaNacimientoError=true
				}else{
					log.debug "Fecha Nacimiento length: "+params.fechaNacimiento.length()
					params.fechaNacimiento_year=params.fechaNacimiento.substring(6,10)
					params.fechaNacimiento_month=params.fechaNacimiento.substring(3,5)
					params.fechaNacimiento_day=params.fechaNacimiento.substring(0,2)
					try{
						if(params.fechaNacimiento_month.toInteger()>12)
							fechaNacimientoError=true
						if(params.fechaNacimiento_day.toInteger()>31){
							fechaNacimientoError=true
						}
					}catch(NumberFormatException e){
						fechaNacimientoError=true
					}
				}
			}
			
            pacienteInstance.properties = params
			pacienteInstance.obraSocial = ObraSocial.load(params.obraSocialId)
			if(fechaNacimientoError){
				pacienteInstance.validate()
				pacienteInstance.errors.rejectValue("fechaNacimiento","com.medfire.Profesional.fechaNacimiento.date.error","Ingrese una fecha correcta, se sugiere una correción")
				log.debug "ERROR EN FECHA DE NACIMIENTO SEGUN BANDERA"
				render(view: "edit", model: [pacienteInstance: pacienteInstance])
				return
			}
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
		def institucionInstance = authenticateService.userDomain().institucion
		params.altfilters = """{'groupOp':'AND','rules':[{'field':'institucion_id','op':'eq','data':'${institucionInstance.id}'}]}"""
		params._search = "true"

		
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

	def listsearchjson = {
		log.info "INGRESANDO AL CLOSURE listsearchjson"
		log.info "PARAMETROS ${params}"
		def gud=new GUtilDomainClass(Paciente,params,grailsApplication)
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
			
			result=result+'{"id":"'+it.id+'","cell":["'+it.id+'","'+it.apellido+'","'+it.nombre+'"]}'
			 
			flagaddcomilla=true
		}
		result=result+']}'
		render result

	}

	
}
