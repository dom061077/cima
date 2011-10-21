package com.medfire

class DatabaseService {
    static transactional = true

    def TEST = "Test"
    def PRODUCTION = "Production"
	def DEVELOPMENT = "Development"

    def dataSourceTest
    def dataSourceProduction
	def dataSourceDevelopment

	def getDataSource(dbEnv) {
        switch(dbEnv) {
            case TEST:
                return dataSourceTest
            case PRODUCTION:
                return dataSourceProduction
			case DEVELOPMENT:
				return dataSourceDevelopment	
        }
    }
}
