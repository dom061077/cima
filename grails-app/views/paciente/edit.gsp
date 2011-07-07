

<%@ page import="com.medfire.Paciente" %>
<%@ page import="com.medfire.enums.TipoDocumentoEnum" %>
<%@ page import="com.medfire.enums.EstadoCivilEnum" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'paciente.label', default: 'Paciente')}" />
        <title><g:message code="default.edit.label" args="[entityName]" /></title>
        <style type="text/css">
        				.ui-autocomplete-loading { background: white url('<%out << g.resource(dir:'images',file:'load.gif')%>') right center no-repeat; }

							.ui-autocomplete {
									max-height: 100px;
									overflow-y: auto;
									/* prevent horizontal scrollbar */
									overflow-x: hidden;
									/* add padding to account for vertical scrollbar */
									padding-right: 20px;
								}
								/* IE 6 doesn't support max-height
								 * we use height instead, but this forces the menu to always be this tall
								 */
								* html .ui-autocomplete {
									height: 100px;
							}

        
        </style>
        
        <script type="text/javascript">
        		$(document).ready(
					function (){

						$( "#tabs" ).tabs();
						
						$('#dniId').focus();	
						$( "#fechaNacimientoId" ).datepicker("option", $.datepicker.regional[ "es" ] );
						$('#fechaNacimientoId').datepicker({changeMonth:true
							,showOn: "button"
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
			                yearSuffix: '',
			                buttonImage:'<%  out << g.resource(dir:'images',file:'calendar.gif') %>'
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
											$.getJSON("<% out << g.createLink(controller:'localidad',action:'listjsonlocalidad')%>"
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
						
						$( "#provinciaId" ).combobox();
						$("#localidadId").combobox();
						//$("#localidadId").toggle();
						
													
					});
                
        </script>
        
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        </div>
            <h1><g:message code="default.edit.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            
            <g:hasErrors bean="${pacienteInstance}">
            <div class="errors">
                <g:renderErrors bean="${pacienteInstance}" as="list" />
            </div>
            </g:hasErrors>
            
            <g:form method="post" >
                <g:hiddenField name="id" value="${pacienteInstance?.id}" />
                <g:hiddenField name="version" value="${pacienteInstance?.version}" />
                <div class="dialog">
				<div id="tabs">
				<ul>
					<li><a href="#tabs-1">Datos Generales</a></li>
					<li><a href="#tabs-2">Datos Complementarios</a></li>
					<li><a href="#tabs-3">Otros Datos</a></li>
				</ul>
				<div id="tabs-1">


			                    <table>
			                        <tbody>
			                            <tr class="prop">
			                                <td valign="top" class="name">
			                                    <label for="tipoDocumento"><g:message code="paciente.tipodocumento.label" default="Tipo Documento:" /></label>
			                                </td>
			                                <td valign="top" class="value ${hasErrors(bean: pacienteInstance, field: 'tipoDocumento', 'errors')}">
			                                    <g:select name="tipoDocumento" from="${com.medfire.enums.TipoDocumentoEnum.list()}" optionValue="name"  value="${pacienteInstance?.tipoDocumento}"  />
			                                </td>
			                            </tr>

			
			                            <tr class="prop">
			                                <td width='150' valign="top" class="name">
			                                    <label for="dni"><g:message code="paciente.dni.label" default="D.N.I:" /></label>
			                                   
			                                </td>
			                                <td valign="top" class="value ${hasErrors(bean: pacienteInstance, field: 'dni', 'errors')}">
			                                    <g:textField id="dniId" name="dni" value="${pacienteInstance.dni}" />
			                                    <div class="errors"><g:renderErrors bean="${pacienteInstance}" as="list" field="dni"/></div> 
			                                </td>
			                            </tr>
			
			                        
			                            <tr class="prop">
			                                <td valign="top" class="name">
			                                    <label for="apellido"><g:message code="paciente.apellido.label" default="Apellido:" /></label>
			                                    
			                                </td>
			                                <td valign="top" class="value ${hasErrors(bean: pacienteInstance, field: 'apellido', 'errors')}">
			                                    <g:textField name="apellido" value="${pacienteInstance?.apellido}" />
			                                    <div class="errors"><g:renderErrors bean="${pacienteInstance}" as="list" field="apellido"/> </div>
			                                </td>
			                            </tr>
			
			                            <tr class="prop">
			                                <td valign="top" class="name">
			                                    <label for="nombre"><g:message code="paciente.nombre.label" default="Nombre:" /></label>
			                                    
			                                </td>
			                                <td valign="top" class="value ${hasErrors(bean: pacienteInstance, field: 'nombre', 'errors')}">
			                                    <g:textField name="nombre" value="${pacienteInstance?.nombre}" />
			                                    <div class="errors"><g:renderErrors bean="${pacienteInstance}" as="list" field="nombre"/> </div>
			                                </td>
			                            </tr>
			
			                            <tr class="prop">
			                                <td valign="top" class="name">
			                                    <label for="domicilio"><g:message code="paciente.domicilio.label" default="Domicilio:" /></label>
			                                    
			                                </td>
			                                <td valign="top" class="value ${hasErrors(bean: pacienteInstance, field: 'domicilio', 'errors')}">
			                                    <g:textField name="domicilio" value="${pacienteInstance?.domicilio}" />
			                                </td>
			                            </tr>
			
										
										<tr class="prop">
											<td valign="top" class="name">
												<label><g:message code="paciente.provincia.label" default="Provincia:"/></label>
											</td>
											<td valign="top" class="value ${hasErrors(bean: pacienteInstance, field: 'domicilio', 'errors')}">
												
												<div class="ui-widget">
													<g:select name="provinciaid" id="provinciaId" from="${com.medfire.Provincia.list()}" 
														value="${pacienteInstance.localidad?.provincia?.id}"  
														optionKey="id"
														optionValue="nombre" optionKey="id" />
												</div>
												
											</td>
										</tr>
			                        
			                        	<tr class="prop">
			                        		<td valign="top" class="name">
			                        			<label> <g:message code="paciente.localidad.label" default="Localidad:" /></label>
			                        		</td>
			                        		<td valign="top" class="value ${hasErrors(bean: pacienteInstance, field: 'domicilio', 'errors')}">
			                        			<div class="ui-widget">
			                        				<g:select name="localidad.id" id="localidadId" from="${localidades}" optionValue="nombre"
			                        				optionKey="id" 
			                        				value="${pacienteInstance.localidad?.id}"></g:select>
			                        			</div>
			                        			<div class="errors"><g:renderErrors bean="${pacienteInstance}" as="list" field="localidad" />
			                        		</td>
			                        	</tr>
			                        
			                        
			                        
			
			                            <tr class="prop">
			                                <td valign="top" class="name">
			                                    <label for="sexo"><g:message code="paciente.sexo.label" default="Sexo:" /></label>
			                                </td>
			                                <td valign="top" class="value ${hasErrors(bean: pacienteInstance, field: 'sexo', 'errors')}">
			                                	
			                                    <g:radioGroup name="sexo" labels="['Masculino','Femenino']" values="[0,1]" value="${pacienteInstance.sexo?1:0}">
			                                    	<p><g:message code="${it.label}" />: ${it.radio}</p>
			                                    	
			                                    </g:radioGroup>
			                                    
			                                </td>
			                            </tr>
						                        
			                            <tr class="prop">
			                                <td valign="top" class="name">
			                                    <label for="codigoPostal"><g:message code="paciente.codigoPostal.label" default="Codigo Postal:" /></label>
			                                </td>
			                                <td valign="top" class="value ${hasErrors(bean: pacienteInstance, field: 'codigoPostal', 'errors')}">
			                                    <g:textField name="codigoPostal" value="${pacienteInstance?.codigoPostal}" />
			                                </td>
			                            </tr>
						                        
			                            <tr class="prop">
			                                <td valign="top" class="name">
			                                    <label for="telefono"><g:message code="paciente.telefono.label" default="Teléfono:" /></label>
			                                </td>
			                                <td valign="top" class="value ${hasErrors(bean: pacienteInstance, field: 'telefono', 'errors')}">
			                                    <g:textField name="telefono" value="${fieldValue(bean: pacienteInstance, field: 'telefono')}" />
			                                    <div class="errors"><g:renderErrors bean="${pacienteInstance}" as="list" field="telefono" />
			                                </td>
			                            </tr>

										
	                        
	                        </tbody>
	                    </table>

					
				</div>
				<div id="tabs-2">
					<table>
						<tbody>
	                            <tr class="prop">
	                                <td valign="top" class="name">
	                                    <label for="estadoCivil"><g:message code="paciente.estadocivil.label" default="Estado civil:" /></label>
	                                </td>
	                                <td valign="top" class="value ${hasErrors(bean: pacienteInstance, field: 'estadoCivil', 'errors')}">
	                                    <g:select name="estadoCivil" from="${com.medfire.enums.EstadoCivilEnum.list()}" optionValue="name"  value="${pacienteInstance?.estadoCivil}"  />
	                                </td>
	                            </tr>
	                        
	                            <tr class="prop">
	                                <td width="150" valign="top" class="name">
	                                    <label for="fechaNacimiento"><g:message code="paciente.fechaNacimiento.label" default="Fecha Nacimiento:" /></label>
	                                    
	                                </td>
	                                <td valign="top" class="value ${hasErrors(bean: pacienteInstance, field: 'fechaNacimiento', 'errors')}">
	                                    <!-- g:datePicker name="fechaNacimiento" precision="day" value="${pacienteInstance?.fechaNacimiento}"  / -->
	                                    <input type="text" id='fechaNacimientoId' name='fechaNacimiento' value="${g.formatDate(format:'dd/MM/yyyy',date:pacienteInstance?.fechaNacimiento)}" />
	                                    <div class="errors"><g:renderErrors bean="${pacienteInstance}" as="list" field="fechaNacimiento"/> </div>
	                                </td>
	                            </tr>
	                        
	                        
	                        
	                            <tr class="prop">
	                                <td valign="top" class="name">
	                                    <label for="obraSocial"><g:message code="paciente.obrasocial.label" default="Obrasocial:" /></label>
										                                    
	                                </td>
	                                <td valign="top" class="value ${hasErrors(bean: pacienteInstance, field: 'obraSocial', 'errors')}">
	                                    <!-- g:select name="obrasocial.id" from="${com.medfire.ObraSocial.list()}" optionKey="id" value="${pacienteInstance?.obraSocial?.id}"  / -->
	                                    <div class="ui-widget">
	                                    	<g:textField id="osDescripcionId" name="obrasocialdescripcion" value="${pacienteInstance?.obraSocial?.descripcion}"/>
	                                    </div>	
	                                    <div class="errors"><g:renderErrors bean="${pacienteInstance}" as="list" field="obraSocial"/> </div>
	                                    <g:hiddenField id="osId" name="obraSocial.id" value="${pacienteInstance?.obraSocial?.id}"/>
	                                </td>
	                            </tr>
	                        
	                        	<tr class="prop">
	                        		<td valign="top" class="name">
	                        			<label for="numeroAfiliado"><g:message code="paciente.numeroafiliado.label" default="Número afiliado:" /></label>
	                        		</td>
	                        		
	                        		<td valign="top" class="value ${hasErrors(bean: pacienteInstance, field: 'obraSocial', 'errors')}">
	                        			<g:textField name="numeroAfiliado" value="${pacienteInstance?.numeroAfiliado}"/>
	                        			<div class="errors"><g:renderErrors bean="${pacienteInstance}" as="list" field="numeroAfiliado"/></div>
	                        		</td>
	                        	</tr>
	                        

	                            <tr class="prop">
	                                <td valign="top" class="name">
	                                    <label for="cuit"><g:message code="paciente.cuit.label" default="C.U.I.T:" /></label>
	                                </td>
	                                <td valign="top" class="value ${hasErrors(bean: pacienteInstance, field: 'cuit', 'errors')}">
	                                    <g:textField name="cuit" value="${pacienteInstance?.cuit}" />
	                                    <div class="errors"><g:renderErrors bean="${pacienteInstance}" as="list" field="cuit"/></div>
	                                </td>
	                            </tr>

						
						</tbody>
					</table>
				</div>
				<div id="tabs-3">
					<table>
						<tbody>
	                            <tr class="prop">
	                                <td valign="top" class="name">
	                                    <label for="ocupacion"><g:message code="paciente.ocupacion.label" default="Ocupación:" /></label>
	                                </td>
	                                <td valign="top" class="value ${hasErrors(bean: pacienteInstance, field: 'ocupacion', 'errors')}">
	                                    <g:textField name="ocupacion" value="${pacienteInstance?.ocupacion}" />
	                                </td>
	                            </tr>

	                            <tr class="prop">
	                                <td valign="top" class="name">
	                                    <label for="medicoEnviante"><g:message code="paciente.medicoEnviante.label" default="Medico Enviante:" /></label>
	                                </td>
	                                <td valign="top" class="value ${hasErrors(bean: pacienteInstance, field: 'medicoEnviante', 'errors')}">
	                                    <g:textField name="medicoEnviante" value="${pacienteInstance?.medicoEnviante}" />
	                                </td>
	                            </tr>

			                            <tr class="prop">
			                                <td valign="top" class="name">
			                                    <label for="email"><g:message code="paciente.email.label" default="E-mail:" /></label>
			                                    
			                                </td>
			                                <td valign="top" class="value ${hasErrors(bean: pacienteInstance, field: 'email', 'errors')}">
			                                    <g:textField name="email" value="${pacienteInstance?.email}" />
			                                    <div class="errors"><g:renderErrors bean="${pacienteInstante} as="list" field="email"/></div>
			                                </td>
			                            </tr>

						
						</tbody>
					</table>
				</div>
			</div>
                
                
                </div>
                <div style="padding: 10px 15px 15px 15px;">
                    <g:actionSubmit class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only" action="update" value="${message(code: 'default.button.update.label', default: 'Update')}" />
                    <g:actionSubmit class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
                </div>
            </g:form>
    </body>
</html>
