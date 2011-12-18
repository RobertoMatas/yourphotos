package org.upsam.tecmov.yourphotos.service;

import java.io.InputStream;
import java.util.List;

import org.upsam.tecmov.yourphotos.controller.form.LocationForm;
import org.upsam.tecmov.yourphotos.controller.view.PoblacionWithDetailsView;

public interface GoogleRestClient {
	/**
	 * Devuelve el nombre de la localidad y su código postal asociado dadas sus coordenadas
	 * 
	 * @param coordenadas
	 *            {@link LocationForm} con las coordenadas especificadas
	 * @return {@link LocationComponents} con el nombre de la localidad y su código postal
	 */
	LocationComponents getLocationComponents(LocationForm coordenadas);

	/**
	 * Devuelve la distancia en Metros desde el origen al destino
	 * 
	 * @param origin
	 *            Coordenadas del origen
	 * @param destination
	 *            Coordenadas del destino
	 * @return Distancia en metros
	 */
	Integer getDistance(LocationForm origin, LocationForm destination);

	/**
	 * Devuelve un {@link InputStream} conteniendo la imagen del mapa devuelto por el API Static
	 * Maps de Google
	 * 
	 * @param coordenadas
	 *            {@link LocationForm} con las coordenadas especificadas
	 * @param markers
	 *            {@link List} Lista de las poblaciones que deben aparecer como marcadores
	 * @return {@link InputStream} conteniendo la imagen del mapa
	 */
	InputStream getMap(LocationForm coordenadas, List<PoblacionWithDetailsView> markers);
}
