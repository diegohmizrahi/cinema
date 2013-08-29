
<%@ page import="com.globallogic.cinemark.Cinema" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="bootstrap">
		<g:set var="entityName" value="${message(code: 'cinema.label', default: 'Cinema')}" />
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
						
							<g:sortableColumn property="cinemaNumber" title="${message(code: 'cinema.cinemaNumber.label', default: 'Cinema Number')}" />
						
							<g:sortableColumn property="cinemaType" title="${message(code: 'cinema.cinemaType.label', default: 'Cinema Type')}" />
						
							<th class="header"><g:message code="cinema.theater.label" default="Theater" /></th>
						
							<th></th>
						</tr>
					</thead>
					<tbody>
					<g:each in="${cinemaInstanceList}" var="cinemaInstance">
						<tr>
						
							<td>${fieldValue(bean: cinemaInstance, field: "cinemaNumber")}</td>
						
							<td>${fieldValue(bean: cinemaInstance, field: "cinemaType.type")}</td>
						
							<td>${fieldValue(bean: cinemaInstance, field: "theater.name")}</td>
						
							<td class="link">
								<g:link action="show" id="${cinemaInstance.id}" class="btn btn-small">Show &raquo;</g:link>
							</td>
						</tr>
					</g:each>
					</tbody>
				</table>
				<div class="pagination">
					<bootstrap:paginate total="${cinemaInstanceTotal}" />
				</div>
			</div>

		</div>
	</body>
</html>
