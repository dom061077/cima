package com.medfire

class ConsultaException extends RuntimeException {
	String message
	Consulta consulta
	EstudioComplementario estudioComplementario
	EstudioComplementarioImagen estudioComplementarioImagen
	
	public ConsultaException(String message,Consulta consulta){
		this.message = message
		this.consulta = consulta
	}
	
	public ConsultaException(String message,EstudioComplementario estudioComplementario){
		this.estudioComplementario = estudioComplementario
		this.message = message
	}
	
	public ConsultaException(String message,EstudioComplementarioImagen estudioComplementarioImagen){
		this.message = message
		this.estudioComplementarioImagen = estudioComplementarioImagen
	}
}
