package com.medfire

import pl.burningice.plugins.image.ast.FileImageContainer

@FileImageContainer(field = 'imagen')
class EstudioComplementarioImagen {
	EstudioComplementario estudioComplementario
	static belongsTo = [estudioComplementario:EstudioComplementario]
    static constraints = {
    }
}
