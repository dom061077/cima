
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
                        
                            <g:sortableColumn property="t1Label" title="${message(code: 'antecedenteLabel.t1Label.label', default: 'T1 Label')}" />
                        
                            <g:sortableColumn property="t2Label" title="${message(code: 'antecedenteLabel.t2Label.label', default: 'T2 Label')}" />
                        
                            <g:sortableColumn property="t3Label" title="${message(code: 'antecedenteLabel.t3Label.label', default: 'T3 Label')}" />
                        
                            <g:sortableColumn property="t4Label" title="${message(code: 'antecedenteLabel.t4Label.label', default: 'T4 Label')}" />
                        
                            <g:sortableColumn property="t5Label" title="${message(code: 'antecedenteLabel.t5Label.label', default: 'T5 Label')}" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${antecedenteLabelInstanceList}" status="i" var="antecedenteLabelInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${antecedenteLabelInstance.id}">${fieldValue(bean: antecedenteLabelInstance, field: "id")}</g:link></td>
                        
                            <td>${fieldValue(bean: antecedenteLabelInstance, field: "t1Label")}</td>
                        
                            <td>${fieldValue(bean: antecedenteLabelInstance, field: "t2Label")}</td>
                        
                            <td>${fieldValue(bean: antecedenteLabelInstance, field: "t3Label")}</td>
                        
                            <td>${fieldValue(bean: antecedenteLabelInstance, field: "t4Label")}</td>
                        
                            <td>${fieldValue(bean: antecedenteLabelInstance, field: "t5Label")}</td>
                        
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
