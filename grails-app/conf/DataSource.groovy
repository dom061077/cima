System.setProperty 'mail.smtp.port', '587'
System.setProperty 'mail.smtp.starttls.enable', 'true'

dataSource {
	//username="root"
	//password="exito"
}
hibernate {
    cache.use_second_level_cache = true
    cache.use_query_cache = true
    cache.provider_class = 'net.sf.ehcache.hibernate.EhCacheProvider'
}
// environment specific settings
environments {
    development {
        dataSource {
			pooled = true
			driverClassName = "com.mysql.jdbc.Driver"
			username = "root"
			password = "exito"
		
            dbCreate = "create-drop" // one of 'create', 'create-drop','update'
            url = "jdbc:mysql://localhost/medfireweb"
        }
    }
    test {
        dataSource {
			pooled = true
			driverClassName = "com.mysql.jdbc.Driver"
			username = "root"
			password = "exito"
            dbCreate = "create-drop"
            url = "jdbc:mysql://localhost/medfireweb"
        }
    }
    production {
        dataSource {
			pooled = true
			driverClassName = "com.mysql.jdbc.Driver"
			username = "inapcom_root"
			password = "DomPomoSkiby2011"
            dbCreate = "update"
            url = "jdbc:mysql://localhost/inapcom_medfireweb"
			properties {
				maxActive = 50
				maxIdle = 25
				minIdle = 5
				initialSize = 5
				minEvictableIdleTimeMillis = 60000
				timeBetweenEvictionRunsMillis = 60000
				maxWait = 10000
				validationQuery = "select 1"
			}
        }
    }
	
	dbdiff {
		dataSource {
			pooled = true
			driverClassName = "com.mysql.jdbc.Driver"
			username = "root"
			password = "exito"
			dbCreate = "create-drop"
			url = "jdbc:mysql://10.0.0.199/medfireweb"
			dbCreate = "create-drop"
		}
		
	}
}


