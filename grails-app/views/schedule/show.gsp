
<%@ page import="com.globallogic.cinemark.Schedule" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="bootstrap">
		<g:set var="entityName" value="${message(code: 'schedules.label', default: 'Schedules')}" />
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
				
					<g:if test="${schedulesInstance?.showTime}">
						<dt><g:message code="schedules.showTime.label" default="Show Time" /></dt>
						
							<dd><g:link controller="showTimes" action="show" id="${schedulesInstance?.showTime?.id}">${schedulesInstance?.showTime?.movie?.title.encodeAsHTML()} - Sala ${schedulesInstance?.showTime?.cinema?.cinemaNumber?.encodeAsHTML()} - ${schedulesInstance?.showTime?.cinema?.theater?.name?.encodeAsHTML()}</g:link></dd>
						
					</g:if>
				
					<g:if test="${schedulesInstance?.time}">
						<dt><g:message code="schedules.time.label" default="Time" /></dt>
						
							<dd><g:fieldValue bean="${schedulesInstance}" field="time"/></dd>
						
					</g:if>
				
				</dl>

				<g:form>
					<g:hiddenField name="id" value="${schedulesInstance?.id}" />
					<div class="form-actions">
						<g:link class="btn" action="edit" id="${schedulesInstance?.id}">
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
