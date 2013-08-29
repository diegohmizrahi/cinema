<%@ page import="com.globallogic.cinemark.security.PasswordChange" %>



<div class="fieldcontain ${hasErrors(bean: passwordChangeInstance, field: 'changeDate', 'error')} required">
	<label for="changeDate">
		<g:message code="passwordChange.changeDate.label" default="Change Date" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="changeDate" precision="day"  value="${passwordChangeInstance?.changeDate}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: passwordChangeInstance, field: 'password', 'error')} ">
	<label for="password">
		<g:message code="passwordChange.password.label" default="Password" />
		
	</label>
	<g:textField name="password" value="${passwordChangeInstance?.password}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: passwordChangeInstance, field: 'user', 'error')} required">
	<label for="user">
		<g:message code="passwordChange.user.label" default="User" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="user" name="user.id" from="${com.globallogic.cinemark.security.User.list()}" optionKey="id" required="" value="${passwordChangeInstance?.user?.id}" class="many-to-one"/>
</div>

