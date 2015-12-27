<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<spring:message code="signup.page.title" var="title" />
<jsp:include page="/WEB-INF/views/fragments/header.jsp">
	<jsp:param name="title" value="${title}" />
</jsp:include>

<form:form name="signupform" action="${pageContext.request.contextPath}/signup"
	commandName="signupForm" method="post"
	class="form-narrow form-horizontal">
	<fieldset>
		<legend>
			<spring:message code="signup.form.title" />
		</legend>
		<div class="form-group ${requestScope['org.springframework.validation.BindingResult.signupForm'].hasFieldErrors('email') ? 'has-error' : ''}">
			<label for="email" class="col-lg-2 control-label"><spring:message
					code="signup.form.email" /></label>
			<div class="col-lg-10">
				<form:input path="email" id="email" class="form-control" />
				<form:errors path="email" class="help-block" />
				
			</div>
		</div>
		<div class="form-group ${requestScope['org.springframework.validation.BindingResult.signupForm'].hasFieldErrors('password') ? 'has-error' : ''}">
			<label for="password" class="col-lg-2 control-label"><spring:message
					code="signup.form.password" /></label>
			<div class="col-lg-10">
				<form:password path="password" id="password" class="form-control" />
				<form:errors path="password" class="help-block" />
			</div>
		</div>
		<div class="form-group">
			<div class="col-lg-offset-2 col-lg-10">
				<button type="submit" class="btn btn-default"
					title="<spring:message code="signup.form.submit" />">
					<spring:message code="signup.form.submit" />
				</button>
			</div>
		</div>
		<div class="form-group">
			<div class="col-lg-offset-2 col-lg-10">
				<p>
					<spring:message code="signup.form.info" />
					<a href="${pageContext.request.contextPath}/signin"
						title="<spring:message
							code="signup.form.signin" />"><spring:message
							code="signup.form.signin" /></a>
				</p>
			</div>
		</div>
	</fieldset>
</form:form>

<script type="text/javascript">document.signupform.email.focus();</script>

<jsp:include page="/WEB-INF/views/fragments/footer.jsp" />