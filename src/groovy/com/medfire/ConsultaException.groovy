package com.medfire

class ConsultaException extends RuntimeException {
	String message
	Consulta consulta
	
	public ConsultaException(String message,Consulta consulta){
		this.message = message
		this.consulta = consulta
	}
}
