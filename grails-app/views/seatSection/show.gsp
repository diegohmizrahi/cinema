
<%@ page import="com.globallogic.cinemark.SeatSection" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="bootstrap">
		<g:set var="entityName" value="${message(code: 'seatSection.label', default: 'SeatSection')}" />
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
				
					<g:if test="${seatSectionInstance?.cinema}">
						<dt><g:message code="seatSection.cinema.label" default="Cinema" /></dt>
						
							<dd><g:link controller="cinema" action="show" id="${seatSectionInstance?.cinema?.id}">Sala ${seatSectionInstance?.cinema.cinemaNumber.encodeAsHTML()} - ${seatSectionInstance?.cinema?.theater?.name?.encodeAsHTML() }</g:link></dd>
						
					</g:if>
				
					<g:if test="${seatSectionInstance?.columns}">
						<dt><g:message code="seatSection.columns.label" default="columns" /></dt>
						
							<dd><g:fieldValue bean="${seatSectionInstance}" field="columns"/></dd>
						
					</g:if>
				
					<g:if test="${seatSectionInstance?.rows}">
						<dt><g:message code="seatSection.rows.label" default="Rows" /></dt>
						
							<dd><g:fieldValue bean="${seatSectionInstance}" field="rows"/></dd>
						
					</g:if>
				
					<g:if test="${seatSectionInstance?.type}">
						<dt><g:message code="seatSection.type.label" default="Type" /></dt>
						
							<dd><g:fieldValue bean="${seatSectionInstance}" field="type.section"/></dd>
						
					</g:if>
				
				</dl>

				<g:form>
					<g:hiddenField name="id" value="${seatSectionInstance?.id}" />
					<div class="form-actions">
						<g:link class="btn" action="edit" id="${seatSectionInstance?.id}">
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
