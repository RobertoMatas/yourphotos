<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Resumen</title>
<!--Load the AJAX API-->
<script type="text/javascript" src="https://www.google.com/jsapi"></script>
<script type="text/javascript" src="<c:url value="/resources/js/jquery-1.7.1.min.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/js/yourphotos-table.js" />"></script>
</head>
<body>
<c:url value="/" var="inicio" scope="page" />
<p><a href="${inicio }">Inicio</a></p>
<h2>Resumen</h2>
<div id="chart_table"></div>
<c:url value="/j_spring_security_logout" var="logout" scope="page" />
<p><a href="${logout }">Logout</a></p>
</body>
</html>