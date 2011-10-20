package com.medfire

class EspecialidadMedica {
	static auditable = true
	
	String descripcion
    static constraints = {
		descripcion(nullable:false,blank:false)
    }
	static hasMany = [profesionales:Profesional]
}
