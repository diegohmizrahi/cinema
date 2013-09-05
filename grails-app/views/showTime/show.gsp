
<%@ page import="com.globallogic.cinemark.ShowTime" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="bootstrap">
		<g:set var="entityName" value="${message(code: 'showTimes.label', default: 'ShowTimes')}" />
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
				
					<g:if test="${showTimesInstance?.cinema}">
						<dt><g:message code="showTimes.cinema.label" default="Cinema" /></dt>
						
						<dd><g:link controller="cinema" action="show" id="${showTimesInstance?.cinema?.id}">Sala ${showTimesInstance?.cinema.cinemaNumber.encodeAsHTML()} - ${showTimesInstance?.cinema?.theater?.name?.encodeAsHTML() }</g:link></dd>
					</g:if>
				
					<g:if test="${showTimesInstance?.fromDate}">
						<dt><g:message code="showTimes.fromDate.label" default="From Date" /></dt>
						
							<dd><g:formatDate date="${showTimesInstance?.fromDate}" /></dd>
						
					</g:if>
					
					<g:if test="${showTimesInstance?.untilDate}">
						<dt><g:message code="showTimes.untilDate.label" default="Until Date" /></dt>
						
							<dd><g:formatDate date="${showTimesInstance?.untilDate}" /></dd>
						
					</g:if>
				
					<g:if test="${showTimesInstance?.movie}">
						<dt><g:message code="showTimes.movie.label" default="Movie" /></dt>
						
							<dd><g:link controller="movie" action="show" id="${showTimesInstance?.movie?.id}">${showTimesInstance?.movie?.title?.encodeAsHTML()}</g:link></dd>
						
					</g:if>
				
					<g:if test="${showTimesInstance?.price}">
						<dt><g:message code="showTimes.price.label" default="Price" /></dt>
						
							<dd><g:fieldValue bean="${showTimesInstance}" field="price"/></dd>
						
					</g:if>
				
					<g:if test="${showTimesInstance?.schedules}">
						<dt><g:message code="showTimes.schedules.label" default="Schedules" /></dt>
						
							<g:each in="${showTimesInstance.schedules}" var="s">
							<dd><g:link controller="schedules" action="show" id="${s.id}">${s?.time?.encodeAsHTML()}</g:link></dd>
							</g:each>
						
					</g:if>
				
				</dl>

				<g:form>
					<g:hiddenField name="id" value="${showTimesInstance?.id}" />
					<div class="form-actions">
						<g:link class="btn" action="edit" id="${showTimesInstance?.id}">
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
