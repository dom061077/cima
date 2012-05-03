package com.medfire

import com.medfire.Role

/**
 * User domain class.
 */
class User {
	static auditable = true
	
	static transients = ['pass']
	static hasMany = [authorities: Role,medicos:Profesional]
	static belongsTo =Role

	/** Username */
	String username
	/** User Real Name*/
	String userRealName
	/** MD5 Password */
	String passwd
	/** enabled */
	boolean enabled=true
	boolean esProfesional=false

	String email
	boolean emailShow

	/** description */
	String description = ''

	/** plain password to create a MD5 password */
	String pass = '[secret]'
	Profesional profesionalAsignado
	Institucion institucion
	
	
	static constraints = {
		username(blank: false, unique: true)
		userRealName(blank: false)
		passwd(blank: false)
		enabled()
		profesionalAsignado(blank:true, nullable:true,validator:{val,obj->
				if(obj.profesionalAsignado && !obj.esProfesional)
					return ['invalid.activation']
			})
		esProfesional(validator:{val,obj->
				if(obj.esProfesional && !obj.profesionalAsignado)
					return ['invalid.activation']
		})
	}
	
	static mapping = {
		profesionalAsignado lazy:false
		profesionalAsignado cascade:'none'
	}
}
