$(document).ready(function(){
	$('#tabs').tabs();
	$('#tabs-graficos').tabs();
	$("#fechaDesdeId" ).datepicker($.datepicker.regional[ "es" ]);
	$("#fechaHastaId" ).datepicker($.datepicker.regional[ "es" ]);
	
	//------------pestaña detalle---------------
	jQuery("#detallegrid").jqGrid({
	   	url:'pacientesatendidosporosjson',
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
	   	colNames:['H.C','Paciente', 'O.S', 'CIE10','DescripciÃ³n'],
	   	colModel:[
	   		
	   		{name:'id',index:'id', width:40,hidden:false,sortable:false},
	   		{name:'paciente',index:'paciente', width:92,sortable:false},
	   		{name:'obraSocial',index:'obraSocial', width:150},
	   		{name:'cie10',index:'cie10', width:80, sortable:false},
	   		{name:'diagnostico',index:'diagnostico', width:150, align:"left", sortable:false}
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
	       caption:" Detalle", 
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
	    	   
	           window.location = 'reporteporos?target=_blank&fechaDesde='+fechaDesde+'&fechaHasta='+fechaHasta
	           						+'&obraSocialId='+obraSocialId;
	       } 
	});
	
	jQuery("#detallegrid").jqGrid('navButtonAdd','#pagerdetalle',{
	       caption:"Ranking", 
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
	    	   
	           window.location = 'reporteporosranking?target=_blank&fechaDesde='+fechaDesde+'&fechaHasta='+fechaHasta
	           						+'&obraSocialId='+obraSocialId;
	       } 
	});
	
	
});