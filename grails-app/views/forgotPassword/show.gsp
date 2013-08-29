
<%@ page import="com.globallogic.cinemark.security.ForgotPassword" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="bootstrap">
		<g:set var="entityName" value="${message(code: 'forgotPassword.label', default: 'ForgotPassword')}" />
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
				
					<g:if test="${forgotPasswordInstance?.backOficeUser}">
						<dt><g:message code="forgotPassword.backOficeUser.label" default="Back Ofice User" /></dt>
						
							<dd><g:link controller="user" action="show" id="${forgotPasswordInstance?.backOficeUser?.id}">${forgotPasswordInstance?.backOficeUser?.encodeAsHTML()}</g:link></dd>
						
					</g:if>
				
					<g:if test="${forgotPasswordInstance?.expirationTime}">
						<dt><g:message code="forgotPassword.expirationTime.label" default="Expiration Time" /></dt>
						
							<dd><g:formatDate date="${forgotPasswordInstance?.expirationTime}" /></dd>
						
					</g:if>
				
					<g:if test="${forgotPasswordInstance?.isExpired}">
						<dt><g:message code="forgotPassword.isExpired.label" default="Is Expired" /></dt>
						
							<dd><g:formatBoolean boolean="${forgotPasswordInstance?.isExpired}" /></dd>
						
					</g:if>
				
					<g:if test="${forgotPasswordInstance?.tokenFP}">
						<dt><g:message code="forgotPassword.tokenFP.label" default="Token FP" /></dt>
						
							<dd><g:fieldValue bean="${forgotPasswordInstance}" field="tokenFP"/></dd>
						
					</g:if>
				
					<g:if test="${forgotPasswordInstance?.passwordChanged}">
						<dt><g:message code="forgotPassword.passwordChanged.label" default="Password Changed" /></dt>
						
							<dd><g:formatBoolean boolean="${forgotPasswordInstance?.passwordChanged}" /></dd>
						
					</g:if>
				
					<g:if test="${forgotPasswordInstance?.expiredTime}">
						<dt><g:message code="forgotPassword.expiredTime.label" default="Expired Time" /></dt>
						
							<dd><g:formatDate date="${forgotPasswordInstance?.expiredTime}" /></dd>
						
					</g:if>
				
				</dl>

				<g:form>
					<g:hiddenField name="id" value="${forgotPasswordInstance?.id}" />
					<div class="form-actions">
						<g:link class="btn" action="edit" id="${forgotPasswordInstance?.id}">
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
