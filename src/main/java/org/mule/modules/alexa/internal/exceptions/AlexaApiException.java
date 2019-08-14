package org.mule.modules.alexa.internal.exceptions;

public class AlexaApiException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3461452158138328912L;

	public AlexaApiException(String message) {
		super(message);
	}
	
	public AlexaApiException(String message,Throwable t) {
		super(message,t);
	}
}
