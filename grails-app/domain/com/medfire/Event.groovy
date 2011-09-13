package com.medfire
import java.util.Date

import com.medfire.enums.EstadoEvent;

class Event {

	
	Date fechaStart
	Date fechaEnd
	Integer start
	Integer end
	String titulo
	boolean allDay
	boolean atendido
	EstadoEvent estado=EstadoEvent.EVENT_PENDIENTE
	Paciente paciente
	Profesional profesional
	User user
	
	//static belongTo = [paciente:Paciente,profesional:User,user:User]
    static constraints = {
    	start (unique:'profesional')
		start (unique:'paciente')
    	end (unique:'profesional')
		end (unique:'paciente')
		paciente(nullable:true,blank:true)
		profesional(nullable:false,blank:false)
		user(nullable:false,blank:false)
    }
	
	
}
