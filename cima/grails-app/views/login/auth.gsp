<head>
<meta name='layout' content='main' />
<title>Login</title>
<style type='text/css' media='screen'>

.inner {
	background: url(../images/login-box-backg.png) no-repeat;
}
#login .inner .fheader {
	padding:4px;margin:3px 0px 3px 0;color:#2e3741;font-size:14px;font-weight:bold;
}
#login .inner .cssform p {
	clear: left;
	margin: 0;
	padding: 5px 0 8px 0;
	padding-left: 105px;
	border-top: 1px  gray;
	margin-bottom: 10px;
	height: 1%;
}
#login .inner .cssform input[type='text'] {
	width: 120px;
}
#login .inner .cssform label {
	font-weight: bold;
	float: left;
	margin-left: -105px;
	width: 100px;
}

#login .inner .login_message {color:red;}
#login .inner .text_ {width:120px;}
#login .inner .chk {height:12px;}
</style>
</head>

<body>
	<div class="span-8 prepend-7">
		<div class='span-3'>
			<g:if test='${flash.message}'>
			<div class='login_message'>${flash.message}</div>
			</g:if>
		</div>
		<div class="clear"></div>	
		<div class='span-4'>Ingrese al Sistema</div>
		<div class="clear"></div>
		<div class="inner span-8">
			<form action='${postUrl}' method='POST' id='loginForm' class='cssform'>
				<div class="span-2">
					<label for='j_username'>Usuario:</label>
				</div>
				<div class="span-4">	
					<input type='text' class='text_' name='j_username' id='j_username' value='${request.remoteUser}' />
				</div>
				<div class="clear"></div>
				
				<div class="span-2">
					<label for='j_password'>Contraseña:</label>
				</div>
				<div class="span-4">	
					<input type='password' class='text_' name='j_password' id='j_password' />
				</div>
				<div class="clear"></div>				
				
				<div class="span-2">
					<label for='remember_me'>Recordarme</label>
				</div>
				<div class="span-1">	
					<input type='checkbox' class='chk' name='_spring_security_remember_me' id='remember_me'
					<g:if test='${hasCookie}'>checked='checked'</g:if> />
				</div>
				<div class="span-3">
					<input type='submit' value='Ingresar' />
				</div>	
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
