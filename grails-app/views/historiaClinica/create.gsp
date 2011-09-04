
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'historiaClinica.label', default: 'HistoriaClinica')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
        <script type="text/javascript" src="${resource(dir:'js/script',file:'jquicombobox.js')}"></script>        
      	<script type="text/javascript" src="${resource(dir:'js/jquery',file:'jquery.jlookupfield.js')}"></script>
      	<script type="text/javascript" src="${resource(dir:'js/jquery',file:'jquery.form.js')}"></script>
        <script type="text/javascript" src="${resource(dir:'js/editor',file:'ckeditor.js')}"></script>
<!--        <link rel="stylesheet" href="${resource(dir:'css/framework',file:'forms.css')}" />-->
        
        <script type="text/javascript">
        	var loccie10search = "<%out << g.createLink(controller:'cie10',action:'listsearchjson');%>";
        	var loccie10autocomplete = "<%out << g.createLink(controller:'cie10',action:'listautocompletejson');%>";
        	var locvademec = '<%out << g.createLink(controller:'vademecum',action:'listjson')%>';
        	$("#imagenUnoId").val('<%out << "${imagen1}"; %>');
        	//var strjson='[{"id":"2","imprimirPor":"Nombre Comercial","nombreComercial":"DIAMOX","nombreGenerico":"Acetazolamida","cantidad":"4","presentacion":"null"},{"id":"1","imprimirPor":"Nombre generico","nombreComercial":"ACEMUK","nombreGenerico":"Acetilciste&iacute;na","cantidad":"8","presentacion":"null"}]';
        	function initsubmit(){
        		var gridData = jQuery("#prescripcionesId").getRowData();
            	var postData = JSON.stringify(gridData);
            	$("#prescripcionesSerializedId").val(postData);
            	
            }
                		
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
            
            <div id="outputId">
                
            </div>
            
            
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
            
            
            <form onSubmit="initsubmit();return true;" method="post" action="save" enctype="multipart/form-data" id="historiaFormId" >
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
            			<li><a href="#tabs-5">Antecedentes</a></li>
            		</ul>
            		<div id="tabs-1">
            			<fieldset>
            				<g:hiddenField name="pacienteId" value="${pacienteInstance?.id}"></g:hiddenField>
            				<div class="span-6">
            						
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
   							<div class="clear"></div>
   							<div class="span-20 colborder" >
   									
								<textArea name="consulta.contenido" class="ckeditor"  id="contenidoId">
									${consultaInstance?.contenido}
								</textArea>
							</div>
							           			
            			</fieldset>
            		</div>
            		<div id="tabs-2">
            			<fieldset>
            				<legend>Signos Vitales</legend>
            							<div class="span-3">
										<label for=""><g:message code="historia.pulso.label" default="Pulso:" /></label>
										</div>
										<div class="span-4">
										<g:textField class="ui-widget ui-corner-all ui-widget-content" id="pulsoId" name="consulta.pulso" value="${consultaInstance?.pulso}"></g:textField>
			                                		<g:hasErrors bean="${consultaInstance}" field="pulso">
				                                    	<div class="ui-state-error ui-corner-all"><g:renderErrors bean="${consultaInstance}" as="list" field="pulso"/></div>
			                                    	</g:hasErrors>
			                            </div>        	
			                            
			                            <div class="span-3">
										<label for=""><g:message code="historia.fc.label" default="FC:" /></label>
										</div>
										<div class="span-4">
										<g:textField class="ui-widget ui-corner-all ui-widget-content" id="fcId" name="consulta.fc" value="${consultaInstance?.fc}"></g:textField>
			                                		<g:hasErrors bean="${consultaInstance}" field="fc">
			                                			<br/>
				                                    	<div class="ui-state-error ui-corner-all"><g:renderErrors bean="${consultaInstance}" as="list" field="fc"/></div>
			                                    	</g:hasErrors>
			                            </div>

			                            <div class="clear"></div>        
			                                    	
										<div class="span-3">
										<label for=""><g:message code="historia.ta.label" default="TA:" /></label>
										</div>
										<div class="span-4">
										<g:textField class="ui-widget ui-corner-all ui-widget-content" id="taId" name="consulta.ta" value="${consultaInstance?.ta}"></g:textField>
			                                		<g:hasErrors bean="${consultaInstance}" field="ta">
			                                			<br/>
				                                    	<div class="ui-state-error ui-corner-all"><g:renderErrors bean="${consultaInstance}" as="list" field="ta"/></div>
			                                    	</g:hasErrors>
										</div>
										
										<div class="span-3">
										<label for=""><g:message code="historia.fr.label" default="FR:" /></label>
										</div>
										<div class="span-4 ">
										<g:textField class="ui-widget ui-corner-all ui-widget-content" id="frId" name="consulta.fr" value="${consultaInstance?.fr}"></g:textField>
			                                		<g:hasErrors bean="${consultaInstance}" field="fr">
			                                			<br/>
				                                    	<div class="ui-state-error ui-corner-all"><g:renderErrors bean="${consultaInstance}" as="list" field="fr"/></div>
			                                    	</g:hasErrors>
										</div>
										
										<div class="clear"></div>
										
										<div class="span-3">
										<label for=""><g:message code="historia.taxilar.label" default="T.Axilar:" /></label>
										</div>
										<div class="span-4">
										<g:textField class="ui-widget ui-corner-all ui-widget-content" id="taxilarId" name="consulta.taxilar" value="${consultaInstance?.taxilar}"></g:textField>
			                                		<g:hasErrors bean="${consultaInstance}" field="taxilar">
			                                			<br/>
				                                    	<div class="ui-state-error ui-corner-all"><g:renderErrors bean="${consultaInstance}" as="list" field="taxilar"/></div>
			                                    	</g:hasErrors>
		                                </div>
		                                
		                                <div class="span-3">
										<label for=""><g:message code="historia.trectal.label" default="T.Rectal:" /></label>
										</div>
										<div class="span-4 ">
										<g:textField class="ui-widget ui-corner-all ui-widget-content" id="trectalId" name="consulta.trectal" value="${consultaInstance?.trectal}"></g:textField>
			                                		<g:hasErrors bean="${consultaInstance}" field="trectal">
			                                			<br/>
				                                    	<div class="ui-state-error ui-corner-all"><g:renderErrors bean="${consultaInstance}" as="list" field="trectal"/></div>
			                                    	</g:hasErrors>
		                                </div>    
		                                
		                                <div class="clear"></div>      
		                                
		                                <div class="span-3">          
										<label for=""><g:message code="historia.pesoh.label" default="Peso habitual:" /></label>
										</div>
										<div class="span-4">
										<g:textField class="ui-widget ui-corner-all ui-widget-content" id="pesohId" name="consulta.pesoh" value="${consultaInstance?.pesoh}"></g:textField>
			                                		<g:hasErrors bean="${consultaInstance}" field="pesoh">
			                                			<br/>
				                                    	<div class="ui-state-error ui-corner-all"><g:renderErrors bean="${consultaInstance}" as="list" field="pesoh"/></div>
			                                    	</g:hasErrors>
		                                </div>
		                                
		                                <div class="span-3">
										<label for=""><g:message code="historia.pesoa.label" default="Peso actual:" /></label>
										</div>
										<div class="span-4">
										<g:textField class="ui-widget ui-corner-all ui-widget-content" id="pesoaId" name="consulta.pesoa" value="${consultaInstance?.pesoa}"></g:textField>
			                                		<g:hasErrors bean="${consultaInstance}" field="pesoa">
			                                			<br/>
				                                    	<div class="ui-state-error ui-corner-all"><g:renderErrors bean="${consultaInstance}" as="list" field="pesoa"/></div>
			                                    	</g:hasErrors>
			                            </div>         	
										<div class="clear"></div>
										<div class="span-20 colborder">
											
										</div>
										
                 		</fieldset>
                 		<fieldset>
                 			<legend>Otros datos</legend>
                 			<div class="clear"></div>
                 			<div class="span-3">
							<label for="impresion"><g:message code="historia.impresion.label" default="Impresión:" /></label>
							</div>
							<div class="span-5 colborder">
							<textArea class="ui-widget ui-corner-all ui-widget-content" id="impresionId" name="consulta.impresion">${consultaInstance?.impresion}</textArea>
                                		<g:hasErrors bean="${consultaInstance}" field="impresion">
                                			<br/>
	                                    	<div class="ui-state-error ui-corner-all"><g:renderErrors bean="${consultaInstance}" as="list" field="impresion"/></div>
                                    	</g:hasErrors>
                            </div>
                            <div class="clear"></div>
                            
                            <div class="span-3">        	
							<label for=""><g:message code="historia.habito.label" default="Hábito:" /></label>
							</div>
							<div class="span-4">
							<g:textField class="ui-widget ui-corner-all ui-widget-content" id="habitoId" name="consulta.habito" value="${consultaInstance?.habito}"></g:textField>
                                		<g:hasErrors bean="${consultaInstance}" field="habito">
                                			<br/>
	                                    	<div class="ui-state-error ui-corner-all"><g:renderErrors bean="${consultaInstance}" as="list" field="habito"/></div>
                                    	</g:hasErrors>
                            </div>
                            
                            <div class="span-3">   
							<label for=""><g:message code="historia.actitud.label" default="Actitud:" /></label>
							</div>
							<div class="span-4">
							<g:textField class="ui-widget ui-corner-all ui-widget-content" id="atitudId" name="consulta.actitud" value="${consultaInstance?.actitud}"></g:textField>
                                		<g:hasErrors bean="${consultaInstance}" field="actitud">
                                			<br/>
	                                    	<div class="ui-state-error ui-corner-all"><g:renderErrors bean="${consultaInstance}" as="list" field="actitud"/></div>
                                    	</g:hasErrors>
                            </div>
                            
                            <div class="clear"></div>

							<div class="span-3">
							<label for=""><g:message code="historia.ubicacion.label" default="Ubicación:" /></label>
							</div>
							<div class="span-4">
							<g:textField class="ui-widget ui-corner-all ui-widget-content" id="ubicacionId" name="consulta.ubicacion" value="${consultaInstance?.ubicacion}"></g:textField>
                                		<g:hasErrors bean="${consultaInstance}" field="ubicacion">
                                			<br/>
	                                    	<div class="ui-state-error ui-corner-all"><g:renderErrors bean="${consultaInstance}" as="list" field="ubicacion"/></div>
                                    	</g:hasErrors>
                            </div>
                            <div class="span-3">        	
							<label for=""><g:message code="historia.marcha.label" default="Marcha:" /></label>
							</div>
							<div class="span-4">
							<g:textField class="ui-widget ui-corner-all ui-widget-content" id="marchaId" name="consulta.marcha" value="${consultaInstance?.marcha}"></g:textField>
                                		<g:hasErrors bean="${consultaInstance}" field="marcha">
                                			<br/>
	                                    	<div class="ui-state-error ui-corner-all"><g:renderErrors bean="${consultaInstance}" as="list" field="marcha"/></div>
                                    	</g:hasErrors>
                            </div>        	
                            <div class="clear"></div>        	
                             
                            <div class="span-3">                             			
							<label for=""><g:message code="historia.psiqui.label" default="Psiquismo:" /></label>
							</div>
							<div class="span-4">
							<g:textField class="ui-widget ui-corner-all ui-widget-content" id="psiquiId" name="consulta.psiqui" value="${consultaInstance?.psiqui}"></g:textField>
                                		<g:hasErrors bean="${consultaInstance}" field="psiqui">
                                			<br/>
	                                    	<div class="ui-state-error ui-corner-all"><g:renderErrors bean="${consultaInstance}" as="list" field="psiqui"/></div>
                                    	</g:hasErrors>
                            </div>
                            <div class="span-3">        	
							<label for=""><g:message code="historia.facie.label" default="Facie:" /></label>
							</div>
							<div class="span-4">
							<g:textField class="ui-widget ui-corner-all ui-widget-content" id="facieId" name="consulta.facie" value="${consultaInstance?.facie}"></g:textField>
                                		<g:hasErrors bean="${consultaInstance}" field="facie">
                                			<br/>
	                                    	<div class="ui-state-error ui-corner-all"><g:renderErrors bean="${consultaInstance}" as="list" field="facie"/></div>
                                    	</g:hasErrors>
                 			</div>
                 		</fieldset>
            		</div>  
            		<div id="tabs-3">
            			<fieldset>
            				
            				<div class="span-10">
            					<label for="consulta.estudioComplementarioObs"><g:message code="historia.estudioComplementarioObs.label" default="Observación:" /></label>
            					<br/>
            					<div class="span-4">
	            					<g:textArea class="ui-widget ui-corner-all ui-widget-content" id="estudioComplementarioObsId" name="consulta.estudioComplementarioObs">
	            						${consultaInstance?.estudioComplementarioObs}
	            					</g:textArea>
                               		<g:hasErrors bean="${consultaInstance}" field="estudioComplementarioObs">
                               			<br/>
                                    	<div class="ui-state-error ui-corner-all"><g:renderErrors bean="${consultaInstance}" as="list" field="estudioComplementarioObs"/></div>
                                   	</g:hasErrors>
            					</div>		
            				</div>
            				<div class="clear"/>
            				
            				<div class="span-6">
            					<label for="imagen.1"><g:message code ="historia.estudiocomplementario.image1" default="Imagen 1"/></label>
            				</div>
            				<div class="span-10">
            					<input type="file" id="imagenUnoId" name="imagen.1" />
            				</div>
            				
            				<div class="clear"></div>
            				
            				<div class="span-6">
            					<label for="imagen.2"><g:message code ="historia.estudiocomplementario.image2" default="Imagen 2"/></label>
            				</div>
            				<div class="span-10">
            					<input type="file" name="imagen.2" />
            				</div>

            				<div class="clear"></div>

            				<div class="span-6">
            					<label for="imagen.3"><g:message code ="historia.estudiocomplementario.image3" default="Imagen 3"/></label>
            				</div>
            				<div class="span-10">
            					<input type="file" name="imagen.3" />
            				</div>
            				
            			</fieldset>
            		</div>
            		<div id="tabs-4">
            			<fieldset>
            				<g:hiddenField id="prescripcionesSerializedId" name="prescripciones" value="${prescripciones}"/>
            				<div class="clear"></div>
                            <div class="span-18 colborder">
                            	<table id="prescripcionesId"></table>
                            </div>
                            <div id="pagerPrescripciones">	</div>
            			</fieldset>
            		</div>
            		
            		<div id="tabs-5">
            			<div class="span-22">
	            				<fieldset>
	            					<legend>Personales</legend>
	            					<div class="span-9 colborder">
	            						<label for="paciente.antecedente.consulta"><g:antecedenteLabel label="t1Label"/>:</label>
	            						<br/>
	            						<g:checkBox  name="paciente.antecedente."></g:checkBox>
	            						<g:textField class="inputlarge" name="paciente.antecedente.consulta" value="${pacienteInstance.antecedente?.consulta}"></g:textField>
	            					</div>
	            					<div class="span-9 colborder">
	            						<label for="paciente.antecedente.hipertension"><g:antecedenteLabel label="t2Label"/></label>
	            						<br/>	            						
	            						<g:checkBox  name="paciente.antecedente."></g:checkBox>
	            						<g:textField class="inputlarge" name="paciente.antecedente.hipertension" value="${pacienteInstance.antecedente?.hipertension}"></g:textField>
	            					</div>

	            					<div class="clear"></div>
	            					<div class="span-9 colborder">
	            						<label for="paciente.antecedente.coronariopatia"><g:antecedenteLabel label="t3Label"/>:</label>
	            						<br/>	            						
	            						<g:textField class="inputlarge" name="paciente.antecedente.coronariopatia" value="${pacienteInstance.antecedente?.hipertension}"></g:textField>
	            					</div>
	            					<div class="span-9 colborder">
	            						<label for="paciente.antecedente.dislipidemia"><g:antecedenteLabel label="t4Label"/>:</label>
	            						<br/>
	            						<g:textField class="inputlarge" name="paciente.antecedente.dislipidemia" value="${pacienteInstance.antecedente?.dislipidemia}"></g:textField>
	            					</div>

	            					<div class="clear"></div>
	            					<div class="span-9 colborder">
	            						<label for="paciente.antecedente.asmaBronquial"><g:antecedenteLabel label="t5Label"/>:</label>
	            						<br/>
	            						<g:textField class="inputlarge" name="paciente.antecedente.asmaBronquial" value="${pacienteInstance.antecedente?.asmaBronquial}"></g:textField>
	            					</div>
	            					<div class="span-9 colborder">
	            						<label for="paciente.antecedente.psocopatia"><g:antecedenteLabel label="t6Label"/>:</label>
	            						<br/>
	            						<g:textField class="inputlarge" name="paciente.antecedente.psocopatia" value="${pacienteInstance.antecedente?.psocopatia}"></g:textField>
	            					</div>
	            					
	            					<div class="clear"></div>
	            					<div class="span-9 colborder">
	            						<label for="paciente.antecedente.alergia"><g:antecedenteLabel label="t7Label"/>:</label>
	            						<br/>
	            						<g:textField class="inputlarge" name="paciente.antecedente.alergia" value="${pacienteInstance.antecedente?.alergia}"></g:textField>
	            					</div>
	            					<div class="span-9 colborder">
	            						<label for="paciente.antecedente.tuberculosis"><g:antecedenteLabel label="t8Label"/>:</label>
	            						<br/>
	            						<g:textField class="inputlarge" name="paciente.antecedente.tuberculosis" value="${pacienteInstance.antecedente?.tuberculosis}"></g:textField>
	            					</div>

	            					<div class="clear"></div>
	            					<div class="span-9 colborder">
	            						<label for="paciente.antecedente.atipia"><g:antecedenteLabel label="t9Label"/>:</label>
	            						<br/>
	            						<g:textField class="inputlarge" name="paciente.antecedente.atipia" value="${pacienteInstance.antecedente?.atipia}"></g:textField>
	            					</div>
	            					<div class="span-9 colborder">
	            						<label for="paciente.antecedente.gota"><g:antecedenteLabel label="t10Label"/>:</label>
	            						<br/>
	            						<g:textField class="inputlarge" name="paciente.antecedente.gota" value="${pacienteInstance.antecedente?.gota}"></g:textField>
	            					</div>


	            					<div class="clear"></div>
	            					<div class="span-9 colborder">
	            						<label for="paciente.antecedente.afeccionBroncopulmonar"><g:antecedenteLabel label="t11Label"/>:</label>
	            						<br/>
	            						<g:textField class="inputlarge" name="paciente.antecedente.afeccionBroncopulmonar" value="${pacienteInstance.antecedente?.afeccionBroncopulmonar}"></g:textField>
	            					</div>
	            					<div class="span-9 colborder">
	            						<label for="paciente.antecedente.endicronopatia"><g:antecedenteLabel label="t12Label"/>:</label>
	            						<br/>
	            						<g:textField class="inputlarge" name="paciente.antecedente.endicronopatia" value="${pacienteInstance.antecedente?.endicronopatia}"></g:textField>
	            					</div>
	            					
	            					<div class="clear"></div>	            					
	            					<div class="span-9 colborder">
	            						<label for="paciente.antecedente.nefropatia"><g:antecedenteLabel label="t13Label"/>:</label>
	            						<br/>
	            						<g:textField class="inputlarge" name="paciente.antecedente.nefropatia" value="${pacienteInstance.antecedente?.nefropatia}"></g:textField>
	            					</div>
	            					<div class="span-9 colborder">
	            						<label for="paciente.antecedente.uropatia"><g:antecedenteLabel label="t14Label"/>:</label>
	            						<br/>
	            						<g:textField class="inputlarge" name="paciente.antecedente.uropatia" value="${pacienteInstance.antecedente?.uropatia}"></g:textField>
	            					</div>
	            					

	            					<div class="clear"></div>
	            					<div class="span-9 colborder">
	            						<label for="paciente.antecedente.hemopatia"><g:antecedenteLabel label="t15Label"/>:</label>
	            						<br/>
	            						<g:textField name="paciente.antecedente.hemopatia" value="${pacienteInstance.antecedente?.hemopatia}"></g:textField>
	            					</div>
	            					<div class="span-9 colborder">
	            						<label for="paciente.antecedente.ets"><g:antecedenteLabel label="t16Label"/>:</label>
	            						<br/>
	            						<g:textField class="inputlarge" name="paciente.antecedente.ets" value="${pacienteInstance.antecedente?.ets}"></g:textField>
	            					</div>

	            					<div class="clear"></div>
	            					<div class="span-9 colborder">
	            						<label for="paciente.antecedente.ulceraGastroduodenal"><g:antecedenteLabel label="t17Label"/>:</label>
	            						<br/>
	            						<g:textField class="inputlarge" name="paciente.antecedente.ulceraGastroduodenal" value="${pacienteInstance.antecedente?.ulceraGastroduodenal}"></g:textField>
	            					</div>
	            					<div class="span-9 colborder">
	            						<label for="paciente.antecedente.hepatitis"><g:antecedenteLabel label="t18Label"/>:</label>
	            						<br/>
	            						<g:textField class="inputlarge" name="paciente.antecedente.hepatitis" value="${pacienteInstance.antecedente?.hepatitis}"></g:textField>
	            					</div>
	            					
	            					<div class="clear"></div>
	            					<div class="span-9 colborder">
	            						<label for="paciente.antecedente.colecistopatia"><g:antecedenteLabel label="t19Label"/>:</label>
	            						<br/>
	            						<g:textField class="inputlarge" name="paciente.antecedente.colecistopatia" value="${pacienteInstance.antecedente?.colecistopatia}"></g:textField>
	            					</div>
	            					<div class="span-9 colborder">
	            						<label for="paciente.antecedente.enfermedadNeurologica"><g:antecedenteLabel label="t20Label"/>:</label>
	            						<br/>
	            						<g:textField class="inputlarge" name="paciente.antecedente.enfermedadNeurologica" value="${pacienteInstance.antecedente?.enfermedadNeurologica}"></g:textField>
	            					</div>
	            					
	            					<div class="clear"></div>
	            					<div class="span-9 colborder">
	            						<label for="paciente.antecedente.fiebreProlongada"><g:antecedenteLabel label="t21Label"/>:</label>
	            						<br/>
	            						<g:textField class="inputlarge" name="paciente.antecedente.fiebreProlongada" value="${pacienteInstance.antecedente?.fiebreProlongada}"></g:textField>
	            					</div>
	            					<div class="span-9 colborder">
	            						<label for="paciente.antecedente.colagenopatia"><g:antecedenteLabel label="t22Label"/>:</label>
	            						<br/>
	            						<g:textField class="inputlarge" name="paciente.antecedente.colagenopatia" value="${pacienteInstance.antecedente?.colagenopatia}"></g:textField>
	            					</div>

	            					
	            				</fieldset>
	            				<fieldset>
	            					<legend>Familiares</legend>
	            					<div class="span-9">
	            						<label for="paciente.antecedente.antecedenteFamiliar">Antecedentes Familiares:</label>
	            						<br/>
	            						<g:textArea class="title" name="paciente.antecedente.antecedenteFamiliar" value="${pacienteInstance.antecedente?.antecedenteFamiliar}"></g:textArea>
	            					</div>
	            				</fieldset>
						</div>					
            		</div><!-- cierre div tabs-5 -->
            		<div class="clear"></div>
            		
       			</div><!-- cierre div tabs -->   	
            
                
                <div style="padding: 10px 15px 15px 15px;">
                    <g:submitButton class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only"  name="create" value="${message(code: 'default.button.create.label', default: 'Create')}" />
                </div>
            </form>
        </div>
		        
    </body>
</html>
