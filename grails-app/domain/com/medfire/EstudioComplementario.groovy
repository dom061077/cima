package com.medfire
 
class EstudioComplementario {
	String pedido
	String resultado
	Consulta consulta
	Integer secuencia
	
	static belongsTo = [consulta:Consulta]
	
	static hasMany = [imagenes:EstudioComplementarioImagen]
	
    static constraints = {
		imagenes validator: {
			it?.every{it?.validate()}
		}
    } 
	
	static mapping = {
		resultado type:"text"
		images sort:'secuencia'
	}
}
