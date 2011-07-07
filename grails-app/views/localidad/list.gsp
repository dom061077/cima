
<%@ page import="com.medfire.Localidad" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'localidad.label', default: 'Localidad')}" />
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
                        
                            <g:sortableColumn property="id" title="${message(code: 'localidad.id.label', default: 'Id')}" />
                        
                            <g:sortableColumn property="codigoPostal" title="${message(code: 'localidad.codigoPostal.label', default: 'Codigo Postal')}" />
                        
                            <g:sortableColumn property="nombre" title="${message(code: 'localidad.nombre.label', default: 'Nombre')}" />
                        
                            <th><g:message code="localidad.provincia.label" default="Provincia" /></th>
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${localidadInstanceList}" status="i" var="localidadInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${localidadInstance.id}">${fieldValue(bean: localidadInstance, field: "id")}</g:link></td>
                        
                            <td>${fieldValue(bean: localidadInstance, field: "codigoPostal")}</td>
                        
                            <td>${fieldValue(bean: localidadInstance, field: "nombre")}</td>
                        
                            <td>${fieldValue(bean: localidadInstance, field: "provincia")}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${localidadInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
