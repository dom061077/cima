package com.medfire.enums

public enum IvaEnum {
	IVA_EXENTO("Exento"),
	IVA_FINAL("Consumidor Final"),
	IVA_INSCRIPTO("Responsable Inscripto"),
	IVA_MONOTRIBUTO("Monotributo"),
	IVA_NOINSCRIPTO("Responsable no Inscripto"),
	IVA_NORESPONSABLE("No Responsable")
	
	
	String name
	
	public IvaEnum(String name){
		this.name=name
	}
	
	static list(){
		[IVA_EXENTO,IVA_FINAL,IVA_INSCRIPTO,IVA_MONOTRIBUTO,IVA_NOINSCRIPTO,IVA_NORESPONSABLE]
	}

}
