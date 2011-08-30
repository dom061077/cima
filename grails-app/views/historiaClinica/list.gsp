
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'historiaClinica.label', default: 'HistoriaClinica')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
         <link rel="stylesheet" type="text/css" media="screen" href="${g.resource(dir:'js/jquery-ui/js/src/css',file:'jquery.searchFilter.css')}" />        
        <script type="text/javascript" src="${g.resource(dir:'js/jquery-ui/js/src/i18n',file:'grid.locale-es.js')}"></script>
        <script type="text/javascript" src="${g.resource(dir:'js/jquery-ui/js',file:'jquery.jqGrid.min.js')}"></script>
        <script type="text/javascript">
        	var lochistoria = '<%out << "${g.createLink(controller:'historiaClinica',action:'listjson')}"%>;';
        	var locsubgridconsulta = '<%out << "${g.createLink(controller:'consulta',action:'listjson')}"%>';
        	var locconsultaedit = '<%out << "${g.createLink(controller:'consulta',action:'edit')}"%>';
        	var locconsultadel = '<%out << "${g.createLink(controller:'consulta',action:'delete')}"%>';
        </script>
        <script type="text/javascript" src="${g.resource(dir:'js/script/historia',file:'list.js')}"></script>        
        
    </head>
    <body>
        <%--div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        </div --%>
        <div class="body">
	        <h1><g:message code="default.list.label" args="[entityName]" /></h1>
	        <div class="body">
				<table style="z-index:1"  id="listhistoria"></table>
				<div id="pagerhistoria" ></div>
	        </div>
        </div>
    </body>
</html>
