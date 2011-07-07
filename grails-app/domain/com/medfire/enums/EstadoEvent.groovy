package com.medfire.enums

public enum EstadoEvent {
	EVENT_ATENDIDO("Atendido"),
	EVENT_AUSENTE("Ausente"),
	EVENT_ANULADO("Anulado"),
	EVENT_PENDIENTE("Pendiente");
	
	String name
	
	public EstadoEvent(String name){
		this.name=name
	}
	
	static list(){
		[EVENT_ATENDIDO,EVENT_AUSENTE,EVENT_ANULADO,EVENT_PENDIENTE]
	}
	
	
}
