
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'user.label', default: 'User')}" />
        <title><g:message code="user.changepassd.label" args="[entityName]" /></title>
        <script type="text/javascript">
        	$(document).ready(function(){
            	$('#oldPasswordId').focus();	
            });
        </script>
	</head>
<body>
	<h1><g:message code="default.create.label" args="[entityName]" /></h1>
	<div class="span-16 prepend-2">
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${userInstance}">
            <div class="ui-state-error ui-corner-all" style="padding: 0pt 0.7em;">
                <g:renderErrors bean="${userInstance}" as="list" />
            </div>
            </g:hasErrors>
            <br/>
	
	
		<g:form action="change">
			<fieldset>
					<div class="span-3 spanlabel">
						<label for="oldPassword"><g:message code="user.oldpassword.label" /></label>
					</div>
					<div class="span-4">
						<g:passwordField id="oldPasswordId" name="oldPassword" id="oldPasswordId" value="${userInstance.oldPassword}"/>
					</div>
					<div class="clear"></div>
					
					<div class="span-3 spanlabel">
						<label for="newPassword"><g:message code="user.newpassword.label" /></label>
					</div>
					<div class="span-4">
						<g:passwordField name="newPassword" id="newPasswordId" value="${userInstance.newPassword}"/>
					</div>
					<div class="clear"></div>
		
					<div class="span-3">
						<label for="passwordRepeat"><g:message code="user.passwordrepeat.label" /></label>
					</div>
					<div class="span-4">
						<g:passwordField name="passwordRepeat" id="passwordRepeatId" value="${userInstance.passwordRepeat}"/>
					</div>
					<div class="clear"></div>
			</fieldset>		
            <div class="buttons">
                <span class="button"><g:submitButton name="changexx" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only" value="${message(code: 'default.button.change.label', default: 'Cambiar')}" /></span>
            </div>
		</g:form>
	</div>
</body>

</html>	