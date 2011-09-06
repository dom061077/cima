package medfireweb

import com.medfire.User;


class ProfesionallabelTagLib {
	def authenticateService
	
	def antecedenteLabel = { attrs ->
		def fieldLabel = attrs.field
		def userInstance = User.get(authenticateService.userDomain().id)
		def value = userInstance.profesionalAsignado?.antecedenteLabel?."${fieldLabel}"
		if(value){
			if(attrs.url)
				out << response.encodeURL('/'+value)
			else	
				out << response.encodeURL(value)
		}
		
 	 
	}
}
