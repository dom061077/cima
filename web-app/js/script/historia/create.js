//http://www.trirand.com/jqgridwiki/doku.php?id=wiki%3Acommon_rules
//http://jquery.malsup.com/form/#file-upload  --- este link me sirve para hacer ajaxsubmitform
$(document).ready(function(){
	
	
	//$("#estadoId").combobox() ;
    //var arrayEstudios=[{id}];
	var countEstudios=1;
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
		descfield:['descripcion']	
			
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
		url:'listprescripciones'
		,editurl:'editprescripciones'
		,datatype: "json"
		,width:800
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
	
	
	//--------incializo eventos de los checkboxes de los antecedentes familiares------
	for(var i=1;i<=22;i++){
		$('#checklabel'+i+'Id').click(function (){
			var indice = $(this).attr('indice');
			
			var check = $('#checklabel'+indice+'Id').is(':checked');
    		if (check==true) {
    			$('#antecedentet'+indice+'Id').removeAttr('disabled');
    			$('#antecedentet'+indice+'Id').focus();
    		}else{
    			$('#antecedentet'+indice+'Id').val('');
    			$('#antecedentet'+indice+'Id').attr('disabled','disabled');
    		}
			
		});
		$('#antecedentet'+i+'Id').attr('disabled','disabled');
	}

			
	var options={
		target: '#outputId',
		//dataType: 'json',
		success: function(){
			
			var retorno = $("#consultasalvadaId").val();
			if(retorno){
				$("#outputId").hide();
				window.location='show/'+retorno;
			}
			scroll(0,0);	
				
		}
	};
	
	
	$('#historiaFormId').ajaxForm(options);
	//****************************************/
	
	$('#agregarEstudioId').click(function(){
		//arrayEstudios.push({id:0});		
		countEstudios++;
		/*$('#tabs-3').append('<fieldset class="coolfieldset" id="estudio2"></fieldset>');
		$('#tabs-3').find('fieldset#estudio2').append('<legend>Estudio #</legend>');
		$('#estudio2').append('<div>contenido de div</div>');
		$('#estudio2').coolfieldset({speed:'fast'});*/
		/*var clon = $('#estudio1').clone();
		clon.attr('id','estudio'+countEstudios);
		clon.children('legend').toggle();
		clon.coolfieldset({speed:'fast'});
		clon.appendTo('#tabs-3');*/
		
		$("#tabs-estudios").tabs( "add", "#tab-estudio" + countEstudios, "Estudio "+countEstudios );

	});
	
	//-----------inicializacion de tabas estudios-----------
	var $tabs = $( "#tabs-estudios").tabs({
			tabTemplate: "<li><a href='#{href}'>#{label}</a><span class='ui-icon ui-icon-close'>Remove Tab</span></li>",
			add: function( event, ui ) {
				//var tab_content = $tab_content_input.val() || "Tab " + tab_counter + " content.";
				var clon = $('#estudio1').clone();
				clon.attr('id','estudio'+countEstudios);
				clon.appendTo('#tab-estudio'+countEstudios);
				
				clon.find('#estudio1pedido').attr('name','consulta.estudio.'+countEstudios+'.pedido');
				clon.find('#estudio1pedido').attr('id','estudio'+countEstudios+'pedido');
				
				clon.find('#estudio1resultado').attr('name','consulta.estudio.'+countEstudios+'.resultado');
				clon.find('#estudio1resultado').attr('id','estudio'+countEstudios+'resultado')
				
				clon.find('#estudio1imagen1').attr('name','consulta.estudio.'+countEstudios+'.imagen.1');
				clon.find('#estudio1imagen1').attr('id','consulta.estudio'+countEstudios+'imagen1');
				
				clon.find('#estudio1imagen2').attr('name','consulta.estudio.'+countEstudios+'.imagen.2');
				clon.find('#estudio1imagen2').attr('id','estudio'+countEstudios+'imagen2');
				
				clon.find('#estudio1imagen3').attr('name','consulta.estudio.'+countEstudios+'.imagen.3');
				clon.find('#estudio1imagen3').attr('id','estudio'+countEstudios+'imagen3');
				clon.show();
			}
		});

	$( "#tabs-estudios span.ui-icon-close" ).live( "click", function() {
				var index = $( "li", $tabs ).index( $( this ).parent() );
				$tabs.tabs( "remove", index );
			});
	//$("#estudio1").hide();	
	
});//end function ready
