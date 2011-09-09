
<%@ page import="com.medfire.User" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'user.label', default: 'User')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        </div>
        <h1><g:message code="default.show.label" args="[entityName]" /></h1>
        
        <div class="span-10 prepend-3 body">
            <g:if test="${flash.message}">
            <div class="message"><h3>${flash.message}</h3></div>
            </g:if>
            <div class="dialog">
            			<div class="span-3">
                            <g:message code="user.id.label" default="Id" />
						</div>
						
						<div class="span-4">                            
                            ${fieldValue(bean: userInstance, field: "id")}
                        </div>    
                        
						<div class="clear"></div>                        
            			<div class="span-3">                        
                            <g:message code="user.username.label" default="Username" />
                        </div>
                        <div class="span-4">    
                            ${fieldValue(bean: userInstance, field: "username")}
                        </div>
                            
						<div class="clear"></div>                            
            			<div class="span-3">                            
                            <g:message code="user.userRealName.label" default="User Real Name" />
                        </div>
                        <div class="span-4">    
                            ${fieldValue(bean: userInstance, field: "userRealName")}
                        </div>
                            
						<div class="clear"></div>                            
            			<div class="span-3">                            
                            <g:message code="user.enabled.label" default="Enabled" />
                        </div>
                        <div class="span-4">    
                            <g:formatBoolean boolean="${userInstance?.enabled}" true="SI" false="NO" />
                        </div>    
                            
						<div class="clear"></div>                            
            			<div class="span-3">                            
                            <g:message code="user.profesionalAsignado.label" default="Profesional Asignado" />
                        </div>
                        <div class="span-4">    
                            ${userInstance?.profesionalAsignado?.nombre?.encodeAsHTML()}
						</div>

						<div class="clear"></div>
            			<div class="span-3">                            
                            <g:message code="user.email.label" default="Email" />
                        </div>
                        <div class="span-4">    
                            ${fieldValue(bean: userInstance, field: "email")}
						</div>
                            
						<div class="clear"></div>                            
            			<div class="span-3">                            
                            <g:message code="user.esProfesional.label" default="Es Profesional" />
                        </div>
                        <div class="span-4">    
                            <g:formatBoolean boolean="${userInstance?.esProfesional}" true="SI" false="NO" />
						</div>                            
                            
						<div class="clear"></div>                            
           				<fieldset> 
            					<legend>Roles:</legend>                           
                                <g:each in="${userInstance?.authorities}" var="a">
                                    ${a?.description.encodeAsHTML()}
                                </g:each>
                        </fieldset>    
                        
                        <div class="clear"></div>      
                                
            </div>
            <div class="buttons">
                <g:form>
                    <g:hiddenField name="id" value="${userInstance?.id}" />
                    <span class="button"><g:actionSubmit class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only" action="editrefactor" value="${message(code: 'default.button.edit.label', default: 'Edit')}" /></span>
                    <span class="button"><g:actionSubmit class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
                </g:form>
            </div>
        </div>
    </body>
</html>
