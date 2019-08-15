/**
 * (c) 2003-2017 MuleSoft, Inc. The software in this package is published under the terms of the Commercial Free Software license V.1 a copy of which has been included with this distribution in the LICENSE.md file.
 */

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
