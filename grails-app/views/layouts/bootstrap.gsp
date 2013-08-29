<%@ page import="org.codehaus.groovy.grails.web.servlet.GrailsApplicationAttributes" %>
<!doctype html>
<html lang="en">
	<head>
		<meta charset="utf-8">
		<title>Cinemark</title>
		<meta name="description" content="">
		<meta name="author" content="">

		<meta name="viewport" content="initial-scale = 1.0">

		<!-- Le HTML5 shim, for IE6-8 support of HTML elements -->
		<!--[if lt IE 9]>
			<script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
		<![endif]-->

		<r:require modules="scaffolding"/>

		<!-- Le fav and touch icons -->
		<link rel="shortcut icon" href="${resource(dir: 'images', file: 'favicon.ico')}" type="image/x-icon">
		<link rel="apple-touch-icon" href="${resource(dir: 'images', file: 'apple-touch-icon.png')}">
		<link rel="apple-touch-icon" sizes="72x72" href="${resource(dir: 'images', file: 'apple-touch-icon-72x72.png')}">
		<link rel="apple-touch-icon" sizes="114x114" href="${resource(dir: 'images', file: 'apple-touch-icon-114x114.png')}">

		<g:layoutHead/>
		<r:layoutResources/>
	</head>

	<body>

		<nav class="navbar navbar-fixed-top">
			<div class="navbar-inner">
				<div class="container-fluid">
					
					<a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
					</a>
					<sec:ifAllGranted roles="ROLE_ADMIN">
					<a class="brand" href="${createLink(uri: '/')}">Cinemark</a>

					<div class="nav-collapse">
						<ul class="nav">							
							<li	<%= request.forwardURI == "${createLink(uri: '/_ssl_/log_ad')}" ? ' class="active"' : '' %>><a href="${createLink(uri: '/_ssl_/log_ad')}">Home</a></li>
							<li <%=entityName == "User" ? ' class="active"' : ''%>><g:link controller="User" action="list">${message(code: 'user.label', default: 'User')}</g:link></li>
							<li <%=entityName == "Theater" ? ' class="active"' : ''%>><g:link controller="Theater" action="list">${message(code: 'theater.label', default: 'Theater')}</g:link></li>
							<li <%=entityName == "Cinema" ? ' class="active"' : ''%>><g:link controller="Cinema" action="list">${message(code: 'cinema.label', default: 'Cinema')}</g:link></li>
							<li <%=entityName == "SeatSection" ? ' class="active"' : ''%>><g:link controller="SeatSection" action="list">${message(code: 'seatSection.label', default: 'SeatSection')}</g:link></li>
							<li <%=entityName == "ShowTimes" ? ' class="active"' : ''%>><g:link controller="ShowTimes" action="list">${message(code: 'showTimes.label', default: 'Show Times')}</g:link></li>
							<li <%=entityName == "Movie" ? ' class="active"' : ''%>><g:link controller="Movie" action="list">${message(code: 'movie.label', default: 'Movie')}</g:link></li>
							<li <%=entityName == "Schedules" ? ' class="active"' : ''%>><g:link controller="Schedules" action="list">${message(code: 'schedules.label', default: 'Schedules')}</g:link></li>
						</ul>
					</div>
				</sec:ifAllGranted>
				</div>
			</div>
		</nav>

		<div class="container-fluid">
			<g:layoutBody/>

			<hr>

			<footer>
				<p>&copy; Company 2011</p>
			</footer>
		</div>

		<r:layoutResources/>

	</body>
</html>