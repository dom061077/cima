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

				
        def profesionalInstance = new Profesional(params)
		if(params.localidad.id){
			profesionalInstance.localidad = Localidad.load(params.localidad.id.toLong())
		}
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
			
            profesionalInstance.properties = params
            if (!profesionalInstance.hasErrors() && profesionalInstance.save(flush: true)) {
				if (!profesionalInstance.photo.isEmpty()){
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
    	list.each{
    		
    		if (flagaddcomilla)
    			result=result+','
			//log.debug it.class
			try{		
				urlimg = bi.resource(size:'small',bean:it)
			}catch(Exception e){	
				urlimg = ''	
			}
    		result=result+'{"id":"'+it.id+'","cell":["'+it.id+'","'+(it.cuit==null?"":it.cuit)+'","'+(it.matricula==null?"":it.matricula)+'","'+(it.nombre==null?"":it.nombre)+'","'+(it.telefono==null?"":it.telefono)+'","'+urlimg+'"]}'
    		 
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
	
}
