
<%@ page import="com.medfire.Vademecum" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'vademecum.label', default: 'Vademecum')}" />
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
                            <td valign="top" class="name"><g:message code="vademecum.id.label" default="Id" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: vademecumInstance, field: "id")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="vademecum.accionTerapeutica.label" default="Accion Terapeutica" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: vademecumInstance, field: "accionTerapeutica")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="vademecum.advertencias.label" default="Advertencias" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: vademecumInstance, field: "advertencias")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="vademecum.asoc2.label" default="Asoc2" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: vademecumInstance, field: "asoc2")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="vademecum.composicion.label" default="Composicion" /></td>
                            
                            <td valign="top" class="value"><g:link controller="composicion" action="show" id="${vademecumInstance?.composicion?.id}">${vademecumInstance?.composicion?.encodeAsHTML()}</g:link></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="vademecum.conservacion.label" default="Conservacion" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: vademecumInstance, field: "conservacion")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="vademecum.contraIndicacion.label" default="Contra Indicacion" /></td>
                            
                            <td valign="top" class="value"><g:link controller="contraindicacion" action="show" id="${vademecumInstance?.contraIndicacion?.id}">${vademecumInstance?.contraIndicacion?.encodeAsHTML()}</g:link></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="vademecum.dosificacion.label" default="Dosificacion" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: vademecumInstance, field: "dosificacion")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="vademecum.embarazoyLactancia.label" default="Embarazoy Lactancia" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: vademecumInstance, field: "embarazoyLactancia")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="vademecum.farmacocinetica.label" default="Farmacocinetica" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: vademecumInstance, field: "farmacocinetica")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="vademecum.farmacodinamia.label" default="Farmacodinamia" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: vademecumInstance, field: "farmacodinamia")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="vademecum.farmacologia.label" default="Farmacologia" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: vademecumInstance, field: "farmacologia")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="vademecum.grupos.label" default="Grupos" /></td>
                            
                            <td valign="top" class="value"><g:link controller="grupoTerapeutico" action="show" id="${vademecumInstance?.grupos?.id}">${vademecumInstance?.grupos?.encodeAsHTML()}</g:link></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="vademecum.idicaciones.label" default="Idicaciones" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: vademecumInstance, field: "idicaciones")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="vademecum.laboratorios.label" default="Laboratorios" /></td>
                            
                            <td valign="top" class="value"><g:link controller="laboratorio" action="show" id="${vademecumInstance?.laboratorios?.id}">${vademecumInstance?.laboratorios?.encodeAsHTML()}</g:link></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="vademecum.nombreComercial.label" default="Nombre Comercial" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: vademecumInstance, field: "nombreComercial")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="vademecum.prestaciones.label" default="Prestaciones" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: vademecumInstance, field: "prestaciones")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="vademecum.principios.label" default="Principios" /></td>
                            
                            <td valign="top" class="value"><g:link controller="principioActivo" action="show" id="${vademecumInstance?.principios?.id}">${vademecumInstance?.principios?.encodeAsHTML()}</g:link></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="vademecum.reaccionesAdversas.label" default="Reacciones Adversas" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: vademecumInstance, field: "reaccionesAdversas")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="vademecum.sobreDosificacion.label" default="Sobre Dosificacion" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: vademecumInstance, field: "sobreDosificacion")}</td>
                            
                        </tr>
                    
                    </tbody>
                </table>
            </div>
            <div class="buttons">
                <g:form>
                    <g:hiddenField name="id" value="${vademecumInstance?.id}" />
                    <span class="button"><g:actionSubmit class="edit" action="edit" value="${message(code: 'default.button.edit.label', default: 'Edit')}" /></span>
                    <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
                </g:form>
            </div>
        </div>
    </body>
</html>
