package org.upsam.tecmov.yourphotos.controller.form;

import java.util.Date;

import javax.validation.constraints.NotNull;

public class ManualSuggestionForm extends SuggestionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6609586389887193253L;
	/**
	 * Campo para guardar la fecha cuando cliente indica la fecha explícitamente (mediante entrada
	 * manual)
	 */
	@NotNull
	private Date manualDate;

	/**
	 * @param manualDate
	 */
	public ManualSuggestionForm(Date manualDate) {
		super();
		this.manualDate = manualDate;
	}

	/**
	 * 
	 */
	public ManualSuggestionForm() {
		super();
	}

	/**
	 * @param lat
	 * @param lng
	 */
	public ManualSuggestionForm(String lat, String lng) {
		super(lat, lng);
	}

	/**
	 * @return the manualDate
	 */
	public Date getManualDate() {
		return manualDate;
	}

	/**
	 * @param manualDate
	 *            the manualDate to set
	 */
	public void setManualDate(Date manualDate) {
		this.manualDate = manualDate;
	}

}
