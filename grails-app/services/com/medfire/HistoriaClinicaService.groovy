package com.medfire

class HistoriaClinicaService {
	def imageUploadService
    static transactional = true

    def registrarVisita(Consulta consultaInstance) {
		log.info "INGRESANDO AL METODO registrarVisita DEL SERVICE HistoriaClinicaService"
		log.info "PARAMETRO consulta: "+consultaInstance
		def errorMessage=""
		consultaInstance.fechaAlta = new java.sql.Date((new java.util.Date()).getTime())
		if(consultaInstance.save()){
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
