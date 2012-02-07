<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<c:url value="/resources/css/yourphotos.css" />" rel="stylesheet" type="text/css" />
<title>Listado de fotos</title>
<style type="text/css">

html, body {
	background-color: white; 
	
	color: #2B3956;
	font-family: Verdana, Arial, Sans-Serif;
	font-size: 11px;
	text-align: left;
}
/* Pagination DIV */
#pg {
	width: 700px;
	background-color: #FFFFFF;
	
	text-align: center;
	font-size: 10px;
	
	margin-bottom: 5px;
	
	padding: 10px;
}

/* Pagination Link */

#pg a {
	font-size: 10px;
	text-decoration: none;
	color: #000000;
	border: 1px solid #83A0C1;
	padding: 3px;
	-moz-border-radius: 3px;
}

#pg a:hover {
	font-size: 10px;
	text-decoration: none;
	color: #000000;
	border: 1px solid #000000;
	background-color: white;
	padding: 3px;
	-moz-border-radius: 3px;
}

/* Pagination Current Page */

#pg a.current {
	font-size: 10px;
	text-decoration: none;
	font-weight: bold;
	color: white;
	border: 1px solid #0D62C3;
	background-color: #0D62C3;
	padding: 3px;
	-moz-border-radius: 3px;
}

/* Pagination Disabled Page */

#pg span.disabled {
	font-size: 10px;
	text-decoration: none;
	color: #C6C7C7;
	border: 1px solid #C6C7C7;
	background-color: white;
	padding: 3px;
	-moz-border-radius: 3px;
}

ul{
    margin: 0 auto;
    padding: 0;
}

</style>
</head>
<body>
<c:url value="/" var="inicio" scope="page" />
<p><a href="${inicio }">Inicio</a></p>
<h2>Listado de fotos</h2>
<br />
<table height="200px" width="900px" cellpadding="3" cellspacing="3"> 
	<thead>
		<tr>
			<th>ID</th>
			<th>Comentario</th>
			<th>Foto</th>
		</tr>
	</thead>
	<c:forEach items="${photos.content }" var="p">
	<tbody>
		<tr>
			<td><c:out value="${p.id }" /></td>
			<td ><c:out value="${p.comment }" /></td>
			<c:url var="photoUrl" value="/photos/image">
				<c:param name="id" value="${p.id }" />
			</c:url>
			<td><img alt="Foto" src="${photoUrl}" height="50" width="50" /> </td>
		</tr>
	</tbody>
	</c:forEach>
</table>
<!-- INICIO PAGINCACION -->
<c:url var="photosUrl" value="/photos/show" />
<c:set var="photosUrlStr" value="${photosUrl }" />
<div id="pg">
	<c:choose>
		<c:when test="${photos.firstPage eq false }">		
			<a href="${photosUrlStr}?page=${photos.number - 1 }">&laquo; Anterior</a>
		</c:when>
		<c:otherwise>
			<span class="disabled">&laquo; Anterior</span>
		</c:otherwise>
	</c:choose>
	<c:if test="${(photos.totalPages - 1) > 0 }">
		
		<c:forEach begin="0" end="${photos.totalPages - 1 }" varStatus="status" var="i">
			<c:choose>
			<c:when test="${i eq photos.number }">				
				<a href="${photosUrlStr}?page=${i }" class="current">${i + 1}</a>
			</c:when>
			<c:otherwise>
				<a href="${photosUrlStr}?page=${i }">${i + 1}</a>
			</c:otherwise>
			</c:choose>
		</c:forEach>		
	
	</c:if>
	<c:choose>
		<c:when test="${photos.lastPage eq false }">
			<a href="${photosUrlStr}?page=${photos.number + 1 }">Siguiente &raquo;</a>
		</c:when>
		<c:otherwise>
			<span class="disabled">Siguiente &raquo;</span>
		</c:otherwise>
	</c:choose>

</div>
<!-- FIN PAGINCACION -->
<c:url value="/j_spring_security_logout" var="logout" scope="page" />
<p><a href="${logout }">Logout</a></p>
</body>
</html>