
<%@ page import="com.globallogic.cinemark.Theater" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="bootstrap">
		<g:set var="entityName" value="${message(code: 'theater.label', default: 'Theater')}" />
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
				
					<g:if test="${theaterInstance?.address}">
						<dt><g:message code="theater.address.label" default="Address" /></dt>
						
							<dd><g:fieldValue bean="${theaterInstance}" field="address"/></dd>
						
					</g:if>
				
					<g:if test="${theaterInstance?.cinemas}">
						<dt><g:message code="theater.cinemas.label" default="Cinemas" /></dt>
						
							<g:each in="${theaterInstance.cinemas}" var="c">
							<dd><g:link controller="cinema" action="show" id="${c.id}">Sala ${c?.cinemaNumber?.encodeAsHTML()}</g:link></dd>
							</g:each>
						
					</g:if>
					
					<g:if test="${theaterInstance?.name}">
						<dt><g:message code="theater.name.label" default="Name" /></dt>
						
							<dd><g:fieldValue bean="${theaterInstance}" field="name"/></dd>
						
					</g:if>
				
					<g:if test="${theaterInstance?.phone}">
						<dt><g:message code="theater.phone.label" default="Phone" /></dt>
						
							<dd><g:fieldValue bean="${theaterInstance}" field="phone"/></dd>
						
					</g:if>
				
				</dl>

				<g:form>
					<g:hiddenField name="id" value="${theaterInstance?.id}" />
					<div class="form-actions">
						<g:link class="btn" action="edit" id="${theaterInstance?.id}">
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
