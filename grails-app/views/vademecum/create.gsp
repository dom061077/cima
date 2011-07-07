

<%@ page import="com.medfire.Vademecum" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'vademecum.label', default: 'Vademecum')}" />
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
            <g:hasErrors bean="${vademecumInstance}">
            <div class="errors">
                <g:renderErrors bean="${vademecumInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="accionTerapeutica"><g:message code="vademecum.accionTerapeutica.label" default="Accion Terapeutica" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: vademecumInstance, field: 'accionTerapeutica', 'errors')}">
                                    <g:textField name="accionTerapeutica" value="${vademecumInstance?.accionTerapeutica}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="advertencias"><g:message code="vademecum.advertencias.label" default="Advertencias" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: vademecumInstance, field: 'advertencias', 'errors')}">
                                    <g:textField name="advertencias" value="${vademecumInstance?.advertencias}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="asoc2"><g:message code="vademecum.asoc2.label" default="Asoc2" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: vademecumInstance, field: 'asoc2', 'errors')}">
                                    <g:textField name="asoc2" value="${vademecumInstance?.asoc2}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="composicion"><g:message code="vademecum.composicion.label" default="Composicion" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: vademecumInstance, field: 'composicion', 'errors')}">
                                    <g:select name="composicion.id" from="${com.medfire.Composicion.list()}" optionKey="id" value="${vademecumInstance?.composicion?.id}"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="conservacion"><g:message code="vademecum.conservacion.label" default="Conservacion" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: vademecumInstance, field: 'conservacion', 'errors')}">
                                    <g:textField name="conservacion" value="${vademecumInstance?.conservacion}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="contraIndicacion"><g:message code="vademecum.contraIndicacion.label" default="Contra Indicacion" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: vademecumInstance, field: 'contraIndicacion', 'errors')}">
                                    <g:select name="contraIndicacion.id" from="${com.medfire.Contraindicacion.list()}" optionKey="id" value="${vademecumInstance?.contraIndicacion?.id}"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="dosificacion"><g:message code="vademecum.dosificacion.label" default="Dosificacion" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: vademecumInstance, field: 'dosificacion', 'errors')}">
                                    <g:textField name="dosificacion" value="${vademecumInstance?.dosificacion}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="embarazoyLactancia"><g:message code="vademecum.embarazoyLactancia.label" default="Embarazoy Lactancia" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: vademecumInstance, field: 'embarazoyLactancia', 'errors')}">
                                    <g:textField name="embarazoyLactancia" value="${vademecumInstance?.embarazoyLactancia}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="farmacocinetica"><g:message code="vademecum.farmacocinetica.label" default="Farmacocinetica" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: vademecumInstance, field: 'farmacocinetica', 'errors')}">
                                    <g:textField name="farmacocinetica" value="${vademecumInstance?.farmacocinetica}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="farmacodinamia"><g:message code="vademecum.farmacodinamia.label" default="Farmacodinamia" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: vademecumInstance, field: 'farmacodinamia', 'errors')}">
                                    <g:textField name="farmacodinamia" value="${vademecumInstance?.farmacodinamia}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="farmacologia"><g:message code="vademecum.farmacologia.label" default="Farmacologia" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: vademecumInstance, field: 'farmacologia', 'errors')}">
                                    <g:textField name="farmacologia" value="${vademecumInstance?.farmacologia}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="grupos"><g:message code="vademecum.grupos.label" default="Grupos" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: vademecumInstance, field: 'grupos', 'errors')}">
                                    <g:select name="grupos.id" from="${com.medfire.GrupoTerapeutico.list()}" optionKey="id" value="${vademecumInstance?.grupos?.id}"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="idicaciones"><g:message code="vademecum.idicaciones.label" default="Idicaciones" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: vademecumInstance, field: 'idicaciones', 'errors')}">
                                    <g:textField name="idicaciones" value="${vademecumInstance?.idicaciones}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="laboratorios"><g:message code="vademecum.laboratorios.label" default="Laboratorios" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: vademecumInstance, field: 'laboratorios', 'errors')}">
                                    <g:select name="laboratorios.id" from="${com.medfire.Laboratorio.list()}" optionKey="id" value="${vademecumInstance?.laboratorios?.id}"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="nombreComercial"><g:message code="vademecum.nombreComercial.label" default="Nombre Comercial" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: vademecumInstance, field: 'nombreComercial', 'errors')}">
                                    <g:textField name="nombreComercial" value="${vademecumInstance?.nombreComercial}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="prestaciones"><g:message code="vademecum.prestaciones.label" default="Prestaciones" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: vademecumInstance, field: 'prestaciones', 'errors')}">
                                    <g:textField name="prestaciones" value="${vademecumInstance?.prestaciones}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="principios"><g:message code="vademecum.principios.label" default="Principios" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: vademecumInstance, field: 'principios', 'errors')}">
                                    <g:select name="principios.id" from="${com.medfire.PrincipioActivo.list()}" optionKey="id" value="${vademecumInstance?.principios?.id}"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="reaccionesAdversas"><g:message code="vademecum.reaccionesAdversas.label" default="Reacciones Adversas" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: vademecumInstance, field: 'reaccionesAdversas', 'errors')}">
                                    <g:textField name="reaccionesAdversas" value="${vademecumInstance?.reaccionesAdversas}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="sobreDosificacion"><g:message code="vademecum.sobreDosificacion.label" default="Sobre Dosificacion" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: vademecumInstance, field: 'sobreDosificacion', 'errors')}">
                                    <g:textField name="sobreDosificacion" value="${vademecumInstance?.sobreDosificacion}" />
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
