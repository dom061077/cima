	var timer;
	var refrescarAtencion=true;
	function updateestadoturno(id,estado){
		$.ajax({
			url:locupdturnos,
			type:'GET',
			data:"id="+id+"&estado="+estado,
			success : function(msg) {
				if (msg.result.success)
					$('#listturnos').trigger('reloadGrid');		
				else{
					$(
							'<div>' + msg.result.title + '</div>')
							.dialog();
					return;
				}
				
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$(	'<div>' + 'Error: item not created: ' + textStatus
								+ ' - ' + errorThrown + ' - '
								+ XMLHttpRequest + '</div>').dialog();
			}
		});
	}

	var eventsMenu = {
            bindings: {
                'atendido': function(t) {
                	//var obj=$("#listturnos").getRowData(t.id);
                    //alert('Titulo del Turno: '+obj.titulo+', id el turno: '+obj.id);
                	updateestadoturno(t.id,'EVENT_ATENDIDO');
                    
                },
                'ausente': function(t) {
                    updateestadoturno(t.id,'EVENT_AUSENTE');
                },
                'anulado': function(t) {
                    updateestadoturno(t.id,'EVENT_ANULADO');
                },
                'pendiente': function(t) {
                    updateestadoturno(t.id,'EVENT_PENDIENTE');
                },
                'datosdelpaciente': function(t) {
                	window.location=locpacientes+'/'+t.id;
                }
            }
        };	
			$(document).ready(function() {
					
		
			jQuery("#listturnos").jqGrid({ 
						caption:'Turnos en Espera', 
						//height:200, 
						width: 200,
						url:locturnos,
						//rowNum:10,
						//fillSpace: true,
						//postData: {profesionalId : profesionalId,cmd:'list'},
        				mtype:"POST",
        				//rownumbers:true,
				   		//rowList:[10,20,30],
				   		//rowTotal:2000,
				   		
				   		 
						//scrollOffset:0,
						//viewrecords: true,
						datatype: "json",
						colNames:['Id','Paciente','Cod_Estado','Estado','Version','Inicio','Fin'],
						colModel:[ {name:'id',index:'id', width:10, sorttype:"int", sortable:false,hidden:true},
								   {name:'titulo',index:'titulo', width:100,sorttype:'text',sortable:false},	
								   {name:'cod_estado',index:'cod_estado', width:60,sortable:false,hidden:true,search:false},
								   {name:'estado',index:'estado', width:60,sortable:false,hidden:false,search:false},
								   {name:'version',index:'version', width:60,sortable:false,hidden:true,search:false},								   
								   {name:'fechaStart',index:'fechaStart', width:60,sortable:false,hidden:false,search:false},
								   {name:'fechaEnd',index:'fechaEnd', width:60,sortable:false,hidden:false,search:false}
								 ], 
					    afterInsertRow: function(rowid, rowdata, rowelem) {
				                        $('#' + rowid).contextMenu('MenuJqGrid', eventsMenu);
				                },
						ondblClickRow:function( rowid) { // here is the code 		 
								// var selr = jQuery('#grid').jqGrid('getGridParam','selrow')
							var row = jQuery("#listturnos").getRowData(rowid);
							
							$("#estadoeventId option[value*='"+row.cod_estado+"']").attr('selected', 'selected');

							$("#pacienteturnoId").val(row.paciente);
							$("#turnoId").val(row.id);
							$("#versionId").val(row.version);
							$("#fechaturnoId").val(row.fechaStart)
							$( "#dialog-form" ).dialog({
									autoOpen: true,
									modal: true,
									open:function(event,ui){
										refrescarAtencion=false;
									},
									buttons: {
										"Guardar": function() {
											$.ajax({
												 url:locturnos,
												 type:"post",
												 data:$("#formturnosId").serialize(),
												 success:function(msg){
												 	if(msg.result.success){
												 		$("#listturnos").trigger("reloadGrid");
												 		
												 	}else
												 		$('<div><span class="ui-icon ui-icon-alert" style="float:left; margin:0 7px 50px 0;"></span>'
												 			+msg.result.title+'</div>').dialog({
															title: 'Error',
															modal:true,
															resizable: false,
															close:function(event,ui){
																
															}
														});
												 },
												 error : function(XMLHttpRequest, textStatus, errorThrown) {
												 }
											});
											$(this).dialog( "close" );
										},
										"Cancelar": function() {
											$( this ).dialog( "close" );
										}
									},
									close: function() {
										refrescarAtencion=true;
									}
								});
						},
						multiselect: false});
			//$("#listturnos").closest(".ui-jqgrid-bdiv").css({ 'overflow-y' : 'scroll' });
			//for(var i=0;i<=mydata.length;i++) jQuery("#listturnos").jqGrid('addRowData',i+1,mydata[i]);
			
				jQuery("#listturnos").jqGrid('filterToolbar',{stringResult: true,searchOnEnter : true}); 
							

						
				timer = $.timer(25000, function() {
						if (refrescarAtencion)
							$("#listturnos").trigger("reloadGrid")
						;
					});			
			});                
