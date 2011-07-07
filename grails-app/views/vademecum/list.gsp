
<%@ page import="com.medfire.Vademecum" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'vademecum.label', default: 'Vademecum')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
        <script type="text/javascript" src="${resource(dir:'js/script/vademecum',file:'vademec.js')}"></script>
        <script type="text/javascript">
        	var locvademec = '<%out << g.createLink(controller:'vademecum',action:'listjson')%>';
        	$(document).ready(
                	function(){
                    }
            );
        </script>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        </div>
        <h1><g:message code="default.list.label" args="[entityName]" /></h1>
		<div class="body">
       		<table id="listvademec"></table>
        </div>
        <div id="pagervademec"></div>
    </body>
</html>
