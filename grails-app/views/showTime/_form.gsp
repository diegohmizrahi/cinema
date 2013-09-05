<%@ page import="com.globallogic.cinemark.ShowTime" %>



<div class="fieldcontain ${hasErrors(bean: showTimesInstance, field: 'cinema', 'error')} required">
	<label for="cinema">
		<g:message code="showTimes.cinema.label" default="Cinema" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="cinema" name="cinema.id" from="${com.globallogic.cinemark.Cinema.list()}" optionKey="id" optionValue="${{'Sala ' + it.cinemaNumber + ' - ' + it.theater.name }}" required="" value="${showTimesInstance?.cinema?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: showTimesInstance, field: 'fromDate', 'error')} required">
	<label for="fromDate">
		<g:message code="showTimes.fromDate.label" default="From Date" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="fromDate" precision="day"  value="${showTimesInstance?.fromDate}"  />
</div>


<div class="fieldcontain ${hasErrors(bean: showTimesInstance, field: 'untilDate', 'error')} required">
	<label for="untilDate">
		<g:message code="showTimes.untilDate.label" default="Until Date" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="untilDate" precision="day"  value="${showTimesInstance?.untilDate}"  />
</div>


<div class="fieldcontain ${hasErrors(bean: showTimesInstance, field: 'movie', 'error')} required">
	<label for="movie">
		<g:message code="showTimes.movie.label" default="Movie" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="movie" name="movie.id" from="${com.globallogic.cinemark.Movie.list()}" optionValue="title" optionKey="id" required="" value="${showTimesInstance?.movie?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: showTimesInstance, field: 'price', 'error')} required">
	<label for="price">
		<g:message code="showTimes.price.label" default="Price" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="price" value="${fieldValue(bean: showTimesInstance, field: 'price')}" required=""/>
</div>

