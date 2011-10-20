package com.medfire

import com.medfire.enums.ImpresionVademecumEnum

class Prescripcion {
	static auditable = true
	
	String nombreComercial
	String nombreGenerico
	String presentacion
	Integer secuencia
	Integer cantidad
	ImpresionVademecumEnum impresion
	
	static belongsTo = [consulta:Consulta]
	
    static constraints = {
    }
}
