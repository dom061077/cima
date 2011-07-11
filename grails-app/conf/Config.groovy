// locations to search for config files that get merged into the main config
// config files can either be Java properties files or ConfigSlurper scripts

// grails.config.locations = [ "classpath:${appName}-config.properties",
//                             "classpath:${appName}-config.groovy",
//                             "file:${userHome}/.grails/${appName}-config.properties",
//                             "file:${userHome}/.grails/${appName}-config.groovy"]

// if(System.properties["${appName}.config.location"]) {
//    grails.config.locations << "file:" + System.properties["${appName}.config.location"]
// }

import pl.burningice.plugins.image.engines.scale.ScaleType

grails.project.groupId = appName // change this to alter the default package name and Maven publishing destination
grails.mime.file.extensions = true // enables the parsing of file extensions from URLs into the request format
grails.mime.use.accept.header = false
grails.mime.types = [ html: ['text/html','application/xhtml+xml'],
                      xml: ['text/xml', 'application/xml'],
                      text: 'text/plain',
                      js: 'text/javascript',
                      rss: 'application/rss+xml',
                      atom: 'application/atom+xml',
                      css: 'text/css',
                      csv: 'text/csv',
                      all: '*/*',
                      json: ['application/json','text/json'],
                      form: 'application/x-www-form-urlencoded',
                      multipartForm: 'multipart/form-data'
                    ]

// URL Mapping Cache Max Size, defaults to 5000
//grails.urlmapping.cache.maxsize = 1000

// Path de los reportes 
jasper.dir.reports = 'reports'

// The default codec used to encode data with ${}
grails.views.default.codec = "none" // none, html, base64
grails.views.gsp.encoding = "UTF-8"
grails.converters.encoding = "UTF-8"
// enable Sitemesh preprocessing of GSP pages
grails.views.gsp.sitemesh.preprocess = true
// scaffolding templates configuration
grails.scaffolding.templates.domainSuffix = 'Instance'

// Set to false to use the new Grails 1.2 JSONBuilder in the render method
grails.json.legacy.builder = false
// enabled native2ascii conversion of i18n properties files
grails.enable.native2ascii = true
// whether to install the java.util.logging bridge for sl4j. Disable for AppEngine!
grails.logging.jul.usebridge = true
// packages to include in Spring bean scanning
grails.spring.bean.packages = []

//grails.plugin.cloudfoundry.target = "http://api.cloudfoundry.com:3128"
grails.plugin.cloudfoundry.username = "dom061077@gmail.com"
grails.plugin.cloudfoundry.password = "aCZytfim"
grails.plugin.cloudfoundry.appname = "medfireweb"
grails.plugin.cloudfoundry.http_proxy = "http://proxy.vmware.com:3128vmc target api.cloudfoundry.com"

// set per-environment serverURL stem for creating absolute links
environments {
    production {
        grails.serverURL = "http://www.changeme.com"
    }
    development {
        grails.serverURL = "http://localhost:8080/${appName}"
    }
    test {
        grails.serverURL = "http://localhost:8080/${appName}"
    }

}

// log4j configuration



//log4j.logger.org.springframework.security='off,stdout'

//log4j.logger.org.springframework.security='off,stdout'


bi.Profesional = [
	//outputDir: 'path/to/output/dir',
	//prefix: 'mySuperImage',
	images: ['large':[scale:[width:100, height:100, type:ScaleType.APPROXIMATE],
					  //watermark:[sign:'images/watermark.png', offset:[top:10, left:10]]
					  ],
			 'small':[scale:[width:25, height:25, type:ScaleType.ACCURATE],
					  //watermark:[sign:'images/watermark.png', offset:[top:10, left:10]]
					  ]],
	constraints:[
		nullable:true,
		maxSize:716800,
		contentType:['image/gif', 'image/png' , 'image/jpg', 'image/jpeg']
	]
]
