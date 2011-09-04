
<%@ page import="com.medfire.AntecedenteLabel" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'antecedenteLabel.label', default: 'AntecedenteLabel')}" />
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
                            <td valign="top" class="name"><g:message code="antecedenteLabel.id.label" default="Id" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: antecedenteLabelInstance, field: "id")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="antecedenteLabel.t10Label.label" default="T10 Label" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: antecedenteLabelInstance, field: "t10Label")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="antecedenteLabel.t11Label.label" default="T11 Label" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: antecedenteLabelInstance, field: "t11Label")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="antecedenteLabel.t12Label.label" default="T12 Label" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: antecedenteLabelInstance, field: "t12Label")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="antecedenteLabel.t13Label.label" default="T13 Label" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: antecedenteLabelInstance, field: "t13Label")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="antecedenteLabel.t14Label.label" default="T14 Label" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: antecedenteLabelInstance, field: "t14Label")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="antecedenteLabel.t15Label.label" default="T15 Label" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: antecedenteLabelInstance, field: "t15Label")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="antecedenteLabel.t16Label.label" default="T16 Label" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: antecedenteLabelInstance, field: "t16Label")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="antecedenteLabel.t17Label.label" default="T17 Label" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: antecedenteLabelInstance, field: "t17Label")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="antecedenteLabel.t18Label.label" default="T18 Label" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: antecedenteLabelInstance, field: "t18Label")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="antecedenteLabel.t19Label.label" default="T19 Label" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: antecedenteLabelInstance, field: "t19Label")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="antecedenteLabel.t1Label.label" default="T1 Label" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: antecedenteLabelInstance, field: "t1Label")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="antecedenteLabel.t20Label.label" default="T20 Label" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: antecedenteLabelInstance, field: "t20Label")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="antecedenteLabel.t21Label.label" default="T21 Label" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: antecedenteLabelInstance, field: "t21Label")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="antecedenteLabel.t22Label.label" default="T22 Label" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: antecedenteLabelInstance, field: "t22Label")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="antecedenteLabel.t2Label.label" default="T2 Label" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: antecedenteLabelInstance, field: "t2Label")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="antecedenteLabel.t3Label.label" default="T3 Label" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: antecedenteLabelInstance, field: "t3Label")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="antecedenteLabel.t4Label.label" default="T4 Label" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: antecedenteLabelInstance, field: "t4Label")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="antecedenteLabel.t5Label.label" default="T5 Label" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: antecedenteLabelInstance, field: "t5Label")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="antecedenteLabel.t6Label.label" default="T6 Label" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: antecedenteLabelInstance, field: "t6Label")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="antecedenteLabel.t7Label.label" default="T7 Label" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: antecedenteLabelInstance, field: "t7Label")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="antecedenteLabel.t8Label.label" default="T8 Label" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: antecedenteLabelInstance, field: "t8Label")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="antecedenteLabel.t9Label.label" default="T9 Label" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: antecedenteLabelInstance, field: "t9Label")}</td>
                            
                        </tr>
                    
                    </tbody>
                </table>
            </div>
            <div class="buttons">
                <g:form>
                    <g:hiddenField name="id" value="${antecedenteLabelInstance?.id}" />
                    <span class="button"><g:actionSubmit class="edit" action="edit" value="${message(code: 'default.button.edit.label', default: 'Edit')}" /></span>
                    <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
                </g:form>
            </div>
        </div>
    </body>
</html>
