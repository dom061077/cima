
<%@ page import="com.medfire.AntecedenteLabel" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'antecedenteLabel.label', default: 'AntecedenteLabel')}" />
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
                        
                            <g:sortableColumn property="id" title="${message(code: 'antecedenteLabel.id.label', default: 'Id')}" />
                        
                            <g:sortableColumn property="t10Label" title="${message(code: 'antecedenteLabel.t10Label.label', default: 'T10 Label')}" />
                        
                            <g:sortableColumn property="t11Label" title="${message(code: 'antecedenteLabel.t11Label.label', default: 'T11 Label')}" />
                        
                            <g:sortableColumn property="t12Label" title="${message(code: 'antecedenteLabel.t12Label.label', default: 'T12 Label')}" />
                        
                            <g:sortableColumn property="t13Label" title="${message(code: 'antecedenteLabel.t13Label.label', default: 'T13 Label')}" />
                        
                            <g:sortableColumn property="t14Label" title="${message(code: 'antecedenteLabel.t14Label.label', default: 'T14 Label')}" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${antecedenteLabelInstanceList}" status="i" var="antecedenteLabelInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${antecedenteLabelInstance.id}">${fieldValue(bean: antecedenteLabelInstance, field: "id")}</g:link></td>
                        
                            <td>${fieldValue(bean: antecedenteLabelInstance, field: "t10Label")}</td>
                        
                            <td>${fieldValue(bean: antecedenteLabelInstance, field: "t11Label")}</td>
                        
                            <td>${fieldValue(bean: antecedenteLabelInstance, field: "t12Label")}</td>
                        
                            <td>${fieldValue(bean: antecedenteLabelInstance, field: "t13Label")}</td>
                        
                            <td>${fieldValue(bean: antecedenteLabelInstance, field: "t14Label")}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${antecedenteLabelInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
