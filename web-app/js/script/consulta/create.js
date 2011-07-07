$(document).ready(function(){
	$( "#tabs" ).tabs();
	 
	$("#fechaConsultaId" ).datepicker($.datepicker.regional[ "es" ]);	
	$("#cie10DescripcionId").lookupfield({
		source:loccie10search,
		title:'Búsqueda de CIE10',
		colnames:['Id','Descripción'],
		colModel:[
				{name:'id',index:'id', width:10, sorttype:"int", sortable:true,hidden:false,search:false},
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
});



