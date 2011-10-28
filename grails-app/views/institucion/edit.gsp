

<%@ page import="com.medfire.Institucion" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'institucion.label', default: 'Institucion')}" />
        <title><g:message code="default.edit.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.edit.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${institucionInstance}">
            <div class="errors">
                <g:renderErrors bean="${institucionInstance}" as="list" />
            </div>
            </g:hasErrors>
            <form method="post" enctype="multipart/form-data" >
                <g:hiddenField name="id" value="${institucionInstance?.id}" />
                <g:hiddenField name="version" value="${institucionInstance?.version}" />
                <div class="dialog">
                        
                            <div class="span-2 spanlabel">
                                  <label for="email"><g:message code="institucion.email.label" default="Email:" /></label>
                            </div>
                            <div class="span-4">      
                                  <g:textField class="ui-widget ui-corner-all ui-widget-content" name="email" value="${institucionInstance?.email}" />
                            </div>
                            <div class="clear"></div>
                        
                        	<div class="span-2 spanlabel">
                                  <label for="direccion"><g:message code="institucion.direccion.label" default="Dirección:" /></label>
                            </div>      
                            <div class="span-4">
                                  <g:textField class="ui-widget ui-corner-all ui-widget-content" name="direccion" value="${institucionInstance?.direccion}" />
                        	</div>
                            <div class="clear"></div>                        	
                        	
                        	<div class="span-2 spanlabel">
                                  <label for="nombre"><g:message code="institucion.nombre.label" default="Nombre:" /></label>
                            </div>
                            <div class="span-4">      
                                  <g:textField class="ui-widget ui-corner-all ui-widget-content" name="nombre" value="${institucionInstance?.nombre}" />
                        	</div>
                            <div class="clear"></div>                        	


                        	<div class="span-2 spanlabel">
                                  <label for="telefonos"><g:message code="institucion.telefonos.label" default="Teléfonos:" /></label>
                            </div>
                            <div class="span-4">      
                                  <g:textField class="ui-widget ui-corner-all ui-widget-content" name="telefonos" value="${institucionInstance?.telefonos}" />
                        	</div>
                            <div class="clear"></div>                        	


                        	<div class="span-2 spanlabel">
                           		  <label for="imagen"><g:message code="institucion.telefonos.label" default="Imagen:" /></label>
                            </div>
                            <div class="span-4">      
								  <input class="ui-widget ui-corner-all ui-widget-content" type="file" name="imagen"/>                            	
                        	</div>
                            <div class="clear"></div>                        	
                        
                </div>
                <div class="buttons">
                    <span class="button">
                    	<g:actionSubmit class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only" action="update" value="${message(code: 'default.button.update.label', default: 'Update')}" />
                    </span>
<%--                    <span class="button">--%>
<%--                    		<g:actionSubmit class="ui-widget ui-corner-all ui-widget-content" action="delete"	value="${message(code: 'default.button.delete.label', default: 'Delete')}" 	--%>
<%--        		            		onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />--%>
<%--                    </span>--%>
                </div>
            </form>
        </div>
    </body>
</html>
