package com.medfire
//http://fokot.blogspot.com/2011/06/having-clause-in-hibernate-criteria-no.html
import java.text.DateFormat

import java.text.ParsePosition;
import java.text.SimpleDateFormat
import java.text.ParseException
import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections
import org.hibernate.criterion.Subqueries

import com.medfire.util.GUtilDomainClass
import grails.converters.JSON
import com.medfire.util.ChartGenerator
import pl.burningice.plugins.image.container.ContainerUtils
import org.codehaus.groovy.grails.commons.ApplicationHolder as AH
import org.hibernate.SessionFactory
import org.hibernate.Session

class ConsultaController {
	def authenticateService

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
	
	def consultaspropias = {
		log.info "INGRESANDO AL CLOSURE consultaspropias"
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		return [cmdInstance:new ConsultaCommand(fechaDesde:sdf.format(Calendar.getInstance().getTime()),fechaHasta:sdf.format(Calendar.getInstance().getTime()))]
	}
	
	def listjsonconsultaspropias = {
		log.info "INGRESANDO AL CLOSURE listjsonconsultaspropias"
		log.info "PARAMETROS: $params"
		
		def result
		
		def flagcomilla=false
		def totalregistros
		def totalpaginas
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy")
		def usuario = authenticateService.userDomain()
		def list=Consulta.createCriteria().list(){
			if(params.fechaDesde){
				java.util.Date fechaDesde = df.parse(params.fechaDesde, new ParsePosition(0))
				ge("fechaConsulta",new java.sql.Date(fechaDesde.getTime()))
			}
			if(params.fechaHasta){
				java.util.Date fechaHasta = df.parse(params.fechaHasta, new ParsePosition(0))
				le("fechaConsulta",new java.sql.Date(fechaHasta.getTime()))
			}
			if(params.pacienteId){
				paciente{
					eq("id",params.pacienteId.toLong())
				}
			}
			
			profesional{
				eq("id",usuario.profesionalAsignado?.id)
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
			if(params.pacienteId){
				paciente{
					eq("id",params.pacienteId.toLong())
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
				result=result+'{"id":"'+it.id+'","cell":["'+it.id+'","'+(it.fechaConsulta!=null?it.fechaConsulta:"")+'","'+it.paciente.apellido+'","'+it.paciente.nombre+'","'+(it.cie10?.cie10!=null?it.cie10?.cie10:"")+'","'+(it.cie10?.descripcion!=null?it.cie10?.descripcion:"")+'","'+(it.estado.name!=null?it.estado.name:"")+'"]}'
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
	
	def consultaspropiasbuscar = {ConsultaCommand cmd->
		log.info "INGRESANDO AL CLOSURE consultaspropiasbuscar"
		log.info "PARAMETROS $params"
		if(cmd.validate()){
			render(view:"consultaspropias", model:[cmdInstance:cmd])
			
		}else{
			render(view:"consultaspropias", model:[cmdInstance:cmd])
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
				groupProperty 'profesional'
			}
		}
		log.debug "CANTIDAD DE REGISTROS: "+list.size()

		ChartGenerator chart = new ChartGenerator()
		List keys = new ArrayList()
		List values = new ArrayList()
		list.each{
			if(it[1]){
				keys.add(it[1]?.nombre)
				values.add(it[0])
			}
		}
		String pathimg = servletContext.getRealPath("/images/piechartprof.png")
		if(keys.size()>0 && values.size()>0)
			chart.drawPieChart(keys, values, new Integer(200), new Integer(200),pathimg)
		
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
		ChartGenerator chart = new ChartGenerator()
		List keys = new ArrayList()
		List values = new ArrayList()
		list.each{
			if(it[1]){
				keys.add(it[1]?.descripcion)
				values.add(it[0])
			}else{
				keys.add("SIN OBRA SOCIAL")
				values.add(it[0])
			}
		}
		String pathimg = servletContext.getRealPath("/images/piechartos.png")
		if(keys.size()>0 && values.size()>0)
			chart.drawPieChart(keys, values, new Integer(200), new Integer(200),pathimg)

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
		ChartGenerator chart = new ChartGenerator()
		List keys = new ArrayList()
		List values = new ArrayList()
		list.each{
			if(it[1]){
				keys.add(it[1]?.descripcion)
				values.add(it[0])
			}else{
				keys.add("SIN DIAGNOSTICO")
				values.add(it[0])
			}
		}
		String pathimg = servletContext.getRealPath("/images/piechartdiag.png")
		if(keys.size()>0 && values.size()>0)
			chart.drawPieChart(keys, values, new Integer(200), new Integer(200),pathimg)


		return list

	}
	
	
	
	def pacientesatendidosbuscar = { ConsultaCommand cmd ->
		log.info "INGRESANDO AL CLOSURE pacientesatendidosbuscar"
		log.info "PARAMETROS: $params" 
		log.debug "COMMAND OBJECT: $cmd.properties"
		/*response.AppendHeader("pragma", "no-store,no-cache")  //HTTP 1.0
		response.AppendHeader("cache-control", "no-cache, no-store,must-revalidate, max-age=-1") // HTTP 1.1
		response.AppendHeader("expires", "-1")*/
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
			log.debug "TOTAL DE REGISTROS: "+totalregistros
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
					//createAlias("evento","e")
					projections {
						count ("paciente.id")
						groupProperty 'cie10'
					}
				}
				
				if(list){
					result='{"page":'+params.page+',"total":"'+1+'","records":"'+list.size()+'","rows":['
					list.each{
						log.debug "ESTRUCTURA IT: "+it
						
						if(flagcomilla)
							result=result+','
						result=result+'{"id":"'+it[1]?.id+'","cell":["'+it[1]?.id+'","'+(it[1]?.cie10!=null?it[1]?.cie10:"SIN CODIGO")+'","'+(it[1]?.descripcion!=null?it[1]?.descripcion:"SIN DIAGNOSTICO")+'","'+it[0]+'"]}'
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
	
	def reportegral = {
		log.info "INGRESANDO AL CLOSURE reportegral"
		log.info "PARAMETROS: $params"
		//bi.resource(size:'large',bean:it)
		def list = Institucion.findAll()
		def institucionInstance = list.getAt(0)
		String pathimage
		String nameimage
		def config
		if(institucionInstance){ 
			pathimage = bi.resource(size:'large',bean:institucionInstance)
			if(pathimage.contains(".null")){
				pathimage = servletContext.getRealPath("/images")
				nameimage = "noDisponibleLarge.jpg"
			}else{
				config = ContainerUtils.getConfig(institucionInstance)
				pathimage = servletContext.getRealPath("/institucional")
				nameimage = ContainerUtils.getFullName("large", institucionInstance, config)
			}
		
		}
		params.put("pathimage", pathimage);
		params.put("nameimage", nameimage)
		params.put("nombreInstitucion", institucionInstance.nombre);
		params.put("telefonos", institucionInstance.telefonos);
		params.put("email", institucionInstance.email);
		params.put("direccion", institucionInstance.direccion);
		params.put("_format","PDF")
		params.put("_name","pacientesgral")
		params.put("_file","pacientesatendidosgral")
		
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy")
		def listPacientes=Consulta.createCriteria().list(){
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
			} 
		
		
		chain(controller:'jasper',action:'index',model:[data:listPacientes],params:params)
	}	
	
	
	def reporteporprof = {
		log.info "INGRESANDO AL CLOSURE reporteporprof"
		log.info "PARAMETROS: $params"
		
		def list = Institucion.findAll()
		def institucionInstance = list.getAt(0)
		String pathimage
		String nameimage
		def config
		if(institucionInstance){
			pathimage = bi.resource(size:'large',bean:institucionInstance)
			if(pathimage.contains(".null")){
				pathimage = servletContext.getRealPath("/images")
				nameimage = "noDisponibleLarge.jpg"
			}else{
				config = ContainerUtils.getConfig(institucionInstance)
				pathimage = servletContext.getRealPath("/institucional")
				nameimage = ContainerUtils.getFullName("large", institucionInstance, config)
			}
		
		}
		params.put("pathimage", pathimage);
		params.put("nameimage", nameimage)
		params.put("nombreInstitucion", institucionInstance.nombre);
		params.put("telefonos", institucionInstance.telefonos);
		params.put("email", institucionInstance.email);
		params.put("direccion", institucionInstance.direccion);
		params.put("_format","PDF")
		params.put("_name","profesionales")
		params.put("_file","pacientesatendidosporprof")
		
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy")
		def listporprof = Consulta.createCriteria().list(){
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
			createAlias("evento","e")
			projections {
				count ("paciente.id","cantpacientes")
				sum	("e.tiempoAtencion","totalminutos")
				groupProperty 'profesional'
			}
		}
		chain(controller:'jasper',action:'index',model:[data:listporprof],params:params)
	}

	def  reportepordiag = {
		log.info "INGRESANDO AL CLOSURE reportepordiag"
		log.info "PARAMETROS: $params"
		def list = Institucion.findAll()
		def institucionInstance = list.getAt(0)
		String pathimage
		String nameimage
		def config
		if(institucionInstance){
			pathimage = bi.resource(size:'large',bean:institucionInstance)
			if(pathimage.contains(".null")){
				pathimage = servletContext.getRealPath("/images")
				nameimage = "noDisponibleLarge.jpg"
			}else{
				config = ContainerUtils.getConfig(institucionInstance)
				pathimage = servletContext.getRealPath("/institucional")
				nameimage = ContainerUtils.getFullName("large", institucionInstance, config)
			}
		
		}
		params.put("pathimage", pathimage);
		params.put("nameimage", nameimage)
		params.put("nombreInstitucion", institucionInstance.nombre);
		params.put("telefonos", institucionInstance.telefonos);
		params.put("email", institucionInstance.email);
		params.put("direccion", institucionInstance.direccion);
		params.put("_format","PDF")
		params.put("_name","diagnostico")
		params.put("_file","pacientesatendidospordiag")
		
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy")
		def listdiagnostico = Consulta.createCriteria().list(){
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
				groupProperty 'cie10'
			}
		}
		chain(controller:'jasper',action:'index',model:[data:listdiagnostico],params:params)
	}
	
	def reportepordiagdetalle = {
		log.info "INGRESANDO AL CLOSURE reportepordiagdetalle"
		log.info "PARAMETROS: $params"
		log.info "INGRESANDO AL CLOSURE reportepordiag"
		log.info "PARAMETROS: $params"
		def list = Institucion.findAll()
		def institucionInstance = list.getAt(0)
		String pathimage
		String nameimage
		def config
		if(institucionInstance){
			pathimage = bi.resource(size:'large',bean:institucionInstance)
			if(pathimage.contains(".null")){
				pathimage = servletContext.getRealPath("/images")
				nameimage = "noDisponibleLarge.jpg"
			}else{
				config = ContainerUtils.getConfig(institucionInstance)
				pathimage = servletContext.getRealPath("/institucional")
				nameimage = ContainerUtils.getFullName("large", institucionInstance, config)
			}
		
		}
		params.put("pathimage", pathimage);
		params.put("nameimage", nameimage)
		params.put("nombreInstitucion", institucionInstance.nombre);
		params.put("telefonos", institucionInstance.telefonos);
		params.put("email", institucionInstance.email);
		params.put("direccion", institucionInstance.direccion);
		params.put("_format","PDF")
		params.put("_name","diagnostico")
		params.put("_file","pacientesatendidospordiagdetalle")
		
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy")
		def listdiagnostico = Consulta.createCriteria().list(){
			if(params.fechaDesde){
				java.util.Date fechaDesde = df.parse(params.fechaDesde, new ParsePosition(0))
				ge("fechaConsulta",new java.sql.Date(fechaDesde.getTime()))
			}
			if(params.fechaHasta){
				java.util.Date fechaHasta = df.parse(params.fechaHasta, new ParsePosition(0))
				le("fechaConsulta",new java.sql.Date(fechaHasta.getTime()))
			}

			if(params.cie10Id){
				cie10{
					eq("id",params.cie10Id.toLong())
				}
			}
		}
		chain(controller:'jasper',action:'index',model:[data:listdiagnostico],params:params)

	}
	
	//-----------------------------------------------
	
	def pacientesatendidosporos = {
		log.info "INGRESANDO AL CLOSURE pacientesatendidosporos"
		log.info "PARAMETROS: $params"
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		return [cmdInstance:new ConsultaCommand(fechaDesde:sdf.format(Calendar.getInstance().getTime()),fechaHasta:sdf.format(Calendar.getInstance().getTime()))]
		
	}
	
	def pacientesatendidosporosbuscar = {ConsultaCommand cmd->
		log.info "INGRESANDO AL CLOSURE pacientesatendidosporosbucar"
		log.info "PARAMETROS $params"
		log.info "PROPIEDADES COMMAND: "+cmd.properties
		def result
		
		def flagcomilla=false
		def totalregistros
		def totalpaginas
		def list
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy")
		def osGraph
		if(cmd.validate()){
			osGraph = porobrasocialgraph(params)
			render(view:"pacientesatendidosporos", model:[cmdInstance:cmd, buscar:true,osGraph:osGraph])
			
		}else{
			log.debug "ERRORES: "+cmd.errors.allErrors
			render(view:"pacientesatendidosporos", model:[cmdInstance:cmd, buscar:false])
		}
	}
	
	def pacientesatendidosporosjson = {ConsultaCommand cmd->
		log.info "INGRESANDO AL CLOSURE pacientesatendidosporosjson"
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
				if(params.obraSocialId){
					paciente{
						obraSocial{
							eq("id",params.obraSocialId.toLong())
						}
					}
				}
				paciente{
					order("apellido","desc")
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
				if(params.obraSocialId){
					paciente{
						obraSocial{
							eq("id",params.obraSocialId.toLong())
						}
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
					result=result+'{"id":"'+it.id+'","cell":["'+it.id+'","'+it.paciente.apellido+'-'+it.paciente.nombre+'","'+'","'+(it.paciente.obraSocial?.descripcion!=null?it.paciente.obraSocial?.descripcion:"")+(it.cie10?.cie10!=null?it.cie10?.cie10:"")+'","'+(it.cie10?.descripcion!=null?it.cie10?.descripcion:"")+'"]}'
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
	//-------------------------------------------------
	def pacientesatendidosporprimeravez = {
		log.info "INGRESANDO AL CLOSURE pacientesatendidosporprimeravez"
		log.info "PARAMETROS: $params"
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		return [cmdInstance:new ConsultaCommand(fechaDesde:sdf.format(Calendar.getInstance().getTime()),fechaHasta:sdf.format(Calendar.getInstance().getTime()))]

	}
	
	def pacientesatendidosporprimeravezbuscar = {ConsultaCommand cmd->
		log.info "INGRESANDO AL CLOSURE: pacientesatendidosporprimeravezbuscar"
		log.info "PARAMETROS: $params"
		log.info "PROPIEDADES COMMAND: "+cmd.properties
		def result
		
		def flagcomilla=false
		def totalregistros
		def totalpaginas
		def list
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy")
		if(cmd.validate()){
			render(view:"pacientesatendidosporprimeravez", model:[cmdInstance:cmd, buscar:true])
			
		}else{
			log.debug "ERRORES: "+cmd.errors.allErrors
			render(view:"pacientesatendidosporprimeravez", model:[cmdInstance:cmd, buscar:false])
		}

	}
	
	def pacientesatendidosporprimeravezjson = {ConsultaCommand cmd ->
		log.info "INGRESANDO AL CLOSURE pacientesatendidosporprimeravezjson"
		log.info "PARAMETROS: $params"
		def result
		
		//def ctx = AH.application.mainContext
		//def sessionFactory = ctx.sessionFactory
		//def sessionH = sessionFactory.currentSession
				
		def flagcomilla=false
		def totalregistros
		def totalpaginas
		def list
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy")
		java.util.Date fechaDesde = df.parse(params.fechaDesde, new ParsePosition(0))
		java.util.Date fechaHasta = df.parse(params.fechaHasta, new ParsePosition(0))
		def detachedCriteria = DetachedCriteria.forClass(Consulta.class,"inner")
		detachedCriteria.setProjection(Projections.rowCount())
		Criterion c1 = Restrictions.and(Restrictions.and(Restrictions.ge("fechaConsulta",fechaDesde),Restrictions.le("fechaConsulta", fechaHasta)),Restrictions.eqProperty("inner.paciente.id","p.id"))
		
		detachedCriteria.add(c1)
		//detachedCriteria.setProjection(Projections.groupProperty("paciente.id"))
		detachedCriteria.setProjection(Projections.min("fechaConsulta"))
		if(cmd.validate()){
			/*def criteria = sessionH.createCriteria(Consulta.class,"outer")
			criteria.add(Restrictions.and(
									Restrictions.ge("fechaConsulta",fechaDesde),Restrictions.le("fechaConsulta", fechaHasta)
									)
						)
			//criteria.setProjection()
			criteria.setProjection(Projections.property("paciente"))	
			criteria.add(Subqueries.le(fechaDesde,detachedCriteria))	
			list = criteria.list()  
			log.debug "CANTIDAD DE OBJETOS DEVUELTOS: "+list?.size()
			list?.each{
				log.debug "ESTRUCTURA DE OBJETO DEVUELTO: "+it.properties
				log.debug "OBJETO DEVUELTO: "+it
			}	*/
			list = Consulta.createCriteria().list(){
				and{
					if(params.fechaDesde)
						ge("fechaConsulta",fechaDesde)
					if(params.fechaHasta)	
						le("fechaConsulta",fechaHasta)
					if(params.obraSocialId){
						paciente{
							obraSocial{
								eq("id",params.obraSocialId.toLong())
							}
						}
					}	
						
				}
				createAlias("paciente","p")
				ge("fechaConsulta",fechaDesde)
				le("fechaConsulta",fechaHasta)
				projections{
					count("p.id")
					groupProperty("paciente")
					instance.add(Subqueries.le(fechaDesde,detachedCriteria))
				}
				firstResult((params.page.toInteger()-1)*params.rows.toInteger())
				maxResults(params.rows.toInteger())
				order("p.apellido","desc")
			}
				
			totalregistros=Consulta.createCriteria().get(){
				createAlias("paciente","p")
				if(params.fechaDesde){
					ge("fechaConsulta",new java.sql.Date(fechaDesde.getTime()))
				}
				if(params.fechaHasta){
					le("fechaConsulta",new java.sql.Date(fechaHasta.getTime()))
				}
				if(params.obraSocialId){
							eq("p.obraSocial.id",params.obraSocialId.toLong())
				}
				projections{
					countDistinct("p.id")
					instance.add(Subqueries.le(fechaDesde,detachedCriteria))
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
					result=result+'{"id":"'+it[1].id+'","cell":["'+it[1].id+'","'+it[1].apellido+'-'+it[1].nombre+'","'+(it[1].obraSocial?.descripcion!=null?it[1].obraSocial?.descripcion:"")+'"]}'
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
	
	//------------------------------------------------------
	
	def cantidadvisitasporpaciente = {
		log.info "INGRESANDO AL CLOSURE: cantidadvisitasporpaciente"
		log.info "PARAMETROS: $params"
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		return [cmdInstance:new ConsultaCommand(fechaDesde:sdf.format(Calendar.getInstance().getTime()),fechaHasta:sdf.format(Calendar.getInstance().getTime()))]
		
	}
	
	
	def cantidadvisitasporpacientebuscar = {ConsultaCommand cmd->
		log.info "INGRESANDO AL CLOSURE: cantidadvisitasporpacientebuscar"
		log.info "PARAMETROS: $params"
		log.info "PROPIEDADES COMMAND: "+cmd.properties
		def result
		
		def flagcomilla=false
		def totalregistros
		def totalpaginas
		def list
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy")
		if(cmd.validate()){
			render(view:"cantidadvisitasporpaciente", model:[cmdInstance:cmd, buscar:true])
			
		}else{
			log.debug "ERRORES: "+cmd.errors.allErrors
			render(view:"cantidadvisitasporpaciente", model:[cmdInstance:cmd, buscar:false])
		}
		
	}
	
	def cantidadvisitasporpacientejson = {ConsultaCommand cmd ->
		log.info "INGRESANDO AL CLOSURE cantidadvisitasporpacientejson"
		log.info "PARAMETROS: $params"
		def result
		
		def flagcomilla=false
		def totalregistros
		def totalpaginas
		def list
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy")
		java.util.Date fechaDesde = df.parse(params.fechaDesde, new ParsePosition(0))
		java.util.Date fechaHasta = df.parse(params.fechaHasta, new ParsePosition(0))
		if(cmd.validate()){
			list = Consulta.createCriteria().list(){
				and{
					if(params.fechaDesde)
						ge("fechaConsulta",fechaDesde)
					if(params.fechaHasta)
						le("fechaConsulta",fechaHasta)
					if(params.cie10Id){
						cie10{
							eq("id",params.cie10Id.toLong())
						}
					}
					if(params.pacienteId){
						paciente{
							eq("id",params.pacienteId.toLong())
						}
					}
				}
				projections{
					count("paciente.id")
					groupProperty("paciente")
					groupProperty("cie10")
				}
					
				firstResult((params.page.toInteger()-1)*params.rows.toInteger())
				maxResults(params.rows.toInteger())
				order("fechaConsulta","desc")
			}
				
			totalregistros=Consulta.createCriteria().get(){
				createAlias("paciente","p")
				if(params.fechaDesde){
					ge("fechaConsulta",new java.sql.Date(fechaDesde.getTime()))
				}
				if(params.fechaHasta){
					le("fechaConsulta",new java.sql.Date(fechaHasta.getTime()))
				}
				if(params.cie10Id){
						cie10{
							eq("id",params.cie10Id.toLong())
						}
				}
				if(params.pacienteId){
					paciente{
						eq("id",params.pacienteId.toLong())
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
					log.debug "OBJETO: $it,"
					if(flagcomilla)
						result=result+','
					result=result+'{"id":"'+it[1].id+'","cell":["'+it[1].id+'","'+it[2]+'","'+it[1].apellido+'-'+it[1].nombre+'","'+(it[1].cie10!=null?it[1].cie10.id:"")+'","'+(it[1].cie10!=null?it[1].cie10.descripcion:"")+'","'+(it[1].obraSocial!=null?it[1].obraSocial?.descripcion:"")+'","'+it[0]+'"]}'
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
	//-----------------------------------------
	def pacientesatendidosporgrupodiag = {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		return [cmdInstance:new ConsultaCommand(fechaDesde:sdf.format(Calendar.getInstance().getTime()),fechaHasta:sdf.format(Calendar.getInstance().getTime()))]
		
	}
	
	
	def pacientesatendidosporgrupodiagbuscar = {ConsultaCommand cmd->
		log.info "INGRESANDO AL CLOSURE: pacientesatendidosporgrupodiag"
		log.info "PARAMETROS: $params"
		log.info "PROPIEDADES COMMAND: "+cmd.properties
		def result
		
		def flagcomilla=false
		def totalregistros
		def totalpaginas
		def list
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy")
		def diagGraph
		if(cmd.validate()){
			diagGraph = pordiagnosticogrph(params)
			render(view:"pacientesatendidosporgrupodiag", model:[cmdInstance:cmd, buscar:true,diagGraph:diagGraph])
			
		}else{
			log.debug "ERRORES: "+cmd.errors.allErrors
			render(view:"pacientesatendidosporgrupodiag", model:[cmdInstance:cmd, buscar:false])
		}
		
	}
	
	def pacientesatendidosporgrupodiagjson = {ConsultaCommand cmd ->
		log.info "INGRESANDO AL CLOSURE pacientesatendidosporgrupodiagjson"
		log.info "PARAMETROS: $params"
		def result
		
		def flagcomilla=false
		def totalregistros
		def totalpaginas
		def list
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy")
		java.util.Date fechaDesde = df.parse(params.fechaDesde, new ParsePosition(0))
		java.util.Date fechaHasta = df.parse(params.fechaHasta, new ParsePosition(0))
		if(cmd.validate()){
			list = Consulta.createCriteria().list(){
				and{
					if(params.fechaDesde)
						ge("fechaConsulta",fechaDesde)
					if(params.fechaHasta)
						le("fechaConsulta",fechaHasta)
					if(params.cie10Id){
						cie10{
							eq("id",params.cie10Id.toLong())
						}
					}
				}
					
				firstResult((params.page.toInteger()-1)*params.rows.toInteger())
				maxResults(params.rows.toInteger())
				order("fechaConsulta","desc")
			}
				
			totalregistros=Consulta.createCriteria().get(){
				createAlias("paciente","p")
				if(params.fechaDesde){
					ge("fechaConsulta",new java.sql.Date(fechaDesde.getTime()))
				}
				if(params.fechaHasta){
					le("fechaConsulta",new java.sql.Date(fechaHasta.getTime()))
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
					result=result+'{"id":"'+it.id+'","cell":["'+it.id+'","'+it.fechaConsulta+'","'+it.paciente.apellido+'-'+it.paciente.nombre+'","'+(it.cie10!=null?it.cie10.id:"")+'","'+(it.cie10!=null?it.cie10.descripcion:"")+'","'+(it.paciente.obraSocial!=null?it.paciente.obraSocial?.descripcion:"")+'"]}'
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

	//-----------------------------------------
	def reporteporos = {
		log.info "INGRESANDO AL CLOSURE reporteporos"
		log.info "PARAMETROS: $params"
		def list = Institucion.findAll()
		def institucionInstance = list.getAt(0)
		String pathimage
		String nameimage
		def config
		if(institucionInstance){
			pathimage = bi.resource(size:'large',bean:institucionInstance)
			if(pathimage.contains(".null")){
				pathimage = servletContext.getRealPath("/images")
				nameimage = "noDisponibleLarge.jpg"
			}else{
				config = ContainerUtils.getConfig(institucionInstance)
				pathimage = servletContext.getRealPath("/institucional")
				nameimage = ContainerUtils.getFullName("large", institucionInstance, config)
			}
		
		}
		params.put("pathimage", pathimage);
		params.put("nameimage", nameimage)
		params.put("nombreInstitucion", institucionInstance.nombre);
		params.put("telefonos", institucionInstance.telefonos);
		params.put("email", institucionInstance.email);
		params.put("direccion", institucionInstance.direccion);
		params.put("_format","PDF")
		params.put("_name","pacientesatendidosporos")
		params.put("_file","pacientesatendidosporos")

		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy")
		def listos=Consulta.createCriteria().list(){
			if(params.fechaDesde){
				java.util.Date fechaDesde = df.parse(params.fechaDesde, new ParsePosition(0))
				ge("fechaConsulta",new java.sql.Date(fechaDesde.getTime()))
			}
			if(params.fechaHasta){
				java.util.Date fechaHasta = df.parse(params.fechaHasta, new ParsePosition(0))
				le("fechaConsulta",new java.sql.Date(fechaHasta.getTime()))
			}
			if(params.obraSocialId){
				paciente{
					obraSocial{
						eq("id",params.obraSocialId.toLong())
					}
				}
			}
			paciente{
				order("apellido","desc")
			}
		}
		chain(controller:'jasper',action:'index',model:[data:listos],params:params)
	}
	
	def reporteporosranking = {
		log.info "INGRESANDO AL CLOSURE reporteporosranking"
		log.info "PARAMETROS: $params"
		def list = Institucion.findAll()
		def institucionInstance = list.getAt(0)
		String pathimage
		String nameimage
		def config
		if(institucionInstance){
			pathimage = bi.resource(size:'large',bean:institucionInstance)
			if(pathimage.contains(".null")){
				pathimage = servletContext.getRealPath("/images")
				nameimage = "noDisponibleLarge.jpg"
			}else{
				config = ContainerUtils.getConfig(institucionInstance)
				pathimage = servletContext.getRealPath("/institucional")
				nameimage = ContainerUtils.getFullName("large", institucionInstance, config)
			}
		
		}
		params.put("pathimage", pathimage);
		params.put("nameimage", nameimage)
		params.put("nombreInstitucion", institucionInstance.nombre);
		params.put("telefonos", institucionInstance.telefonos);
		params.put("email", institucionInstance.email);
		params.put("direccion", institucionInstance.direccion);
		params.put("_format","PDF")
		params.put("_name","pacientesatendidosporosranking")
		params.put("_file","pacientesatendidosporosranking")

		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy")
		def listos=Consulta.createCriteria().list(){
			createAlias("paciente","p")
			
			if(params.fechaDesde){
				java.util.Date fechaDesde = df.parse(params.fechaDesde, new ParsePosition(0))
				ge("fechaConsulta",new java.sql.Date(fechaDesde.getTime()))
			}
			if(params.fechaHasta){
				java.util.Date fechaHasta = df.parse(params.fechaHasta, new ParsePosition(0))
				le("fechaConsulta",new java.sql.Date(fechaHasta.getTime()))
			}
			if(params.obraSocialId){
//				paciente{
//					obraSocial{
//						eq("id",params.obraSocialId.toLong())
//					}
//				}
				eq("p.obraSocial.id",params.obraSocialId.toLong())
			}
			
			order("p.apellido","desc")
			projections {
				count ("p.id")
				groupProperty 'p.obraSocial'
			}
		}
		chain(controller:'jasper',action:'index',model:[data:listos],params:params)

	}
	
	def reporteporprimeravez = {
		log.info "INGRESANDO AL CLOSURE reporteporprimeravez"
		log.info "PARAMEETROS: $params"
		def list = Institucion.findAll()
		def institucionInstance = list.getAt(0)
		String pathimage
		String nameimage
		def config
		if(institucionInstance){
			pathimage = bi.resource(size:'large',bean:institucionInstance)
			if(pathimage.contains(".null")){
				pathimage = servletContext.getRealPath("/images")
				nameimage = "noDisponibleLarge.jpg"
			}else{
				config = ContainerUtils.getConfig(institucionInstance)
				pathimage = servletContext.getRealPath("/institucional")
				nameimage = ContainerUtils.getFullName("large", institucionInstance, config)
			}
		
		}
		params.put("pathimage", pathimage);
		params.put("nameimage", nameimage)
		params.put("nombreInstitucion", institucionInstance.nombre);
		params.put("telefonos", institucionInstance.telefonos);
		params.put("email", institucionInstance.email);
		params.put("direccion", institucionInstance.direccion);
		params.put("_format","PDF")
		params.put("_name","pacientesatendidosporprimeravez")
		params.put("_file","pacientesatendidosporprimeravez")

		
		def listPrimeravez
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy")
		java.util.Date fechaDesde = df.parse(params.fechaDesde, new ParsePosition(0))
		java.util.Date fechaHasta = df.parse(params.fechaHasta, new ParsePosition(0))
		def detachedCriteria = DetachedCriteria.forClass(Consulta.class,"inner")
		detachedCriteria.setProjection(Projections.rowCount())
		Criterion c1 = Restrictions.and(Restrictions.and(Restrictions.ge("fechaConsulta",fechaDesde),Restrictions.le("fechaConsulta", fechaHasta)),Restrictions.eqProperty("inner.paciente.id","p.id"))
		
		detachedCriteria.add(c1)
		//detachedCriteria.setProjection(Projections.groupProperty("paciente.id"))
		detachedCriteria.setProjection(Projections.min("fechaConsulta"))

		listPrimeravez = Consulta.createCriteria().list(){
			and{
				if(params.fechaDesde)
					ge("fechaConsulta",fechaDesde)
				if(params.fechaHasta)
					le("fechaConsulta",fechaHasta)
				if(params.obraSocialId){
					paciente{
						obraSocial{
							eq("id",params.obraSocialId.toLong())
						}
					}
				}
					
			}
			createAlias("paciente","p")
			ge("fechaConsulta",fechaDesde)
			le("fechaConsulta",fechaHasta)
			projections{
				count("p.id")
				groupProperty("paciente")
				instance.add(Subqueries.le(fechaDesde,detachedCriteria))
			}
			order("p.apellido","desc")
		}

		
		chain(controller:'jasper',action:'index',model:[data:listPrimeravez],params:params)

	}
	
	def reportepordiagranking = {
		log.info "INGRESANDO AL CLOSURE reportepordiagranking"
		log.info "PARAMETROS: $params"
		def list = Institucion.findAll()
		def institucionInstance = list.getAt(0)
		String pathimage
		String nameimage
		def config
		if(institucionInstance){
			pathimage = bi.resource(size:'large',bean:institucionInstance)
			if(pathimage.contains(".null")){
				pathimage = servletContext.getRealPath("/images")
				nameimage = "noDisponibleLarge.jpg"
			}else{
				config = ContainerUtils.getConfig(institucionInstance)
				pathimage = servletContext.getRealPath("/institucional")
				nameimage = ContainerUtils.getFullName("large", institucionInstance, config)
			}
		
		}
		params.put("pathimage", pathimage);
		params.put("nameimage", nameimage)
		params.put("nombreInstitucion", institucionInstance.nombre);
		params.put("telefonos", institucionInstance.telefonos);
		params.put("email", institucionInstance.email);
		params.put("direccion", institucionInstance.direccion);
		params.put("_format","PDF")
		params.put("_name","pacientesatendidospordiagranking")
		params.put("_file","pacientesatendidospordiagranking")

		
		def listPrimeravez
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy")
		java.util.Date fechaDesde = df.parse(params.fechaDesde, new ParsePosition(0))
		java.util.Date fechaHasta = df.parse(params.fechaHasta, new ParsePosition(0))
		def detachedCriteria = DetachedCriteria.forClass(Consulta.class,"inner")
		detachedCriteria.setProjection(Projections.rowCount())
		Criterion c1 = Restrictions.and(Restrictions.and(Restrictions.ge("fechaConsulta",fechaDesde),Restrictions.le("fechaConsulta", fechaHasta)),Restrictions.eqProperty("inner.paciente.id","p.id"))
		
		detachedCriteria.add(c1)
		//detachedCriteria.setProjection(Projections.groupProperty("paciente.id"))
		detachedCriteria.setProjection(Projections.min("fechaConsulta"))

		listPrimeravez = Consulta.createCriteria().list(){
			and{
				if(params.fechaDesde)
					ge("fechaConsulta",fechaDesde)
				if(params.fechaHasta)
					le("fechaConsulta",fechaHasta)
				if(params.cie10Id){
					cie10{
							eq("id",params.cie10Id.toLong())
					}
				}
					
			}
			createAlias("paciente","p")
			ge("fechaConsulta",fechaDesde)
			le("fechaConsulta",fechaHasta)
			projections{
				count("cie10.id","cantidad")
				groupProperty("cie10")
				instance.add(Subqueries.le(fechaDesde,detachedCriteria))
			}
			order("cantidad","desc")
		}

		
		chain(controller:'jasper',action:'index',model:[data:listPrimeravez],params:params)
		
	} 
	
	
}


class ConsultaCommand{
	String fechaDesde
	String fechaHasta
	String profesional
	String profesionalId
	String obraSocial
	String obraSocialId
	String paciente
	String pacienteId
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
		
		pacienteId(blank:true, validator:{v,cmd->
			if(v){
				try{
					def pacienteInstance = Paciente.load(v.toLong())
					if(pacienteInstance){
						cmd.paciente = pacienteInstance.apellido+"-"+pacienteInstance.nombre
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

