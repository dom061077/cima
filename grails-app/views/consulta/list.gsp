
<%@ page import="com.medfire.Consulta" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'consulta.label', default: 'Consulta')}" />
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
                        
                            <g:sortableColumn property="id" title="${message(code: 'consulta.id.label', default: 'Id')}" />
                        
                            <th><g:message code="consulta.cie10.label" default="Cie10" /></th>
                        
                            <g:sortableColumn property="contenido" title="${message(code: 'consulta.contenido.label', default: 'Contenido')}" />
                        
                            <g:sortableColumn property="estado" title="${message(code: 'consulta.estado.label', default: 'Estado')}" />
                        
                            <g:sortableColumn property="fecha" title="${message(code: 'consulta.fecha.label', default: 'Fecha')}" />
                        
                            <th><g:message code="consulta.historiaClinica.label" default="Historia Clinica" /></th>
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${consultaInstanceList}" status="i" var="consultaInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${consultaInstance.id}">${fieldValue(bean: consultaInstance, field: "id")}</g:link></td>
                        
                            <td>${fieldValue(bean: consultaInstance, field: "cie10")}</td>
                        
                            <td>${fieldValue(bean: consultaInstance, field: "contenido")}</td>
                        
                            <td>${fieldValue(bean: consultaInstance, field: "estado")}</td>
                        
                            <td><g:formatDate date="${consultaInstance.fecha}" /></td>
                        
                            <td>${fieldValue(bean: consultaInstance, field: "historiaClinica")}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${consultaInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
