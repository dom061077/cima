

<%@ page import="com.medfire.Laboratorio" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'laboratorio.label', default: 'Laboratorio')}" />
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
            <g:hasErrors bean="${laboratorioInstance}">
            <div class="errors">
                <g:renderErrors bean="${laboratorioInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <g:hiddenField name="id" value="${laboratorioInstance?.id}" />
                <g:hiddenField name="version" value="${laboratorioInstance?.version}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="codigoPostal"><g:message code="laboratorio.codigoPostal.label" default="Codigo Postal" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: laboratorioInstance, field: 'codigoPostal', 'errors')}">
                                    <g:textField name="codigoPostal" value="${laboratorioInstance?.codigoPostal}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="comentario"><g:message code="laboratorio.comentario.label" default="Comentario" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: laboratorioInstance, field: 'comentario', 'errors')}">
                                    <g:textField name="comentario" value="${laboratorioInstance?.comentario}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="contacto"><g:message code="laboratorio.contacto.label" default="Contacto" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: laboratorioInstance, field: 'contacto', 'errors')}">
                                    <g:textField name="contacto" value="${laboratorioInstance?.contacto}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="localidad"><g:message code="laboratorio.localidad.label" default="Localidad" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: laboratorioInstance, field: 'localidad', 'errors')}">
                                    <g:select name="localidad.id" from="${com.medfire.Localidad.list()}" optionKey="id" value="${laboratorioInstance?.localidad?.id}"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="nombre"><g:message code="laboratorio.nombre.label" default="Nombre" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: laboratorioInstance, field: 'nombre', 'errors')}">
                                    <g:textField name="nombre" value="${laboratorioInstance?.nombre}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="telefono"><g:message code="laboratorio.telefono.label" default="Telefono" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: laboratorioInstance, field: 'telefono', 'errors')}">
                                    <g:textField name="telefono" value="${laboratorioInstance?.telefono}" />
                                </td>
                            </tr>
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:actionSubmit class="save" action="update" value="${message(code: 'default.button.update.label', default: 'Update')}" /></span>
                    <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
