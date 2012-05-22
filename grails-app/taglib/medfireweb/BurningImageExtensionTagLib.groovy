package medfireweb

import pl.burningice.plugins.image.container.ContainerWorkerFactory

class BurningImageExtensionTagLib {
	ContainerWorkerFactory containerWorkerFactory
	def authenticateService
	
	def resourceimgext = { attrs ->
		def tamanio = attrs.size
		if(containerWorkerFactory.produce(attrs.bean).hasImage()){
			out << bi.resource(bean:attrs.bean,size:attrs.size)
		}else{
			if(tamanio.equals("small"))
				out << g.resource(dir:'images',file:'noDisponible.jpg');
			if(tamanio.equals("large"))	
				out << g.resource(dir:'images',file:'noDisponibleLarge.jpg');
		}
	}
	
	
	def institucionimg = { attrs ->
		//<bi:img size="large" bean="${imageContainer}" />
		def institucionInstance = authenticateService.userDomain().institucion 

		out << bi.img(size:"large",bean:institucionInstance)

		
	}
	
	def institucioninfo = { attrs ->
		out << "<CENTER>"
		out << "<p><H1>"+authenticateService.userDomain().institucion.nombre+"</H1></p>"+"<p>"+(authenticateService.userDomain().institucion.direccion?authenticateService.userDomain().institucion.direccion:"")+"</p>"
		out << "<p><a href='${(authenticateService.userDomain().institucion.web?authenticateService.userDomain().institucion.web:"")}'>"+(authenticateService.userDomain().institucion.web?authenticateService.userDomain().institucion.web:"")+"</a>"
		out << "</CENTER>"
	}

	
}
