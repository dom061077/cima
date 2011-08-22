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
		,width:700
		,colNames:['Id','Nombre Comercial', 'Nombre Generico', 'Cantidad','Imprimir Por']
		,colModel:[ 
			{name:'id',index:'id', width:55,editable:false	,editoptions:{readonly:true,size:10}}
			, {name:'nombreComercial',index:'nombreComercial', width:90,editable:true,editoptions:{size:25},editrules:{required:true}}
			, {name:'nombreGenerico',index:'nombreGenerico', width:60, align:"right",editable:true,editoptions:{size:10}}
			, {name:'cantidad',index:'cantidad', width:60, align:"right",editable:true,editoptions:{size:10}}
			, {name:'imprimirPor',index:'imprimirPor', width:60,align:"right",editable:true,editoptions:{size:10}}
		]
		//, rowNum:10, rowList:[10,20,30]
		, pager: '#pagerPrescripciones', sortname: 'id'
		, viewrecords: true, sortorder: "desc"
		, caption:"Navigator Example",  height:210
	}); 
	
	function busquedaVademecum(){
						$('body').append('<div style="display:none" id="busquedaVademecumDialogId"></div>');
		            	$('#busquedaVademecumDialogId').append('<table id="tablaBusquedaVademecumId"></table><div id="pagerBusquedaVademecumId"></div>');
						var grid = $('#tablaBusquedaVademecumId');
		            	$('#busquedaVademecumDialogId').append(grid);
						$('#tablaBusquedaVademecumId').jqGrid({
							caption:'Búsqueda de Vademecum',
							width:380,
							url:locvademec,
						mtype:'POST',
						width:550,
						rownumbers:true,
						pager:'pagerBusquedaVademecumId',
						datatype:'json',
						colNames:['Id','Nombre Comercial','Principio Activo','Laboratorio','Grupo Terapeutico'],
						colModel:[
								{name:'id',index:'id',width:10,hidden:true},
								{name:'nombreComercial',index:'nombreComercial',width:100,sorttype:'text',sortable:true},
								{name:'principio_principioActivo',index:'principio_principioActivo',width:100,sorttype:'text',sortable:true},
								{name:'laboratorio_nombre',index:'laboratorio_nombre',width:100,sorttype:'text',sortable:true},
								{name:'grupo_nombre',index:'grupo_nombre',width:100,sorttype:'text',sortable:true}
						],
						ondblClickRow: function(id){
								var obj=$('#tablaBusquedaVademecumId').getRowData(id);
								//$('#'+settings.hiddenid).val(obj[settings.hiddenfield]);
								//$('#'+settings.descid).val(obj[settings.descfield]);		 
								//$('#'+searchDialogId).dialog("close");
								
							} 
						});
						jQuery('#tablaBusquedaVademecumId').jqGrid('filterToolbar',{stringResult: true,searchOnEnter : true});
		 
		            	$('#busquedaVademecumDialogId').dialog({
		            		title:'Buscar',
		            		modal:true,
		            		resizable:false,
		            		autoOpen:true,
		            		width : 600,
		            		height: 'auto',
		            		minHeight:350,
		            		position:'center',
		            		open: function(event,ui){
		            			
		            		}
		            	});
		
	}
	
	jQuery("#prescripcionesId").jqGrid('navGrid','#pagerPrescripciones', {}, //options 
		{height:280,reloadAfterSubmit:false
			, recreateForm:true
			, beforeShowForm:function(form){
				$('#TblGrid_prescripcionesId').before('<a style="width:50px" id="searchlinkformgridId" href="#"><span  class="ui-icon ui-icon-search"></span>Vademecum</a>');
				$('#searchlinkformgridId').bind('click',function(){
					$('<div>HOLA</div>').dialog({});
				});
			}
		
		}, // edit options 
		{height:280,reloadAfterSubmit:false
			,recreateForm:true
			, beforeShowForm:function(form){
				$('#TblGrid_prescripcionesId').before('<a style="width:50px" id="searchlinkformgridId" href="#"><span  class="ui-icon ui-icon-search"></span>Vademecum</a>');
				$('#searchlinkformgridId').bind('click',function(){
					busquedaVademecum();
				});
			}
		
		}, // add options 
		{reloadAfterSubmit:false}, // del options 
		{} // search options 
	);	
	
		
	
	$("#testgrid").bind('click',function(){
		var gridData = jQuery("#prescripcionesId").getRowData();
    	var postData = JSON.stringify(gridData);
    	alert("JSON serialized jqGrid data:\n" + postData);
	});
	
			
	});//end function ready
