
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
            			<div class="span-22">
	            				<fieldset>
	            					<legend>Personales</legend>
	            					<div class="span-9 colborder">
	            						<label for="paciente.antecedente.consulta">Consulta:</label>
	            						<br/>
	            						${fieldValue(bean: consultaInstance?.paciente?.antecedente, field: "consulta")}
	            					</div>
	            					<div class="span-9 colborder">
	            						<label for="paciente.antecedente.hipertension">Hiper.:</label>
	            						<br/>	            						
	            						${fieldValue(bean: consultaInstance?.paciente?.antecedente, field: "hipertension")}
	            					</div>

	            					<div class="clear"></div>
	            					<div class="span-9 colborder">
	            						<label for="paciente.antecedente.coronariopatia">Coronariopatia:</label>
	            						<br/>	            						
	            						${fieldValue(bean: consultaInstance?.paciente?.antecedente, field: "coronariopatia")}	            						
	            					</div>
	            					<div class="span-9 colborder">
	            						<label for="paciente.antecedente.dislipidemia">Dislipidemia:</label>
	            						<br/>
	            						${fieldValue(bean: consultaInstance?.paciente?.antecedente, field: "dislipidemia")}	            						
	            					</div>

	            					<div class="clear"></div>
	            					<div class="span-9 colborder">
	            						<label for="paciente.antecedente.asmaBronquial">Asma Bronquial:</label>
	            						<br/>
	            						${fieldValue(bean: consultaInstance?.paciente?.antecedente, field: "asmaBronquial")}	            						
	            					</div>
	            					<div class="span-9 colborder">
	            						<label for="paciente.antecedente.psocopatia">Psocopatía:</label>
	            						<br/>
	            						${fieldValue(bean: consultaInstance?.paciente?.antecedente, field: "psocopatia")}	            						
	            					</div>
	            					
	            					<div class="clear"></div>
	            					<div class="span-9 colborder">
	            						<label for="paciente.antecedente.alergia">Alergia:</label>
	            						<br/>
	            						${fieldValue(bean: consultaInstance?.paciente?.antecedente, field: "alergia")}	            						
	            					</div>
	            					<div class="span-9 colborder">
	            						<label for="paciente.antecedente.tuberculosis">Tuberculosis:</label>
	            						<br/>
	            						${fieldValue(bean: consultaInstance?.paciente?.antecedente, field: "tuberculosis")}	            						
	            					</div>

	            					<div class="clear"></div>
	            					<div class="span-9 colborder">
	            						<label for="paciente.antecedente.atipia">Atipia:</label>
	            						<br/>
	            						${fieldValue(bean: consultaInstance?.paciente?.antecedente, field: "atipia")}	            						
	            					</div>
	            					<div class="span-9 colborder">
	            						<label for="paciente.antecedente.gota">Gota:</label>
	            						<br/>
	            						${fieldValue(bean: consultaInstance?.paciente?.antecedente, field: "gota")}	            						
	            					</div>


	            					<div class="clear"></div>
	            					<div class="span-9 colborder">
	            						<label for="paciente.antecedente.afeccionBroncopulmonar">Afecciones Broncopul.:</label>
	            						<br/>
	            						${fieldValue(bean: consultaInstance?.paciente?.antecedente, field: "afeccionBroncopulmonar")}
	            					</div>
	            					<div class="span-9 colborder">
	            						<label for="paciente.antecedente.nefropatia">Nefropatías:</label>
	            						<br/>
	            						${fieldValue(bean: consultaInstance?.paciente?.antecedente, field: "nefropatia")}
	            					</div>

	            					<div class="clear"></div>
	            					<div class="span-9 colborder">
	            						<label for="paciente.antecedente.uropatia">Uropatías:</label>
	            						<br/>
	            						${fieldValue(bean: consultaInstance?.paciente?.antecedente, field: "uropatia")}
	            					</div>
	            					<div class="span-9 colborder">
	            						<label for="paciente.antecedente.hemopatia">Hemopatías:</label>
	            						<br/>
	            						${fieldValue(bean: consultaInstance?.paciente?.antecedente, field: "hemopatia")}
	            					</div>

	            					<div class="clear"></div>
	            					<div class="span-9 colborder">
	            						<label for="paciente.antecedente.ets">E.T.S:</label>
	            						<br/>
	            						${fieldValue(bean: consultaInstance?.paciente?.antecedente, field: "ets")}
	            					</div>
	            					<div class="span-9 colborder">
	            						<label for="paciente.antecedente.ulceraGastroduodenal">Ulcera Gastroduodenal:</label>
	            						<br/>
	            						${fieldValue(bean: consultaInstance?.paciente?.antecedente, field: "ulceraGastroduodenal")}
	            					</div>
	            					
	            					<div class="clear"></div>
	            					<div class="span-9 colborder">
	            						<label for="paciente.antecedente.hepatitis">Hepatitis:</label>
	            						<br/>
	            						${fieldValue(bean: consultaInstance?.paciente?.antecedente, field: "hepatitis")}
	            					</div>
	            					<div class="span-9 colborder">
	            						<label for="paciente.antecedente.colecistopatia">Colecistopatía:</label>
	            						<br/>
	            						${fieldValue(bean: consultaInstance?.paciente?.antecedente, field: "colecistopatia")}
	            					</div>
	            					
	            					<div class="clear"></div>
	            					<div class="span-9 colborder">
	            						<label for="paciente.antecedente.enfermedadNeurologica">Enferm. Neurológicas:</label>
	            						<br/>
	            						${fieldValue(bean: consultaInstance?.paciente?.antecedente, field: "enfermedadNeurologica")}
	            					</div>
	            					<div class="span-9 colborder">
	            						<label for="paciente.antecedente.fiebreProlongada">Fiebre Prolongada:</label>
	            						<br/>
	            						${fieldValue(bean: consultaInstance?.paciente?.antecedente, field: "fiebreProlongada")}
	            					</div>

	            					<div class="clear"></div>
	            					<div class="span-9 colborder">
	            						<label for="paciente.antecedente.colagenopatia">Colagenopatías:</label>
	            						<br/>
	            						${fieldValue(bean: consultaInstance?.paciente?.antecedente, field: "colagenopatia")}
	            					</div>

	            					
	            				</fieldset>
	            				<fieldset>
	            					<legend>Familiares</legend>
	            					<div class="span-9">
	            						<label for="paciente.antecedente.antecedenteFamiliar">Antecedentes Familiares:</label>
	            						<br/>
	            						<g:textArea class="title" name="paciente.antecedente.antecedenteFamiliar" value="${consultaInstance?.paciente?.antecedente?.antecedenteFamiliar}"></g:textArea>
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
