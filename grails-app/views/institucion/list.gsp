
<%@ page import="com.medfire.Institucion" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'institucion.label', default: 'Institucion')}" />
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
                        
                            <g:sortableColumn property="id" title="${message(code: 'institucion.id.label', default: 'Id')}" />
                        
                            <g:sortableColumn property="email" title="${message(code: 'institucion.email.label', default: 'Email')}" />
                        
                            <g:sortableColumn property="direccion" title="${message(code: 'institucion.direccion.label', default: 'Direccion')}" />
                        
                            <g:sortableColumn property="nombre" title="${message(code: 'institucion.nombre.label', default: 'Nombre')}" />
                        
                            <g:sortableColumn property="telefonos" title="${message(code: 'institucion.telefonos.label', default: 'Telefonos')}" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${institucionInstanceList}" status="i" var="institucionInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${institucionInstance.id}">${fieldValue(bean: institucionInstance, field: "id")}</g:link></td>
                        
                            <td>${fieldValue(bean: institucionInstance, field: "email")}</td>
                        
                            <td>${fieldValue(bean: institucionInstance, field: "direccion")}</td>
                        
                            <td>${fieldValue(bean: institucionInstance, field: "nombre")}</td>
                        
                            <td>${fieldValue(bean: institucionInstance, field: "telefonos")}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${institucionInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
