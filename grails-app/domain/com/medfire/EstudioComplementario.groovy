package com.medfire
 
import pl.burningice.plugins.image.ast.FileImageContainer 

@FileImageContainer(field = 'imagen')
class EstudioComplementario {
	Consulta consulta
	
	static belongsTo = [consulta:Consulta]
	
    static constraints = {
    } 
}
