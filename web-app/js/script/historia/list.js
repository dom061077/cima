$(document).ready(function(){
	jQuery("#listhistoria").jqGrid({
		caption:'Historia Clínica',
		url:lochistoria,
		mtype:'POST',
		width:800,
		height:400,
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
			var subgrid_table_id, pager_id;
			subgrid_table_id = subgrid_id+"_t";
			pager_id = "p_"+subgrid_table_id;
			var obj=$('#listhistoria').getRowData(row_id);
			$("#"+subgrid_id).html("<table id='"+subgrid_table_id+"' class='scroll'></table><div id='"+pager_id+"' class='scroll'></div>");
			jQuery("#"+subgrid_table_id).jqGrid({
				url:locsubgridconsulta+'?pacienteId='+obj.id,
				datatype: "json",
				mtype:'POST',
				colNames: ['Id','Fecha Consulta','Cie10','Cie10 Desc.','Profesional','Ambito','Paciente ID','Operaciones'],
				colModel: [
					{name:"id",index:"id",width:80,key:true,hidden:true},				           
					{name:"fechaConsulta",index:"fechaConsulta",width:80,sorttype:'date', formatter:'date'},
					{name:"cie10_cie10",index:"cie10_cie10",width:70},
					{name:"cie10_descripcion",index:"cie10_descripcion",width:130},					
					{name:"profesional_nombre",index:"profesional_nombre",width:130},					
					{name:"estado",index:"estado",width:70,align:"left"},
					{name:"paciente_id",index:"paciente_id",width:70,align:"left"},					
					{name:'operaciones',index:'operaciones',width:75,sorttype:'text',sortable:false,search:false}
				],
			   	rowNum:20,
			   	pager: pager_id,
			   	sortname: 'fechaConsulta',
			    sortorder: "desc",
			    height: '100%',
			    gridComplete: function(){
					var ids = jQuery('#'+subgrid_table_id).jqGrid('getDataIDs');
					var be;
					var row
					for(var i=0;i < ids.length;i++){ 
						var cl = ids[i];
						row = jQuery('#'+subgrid_table_id).getRowData(cl);
						be = "<a href='edit/"+ids[i]+"'><span style='float:left' class='ui-icon ui-icon-pencil'></span></a>";
						se = "<a href='delete/"+ids[i]+"'><span style='float:left' class='ui-icon ui-icon-trash'></span></a>";
						jQuery("#"+subgrid_table_id).jqGrid('setRowData',ids[i],{operaciones:be+se}); 
						} 
			    }
			});
			jQuery("#"+subgrid_table_id).jqGrid('navGrid',"#"+pager_id,{search:false,edit:false,add:false,del:false});
			//jQuery("#"+subgrid_table_id).jqGrid('filterToolbar',{stringResult: true,searchOnEnter : true});
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