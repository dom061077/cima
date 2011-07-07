package com.medfire

class Provincia {
	String nombre
	
    static constraints = {
    }
	static hasMany = [localidades:Localidad]
}
