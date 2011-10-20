package com.medfire

import groovy.sql.Sql
import java.text.SimpleDateFormat
import java.text.ParseException
import java.text.ParsePosition;


class AuditoriaController {

    def index = { }
	
	def consultabuscar = {AuditoriaCommand cmd ->
		log.info "INGRESANDO AL CLOSURE consulta"
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
		def totalregistros
		def totalpaginas
		def list
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy")
		java.util.Date fechaDesde = df.parse(params.fechaDesde, new ParsePosition(0))
		java.util.Date fechaHasta = df.parse(params.fechaHasta, new ParsePosition(0))

		if(cmd.validate()){
			def sql = Sql.newInstance(ConfigurationHolder.config.db as String,
				ConfigurationHolder.config.dbUsername as String,
				ConfigurationHolder.config.dbPassword as String,
				"com.mysql.jdbc.Driver")
			list = sql.rows("""select * from audit_log """)
			
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

		}

	}
}

class AuditoriaCommand{
	String fechaDesde
	String fechaHasta
	String nombreUsuario
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
		
		nombreUsuario(blank:true,nullable:true)
		tipoTransaccion(blank:true,nullable:true)

	}
}
