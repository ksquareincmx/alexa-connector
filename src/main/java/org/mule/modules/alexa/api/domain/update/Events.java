/**
 * (c) 2003-2017 MuleSoft, Inc. The software in this package is published under the terms of the Commercial Free Software license V.1 a copy of which has been included with this distribution in the LICENSE.md file.
 */

package org.mule.modules.alexa.api.domain.update;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.mule.runtime.extension.api.annotation.Expression;
import org.mule.runtime.extension.api.annotation.param.Optional;
import org.mule.runtime.extension.api.annotation.param.Parameter;

public class Events {
	
	public Events() {
		
	}
	public Events(EndpointInfo endpoint, List<SubScription> subscriptions) {
		this.endpoint = endpoint;
		this.subscriptions = subscriptions;
	}
	
	@Parameter
	@Expression
	private EndpointInfo endpoint;

	@Parameter
	@Expression
	@Optional
	private List<SubScription> subscriptions = new ArrayList<SubScription>();


	public EndpointInfo getEndpoint() {
		return endpoint;
	}

	public void setEndpoint(EndpointInfo endpoint) {
		this.endpoint = endpoint;
	}

	public List<SubScription> getSubscriptions() {
		return subscriptions;
	}

	public void setSubscriptions(List<SubScription> subscriptions) {
		this.subscriptions = subscriptions;
	}

	public static Events defaultEvents(Events events) {
		List<SubScription> sub = events.getSubscriptions().stream().map(m -> SubScription.defaultSub(m)).collect(Collectors.toList());
		return new Events(EndpointInfo.defaultEndPointInfo(events.getEndpoint()), sub);
	}
}
