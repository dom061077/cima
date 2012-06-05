

<%@ page import="com.medfire.Institucion" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <script type="text/javascript" src="${resource(dir:'js/jquery',file:'thickbox.js')}"></script>
        <link rel="stylesheet" type="text/css" media="screen" href="${g.resource(dir:'css',file:'thickbox.css')}" />
        
        <g:set var="entityName" value="${message(code: 'institucion.label', default: 'Institucion')}" />
        <title><g:message code="default.edit.label" args="[entityName]" /></title>
        <script type="text/javascript">
        	$(document).ready(function(){
		    	$('.cambiarlink').live('click',function(){
			    	if($(this).attr('inputactivo')=='false'){
				    	$(this).attr('inputactivo','true');
				    	var link = $(this);
				    	$('#image').fadeOut(function(){
				    		$('#inputimage').fadeIn('slow',function(){
				    			link.html('Cancelar');
					    	});
				    				
					    });
				    }else{
				    	$(this).attr('inputactivo','false');
				    	var link = $(this);
				    	$('#inputimage').fadeOut(function(){
					    	$('#image').fadeIn('slow',function(){
					    		link.html('Cambiar Imagen');
						    });
					    	
					    });
					}
		    	});
		    	$('#inputimage').hide();	
            });
        </script>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
        </div>
        <div class="body">
            <h1><g:message code="default.edit.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${institucionInstance}">
            <div class="errors">
                <g:renderErrors bean="${institucionInstance}" as="list" />
            </div>
            </g:hasErrors>
            <form method="post" enctype="multipart/form-data" >
                <g:hiddenField name="id" value="${institucionInstance?.id}" />
                <g:hiddenField name="version" value="${institucionInstance?.version}" />
                <div class="dialog">
                        
                            <div class="span-2 spanlabel">
                                  <label for="email"><g:message code="institucion.email.label" default="Email:" /></label>
                            </div>
                            <div class="span-4">      
                                  <g:textField class="ui-widget ui-corner-all ui-widget-content" name="email" value="${institucionInstance?.email}" />
                            </div>
                            <div class="clear"></div>
                        
                        	<div class="span-2 spanlabel">
                                  <label for="direccion"><g:message code="institucion.direccion.label" default="Dirección:" /></label>
                            </div>      
                            <div class="span-4">
                                  <g:textField class="ui-widget ui-corner-all ui-widget-content" name="direccion" value="${institucionInstance?.direccion}" />
                        	</div>
                            <div class="clear"></div>                        	
                        	
                        	<div class="span-2 spanlabel">
                                  <label for="nombre"><g:message code="institucion.nombre.label" default="Nombre:" /></label>
                            </div>
                            <div class="span-4">      
                                  <g:textField class="ui-widget ui-corner-all ui-widget-content" name="nombre" value="${institucionInstance?.nombre}" />
                        	</div>
                            <div class="clear"></div>                        	


                        	<div class="span-2 spanlabel">
                                  <label for="nombre"><g:message code="institucion.descripcion.label" default="Descripción:" /></label>
                            </div>
                            <div class="span-4">      
                                  <g:textField class="ui-widget ui-corner-all ui-widget-content" name="descripcion" value="${institucionInstance?.descripcion}" />
                        	</div>
                            <div class="clear"></div>                        	


                        	<div class="span-2 spanlabel">
                                  <label for="telefonos"><g:message code="institucion.telefonos.label" default="Teléfonos:" /></label>
                            </div>
                            <div class="span-4">      
                                  <g:textField class="ui-widget ui-corner-all ui-widget-content" name="telefonos" value="${institucionInstance?.telefonos}" />
                        	</div>
                            <div class="clear"></div>                        	


                        	<div class="span-2 spanlabel">
                           		  <label for="imagen"><g:message code="institucion.telefonos.label" default="Imagen:" /></label>
                            </div>
                            <div id="image" class="span-5">
		            					<a class="thickbox" href="${g.resourceimgext(size:'large', bean:institucionInstance)}"><img src="${g.resourceimgext(size:'small', bean:institucionInstance)}"  alt=""> </img></a>
	            			</div>
                            <div id="inputimage" class="span-5">      
								  <input class="ui-widget ui-corner-all ui-widget-content" type="file" name="imagen"/>                            	
                        	</div>
							<div  class="span-5">
		            					<span>
		            						<a  class="cambiarlink" inputactivo="false" href="" onclick="return false;">Cambiar Imagen</a>
		            					</span>
		            		</div>                        	
                            <div class="clear"></div>                        	
                        
                </div>
                <div class="buttons">
                    <span class="button">
                    	<g:actionSubmit class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only" action="update" value="${message(code: 'default.button.update.label', default: 'Update')}" />
                    </span>
<%--                    <span class="button">--%>
<%--                    		<g:actionSubmit class="ui-widget ui-corner-all ui-widget-content" action="delete"	value="${message(code: 'default.button.delete.label', default: 'Delete')}" 	--%>
<%--        		            		onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />--%>
<%--                    </span>--%>
                </div>
            </form>
        </div>
    </body>
</html>
