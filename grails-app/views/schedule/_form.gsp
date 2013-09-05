<%@ page import="com.globallogic.cinemark.Schedule" %>



<div class="fieldcontain ${hasErrors(bean: schedulesInstance, field: 'showTime', 'error')} required">
	<label for="showTime">
		<g:message code="schedules.showTime.label" default="Show Time" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="showTime" name="showTime.id" from="${com.globallogic.cinemark.ShowTime.list()}" 	optionValue="${{it.movie?.title + ' - Sala ' + it.cinema?.cinemaNumber + ' - ' + it.cinema?.theater?.name}}"	optionKey="id" required="" value="${schedulesInstance?.showTime?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: schedulesInstance, field: 'time', 'error')} ">
	<label for="time">
		<g:message code="schedules.time.label" default="Time" />
		
	</label>
	<g:select name="hours" from="${00..24}" value="${schedulesInstance?.time?.split(':')?.first() }" />
	<g:select name="minutes" from="${00..59}" value="${schedulesInstance?.time?.split(':')?.last() }"/>
</div>


