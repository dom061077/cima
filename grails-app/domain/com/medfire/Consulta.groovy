package com.medfire

import java.sql.Date
import com.medfire.enums.EstadoConsultaEnum

class Consulta {

	Date fechaConsulta
	Date fechaAlta
	String contenido
	Cie10 cie10
	Profesional profesional
	Paciente paciente
	EstadoConsultaEnum estado

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

	
	static belongsTo = [paciente:Paciente]
	
	static hasMany = [prescripciones:Prescripcion,estudios:EstudioComplementario]
	
    static constraints = {
		cie10(nullable:true,blank:true)
    }
	
	static mapping = {
		contenido type: "text"
	}
	
	
	
}
