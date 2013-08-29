<html>
<head>
	<meta name='layout' content='bootstrap'/>
	<title><g:message code="springSecurity.login.title"/></title>
    <r:require module="bootstrap" />
</head>

<body>

<div class="container-fluid">
    <div class="content">
        <div class="row span4 offset4" style="left: 50%;">
        
        	<g:if test="${flash.message}">
	            <bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
        	</g:if>
        
            <div class="login-form well" style="margin: auto;">
                <h3>Login</h3>
                <form action='${postUrl}' method='POST' id='loginForm' autocomplete='off' >
                    <fieldset>
                        <div class="clearfix">
                            <label for='username'><g:message code="springSecurity.login.username.label"/>:</label>
                            <input type='text' class='text_' name='j_username' id='username'/>
                        </div>
                        <div class="clearfix">
                            <label for='password'><g:message code="springSecurity.login.password.label"/>:</label>
                            <input type='password' class='text_' name='j_password' id='password'/>
                        </div>
<%--                        <p id="remember_me_holder">--%>
<%--                            <input type='checkbox' class='chk' name='${rememberMeParameter}' id='remember_me' checked='checked'/>--%>
<%--                            <label for='remember_me'><g:message code="springSecurity.login.remember.me.label"/></label>--%>
<%--                        </p>--%>
                        <input class="btn primary" type='submit' id="submit" value='${message(code: "springSecurity.login.button")}'/>
                    </fieldset>
                </form>
<%--                 <g:form controller="login" action="forgotPassword" >
					<g:actionSubmit class="btn btn-info" value='${message(code: "user.password.forgot", default: "Forgot Password")}'/>
				</g:form>--%>
            </div>
        </div>
    </div>
</div>

<script type='text/javascript'>
	<!--
	(function() {
		document.forms['loginForm'].elements['j_username'].focus();
	})();
	// -->
</script>
</body>
</html>
