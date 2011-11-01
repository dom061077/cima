$(document).ready(function(){
	jQuery("#listvademec").jqGrid({
		caption:'Productos',
		url:locvademec,
		mtype:'POST',
		width:800,
		rownumbers:true,
		pager:'pagervademec',
		datatype:'json',
		colNames:['Id','Nombre Comercial','Principio Activo','Laboratorio','Presentación','Grupo Terapeutico'],
		colModel:[
				{name:'id',index:'id',width:10,hidden:true},
				{name:'nombreComercial',index:'nombreComercial',width:100,sorttype:'text',sortable:true},
				{name:'principio_principioActivo',index:'principio_principioActivo',width:100,sorttype:'text',sortable:true},
				{name:'laboratorio_nombre',index:'laboratorio_nombre',width:100,sorttype:'text',sortable:true},
				{name:'presentacion',index:'presentacion',width:100,hidden:false,sorttype:'text'},
				{name:'grupo_nombre',index:'grupo_nombre',width:100,sorttype:'text',sortable:true}
		]
		
	});
	jQuery("#listvademec").jqGrid('filterToolbar',{stringResult: true,searchOnEnter : true});
	jQuery("#listvademec").jqGrid('navGrid','#pagervademec',{search:false,edit:false,add:false,del:false,pdf:true});
	jQuery("#listvademec").jqGrid('navButtonAdd','#pagervademec',{
	       caption:"Informe", 
	       onClickButton : function () {
	    	   var id = jQuery('#listvademec').jqGrid('getGridParam','selrow');
	    	   if(id)
	    		   window.location = 'vademecumdetalle?target=_blank&id='+id;
	    	   else
	    		   $('<div>Seleccione una fila para activar esta opción</div>').dialog({title:'Mensaje',modal: true});
	    	   
	       } 
	});	
});
