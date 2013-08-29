
<%@ page import="com.globallogic.cinemark.Movie" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="bootstrap">
		<g:set var="entityName" value="${message(code: 'movie.label', default: 'Movie')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<div class="row-fluid">
			
			<div class="span3">
				<div class="well">
					<ul class="nav nav-list">
						<li class="nav-header">${entityName}</li>
						<li>
							<g:link class="list" action="list">
								<i class="icon-list"></i>
								<g:message code="default.list.label" args="[entityName]" />
							</g:link>
						</li>
						<li>
							<g:link class="create" action="create">
								<i class="icon-plus"></i>
								<g:message code="default.create.label" args="[entityName]" />
							</g:link>
						</li>
					</ul>
				</div>
			</div>
			
			<div class="span9">

				<div class="page-header">
					<h1><g:message code="default.show.label" args="[entityName]" /></h1>
				</div>

				<g:if test="${flash.message}">
				<bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
				</g:if>

				<dl>
				
					<g:if test="${movieInstance?.imdbId}">
						<dt><g:message code="movie.imdbId.label" default="Imdb Id" /></dt>
						
							<dd><g:fieldValue bean="${movieInstance}" field="imdbId"/></dd>
						
					</g:if>
				
					<g:if test="${movieInstance?.summary}">
						<dt><g:message code="movie.summary.label" default="Summary" /></dt>
						
							<dd><g:fieldValue bean="${movieInstance}" field="summary"/></dd>
						
					</g:if>
				
					<g:if test="${movieInstance?.title}">
						<dt><g:message code="movie.title.label" default="Title" /></dt>
						
							<dd><g:fieldValue bean="${movieInstance}" field="title"/></dd>
						
					</g:if>
				
				<g:if test="${movieInstance?.actors}">
						<dt><g:message code="movie.actors.label" default="Actors" /></dt>
						
							<dd><g:fieldValue bean="${movieInstance}" field="actors"/></dd>
						
					</g:if>
					
					<g:if test="${movieInstance?.picUrl}">
						<dt><g:message code="movie.picUrl.label" default="Picture Url" /></dt>
						
							<dd><g:fieldValue bean="${movieInstance}" field="picUrl"/></dd>
						
					</g:if>
				
				<g:if test="${movieInstance?.trailerUrl}">
						<dt><g:message code="movie.trailerUrl.label" default="Trailer Url" /></dt>
						
							<dd><g:fieldValue bean="${movieInstance}" field="trailerUrl"/></dd>
						
					</g:if>
				
				<g:if test="${movieInstance?.genre}">
						<dt><g:message code="movie.genre.label" default="Genre" /></dt>
						
							<dd><g:fieldValue bean="${movieInstance}" field="genre"/></dd>
						
					</g:if>
					
					<g:if test="${movieInstance?.director}">
						<dt><g:message code="movie.director.label" default="Director" /></dt>
						
							<dd><g:fieldValue bean="${movieInstance}" field="director"/></dd>
						
					</g:if>
					
					<g:if test="${movieInstance?.year}">
						<dt><g:message code="movie.year.label" default="Year" /></dt>
						
							<dd><g:fieldValue bean="${movieInstance}" field="year"/></dd>
						
					</g:if>
				</dl>
				
				
				

				<g:form>
					<g:hiddenField name="id" value="${movieInstance?.id}" />
					<div class="form-actions">
						<g:link class="btn" action="edit" id="${movieInstance?.id}">
							<i class="icon-pencil"></i>
							<g:message code="default.button.edit.label" default="Edit" />
						</g:link>
						<button class="btn btn-danger" type="submit" name="_action_delete">
							<i class="icon-trash icon-white"></i>
							<g:message code="default.button.delete.label" default="Delete" />
						</button>
					</div>
				</g:form>

			</div>

		</div>
	</body>
</html>
