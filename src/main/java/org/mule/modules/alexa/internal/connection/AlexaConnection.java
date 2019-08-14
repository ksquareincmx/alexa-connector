package org.mule.modules.alexa.internal.connection;

/**
 * This class represents an extension connection just as example (there is no
 * real connection with anything here c:).
 */
public final class AlexaConnection {

	private String accessToken;

	public AlexaConnection(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getAccessToken() {
		return "Bearer " + accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

}
