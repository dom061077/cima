package com.medfire

class HistoriaClinicaService {
	def imageUploadService
    static transactional = true

    def registrarVisita(Consulta consultaInstance) {
		log.info "INGRESANDO AL METODO registrarVisita DEL SERVICE HistoriaClinicaService"
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
		
		
		
		if(consultaInstance.validate() && consultaInstance.save()){
			consultaInstance.estudios.each {
				imageUploadService.save(it) 
			}
			return consultaInstance
		}else{
			errorMessage="ERROR AL SALVAR LA INSTANCIA DE consultaInstance EN registrarVisita EN HistoriaClinicaService"
			throw new ConsultaException(errorMessage,consultaInstance)
		}
    }
}
