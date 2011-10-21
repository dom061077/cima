package com.medfire

import groovy.sql.Sql
import org.codehaus.groovy.grails.commons.ConfigurationHolder
import java.text.SimpleDateFormat
import java.text.ParseException
import java.text.ParsePosition;


class AuditoriaController {
	def dataSource

    def index = { }
	
	def consulta = {AuditoriaCommand cmd->
		log.info "INGRESANDO AL CLOSURE consulta"
		log.info "PARAMETROS: $params"		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		return [cmdInstance:new AuditoriaCommand(fechaDesde:sdf.format(Calendar.getInstance().getTime()),fechaHasta:sdf.format(Calendar.getInstance().getTime()))]

	}
	
	
	def consultabuscar = {AuditoriaCommand cmd ->
		log.info "INGRESANDO AL CLOSURE consultabuscar"
		log.info "PARAMETROS: $params"
		log.debug "COMMAND OBJECT: $cmd.properties"
		if(cmd.validate()){
			render(view:"consulta",model:[cmdInstance:cmd])
		}else{
		
			render(view:"consulta",model:[cmdInstance:cmd])
		}

	}
	
	def auditoriajson = {AuditoriaCommand cmd ->
		log.info "INGRESANDO AL CLOSURE auditoriajson"
		log.info "PARAMETROS: $params"
		def result
		
		def flagcomilla=false
		def totalregistros=0
		def totalpaginas=0
		def list
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy")
		java.util.Date fechaDesde = df.parse(params.fechaDesde, new ParsePosition(0))
		java.util.Date fechaHasta = df.parse(params.fechaHasta, new ParsePosition(0))
		def param = [fechaDesde:fechaDesde,fechaHasta:fechaHasta]
		def sqlstr = "select * from audit_log where date_created between :fechaDesde and :fechaHasta"

		if(cmd.validate()){
			
			log.debug "username: "+dataSource
			def sql = Sql.newInstance(dataSource)
			if(params.usuarioId){
				sqlstr = sqlstr + " and actor = :actor"
				param.put("actor", usuario)
			}
			if(params.tipoTransaccion){
				sqlstr = sqlstr + " and event_name = :eventname"
				param.put("eventname",params.tipoTransaccion)
			}		
			list = sql.rows(sqlstr,param)
			log.debug "TAMAÑO DE LA LISTA: "+list.size()
			
			if(list){
				totalpaginas = new Float(totalregistros/Integer.parseInt(params.rows))
				if (totalpaginas>0 && totalpaginas<1)
					totalpaginas=1
				totalpaginas = totalpaginas.intValue()
				result='{"page":'+params.page+',"total":"'+totalpaginas+'","records":"'+totalregistros+'","rows":['
				def i=1
				list.each{
					log.debug "OBJETO: $it,"
					if(flagcomilla)
						result=result+','
					result=result+'{"id":"'+i+'","cell":["'+i+'","'+it.actor+'","'+it.class_name+'","'+it.date_created+'","'+it.event_name+'","'+it.last_updateed+'","'+it.+'"]}'
					flagcomilla=true
					i++
				}
				result=result+']}'
				render result
			}else{
				result='{"page":0,"total":"0","records":"0","rows":['
				result=result+']}'
				render result
			}

		}

	}
}

class AuditoriaCommand{
	String fechaDesde
	String fechaHasta
	String usuarioId
	String usuario
	String tipoTransaccion
	
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
		
		usuarioId(blank:true,nullable:true,validator:{v,cmd ->
			if(v){
				try{
					def usuarioInstance = User.load(v.toLong())
					if(usuarioInstance){
						cmd.usuario = usuarioInstance.apellido+"-"+usuarioInstance.nombre
						return true
					}else
						return false
				}catch(Exception e){
					return false
				}
				return true
			}
		})
		tipoTransaccion(blank:true,nullable:true)

	}
}
