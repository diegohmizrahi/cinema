<!doctype html>
<html>
	<head>
		<meta name="layout" content="bootstrap"/>
		<title>Cinemark</title>
	</head>

	<body>
		<div class="row-fluid">
			<aside id="application-status" class="span2">
				<div class="well sidebar-nav">
					<h5>Menu</h5>
						<ul class="nav">							
							<li<%= request.forwardURI == "${createLink(uri: '/_ssl_/log_ad')}" ? ' class="active"' : '' %>><a href="${createLink(uri: '/_ssl_/log_ad')}">Home</a></li>
							<li<%=entityName == "User" ? ' class="active"' : ''%>><g:link 
									controller="User" action="list">Administrators</g:link></li>
									<li<%=entityName == "Player" ? ' class="active"' : ''%>><g:link 
									controller="Player" action="list">Players</g:link></li>
							<li<%=entityName == "Game" ? ' class="active"' : ''%>><g:link 
									controller="Game" action="list">Games</g:link></li>
									<li<%=entityName == "Phase" ? ' class="active"' : ''%>><g:link 
									controller="Phase" action="list">Phases</g:link></li>
									<li<%=entityName == "Item" ? ' class="active"' : ''%>><g:link 
									controller="Item" action="list">Items</g:link></li>
									<li<%=entityName == "PlayerItem" ? ' class="active"' : ''%>><g:link 
									controller="PlayerItem" action="list">Player Items</g:link></li>
									<li<%=entityName == "Logout" ? ' class="active"' : ''%>><g:link 
									controller="Logout">Logout</g:link></li>
									
						</ul>
					
				</div>
			</aside>
</div>
				
	</body>
</html>
