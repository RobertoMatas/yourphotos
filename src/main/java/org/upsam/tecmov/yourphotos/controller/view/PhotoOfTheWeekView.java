package org.upsam.tecmov.yourphotos.controller.view;

import java.io.Serializable;

public class PhotoOfTheWeekView implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6044906641771140245L;
	/**
	 * Url relativa de la foto de la semana
	 */
	private String relativeUrl;
	/**
	 * Comentario de la foto de la semana
	 */
	private String comment;

	/**
	 * @param url
	 * @param comment
	 */
	public PhotoOfTheWeekView(String relativeUrl, String comment) {
		super();
		this.relativeUrl = relativeUrl;
		this.comment = comment;
	}

	/**
	 * @return the url
	 */
	public String getRelativeUrl() {
		return relativeUrl;
	}

	/**
	 * @return the comment
	 */
	public String getComment() {
		return comment;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format("PhotoOfTheWeekView [comment=%s, url=%s]", comment, relativeUrl);
	}

}
