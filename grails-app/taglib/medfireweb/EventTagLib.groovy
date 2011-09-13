package medfireweb

import com.medfire.Event
import com.medfire.enums.EstadoEvent;

class EventTagLib {
		def colorsList = {attrs->
			if(attrs['event'])
				out<<"<span style='float:left;width:15px;height:15px;background:${grailsApplication.config.event."${attrs['event']}"};'></span>";
			else{
				out << "<ul style='list-style-type:none'>"
				out << "<li style='display:inline'>"+EstadoEvent.EVENT_ATENDIDO.name+": <div style='width:50px;height:15px;background:${grailsApplication.config.event.COLOR_ATENDIDO};'></div></li>"
				out << "<li style='display:inline'>"+EstadoEvent.EVENT_AUSENTE.name+": <div style='width:50px;height:15px;background:${grailsApplication.config.event.COLOR_AUSENTE};'></div></li>"
				out << "<li style='display:inline'>"+EstadoEvent.EVENT_ANULADO.name+": <div style='width:50px;height:15px;background:${grailsApplication.config.event.COLOR_ANULADO};'></div></li>"
				out << "<li style='display:inline'>"+EstadoEvent.EVENT_PENDIENTE.name+": <div style='width:50px;height:15px;background:${grailsApplication.config.event.COLOR_PENDIENTE};'></div></li>"
				out << "</ul>"
			}
		}
}
