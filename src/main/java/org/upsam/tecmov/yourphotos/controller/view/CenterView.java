package org.upsam.tecmov.yourphotos.controller.view;

import java.io.Serializable;

public class CenterView implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7874093690131842331L;
	/**
	 * Latitud
	 */
	private String lat;
	/**
	 * Longitud
	 */
	private String lng;

	/**
	 * 
	 */
	public CenterView() {
		super();
	}

	/**
	 * @param lat
	 * @param lng
	 */
	public CenterView(String lat, String lng) {
		super();
		this.lat = lat;
		this.lng = lng;
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

}
