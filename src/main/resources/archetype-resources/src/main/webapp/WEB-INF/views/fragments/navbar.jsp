<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<spring:message code="project.name" var="brand" />

<nav class="navbar navbar-inverse navbar-fixed-top">
	<div class="container">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#navbar" aria-expanded="false"
				aria-controls="navbar">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="${pageContext.request.contextPath}/" title="${brand}">${brand}</a>
		</div>
		<div id="navbar" class="navbar-collapse collapse">
			<ul class="nav navbar-nav">
				<!-- <li><a href="#">Menu</a></li> -->
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<sec:authorize ifNotGranted="ROLE_USER">
					<li><a href="${pageContext.request.contextPath}/signin" title="<spring:message code="navbar.signin" />"><spring:message code="navbar.signin" /></a></li>
				</sec:authorize>
				<sec:authorize ifAllGranted="ROLE_USER">
					<li class="dropdown"><a href="#"
						title="<sec:authentication property="principal.username"/>"
						class="dropdown-toggle profile-image" data-toggle="dropdown">
							<!-- 
							<img
							src="https://s.gravatar.com/avatar/11e92c7a963fec31d5845055936b27a6?s=30"
							class="img-circle special-img"> --> <sec:authentication
								property="principal.username" /><b class="caret"></b>
					</a>
						<ul class="dropdown-menu">
							<li><a href="#" title="<spring:message
										code="navbar.settings" />"><i class="fa fa-cog"></i> <spring:message
										code="navbar.settings" /></a></li>
							<li class="divider"></li>
							<li><a href="${pageContext.request.contextPath}/logout"
								title="<spring:message
										code="logout.menu.exit" />"><i class="fa fa-sign-out"></i><spring:message
										code="logout.menu.exit" /></a></li>
						</ul></li>
				</sec:authorize>
			</ul>
		</div>
	</div>
</nav>