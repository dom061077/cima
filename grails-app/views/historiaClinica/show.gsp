
<%@ page import="com.medfire.Consulta" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'consulta.label', default: 'Consulta')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
        <script type="text/javascript" src="${resource(dir:'js/editor',file:'ckeditor.js')}"></script>
        <script type="text/javascript">
        	$(document).ready(function(){
        		CKEDITOR.on( 'instanceReady', function( ev )
        				{
        					editor = ev.editor;
        					editor.setReadOnly( true );
        				});            	
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
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="dialog">
                <table>
                    <tbody>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="consulta.id.label" default="Id" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: consultaInstance, field: "id")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="consulta.numero.label" default="Numero" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: consultaInstance?.paciente, field: "id")}</td>
                            
                        </tr>
                        <tr class="prop">
                        	<td>
                        		contenido:
                        	</td>
                        	<td>
                        	
                        		<textarea class="ckeditor" id="contenidoId" cols="100" rows="10>
                        			${consultaInstance?.contenido}
                        		</textarea>
                        		
                        	</td>
                        	
                        </tr>
                    
                    </tbody>
                </table>
            </div>
            <div class="buttons">
                <g:form>
                    <g:hiddenField name="id" value="${historiaClinicaInstance?.id}" />
                    <span class="button"><g:actionSubmit class="edit" action="edit" value="${message(code: 'default.button.edit.label', default: 'Edit')}" /></span>
                    <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
                </g:form>
            </div>
        </div>
    </body>
</html>
