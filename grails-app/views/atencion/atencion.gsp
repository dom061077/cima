
  <%@ page import="com.medfire.Event" ;
  %>

<html>
	<head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'paciente.label', default: 'Paciente')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>

		<link rel="stylesheet" type="text/css" media="screen" href="${g.resource(dir:'js/jquery-ui/js/src/css',file:'ui.jqgrid.css')}" />
        <link rel="stylesheet" type="text/css" media="screen" href="${g.resource(dir:'js/jquery-ui/js/src/css',file:'jquery.searchFilter.css')}" />
        <script type="text/javascript" src="${g.resource(dir:'js/jquery-ui/js/src/i18n',file:'grid.locale-es.js')}"></script>
        <script type="text/javascript" src="${g.resource(dir:'js/jquery-ui/js',file:'jquery.jqGrid.min.js')}"></script>
        <script type="text/javascript">
        <%					  
				  out << "var locturnos='"+g.createLink(controller:"event",action:"operation")+"';";
		%>
		</script>
        <script type="text/javascript" src="${g.resource(dir:'js/script',file:'scriptatencion.js')}"></script>        
        
        
        <style type="text/css">
		        #container {
					background:	#999;
					height:		100%;
					margin:		0 auto;
					width:		100%;
					max-width:	900px;
					min-width:	700px;
					_width:		700px; /* min-width for IE6 */
				}
				.pane {
					display:	none; /* will appear when layout inits */
				}
        
        </style>
        
        <script type="text/javascript">
        </script>
        
	</head>

<body>

					

						 
							
			
			<div class="hidden" id="dialog-form" title="Cambiar estado turno">
				
			
				<form id="formturnosId">
					<table>
						<tr class="prop">
							<td>
								<label for='pacienteturno'>Paciente:</label>
							</td>
							<td>
								<input type="text" disabled name='pacienteturno' id='pacienteturnoId' />
								<input type="hidden" name="id" id="turnoId" />
								<input type="hidden" name="cmd" value="update" />
								<input type="hidden" name="version" id="versionId"/>
							</td>
						</tr>
						<tr class="prop">
							<td valign="top" class="name">
								<label for="fechaturno">Fecha y Hora del Turno:</label>
							</td>
							<td>
								<input type="text" disabled id="fechaturnoId"/>
							</td>
						</tr>								
						
						<tr class="prop">
							<td valign="top" class="name">
								<label for='estado'>Estado del Turno:</label>	
							</td>
							<td>
								<g:select name="estado" id="estadoeventId" from="${EstadoEvent.list()}" 
														optionValue="name" />
							</td>							
						</tr>
					</table>									
				</form>
			</div>


</body>
</html>