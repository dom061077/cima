package com.medfire

class PrincipioActivo {
	String principioActivo
	String contraindicaciones
	String precauciones
	String interacciones
	String embarazoLactancia
	
    static constraints = {
    }
	
	static hasMany = [vademecum:Vademecum]
	
	static mapping = {
		contradicciones type:'text'
		precauciones type:'text'
		interacciones type:'text'
		embarazoLactancia type:'text'
	}
}
