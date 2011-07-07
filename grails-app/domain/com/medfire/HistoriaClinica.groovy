package com.medfire

class HistoriaClinica {
	Long numero
	String t1
	String t2
	String t3
	String t4
	String t5
	String t6
	String t7
	String t8
	String t9
	String t10
	String t11
	String t12
	String t13
	String t14
	String t15
	String t16
	String t17
	String t18
	String t19
	String t20
	String t21
	String t22
	String descripcion
	
	
	Paciente paciente	
	
	static hasMany = [consultas:Consulta]
	
	static mapping = {
		consultas cascade : "save-update"
	}
	
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
		 descripcion(nullable:true,blank:true)
		
		 pulso(nullable:true,blank:true)
		 fc(nullable:true,blank:true)
		 ta(nullable:true,blank:true)
		 fr(nullable:true,blank:true)
		 taxilar(nullable:true,blank:true)
		 trectal(nullable:true,blank:true)
		 pesoh(nullable:true,blank:true)
		 pesoa(nullable:true,blank:true)
		 impresion(nullable:true,blank:true)
		 habito(nullable:true,blank:true)
		 actitud(nullable:true,blank:true)
		 ubicacion(nullable:true,blank:true)
		 marcha(nullable:true,blank:true)
		 psiqui(nullable:true,blank:true)
		 facie(nullable:true,blank:true)
	
    }
}
