package com.medfire.enums

public enum EstadoCivilEnum {
	ESTADO_CASADO("Casado"),
	ESTADO_SOLTERO("Soltero"),
	ESTADO_DIVORCIADO("Divorciado"),
	ESTADO_VIUDO("Viudo"),
	
	
	String name
	
	public EstadoCivilEnum(String name){
		this.name=name
	}
	
	static list(){
		[ESTADO_CASADO,ESTADO_SOLTERO,ESTADO_DIVORCIADO,ESTADO_VIUDO]
	}

}
