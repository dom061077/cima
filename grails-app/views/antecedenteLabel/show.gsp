
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
            <h1><g:message code="default.show.label" args="[entityName]" /></h1>
        
        <div class="span-16 prepend-2">
            <g:if test="${flash.message}">
            <div class="ui-state-highlight ui-corner-all"><h3>${flash.message}</h3></div>
            </g:if>
            <div class="span-10 prepend-2">
           		<fieldset>
                		<div class="span-2">
                            <label class="spanlabel"><g:message code="antecedenteLabel.id.label" default="Id" /></label>
                        </div>    
                        <div class="span-4">
                            ${fieldValue(bean: antecedenteLabelInstance, field: "id")}
						</div>
						<div class="clear"></div>                            
                            
                		<div class="span-2">
                            <label class="spanlabel"><g:message code="antecedenteLabel.t1Label.label" default="T1 Label" /></label>
                        </div>    
                        <div class="span-4">
                            ${fieldValue(bean: antecedenteLabelInstance, field: "t1Label")}
						</div>
						<div class="clear"></div>                            
                            
                            
                		<div class="span-2">                    
                            <label class="spanlabel"><g:message code="antecedenteLabel.t2Label.label" default="T2 Label" /></label>
                        </div>    
                        <div class="span-4">
                            ${fieldValue(bean: antecedenteLabelInstance, field: "t2Label")}
						</div>
						<div class="clear"></div>                            
                    
                		<div class="span-2">                    
                            <label class="spanlabel"><g:message code="antecedenteLabel.t3Label.label" default="T3 Label" /></label>
                        </div>    
                        <div class="span-4">
                            ${fieldValue(bean: antecedenteLabelInstance, field: "t3Label")}
						</div>
						<div class="clear"></div>                            
                            
                		<div class="span-2">                    
                            <label class="spanlabel"><g:message code="antecedenteLabel.t4Label.label" default="T4 Label" /></label>
                        </div>    
                        <div class="span-4">
                            ${fieldValue(bean: antecedenteLabelInstance, field: "t4Label")}
						</div>
						<div class="clear"></div>                            
                            
                		<div class="span-2">                    
                            <label class="spanlabel"><g:message code="antecedenteLabel.t5Label.label" default="T5 Label" /></label>
                        </div>    
                        <div class="span-4">
                            ${fieldValue(bean: antecedenteLabelInstance, field: "t5Label")}
						</div>
						<div class="clear"></div>                            
                            
                		<div class="span-2">                    
                            <label class="spanlabel"><g:message code="antecedenteLabel.t6Label.label" default="T6 Label" /></label>
                        </div>    
                        <div class="span-4">
                            ${fieldValue(bean: antecedenteLabelInstance, field: "t6Label")}
                         </div>
                         
                         <div class="clear"></div>   
                		<div class="span-2">                    
                            <label class="spanlabel"><g:message code="antecedenteLabel.t7Label.label" default="T7 Label" /></label>
                        </div>    
                        <div class="span-4">
                            ${fieldValue(bean: antecedenteLabelInstance, field: "t7Label")}
						</div>
						<div class="clear"></div>                            
                            
                		<div class="span-2">                    
                            <label class="spanlabel"><g:message code="antecedenteLabel.t8Label.label" default="T8 Label" /></label>
                        </div>    
                        <div class="span-4">
                            ${fieldValue(bean: antecedenteLabelInstance, field: "t8Label")}
						</div>
						<div class="clear"></div>                            
                            
                		<div class="span-2">                    
                            <label class="spanlabel"><g:message code="antecedenteLabel.t9Label.label" default="T9 Label" /></label>
                        </div>    
                        <div class="span-4">
                            ${fieldValue(bean: antecedenteLabelInstance, field: "t9Label")}
						</div>
						<div class="clear"></div>                            
                            
                		<div class="span-2">                    
                            <label class="spanlabel"><g:message code="antecedenteLabel.t10Label.label" default="T10 Label" /></label>
                        </div>    
                        <div class="span-4">
                            ${fieldValue(bean: antecedenteLabelInstance, field: "t10Label")}
						</div>
						<div class="clear"></div>                            
                            
                		<div class="span-2">                    
                            <label class="spanlabel"><g:message code="antecedenteLabel.t11Label.label" default="T11 Label" /></label>
                        </div>    
                        <div class="span-4">
                            ${fieldValue(bean: antecedenteLabelInstance, field: "t11Label")}
						</div>
						<div class="clear"></div>                            
                            
                		<div class="span-2">                    
                            <label class="spanlabel"><g:message code="antecedenteLabel.t12Label.label" default="T12 Label" /></label>
                        </div>    
                        <div class="span-4">
                            ${fieldValue(bean: antecedenteLabelInstance, field: "t12Label")}
						</div>
						<div class="clear"></div>                            
                            
                		<div class="span-2">                    
                            <label class="spanlabel"><g:message code="antecedenteLabel.t13Label.label" default="T13 Label" /></label>
                        </div>    
                        <div class="span-4">
                            ${fieldValue(bean: antecedenteLabelInstance, field: "t13Label")}
						</div>
						<div class="clear"></div>                            
                            
                		<div class="span-2">                    
                            <label class="spanlabel"><g:message code="antecedenteLabel.t14Label.label" default="T14 Label" /></label>
                        </div>    
                        <div class="span-4">
                            ${fieldValue(bean: antecedenteLabelInstance, field: "t14Label")}
						</div>
						<div class="clear"></div>                            
                            
                		<div class="span-2">                    
                            <label class="spanlabel"><g:message code="antecedenteLabel.t15Label.label" default="T15 Label" /></label>
                        </div>    
                        <div class="span-4">
                            ${fieldValue(bean: antecedenteLabelInstance, field: "t15Label")}
						</div>
						<div class="clear"></div>                            
                            
                		<div class="span-2">                    
                            <label class="spanlabel"><g:message code="antecedenteLabel.t16Label.label" default="T16 Label" /></label>
                        </div>    
                        <div class="span-4">
                            ${fieldValue(bean: antecedenteLabelInstance, field: "t16Label")}
						</div>
						<div class="clear"></div>                            
                            
                		<div class="span-2">                    
                            <label class="spanlabel"><g:message code="antecedenteLabel.t17Label.label" default="T17 Label" /></label>
                        </div>    
                        <div class="span-4">
                            ${fieldValue(bean: antecedenteLabelInstance, field: "t17Label")}
						</div>
						<div class="clear"></div>                            
                            
                		<div class="span-2">                    
                            <label class="spanlabel"><g:message code="antecedenteLabel.t18Label.label" default="T18 Label" /></label>
                        </div>    
                        <div class="span-4">
                            ${fieldValue(bean: antecedenteLabelInstance, field: "t18Label")}
						</div>
						<div class="clear"></div>                            
                            
                		<div class="span-2">                    
                            <label class="spanlabel"><g:message code="antecedenteLabel.t19Label.label" default="T19 Label" /></label>
                        </div>    
                        <div class="span-4">
                            ${fieldValue(bean: antecedenteLabelInstance, field: "t19Label")}
						</div>
						<div class="clear"></div>                            
                            
                		<div class="span-2">                    
                            <label class="spanlabel"><g:message code="antecedenteLabel.t20Label.label" default="T20 Label" /></label>
                        </div>    
                        <div class="span-4">
                            ${fieldValue(bean: antecedenteLabelInstance, field: "t20Label")}
						</div>
						<div class="clear"></div>                            
                            
                		<div class="span-2">                    
                            <label class="spanlabel"><g:message code="antecedenteLabel.t21Label.label" default="T21 Label" /></label>
                        </div>    
                        <div class="span-4">
                            ${fieldValue(bean: antecedenteLabelInstance, field: "t21Label")}
						</div>
						<div class="clear"></div>                            
                            
                		<div class="span-2">                    
                            <label class="spanlabel"><g:message code="antecedenteLabel.t22Label.label" default="T22 Label" /></label>
                        </div>    
                        <div class="span-4">
                            ${fieldValue(bean: antecedenteLabelInstance, field: "t22Label")}
						</div>
						<div class="clear"></div>                            
                            
                		<div class="span-2">                    
                           <label class="spanlabel"> <g:message code="antecedenteLabel.profesional.label" default="Profesional:" /> </label>
                        </div>    
                        <div class="span-3">
                            ${antecedenteLabelInstance?.profesional?.nombre?.encodeAsHTML()}
						</div>
                            
				</fieldset>                    		
            </div>
			<div class="clear"></div>                            
            <g:ifAnyGranted role="ROLE_ADMIN">
            <div class="span-10 prepend-2">
                <g:form>
                    <g:hiddenField name="id" value="${antecedenteLabelInstance?.id}" />
                    <span class="button"><g:actionSubmit class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only" action="edit" value="${message(code: 'default.button.edit.label', default: 'Edit')}" /></span>
                    <span class="button"><g:actionSubmit class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
                </g:form>
            </div>
            </g:ifAnyGranted>
        </div>
    </body>
</html>
