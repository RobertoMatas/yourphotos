package org.upsam.tecmov.yourphotos.controller.view;

import java.io.Serializable;

public class DetailsView implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6993079295155457100L;

	/**
	 * Número real de sugerencias
	 */
	private Integer numSuggestions;
	/**
	 * Fecha de la primera sugerencia
	 */
	private String first;
	/**
	 * Fecha de la última sugerencia
	 */
	private String last;
	/**
	 * Tipo de la primera sugerencia
	 */
	private String firstType;
	/**
	 * Tipo de la última sugerencia
	 */
	private String lastType;

	/**
	 * @param numSuggestions
	 * @param first
	 * @param last
	 * @param firstType
	 * @param lastType
	 */
	public DetailsView(Integer numSuggestions, String first, String last,
			String firstType, String lastType) {
		super();
		this.numSuggestions = numSuggestions;
		this.first = first;
		this.last = last;
		this.firstType = firstType;
		this.lastType = lastType;
	}

	/**
	 * @return the numSuggestions
	 */
	public Integer getNumSuggestions() {
		return numSuggestions;
	}

	/**
	 * @return the first
	 */
	public String getFirst() {
		return first;
	}

	/**
	 * @return the last
	 */
	public String getLast() {
		return last;
	}

	/**
	 * @return the firstType
	 */
	public String getFirstType() {
		return firstType;
	}

	/**
	 * @return the lastType
	 */
	public String getLastType() {
		return lastType;
	}

}
