package com.medfire

import pl.burningice.plugins.image.ast.FileImageContainer

@FileImageContainer(field = 'imagen')
class Institucion {
	String nombre
	String direccion
	String telefonos
	String email
	String web
	String descripcion

	
    static constraints = {
		nombre(nullable:false,blank:false)
		email(email:true)
		direccion nullable:true,blank:true
		telefonos nullable:true, blank:true
		web nullable:true, blank:true
		descripcion(size:1..50)  
    }
}
