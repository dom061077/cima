
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
			  		<%	def flagcolon = false;
			  			consultaInstance.estudios?.each{
							  if(flagcolon)
							  	out<< ",{url:'${bi.resource(size:'large', bean:it)}',title:''}"
							  else
							  	out<< "{url:'${bi.resource(size:'large', bean:it)}',title:''}"
							  flagcolon = true
						  }
			  		%>			
			  ];

			function mycarousel_getItemHTML(item)
			{
			  
			    return '<a   href="' + item.url + '" title="' + item.title + '"><img src="' + item.url + '" width="600" height="600" border="0" alt="' + item.title + '" /></a>';
			};

			
        	function mycarousel_itemLoadCallback(carousel, state)
        	{
            	var ul = document.getElementById("estudioscomplementariosId");
            	var	item;
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
            <div class="ui-state-highlight ui-corner-all">${flash.message}</div>
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
							<textArea class="ui-widget ui-corner-all ui-widget-content" id="impresionId" name="consulta.impresion" >
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
           				<div class="span-10">
           					<label for="consulta.estudioComplementarioObs"><g:message code="historia.estudioComplementarioObs.label" default="Observación:" /></label>
           					<br/>
           					<g:textArea class="ui-widget ui-corner-all ui-widget-content" id="estudioComplementarioObsId" name="consulta.estudioComplementarioObs">
            						${consultaInstance?.estudioComplementarioObs}
           					</g:textArea>
           				</div>
            			<div class="clear"> </div>
            			<div class="span-5">
	            			<ul id='estudioscomplementariosId'  class="jcarousel-skin-tango">
		            			<g:each var="estudio" in ="${consultaInstance?.estudios}">
			            			<bi:hasImage bean="${estudio}">
			            				<li>	
				    						<a class="thickbox" href="${bi.resource(size:'large', bean:estudio)}"><img src="${bi.resource(size:'large', bean:estudio)}" width="150" height="150" alt=""> </img></a>
										</li>
									</bi:hasImage>
		            			</g:each>
		            			
							</ul>            		
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
	            						<input type="checkbox" id="checklabel1Id" indice="1" name="paciente.antecedente.t1Check" />
	            						<g:fieldValue bean="consultaInstance.paciente.antecedente" field="t1"></g:fieldValue>
	            						<g:textField id="antecedentet1Id" class="inputlarge" name="paciente.antecedente.t1" value="${pacienteInstance.antecedente?.t1}"></g:textField>
	            					</div>
	            					<div class="span-9">
	            						<label for="paciente.antecedente.t2"><g:antecedenteLabel field="t2Label"/>:</label>
	            						<br/>	            						
	            						<g:checkBox id="checklabel2Id" indice="2"   name="paciente.antecedente.t2Check" value="${pacienteInstance.antecedente?.t2Check}"></g:checkBox>
	            						<g:textField id="antecedentet2Id" class="inputlarge" name="paciente.antecedente.t2" value="${pacienteInstance.antecedente?.hipertension}"></g:textField>
	            					</div>

	            					<div class="clear"></div>
	            					<div class="span-9 colborder">
	            						<label for="paciente.antecedente.t3"><g:antecedenteLabel field="t3Label"/>:</label>
	            						<br/>
	            						<g:checkBox id="checklabel3Id" indice="3"   name="paciente.antecedente.t3Check" value="${pacienteInstance.antecedente?.t3Check}" ></g:checkBox>
	            						<g:textField id="antecedentet3Id" class="inputlarge" name="paciente.antecedente.t3" value="${pacienteInstance.antecedente?.hipertension}"></g:textField>
	            					</div>
	            					<div class="span-9">
	            						<label for="paciente.antecedente.t4"><g:antecedenteLabel field="t4Label"/>:</label>
	            						<br/>
	            						<g:checkBox id="checklabel4Id" indice="4"   name="paciente.antecedente.t4Check" value="${pacienteInstance.antecedente?.t4Check}"></g:checkBox>
	            						<g:textField id="antecedentet4Id" class="inputlarge" name="paciente.antecedente.t4" value="${pacienteInstance.antecedente?.dislipidemia}"></g:textField>
	            					</div>

	            					<div class="clear"></div>
	            					<div class="span-9 colborder">
	            						<label for="paciente.antecedente.t5"><g:antecedenteLabel field="t5Label"/>:</label>
	            						<br/>
	            						<g:checkBox id="checklabel5Id" indice="5"   name="paciente.antecedente.t5Check" value="${pacienteInstance.antecedente?.t5Check}"></g:checkBox>
	            						<g:textField id="antecedentet5Id" class="inputlarge" name="paciente.antecedente.asmaBronquial" value="${pacienteInstance.antecedente?.asmaBronquial}"></g:textField>
	            					</div>
	            					<div class="span-9">
	            						<label for="paciente.antecedente.t6"><g:antecedenteLabel field="t6Label"/>:</label>
	            						<br/>
	            						<g:checkBox id="checklabel6Id" indice="6"   name="paciente.antecedente.t6Check" value="${pacienteInstance.antecedente?.t6Check}"></g:checkBox>	            						
	            						<g:textField id="antecedentet6Id" class="inputlarge" name="paciente.antecedente.t6" value="${pacienteInstance.antecedente?.psocopatia}"></g:textField>
	            					</div>
	            					
	            					<div class="clear"></div>
	            					<div class="span-9 colborder">
	            						<label for="paciente.antecedente.t7"><g:antecedenteLabel field="t7Label"/>:</label>
	            						<br/>
	            						<g:checkBox id="checklabel7Id" indice="7"   name="paciente.antecedente.t7Check" value="${pacienteInstance.antecedente?.t7Check}"></g:checkBox>	            						
	            						<g:textField id="antecedentet7Id" class="inputlarge" name="paciente.antecedente.t7" value="${pacienteInstance.antecedente?.alergia}"></g:textField>
	            					</div>
	            					<div class="span-9">
	            						<label for="paciente.antecedente.t8"><g:antecedenteLabel field="t8Label"/>:</label>
	            						<br/>
	            						<g:checkBox id="checklabel8Id" indice="8"   name="paciente.antecedente.t8Check" value="${pacienteInstance.antecedente?.t8Check}"></g:checkBox>	            						
	            						<g:textField id="antecedentet8Id" class="inputlarge" name="paciente.antecedente.t8" value="${pacienteInstance.antecedente?.tuberculosis}"></g:textField>
	            					</div>

	            					<div class="clear"></div>
	            					<div class="span-9 colborder">
	            						<label for="paciente.antecedente.t9"><g:antecedenteLabel field="t9Label"/>:</label>
	            						<br/>
	            						<g:checkBox id="checklabel9Id" indice="9"   name="paciente.antecedente.t9Check" value="${pacienteInstance.antecedente?.t9Check}"></g:checkBox>	            						
	            						<g:textField id="antecedentet9Id" class="inputlarge" name="paciente.antecedente.t9" value="${pacienteInstance.antecedente?.atipia}"></g:textField>
	            					</div>
	            					<div class="span-9">
	            						<label for="paciente.antecedente.t10"><g:antecedenteLabel field="t10Label"  />:</label>
	            						<br/>
	            						<g:checkBox id="checklabel10Id" indice="10"   name="paciente.antecedente.t10Check" value="${pacienteInstance.antecedente?.t10Check}"></g:checkBox>	            						
	            						<g:textField id="antecedentet10Id" class="inputlarge" name="paciente.antecedente.t10" value="${pacienteInstance.antecedente?.gota}"></g:textField>
	            					</div>


	            					<div class="clear"></div>
	            					<div class="span-9 colborder">
	            						<label for="paciente.antecedente.t11"><g:antecedenteLabel field="t11Label"/>:</label>
	            						<br/>
	            						<g:checkBox id="checklabel11Id" indice="11"   name="paciente.antecedente.t11Check"  value="${pacienteInstance.antecedente?.t11Check}"></g:checkBox>	            						
	            						<g:textField id="antecedentet11Id" class="inputlarge" name="paciente.antecedente.t11" value="${pacienteInstance.antecedente?.afeccionBroncopulmonar}"></g:textField>
	            					</div>
	            					<div class="span-9">
	            						<label for="paciente.antecedente.t12"><g:antecedenteLabel field="t12Label"/>:</label>
	            						<br/>
	            						<g:checkBox id="checklabel12Id" indice="12"   name="paciente.antecedente.t12Check"  value="${pacienteInstance.antecedente?.t12Check}"></g:checkBox>	            						
	            						<g:textField id="antecedentet12Id" class="inputlarge" name="paciente.antecedente.t12" value="${pacienteInstance.antecedente?.endicronopatia}"></g:textField>
	            					</div>
	            					
	            					<div class="clear"></div>	            					
	            					<div class="span-9 colborder">
	            						<label for="paciente.antecedente.nefropatia"><g:antecedenteLabel field="t13Label"/>:</label>
	            						<br/>
	            						<g:checkBox id="checklabel13Id" indice="13"   name="paciente.antecedente.t13Check"  value="${pacienteInstance.antecedente?.t13Check}"></g:checkBox>	            						
	            						<g:textField id="antecedentet13Id" class="inputlarge" name="paciente.antecedente.t13" value="${pacienteInstance.antecedente?.nefropatia}"></g:textField>
	            					</div>
	            					<div class="span-9">
	            						<label for="paciente.antecedente.t14"><g:antecedenteLabel field="t14Label"/>:</label>
	            						<br/>
	            						<g:checkBox id="checklabel14Id" indice="14"   name="paciente.antecedente.t14Check"  value="${pacienteInstance.antecedente?.t14Check}"></g:checkBox>	            						
	            						<g:textField id="antecedentet14Id" class="inputlarge" name="paciente.antecedente.t14" value="${pacienteInstance.antecedente?.uropatia}"></g:textField>
	            					</div>
	            					

	            					<div class="clear"></div>
	            					<div class="span-9 colborder">
	            						<label for="paciente.antecedente.t15"><g:antecedenteLabel field="t15Label"/>:</label>
	            						<br/>
	            						<g:checkBox id="checklabel15Id" indice="15"   name="paciente.antecedente.t15Check" value="${pacienteInstance.antecedente?.t15Check}"></g:checkBox>	            						
	            						<g:textField id="antecedentet15Id" class="inputlarge" name="paciente.antecedente.t15" value="${pacienteInstance.antecedente?.hemopatia}"></g:textField>
	            					</div>
	            					<div class="span-9">
	            						<label for="paciente.antecedente.t16"><g:antecedenteLabel field="t16Label"/>:</label>
	            						<br/>
	            						<g:checkBox id="checklabel16Id" indice="16"   name="paciente.antecedente.t16Check" value="${pacienteInstance.antecedente?.t16Check}"></g:checkBox>	            						
	            						<g:textField id="antecedentet16Id" class="inputlarge" name="paciente.antecedente.t16" value="${pacienteInstance.antecedente?.ets}"></g:textField>
	            					</div>

	            					<div class="clear"></div>
	            					<div class="span-9 colborder">
	            						<label for="paciente.antecedente.t17"><g:antecedenteLabel field="t17Label"/>:</label>
	            						<br/>
	            						<g:checkBox id="checklabel17Id" indice="17"   name="paciente.antecedente.t17Check" value="${pacienteInstance.antecedente?.t17Check}"></g:checkBox>	            						
	            						<g:textField id="antecedentet17Id" class="inputlarge" name="paciente.antecedente.t17" value="${pacienteInstance.antecedente?.ulceraGastroduodenal}"></g:textField>
	            					</div>
	            					<div class="span-9">
	            						<label for="paciente.antecedente.t18"><g:antecedenteLabel field="t18Label"/>:</label>
	            						<br/>
	            						<g:checkBox id="checklabel18Id" indice="18"   name="paciente.antecedente.t18Check" value="${pacienteInstance.antecedente?.t18Check}"></g:checkBox>	            						
	            						<g:textField id="antecedentet18Id" class="inputlarge" name="paciente.antecedente.t18" value="${pacienteInstance.antecedente?.hepatitis}"></g:textField>
	            					</div>
	            					
	            					<div class="clear"></div>
	            					<div class="span-9 colborder">
	            						<label for="paciente.antecedente.t19"><g:antecedenteLabel field="t19Label"/>:</label>
	            						<br/>
	            						<g:checkBox id="checklabel19Id" indice="19"   name="paciente.antecedente.t19Check"  value="${pacienteInstance.antecedente?.t19Check}"></g:checkBox>	            						
	            						<g:textField id="antecedentet19Id" class="inputlarge" name="paciente.antecedente.t19" value="${pacienteInstance.antecedente?.colecistopatia}"></g:textField>
	            					</div>
	            					<div class="span-9">
	            						<label for="paciente.antecedente.t20"><g:antecedenteLabel field="t20Label"/>:</label>
	            						<br/>
	            						<g:checkBox id="checklabel20Id" indice="20"   name="paciente.antecedente.t20Check" value="${pacienteInstance.antecedente?.t20Check}"></g:checkBox>	            						
	            						<g:textField id="antecedentet20Id" class="inputlarge" name="paciente.antecedente.t20" value="${pacienteInstance.antecedente?.enfermedadNeurologica}"></g:textField>
	            					</div>
	            					
	            					<div class="clear"></div>
	            					<div class="span-9 colborder">
	            						<label for="paciente.antecedente.t21"><g:antecedenteLabel field="t21Label"/>:</label>
	            						<br/>
	            						<g:checkBox id="checklabel21Id" indice="21"   name="paciente.antecedente.t21Check" value="${pacienteInstance.antecedente?.t21Check}"></g:checkBox>	            						
	            						<g:textField id="antecedentet21Id" class="inputlarge" name="paciente.antecedente.t21" value="${pacienteInstance.antecedente?.fiebreProlongada}"></g:textField>
	            					</div>
	            					<div class="span-9">
	            						<label for="paciente.antecedente.t22"><g:antecedenteLabel field="t22Label"/>:</label>
	            						<br/>
	            						<g:checkBox id="checklabel22Id" indice="22"   name="paciente.antecedente.t22Check"  value="${pacienteInstance.antecedente?.t22Check}"></g:checkBox>	            						
	            						<g:textField id="antecedentet22Id" class="inputlarge" name="paciente.antecedente.t22" value="${pacienteInstance.antecedente?.colagenopatia}"></g:textField>
	            					</div>

	            					
	            				</fieldset>
	            				<fieldset>
	            					<legend>Familiares</legend>
	            					<div class="span-9">
	            						<label for="paciente.antecedente.antecedenteFamiliar">Antecedentes Familiares:</label>
	            						<br/>
	            						<g:textArea class="title" name="paciente.antecedente.antecedenteFamiliar" >
	            							<g:fieldValue bean="consultaInstance.paciente.antecedente" field="antecedenteFamiliar"></g:fieldValue>
	            						</g:textArea>
	            					</div>
	            				</fieldset>
						</div>					
            		</div><!-- cierre div tabs-5 -->
            		<div class="clear"></div>
            		
            
            </div>
            
            <div class="buttons">
                <g:form>
                    <g:hiddenField name="id" value="${consultaInstance?.id}" />
                    <g:actionSubmit class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only" action="edit" value="${message(code: 'default.button.edit.label', default: 'Edit')}" />
                    <g:actionSubmit class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Está seguro ?')}');" />
                </g:form>
            </div>
        </div>
    </body>
</html>
