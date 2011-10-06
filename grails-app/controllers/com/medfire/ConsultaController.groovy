package com.medfire

import java.text.DateFormat
import java.text.ParsePosition;
import java.text.SimpleDateFormat
import java.text.ParseException
import com.medfire.util.GUtilDomainClass
import grails.converters.JSON

class ConsultaController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [consultaInstanceList: Consulta.list(params), consultaInstanceTotal: Consulta.count()]
    }
 
    def create = {
		log.info "INGREANDO AL CLOSURE create DEL CONTROLLER ConsultaController"
		log.info "PARAMETROS $params"
		def paciente = Paciente.load(params.paciente.id.toLong())
        def consultaInstance = new Consulta()
        consultaInstance.properties = params
        return [consultaInstance: consultaInstance,pacienteId:paciente.id,pacienteNombre:paciente.apellido+"-"+paciente.nombre]
    }

    def save = {
		log.info "INGRESANDO AL CLOSURE save DEL CONTROLLER ConsultaController"
		log.info "PARAMETROS $params"
		
		if (params.fechaConsulta){
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy")
			def fecha
			try{
				fecha = df.parse(params.fechaConsulta)
				log.debug "LA FECHA SE PARSEO BIEN"
			}catch(ParseException e){
				log.debug "LA FECHA NO SE PARSEO BIEN" 
			}
			def gc = Calendar.getInstance()
			gc.setTime(fecha)
			log.debug "ANIO: "+gc.get(Calendar.YEAR).toString()+", MES "+gc.get(Calendar.MONTH+1).toString()+" DIA "+gc.get(Calendar.DATE).toString()
			params.fechaConsulta_year=gc.get(Calendar.YEAR).toString()
			params.fechaConsulta_month=(gc.get(Calendar.MONTH)+1).toString()
			params.fechaConsulta_day=gc.get(Calendar.DATE).toString()
		}

		
        def consultaInstance = new Consulta(params)
		consultaInstance.fechaAlta= new java.sql.Date((new Date()).getTime())
        if (consultaInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'consulta.label', default: 'Consulta'), consultaInstance.id])}"
            redirect(action: "show", id: consultaInstance.id)
        }
        else {
            render(view: "create", model: [consultaInstance: consultaInstance])
        }
    }

    def show = {
        def consultaInstance = Consulta.get(params.id)
        if (!consultaInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'consulta.label', default: 'Consulta'), params.id])}"
            redirect(action: "list")
        }
        else {
            [consultaInstance: consultaInstance]
        }
    }

    def edit = {
        def consultaInstance = Consulta.get(params.id)
        if (!consultaInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'consulta.label', default: 'Consulta'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [consultaInstance: consultaInstance]
        }
    }

    def update = {
        def consultaInstance = Consulta.get(params.id)
        if (consultaInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (consultaInstance.version > version) {
                    
                    consultaInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'consulta.label', default: 'Consulta')] as Object[], "Another user has updated this Consulta while you were editing")
                    render(view: "edit", model: [consultaInstance: consultaInstance])
                    return
                }
            }
            consultaInstance.properties = params
            if (!consultaInstance.hasErrors() && consultaInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'consulta.label', default: 'Consulta'), consultaInstance.id])}"
                redirect(action: "show", id: consultaInstance.id)
            }
            else {
                render(view: "edit", model: [consultaInstance: consultaInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'consulta.label', default: 'Consulta'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def consultaInstance = Consulta.get(params.id)
        if (consultaInstance) {
            try {
                consultaInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'consulta.label', default: 'Consulta'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'consulta.label', default: 'Consulta'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'consulta.label', default: 'Consulta'), params.id])}"
            redirect(action: "list")
        }
    }
	
	def listjson = {
		log.info "INGRESANDO AL CLOSURE listjson"
		params.filters = """{'groupOp':'AND','rules':[{'field':'paciente_id','op':'eq','data':'${params.pacienteId}'}]}"""
		params._search = "true"
		log.info "PARAMETROS $params"
		

		def result

		def flagcomilla=false
		def totalregistros
		def totalpaginas
		def gud = new GUtilDomainClass(Consulta,params,grailsApplication)
		def list=gud.listrefactor(false)
		totalregistros=gud.listrefactor(true)


		if(list){
			totalpaginas = new Float(totalregistros/Integer.parseInt(params.rows))
			if (totalpaginas>0 && totalpaginas<1)
				totalpaginas=1
			totalpaginas = totalpaginas.intValue()
			result='{"page":'+params.page+',"total":"'+totalpaginas+'","records":"'+totalregistros+'","rows":['
			list.each{
				if(flagcomilla)
					result=result+','
				result=result+'{"id":"'+it.id+'","cell":["'+it.id+'","'+(it.fechaConsulta!=null?it.fechaConsulta:"")+'","'+(it.cie10?.cie10!=null?it.cie10?.cie10:"")+'","'+(it.cie10?.descripcion!=null?it.cie10?.descripcion:"")+'","'+(it.profesional?.nombre!=null?it.profesional?.nombre:"")+'","'+(it.estado.name!=null?it.estado.name:"")+'"]}'	
				flagcomilla=true
			}
			result=result+']}'
			render result
		}else{
			result='{"page":0,"total":"0","records":"0","rows":['
			result=result+']}'
			render result
		}
	}
	
	def pacientesatendidos = { 
		log.info "INGRESANDO AL CLOSURE pacientesatendidos"
		log.info "PARAMETROS: $params"
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		return [cmdInstance:new ConsultaCommand(fechaDesde:sdf.format(Calendar.getInstance().getTime()),fechaHasta:sdf.format(Calendar.getInstance().getTime()))]
	}
	
	private def porprofesionalesgraph(def params){
		log.info "INGRESANDO AL METODO PRIVADO porprofesionalesgraph"
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy")
		list = Consulta.createCriteria().list(){
			if(params.fechaDesde){
				java.util.Date fechaDesde = df.parse(params.fechaDesde, new ParsePosition(0))
				ge("fechaConsulta",new java.sql.Date(fechaDesde.getTime()))
			}
			if(params.fechaHasta){
				java.util.Date fechaHasta = df.parse(params.fechaHasta, new ParsePosition(0))
				le("fechaConsulta",new java.sql.Date(fechaHasta.getTime()))
			}
	
			if(params.profesionalId){
				profesional{
					eq("id",params.profesionalId.toLong())
				}
			}
			if(params.obraSocialId){
				paciente{
					obraSocial{
						eq("id",params.obraSocialId.toLong())
					}
				}
			}

			if(params.cie10Id){
				cie10{
					eq("id",params.cie10Id.toLong())
				}
			}
			projections {
				count ("paciente.id")
				groupProperty 'profesional'
			}
		}

		return list
	}
	
	private def porobrasocialgraph(def params){
		log.info "INGRESANDO AL METODO PRIVADO porobrasocialgraph"
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy")
		list = Consulta.createCriteria().list(){
			createAlias("paciente","p")
			
			if(params.fechaDesde){
				java.util.Date fechaDesde = df.parse(params.fechaDesde, new ParsePosition(0))
				ge("fechaConsulta",new java.sql.Date(fechaDesde.getTime()))
			}
			if(params.fechaHasta){
				java.util.Date fechaHasta = df.parse(params.fechaHasta, new ParsePosition(0))
				le("fechaConsulta",new java.sql.Date(fechaHasta.getTime()))
			}
	
			if(params.profesionalId){
				profesional{
					eq("id",params.profesionalId.toLong())
				}
			}
			if(params.obraSocialId){
				/*paciente{
					obraSocial{
						eq("id",params.obraSocialId.toLong())
					}
				}*/
				eq("p.obraSocial.id",params.obraSocialId.toLong())
			}

			if(params.cie10Id){
				cie10{
					eq("id",params.cie10Id.toLong())
				}
			}
			projections {
				count ("paciente.id")
				groupProperty 'p.obraSocial'
			}
		}

		return list

	}
	
	private def pordiagnosticogrph(def params){
		log.info "INGRESANDO AL METODO PRIVADO pordiagnosticogrph"
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy")
		list = Consulta.createCriteria().list(){
			createAlias("paciente","p")
			
			if(params.fechaDesde){
				java.util.Date fechaDesde = df.parse(params.fechaDesde, new ParsePosition(0))
				ge("fechaConsulta",new java.sql.Date(fechaDesde.getTime()))
			}
			if(params.fechaHasta){
				java.util.Date fechaHasta = df.parse(params.fechaHasta, new ParsePosition(0))
				le("fechaConsulta",new java.sql.Date(fechaHasta.getTime()))
			}
	
			if(params.profesionalId){
				profesional{
					eq("id",params.profesionalId.toLong())
				}
			}
			if(params.obraSocialId){
						eq("p.obraSocial.id",params.obraSocialId.toLong())
			}

			if(params.cie10Id){
				cie10{
					eq("id",params.cie10Id.toLong())
				}
			}
			projections {
				count ("paciente.id")
				groupProperty "cie10"
			}
		}

		return list

	}
	
	def pacientesatendidosbuscar = { ConsultaCommand cmd ->
		log.info "INGRESANDO AL CLOSURE pacientesatendidosbuscar"
		log.info "PARAMETROS: $params"
		log.debug "COMMAND OBJECT: $cmd.properties"
		if(cmd.validate()){
			def profGraph = porprofesionalesgraph(params)
			def osGraph = porobrasocialgraph(params)
			def cie10Graph= pordiagnosticogrph(params)
			render(view:"pacientesatendidos", model:[cmdInstance:cmd, buscar:true,profGraph:profGraph,osGraph:osGraph,cie10Graph:cie10Graph])
			
		}else{
			log.debug "ERRORES: "+cmd.errors.allErrors
			render(view:"pacientesatendidos", model:[cmdInstance:cmd, buscar:false])
		}
	}
	
	def pacientesatendidosjson = {ConsultaCommand cmd->
		log.info "INGRESANDO AL CLOSURE pacientesatendidosjson"
		log.info "PARAMETROS: $params"
		
		def result
		
		def flagcomilla=false
		def totalregistros
		def totalpaginas
		def list
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy")
		if(cmd.validate()){
			list=Consulta.createCriteria().list(){
				if(params.fechaDesde){
					java.util.Date fechaDesde = df.parse(params.fechaDesde, new ParsePosition(0))
					ge("fechaConsulta",new java.sql.Date(fechaDesde.getTime()))
				}
				if(params.fechaHasta){
					java.util.Date fechaHasta = df.parse(params.fechaHasta, new ParsePosition(0))
					le("fechaConsulta",new java.sql.Date(fechaHasta.getTime()))
				}
				if(params.profesionalId){
					profesional{
						eq("id",params.profesionalId.toLong())
					}
				}
				if(params.obraSocialId){
					paciente{
						obraSocial{
							eq("id",params.obraSocialId.toLong())
						}
					}
				}

				if(params.cie10Id){
					cie10{
						eq("id",params.cie10Id.toLong())
					}
				}
				firstResult((params.page.toInteger()-1)*params.rows.toInteger())
				maxResults(params.rows.toInteger())

				
			}
			totalregistros=Consulta.createCriteria().get(){
				if(params.fechaDesde){
					java.util.Date fechaDesde = df.parse(params.fechaDesde, new ParsePosition(0))
					ge("fechaConsulta",new java.sql.Date(fechaDesde.getTime()))
				}
				if(params.fechaHasta){
					java.util.Date fechaHasta = df.parse(params.fechaHasta, new ParsePosition(0))
					le("fechaConsulta",new java.sql.Date(fechaHasta.getTime()))
				}
				if(params.profesionalId){
					profesional{
						eq("id",params.profesionalId.toLong())
					}
				}
				if(params.obraSocialId){
					paciente{
						obraSocial{
							eq("id",params.obraSocialId.toLong())
						}
					}
				}
				if(params.cie10Id){
					cie10{
						eq("id",params.cie10Id.toLong())
					}
				}


				projections{
					rowCount()
				}

			}
			
			if(list){
				totalpaginas = new Float(totalregistros/Integer.parseInt(params.rows))
				if (totalpaginas>0 && totalpaginas<1)
					totalpaginas=1
				totalpaginas = totalpaginas.intValue()
				result='{"page":'+params.page+',"total":"'+totalpaginas+'","records":"'+totalregistros+'","rows":['
				list.each{
					if(flagcomilla)
						result=result+','
					result=result+'{"id":"'+it.id+'","cell":["'+it.id+'","'+(it.fechaConsulta!=null?it.fechaConsulta:"")+'","'+it.paciente.apellido+'-'+it.paciente.nombre+'","'+(it.cie10?.cie10!=null?it.cie10?.cie10:"")+'","'+(it.cie10?.descripcion!=null?it.cie10?.descripcion:"")+'","'+(it.profesional?.nombre!=null?it.profesional?.nombre:"")+'","'+(it.paciente.obraSocial?.descripcion!=null?it.paciente.obraSocial?.descripcion:"")+'"]}'
					flagcomilla=true
				}
				result=result+']}'
				render result
			}else{
				result='{"page":0,"total":"0","records":"0","rows":['
				result=result+']}'
				render result
			}
		}else{
				result='{"page":0,"total":"0","records":"0","rows":['
				result=result+']}'
				render result
		}
		

		
	}
	
	def porprofesionaljson={ConsultaCommand cmd->
		log.info "INGRESANDO AL CLOSURE porprofesionaljson"
		log.info "PARAMETROS: $params"
		def result
		
		def flagcomilla=false
		def list
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy")
		
		if(cmd.validate()){
				list = Consulta.createCriteria().list(){
					if(params.profesionalId){
						profesional{
							eq("id",params.profesionalId.toLong())
						}
					}
					if(params.obraSocialId){
						paciente{
							obraSocial{
								eq("id",params.obraSocialId.toLong())
							}
						}
					}
		
					if(params.cie10Id){
						cie10{
							eq("id",params.cie10Id.toLong())
						}
					}
					createAlias("evento","e")
					projections {
						count ("paciente.id")
						sum	("e.tiempoAtencion") 
						groupProperty 'profesional'
					}
				}
				
				if(list){
					result='{"page":'+params.page+',"total":"'+1+'","records":"'+list.size()+'","rows":['
					list.each{
						if(flagcomilla)
							result=result+','
						result=result+'{"id":"'+it[2].id+'","cell":["'+it[2].id+'","'+(it[2].matricula!=null?it[2].matricula:"")+'","'+it[2].nombre+'","'+it[0]+'","'+it[1]+'"]}'
						flagcomilla=true
					}
					result=result+']}'
					render result
				}else{
					result='{"page":0,"total":"0","records":"0","rows":['
					result=result+']}'
					render result
				}
		}else{
				result='{"page":0,"total":"0","records":"0","rows":['
				result=result+']}'
				render result

		}
	}
	
	
	def pordiagnosticojson = {ConsultaCommand cmd->
		log.info "INGRESANDO AL CLOSURE pordiagnostico"
		log.info "PARAMETROS: $params"
		def result
		
		def flagcomilla=false
		def list
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy")
		
		if(cmd.validate()){
				list = Consulta.createCriteria().list(){
					if(params.profesionalId){
						profesional{
							eq("id",params.profesionalId.toLong())
						}
					}
					if(params.obraSocialId){
						paciente{
							obraSocial{
								eq("id",params.obraSocialId.toLong())
							}
						}
					}
		
					if(params.cie10Id){
						cie10{
							eq("id",params.cie10Id.toLong())
						}
					}
					createAlias("evento","e")
					projections {
						count ("cie10.id")
						groupProperty 'cie10'
					}
				}
				
				if(list){
					result='{"page":'+params.page+',"total":"'+1+'","records":"'+list.size()+'","rows":['
					list.each{
						log.debug "ESTRUCTURA IT: "+it
						
						if(flagcomilla)
							result=result+','
						result=result+'{"id":"'+it[1]?.id+'","cell":["'+it[1]?.id+'","'+(it[1]?.cie10!=null?it[1]?.cie10:"")+'","'+(it[1]?.descripcion!=null?it[1]?.descripcion:"")+'","'+it[0]+'"]}'
						flagcomilla=true
					}
					result=result+']}'
					render result
				}else{
					result='{"page":0,"total":"0","records":"0","rows":['
					result=result+']}'
					render result
				}
		}else{
				result='{"page":0,"total":"0","records":"0","rows":['
				result=result+']}'
				render result

		}

	}
	
	
	

}


class ConsultaCommand{
	String fechaDesde
	String fechaHasta
	String profesional
	String profesionalId
	String obraSocial
	String obraSocialId
	String cie10
	String cie10Id
	static constraints = {
		fechaDesde(blank:false,nullable:false,validator:{v,cmd ->
			def df = new SimpleDateFormat('dd/MM/yyyy')
			df.lenient = false

			return df.parse(v, new ParsePosition(0)) ? true : false
		})
		fechaHasta(blank:false,nullable:false,validator:{v,cmd ->
			def df = new SimpleDateFormat('dd/MM/yyyy')
			df.lenient = false

			return df.parse(v, new ParsePosition(0)) ? true : false
		})
		
		profesionalId(blank:true, validator:{v,cmd->
			if(v){
				try{
					def profesionalInstance = Profesional.load(v.toLong())
					if(profesionalInstance){
						cmd.profesional = profesionalInstance.nombre
						return true
					}else
						return false
				}catch(Exception e){
					return false
				}
			}
		})

	}
	
}

