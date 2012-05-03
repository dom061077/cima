

<%@ page import="com.medfire.Profesional"
  @ page import="com.medfire.Provincia"
  @ page import="com.medfire.Localidad"
 %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'profesional.label', default: 'Profesional')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
        <script type="text/javascript" src="${resource(dir:'js/script',file:'jquicombobox.js')}"></script>
        <script type="text/javascript" src="${resource(dir:'js/jquery',file:'jquery.jlookupfield.js')}"></script>
        <script type="text/javascript">

	        
        	$(document).ready(function(){

                $("#especialidadDescId").lookupfield({
                	source:"<% out << g.createLink(controller:"especialidadMedica",action:'listjson')%>",
					title:'Búsqueda de Especialidades',
					colnames:['Id','Descripción'],
					colModel:[
							{name:'id',index:'id', width:10, sorttype:"int", sortable:true,hidden:false,search:false},
							{name:'descripcion',index:'descripcion', width:100,  sortable:true,search:true},	
						],
					hiddenid:'especialidadId',
					descid:'especialidadDescId',
					hiddenfield:'id',
					descfield:['descripcion']	
                 });	
				
                $("#especialidadDescId").autocomplete({
					source: "<% out << g.createLink(controller:"especialidadMedica",action:'listjsonautocomplete')%>",
					minLength:2,
					select: function(event,ui){
						if(ui.item){
							$("#especialidadId").val(ui.item.id);
						}
					}
                });    
				//$("#tipodocumentoId").combobox();
				//$("#codigoivaId").combobox();
				//$("#sexoId").combobox();
				$("#provinciadescId").autocomplete({
						source: "<% out << g.createLink(controller:"provincia",action:'listjsonautocomplete')%>",
						minLength:2,
						select: function(event,ui){
							if(ui.item){
								$("#provinciaId").val(ui.item.id);
								$("#localidaddescId").val("");
								$("#localidadId").val("");
							}
						}
				});
				$("#usuarioAsignadoDescId").autocomplete({
					source: "<% out << g.createLink(controller:"user",action:'listjsonautocomplete')%>",
					minLength:2,
					select: function(event,ui){
						if(ui.item){
							$("#usuarioAsignadoId").val(ui.item.id);
						}
					}

				});
				
				$("#localidaddescId").autocomplete({
					minLenth:2,
					source: function(request,response){
							$.getJSON("<% out << g.createLink(controller:"localidad",action:'listjsonautocomplete')%>",
								"term=" + request.term + "&provinciaId=" + $("#provinciaId").val(),
								function (data, status, xhr) {
									response(data);
								}
							);
						
							
						},
					select : function(event,ui){
							if(ui.item){
								$("#localidadId").val(ui.item.id);
								
							}
					}
				});
				
				$('#fechaNacimientoId').datepicker({changeMonth:true
					//,showOn: "button"
					,yearRange:'c-111:c+10'
					,dateFormat:'dd/mm/yy'
					,changeYear:true,
	                closeText: 'Cerrar',
	                prevText: '&#x3c;Ant',
	                nextText: 'Sig&#x3e;',
	                currentText: 'Hoy',
	                monthNames: ['Enero','Febrero','Marzo','Abril','Mayo','Junio',
	                'Julio','Agosto','Septiembre','Octubre','Noviembre','Diciembre'],
	                monthNamesShort: ['Ene','Feb','Mar','Abr','May','Jun',
	                'Jul','Ago','Sep','Oct','Nov','Dic'],
	                dayNames: ['Domingo','Lunes','Martes','Mi&eacute;rcoles','Jueves','Viernes','S&aacute;bado'],
	                dayNamesShort: ['Dom','Lun','Mar','Mi&eacute;','Juv','Vie','S&aacute;b'],
	                dayNamesMin: ['Do','Lu','Ma','Mi','Ju','Vi','S&aacute;'],
	                weekHeader: 'Sm',
	                dateFormat: 'dd/mm/yy',
	                firstDay: 1,
	                isRTL: false,
	                showMonthAfterYear: false,
	                yearSuffix: ''
	                //buttonImage:'<%  out << g.resource(dir:'images',file:'calendar.gif') %>'
					});
				$('#fechaIngresoId').datepicker({changeMonth:true
					//,showOn: "button"
					,yearRange:'c-111:c+10'
					,dateFormat:'dd/mm/yy'
					,changeYear:true,
	                closeText: 'Cerrar',
	                prevText: '&#x3c;Ant',
	                nextText: 'Sig&#x3e;',
	                currentText: 'Hoy',
	                monthNames: ['Enero','Febrero','Marzo','Abril','Mayo','Junio',
	                'Julio','Agosto','Septiembre','Octubre','Noviembre','Diciembre'],
	                monthNamesShort: ['Ene','Feb','Mar','Abr','May','Jun',
	                'Jul','Ago','Sep','Oct','Nov','Dic'],
	                dayNames: ['Domingo','Lunes','Martes','Mi&eacute;rcoles','Jueves','Viernes','S&aacute;bado'],
	                dayNamesShort: ['Dom','Lun','Mar','Mi&eacute;','Juv','Vie','S&aacute;b'],
	                dayNamesMin: ['Do','Lu','Ma','Mi','Ju','Vi','S&aacute;'],
	                weekHeader: 'Sm',
	                dateFormat: 'dd/mm/yy',
	                firstDay: 1,
	                isRTL: false,
	                showMonthAfterYear: false,
	                yearSuffix: ''
	                //buttonImage:'<%  out << g.resource(dir:'images',file:'calendar.gif') %>'
					});
    			//-----------------institucion search------------
                $("#institucionDescId").lookupfield({
                	source:"<% out << g.createLink(controller:"institucion",action:'listjson')%>",
					title:'Búsqueda de Instituciones',
					colnames:['Id','Nombre'],
					colModel:[
							{name:'id',index:'id', width:10, sorttype:"int", sortable:true,hidden:false,search:false},
					   		{name:'nombre',index:'nombre', width:150, sortable:true}
						],
					hiddenid:'institucionId',
					descid:'institucionDescId',
					hiddenfield:'id',
					descfield:['nombre']	
                 });	

               	
    			$("#institucionDescId").autocomplete({
    				source: "<% out << g.createLink(controller:"institucion",action:'listjsonautocomplete')%>",
    				minLength:2,
    				select: function(event,ui){
    					if(ui.item){
    						$("#institucionId").val(ui.item.id);
    					}
    				}
    	
    			});

				
				$("#tabs").tabs();
            });
        </script>
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
            <g:hasErrors bean="${profesionalInstance}">
			<div class="ui-state-error ui-corner-all" style="padding: 0pt 0.7em;">
                <g:renderErrors bean="${profesionalInstance}" as="list" />
            </div>
            </g:hasErrors>
            <form action="save" method="post" enctype="multipart/form-data">
                <div id="tabs">
                	<ul>
						<li><a href="#tabs-1">Datos Generales</a></li>
						<li><a href="#tabs-2">Datos Complementarios</a></li>
                	</ul>
                	<div id="tabs-1">
                		<fieldset>
                		
								<div class="span-3 spanlabel">		                                    
		                                    <label style="float:left" for="institucion.id"><g:message code="profesional.institucion.label" default="Institución:" /></label>
								</div>
								<div class="span-4">		                                    
											<g:textField style="float:left" id="institucionDescId" class="ui-widget ui-corner-all ui-widget-content" name="institucionDesc" value="${profesionalInstance?.institucion?.nombre}" />
		                                    <g:hiddenField name="institucion.id" id="institucionId" value="${profesionalInstance?.institucion?.id}"></g:hiddenField>		                                    
								</div>		      
								<br/>
								<div class="clear"></div>
								<div class="span-3 spanlabel">                 
		                                    <label for="activo"><g:message code="profesional.activo.label" default="Activo:" /></label>
								</div>		                
								<div class="span-4">                    
		                                    <g:checkBox class="ui-widget ui-corner-all ui-widget-content" name="activo" value="${profesionalInstance?.activo}" />
								</div>		      
								<div class="clear"></div>
                		
                		
                            	<g:hasErrors bean="${profesionalInstance}" field="matricula">
                                	<div class="ui-state-error ui-corner-all" style="padding: 0pt 0.7em;">
                                </g:hasErrors>	
                				<div class="span-3 spanlabel">
		                                    <label for="matricula"><g:message code="profesional.matricula.label" default="Matrícula:" /></label>
		                        </div>
		                        <div class="span-4">             
		                                    <g:textField class="ui-widget ui-corner-all ui-widget-content" name="matricula" value="${fieldValue(bean: profesionalInstance, field: 'matricula')}" />
								</div>
                                <g:hasErrors bean="${profesionalInstance}" field="matricula">
                               		<g:renderErrors bean="${profesionalInstance}" as="list" field="matricula"/>
                               		</div>
                                </g:hasErrors>	
								
								
								<div class="clear"></div>
								<div class="span-3 spanlabel">                  
		                                    <label for="tipoMatricula"><g:message code="profesional.tipoMatricula.label" default="Tipo Matricula:" /></label>
								</div>		                
								<div class="span-4">         
		                                    <g:select class="ui-widget ui-corner-all ui-widget-content"  id="tipoMatriculaId" name="tipoMatricula" from="${com.medfire.enums.TipoMatriculaEnum?.list()}" 
		                                    	optionValue="name" value="${profesionalInstance?.tipoMatricula}" noSelection="['': '']" />
								</div>		                                    
								
								
								<div class="clear"></div>
                            	<g:hasErrors bean="${profesionalInstance}" field="nombre">
                                	<div class="ui-state-error ui-corner-all" style="padding: 0pt 0.7em;">
                                </g:hasErrors>	
		                        <div class="span-3 spanlabel">								
		                                    <label for="nombre"><g:message code="profesional.nombre.label" default="Nombre:" /></label>
								</div>
		                        <div class="span-4">										                                    
		                                    <g:textField class="ui-widget ui-corner-all ui-widget-content" name="nombre" value="${profesionalInstance?.nombre}" />
								</div>		
                                <g:hasErrors bean="${profesionalInstance}" field="nombre">
                               		<g:renderErrors bean="${profesionalInstance}" as="list" field="nombre"/>
                               		</div>
                                </g:hasErrors>	
								
								                                    
								<div class="clear"></div>
                            	<g:hasErrors bean="${profesionalInstance}" field="domicilio">
                                	<div class="ui-state-error ui-corner-all" style="padding: 0pt 0.7em;">
                                </g:hasErrors>	
		                        <div class="span-3 spanlabel">										                                    
		                                    <label for="domicilio"><g:message code="profesional.domicilio.label" default="Domicilio:" /></label>
								</div>
		                        <div class="span-4">																						                                    
		                                    <g:textField class="ui-widget ui-corner-all ui-widget-content" name="domicilio" value="${profesionalInstance?.domicilio}" />
								</div>
                               <g:hasErrors bean="${profesionalInstance}" field="domicilio">
                              		<g:renderErrors bean="${profesionalInstance}" as="list" field="domicilio"/>
	                              	</div>
                               </g:hasErrors>
										
										                                    	
								<div class="clear"></div>
		                        <div class="span-3 spanlabel">										                                    
		                                	<g:hasErrors bean="${profesionalInstance}" field="codigoPostal">
		                                    	<div class="ui-state-error ui-corner-all" style="padding: 0pt 0.7em;">
		                                    </g:hasErrors>	
		                                    <label for="codigoPostal"><g:message code="profesional.codigoPostal.label" default="Código Postal" /></label>
								</div>
								<div class="span-4">										                                    
		                                    <g:textField class="ui-widget ui-corner-all ui-widget-content" name="codigoPostal" value="${profesionalInstance?.codigoPostal}" />
				                                    <g:hasErrors bean="${profesionalInstance}" field="codigoPostal">
			                                    		<g:renderErrors bean="${profesionalInstance}" as="list" field="codigoPostal"/>
			                                    	</div>
			                                    	
				                                    </g:hasErrors>
				               	</div>     
				               	                	
								<div class="clear"></div>
		                        <div class="span-3 spanlabel">										                                    
		                            		<label><g:message code="profesional.provincia.label" default="Provincia:"/></label>
		                        </div>
		                        <div class="span-3">    		
		                            		<g:textField class="ui-widget ui-corner-all ui-widget-content" name="provinciadesc" id="provinciadescId" value="${profesionalInstance.localidad?.provincia?.nombre}" />
		                            		<g:hiddenField id="provinciaId" name="provincia" value="${profesionalInstance.localidad?.provincia?.id}"/>
		                                	<g:hasErrors bean="${profesionalInstance}" field="localidad">
		                                    	<div class="ui-state-error ui-corner-all" style="padding: 0pt 0.7em;">
		                                    </g:hasErrors>
								</div>
								
								<div class="clear"></div>		                                    
								<div class="span-3 spanlabel">		                                    	
		                                    <label for="localidad"><g:message code="profesional.localidad.label" default="Localidad" /></label>
								</div>
								<div class="span-4">		                                    
		                                    <g:textField class="ui-widget ui-corner-all ui-widget-content" id="localidaddescId" name="localidaddesc" value="${profesionalInstance?.localidad?.nombre}" />
		                                    <g:hiddenField id="localidadId" name="localidad.id" value="${profesionalInstance.localidad?.id}"/>
				                                    <g:hasErrors bean="${profesionalInstance}" field="localidad">
			                                    		<g:renderErrors bean="${profesionalInstance}" as="list" field="localidad"/>
			                                    	</div>
				                                    </g:hasErrors>	
								</div>		      
								
								<div class="clear"></div>      
								<div class="span-3 spanlabel">            
		                                	<g:hasErrors bean="${profesionalInstance}" field="telefono">
		                                    	<div class="ui-state-error ui-corner-all" style="padding: 0pt 0.7em;">
		                                    </g:hasErrors>	
		                                    <label for="telefono"><g:message code="profesional.telefono.label" default="Teléfono:" /></label>
								</div>
								<div class="span-4">		                                    
		                                    <g:textField class="ui-widget ui-corner-all ui-widget-content" name="telefono" value="${profesionalInstance?.telefono}" />
				                                    <g:hasErrors bean="${profesionalInstance}" field="telefono">
			                                    		<g:renderErrors bean="${profesionalInstance}" as="list" field="telefono"/>
			                                    	</div>
			                                    	
				                                    </g:hasErrors>	
								</div>
								
								<div class="clear"></div>
                            	<g:hasErrors bean="${profesionalInstance}" field="fechaNacimiento">
                                	<div class="ui-state-error ui-corner-all" style="padding: 0pt 0.7em;">
                                </g:hasErrors>	
								<div class="span-3 spanlabel">
		                                    <label for="fechaNacimiento"><g:message code="profesional.fechaNacimiento.label" default="Fecha Nacimiento:" /></label>
								</div>	
								<div class="span-4">	                                    
		                                    <g:textField class="ui-widget ui-corner-all ui-widget-content" type="text" id='fechaNacimientoId' name='fechaNacimiento' value="${g.formatDate(format:'dd/MM/yyyy',date:profesionalInstance?.fechaNacimiento)}" / noSelection="['': '']"/>
								</div>
                                <g:hasErrors bean="${profesionalInstance}" field="fechaNacimiento">
                               		<g:renderErrors bean="${profesionalInstance}" as="list" field="fechaNacimiento"/>
                               	</div>
                               	
                                </g:hasErrors>	
									
									                        
								<div class="clear"></div>
		                        <div class="span-3 spanlabel">										                                    
		                                	<g:hasErrors bean="${profesionalInstance}" field="numeroDocumento">
		                                    	<div class="ui-state-error ui-corner-all" style="padding: 0pt 0.7em;">
		                                    </g:hasErrors>
		                                    <label for="numeroDocumento"><g:message code="profesional.numeroDocumento.label" default="Numero Documento:" /></label>
		                        </div>
		                        <div class="span-4">            
		                                    <g:textField class="ui-widget ui-corner-all ui-widget-content" name="numeroDocumento" value="${fieldValue(bean: profesionalInstance, field: 'numeroDocumento')}" />
				                                    <g:hasErrors bean="${profesionalInstance}" field="numeroDocumento">
			                                    		<g:renderErrors bean="${profesionalInstance}" as="list" field="numeroDocumento"/>
			                                    	</div>
			                                    	
				                                    </g:hasErrors>	
								</div>

								<div class="clear"></div>
								<div class="span-3 spanlabel">
		                                	<g:hasErrors bean="${profesionalInstance}" field="tipoDocumento">
		                                    	<div class="ui-state-error ui-corner-all" style="padding: 0pt 0.7em;">
		                                    </g:hasErrors>	
		                                    <label for="tipoDocumento"><g:message code="profesional.tipoDocumento.label" default="Tipo Documento:" /></label>
								</div>
								<div class="span-4">		                                    	
		                                    <g:select class="ui-widget ui-corner-all ui-widget-content" id="tipodocumentoId" name="tipoDocumento" from="${com.medfire.enums.TipoDocumentoEnum?.list()}" optionValue="name" value="${profesionalInstance?.tipoDocumento}" noSelection="['': '']" />
				                                    <g:hasErrors bean="${profesionalInstance}" field="tipoDocumento">
			                                    		<g:renderErrors bean="${profesionalInstance}" as="list" field="tipoDocumento"/>
			                                    	</div>
			                                    	
				                                    </g:hasErrors>	
								</div>		                        
                		</fieldset>
		            </div>
		            <div id="tabs-2">
		            	<fieldset>
		            			<div class="span-3 spanlabel">
		                                    <label for="observaciones"><g:message code="profesional.observaciones.label" default="Observaciones:" /></label>
		                        </div>
		                        <div class="span-4">            
		                                    <g:textField class="ui-widget ui-corner-all ui-widget-content inputlarge" name="observaciones" value="${profesionalInstance?.observaciones}" />
								</div>
								
								<div class="clear"></div>
		                        <div class="span-3 spanlabel">    
		                                    <label  for="codigoIva"><g:message code="profesional.codigoIva.label" default="Codigo I.V.A:" /></label>
		                        </div>
		                        <div class="span-4">            
		                                    <g:select class="ui-widget ui-corner-all ui-widget-content"  id="codigoivaId" name="codigoIva" from="${com.medfire.enums.IvaEnum?.list()}" optionValue="name" value="${profesionalInstance?.codigoIva}" noSelection="['': '']" />
		                        </div>     		                        
		                        
		                        <div class="clear"></div>
                               	<g:hasErrors bean="${profesionalInstance}" field="cuit">
                                   	<div class="ui-state-error ui-corner-all" style="padding: 0pt 0.7em;">
                                </g:hasErrors>	
								<div class="span-3 spanlabel">	
		                                    <label for="cuit"><g:message code="profesional.cuit.label" default="C.U.I.T:" /></label>
		                        </div>
		                        <div class="span-4">            
		                                    <g:textField class="ui-widget ui-corner-all ui-widget-content" name="cuit" value="${profesionalInstance?.cuit}" />
								</div>
                                  <g:hasErrors bean="${profesionalInstance}" field="cuit">
                                 		<g:renderErrors bean="${profesionalInstance}" as="list" field="cuit"/>
                                 	</div>
                                 	
                                  </g:hasErrors>	
								
								
								<div class="clear"></div>
                            	<g:hasErrors bean="${profesionalInstance}" field="fechaIngreso">
                                	<div class="ui-state-error ui-corner-all" style="padding: 0pt 0.7em;">
                                </g:hasErrors>	
								<div class="span-3 spanlabel">		                                    
		                                    <label for="fechaIngreso"><g:message code="profesional.fechaIngreso.label" default="Fecha Ingreso:" /></label>
								</div>
								<div class="span-4">		                                    
		                                    <g:textField class="ui-widget ui-corner-all ui-widget-content" type="text" id='fechaIngresoId' name='fechaIngreso' value="${g.formatDate(format:'dd/MM/yyyy',date:profesionalInstance?.fechaIngreso)}" / noSelection="['': '']"/>
		                        </div>
                                <g:hasErrors bean="${profesionalInstance}" field="fechaIngreso">
                               		<g:renderErrors bean="${profesionalInstance}" as="list" field="fechaIngreso"/>
                               		</div>
                                </g:hasErrors>		                        

								<div class="clear"></div>
								<div class="span-3 spanlabel">		                                    
		                                    <label style="float:left" for="especialidad"><g:message code="profesional.especialidad.label" default="Especialidad:" /></label>
								</div>
								<div class="span-4">		                                    
											<g:textField style="float:left" id="especialidadDescId" class="ui-widget ui-corner-all ui-widget-content" name="especialidadDesc" value="${profesionalInstance?.especialidad?.descripcion}" />
		                                    <g:hiddenField name="especialidad.id" id="especialidadId" value="${profesionalInstance?.especialidad?.id}"></g:hiddenField>		                                    
								</div>		      
								<br/>
								<div class="clear"></div>
								<div class="span-3 spanlabel">                 
		                                    <label for="activo"><g:message code="profesional.activo.label" default="Activo:" /></label>
								</div>		                
								<div class="span-4">                    
		                                    <g:checkBox class="ui-widget ui-corner-all ui-widget-content" name="activo" value="${profesionalInstance?.activo}" />
								</div>		      
								

								<div class="clear"></div>
								<div class="span-3 spanlabel">		                        
		                                    <label for="sexo"><g:message code="profesional.sexo.label" default="Sexo:" /></label>
								</div>		                
								<div class="span-4">                    
		                                    <g:select class="ui-widget ui-corner-all ui-widget-content"  id="sexoId" name="sexo" from="${com.medfire.enums.SexoEnum?.list()}" 
		                                    	optionValue="name" value="${profesionalInstance?.sexo}" noSelection="['': '']" />
								</div>		                                    	
		                                    	
		                        <div class="clear"></div>
		                        <div class="span-3 spanlabel">            	
		                                    <label for="photo"><g:message code="profesional.photo.label" default="Foto:" /></label>
								</div>		                
								<div class="span-4">                    
		                                    <input class="ui-widget ui-corner-all ui-widget-content" type="file" name="photo" />
		            			</div>
		            	</fieldset>
		            </div>        
		        </div>
                <div class="buttons">
                    <span class="button"><g:submitButton name="create" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only" value="${message(code: 'default.button.create.label', default: 'Create')}" /></span>
                </div>
            </form>
        </div>
    </body>
</html>
