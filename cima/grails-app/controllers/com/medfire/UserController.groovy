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
        def userInstance = new User()
		//userInstance.passwd = authenticateService.encodePassword(params.passwd)
		def roles = Role.list()
        userInstance.properties = params
        return [userInstance: userInstance,authorityList:roles]
    }

    def save = {
		log.info "INGRESANDO AL CLOSURE save DEL CONTROLLER UserController"
		log.info "PARAMETROS: $params"
        def userInstance = new User(params)
		userInstance.passwd = authenticateService.encodePassword(params.passwd)
		def roles = Role.list()
        if (userInstance.save(flush: true)) {
			addRoles(userInstance)
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'user.label', default: 'User'), userInstance.id])}"
            redirect(action: "show", id: userInstance.id)
        }
        else {
			
            render(view: "create", model: [userInstance: userInstance,authorityList:roles])
        }
    }

    def show = {
		log.info "INGRESANDO AL CLOSURE show DEL CONTROLLER UserController"
		log.info "PARAMETROS: ${params}"
        def userInstance = User.get(params.id)
        if (!userInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), params.id])}"
            redirect(action: "list")
        }
        else {
			log.debug "USUARIO ENCONTRADO ${userInstance} "
            [userInstance: userInstance]
        }
    }

    def edit = {
		log.info "INGRESANDO AL CLOSURE edit DEL CONTROLLER UserController"
		log.info "PARAMETROS: ${params}"
        def userInstance = User.get(params.id)
		def roles = Role.list()
        if (!userInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), params.id])}"
            redirect(action: "list")
        }
        else {
            //return [userInstance: userInstance,authorityList:roles]
			return buildUserModel(userInstance)
        }
    }

    def update = {
		log.info "INGRESANDO AL CLOSURE update DEL CONTROLLER UserController"
		log.info "PARAMETROS: ${params}"
        def userInstance = User.get(params.id)
        if (userInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (userInstance.version > version) {
                    
                    userInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'user.label', default: 'User')] as Object[], "Another user has updated this User while you were editing")
                    render(view: "edit", model: [userInstance: userInstance])
                    return
                }
            }
            userInstance.properties = params
			userInstance.passwd = authenticateService.encodePassword(params.passwd)
            if (!userInstance.hasErrors() && userInstance.save(flush: true)) {
				Role.findAll().each{
					it.removeFromPeople(userInstance)
				}
			    addRoles(userInstance)
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'user.label', default: 'User'), userInstance.id])}"
                redirect(action: "show", id: userInstance.id)
            }
            else {
                render(view: "edit", model: [userInstance: userInstance])
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
                redirect(action: "show", id: params.id)
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


			result=result+'{"id":"'+it.id+'","cell":["'+it.id+'","'+(it.username==null?"":it.username)+'","'+(it.userRealName==null?"":it.userRealName)+'","'+(it.enabled==null?"":it.enabled)+'","'+(it.esProfesional==null?"":it.esProfesional)+'","'+(it.email==null?"":it.email)+'","'+(it.profesionalAsignado?.nombre==null?"":it.profesionalAsignado?.nombre)+'"]}'
			 
			flagaddcomilla=true
		}
		result=result+']}'
		render result

	}
	
	private void addRoles(user) {
		log.info "INGRESANDO AL METODO PRIVADO addRoles"
		for (String key in params.keySet()) {
			if (key.contains('ROLE') && 'on' == params.get(key)) {
				Role.findByAuthority(key).addToPeople(user)
			}
		}
	}
	private Map buildUserModel(user) {
				log.info "INGRESANDO AL METODO PRIVADO buildUser DEL CONTROLLER UserController"
				List roles = Role.list()
				roles.sort { r1, r2 ->
					r1.authority <=> r2.authority
				}
				Set userRoleNames = []
				for (role in user.authorities) {
					userRoleNames << role.authority
				}
				LinkedHashMap<Role, Boolean> roleMap = [:]
				for (role in roles) {
					roleMap[(role)] = userRoleNames.contains(role.authority)
				}
		
				return [userInstance: user, authorityList: roleMap]
			}

}