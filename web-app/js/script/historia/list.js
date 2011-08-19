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
		subGrid:true,
		gridComplete: function(){ 
								var ids = jQuery('#listhistoria').jqGrid('getDataIDs');
								var row
								for(var i=0;i < ids.length;i++){ 
									var cl = ids[i];
									row = jQuery('#listhistoria').getRowData(cl);
									//if(row.numero)
									//	be = "<a href='edit/"+ids[i]+"'><span class='ui-icon ui-icon-pencil�' style='margin: 3px 3px 3px 10px'  ></span></a>";
									//else
										be = "<a href='create?pacienteId="+ids[i]+"'><span class='ui-icon ui-icon-plusthick' style='margin: 3px 3px 3px 10px'  ></span></a>";
									jQuery("#listhistoria").jqGrid('setRowData',ids[i],{operaciones:be}); 
									} 
							}	
		,subGridRowExpanded: function(subgrid_id, row_id) {
			// we pass two parameters
			// subgrid_id is a id of the div tag created whitin a table data
			// the id of this elemenet is a combination of the "sg_" + id of the row
			// the row_id is the id of the row
			// If we wan to pass additinal parameters to the url we can use
			// a method getRowData(row_id) - which returns associative array in type name-value
			// here we can easy construct the flowing
			var subgrid_table_id, pager_id;
			subgrid_table_id = subgrid_id+"_t";
			pager_id = "p_"+subgrid_table_id;
			$("#"+subgrid_id).html("<table id='"+subgrid_table_id+"' class='scroll'></table><div id='"+pager_id+"' class='scroll'></div>");
			jQuery("#"+subgrid_table_id).jqGrid({
				url:"subgrid.php?q=2&id="+row_id,
				datatype: "xml",
				colNames: ['Fecha Consulta','Cie10','Ambito','Contenido'],
				colModel: [
					{name:"fechaConsulta",index:"fechaConsulta",width:80,key:true},
					{name:"cie10",index:"cie10",width:130},
					{name:"ambito",index:"ambito",width:70,align:"right"},
					{name:"contenido",index:"contenido",width:70,align:"right"}
				],
			   	rowNum:20,
			   	pager: pager_id,
			   	sortname: 'num',
			    sortorder: "asc",
			    height: '100%'
			});
			jQuery("#"+subgrid_table_id).jqGrid('navGrid',"#"+pager_id,{edit:false,add:false,del:false})
		},
		subGridRowColapsed: function(subgrid_id, row_id) {
			// this function is called before removing the data
			//var subgrid_table_id;
			//subgrid_table_id = subgrid_id+"_t";
			//jQuery("#"+subgrid_table_id).remove();
		}
							
		
	});
	jQuery('#listhistoria').jqGrid('filterToolbar',{stringResult: true,searchOnEnter : true});
	jQuery('#listhistoria').jqGrid('navGrid','#pagerhistoria',{search:false,edit:false,add:false,del:false,pdf:true});
});
;