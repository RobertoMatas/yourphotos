package org.upsam.tecmov.yourphotos.controller.form;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

public class PhotoForm implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8751190110994310680L;

	/**
	 * Comentario
	 */
	@NotNull
	@Size(min = 1, max = 255)
	private String comment;

	/**
	 * Imagen
	 */
	private MultipartFile photo;

	/**
	 * 
	 */
	public PhotoForm() {
		super();
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
	 * @return the photo
	 */
	public MultipartFile getPhoto() {
		return photo;
	}

	/**
	 * @param photo
	 *            the photo to set
	 */
	public void setPhoto(MultipartFile photo) {
		this.photo = photo;
	}

}
