$(document).ready(function(){
	$('#tabs').tabs();
	$("#fechaDesdeId" ).datepicker($.datepicker.regional[ "es" ]);
	$("#fechaHastaId" ).datepicker($.datepicker.regional[ "es" ]);
	
	//------------pesta�a detalle---------------
	jQuery("#detallegrid").jqGrid({
	   	url:'cantidadvisitasporpacientejson',
	   	postData:{
	   		fechaDesde:function(){
	   					return $('#fechaDesdeId').val();
	   				},
	   		fechaHasta:function(){return $('#fechaHastaId').val()},
	   		cie10:function(){
	   					return $('#cie10Id').val();
	   				},
	   		cie10Id:function(){
	   					if($('#cie10IdId')==undefined)
	   						return '';
	   					else
	   						return $('#cie10IdId').val();
	   		}
	   		
	   	},
		datatype: "json",
		width:680,
	   	colNames:['Id','Fecha Consulta','Paciente','Diagnóstico','Descripción', 'O.S', 'Cantidad'],
	   	colModel:[
	   		
	   		{name:'id',index:'id', width:40,hidden:true,sortable:false},
	   		{name:'fechaConsulta',index:'fechaConsulta', width:92,sortable:false},
	   		{name:'paciente',index:'paciente', width:150,sortable:false},
	   		{name:'codDiag',index:'codDiag', width:80,sortable:false},
	   		{name:'descDiag',index:'descDiag', width:150,sortable:false},	   		
	   		{name:'obraSocial',index:'obraSocial', width:150,sortable:false},
	   		{name:'cantidad',index:'cantidad',width:80,sortable:false}
	   	],
	   	
	   	rowNum:10,
	   	rownumbers:true,
	   	rowList:[10,20,30],
	   	pager: '#pagerdetalle',
	   	sortname: 'id',
	    viewrecords: true,
	    sortorder: "desc",
	    caption:"Listado Detalle"
	});
	jQuery("#detallegrid").jqGrid('navGrid','#pagerdetalle',{edit:false,search:false,add:false,del:false,pdf:true});

	jQuery("#detallegrid").jqGrid('navButtonAdd','#pagerdetalle',{
	       caption:"Detalle", 
	       onClickButton : function () { 
	    	   var fechaDesde = $('#fechaDesdeId').val();
	    	   var fechaHasta = $('#fechaHastaId').val();
	    	   var cie10Id
	    	   
	    	   if($('#cie10IdId').val()==undefined)
	    		   cie10Id='';
	    	   else
	    		   cie10Id=$('#cie10IdId').val();
	    	   
	           window.location = 'reportepordiagdetalle?target=_blank&fechaDesde='+fechaDesde+'&fechaHasta='+fechaHasta
	           						+'&cie10Id='+cie10Id;
	       } 
	});
	
	jQuery("#detallegrid").jqGrid('navButtonAdd','#pagerdetalle',{
	       caption:"Ranking", 
	       onClickButton : function () { 
	    	   var fechaDesde = $('#fechaDesdeId').val();
	    	   var fechaHasta = $('#fechaHastaId').val();

	    	   var cie10Id
	    	   
	    	   if($('#cie10IdId').val()==undefined)
	    		   cie10Id='';
	    	   else
	    		   cie10Id=$('#cie10IdId').val();
	    	   
	           window.location = 'reportepordiagranking?target=_blank&fechaDesde='+fechaDesde+'&fechaHasta='+fechaHasta
	           						+'&cie10Id='+cie10Id;
	       } 
	});	
	
});