<html>
    <head>
        <title><g:layoutTitle default="Grails" /></title>
        <link rel="stylesheet" href="${createLinkTo(dir:'css',file:'main.css')}" />
        <link rel="shortcut icon" href="${createLinkTo(dir:'images',file:'favicon.ico')}" type="image/x-icon" />
        <g:layoutHead />
        <g:javascript library="application" />				
    </head>
    <body>
        <div id="spinner" class="spinner" style="display:none;">
            <img src="${createLinkTo(dir:'images',file:'spinner.gif')}" alt="Spinner" />
        </div>	
        <div class="logo"><img src="${createLinkTo(dir:'images',file:'grails_plugins_logo.png')}" alt="Grails Plugins" /></div>	
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link controller="plugin" class="list" action="list">List Plugins</g:link></span>
            <jsec:hasRole name="Administrator">
	            <span class="menuButton"><g:link controller="plugin" class="create" action="create">New Plugin</g:link></span>
				      <span class="menuButton"><g:link controller="plugin" class="create" action="remoteList">Update List</g:link></span>
	          </jsec:hasRole>
			      <span class="menuButton"><g:link class="list" controller="searchable">Search Plugins</g:link></span>
     				<jsec:isLoggedIn>
		  				<span class="menuButton">Logged in as: <jsec:principal/> (<g:link controller="auth" action="signOut">sign out</g:link>)</span>
						</jsec:isLoggedIn>
						<jsec:isNotLoggedIn>
							<span class="menuButton">Please <g:link controller="auth" action="login">Login</g:link> 
								or <g:link controller="auth" action="register">Register</g:link></span>
						</jsec:isNotLoggedIn>
        </div>
				
				<div id="page">
					<div id="content">
						<g:layoutBody />
					</div>
					
					<div id="sidebar">
						<g:render template="/sidebar" />
					</div>
				
					<div id="footer">
						<g:render template="/footer" />
					</div>
				</div>
    </body>	
</html>