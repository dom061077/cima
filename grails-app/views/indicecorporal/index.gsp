<html>

	<head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
         <title><g:message code="indice.corporal"/></title>
		
	</head>

<body>
	<h1><g:message code="indice.corporal"/></h1>
	
	<g:if test="${flash.error}">
		<div class="ui-state-error ui-corner-all" style="padding: 0pt 0.7em;">
			${flash.error}
		</div>
	</g:if>
	<g:form action="calcular">
		<div class="span-2 spanlabel">
			<label for=""><g:message code="indice.corporal.estatura" default="Estatura (cm)"/> </label>
		</div>
		<div class="span-4">
			<g:textField class="ui-widget ui-corner-all ui-widget-content" name="estatura" id="estaturaId" value="${estatura}" />		
		</div>
		
		<div class="clear"></div>
		
		<div class="span-2 spanlabel">
			<label for=""><g:message code="indice.corporal.estatura" default="Peso (kg)"/> </label>
		</div>
		<div class="span-4">
			<g:textField class="ui-widget ui-corner-all ui-widget-content" name="peso"  id="pesoId" value="${peso}"/>
		</div>
		<div class="clear"></div>
		
		<div class="6">
			<g:
			<p><H1>Indice: ${indice}</H1></p>
			<p><H2>${leyenda}</H2></p>
		</div>
		
		<div class="clear"></div>
	
         <div style="padding: 10px 15px 15px 15px;">
             <g:submitButton name="submit" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only" value="${message(code: 'default.button.submit.label', default: 'Enviar')}" />
         </div>
	
	</g:form>

</body>


</html>