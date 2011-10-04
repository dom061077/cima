<html>
	<head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'consulta.label', default: 'Consulta')}" />
        <script type="text/javascript" src="${resource(dir:"js/script/consulta",file:"pacientesatendidos.js")}"></script>
        <title>Pacientes Atendidos</title>
        <script type="text/javascript">
        	var buscar=<%out << "${buscar}"%>;
        </script>
	</head>
	<body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
        </div>
		<div class="body">
            <h1>Pacientes Atendidos</h1>
            <g:if test="${flash.message}">
            	<div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${cmdInstance}">
	            <div class="ui-state-error ui-corner-all" style="padding: 0pt 0.7em;">
	                <g:renderErrors bean="${cmdInstance}" as="list" />
	            </div>
            </g:hasErrors>
            <g:form action="pacientesatendidosbuscar">
		            <div class="span-16">
		   				<fieldset>
							<div class="span-2 spanlabel">
								<label for="fechaDesde">Desde:</label>
							</div>
							<div class="span-2">
								<g:textField id="fechaDesdeId" class="ui-widget ui-corner-all ui-widget-content" name="cmdInstance.fechaDesde" value="${cmdInstance.fechaDesde}"/>
							</div>        				
							<div class="clear"></div>
							<div class="span-2 spanlabel">
								<label for="fechaHasta">Hasta:</label>
							</div>
							<div class="span-3">
								<g:textField id="fechaHastaId" class="ui-widget ui-corner-all ui-widget-content" name="cmdInstance.fechaHasta" value="${cmdInstance.fechaHasta}"/>
							</div>        				
							<div class="clear"></div>
							
							<div class="span-2 spanlabel">
								<label for="">Profesional:</label>
							</div>
							<div class="span-3">
								<g:textField class="ui-widget ui-corner-all ui-widget-content" name="profesional" value="${cmdInstance.profesional}"/>
							</div>        				
							<div class="clear"></div>
		
							<div class="span-2 spanlabel">
								<label for="">Obra Social:</label>
							</div>
							<div class="span-3">
								<g:textField class="ui-widget ui-corner-all ui-widget-content" name="obraSocial" value="${cmdInstance.obraSocial}"/>
							</div>        				
							<div class="clear"></div>
							
							<div class="span-2 spanlabel">
								<label for="">Diagnóstico:</label>
							</div>
							<div class="span-3">
								<g:textField class="ui-widget ui-corner-all ui-widget-content" name="cie10" value="${cmdInstance.cie10}"/>
							</div>        				
							<div class="span-3"><g:submitButton class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only"  name="create" 
															value="${message(code: 'default.button.search.label', default: 'Create')}"/> </div>
												        				
												        				
		      			</fieldset>
		      		</div>	
      		</g:form>
      		<div class="clear"></div>
            <div class="span-16">
	            <div id="tabs">
	            	<ul>
	            		<li><a href="#tabs-1">General</a></li>
	            		<li><a href="#tabs-2">Por Profesional</a></li>           		
	            		<li><a href="#tabs-3">Diagnóstico</a></li>            		
	            		<li><a href="#tabs-4">Gráficos</a></li>
	            	</ul>
	        		<div id="tabs-1">
	        			<div id="generalgrid">
	        			</div>
	        			<div id="pagergeneral">
	        			</div>
	        		</div>
	        		<div id="tabs-2">
	        			<div id="profesionalgrid">
	        			</div>
	        			<div id="pagerprofesional">
	        			</div>
	        		</div>    
	        		<div id="tabs-3">
	        			<div id="diagnosticogrid">
	        			</div>
	        			<div id="pagerdiagnostico">
	        			</div>
	        		</div>    
	        		<div id="tabs-4">
	        			<div id="graficos">
	        			</div>
	        			<div id="pagergraficos">
	        			</div>
	        		</div>    
	        		    
	            </div>
            </div>
		</div>	
	</body>
</html>