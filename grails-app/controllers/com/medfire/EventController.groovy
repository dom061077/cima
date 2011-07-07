package com.medfire

import com.medfire.util.FilterUtils
import java.util.Calendar;
import java.util.Date
import java.util.GregorianCalendar
import grails.converters.JSON

class EventController {
	def authenticateService
	
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }
 
    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [eventInstanceList: Event.list(params), eventInstanceTotal: Event.count()]
    }

    def create = {
		log.info "INGRESANDO AL METODO create DEL CONTROLLER EventController"
		log.info "PARAMETROS $params" 
		//def eventInstance = new Event()
        //eventInstance.properties = params
		
		
		def usuario = User.load( authenticateService.userDomain()?.id)
		def profList = Profesional.list() //usuario?.medicos
		log.debug "cantidad de profesionales que atiende: "+profList.size()
        return [profesionales:profList,profesionalId:params.profesionalId]
    }

    def save = {
        def eventInstance = new Event(params)
        if (eventInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'event.label', default: 'Event'), eventInstance.id])}"
            redirect(action: "show", id: eventInstance.id)
        }
        else {
            render(view: "create", model: [eventInstance: eventInstance])
        }
    }

    def show = {
        def eventInstance = Event.get(params.id)
        if (!eventInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'event.label', default: 'Event'), params.id])}"
            redirect(action: "list")
        }
        else {
            [eventInstance: eventInstance]
        }
    }

    def edit = {
        def eventInstance = Event.get(params.id)
        if (!eventInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'event.label', default: 'Event'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [eventInstance: eventInstance]
        }
    }

    def update = {
        def eventInstance = Event.get(params.id)
        if (eventInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (eventInstance.version > version) {
                    
                    eventInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'event.label', default: 'Event')] as Object[], "Another user has updated this Event while you were editing")
                    render(view: "edit", model: [eventInstance: eventInstance])
                    return
                }
            }
            eventInstance.properties = params
            if (!eventInstance.hasErrors() && eventInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'event.label', default: 'Event'), eventInstance.id])}"
                redirect(action: "show", id: eventInstance.id)
            }
            else {
                render(view: "edit", model: [eventInstance: eventInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'event.label', default: 'Event'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def eventInstance = Event.get(params.id)
        if (eventInstance) {
            try {
                eventInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'event.label', default: 'Event'), params.id])}"
                redirect(action: "list")
                
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'event.label', default: 'Event'), params.id])}"
                redirect(action: "show", id: params.id)
                
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'event.label', default: 'Event'), params.id])}"
            redirect(action: "list")
             
        }
    }

	def operation = {
		log.info "INGRESANDO AL METODO operation DEL CONTROLLER EventController"
		log.info "PARAMETROS $params"
		if(params.cmd.equals("read")){
			return read(params)		
		}
		
		if(params.cmd.equals("save")){
			return savejson(params)		
		}
		
		if(params.cmd.equals("update")){
			return updateeventjson(params)		
		}
		
		if(params.cmd.equals("updatePos")){
			return updateeventposjson(params)
		}
		
		if(params.cmd.equals("delete")){
			return deleteevent(params)
		}
		
		if(params.cmd.equals("list")){
			return listatencionjson(params)
		}
		
		
	}
	    
	
	
    private def read(def params){
		log.info "INGRESANDO AL METODO PRIVADO read"
		def eventos = Event.createCriteria().list(){
			
			profesional{
				eq('id',new Long(params.profesionalId?:0))
			}
		}
		
		render(contentType:"text/json"){
			array{
				for(e in eventos){
					log.debug "paciente: ${e.paciente}}"
					evento id: e.id,pacienteId:e.paciente?.id, title:e.titulo,start:e.start, end:e.end, allDay:false,version:e.version
				}
			}
		}
	}

    private def savejson(def params) {
		log.info "INGRESANDO AL METODO PRIVADO savejson "
		log.info "PARAMETROS $params"
        def eventInstance = new Event()
        
		Calendar currentCal = Calendar.getInstance()
		Calendar cal = Calendar.getInstance()
		currentCal.set(cal.get(Calendar.YEAR),cal.get(Calendar.MONTH),cal.get(Calendar.DATE))
		
        GregorianCalendar gc = new GregorianCalendar(
        		Integer.parseInt(params.startyear)
        		,Integer.parseInt(params.startmonth)
        		,Integer.parseInt(params.startday)
        								 
        		,Integer.parseInt(params.starthours)
        		,Integer.parseInt(params.startminutes)
        	)
		if(gc.before(currentCal)){
			render "Fecha de turno anterior al dia de hoy"
			return
		}
		
        eventInstance.fechaStart = gc.getTime()
        
        gc.set(Integer.parseInt(params.endyear)
        		,Integer.parseInt(params.endmonth)
        		,Integer.parseInt(params.endday)
        		,Integer.parseInt(params.endhours)
        		,Integer.parseInt(params.endminutes)
        		)
        
		
		
        eventInstance.fechaEnd = gc.getTime()
		eventInstance.start = new Integer(params.start)
		eventInstance.end = new Integer(params.end)
		eventInstance.allDay = false
		eventInstance.titulo = params.title
		if(!params.pacienteId.trim().equals(""))
			eventInstance.paciente = Paciente.load(new Long(params.pacienteId))
			
		if(params.profesionalId)	
			eventInstance.profesional = Profesional.load(new Long(params.profesionalId))
		eventInstance.user = authenticateService.userDomain()
        if (eventInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'event.label', default: 'Event'), eventInstance.id])}"
            
            render eventInstance.id
        }
        else {
			log.debug "ERRORES: "+eventInstance.errors.allErrors
            render "Error al tratar de guardar el turno"
        }
    }
    
    private def deleteevent(def params){
    	log.info "INGRESANDO AL METODO PRIVADO deleteevent"
    	log.info "PARAMETROS $params"
        def eventInstance = Event.get(params.id)
        if (eventInstance) {
            try {
                eventInstance.delete(flush: true)
                //flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'event.label', default: 'Event'), params.id])}"
                //redirect(action: "list")
                render ""
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                //flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'event.label', default: 'Event'), params.id])}"
                //redirect(action: "show", id: params.id)
                render "${message(code: 'default.not.deleted.message', args: [message(code: 'event.label', default: 'Event'), params.id])}"
            }
        }
        else {
            //flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'event.label', default: 'Event'), params.id])}"
            //redirect(action: "list")
            render "${message(code: 'default.not.found.message', args: [message(code: 'event.label', default: 'Event'), params.id])}" 
        }
    }
    
    private def updateeventjson(def params){
    	log.info "INGRESANDO AL METODO PRIVADO updateeventjson"
    	log.info "PARAMETROS $params"
    	
        def eventInstance = Event.get(params.id)
		
        if (eventInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (eventInstance.version > version) {
					
                    //eventInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'event.label', default: 'Event')] as Object[], "Another user has updated this Event while you were editing")
                    //render(view: "edit", model: [eventInstance: eventInstance])
                    //return
                    render (contentType:"text/json"){
						result success:false,title:"Error, el turno está siendo modificado por otro usuario"
					}
					return
                }
            }
			if(params.paciente.id)
            	eventInstance.paciente=Paciente.load(params.paciente.id)
			else
				eventInstance.paciente=null	
			eventInstance.properties=params
			
            if (!eventInstance.hasErrors() && eventInstance.save(flush: true)) {
                //flash.message = "${message(code: 'default.updated.message', args: [message(code: 'event.label', default: 'Event'), eventInstance.id])}"
                //redirect(action: "show", id: eventInstance.id)
                render (contentType:"text/json"){
					result success:true,title:eventInstance.paciente.apellido+"-"+eventInstance.paciente.nombre
				}
            }
            else {
                //render(view: "edit", model: [eventInstance: eventInstance])
                render (contentType:"text/json"){
                	result success:false,title:"Error, datos incompletos"
                }
                //render "Error, no se modifcó el turno"
            }
        }
        else {
            //flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'event.label', default: 'Event'), params.id])}"
            //redirect(action: "list")
            //render "${message(code: 'default.not.found.message', args: [message(code: 'event.label', default: 'Event'), params.id])}"
            render(contentType:"text/json"){
            	result success:false,title:"${message(code: 'default.not.found.message', args: [message(code: 'event.label', default: 'Event'), params.id])}"
            }
        }
    }
    

	private def updateeventposjson(def params){
		log.info "INGRESANDO AL METODO PRIVADO updateeventposjson"
		log.info "PARAMETROS $params"
		
		def eventInstance = Event.get(params.id)
		Calendar currentCal = Calendar.getInstance()
		Calendar cal = Calendar.getInstance()
		currentCal.set(cal.get(Calendar.YEAR),cal.get(Calendar.MONTH),cal.get(Calendar.DATE))
		
		
		if (eventInstance) {
			if (params.version) {
				def version = params.version.toLong()
				if (eventInstance.version > version) {
					
					//eventInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'event.label', default: 'Event')] as Object[], "Another user has updated this Event while you were editing")
					//render(view: "edit", model: [eventInstance: eventInstance])
					//return
					log.info "EL EVENTO O TURNO ESTA SIENDO MODIFICADO POR OTRO USUARIO"
					render (contentType:"text/json"){
						result success:false,title:"Error, el turno está siendo modificado por otro usuario"
					}
					return
				}
			}
			
	        GregorianCalendar gc = new GregorianCalendar(
	        		Integer.parseInt(params.startyear)
	        		,Integer.parseInt(params.startmonth)
	        		,Integer.parseInt(params.startday)
	        								 
	        		,Integer.parseInt(params.starthours)
	        		,Integer.parseInt(params.startminutes)
	        	)
	        	
			if(gc.before(currentCal)){
				
				render(contentType:"text/json"){
					result success:false,title:"Fecha de turno anterior al dia de hoy"
				}
				log.debug "FECHA DE TURNO ANTERIOR AL DIA DE HOY"
				return
			}		

			eventInstance.start=new Integer(params.start)
			eventInstance.end=new Integer(params.end)

		        	
	        eventInstance.fechaStart = gc.getTime()
	        log.debug "SUPERADA VALIDACION DE FECHAS"
	        gc.set(Integer.parseInt(params.endyear)
	        		,Integer.parseInt(params.endmonth)
	        		,Integer.parseInt(params.endday)
	        		,Integer.parseInt(params.endhours)
	        		,Integer.parseInt(params.endminutes)
	        		)
	        
	        eventInstance.fechaEnd = gc.getTime()

			
			
			if (!eventInstance.hasErrors() && eventInstance.save(flush: true)) {
				//flash.message = "${message(code: 'default.updated.message', args: [message(code: 'event.label', default: 'Event'), eventInstance.id])}"
				//redirect(action: "show", id: eventInstance.id)
				log.info "EL EVENTO O TURNO SE GUARDO CORRECTAMENTE"
				render (contentType:"text/json"){
					result success:true,title:eventInstance.titulo
				}
			}
			else {
				//render(view: "edit", model: [eventInstance: eventInstance])
				log.info "ERRORES DE VALIDACION EN EVENTO O TURNO: "+eventInstance.errors.allErrors
				def msgerror=''
				eventInstance.errors.allErrors.each{
					if(msgerror.indexOf(g.message(error:it))==-1)
						msgerror+=g.message(error:it)
				}
				render (contentType:"text/json"){
					result success:false,title:"$msgerror"
				}
			}
		}
		else {
			//flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'event.label', default: 'Event'), params.id])}"
			//redirect(action: "list")
			//render "${message(code: 'default.not.found.message', args: [message(code: 'event.label', default: 'Event'), params.id])}"
			render(contentType:"text/json"){
				result success:false,title:"${message(code: 'default.not.found.message', args: [message(code: 'event.label', default: 'Event'), params.id])}"
			}
		}
	}

	def listjson={
		log.info "INGRESANDO AL CLOSURE listjson DEL CONTROLLER EventController"
		log.debug "PARAMETROS $params"
		def pagingConfig = []
		if(params.rows){
			pagingConfig = [
				max: Integer.parseInt(params.rows),
				offset:	Integer.parseInt(params.page-1)
			]
		}
		def list = User.createCriteria().list(pagingConfig){
			and{
				eq("enabled",true)
				eq("esProfesional",true)
				secretaria{
					eq("id",true)
				}
			}	
		}
		
	}
	
	private def listatencionjson(def params) {
		log.info "INGRESANDO AL METODO PRIVADO listatencionjson DEL CONTROLLER EventController"
		log.debug "PARAMETROS $params"	
		
		def pagingConfig = []
	
		
		if(params.rows){
			pagingConfig = [
				max: Integer.parseInt(params.rows),
				offset:	Integer.parseInt("1")-1
			]
		}
		def list = Event.createCriteria().list(){
			
			profesional{
				eq('id',new Long(params.profesionalId))
			}
		}
		
		
		
		
    	log.debug "TOTAL DE LIST: "+list.size()
    	def totalregistros=list.size()
    	def totalpaginas=new Float(totalregistros/Integer.parseInt(params.rows))
    	
    	def result='{"page":'+params.page+',"total":"'+totalpaginas+'","records":"'+totalregistros+'","rows":['
    	
    	
    	def flagaddcomilla=false
    	log.debug "Total registros: "+totalregistros
    	list.each{
    		if (flagaddcomilla)
    			result=result+','
    		log.debug "FECHA FORMATEADA CON EL TAG: "+g.formatDate(format:"dd/MM/yyyy hh:mm:ss",date:it.fechaStart)	
    		result=result+'{"id":"'+it.id+'","cell":["'+it.id+'","'+ (it.paciente?it.paciente?.apellido+'-'+it.paciente?.nombre:it.titulo)+'","'+it.estado+'","'+it.estado.name+'","'+it.version+'","'+g.formatDate(format:"dd/MM/yyyy hh:mm",date:it.fechaStart)+'","'+g.formatDate(format:"dd/MM/yyyy hh:mm",date:it.fechaEnd)+'"]}'
    		flagaddcomilla=true
    	}
    	
    	result=result+']}'
    	render result
    	
    	
    	/*render(contentType:"text/json"){
			if(list.size()>0)
    			success = true
			else
				success = false	
    		results = array{
    			list.each{
    				result(id:it.id,estado:'hola',paciente:it.paciente.apellido+"-"+it.paciente.nombre)
    				
    			}
    		}
    	}*/
	}
	
	def atenciondeldia= {
		log.info "INGRESANDO AL CLOSURE atenciondeldia"
		log.info "PARAMETROS ${params}"
		log.info "INGRESANDO"
		Calendar cal= Calendar.getInstance()
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		def dateWithoutTime = cal.getTime();
		def user = User.load(authenticateService.userDomain().id)
		def filtersJson 
		def oper
		
		log.debug "PROFESIONAL ASIGNADO ID: "+user?.profesionalAsignado?.id
		//def list = new GUtilDomainClass(Event,params,grailsApplication).listrefactor()
		def criteria = Event.createCriteria()
		def closure = {
			criteria.and(){
				criteria.profesional(){
					criteria.eq('id',user.profesionalAsignado?.id)
				}
				criteria.gt('fechaStart',dateWithoutTime)
				if(Boolean.parseBoolean(params._search)){
					if(params.filters){
						filtersJson = JSON.parse(params.filters)
						criteria."${filtersJson.groupOp.toLowerCase()}"(){
							filtersJson?.rules?.each{
								oper = FilterUtils.operationSearch(it.op)
								criteria."${oper}"(it.field,"%"+it.data+"%")
							}
						}
					}
				}
			}
		}
		
		def list = criteria.list(closure)

		
		log.debug "TOTAL DE LIST: "+list.size()
		def totalregistros=list.size()
		def totalpaginas=new Float(totalregistros/Integer.parseInt(params.rows))
		
		def result='{"page":'+params.page+',"total":"'+totalpaginas+'","records":"'+totalregistros+'","rows":['
		
		
		def flagaddcomilla=false
		log.debug "Total registros: "+totalregistros
		list.each{
			if (flagaddcomilla)
				result=result+','
			log.debug "FECHA FORMATEADA CON EL TAG: "+g.formatDate(format:"dd/MM/yyyy hh:mm:ss",date:it.fechaStart)
			result=result+'{"id":"'+it.id+'","cell":["'+it.id+'","'+ (it.paciente?it.paciente?.apellido+'-'+it.paciente?.nombre:it.titulo)+'","'+it.estado+'","'+it.estado.name+'","'+it.version+'","'+g.formatDate(format:"dd/MM/yyyy hh:mm",date:it.fechaStart)+'","'+g.formatDate(format:"dd/MM/yyyy hh:mm",date:it.fechaEnd)+'"]}'
			flagaddcomilla=true
		}
		
		result=result+']}'
		render result

	}
	
	def updateestado = {
		log.info "INGRESANDO AL CLOSURE updateestado DEL CONTROLLER EventController"
		log.info "PARAMETROS: ${params}"
		def eventInstance = Event.get(params.id.toLong())
        if (eventInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (eventInstance.version > version) {
					
                    render (contentType:"text/json"){
						result success:false,title:"Error, el turno está siendo modificado por otro usuario"
					}
					return
                }
            }
			eventInstance.properties=params
			
            if (!eventInstance.hasErrors() && eventInstance.save(flush: true)) {
                render (contentType:"text/json"){
					result success:true,title:eventInstance.titulo
				}
            }
            else {
                render (contentType:"text/json"){
                	result success:false,title:"Error, datos incompletos"
                }
            }
        }
        else {
            render(contentType:"text/json"){
            	result success:false,title:"${message(code: 'default.not.found.message', args: [message(code: 'event.label', default: 'Event'), params.id])}"
            }
        }

	}
	 
	def editpaciente = {
		log.info "INGRESANDO AL CLOSURE editpaciente DEL CONTROLLER EventController"
		log.info "PARAMETROS: ${params}"
		def eventInstance = Event.load(new Long(params.id))
		if (eventInstance.paciente)
			redirect(controller:"paciente",action: "edit", id: eventInstance.paciente.id)
		else{
			log.debug "ID DEL EVENTO A ASOCIAR CON EL PACIENTE QUE SE CREARA: "+eventInstance.id
			log.debug "VERSION DEL EVENTO A ASOCIAR CON EL PACIENTE QUE SE CREARA: "+eventInstance.version
			redirect(controller:"paciente",action: "create",params:[eventId:eventInstance.id,eventVersion:eventInstance.version])
		}	
	}    
}
