package com.medfire

import com.medfire.util.GUtilDomainClass
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.text.ParseException;
import org.springframework.web.multipart.commons .CommonsMultipartFile

class ProfesionalController {
	def imageUploadService
	
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [profesionalInstanceList: Profesional.list(params), profesionalInstanceTotal: Profesional.count()]
    }

    def create = {
        def profesionalInstance = new Profesional()
        profesionalInstance.properties = params
        return [profesionalInstance: profesionalInstance]
    }

    def save = {
		log.info "INGRESANDO AL CLOSURE save DEL CONTROLLER ProfesionalController"
		log.info "PARAMETROS: ${params}"
		def fechaNacimientoError=false
		def fechaIngresoError=false

		if (params.fechaNacimiento){
			params.fechaNacimiento_year=params.fechaNacimiento.substring(6,10)
			log.debug "Anio "+params.fechaNacimiento.substring(6,10)
			params.fechaNacimiento_month=params.fechaNacimiento.substring(3,5)
			log.debug "Mes "+params.fechaNacimiento.substring(3,5)
			params.fechaNacimiento_day=params.fechaNacimiento.substring(0,2)
			log.debug "Dia "+params.fechaNacimiento.substring(0,2)
			if(params.fechaNacimiento_month.toInteger()>12)
				fechaNacimientoError=true
			if(params.fechaNacimiento_day.toInteger()>31){
				log.debug "DIA mayor 31"
				fechaNacimientoError=true
			}
		}

		
		if (params.fechaIngreso){
			params.fechaIngreso_year=params.fechaIngreso.substring(6,10)
			params.fechaIngreso_month=params.fechaIngreso.substring(3,4)
			params.fechaIngreso_day=params.fechaIngreso.substring(0,1)
			if(params.fechaIngreso_month.toInteger()>12)
				fechaIngresoError=true
			if(params.fechaIngreso_day.toInteger()>31)
				fechaIngresoError=true
		}

				
        def profesionalInstance = new Profesional(params)
		if(fechaIngresoError){
			profesionalInstance.errors.rejectValue("fechaIngreso","com.medfire.Profesional.fechaIngreso.date.error","")
			render(view: "create", model: [profesionalInstance: profesionalInstance])
			return
		}
		if(fechaNacimientoError){
			profesionalInstance.errors.rejectValue("fechaNacimiento","com.medfire.Profesional.fechaNacimiento.date.error","Ingrese una fecha correcta, se sugiere una correción")
			//profesionalInstance.errors.getFieldError('fechaNacimiento').rejectedValue
			log.debug "ERROR EN FECHA DE NACIMIENTO SEGUN BANDERA"
			render(view: "create", model: [profesionalInstance: profesionalInstance])
			return
		}	
		if(params.localidad?.id){
			profesionalInstance.localidad = Localidad.load(params.localidad.id.toLong())
		}
		
		profesionalInstance.antecedenteLabel= new AntecedenteLabel()
		
		//profesionalInstance.photo = request.getFile("photo")
	
        if (profesionalInstance.save()) {
			if (!profesionalInstance.photo.isEmpty()){
				log.debug "EXISTE EL CONTENIDO DE LA FOTO DEL PROFESIONAL"
				imageUploadService.save(profesionalInstance)
			}else{
				log.debug "NO EXISTE EL CONTENIDO DE LA FOTO DEL PROFESIONAL"
				def path=grailsApplication.parentContext.getResource("images/noDisponible.jpg").file.toString()
				//profesionalInstance.photo=new FileInputStream(path);
			}

            flash.message = "${message(code: 'default.created.message', args: [message(code: 'profesional.label', default: 'Profesional'), profesionalInstance.id])}"
            redirect(action: "show", id: profesionalInstance.id)
        }
        else {
			log.debug "ERROR DE VALIDACION: "+profesionalInstance.errors.allErrors
            render(view: "create", model: [profesionalInstance: profesionalInstance])
        }
    }

    def show = {
        def profesionalInstance = Profesional.get(params.id)
        if (!profesionalInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'profesional.label', default: 'Profesional'), params.id])}"
            redirect(action: "list")
        }
        else {
            [profesionalInstance: profesionalInstance]
        }
    }

    def edit = {
        def profesionalInstance = Profesional.get(params.id)
        if (!profesionalInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'profesional.label', default: 'Profesional'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [profesionalInstance: profesionalInstance]
        }
    }

    def update = {
		log.info "INGRESANDO AL CLOSURE update DEL CONTROLLER ProfesionalController"
		log.info "PARAMETROS: ${params}"
        def profesionalInstance = Profesional.get(new Long(params.id))
        if (profesionalInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (profesionalInstance.version > version) {
                    
                    profesionalInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'profesional.label', default: 'Profesional')] as Object[], "Another user has updated this Profesional while you were editing")
                    render(view: "edit", model: [profesionalInstance: profesionalInstance])
                    return
                }
            }
			if (params.fechaNacimiento){
				DateFormat df = new SimpleDateFormat("dd/MM/yyyy")
				log.debug "FECHA REEMPLAZADA: "+params.fechaNacimiento
				def fecha
				try{
					fecha = df.parse(params.fechaNacimiento)
					log.debug "LA FECHA SE PARSEO BIEN"
				}catch(ParseException e){
					log.debug "LA FECHA NO SE PARSEO BIEN"
				}
				def gc = Calendar.getInstance()
				gc.setTime(fecha)
				log.debug "ANIO: "+gc.get(Calendar.YEAR).toString()+", MES "+gc.get(Calendar.MONTH+1).toString()+" DIA "+gc.get(Calendar.DATE).toString()
				params.fechaNacimiento_year=gc.get(Calendar.YEAR).toString()
				params.fechaNacimiento_month=(gc.get(Calendar.MONTH)+1).toString()
				params.fechaNacimiento_day=gc.get(Calendar.DATE).toString()
			}
			
			if (params.fechaIngreso){
				DateFormat df = new SimpleDateFormat("dd/MM/yyyy")
				log.debug "FECHA REEMPLAZADA: "+params.fechaIngreso
				def fecha
				try{
					fecha = df.parse(params.fechaIngreso)
					log.debug "LA FECHA SE PARSEO BIEN"
				}catch(ParseException e){
					log.debug "LA FECHA NO SE PARSEO BIEN"
				}
				def gc = Calendar.getInstance()
				gc.setTime(fecha)
				log.debug "ANIO: "+gc.get(Calendar.YEAR).toString()+", MES "+gc.get(Calendar.MONTH+1).toString()+" DIA "+gc.get(Calendar.DATE).toString()
				params.fechaIngreso_year=gc.get(Calendar.YEAR).toString()
				params.fechaIngreso_month=(gc.get(Calendar.MONTH)+1).toString()
				params.fechaIngreso_day=gc.get(Calendar.DATE).toString()
			}
	
			
            profesionalInstance.properties = params
            if (!profesionalInstance.hasErrors() && profesionalInstance.save(flush: true)) {
				if (!params.photo.isEmpty()){
					log.debug "EXISTE EL CONTENIDO DE LA FOTO DEL PROFESIONAL"
					imageUploadService.save(profesionalInstance)
				}else{
					log.debug "NO EXISTE EL CONTENIDO DE LA FOTO DEL PROFESIONAL"
					def path=grailsApplication.parentContext.getResource("images/noDisponible.jpg").file.toString()
					//profesionalInstance.photo=new FileInputStream(path);
				}
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'profesional.label', default: 'Profesional'), profesionalInstance.id])}"
                redirect(action: "show", id: profesionalInstance.id)
            }
            else {
                render(view: "edit", model: [profesionalInstance: profesionalInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'profesional.label', default: 'Profesional'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def profesionalInstance = Profesional.get(params.id)
        if (profesionalInstance) {
            try {
                profesionalInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'profesional.label', default: 'Profesional'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'profesional.label', default: 'Profesional'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'profesional.label', default: 'Profesional'), params.id])}"
            redirect(action: "list")
        }
    }
	
	def listjson = {
		log.info "INGRESANDO AL CLOSURE listjson DEL CONTROLLER ProfesionalController"
		log.info "PARAMETROS: ${params}"
		def gud=new GUtilDomainClass(Profesional,params,grailsApplication)
    	def list=gud.listrefactor(false)	
		def totalregistros=gud.listrefactor(true)
		
		def totalpaginas=new Float(totalregistros/Integer.parseInt(params.rows))
		if (totalpaginas>0 && totalpaginas<1)
			totalpaginas=1;
		totalpaginas=totalpaginas.intValue()

    	
    	log.debug "TOTAL PROFESIONALES: "+list.size()
    	
    	def result='{"page":'+params.page+',"total":"'+totalpaginas+'","records":"'+totalregistros+'","rows":['
    	log.debug "CONSULTA DE PROFESIONALES: $list"
    	def flagaddcomilla=false
		def urlimg
		def urllargeimg
    	list.each{
    		
    		if (flagaddcomilla)
    			result=result+','
				
//			try{		
//				urlimg = bi.resource(size:'small',bean:it)
//			}catch(Exception e){	
//				urlimg = g.resource(dir:'images',file:'noDisponible.jpg')	
//			}
			
			urlimg = bi.resource(size:'small',bean:it)
			urllargeimg = bi.resource(size:'large',bean:it)
			if(urlimg.contains(".null")){
				urlimg = g.resource(dir:'images',file:'noDisponible.jpg')
				urllargeimg = g.resource(dir:'images',file:'noDisponibleLarge.jpg')
			}
    		result=result+'{"id":"'+it.id+'","cell":["'+it.id+'","'+(it.cuit==null?"":it.cuit)+'","'+(it.matricula==null?"":it.matricula)+'","'+(it.nombre==null?"":it.nombre)+'","'+(it.telefono==null?"":it.telefono)+'","'+urllargeimg+'","'+urlimg+'"]}'
    		 
    		flagaddcomilla=true
    	}
    	result=result+']}'
    	render result

	}
	
	def listjsonautocomplete={
		log.info "INGRESANDO AL CLOSURE listjsonautocomplete"
		log.info "PARAMETROS: ${params}"
		def profesionales = Profesional.createCriteria().list(){
				like('nombre','%'+params.term+'%')
		}
		log.debug "PROFESIONALES LISTADOS: "+profesionales.size()
		render(contentType:"text/json"){
			array{
				for (prof in profesionales){
					profesional id:prof.id,label:prof.nombre,value:prof.nombre
				}
			}
			
		}
	}
	
	def listado = {
		log.info "INGRESANDO AL CLOSURE listado DEL CONTROLADOR ProfesionalController"
		log.info "PARAMAMETROS: $params"

		def profesionales = Profesional.list()
		chain(controller:'jasper',action:'index',model:[data:profesionales],params:params)
	}

	
}
