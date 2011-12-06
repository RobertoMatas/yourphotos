<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Listado de fotos</title>
</head>
<body>
<c:forEach items="${photos }" var="p">
<c:out value="${p.comment }" />
<c:out value="${p.id }" />
<c:url var="photoUrl" value="/photos/image">
	<c:param name="id" value="${p.id }" />
</c:url>
<img alt="Foto" src="${photoUrl}" height="50" width="50" />
 
</c:forEach>
</body>
</html>