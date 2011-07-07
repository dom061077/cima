

<%@ page import="com.medfire.Consulta" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'consulta.label', default: 'Consulta')}" />
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
            <g:hasErrors bean="${consultaInstance}">
            <div class="errors">
                <g:renderErrors bean="${consultaInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <g:hiddenField name="id" value="${consultaInstance?.id}" />
                <g:hiddenField name="version" value="${consultaInstance?.version}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="cie10"><g:message code="consulta.cie10.label" default="Cie10" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: consultaInstance, field: 'cie10', 'errors')}">
                                    <g:select name="cie10.id" from="${com.medfire.Cie10.list()}" optionKey="id" value="${consultaInstance?.cie10?.id}"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="contenido"><g:message code="consulta.contenido.label" default="Contenido" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: consultaInstance, field: 'contenido', 'errors')}">
                                    <g:textField name="contenido" value="${consultaInstance?.contenido}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="estado"><g:message code="consulta.estado.label" default="Estado" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: consultaInstance, field: 'estado', 'errors')}">
                                    <g:select name="estado" from="${com.medfire.enums.EstadoConsultaEnum?.values()}" keys="${com.medfire.enums.EstadoConsultaEnum?.values()*.name()}" value="${consultaInstance?.estado?.name()}"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="fecha"><g:message code="consulta.fecha.label" default="Fecha" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: consultaInstance, field: 'fecha', 'errors')}">
                                    <g:datePicker name="fecha" precision="day" value="${consultaInstance?.fecha}"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="historiaClinica"><g:message code="consulta.historiaClinica.label" default="Historia Clinica" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: consultaInstance, field: 'historiaClinica', 'errors')}">
                                    <g:select name="historiaClinica.id" from="${com.medfire.HistoriaClinica.list()}" optionKey="id" value="${consultaInstance?.historiaClinica?.id}"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="profesional"><g:message code="consulta.profesional.label" default="Profesional" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: consultaInstance, field: 'profesional', 'errors')}">
                                    <g:select name="profesional.id" from="${com.medfire.Profesional.list()}" optionKey="id" value="${consultaInstance?.profesional?.id}"  />
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
