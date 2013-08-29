
<%@ page import="com.globallogic.cinemark.ShowTimes" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="bootstrap">
		<g:set var="entityName" value="${message(code: 'showTimes.label', default: 'ShowTimes')}" />
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
						
							<th class="header"><g:message code="showTimes.cinema.label" default="Cinema" /></th>
						
							<g:sortableColumn property="fromDate" title="${message(code: 'showTimes.fromDate.label', default: 'From Date')}" />
							
							<g:sortableColumn property="untilDate" title="${message(code: 'showTimes.untilDate.label', default: 'Until Date')}" />
						
							<th class="header"><g:message code="showTimes.movie.label" default="Movie" /></th>
						
							<g:sortableColumn property="price" title="${message(code: 'showTimes.price.label', default: 'Price')}" />
						
							
						
							<th></th>
						</tr>
					</thead>
					<tbody>
					<g:each in="${showTimesInstanceList}" var="showTimesInstance">
						<tr>
						
							<td>Sala ${fieldValue(bean: showTimesInstance, field: "cinema.cinemaNumber")}</td>
						
							<td><g:formatDate date="${showTimesInstance.fromDate}" /></td>
							
							<td><g:formatDate date="${showTimesInstance.untilDate}" /></td>
						
							<td>${fieldValue(bean: showTimesInstance, field: "movie.title")}</td>
						
							<td>${fieldValue(bean: showTimesInstance, field: "price")}</td>
						
							<td class="link">
								<g:link action="show" id="${showTimesInstance.id}" class="btn btn-small">Show &raquo;</g:link>
							</td>
						</tr>
					</g:each>
					</tbody>
				</table>
				<div class="pagination">
					<bootstrap:paginate total="${showTimesInstanceTotal}" />
				</div>
			</div>

		</div>
	</body>
</html>
