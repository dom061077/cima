package com.medfire

class Vademecum {
	String nombreComercial
	Composicion composicion
	Contraindicacion contraIndicacion
	String farmacologia
	String farmacodinamia
	String farmacocinetica
	String reaccionesAdversas
	String indicaciones
	String dosificacion
	String sobreDosificacion
	String prestaciones
	String conservacion
	String advertencias
	String embarazoyLactancia
	String accionTerapeutica
	String asoc2
	String presentacion
	PrincipioActivo principio
	GrupoTerapeutico grupo
	Laboratorio laboratorio
	
	static belongsTo = [principio:PrincipioActivo,grupo:GrupoTerapeutico,laboratorio:Laboratorio]
	
    static constraints = {
    }
}
