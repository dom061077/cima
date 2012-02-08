
<%@ page import="com.medfire.Institucion" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'institucion.label', default: 'Institucion')}" />
        <script type="text/javascript" src="${resource(dir:'js/jquery',file:'thickbox.js')}"></script>
        <link rel="stylesheet" type="text/css" media="screen" href="${g.resource(dir:'css',file:'thickbox.css')}" />
        
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
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
                            <td valign="top" class="name"><g:message code="institucion.id.label" default="Id" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: institucionInstance, field: "id")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="institucion.email.label" default="Email:" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: institucionInstance, field: "email")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="institucion.direccion.label" default="Direccion:" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: institucionInstance, field: "direccion")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="institucion.nombre.label" default="Nombre:" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: institucionInstance, field: "nombre")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="institucion.telefonos.label" default="Telefonos:" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: institucionInstance, field: "telefonos")}</td>
                            
                        </tr>
                        
                        <tr class="prop">
                        	<td><g:message code="institucion.telefonos.label" default="Imagen:" /></td>
                        	<td>
								<bi:hasImage bean="${institucionInstance}">
									<a class="thickbox" href="${g.resourceimgext(size:'large', bean:institucionInstance)}"><img src="${g.resourceimgext(size:'small', bean:institucionInstance)}"  alt=""> </img></a>
								</bi:hasImage>
							</td>	
						</tr>	                        
                    
                    </tbody>
                </table>
            </div>
            <div class="buttons">
                <g:form>
                    <g:hiddenField name="id" value="${institucionInstance?.id}" />
                    <span class="button"><g:actionSubmit class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only" action="edit" value="${message(code: 'default.button.edit.label', default: 'Edit')}" /></span>
                    <span class="button"><g:actionSubmit class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
                </g:form>
            </div>
        </div>
    </body>
</html>
