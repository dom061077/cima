//http://www.trirand.com/jqgridwiki/doku.php?id=wiki%3Acommon_rules
//http://jquery.malsup.com/form/#file-upload  --- este link me sirve para hacer ajaxsubmitform

	function descartarImg(self){
		var indexImg = $(self).attr('indice');
		$('#imagen'+indexImg+'Id').hide();
		$('#imagen'+indexImg+'IdOp').html('<a indice="'+indexImg+'"  onClick="cancelarImg(this)">Cancelar</a>');
		$('#imagen'+indexImg+'IdInp').show();
		var id = $('#imagen'+indexImg+'InpId').attr('codigo');
		arrayDeletedImg.push({id:id});
		
		
	}
	
	function cancelarImg(self){
		var indexImg = $(self).attr('indice');
		$('#imagen'+indexImg+'Id').show();		
		$('#imagen'+indexImg+'IdOp').html('<a indice="'+indexImg+'"  onClick="descartarImg(this)">Descartar</a>');
		$('#imagen'+indexImg+'IdInp').hide();
		var id = $('#imagen'+indexImg+'InpId').attr('codigo');
		arrayDeletedImg = $.grep(arrayDeletedImg,function(value){
			return value.id!=id;
		});
	}
	
	var arrayDeletedImg=[];

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
				{name:'descripcion',index:'descripcion', width:100,  sortable:true,search:true}	
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
		url:locprescripciones+'/'+consultaId
		,editurl:loceditprescripciones
		,datatype: "json"
		,width:700
		,rownumbers:true
		,colNames:['Id','Nombre Comercial', 'Nombre Genérico','Presentación', 'Cantidad','Imprimir Por Valor','Imprimir Por']
		,colModel:[ 
			{name:'id',index:'id', width:55,editable:false,hidden:true	,editoptions:{readonly:true,size:10}, sortable:false}
			, {name:'nombreComercial',index:'nombreComercial', width:100,editable:true,editoptions:{size:30},editrules:{required:true}, sortable:false}
			, {name:'nombreGenerico',index:'nombreGenerico', width:100, align:"right",editable:true,editoptions:{size:30},editrules:{required:true}, sortable:false}
			, {name:'presentacion',index:'presentacion', width:100, align:"right",editable:true,editoptions:{size:30},editrules:{required:true}, sortable:false}			
			, {name:'cantidad',index:'cantidad', width:100, align:"right",editable:true,editoptions:{size:30},editrules:{required:true}, sortable:false}
			, {name:'imprimirPorValue', index:'imprimirPorValue',hidden:true,editable:true}
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
			,beforeSubmit: function(postData,formId){
				postData.imprimirPorValue= $("#imprimirPor").val();
				return [true,'']
			}
			,beforeShowForm:function(form){
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
	
	
	var options={
		target: '#outputId',
		//dataType: 'json',
		success: function(){
			
			var retorno = $("#consultasalvadaId").val();
			if(retorno){
				$("#outputId").hide();
				window.location='show/'+retorno;
			}
				
		}
	};
	
	
	$('#historiaFormId').ajaxForm(options);
	//------------reubicacion de imagenes----------------------
	//var trickimg = '<a   href="' + item.url + '" title="' + item.title + '"><img src="' + item.url + '" width="600" height="600" border="0" alt="' + item.title + '" /></a>';
	
	//------------funciones para manejar el descartar y cancelar de cada imagen---------
	
	
	$.each(imagenList,function(index,value){
		var i = index+1;
		$('#imagen'+i+'Id').html('<a indice="'+index+'" id="imagen'+i+'Idthick" class="thickbox"   href="' + value.url + '" title="' + value.title 
							+ '"><img src="' + value.url 
							+ '" width="50" height="50" border="0" alt="' + value.title + '" /></a>');
		$('#imagen'+i+'Idthick').click(function(){
				var t = this.title || this.name || null;
				var a = this.href || this.alt;
				var g = this.rel || false;
				//alert('INDICE: '+$(this).attr('indice'));
				tb_show(t,a,g);
				this.blur();
				return false;
			});		
		$('#imagen'+i+'IdOp').html('<a indice="'+i+'"  onClick="descartarImg(this)">Descartar</a>');
		$('#imagen'+i+'IdInp').hide();
		$('#imagen'+i+'InpId').attr('codigo',value.codigo);
	});	
			
});//end function ready
