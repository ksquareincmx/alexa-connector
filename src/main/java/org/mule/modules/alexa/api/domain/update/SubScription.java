package org.mule.modules.alexa.api.domain.update;

import org.mule.runtime.extension.api.annotation.Alias;
import org.mule.runtime.extension.api.annotation.param.Parameter;

public class SubScription {

	@Parameter
	@Alias("Event Name")
	private String eventName ;

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public SubScription() {
		super();
	}
	
	
}
