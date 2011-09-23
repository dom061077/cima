package medfireweb

class BurningImageExtensionTagLib {

	def resourceimgext = { attrs ->
		def bean = attrs.bean
		def tamanio = attrs.size
		if(bi.hasImage(bean:bean)){
			out << "TIENE IMAGEN";
		}else{
			if(tamanio.equals("small"))
				out << g.resource(dir:'images',file:'noDisponible.jpg');
			if(tamanio.equals("large"))	
				out << g.resource(dir:'images',file:'noDisponibleLarge.jpg');
		}
	}
	
}
