

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
        </div>
        <div class="span-16 prepend-2">
            <h1><g:message code="default.create.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="ui-state-highlight ui-corner-all"><h3>${flash.message}</h3></div>
            </g:if>
            <g:hasErrors bean="${antecedenteLabelInstance}">
            <div class="errors">
                <g:renderErrors bean="${antecedenteLabelInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" >
                <div class="span-10 prepend-2">
                		<fieldset>
                				<div class="span-3 spanlabel">	
                                    <label for="t1Label"><g:message code="antecedenteLabel.t1Label.label" default="T1 Label" /></label>
                                </div>
                                <div class="span-4">    
                                    <g:textField  class="ui-widget ui-corner-all ui-widget-content" name="t1Label" value="${antecedenteLabelInstance?.t1Label}" />
								</div>                        
                        
                        		<div class="clear"></div>
                				<div class="span-3 spanlabel">                        
                                    <label for="t2Label"><g:message code="antecedenteLabel.t2Label.label" default="T2 Label" /></label>
                                </div>
                                <div class="span-4">    
                                    <g:textField class="ui-widget ui-corner-all ui-widget-content" name="t2Label" value="${antecedenteLabelInstance?.t2Label}" />
                        		</div>
                        		
                        		<div class="clear"></div>                        		
                				<div class="span-3 spanlabel">                        
                                    <label for="t3Label"><g:message code="antecedenteLabel.t3Label.label" default="T3 Label" /></label>
                                </div>
                                <div class="span-4">    
                                    <g:textField class="ui-widget ui-corner-all ui-widget-content" name="t3Label" value="${antecedenteLabelInstance?.t3Label}" />
                        		</div>                        
                        
                        		<div class="clear"></div>                        
                				<div class="span-3 spanlabel">                        
                                    <label for="t4Label"><g:message code="antecedenteLabel.t4Label.label" default="T4 Label" /></label>
                                </div>
                                <div class="span-4">    
                                    <g:textField class="ui-widget ui-corner-all ui-widget-content" name="t4Label" value="${antecedenteLabelInstance?.t4Label}" />
                        		</div>                        
                        
                        		<div class="clear"></div>                        
                				<div class="span-3 spanlabel">                        
                                    <label for="t5Label"><g:message code="antecedenteLabel.t5Label.label" default="T5 Label" /></label>
                                </div>
                                <div class="span-4">    
                                    <g:textField class="ui-widget ui-corner-all ui-widget-content" name="t5Label" value="${antecedenteLabelInstance?.t5Label}" />
                        		</div>                        
                        
                        		<div class="clear"></div>                        
                				<div class="span-3 spanlabel">                        
                                    <label for="t6Label"><g:message code="antecedenteLabel.t6Label.label" default="T6 Label" /></label>
                                </div>
                                <div class="span-4">    
                                    <g:textField class="ui-widget ui-corner-all ui-widget-content" name="t6Label" value="${antecedenteLabelInstance?.t6Label}" />
                        		</div>                                    
                        
                        		<div class="clear"></div>                        
                				<div class="span-3 spanlabel">                        
                                    <label for="t7Label"><g:message code="antecedenteLabel.t7Label.label" default="T7 Label" /></label>
                                </div>
                                <div class="span-4">    
                                    <g:textField class="ui-widget ui-corner-all ui-widget-content" name="t7Label" value="${antecedenteLabelInstance?.t7Label}" />
                        		</div>                                    
                        
                        		<div class="clear"></div>                        
                				<div class="span-3 spanlabel">                        
                                    <label for="t8Label"><g:message code="antecedenteLabel.t8Label.label" default="T8 Label" /></label>
                                </div>
                                <div class="span-4">    
                                    <g:textField class="ui-widget ui-corner-all ui-widget-content" name="t8Label" value="${antecedenteLabelInstance?.t8Label}" />
                        		</div>                                    
                        
                        		<div class="clear"></div>                        
                				<div class="span-3 spanlabel">                        
                                    <label for="t9Label"><g:message code="antecedenteLabel.t9Label.label" default="T9 Label" /></label>
                                </div>
                                <div class="span-4">    
                                    <g:textField class="ui-widget ui-corner-all ui-widget-content" name="t9Label" value="${antecedenteLabelInstance?.t9Label}" />
                        		</div>                                    
                        
                        		<div class="clear"></div>                        
                				<div class="span-3 spanlabel">                        
                                    <label for="t10Label"><g:message code="antecedenteLabel.t10Label.label" default="T10 Label" /></label>
                                </div>
                                <div class="span-4">    
                                    <g:textField class="ui-widget ui-corner-all ui-widget-content" name="t10Label" value="${antecedenteLabelInstance?.t10Label}" />
                        		</div>                                    

                        		<div class="clear"></div>                        
                				<div class="span-3 spanlabel">                        
                                    <label for="t11Label"><g:message code="antecedenteLabel.t11Label.label" default="T11 Label" /></label>
                                </div>
                                <div class="span-4">    
                                    <g:textField class="ui-widget ui-corner-all ui-widget-content" name="t11Label" value="${antecedenteLabelInstance?.t11Label}" />
                        		</div>                        
                        
                        		<div class="clear"></div>                        
                				<div class="span-3 spanlabel">                        
                                    <label for="t12Label"><g:message code="antecedenteLabel.t12Label.label" default="T12 Label" /></label>
                                </div>
                                <div class="span-4">    
                                    <g:textField class="ui-widget ui-corner-all ui-widget-content" name="t12Label" value="${antecedenteLabelInstance?.t12Label}" />
                        		</div>                        
                        
                        		<div class="clear"></div>                        
                				<div class="span-3 spanlabel">                        
                                    <label for="t13Label"><g:message code="antecedenteLabel.t13Label.label" default="T13 Label" /></label>
                                </div>
                                <div class="span-4">    
                                    <g:textField class="ui-widget ui-corner-all ui-widget-content" name="t13Label" value="${antecedenteLabelInstance?.t13Label}" />
                        		</div>                                    
                        
                        		<div class="clear"></div>                        
                				<div class="span-3 spanlabel">                        
                                    <label for="t14Label"><g:message code="antecedenteLabel.t14Label.label" default="T14 Label" /></label>
                                </div>
                                <div class="span-4">    
                                    <g:textField class="ui-widget ui-corner-all ui-widget-content" name="t14Label" value="${antecedenteLabelInstance?.t14Label}" />
                        		</div>                                    
                        
                        		<div class="clear"></div>                        
                				<div class="span-3 spanlabel">                        
                                    <label for="t15Label"><g:message code="antecedenteLabel.t15Label.label" default="T15 Label" /></label>
                                </div>
                                <div class="span-4">    
                                    <g:textField class="ui-widget ui-corner-all ui-widget-content" name="t15Label" value="${antecedenteLabelInstance?.t15Label}" />
                        		</div>                                    
                        
                        		<div class="clear"></div>                        
                				<div class="span-3 spanlabel">                        
                                    <label for="t16Label"><g:message code="antecedenteLabel.t16Label.label" default="T16 Label" /></label>
                                </div>
                                <div class="span-4">    
                                    <g:textField class="ui-widget ui-corner-all ui-widget-content" name="t16Label" value="${antecedenteLabelInstance?.t16Label}" />
                        		</div>                                    

                        		<div class="clear"></div>                        
                				<div class="span-3 spanlabel">                        
                                    <label for="t17Label"><g:message code="antecedenteLabel.t17Label.label" default="T17 Label" /></label>
                                </div>
                                <div class="span-4">    
                                    <g:textField class="ui-widget ui-corner-all ui-widget-content" name="t17Label" value="${antecedenteLabelInstance?.t17Label}" />
                        		</div>                                    
                        
                        		<div class="clear"></div>                        
                				<div class="span-3 spanlabel">                        
                                    <label for="t18Label"><g:message code="antecedenteLabel.t18Label.label" default="T18 Label" /></label>
                                </div>
                                <div class="span-4">    
                                    <g:textField class="ui-widget ui-corner-all ui-widget-content" name="t18Label" value="${antecedenteLabelInstance?.t18Label}" />
                        		</div>                                    
                        
                        		<div class="clear"></div>                        
                				<div class="span-3 spanlabel">                        
                                    <label for="t19Label"><g:message code="antecedenteLabel.t19Label.label" default="T19 Label" /></label>
                                </div>
                                <div class="span-4">    
                                    <g:textField class="ui-widget ui-corner-all ui-widget-content" name="t19Label" value="${antecedenteLabelInstance?.t19Label}" />
                        		</div>                                    
                        
                        		<div class="clear"></div>                        
                				<div class="span-3 spanlabel">                        
                                    <label for="t20Label"><g:message code="antecedenteLabel.t20Label.label" default="T20 Label" /></label>
                                </div>
                                <div class="span-4">    
                                    <g:textField class="ui-widget ui-corner-all ui-widget-content" name="t20Label" value="${antecedenteLabelInstance?.t20Label}" />
                        		</div>                                    
                        
                        		<div class="clear"></div>                        
                				<div class="span-3 spanlabel">                        
                                    <label for="t21Label"><g:message code="antecedenteLabel.t21Label.label" default="T21 Label" /></label>
                                </div>
                                <div class="span-4">    
                                    <g:textField class="ui-widget ui-corner-all ui-widget-content" name="t21Label" value="${antecedenteLabelInstance?.t21Label}" />
                        		</div>                                    
                        
                        		<div class="clear"></div>                        
                				<div class="span-3 spanlabel">                        
                                    <label for="t22Label"><g:message code="antecedenteLabel.t22Label.label" default="T22 Label" /></label>
                                </div>
                                <div class="span-4">    
                                    <g:textField class="ui-widget ui-corner-all ui-widget-content" name="t22Label" value="${antecedenteLabelInstance?.t22Label}" />
                        		</div>                                    
						</fieldset>                        
                </div>
           		<div class="clear"></div>
                
                 <div class="span-10 prepend-2">
                    <span class="button"><g:submitButton name="create" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only" value="${message(code: 'default.button.create.label', default: 'Create')}" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
