<!doctype html>
<html>
	<head>
		<meta name="layout" content="bootstrap"/>
		<title>Cinemark</title>
	</head>

	<body>
		<div class="row-fluid">
			<aside id="application-status" class="span2">
			<sec:ifAllGranted roles="ROLE_ADMIN">
				<div class="well sidebar-nav">
					<h5>Menu</h5>
						<ul class="nav">		
											
							<li<%= request.forwardURI == "${createLink(uri: '/_ssl_/log_ad')}" ? ' class="active"' : '' %>><a href="${createLink(uri: '/_ssl_/log_ad')}">Home</a></li>
							<li <%=entityName == "User" ? ' class="active"' : ''%>><g:link controller="User" action="list">${message(code: 'user.label', default: 'User')}</g:link></li>
							<li <%=entityName == "Theater" ? ' class="active"' : ''%>><g:link controller="Theater" action="list">${message(code: 'theater.label', default: 'Theater')}</g:link></li>
							<li <%=entityName == "Cinema" ? ' class="active"' : ''%>><g:link controller="Cinema" action="list">${message(code: 'cinema.label', default: 'Cinema')}</g:link></li>
							<li <%=entityName == "SeatSection" ? ' class="active"' : ''%>><g:link controller="SeatSection" action="list">${message(code: 'seatSection.label', default: 'Seat Section')}</g:link></li>
							<li <%=entityName == "ShowTime" ? ' class="active"' : ''%>><g:link controller="ShowTime" action="list">${message(code: 'showTimes.label', default: 'Show Times')}</g:link></li>
							<li <%=entityName == "Movie" ? ' class="active"' : ''%>><g:link controller="Movie" action="list">${message(code: 'movie.label', default: 'Movie')}</g:link></li>
							<li <%=entityName == "Schedule" ? ' class="active"' : ''%>><g:link controller="Schedule" action="list">${message(code: 'schedules.label', default: 'Schedules')}</g:link></li>
							<li<%=entityName == "Logout" ? ' class="active"' : ''%>><g:link	controller="Logout">Logout</g:link></li>
						</ul>
					
				</div>
			</sec:ifAllGranted>
			</aside>
</div>
				
	</body>
</html>
