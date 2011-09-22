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
		def deletedImgList
		
		if(params.deletedEstSerialized){
			deletedestjson = grails.converters.JSON.parse(params.deletedEstSerialized)
			deletedestjson?.each{
				estudioInstance = EstudioComplementario.get(it.id)
				deletedImgList = EstudioComplementarioImagen.createCriteria().list{
							estudioComplementario{
								eq("id",estudioInstance.id)
							}
					}
				deletedImgList.each{imagen->
					imageUploadService.delete(imagen)
					imagen.delete()
					estudioInstance.removeFromImagenes(imagen)
				}
				consultaInstance.removeFromEstudios(estudioInstance)
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
				log.debug "SE BORRO EL SIGUIENTE ESTUDIO: "+estudioImagenInstance?.id
				
				//consultaInstance.removeFromEstudios(estudioInstance)
				consultaInstance.estudios.each{est->
					if(it.estId.toLong().equals(est.id)){
						est.removeFromImagenes(estudioImagenInstance)
					}
				}
				imageUploadService.delete(estudioImagenInstance)
				//estudioImagenInstance.delete()
			}
		}

		params.consulta.estudio.each{
			try{
				log.debug "IT DE PARAMS: ${it.value.id}"
				if(it.value.id){
					log.debug "ESTUDIO A RECUPERAR: ${it.value.id}"
					consultaInstance.estudios.each{estudio->
						if(estudio.id.equals(it.value.id.toLong())){
							log.debug "MODIFICANDO LOS DATOS CABECERA DEL ESTUDIO EXISTENTE, codigo: ${it.value.id}"
							estudio.pedido=it.value.pedido
							estudio.resultado=it.value.resultado
							it.value?.imagen?.each {image->
								if(!image.value.isEmpty()){
									log.debug "AGREGANDO IMAGENES AL ESTUDIO EXISTENTE, CLASE: "+image.value.class
									estudioImagenInstance = new EstudioComplementarioImagen(estudioComplementario:estudio,imagen:image.value,secuencia:it.key.toInteger())
									if(!estudioImagenInstance.validate()){
										log.debug "ERROR DE VALIDACION EN LA IMAGEN: "+estudioImagenInstance.errors.allErrors
										throw new ConsultaException("ERROR DE VALIDACION EN LA IMAGEN A GUARDAR",estudioImagenInstance)
									}
									//estudioImagenInstance= estudioImagenInstance.save()
									estudio.addToImagenes(estudioImagenInstance)
									//imageUploadService.save(estudioImagenInstance)
								}
							}
						}
					}
				}else{
					log.debug "AGREGANDO ESTUDIO NUEVO"
					log.debug "ESTUDIO NUEVO A AGREGAR: "+it.value
					estudioInstance = new EstudioComplementario(consulta:consultaInstance,pedido:it.value.pedido,resultado:it.value.resultado,secuencia:it.key.toInteger())
					if (!estudioInstance.validate())
						log.debug "ERROR DE VALIDACION: "+estudioInstance.errors.allErrors
					//estudioInstance=estudioInstance.save()
					log.debug "ESTUDIO INSTANCE SALVADO: "+estudioInstance.properties
					it.value?.imagen?.each{image->
						log.debug "IMAGEN ITERADA: $image"
						if(!image.value.isEmpty()){
							log.debug "IMAGEN AGREGADA: "+image.value.class
							estudioImagenInstance = new EstudioComplementarioImagen(estudioComplementario:estudioInstance,imagen:image.value,secuencia:it.key.toInteger())
							if(!estudioImagenInstance.validate()){
								log.debug "ERROR DE VALDACION EN LA IMAGEN: "+estudioImagenInstance.errors.allErrors
								throw new ConsultaException("ERROR DE VALIDACION EN LA IMAGEN A GUARDAR",estudioImagenInstance)
							}
							//estudioImagenInstance = estudioImagenInstance.save()
							estudioInstance.addToImagenes(estudioImagenInstance)
							//imageUploadService.save(estudioImagenInstance,true)
						}
					}
					consultaInstance.addToEstudios(estudioInstance)
				}
				
			}catch(MissingPropertyException e){
			
			}
		}
	
		
		consultaInstance.properties = params.consulta
		if(!consultaInstance.hasErrors() && consultaInstance.save()){
			throw new ConsultaException("ERROR DE VALIDACION EN LA IMAGEN A GUARDAR",consultaInstance)
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
