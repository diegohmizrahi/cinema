<%@ page import="com.globallogic.cinemark.Theater" %>



<div class="fieldcontain ${hasErrors(bean: theaterInstance, field: 'address', 'error')} ">
	<label for="address">
		<g:message code="theater.address.label" default="Address" />
		
	</label>
	<g:textField name="address" value="${theaterInstance?.address}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: theaterInstance, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="theater.name.label" default="Name" />
		
	</label>
	<g:textField name="name" value="${theaterInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: theaterInstance, field: 'phone', 'error')} ">
	<label for="phone">
		<g:message code="theater.phone.label" default="Phone" />
		
	</label>
	<g:textField name="phone" value="${theaterInstance?.phone}"/>
</div>

