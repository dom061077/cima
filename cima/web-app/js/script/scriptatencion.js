	var timer;
	var refrescarAtencion=true;
	
	function mostrarconsultashistoria(){
		var id = $('#listturnos').jqGrid('getGridParam','selrow');
	    if(!id){
		   $('<div>Seleccione una fila para activar esta opción</div>').dialog({title:'Mensaje',modal: true});
		   return;
	    }
	    var rowData = $("#listturnos").getRowData(id);
		var pacienteId  = rowData.paciente_id;
		$('#hcnConsultaHistoriaId').html(pacienteId);
		$('#apellidoNombreConsultaHistoriaId').html(rowData.titulo);
		
		$('#listConsultasHistoriaId').jqGrid({
			caption:'Consultas del paciente',
			height:100, 
			width: 450,
			url:locconsultashistoria,
			rowNum:10,
			//fillSpace: true,
			postData: {pacienteId : pacienteId},
			mtype:"POST",
			loadtext:'',
			//rownumbers:true,
	   		//rowList:[10,20,30],
	   		//rowTotal:2000,
	   		pager:"#pagerListCOnsultasHistoriaId",
			sortname:'fechaAlta',
			sortorder:'desc',
			//scrollOffset:0,
			//viewrecords: true,
	   		subGrid:true,
	   		subGridRowExpanded: function(subgrid_id, row_id){
				var obj = $('#listConsultasHistoriaId').getRowData(row_id);
	   			$.ajax({
	   				url:locconsultahistoriashow+'/'+obj.id,
	   				success: function(data){
	   					$('#'+subgrid_id).html(data);
	   					
	   					$('#tabsConsultasHistoria').tabs();
	   					$('#tabs-estudiosconsultashistoria').tabs();
	   				}
	   			});
				
	   		},
			datatype: "json",
			colNames:['Id','Fecha Visita','CIE-10','CIE-10 Desc.','Profesional','Estado'],
			colModel:[ {name:'id',index:'id', width:10, sorttype:"int", sortable:false,hidden:true},
					   {name:'fechaAlta',index:'fechaAlta', width:60,sorttype:'text',sortable:true},	
					   {name:'cie10',index:'cod_estado', width:30,sortable:false,hidden:false,search:false},
					   {name:'cie10_descripcion',index:'cie10_descripcion',width:150,sortable:true},
					   {name:'profesional_nombre',index:'profesional_nombre',hidden:true},
					   {name:'estado',index:'estado',hidden:true}
					 ] 

		});
		var filter = { groupOp: "AND", rules: []};
		filter.rules.push({field:"paciente_id",op:"eq",data:pacienteId});

		var grid = $('#listConsultasHistoriaId') 
		grid[0].p.search = filter.rules.length>0;
		$.extend(grid[0].p.postData,{altfilters:JSON.stringify(filter)});
		grid.trigger("reloadGrid",[]);
		
		$('#panelConsultasHistoriaId').dialog({
			height:300,width:500
			,modal:true
			,show:'explode'
			,resizable:false
			,hide: 'explode'
			
		});
	}

	function cargarturnos(){
		var left = parseInt($.cookie('atencionleft'));
		$('body').append('PARAMETRO LEFT: '+left);
		$('#exploradorId').dialog({height:300,width:215,position:[left,200]
			,dragStop: function(event,ui){
				//alert('PARO DE MOVERSE');
				$('body').append('LEFT: '+ui.position.left);
				$.cookie('atencionleft',ui.position.left,{path:'/'});
				$.cookie('atenciontop',ui.position.top,{path:'/'});
				$('body').append('LEFT con cookie: '+$.cookie('atencionleft'));
			}
			,resizeStop: function(event,ui){
				$.cookie('atencionwidth',ui.size.width,{path:'/'});
				$.cookie('atencionheight',ui.size.height,{path:'/'});
			}
			,show: 'blind'
			,hide: 'explode'
			,close: function(event,ui){
				$('#panelEsperaId').show();
				$.cookie('flagShowTurnos','false',{path:'/'});
			}
		});
	}
	
	function gotonuevaconsulta(){
 	   var id = jQuery('#listturnos').jqGrid('getGridParam','selrow');
	   if(id)
		   window.location=locpacientes+'/'+id;
	   else
		   $('<div>Seleccione una fila para activar esta opción</div>').dialog({title:'Mensaje',modal: true});
	}
	
	function gototocambiarestado(){
		var rowid = $('#listturnos').getGridParam('selrow');
	    if(!rowid){
		   $('<div>Seleccione una fila para activar esta opción</div>').dialog({title:'Mensaje',modal: true});
		   return;
	    }
		
		var row = jQuery("#listturnos").getRowData(rowid);
		$("#estadoeventId option[value*='"+row.cod_estado+"']").attr('selected', 'selected');
		$("#pacienteturnoId").val(row.titulo);
		$("#turnoId").val(row.id);
		$("#versionId").val(row.version);
		$("#fechaturnoInicioId").val(row.fechaStartLarge)
		$("#fechaturnoFinId").val(row.fechaEndLarge)							
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
	}
	
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
						//caption:'Turnos en Espera', 
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
						colNames:['Id','Paciente','Cod_Estado','Estado','Version','Inicio','Inicio Large','Fin','Fin Large','BackgroundColor','Paciente Id'],
						colModel:[ {name:'id',index:'id', width:10, sorttype:"int", sortable:false,hidden:true},
								   {name:'titulo',index:'titulo', width:60,sorttype:'text',sortable:false},	
								   {name:'cod_estado',index:'cod_estado', width:30,sortable:false,hidden:true,search:false},
								   {name:'estado',index:'estado', width:20,sortable:false,hidden:false,search:false},
								   {name:'version',index:'version', width:20,sortable:false,hidden:true,search:false},								   
								   {name:'fechaStart',index:'fechaStart', width:20,sortable:false,hidden:false,search:false},
								   {name:'fechaStartLarge',index:'fechaStart', width:20,sortable:false,hidden:true,search:false},
								   {name:'fechaEnd',index:'fechaEnd', width:25,sortable:false,hidden:false,search:false},
								   {name:'fechaEndLarge',index:'fechaEnd', width:25,sortable:false,hidden:true,search:false},
								   {name:'backgroundColor',index:'backgroundColor', width:20,sortable:false,hidden:true,search:false},
								   {name:'paciente_id',index:'paciente_id',hidden:true}
								 ], 
					    afterInsertRow: function(rowid, rowdata, rowelem) {
				                        //$('#' + rowid).contextMenu('MenuJqGrid', eventsMenu);
				                },
						ondblClickRow:function( rowid) { // here is the code
							gototocambiarestado();
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
				
				if($.cookie('atencionleft')){
					left = $.cookie('atencionleft');
					
				}
				if($.cookie('atenciontop'))
					top = $.cookie('atenciontop');
				if($.cookie('atencionwidth'))
					width = $.cookie('atencionwidth');
				if($.cookie('atencionheight'))
					height = $.cookie('atencionheight');
				//alert('Ubicaciones: '+left+'-'+top+'-'+width+'-'+height);
				
				$('#linkActivateEsperaId').click(function(){
					$('#panelEsperaId').hide();
					cargarturnos();
					$.cookie('flagShowTurnos','true',{path:'/'});
				});	
				
				if($.cookie('flagShowTurnos')=='true'){
					$('#panelEsperaId').hide();
					cargarturnos();
				}else{
					$('#panelEsperaId').show();
				}
				
				$('#menuExploradorEstadoId').button({
					icons:{primary:'ui-icon-person'}
				});
				
				$('#menuExploradorHistId').button({
					icons:{primary:'ui-icon-folder-open'}
				});
				
				$('#menuExploradorNuevaConsultaId').button({
					icons:{primary:'ui-icon-document'}
				});
				
				$('#menuExploradorEstadoId').bind('click',function(){
					gototocambiarestado();
				});
				
				$('#menuExploradorNuevaConsultaId').bind('click',function(){
					gotonuevaconsulta();
				});
				
				$('#menuExploradorHistId').bind('click',function(){
					mostrarconsultashistoria();
				});
				
				
				$('#menuExploradorHistFechaId').click(function(){
					$('#menuExplorardorHistFechaDatePickerId').show();					
				});
				
				
				$('#menuExplorardorHistFechaDatePickerId').datepicker({
					onSelect:function(dateText, inst){
						$('#menuExploradorHistFechaId').html(dateText);
						$('#menuExplorardorHistFechaDatePickerId').hide();
						var grid = $('#listturnos'); 
						$.extend(grid[0].p.postData,{fechaFiltro:dateText});
						grid.trigger("reloadGrid",[]);

					}
					,dateFormat:'dd/mm/yy'
					,changeYear:true,
	                closeText: 'Cerrar',
	                prevText: '&#x3c;Ant',
	                nextText: 'Sig&#x3e;',
	                currentText: 'Hoy',
	                monthNames: ['Enero','Febrero','Marzo','Abril','Mayo','Junio',
	                'Julio','Agosto','Septiembre','Octubre','Noviembre','Diciembre'],
	                monthNamesShort: ['Ene','Feb','Mar','Abr','May','Jun',
	                'Jul','Ago','Sep','Oct','Nov','Dic'],
	                dayNames: ['Domingo','Lunes','Martes','Mi&eacute;rcoles','Jueves','Viernes','S&aacute;bado'],
	                dayNamesShort: ['Dom','Lun','Mar','Mi&eacute;','Juv','Vie','S&aacute;b'],
	                dayNamesMin: ['Do','Lu','Ma','Mi','Ju','Vi','S&aacute;'],
	                weekHeader: 'Sm',
	                dateFormat: 'dd/mm/yy',
	                firstDay: 1,
	                isRTL: false,
	                showMonthAfterYear: false,
	                yearSuffix: ''					
				});
				var fecha = new Date();
				fecha.setDate(fecha.getDate());
				$('#menuExplorardorHistFechaDatePickerId').hide();
				$('#menuExplorardorHistFechaDatePickerId').datepicker("setDate",fecha);
				$('#menuExploradorHistFechaId').html($.datepicker.formatDate('dd/mm/yy', fecha));
					
});                
