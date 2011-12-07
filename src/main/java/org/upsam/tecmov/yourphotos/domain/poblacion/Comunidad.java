package org.upsam.tecmov.yourphotos.domain.poblacion;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Comunidad {
	/**
	 * Identificador de comunidad autónoma
	 */
	@Id
	private Long id;
	/**
	 * Nombre de la comunidad autónoma
	 */
	private String nombre;

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

}
