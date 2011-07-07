package com.medfire.enums

public enum TipoDocumentoEnum {
	TIPODOC_DNI("D.N.I"),
	TIPODOC_CI("C.I"),
	TIPODOC_LE("L.E"),
	TIPODOC_LC("L.C"),
	TIPODOC_PASAPORTE("Pasaporte")
	
	String name
	
	public TipoDocumentoEnum(String name){
		this.name=name
	}
	
	static list(){
		[TIPODOC_DNI,TIPODOC_CI,TIPODOC_LE,TIPODOC_LC,TIPODOC_PASAPORTE]
	}
	
	
}
