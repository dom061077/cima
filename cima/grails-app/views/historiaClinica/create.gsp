
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

		<style>
			#tabs-estudios { margin-top: 1em; }
			#tabs-estudios li .ui-icon-close { float: left; margin: 0.4em 0.2em 0 0; cursor: pointer; }
			#tabs-estudios li .ui-icon-arrowreturnthick-1-w { float: left; margin: 0.4em 0.2em 0 0; cursor: pointer; }
			
			#add_tab { cursor: pointer; }
		</style>

        
        <script type="text/javascript">
        	var loccie10search = "<%out << g.createLink(controller:'cie10',action:'listsearchjson');%>";
        	var loccie10autocomplete = "<%out << g.createLink(controller:'cie10',action:'listautocompletejson');%>";
        	var locvademec = '<%out << g.createLink(controller:'vademecum',action:'listjson')%>';
        	var locvademecdetalle = '<%out << g.createLink(controller:'vademecum',action:'vademecumdetalle')%>';
        	$("#imagenUnoId").val('<%out << "${imagen1}"; %>');
        	//var strjson='[{"id":"2","imprimirPor":"Nombre Comercial","nombreComercial":"DIAMOX","nombreGenerico":"Acetazolamida","cantidad":"4","presentacion":"null"},{"id":"1","imprimirPor":"Nombre generico","nombreComercial":"ACEMUK","nombreGenerico":"Acetilciste&iacute;na","cantidad":"8","presentacion":"null"}]';
        	function initsubmit(){
        		var gridData = jQuery("#prescripcionesId").getRowData();
            	var postData = JSON.stringify(gridData);
            	$('#submitButtonId').attr('disabled','true')
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
            <h2 style="margin-bottom:0px"><g:message code="default.create.label" args="[entityName]" /></h2>
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
            				<g:hiddenField name="pacienteId" value="${pacienteInstance?.id}"/>
            				<g:hiddenField name="pacienteVersion" value="${pacienteInstance?.version}"/>
            				<g:hiddenField name="eventId" value="${eventInstance?.id}"/>
            				<g:hiddenField name="eventVersion" value="${eventInstance?.version}" />
             				<div class="span-1 spanlabel">
		   							<label for="cie10Descripcion">CIE-10:</label>
		   					</div>		
		   					<div class="span-4">		
		   							<input  type="text" id="cie10DescripcionId" class="ui-widget ui-corner-all ui-widget-content" name="consulta.cie10Descripcion" value="${consultaInstance?.cie10?.descripcion}"/>
		   							<g:hiddenField id="cie10Id" name="consulta.cie10.id" value="${consultaInstance?.cie10?.id}"/>
   							</div>
   							
   							<div class="span-3 spanlabel">
		  							<label for="consulta.fechaConsulta">Fecha de Consulta:</label>
		  					</div>
		  					<div class="span-3  append-1">		
		  							<g:textField class="ui-widget ui-corner-all ui-widget-content" id="fechaConsultaId" name="consulta.fechaConsulta" value="${g.formatDate(format:'dd/MM/yyyy',date:consultaInstance?.fechaConsulta)}"/>
   							</div>
   							
   							<div class="span-1 spanlabel">
		   							<label for="consulta.estado">Estado:</label>
		   					</div>
		   					<div class="span-3">
		   							<g:select class="ui-widget ui-corner-all ui-widget-content" id="estadoId" name="consulta.estado" from="${com.medfire.enums.EstadoConsultaEnum.list()}" optionValue="name" value="${consultaInstance?.estado}"></g:select>
   							</div>
   							<div class="clear"></div>
   							<div class="span-18" >
   									
								<textArea name="consulta.contenido" class="ckeditor"  id="contenidoId">
									${consultaInstance?.contenido}
								</textArea>
							</div>
							           			
            			</fieldset>
            		</div>
            		<div id="tabs-2">
            			<div class="span-18 colborder">
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
										
                 		</fieldset>
                 		<fieldset>
                 			<legend >Otros datos</legend>
                 			<div class="clear"></div>
                 			<div class="span-3">
							<label for="impresion"><g:message code="historia.impresion.label" default="Impresión:" /></label>
							</div>
							<div class="span-5 colborder">
							<textArea class="ui-widget ui-corner-all ui-widget-content textareastudio" id="impresionId" name="consulta.impresion">${consultaInstance?.impresion}</textArea>
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
                 		</div><!-- div del span en la pesta�a  -->
            		</div>  
            		<div id="tabs-3">
            			<div class="span-3 append-bottom"><a id="agregarEstudioId" onClick="return false" href="">Agregar Estudio</a></div>
            			<div class="clear"></div>
            			<div id="tabs-estudios">
            					<ul>
            						<li><a href="#tab-estudio1">Estudio 1</a> <span class="ui-icon ui-icon-close">Remove Tab</span></li>
            					</ul>
            					<div id="tab-estudio1">
				           					<label for="consulta.estudio.1.pedido">Pedido:</label>
				           					<br/>
				           					<g:textField id="estudio1pedido" class="ui-widget ui-corner-all ui-widget-content inputlarge" name="consulta.estudio.1.pedido" />
				           					<br/>
				           					<label for="consulta.estudio.1.resultado"><g:message code="historia.estudioComplementarioObs.label" default="Resultado:" /></label>
				           					<br/>
			            					<g:textArea id="estudio1resultado" class="textareastudio ui-widget ui-corner-all ui-widget-content"  name="consulta.estudio.1.resultado">
			            					</g:textArea>
			            					<br>
			            					<fieldset>
			            						<legend>Estudios</legend>
					           					<label for="estudio.1.imagen.1"><g:message code ="historia.estudiocomplementario.image1" default="Imagen 1"/></label>
					           					<br>
					           					<input type="file" id="estudio1imagen1" name="consulta.estudio.1.imagen.1" />
					           					<br>
					           					<label for="estudio.1.imagen.2"><g:message code ="historia.estudiocomplementario.image2" default="Imagen 2"/></label>
					           					<br>
					           					<input type="file" id="estudio1imagen2" name="consulta.estudio.1.imagen.2" />
					           					<br>
					           					<label for="estudio.1.imagen.3"><g:message code ="historia.estudiocomplementario.image3" default="Imagen 3"/></label>
					           					<br>
					           					<input type="file" id="estudio1imagen3" name="consulta.estudio.1.imagen.3" />
					           				</fieldset>	
				           		</div>	
            			</div>
            		</div>
            		<div id="tabs-4">
            			<fieldset>
            				<g:hiddenField id="prescripcionesSerializedId" name="prescripciones" value="${prescripciones}"/>
            				<div class="clear"></div>
                            <div class="span-18">
                            	<table id="prescripcionesId"></table>
                            </div>
                            <div id="pagerPrescripciones">	</div>
            			</fieldset>
            		</div>
            		
            		<div id="tabs-5">
            			<div class="span-21">
	            				<fieldset>
	            					<legend>Personales</legend>
	            					<div class="span-9 colborder">
	            						<label for="paciente.antecedente.t1"><g:antecedenteLabel field="t1Label"/>:</label>
	            						<br/>
	            						<g:checkBox type="checkbox" id="checklabel1Id" indice="1" name="paciente.antecedente.t1Check" value="${antecedenteInstance?.t1Check}" />
	            						<g:textField id="antecedentet1Id" class="inputlarge" name="paciente.antecedente.t1" value="${antecedenteInstance?.t1}"></g:textField>
	            					</div>
	            					<div class="span-9">
	            						<label for="paciente.antecedente.t2"><g:antecedenteLabel field="t2Label"/>:</label>
	            						<br/>	            						
	            						<g:checkBox id="checklabel2Id" indice="2"   name="paciente.antecedente.t2Check" value="${antecedenteInstance?.t2Check}"></g:checkBox>
	            						<g:textField id="antecedentet2Id" class="inputlarge" name="paciente.antecedente.t2" value="${antecedenteInstance?.t2}"></g:textField>
	            					</div>

	            					<div class="clear"></div>
	            					<div class="span-9 colborder">
	            						<label for="paciente.antecedente.t3"><g:antecedenteLabel field="t3Label"/>:</label>
	            						<br/>
	            						<g:checkBox id="checklabel3Id" indice="3"   name="paciente.antecedente.t3Check" value="${antecedenteInstance?.t3Check}" ></g:checkBox>
	            						<g:textField id="antecedentet3Id" class="inputlarge" name="paciente.antecedente.t3" value="${antecedenteInstance?.t3}"></g:textField>
	            					</div>
	            					<div class="span-9">
	            						<label for="paciente.antecedente.t4"><g:antecedenteLabel field="t4Label"/>:</label>
	            						<br/>
	            						<g:checkBox id="checklabel4Id" indice="4"   name="paciente.antecedente.t4Check" value="${antecedenteInstance?.t4Check}"></g:checkBox>
	            						<g:textField id="antecedentet4Id" class="inputlarge" name="paciente.antecedente.t4" value="${antecedenteInstance?.t4}"></g:textField>
	            					</div>

	            					<div class="clear"></div>
	            					<div class="span-9 colborder">
	            						<label for="paciente.antecedente.t5"><g:antecedenteLabel field="t5Label"/>:</label>
	            						<br/>
	            						<g:checkBox id="checklabel5Id" indice="5"   name="paciente.antecedente.t5Check" value="${antecedenteInstance?.t5Check}"></g:checkBox>
	            						<g:textField id="antecedentet5Id" class="inputlarge" name="paciente.antecedente.t5" value="${antecedenteInstance?.t5}"></g:textField>
	            					</div>
	            					<div class="span-9">
	            						<label for="paciente.antecedente.t6"><g:antecedenteLabel field="t6Label"/>:</label>
	            						<br/>
	            						<g:checkBox id="checklabel6Id" indice="6"   name="paciente.antecedente.t6Check" value="${antecedenteInstance?.t6Check}"></g:checkBox>	            						
	            						<g:textField id="antecedentet6Id" class="inputlarge" name="paciente.antecedente.t6" value="${antecedenteInstance?.t6}"></g:textField>
	            					</div>
	            					
	            					<div class="clear"></div>
	            					<div class="span-9 colborder">
	            						<label for="paciente.antecedente.t7"><g:antecedenteLabel field="t7Label"/>:</label>
	            						<br/>
	            						<g:checkBox id="checklabel7Id" indice="7"   name="paciente.antecedente.t7Check" value="${antecedenteInstance?.t7Check}"></g:checkBox>	            						
	            						<g:textField id="antecedentet7Id" class="inputlarge" name="paciente.antecedente.t7" value="${antecedenteInstance?.t7}"></g:textField>
	            					</div>
	            					<div class="span-9">
	            						<label for="paciente.antecedente.t8"><g:antecedenteLabel field="t8Label"/>:</label>
	            						<br/>
	            						<g:checkBox id="checklabel8Id" indice="8"   name="paciente.antecedente.t8Check" value="${antecedenteInstance?.t8Check}"></g:checkBox>	            						
	            						<g:textField id="antecedentet8Id" class="inputlarge" name="paciente.antecedente.t8" value="${antecedenteInstance?.t8}"></g:textField>
	            					</div>

	            					<div class="clear"></div>
	            					<div class="span-9 colborder">
	            						<label for="paciente.antecedente.t9"><g:antecedenteLabel field="t9Label"/>:</label>
	            						<br/>
	            						<g:checkBox id="checklabel9Id" indice="9"   name="paciente.antecedente.t9Check" value="${antecedenteInstance?.t9Check}"></g:checkBox>	            						
	            						<g:textField id="antecedentet9Id" class="inputlarge" name="paciente.antecedente.t9" value="${antecedenteInstance?.t9}"></g:textField>
	            					</div>
	            					<div class="span-9">
	            						<label for="paciente.antecedente.t10"><g:antecedenteLabel field="t10Label"  />:</label>
	            						<br/>
	            						<g:checkBox id="checklabel10Id" indice="10"   name="paciente.antecedente.t10Check" value="${antecedenteInstance?.t10Check}"></g:checkBox>	            						
	            						<g:textField id="antecedentet10Id" class="inputlarge" name="paciente.antecedente.t10" value="${antecedenteInstance?.t10}"></g:textField>
	            					</div>


	            					<div class="clear"></div>
	            					<div class="span-9 colborder">
	            						<label for="paciente.antecedente.t11"><g:antecedenteLabel field="t11Label"/>:</label>
	            						<br/>
	            						<g:checkBox id="checklabel11Id" indice="11"   name="paciente.antecedente.t11Check"  value="${antecedenteInstance?.t11Check}"></g:checkBox>	            						
	            						<g:textField id="antecedentet11Id" class="inputlarge" name="paciente.antecedente.t11" value="${antecedenteInstance?.t11}"></g:textField>
	            					</div>
	            					<div class="span-9">
	            						<label for="paciente.antecedente.t12"><g:antecedenteLabel field="t12Label"/>:</label>
	            						<br/>
	            						<g:checkBox id="checklabel12Id" indice="12"   name="paciente.antecedente.t12Check"  value="${antecedenteInstance?.t12Check}"></g:checkBox>	            						
	            						<g:textField id="antecedentet12Id" class="inputlarge" name="paciente.antecedente.t12" value="${antecedenteInstance?.t12}"></g:textField>
	            					</div>
	            					
	            					<div class="clear"></div>	            					
	            					<div class="span-9 colborder">
	            						<label for="paciente.antecedente.nefropatia"><g:antecedenteLabel field="t13Label"/>:</label>
	            						<br/>
	            						<g:checkBox id="checklabel13Id" indice="13"   name="paciente.antecedente.t13Check"  value="${antecedenteInstance?.t13Check}"></g:checkBox>	            						
	            						<g:textField id="antecedentet13Id" class="inputlarge" name="paciente.antecedente.t13" value="${antecedenteInstance?.t13}"></g:textField>
	            					</div>
	            					<div class="span-9">
	            						<label for="paciente.antecedente.t14"><g:antecedenteLabel field="t14Label"/>:</label>
	            						<br/>
	            						<g:checkBox id="checklabel14Id" indice="14"   name="paciente.antecedente.t14Check"  value="${antecedenteInstance?.t14Check}"></g:checkBox>	            						
	            						<g:textField id="antecedentet14Id" class="inputlarge" name="paciente.antecedente.t14" value="${antecedenteInstance?.t14}"></g:textField>
	            					</div>
	            					

	            					<div class="clear"></div>
	            					<div class="span-9 colborder">
	            						<label for="paciente.antecedente.t15"><g:antecedenteLabel field="t15Label"/>:</label>
	            						<br/>
	            						<g:checkBox id="checklabel15Id" indice="15"   name="paciente.antecedente.t15Check" value="${antecedenteInstance?.t15Check}"></g:checkBox>	            						
	            						<g:textField id="antecedentet15Id" class="inputlarge" name="paciente.antecedente.t15" value="${antecedenteInstance?.t15}"></g:textField>
	            					</div>
	            					<div class="span-9">
	            						<label for="paciente.antecedente.t16"><g:antecedenteLabel field="t16Label"/>:</label>
	            						<br/>
	            						<g:checkBox id="checklabel16Id" indice="16"   name="paciente.antecedente.t16Check" value="${antecedenteInstance?.t16Check}"></g:checkBox>	            						
	            						<g:textField id="antecedentet16Id" class="inputlarge" name="paciente.antecedente.t16" value="${antecedenteInstance?.t16}"></g:textField>
	            					</div>

	            					<div class="clear"></div>
	            					<div class="span-9 colborder">
	            						<label for="paciente.antecedente.t17"><g:antecedenteLabel field="t17Label"/>:</label>
	            						<br/>
	            						<g:checkBox id="checklabel17Id" indice="17"   name="paciente.antecedente.t17Check" value="${antecedenteInstance?.t17Check}"></g:checkBox>	            						
	            						<g:textField id="antecedentet17Id" class="inputlarge" name="paciente.antecedente.t17" value="${antecedenteInstance?.t17}"></g:textField>
	            					</div>
	            					<div class="span-9">
	            						<label for="paciente.antecedente.t18"><g:antecedenteLabel field="t18Label"/>:</label>
	            						<br/>
	            						<g:checkBox id="checklabel18Id" indice="18"   name="paciente.antecedente.t18Check" value="${antecedenteInstance?.t18Check}"></g:checkBox>	            						
	            						<g:textField id="antecedentet18Id" class="inputlarge" name="paciente.antecedente.t18" value="${antecedenteInstance?.t18}"></g:textField>
	            					</div>
	            					
	            					<div class="clear"></div>
	            					<div class="span-9 colborder">
	            						<label for="paciente.antecedente.t19"><g:antecedenteLabel field="t19Label"/>:</label>
	            						<br/>
	            						<g:checkBox id="checklabel19Id" indice="19"   name="paciente.antecedente.t19Check"  value="${antecedenteInstance?.t19Check}"></g:checkBox>	            						
	            						<g:textField id="antecedentet19Id" class="inputlarge" name="paciente.antecedente.t19" value="${antecedenteInstance?.t19}"></g:textField>
	            					</div>
	            					<div class="span-9">
	            						<label for="paciente.antecedente.t20"><g:antecedenteLabel field="t20Label"/>:</label>
	            						<br/>
	            						<g:checkBox id="checklabel20Id" indice="20"   name="paciente.antecedente.t20Check" value="${antecedenteInstance?.t20Check}"></g:checkBox>	            						
	            						<g:textField id="antecedentet20Id" class="inputlarge" name="paciente.antecedente.t20" value="${antecedenteInstance?.t20}"></g:textField>
	            					</div>
	            					
	            					<div class="clear"></div>
	            					<div class="span-9 colborder">
	            						<label for="paciente.antecedente.t21"><g:antecedenteLabel field="t21Label"/>:</label>
	            						<br/>
	            						<g:checkBox id="checklabel21Id" indice="21"   name="paciente.antecedente.t21Check" value="${antecedenteInstance?.t21Check}"></g:checkBox>	            						
	            						<g:textField id="antecedentet21Id" class="inputlarge" name="paciente.antecedente.t21" value="${antecedenteInstance?.t21}"></g:textField>
	            					</div>
	            					<div class="span-9">
	            						<label for="paciente.antecedente.t22"><g:antecedenteLabel field="t22Label"/>:</label>
	            						<br/>
	            						<g:checkBox id="checklabel22Id" indice="22"   name="paciente.antecedente.t22Check"  value="${antecedenteInstance?.t22Check}"></g:checkBox>	            						
	            						<g:textField id="antecedentet22Id" class="inputlarge" name="paciente.antecedente.t22" value="${antecedenteInstance?.t22}"></g:textField>
	            					</div>

	            					
	            				</fieldset>
	            				<fieldset>
	            					<legend>Familiares</legend>
	            					<div class="span-9">
	            						<label for="paciente.antecedente.antecedenteFamiliar">Antecedentes Familiares:</label>
	            						<br/>
	            						<g:textArea class="textareastudio" name="paciente.antecedente.antecedenteFamiliar" value="${antecedenteInstance?.antecedenteFamiliar}"></g:textArea>
	            					</div>
	            				</fieldset>
						</div>					
            		</div><!-- cierre div tabs-5 -->
            		<div class="clear"></div>
            		
       			</div><!-- cierre div tabs -->   	
            
                
                <div style="padding: 10px 15px 15px 15px;">
                    <g:submitButton id="submitButtonId" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only"  name="create" value="Guardar" />
                </div>
            </form>
        </div>
		        
    </body>
</html>
