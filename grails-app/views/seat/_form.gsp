<%@ page import="com.globallogic.cinemark.Seat" %>



<div class="fieldcontain ${hasErrors(bean: seatInstance, field: 'email', 'error')} ">
	<label for="email">
		<g:message code="seat.email.label" default="Email" />
		
	</label>
	<g:field type="email" name="email" value="${seatInstance?.email}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: seatInstance, field: 'confirmationCode', 'error')} ">
	<label for="confirmationCode">
		<g:message code="seat.confirmationCode.label" default="Confirmation Code" />
		
	</label>
	<g:textField name="confirmationCode" value="${seatInstance?.confirmationCode}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: seatInstance, field: 'identificationNumber', 'error')} ">
	<label for="identificationNumber">
		<g:message code="seat.identificationNumber.label" default="Identification Number" />
		
	</label>
	<g:field name="identificationNumber" type="number" value="${seatInstance.identificationNumber}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: seatInstance, field: 'row', 'error')} ">
	<label for="row">
		<g:message code="seat.row.label" default="Row" />
		
	</label>
	<g:textField name="row" value="${seatInstance?.row}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: seatInstance, field: 'seatNumber', 'error')} required">
	<label for="seatNumber">
		<g:message code="seat.seatNumber.label" default="Seat Number" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="seatNumber" type="number" value="${seatInstance.seatNumber}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: seatInstance, field: 'showTime', 'error')} required">
	<label for="showTime">
		<g:message code="seat.showTime.label" default="Show Time" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="showTime" name="showTime.id" from="${com.globallogic.cinemark.ShowTimes.list()}" optionKey="id" required="" value="${seatInstance?.showTime?.id}" class="many-to-one"/>
</div>

