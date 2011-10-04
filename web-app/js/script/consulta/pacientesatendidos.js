$(document).ready(function(){
	$('#tabs').tabs();
	$("#fechaDesdeId" ).datepicker($.datepicker.regional[ "es" ]);
	$("#fechaHastaId" ).datepicker($.datepicker.regional[ "es" ]);
	
	//------------pestaña general---------------
	jQuery("#generalgrid").jqGrid({
	   	url:'pacientesatendidosjson',
	   	postData:{
	   		fechaDesde:function(){
	   					return $('#fechaDesdeId').val();
	   				},
	   		fechaHasta:function(){return $('#fechaHastaId').val()},
	   		profesional:function(){return $('#profesionalId').val()},
	   		profesionalId:function(){
	   					if($('#profesionalIdId').val()==undefined)
	   						return '';
	   					else
	   						return $('#profesionalIdId').val()
	   				},
	   		obraSocial:function(){
	   					return $('#obraSocialId').val();
	   				},
	   		obraSocialId:function(){
	   					if($('#obraSocialIdId')==undefined)
	   						return '';
	   					else
	   						return $('#obraSocialIdId').val();
	   		},
	   		cie10:function(){return $('#cie10IdId').val()}
	   		
	   	},
		datatype: "json",
		width:680,
	   	colNames:['Id','Fecha Consulta', 'Paciente', 'CIE10','DiagnÃ³stico','Profesional','Obra Social'],
	   	colModel:[
	   		
	   		{name:'id',index:'id', width:40,hidden:true},
	   		{name:'fechaConsulta',index:'fechaConsulta', width:92,sortable:false},
	   		{name:'paciente',index:'paciente', width:150},
	   		{name:'cie10',index:'cie10', width:80, sortable:false},
	   		{name:'diagnostico',index:'diagnostico', width:150, align:"left", sortable:false},
	   		{name:'profesional',index:'profesional', width:150, align:"left", sortable:false},		
	   		{name:'obraSocial',index:'obraSocial', width:150,align:"left",sortable:false}
	   	],
	   	
	   	rowNum:10,
	   	rownumbers:true,
	   	rowList:[10,20,30],
	   	pager: '#pagergeneral',
	   	sortname: 'id',
	    viewrecords: true,
	    sortorder: "desc",
	    caption:"Listado General"
	});
	jQuery("#generalgrid").jqGrid('navGrid','#pagergeneral',{edit:false,search:false,add:false,del:false,pdf:true});

	jQuery("#generalgrid").jqGrid('navButtonAdd','#pagergeneral',{
	       caption:"Reporte", 
	       onClickButton : function () { 
	           //jQuery("#list").excelExport();
	           jQuery("#generalgrid").jqGrid("excelExport",{url:"Iimprimir"});
	       } 
	});
	
	//----------------Por profesional----------------
	
	//------------pestaña general---------------
	jQuery("#profesionalgrid").jqGrid({
	   	url:'porprofesionaljson',
	   	postData:{
	   		fechaDesde:function(){
	   					return $('#fechaDesdeId').val();
	   				},
	   		fechaHasta:function(){return $('#fechaHastaId').val()},
	   		profesional:function(){return $('#profesionalId').val()},
	   		profesionalId:function(){
	   					if($('#profesionalIdId').val()==undefined)
	   						return '';
	   					else
	   						return $('#profesionalIdId').val()
	   				},
	   		obraSocial:function(){
	   					return $('#obraSocialId').val();
	   				},
	   		obraSocialId:function(){
	   					if($('#obraSocialIdId')==undefined)
	   						return '';
	   					else
	   						return $('#obraSocialIdId').val();
	   		},
	   		cie10:function(){return $('#cie10IdId').val()}
	   		
	   	},
		datatype: "json",
		width:680,
	   	colNames:['Id','Fecha Consulta', 'Paciente', 'CIE10','DiagnÃ³stico','Profesional','Obra Social'],
	   	colModel:[
	   		
	   		{name:'id',index:'id', width:40,hidden:true},
	   		{name:'fechaConsulta',index:'fechaConsulta', width:92,sortable:false},
	   		{name:'paciente',index:'paciente', width:150},
	   		{name:'cie10',index:'cie10', width:80, sortable:false},
	   		{name:'diagnostico',index:'diagnostico', width:150, align:"left", sortable:false},
	   		{name:'profesional',index:'profesional', width:150, align:"left", sortable:false},		
	   		{name:'obraSocial',index:'obraSocial', width:150,align:"left",sortable:false}
	   	],
	   	
	   	rowNum:10,
	   	rownumbers:true,
	   	rowList:[10,20,30],
	   	pager: '#pagerprofesional',
	   	sortname: 'id',
	    viewrecords: true,
	    sortorder: "desc",
	    caption:"Listado Por Profesional"
	});
	jQuery("#profesionalgrid").jqGrid('navGrid','#pagerprofesional',{edit:false,search:false,add:false,del:false,pdf:true});

	jQuery("#profesionalgrid").jqGrid('navButtonAdd','#profesionalpager',{
	       caption:"Reporte", 
	       onClickButton : function () { 
	           //jQuery("#list").excelExport();
	           jQuery("#profesionalgrid").jqGrid("excelExport",{url:"Iimprimir"});
	       } 
	});
	
	
});