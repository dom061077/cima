$(document).ready(function(){
	$('#tabs').tabs();
	$('#tabs-graficos').tabs();
	$("#fechaDesdeId" ).datepicker($.datepicker.regional[ "es" ]);
	$("#fechaHastaId" ).datepicker($.datepicker.regional[ "es" ]);
	
	//------------pestaña detalle---------------
	jQuery("#detallegrid").jqGrid({
	   	url:'pacientesatendidosporprimeravezjson',
	   	postData:{
	   		fechaDesde:function(){
	   					return $('#fechaDesdeId').val();
	   				},
	   		fechaHasta:function(){return $('#fechaHastaId').val()},
	   		obraSocial:function(){
	   					return $('#obraSocialId').val();
	   				},
	   		obraSocialId:function(){
	   					if($('#obraSocialIdId')==undefined)
	   						return '';
	   					else
	   						return $('#obraSocialIdId').val();
	   		}
	   		
	   	},
		datatype: "json",
		width:680,
	   	colNames:['H.C','Paciente', 'O.S'],
	   	colModel:[
	   		
	   		{name:'id',index:'id', width:40,hidden:false,sortable:false},
	   		{name:'paciente',index:'paciente', width:92,sortable:false},
	   		{name:'obraSocial',index:'obraSocial', width:150}
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
	       caption:"Reporte", 
	       onClickButton : function () { 
	    	   var fechaDesde = $('#fechaDesdeId').val();
	    	   var fechaHasta = $('#fechaHastaId').val();
	    	   var profesionalId;
	    	   var obraSocialId;
	    	   var cie10Id
	    	   
	    	   if($('#obraSocialIdId').val()==undefined)
					obraSocialId='';
	    	   else
					obraSocialId=$('#obraSocialIdId').val();
	    	   
	           window.location = 'reporteporprimeravez?target=_blank&fechaDesde='+fechaDesde+'&fechaHasta='+fechaHasta
	           						+'&obraSocialId='+obraSocialId;
	       } 
	});
	
	
	
});