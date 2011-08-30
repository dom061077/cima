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
    }
	
	static mapping = {
		antecedenteFamiliar type:"text"
	}
}
