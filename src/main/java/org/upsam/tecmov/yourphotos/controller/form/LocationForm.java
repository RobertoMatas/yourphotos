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
	 * @param lat
	 * @param lng
	 */
	public LocationForm(String lat, String lng) {
		super();
		this.lat = lat;
		this.lng = lng;
	}

	/**
	 * 
	 */
	public LocationForm() {
		super();
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

}
