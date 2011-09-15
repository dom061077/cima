package com.medfire
 
class EstudioComplementario {
	String pedido
	String resultado
	Consulta consulta
	
	static belongsTo = [consulta:Consulta]
	
	static hasMany = [imagenes:EstudioComplementarioImagen]
	
    static constraints = {
    } 
	
	static mapping = {
		resultado type:"text"
	}
}
