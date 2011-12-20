<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Administración</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
</head>
<body>
    <c:if test="${not empty param.login_error}">
    	<p class="">
        Your login attempt was not successful, try again.<br/><br/>
        Reason: <c:out value="${sessionScope.SPRING_SECURITY_LAST_EXCEPTION.message}"/>.
        </p>
    </c:if>
    
	<div id="div_centrado">
    <div id="div_login">
    	<form id="loginForm" action="<c:url value="/j_spring_security_check" />" method="POST">
			<table cellpadding="2" cellspacing="0" align="center">
				<tr>
					<td colspan="2"><img src="<c:url value="/resources/images/MadridPlaces.png" />"
						alt="MadridPlaces" border="0" /></td>
				</tr>
				<tr>
					<td colspan="2">&nbsp;</td>
				</tr>
				<tr>
					<td align="right"><label for="username">Nombre de
							usuario:</label></td>
					<td><input type="text" name="j_username" id="username"
						value="${sessionScope.SPRING_SECURITY_LAST_USERNAME}"
						class="formulario" /></td>
				</tr>
				<tr>
					<td align="right"><label for="password">Password:</label></td>
					<td><input type="password" name="j_password" id="password"
						value="" class="formulario" /></td>
				</tr>
				<tr>
					<td align="right"><input type="checkbox"
						name="_spring_security_remember_me" id="remember_me" /></td>
					<td><label for="remember_me">Recordarme en este
							dispositivo</label></td>
				</tr>
				<tr>
					<td colspan="2">&nbsp;</td>
				</tr>
				<tr>
					<td colspan="2" align="center"><input type="submit"
						value="Login" class="boton"></td>
				</tr>
			</table>
		</form>
	</div>
	</div>
</body>
</html>