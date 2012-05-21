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

	
}
