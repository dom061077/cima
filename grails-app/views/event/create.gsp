

<%@ page import="com.medfire.Event" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'event.label', default: 'Event')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
        <link rel="stylesheet" href="${resource(dir:'css',file:'fullcalendar.css')}" />
        <script type="text/javascript">
        	var loc='<%out<< g.createLink(controller:'event',action:'operation');%>';
        </script>
        <script type="text/javascript" src="${resource(dir:'js/jquery-ui/js',file:'fcalendar.js')}"></script>
        <script type="text/javascript" src="${resource(dir:'js/jquery-ui/js',file:'fullcalendar.min.js')}"></script>
        <script type="text/javascript" src="${resource(dir:'js/script',file:'jquicombobox.js')}"></script>
        <script type="text/javascript" src="${resource(dir:'js/script',file:'jquery.jlookupfield.js')}"></script>
        

        
        <style type="text/css">
        	//div.ui-datepicker {
			//	font-size: 40%; 
			//}
 			//.calendar{font-size: 9px !important:}
 			
        </style>
        <script type="text/javascript">
			$(document).ready(function() {
				$('#calendar').fullCalendar( {
					theme : true,
					header : {
						left : 'prev,next today',
						center : 'title',
						right : 'month,agendaWeek,agendaDay'
					},
					/*custumization*/
					monthNames: ['Enero','Febrero','Marzo','Abril','Mayo','Junio','Julio','Agosto','Septiembre','Octubre','Noviembre','Diciembre'],
					monthNamesShort: ['Ene','Feb','Mar','Abr','May','Jun','Jul','Ago','Sep','Oct','Nov','Dic'],
					dayNames: ['Domingo','Lunes','Martes','Miércoles','Jueves','Viernes','Sábado'],
					dayNamesShort: ['Dom','Lun','Mar','Mie','Jue','Vie','Sab'],
					columnFormat: {
						month: 'ddd',
						week: 'ddd d/M',
						day: 'dddd d/M'
					},
					height:400,
					
					buttonText: {
						prev: '&nbsp;&#9668;&nbsp;',
						next: '&nbsp;&#9658;&nbsp;',
						prevYear: '&nbsp;&lt;&lt;&nbsp;',
						nextYear: '&nbsp;&gt;&gt;&nbsp;',
						today: 'hoy',
						month: 'mes',
						week: 'semana',
						day: 'día'
					},	
					allDayText:'todo el día',
					weekends: true,
					slotMinutes: 30,
						
					/***************/
					allDaySlot:false,
					timeFormat : 'H:mm',
					axisFormat : 'H:mm',
					defaultView : 'agendaWeek',
					firstDay : 1,
					selectable : true,
					selectHelper : true,
					select : guiCreate,
					editable : true,
					disableResizing:true,
					//minTime:7,
					//maxTime:'10pm',
					eventDragStart: dragStart,
					eventDragStop: dragStop,
					eventDrop : guiUpdateDrag,
					eventResize : guiUpdateDrag,
					eventClick : guiUpdateClick,
					loading : function(bool) {
						if (bool)
							$('#loading').show();
						else
							setTimeout("$('#loading').hide()",2000);
					},
					events : loc + '/?cmd=read&profesionalId='+$("#profesionalId").val()
					
					/*events: function(start, end, callback) {
						        $.ajax({
						            url: loc,
						            dataType: 'json',
						            data: {
						                // our hypothetical feed requires UNIX timestamps
						                cmd: "read",
						                profesionalId: $("#profesionalId").val(),
						            },
						            success: function(doc) {
						
						                var events = [];
										
										$(doc).each(function(){
											events.push({
												title: this.title,
												start: this.start,
												end: this.end,
												allDay: this.allDay,
												id: this.id
											});
										});
											
						
						                callback(events);
						            }
						        });
					    	}*/
					
					});//end del llamado al metodo fullcalendar
			
				// for skipping after a callback from fullcalendar
					var skip = false;
					/*
					$('#datepicker').datepicker( {
						inline : true,
						firstDay : 1,
						changeMonth:true
						,showOn: "button"
						,yearRange:'c-111:c+10'
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
			            yearSuffix: '',
			            			
						onSelect : function(dateText, inst) {
							var d = new Date(inst.currentYear,inst.currentMonth,inst.currentDay);
							$('#calendar').fullCalendar('gotoDate', d);
						},
						onChangeMonthYear : function(year, month, inst) {
							if (skip) {
								skip = false;
								return;
							}
							//var dateCurrent = $("#datepicker").datepicker('getDate');
							//dateCurrent.setYear(year);
							//dateCurrent.setMonth(month - 1);
							//$('#calendar').fullCalendar('gotoDate', dateCurrent);
							//$('#calendar').fullCalendar('setDate', dateCurrent);
						}
					});*/
			
					// React on datepicker next/prev/today buttons
					/*
					setDatepickerFromFullCalendar = function() {
						var d = $('#calendar').fullCalendar('getDate');
			
						// detect whether datepicker should skip the next trigger
						//var dateCurrent = $("#datepicker").datepicker('getDate');
						//if (d.getMonth() != dateCurrent.getMonth()) {
						//	skip = true;
						//}
						//$("#datepicker").datepicker('setDate', d);
					};
					
					$(".fc-button-prev").click(setDatepickerFromFullCalendar);
					$(".fc-button-next").click(setDatepickerFromFullCalendar);
					$(".fc-button-today").click(function() {
						var d = new Date();
						skip = true;
						//$("#datepicker").datepicker('setDate', d);
					//$('#calendar').fullCalendar('gotoDate', d);
					});*/
					
					timer = $.timer(10000, function() {
						if (refrescarflag)
							//$('#calendar').fullCalendar('refetchEvents')
							;
					});
					
					/*$( "#paciente" ).autocomplete({
									source: "<% out << g.createLink(controller:'paciente',action:'listjsonautocomplete') %>",
									minLength: 2,
									select: function( event, ui ) {
										if(ui.item){
											$("#pacienteId").val(ui.item.id)
										}
										
									},
									open: function() {
										$( this ).removeClass( "ui-corner-all" ).addClass( "ui-corner-top" );
									},
									close: function() {
										$( this ).removeClass( "ui-corner-top" ).addClass( "ui-corner-all" );
									}
						});*/	
					
					
					 $("#profesionalId").combobox({
					        selected: function(event, ui) {
					        	document.profesionales.submit();
					        } // selected
 						}); // combo	
					
					$("#pacienteseventId").jqGrid({
						caption:'Pacientes registrados',
						width:430,
						url:'<% out << g.createLink(controller:'paciente',action:'listjson'); %>',
						postData:{profesionalId:$("#profesionalId").val()},
						mtype:"POST",
						rownumbers:false,
						pager:'#pagerpacienteeventId',
						datatype:"json",
						colnames:['Id','D.N.I','Apellido','Nombre'],
						colModel:[
									{name:'id',index:'id', width:10, sorttype:"int", sortable:false,hidden:true,search:false},
									{name:'dni',index:'dni', width:100, sorttype:"int", sortable:false,search:true, searchoptions: {sopt:['eq']} },	
									{name:'apellido',index:'apellido', width:100,sortable:false,search:true},
									{name:'nombre',index:'nombre', width:100, sortable:false,search:true}
								 ],
						ondblClickRow: function(id){
							var obj=$("#pacienteseventId").getRowData(id);
							$("#paciente").val(obj.apellido+"-"+obj.nombre);
							$("#pacienteId").val(obj.id);		 
							$("#pacienteeventdialogId").dialog("close");
						} 
					});

					jQuery("#pacienteseventId").jqGrid('filterToolbar',{stringResult: true,searchOnEnter : true});
					jQuery("#pacienteseventId").jqGrid('navGrid',"#pagerpacienteeventId",{del:false,add:false,edit:false,search:false}); 			
					$("#buscarpacienteId").button();
					$("#buscarpacienteId").click(function(){
							$("#pacienteeventdialogId").dialog({
								title:"Buscar Paciente",
								modal:true,
								autoOpen:true,
								width:450,
								height:400,
								resizable:false
								
							});
							$("#pacienteeventdialogId").height("auto");
							//$("#pacienteeventdialogId").width("auto");
						});
				});//end del ready's function
        	
        </script>
        
    </head>
    <body>
        <div class="nav">
        </div>
        
		<div id='loading' style='display: none;position:absolute;top:0px;left:0px;background-color: red; color: white;margin: 5px; padding: 3px'>
			cargando...
		</div>
		
		<div id="pacienteeventdialogId" style="float:left; padding: 5px 5px 5px 5px;display: none">
					<table id="pacienteseventId"></table>
		</div>
		<div id="pagerpacienteeventId" ></div>
		
		
		<div style="float:left">
		<div id='datepicker'></div>
		</div>
		<div style="float:left;padding-left:50px; ">
						<form name="profesionales">
							<fieldset class="ingresodialogfieldset">
								<label for="profesional">Profesional:</label>
								<g:select name="profesionalId" id="profesionalId" from="${profesionales}" 
										value="${profesionalId}"  
										optionKey="id"
										optionValue="nombre"   /><br/>
							</fieldset>		
						</form>									
		</div>
		
		
		<div id='calendars' style="padding: 5px 5px 5px 15px">
			<div style="clear: left"></div>
			<div id='calendar'></div>
		</div>
        
        <div id="">
        
        </div>
        
        <div style="display: none" id="event-form" title="Crear Turno" >
        	
        	<form  onsubmit="return false">
        			  
								      	
				<div style="float:left; padding: 5px 5px 5px 5px">
					<label for="pacienteId" style="width:50px">Id:</label>
					<input readonly="true" class="ui-widget ui-corner-all ui-widget-content" type="text" name="pacienteId" id="pacienteId"/><br/>
					
        			<label for="paciente" style="width:50px" for="paciente">Paciente:</label>
        			<input class="ui-widget ui-corner-all ui-widget-content" name="paciente" id="paciente"/>
        			<a id="buscarpacienteId" href="#">...</a>
        			
        		</div>	
        		
        	</form>
        		
        </div>
        <div id="">
        
        </div>
        
        
    </body>
</html>
