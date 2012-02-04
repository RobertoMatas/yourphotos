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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Provincia other = (Provincia) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
