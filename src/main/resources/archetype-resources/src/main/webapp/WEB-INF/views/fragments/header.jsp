<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html lang="${pageContext.response.locale}">
<head>
	<meta charset="UTF-8">
	<title>${param.title} | <spring:message code="project.name" /></title>
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="keywords" content="">
	<meta name="description" content="">
	<meta name="robots" content="all,index,follow">
	<meta name="googlebot" content="index,follow">
	<meta name="google-site-verification" content="">
	<meta name="msvalidate.01" content="">
	<meta name="alexaVerifyID" content="">
	<meta name="yandex-verification" content="">
	<meta name="HandheldFriendly" content="True">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<jsp:include page="/WEB-INF/views/fragments/styles.jsp" />
</head>
<body>