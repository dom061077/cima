
<%@ page import="com.medfire.Profesional" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'profesional.label', default: 'Profesional')}" />
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
                            <td valign="top" class="name"><g:message code="profesional.id.label" default="Id" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: profesionalInstance, field: "id")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="profesional.matricula.label" default="Matricula" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: profesionalInstance, field: "matricula")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="profesional.nombre.label" default="Nombre" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: profesionalInstance, field: "nombre")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="profesional.observaciones.label" default="Observaciones" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: profesionalInstance, field: "observaciones")}</td>
                            
                        </tr>
                    
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="profesional.domicilio.label" default="Domicilio" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: profesionalInstance, field: "domicilio")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="profesional.codigoPostal.label" default="Codigo Postal" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: profesionalInstance, field: "codigoPostal")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="profesional.localidad.label" default="Localidad" /></td>
                            
                            <td valign="top" class="value"><g:link controller="localidad" action="show" id="${profesionalInstance?.localidad?.id}">${profesionalInstance?.localidad?.encodeAsHTML()}</g:link></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="profesional.telefono.label" default="Telefono" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: profesionalInstance, field: "telefono")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="profesional.fechaNacimiento.label" default="Fecha Nacimiento" /></td>
                            
                            <td valign="top" class="value"><g:formatDate date="${profesionalInstance?.fechaNacimiento}" /></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="profesional.numeroDocumento.label" default="Numero Documento" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: profesionalInstance, field: "numeroDocumento")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="profesional.tipoDocumento.label" default="Tipo Documento" /></td>
                            
                            <td valign="top" class="value">${profesionalInstance?.tipoDocumento?.encodeAsHTML()}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="profesional.codigoIva.label" default="Codigo Iva" /></td>
                            
                            <td valign="top" class="value">${profesionalInstance?.codigoIva?.encodeAsHTML()}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="profesional.cuit.label" default="Cuit" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: profesionalInstance, field: "cuit")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="profesional.fechaIngreso.label" default="Fecha Ingreso" /></td>
                            
                            <td valign="top" class="value"><g:formatDate date="${profesionalInstance?.fechaIngreso}" /></td>
                            
                        </tr>
                    
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="profesional.especialidad.label" default="Especialidad" /></td>
                            
                            <td valign="top" class="value"><g:link controller="especialidadMedica" action="show" id="${profesionalInstance?.especialidad?.id}">${profesionalInstance?.especialidad?.encodeAsHTML()}</g:link></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="profesional.activo.label" default="Activo" /></td>
                            
                            <td valign="top" class="value"><g:formatBoolean boolean="${profesionalInstance?.activo}" /></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="profesional.tipoMatricula.label" default="Tipo Matricula" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: profesionalInstance, field: "tipoMatricula")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="profesional.sexo.label" default="Sexo" /></td>
                            
                            <td valign="top" class="value"><g:formatBoolean boolean="${profesionalInstance?.sexo}" /></td>
                            
                        </tr>
                    
                    </tbody>
                </table>
            </div>
            <div class="buttons">
                <g:form>
                    <g:hiddenField name="id" value="${profesionalInstance?.id}" />
                    <span class="button"><g:actionSubmit class="edit" action="edit" value="${message(code: 'default.button.edit.label', default: 'Edit')}" /></span>
                    <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
                </g:form>
            </div>
        </div>
    </body>
</html>
