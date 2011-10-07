$(document).ready(function(){
	$('#tabs').tabs();
	$('#tabs-graficos').tabs();
	$("#fechaDesdeId" ).datepicker($.datepicker.regional[ "es" ]);
	$("#fechaHastaId" ).datepicker($.datepicker.regional[ "es" ]);
	
	//------------pesta馻 general---------------
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
	   	colNames:['Id','Fecha Consulta', 'Paciente', 'CIE10','Diagn贸stico','Profesional','Obra Social'],
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
	           jQuery("#generalgrid").jqGrid("excelExport",{url:"reportegral"});
	       } 
	});
	
	//----------------Por profesional----------------
	
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
	   	colNames:['Id','Matr铆cula', 'Profesional', 'Pacientes','Minutos'],
	   	colModel:[
	   		{name:'id',index:'id', width:40,hidden:true},
	   		{name:'matricula',index:'matricula', width:92,sortable:false},
	   		{name:'profesional',index:'profesional', width:150},
	   		{name:'cantPacientes',index:'cantPacientes', width:80, sortable:false},
	   		{name:'minutos',index:'minutos', width:80, sortable:false}	   		
	   	],
	   	gridview:true,
	   	pgbuttons:false,
	   	pgtext:'',
	   	//rowNum:10,
	   	rownumbers:true,
	   	//rowList:[10,20,30],
	   	pager: '#pagerprofesional',
	   	sortname: 'id',
	    viewrecords: true,
	    sortorder: "desc",
	    caption:"Listado Por Profesional"
	});
	jQuery("#profesionalgrid").jqGrid('navGrid','#pagerprofesional',{edit:false,search:false,add:false,del:false,pdf:true});

	jQuery("#profesionalgrid").jqGrid('navButtonAdd','#pagerprofesional',{
	       caption:"Reporte", 
	       onClickButton : function () { 
	           //jQuery("#list").excelExport();
	           jQuery("#profesionalgrid").jqGrid("excelExport",{url:"Iimprimir"});
	       } 
	});
	
	//----------------Por diagnostico----------------
	
	jQuery("#diagnosticogrid").jqGrid({
	   	url:'pordiagnosticojson',
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
	   	colNames:['Id','Diagn贸stico', 'Descripci贸n', 'Total'],
	   	colModel:[
	   		{name:'id',index:'id', width:40,hidden:true},
	   		{name:'diagnostico',index:'diagnostico', width:92,sortable:false},
	   		{name:'descripcion',index:'descripcion', width:150},
	   		{name:'total',index:'total', width:80, sortable:false}	   		
	   	],
	   	gridview:true,
	   	pgbuttons:false,
	   	pgtext:'',
	   	//rowNum:10,
	   	rownumbers:true,
	   	//rowList:[10,20,30],
	   	pager: '#pagerdiagnostico',
	   	sortname: 'id',
	    viewrecords: true,
	    sortorder: "desc",
	    caption:"Listado Por Diagn贸stico"
	});
	jQuery("#diagnosticogrid").jqGrid('navGrid','#pagerdiagnostico',{edit:false,search:false,add:false,del:false,pdf:true});

	jQuery("#diagnosticogrid").jqGrid('navButtonAdd','#pagerdiagnostico',{
	       caption:"Reporte", 
	       onClickButton : function () { 
	           //jQuery("#list").excelExport();
	           jQuery("#diagnosticogrid").jqGrid("excelExport",{url:"reportegral"});
	       } 
	});	
	
});