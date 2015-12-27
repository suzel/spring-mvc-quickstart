<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<spring:message code="home.title" var="title" />
<jsp:include page="/WEB-INF/views/fragments/header.jsp">
	<jsp:param name="title" value="${title}" />
</jsp:include>

<jsp:include page="/WEB-INF/views/fragments/navbar.jsp" />

<!-- Welcome to the Spring MVC Quickstart application! -->

<div class="container">
	<div class="text-center">
		<h1>
			<spring:message code="project.name" />
		</h1>
		<p class="lead">
			<spring:message code="home.welcome" />
		</p>
		<p>
			<a href="${pageContext.request.contextPath}/signup"
				title="<spring:message code="button.signup" />"
				class="btn btn-success btn-lg"><spring:message
					code="button.signup" /></a>
		</p>
	</div>
</div>


<jsp:include page="/WEB-INF/views/fragments/footer.jsp" />