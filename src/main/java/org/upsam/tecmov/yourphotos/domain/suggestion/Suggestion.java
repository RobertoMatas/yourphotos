package org.upsam.tecmov.yourphotos.domain.suggestion;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.upsam.tecmov.yourphotos.domain.poblacion.Poblacion;

@Entity
public class Suggestion {
	/**
	 * Id autonumérico
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	/**
	 * Fecha de realización de la foto
	 */
	private Date date;
	/**
	 * Tipo de foto según lugar: Naturaleza / Urbana
	 */
	private PhotoPlace photoPlace;
	/**
	 * Tipo de foto según hora: amanecer / atardecer / diurna
	 */
	private PhotoType photoType;
	/**
	 * Latitud dónde se realizó la foto
	 */
	private String latitude;
	/**
	 * Longitud dónde se realizó la foto
	 */
	private String longitude;
	/**
	 * Población a la que se asocian las coordenadas
	 */
	@ManyToOne(optional = false)
	private Poblacion poblacion;
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	@SuppressWarnings("unused")
	private void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}
	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}
	/**
	 * @return the photoPlace
	 */
	public PhotoPlace getPhotoPlace() {
		return photoPlace;
	}
	/**
	 * @param photoPlace the photoPlace to set
	 */
	public void setPhotoPlace(PhotoPlace photoPlace) {
		this.photoPlace = photoPlace;
	}
	/**
	 * @return the photoType
	 */
	public PhotoType getPhotoType() {
		return photoType;
	}
	/**
	 * @param photoType the photoType to set
	 */
	public void setPhotoType(PhotoType photoType) {
		this.photoType = photoType;
	}
	/**
	 * @return the latitude
	 */
	public String getLatitude() {
		return latitude;
	}
	/**
	 * @param latitude the latitude to set
	 */
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	/**
	 * @return the longitude
	 */
	public String getLongitude() {
		return longitude;
	}
	/**
	 * @param longitude the longitude to set
	 */
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	/**
	 * @return the poblacion
	 */
	public Poblacion getPoblacion() {
		return poblacion;
	}
	/**
	 * @param poblacion the poblacion to set
	 */
	public void setPoblacion(Poblacion poblacion) {
		this.poblacion = poblacion;
	}
	
}
