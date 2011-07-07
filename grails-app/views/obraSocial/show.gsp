
<%@ page import="com.medfire.ObraSocial" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'obraSocial.label', default: 'ObraSocial')}" />
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
                            <td valign="top" class="name"><g:message code="obraSocial.id.label" default="Id" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: obraSocialInstance, field: "id")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="obraSocial.codigoPostal.label" default="Codigo Postal" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: obraSocialInstance, field: "codigoPostal")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="obraSocial.contacto.label" default="Contacto" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: obraSocialInstance, field: "contacto")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="obraSocial.cuit.label" default="Cuit" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: obraSocialInstance, field: "cuit")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="obraSocial.descripcion.label" default="Descripcion" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: obraSocialInstance, field: "descripcion")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="obraSocial.domicilio.label" default="Domicilio" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: obraSocialInstance, field: "domicilio")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="obraSocial.habilitada.label" default="Habilitada" /></td>
                            
                            <td valign="top" class="value"><g:formatBoolean boolean="${obraSocialInstance?.habilitada}" /></td>
                            
                        </tr>
                    
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="obraSocial.razonSocial.label" default="Razon Social" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: obraSocialInstance, field: "razonSocial")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="obraSocial.telefono.label" default="Telefono" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: obraSocialInstance, field: "telefono")}</td>
                            
                        </tr>
                    
                    </tbody>
                </table>
            </div>
            <div class="buttons">
                <g:form>
                    <g:hiddenField name="id" value="${obraSocialInstance?.id}" />
                    <span class="button"><g:actionSubmit class="edit" action="edit" value="${message(code: 'default.button.edit.label', default: 'Edit')}" /></span>
                    <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
                </g:form>
            </div>
        </div>
    </body>
</html>
