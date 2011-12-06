package org.upsam.tecmov.yourphotos.controller.view;

import java.io.InputStream;
import java.io.Serializable;
import java.util.Date;

public class PhotoView implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7418620062842759198L;

	/**
	 * Identificador
	 */
	private Long id;

	/**
	 * Comentario
	 */
	private String comment;

	/**
	 * Fecha de alta
	 */
	private Date date;

	/**
	 * Imagen
	 */
	private InputStream photo;

	/**
	 * Tipo MIME de la imagen
	 */
	private String contentType;

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
	 * @return the comment
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * @param comment
	 *            the comment to set
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param date
	 *            the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * @return the photo
	 */
	public InputStream getPhoto() {
		return photo;
	}

	/**
	 * @param photo
	 *            the photo to set
	 */
	public void setPhoto(InputStream photo) {
		this.photo = photo;
	}

	/**
	 * @return the contentType
	 */
	public String getContentType() {
		return contentType;
	}

	/**
	 * @param contentType
	 *            the contentType to set
	 */
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

}
