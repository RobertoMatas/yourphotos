package org.upsam.tecmov.yourphotos.domain.poblacion;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Provincia {
	/**
	 * Identificador de la provincia
	 */
	@Id
	private Long id;
	/**
	 * Nombre
	 */
	private String nombre;
	/**
	 * Nombre normalizado
	 */
	@Column(name = "nombre_seo", unique = true)
	private String nombreSeo;
	/**
	 * Código manual de la provincia
	 */
	@Column(unique = true)
	private String codigo;
	/**
	 * Comunidad autónoma a la que pertence la provincia
	 */
	@ManyToOne
	private Comunidad comunidad;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre
	 *            the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the nombreSeo
	 */
	public String getNombreSeo() {
		return nombreSeo;
	}

	/**
	 * @param nombreSeo
	 *            the nombreSeo to set
	 */
	public void setNombreSeo(String nombreSeo) {
		this.nombreSeo = nombreSeo;
	}

	/**
	 * @return the codigo
	 */
	public String getCodigo() {
		return codigo;
	}

	/**
	 * @param codigo
	 *            the codigo to set
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	/**
	 * @return the comunidad
	 */
	public Comunidad getComunidad() {
		return comunidad;
	}

	/**
	 * @param comunidad
	 *            the comunidad to set
	 */
	public void setComunidad(Comunidad comunidad) {
		this.comunidad = comunidad;
	}

}
