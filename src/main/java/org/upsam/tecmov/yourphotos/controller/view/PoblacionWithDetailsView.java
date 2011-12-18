package org.upsam.tecmov.yourphotos.controller.view;

import java.io.Serializable;

public class PoblacionWithDetailsView implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7681230785665113863L;

	/**
	 * Nombre de la poblción
	 */
	private String poblacion;
	/**
	 * Nombre de la provincia
	 */
	private String provincia;
	/**
	 * Distancia en kilómetros con respecto a la posición actual del usuario
	 */
	private Integer distancia;
	/**
	 * Categoría
	 */
	private Integer categoria;
	/**
	 * Latitud
	 */
	private String lat;
	/**
	 * Longitud
	 */
	private String lng;
	/**
	 * Información adicional
	 */
	private DetailsView details;

	/**
	 * @param poblacion
	 * @param provincia
	 * @param categoria
	 * @param details
	 */
	public PoblacionWithDetailsView(String poblacion, String provincia, Integer distancia,
			Integer categoria, String lat, String lng, DetailsView details) {
		super();
		this.poblacion = poblacion;
		this.provincia = provincia;
		this.distancia = distancia;
		this.categoria = categoria;
		this.lat = lat;
		this.lng = lng;
		this.details = details;
	}

	/**
	 * @return the poblacion
	 */
	public String getPoblacion() {
		return poblacion;
	}

	/**
	 * @return the provincia
	 */
	public String getProvincia() {
		return provincia;
	}

	/**
	 * @return the distancia
	 */
	public Integer getDistancia() {
		return distancia;
	}

	/**
	 * @return the categoria
	 */
	public Integer getCategoria() {
		return categoria;
	}

	/**
	 * @return the lat
	 */
	public String getLat() {
		return lat;
	}

	/**
	 * @return the lng
	 */
	public String getLng() {
		return lng;
	}

	/**
	 * @return the details
	 */
	public DetailsView getDetails() {
		return details;
	}

}
