$(document).ready(function(){
	$('#tabs').tabs();
	$("#fechaDesdeId" ).datepicker($.datepicker.regional[ "es" ]);
	$("#fechaHastaId" ).datepicker($.datepicker.regional[ "es" ]);
	
	//------------pesta�a general---------------
	jQuery("#generalgrid").jqGrid({
	   	url:'pacientesatendidosjson',
	   	postData:{
	   		fechaDesde:function(){},
	   		fechaHasta:function(){},
	   		profesional:function(){},
	   		profesionalId:function(){},
	   		obraSocial:function(){},
	   		obraSocialId:function(){},
	   		cie10:function(){}
	   		
	   	},
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
	
});