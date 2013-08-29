<%@ page import="com.globallogic.cinemark.SeatSection" %>



<div class="fieldcontain ${hasErrors(bean: seatSectionInstance, field: 'cinema', 'error')} required">
	<label for="cinema">
		<g:message code="seatSection.cinema.label" default="Cinema" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="cinema" name="cinema.id" from="${com.globallogic.cinemark.Cinema.list()}" optionValue="${{'Sala ' + it.cinemaNumber + ' - ' + it.theater.name }}" optionKey="id" required="" value="${seatSectionInstance?.cinema?.id}" class="many-to-one"
	valueMessagePrefix="default.list.label"
	
	/>
</div>

<div class="fieldcontain ${hasErrors(bean: seatSectionInstance, field: 'colums', 'error')} required">
	<label for="colums">
		<g:message code="seatSection.colums.label" default="Colums" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="colums" type="number" value="${seatSectionInstance.colums}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: seatSectionInstance, field: 'rows', 'error')} required">
	<label for="rows">
		<g:message code="seatSection.rows.label" default="Rows" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="rows" type="number" value="${seatSectionInstance.rows}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: seatSectionInstance, field: 'type', 'error')} required">
	<label for="type">
		<g:message code="seatSection.type.label" default="Type" />
		<span class="required-indicator">*</span>
	</label>
	<g:select name="type" from="${com.globallogic.cinemark.enums.SeatsSectionType?.values()}" optionValue="section" keys="${com.globallogic.cinemark.enums.SeatsSectionType.values()*.name()}" required="" value="${seatSectionInstance?.type?.name()}"/>
</div>

