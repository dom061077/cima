package com.medfire

import org.springframework.transaction.TransactionStatus
import com.medfire.util.GUtilDomainClass
 
class AntecedenteLabelController {
	def authenticateService
	
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        //params.max = Math.min(params.max ? params.int('max') : 10, 100)
        //[antecedenteLabelInstanceList: AntecedenteLabel.list(params), antecedenteLabelInstanceTotal: AntecedenteLabel.count()]
    }

    def create = {
        def antecedenteLabelInstance = new AntecedenteLabel()
        antecedenteLabelInstance.properties = params
        return [antecedenteLabelInstance: antecedenteLabelInstance]
    }

    def save = {
		log.info "INGRESANDO AL CLOSURE save"
		
        def antecedenteLabelInstance = new AntecedenteLabel(params)
		def profesionalInstance = User.load(authenticateService.userDomain().id).profesionalAsignado
		profesionalInstance.antecedenteLabel=antecedenteLabelInstance
		log.debug "PROFESIONAL: ASIGNADO "+profesionalInstance
		log.debug "PROFESIONAL ASIGNADO DESDE EL USUARIO: "+antecedenteLabelInstance.profesional
        if (profesionalInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'antecedenteLabel.label', default: 'AntecedenteLabel'), antecedenteLabelInstance.id])}"
            redirect(action: "show", id: antecedenteLabelInstance.id)
        }
        else {
            render(view: "createprof", model: [antecedenteLabelInstance: antecedenteLabelInstance])
        }
    }

    def show = {
        def antecedenteLabelInstance = AntecedenteLabel.get(params.id)
        if (!antecedenteLabelInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'antecedenteLabel.label', default: 'AntecedenteLabel'), params.id])}"
            redirect(action: "list")
        }
        else {
            [antecedenteLabelInstance: antecedenteLabelInstance]
        }
    }

    def edit = {
        def antecedenteLabelInstance = AntecedenteLabel.get(params.id)
        if (!antecedenteLabelInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'antecedenteLabel.label', default: 'AntecedenteLabel'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [antecedenteLabelInstance: antecedenteLabelInstance]
        }
    }

    def update = {
        def antecedenteLabelInstance = AntecedenteLabel.get(params.id)
        if (antecedenteLabelInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (antecedenteLabelInstance.version > version) {
                    
                    antecedenteLabelInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'antecedenteLabel.label', default: 'AntecedenteLabel')] as Object[], "Another user has updated this AntecedenteLabel while you were editing")
                    render(view: "edit", model: [antecedenteLabelInstance: antecedenteLabelInstance])
                    return
                }
            }
            antecedenteLabelInstance.properties = params
            if (!antecedenteLabelInstance.hasErrors() && antecedenteLabelInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'antecedenteLabel.label', default: 'AntecedenteLabel'), antecedenteLabelInstance.id])}"
                redirect(action: "show", id: antecedenteLabelInstance.id)
            }
            else {
                render(view: "edit", model: [antecedenteLabelInstance: antecedenteLabelInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'antecedenteLabel.label', default: 'AntecedenteLabel'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def antecedenteLabelInstance = AntecedenteLabel.get(params.id)
        if (antecedenteLabelInstance) {
			Profesional.withTransaction{TransactionStatus status ->
	            try {
					antecedenteLabelInstance.profesional.antecedenteLabel=null
						antecedenteLabelInstance.profesional.save(flush:true)
						antecedenteLabelInstance.delete(flush: true)
						flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'antecedenteLabel.label', default: 'AntecedenteLabel'), params.id])}"
						redirect(action: "list")
		
	            }
	            catch (org.springframework.dao.DataIntegrityViolationException e) {
					status.setRollbackOnly()
	                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'antecedenteLabel.label', default: 'AntecedenteLabel'), params.id])}"
	                redirect(action: "show", id: params.id)
	            }
			}
			
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'antecedenteLabel.label', default: 'AntecedenteLabel'), params.id])}"
            redirect(action: "list")
        }
    }
	
	def redirect = {
		log.info "INGRESANDO AL CLOSURE redirect"
		log.info "PARAMETROS: $params"
		def userInstance = User.load(authenticateService.userDomain().id)  
		//def profesionalInstance = Profesional.load(authenticateService.userDomain().profesionalAsignado?.id)
		if(!userInstance.profesionalAsignado){
			flash.message="No tiene un profesional asignado a su usuario"
			render(view:"/index")
			return
		}
		
		if (userInstance.profesionalAsignado?.antecedenteLabel){
			flash.message="Ud. y hizo una carga inicial de etiquetas, si desea modificar alguna consulte a su Administrador"
			redirect(action:"show",params:[id:userInstance.profesionalAsignado.antecedenteLabel.id])
		} else{
			flash.message="Está por cargar por primera vez las etiquetas, recuerde que si luego quiere modificar alguna de ellas debe consultar a su Administrador"
			redirect(action:"createprof")
		}
	}
	
	def createprof = {
		log.info "INGRESANDO AL CLOSURE createprof"
		log.info "PARAMETROS: $params"
		def userInstance = User.load(authenticateService.userDomain().id)
		if(!userInstance.profesionalAsignado){
			flash.message="Su usuario no tiene un profesional asignado"
			render(view:"/index")
			return
		}
		def antecedenteLabelInstance = new AntecedenteLabel()
		antecedenteLabelInstance.properties = params
		antecedenteLabelInstance.profesional = userInstance.profesionalAsignado
		return [antecedenteLabelInstance:antecedenteLabelInstance]
	}
	
	def listjson = {
		log.info "INGRESANDO AL CLOSURE listjson"
		log.info "PARAMETROS: $params"
		def gud = new GUtilDomainClass(AntecedenteLabel,params,grailsApplication)
		def list=gud.listrefactor(false)
		def totalregistros=gud.listrefactor(true)
		
		def totalpaginas=new Float(totalregistros/Integer.parseInt(params.rows))
		if (totalpaginas>0 && totalpaginas<1)
			totalpaginas=1;
		totalpaginas=totalpaginas.intValue()

		
		log.debug "TOTAL USUARIOS: "+list.size()
		
		def result='{"page":'+params.page+',"total":"'+totalpaginas+'","records":"'+totalregistros+'","rows":['
		log.debug "CONSULTA DE USUARIOS: $list"
		def flagaddcomilla=false
		def urlimg
		list.each{
			
			if (flagaddcomilla)
				result=result+','


			result=result+'{"id":"'+it.id+'","cell":["'+it.id+'","'+(it.profesional.nombre==null?"":it.profesional.nombre)+'","'+(it.profesional.matricula==null?"":it.profesional.matricula)+'"]}'
			 
			flagaddcomilla=true
		}
		result=result+']}'
		render result
		
	}
	
}
