<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<spring:message code="signin.page.title" var="title" />
<jsp:include page="/WEB-INF/views/fragments/header.jsp">
	<jsp:param name="title" value="${title}" />
</jsp:include>

<form name="signinform" class="form-narrow form-horizontal" action="${pageContext.request.contextPath}/authenticate" method="post">

	<c:if test="${param.error != null}">
		<div class="alert alert-danger alert-dismissible" role="alert">
			<button type="button" class="close" data-dismiss="alert"
				aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
			<spring:message code="signin.form.tryagain" />
		</div>
	</c:if>

	<fieldset>
		<legend>
			<spring:message code="signin.form.title" />
		</legend>
		<div class="form-group">
			<label for="inputEmail" class="col-lg-2 control-label"><spring:message
					code="signin.form.email" /></label>
			<div class="col-lg-10">
				<input type="text" class="form-control" id="inputEmail"
					placeholder="<spring:message code="signin.form.email.placeholder"/>"
					name="username" />
			</div>
		</div>
		<div class="form-group">
			<label for="inputPassword" class="col-lg-2 control-label"><spring:message
					code="signin.form.password" /></label>
			<div class="col-lg-10">
				<input type="password" class="form-control" id="inputPassword"
					placeholder="<spring:message code="signin.form.password.placeholder"/>"
					name="password" />
			</div>
		</div>
		<div class="form-group">
			<div class="col-lg-offset-2 col-lg-10">
				<div class="checkbox">
					<label><input type="checkbox"
						name="_spring_security_remember_me" /> <spring:message
							code="signin.form.rememberme" />
					</label>
				</div>
			</div>
		</div>
		<div class="form-group">
			<div class="col-lg-offset-2 col-lg-10">
				<button type="submit" class="btn btn-default">
					<spring:message code="signin.form.submit" />
				</button>
			</div>
		</div>
		<div class="form-group">
			<div class="col-lg-offset-2 col-lg-10">
				<p>
					<spring:message code="signin.form.info" />
					<a href="${pageContext.request.contextPath}/signup"><spring:message
							code="signin.form.signup" /></a>
				</p>
			</div>
		</div>
	</fieldset>
	<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
</form>

<script type="text/javascript">document.signinform.username.focus();</script>

<jsp:include page="/WEB-INF/views/fragments/footer.jsp" />