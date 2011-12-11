package org.upsam.tecmov.yourphotos.service.impl;

import java.io.IOException;
import java.io.StringReader;
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
import org.upsam.tecmov.yourphotos.service.GoogleRestClient;

@Service
public class GoogleRestClientImpl implements GoogleRestClient {
	
	protected static Logger  logger = Logger.getLogger(GoogleRestClientImpl.class); 
	
	private static final String BASE_GEOCODE_URL = "http://maps.google.com/maps/api/geocode/json?sensor=false";
	
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

	public String getPostalCode(LocationForm coordenadas) {
		URI uri = null;
		try {
			uri = new URI(BASE_GEOCODE_URL + "&latlng=" + coordenadas.getLat() + "," + coordenadas.getLng());
			
		} catch (URISyntaxException e) {
			logger.error("URI para llamar al servicio de Google mal formada", e);
		}
		try {
			return extractZipCode(restTemplate.getForObject(uri, String.class));
			
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
	
	private String extractZipCode(String json) throws JsonParseException, JsonMappingException, IOException {
		JsonNode rootNode = mapper.readValue(new StringReader(json), JsonNode.class);
		List<JsonNode> addressComponents = rootNode.findValues("address_components");
		for (JsonNode jsonNode : addressComponents) {
			JsonNode types = jsonNode.findValue("types");
			if (types.isArray()) {
				for (JsonNode type : types) {
					if (type.getValueAsText().equalsIgnoreCase("postal_code")) {
						return jsonNode.findValue("long_name").getTextValue();
					}
				}
			}
		}
		return null;
	}

}
