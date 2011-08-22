$(document).ready(function(){
	jQuery("#listvademec").jqGrid({
		caption:'Productos',
		url:locvademec,
		mtype:'POST',
		width:600,
		rownumbers:true,
		pager:'pagervademec',
		datatype:'json',
		colNames:['Id','Nombre Comercial','Principio Activo','Laboratorio','Grupo Terapeutico'],
		colModel:[
				{name:'id',index:'id',width:10,hidden:true},
				{name:'nombreComercial',index:'nombreComercial',width:100,sorttype:'text',sortable:true},
				{name:'principio_principioActivo',index:'principio_principioActivo',width:100,sorttype:'text',sortable:true},
				{name:'laboratorio_nombre',index:'laboratorio_nombre',width:100,sorttype:'text',sortable:true},
				{name:'grupo_nombre',index:'grupo_nombre',width:100,sorttype:'text',sortable:true}
		]
		
	});
	jQuery("#listvademec").jqGrid('filterToolbar',{stringResult: true,searchOnEnter : true});
	jQuery("#listvademec").jqGrid('navGrid','#pagervademec',{search:false,edit:false,add:false,del:false,pdf:true});
});
