package com.medfire.enums

public enum ImpresionVademecumEnum{
	IMPRIME_GENERICO("Nombre genérico"),
	IMPRIME_COMERCIAL("Nombre Comercial"),
	IMPRIME_AMBOS("Ambos")
	
	String name
	
	public ImpresionVademecEnum(String name){
		this.name=name
	}
	
	
	static list(){
		[IMPRIME_GENERICO,IMPRIME_COMERCIAL,IMPRIME_AMBOS]
	}
	
}