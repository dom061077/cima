package com.medfire

import pl.burningice.plugins.image.ast.FileImageContainer

@FileImageContainer(field = 'imagen')
class EstudioComplementarioImagen {
	static auditable = true
	
	EstudioComplementario estudioComplementario
	Integer secuencia
	static belongsTo = [estudioComplementario:EstudioComplementario]
    static constraints = {
    }
}
