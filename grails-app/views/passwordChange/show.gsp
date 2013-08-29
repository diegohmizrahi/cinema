
<%@ page import="com.globallogic.cinemark.security.PasswordChange" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="bootstrap">
		<g:set var="entityName" value="${message(code: 'passwordChange.label', default: 'PasswordChange')}" />
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
				
					<g:if test="${passwordChangeInstance?.changeDate}">
						<dt><g:message code="passwordChange.changeDate.label" default="Change Date" /></dt>
						
							<dd><g:formatDate date="${passwordChangeInstance?.changeDate}" /></dd>
						
					</g:if>
				
					<g:if test="${passwordChangeInstance?.password}">
						<dt><g:message code="passwordChange.password.label" default="Password" /></dt>
						
							<dd><g:fieldValue bean="${passwordChangeInstance}" field="password"/></dd>
						
					</g:if>
				
					<g:if test="${passwordChangeInstance?.user}">
						<dt><g:message code="passwordChange.user.label" default="User" /></dt>
						
							<dd><g:link controller="user" action="show" id="${passwordChangeInstance?.user?.id}">${passwordChangeInstance?.user?.encodeAsHTML()}</g:link></dd>
						
					</g:if>
				
				</dl>

				<g:form>
					<g:hiddenField name="id" value="${passwordChangeInstance?.id}" />
					<div class="form-actions">
						<g:link class="btn" action="edit" id="${passwordChangeInstance?.id}">
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
