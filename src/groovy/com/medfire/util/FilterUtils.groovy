package com.medfire.util

import org.codehaus.groovy.grails.commons.DefaultGrailsDomainClass
import org.apache.log4j.Logger


class FilterUtils {
	private static Logger log = Logger.getLogger(FilterUtils.class)
	
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
	

	static def getNestedMetaProperty(def grailsApplication,def domainClass, String propertyName) {
        def nest = propertyName.tokenize('_')
        def thisDomainClass = grailsApplication.getDomainClass(domainClass.name)
        def thisDomainProp = null
        
        nest.each() {egg ->
            if(thisDomainClass){
                thisDomainProp =thisDomainClass.persistentProperties.find {
                    
                    it.name == egg
                }
                thisDomainClass = thisDomainProp?.referencedDomainClass
            }
     
        }
		log.info "FILTER UTILS : metaproperty devuelta: ${thisDomainProp}"
        return thisDomainProp
	}

	static java.util.Date parseDateFromDatePickerParams(def paramProperty, def params) {
		try {
			def year = params["${paramProperty}_year"]
			def month = params["${paramProperty}_month"]
			def day = params["${paramProperty}_day"]
			def hour = params["${paramProperty}_hour"]
			def minute = params["${paramProperty}_minute"]

//            if (log.isDebugEnabled()) {
//                log.debug("Parsing date from params: ${year} ${month} ${day} ${hour} ${minute}")
//            }

			String format = ''
			String value = ''
			if (year != null) {
				format = "yyyy"
				value = year
			}
			if (month != null) {
				format += 'MM'
				value += zeroPad(month)
			}
			if (day != null) {
				format += 'dd'
				value += zeroPad(day)
			}
			if (hour != null) {
				format += 'HH'
				value += zeroPad(hour)
			} else if (paramProperty.endsWith('To')) {
				format += 'HH'
				value += '23'
			}

			if (minute != null) {
				format += 'mm'
				value += zeroPad(minute)
			} else if (paramProperty.endsWith('To')) {
				format += 'mm:ss.SSS'
				value += '59:59.999'
			}

			if (value == '') { // Don't even bother parsing.  Just return null if blank.
				return null
			}

			if (log.isDebugEnabled()) log.debug("Parsing ${value} with format ${format}")
			return new java.text.SimpleDateFormat(format).parse(value)
		} catch (Exception ex) {
			log.error("${ex.getClass().simpleName} parsing date for property ${paramProperty}: ${ex.message}")
			return null
		}
	}

	
	static String operationSearch(String cr){
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
		
}
