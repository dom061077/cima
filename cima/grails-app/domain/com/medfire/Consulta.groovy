package com.medfire

import java.sql.Date
import com.medfire.enums.EstadoConsultaEnum

class Consulta {
	static auditable = true
	
	Date fechaConsulta = new java.sql.Date((new java.util.Date()).getTime())
	Date fechaAlta = new java.sql.Date((new java.util.Date()).getTime())
	String contenido
	Cie10 cie10
	Profesional profesional
	Paciente paciente
	EstadoConsultaEnum estado
	Event evento

	//examen fisico	
	String pulso
	String fc
	String ta
	String fr
	String taxilar
	String trectal
	String pesoh
	String pesoa
	String impresion
	String habito
	String actitud
	String ubicacion
	String marcha
	String psiqui
	String facie
	Antecedente antecedente
	

	
	static belongsTo = [paciente:Paciente]
	
	static hasMany = [prescripciones:Prescripcion,estudios:EstudioComplementario]
	
    static constraints = {
		cie10(nullable:true,blank:true)
		contenido(blank:false)
		estudios validator: {
			it?.every { it?.validate() }
		}
		evento(nullable:true)

    }
	
	static mapping = {
		contenido type: "text"
		impresion type:"text"
		paciente lazy:false
		cie10 lazy:false
		profesional lazy:false
		prescripciones lazy:false
		estudios sort:'secuencia'
	}
	
	
	
	
}
