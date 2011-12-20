var serverUrl = 'http://yourphotos.herokuapp.com/maps';
var q = jQuery.noConflict();
var mapData = null;
var map;
function initializeData() {
	q.getJSON(serverUrl + '?lat=' + q("#centerLatitude").text() + '&lng=' + q("#centerLongitude").text(), function(data) {
		mapData = data;
		initialize();
	});
}	
function generateInfoWindow(marker) {
	return  "<div>" +
			"<span>" + marker.details.numSuggestions + "</span>" +
			"<span>" + marker.poblacion + " (" + marker.provincia + ")" + "</span>" +
			"<span>Categoría: " + marker.categoria + "</span>" +
			"</div>";
}
function findMarker(markers, latLng) {
	for ( var i = 0; i < markers.length; i++) {
		if (markers[i].getPosition().equals(latLng)) return markers[i];
	}
}
function findPoblacion(latLng) {
	var ll = null;
	for ( var i = 0; i < mapData.markers.length; i++) {
		ll = new google.maps.LatLng(mapData.markers[i].lat, mapData.markers[i].lng);
		if (latLng.equals(ll)) return mapData.markers[i];
	}
}	
function initialize() {
	var myOptions = {
		zoom: 13,
		center: new google.maps.LatLng(mapData.center.lat, mapData.center.lng),
		mapTypeId: google.maps.MapTypeId.ROADMAP
	};
	// creamos el mapa
  	map = new google.maps.Map(document.getElementById('map_canvas'), myOptions);
  	// creamos los marcadores
  	var markers = [];
  	var infow = new google.maps.InfoWindow({
		content: ""
	});
  	var marker = null;
	for ( var i = 0; i < mapData.markers.length; i++) {
		marker = new google.maps.Marker({
			  position: new google.maps.LatLng(mapData.markers[i].lat, mapData.markers[i].lng),
			  map: map,
			  title: mapData.markers[i].poblacion,
			  icon: 'http://bit.ly/vCek8s'
		});			
		markers.push(marker);
		google.maps.event.addListener(marker, 'click', function(event) {
			var markerClicked = findMarker(markers, event.latLng);
			var poblacionMarker = findPoblacion(event.latLng);
			infow.setContent(generateInfoWindow(poblacionMarker));
			infow.open(map, markerClicked);
		});
	}
}

google.maps.event.addDomListener(window, 'load', initializeData);