package org.upsam.tecmov.yourphotos.controller.form;

import javax.validation.constraints.NotNull;

import org.upsam.tecmov.yourphotos.domain.suggestion.PhotoPlace;

public class SuggestionForm extends LocationForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5527025961938892164L;
	/**
	 * Tipo de foto según lugar: Naturaleza / Urbana
	 */
	@NotNull
	private PhotoPlace photoPlace;

	/**
	 * @return the photoPlace
	 */
	public PhotoPlace getPhotoPlace() {
		return photoPlace;
	}

	/**
	 * @param photoPlace
	 *            the photoPlace to set
	 */
	public void setPhotoPlace(PhotoPlace photoPlace) {
		this.photoPlace = photoPlace;
	}

}
