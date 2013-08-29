
<%@ page import="com.globallogic.cinemark.Seat" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="bootstrap">
		<g:set var="entityName" value="${message(code: 'seat.label', default: 'Seat')}" />
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
						
							<g:sortableColumn property="email" title="${message(code: 'seat.email.label', default: 'Email')}" />
						
							<g:sortableColumn property="confirmationCode" title="${message(code: 'seat.confirmationCode.label', default: 'Confirmation Code')}" />
						
							<g:sortableColumn property="identificationNumber" title="${message(code: 'seat.identificationNumber.label', default: 'Identification Number')}" />
						
							<g:sortableColumn property="row" title="${message(code: 'seat.row.label', default: 'Row')}" />
						
							<g:sortableColumn property="seatNumber" title="${message(code: 'seat.seatNumber.label', default: 'Seat Number')}" />
						
							<th class="header"><g:message code="seat.showTime.label" default="Show Time" /></th>
						
							<th></th>
						</tr>
					</thead>
					<tbody>
					<g:each in="${seatInstanceList}" var="seatInstance">
						<tr>
						
							<td>${fieldValue(bean: seatInstance, field: "email")}</td>
						
							<td>${fieldValue(bean: seatInstance, field: "confirmationCode")}</td>
						
							<td>${fieldValue(bean: seatInstance, field: "identificationNumber")}</td>
						
							<td>${fieldValue(bean: seatInstance, field: "row")}</td>
						
							<td>${fieldValue(bean: seatInstance, field: "seatNumber")}</td>
						
							<td>${fieldValue(bean: seatInstance, field: "showTime")}</td>
						
							<td class="link">
								<g:link action="show" id="${seatInstance.id}" class="btn btn-small">Show &raquo;</g:link>
							</td>
						</tr>
					</g:each>
					</tbody>
				</table>
				<div class="pagination">
					<bootstrap:paginate total="${seatInstanceTotal}" />
				</div>
			</div>

		</div>
	</body>
</html>
