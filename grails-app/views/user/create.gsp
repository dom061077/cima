

<%@ page import="com.medfire.User" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'user.label', default: 'User')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
        <script type="text/javascript" src="${resource(dir:'js/script',file:'jquicombobox.js')}"></script>
        <script type="text/javascript" src="${resource(dir:'js/jquery',file:'jquery.jlookupfield.js')}"></script>
        <script type="text/javascript">
        	$(document).ready(function(){
       	
    			$("#profesionalDescId").autocomplete({
    				source: "<% out << g.createLink(controller:"profesional",action:'listjsonautocomplete')%>",
    				minLength:2,
    				select: function(event,ui){
    					if(ui.item){
    						$("#profesionalId").val(ui.item.id);
    					}
    				}
    	
    			});
            });
        
        </script>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.create.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${userInstance}">
            <div class="ui-state-error ui-corner-all" style="padding: 0pt 0.7em;">
                <g:renderErrors bean="${userInstance}" as="list" />
            </div>
            </g:hasErrors>
            <br/>
            <g:form id="formuser" action="save" >
	                               <g:hasErrors bean="${userInstance}" field="username">
		                              	<div class="ui-state-error ui-corner-all" style="padding: 0pt 0.7em;">
		                           </g:hasErrors>	
                                   <label for="username"><g:message code="user.username.label" default="Username" /></label>
                                   <g:textField class="ui-widget ui-corner-all ui-widget-content" name="username" value="${userInstance?.username}" />
	                                    <g:hasErrors bean="${userInstance}" field="username">
	                                    	<br/>
                                    		<g:renderErrors bean="${userInstance}" as="list" field="username"/>
                                    	</div>
	                                    </g:hasErrors>	
                                   
                                   <br/>
                        
	                                <g:hasErrors bean="${userInstance}" field="userRealName">
		                              	<div class="ui-state-error ui-corner-all" style="padding: 0pt 0.7em;">
		                            </g:hasErrors>	
                                    <label for="userRealName"><g:message code="user.userRealName.label" default="User Real Name" /></label>
                                    <g:textField class="ui-widget ui-corner-all ui-widget-content" name="userRealName" value="${userInstance?.userRealName}" />
	                                    <g:hasErrors bean="${userInstance}" field="userRealName">
	                                    	<br/>
                                    		<g:renderErrors bean="${userInstance}" as="list" field="userRealName"/>
                                    	</div>
	                                    </g:hasErrors>	
                        			<br/>
                        
	                                <g:hasErrors bean="${userInstance}" field="passwd">
		                              	<div class="ui-state-error ui-corner-all" style="padding: 0pt 0.7em;">
		                            </g:hasErrors>	
                                    <label for="passwd"><g:message code="user.passwd.label" default="Passwd" /></label>
                                    
                                    <g:passwordField class="ui-widget ui-corner-all ui-widget-content" name="passwd" value="${userInstance?.passwd}" />
	                                    <g:hasErrors bean="${userInstance}" field="passwd">
	                                    	<br/>
                                    		<g:renderErrors bean="${userInstance}" as="list" field="passwd"/>
                                    	</div>
	                                    </g:hasErrors>	
                        			<br/>
                        
	                                <g:hasErrors bean="${userInstance}" field="profesionalAsignado">
		                              	<div class="ui-state-error ui-corner-all" style="padding: 0pt 0.7em;">
		                            </g:hasErrors>	
                                    <label for="profesionalAsignado"><g:message code="user.profesionalAsignado.label" default="Profesional Asignado" /></label>
                                    <g:textField name="profesionalDesc" id="profesionalDescId"  class="ui-widget ui-corner-all ui-widget-content" value="${userInstance?.profesionalAsignado?.nombre}" />
                                    <g:hiddenField id="profesionalId" name="profesionalAsignado.id" value="${userInstance?.profesionalAsignado?.id}" />
	                                    <g:hasErrors bean="${userInstance}" field="profesionalAsignado">
	                                    	<br/>
                                    		<g:renderErrors bean="${userInstance}" as="list" field="profesionalAsignado"/>
                                    	</div>
	                                    </g:hasErrors>	
                        			<br/>
                        
	                                <g:hasErrors bean="${userInstance}" field="description">
		                              	<div class="ui-state-error ui-corner-all" style="padding: 0pt 0.7em;">
		                            </g:hasErrors>	
                                    <label for="description"><g:message code="user.description.label" default="Description" /></label>
                                    <g:textField class="ui-widget ui-corner-all ui-widget-content" name="description" value="${userInstance?.description}" />
	                                    <g:hasErrors bean="${userInstance}" field="description">
	                                    	<br/>
                                    		<g:renderErrors bean="${userInstance}" as="list" field="description"/>
                                    	</div>
	                                    </g:hasErrors>	
                        			<br/>	
                        
	                                <g:hasErrors bean="${userInstance}" field="email">
		                              	<div class="ui-state-error ui-corner-all" style="padding: 0pt 0.7em;">
		                            </g:hasErrors>	
                                    <label for="email"><g:message code="user.email.label" default="Email" /></label>
                                    <g:textField class="ui-widget ui-corner-all ui-widget-content" name="email" value="${userInstance?.email}" />
	                                    <g:hasErrors bean="${userInstance}" field="email">
	                                    	<br/>
                                    		<g:renderErrors bean="${userInstance}" as="list" field="email"/>
                                    	</div>
	                                    </g:hasErrors>	
                        			<br/>
                        
                                    <label for="emailShow"><g:message code="user.emailShow.label" default="Email Show" /></label>
                                    <g:checkBox name="emailShow" value="${userInstance?.emailShow}" />
                        			<br/>
                        
                                    <label for="esProfesional"><g:message code="user.esProfesional.label" default="Es Profesional" /></label>
                                    <g:checkBox name="esProfesional" value="${userInstance?.esProfesional}" />
                        			<br/>
  
                                   <label for="enabled"><g:message code="user.enabled.label" default="Enabled" /></label>
                                    <g:checkBox name="enabled" value="${userInstance?.enabled}" />
                        			<br/>
									<fieldset>
										<legend>Roles Asignados:</legend>
										<g:each in="${authorityList}">
											${it.authority.encodeAsHTML()}
											<g:checkBox name="${it.authority}"/>
											<br/>
										</g:each>
                        			</fieldset>
                        			<br/>
                <div class="buttons">
                    <span class="button"><g:submitButton name="create" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only" value="${message(code: 'default.button.create.label', default: 'Create')}" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
