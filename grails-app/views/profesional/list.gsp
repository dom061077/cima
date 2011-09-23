
<%@ page import="com.medfire.Profesional" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'profesional.label', default: 'Profesional')}" />
        
        <link rel="stylesheet" type="text/css" media="screen" href="${g.resource(dir:'js/jquery-ui/js/src/css',file:'jquery.searchFilter.css')}" />        
        <script type="text/javascript" src="${g.resource(dir:'js/jquery-ui/js/src/i18n',file:'grid.locale-es.js')}"></script>
        <script type="text/javascript" src="${g.resource(dir:'js/jquery-ui/js',file:'jquery.jqGrid.min.js')}"></script>
        <link rel="stylesheet" type="text/css" media="screen" href="${g.resource(dir:'css',file:'thickbox.css')}" />
        <script type="text/javascript" src="${resource(dir:'js/jquery',file:'thickbox.js')}"></script>
        <title><g:message code="default.list.label" args="[entityName]" /></title>
        <script type="text/javascript">
        
	    	$(document).ready(
					function(){
						jQuery("#list").jqGrid({
						   	url:'listjson',
							datatype: "json",
							width:680,
						   	colNames:['Id','C.U.I.T', 'Matricula', 'Nombre','Telófono','Large Url','Url','Foto','Operaciones'],
						   	colModel:[
						   		
						   		{name:'id',index:'id', width:40,searchoptions:{sopt:['eq']}},
						   		{name:'cuit',index:'cuit', width:92,sortable:false,searchoptions:{sopt:['eq']}},
						   		{name:'matricula',index:'matricula', width:100,search:false,searchoptions:{sopt:['eq']}},
						   		{name:'nombre',index:'nombre', width:150, sortable:true},
						   		{name:'telefono',index:'telefono', width:80, align:"right",search:false, sortable:false,searchoptions:{sopt:['eq']}},
						   		{name:'urllargephoto',index:'urllargephoto', hidden:true, width:80,search:false, align:"right", sortable:false},
						   		{name:'urlphoto',index:'urlphoto', hidden:true, width:80,search:false, align:"right", sortable:false},						   		
						   		{name:'foto',index:'foto', width:80, align:"center",search:false, sortable:false},						   		
						   		{name:'operaciones',index:'operaciones', width:55,search:false,sortable:false}
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
								var obj; 
								for(var i=0;i < ids.length;i++){ 
									var cl = ids[i];
									obj = $("#list").getRowData(ids[i]); 
									be = "<a href='edit/"+ids[i]+"'><span class='ui-icon ui-icon-pencil' style='margin: 3px 3px 3px 10px'  ></span></a>";
									ph = '<a class="thickbox" href="'+obj.urllargephoto+'" onclick="return false"><img src="'+obj.urlphoto+'"/></a>';
									jQuery("#list").jqGrid('setRowData',ids[i],{operaciones:be,foto:ph}); 
									}
								tb_init('a.thickbox, area.thickbox, input.thickbox');
								 
							}, 						    
						    caption:"Listado de Profesionales"
						});
						jQuery("#list").jqGrid('navGrid','#pager',{search:false,edit:false,add:false,del:false,pdf:true});
	
						jQuery("#list").jqGrid('navButtonAdd','#pager',{
						       caption:"Informe", 
						       onClickButton : function () { 
						           //jQuery("#list").excelExport();
						           jQuery("#list").jqGrid("excelExport",{url:"excelexport"});
						       } 
						});

						jQuery("#list").jqGrid('filterToolbar',{stringResult: true,searchOnEnter : true});						
						
					}
	        );
        </script>
        
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="ui-state-highlight ui-corner-all"><h3>${flash.message}</h3></div>
            </g:if>
			<table style="z-index:1"  id="list"></table>
			<div id="pager" ></div>
        </div>
        
            <%-- Reporte --%>
            <g:jasperReport controller="profesional" action="listado" 
            	jasper="profesionales" format="HTML" name="profesionales">
            </g:jasperReport>
        
    </body>
</html>
