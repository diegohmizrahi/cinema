
<%@ page import="com.globallogic.cinemark.Movie" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="bootstrap">
		<g:set var="entityName" value="${message(code: 'movie.label', default: 'Movie')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<div class="row-fluid">
			
			<div class="span3">
				<div class="well">
					<ul class="nav nav-list">
						<li class="nav-header">${entityName}</li>
						<li class="active">
							<g:link class="list" action="list">
								<i class="icon-list icon-white"></i>
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
					<h1><g:message code="default.list.label" args="[entityName]" /></h1>
				</div>

				<g:if test="${flash.message}">
				<bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
				</g:if>
				
				<table class="table table-striped">
					<thead>
						<tr>
						
							<g:sortableColumn property="imdbId" title="${message(code: 'movie.imdbId.label', default: 'Imdb Id')}" />
						
							<g:sortableColumn property="summary" title="${message(code: 'movie.summary.label', default: 'Summary')}" />
						
							<g:sortableColumn property="title" title="${message(code: 'movie.title.label', default: 'Title')}" />
						
							<th></th>
						</tr>
					</thead>
					<tbody>
					<g:each in="${movieInstanceList}" var="movieInstance">
						<tr>
						
							<td>${fieldValue(bean: movieInstance, field: "imdbId")}</td>
						
							<td>${fieldValue(bean: movieInstance, field: "summary")}</td>
						
							<td>${fieldValue(bean: movieInstance, field: "title")}</td>
						
							<td class="link">
								<g:link action="show" id="${movieInstance.id}" class="btn btn-small">Show &raquo;</g:link>
							</td>
						</tr>
					</g:each>
					</tbody>
				</table>
				<div class="pagination">
					<bootstrap:paginate total="${movieInstanceTotal}" />
				</div>
			</div>

		</div>
	</body>
</html>
