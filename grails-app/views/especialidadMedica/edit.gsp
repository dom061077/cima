

<%@ page import="com.medfire.EspecialidadMedica" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'especialidadMedica.label', default: 'EspecialidadMedica')}" />
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
            <g:hasErrors bean="${especialidadMedicaInstance}">
            <div class="errors">
                <g:renderErrors bean="${especialidadMedicaInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <g:hiddenField name="id" value="${especialidadMedicaInstance?.id}" />
                <g:hiddenField name="version" value="${especialidadMedicaInstance?.version}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="descripcion"><g:message code="especialidadMedica.descripcion.label" default="Descripcion" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: especialidadMedicaInstance, field: 'descripcion', 'errors')}">
                                    <g:textField name="descripcion" value="${especialidadMedicaInstance?.descripcion}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="profesionales"><g:message code="especialidadMedica.profesionales.label" default="Profesionales" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: especialidadMedicaInstance, field: 'profesionales', 'errors')}">
                                    
<ul>
<g:each in="${especialidadMedicaInstance?.profesionales?}" var="p">
    <li><g:link controller="profesional" action="show" id="${p.id}">${p?.encodeAsHTML()}</g:link></li>
</g:each>
</ul>
<g:link controller="profesional" action="create" params="['especialidadMedica.id': especialidadMedicaInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'profesional.label', default: 'Profesional')])}</g:link>

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
