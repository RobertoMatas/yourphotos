package org.upsam.tecmov.yourphotos.controller.form;

import java.io.Serializable;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

public class LocationForm implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7316370890528339545L;
	/**
	 * Latitud
	 */
	@NotNull
	@Digits(integer = 3, fraction = 8)
	private String lat;
	/**
	 * Longitud
	 */
	@NotNull
	@Digits(integer = 3, fraction = 8)
	private String lng;
	/**
	 * Categoría mínima de visualización
	 * Si el cliente no establece una, no se aplica el filtro
	 */
	private Integer category;
	/**
	 * Distancia (en metros) mínima de visualización.
	 * Si el cliente no establece una, no se aplica el filtro
	 */
	private Integer distancia;

	/**
	 * @param lat
	 * @param lng
	 */
	public LocationForm(String lat, String lng) {
		super();
		this.lat = lat;
		this.lng = lng;
		this.category = -1;
		this.distancia = 0;
	}

	/**
	 * 
	 */
	public LocationForm() {
		super();
		this.category = -1;		
	}

	/**
	 * @return the lat
	 */
	public String getLat() {
		return lat;
	}

	/**
	 * @param lat
	 *            the lat to set
	 */
	public void setLat(String lat) {
		this.lat = lat;
	}

	/**
	 * @return the lng
	 */
	public String getLng() {
		return lng;
	}

	/**
	 * @param lng
	 *            the lng to set
	 */
	public void setLng(String lng) {
		this.lng = lng;
	}

	/**
	 * @return the category
	 */
	public Integer getCategory() {
		return category;
	}

	/**
	 * @param category the category to set
	 */
	public void setCategory(Integer category) {
		this.category = category != null && category <=5 ? category - 1 : -1;
	}

	/**
	 * @param distancia the distancia to set
	 */
	public void setDistancia(Integer distancia) {
		this.distancia = distancia != null ? distancia : 0;
	}

	/**
	 * @return the distancia
	 */
	public Integer getDistancia() {
		return distancia;
	}

}
