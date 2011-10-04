security {

	// see DefaultSecurityConfig.groovy for all settable/overridable properties

	active = true

	loginUserDomainClass = "com.medfire.User"
	authorityDomainClass = "com.medfire.Role"
	requestMapClass = "com.medfire.Requestmap"
	
	loginFormUrl = "/login"
	defaultTargetUrl = "/"
	defaultRole = "ROLE_ADMIN"
	useMail = true
	userRequestMapDomainClass = true
	afterLogoutUrl = '/login'
	useLogger = true
	cacheUsers = false
	
	
}
