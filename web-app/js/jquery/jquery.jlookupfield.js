/*http://thefinishedbox.com/tutorials/javascript-ajax/simple-jquery-button-plugin-tutorial/*/

$(document).ready(function(){
	$.fn.extend({
		 
		        lookupfield: function(settings) {
		 
		            var defaults = {
		                hiddenid: '',
		                descid:'',
		                source:'',
		                title:'',
		                colnames:[],
		                colModel:[],
		                hiddenfield:'id',
		                descfield:['descripcion']
		            };
		            function showgriddialog(dialog){
						//$('#searchDialogId').add(grid);
		            	$('#'+dialog).dialog('open');
		            }
		            var settings = $.extend(defaults, settings);
		 
		            return this.each(function() {
		 
		                var s = settings;
		                var b = $(this);
		                var tableSearchId = $(this).attr("id")+"tablesearchId";
		                var searchDialogId = $(this).attr("id")+"searchDialogId";
		                var pagerSearchId = $(this).attr("id")+"pagersearchId";
		                var searchLinkId = $(this).attr("id")+"searchlinkId";
						$('body').append('<div style="display:none" id="'+searchDialogId+'"></div>');
		            	$('#'+searchDialogId).append('<table id="'+tableSearchId+'"></table><div id="'+pagerSearchId+'"></div>');
						var grid = $('#'+tableSearchId);
		            	$('#'+searchDialogId).append(grid);
						$('#'+tableSearchId).jqGrid({
							caption:settings.title,
							width:380,
							url:settings.source,
							//postData:{profesionalId:$("#profesionalId").val()},
							mtype:"POST",
							rownumbers:false,
							pager:'#'+pagerSearchId,
							datatype:"json",
							/*colnames:['Id','D.N.I','Apellido','Nombre'],
							colModel:[
										{name:'id',index:'id', width:10, sorttype:"int", sortable:false,hidden:true,search:false},
										{name:'dni',index:'dni', width:100, sorttype:"int", sortable:false,search:true, searchoptions: {sopt:['eq']} },	
										{name:'apellido',index:'apellido', width:100,sortable:false,search:true},
										{name:'nombre',index:'nombre', width:100, sortable:false,search:true}
									 ],*/
							//colnames:settings.colnames,
							//colModel:settings.colModel,
							colnames:settings.colnames,
							colModel:settings.colModel,
									 
							ondblClickRow: function(id){
								var obj=$('#'+tableSearchId).getRowData(id);
								var descriptions = "";
								$.each(settings.descfield,function(index,value){
									descriptions=descriptions+"-"+obj[value];
								});
								descriptions = descriptions.substring(1,descriptions.length);
								$('#'+settings.hiddenid).val(obj[settings.hiddenfield]);
								$('#'+settings.descid).val(descriptions);		 
								$('#'+searchDialogId).dialog("close");
								
							} 
						});
						jQuery('#'+tableSearchId).jqGrid('filterToolbar',{stringResult: true,searchOnEnter : true});
		 
		            	$('#'+searchDialogId).dialog({
		            		title:'Buscar',
		            		modal:true,
		            		resizable:false,
		            		autoOpen:false,
		            		width : 400,
		            		height: 'auto',
		            		minHeight:350,
		            		position:'center',
		            		open: function(event,ui){
		            			
		            		}
		            	});
		            	//$('#searchDialogId').height('auto'); 
		            	//$('#searchiDialogId').add(grid);	
		                $(this).after('<div style="float:left;padding:5px 0px 0px 0px"><a href="" onclick="return false" style="width:50px" id="'+searchLinkId+'" href="#"><span  class="ui-icon ui-icon-search">...</span></a></div>');
		                $(this).css('float','left');
		                $(this).keyup(function(){
		                	if($.trim($(this).val())==""){
		                		$('#'+settings.hiddenid).val("")
		                	}
		                });
		                $('#'+searchLinkId).click(function(){
		                	showgriddialog(searchDialogId);
		                });
		            });
		        }
		    });
});