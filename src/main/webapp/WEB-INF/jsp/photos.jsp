<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<c:url value="/resources/css/yourphotos.css" />" rel="stylesheet" type="text/css" />
<title>Listado de fotos</title>
</head>
<body>
<c:forEach items="${photos.content }" var="p">
<c:out value="${p.comment }" />
<c:out value="${p.id }" />
<c:url var="photoUrl" value="/photos/image">
	<c:param name="id" value="${p.id }" />
</c:url>
<img alt="Foto" src="${photoUrl}" height="50" width="50" /> 
</c:forEach>

<!-- INICIO PAGINCACION -->
<c:url var="photosUrl" value="/photos/show" />
<c:set var="photosUrlStr" value="${photosUrl }" />
<div class="pagbox mark">
<ul class="paginacion">
	<c:if test="${photos.firstPage eq false }">
		<li title="Ir a la primera página" class="primero">
			<a name="paginaIR" href="${photosUrlStr}?page=0"><span>Primero</span></a>
		</li>
		<li title="Ir a la página anterior" class="anterior">
			<a name="paginaIR" href="${photosUrlStr}?page=${photos.number - 1 }"><span>Anterior</span></a>
		</li>
	</c:if>
	<c:if test="${(photos.totalPages - 1) > 0 }">
		<li class="paginas">
			<c:forEach begin="0" end="${photos.totalPages - 1 }" varStatus="status" var="i">
				<c:choose>
				<c:when test="${i eq photos.number }">				
					<a name="paginaIR" href="${photosUrlStr}?page=${i }" class="active"><span>${i + 1}</span></a>
				</c:when>
				<c:otherwise>
					<a name="paginaIR" href="${photosUrlStr}?page=${i }"><span>${i + 1}</span></a>
				</c:otherwise>
				</c:choose>
			</c:forEach>		
		</li>
	</c:if>
	<c:if test="${photos.lastPage eq false }">
		<li title="Ir a la página siguiente" class="siguiente">
			<a name="paginaIR" href="${photosUrlStr}?page=${photos.number + 1 }"><span>Siguiente</span></a>
		</li>
		<li title="Ir a la última página" class="ultimo">
			<a name="paginaIR" href="${photosUrlStr}?page=${photos.totalPages - 1}"><span>Último</span></a>
		</li>
	</c:if>
</ul>
</div>
<!-- FIN PAGINCACION -->
<c:url value="/j_spring_security_logout" var="logout" scope="page" />
<p><a href="${logout }">Logout</a></p>
</body>
</html>