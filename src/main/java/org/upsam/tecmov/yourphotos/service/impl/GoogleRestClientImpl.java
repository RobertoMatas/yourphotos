package org.upsam.tecmov.yourphotos.service.impl;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.upsam.tecmov.yourphotos.controller.form.LocationForm;
import org.upsam.tecmov.yourphotos.controller.view.PoblacionWithDetailsView;
import org.upsam.tecmov.yourphotos.service.GoogleRestClient;
import org.upsam.tecmov.yourphotos.service.LocationComponents;

@Service
public class GoogleRestClientImpl implements GoogleRestClient {

	private static final String ICON_MARKER = "http://bit.ly/vCek8s";
	
	private static final String ICON_MARKER_WITH_STAR = "http://bit.ly/vSDHgP";

	private static final String LATLNG_PARAM = "&latlng=";

	protected static Logger logger = Logger.getLogger(GoogleRestClientImpl.class);
	/**
	 * URL de geolocalizacion de Google
	 */
	private static final String BASE_GEOCODE_URL = "http://maps.google.com/maps/api/geocode/json?sensor=false";
	/**
	 * URL del API Directions de Google
	 */
	private static final String BASE_DIRECTIONS_URL = "http://maps.google.com/maps/api/directions/json?sensor=false&mode=walking";
	/**
	 * URL del API de Static Maps de Google
	 */
	private static final String BASE_MAPS_URL = "http://maps.google.com/maps/api/staticmap?sensor=false";

	/**
	 * Mapper de Json a Objetos
	 */
	private ObjectMapper mapper;
	/**
	 * Template para realizar llamadas REST
	 */
	private RestTemplate restTemplate;

	/**
	 * 
	 */
	public GoogleRestClientImpl() {
		super();
		this.mapper = new ObjectMapper();
		this.restTemplate = new RestTemplate();
	}

	@Override
	public LocationComponents getLocationComponents(LocationForm coordenadas) {
		URI uri = null;
		try {
			uri = new URI(BASE_GEOCODE_URL + LATLNG_PARAM + coordenadas.getLat() + ","
					+ coordenadas.getLng());

		} catch (URISyntaxException e) {
			logger.error("URI para llamar al servicio de Google mal formada", e);
		}
		try {
			String[] components = extractZipCodeAndLocality(restTemplate.getForObject(uri,
					String.class));
			return new LocationComponents(components[1], components[0]);

		} catch (JsonParseException e) {
			logger.error("Error al analizar el Json devuelto por Google", e);
		} catch (JsonMappingException e) {
			logger.error("Error al analizar el Json devuelto por Google", e);
		} catch (RestClientException e) {
			logger.error("Error al llamar al servicio de Google", e);
		} catch (IOException e) {
			logger.error("Error al analizar el Json devuelto por Google", e);
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer getDistance(LocationForm origin, LocationForm destination) {
		URI uri = null;
		try {
			uri = new URI(BASE_DIRECTIONS_URL + "&origin=" + origin.getLat() + ","
					+ origin.getLng() + "&destination=" + destination.getLat() + ","
					+ destination.getLng());

		} catch (URISyntaxException e) {
			logger.error("URI para llamar al servicio de Google mal formada", e);
		}
		try {
			logger.debug("getDistance() -> URI: " + uri);
			return extractDistance(restTemplate.getForObject(uri, String.class));

		} catch (JsonParseException e) {
			logger.error("Error al analizar el Json devuelto por Google", e);
		} catch (JsonMappingException e) {
			logger.error("Error al analizar el Json devuelto por Google", e);
		} catch (RestClientException e) {
			logger.error("Error al llamar al servicio de Google", e);
		} catch (IOException e) {
			logger.error("Error al analizar el Json devuelto por Google", e);
		}
		return null;
	}

	private Integer extractDistance(String json) throws JsonParseException, JsonMappingException,
			IOException {
		JsonNode rootNode = mapper.readValue(new StringReader(json), JsonNode.class);
		JsonNode legs = rootNode.findValue("legs");
		JsonNode distance = legs.findValue("distance");
		JsonNode value = distance.findValue("value");
		return value.getIntValue();
	}

	private String[] extractZipCodeAndLocality(String json) throws JsonParseException,
			JsonMappingException, IOException {
		boolean zipCodeExtracted = false;
		boolean localityExtracted = false;
		String[] components = new String[2];
		JsonNode rootNode = mapper.readValue(new StringReader(json), JsonNode.class);
		List<JsonNode> addressComponents = rootNode.findValues("address_components");
		for (JsonNode jsonNode : addressComponents) {
			JsonNode types = jsonNode.findValue("types");
			if (types.isArray()) {
				for (JsonNode type : types) {
					if (type.getValueAsText().equalsIgnoreCase("postal_code")) {
						components[0] = jsonNode.findValue("long_name").getTextValue();
						zipCodeExtracted = true;
					}
					if (type.getValueAsText().contains("locality")) {
						components[1] = jsonNode.findValue("short_name").getTextValue();
						localityExtracted = true;
					}
					if (localityExtracted && zipCodeExtracted)
						return components;
				}
			}
		}
		return null;
	}

	@Override
	public InputStream getMap(LocationForm coordenadas, List<PoblacionWithDetailsView> markers) {
		String uri = null;
		try {
			uri = BASE_MAPS_URL + "&size=90x90&center=" + coordenadas.getLat()
					+ "," + coordenadas.getLng() + generateMarkers(markers);
			logger.debug("Generada url: " + uri);
		} catch (UnsupportedEncodingException e) {
			logger.error("Error al hacer un encode de la url del icono", e);
		}

		try {
			if (uri != null) {
				byte[] bytes = restTemplate.getForObject(uri, byte[].class);
				if (bytes != null) {
					return new BufferedInputStream(new ByteArrayInputStream(bytes));
				}
			}
		} catch (RestClientException e) {
			logger.error("Error al llamar al servicio de Google", e);
		}
		return null;

	}

	private String generateMarkers(List<PoblacionWithDetailsView> markers)
			throws UnsupportedEncodingException {
		if (markers == null || markers.isEmpty()) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		for (PoblacionWithDetailsView poblacionWithDetailsView : markers) {
			sb.append("&markers=icon:");
			if (poblacionWithDetailsView.getCategoria() >= 4) {
				sb.append(ICON_MARKER_WITH_STAR);
			} else {
				sb.append(ICON_MARKER);
			}
			sb.append("|" + poblacionWithDetailsView.getLat() + ","
					+ poblacionWithDetailsView.getLng());
		}
		return sb.toString();
	}
}
