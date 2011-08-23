
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'historiaClinica.label', default: 'HistoriaClinica')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
<!--        <fckeditor:config CustomConfigurationsPath="${resource(dir:'js/jquery',file:'fckeditconfig.js')}"/>-->
        <script type="text/javascript" src="${resource(dir:'js/script',file:'jquicombobox.js')}"></script>        
      	<script type="text/javascript" src="${resource(dir:'js/jquery',file:'jquery.jlookupfield.js')}"></script>
        <script type="text/javascript" src="${resource(dir:'js/editor',file:'ckeditor.js')}"></script>
        <script type="text/javascript">
        	var loccie10search = "<%out << g.createLink(controller:'cie10',action:'listsearchjson');%>";
        	var loccie10autocomplete = "<%out << g.createLink(controller:'cie10',action:'listautocompletejson');%>";
        	var locvademec = '<%out << g.createLink(controller:'vademecum',action:'listjson')%>';
        	$(document).ready(
                	function(){
                		$("#estadoId").combobox() ;
                    }

            );
        </script>
        
      	<script type="text/javascript" src="${resource(dir:'js/script/historia',file:'create.js')}"></script>
    </head>
    <body>
		<div style="display:none" id="busquedaVademecumDialogId">
			<table id="tablaBusquedaVademecumId"></table><div id="pagerBusquedaVademecumId"></div>
		</div>

    	
    	<div id="pagerPrescripciones">	</div>
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
            
            <g:hasErrors bean="${pacienteInstance}">
            <div class="ui-state-error ui-corner-all" style="padding: 0pt 0.7em;">
                <g:renderErrors bean="${pacienteInstance}" as="list" />
            </div>
            </g:hasErrors>
            
            
            <g:form action="save" enctype="multipart/form-data" >
           		<div>
           			Historia Clínica: <g:formatNumber number="${pacienteInstance.id}" format="000000" />
           			<br/>
           			Paciente: ${pacienteInstance?.apellido+" - "+pacienteInstance?.nombre}
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
            				<g:hiddenField name="pacienteId" value="${pacienteInstance?.id}"></g:hiddenField>
            				<div class="span-5 colborder">
            						
		   							<label style="float:left;" for="cie10Descripcion">CIE-10:</label>
		   							<input style="float:left" type="text" id="cie10DescripcionId" class="ui-widget ui-corner-all ui-widget-content" name="consulta.cie10Descripcion" value="${consultaInstance?.cie10?.descripcion}"/>
		   							<g:hiddenField id="cie10Id" name="consulta.cie10.id" value="${consultaInstance?.cie10?.id}"/>
   							</div>
   							
   							<div class="span-7 colborder">
		  							<label for="consulta.fechaConsulta">Fecha de Consulta:</label>
		  							<g:textField class="ui-widget ui-corner-all ui-widget-content" id="fechaConsultaId" name="consulta.fechaConsulta" value="${g.formatDate(format:'dd/MM/yyyy',date:consultaInstance?.fechaConsulta)}"/>
   							</div>
   							
   							<div class="span-6 colborder">
		   							<label for="consulta.estado">Estado:</label>
		   							<g:select id="estadoId" name="consulta.estado" from="${com.medfire.enums.EstadoConsultaEnum.list()}" optionValue="name" value="${consultaInstance?.estado}"></g:select>
   							</div>
   							<div class="clearfix">
   							<div class="span-20 colborder" >
								<textArea class="ckeditor" id="contenidoId">
									${consultaInstance?.contenido}
								</textArea>
							</div>
							</div>            			
            			</fieldset>
            			</fieldset>
            		</div>
            		<div id="tabs-2">
            			<fieldset>
            				<legend>Signos Vitales</legend>
            							<div class="span-3">
										<label for=""><g:message code="historia.pulso.label" default="Pulso:" /></label>
										</div>
										<div class="span-4">
										<g:textField class="ui-widget ui-corner-all ui-widget-content" id="pulsoId" name="consulta.pulso" value="${consultaInstace?.pulso}"></g:textField>
			                                		<g:hasErrors bean="${consultaInstance}" field="pulso">
				                                    	<div class="ui-state-error ui-corner-all"><g:renderErrors bean="${consultaInstance}" as="list" field="pulso"/></div>
			                                    	</g:hasErrors>
			                            </div>        	
			                            
			                            <div class="span-3">
										<label for=""><g:message code="historia.fc.label" default="FC:" /></label>
										</div>
										<div class="span-4">
										<g:textField class="ui-widget ui-corner-all ui-widget-content" id="fcId" name="consulta.fc" value="${consultaInstace?.fc}"></g:textField>
			                                		<g:hasErrors bean="${consultaInstance}" field="fc">
			                                			<br/>
				                                    	<div class="ui-state-error ui-corner-all"><g:renderErrors bean="${consultaInstance}" as="list" field="fc"/></div>
			                                    	</g:hasErrors>
			                            </div>

			                            <div class="clearfix"></div>        
			                                    	
										<div class="span-3">
										<label for=""><g:message code="historia.ta.label" default="TA:" /></label>
										</div>
										<div class="span-4">
										<g:textField class="ui-widget ui-corner-all ui-widget-content" id="taId" name="consulta.ta" value="${consultaInstace?.ta}"></g:textField>
			                                		<g:hasErrors bean="${consultaInstance}" field="ta">
			                                			<br/>
				                                    	<div class="ui-state-error ui-corner-all"><g:renderErrors bean="${consultaInstance}" as="list" field="ta"/></div>
			                                    	</g:hasErrors>
										</div>
										
										<div class="span-3">
										<label for=""><g:message code="historia.fr.label" default="FR:" /></label>
										</div>
										<div class="span-4 ">
										<g:textField class="ui-widget ui-corner-all ui-widget-content" id="frId" name="consulta.fr" value="${consultaInstace?.fr}"></g:textField>
			                                		<g:hasErrors bean="${consultaInstance}" field="fr">
			                                			<br/>
				                                    	<div class="ui-state-error ui-corner-all"><g:renderErrors bean="${consultaInstance}" as="list" field="fr"/></div>
			                                    	</g:hasErrors>
										</div>
										
										<div class="clearfix"></div>
										
										<div class="span-3">
										<label for=""><g:message code="historia.taxilar.label" default="T.Axilar:" /></label>
										</div>
										<div class="span-4">
										<g:textField class="ui-widget ui-corner-all ui-widget-content" id="taxilarId" name="consulta.taxilar" value="${consultaInstace?.taxilar}"></g:textField>
			                                		<g:hasErrors bean="${consultaInstance}" field="taxilar">
			                                			<br/>
				                                    	<div class="ui-state-error ui-corner-all"><g:renderErrors bean="${consultaInstance}" as="list" field="taxilar"/></div>
			                                    	</g:hasErrors>
		                                </div>
		                                
		                                <div class="span-3">
										<label for=""><g:message code="historia.trectal.label" default="T.Rectal:" /></label>
										</div>
										<div class="span-4 ">
										<g:textField class="ui-widget ui-corner-all ui-widget-content" id="trectalId" name="consulta.trectal" value="${consultaInstace?.trectal}"></g:textField>
			                                		<g:hasErrors bean="${consultaInstance}" field="trectal">
			                                			<br/>
				                                    	<div class="ui-state-error ui-corner-all"><g:renderErrors bean="${consultaInstance}" as="list" field="trectal"/></div>
			                                    	</g:hasErrors>
		                                </div>    
		                                
		                                <div class="clearfix"></div>      
		                                
		                                <div class="span-3">          
										<label for=""><g:message code="historia.pesoh.label" default="Peso habitual:" /></label>
										</div>
										<div class="span-4">
										<g:textField class="ui-widget ui-corner-all ui-widget-content" id="pesohId" name="consulta.pesoh" value="${consultaInstace?.pesoh}"></g:textField>
			                                		<g:hasErrors bean="${consultaInstance}" field="pesoh">
			                                			<br/>
				                                    	<div class="ui-state-error ui-corner-all"><g:renderErrors bean="${consultaInstance}" as="list" field="pesoh"/></div>
			                                    	</g:hasErrors>
		                                </div>
		                                
		                                <div class="span-3">
										<label for=""><g:message code="historia.pesoa.label" default="Peso actual:" /></label>
										</div>
										<div class="span-4">
										<g:textField class="ui-widget ui-corner-all ui-widget-content" id="pesoaId" name="consulta.pesoa" value="${consultaInstace?.pesoa}"></g:textField>
			                                		<g:hasErrors bean="${consultaInstance}" field="pesoa">
			                                			<br/>
				                                    	<div class="ui-state-error ui-corner-all"><g:renderErrors bean="${consultaInstance}" as="list" field="pesoa"/></div>
			                                    	</g:hasErrors>
			                            </div>         	
										<div class="clearfix"></div>
										<div class="span-20 colborder">
											
										</div>
										
                 		</fieldset>
                 		<fieldset>
                 			<legend>Otros datos</legend>
                 			<div class="span-3">
							<label for="impresion"><g:message code="historia.impresion.label" default="Impresión:" /></label>
							</div>
							<div class="span-5 colborder">
							<g:textArea class="ui-widget ui-corner-all ui-widget-content" id="impresionId" name="consulta.impresion" value="${consultaInstace?.impresion}"></g:textArea>
                                		<g:hasErrors bean="${consultaInstance}" field="impresion">
                                			<br/>
	                                    	<div class="ui-state-error ui-corner-all"><g:renderErrors bean="${consultaInstance}" as="list" field="impresion"/></div>
                                    	</g:hasErrors>
                            </div>
                            <div class="clearfix"></div>
                            
                            <div class="span-3">        	
							<label for=""><g:message code="historia.habito.label" default="Hábito:" /></label>
							</div>
							<div class="span-4">
							<g:textField class="ui-widget ui-corner-all ui-widget-content" id="habitoId" name="consulta.habito" value="${consultaInstace?.habito}"></g:textField>
                                		<g:hasErrors bean="${consultaInstance}" field="habito">
                                			<br/>
	                                    	<div class="ui-state-error ui-corner-all"><g:renderErrors bean="${consultaInstance}" as="list" field="habito"/></div>
                                    	</g:hasErrors>
                            </div>
                            
                            <div class="span-3">   
							<label for=""><g:message code="historia.actitud.label" default="Actitud:" /></label>
							</div>
							<div class="span-4">
							<g:textField class="ui-widget ui-corner-all ui-widget-content" id="atitudId" name="consulta.actitud" value="${consultaInstace?.actitud}"></g:textField>
                                		<g:hasErrors bean="${consultaInstance}" field="actitud">
                                			<br/>
	                                    	<div class="ui-state-error ui-corner-all"><g:renderErrors bean="${consultaInstance}" as="list" field="actitud"/></div>
                                    	</g:hasErrors>
                            </div>
                            
                            <div class="clearfix"></div>

							<div class="span-3">
							<label for=""><g:message code="historia.ubicacion.label" default="Ubicación:" /></label>
							</div>
							<div class="span-4">
							<g:textField class="ui-widget ui-corner-all ui-widget-content" id="ubicacionId" name="consulta.ubicacion" value="${consultaInstace?.ubicacion}"></g:textField>
                                		<g:hasErrors bean="${consultaInstance}" field="ubicacion">
                                			<br/>
	                                    	<div class="ui-state-error ui-corner-all"><g:renderErrors bean="${consultaInstance}" as="list" field="ubicacion"/></div>
                                    	</g:hasErrors>
                            </div>
                            <div class="span-3">        	
							<label for=""><g:message code="historia.marcha.label" default="Marcha:" /></label>
							</div>
							<div class="span-4">
							<g:textField class="ui-widget ui-corner-all ui-widget-content" id="marchaId" name="consulta.marcha" value="${consultaInstace?.marcha}"></g:textField>
                                		<g:hasErrors bean="${consultaInstance}" field="marcha">
                                			<br/>
	                                    	<div class="ui-state-error ui-corner-all"><g:renderErrors bean="${consultaInstance}" as="list" field="marcha"/></div>
                                    	</g:hasErrors>
                            </div>        	
                            <div class="clearfix"></div>        	
                             
                            <div class="span-3">                             			
							<label for=""><g:message code="historia.psiqui.label" default="Psiquismo:" /></label>
							</div>
							<div class="span-4">
							<g:textField class="ui-widget ui-corner-all ui-widget-content" id="psiquiId" name="consulta.psiqui" value="${consultaInstace?.psiqui}"></g:textField>
                                		<g:hasErrors bean="${consultaInstance}" field="psiqui">
                                			<br/>
	                                    	<div class="ui-state-error ui-corner-all"><g:renderErrors bean="${consultaInstance}" as="list" field="psiqui"/></div>
                                    	</g:hasErrors>
                            </div>
                            <div class="span-3">        	
							<label for=""><g:message code="historia.facie.label" default="Facie:" /></label>
							</div>
							<div class="span-4">
							<g:textField class="ui-widget ui-corner-all ui-widget-content" id="facieId" name="consulta.facie" value="${consultaInstace?.facie}"></g:textField>
                                		<g:hasErrors bean="${consultaInstance}" field="facie">
                                			<br/>
	                                    	<div class="ui-state-error ui-corner-all"><g:renderErrors bean="${consultaInstance}" as="list" field="facie"/></div>
                                    	</g:hasErrors>
                 			</div>
                 		</fieldset>
            		</div>
            		<div id="tabs-3">
            			<fieldset>
            				<div class="span-6">
            					<label for=""><g:message code ="historia.estudiocomplementario.image1" default="Imagen 1"/></label>
            				</div>
            				<div class="span-10">
            					<input type="file" name="imagen.1" />
            				</div>
            				
            				<div class="span-6">
            					<label for=""><g:message code ="historia.estudiocomplementario.image2" default="Imagen 2"/></label>
            				</div>
            				<div class="span-10">
            					<input type="file" name="imagen.2" />
            				</div>

            				<div class="span-6">
            					<label for=""><g:message code ="historia.estudiocomplementario.image3" default="Imagen 3"/></label>
            				</div>
            				<div class="span-10">
            					<input type="file" name="imagen.3" />
            				</div>
            				
            			</fieldset>
            		</div>
            		<div id="tabs-4">
            			<fieldset>
                            <div class="span-18 colborder">
                            	<table id="prescripcionesId"></table>
                            </div>
                            <div id="pagerPrescripciones">	</div>
                            <button id="testgrid" onClick="return false;"></button>
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
