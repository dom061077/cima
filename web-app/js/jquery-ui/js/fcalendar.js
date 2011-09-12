/*
 * FCalendar 1.00
 *
 * Enjoy this software. Originally downloaded from http://www.sokati.com, 
 * check there for the latest version.
 *
 */
//var loc = 'fcalendar'; // url relative to the index.html
var formStart = '<div><form onsubmit="return false;"><p>Title</p><p><input name=title id=titleId maxlength=192 size=48 value=\'';
var formEnd = '\'></p></form></div>';
var refrescarflag=true;
var timer

function dragStart( event, jsEvent, ui, view ){
	refrescarflag=false;
}

function dragStop( event, jsEvent, ui, view ){
	refrescarflag=true;
	
}

function refrescar(){
	$('#calendar').events=loc + '/?cmd=read&profesionalId='+$("#profesionalId").val();
	  
	$('#calendar').fullCalendar('refetchEvents');
}

function isInteger(iString) {
	return (("" + parseInt(iString)) == iString);
}

function guiCreate(start, end, allDay) {
	refrescarflag=false;
	
	/*if((end-start)!=1800000){
		$('<div><span class="ui-icon ui-icon-alert" style="float:left; margin:0 7px 50px 0;"></span>El turno no puede ser mayor a 30 minutos</div>').dialog({
			title: 'Error',
			modal:true,
			resizable: false,
			close:function(event,ui){
				refrescarflag=true
			}
		});
		return
	}*/
	if(allDay){
		$('<div><span class="ui-icon ui-icon-alert" style="float:left; margin:0 7px 50px 0;"></span>No se puede crear un turno jornada completa</div>').dialog({
			title: 'Error',
			modal:true,
			resizable: false,
			close:function(event,ui){
				refrescarflag=true
			}
		});
		return
	}
	$('#calendar').fullCalendar('unselect');	
	var id = $("#event-form")//var id = $(formStart + formEnd);
	
	$(id).dialog( {
		title : 'Crear Turno',
		modal : true,
		autoOpen : true,
		width : 400,
		height: 400,
		minHeight:400,
		resizable : false,
		open:function(event,ui){
			refrescarflag=false;
			$("#paciente").val("");
			$("#pacienteId").val("");
			$("#fechaStartId").val($.format.date(start,"dd/MM/yyyy hh:mm"));
			$("#fechaEndId").val($.format.date(end,"dd/MM/yyyy hh:mm"));
		},
		close : function(event, ui) {
			/*$(id).html('');*/
			refrescarflag=true;
		},
		buttons : {
			"Ok" : function() {
				pacienteId = $("#pacienteId").val();
				profesional= $("#profesionalId").val();
				$(id).dialog("close");
				ev = {
					pacienteId : pacienteId,
					profesionalId:profesional,
					title: $("#paciente").val(),
					start : start.getTime() / 1000,
					end : end.getTime() / 1000,
					allDay : allDay,
					startyear:start.getFullYear(),
					startmonth:start.getMonth(),
					startday:start.getDate(),
					starthours:start.getHours(),
					startminutes:start.getMinutes(),
					endyear:end.getFullYear(),
					endmonth:end.getMonth(),
					endday:end.getDate(),
					endhours:end.getHours(),
					endminutes:end.getMinutes()
				};
				//refrescarflag=true;
				//if (!paciente) {
				//	return;
				//}
				if  ($.trim(ev.title)==""){
					$('<div><span class="ui-icon ui-icon-alert" style="float:left; margin:0 7px 50px 0;"></span>Ingrese algún texto descriptivo para el turno</div>').dialog({
						title: 'Error',
						modal:true,
						resizable: false,
						close:function(event,ui){
							
						}
					});					
					return;
				}
				serverSave(ev);
			},
			"Cancelar" : function() {
				$(id).dialog("close");
				//refrescarflag=true;
			}
		}
	});
	
	$(id).height("auto");


}

function serverSave(ev) {
	$
			.ajax( {
				type : 'GET',
				url : loc,
				data : 'cmd=save&title=' + encodeURIComponent(ev.title)
						+ '&start=' + ev.start + '&end=' + ev.end + '&allDay='
						+ ev.allDay+'&pacienteId='+ev.pacienteId+'&profesionalId='+ev.profesionalId
						+ '&startyear='+ev.startyear+'&startmonth='+ev.startmonth+'&startday='+ev.startday
						+ '&starthours='+ev.starthours+'&startminutes='+ev.startminutes
						+ '&endyear='+ev.endyear+'&endmonth='+ev.endmonth+'&endday='+ev.endday
						+ '&endhours='+ev.endhours+'&endminutes='+ev.endminutes
						,
				success : function(msg) {
					if (!isInteger(msg)) {
						$(
								'<div>' + msg + '</div>')
								.dialog();
						return;
					}
					ev.id = msg;
					$('#calendar').fullCalendar('renderEvent', ev); // create
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					$(
							'<div>' + 'Error - no se creó el turno: ' + textStatus
									+ ' - ' + errorThrown + ' - '
									+ XMLHttpRequest + '</div>').dialog();
				}
			});
}

function guiUpdateDrag(ev, dayDelta, minuteDelta, allDay, revertFunc, jsEvent,
		ui, view) {
	//ev.start = ev.start.getTime() / 1000;
	//ev.end = ev.end.getTime() / 1000;
	refrescarflag=false
	var fechatest = ev.start.getTime();
	if (typeof (ev.id) == 'undefined') {// can be removed
		$('<div>ev.id no está definido...</div>').dialog();
		return;
	}

	// server update drag
	$.ajax( {
				type : 'GET',
				url : loc,
				data : 'cmd=updatePos&id=' + ev.id 
					   + '&start=' + ev.start.getTime()/1000
					   + '&startyear='+ev.start.getFullYear()
					   + '&startmonth='+ev.start.getMonth()
					   + '&startday='+ev.start.getDate()
					   + '&starthours='+ev.start.getHours()
					   + '&startminutes='+ev.start.getMinutes()
					   + '&end=' + ev.end.getTime()/1000
					   + '&endyear='+ev.end.getFullYear()
					   + '&endmonth='+ev.end.getMonth()
					   + '&endday='+ev.end.getDate()
					   + '&endhours='+ev.end.getHours()
					   + '&endminutes='+ev.end.getMinutes()
					   + '&version='+ev.version
					   ,
				success : function(msg) {
					if (!msg.result.success) {
						$(
								'<div>' + msg.result.title + '</div>').dialog({
									close: function(event,ui){
										refrescarflag=true
									}
								});
						revertFunc();
						
						return;
					}
					//$('#calendar').fullCalendar('updateEvent', ev);
					$('#calendar').fullCalendar('refetchEvents')
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					$(
							'<div>'+msg.result.title+ '</div>').dialog({
								close:function(event,ui){
									refrescarflag=true
								}
							});
					refrescarflag=true		
					revertFunc();
				}
			});
}

function guiUpdateClick(calEvent, jsEvent, view) {
	if (typeof (calEvent.id) == 'undefined') { // can be removed
		$('<div>ev.id está definido...</div>').dialog();
		return;
	}
	//var id = $(formStart + htmlEncode(calEvent.title, true, false) + formEnd);
	var id = $("#event-form");
	$(id).dialog( {
		title : 'Editar',
		modal : true,
		autoOpen : true,
		position : 'center',
		resizable : false,
		open: function(event,ui){
			refrescarflag=false;
			$("#paciente").val(calEvent.title)
			$("#pacienteId").val(calEvent.pacienteId);
			$("#fechaStartId").val(calEvent.fechaStart);
			$("#fechaEndId").val(calEvent.fechaEnd);			
		},
		width : "340px",
		close : function() {
			//$(id).html('');
			refrescarflag=true;
		},
		buttons : {
			"Guardar" : function() {
				textNew = '';
				$(id).dialog("close");
				serverUpdateTitle(calEvent, textNew);
				$('#calendar').fullCalendar('refetchEvents')
			},
			"Cancelar" : function() {
				$(id).dialog("close");
			},
			"Borrar" : function() {
				$(id).dialog("close");

				var del = $('<div>Está seguro que desea borrar este turno?</div>');
				$(del).dialog( {
					title : 'Delete',
					modal : true,
					autoOpen : true,
					position : 'center',
					resizable : false,
					close : function() {
						//$(del).html('');
					},
					buttons : {
						"Ok" : function() {
							$(del).dialog("close");
							serverDelete(calEvent.id);
						},
						"Cancel" : function() {
							$(del).dialog("close");
						}
					}
				});
			}
		}
	});
	$(id).height("auto");
}

function serverUpdateTitle(calEvent, textNew) {
	
			$.ajax( {
				type : 'GET',
				url : loc,
				data : 'cmd=update&id=' + calEvent.id + '&paciente.id='+$("#pacienteId").val()+'&titulo='+$("#paciente").val()+'&version='+calEvent.version,
						
				success : function(msg) {
					if (!msg.result.success) {
						$('<div ><span class="ui-icon ui-icon-alert" style="float:left; margin:0 7px 50px 0;"></span>' + msg.result.title + '</div>').dialog();
						return;
					}
					calEvent.title = msg.result.title;
					$('#calendar').fullCalendar('updateEvent', calEvent);
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					$(
							'<div>' + 'Error: el turno no se modificó: ' + textStatus
									+ ' - ' + errorThrown + ' - '
									+ XMLHttpRequest + '</div>').dialog();
				}
			});
}

function serverDelete($id) {
	$
			.ajax( {
				type : 'GET',
				url : loc,
				data : 'cmd=delete&id=' + $id,
				success : function(msg) {
					if (msg.length != 0) {
						$(
								'<div>' +  msg + '</div>')
								.dialog();
						return;
					}
					$('#calendar').fullCalendar('removeEvents', $id);
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					$(
							'<div>' + 'Error: error el turno no se borró: ' + textStatus
									+ ' - ' + errorThrown + ' - '
									+ XMLHttpRequest + '</div>').dialog();
				}
			});
}




// http://www.tumuski.com/library/htmlEncode/htmlEncode.js?download
var htmlEncode = function(source, display, tabs) {
	var i, s, ch, peek, line, result, next, endline, push, spaces;
	next = function() {
		peek = source.charAt(i);
		i += 1;
	};
	endline = function() {
		line = line.join('');
		if (display) {
			line = line.replace(/(^ )|( $)/g, '&nbsp;');
		}
		result.push(line);
		line = [];
	};
	push = function() {
		if (ch < ' ' || ch > '~') {
			line.push('&#' + ch.charCodeAt(0) + ';');
		} else {
			line.push(ch);
		}
	};
	tabs = (tabs >= 0) ? Math.floor(tabs) : 4;
	result = [];
	line = [];
	i = 0;
	next();
	while (i <= source.length) {
		ch = peek;
		next();
		switch (ch) {
		case '<':
			line.push('&lt;');
			break;
		case '>':
			line.push('&gt;');
			break;
		case '&':
			line.push('&amp;');
			break;
		case '"':
			line.push('&quot;');
			break;
		case "'":
			line.push('&#39;');
			break;
		default:
			if (display) {
				switch (ch) {
				case '\r':
					if (peek === '\n') {
						next();
					}
					endline();
					break;
				case '\n':
					endline();
					break;
				case '\t':
					spaces = tabs - (line.length % tabs);
					for (s = 0; s < spaces; s += 1) {
						line.push(' ');
					}
					break;
				default:
					push();
				}
			} else {
				push();
			}
		}
	}
	endline();
	result = result.join('<br />');
	if (display) {
		result = result.replace(/ {2}/g, ' &nbsp;');
	}
	return result;
};
