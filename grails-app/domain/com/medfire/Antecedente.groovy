package com.medfire

class Antecedente {

	Paciente paciente
	String consulta
	String hipertension
	String coronariopatia
	String dislipidemia
	String asmaBronquial
	String psocopatia
	String alergia
	String tuberculosis
	String atipia
	String gota
	String afeccionBroncopulmonar
	String endicronopatia
	String nefropatia
	String uropatia
	String hemopatia
	String ets
	String ulceraGastroduodenal
	String hepatitis
	String colecistopatia
	String efermedadNeurologica
	String fiebreProlongada
	String colagenopatia
	String antecedenteFamiliar
	
	
	static belongsTo = [paciente:Paciente]
	
	
	
    static constraints = {
		 consulta(nullable:true,blank:true)
		 hipertension(nullable:true,blank:true)
		 coronariopatia(nullable:true,blank:true)
		 dislipidemia(nullable:true,blank:true)
		 asmaBronquial(nullable:true,blank:true)
		 psocopatia(nullable:true,blank:true)
		 alergia(nullable:true,blank:true)
		 tuberculosis(nullable:true,blank:true)
		 atipia(nullable:true,blank:true)
		 gota(nullable:true,blank:true)
		 afeccionBroncopulmonar(nullable:true,blank:true)
		 endicronopatia(nullable:true,blank:true)
		 nefropatia(nullable:true,blank:true)
		 uropatia(nullable:true,blank:true)
		 hemopatia(nullable:true,blank:true)
		 ets(nullable:true,blank:true)
		 ulceraGastroduodenal(nullable:true,blank:true)
		 hepatitis(nullable:true,blank:true)
		 colecistopatia(nullable:true,blank:true)
		 efermedadNeurologica(nullable:true,blank:true)
		 fiebreProlongada(nullable:true,blank:true)
		 colagenopatia(nullable:true,blank:true)
		 antecedenteFamiliar(nullable:true,blank:true)
	
    }
	
	static mapping = {
		antecedenteFamiliar type:"text"
	}
}
