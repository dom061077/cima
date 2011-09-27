

<%@ page import="com.medfire.Paciente";
  @	page import="java.text.SimpleDateFormat";
  @ page import="com.medfire.Provincia";
 %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        
        <g:set var="entityName" value="${message(code: 'paciente.label', default: 'Paciente')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
        
        <script type="text/javascript" src="${resource(dir:'js/jquery',file:'jquery.jlookupfield.js')}"></script>
        
        <script type="text/javascript">
        		$(document).ready(
						function (){
							$( "#tabs" ).tabs();
							
							$('#dniId').focus();	
							$("#fechaNacimientoId" ).datepicker("option", $.datepicker.regional[ "es" ] );
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
							 
								$("#osDescripcionId").lookupfield({
									source:"<% out << g.createLink(controller:'obraSocial',action:'listsearchjson')%>",
									title:'Búsqueda de Obra Social',
									colnames:['Id','Descripción'],
									colModel:[
											{name:'id',index:'id', width:10, sorttype:"int", sortable:true,hidden:false,search:false},
											{name:'descripcion',index:'descripcion', width:100,  sortable:true,search:true},	
										],
									hiddenid:'osId',
									descid:'osDescripcionId',
									hiddenfield:'id',
									descfield:['descripcion']	
										
								});

								$( "#osDescripcionId" ).autocomplete({
									source: "<% out << g.createLink(controller:'obraSocial',action:'listjsonautocomplete') %>",
									minLength: 2,
									select: function( event, ui ) {
										if(ui.item){
											$("#osId").val(ui.item.id)
										}
										
									},
									open: function() {
										$( this ).removeClass( "ui-corner-all" ).addClass( "ui-corner-top" );
									},
									close: function() {
										$( this ).removeClass( "ui-corner-top" ).addClass( "ui-corner-all" );
									}
								});
								
							/*
								combo de provincias y localidades
							*/

							function cargarlocalidades(provinciaid){
								$.getJSON("<% out << g.createLink(controller:'localidad',action:'listjsonlocalidad')%>"
										,{provinciaid:provinciaid,ajax:'true'}
										,function(j){
												var options = '';
												for (var i=0;i<j.length;i++){
													options += '<option value="' + j[i].id + '">' + j[i].label + '</option>';
												}
												$("#localidadId").html(options);
											}
										);
							}
							
					      	
														
							$.widget( "ui.combobox", {
								_create: function() {
									var self = this,
										select = this.element.hide(),
										selected = select.children( ":selected" ),
										value = selected.val() ? selected.text() : "";
									var minlength=0;
									if (select[0].name=="localidad.id"){
										minlength=2;
									}		
									var input = this.input = $( "<input>" )
										.insertAfter( select )
										.val( value )
										.autocomplete({
											delay: 0,
											minLength: minlength,
											source:function( request, response ) {
												var provinciaid=$('#provinciaId').val();
												$.getJSON("<% out << g.createLink(controller:'localidad',action:'listjsonautocomplete')%>"
														,{provinciaid:provinciaid,term:request.term,ajax:'true'}
														,function(j){
																var options = '';
																for (var i=0;i<j.length;i++){
																	options += '<option value="' + j[i].id + '">' + j[i].label + '</option>';
																}
																$("#localidadId").html(options);
															}
														);
													
												var matcher = new RegExp( $.ui.autocomplete.escapeRegex(request.term), "i" );
												response( select.children( "option" ).map(function() {
													var text = $( this ).text();
													if ( this.value && ( !request.term || matcher.test(text) ) )
														return {
															label: text.replace(
																new RegExp(
																	"(?![^&;]+;)(?!<[^<>]*)(" +
																	$.ui.autocomplete.escapeRegex(request.term) +
																	")(?![^<>]*>)(?![^&;]+;)", "gi"
																), "<strong>$1</strong>" ),
															value: text,
															option: this
														};
												}) );
											},
											select: function( event, ui ) {
												if (select[0].id=='provinciaId'){
													cargarlocalidades(ui.item.option.value);
												}
												
												ui.item.option.selected = true;
												self._trigger( "selected", event, {
													item: ui.item.option
												});
										         $('#localidadId option[value='+ui.item.option.value+']').attr('selected', 'selected'); 
												
											},
											change: function( event, ui ) {
												if ( !ui.item ) {
													var matcher = new RegExp( "^" + $.ui.autocomplete.escapeRegex( $(this).val() ) + "$", "i" ),
														valid = false;
													select.children( "option" ).each(function() {
														if ( $( this ).text().match( matcher ) ) {
															this.selected = valid = true;
															return false;
														}
													});
													if ( !valid ) {
														// remove invalid value, as it didn't match anything
														$( this ).val( "" );
														select.val( "" );
														input.data( "autocomplete" ).term = "";
														return false;
													}
												}
											}
										})
									.addClass( "ui-widget ui-widget-content ui-corner-left" );

									input.data( "autocomplete" )._renderItem = function( ul, item ) {
										return $( "<li></li>" )
											.data( "item.autocomplete", item )
											.append( "<a>" + item.label + "</a>" )
											.appendTo( ul );
									};

									this.button = $( "<button type='button'>&nbsp;</button>" )
										.attr( "tabIndex", -1 )
										.attr( "title", "Show All Items" )
										.insertAfter( input )
										.button({
											icons: {
												primary: "ui-icon-triangle-1-s"
											},
											text: false
										})
										.removeClass( "ui-corner-all" )
										.addClass( "ui-corner-right ui-button-icon-only" )
										.click(function() {
											// close if already visible
											if ( input.autocomplete( "widget" ).is( ":visible" ) ) {
												input.autocomplete( "close" );
												return;
											}

											// pass empty string as value to search for, displaying all results
											input.autocomplete( "search", "" );
											input.focus();
										});
								},
	
								destroy: function() {
									this.input.remove();
									this.button.remove();
									this.element.show();
									$.Widget.prototype.destroy.call( this );
								}
							});
							
							$("#provinciaId").combobox();
							$("#localidadId").combobox();

							
						

						

						});
                
        </script>
        <style type="text/css">
        	.ui-button-text{
        		padding: 1px 1px 1px 1px;
        	}
        </style>

        
    </head>
    <body>
    
    
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
        </div>
            <h1><g:message code="default.create.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
        	<%--
            <g:hasErrors bean="${pacienteInstance}">
            <div class="errors">
                <g:renderErrors bean="${pacienteInstance}" as="list" />
            </div>
            </g:hasErrors>
            --%>
            
			            
            
            <g:form action="save" >
                

			<g:hiddenField name="eventId"  value="${eventId}"/>
			<g:hiddenField name="eventVersion" value="${eventVersion}"/>
			

                
			<g:hasErrors bean="${pacienteInstance}">
				<div class="ui-state-error ui-corner-all" style="padding: 0pt 0.7em;"> 
					<g:renderErrors bean="${pacienteInstance}" as="list" /> 
				</div>			
            </g:hasErrors>
            
			<div id="tabs">
				<ul>
					<li><a href="#tabs-1">Datos Generales</a></li>
					<li><a href="#tabs-2">Datos Complementarios</a></li>
					<li><a href="#tabs-3">Otros Datos</a></li>
				</ul>
				<div id="tabs-1">
							<fieldset>
									<div class="span-3 spanlabel">
			                                    <label for="tipoDocumento"><g:message code="paciente.tipodocumento.label" default="Tipo Documento:" /></label>
									</div>			  
									<div class="span-4">                                   		
												<g:select class="ui-widget ui-corner-all ui-widget-content"  id="tipodocumentoId" name="tipoDocumento" from="${com.medfire.enums.TipoDocumentoEnum.list()}" optionValue="name"  value="${pacienteInstance?.tipoDocumento}"  />
		                            </div>
		                            <div class="clear"></div>        	

			                        <g:hasErrors bean="${pacienteInstance}" field="dni">
										<div class="ui-state-error ui-corner-all">			                        
			                        </g:hasErrors>            
									<div class="span-3 spanlabel">
			                                    <label for="dni"><g:message code="paciente.dni.label" default="D.N.I:" /></label>
			                        </div>            
									<div class="span-4">                                   		
			                                	<g:textField class="ui-widget ui-corner-all ui-widget-content" id="dniId" name="dni" value="${pacienteInstance?.dni}" />
			                        </div>
                               		<g:hasErrors bean="${pacienteInstance}" field="dni">
                                    	<g:renderErrors bean="${pacienteInstance}" as="list" field="dni"/>
                                    	</div>
                                   	</g:hasErrors>
			                        			                                    

		                            <div class="clear"></div>
                                	<g:hasErrors bean="${pacienteInstance}" field="apellido">
                                    	<div class="ui-state-error ui-corner-all" style="padding: 0pt 0.7em;">
                                    </g:hasErrors>
		                                    	
									<div class="span-3 spanlabel">
			                                    <label for="apellido"><g:message code="paciente.apellido.label" default="Apellido:" /></label>
			                        </div>			                                    
									<div class="span-4">                                   		
				                                    <g:textField class="ui-widget ui-corner-all ui-widget-content" name="apellido" value="${pacienteInstance?.apellido}" />
			                        </div>
                                    <g:hasErrors bean="${pacienteInstance}" field="apellido">
                                   		<g:renderErrors bean="${pacienteInstance}" as="list" field="apellido"/>
	                                   	</div>
                                    </g:hasErrors>	

			                                    
		                            <div class="clear"></div>
                                    <g:hasErrors bean="${pacienteInstance}" field="nombre">
                                    	<div class="ui-state-error ui-corner-all" style="padding: 0pt 0.7em;">
                                    </g:hasErrors>
									<div class="span-3 spanlabel">
			                                    <label for="nombre"><g:message code="paciente.nombre.label" default="Nombre:" /></label>
			                        </div>
									<div class="span-4">                                   		
		                                    	<g:textField class="ui-widget ui-corner-all ui-widget-content" name="nombre" value="${pacienteInstance?.nombre}" />
			                        </div>
                                   	<g:hasErrors bean="${pacienteInstance}" field="nombre">
                                    	<g:renderErrors bean="${pacienteInstance}" as="list" field="nombre"/> 
                                    	</div>
                                   	</g:hasErrors>

		                            <div class="clear"></div>        	
									<div class="span-3 spanlabel">
			                                    <label for="domicilio"><g:message code="paciente.domicilio.label" default="Domicilio:" /></label>
			                        </div>			                                    
									<div class="span-4">                                   		
		                                    	<g:textField class="ui-widget ui-corner-all ui-widget-content" name="domicilio" value="${pacienteInstance?.domicilio}" />
			                        </div>		                                    	

		                            <div class="clear"></div>        	
									<div class="span-3 spanlabel">
												<label><g:message code="paciente.provincia.label" default="Provincia:"/></label>
			                        </div>												
									<div class="span-4">                                   		
												<g:select class="ui-widget ui-corner-all ui-widget-content" name="provinciaid" id="provinciaId" from="${Provincia.list()}" 
														value="${pacienteInstance.localidad?.provincia?.id}"  
														optionKey="id"
														optionValue="nombre" />
			                        </div>														
														
		                            <div class="clear"></div>
									<g:hasErrors bean="${pacienteInstance}" as="list" field="localidad">
										<div class="ui-state-error ui-corner-all" style="padding: 0pt 0.7em;">	
									</g:hasErrors>
									<div class="span-3 spanlabel">
			                        			<label> <g:message code="paciente.localidad.label" default="Localidad:" /></label>
			                        </div>														
									<div class="span-4">                                   		
		                        				<g:select class="ui-widget ui-corner-all ui-widget-content" name="localidad.id" id="localidadId" from="${localidades}" optionValue="nombre"
			                        				optionKey="id" 
			                        				value="${pacienteInstance.localidad?.id}"></g:select>
			                        </div>
                        			<g:hasErrors bean="${pacienteInstance}" field="localidad">	
                        				<g:renderErrors bean="${pacienteInstance}" as="list" field="localidad" />
                        				</div>
                        			</g:hasErrors>	
			                        														

		                            <div class="clear"></div>        	
									<div class="span-3 spanlabel">
			                                    <label for="sexo"><g:message code="paciente.sexo.label" default="Sexo:" /></label>
			                        </div>														
			                                    
									<div class="span-4">                                   		
			                                    <g:radioGroup name="sexo" labels="['Masculino','Femenino']" values="[0,1]" value="${pacienteInstance.sexo?1:0}">
			                                    	<p><g:message code="${it.label}" /> ${it.radio}</p>
			                                    </g:radioGroup>
			                        </div>														

		                            <div class="clear"></div>        	
									<div class="span-3 spanlabel">
			                                    <label for="codigoPostal"><g:message code="paciente.codigoPostal.label" default="Codigo Postal:" /></label>
			                        </div>														
			                                    
									<div class="span-4">                                   		
		                                    	<g:textField class="ui-widget ui-corner-all ui-widget-content" name="codigoPostal" value="${pacienteInstance?.codigoPostal}" />
			                        </div>														

		                            <div class="clear"></div>        	
									<div class="span-3 spanlabel">		                                    	
			                                    <label for="telefono"><g:message code="paciente.telefono.label" default="Teléfono:" /></label>
			                        </div>														
			                                    
									<div class="span-4">                                   		
		                                    	<g:textField class="ui-widget ui-corner-all ui-widget-content" name="telefono" value="${fieldValue(bean: pacienteInstance, field: 'telefono')}" />
			                        </div>														
			                                    
								</fieldset>
				</div>
				<div id="tabs-2">
					<fieldset>
						
								<div class="span-3 spanlabel">
	                                    <label for="estadoCivil"><g:message code="paciente.estadocivil.label" default="Estado civil:" /></label>
	                            </div>        
	                            <div class="span-4">
	                                    <g:select class="ui-widget ui-corner-all ui-widget-content" id="estadocivilId" name="estadoCivil" from="${com.medfire.enums.EstadoCivilEnum.list()}" optionValue="name" value="${pacienteInstance?.estadoCivil}"  />
	                            </div>        

								<div class="clear"></div>
                                <g:hasErrors bean="${pacienteInstance}" field="fechaNacimiento">
                                   	<div class="ui-state-error ui-corner-all" style="padding: 0pt 0.7em;">
                                </g:hasErrors>
	                            <div class="span-3 spanlabel">
	                                    <label for="fechaNacimiento"><g:message code="paciente.fechaNacimiento.label" default="Fecha Nacimiento:" /></label>
								</div>
								<div class="span-4">	                                    
	                                    <input class="ui-widget ui-corner-all ui-widget-content" type="text" id='fechaNacimientoId' name='fechaNacimiento' value="${g.formatDate(format:'dd/MM/yyyy',date:pacienteInstance?.fechaNacimiento)}" />
								</div>	                                    
                                <g:hasErrors bean="${pacienteInstance}" field="fechaNacimiento">
                               		<g:renderErrors bean="${pacienteInstance}" as="list" field="fechaNacimiento"/>
                               		</div>
                                </g:hasErrors>	 
										
								<div class="clear"></div>										
								<g:hasErrors bean="${pacienteInstance}" field="obraSocial">
									<div class="ui-state-error ui-corner-all" style="padding: 0pt 0.7em;">
								</g:hasErrors>
								<div class="span-3 spanlabel">
                                	<label for="obraSocial"><g:message code="paciente.obrasocial.label" default="Obra Social:" /></label>
                                </div>	
                                <div class="span-4">
                                	<g:textField id="osDescripcionId" class="ui-widget ui-corner-all ui-widget-content" name="obrasocialdescripcion" value="${pacienteInstance?.obraSocial?.descripcion}"/>
                                </div>
                                <g:hasErrors bean="${pacienteInstance}" field="obraSocial">
                                   	<g:renderErrors bean="${pacienteInstance}" as="list" field="obraSocial"/>
                                   	</div>
                                 </g:hasErrors>	 
                                 <g:hiddenField id="osId" name="obraSocial.id" value="${pacienteInstance?.obraSocial?.id}"/>

								<div class="clear"></div>
								<div class="span-3 spanlabel">                                   
                       				<label for="numeroAfiliado"><g:message code="paciente.numeroafiliado.label" default="Número afiliado:" /></label>
                       			</div>
                       			<div class="span-4">	
                       				<g:textField class="ui-widget ui-corner-all ui-widget-content" name="numeroAfiliado" value="${pacienteInstance?.numeroAfiliado}"/>
                       			</div>	
                       			<g:hasErrors bean="${pacienteInstance}" field="numeroAfiliado">
                       				<g:renderErrors bean="${pacienteInstance}" as="list" field="numeroAfiliado"/>
									</div>	    
								</g:hasErrors>                    

								<div class="clear"></div>                                   
								<g:hasErrors bean="${pacienteInstance}" field="cuit">
									<div class="ui-state-error ui-corner-all" style="padding: 0pt 0.7em;">
								</g:hasErrors>
								<div class="span-3 spanlabel">
                                   <label for="cuit"><g:message code="paciente.cuit.label" default="C.U.I.T:" /></label>
                                </div>
                                <div class="span-4">   
                                   <g:textField class="ui-widget ui-corner-all ui-widget-content" name="cuit" value="${pacienteInstance?.cuit}" />
                                </div>    
                                <g:hasErrors bean="${pacienteInstance}" as="list" field="cuit">
                                	<g:renderErrors bean="${pacienteInstance}" as="list" field="cuit"/>
                                	</div>
                                </g:hasErrors>
	                                    
	                                    
					</fieldset>	
				</div>
				<div id="tabs-3">
					<fieldset>
								<div class="span-3 spanlabel">
	                                    <label for="ocupacion"><g:message code="paciente.ocupacion.label" default="Ocupación:" /></label>
	                            </div>        
	                            <div class="span-4">
	                                    <g:textField class="ui-widget ui-corner-all ui-widget-content" name="ocupacion" value="${pacienteInstance?.ocupacion}" />
	                            </div>        

								<div class="clear"></div>	
								<div class="span-3 spanlabel">
	                                    <label for="medicoEnviante"><g:message code="paciente.medicoEnviante.label" default="Medico Enviante:" /></label>
	                            </div>
	                            <div class="span4">        
	                                    <g:textField class="ui-widget ui-corner-all ui-widget-content" name="medicoEnviante" value="${pacienteInstance?.medicoEnviante}" />
	                            </div>        

								<div class="clear"></div>
								<g:hasErrors bean="${pacienteInstance}" field="email">
									<div class="ui-state-error ui-corner-all" style="padding: 0pt 0.7em;">
								</g:hasErrors>
								<div class="span-3 spanlabel">
	                                    <label for="email"><g:message code="paciente.email.label" default="E-mail:" /></label>
								</div>	
								<div class="span-4">                                    
	                                    <g:textField class="ui-widget ui-corner-all ui-widget-content" name="email" value="${pacienteInstance?.email}" />
								</div>	                                    
                                <g:hasErrors bean="${pacienteInstance}" field="email">
                                 <g:renderErrors bean="${pacienteInstante} as="list" field="email"/>
                                 </div>
                                </g:hasErrors>
	                                    
	                                    
	                </fieldset>                    
				</div>
			</div>



       
                
                
                <div style="padding: 10px 15px 15px 15px;">
                    <g:submitButton name="create" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only" value="${message(code: 'default.button.create.label', default: 'Create')}" />
                </div>
                
            </g:form>
        
    </body>
</html>
