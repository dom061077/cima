package com.medfire

class IndicecorporalController {

    def index = { 
		log.info "INGRESANDO AL CLOSURE index"
		log.info "PARAMETROS: $params"	
		
	}
	
	
	
	def calcular = {
		log.info "INGRESANDO AL CLOSURE calcular"
		log.info "PARAMETROS: $params"
		if(!params.estatura){
			flash.error = "Ingrese una estatura mayor a CERO"
			render(view:"index",model:[estatura:params.estatura,peso:params.peso])
			return
		}
		if(!params.peso){
			flash.error = "Ingrese un peso mayor a CERO"
			render(view:"index")
			return
		}	
		
		def a = params.estatura.toInteger()/100
		def b = params.peso.toInteger()
		def r = b/(a*a)
		log.debug "INDICE: $r"
		def leyenda=""
		r = Math.round(r*Math.pow(10,1))/Math.pow(10,1)
		
		if(r<=20){
			leyenda="Usted está bajo de peso"
		}else{
			if(r>20 && r<=25){
				leyenda="Su peso es normal"
			}else{
				if(r>25 && r<=30){
					leyenda="Usted tiene sobrepeso"
				}else{
					if(r>30 && r<=40)
						leyenda="Usted está obeso"
					else
						leyenda="Usted sufre de obesidad severa"
				}
			}
		}	
		flash.error= null
		render(view:"index",model:[indice:r,leyenda:leyenda,estatura:params.estatura,peso:params.peso])
	}
	
}
