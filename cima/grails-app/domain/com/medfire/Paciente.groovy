package com.medfire

import com.medfire.enums.*

class Paciente {
	static auditable = true
	
	Long dni
	String apellido
	String nombre
	String domicilio
	String codigoPostal
	String telefono
	Date fechaNacimiento
	String cuit
	String ocupacion
	String medicoEnviante
	String email
	Boolean sexo
	Long numeroAfiliado
	Localidad localidad
	EstadoCivilEnum estadoCivil
	TipoDocumentoEnum tipoDocumento
	IvaEnum estadoIva
	ObraSocial obraSocial
	
	Institucion institucion
	
	
	//static belongsTo = [obrasocial:ObraSocial,estadocivil:EstadoCivil,localidad:Localidad,tipodocumento:TipoDocumento]
	
	static hasMany = [consultas:Consulta,antecedentes:Antecedente]
	
	def beforeInsert = {
		apellido = apellido?.toUpperCase()
		nombre = nombre?.toUpperCase()
		ocupacion = ocupacion?.toUpperCase()
		medicoEnviante = medicoEnviante?.toUpperCase()
	}
	
	def beforeUpdate = {
		apellido = apellido?.toUpperCase()
		nombre = nombre?.toUpperCase()
		ocupacion = ocupacion?.toUpperCase()
		domicilio = domicilio?.toUpperCase()
		medicoEnviante = medicoEnviante?.toUpperCase()
	}
	
    static constraints = {
		apellido(blank:false,nullable:false)
		nombre(blank:false,nullable:false)
		telefono(blank:true,nullable:true)
		email(email:true,blank:true,nullable:true)
		dni(unique:true,blank:true,nullable:true)
		cuit(blank:true,nullable:true)
		cuit(matches:'[0-9]{2}-[0-9]{8}-[0-9]{1}')
		domicilio(blank:true,nullable:true)
		estadoCivil(blank:true,nullable:true)
		medicoEnviante(blank:true,nullable:true)
		numeroAfiliado(blank:true,nullable:true)
		codigoPostal(blank:true,nullable:true)
		fechaNacimiento(blank:true,nullable:true)
		sexo(blank:true,nullable:true)
		ocupacion(blank:true,nullable:true)
		tipoDocumento(blank:true,nullable:true)
		estadoIva(blank:true,nullable:true)
		//telefono(matches:'[0-9]{3}-[0-9]{7}')
		obraSocial(blank:true,nullable:true)
		localidad(blank:true,nullable:true)
		//antecedente(nullable:true)
		/*dateOfBirth(blank:false, validator: { val, obj ->
			// Ensure that the date of birth is before the enrollment date
			return ! val.after(obj.enrollmentDate)
		}))*/
		antecedentes validator : {
			it?.every { it?.validate()}
		}
		institucion(nullable:false,blank:false)
    }
	
	static mapping = {
		obraSocial lazy:false
		antecedentes lazy:false
	}
}
