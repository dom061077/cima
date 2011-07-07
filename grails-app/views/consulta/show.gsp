
<%@ page import="com.medfire.Consulta" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'consulta.label', default: 'Consulta')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.show.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="dialog">
                <table>
                    <tbody>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="consulta.id.label" default="Id" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: consultaInstance, field: "id")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="consulta.cie10.label" default="Cie10" /></td>
                            
                            <td valign="top" class="value"><g:link controller="cie10" action="show" id="${consultaInstance?.cie10?.id}">${consultaInstance?.cie10?.encodeAsHTML()}</g:link></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="consulta.contenido.label" default="Contenido" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: consultaInstance, field: "contenido")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="consulta.estado.label" default="Estado" /></td>
                            
                            <td valign="top" class="value">${consultaInstance?.estado?.encodeAsHTML()}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="consulta.fecha.label" default="Fecha" /></td>
                            
                            <td valign="top" class="value"><g:formatDate date="${consultaInstance?.fecha}" /></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="consulta.historiaClinica.label" default="Historia Clinica" /></td>
                            
                            <td valign="top" class="value"><g:link controller="historiaClinica" action="show" id="${consultaInstance?.historiaClinica?.id}">${consultaInstance?.historiaClinica?.encodeAsHTML()}</g:link></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="consulta.profesional.label" default="Profesional" /></td>
                            
                            <td valign="top" class="value"><g:link controller="profesional" action="show" id="${consultaInstance?.profesional?.id}">${consultaInstance?.profesional?.encodeAsHTML()}</g:link></td>
                            
                        </tr>
                    
                    </tbody>
                </table>
            </div>
            <div class="buttons">
                <g:form>
                    <g:hiddenField name="id" value="${consultaInstance?.id}" />
                    <span class="button"><g:actionSubmit class="edit" action="edit" value="${message(code: 'default.button.edit.label', default: 'Edit')}" /></span>
                    <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
                </g:form>
            </div>
        </div>
    </body>
</html>
