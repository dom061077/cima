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




grails {
    mail {
        host = "smtp.gmail.com"
        port = 587
        username = "mimail@gmail.com"
        password = "xxxxxx"
        props = ["mail.debug": "true",
                "mail.smtp.protocol": "smtps",
                "mail.smtp.auth": "true",
                "mail.smtp.starttls.enable": "true",
                "mail.smtp.host": "smtp.gmail.com",
                "mail.smtp.user": "mimail@gmail.com",
                "mail.smtp.password": "xxxxx",
				]
    }
}



event.COLOR_ATENDIDO="#54de25"
event.COLOR_AUSENTE="#fab7a0"
event.COLOR_ANULADO="#D8D8D8"
event.COLOR_PENDIENTE="#8c94f0"
event.COLOR_SOBRETURNO="#DF0101"
event.COLOR_ENSALA="C29C05"


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
	outputDir: 'profesionalesimg',
	//prefix: 'mySuperImage',
	images: ['large':[scale:[width:700, height:500, type:ScaleType.APPROXIMATE]
					  //,watermark:[sign:'images/watermark.png', offset:[top:10, left:10]]
					  ],
			 'small':[scale:[width:25, height:25, type:ScaleType.ACCURATE]
					  //,watermark:[sign:'images/watermark.png', offset:[top:10, left:10]]
					  ],
				  ],
	constraints:[
		nullable:true,
		maxSize:1024*250,
		contentType:['image/gif', 'image/png' , 'image/jpg', 'image/jpeg', 'image/pjpeg', 'image/x-png']
	]
]


bi.EstudioComplementarioImagen = [
	outputDir: 'imagenestudios',
	//prefix: 'mySuperImage',
	images: ['large':[scale:[width:500, height:500, type:ScaleType.APPROXIMATE]
					  //,watermark:[sign:'images/watermark.png', offset:[top:10, left:10]]
					  ],
			 'small':[scale:[width:50, height:50, type:ScaleType.ACCURATE]
					  //,watermark:[sign:'images/trash.gif', offset:[top:10, left:10]]
					  ]
		  	]
	//,outputDir: ['path':'/var/www/my-app/images/', 'alias':'/upload/']
	,constraints:[
		nullable:true,
		maxSize:1024*5000,
		contentType:['image/gif', 'image/png' , 'image/jpg', 'image/jpeg', 'image/pjpeg', 'image/x-png']
	]
]

bi.Institucion = [
	outputDir : 'institucional',
	images: ['large':[scale:[width:400, height:100, type:ScaleType.APPROXIMATE]
					  //,watermark:[sign:'images/watermark.png', offset:[top:10, left:10]]
					  ],
			 'small':[scale:[width:50, height:50, type:ScaleType.ACCURATE]
					  //,watermark:[sign:'images/trash.gif', offset:[top:10, left:10]]
					  ]
		  	]
	//,outputDir: ['path':'/var/www/my-app/images/', 'alias':'/upload/']
	,constraints:[
		nullable:true,
		maxSize:1024*5000,
		contentType:['image/gif', 'image/png' , 'image/jpg', 'image/jpeg', 'image/pjpeg', 'image/x-png']
	]
]
