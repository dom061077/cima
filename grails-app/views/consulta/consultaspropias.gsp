<html>
	<head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'consulta.label', default: 'Consulta')}" />
        <script type="text/javascript" src="${resource(dir:'js/jquery',file:'jquery.jlookupfield.js')}"></script>
        <title>Visitas de mis Pacientes</title>
        <script type="text/javascript">
        	var buscar=<%out << "${buscar}"%>;
        	$(document).ready(function(){
        		$("#fechaDesdeId" ).datepicker($.datepicker.regional[ "es" ]);
        		$("#fechaHastaId" ).datepicker($.datepicker.regional[ "es" ]);
            	
        		$("#pacienteId").lookupfield({
        			source:"<% out << g.createLink(controller:'paciente',action:'listsearchjson')%>",
        			title:'Búsqueda de Visitas',
        			colnames:['Id','Apellido','Nombre'],
        			colModel:[
        					{name:'id',index:'id', width:10, sorttype:"int", sortable:true,hidden:false,search:false},
        					{name:'apellido',index:'apellido', width:100,  sortable:true,search:true},
        					{name:'nombre',index:'nombre', width:100,  sortable:true,search:true},	
        				],
        			hiddenid:'pacienteIdId',
        			descid:'pacienteId',
        			hiddenfield:'id',
        			descfield:['apellido','nombre']	
        		});	
        		
        		$("#pacienteId").autocomplete({
        				source: "<% out << g.createLink(controller:"paciente",action:'listjsonautocomplete')%>",
        				minLength:2,
        				select: function(event,ui){
        					if(ui.item){
        						$("#pacienteIdId").val(ui.item.id);
        					}
        				}
        	        }); 	

    			$("#generalgrid").jqGrid({
        			caption:'Visitas',
    				url:'<%out << "${g.createLink(controller:'consulta',action:'listjsonconsultaspropias')}"%>',
    				postData:{
	    		   		fechaDesde:function(){
		    		   		if($('#fechaDesdeId').val==undefined)
			    		   		return '';
		    		   		else
		   						return $('#fechaDesdeId').val();
		   				},
			   			fechaHasta:function(){
		    		   		if($('#fechaHastaId').val==undefined)
			    		   		return '';
		    		   		else
		   						return $('#fechaHastaId').val();
				   		},
			   			pacienteId:function(){
		    		   		if($('#pacienteIdId').val==undefined)
			    		   		return '';
		    		   		else
		   						return $('#pacienteIdId').val();
				   		}
    				},
    				datatype: "json",
    				mtype:'POST',
    				colNames: ['Id','Fecha Consulta','Cie10','Cie10 Desc.','Apellido','Nombre','Ambito'],
    				colModel: [
    					{name:"id",index:"id",width:80,key:true,hidden:true},				           
    					{name:"fechaConsulta",index:"fechaConsulta",width:80,sorttype:'date', formatter:'date'},
    					{name:"paciente_apellido",index:"paciente_apellido",width:100,sortable:true},
    					{name:"paciente_nombre",index:"paciente_nombre",width:100,sortable:true},
    					{name:"cie10_cie10",index:"cie10_cie10",width:70},
    					{name:"cie10_descripcion",index:"cie10_descripcion",width:130},					
    					{name:"estado",index:"estado",width:70,align:"left"}
    				],
    			   	rowNum:20,
    			   	pager: '#pagergeneral',
    			   	sortname: 'fechaConsulta',
    			    sortorder: "desc"
    			});

    			$("#generalgrid").jqGrid('navGrid','#pagergeneral',{search:false,edit:false,add:false,del:false});
    			$("#generalgrid").jqGrid('navButtonAdd','#pagergeneral',{
    			       caption:"Informe", 
    			       onClickButton : function () {
    			    		   window.location = '<%out << createLink(controller:"historiaClinica",action:"reportehistoriapropio")%>?target=_blank';
    			       } 
    			});	
    	         
            });
        </script>
	</head>
	<body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
        </div>
		<div class="body">
            <h1>Visitas de mis Pacientes</h1>
            <g:if test="${flash.message}">
            	<div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${cmdInstance}">
	            <div class="ui-state-error ui-corner-all" style="padding: 0pt 0.7em;">
	                <g:renderErrors bean="${cmdInstance}" as="list" />
	            </div>
            </g:hasErrors>
            <g:form action="consultaspropiasbuscar">
		            <div class="span-20">
		   				<fieldset>
							<div class="span-2 spanlabel">
								<label for="fechaDesde">Desde:</label>
							</div>
							<div class="span-3">
								<g:textField id="fechaDesdeId" class="ui-widget ui-corner-all ui-widget-content" name="fechaDesde" value="${cmdInstance.fechaDesde}"/>
							</div>        				
							<div class="span-2 spanlabel">
								<label for="fechaHasta">Hasta:</label>
							</div>
							<div class="span-3">
								<g:textField id="fechaHastaId" class="ui-widget ui-corner-all ui-widget-content" name="fechaHasta" value="${cmdInstance.fechaHasta}"/>
							</div>        				
							
							<div class="span-2 spanlabel">
								<label for="profesional">Paciente:</label>
							</div>
							<div class="span-4">
								<g:textField id="pacienteId" class="ui-widget ui-corner-all ui-widget-content" name="paciente" value="${cmdInstance.paciente}"/>
								<g:hiddenField id="pacienteIdId" name="pacienteId" value="${cmdInstance.pacienteId}"/>
							</div>        				
							<div class="span-3  prepend-2"><g:submitButton class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only"  name="create" 
															value="${message(code: 'default.button.search.label', default: 'Create')}"/> </div>
							
												        				
		      			</fieldset>
		      		</div>	
      		</g:form>
      		<div class="clear"></div>
            <div class="span-20">
	        			<table id="generalgrid"></table>
	        			<div id="pagergeneral"></div>
            </div>
		</div>	
	</body>
</html>