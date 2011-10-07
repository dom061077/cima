<html>
	<head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'consulta.label', default: 'Consulta')}" />
        <script type="text/javascript" src="${resource(dir:"js/script/consulta",file:"pacientesatendidos.js")}"></script>
        <script type="text/javascript" src="${resource(dir:'js/jquery',file:'jquery.jlookupfield.js')}"></script>
        <script type="text/javascript" src="${resource(dir:'js/jquery/chart',file:'jgcharts.pack.js')}"></script>        
        <script type="text/javascript" src="${resource(dir:'js/jquery/chart',file:'jquery.metadata.pack.js')}"></script>        
        <script type="text/javascript" src="${resource(dir:'js/jquery/chart',file:'jgtable.pack.js')}"></script>        
        <title>Pacientes Atendidos</title>
        <script type="text/javascript">
        	var buscar=<%out << "${buscar}"%>;
        	$(document).ready(function(){
        		$("#obraSocialId").lookupfield({
        			source:"<% out << g.createLink(controller:'obraSocial',action:'listsearchjson')%>",
        			title:'Búsqueda de Obra Social',
        			colnames:['Id','Descripción'],
        			colModel:[
        					{name:'id',index:'id', width:10, sorttype:"int", sortable:true,hidden:false,search:false},
        					{name:'descripcion',index:'descripcion', width:100,  sortable:true,search:true},	
        				],
        			hiddenid:'obraSocialIdId',
        			descid:'obraSocialId',
        			hiddenfield:'id',
        			descfield:['descripcion']	
        		});	
        		
        		$("#obraSocialId").autocomplete({
        				source: "<% out << g.createLink(controller:"obraSocial",action:'listjsonautocomplete')%>",
        				minLength:2,
        				select: function(event,ui){
        					if(ui.item){
        						$("#obraSocialIdId").val(ui.item.id);
        					}
        				}
        	        }); 	

        		$("#cie10Id").lookupfield({
        			source:"<% out << g.createLink(controller:'cie10',action:'listsearchjson')%>",
        			title:'Búsqueda de CIE10',
        			colnames:['Id','Descripción'],
        			colModel:[
        					{name:'id',index:'id', width:10, sorttype:"int", sortable:true,hidden:false,search:false},
        					{name:'cie10',index:'cie10', width:100,  sortable:true,search:true},
        					{name:'descripcion',index:'descripcion', width:100,  sortable:true,search:true}	
        				],
        			hiddenid:'cie10IdId',
        			descid:'cie10Id',
        			hiddenfield:'id',
        			descfield:['descripcion']	
        		});	
        		
        		$("#cie10Id").autocomplete({
        				source: "<% out << g.createLink(controller:"cie10",action:'listjsonautocomplete')%>",
        				minLength:2,
        				select: function(event,ui){
        					if(ui.item){
        						$("#cie10IdId").val(ui.item.id);
        					}
        				}
        	        });

        		$("#profesionalId").lookupfield({
        			source:"<% out << g.createLink(controller:'profesional',action:'listsearchjson')%>",
        			title:'Búsqueda de Profesional',
        			colnames:['Id','Nombre'],
        			colModel:[
        					{name:'id',index:'id', width:10, sorttype:"int", sortable:true,hidden:false,search:false},
        					{name:'nombre',index:'nombre', width:100,  sortable:true,search:true},	
        				],
        			hiddenid:'profesionalIdId',
        			descid:'profesionalId',
        			hiddenfield:'id',
        			descfield:['nombre']	
        		});	
        		
        		$("#profesionalId").autocomplete({
        				source: "<% out << g.createLink(controller:"profesional",action:'listjsonautocomplete')%>",
        				minLength:2,
        				select: function(event,ui){
        					if(ui.item){
        						$("#profesionalIdId").val(ui.item.id);
        					}
        				}
        	        }); 
    	         
        		 jQuery(".jgtable").jgtable();        		
            });
        </script>
	</head>
	<body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
        </div>
		<div class="body">
            <h1>Pacientes Atendidos</h1>
            <g:if test="${flash.message}">
            	<div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${cmdInstance}">
	            <div class="ui-state-error ui-corner-all" style="padding: 0pt 0.7em;">
	                <g:renderErrors bean="${cmdInstance}" as="list" />
	            </div>
            </g:hasErrors>
            <g:form action="pacientesatendidosbuscar">
		            <div class="span-20">
		   				<fieldset>
							<div class="span-2 spanlabel">
								<label for="fechaDesde">Desde:</label>
							</div>
							<div class="span-2">
								<g:textField id="fechaDesdeId" class="ui-widget ui-corner-all ui-widget-content" name="fechaDesde" value="${cmdInstance.fechaDesde}"/>
							</div>        				
							<div class="clear"></div>
							<div class="span-2 spanlabel">
								<label for="fechaHasta">Hasta:</label>
							</div>
							<div class="span-3">
								<g:textField id="fechaHastaId" class="ui-widget ui-corner-all ui-widget-content" name="fechaHasta" value="${cmdInstance.fechaHasta}"/>
							</div>        				
							<div class="clear"></div>
							
							<div class="span-2 spanlabel">
								<label for="profesional">Profesional:</label>
							</div>
							<div class="span-4">
								<g:textField id="profesionalId" class="ui-widget ui-corner-all ui-widget-content" name="profesional" value="${cmdInstance.profesional}"/>
								<g:hiddenField id="profesionalIdId" name="profesionalId" value="${cmdInstance.profesionalId}"/>
							</div>        				
							<div class="clear"></div>
		
							<div class="span-2 spanlabel">
								<label for="obraSocial">Obra Social:</label>
							</div>
							<div class="span-4">
								<g:textField id="obraSocialId" class="ui-widget ui-corner-all ui-widget-content" name="obraSocial" value="${cmdInstance.obraSocial}"/>
								<g:hiddenField id="obraSocialIdId" name="obraSocialId" value="${cmdInstance.obraSocialId}"/>
							</div>        				
							<div class="clear"></div>
							
							<div class="span-2 spanlabel">
								<label for="cie10">Diagnóstico:</label>
							</div>
							<div class="span-4">
								<g:textField id="cie10Id" class="ui-widget ui-corner-all ui-widget-content" name="cie10" value="${cmdInstance.cie10}"/>
								<g:hiddenField id="cie10IdId" name="cie10Id" value="${cmdInstance.cie10Id}"/>
							</div>   
							<div class="clear"></div>     				
							<div class="span-3  prepend-2"><g:submitButton class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only"  name="create" 
															value="${message(code: 'default.button.search.label', default: 'Create')}"/> </div>
												        				
												        				
		      			</fieldset>
		      		</div>	
      		</g:form>
      		<div class="clear"></div>
            <div class="span-20">
	            <div id="tabs">
	            	<ul>
	            		<li><a href="#tabs-1">General</a></li>
	            		<li><a href="#tabs-2">Por Profesional</a></li>           		
	            		<li><a href="#tabs-3">Diagnóstico</a></li>            		
	            		<li><a href="#tabs-4">Gráficos</a></li>
	            	</ul>
	        		<div id="tabs-1">
	        			<table id="generalgrid">
	        			</table>
	        			<div id="pagergeneral">
	        			</div>
	        		</div>
	        		<div id="tabs-2">
	        			<table id="profesionalgrid">
	        			</table>
	        			<div id="pagerprofesional">
	        			</div>
	        		</div>    
	        		<div id="tabs-3">
	        			<table id="diagnosticogrid">
	        			</table>
	        			<div id="pagerdiagnostico">
	        			</div>
	        		</div>    
	        		<div id="tabs-4">
	        			<div id="tabs-graficos">
	        				<ul>
	        					<li><a href="#tabs-graph1">Profesionales</a></li>
	        					<li><a href="#tabs-graph2">Obras Sociales</a></li>
	        					<li><a href="#tabs-graph3">Diagnóstico</a></li>
	        				</ul>
	        				<div id="tabs-graph1">
									<table id="table-3" class="jgtable {size:'500x150',type:'p3',axis_step:1,fillarea:true,fillbottom:true,filltop:false,bg_type:'gradient',bg_angle:90,grid:false,grid_x:7,grid_y:10,grid_line:0,grid_blank:1}">
									  <thead>
									    <tr>
									        <th>Profesional</th>
									        <th class="serie">Cant.Pacientes</th>
									    </tr>
									  </thead>
									  <tbody>
									  	<g:each var="prof" in="${profGraph}">
										    <tr>
										        <th class="serie">${prof[1]?.nombre}</th>
										        <td>${prof[0]}</td> 
										    </tr>
									    </g:each>
									  </tbody>
									</table>
							</div>
							
							<div id="tabs-graph2">
									<table id="table-3" class="jgtable {size:'500x150',type:'p3',axis_step:1,fillarea:true,fillbottom:true,filltop:false,bg_type:'gradient',bg_angle:90,grid:false,grid_x:7,grid_y:10,grid_line:0,grid_blank:1}">
									  <thead>
									    <tr>
									        <th>Obras Sociales</th>
									        <th class="serie">Cant.Pacientes</th>
									    </tr>
									  </thead>
									  <tbody>
									  	<g:each var="os" in="${osGraph}">
									  		<g:if test="${os[1]!=null}">
											    <tr>
											        <th class="serie">${os[1]?.descripcion}</th>
											        <td>${os[0]}</td> 
											    </tr>
										    </g:if>
									    </g:each>
									  </tbody>
									</table>
							</div>
							<div id="tabs-graph3">
									<table id="table-3" class="jgtable {size:'500x150',type:'p3',axis_step:1,fillarea:true,fillbottom:true,filltop:false,bg_type:'gradient',bg_angle:90,grid:false,grid_x:7,grid_y:10,grid_line:0,grid_blank:1}">
									  <thead>
									    <tr>
									        <th>Diagnóstico</th>
									        <th class="serie">Cant.Pacientes</th>
									    </tr>
									  </thead>
									  <tbody>
									  	<g:each var="cie10" in="${cie10Graph}">
									  		<g:if test="${cie10[1]!=null}">
											    <tr>
											        <th class="serie">${cie10[1]?.descripcion}</th>
											        <td>${cie10[0]}</td> 
											    </tr>
										    </g:if>
									    </g:each>
									  </tbody>
									</table>
							</div>
							
						</div>
	        		</div>    
	        		    
	            </div>
            </div>
		</div>	
	</body>
</html>