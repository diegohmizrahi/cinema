<%@ page import="com.globallogic.cinemark.Movie" %>



<div class="fieldcontain ${hasErrors(bean: movieInstance, field: 'imdbId', 'error')} ">
	<label for="imdbId">
		<g:message code="movie.imdbId.label" default="Imdb Id" />
		
	</label>
	<g:textField name="imdbId" value="${movieInstance?.imdbId}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: movieInstance, field: 'summary', 'error')} ">
	<label for="summary">
		<g:message code="movie.summary.label" default="Summary" />
		
	</label>
	<g:textField name="summary" value="${movieInstance?.summary}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: movieInstance, field: 'title', 'error')} ">
	<label for="title">
		<g:message code="movie.title.label" default="Title" />
		
	</label>
	<g:textField name="title" value="${movieInstance?.title}"/>
</div>

