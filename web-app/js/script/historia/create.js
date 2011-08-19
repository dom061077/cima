$(document).ready(function(){
	$( "#tabs" ).tabs();
	 
	$("#fechaConsultaId" ).datepicker($.datepicker.regional[ "es" ]);	
	$("#cie10DescripcionId").lookupfield({
		source:loccie10search,
		title:'Búsqueda de CIE10',
		colnames:['Id','CIE10','Descripción'],
		colModel:[
				{name:'id',index:'id', width:10, sorttype:"int", sortable:true,hidden:false,search:false},
				{name:'cie10',index:'cie10', width:100,  sortable:true,search:true},				
				{name:'descripcion',index:'descripcion', width:100,  sortable:true,search:true},	
			],
		hiddenid:'cie10Id',
		descid:'cie10DescripcionId',
		hiddenfield:'id',
		descfield:'descripcion'	
			
	});

	$( "#cie10DescripcionId" ).autocomplete({
		source: loccie10autocomplete,
		minLength: 2,
		select: function( event, ui ) {
			if(ui.item){
				$("#cie10Id").val(ui.item.id)
			}
			
		},
		open: function() {
			$( this ).removeClass( "ui-corner-all" ).addClass( "ui-corner-top" );
		},
		close: function() {
			$( this ).removeClass( "ui-corner-top" ).addClass( "ui-corner-all" );
		}
	});
	
	jQuery("#prescripcionesId").jqGrid({ 
		url:'editprescripciones'
		,editurl:'editprescripciones'
		,datatype: "json"
		,colNames:['Id','Nombre Comercial', 'Nombre Generico', 'Cantidad','Imprimir Por']
		,colModel:[ 
			{name:'id',index:'id', width:55,editable:false	,editoptions:{readonly:true,size:10}}
			, {name:'nombreComercial',index:'nombreComercial', width:90,editable:true,editoptions:{size:25}}
			, {name:'nombreGenerico',index:'nombreGenerico', width:60, align:"right",editable:true,editoptions:{size:10}}
			, {name:'cantidad',index:'cantidad', width:60, align:"right",editable:true,editoptions:{size:10}}
			, {name:'imprimirPor',index:'imprimirPor', width:60,align:"right",editable:true,editoptions:{size:10}}
		]
		//, rowNum:10, rowList:[10,20,30]
		, pager: '#pagerPrescripciones', sortname: 'id'
		, viewrecords: true, sortorder: "desc"
		, caption:"Navigator Example",  height:210 }); 
	jQuery("#prescripcionesId").jqGrid('navGrid','#pagerPrescripciones', {}, //options 
		{height:280,reloadAfterSubmit:false}, // edit options 
		{height:280,reloadAfterSubmit:false}, // add options 
		{reloadAfterSubmit:false}, // del options 
		{} // search options 
	); 	
	
	$("#testgrid").bind('click',function(){
		var gridData = jQuery("#prescripcionesId").getRowData();
    	var postData = JSON.stringify(gridData);
    	alert("JSON serialized jqGrid data:\n" + postData);
	});
	
			
	});//end function ready
