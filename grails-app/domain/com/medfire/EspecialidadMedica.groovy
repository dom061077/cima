package com.medfire

class EspecialidadMedica {
	String descripcion
    static constraints = {
		descripcion(nullable:false,blank:false)
    }
	static hasMany = [profesionales:Profesional]
}
