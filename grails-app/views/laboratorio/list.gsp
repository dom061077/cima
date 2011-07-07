
<%@ page import="com.medfire.Laboratorio" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'laboratorio.label', default: 'Laboratorio')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                            <g:sortableColumn property="id" title="${message(code: 'laboratorio.id.label', default: 'Id')}" />
                        
                            <g:sortableColumn property="codigoPostal" title="${message(code: 'laboratorio.codigoPostal.label', default: 'Codigo Postal')}" />
                        
                            <g:sortableColumn property="comentario" title="${message(code: 'laboratorio.comentario.label', default: 'Comentario')}" />
                        
                            <g:sortableColumn property="contacto" title="${message(code: 'laboratorio.contacto.label', default: 'Contacto')}" />
                        
                            <th><g:message code="laboratorio.localidad.label" default="Localidad" /></th>
                        
                            <g:sortableColumn property="nombre" title="${message(code: 'laboratorio.nombre.label', default: 'Nombre')}" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${laboratorioInstanceList}" status="i" var="laboratorioInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${laboratorioInstance.id}">${fieldValue(bean: laboratorioInstance, field: "id")}</g:link></td>
                        
                            <td>${fieldValue(bean: laboratorioInstance, field: "codigoPostal")}</td>
                        
                            <td>${fieldValue(bean: laboratorioInstance, field: "comentario")}</td>
                        
                            <td>${fieldValue(bean: laboratorioInstance, field: "contacto")}</td>
                        
                            <td>${fieldValue(bean: laboratorioInstance, field: "localidad")}</td>
                        
                            <td>${fieldValue(bean: laboratorioInstance, field: "nombre")}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${laboratorioInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
