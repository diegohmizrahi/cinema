<%@ page import="com.globallogic.cinemark.Cinema" %>



<div class="fieldcontain ${hasErrors(bean: cinemaInstance, field: 'cinemaNumber', 'error')} required">
	<label for="cinemaNumber">
		<g:message code="cinema.cinemaNumber.label" default="Cinema Number" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="cinemaNumber" type="number" value="${cinemaInstance.cinemaNumber}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: cinemaInstance, field: 'cinemaType', 'error')} required">
	<label for="cinemaType">
		<g:message code="cinema.cinemaType.label" default="Cinema Type" />
		<span class="required-indicator">*</span>
	</label>
	<g:select name="cinemaType" from="${com.globallogic.cinemark.enums.CinemaType?.values()}" keys="${com.globallogic.cinemark.enums.CinemaType.values()*.name()}" required="" optionValue="type" value="${cinemaInstance?.cinemaType?.name()}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: cinemaInstance, field: 'theater', 'error')} required">
	<label for="theater">
		<g:message code="cinema.theater.label" default="Theater" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="theater" name="theater.id" from="${com.globallogic.cinemark.Theater.list()}" optionKey="id" optionValue="name" required="" value="${cinemaInstance?.theater?.id}" class="many-to-one"/>
</div>

