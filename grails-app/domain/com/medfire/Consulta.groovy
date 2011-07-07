package com.medfire

import java.sql.Date
import com.medfire.enums.EstadoConsultaEnum

class Consulta {

	Date fechaConsulta
	Date fechaAlta
	String contenido
	Cie10 cie10
	HistoriaClinica historiaClinica
	Profesional profesional
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

	
	static belongsTo = [historiaClinica:HistoriaClinica]
	
    static constraints = {
    }
	
	
	
}
