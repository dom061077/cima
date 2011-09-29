

<%@ page import="com.medfire.AntecedenteLabel" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'antecedenteLabel.label', default: 'AntecedenteLabel')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
        </div>
	    <h1><g:message code="default.create.label" args="[entityName]" /></h1>
        
        <div class="body">
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${antecedenteLabelInstance}">
            <div class="errors">
                <g:renderErrors bean="${antecedenteLabelInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="t1Label"><g:message code="antecedenteLabel.t1Label.label" default="T1 Label" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: antecedenteLabelInstance, field: 't1Label', 'errors')}">
                                    <g:textField name="t1Label" value="${antecedenteLabelInstance?.t1Label}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="t2Label"><g:message code="antecedenteLabel.t2Label.label" default="T2 Label" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: antecedenteLabelInstance, field: 't2Label', 'errors')}">
                                    <g:textField name="t2Label" value="${antecedenteLabelInstance?.t2Label}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="t3Label"><g:message code="antecedenteLabel.t3Label.label" default="T3 Label" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: antecedenteLabelInstance, field: 't3Label', 'errors')}">
                                    <g:textField name="t3Label" value="${antecedenteLabelInstance?.t3Label}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="t4Label"><g:message code="antecedenteLabel.t4Label.label" default="T4 Label" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: antecedenteLabelInstance, field: 't4Label', 'errors')}">
                                    <g:textField name="t4Label" value="${antecedenteLabelInstance?.t4Label}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="t5Label"><g:message code="antecedenteLabel.t5Label.label" default="T5 Label" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: antecedenteLabelInstance, field: 't5Label', 'errors')}">
                                    <g:textField name="t5Label" value="${antecedenteLabelInstance?.t5Label}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="t6Label"><g:message code="antecedenteLabel.t6Label.label" default="T6 Label" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: antecedenteLabelInstance, field: 't6Label', 'errors')}">
                                    <g:textField name="t6Label" value="${antecedenteLabelInstance?.t6Label}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="t7Label"><g:message code="antecedenteLabel.t7Label.label" default="T7 Label" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: antecedenteLabelInstance, field: 't7Label', 'errors')}">
                                    <g:textField name="t7Label" value="${antecedenteLabelInstance?.t7Label}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="t8Label"><g:message code="antecedenteLabel.t8Label.label" default="T8 Label" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: antecedenteLabelInstance, field: 't8Label', 'errors')}">
                                    <g:textField name="t8Label" value="${antecedenteLabelInstance?.t8Label}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="t9Label"><g:message code="antecedenteLabel.t9Label.label" default="T9 Label" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: antecedenteLabelInstance, field: 't9Label', 'errors')}">
                                    <g:textField name="t9Label" value="${antecedenteLabelInstance?.t9Label}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="t10Label"><g:message code="antecedenteLabel.t10Label.label" default="T10 Label" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: antecedenteLabelInstance, field: 't10Label', 'errors')}">
                                    <g:textField name="t10Label" value="${antecedenteLabelInstance?.t10Label}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="t11Label"><g:message code="antecedenteLabel.t11Label.label" default="T11 Label" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: antecedenteLabelInstance, field: 't11Label', 'errors')}">
                                    <g:textField name="t11Label" value="${antecedenteLabelInstance?.t11Label}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="t12Label"><g:message code="antecedenteLabel.t12Label.label" default="T12 Label" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: antecedenteLabelInstance, field: 't12Label', 'errors')}">
                                    <g:textField name="t12Label" value="${antecedenteLabelInstance?.t12Label}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="t13Label"><g:message code="antecedenteLabel.t13Label.label" default="T13 Label" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: antecedenteLabelInstance, field: 't13Label', 'errors')}">
                                    <g:textField name="t13Label" value="${antecedenteLabelInstance?.t13Label}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="t14Label"><g:message code="antecedenteLabel.t14Label.label" default="T14 Label" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: antecedenteLabelInstance, field: 't14Label', 'errors')}">
                                    <g:textField name="t14Label" value="${antecedenteLabelInstance?.t14Label}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="t15Label"><g:message code="antecedenteLabel.t15Label.label" default="T15 Label" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: antecedenteLabelInstance, field: 't15Label', 'errors')}">
                                    <g:textField name="t15Label" value="${antecedenteLabelInstance?.t15Label}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="t16Label"><g:message code="antecedenteLabel.t16Label.label" default="T16 Label" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: antecedenteLabelInstance, field: 't16Label', 'errors')}">
                                    <g:textField name="t16Label" value="${antecedenteLabelInstance?.t16Label}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="t17Label"><g:message code="antecedenteLabel.t17Label.label" default="T17 Label" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: antecedenteLabelInstance, field: 't17Label', 'errors')}">
                                    <g:textField name="t17Label" value="${antecedenteLabelInstance?.t17Label}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="t18Label"><g:message code="antecedenteLabel.t18Label.label" default="T18 Label" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: antecedenteLabelInstance, field: 't18Label', 'errors')}">
                                    <g:textField name="t18Label" value="${antecedenteLabelInstance?.t18Label}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="t19Label"><g:message code="antecedenteLabel.t19Label.label" default="T19 Label" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: antecedenteLabelInstance, field: 't19Label', 'errors')}">
                                    <g:textField name="t19Label" value="${antecedenteLabelInstance?.t19Label}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="t20Label"><g:message code="antecedenteLabel.t20Label.label" default="T20 Label" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: antecedenteLabelInstance, field: 't20Label', 'errors')}">
                                    <g:textField name="t20Label" value="${antecedenteLabelInstance?.t20Label}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="t21Label"><g:message code="antecedenteLabel.t21Label.label" default="T21 Label" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: antecedenteLabelInstance, field: 't21Label', 'errors')}">
                                    <g:textField name="t21Label" value="${antecedenteLabelInstance?.t21Label}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="t22Label"><g:message code="antecedenteLabel.t22Label.label" default="T22 Label" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: antecedenteLabelInstance, field: 't22Label', 'errors')}">
                                    <g:textField name="t22Label" value="${antecedenteLabelInstance?.t22Label}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="profesional"><g:message code="antecedenteLabel.profesional.label" default="Profesional" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: antecedenteLabelInstance, field: 'profesional', 'errors')}">
                                    <g:select name="profesional.id" from="${com.medfire.Profesional.list()}" optionKey="id" value="${antecedenteLabelInstance?.profesional?.id}"  />
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
