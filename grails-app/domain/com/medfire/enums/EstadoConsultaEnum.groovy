package com.medfire.enums

public enum EstadoConsultaEnum{
	ESTADO_PUBLICO("Público"),
	ESTADO_PRIVADO("Privado")
	
	String name
	
	public EstadoConsultaEnum(String name){
		this.name = name
	}
	
	static list(){
		[ESTADO_PUBLICO, ESTADO_PRIVADO]
	}
	
	
}