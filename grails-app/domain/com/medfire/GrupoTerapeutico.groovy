package com.medfire

class GrupoTerapeutico {

	String grupo
	String nombre
	
	static hasMany=[vademecum:Vademecum]
	
    static constraints = {
    }
}
