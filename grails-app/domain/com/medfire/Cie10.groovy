package com.medfire

class Cie10 {
	String descripcion
	String cie10
    static constraints = {
		descripcion(nullable:true,blank:true)
		cie10(nullable:true,blank:true,unique:true)
    }
	
}
