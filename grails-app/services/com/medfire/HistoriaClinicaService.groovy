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
				log.info "ITERANDO ESTUDIO "+it
				it.imagenes.each{imagen->
					log.info "ITERANDO IMAGEN "+imagen.class
					imageUploadService.save(imagen)
				} 
			}
			return consultaInstance
		}else{
			errorMessage="ERROR AL SALVAR LA INSTANCIA DE consultaInstance EN registrarVisita EN HistoriaClinicaService"
			throw new ConsultaException(errorMessage,consultaInstance)
		}
    }
	
	def updateVisita(def consultaInstance, def params){
		log.info "INGRESANDO AL METODO updateVisita"

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
		
				
		def prescripcionesjson
		if(params.prescripcionesSerialized){
			prescripcionesjson = grails.converters.JSON.parse(params.prescripcionesSerialized)
			
			prescripcionesjson?.each{
				consultaInstance.addToPrescripciones(new Prescripcion(nombreComercial:it.nombreComercial
					,nombreGenerico:it.nombreGenerico,presentacion:it.presentacion,impresion:ImpresionVademecumEnum."${it.imprimirPorValue}",cantidad:it.cantidad,secuencia:it.id))
			}
		}

		def deletedestjson
		def estudioInstance
		
		if(params.deletedEstSerialized){
			deletedestjson = grails.converters.JSON.parse(params.deletedEstSerialized)
			deletedestjson?.each{
				estudioInstance = EstudioComplementario.get(it.id)
				estudioInstance.imagenes.each{
					imageUploadService.delete(it)
				}
				estudioInstance.delete()
			}
		}

		
		def deletedimgjson
		def estudioImagenInstance

				// elimino las imagenes marcadas para la eliminacion
		if(params.deletedImgSerialized){
			deletedimgjson = grails.converters.JSON.parse(params.deletedImgSerialized)
			deletedimgjson?.each{
				estudioImagenInstance = EstudioComplementarioImagen.get(it.id.toLong())
				log.debug "SE BORRO EL SIGUIENTE ESTUDIO: "+estudioInstance.id
				
				consultaInstance.removeFromEstudios(estudioInstance)
				consultaInstance.estudios.each{est->
					if(it.estId.toLong().equals(est.id)){
						est.removeFromImagenes(estudioImagenInstance)
					}
				}
				imageUploadService.delete(estudioImagenInstance)
				estudioImagenInstance.delete()
			}
		}

		
		params.consulta.estudio.each{
			log.debug "getTypeContent de la imagen "+it.value.contentType
			log.debug "NOMBRE DEL ARCHIVO: "+it.value.name
			if(!it.value.isEmpty()){
				estudioInstance = new EstudioComplementario(consulta:consultaInstance,imagen:it.value)
				consultaInstance.addToEstudios(estudioInstance)
				estudioInstance.save()
				imageUploadService.save(estudioInstance)
			}
		}
		
		consultaInstance.properties = params.consulta
		if(!consultaInstance.hasErrors() && consultaInstance.save()){

			return consultaInstance
		}else{
			errorMessage="ERROR AL SALVAR LA INSTANCIA DE consultaInstance EN registrarVisita EN HistoriaClinicaService"
			throw new ConsultaException(errorMessage,consultaInstance)
		}

	
		
	}
	
	void deleteVisita(def consultaInstance){
		log.info "INGRESANDO AL METODO deleteVisita "
		log.info "PARAMETROS: $consultaInstance"
//		consultaInstance.removeFromEstudios(estudioInstance)
//		imageUploadService.delete(estudioInstance)
//		estudioInstance.delete()
		def listEstudios = EstudioComplementario.createCriteria().list{
			eq("id",consultaInstance.id)
		}
		listEstudios.each{
			consultaInstance.removeFromEstudios(it)
			imageUploadService.delete(it)
			it.delete()
		}
		consultaInstance.delete(flush:true)
	}
	
}
