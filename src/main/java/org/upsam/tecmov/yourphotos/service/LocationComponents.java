package org.upsam.tecmov.yourphotos.service;

public class LocationComponents {
	/**
	 * Nombre corto de la localidad
	 */
	public final String locality;
	/**
	 * Código Postal de la localidad
	 */
	public final String zipCode;

	/**
	 * @param locality
	 * @param zipCode
	 */
	public LocationComponents(String locality, String zipCode) {
		super();
		this.locality = locality;
		this.zipCode = zipCode;
	}

}
