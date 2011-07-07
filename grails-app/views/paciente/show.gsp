
<%@ page import="com.medfire.Paciente" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'paciente.label', default: 'Paciente')}" />
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
                            <td valign="top" class="name"><g:message code="paciente.id.label" default="Id" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: pacienteInstance, field: "id")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="paciente.apellido.label" default="Apellido" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: pacienteInstance, field: "apellido")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="paciente.codigoPostal.label" default="Codigo Postal" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: pacienteInstance, field: "codigoPostal")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="paciente.cuit.label" default="Cuit" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: pacienteInstance, field: "cuit")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="paciente.dni.label" default="Dni" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: pacienteInstance, field: "dni")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="paciente.domicilio.label" default="Domicilio" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: pacienteInstance, field: "domicilio")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="paciente.email.label" default="Email" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: pacienteInstance, field: "email")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="paciente.estadocivil.label" default="Estadocivil" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean:pacienteInstance,field:"estadoCivil")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="paciente.fechaNacimiento.label" default="Fecha Nacimiento" /></td>
                            
                            <td valign="top" class="value"><g:formatDate date="${pacienteInstance?.fechaNacimiento}" /></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="paciente.medicoEnviante.label" default="Medico Enviante" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: pacienteInstance, field: "medicoEnviante")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="paciente.nombre.label" default="Nombre" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: pacienteInstance, field: "nombre")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="paciente.obrasocial.label" default="Obrasocial" /></td>
                            
                            <td valign="top" class="value"><g:link controller="obraSocial" action="show" id="${pacienteInstance?.obraSocial?.id}">${pacienteInstance?.obraSocial?.descripcion.encodeAsHTML()}</g:link></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="paciente.ocupacion.label" default="Ocupacion" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: pacienteInstance, field: "ocupacion")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="paciente.telefono.label" default="Telefono" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: pacienteInstance, field: "telefono")}</td>
                            
                        </tr>
                    
                    </tbody>
                </table>
            </div>
            <div style="padding: 10px 15px 15px 15px;">
                <g:form>
                    <g:hiddenField name="id" value="${pacienteInstance?.id}" />
                    <g:actionSubmit class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only" action="edit" value="${message(code: 'default.button.edit.label', default: 'Edit')}" />
                    <g:actionSubmit class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
                </g:form>
            </div>
        </div>
    </body>
</html>
