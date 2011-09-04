package com.medfire

class Antecedente {

	Paciente paciente
	String t1
	boolean t1Check
	String t2
	boolean t2Check
	String t3
	boolean t3Check
	String t4
	boolean t4Check
	String t5
	boolean t5Check
	String t6
	boolean t6Check
	String t7
	boolean t7Check
	String t8
	boolean t8Check
	String t9
	boolean t9Check
	String t10
	boolean t10Check
	String t11
	boolean t11Check
	String t12
	boolean t12Check
	String t13
	boolean t13Check
	String t14
	boolean t14Check
	String t15
	boolean t15Check
	String t16
	boolean t16Check
	String t17
	boolean t17Check
	String t18
	boolean t18Check
	String t19
	boolean t19Check
	String t20
	boolean t20Check
	String t21
	boolean t21Check
	String t22
	boolean t22Check
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
		 enfermedadNeurologica(nullable:true,blank:true)
		 fiebreProlongada(nullable:true,blank:true)
		 colagenopatia(nullable:true,blank:true)
		 antecedenteFamiliar(nullable:true,blank:true)
	
    }
	
	static mapping = {
		antecedenteFamiliar type:"text"
	}
}
