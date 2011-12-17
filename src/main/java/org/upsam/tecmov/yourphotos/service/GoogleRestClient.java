package org.upsam.tecmov.yourphotos.service;

import org.upsam.tecmov.yourphotos.controller.form.LocationForm;

public interface GoogleRestClient {

	LocationComponents getLocationComponents(LocationForm coordenadas);
	/**
	 * Devuelve la distancia en Metros desde el origen al destino
	 * @param origin Coordenadas del origen
	 * @param destination Coordenadas del destino
	 * @return Distancia en metros
	 */
	Integer getDistance(LocationForm origin, LocationForm destination);
}
