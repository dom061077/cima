package medfireweb

class ProfesionallabelTagLib {
	def authenticateService
	
	def antecedenteLabel = { attrs ->
		def fieldLabel = attrs.label
		String label = authenticateService.userDomain()?.profesionalAsignado?.antecedenteLabel?."${fieldLabel}"
		out <<  label
	}
}
