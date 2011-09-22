
<%@ page import="com.medfire.Profesional" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'profesional.label', default: 'Profesional')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
        <script type="text/javascript" src="${resource(dir:'js/jquery',file:'thickbox.js')}"></script>
        link rel="stylesheet" type="text/css" media="screen" href="${g.resource(dir:'css',file:'thickbox.css')}" />
        <script type="text/javascript">
        	$(document).ready(function(){
        		$("#tabs").tabs();
            });
        </script>
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
            <div class="ui-state-highlight ui-corner-all"><h3>${flash.message}</h3></div>
            </g:if>
             <div id="tabs">
             	<ul>
					<li><a href="#tabs-1">Datos Generales</a></li>
					<li><a href="#tabs-2">Datos Complementarios</a></li>
             	</ul>
             	<div id="tabs-1">
             		<fieldset>
						<div class="span-3">                    	
                            <g:message code="profesional.id.label" default="Id:" />
						</div>                                                 
						<div class="span-4">						       
                            ${fieldValue(bean: profesionalInstance, field: "id")}
						</div>                            

						<div class="clear"></div>
                    	<div class="span-3">
                            <g:message code="profesional.matricula.label" default="Matricula:" />
                        </div>    
                        <div class="span-4">
                            ${fieldValue(bean: profesionalInstance, field: "matricula")}
                        </div>    
                        
						<div class="clear"></div>
                    	<div class="span-3">
                            <g:message code="profesional.nombre.label" default="Nombre:" />
						</div>
						<div class="span-4">                            
                            ${fieldValue(bean: profesionalInstance, field: "nombre")}
                        </div>    
                        
						<div class="clear"></div>
                    	<div class="span-3">
                            <g:message code="profesional.observaciones.label" default="Observaciones:" />
                        </div>
                        <div class="span-4">     
                            ${fieldValue(bean: profesionalInstance, field: "observaciones")}
                        </div>    
                    
                    
						<div class="clear"></div>
                    	<div class="span-3">
                            <g:message code="profesional.codigoPostal.label" default="Codigo Postal:" />
						</div>
						<div class="span-4">                            
                            ${fieldValue(bean: profesionalInstance, field: "codigoPostal")}
						</div>
						                            
						<div class="clear"></div>
                    	<div class="span-3">
                            <g:message code="profesional.localidad.label" default="Localidad:" />
						</div>
						<div class="span-4">                            
                           ${profesionalInstance?.localidad?.nombre?.encodeAsHTML()}
						</div>
						                            
						<div class="clear"></div>
                    	<div class="span-3">
                            <g:message code="profesional.telefono.label" default="Telefono:" />
						</div>
						<div class="span-4">                            
                            ${fieldValue(bean: profesionalInstance, field: "telefono")}
						</div>                        
                            
						<div class="clear"></div>
                    	<div class="span-3">
                            <g:message code="profesional.fechaNacimiento.label" default="Fecha Nacimiento:" />
						</div>
						<div class="span-4">                            
                            <g:formatDate format="dd/MM/yyyy" style="SHORT" date="${profesionalInstance?.fechaNacimiento}" />
						</div>                            
                            
						<div class="clear"></div>
                    	<div class="span-3">
                            <g:message code="profesional.numeroDocumento.label" default="Numero Documento:" />
						</div>
						<div class="span-4">                            
                            ${fieldValue(bean: profesionalInstance, field: "numeroDocumento")}
						</div>                            
                            
						<div class="clear"></div>
                    	<div class="span-3">
                            <g:message code="profesional.tipoDocumento.label" default="Tipo Documento:" />
						</div>
						<div class="span-4">                            
                            ${profesionalInstance?.tipoDocumento?.name?.encodeAsHTML()}
						</div>
					</fieldset>						
				</div>						                            
             	<div id="tabs-2">						                            
					<fieldset>						                            
						<div class="clear"></div>
                    	<div class="span-3">
                            <g:message code="profesional.codigoIva.label" default="Codigo I.V.A:" />
						</div>
						<div class="span-4">                            
                            ${profesionalInstance?.codigoIva?.encodeAsHTML()}
						</div>                        
                            
						<div class="clear"></div>
                    	<div class="span-3">
                            <g:message code="profesional.cuit.label" default="Cuit" />
						</div>
						<div class="span-4">                            
                            ${fieldValue(bean: profesionalInstance, field: "cuit")}
						</div>                            
                            
						<div class="clear"></div>
                    	<div class="span-3">
                            <g:message code="profesional.fechaIngreso.label" default="Fecha Ingreso:" />
						</div>
						<div class="span-4">                            
                            <g:formatDate format="dd/MM/yyyy" style="SHORT" date="${profesionalInstance?.fechaIngreso}" />
						</div>                            
                    
						<div class="clear"></div>
                    	<div class="span-3">
                            <g:message code="profesional.especialidad.label" default="Especialidad:" />
						</div>
						<div class="span-4">                            
                            ${profesionalInstance?.especialidad?.descripcion?.encodeAsHTML()}
						</div>                            
                            
						<div class="clear"></div>
                    	<div class="span-3">
                            <g:message code="profesional.activo.label" default="Activo:" />
						</div>
						<div class="span-4">                            
                            <g:formatBoolean true="SI" false="NO" boolean="${profesionalInstance?.activo}" />
						</div>                            
                            
						<div class="clear"></div>
                    	<div class="span-3">
                            <g:message code="profesional.tipoMatricula.label" default="Tipo Matricula:" />
						</div>
						<div class="span-4">                            
                            ${fieldValue(bean: profesionalInstance, field: "tipoMatricula")}
						</div>                            
                            
						<div class="clear"></div>
                    	<div class="span-3">
                            <g:message code="profesional.sexo.label" default="Sexo:" />
						</div>
						<div class="span-4">                            
                            ${profesionalInstance?.sexo?.name?.encodeAsHTML()}
						</div>
						<div class="clear"></div>
						<div class="span-3">
							<g:message code="profesional.foto.label" default="Foto:"/>
						</div>
						<div class="span-4">
							<bi:hasImage bean="${profesionalInstance}">
								<a class="thickbox" href="${bi.resource(size:'large', bean:profesionalInstance)}"><img src="${bi.resource(size:'small', bean:profesionalInstance)}"  alt=""> </img></a>
							</bi:hasImage>
						</div>
					</fieldset>						                      
				</div>	      
			</div>
                    
            <div class="buttons">
                <g:form>
                    <g:hiddenField name="id" value="${profesionalInstance?.id}" />
                    <span class="button"><g:actionSubmit class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only" action="edit" value="${message(code: 'default.button.edit.label', default: 'Edit')}" /></span>
                    <span class="button"><g:actionSubmit class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
                </g:form>
            </div>
        </div>
    </body>
</html>
