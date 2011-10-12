$(document).ready(function(){
	$('#tabs').tabs();
	$('#tabs-graficos').tabs();
	$("#fechaDesdeId" ).datepicker($.datepicker.regional[ "es" ]);
	$("#fechaHastaId" ).datepicker($.datepicker.regional[ "es" ]);
	
	//------------pestaña detalle---------------
	jQuery("#detallegrid").jqGrid({
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
	   		cie10Id:function(){
				if($('#cie10IdId')==undefined)
					return '';
				else
					return $('#cie10IdId').val();
	   			
	   			
	   		}
	   		
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
	    	   if($('#profesionalIdId').val()==undefined)
					profesionalId='';
				else
					profesionalId=$('#profesionalIdId').val();
	    	   
	    	   if($('#obraSocialIdId').val()==undefined)
					obraSocialId='';
	    	   else
					obraSocialId=$('#obraSocialIdId').val();
	    	   
	    	   if($('#cie10IdId').val()==undefined)
					cie10Id='';
	    	   else
					cie10Id=$('#cie10IdId').val();
	           window.location = 'reportegral?target=_blank&fechaDesde='+fechaDesde+'&fechaHasta='+fechaHasta+'&profesionalId='
	           						+profesionalId+'&obraSocialId='+obraSocialId+'&cie10Id='+cie10Id;
	       } 
	});
	
	
	
});