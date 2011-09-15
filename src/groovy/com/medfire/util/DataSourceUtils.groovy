package com.medfire.util

import org.apache.log4j.Logger
import org.codehaus.groovy.grails.commons.ApplicationAttributes

class DataSourceUtils {

	static final Logger log = Logger.getLogger(DataSourceUtils)
	
	   private static final ms = 1000 * 15 * 60
	
	   public static tune = { servletContext ->
	
		   def ctx = servletContext.getAttribute(ApplicationAttributes.APPLICATION_CONTEXT)
		   ctx.dataSource.with {d ->
			   d.targetDataSource.setMinEvictableIdleTimeMillis(ms)
			   d.targetDataSource.setTimeBetweenEvictionRunsMillis(ms)
			   d.targetDataSource.setNumTestsPerEvictionRun(3)
			   d.targetDataSource.setTestOnBorrow(true)
			   d.targetDataSource.setTestWhileIdle(true)
			   d.targetDataSource.setTestOnReturn(true)
			   d.targetDataSource.setValidationQuery('select 1')
			   d.targetDataSource.setMinIdle(1)
			   d.targetDataSource.setMaxActive(16)
			   d.targetDataSource.setInitialSize(8)
		   }
	
		   if (log.infoEnabled) {
			   log.info "Configured Datasource properties:"
			   ctx.dataSource.properties.findAll {k, v -> !k.contains('password') }.each {p ->
				   log.info "  $p"
			   }
		   }
	   }
	
}
