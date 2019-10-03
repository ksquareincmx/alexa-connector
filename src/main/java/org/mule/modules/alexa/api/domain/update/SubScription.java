/**
 * (c) 2003-2017 MuleSoft, Inc. The software in this package is published under the terms of the Commercial Free Software license V.1 a copy of which has been included with this distribution in the LICENSE.md file.
 */
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
