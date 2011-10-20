
<%@ page import="com.medfire.ObraSocial" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'obraSocial.label', default: 'Obra Social')}" />
         <link rel="stylesheet" type="text/css" media="screen" href="${g.resource(dir:'js/jquery-ui/js/src/css',file:'jquery.searchFilter.css')}" />        
        <script type="text/javascript" src="${g.resource(dir:'js/jquery-ui/js/src/i18n',file:'grid.locale-es.js')}"></script>
        <script type="text/javascript" src="${g.resource(dir:'js/jquery-ui/js',file:'jquery.jqGrid.min.js')}"></script>

        <title><g:message code="default.list.label" args="[entityName]" /></title>
        <script type="text/javascript">
        	$(document).ready(
					function(){
						jQuery("#list").jqGrid({
						   	url:'listjson',
							datatype: "json",
							width:680,
						   	colNames:['Id','C.U.I.T', 'Nombre', 'Razón Social','Telófono','C.P','Domicilio','Contacto','Operaciones'],
						   	colModel:[
						   		
						   		{name:'id',index:'id', width:40},
						   		{name:'cuit',index:'cuit', width:92,sortable:false},
						   		{name:'descripcion',index:'descripcion', width:100},
						   		{name:'razonSocial',index:'razonSocial', width:150, sortable:false},
						   		{name:'telefono',index:'telefono', width:80, align:"right", sortable:false},
						   		{name:'codigoPostal',index:'codigoPostal', width:40, align:"right", sortable:false},		
						   		{name:'domicilio',index:'domicilio', width:80,align:"right",sortable:false},
						   		{name:'contacto',index:'contacto', width:100, sortable:false},
						   		{name:'operaciones',index:'operaciones', width:55,sortable:false}
						   	],
						   	
						   	rowNum:10,
						   	rownumbers:true,
						   	rowList:[10,20,30],
						   	pager: '#pager',
						   	sortname: 'id',
						    viewrecords: true,
						    sortorder: "desc",
							gridComplete: function(){ 
								var ids = jQuery("#list").jqGrid('getDataIDs'); 
								for(var i=0;i < ids.length;i++){ 
									var cl = ids[i]; 
									be = "<a href='edit/"+ids[i]+"'><span class='ui-icon ui-icon-pencil' style='margin: 3px 3px 3px 10px'  ></span></a>"; 
									jQuery("#list").jqGrid('setRowData',ids[i],{operaciones:be}); 
									} 
							}, 						    
						    caption:"Listado de Obras Sociales"
						});
						jQuery("#list").jqGrid('navGrid','#pager',{edit:false,add:false,del:false,pdf:true});

						jQuery("#list").jqGrid('navButtonAdd','#pager',{
						       caption:"Informe", 
						       onClickButton : function () { 
						           //jQuery("#list").excelExport();
						           jQuery("#list").jqGrid("excelExport",{url:"listado"});
						       } 
						});
						
					}
            );
        </script>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        </div>
        <h1><g:message code="default.list.label" args="[entityName]" /></h1>
        <div  class="body">
       
			<table style="z-index:1"  id="list"></table>
			<div id="pager" ></div>
       </div>
       
       
    </body>
</html>
