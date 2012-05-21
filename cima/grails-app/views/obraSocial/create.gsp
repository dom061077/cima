

<%@ page import="com.medfire.ObraSocial" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'obraSocial.label', default: 'ObraSocial')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
        
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.create.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            
			
            
            <g:hasErrors bean="${obraSocialInstance}">
	            <div class="ui-state-error ui-corner-all" style="padding: 0pt 0.7em;">
	                <g:renderErrors bean="${obraSocialInstance}" as="list" />
	            </div>
            </g:hasErrors>
            <g:form action="save" >
            	<fieldset>
            	                    <g:hasErrors bean="${obraSocialInstance}" field="descripcion">
                                    	<div class="ui-state-error ui-corner-all">
                                    </g:hasErrors>
                                    <div class="span-4">
                                    	<label for="descripcion"><g:message code="obraSocial.descripcion.label" default="Descripcion" /></label>
                                    </div>
                                    <div class="span-4">
                                    	<g:textField class="ui-widget ui-corner-all ui-widget-content" name="descripcion" value="${obraSocialInstance?.descripcion}" />
                                    </div>
                               		<g:hasErrors bean="${obraSocialInstance}" field="descripcion">
                                    	<g:renderErrors bean="${obraSocialInstance}" as="list" field="descripcion"/>
                                    	</div>
                                   	</g:hasErrors>
                                    <div class="clear"></div>

                                    <g:hasErrors bean="${obraSocialInstance}" field="razonSocial">
                                    	<div class="ui-state-error ui-corner-all">	
                                    </g:hasErrors>
                                    <div class="span-4">
	                                    <label for="razonSocial"><g:message code="obraSocial.razonSocial.label" default="Razon Social" /></label>
									</div>
									<div class="span-4">	                                    
                                    	<g:textField class="ui-widget ui-corner-all ui-widget-content" name="razonSocial" value="${obraSocialInstance?.razonSocial}" />
                                    </div>
                               		<g:hasErrors bean="${obraSocialInstance}" field="razonSocial">
                                    	<g:renderErrors bean="${obraSocialInstance}" as="list" field="razonSocial"/>
                                    	</div>
                                   	</g:hasErrors>
                                    <div class="clear"></div>

                                    <g:hasErrors bean="${obraSocialInstance}" field="domicilio">
                                    	<div class="ui-state-error ui-corner-all">
                                    </g:hasErrors>
                                    <div class="span-4">
	                                    <label for="domicilio"><g:message code="obraSocial.domicilio.label" default="Domicilio" /></label>
									</div>	          
									<div class="span-4">                          
    	                                <g:textField class="ui-widget ui-corner-all ui-widget-content" name="domicilio" value="${obraSocialInstance?.domicilio}" />
                                    </div>
                               		<g:hasErrors bean="${obraSocialInstance}" field="domicilio">
                                    	<g:renderErrors bean="${obraSocialInstance}" as="list" field="domicilio"/>
                                    	</div>
                                   	</g:hasErrors>
                                   	<div class="clear"></div>
                                    
                                    <g:hasErrors bean="${obraSocialInstance}" field="codigoPostal">
                                    	<div class="ui-state-error ui-corner-all">
                                    </g:hasErrors>
                                    <div class="span-4">
	    	                            <label for="codigoPostal"><g:message code="obraSocial.codigoPostal.label" default="Codigo Postal" /></label>
									</div>
									<div class="span-4">	    	                            
            	                        <g:textField class="ui-widget ui-corner-all ui-widget-content" name="codigoPostal" value="${obraSocialInstance?.codigoPostal}" />
    	                            </div>    
                               		<g:hasErrors bean="${obraSocialInstance}" field="codigoPostal">
                                    	<g:renderErrors bean="${obraSocialInstance}" as="list" field="codigoPostal"/></div>
                                   	</g:hasErrors>
                                   	<div class="clear"></div>
                                   	
                                    <g:hasErrors bean="${obraSocialInstance}" field="telefono">
                                    	<div class="ui-state-error ui-corner-all">
                                    </g:hasErrors>
                                    <div class="span-4">
	                                    <label for="telefono"><g:message code="obraSocial.telefono.label" default="Telefono" /></label>
	                                </div>    
	                                <div class="span-4">
    	                                <g:textField class="ui-widget ui-corner-all ui-widget-content" name="telefono" value="${fieldValue(bean: obraSocialInstance, field: 'telefono')}" />
    	                            </div>    
                               		<g:hasErrors bean="${obraSocialInstance}" field="telefono">
                                    	<g:renderErrors bean="${obraSocialInstance}" as="list" field="telefono"/></div>
                                   	</g:hasErrors>
                                    <div class="clear"></div>

                                    <g:hasErrors bean="${obraSocialInstance}" field="contacto">
                                    	<div class="ui-state-error ui-corner-all">
                                    </g:hasErrors>
                                    <div class="span-4">
                                    	<label for="contacto"><g:message code="obraSocial.contacto.label" default="Contacto" /></label>
                                    </div>	
                                    <div class="span-4">
                                    	<g:textField class="ui-widget ui-corner-all ui-widget-content" name="contacto" value="${obraSocialInstance?.contacto}" />
                                    </div>	
                               		<g:hasErrors bean="${obraSocialInstance}" field="contacto">
                                    	<g:renderErrors bean="${obraSocialInstance}" as="list" field="contacto"/>
                                    	</div>
                                   	</g:hasErrors>
                                    <div class="clear"></div>
                        
                        			<g:hasErrors bean="${obraSocialInstance}" field="cuit">
                        				<div class="ui-state-error ui-corner-all">
                        			</g:hasErrors>
                        			<div class="span-4">
                                    	<label for="cuit"><g:message code="obraSocial.cuit.label" default="Cuit" /></label>
                                   	</div>
                                   	<div class="span-4">
                                    	<g:textField class="ui-widget ui-corner-all ui-widget-content" name="cuit" value="${obraSocialInstance?.cuit}" />
                               		</div>
                               		<g:hasErrors bean="${obraSocialInstance}" field="cuit">
                                    	<g:renderErrors bean="${obraSocialInstance}" as="list" field="cuit"/>
                                    	</div>
                                   	</g:hasErrors>
                                    <div class="clear"></div>
                                    
                        
                        			<div class="span-4">
                                    	<label for="habilitada"><g:message code="obraSocial.habilitada.label" default="Habilitada" /></label>
									</div>
									<div class="span-4">                                    	
                                    	<g:checkBox class="ui-widget ui-corner-all ui-widget-content" name="habilitada" value="${obraSocialInstance?.habilitada}" />
                                    </div>
                                    <div class="clear"></div>
                        	
                                    
							
                                    
                            
                  </fieldset>
                  
                  <div style="padding: 10px 15px 15px 15px;">
                  	<g:submitButton name="create" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only" value="${message(code: 'default.button.create.label', default: 'Create')}" />
                  </div>                
            </g:form>
        </div>
    </body>
</html>
