

<%@ page import="com.medfire.Institucion" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'institucion.label', default: 'Institucion')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
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
            <g:hasErrors bean="${institucionInstance}">
            <div class="errors">
                <g:renderErrors bean="${institucionInstance}" as="list" />
            </div>
            </g:hasErrors>
            <form action="save" method="post" enctype="multipart/form-data">
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="email"><g:message code="institucion.email.label" default="Email:" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: institucionInstance, field: 'email', 'errors')}">
                                    <g:textField name="email" value="${institucionInstance?.email}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="direccion"><g:message code="institucion.direccion.label" default="Direccion:" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: institucionInstance, field: 'direccion', 'errors')}">
                                    <g:textField name="direccion" value="${institucionInstance?.direccion}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="nombre"><g:message code="institucion.nombre.label" default="Nombre:" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: institucionInstance, field: 'nombre', 'errors')}">
                                    <g:textField name="nombre" value="${institucionInstance?.nombre}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="telefonos"><g:message code="institucion.telefonos.label" default="Telefonos:" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: institucionInstance, field: 'telefonos', 'errors')}">
                                    <g:textField name="telefonos" value="${institucionInstance?.telefonos}" />
                                </td>
                            </tr>
                            <tr class="prop">
                            	<td>
                            		<label for="imagen"><g:message code="institucion.telefonos.label" default="Imagen:" /></label>
                            	</td>
                            	<td>
									<input type="file" name="imagen"/>                            	
                            	</td>
                            </tr>
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" /></span>
                </div>
            </form>
        </div>
    </body>
</html>
