package com.medfire

import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.ArrayList

import com.medfire.enums.ImpresionVademecumEnum
import com.medfire.util.GUtilDomainClass
import pl.burningice.plugins.image.container.ContainerUtils


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
		def userInstance = User.load(authenticateService.userDomain().id)

		if(!userInstance.profesionalAsignado){
			flash.message = "No tiene un profesional asignado"
			redirect(action: "list", params: params)
			return
		} 
		def pacienteInstance
		pacienteInstance= Paciente.get(params.pacienteId)
		if(!pacienteInstance){
			log.error "PACIENTE CON ID $params.pacienteId NO ENCONTRADO"
			flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'paciente.label', default: 'Paciente'), params.id])}"
			redirect(action: "list")
			return
		}
		def eventInstance
		if(params.eventId){
			eventInstance = Event.get(params.eventId)
			if(!eventInstance){
				log.error "EVENTO ENVIADO CON ID ${params.eventId} NO ENCONTRADO"
				flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'event.label', default: 'Turno'), params.id])}"
				redirect(action: "list")
				return
			}
		}
		
		def consultaInstance = new Consulta()
			
		return [pacienteInstance: pacienteInstance, consultaInstance:consultaInstance,eventInstance:eventInstance]
	}

	def save = {
		log.info "INGRESANDO AL CLOSURE save DEL CONTROLLER HistoriaClinicaController"
		log.info "PARAMETROS $params"
		

		
		//---pasear json de la grilla de prescripciones----
		def prescripcionesjson
		if(params.prescripciones)
			prescripcionesjson = grails.converters.JSON.parse(params.prescripciones)
		def fechaConsultaError = false
		if (params.consulta.fechaConsulta){
			if(params.consulta.fechaConsulta.length()<10){
				fechaConsultaError = true
			}else{
				params.consulta.fechaConsulta_year=params.consulta.fechaConsulta.substring(6,10)
				params.consulta.fechaConsulta_month=params.consulta.fechaConsulta.substring(3,5)
				params.consulta.fechaConsulta_day=params.consulta.fechaConsulta.substring(0,2)
				try{
					if(params.consulta.fechaConsulta_month.toInteger()>12)
						fechaConsultaError=true
					if(params.consulta.fechaConsulta_day.toInteger()>31){
						fechaConsultaError=true
					}
				}catch(NumberFormatException e){
					fechaConsultaError=true
				}
			}
		}

		
		if(fechaConsultaError){
			log.debug "ERROR EN LA FECHA DE CONSULTA SEGUN BANDERA"
			if(params.eventId){
				eventInstance = Event.get(params.eventId)
			}	
			//consultaInstance.errors.rejectValue("fechaConsulta","com.medfire.Consulta.fechaConsulta.date.error")
			render " <div class='ui-state-error ui-corner-all' style='padding: 0pt 0.7em;'>	<ul><li>${g.message(code:'com.medfire.Consulta.fechaConsulta.date.error')}</li></ul></div>	"
			return 
		}

		def consultaInstance = new Consulta(params.consulta)
		def pacienteInstance = Paciente.get(params.pacienteId.toLong())
		def eventInstance
		def userInstance = User.load(authenticateService.userDomain().id)
		def profesionalInstance = Profesional.load(userInstance.profesionalAsignado.id)
		consultaInstance.profesional=profesionalInstance

				
		pacienteInstance.properties = params.paciente
		if(params.pacienteVersion){
			def pacienteVersion = params.pacienteVersion.toLong()
				if(pacienteInstance.version > pacienteVersion){
				pacienteInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'paciente.label', default: 'Paciente')] as Object[], "Another user has updated this Turno while you were editing")
				render " <div class='ui-state-error ui-corner-all' style='padding: 0pt 0.7em;'>	${g.renderErrors(bean:pacienteInstance)}<br/> ${g.renderErrors(bean:consultaInstance.paciente)} </div>	"
				return
			}
		}
		consultaInstance.paciente=pacienteInstance
		
		def i=1
		def estudioImagen
		def estudio
		def secuencia=1
		params.consulta.estudio.each{
			try{
				log.debug "PEDIDO: "+it.value.pedido
				log.debug "RESULTADO: "+it.value.resultado
				log.debug "KEY: "+it.key
				estudio = new EstudioComplementario(pedido:it.value.pedido,resultado:it.value.resultado,secuencia:it.key.toInteger()) 	
				it.value?.imagen?.each{ image ->
					log.debug "ITERANDO IMAGENES: "+image.class
					if(!image.value.isEmpty()){
						log.debug "IMAGEN AGREGADA"
						estudio.addToImagenes(new EstudioComplementarioImagen(imagen:image.value,secuencia:image.key.toInteger()))
					} 
				}
				consultaInstance.addToEstudios(estudio)
				secuencia++
			}catch(Exception e){
				//log.debug "EXCEPCION LANZADA, ESTRUCTURA: "+it.properties
			}
//			it.value.imagen.each{
//				if(!it.value.isEmpty()){
//					log.debug "IMAGEN AGREGADA "+it.value.class+" it: "+it.class
//					//consultaInstance.addToEstudios(new EstudioComplementario(imagen:request.getFile(it.value.getName)))
//					consultaInstance.addToEstudios(new EstudioComplementario(imagen:it.value))
//				}else
//					log.debug "IMAGEN VACIA"
//				log.debug "IMAGEN: $i"		
//			}
		}
		
		//------------si tiene un turno o evento asignado, le cambio el estado-----------
		if(params.eventId){
			eventInstance = Event.get(params.eventId)
			if(params.eventVersion){
				def versionEvent = params.eventVersion.toLong()
				if(eventInstance.version > versionEvent){
					consultaInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'event.label', default: 'Turno')] as Object[], "Another user has updated this Turno while you were editing")
					render " <div class='ui-state-error ui-corner-all' style='padding: 0pt 0.7em;'>	${g.renderErrors(bean:consultaInstance)}<br/> ${g.renderErrors(bean:consultaInstance.paciente)} </div>	"
					return
				}
			}
		}
		
		prescripcionesjson.each{ 
			log.debug "OBJETO JSON DE LA PRESCRIPCION: $it"
			consultaInstance.addToPrescripciones(new Prescripcion(nombreComercial:it.nombreComercial
					,nombreGenerico:it.nombreGenerico,presentacion:it.presentacion,impresion:ImpresionVademecumEnum."${it.imprimirPorValue}",cantidad:it.cantidad,secuencia:it.id))
		}

		try{
			consultaInstance=historiaClinicaService.registrarVisita(consultaInstance,eventInstance)
			flash.message = "${message(code: 'default.created.message', args: [message(code: 'consulta.label', default: 'Consulta'), consultaInstance.id])}"
			//redirect(action: "show", id: consultaInstance.id)
			log.debug "REENDERIZANDO EL INPUT TEXT CON EL ID CARGADO, CONSULTA GUARDADA ${consultaInstance.id}"
			render "<input type='text' id='consultasalvadaId'  name='consultasalvada' value='${consultaInstance.id}' />"

		}catch(ConsultaException e){
			log.error "ERROR DE AL TRATAR DE GUARDAR LA CONSULTA DE VISITA: "+e.consulta.errors.allErrors
			log.error "MENSAJE DE ERROR: "+e.message
			//render(view:"create", model:[consultaInstance: e.consulta, pacienteInstance:pacienteInstance, prescripciones:prescripcionesjson])
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
		log.info "INGRESANDO AL CLOSURE edit"
		log.info "PARAMETROS: $params"
		def userInstance = authenticateService.userDomain()
		
		def consultaInstance = Consulta.get(params.id)
		if (!consultaInstance) {
			flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'consulta.label', default: 'Historia Clinica'), params.id])}"
			redirect(action: "list")
		}
		else {
			if(!consultaInstance.profesional.id.equals(userInstance.profesionalAsignado.id)){
				flash.message = "Solo puede modificar las consultas que Ud. atendió "
				redirect(action:"list")	
			}else
				return [consultaInstance: consultaInstance]
		}
	}

	def update = {
		log.info "INGRESANDO AL CLOSURE update"
		log.info "PARAMS: $params"
		log.debug "ID: ${params.id}"
		
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

		
		def consultaInstance = Consulta.get(params.id)
		def estudioInstance
		def estudioImagenInstance
		if (consultaInstance) { 
			
			if (params.version) {
				def version = params.version.toLong()
				if (consultaInstance.version > version) {
					errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'historiaClinica.label', default: 'HistoriaClinica')] as Object[], "Another user has updated this HistoriaClinica while you were editing")
					//render(view: "edit", model: [historiaClinicaInstance: historiaClinicaInstance])
					render " <div class='ui-state-error ui-corner-all' style='padding: 0pt 0.7em;'>	${g.renderErrors(bean:consultaInstance)}<br/> ${g.renderErrors(bean:consultaInstance.paciente)} </div>	"
					return
				}
			}
			try{
				consultaInstance=historiaClinicaService.updateVisita(consultaInstance,params)
				flash.message = "${message(code: 'default.updated.message', args: [message(code: 'historiaClinica.label', default: 'HistoriaClinica'), consultaInstance?.id])}"
				render "<input type='text' id='consultasalvadaId'  name='consultasalvada' value='${consultaInstance?.id}' />"
				//redirect(action: "show", id: consultaInstance.id)
			}catch(ConsultaException e){
				log.error "MENSAJE DE ERROR: "+e.message
				render " <div class='ui-state-error ui-corner-all' style='padding: 0pt 0.7em;'>	${g.renderErrors(bean:e.consulta)}<br/> ${g.renderErrors(bean:e?.consulta?.paciente)} <br/> ${g.renderErrors(bean:e?.estudioComplementario)}<br/> ${g.renderErrors(bean:e?.estudioComplementarioImagen)} </div>	"
			}
		}
		else {
			flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'historiaClinica.label', default: 'HistoriaClinica'), params.id])}"
			redirect(action: "list")
		}
	}

	def delete = {
		log.info "INGRESANDO EL CLOSURE delete"
		log.info "PARAMETROS: $params"
		def consultaInstance = Consulta.get(params.id)
		def userInstance = authenticateService.userDomain()
		if (consultaInstance) {
			if(!consultaInstance.profesional.id.equals(userInstance.profesionalAsignado.id)){
				flash.message = "Solo puede modificar las consultas que Ud. atendió"
				redirect(action:"list")
				return
			}
			try {
				historiaClinicaService.deleteVisita(consultaInstance)
				log.info "OPERACION EJECUTADA CORRECTAMENTE"
				flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'historiaClinica.label', default: 'HistoriaClinica'), params.id])}"
				redirect(action: "list")
			}
			catch (org.springframework.dao.DataIntegrityViolationException e) {
				log.error "CONSTRAINT VIOLATION PARA ID: ${consultaInstance.id} - "+e.message
				flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'historiaClinica.label', default: 'HistoriaClinica'), params.id])}"
				redirect(action: "show", id: params.id)
			}
		}
		else {
			log.warn "NO SE ENCONTRO LA INSTANCIA CON ID ${consultaInstance.id} - DELETE CANCELADO"
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
			
			result=result+'{"id":"'+it.id+'","cell":["'+g.formatNumber(number:it.id,format:'000000')+'","'+(it.apellido!=null?it.apellido:"")+'","'+(it.nombre!=null?it.nombre:"")+'"]}'
			 
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
	
	def reportecontenidovisita = {
		log.info "INGRESANDO AL CLOSURE reportecontenidovisita"
		log.info "PARAMETROS : $params"
		def consultaInstance = Consulta.load(params.id.toLong())
		log.debug "PACIENTE: "+consultaInstance.paciente.apellido
		def list = Institucion.findAll()
		def institucionInstance = list.getAt(0)
		String pathimage
		String nameimage
		def config
		if(institucionInstance){
			pathimage = bi.resource(size:'large',bean:institucionInstance)
			if(pathimage.contains(".null")){
				pathimage = servletContext.getRealPath("/images")
				nameimage = "noDisponibleLarge.jpg"
			}else{
				config = ContainerUtils.getConfig(institucionInstance)
				pathimage = servletContext.getRealPath("/institucional")
				nameimage = ContainerUtils.getFullName("large", institucionInstance, config)
			}
		
		}
		
		params.put("pathimage", pathimage);
		params.put("nameimage", nameimage)
		params.put("nombreInstitucion", institucionInstance.nombre);
		params.put("telefonos", institucionInstance.telefonos);
		params.put("email", institucionInstance.email);
		params.put("direccion", institucionInstance.direccion);
		params.put("reportsDirPath",servletContext.getRealPath("/reports/"))
		params.put("_format","PDF")
		params.put("_name","historiacontenidovisita")
		params.put("_file","historiacontenidovisita")
		def listReporte = new ArrayList()
		listReporte.add(consultaInstance)
		log.debug "Cantidad de prescripciones: "+consultaInstance.prescripciones.size()
		consultaInstance.prescripciones.each{
			log.debug "Prescripcion: "+it.nombreComercial
		}
		chain(controller:'jasper',action:'index',model:[data:listReporte],params:params)
		
	}

	def reportehistoriapropio = {
		log.info "INGRESANDO AL CLOSURE reportehistoriapropio"
		log.info "PARAMETROS : $params"
		def list = Institucion.findAll()
		def institucionInstance = list.getAt(0)
		String pathimage
		String nameimage
		def config
		if(institucionInstance){
			pathimage = bi.resource(size:'large',bean:institucionInstance)
			if(pathimage.contains(".null")){
				pathimage = servletContext.getRealPath("/images")
				nameimage = "noDisponibleLarge.jpg"
			}else{
				config = ContainerUtils.getConfig(institucionInstance)
				pathimage = servletContext.getRealPath("/institucional")
				nameimage = ContainerUtils.getFullName("large", institucionInstance, config)
			}
		
		}
		
		params.put("pathimage", pathimage);
		params.put("nameimage", nameimage)
		params.put("nombreInstitucion", institucionInstance.nombre);
		params.put("telefonos", institucionInstance.telefonos);
		params.put("email", institucionInstance.email);
		params.put("direccion", institucionInstance.direccion);
		params.put("reportsDirPath",servletContext.getRealPath("/reports/"))
		params.put("_format","PDF")
		params.put("_name","historiacontenidovisita")
		params.put("_file","historiacontenidovisita")
		def userLogged = authenticateService.userDomain()
		def listReporte = Consulta.createCriteria().list(){
			profesional{
				eq("id",userLogged.profesionalAsignado?.id)
			}
		} 
		chain(controller:'jasper',action:'index',model:[data:listReporte],params:params)
		
	}

		
	
}
