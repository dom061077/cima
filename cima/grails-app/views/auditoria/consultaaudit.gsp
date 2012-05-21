<html>
	<head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'consulta.label', default: 'Consulta')}" />
        <script type="text/javascript" src="${resource(dir:"js/script/auditoria",file:"consulta.js")}"></script>
        <script type="text/javascript" src="${resource(dir:'js/jquery',file:'jquery.jlookupfield.js')}"></script>
        <script type="text/javascript" src="${resource(dir:'js/jquery/chart',file:'jgcharts.pack.js')}"></script>        
        <script type="text/javascript" src="${resource(dir:'js/jquery/chart',file:'jquery.metadata.pack.js')}"></script>        
        <title>Pacientes Atendidos por Grupo de Diagnóstico</title>
	</head>
	<body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
        </div>
		<div class="body">
            <h1>Auditoría</h1>
            <g:if test="${flash.message}">
            	<div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${cmdInstance}">
	            <div class="ui-state-error ui-corner-all" style="padding: 0pt 0.7em;">
	                <g:renderErrors bean="${cmdInstance}" as="list" />
	            </div>
            </g:hasErrors>
            <g:form action="consultabuscar">
	   				<fieldset>
		            <div class="span-20">
							<div class="span-2 spanlabel">
								<label for="fechaDesde">Desde:</label>
							</div>
							<div class="span-2">
								<g:textField id="fechaDesdeId" class="ui-widget ui-corner-all ui-widget-content" name="fechaDesde" value="${cmdInstance.fechaDesde}"/>
							</div>        				
							<div class="clear"></div>
							<div class="span-2 spanlabel">
								<label for="fechaHasta">Hasta:</label>
							</div>
							<div class="span-3">
								<g:textField id="fechaHastaId" class="ui-widget ui-corner-all ui-widget-content" name="fechaHasta" value="${cmdInstance.fechaHasta}"/>
							</div>        				
							<div class="clear"></div>
							
							<div class="span-2 spanlabel">
								<label for="cie10">Usuario:</label>
							</div>
							<div class="span-4">
									<g:select class="ui-widget ui-corner-all ui-widget-content" id="usuarioIdId"  name="usuarioId" from="${com.medfire.User.list()}"
											optionKey="id" 
		                                 	optionValue="userRealName" value="${cmdInstance?.usuarioId}" noSelection="['': '']" />							
		                    </div>   

							<div class="clear"></div>
							<div class="span-2 spanlabel">
								<label for="cie10">Transacción:</label>
							</div>
							<div class="span-4">
									<g:select class="ui-widget ui-corner-all ui-widget-content" id="tipoTransaccionId"  name="tipoTransaccion" from="${com.medfire.enums.TipoTransaccionEnum?.list()}" 
		                                   	 optionValue="name" value="${cmdInstance?.tipoTransaccion}" noSelection="['': '']" />									
							</div>
							<div class="clear"></div>

							
							<div class="span-3  prepend-2"><g:submitButton class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only"  name="create" 
															value="${message(code: 'default.button.search.label', default: 'Create')}"/> </div>
					</div>   
												        				
	      			</fieldset>
		      			
      		</g:form>
      		<div class="clear"></div>
            
			<table>
				<thead>
					<tr>
						<g:sortableColumn property="actor" title="Usuario" />
						<g:sortableColumn property="class_name" title="Clase de Dominio" />
						<g:sortableColumn property="date_created" title="Fecha Creación" />
						<g:sortableColumn property="event_name" title="Tipo Evento" />		
						<g:sortableColumn property="last_updated" title="Fecha Modificación" />
						<g:sortableColumn property="new_value" title="Nuevo Valor" />													
						<g:sortableColumn property="old_value" title="Viejo Valor" />					
						<g:sortableColumn property="persisted_object_id" title="Id Obj. Persistido" />					
						<g:sortableColumn property="persisted_object_version" title="Versión objeto persistido" />
						<g:sortableColumn property="property_name" title="Nombre de la Propiedad" />
						<g:sortableColumn property="uri" title="URI" />															
						<th>&nbsp;</th>
					</tr>
				</thead>
				<tbody>
					<g:each in="${list}" status="i" var="audit">
						<tr>
							<td>${audit["actor"]}</td>
							<td>${audit["class_name"]}</td>
							<td>${audit["date_created"]}</td>
							<td>${audit["event_name"]}</td>
							<td>${audit["last_updated"]}</td>							
							<td>${audit["new_value"]}</td>
							<td>${audit["old_value"]}</td>
							<td>${audit["persisted_object_id"]}</td>
							<td>${audit["persisted_object_version"]}</td>
							<td>${audit["property_name"]}</td>
							<td>${audit["uri"]}</td>																												
						</tr>
					</g:each>
				</tbody>
			</table>
			
            
		</div>	
	</body>
</html>