
<%@ page import="com.globallogic.cinemark.SeatSection" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="bootstrap">
		<g:set var="entityName" value="${message(code: 'seatSection.label', default: 'SeatSection')}" />
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
						
							<th class="header"><g:message code="seatSection.cinema.label" default="Cinema" /></th>
						
							<g:sortableColumn property="colums" title="${message(code: 'seatSection.colums.label', default: 'Colums')}" />
						
							<g:sortableColumn property="rows" title="${message(code: 'seatSection.rows.label', default: 'Rows')}" />
						
							<g:sortableColumn property="type" title="${message(code: 'seatSection.type.label', default: 'Type')}" />
						
							<th></th>
						</tr>
					</thead>
					<tbody>
					<g:each in="${seatSectionInstanceList}" var="seatSectionInstance">
						<tr>
							<td>Sala ${fieldValue(bean: seatSectionInstance, field: "cinema.cinemaNumber")} - ${seatSectionInstance?.cinema?.theater?.name }</td>
						
							<td>${fieldValue(bean: seatSectionInstance, field: "colums")}</td>
						
							<td>${fieldValue(bean: seatSectionInstance, field: "rows")}</td>
						
							<td>${fieldValue(bean: seatSectionInstance, field: "type.section")}</td>
						
							<td class="link">
								<g:link action="show" id="${seatSectionInstance.id}" class="btn btn-small">Show &raquo;</g:link>
							</td>
						</tr>
					</g:each>
					</tbody>
				</table>
				<div class="pagination">
					<bootstrap:paginate total="${seatSectionInstanceTotal}" />
				</div>
			</div>

		</div>
	</body>
</html>
