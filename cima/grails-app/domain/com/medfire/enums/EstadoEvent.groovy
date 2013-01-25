package com.medfire.enums

public enum EstadoEvent {
	EVENT_ATENDIDO("Atendido"),
	EVENT_AUSENTE("Ausente"),
	EVENT_ANULADO("Anulado"),
	EVENT_PENDIENTE("Pendiente"),
	EVENT_ENSALA("En Sala") 
	
	String name
	
	public EstadoEvent(String name){
		this.name=name
	}
	
	static list (def excluded=null){
		def listint = listinterno()
		listint.remove(excluded)
		return listint
	}
	
	private static listinterno(){
		[EVENT_ATENDIDO,EVENT_AUSENTE,EVENT_ANULADO,EVENT_PENDIENTE,EVENT_ENSALA]
	}
	
	
}
