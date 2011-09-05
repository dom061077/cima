package medfireweb



class ProfesionallabelTagLib {
	def authenticateService
	
	def antecedenteLabel = { attrs ->
		/*def writer = getOut()
		def fieldLabel = attrs.field
		def value = authenticateService.userDomain()?.profesionalAsignado?.antecedenteLabel?."${fieldLabel}"
		//def bean =  authenticateService.userDomain()?.profesionalAsignado?.antecedenteLabel
		//URLEncoder.encode(obj.toString(), URLCodec.getEncoding()) 
		writer << URLEncoder.encode(value, URLCodec.getEncoding())
		*/
		def fieldLabel = attrs.field
		def value = authenticateService.userDomain()?.profesionalAsignado?.antecedenteLabel?."${fieldLabel}"
		out << response.encodeURL(value)
	}
}
