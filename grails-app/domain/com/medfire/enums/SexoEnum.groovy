package com.medfire.enums

public enum SexoEnum {
	SEXO_MASCULINO("Masculino"),
	SEXO_FEMENINO("Femenino")
	String name
	public SexoEnum(String name){
		this.name=name
	}
	
	static list(){
		[SEXO_MASCULINO,SEXO_FEMENINO]
	}
}
