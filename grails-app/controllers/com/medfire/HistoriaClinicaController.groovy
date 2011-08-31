package com.medfire

import com.medfire.enums.ImpresionVademecumEnum 
import com.medfire.util.GUtilDomainClass
import java.text.SimpleDateFormat
import java.text.DateFormat
import java.text.ParseException;


class HistoriaClinicaController {

	def imageUploadService
	def historiaClinicaService
	def authenticateService 
	
	static allowedMethods = [save:"POST",update: "POST", delete: "POST"]

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
		def pacienteInstance = Paciente.load(params.pacienteId.toLong())
		def consultaInstance = new Consulta()
		return [pacienteInstance: pacienteInstance, consultaInstance:consultaInstance]
	}

	def save = {
		log.info "INGRESANDO AL CLOSURE save DEL CONTROLLER HistoriaClinicaController"
		log.info "PARAMETROS $params"
		
		//---pasear json de la grilla de prescripciones----
		def prescripcionesjson
		if(params.prescripciones)
			prescripcionesjson = grails.converters.JSON.parse(params.prescripciones)
		
		if (params.consulta.fechaConsulta){
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy")
			def fecha
			try{
				fecha = df.parse(params.consulta.fechaConsulta)
				log.debug "LA FECHA SE PARSEO BIEN"
			}catch(ParseException e){
				log.debug "LA FECHA NO SE PARSEO BIEN"
			}
			def gc = Calendar.getInstance()
			gc.setTime(fecha)
			log.debug "ANIO: "+gc.get(Calendar.YEAR).toString()+", MES "+gc.get(Calendar.MONTH+1).toString()+" DIA "+gc.get(Calendar.DATE).toString()
			params.consulta.fechaConsulta_year=gc.get(Calendar.YEAR).toString()
			params.consulta.fechaConsulta_month=(gc.get(Calendar.MONTH)+1).toString()
			params.consulta.fechaConsulta_day=gc.get(Calendar.DATE).toString()
		}
		def consultaInstance = new Consulta(params.consulta)
		def userInstance = User.load(authenticateService.userDomain().id)
		def profesionalInstance = Profesional.load(userInstance.profesionalAsignado.id)
		consultaInstance.profesional=profesionalInstance
		def pacienteInstance = Paciente.get(params.pacienteId.toLong())
		pacienteInstance.properties = params.paciente
		consultaInstance.paciente=pacienteInstance
		def estudioImagen
		
		params.imagen.each{
			if(!it.value.isEmpty()){
				log.debug "IMAGEN AGREGADA "+it.value.class+" it: "+it.class
				//consultaInstance.addToEstudios(new EstudioComplementario(imagen:request.getFile(it.value.getName)))
				consultaInstance.addToEstudios(new EstudioComplementario(imagen:it.value))
			}else
				log.debug "IMAGEN VACIA"	
		}
		
		prescripcionesjson.each{ 
			log.debug "OBJETO JSON DE LA PRESCRIPCION: $it"
			consultaInstance.addToPrescripciones(new Prescripcion(nombreComercial:it.nombreComercial
					,nombreGenerico:it.nombreGenerico,presentacion:it.presentacion,impresion:ImpresionVademecumEnum."${it.imprimirPorValue}",cantidad:it.cantidad,secuencia:it.id))
		}

		try{
			consultaInstance=historiaClinicaService.registrarVisita(consultaInstance)
			flash.message = "${message(code: 'default.created.message', args: [message(code: 'consulta.label', default: 'Consulta'), consultaInstance.id])}"
			//redirect(action: "show", id: consultaInstance.id)
			log.debug "REENDERIZANDO EL INPUT TEXT CON EL ID CARGADO, CONSULTA GUARDADA ${consultaInstance.id}"
			render "<input type='text' id='consultasalvadaId'  name='consultasalvada' value='${consultaInstance.id}' />"

		}catch(ConsultaException e){
			log.error "ERROR DE AL TRATAR DE GUARDAR LA CONSULTA DE VISITA: "+e.consulta.errors.allErrors
			log.error "MENSAJE DE VALIDACION: "+e.message
			//render(view:"create", model:[consultaInstance: e.consulta, pacienteInstance:pacienteInstance, prescripciones:prescripcionesjson])
			def consultaE=e.consulta
			render " <div class='ui-state-error ui-corner-all' style='padding: 0pt 0.7em;'>	${g.renderErrors(bean:e.consulta)}<br/> ${g.renderErrors(bean:e.consulta.paciente)} </div>	"
					/*errors{
						g.eachError(bean:consultaE){
							title g.message(error:it)
						}
					}*/
					//errors consultaE.errors.allErrors
			
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
			flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'consulta.label', default: 'Historia Clinica'), params.id])}"
			redirect(action: "list")
		}
		else {
			return [consultaInstance: consultaInstance]
		}
	}

	def update = {
		def historiaClinicaInstance = HistoriaClinica.get(params.id)
		if (historiaClinicaInstance) {
			if (params.version) {
				def version = params.version.toLong()
				if (historiaClinicaInstance.version > version) {
					
					historiaClinicaInstance.
					errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'historiaClinica.label', default: 'HistoriaClinica')] as Object[], "Another user has updated this HistoriaClinica while you were editing")
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
	
	def editprescripciones = {
		render(contentType:"text/json"){
			array{
				
			}
		}
		
	}
	
	def listprescripciones = {
		log.info "INGRESANDO AL CLOSURE listprescripciones"
		log.info "PARAMETROS: $params"
		def consultaInstance
		def totalRegistros = 0
		if(params.id){
			consultaInstance = Consulta.load(params.id.toLong())
			totalRegistros = consultaInstance.prescripciones?.size()
		}
			
		def result='{"page":"1","total":"'+totalRegistros+'","records":"'+totalRegistros+'","rows":['
		def flagaddcomilla=false
		consultaInstance?.prescripciones?.each { 
			if (flagaddcomilla)
				result=result+','

			result=result+'{"id":"'+it.id+'","cell":["'+it.id+'","'+(it.nombreComercial!=null?it.nombreComercial:"")+'","'+(it.nombreGenerico!=null?it.nombreGenerico:"")+'","'+(it.presentacion!=null?it.presentacion:"")+'","'+(it.cantidad!=null?it.cantidad:"")+'","'+(it.impresion!=null?it.impresion:"")+'","'+(it.impresion.name!=null?it.impresion.name:"")+'"]}'
			flagaddcomilla=true
		}
		result=result+']}'
		render result
	}
	
}
