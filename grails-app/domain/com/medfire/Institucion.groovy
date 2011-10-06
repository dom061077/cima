package com.medfire

import pl.burningice.plugins.image.ast.FileImageContainer

@FileImageContainer(field = 'imagen')
class Institucion {
	String nombre
	String direccion
	String telefonos
	String email
	
    static constraints = {
		email(email:true)
    }
}
