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

<div class="fieldcontain ${hasErrors(bean: movieInstance, field: 'actors', 'error')} ">
	<label for="actors">
		<g:message code="movie.actors.label" default="Actors" />
		
	</label>
	<g:textField name="actors" value="${movieInstance?.actors}"/>
</div>


<div class="fieldcontain ${hasErrors(bean: movieInstance, field: 'picUrl', 'error')} ">
	<label for="picUrl">
		<g:message code="movie.picUrl.label" default="Picture Url" />
		
	</label>
	<g:textField name="picUrl" value="${movieInstance?.picUrl}"/>
</div>


<div class="fieldcontain ${hasErrors(bean: movieInstance, field: 'trailerUrl', 'error')} ">
	<label for="trailerUrl">
		<g:message code="movie.trailerUrl.label" default="Trailer Url" />
		
	</label>
	<g:textField name="trailerUrl" value="${movieInstance?.trailerUrl}"/>
</div>


<div class="fieldcontain ${hasErrors(bean: movieInstance, field: 'genre', 'error')} ">
	<label for="genre">
		<g:message code="movie.genre.label" default="Genre" />
		
	</label>
	<g:textField name="genre" value="${movieInstance?.genre}"/>
</div>


<div class="fieldcontain ${hasErrors(bean: movieInstance, field: 'director', 'error')} ">
	<label for="director">
		<g:message code="movie.director.label" default="Director" />
		
	</label>
	<g:textField name="director" value="${movieInstance?.director}"/>
</div>


<div class="fieldcontain ${hasErrors(bean: movieInstance, field: 'year', 'error')} ">
	<label for="year">
		<g:message code="movie.year.label" default="Year" />
		
	</label>
	<g:textField name="year" value="${movieInstance?.year}"/>
</div>
