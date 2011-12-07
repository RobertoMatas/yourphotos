package org.upsam.tecmov.yourphotos.domain.poblacion;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Poblacion {
	/**
	 * Identificador de población
	 */
	@Id
	private Long id;
	/**
	 * Provincia a la que pertence la población
	 */
	@ManyToOne
	private Provincia provincia;
	/**
	 * Nombre de la poblción
	 */
	private String poblacion;
	/**
	 * Nombre normalizado de la población
	 */
	@Column(name = "poblacion_seo", unique = true)
	private String poblacionSeo;
	/**
	 * Código postal asociado a la población
	 */
	@Column(name = "cod_postal")
	private String codPostal;
	/**
	 * Latitud
	 */
	private String latitud;
	/**
	 * Longitud
	 */
	private String longitud;
	/**
	 * Número de fotos que se han hecho en esta zona
	 */
	@Column(name = "num_fotos")
	private Integer numFotos;
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return the provincia
	 */
	public Provincia getProvincia() {
		return provincia;
	}
	/**
	 * @param provincia the provincia to set
	 */
	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}
	/**
	 * @return the poblacion
	 */
	public String getPoblacion() {
		return poblacion;
	}
	/**
	 * @param poblacion the poblacion to set
	 */
	public void setPoblacion(String poblacion) {
		this.poblacion = poblacion;
	}
	/**
	 * @return the poblacionSeo
	 */
	public String getPoblacionSeo() {
		return poblacionSeo;
	}
	/**
	 * @param poblacionSeo the poblacionSeo to set
	 */
	public void setPoblacionSeo(String poblacionSeo) {
		this.poblacionSeo = poblacionSeo;
	}
	/**
	 * @return the codPostal
	 */
	public String getCodPostal() {
		return codPostal;
	}
	/**
	 * @param codPostal the codPostal to set
	 */
	public void setCodPostal(String codPostal) {
		this.codPostal = codPostal;
	}
	/**
	 * @return the latitud
	 */
	public String getLatitud() {
		return latitud;
	}
	/**
	 * @param latitud the latitud to set
	 */
	public void setLatitud(String latitud) {
		this.latitud = latitud;
	}
	/**
	 * @return the longitud
	 */
	public String getLongitud() {
		return longitud;
	}
	/**
	 * @param longitud the longitud to set
	 */
	public void setLongitud(String longitud) {
		this.longitud = longitud;
	}
	/**
	 * @return the numFotos
	 */
	public Integer getNumFotos() {
		return numFotos;
	}
	/**
	 * @param numFotos the numFotos to set
	 */
	public void setNumFotos(Integer numFotos) {
		this.numFotos = numFotos;
	}
	
}
