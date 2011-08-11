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
		                descfield:'descripcion'
		            };
		            function showgriddialog(){
						//$('#searchDialogId').add(grid);
		            	$('#searchDialogId').dialog('open');
		            }
		            var settings = $.extend(defaults, settings);
		 
		            return this.each(function() {
		 
		                var s = settings;
		                var b = $(this);
						$('body').append('<div style="display:none" id="searchDialogId"></div>');
		            	$('#searchDialogId').append('<table id="tablesearchId"></table><div id="pagersearchId"></div>');
						var grid = $('#tablesearchId');
		            	$('#searchDialogId').append(grid);
						$("#tablesearchId").jqGrid({
							caption:settings.title,
							width:380,
							url:settings.source,
							//postData:{profesionalId:$("#profesionalId").val()},
							mtype:"POST",
							rownumbers:false,
							pager:'#pagersearchId',
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
								var obj=$("#tablesearchId").getRowData(id);
								$('#'+settings.hiddenid).val(obj[settings.hiddenfield]);
								$('#'+settings.descid).val(obj[settings.descfield]);		 
								$("#searchDialogId").dialog("close");
								
							} 
						});
						jQuery("#tablesearchId").jqGrid('filterToolbar',{stringResult: true,searchOnEnter : true});
		 
		            	$('#searchDialogId').dialog({
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
						
		                $(this).after('<label><a style="width:50px" id="searchlink" href="#"><span  class="ui-icon ui-icon-search">...</span></a></label>');
		                $('#searchlink').click(function(){
		                	showgriddialog();
		                });
		            });
		        }
		    });
});