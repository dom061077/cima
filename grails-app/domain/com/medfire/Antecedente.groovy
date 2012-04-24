package com.medfire

class Antecedente {

	String t1
	boolean t1Check = false
	String t2
	boolean t2Check = false
	String t3
	boolean t3Check = false
	String t4
	boolean t4Check = false
	String t5
	boolean t5Check = false
	String t6
	boolean t6Check = false
	String t7
	boolean t7Check = false
	String t8
	boolean t8Check = false
	String t9
	boolean t9Check = false
	String t10
	boolean t10Check = false
	String t11
	boolean t11Check = false
	String t12
	boolean t12Check = false
	String t13
	boolean t13Check = false
	String t14
	boolean t14Check = false
	String t15
	boolean t15Check = false
	String t16
	boolean t16Check = false
	String t17
	boolean t17Check = false
	String t18
	boolean t18Check = false
	String t19
	boolean t19Check = false
	String t20
	boolean t20Check = false
	String t21
	boolean t21Check = false
	String t22
	boolean t22Check = false
	String antecedenteFamiliar
	Paciente paciente
	Profesional profesional
	

	//cambio de antecedente
	
	
	static belongsTo = [paciente:Paciente]
	
    static constraints = {
		 t1(nullable:true,blank:true)
		 t2(nullable:true,blank:true)
		 t3(nullable:true,blank:true)
		 t4(nullable:true,blank:true)
		 t5(nullable:true,blank:true)
		 t6(nullable:true,blank:true)
		 t7(nullable:true,blank:true)
		 t8(nullable:true,blank:true)
		 t9(nullable:true,blank:true)
		 t10(nullable:true,blank:true)
		 t11(nullable:true,blank:true)
		 t12(nullable:true,blank:true)
		 t13(nullable:true,blank:true)
		 t14(nullable:true,blank:true)
		 t15(nullable:true,blank:true)
		 t16(nullable:true,blank:true)
		 t17(nullable:true,blank:true)
		 t18(nullable:true,blank:true)
		 t19(nullable:true,blank:true)
		 t20(nullable:true,blank:true)
		 t21(nullable:true,blank:true)
		 t22(nullable:true,blank:true)
		 antecedenteFamiliar(nullable:true,blank:true)
		 paciente(unique:'profesional')
	
    }
	
	static mapping = {
		antecedenteFamiliar type:"text"
		
	}
}
