$(document).ready(function(){
	jQuery("#listhistoria").jqGrid({
		caption:'Historia Clínica',
		url:lochistoria,
		mtype:'POST',
		width:600,
		rownumbers:true,
		pager:'pagerhistoria',
		datatype:'json',
		colNames:['Id','Apellido','Nombre','Nro.Hist. Clínica','Operaciones'],
		colModel:[
				{name:'id',index:'id',width:10,hidden:true},
				{name:'apellido',index:'apellido',width:100,sorttype:'text',sortable:true},
				{name:'nombre',index:'nombre',width:100,sorttype:'text',sortable:true},
				{name:'numero',index:'numero',width:40,sorttype:'text',sortable:true},
				{name:'operaciones',index:'operaciones',width:40,sorttype:'text',sortable:false,search:false}
		],
		gridComplete: function(){ 
								var ids = jQuery('#listhistoria').jqGrid('getDataIDs');
								var row
								for(var i=0;i < ids.length;i++){ 
									var cl = ids[i];
									row = jQuery('#listhistoria').getRowData(cl);
									if(row.numero)
										be = "<a href='edit/"+ids[i]+"'><span class='ui-icon ui-icon-pencil�' style='margin: 3px 3px 3px 10px'  ></span></a>";
									else
										be = "<a href='create?paciente.id="+ids[i]+"'><span class='ui-icon ui-icon-plusthick' style='margin: 3px 3px 3px 10px'  ></span></a>";
									jQuery("#listhistoria").jqGrid('setRowData',ids[i],{operaciones:be}); 
									} 
							}	
		
	});
	jQuery('#listhistoria').jqGrid('filterToolbar',{stringResult: true,searchOnEnter : true});
	jQuery('#listhistoria').jqGrid('navGrid','#pagerhistoria',{search:false,edit:false,add:false,del:false,pdf:true});
});
;