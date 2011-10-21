$(document).ready(function(){
	$('#tabs').tabs();
	$("#fechaDesdeId" ).datepicker($.datepicker.regional[ "es" ]);
	$("#fechaHastaId" ).datepicker($.datepicker.regional[ "es" ]);
	
	//------------pestaña detalle---------------
	jQuery("#detallegrid").jqGrid({
	   	url:'auditoriajson',
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
	   	colNames:['Id','Usuario','Nombre de Clase','Fecha Creacion','Transaccion', 'Ultima modificacion'
	   	          	, 'Nuevo valor','Viejo valor','Id del objeto','Propiedad','Uri'],
	   	colModel:[
	   		
	   		{name:'id',index:'id', width:40,hidden:true,sortable:false},
	   		{name:'actor',index:'actor', width:92,sortable:false},
	   		{name:'classname',index:'classname', width:150,sortable:false},
	   		{name:'datecreated',index:'datecreated', width:80,sortable:false},
	   		{name:'eventname',index:'eventname', width:150,sortable:false},	   		
	   		{name:'lastupdated',index:'lastupdated', width:150,sortable:false},
	   		{name:'newvalue',index:'newvalue',width:80,sortable:false},
	   		{name:'oldvalue',index:'oldvalue',width:80,sortable:false},
	   		{name:'persistedobjectid',index:'persistedobjectid',width:80,sortable:false},
	   		{name:'propertyname',index:'propertyname',width:80,sortable:false},
	   		{name:'uri',index:'uri',width:80,sortable:false}
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