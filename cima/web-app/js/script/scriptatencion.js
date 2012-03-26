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
					
					
			var exploradorGrid = jQuery("#listturnos").jqGrid({ 
						caption:'Turnos en Espera', 
						//height:200, 
						width: 190,
						url:locturnos,
						//rowNum:10,
						//fillSpace: true,
						//postData: {profesionalId : profesionalId,cmd:'list'},
        				mtype:"POST",
        				loadtext:'',
        				//rownumbers:true,
				   		//rowList:[10,20,30],
				   		//rowTotal:2000,
				   		//pager:"#pagerlistturnos",
				   		 
						//scrollOffset:0,
						//viewrecords: true,
						datatype: "json",
						colNames:['Id','Paciente','Cod_Estado','Estado','Version','Inicio','Fin','BackgroundColor'],
						colModel:[ {name:'id',index:'id', width:10, sorttype:"int", sortable:false,hidden:true},
								   {name:'titulo',index:'titulo', width:60,sorttype:'text',sortable:false},	
								   {name:'cod_estado',index:'cod_estado', width:30,sortable:false,hidden:true,search:false},
								   {name:'estado',index:'estado', width:20,sortable:false,hidden:false,search:false},
								   {name:'version',index:'version', width:20,sortable:false,hidden:true,search:false},								   
								   {name:'fechaStart',index:'fechaStart', width:20,sortable:false,hidden:false,search:false},
								   {name:'fechaEnd',index:'fechaEnd', width:25,sortable:false,hidden:false,search:false},
								   {name:'backgroundColor',index:'backgroundColor', width:20,sortable:false,hidden:true,search:false}
								 ], 
					    afterInsertRow: function(rowid, rowdata, rowelem) {
				                        //$('#' + rowid).contextMenu('MenuJqGrid', eventsMenu);
				                },
						ondblClickRow:function( rowid) { // here is the code 		 
								// var selr = jQuery('#grid').jqGrid('getGridParam','selrow')
							var row = jQuery("#listturnos").getRowData(rowid);
							
							$("#estadoeventId option[value*='"+row.cod_estado+"']").attr('selected', 'selected');

							$("#pacienteturnoId").val(row.titulo);
							$("#turnoId").val(row.id);
							$("#versionId").val(row.version);
							$("#fechaturnoInicioId").val(row.fechaStart)
							$("#fechaturnoFinId").val(row.fechaEnd)							
							$( "#dialog-form" ).dialog({
									autoOpen: true,
									modal: true,
									open:function(event,ui){
										refrescarAtencion=false;
									},
									buttons: {
										"Guardar": function() {
											var id = $('#turnoId').val()
											var estado = $('#estadoturnoId').val();
											updateestadoturno(id,estado);
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
						multiselect: false,
						gridComplete: function(){
							var ids = $("#listturnos").jqGrid('getDataIDs');
							var obj;
							for(var i=0;i < ids.length;i++){
								obj = $("#listturnos").getRowData(ids[i]);
								$("#listturnos").setCell (ids[i],'estado','',{'background':obj.backgroundColor});	
							}
							

						}
					});
			//$("#listturnos").closest(".ui-jqgrid-bdiv").css({ 'overflow-y' : 'scroll' });
			//for(var i=0;i<=mydata.length;i++) jQuery("#listturnos").jqGrid('addRowData',i+1,mydata[i]);
			
				jQuery("#listturnos").jqGrid('filterToolbar',{stringResult: true,searchOnEnter : true});
				//jQuery("#listturnos").jqGrid('navGrid','#pagerlistturnos',{refresh:false,search:false,edit:false,add:false,del:false,pdf:true});
				
				jQuery("#listturnos").jqGrid('navButtonAdd','#pagerlistturnos',{
				       caption:"Hist.Clínica", 
				       onClickButton : function () { 
				           //jQuery("#list").excelExport();
				    	   var id = jQuery('#listturnos').jqGrid('getGridParam','selrow');
				    	   if(id)
				    		   window.location=locpacientes+'/'+id;
				    	   else
				    		   $('<div>Seleccione una fila para activar esta opción</div>').dialog({title:'Mensaje',modal: true});
				       } 
				});				
				
				//$(".ui-jqgrid-titlebar-close",exploradorGrid[0].grid.cDiv).click()			

						
				timer = $.timer(25000, function() {
						if (refrescarAtencion)
							$("#listturnos").trigger("reloadGrid")
						;
					});	
				var height=250;
				var width=200;
				var top=200;
				var left=0;
				
				if($.cookie('atencionleft'))
					left = $.cookie('atencionleft'); 
				if($.cookie('atenciontop'))
					top = $.cookie('atenciontop');
				if($.cookie('atencionwidth'))
					width = $.cookie('atencionwidth');
				if($.cookie('atencionheight'))
					height = $.cookie('atencionheight');
				//alert('Ubicaciones: '+left+'-'+top+'-'+width+'-'+height);
				
				$('#exploradorId').dialog({height:300,width:215,position:[0,200]
						/*,dragStop: function(event,ui){
							//alert('PARO DE MOVERSE');
							$.cookie('atencionleft',ui.position.left);
							$.cookie('atenciontop',ui.position.top);
						}
						,resizeStop: function(event,ui){
							$.cookie('atencionwidth',ui.size.width);
							$.cookie('atencionheight',ui.size.height);
						}*/
					});
					
	
				
					
});                
