
<%@ page import="com.globallogic.cinemark.Seat" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="bootstrap">
		<g:set var="entityName" value="${message(code: 'seat.label', default: 'Seat')}" />
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
				
					<g:if test="${seatInstance?.email}">
						<dt><g:message code="seat.email.label" default="Email" /></dt>
						
							<dd><g:fieldValue bean="${seatInstance}" field="email"/></dd>
						
					</g:if>
				
					<g:if test="${seatInstance?.confirmationCode}">
						<dt><g:message code="seat.confirmationCode.label" default="Confirmation Code" /></dt>
						
							<dd><g:fieldValue bean="${seatInstance}" field="confirmationCode"/></dd>
						
					</g:if>
				
					<g:if test="${seatInstance?.identificationNumber}">
						<dt><g:message code="seat.identificationNumber.label" default="Identification Number" /></dt>
						
							<dd><g:fieldValue bean="${seatInstance}" field="identificationNumber"/></dd>
						
					</g:if>
				
					<g:if test="${seatInstance?.row}">
						<dt><g:message code="seat.row.label" default="Row" /></dt>
						
							<dd><g:fieldValue bean="${seatInstance}" field="row"/></dd>
						
					</g:if>
				
					<g:if test="${seatInstance?.seatNumber}">
						<dt><g:message code="seat.seatNumber.label" default="Seat Number" /></dt>
						
							<dd><g:fieldValue bean="${seatInstance}" field="seatNumber"/></dd>
						
					</g:if>
				
					<g:if test="${seatInstance?.showTime}">
						<dt><g:message code="seat.showTime.label" default="Show Time" /></dt>
						
							<dd><g:link controller="showTimes" action="show" id="${seatInstance?.showTime?.id}">${seatInstance?.showTime?.encodeAsHTML()}</g:link></dd>
						
					</g:if>
				
				</dl>

				<g:form>
					<g:hiddenField name="id" value="${seatInstance?.id}" />
					<div class="form-actions">
						<g:link class="btn" action="edit" id="${seatInstance?.id}">
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
