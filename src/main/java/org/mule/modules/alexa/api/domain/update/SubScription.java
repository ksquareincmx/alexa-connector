package org.mule.modules.alexa.api.domain.update;

import org.mule.modules.alexa.api.domain.data.EventsEnum;
import org.mule.runtime.api.meta.ExpressionSupport;
import org.mule.runtime.extension.api.annotation.Alias;
import org.mule.runtime.extension.api.annotation.Expression;
import org.mule.runtime.extension.api.annotation.param.Parameter;

public class SubScription {

	

	public SubScription(EventsEnum eventName) {
		this.eventName = eventName;
	}

	@Parameter
	@Alias("EventName")
	@Expression(ExpressionSupport.NOT_SUPPORTED)
	private EventsEnum eventName;

	

	public SubScription() {
		super();
	}

	
	public EventsEnum getEventName() {
		return eventName;
	}


	public void setEventName(EventsEnum eventName) {
		this.eventName = eventName;
	}


	public static SubScription defaultSub(SubScription s) {
		return new SubScription(s.getEventName());
	}
}
