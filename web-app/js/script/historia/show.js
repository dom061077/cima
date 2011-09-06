$(document).ready(function(){
	$('#estudioscomplementariosId').jcarousel({
		itemLoadCallback: {onBeforeAnimation: mycarousel_itemLoadCallback}
	});	
	
	CKEDITOR.on( 'instanceReady', function( ev )
			{
				editor = ev.editor;
				editor.setReadOnly( true );
			}); 	
	
	$("#tabs").tabs();
	
	$('#prescripcionesId').jqGrid({
		caption:'Prescripciones',
		width:380,
		url:locprescripciones,
		mtype:'POST',
		width:800,
		rownumbers:true,
		//pager:'pagerPrescripciones',
		datatype:'json'
		,colNames:['Id','Nombre Comercial', 'Nombre Genérico','Presentación', 'Cantidad','Imprimir Por Valor','Imprimir Por']
		,colModel:[ 
				{name:'id',index:'id', width:55,editable:false,hidden:true}
				, {name:'nombreComercial',index:'nombreComercial', width:100}
				, {name:'nombreGenerico',index:'nombreGenerico', width:100}
				, {name:'presentacion',index:'presentacion', width:100}			
				, {name:'cantidad',index:'cantidad', width:100}
				, {name:'imprimirPorValue', index:'imprimirPorValue',hidden:true,editable:true}
				, {name:'imprimirPor',index:'imprimirPor', width:60}
		]
		});
		

	for(var i=1;i<=22;i++){
		$('#checklabel'+i+'Id').attr('disabled','disabled');
	}
	
	
	
});