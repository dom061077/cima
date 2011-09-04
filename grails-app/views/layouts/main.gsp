<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN"
   "http://www.w3.org/TR/html4/strict.dtd">

<%@ page import="org.codehaus.groovy.grails.plugins.springsecurity.AuthorizeTools"

%>



<html>
    <head>
        <title><g:layoutTitle default="Grails" /></title>
	    <link rel="stylesheet" href="${resource(dir:'css/blueprint',file:'screen.css')}" type="text/css" media="screen, projection">
    	<link rel="stylesheet" href="${resource(dir:'css/blueprint',file:'print.css')}" media="print">
    	<!--[if lt IE 8]><link rel="stylesheet" href="${resource(dir:'css/blueprint',file:'ie.css')}" type="text/css" media="screen, projection"><![endif]-->


         <link rel="stylesheet" href="${resource(dir:'css',file:'jquery-ui-1.8.11.custom.css')}" />
        
        <link rel="shortcut icon" href="${resource(dir:'images',file:'favicon.ico')}" type="image/x-icon" />
        
        <script type="text/javascript" src="${resource(dir:'js/jquery',file:'jquery-1.5.1.min.js')}"></script>
        <script type="text/javascript" src="${resource(dir:'js/jquery-ui/js',file:'jquery-ui-1.8.11.custom.min.js')}"></script>
        <script type="text/javascript" src="${resource(dir:'js/jquery-ui',file:'jquery-ui-i18n.js')}"></script>
        <script type="text/javascript" src="${resource(dir:'js/jquery',file:'json2.js')}"></script>
        <link rel="stylesheet" type="text/css" media="screen" href="${g.resource(dir:'css',file:'custom.css')}" />
        <link rel="stylesheet" type="text/css" media="screen" href="${g.resource(dir:'js/jquery-ui/js/src/css',file:'ui.jqgrid.css')}" />
        <link rel="stylesheet" type="text/css" media="screen" href="${g.resource(dir:'js/jquery-ui/js/src/css',file:'jquery.searchFilter.css')}" />
        

		
		
        <link rel="stylesheet" href="${resource(dir:'css/menu',file:'dropdown.css')}" type="text/css" media="screen, projection"/>        
        <link rel="stylesheet" href="${resource(dir:'css/menu',file:'default.advanced.css')}" type="text/css" media="screen, projection"/>
            
                
        <script type="text/javascript" src="${g.resource(dir:'js/jquery-ui/js/src/i18n',file:'grid.locale-es.js')}"></script>
         <script type="text/javascript" src="${g.resource(dir:'js/jquery-ui/js',file:'jquery.jqGrid.min.js')}"></script>        
        <script type="text/javascript" src="${resource(dir:'js/jquery-ui/js',file:'jquery.timer.js')}"></script> 
		<script type="text/javascript" src="${resource(dir:'js/jquery',file:'jquery.dropdown.js')}"></script>               
		<script type="text/javascript" src="${resource(dir:'js/jquery',file:'jquery.contextmenu.js')}"></script>
        <script type="text/javascript">
			var urlpacientes = '<%out << g.createLink(controller:'event',action:'listatencionjson');%>'
			var urlsesion = '<%out << g.createLink(controller:"login",action:"auth")%>';
			var profesionalesId=0;
			
			
			
			<%
				if (AuthorizeTools.ifAnyGranted("ROLE_ADMIN,ROLE_PROFESIONAL")){
					out << "turnosprof = true;";
					 
					out << "var locturnos='"+g.createLink(controller:"event",action:"atenciondeldia")+"';";
					out << "var locupdturnos ='"+g.createLink(controller:"event",action:"updateestado")+"';";
					out << "var locpacientes ='"+g.createLink(controller:"event",action:"editpaciente")+"';";
				}
			%>
        </script>        
		<g:ifAnyGranted role="ROLE_PROFESIONAL">
			<script type="text/javascript" src="${g.resource(dir:'js/script',file:'scriptatencion.js')}"></script>
			
		</g:ifAnyGranted>
		
        
        <script type="text/javascript" src="${resource(dir:'js',file:'main.js')}"></script> 
        
        <g:layoutHead />
        <g:javascript library="application" />
        <style type="text/css">
        	body{
       			font-size:65%;
       			}
        
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

    </head>
    <body>

<div class="container">

    
<div style="display:none" id="MenuJqGrid">

        <ul>
            <li id="atendido">
                <img src="themes/contextMenu/edit.png" />
                Atendido</li>
            <li id="ausente">
                <img src="themes/contextMenu/cut.png" />
                Ausente</li>
            <li id="anulado">
                <img src="themes/contextMenu/copy.png" />
                Anulado</li>
            <li id="pendiente">
                <img src="themes/contextMenu/paste.png" />
                Pendiente</li>
            <li id="datosdelpaciente">
                <img src="themes/contextMenu/delete.png" />
                Datos del Paciente</li>

        </ul>
    </div>
    
	<div style="position:absolute;top:200px;left:10px; id="exploradorId">
			<div id="turnosesperaId">
					<table id="listturnos"></table>
			</div>		
	</div>
	
			<div style="display:none" id="dialog-form" title="Cambiar estado turno">
				
			
				<form id="formturnosId">
					<table>
						<tr class="prop">
							<td>
								<label for='pacienteturno'>Paciente:</label>
							</td>
							<td>
								<input type="text" disabled name='pacienteturno' id='pacienteturnoId' />
								<input type="hidden" name="id" id="turnoId" />
								<input type="hidden" name="cmd" value="update" />
								<input type="hidden" name="version" id="versionId"/>
							</td>
						</tr>
						<tr class="prop">
							<td valign="top" class="name">
								<label for="fechaturno">Fecha y Hora del Turno:</label>
							</td>
							<td>
								<input type="text" disabled id="fechaturnoId"/>
							</td>
						</tr>								
						
						<tr class="prop">
							<td valign="top" class="name">
								<label for='estado'>Estado del Turno:</label>	
							</td>
							<td>
								<g:select name="estado" id="estadoeventId" from="${EstadoEvent?.list()}" 
														optionValue="name" />
							</td>							
						</tr>
					</table>									
				</form>
			</div>
	
    
        <div>

            <div>
            
            	 
            </div><!-- end branding -->
            <g:isLoggedIn>
				<div>
					Usuario: <%out << g.loggedInUserInfo(field:"userRealName")%>
				</div>
			</g:isLoggedIn>

            <div>
            	<g:isLoggedIn>
		            <ul class="dropdown dropdown-horizontal" >
						<li><a href="./" class="dir">Archivo</a>
							<ul>
								<g:ifAnyGranted role="ROLE_ADMIN">
									<li><a href="${createLink(controller:'user')}">Usuarios</a></li>
									<li><a href="${createLink(controller:'role')}">Roles</a></li>
									<li><a href="${createLink(controller:'requestmap')}">Requestmap</a></li>
								</g:ifAnyGranted>
								<li><a href="./">Cambiar Contraseña</a></li>
							</ul>
						</li>
							<li><a href="./" class="dir">Actualizaciones</a>
								<ul>
									<g:ifAnyGranted role="ROLE_ADMIN,ROLE_USER">
										<li><a href="${createLink(controller:'obraSocial',action:'list')}">Obra Social</a></li>
										<li><a href="${createLink(controller:'event',action:'create')}">Turnos</a></li>
										<li><a href="${createLink(controller:'profesional',action:'list')}">Profesionales</a> </li>
										<li><a href="${createLink(controller:'laboratorio',action:'list')}">Laboratorios</a> </li>
										<li><a href="${createLink(controller:'especialidadMedica',action:'list')}">Especialidades</a> </li>			
									</g:ifAnyGranted>
									<g:ifAnyGranted role="ROLE_ADMIN,ROLE_USER,ROLE_PROFESIONAL">				
										<li><a href="${createLink(controller:'paciente',action:'list')}">Pacientes</a></li>
										<li><a href="${createLink(controller:'vademecum',action:'list')}">Vademecum</a></li>
										<li><a href="${createLink(controller:'historiaClinica',action:'list')}">Historia Cínica</a> </li>
									</g:ifAnyGranted>
								</ul>
							</li>
						
						<li><a href="${createLink(controller:'logout',action:'index')}">Cerrar Sesión</a></li>
					</ul>
				</g:isLoggedIn>	
            </div>
			
            <hr />
            
        </div><!-- end header -->

		<div>
			<div>
	        	<g:layoutBody />
			</div>
		</div>	
        <div>
        	<p>
            &copy; Copyright 2011 &lt;Medfire&gt; | Design by: Marca Registrada
            </p>
        </div><!-- end footer -->

		
		
</div><!-- div page -->

    			
    </body>
</html>