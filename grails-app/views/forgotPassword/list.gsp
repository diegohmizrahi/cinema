
<%@ page import="com.globallogic.cinemark.security.ForgotPassword" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="bootstrap">
		<g:set var="entityName" value="${message(code: 'forgotPassword.label', default: 'ForgotPassword')}" />
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
						
							<th class="header"><g:message code="forgotPassword.backOficeUser.label" default="Back Ofice User" /></th>
						
							<g:sortableColumn property="expirationTime" title="${message(code: 'forgotPassword.expirationTime.label', default: 'Expiration Time')}" />
						
							<g:sortableColumn property="isExpired" title="${message(code: 'forgotPassword.isExpired.label', default: 'Is Expired')}" />
						
							<g:sortableColumn property="tokenFP" title="${message(code: 'forgotPassword.tokenFP.label', default: 'Token FP')}" />
						
							<g:sortableColumn property="passwordChanged" title="${message(code: 'forgotPassword.passwordChanged.label', default: 'Password Changed')}" />
						
							<g:sortableColumn property="expiredTime" title="${message(code: 'forgotPassword.expiredTime.label', default: 'Expired Time')}" />
						
							<th></th>
						</tr>
					</thead>
					<tbody>
					<g:each in="${forgotPasswordInstanceList}" var="forgotPasswordInstance">
						<tr>
						
							<td>${fieldValue(bean: forgotPasswordInstance, field: "backOficeUser")}</td>
						
							<td><g:formatDate date="${forgotPasswordInstance.expirationTime}" /></td>
						
							<td><g:formatBoolean boolean="${forgotPasswordInstance.isExpired}" /></td>
						
							<td>${fieldValue(bean: forgotPasswordInstance, field: "tokenFP")}</td>
						
							<td><g:formatBoolean boolean="${forgotPasswordInstance.passwordChanged}" /></td>
						
							<td><g:formatDate date="${forgotPasswordInstance.expiredTime}" /></td>
						
							<td class="link">
								<g:link action="show" id="${forgotPasswordInstance.id}" class="btn btn-small">Show &raquo;</g:link>
							</td>
						</tr>
					</g:each>
					</tbody>
				</table>
				<div class="pagination">
					<bootstrap:paginate total="${forgotPasswordInstanceTotal}" />
				</div>
			</div>

		</div>
	</body>
</html>
