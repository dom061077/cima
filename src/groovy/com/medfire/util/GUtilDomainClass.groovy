package com.medfire.util

import org.codehaus.groovy.grails.commons.DefaultGrailsDomainClass;
import java.util.StringTokenizer;
import com.medfire.*
import org.apache.log4j.Logger;
import grails.converters.JSON


class GUtilDomainClass{
	private static Logger log = Logger.getLogger(GUtilDomainClass.class)
	def domainClass
	def grailsApplication
	def params
	def field
	def criteria

	GUtilDomainClass(def domainClass,def params,def grailsApplication){
		this.domainClass=domainClass
		this.params=params
		this.grailsApplication=grailsApplication
	}
	
	private def getDomainClassFieldValue(Class definition,String field,String value){
		DefaultGrailsDomainClass obj = new DefaultGrailsDomainClass(definition);
		
		return obj.getPropertyByName(field).getType().newInstance(value);
	}
	
	static final String		EQ="eq";
	static final String		NOTEQUAL="ne";
	static final String 	LESSTHAN="lt";
	static final String 	LESSTHANEQUALS="le";
	static final String 	GREATERTHAN="gt";
	static final String 	GREATERTHANEQUALS="ge";
	static final String		ISIN="in";
	static final String		ISNOTIN="ni";
	static final String		CONTAINS="cn";
	static final String		DOESNOTCONTAIN="nc";
	static final String 	BEGINWITH="bw";
	
	
	def parseValue(def rawValue, def mp, def params) {
		log.info "INGRESANDO AL METODO parseValue DE LA CLASE GUtilDomainClass"
		def val = rawValue

		if (val) {
			Class cls = mp.type //mc.theClass.getDeclaredField(prop).type
			if (cls.isEnum()) {
				val = Enum.valueOf(cls, val.toString())
				//println "val is ${val} and raw val is ${rawValue}"
			} else if (mp.type.getSimpleName().equalsIgnoreCase("boolean")) {
				try{
					val = val.toBoolean()
				}catch(Exception e){
					val = false
				}
			} else if (mp.type == Integer || mp.type == int) {
				try{
					val = val.toInteger()
				}catch(Exception e){
					val = "0".toInteger()
				}
			} else if (mp.type == Long || mp.type == long) {
				try{
					val = val.toLong()
				}catch(Exception e){
					val = "0".toLong()
				}
			} else if (mp.type == Double || mp.type == double) {
				try{
					val = val.toDouble()
				}catch(Exception e){
					val = "0".toDouble()
				}
			} else if (mp.type == Float || mp.type == float) {
				try{
					val = val.toFloat()
				}catch(Exception e){
					val = "0".toFloat()
				}
			} else if (mp.type == Short || mp.type == short) {
				try{
					val = val.toShort()
				}catch(Exception e){
					val = "0".toShort()
				}
			} else if (mp.type == BigDecimal) {
				try{
					val = val.toBigDecimal()
				}catch(Exception e){
					val = "0".toBigDecimal()
				}
			} else if (mp.type == BigInteger) {
				try{
					val = val.toBigInteger()
				}catch(Exception e){
					val = "0".toBigInteger()
				}
			} else if (java.util.Date.isAssignableFrom(mp.type)) {
				val = FilterUtils.parseDateFromDatePickerParams(paramName, params)
			}else
				val= "%${val}%"
		}
		//println "== Parsing value ${rawValue} from param ${paramName}. type is ${mp.type}.  Final value ${val}. Type ${val?.class}"
		return val
	}

	

	
	private String operationSearch(String cr){
		if(cr!=null){
			if(EQ.toUpperCase().equals(cr.toUpperCase()))
				return "eq";
			if(NOTEQUAL.toUpperCase().equals(cr.toUpperCase()))
				return "ne";
			if(LESSTHAN.toUpperCase().equals(cr.toUpperCase()))
				return "lt";
			if(LESSTHANEQUALS.toUpperCase().equals(cr.toUpperCase()))
				return "le";
			if(GREATERTHAN.toUpperCase().equals(cr.toUpperCase()))
				return "gt";
			if(GREATERTHANEQUALS.toUpperCase().equals(cr.toUpperCase()))
				return "ge";
			if(ISIN.toUpperCase().equals(cr.toUpperCase()))
				return "in";
			if(ISNOTIN.toUpperCase().equals(cr.toUpperCase()))
				return "in";
			
			if(CONTAINS.toUpperCase().equals(cr.toUpperCase()) || DOESNOTCONTAIN.toUpperCase().equals(cr.toUpperCase()) || 
				BEGINWITH.toUpperCase().equals(cr.toUpperCase()))
				return "ilike";
			
				
		}	
		return "";
		
	}
	

	
	def listrefactor(boolean rowcount){
		log.info "INGRESANDO AL METODO listrefactor DE LA CLASE GUtilDomainClass"
		log.info "Domain class: ${domainClass}"
		def searchOper=""
		def searchValue
		def metaProperty
		def fieldToken
		def list = []
		def pagingConfing = [
			max: Integer.parseInt(params.rows),
			offset: Integer.parseInt(params.page)-1
		]
		def criteria = domainClass.createCriteria()
		
		def closure = {
			log.debug "INGRESANDO AL CLOSURE INTERNO QUE AGREGA LAS CONDICIONES DE BUSQUEDA DEL CRITERIA"
			if(Boolean.parseBoolean(params._search)){
				if(params.searchOper){
					log.debug "params.searchString ${params.searchString}, params.searchField: ${params.searchField}"
					metaProperty=FilterUtils.getNestedMetaProperty(grailsApplication,domainClass,params.searchField)
					log.info "META PROPERTY DEVUELTA: ${metaProperty}"
					searchOper= operationSearch(params.searchOper)
					searchValue=parseValue(params.searchString, metaProperty, params)
					criteria."${searchOper}"(params.searchField,searchValue)
				}	
				if(params.filters){
					def filtersJson = JSON.parse(params.filters)
					criteria."${filtersJson.groupOp.toLowerCase()}"(){
							filtersJson?.rules?.each{
								log.info "REGLAS DE BUSQUEDA APLICADAS:"
								searchOper = operationSearch(it.op)
								metaProperty = FilterUtils.getNestedMetaProperty(grailsApplication,domainClass,it.field)
								

								searchValue=parseValue(it.data,metaProperty,params)
								if(it.field.contains("_")){
									fieldToken = it.field.tokenize("_")
									criteria."${fieldToken[0]}"{
										criteria."${searchOper}"(fieldToken[1],searchValue)
									}
								}else{
									criteria."${searchOper}"(it.field,searchValue)
								}

								
								
							}
						}	
				}
			}
			log.info "paginacion en list refactor rows: ${params.rows.toInteger()}, pag: ${params.page.toInteger()}"
			if(rowcount){
				criteria.projections{
					rowCount()
				}
			}else{
				firstResult(params.page.toInteger()-1)
				maxResults(params.rows.toInteger())
				if(params.sidx && params.sord)
					order(params.sidx,params.sord)

			}
		} 
		if(rowcount){
			return criteria.get(closure)	
		}else{
			return criteria.list(closure)
		}
		
		
	}
	
	def list(){
		//log.info "INGRESANDO AL METODO list DE LA CLASE GUtilClass"
		DefaultGrailsDomainClass dc = new DefaultGrailsDomainClass(domainClass);
		def list=[]
    	def pagingConfig = [
    		max: Integer.parseInt(params.rows),
    		offset: Integer.parseInt(params.page)-1
    	] 
		def searchOper
		def searchValue
		if((Boolean.parseBoolean(params._search))){
			searchOper = operationSearch(params.searchOper)
			try{
				if(params.searchOper.equals(ISIN) || params.searchOper.equals(ISNOTIN)){
					def st = new StringTokenizer(params.searchString,",")
					searchValue=[]
					st.each{
						searchValue.add(
								dc.getPropertyByName(params.searchField).getType().newInstance(it)
							)	
					}
				}else
					searchValue = dc.getPropertyByName(params.searchField).getType().newInstance(params.searchString)
			}catch(Exception e){
				list
				return list
			}
			
			//---aplico el filtro del toolbar
			if(params.filters?.rules?.size()>0){
				
			}
			
			log.info "PagingConfig: ${pagingConfig}"
			
			list = domainClass.createCriteria().list(pagingConfig){
					if(params.searchOper.equals(CONTAINS) || searchOper.equals(DOESNOTCONTAIN) )
						searchValue="%"+searchValue+"%"

					//log.debug "OPERACION DE BUSQUEDA: "+searchOp+" VALOR DE BUSQUEDA: $searchValue"				
					if(params.searchOper.equals(DOESNOTCONTAIN)||params.searchOper.equals(ISNOTIN)){
						//log.debug "NEGACION DE LA BUSQUEDA LIKE"
						not {
							"${searchOper}"(params.searchField,searchValue)
						}
					}else{
						//if(params.searchOper.equals(ISIN) || params.searchOper.equals(ISNOTIN))
						//	searchOper="'"+searchOper+"'"
						
						"${searchOper}"(params.searchField,searchValue)
					}	
					
					order(params.sidx,params.sord)
					//max: Integer.parseInt(params.rows),
					//offset: Integer.parseInt(params.page)-1
		
					
				}
		}else{
			log.info "cantidad de filas a mostrar: ${para.rows}, valor variable page: ${params.page}"
			list=domainClass.createCriteria().list(pagingConfig){
					if(params.sidx)
						order(params.sidx,params.sord)
	
				}
		}
		return list
		
	}

}