package org.upsam.tecmov.yourphotos.controller.form;

import java.io.Serializable;

public class LocationForm implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7316370890528339545L;
	/**
	 * Latitud
	 */
	private String lat;
	/**
	 * Longitud
	 */
	private String lng;

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
