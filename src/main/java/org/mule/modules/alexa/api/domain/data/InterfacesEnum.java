/**
 * (c) 2003-2019 MuleSoft, Inc. The software in this package is published under the terms of the Commercial Free Software license V.1 a copy of which has been included with this distribution in the LICENSE.md file.
 */
package org.mule.modules.alexa.api.domain.data;

public enum InterfacesEnum {
	ALEXA_PRESENTATION_APL("ALEXA_PRESENTATION_APL"), AUDIO_PLAYER("AUDIO_PLAYER"), CAN_FULFILL_INTENT_REQUEST(
			"CAN_FULFILL_INTENT_REQUEST"), GADGET_CONTROLLER("GADGET_CONTROLLER"), GAME_ENGINE(
					"GAME_ENGINE"), RENDER_TEMPLATE("RENDER_TEMPLATE"), VIDEO_APP("VIDEO_APP");

	private String type;

	private InterfacesEnum(String value) {
		this.type = value;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	
}
