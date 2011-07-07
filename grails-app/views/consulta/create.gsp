

<%@ page import="com.medfire.Consulta" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'consulta.label', default: 'Consulta')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
        <fckeditor:config CustomConfigurationsPath="${resource(dir:'js/jquery',file:'fckeditconfig.js')}"/>
      	<script type="text/javascript" src="${resource(dir:'js/jquery',file:'jquery.jlookupfield.js')}"></script>
        <script type="text/javascript">
        	var loccie10search = "<%out << g.createLink(controller:'cie10',action:'listsearchjson');%>";
        	var loccie10autocomplete = "<%out << g.createLink(controller:'cie10',action:'listautocompletejson');%>";        	
        </script>
      	<script type="text/javascript" src="${resource(dir:'js/script/consulta',file:'create.js')}"></script>
      	
        
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.create.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${consultaInstance}">
            <div class="ui-state-error ui-corner-all" style="padding: 0pt 0.7em;">
                <g:renderErrors bean="${consultaInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" >
           		<div>
           			Historia Clínica: Sin Número
           			<br/>
           			Paciente: ${pacienteNombre}
           		</div>
            
            	<div id="tabs">
            		<ul>
            			<li><a href="#tabs-1">Consulta </a></li>
            			<li><a href="#tabs-2">Examen Fisico </a></li>
            			<li><a href="#tabs-3">Estudios Complementarios</a></li>
            			<li><a href="#tabs-4">Prescripciones</a></li>            			
            		</ul>
            		<div id="tabs-1">
            			<fieldset>
   							<label for="cie10Descripcion">CIE-10:</label>
   							<g:textField id="cie10DescripcionId" class="ui-widget ui-corner-all ui-widget-content" name="cie10Descripcion" value="${consultaInstance?.cie10?.descripcion}"/><br/>
   							<g:hiddenField id="cie10Id" name="cie10.id" value="${consultaInstance?.cie10?.id}"/>
   							<label for="fechaConsulta">Fecha de Consulta:</label>
   							<g:textField class="ui-widget ui-corner-all ui-widget-content" id="fechaConsultaId" name="fechaConsulta" value="${g.formatDate(format:'dd/MM/yyyy',date:consultaInstance?.fechaConsulta)}"/><br/>
   							
							<fckeditor:editor name="text" width="100%" height="400" toolbar="ed-limited" fileBrowser="default" value="">
							</fckeditor:editor>            			
            			</fieldset>
            		</div>
            		<div id="tabs-2">
            			<fieldset>
            				<legend>Signos Vitales</legend>
            					<table>
            						<tr>
            							<td>
										<label for=""><g:message code="historia.pulso.label" default="Pulso:" /></label>
										<g:textField class="ui-widget ui-corner-all ui-widget-content" id="pulsoId" name="historiaClinica.pulso"></g:textField>
			                                		<g:hasErrors bean="${consultaInstance.historiaClinica}" field="historiaClinica.pulso">
			                                			<br/>
				                                    	<div class="ui-state-error ui-corner-all"><g:renderErrors bean="${consultaInstance.historiaClinica}" as="list" field="pulso"/></div>
			                                    	</g:hasErrors>
		                                <br/>
										<label for=""><g:message code="historia.fc.label" default="FC:" /></label>
										<g:textField class="ui-widget ui-corner-all ui-widget-content" id="fcId" name="historiaClinica.fc"></g:textField>
			                                		<g:hasErrors bean="${consultaInstance.historiaClinica}" field="historiaClinica.fc">
			                                			<br/>
				                                    	<div class="ui-state-error ui-corner-all"><g:renderErrors bean="${consultaInstance.historiaClinica}" as="list" field="fc"/></div>
			                                    	</g:hasErrors>
		                                <br/>
		
										<label for=""><g:message code="historia.ta.label" default="TA:" /></label>
										<g:textField class="ui-widget ui-corner-all ui-widget-content" id="taId" name="historiaClinica.ta"></g:textField>
			                                		<g:hasErrors bean="${consultaInstance.historiaClinica}" field="historiaClinica.ta">
			                                			<br/>
				                                    	<div class="ui-state-error ui-corner-all"><g:renderErrors bean="${consultaInstance.historiaClinica}" as="list" field="ta"/></div>
			                                    	</g:hasErrors>
		                                <br/>
		
										<label for=""><g:message code="historia.fr.label" default="FR:" /></label>
										<g:textField class="ui-widget ui-corner-all ui-widget-content" id="frId" name="historiaClinica.fr"></g:textField>
			                                		<g:hasErrors bean="${consultaInstance.historiaClinica}" field="historiaClinica.fr">
			                                			<br/>
				                                    	<div class="ui-state-error ui-corner-all"><g:renderErrors bean="${consultaInstance.historiaClinica}" as="list" field="historiaClinica.fr"/></div>
			                                    	</g:hasErrors>
		                                <br/>
                                		</td>
                                		<td>  	
										<label for=""><g:message code="historia.taxilar.label" default="T.Axilar:" /></label>
										<g:textField class="ui-widget ui-corner-all ui-widget-content" id="taxilarId" name="historiaClinica.taxilar"></g:textField>
			                                		<g:hasErrors bean="${consultaInstance.historiaClinica}" field="historiaClinica.taxilar">
			                                			<br/>
				                                    	<div class="ui-state-error ui-corner-all"><g:renderErrors bean="${consultaInstance.historiaClinica}" as="list" field="historiaClinica.taxilar"/></div>
			                                    	</g:hasErrors>
		                                <br/>
		                                
										<label for=""><g:message code="historia.trectal.label" default="T.Rectal:" /></label>
										<g:textField class="ui-widget ui-corner-all ui-widget-content" id="trectalId" name="historiaClinica.trectal"></g:textField>
			                                		<g:hasErrors bean="${consultaInstance.historiaClinica}" field="historiaClinica.trectal">
			                                			<br/>
				                                    	<div class="ui-state-error ui-corner-all"><g:renderErrors bean="${consultaInstance.historiaClinica}" as="list" field="historiaClinica.trectal"/></div>
			                                    	</g:hasErrors>
		                                <br/>
		                                          
										<label for=""><g:message code="historia.pesoh.label" default="Peso habitual:" /></label>
										<g:textField class="ui-widget ui-corner-all ui-widget-content" id="pesohId" name="historiaClinica.pesoh"></g:textField>
			                                		<g:hasErrors bean="${consultaInstance.historiaClinica}" field="historiaClinica.pesoh">
			                                			<br/>
				                                    	<div class="ui-state-error ui-corner-all"><g:renderErrors bean="${consultaInstance.historiaClinica}" as="list" field="historiaClinica.pesoh"/></div>
			                                    	</g:hasErrors>
		                                <br/>
		                                
										<label for=""><g:message code="historia.pesoa.label" default="Peso actual:" /></label>
										<g:textField class="ui-widget ui-corner-all ui-widget-content" id="pesoaId" name="historiaClinica.pesoa"></g:textField>
			                                		<g:hasErrors bean="${consultaInstance.historiaClinica}" field="historiaClinica.pesoa">
			                                			<br/>
				                                    	<div class="ui-state-error ui-corner-all"><g:renderErrors bean="${consultaInstance.historiaClinica}" as="list" field="historiaClinica.pesoa"/></div>
			                                    	</g:hasErrors>
		                                <br/>
		                                
		                                </td>
                                	</tr>          
                                </table>          
                 		</fieldset>
                 		<fieldset>
                 			<legend>Otros datos</legend>
							<label for="impresion"><g:message code="historia.impresion.label" default="Impresión:" /></label>
							<g:textArea class="ui-widget ui-corner-all ui-widget-content" id="impresionId" name="historiaClinica.impresion"></g:textArea>
                                		<g:hasErrors bean="${consultaInstance.historiaClinica}" field="historiaClinica.impresion">
                                			<br/>
	                                    	<div class="ui-state-error ui-corner-all"><g:renderErrors bean="${consultaInstance.historiaClinica}" as="list" field="historiaClinica.impresion"/></div>
                                    	</g:hasErrors>
                               <br/>
                            <table>
                            	<tr>
                            		<td>
										<label for=""><g:message code="historia.habito.label" default="Hábito:" /></label>
										<g:textField class="ui-widget ui-corner-all ui-widget-content" id="habitoId" name="historiaClinica.habito"></g:textField>
			                                		<g:hasErrors bean="${consultaInstance.historiaClinica}" field="historiaClinica.habito">
			                                			<br/>
				                                    	<div class="ui-state-error ui-corner-all"><g:renderErrors bean="${consultaInstance.historiaClinica}" as="list" field="historiaClinica.habito"/></div>
			                                    	</g:hasErrors>
		                                <br/>
		                                
										<label for=""><g:message code="historia.actitud.label" default="Actitud:" /></label>
										<g:textField class="ui-widget ui-corner-all ui-widget-content" id="atitudId" name="historiaClinica.actitud"></g:textField>
			                                		<g:hasErrors bean="${consultaInstance.historiaClinica}" field="historiaClinica.actitud">
			                                			<br/>
				                                    	<div class="ui-state-error ui-corner-all"><g:renderErrors bean="${consultaInstance.historiaClinica}" as="list" field="historiaClinica.actitud"/></div>
			                                    	</g:hasErrors>
		                                <br/>                            			

										<label for=""><g:message code="historia.ubicacion.label" default="Ubicación:" /></label>
										<g:textField class="ui-widget ui-corner-all ui-widget-content" id="ubicacionId" name="historiaClinica.ubicacion"></g:textField>
			                                		<g:hasErrors bean="${consultaInstance.historiaClinica}" field="historiaClinica.ubicacion">
			                                			<br/>
				                                    	<div class="ui-state-error ui-corner-all"><g:renderErrors bean="${consultaInstance.historiaClinica}" as="list" field="historiaClinica.ubicacion"/></div>
			                                    	</g:hasErrors>
		                                <br/>                            			
		                                                            			
                            		</td>
                            		<td>
										<label for=""><g:message code="historia.marcha.label" default="Marcha:" /></label>
										<g:textField class="ui-widget ui-corner-all ui-widget-content" id="marchaId" name="historiaClinica.marcha"></g:textField>
			                                		<g:hasErrors bean="${consultaInstance.historiaClinica}" field="historiaClinica.marcha">
			                                			<br/>
				                                    	<div class="ui-state-error ui-corner-all"><g:renderErrors bean="${consultaInstance.historiaClinica}" as="list" field="historiaClinica.marcha"/></div>
			                                    	</g:hasErrors>
		                                <br/>  
		                                                          			
										<label for=""><g:message code="historia.psiqui.label" default="Psiquismo:" /></label>
										<g:textField class="ui-widget ui-corner-all ui-widget-content" id="psiquiId" name="historiaClinica.psiqui"></g:textField>
			                                		<g:hasErrors bean="${consultaInstance.historiaClinica}" field="historiaClinica.psiqui">
			                                			<br/>
				                                    	<div class="ui-state-error ui-corner-all"><g:renderErrors bean="${consultaInstance.historiaClinica}" as="list" field="historiaClinica.psiqui"/></div>
			                                    	</g:hasErrors>
		                                <br/>                            			

										<label for=""><g:message code="historia.facie.label" default="Facie:" /></label>
										<g:textField class="ui-widget ui-corner-all ui-widget-content" id="facieId" name="historiaClinica.facie"></g:textField>
			                                		<g:hasErrors bean="${consultaInstance.historiaClinica}" field="historiaClinica.facie">
			                                			<br/>
				                                    	<div class="ui-state-error ui-corner-all"><g:renderErrors bean="${consultaInstance.historiaClinica}" as="list" field="historiaClinica.facie"/></div>
			                                    	</g:hasErrors>
		                                <br/>                            			
                            		
                            		</td>
                            	</tr>
                            </table>   
                 			
                 		</fieldset>
            		</div>
            		<div id="tabs-3">
            			<fieldset>
            			
            			</fieldset>
            		</div>
            		<div id="tabs-4">
            			<fieldset>
            			
            			</fieldset>
            		</div>
            	
            	</div>
            	
            
                
                <div class="buttons">
                    <span class="button"><g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
