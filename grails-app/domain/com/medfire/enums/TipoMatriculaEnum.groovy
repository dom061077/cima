package com.medfire.enums;

public enum TipoMatriculaEnum {
	TIPOMAT_PROVINCIAL("Provincial"),
	TIPOMAT_NACIONAL("Nacional")
	
	String name
	
	public TipoMatriculaEnum(String name){
		this.name=name
	}
	
	static list(){
		[TIPOMAT_PROVINCIAL,TIPOMAT_NACIONAL]
	}
	
}
