<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>Google Maps JavaScript API v3 Example: Map Simple</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=no">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<script type="text/javascript" src="http://maps.google.com/maps/api/js?sensor=false"></script>
<script type="text/javascript" src="<c:url value="/resources/js/jquery-1.7.1.min.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/js/yourphotos.js" />"></script>
<style type="text/css">
  html, body, #map_canvas {
    margin: 0;
    padding: 0;
    height: 100%;
  }
</style>
</head>
<body>
	<span id="centerLatitude" style="display:none;"><c:out value="${centerLatitude}" /></span>
	<span id="centerLongitude" style="display:none;"><c:out value="${centerLongitude}" /></span>
	<div id="map_canvas"></div>
</body>
</html>