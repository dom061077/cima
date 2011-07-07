package com.medfire

import com.medfire.util.GUtilDomainClass

class Cie10Controller {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [cie10InstanceList: Cie10.list(params), cie10InstanceTotal: Cie10.count()]
    }

    def create = {
        def cie10Instance = new Cie10()
        cie10Instance.properties = params
        return [cie10Instance: cie10Instance]
    }

    def save = {
        def cie10Instance = new Cie10(params)
        if (cie10Instance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'cie10.label', default: 'Cie10'), cie10Instance.id])}"
            redirect(action: "show", id: cie10Instance.id)
        }
        else {
            render(view: "create", model: [cie10Instance: cie10Instance])
        }
    }

    def show = {
        def cie10Instance = Cie10.get(params.id)
        if (!cie10Instance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'cie10.label', default: 'Cie10'), params.id])}"
            redirect(action: "list")
        }
        else {
            [cie10Instance: cie10Instance]
        }
    }

    def edit = {
        def cie10Instance = Cie10.get(params.id)
        if (!cie10Instance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'cie10.label', default: 'Cie10'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [cie10Instance: cie10Instance]
        }
    }

    def update = {
        def cie10Instance = Cie10.get(params.id)
        if (cie10Instance) {
            if (params.version) {
                def version = params.version.toLong()
                if (cie10Instance.version > version) {
                    
                    cie10Instance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'cie10.label', default: 'Cie10')] as Object[], "Another user has updated this Cie10 while you were editing")
                    render(view: "edit", model: [cie10Instance: cie10Instance])
                    return
                }
            }
            cie10Instance.properties = params
            if (!cie10Instance.hasErrors() && cie10Instance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'cie10.label', default: 'Cie10'), cie10Instance.id])}"
                redirect(action: "show", id: cie10Instance.id)
            }
            else {
                render(view: "edit", model: [cie10Instance: cie10Instance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'cie10.label', default: 'Cie10'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def cie10Instance = Cie10.get(params.id)
        if (cie10Instance) {
            try {
                cie10Instance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'cie10.label', default: 'Cie10'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'cie10.label', default: 'Cie10'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'cie10.label', default: 'Cie10'), params.id])}"
            redirect(action: "list")
        }
    }
	
	def listsearchjson = {
		log.info "INGRESANDO AL CLOSURE listsearchjson DEL CONTROLLER Cie10Controller"
		log.info "PARAMETROS ${params}"
		def gud=new GUtilDomainClass(Cie10,params,grailsApplication)
		list=gud.listrefactor(false)
		def totalregistros=gud.listrefactor(true)
		
		def totalpaginas=new Float(totalregistros/Integer.parseInt(params.rows))
		if (totalpaginas>0 && totalpaginas<1)
			totalpaginas=1;
		totalpaginas=totalpaginas.intValue()

		
		 
		def result='{"page":'+params.page+',"total":"'+totalpaginas+'","records":"'+totalregistros+'","rows":['
		log.debug "CONSULTA DE Cie10's: $list"
		def flagaddcomilla=false
		list.each{
			
			if (flagaddcomilla)
				result=result+','
			
			result=result+'{"id":"'+it.id+'","cell":["'+it.id+'","'+it.cie10+'","'+it.descripcion+'"]}'
			 
			flagaddcomilla=true
		}
		result=result+']}'
		render result

	}
	
	def listautocompletejson = {
		log.info "INGRESANDO AL CLOSURE listautocomplejson DEL CONTROLLER Ci10Controller"
		log.info "PARAMETROS $params"

		def list = Cie10.createCriteria().list(){
			like('descripcion','%'+params.term+'%')
		}
		log.debug "CONSULTA DE Cie10's: $list"

		render (contentType:"text/json"){
			 array{
						for(os in list){
							obrasocial id:os.id,label:os.descripcion, value:os.descripcion	
						}
					}
		}

	}
}
