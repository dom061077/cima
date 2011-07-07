

<%@ page import="com.medfire.ObraSocial" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'obraSocial.label', default: 'ObraSocial')}" />
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
            <g:hasErrors bean="${obraSocialInstance}">
	            <div class="ui-state-error ui-corner-all" style="padding: 0pt 0.7em;">
	                <g:renderErrors bean="${obraSocialInstance}" as="list" />
	            </div>
            </g:hasErrors>
            <g:form method="post" >
                <g:hiddenField name="id" value="${obraSocialInstance?.id}" />
                <g:hiddenField name="version" value="${obraSocialInstance?.version}" />
                <fieldset>
                                  <label for="descripcion"><g:message code="obraSocial.descripcion.label" default="Descripcion" /></label>
                                  <g:hasErrors bean="${obraSocialInstance}" field="descripcion">
                                  		<div class="ui-state-error ui-corner-all">	
                                  </g:hasErrors>
                                  <g:textField class="ui-widget ui-corner-all ui-widget-content" name="descripcion" value="${obraSocialInstance?.descripcion}" />
                                  <g:hasErrors bean="${obraSocialInstance}" field="descripcion">
                                  		<br/>
                                  		<g:renderErrors bean="${obraSocialInstance}" field="descripcion"/>
                                  		</div>
                                  </g:hasErrors>
                                  <br/>
                
                                  <label for="razonSocial"><g:message code="obraSocial.razonSocial.label" default="Razon Social" /></label>
                                  <g:hasErrors bean="${obraSocialInstance}" field="razonSocial">
										<div class="ui-state-error ui-corner-all">                                  		
                                  </g:hasErrors>
                                  <g:textField class="ui-widget ui-corner-all ui-widget-content" name="razonSocial" value="${obraSocialInstance?.razonSocial}" />
                                  <g:hasErrors bean="${obraSocialInstance}" field="razonSocial">
                                  		<br/>
                                  		<g:renderErrors bean="${obraSocialInstance}" field="razonSocial" />
                                  		</div>
                                  </g:hasErrors>
                                  <br/>


                                  <label for="domicilio"><g:message code="obraSocial.domicilio.label" default="Domicilio" /></label>
                                  <g:hasErrors bean="${obraSocialInstance}" field="domicilio">
                                  		<div class="ui-state-error ui-corner-all">
                                  </g:hasErrors>
                                  <g:textField class="ui-widget ui-corner-all ui-widget-content" name="domicilio" value="${obraSocialInstance?.domicilio}" />
                                  <g:hasErrors bean="${obraSocialInstance}" field="domicilio">
                                  		<br/>
                                  		<g:renderErrors bean="${obraSocialInstance}" field="domicilio"/>
                                  		</div>
                                  </g:hasErrors>
                                  <br/>
                
                                  <label for="codigoPostal"><g:message code="obraSocial.codigoPostal.label" default="Codigo Postal" /></label>
                                  <g:hasErrors bean="${obraSocialInstance}" field="codigoPostal">
                                  		<div class="ui-state-error ui-corner-all">		
                                  </g:hasErrors>
                                  <g:textField class="ui-widget ui-corner-all ui-widget-content" name="codigoPostal" value="${obraSocialInstance?.codigoPostal}" />
                                  <g:hasErrors bean="${obraSocialInstance}" field="codigoPostal">
                                  		<br/>
                                  		<g:renderErrors bean="${obraSocialInstance}" field="codigoPostal"/>
                                  		</div>
                                  </g:hasErrors>
                                  <br/>

                        
                                  <label for="contacto"><g:message code="obraSocial.contacto.label" default="Contacto" /></label>
                                  <g:hasErrors bean="${obraSocialInstance}" field="contacto">
                                  		<div class="ui-state-error ui-corner-all">
                                  </g:hasErrors>
                                  <g:textField class="ui-widget ui-corner-all ui-widget-content" name="contacto" value="${obraSocialInstance?.contacto}" />
                                  <g:hasErrors bean="${obraSocialInstance}" field="contacto">
                                  		<br/>
                                  		<g:renderErrors bean="${obraSocialInstance}" field="contacto" />
                                  		</div>
                                  </g:hasErrors>
                                  <br/>
                        
                                  <label for="cuit"><g:message code="obraSocial.cuit.label" default="Cuit" /></label>
                                  <g:hasErrors bean="${obraSocialInstance}" field="cuit">
                                  		<div class="ui-state-error ui-corner-all">
                                  </g:hasErrors>
                                  <g:textField class="ui-widget ui-corner-all ui-widget-content" name="cuit" value="${obraSocialInstance?.cuit}" />
                                  <g:hasErrors bean="${obraSocialInstance }" field="cuit">
                                  		<br/>
                                  		<g:renderErrors bean="${obraSocialInstance}" field="cuit"/>
                                  		</div>
                                  </g:hasErrors>
								  <br/>	                        
	                        
                        
                                  <label for="habilitada"><g:message code="obraSocial.habilitada.label" default="Habilitada" /></label>
                                  <g:checkBox name="habilitada" value="${obraSocialInstance?.habilitada}" /><br/>
                        
                        
                                  <label for="telefono"><g:message code="obraSocial.telefono.label" default="Telefono" /></label>
                                  <g:hasErrors bean="obraSocialInstance" field="telefono">
                                  		<div class="ui-state-error ui-corner-all">
                                  </g:hasErrors>
                                  <g:textField class="ui-widget ui-corner-all ui-widget-content" name="telefono" value="${fieldValue(bean: obraSocialInstance, field: 'telefono')}" />
                                  <g:hasErrors bean="${obraSocialInstance}" field="telefono">
                                  		<br/>
                                  		<g:renderErrors bean="${obraSocialInstance}" field="telefono"></g:renderErrors>
                                  		</div>
                                  </g:hasErrors>
                                  <br/>
                        
                </fieldset>
                <div style="padding: 10px 15px 15px 15px;">
                    <g:actionSubmit class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only" action="update" value="${message(code: 'default.button.update.label', default: 'Update')}" />
                    <g:actionSubmit class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
                </div>
            </g:form>
        </div>
    </body>
</html>
