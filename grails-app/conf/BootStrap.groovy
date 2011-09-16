import com.medfire.User
import com.medfire.Role
import com.medfire.Requestmap
import com.medfire.ObraSocial
import com.medfire.Iva
import com.medfire.EstadoCivil
import com.medfire.TipoDocumento
import com.medfire.Provincia
import com.medfire.Localidad
import com.medfire.Paciente
import com.medfire.Profesional
import com.medfire.AntecedenteLabel
import org.apache.commons.io.FileUtils;
import groovy.sql.Sql;
import org.codehaus.groovy.grails.commons.ConfigurationHolder as CH
import com.medfire.util.DataSourceUtils


//http://grails.1312388.n4.nabble.com/MySQL-errors-after-standing-idle-for-a-period-td3328284.html
//http://www.sylvioazevedo.com.br/?p=56
//http://sacharya.com/grails-dbcp-stale-connections/
class BootStrap {
	def authenticateService
    def init = { servletContext ->
		createAdminIfRequired()
		//DataSourceUtils.tune(servletContext)
    }
    def destroy = {
    }
	
	void inicializaTablas(){
		 String sqlFilePath = 'C:\\JAVA\\Medfire\\provincias.sql'
		 String sqlString =  FileUtils.readFileToString(new File(sqlFilePath));
		 def sql = Sql.newInstance(CH.config.dataSource.url, CH.config.dataSource.username, CH.config.dataSource.password, CH.config.dataSource.driverClassName)
		 List<String> lines = FileUtils.readLines (new File(sqlFilePath))
		 lines.each{
			 sql.execute(it)
		 }
		 
		 sqlFilePath = 'C:\\JAVA\\Medfire\\localidades2.sql'
		 sqlString =  FileUtils.readFileToString(new File(sqlFilePath));
		 sql = Sql.newInstance(CH.config.dataSource.url, CH.config.dataSource.username, CH.config.dataSource.password, CH.config.dataSource.driverClassName)
		 lines = FileUtils.readLines (new File(sqlFilePath))
		 lines.each{
			 sql.execute(it)
		 }
 
	}

	
	void createAdminIfRequired(){
		if (!User.findByUsername("admin")){
			def role = new Role(authority:"ROLE_ADMIN",description:"Rol Administrador").save()//usuario adminstrador
			def roleregular = new Role(authority:"ROLE_USER",description:"Rol Secretaria").save()//usuario secretaria
			def roleprofesional = new Role(authority:"ROLE_PROFESIONAL",description:"Rol de Profesionales").save()//usuario profesional médico
			
			new Requestmap(url:"/js/**",configAttribute:"IS_AUTHENTICATED_ANONYMOUSLY").save()
			new Requestmap(url:"/css/**",configAttribute:"IS_AUTHENTICATED_ANONYMOUSLY").save()
			new Requestmap(url:"/image/**",configAttribute:"IS_AUTHENTICATED_ANONYMOUSLY").save()
			new Requestmap(url:"/images/**",configAttribute:"IS_AUTHENTICATED_ANONYMOUSLY").save()
			new Requestmap(url:"/plugins/**",configAttribute:"IS_AUTHENTICATED_ANONYMOUSLY").save()
			new Requestmap(url:"/**",configAttribute:"IS_AUTHENTICATED_REMEMBERED").save()
			
			new Requestmap(url:"/user/**",configAttribute:"ROLE_ADMIN").save()
			new Requestmap(url:"/role/**",configAttribute:"ROLE_ADMIN").save()
			new Requestmap(url:"/requestmap/**",configAttribute:"ROLE_ADMIN").save()
			new Requestmap(url:"/paciente/**",configAttribute:"ROLE_ADMIN,ROLE_USER,ROLE_PROFESIONAL").save()
			new Requestmap(url:"/paciente/listjson",configAttribute:"ROLE_ADMIN,ROLE_USER,ROLE_PROFESIONAL").save()
			new Requestmap(url:"/paciente/listjsonautocomplete",configAttribute:"ROLE_ADMIN,ROLE_USER,ROLE_PROFESIONAL").save()
			
			new Requestmap(url:"/login/**",configAttribute:"IS_AUTHENTICATED_ANONYMOUSLY").save()
			new Requestmap(url:"/event/**",configAttribute:"ROLE_USER,ROLE_ADMIN").save()
			new Requestmap(url:"/event/atenciondeldia",configAttribute:"ROLE_USER,ROLE_ADMIN,ROLE_PROFESIONAL").save()
			new Requestmap(url:"/event/operation",configAttribute:"ROLE_USER,ROLE_ADMIN,ROLE_PROFESIONAL").save()
			new Requestmap(url:"/event/updateestado",configAttribute:"ROLE_ADMIN,ROLE_PROFESIONAL").save()
			new Requestmap(url:"/event/editpaciente/**",configAttribute:"ROLE_ADMIN,ROLE_PROFESIONAL").save()
			new Requestmap(url:"/obraSocial/listjsonautocomplete",configAttribute:"ROLE_ADMIN,ROLE_USER,ROLE_PROFESIONAL").save()
			new Requestmap(url:"/obraSocial/listsearchjson",configAttribute:"ROLE_ADMIN,ROLE_USER,ROLE_PROFESIONAL").save()
			new Requestmap(url:"/vademecum/list",configAttribute:"ROLE_ADMIN,ROLE_USER,ROLE_PROFESIONAL").save()
			new Requestmap(url:"/antecedenteLabel/**",configAttribute:"ROLE_ADMIN,ROLE_PROFESIONAL").save()
			new Requestmap(url:"/principioActivo/listjson",configuraAttribute:"ROLE_ADMIN,ROLE_USER,ROLE_PROFESIONAL").save()
			new Requestmap(url:"/grupoTerapeutico/listjson",configuraAttribute:"ROLE_ADMIN,ROLE_USER,ROLE_PROFESIONAL").save()
			new Requestmap(url:"/laboratorio/listjson",configuraAttribute:"ROLE_ADMIN,ROLE_USER,ROLE_PROFESIONAL").save()
			
			new Requestmap(url:"/obraSocial/**",configAttribute:"ROLE_USER,ROLE_ADMIN").save()
			
			
			
			new Requestmap(url:"/profesional/**",configAttribute:"ROLE_ADMIN").save()
			
			def userprof=new User(username:"zalazar",passwd: authenticateService.encodePassword('zalazar')
				,email:"zazalar@yahoo.com.ar",enabled:true,userRealName:"Administrador gral.",esProfesional:false).save()
			
			def user = new User(username:"admin",passwd: authenticateService.encodePassword('admin')
				,email:"dom061077@yahoo.com.ar",enabled:true,userRealName:"Administrador gral.",esProfesional:false).save()
			
			def prof = new Profesional(nombre:"RUBEN ZALAZAR",matricula:new Integer(22334),antecedenteLabel:new AntecedenteLabel(t1:"Consulta")).save(failOnError:true)
				
			user.addToMedicos(prof)
			roleprofesional.addToPeople(userprof)
							
			userprof = new User(username:"ortega",passwd: authenticateService.encodePassword("ortega")
				,email:"medico@noexiste.com.ar",enabled:true,userRealName:"ORTEGA ANDRES DAVID",esProfesional:false).save()
			
			prof=new Profesional(nombre:"DANIEL GONZALEZ",matricula:new Integer(77689),antecedenteLabel:new AntecedenteLabel(t1Label:"Consulta")).save(failOnError:true)	
					 	
			user.addToMedicos(prof)
			roleprofesional.addToPeople(userprof)
					
			userprof = new User(username:"skiby",passwd: authenticateService.encodePassword("skiby")
				,email:"medico@noexiste.com.ar",enabled:true,userRealName:"JORGE SKIBY",esProfesional:false).save()
			prof=new Profesional(nombre:"skiby",matricula:new Integer(9999),antecedenteLabel:new AntecedenteLabel(t1Label:"Consulta")).save()		
			user.addToMedicos(prof)
			roleprofesional.addToPeople(userprof)
						
			role.addToPeople(user)
			
			def obsocial=new ObraSocial(descripcion:"Prensa",razonSocial:"Prensa S.R.L",domicilio:"junin 750",codigoPostal:"4000"
				,telefono:"381-4228823",contacto:"NO TIENE",cuit:"20-23456890-9",habilitada:new Boolean(true)).save()
			new ObraSocial(descripcion:"Subsidio de Salud",razonSocial:"IPSST DE TUCUMAN",domicilio:"junin 750",codigoPostal:"4000"
					,telefono:"381-4228823",contacto:"NO TIENE",cuit:"20-23456890-9",habilitada:new Boolean(true)).save()
	
			
			def provtucuman = new Provincia(nombre:"TUCUMAN").save()
			def provsalta = new Provincia(nombre:"SALTA").save()
			
			def loc=new Localidad(nombre:"SAN MIGUEL DE TUCUMAN",provincia:provtucuman,codigoPostal:4000).save()
			new Localidad(nombre:"TAFI VIEJO",provincia:provtucuman,codigoPostal:4000).save()
			new Localidad(nombre:"SALTA CAPITAL",provincia:provsalta,codigoPostal:5000).save()
			new Localidad(nombre:"ORAN",provincia:provsalta,codigoPostal:5000).save()

			new Paciente(apellido:"POMO",nombre:"CRISTIAN MIGUEL",obraSocial:obsocial,localidad:loc).save()
			new Paciente(apellido:"BRIGA",nombre:"ANGEL ANDRES",obraSocial:obsocial,localidad:loc).save()
			new Paciente(apellido:"JUAREZ",nombre:"LUIS GUSTAVO",obraSocial:obsocial,localidad:loc).save()
			new Paciente(apellido:"VALDEZ",nombre:"raul armando",obraSocial:obsocial,localidad:loc).save()
			new Paciente(apellido:"aguilar",nombre:"jose luis",obraSocial:obsocial,localidad:loc).save()
			
						
			//inicializaTablas()
		}
	}	
}
