<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Subir una foto</title>
</head>
<body>
<c:url value="/" var="inicio" scope="page" />
<p><a href="${inicio }">Inicio</a></p>
<c:out value="${requestScope.error }" />
<c:url var="uploadUrl" value="/photos/upload" />
<h2>Subir una foto</h2>
<form:form action="${uploadUrl }" commandName="photo" modelAttribute="photo" method="post" enctype="multipart/form-data">
	<fieldset>
		<legend class="leyenda">Subir una foto</legend>
		<form:errors path="*" />
		<p>
            <form:label path="comment">Comentario:</form:label>
		    <form:textarea path="comment" cols="100" rows="2"  />
		    <form:errors path="comment" />
		</p>
        <p>
            <form:label path="photo">Foto:</form:label>
		    <input type="file" name="photo"/>
		    <form:errors path="photo" />
        </p>
        <p align="center">
		    <input type="submit" value="Subir" class="boton" />
        </p>
	</fieldset>
</form:form>
<c:url value="/j_spring_security_logout" var="logout" scope="page" />
<p><a href="${logout }">Logout</a></p>
</body>
</html>