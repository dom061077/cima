	package com.medfire
    
import java.util.Map; 

import com.medfire.util.GUtilDomainClass

class UserController {  
	def authenticateService
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {  
        redirect(action: "list", params: params)
    } 
  
    def list = { 
		log.info "INGRESANDO AL CLOSURE list DEL CONTROLLER UserController"
		log.info "PARAMETROS: ${params}"
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
		log.debug "CANTIDAD DE USUARIOS: "+User.count()
        [userInstanceList: User.list(params), userInstanceTotal: User.count()]
    } 

    def create = {
		log.info "INGRESANDO AL CLOSURE create DEL CONTROLLER UserController"
		log.info "PARAMETROS: ${params}"
		
        def userInstance = new User(params)
		//userInstance.passwd = authenticateService.encodePassword(params.passwd)
		def roles = Role.list()
        //userInstance.properties = params
		userInstance.enabled=true
		
		
		roles.sort { r1, r2 ->
			r1.authority <=> r2.authority
		}
		LinkedHashMap<Role, Boolean> roleMap = [:]
		for (role in roles) {
			roleMap[(role)]=false
		}
		
        return [userInstance: userInstance,authorityList:roleMap]
    }

    def saverefactor = { 
		log.info "INGRESANDO AL CLOSURE save DEL CONTROLLER UserController"
		log.info "PARAMETROS: $params"
        def userInstance = new User(params)
		userInstance.passwd = authenticateService.encodePassword(params.passwd)
		def roles = Role.list()
		def cantRoles=0
		roles.sort { r1, r2 ->
			r1.authority <=> r2.authority
		}
		LinkedHashMap<Role, Boolean> roleMap = [:]
		for (role in roles) {
			roleMap[(role)]=false
		}
		
		def rolAux
		for (String key in params.keySet()) {
			if (key.contains('ROLE') && 'on' == params.get(key)) {
				//log.debug "ROL AGREGADO: "+key
				rolAux=Role.findByAuthority(key)
				//roleMap.remove(rolAux)
				roleMap[rolAux]=true
				cantRoles++
			}
		}

		if(cantRoles>1){
			userInstance.validate()
			userInstance.errors.rejectValue("authorities","user.roles.exluyentes","Solo puede selecionar un Rol")
			render(view: "create", model: [userInstance: userInstance,authorityList:roleMap])
			return
		}
        if (userInstance.save(flush: true)) {
			addRoles(userInstance)
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'user.label', default: 'User'), userInstance.id])}"
            redirect(action: "showrefactor", id: userInstance.id)
			
        } 
        else {
			log.warn "ERRORES DE VALIDACION: "+userInstance.errors.allErrors
            render(view: "create", model: [userInstance: userInstance,authorityList:roleMap])
        }
    }

    def showrefactor = { 
		log.info "INGRESANDO AL CLOSURE showrefactor"
		log.info "PARAMETROS: ${params}"
        def person = User.get(params.id)
        if (!person) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), params.id])}"
            redirect(action: "list")
        }
        else {
			log.debug "USUARIO ENCONTRADO ${person} "
            [userInstance: person]
        }
    }

    def editrefactor = {
		log.info "INGRESANDO AL CLOSURE editrefactor DEL CONTROLLER UserController"
		log.info "PARAMETROS: ${params}"
        def userInstance = User.get(params.id)
		def roles = Role.list()
        if (!userInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), params.id])}"
            redirect(action: "list")
        }
        else {
			roles = Role.list()
			roles.sort { r1, r2 ->
				r1.authority <=> r2.authority
			}
			Set userRoleNames = []
			for (role in userInstance.authorities) {
				userRoleNames << role.authority
			}
			LinkedHashMap<Role, Boolean> roleMap = [:]
			for (role in roles) {
				//log.debug "role: "+role
				
				roleMap[(role)] = userRoleNames.contains(role.authority)
			}
			return [userInstance: userInstance, authorityList: roleMap]
        }
    }

	def updaterefactor = {
		log.info "INGRESANDO AL CLOSURE updaterector"
		log.info "PARAMETROS: $params"
		def userInstance = User.get (params.id)
		def	roles = Role.list()
		roles.sort { r1, r2 ->
			r1.authority <=> r2.authority
		}
		Set userRoleNames = []
		for (role in userInstance.authorities) {
			userRoleNames << role.authority
		}
		LinkedHashMap<Role, Boolean> roleMap = [:]
		def cantRoles=0
		
		for (role in roles) {
			roleMap[(role)] = false//userRoleNames.contains(role.authority)
		}
		def rolAux
		for (String key in params.keySet()) {
			if (key.contains('ROLE') && 'on' == params.get(key)) {
				//log.debug "ROL AGREGADO: "+key
				rolAux=Role.findByAuthority(key)
				//roleMap.remove(rolAux)
				roleMap[rolAux]=true
				cantRoles++
			}
		}


		if(cantRoles>1){
			userInstance.validate()
			userInstance.errors.rejectValue("authorities","user.roles.exluyentes","Solo puede selecionar un Rol")
			render(view: "editrefactor", model: [userInstance: userInstance,authorityList:roleMap])
			return
		}

		
		if (userInstance) {
			if (params.version) {
				def version = params.version.toLong()
				if (userInstance.version > version) {
					
					userInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'user.label', default: 'User')] as Object[], "Another user has updated this User while you were editing")
					render(view: "editrefactor", model: [userInstance: userInstance,authorityList:roleMap])
					return
				}
			}
			def oldPasswd = userInstance.passwd
			userInstance.properties = params
			if(!oldPasswd.equals(params.passwd)){
				log.info "PASSWORD DISTINTA DE LA ANTERIOR"
				userInstance.passwd = authenticateService.encodePassword(params.passwd)
			}
			if (!userInstance.hasErrors() && userInstance.save(flush: true)) {
				Role.findAll().each{
					it.removeFromPeople(userInstance)
				}
				addRoles(userInstance)
				flash.message = "${message(code: 'default.updated.message', args: [message(code: 'user.label', default: 'User'), userInstance.id])}"
				log.info "REGISTRO MODIFICADO"
				redirect(action: "showrefactor", id: userInstance.id)
			}
			else {
				log.warn "ERROR DE VALIDACION: "+userInstance.errors.allErrors
				render(view: "editrefactor", model: [userInstance: userInstance,authorityList:roleMap])
			}
		}
		else {
			log.warn "EL USUARIO CON ID $params.id NO ENCONTRADO"
			flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), params.id])}"
			redirect(action: "list")
		}

	}
	
    def update = {
		log.info "INGRESANDO AL CLOSURE update DEL CONTROLLER UserController"
		log.info "PARAMETROS: ${params}"
        def userInstance = User.get(params.id.toLong())
		log.info "ANTES DEL IF (userInstance)"
        if (userInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (userInstance.version > version) {
                    
                    userInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'user.label', default: 'User')] as Object[], "Another user has updated this User while you were editing")
                    render(view: "editrefactor", model: [userInstance: userInstance])
                    return
                }
            }
			def oldPasswd = userInstance.passwd
			log.info "antes del bindin con params"
            userInstance.properties = params
			log.info "PASSWORD PLANO:"+params.passwd
			if(!oldPasswd.equals(params.passwd)){
				log.info "PASSWORD DISTINTA DE LA ANTERIOR"
				userInstance.passwd = authenticateService.encodePassword(params.passwd)
			}
			log.info "antes del if que hace haserrors y el save"
            if (!userInstance.hasErrors() && userInstance.save(flush: true)) {
				Role.findAll().each{
					it.removeFromPeople(userInstance)
				}
				log.info "antes de addRoles"
			    addRoles(userInstance)
				log.info "despues de addRoles"
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'user.label', default: 'User'), userInstance.id])}"
				log.info "REGISTRO MODIFICADO"
                redirect(action: "showrefactor", id: userInstance.id)
            }
            else {
                render(view: "editfactor", model: [userInstance: userInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
		log.info "INGRESANDO AL CLOSURE delete DEL CONTROLLER UserController"
		log.info "PARAMETROS: ${params}"
        def userInstance = User.get(params.id)
        if (userInstance) {
            try {
                userInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'user.label', default: 'User'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'user.label', default: 'User'), params.id])}"
                redirect(action: "showrefactor", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), params.id])}"
            redirect(action: "list")
        }
    }


	def listjson = {
		log.info "INGRESANDO AL CLOSURE listjson DEL CONTROLLER UserController"
		log.info "PARAMETROS: ${params}"
		def gud = new GUtilDomainClass(User,params,grailsApplication)
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


			result=result+'{"id":"'+it.id+'","cell":["'+it.id+'","'+(it.username==null?"":it.username)+'","'+(it.userRealName==null?"":it.userRealName)+'","'+(it.enabled==null?"":(it.enabled==true?'SI':'NO'))+'","'+(it.esProfesional==null?"":it.esProfesional)+'","'+(it.email==null?"":it.email)+'","'+(it.profesionalAsignado?.nombre==null?"":it.profesionalAsignado?.nombre)+'"]}'
			 
			flagaddcomilla=true
		}
		result=result+']}'
		render result

	}
	
	private void addRoles(user) {
		log.info "INGRESANDO AL METODO PRIVADO addRoles"
		for (String key in params.keySet()) {
			if (key.contains('ROLE') && 'on' == params.get(key)) {
				log.debug "ROL AGREGADO: "+key
				Role.findByAuthority(key).addToPeople(user)
			}
		}
	}

	def changepassword = {/*UserPasswordCommand usrc ->*/
		log.info "INGRESNDO AL CLOSURE changepassword"
		log.info "PARAMETROS: $params"
		def userCommand = new UserPasswordCommand()
		return [userInstance:userCommand]
	}	
	
	def change = {UserPasswordCommand cmd ->
		log.info "INGRESANDO AL CLOSURE change"
		log.info "PARAMETROS $params"
		
		if(cmd.validate()){
			def userInstance = User.get(authenticateService.userDomain().id)
			userInstance.passwd = authenticateService.encodePassword(cmd.newPassword)
			userInstance.save()
			flash.message=g.message(code:"user.sucesschanged.flash.message")
			render(view:"/index")
		}else{
			log.debug "ERRORES DE VALIDACION: "+cmd.errors.allErrors
			render(view: "changepassword", model: [userInstance:cmd])
		}
	}

}



class UserPasswordCommand {
	def authenticateService
	String id
	String oldPassword
	String newPassword
	String passwordRepeat
	
	String getOldPasswordEncrypted(){
		return authenticateService.encodePassword(oldPassword)
	}
	
	String getLoggedPassword(){
		return authenticateService.userDomain().passwd
	}
	
	static constraints={
		oldPassword(blank:false,validator: { passwd2, cmd ->
					if(!cmd.oldPasswordEncrypted.equals(cmd.loggedPassword))	
                    	return "oldPasswordEncrypted: "+cmd.oldPasswordEncrypted+" password: "+cmd.loggedPassword
					if(passwd2==cmd.newPassword)
						return "equals.oldpassword"

                })
		newPassword(blank:false)
		passwordRepeat(blank:false,validator:{ current, cmd ->
				if(!current.equals(cmd.newPassword))
					return "notequals"
		})
	} 	
}