package com.medfire

import com.medfire.enums.IvaEnum
import com.medfire.enums.SexoEnum
import com.medfire.enums.TipoDocumentoEnum
import pl.burningice.plugins.image.ast.DBImageContainer

@DBImageContainer(field = 'photo')
class Profesional {
	
	Integer matricula
	String nombre
	String observaciones
	Long ultimaSecuencia
	String domicilio
	String codigoPostal
	Localidad localidad
	String telefono
	Date fechaNacimiento
	Long numeroDocumento
	TipoDocumentoEnum tipoDocumento //tipo enum
	IvaEnum codigoIva //tipo enum
	String cuit
	Date fechaIngreso
	//User usuarioAsignado
	EspecialidadMedica especialidad
	//foto agregar la foto
	Boolean activo
	String tipoMatricula//tipo enum
	Boolean sexo

    static constraints = {
		matricula(nullable:false,blank:false)
		nombre(nullable:false,blank:false)
		observaciones(nullable:true,blank:true)
		ultimaSecuencia(nullable:true,blank:true)
		domicilio(nullable:true,blank:true)
		codigoPostal(nullable:true,blank:true)
		localidad(nullable:true,blank:true)
		telefono(nullable:true,blank:true)
		fechaNacimiento(nullable:true,blank:true)
		numeroDocumento(nullable:true,blank:true)
		tipoDocumento(nullable:true,blank:true)
		codigoIva(nullable:true,blank:true)
		cuit(nullable:true,blank:true)
		fechaIngreso(nullable:true,blank:true)
		//usuarioAsignado(nullable:true,blank:true)
		especialidad(nullable:true,blank:true)
		activo(nullable:true,blank:true)
		tipoMatricula(nullable:true,blank:true)
		sexo(nullable:true,blank:true)
    }

	void setNombre(String s){
		nombre = s?.toUpperCase()
	}
	void setObservaciones(String s){
		observaciones=s?.toUpperCase()
	}
	
	void setDomicilio(String s){
		domicilio = s?.toUpperCase()
	}

	static mapping = {
		sort nombre:"desc"
	}		
	
}
