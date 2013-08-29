<%@ page import="com.globallogic.cinemark.security.ForgotPassword" %>



<div class="fieldcontain ${hasErrors(bean: forgotPasswordInstance, field: 'backOficeUser', 'error')} ">
	<label for="backOficeUser">
		<g:message code="forgotPassword.backOficeUser.label" default="Back Ofice User" />
		
	</label>
	<g:select id="backOficeUser" name="backOficeUser.id" from="${com.globallogic.cinemark.security.User.list()}" optionKey="id" value="${forgotPasswordInstance?.backOficeUser?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: forgotPasswordInstance, field: 'expirationTime', 'error')} required">
	<label for="expirationTime">
		<g:message code="forgotPassword.expirationTime.label" default="Expiration Time" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="expirationTime" precision="day"  value="${forgotPasswordInstance?.expirationTime}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: forgotPasswordInstance, field: 'isExpired', 'error')} ">
	<label for="isExpired">
		<g:message code="forgotPassword.isExpired.label" default="Is Expired" />
		
	</label>
	<g:checkBox name="isExpired" value="${forgotPasswordInstance?.isExpired}" />
</div>

<div class="fieldcontain ${hasErrors(bean: forgotPasswordInstance, field: 'tokenFP', 'error')} ">
	<label for="tokenFP">
		<g:message code="forgotPassword.tokenFP.label" default="Token FP" />
		
	</label>
	<g:textField name="tokenFP" value="${forgotPasswordInstance?.tokenFP}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: forgotPasswordInstance, field: 'passwordChanged', 'error')} ">
	<label for="passwordChanged">
		<g:message code="forgotPassword.passwordChanged.label" default="Password Changed" />
		
	</label>
	<g:checkBox name="passwordChanged" value="${forgotPasswordInstance?.passwordChanged}" />
</div>

<div class="fieldcontain ${hasErrors(bean: forgotPasswordInstance, field: 'expiredTime', 'error')} ">
	<label for="expiredTime">
		<g:message code="forgotPassword.expiredTime.label" default="Expired Time" />
		
	</label>
	<g:datePicker name="expiredTime" precision="day"  value="${forgotPasswordInstance?.expiredTime}" default="none" noSelection="['': '']" />
</div>

