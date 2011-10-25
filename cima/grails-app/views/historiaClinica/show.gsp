
<%@ page import="com.medfire.Consulta" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'consulta.label', default: 'Consulta')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
        <link rel="stylesheet" type="text/css" media="screen" href="${g.resource(dir:'css/skins/tango',file:'skin.css')}" />
        <link rel="stylesheet" type="text/css" media="screen" href="${g.resource(dir:'css',file:'thickbox.css')}" />        
        <script type="text/javascript" src="${resource(dir:'js/editor',file:'ckeditor.js')}"></script>
      	<script type="text/javascript" src="${resource(dir:'js/jquery',file:'jquery.jcarousel.min.js')}"></script>        
      	<script type="text/javascript" src="${resource(dir:'js/jquery',file:'thickbox.js')}"></script>      	
        <script type="text/javascript" src="${resource(dir:'js/script/historia',file:'show.js')}"></script>
        <script type="text/javascript">
        	var locprescripciones='<%out << g.createLink(controller:'historiaClinica',action:'listprescripciones',params:[id:consultaInstance.id])%>';
        	var tb_pathToImage = "<%out << "${resource(dir:'images',file:'loading-thickbox.gif')}"%>";
			var mycarouselList=[
		
			  ];

			function mycarousel_getItemHTML(item)
			{
			  
			    return '<a   href="' + item.url + '" title="' + item.title + '"><img src="' + item.url + '" width="600" height="600" border="0" alt="' + item.title + '" /></a>';
			};

			
        	function mycarousel_itemLoadCallback(carousel, state)
        	{
            	var ul = document.getElementById("estudioscomplementariosId");
            	var	item;
            	if(mycarouselList.length>0)
	        	    for (var i = carousel.first; i <= carousel.last; i++) {
	        	    	item = $(mycarousel_getItemHTML(mycarouselList[i-1])).get(0);
	        	    	tb_init(item);
	
	        	    	
	        	        /*if (carousel.has(i)) {
	        	            continue;
	        	        }
	
	        	        if (i > mycarousel_itemList.length) {
	        	            break;
	        	        }*/
	
	        	        // Create an object from HTML
	        	        //var item = jQuery(mycarousel_getItemHTML(mycarousel_itemList[i-1])).get(0);
	
	        	        // Apply thickbox
	        	        
	
	        	        //carousel.add(i, item);
	        	    }
        	};
        	        	
        </script>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.show.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="ui-state-highlight ui-corner-all"><h3>${flash.message}</h3></div>
            </g:if>
            
			<div>
           		Historia Clínica: <g:formatNumber number="${consultaInstance.paciente.id}" format="000000" />
           		<br/>
           		Paciente: ${consultaInstance.paciente?.apellido+" - "+consultaInstance.paciente?.nombre}
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
            				<div class="span-2"><label style="float:left;" for="cie10Descripcion">CIE-10:</label></div>
            				<div class="span-4">${fieldValue(bean: consultaInstance.cie10, field: "cie10")} ${fieldValue(bean: consultaInstance.cie10, field: "descripcion")}</div>
   							
   							<div class="span-4"><label for="consulta.fechaConsulta">Fecha de Consulta:</label></div>
   							<div class="span-3"><g:formatDate format="dd/MM/yyyy" style="SHORT" date="${consultaInstance.fechaConsulta}"/></div>
   							
   							<div class="span-2"><label for="consulta.estado">Estado:</label></div>
   							<div class="span-2">${consultaInstance.estado.name}</div>
   							<div class="clearfix"></div>
   							
   							<div class="span-20" >
								<textArea name="consulta.contenido" class="ckeditor"  id="contenidoId">
									${consultaInstance?.contenido}
								</textArea>
							</div>
            			
            			</fieldset>
            		</div>
            		
            		<div id="tabs-2">
            			<fieldset>
            				<legend>Signos Vitales</legend>
            							<div class="span-3"><label for=""><g:message code="historia.pulso.label" default="Pulso:" /></label></div>
            							<div class="span-3">${consultaInstance?.pulso}</div>
			                            
			                            <div class="span-3"><label for=""><g:message code="historia.fc.label" default="FC:" /></label></div>
			                            <div class="span-3">${consultaInstance?.fc}</div>
			                            <div class="clearfix"></div>        
			                                    	
										<div class="span-3"><label for=""><g:message code="historia.ta.label" default="TA:" /></label></div>
										<div class="span-3">${consultaInstance?.ta}</div>
										
										<div class="span-3"><label for=""><g:message code="historia.fr.label" default="FR:" /></label></div>
										<div class="span-3">${consultaInstance?.fr}</div>
										<div class="clearfix"></div>
										
										<div class="span-3"><label for=""><g:message code="historia.taxilar.label" default="T.Axilar:" /></label></div>
										<div class="span-3">${consultaInstance?.taxilar}</div>
										
		                                <div class="span-3"><label for=""><g:message code="historia.trectal.label" default="T.Rectal:" /></label></div>
		                                <div class="span-3">${consultaInstance?.trectal}</div>
		                                <div class="clearfix"></div>      
		                                
		                                <div class="span-3"><label for=""><g:message code="historia.pesoh.label" default="Peso habitual:" /></label></div>
		                                <div class="span-3">${consultaInstance?.pesoh}</div>
		                                
		                                <div class="span-3"><label for=""><g:message code="historia.pesoa.label" default="Peso actual:" /></label></div>
		                                <div class="span-3">${consultaInstance?.pesoa}</div>
										<div class="clearfix"></div>
                 		</fieldset>
                 		<fieldset>
                 			<legend>Otros datos</legend>
                 			<div class="clear"></div>
                 			<div class="span-3"><label for="impresion"><g:message code="historia.impresion.label" default="Impresión:" /></label></div>
							<div class="span-5 colborder">
							<textArea readonly="readonly" class="ui-widget ui-corner-all ui-widget-content textareastudio" id="impresionId" name="consulta.impresion" >
								${consultaInstance?.impresion}
							</textArea>
                            </div>
                            <div class="clearfix"></div>
                            
                            <div class="span-3"><label for=""><g:message code="historia.habito.label" default="Hábito:" /></label></div>
							<div class="span-4">${consultaInstance?.habito}</div>
                            
                            <div class="span-3"><label for=""><g:message code="historia.actitud.label" default="Actitud:" /></label></div>
							<div class="span-4">${consultaInstance?.actitud}</div>
                            <div class="clearfix"></div>

							<div class="span-3"><label for=""><g:message code="historia.ubicacion.label" default="Ubicación:" /></label></div>
							<div class="span-4">${consultaInstance?.ubicacion}</div>

                            <div class="span-3"><label for=""><g:message code="historia.marcha.label" default="Marcha:" /></label></div>
							<div class="span-4">${consultaInstance?.marcha}</div>
                            <div class="clearfix"></div>        	
                             
                            <div class="span-3"><label for=""><g:message code="historia.psiqui.label" default="Psiquismo:" /></label></div>
							<div class="span-4">${consultaInstance?.psiqui}</div>

                            <div class="span-3"><label for=""><g:message code="historia.facie.label" default="Facie:" /></label></div>
							<div class="span-4">${consultaInstance?.facie}</div>
                 		</fieldset>
            		</div>

            		<div id="tabs-3">
            			<div id="tabs-estudios">
           					<ul>
           						<g:set var="i" value="${1}" />
           						<g:each var="estudio" in="${consultaInstance?.estudios}">
           							<li><a href="#tab-estudio${i}">Estudio ${i}</a></li>
									<g:set var="i" value="${i+1}"/>   
           						</g:each>
           					</ul>
						    <g:set var="i" value="${0}" />
							<g:each var="estudio" in ="${consultaInstance?.estudios}">
								<g:set var="i" value="${i+1}"/>
	           					<div id="tab-estudio${i}">
					           				<div class="span-10">
											    <label for="consulta.estudio.1.pedido">Pedido:</label>
											    <br/>
												${estudio?.pedido}
												<br/>	
					           					<label for="consulta.estudioComplementarioObs"><g:message code="historia.estudioComplementarioObs.label" default="Resultado:" /></label>
					           					<br/>
					           					<g:textArea readonly="readonly" class="ui-widget ui-corner-all ui-widget-content textareastudio" id="estudioComplementarioObsId" name="consulta.estudioComplementarioObs">
												 	${estudio?.resultado}  
					           					</g:textArea>
					           				</div>
					            			<div class="clear"> </div>
					            			<div class="span-15">
					            				<fieldset>
					            					<legend>Imagenes de estudio</legend>
					            					<g:set var="j" value="${1}"/>
						            				<g:each var="imagen" in="${estudio?.imagenes}">
						            					<label>Imagen ${j}:</label><br/>
								            			<bi:hasImage bean="${imagen}">
									    						<a class="thickbox" href="${bi.resource(size:'large', bean:imagen)}"><img src="${bi.resource(size:'small', bean:imagen)}" width="50" height="50" alt=""> </img></a>
									    						<br/>
														</bi:hasImage>
														<g:set var="j" value="${j+1}"/>
													</g:each>
												</fieldset>       		
											</div>
					            			<div class="clear"> </div>
											
		            			</div>
	            			</g:each>
	            			
	            		</div>	
            		</div>

            		<div id="tabs-4">
            			<fieldset>
            				<div class="clear"></div>
                            <div class="span-18"><table id="prescripcionesId"></table></div>
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
	            						<g:checkBox id="checklabel1Id" indice="1"   name="paciente.antecedente.t1Check" value="${consultaInstance.paciente.antecedente?.t1Check}"></g:checkBox>
	            						<g:fieldValue bean="${consultaInstance.paciente.antecedente}" field="t1"></g:fieldValue>
	            					</div>
	            					<div class="span-9">
	            						<label for="paciente.antecedente.t2"><g:antecedenteLabel field="t2Label"/>:</label>
	            						<br/>	            						
	            						<g:checkBox id="checklabel2Id" indice="2"   name="paciente.antecedente.t2Check" value="${consultaInstance.paciente.antecedente?.t2Check}"></g:checkBox>
	            						<g:fieldValue bean="${consultaInstance.paciente.antecedente}" field="t2"></g:fieldValue>
	            					</div>

	            					<div class="clear"></div>
	            					<div class="span-9 colborder">
	            						<label for="paciente.antecedente.t3"><g:antecedenteLabel field="t3Label"/>:</label>
	            						<br/>
	            						<g:checkBox id="checklabel3Id" indice="3"   name="paciente.antecedente.t3Check" value="${consultaInstance.paciente.antecedente?.t3Check}" ></g:checkBox>
	            						<g:fieldValue bean="${consultaInstance.paciente.antecedente}" field="t3"></g:fieldValue>
	            					</div>
	            					<div class="span-9">
	            						<label for="paciente.antecedente.t4"><g:antecedenteLabel field="t4Label"/>:</label>
	            						<br/>
	            						<g:checkBox id="checklabel4Id" indice="4"   name="paciente.antecedente.t4Check" value="${consultaInstance.paciente.antecedente?.t4Check}"></g:checkBox>
	            						<g:fieldValue bean="${consultaInstance.paciente.antecedente}" field="t4"></g:fieldValue>
	            					</div>

	            					<div class="clear"></div>
	            					<div class="span-9 colborder">
	            						<label for="paciente.antecedente.t5"><g:antecedenteLabel field="t5Label"/>:</label>
	            						<br/>
	            						<g:checkBox id="checklabel5Id" indice="5"   name="paciente.antecedente.t5Check" value="${consultaInstance.paciente.antecedente?.t5Check}"></g:checkBox>
	            						<g:fieldValue bean="${consultaInstance.paciente.antecedente}" field="t5"></g:fieldValue>
	            					</div>
	            					<div class="span-9">
	            						<label for="paciente.antecedente.t6"><g:antecedenteLabel field="t6Label"/>:</label>
	            						<br/>
	            						<g:checkBox id="checklabel6Id" indice="6"   name="paciente.antecedente.t6Check" value="${consultaInstance.paciente.antecedente?.t6Check}"></g:checkBox>	            						
	            						<g:fieldValue bean="${consultaInstance.paciente.antecedente}" field="t6"></g:fieldValue>
	            					</div>
	            					
	            					<div class="clear"></div>
	            					<div class="span-9 colborder">
	            						<label for="paciente.antecedente.t7"><g:antecedenteLabel field="t7Label"/>:</label>
	            						<br/>
	            						<g:checkBox id="checklabel7Id" indice="7"   name="paciente.antecedente.t7Check" value="${consultaInstance.paciente.antecedente?.t7Check}"></g:checkBox>	            						
	            						<g:fieldValue bean="${consultaInstance.paciente.antecedente}" field="t7"></g:fieldValue>
	            					</div>
	            					<div class="span-9">
	            						<label for="paciente.antecedente.t8"><g:antecedenteLabel field="t8Label"/>:</label>
	            						<br/>
	            						<g:checkBox id="checklabel8Id" indice="8"   name="paciente.antecedente.t8Check" value="${consultaInstance.paciente.antecedente?.t8Check}"></g:checkBox>	            						
	            						<g:fieldValue bean="${consultaInstance.paciente.antecedente}" field="t8"></g:fieldValue>
	            					</div>

	            					<div class="clear"></div>
	            					<div class="span-9 colborder">
	            						<label for="paciente.antecedente.t9"><g:antecedenteLabel field="t9Label"/>:</label>
	            						<br/>
	            						<g:checkBox id="checklabel9Id" indice="9"   name="paciente.antecedente.t9Check" value="${consultaInstance.paciente.antecedente?.t9Check}"></g:checkBox>	            						
	            						<g:fieldValue bean="${consultaInstance.paciente.antecedente}" field="t9"></g:fieldValue>
	            					</div>
	            					<div class="span-9">
	            						<label for="paciente.antecedente.t10"><g:antecedenteLabel field="t10Label"  />:</label>
	            						<br/>
	            						<g:checkBox id="checklabel10Id" indice="10"   name="paciente.antecedente.t10Check" value="${consultaInstance.paciente.antecedente?.t10Check}"></g:checkBox>	            						
	            						<g:fieldValue bean="${consultaInstance.paciente.antecedente}" field="t10"></g:fieldValue>
	            					</div>


	            					<div class="clear"></div>
	            					<div class="span-9 colborder">
	            						<label for="paciente.antecedente.t11"><g:antecedenteLabel field="t11Label"/>:</label>
	            						<br/>
	            						<g:checkBox id="checklabel11Id" indice="11"   name="paciente.antecedente.t11Check"  value="${consultaInstance.paciente.antecedente?.t11Check}"></g:checkBox>	            						
	            						<g:fieldValue bean="${consultaInstance.paciente.antecedente}" field="t11"></g:fieldValue>
	            					</div>
	            					<div class="span-9">
	            						<label for="paciente.antecedente.t12"><g:antecedenteLabel field="t12Label"/>:</label>
	            						<br/>
	            						<g:checkBox id="checklabel12Id" indice="12"   name="paciente.antecedente.t12Check"  value="${consultaInstance.paciente.antecedente?.t12Check}"></g:checkBox>	            						
	            						<g:fieldValue bean="${consultaInstance.paciente.antecedente}" field="t12"></g:fieldValue>
	            					</div>
	            					
	            					<div class="clear"></div>	            					
	            					<div class="span-9 colborder">
	            						<label for="paciente.antecedente.nefropatia"><g:antecedenteLabel field="t13Label"/>:</label>
	            						<br/>
	            						<g:checkBox id="checklabel13Id" indice="13"   name="paciente.antecedente.t13Check"  value="${consultaInstance.paciente.antecedente?.t13Check}"></g:checkBox>	            						
	            						<g:fieldValue bean="${consultaInstance.paciente.antecedente}" field="t13"></g:fieldValue>
	            					</div>
	            					<div class="span-9">
	            						<label for="paciente.antecedente.t14"><g:antecedenteLabel field="t14Label"/>:</label>
	            						<br/>
	            						<g:checkBox id="checklabel14Id" indice="14"   name="paciente.antecedente.t14Check"  value="${consultaInstance.paciente.antecedente?.t14Check}"></g:checkBox>	            						
	            						<g:fieldValue bean="${consultaInstance.paciente.antecedente}" field="t14"></g:fieldValue>
	            					</div>
	            					

	            					<div class="clear"></div>
	            					<div class="span-9 colborder">
	            						<label for="paciente.antecedente.t15"><g:antecedenteLabel field="t15Label"/>:</label>
	            						<br/>
	            						<g:checkBox id="checklabel15Id" indice="15"   name="paciente.antecedente.t15Check" value="${consultaInstance.paciente.antecedente?.t15Check}"></g:checkBox>	            						
	            						<g:fieldValue bean="${consultaInstance.paciente.antecedente}" field="t15"></g:fieldValue>
	            					</div>
	            					<div class="span-9">
	            						<label for="paciente.antecedente.t16"><g:antecedenteLabel field="t16Label"/>:</label>
	            						<br/>
	            						<g:checkBox id="checklabel16Id" indice="16"   name="paciente.antecedente.t16Check" value="${consultaInstance.paciente.antecedente?.t16Check}"></g:checkBox>	            						
	            						<g:fieldValue bean="${consultaInstance.paciente.antecedente}" field="t16"></g:fieldValue>
	            					</div>

	            					<div class="clear"></div>
	            					<div class="span-9 colborder">
	            						<label for="paciente.antecedente.t17"><g:antecedenteLabel field="t17Label"/>:</label>
	            						<br/>
	            						<g:checkBox id="checklabel17Id" indice="17"   name="paciente.antecedente.t17Check" value="${consultaInstance.paciente.antecedente?.t17Check}"></g:checkBox>	            						
	            						<g:fieldValue bean="${consultaInstance.paciente.antecedente}" field="t17"></g:fieldValue>
	            					</div> 
	            					<div class="span-9">
	            						<label for="paciente.antecedente.t18"><g:antecedenteLabel field="t18Label"/>:</label>
	            						<br/>
	            						<g:checkBox id="checklabel18Id" indice="18"   name="paciente.antecedente.t18Check" value="${consultaInstance.paciente.antecedente?.t18Check}"></g:checkBox>	            						
	            						<g:fieldValue bean="${consultaInstance.paciente.antecedente}" field="t18"></g:fieldValue>
	            					</div>
	            					
	            					<div class="clear"></div>
	            					<div class="span-9 colborder">
	            						<label for="paciente.antecedente.t19"><g:antecedenteLabel field="t19Label"/>:</label>
	            						<br/>
	            						<g:checkBox id="checklabel19Id" indice="19"   name="paciente.antecedente.t19Check"  value="${consultaInstance.paciente.antecedente?.t19Check}"></g:checkBox>	            						
	            						<g:fieldValue bean="${consultaInstance.paciente.antecedente}" field="t19"></g:fieldValue>
	            					</div>
	            					<div class="span-9">
	            						<label for="paciente.antecedente.t20"><g:antecedenteLabel field="t20Label"/>:</label>
	            						<br/>
	            						<g:checkBox id="checklabel20Id" indice="20"   name="paciente.antecedente.t20Check" value="${consultaInstance.paciente.antecedente?.t20Check}"></g:checkBox>	            						
	            						<g:fieldValue bean="${consultaInstance.paciente.antecedente}" field="t20"></g:fieldValue>
	            					</div>
	            					
	            					<div class="clear"></div>
	            					<div class="span-9 colborder">
	            						<label for="paciente.antecedente.t21"><g:antecedenteLabel field="t21Label"/>:</label>
	            						<br/>
	            						<g:checkBox id="checklabel21Id" indice="21"   name="paciente.antecedente.t21Check" value="${consultaInstance.paciente.antecedente?.t21Check}"></g:checkBox>	            						
	            						<g:fieldValue bean="${consultaInstance.paciente.antecedente}" field="t21"></g:fieldValue>
	            					</div>
	            					<div class="span-9">
	            						<label for="paciente.antecedente.t22"><g:antecedenteLabel field="t22Label"/>:</label>
	            						<br/>
	            						<g:checkBox id="checklabel22Id" indice="22"   name="paciente.antecedente.t22Check"  value="${consultaInstance.paciente.antecedente?.t22Check}"></g:checkBox>	            						
	            						<g:fieldValue bean="${consultaInstance.paciente.antecedente}" field="t22"></g:fieldValue>
	            					</div>

	            					
	            				</fieldset>
	            				<fieldset>
	            					<legend>Familiares</legend>
	            					<div class="span-9">
	            						<label for="paciente.antecedente.antecedenteFamiliar">Antecedentes Familiares:</label>
	            						<br/>
	            						<g:textArea readonly="readonly" class="textareastudio" name="paciente.antecedente.antecedenteFamiliar" >
	            							<g:fieldValue bean="${consultaInstance.paciente.antecedente}" field="antecedenteFamiliar"></g:fieldValue>
	            						</g:textArea>
	            					</div>
	            				</fieldset>
						</div>					
            		</div><!-- cierre div tabs-5 -->
            		<div class="clear"></div>
            		
            
            </div>
            <div class="span-4">
	            <div class="buttons">
	                <g:form>
	                    <g:hiddenField name="id" value="${consultaInstance?.id}" />
	                    <g:actionSubmit class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only" action="edit" value="${message(code: 'default.button.edit.label', default: 'Edit')}" />
	                    <g:actionSubmit class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Está seguro ?')}');" />
	                </g:form>
	            </div>
	        </div>    
            <div class="span-4 prepend-5">
               	<g:jasperReport controller="historiaClinica" action="reportecontenidovisita" 
            			jasper="historiacontenidovisita" format="PDF" name="Cont. de la Visita">
           			<g:hiddenField name="id" value="${consultaInstance?.id}"></g:hiddenField>
            	</g:jasperReport>
            
            </div>
        </div>
    </body>
</html>
