

<%@ page import="com.medfire.AntecedenteLabel" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'antecedenteLabel.label', default: 'AntecedenteLabel')}" />
        <title><g:message code="default.edit.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.edit.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${antecedenteLabelInstance}">
            <div class="errors">
                <g:renderErrors bean="${antecedenteLabelInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <g:hiddenField name="id" value="${antecedenteLabelInstance?.id}" />
                <g:hiddenField name="version" value="${antecedenteLabelInstance?.version}" />
                <div class="span-4" colborder>
                	<g:textField name="t10Label" value="${antecedenteLabelInstance?.t10Label}" />
                </div>
                <div class="span-4" colborder>                
	                <g:textField name="t11Label" value="${antecedenteLabelInstance?.t11Label}" />
                </div>
                <div class="clear"></div>
                
                <div class="span-4" colborder>
    	            <g:textField name="t12Label" value="${antecedenteLabelInstance?.t12Label}" />
    	        </div>
                <div class="span-4" colborder>    
	                <g:textField name="t13Label" value="${antecedenteLabelInstance?.t13Label}" />
				</div>
				<div class="clear"></div>
				
                <div class="span-4" colborder>	                
	                <g:textField name="t14Label" value="${antecedenteLabelInstance?.t14Label}" />
	            </div>
                <div class="span-4" colborder>    
	                <g:textField name="t15Label" value="${antecedenteLabelInstance?.t15Label}" />
	            </div>
	            <div class="clear"></div>
	            
                <div class="span-4" colborder>	                
                	<g:textField name="t16Label" value="${antecedenteLabelInstance?.t16Label}" />
                </div>
                <div class="span-4" colborder>                	
	                <g:textField name="t17Label" value="${antecedenteLabelInstance?.t17Label}" />
	            </div>
	            <div class="clear"></div>
	                
                <div class="span-4" colborder>	                
                	<g:textField name="t18Label" value="${antecedenteLabelInstance?.t18Label}" />
 				</div>
                <div class="span-4" colborder> 				               	
                	<g:textField name="t19Label" value="${antecedenteLabelInstance?.t19Label}" />
                </div>
                <div class="clear"></div>
                	
                <div class="span-4" colborder>                	
	            	<g:textField name="t1Label" value="${antecedenteLabelInstance?.t1Label}" />
	            </div>
                <div class="span-4" colborder>	            	
                	<g:textField name="t20Label" value="${antecedenteLabelInstance?.t20Label}" />
                </div>
                <div class="clear"></div>
                	
                <div class="span-4" colborder>                	
                	<g:textField name="t21Label" value="${antecedenteLabelInstance?.t21Label}" />
                </div>
                <div class="span-4" colborder>                	
                	<g:textField name="t22Label" value="${antecedenteLabelInstance?.t22Label}" />
                </div>
                <div class="clear"></div>
                	
                <div class="span-4" colborder>                	
                	<g:textField name="t2Label" value="${antecedenteLabelInstance?.t2Label}" />
                </div>
                <div class="span-4" colborder>                	
                	<g:textField name="t3Label" value="${antecedenteLabelInstance?.t3Label}" />
                </div>
                <div class="clear"></div>
                	
                <div class="span-4" colborder>                	
                	<g:textField name="t4Label" value="${antecedenteLabelInstance?.t4Label}" />
                </div>
                <div class="span-4" colborder>                	
                	<g:textField name="t5Label" value="${antecedenteLabelInstance?.t5Label}" />
                </div>
                <div class="clear"></div>
                	
                <div class="span-4" colborder>                	
                	<g:textField name="t6Label" value="${antecedenteLabelInstance?.t6Label}" />
                </div>
                <div class="span-4" colborder>                	
                	<g:textField name="t7Label" value="${antecedenteLabelInstance?.t7Label}" />
                </div>
                <div class="clear"></div>
                	
                <div class="span-4" colborder>                	
                	<g:textField name="t8Label" value="${antecedenteLabelInstance?.t8Label}" />
                </div>
                <div class="span-4" colborder>                	
                	<g:textField name="t9Label" value="${antecedenteLabelInstance?.t9Label}" />
                </div>
                <div class="clear"></div>	
                <div class="buttons">
                    <span class="button"><g:actionSubmit class="save" action="update" value="${message(code: 'default.button.update.label', default: 'Update')}" /></span>
                    <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
