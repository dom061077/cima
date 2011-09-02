package com.medfire

import com.medfire.enums.ImpresionVademecumEnum




class HistoriaClinicaService {
	def imageUploadService
    static transactional = true

    def registrarVisita(Consulta consultaInstance) {
		log.info "INGRESANDO AL METODO registrarVisita"
		log.info "PARAMETRO consulta: "+consultaInstance
		def errorMessage=""
		consultaInstance.fechaAlta = new java.sql.Date((new java.util.Date()).getTime())
		/*def flagvalid = true
		consultaInstance.estudios.each{
			if(!it.validate()){
				flagvalid=false
				log.debug "HUBO UN ERROR EN LA VALIDACION: "+it.errors.allErrors
			}
		}*/
		if(consultaInstance.validate() && consultaInstance.paciente.validate() && consultaInstance.save() && consultaInstance.paciente.save()){
			consultaInstance.estudios.each {
				imageUploadService.save(it) 
			}
			return consultaInstance
		}else{
			errorMessage="ERROR AL SALVAR LA INSTANCIA DE consultaInstance EN registrarVisita EN HistoriaClinicaService"
			throw new ConsultaException(errorMessage,consultaInstance)
		}
    }
	
	def updateVisita(def consultaInstance, def params){
		log.info "INGRESANDO AL METODO updateVisita"
		log.info "PARAMETROS: $params"

		def errorMessage = ""
		// elimino todas las prescripciones para volverlas a cargar
		
		def listPrescripciones = Prescripcion.createCriteria().list(){
			consulta{
				eq("id",consultaInstance.id)
			}
		}
		listPrescripciones.each{
			consultaInstance.removeFromPrescripciones(it)
			it.delete()
		}
		
		if(!params.imagepropia.isEmpty()){
			consultaInstance.addToEstudios(new EstudioComplementario(imagen:params.imagepropia))
		}
				
		def prescripcionesjson
		if(params.prescripcionesSerialized){
			prescripcionesjson = grails.converters.JSON.parse(params.prescripcionesSerialized)
			
			prescripcionesjson?.each{
				consultaInstance.addToPrescripciones(new Prescripcion(nombreComercial:it.nombreComercial
					,nombreGenerico:it.nombreGenerico,presentacion:it.presentacion,impresion:ImpresionVademecumEnum."${it.imprimirPorValue}",cantidad:it.cantidad,secuencia:it.id))
			}
		}
		def deletedimgjson
		def estudioInstance

				// elimino las imagenes marcadas para la eliminacion
//		if(params.deletedImgSerialized){
//			deletedimgjson = grails.converters.JSON.parse(params.deletedImgSerialized)
//			deletedimgjson?.each{
//				estudioInstance = EstudioComplementario.get(it.id.toLong())
//				log.debug "SE BORRO EL SIGUIENTE ESTUDIO: "+estudioInstance.id
//				
//				consultaInstance.removeFromEstudios(estudioInstance)
//				estudioInstance.delete()
//			}
//		}
		
		
		//log.debug "CANTIDAD DE IMAGENES: "+params.imagen.size()
//		params.imagen.each{
//			log.debug "getTypeContent de la imagen "+it.value.contentType
//			log.debug "NOMBRE DEL ARCHIVO: "+it.value.name
//			if(!it.value.isEmpty()){
//				estudioInstance = new EstudioComplementario(consulta:consultaInstance,imagen:it.value)
//				consultaInstance.addToEstudios(estudioInstance)
//			}
//		}
		if(!params.imagepropia.isEmpty()){
			
			estudioInstance = new EstudioComplementario(imagen:params.imagepropia,consulta:consultaInstance)
			log.debug "CLASS DE LA IMAGEN ANTES DE ADD TO ESTUDIOS"+estudioInstance.imagen.class
			estudioInstance.validate()
			log.debug "errores: "+estudioInstance.errors.allErrors
			consultaInstance.addToEstudios(estudioInstance)
		}

		if(consultaInstance.validate() && consultaInstance.save(flush:true)){
			consultaInstance.estudios.each {
					log.debug "IMAGEN SALVADA CON imageUploadService "+it.id+" "+it.imagen?.class
					if(!it.id)
						imageUploadService.save(it)
			}
			return consultaInstance
		}else{
			errorMessage="ERROR AL SALVAR LA INSTANCIA DE consultaInstance EN registrarVisita EN HistoriaClinicaService"
			throw new ConsultaException(errorMessage,consultaInstance)
		}

	
		
	}
}
