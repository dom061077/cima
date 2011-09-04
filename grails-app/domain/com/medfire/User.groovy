package com.medfire

import com.medfire.Role

/**
 * User domain class.
 */
class User {
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
	boolean enabled
	boolean esProfesional=false

	String email
	boolean emailShow

	/** description */
	String description = ''

	/** plain password to create a MD5 password */
	String pass = '[secret]'
	Profesional profesionalAsignado
	static constraints = {
		username(blank: false, unique: true)
		userRealName(blank: false)
		passwd(blank: false)
		enabled()
		profesionalAsignado(blank:true, nullable:true)
	}
	
	static mapping = {
		profesionalAsignado lazy:false
	}
}
