

<%@ page import="com.medfire.Laboratorio" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'laboratorio.label', default: 'Laboratorio')}" />
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
            <g:hasErrors bean="${laboratorioInstance}">
            <div class="errors">
                <g:renderErrors bean="${laboratorioInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" >
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
                    <span class="button"><g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
