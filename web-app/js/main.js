        $(document).ready(function() {
        	var timeout    = 500;
        	var closetimer = 0;
        	var ddmenuitem = 0;

        	function jsddm_open()
        	{  jsddm_canceltimer();
        	   jsddm_close();
        	   ddmenuitem = $(this).find('ul').css('visibility', 'visible');}

        	function jsddm_close()
        	{  if(ddmenuitem) ddmenuitem.css('visibility', 'hidden');}

        	function jsddm_timer()
        	{  closetimer = window.setTimeout(jsddm_close, timeout);}

        	function jsddm_canceltimer()
        	{  if(closetimer)
        	   {  window.clearTimeout(closetimer);
        	      closetimer = null;}}

        	$('#jsddm > li').bind('mouseover', jsddm_open);
        	$('#jsddm > li').bind('mouseout',  jsddm_timer);

        	document.onclick = jsddm_close;

        	$.ajaxPrefilter(function( options, _, jqXHR ) {
        	    
        	        jqXHR.success(function(msg) {
            	        if(msg.accessdenied)
            	        	$('<div title="Mensaje"><span class="ui-icon ui-icon-alert" style="float:left; margin:0 7px 50px 0;"></span>Tiene el acceso denegado a alguna funcionalidad</div>').dialog({
	            	        		modal: true,
	            	    			buttons: {
	            	    				Ok: function() {
	            	    					$( this ).dialog( "close" );
	            	    				}
	            	    			}
                	        	});
        	        	if(msg.loginredirect)
            	        	$('<div title="Mensaje"><span class="ui-icon ui-icon-alert" style="float:left; margin:0 7px 50px 0;"></span>Sesión caducada</div>').dialog({
            	        		modal: true,
            	    			buttons: {
            	    				Ok: function() {
                	    				$( this ).dialog( "close" );
                	    				window.location=urlsesion;
            	    				}
            	    			}
            	        	});
        	            
        	        });
        	    
        	});
        	
			
				
				
				
				
				

				function showspinner(){
					$("#turnosol").html("Cargando...");
					
				}

				
				
				function refreshturnosprof(){
					$.ajax({
						type:'POST',
						url:urlpacientes,
						data: 'profesionalId='+profesionalId,
						beforeSend: function(){
							showspinner();
						},
						success: function(msg){
							var listcontent='';
							if(msg.success){
								//msg.results.each
								//listcontent=listcontent+'<li>'+msg.result+'</li>'
								
								for(var i=0;i < msg.results.length; i++){
									listcontent=listcontent+"<li dato='"+msg.results[i].id+"' class='ui-widget-content'>"+msg.results[i].title+"</li>";
									
								}	
							}else
								listcontent='No tiene turnos asignados';
							$('#turnosol').html(listcontent);
							
							
						},
						err: function(XMLHttpRequest, textStatus, errorThrown){
						}
					});
				} 
				
	  });
    
	
