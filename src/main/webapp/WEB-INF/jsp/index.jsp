<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
	<meta name="google-site-verification" content="6FM5jFHlYo41eEp4rvVGk3H9jsf3MibcEuTwGGTeTIQ" />
</head>
<body>
<h2>Bienvenido 
<sec:authorize access="isAuthenticated()">
	<sec:authentication property="principal.username" />
</sec:authorize>
</h2>
<sec:authorize access="isAuthenticated()">
	<c:url value="/j_spring_security_logout" var="logout" scope="page" />
	<p><a href="${logout }">Logout</a></p>
</sec:authorize>
<c:url value="/photos/show" var="pshow" scope="page" />
<c:url value="/photos/upload" var="pupload" scope="page" />
<ol>
	<li><a href="${pupload }">Subir una foto</a></li>
	<li><a href="${pshow }">Listar fotos</a></li>
</ol>
</body>
</html>
