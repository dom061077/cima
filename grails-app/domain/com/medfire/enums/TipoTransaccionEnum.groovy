package com.medfire.enums;

public enum TipoTransaccionEnum {
	TIPOTRAN_INSERT("ALTA"),
	TIPOTRAN_UPDATE("MODIFICACION"),
	TIPOTRAN_DELETE("BAJA")
	
	String name
	
	public TipoTransaccionEnum(String name){
		this.name=name
	}
	
	static list(){
		[TIPOTRAN_INSERT,TIPOTRAN_UPDATE,TIPOTRAN_DELETE]
	}
	
}
