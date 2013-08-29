
<%@ page import="com.globallogic.cinemark.security.User" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="bootstrap">
		<g:set var="entityName" value="${message(code: 'user.label', default: 'User')}" />
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
				
					<g:if test="${userInstance?.username}">
						<dt><g:message code="user.username.label" default="Username" /></dt>
						
							<dd><g:fieldValue bean="${userInstance}" field="username"/></dd>
						
					</g:if>
				
				
						<dt><g:message code="user.accountExpired.label" default="Account Expired" /></dt>
						
							<dd><g:formatBoolean boolean="${userInstance?.accountExpired}" /></dd>
						
				
						<dt><g:message code="user.accountLocked.label" default="Account Locked" /></dt>
						
							<dd><g:formatBoolean boolean="${userInstance?.accountLocked}" /></dd>
						
				
						<dt><g:message code="user.enabled.label" default="Enabled" /></dt>
						
							<dd><g:formatBoolean boolean="${userInstance?.enabled}" /></dd>
							
						<dt><g:message code="user.passwordExpired.label" default="Password Expired" /></dt>
						
							<dd><g:formatBoolean boolean="${userInstance?.passwordExpired}" /></dd>
							
							
						<dt><g:message code="user.enabled.superAdmin" default="Super Admin" /></dt>
						
							<dd><g:formatBoolean boolean="${userInstance?.superAdmin}" /></dd>

				
				</dl>

				<g:form>
					<g:hiddenField name="id" value="${userInstance?.id}" />
					<div class="form-actions">
						<g:link class="btn" action="edit" id="${userInstance?.id}">
							<i class="icon-pencil"></i>
							<g:message code="default.button.edit.label" default="Edit" />
						</g:link>
						<button class="btn btn-danger" type="submit" name="_action_delete" onclick="return confirmar();">
							<i class="icon-trash icon-white"></i>
							<g:message code="default.button.delete.label" default="Delete" />
						</button>
					</div>
				</g:form>

			</div>

		</div>
	</body>
</html>
