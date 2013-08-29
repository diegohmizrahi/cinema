<%@ page import="com.globallogic.cinemark.security.User"%>


<div
	class="control-group ${hasErrors(bean: userInstance, field: 'username', 'error')} required">
	<label class="control-label" for="username"> <g:message
			code="user.username.label" default="Username" /> <span
		class="required-indicator">*</span>
	</label>
	<div class="controls">
		<g:textField name="username" required=""
			value="${userInstance?.username}" />
	</div>
</div>

<div
	class="control-group ${hasErrors(bean: userInstance, field: 'email', 'error')} ">
	<label class="control-label" for="email"> <g:message
			code="user.email.label" default="Email" />

	</label>
	<div class="controls">
		<g:textField name="email" required="" value="${userInstance?.email}" />
	</div>
</div>

<div
	class="control-group ${hasErrors(bean: userInstance, field: 'password', 'error')} required">
	<label class="control-label" for="password"> <g:message
			code="user.password.label" default="Password" /> <span
		class="required-indicator">*</span>
	</label>
	<div class="controls">
		<g:passwordField name="newIdentification" />
	</div>
</div>

<div
	class="control-group ${hasErrors(bean: userInstance, field: 'password', 'error')} required">
	<label class="control-label" for="confirmPassword"> <g:message
			code="user.confirmpassword.label" default="Confirm Password" /> <span
		class="required-indicator">*</span>
	</label>
	<div class="controls">

		<g:passwordField name="confirmIdentification" />

	</div>
</div>
<g:if test="${currentUser.superAdmin == true || userInstance.superAdmin == true}">
<div
	class="control-group ${hasErrors(bean: userInstance, field: 'accountExpired', 'error')} ">
	<label class="control-label" for="accountExpired"> <g:message
			code="user.accountExpired.label" default="Account Expired" />

	</label>
	<div class="controls">
		<g:checkBox name="accountExpired"
			value="${userInstance?.accountExpired}" />
	</div>
</div>

<div
	class="control-group ${hasErrors(bean: userInstance, field: 'accountLocked', 'error')} ">
	<label class="control-label" for="accountLocked"> <g:message
			code="user.accountLocked.label" default="Account Locked" />

	</label>
	<div class="controls">
		<g:checkBox name="accountLocked"
			value="${userInstance?.accountLocked}" />
	</div>
</div>

<div
	class="control-group ${hasErrors(bean: userInstance, field: 'enabled', 'error')} ">
	<label class="control-label" for="enabled"> <g:message
			code="user.enabled.label" default="Enabled" />

	</label>
	<div class="controls">
		<g:checkBox name="enabled" value="${userInstance?.enabled}" />
	</div>
</div>


<div
	class="control-group ${hasErrors(bean: userInstance, field: 'passwordExpired', 'error')} ">
	<label class="control-label" for="passwordExpired"> <g:message
			code="user.passwordExpired.label" default="Password Expired" />

	</label>
	<div class="controls">
		<g:checkBox name="passwordExpired"
			value="${userInstance?.passwordExpired}" />
	</div>
</div>
</g:if>

<g:if test="${params.action == 'edit' || params.action == 'update'}">
	<div
		class="control-group required">
		<label class="control-label" for="password"> <g:message
				code="admin.password.label" default="Password" /> <span
			class="required-indicator">*</span>
		</label>
		<div class="controls">
			<g:passwordField name="adminPassword" required=""/>
		</div>
	</div>
</g:if>







