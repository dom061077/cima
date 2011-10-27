<head>
<meta name='layout' content='main' />
<title>Login</title>
<style type='text/css' media='screen'>
#login-box {
	width:300px;
	height: 300px;
	padding: 25px 70px 0 50px;
	color: #ebebeb;
	font: 11px Arial, Helvetica, sans-serif;
	background: url(../images/login-box-backg.png) no-repeat;
    /*margin:100px 100px 100px 100px;*/
	margin:100px 100px 100px 100px;	
}
#login-box h2 {
	padding:0;
	margin:0;
	color: #ebebeb;
	font: bold 40px "Calibri", Arial;
}

.login_message {color:red; font: bold 12px Arial;}
/*.................................*/


</style>
</head>

<body>
	<div class="span-9 prepend-3">
		
		
	   	<div id="login-box">
					       <H2>C.I.M.A</H2>
							
			<g:if test='${flash.message}'>
			<div class='login_message'>${flash.message}</div>
			</g:if>
							
							<form action='${postUrl}' method='POST' id='loginForm'>
							
									<table border="solid" cellpadding=4 align="left">
										<tr>
											<td align="right" class=rowhead>
												<label for='j_username'>Usuario:</label>
											</td>
											<td align=left>
												<input size="20" class="ui-state-error ui-corner-all" type='text' class='text_' name='j_username' id='j_username' value='${request.remoteUser}' />
											</td>
										</tr>
										<tr>
											<td align="right" class=rowhead>
												<label for='j_password'>Contraseña:</label>
											</td>
											<td align=left>	
												
													<input class="ui-state-error ui-corner-all" type="password" size=20 name='j_password' id='j_password' />
											</td>
										</tr>
										<tr>
											<td><label for='remember_me'>Recordarme:</label></td>
											<td>
												<input type='checkbox' class='chk' name='_spring_security_remember_me' id='remember_me'
													<g:if test='${hasCookie}'>checked='checked'</g:if> />
											</td>
										</tr>
										<tr>
											<td></td>
											<td align="center">
												<input class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only" type='submit' value='Ingresar' />
												
											</td>
										</tr>
									</table>
							</form>
		</div>

		
			
	</div>
<script type='text/javascript'>
<!--
(function(){
	document.forms['loginForm'].elements['j_username'].focus();
})();
// -->
</script>
</body>
