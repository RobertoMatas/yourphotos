package org.upsam.tecmov.yourphotos.service.ex;

public class ServiceException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5702139298088744840L;

	/**
	 * 
	 */
	public ServiceException() {
		super();
	}

	/**
	 * @param message
	 * @param cause
	 */
	public ServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public ServiceException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public ServiceException(Throwable cause) {
		super(cause);
	}

}
