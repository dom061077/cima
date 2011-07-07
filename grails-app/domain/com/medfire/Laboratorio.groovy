package com.medfire

class Laboratorio {

	String nombre
	String direccion
	Localidad localidad
	String codigoPostal
	String telefono
	String contacto
	String comentario
	
    static constraints = {
		nombre(nullable:false,blank:false)
		localidad(nullable:true,blank:true)
    }
	
	
	static hasMany = [vademecum:Vademecum]
	
	static mapping = {
		comentario type: 'text'
	}
}
