dataSource {
    pooled = true
    driverClassName = "com.mysql.jdbc.Driver"
    username = "root"
    password = ""
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
            dbCreate = "create" // one of 'create', 'create-drop','update'
            url = "jdbc:mysql://localhost/medfireweb"
        }
    }
    test {
        dataSource {
            //dbCreate = "update"
            url = "jdbc:mysql://localhost/medfireweb"
        }
    }
    production {
        dataSource {
            //dbCreate = "update"
            url = "jdbc:mysql://localhost/medfireweb"
        }
    }
	
	dbdiff {
		dataSource {
			dbCreate = "create-drop"
			url = "jdbc:mysql://localhost/medfirewebprod"
			dbCreate = "create-drop"
		}
		
	}
}

