<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>Google Maps JavaScript API v3 Example: Map Simple</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=no">
<meta charset="UTF-8">
<script type="text/javascript" src="http://maps.google.com/maps/api/js?sensor=false"></script>
<script type="text/javascript" src="<c:url value="/resources/js/jquery-1.7.1.min.js" />"></script>
<style type="text/css">
  html, body, #map_canvas {
    margin: 0;
    padding: 0;
    height: 100%;
  }
</style>
<script type="text/javascript">
	var q = jQuery.noConflict();
	var mapData;
	var map;
	q(document).ready(function() {
		q.getJSON('http://yourphotos.herokuapp.com/yourphotos/maps?lat=40.4177664&lng=-3.5271783', function(data) {
			mapData = data;
		    console.log(mapData);
		    map.setCenter(new google.maps.LatLng(mapData.center.lat, mapData.center.lng));
		});
		
	});
	function initialize() {
	  var myOptions = {
		zoom: 13,
		center: new google.maps.LatLng(-34.397, 150.644),
		mapTypeId: google.maps.MapTypeId.ROADMAP
	  };
	  map = new google.maps.Map(document.getElementById('map_canvas'),
		  myOptions);
	}
	
	google.maps.event.addDomListener(window, 'load', initialize);
	
</script>
</head>
<body>
	<div id="map_canvas"></div>
</body>
</html>