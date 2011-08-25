//http://www.trirand.com/jqgridwiki/doku.php?id=wiki%3Acommon_rules
//http://jquery.malsup.com/form/#file-upload  --- este link me sirve para hacer ajaxsubmitform
$(document).ready(function(){
	
	
	$("#estadoId").combobox() ;
    	
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
		,rownumbers:true
		,colNames:['Id','Nombre Comercial', 'Nombre Genérico','Presentación', 'Cantidad','Imprimir Por']
		,colModel:[ 
			{name:'id',index:'id', width:55,editable:false,hidden:true	,editoptions:{readonly:true,size:10}, sortable:false}
			, {name:'nombreComercial',index:'nombreComercial', width:100,editable:true,editoptions:{size:30},editrules:{required:true}, sortable:false}
			, {name:'nombreGenerico',index:'nombreGenerico', width:100, align:"right",editable:true,editoptions:{size:30},editrules:{required:true}, sortable:false}
			, {name:'presentacion',index:'presentacion', width:100, align:"right",editable:true,editoptions:{size:30},editrules:{required:true}, sortable:false}			
			, {name:'cantidad',index:'cantidad', width:100, align:"right",editable:true,editoptions:{size:30},editrules:{required:true}, sortable:false}
			, {name:'imprimirPor',index:'imprimirPor', width:60,align:"right",editable:true,editoptions:{size:30,value:'IMPRIME_GENERICO:Nombre generico;IMPRIME_COMERCIAL:Nombre Comercial;IMPRIME_AMBOS:Ambos'}
					,edittype:'select',editrules:{required:true}, sortable:false}
		]
		//, rowNum:10, rowList:[10,20,30]
		, pager: '#pagerPrescripciones'
		, sortname: 'id'
		, viewrecords: true, sortorder: "desc"
		, caption:"Prescripciones",  height:210
	}); 
	
	//---------------inicializacion de la grilla de busqueda del vademecum para sugerir las prescripciones
	$('#tablaBusquedaVademecumId').jqGrid({
		caption:'Búsqueda de Vademecum',
		width:380,
		url:locvademec,
	mtype:'POST',
	width:550,
	rownumbers:true,
	pager:'pagerBusquedaVademecumId',
	datatype:'json',
	colNames:['Id','Nombre Comercial','Principio Activo','Laboratorio','Presentación','Grupo Terapeutico'],
	colModel:[
			{name:'id',index:'id',width:10,hidden:true},
			{name:'nombreComercial',index:'nombreComercial',width:100,sorttype:'text',sortable:true},
			{name:'principio_principioActivo',index:'principio_principioActivo',width:100,sorttype:'text',sortable:true},
			{name:'laboratorio_nombre',index:'laboratorio_nombre',width:100,sorttype:'text',sortable:true},
			{name:'presentacion',index:'presentacion',width:100,sorttyp:'text',sortable:false},
			{name:'grupo_nombre',index:'grupo_nombre',width:100,sorttype:'text',sortable:true}
	],
	ondblClickRow: function(id){
			var obj=$('#tablaBusquedaVademecumId').getRowData(id);
			$('#nombreComercial').val(obj.nombreComercial);
			$('#nombreGenerico').val(obj.principio_principioActivo);
			$('#presentacion').val(obj.presentacion)
			$('#busquedaVademecumDialogId').dialog("close");
			$('#cantidad').focus();
		} 
	});
	jQuery('#tablaBusquedaVademecumId').jqGrid('filterToolbar',{stringResult: true,searchOnEnter : true});
	
	
	
	jQuery("#prescripcionesId").jqGrid('navGrid','#pagerPrescripciones', {add:true,edit:true,del:true,search:false,refresh:false}, //options 
		{height:280,reloadAfterSubmit:false
			, recreateForm:true
			, beforeShowForm:function(form){
				$('#TblGrid_prescripcionesId').before('<a style="width:50px" id="searchlinkformgridId" href="#"><span  class="ui-icon ui-icon-search"></span>Vademecum</a>');
				$('#searchlinkformgridId').bind('click',function(){
	            	$('#busquedaVademecumDialogId').dialog({
	            		title:'Buscar',
	            		modal:true,
	            		resizable:false,
	            		autoOpen:true,
	            		width : 600,
	            		height: 'auto',
	            		minHeight:350,
	            		position:'center'
	            	});
				});
			}
		
		}, // edit options 
		{height:280,reloadAfterSubmit:false
			,recreateForm:true
			,onclickSubmit : function(eparams) { 
				/*var retarr = {}; // we can use all the grid methods here 
				//to obtain some data
				var sr = jQuery("#grid_id").getGridParam('selrow'); 
				rowdata = jQuery("#grid_id").getRowData(sr); 
				if(rowdata.somevalue=='aa') {
					retarr = {myname:"myvalue"}; 
				} 
				return retarr;*/ 
			},
			beforeShowForm:function(form){
				$('#TblGrid_prescripcionesId').before('<a style="width:50px" id="searchlinkformgridId" href="#"><span  class="ui-icon ui-icon-search"></span>Vademecum</a>');
				$('#searchlinkformgridId').bind('click',function(){
	            	$('#busquedaVademecumDialogId').dialog({
	            		title:'Buscar',
	            		modal:true,
	            		resizable:false,
	            		autoOpen:true,
	            		width : 600,
	            		height: 'auto',
	            		minHeight:350,
	            		position:'center'
	            	});
				});
			}
		
		}, // add options 
		{reloadAfterSubmit:false}, // del options 
		{} // search options 
	);	
	
	//---inicializo los datos de la grilla si el create proviene de un redirect del save--
	

		

		
	
	$("#testgrid").bind('click',function(){
		var gridData = jQuery("#prescripcionesId").getRowData();
    	var postData = JSON.stringify(gridData);
    	alert("JSON serialized jqGrid data:\n" + postData);
	});

	//var strjson = $("#prescripcionesSerializedId").val();
	
	//strjson = strjson.replace(new RegExp('&quot;', 'g'),'"');
	var data = jQuery.parseJSON(strjson);
	var i=1;
	
	if(data){
		//alert(this);
		jQuery.each(data,function(){
			$("#prescripcionesId").jqGrid('addRowData',i,this);
			i++;
			
		});
	}
	
	
			
	});//end function ready
