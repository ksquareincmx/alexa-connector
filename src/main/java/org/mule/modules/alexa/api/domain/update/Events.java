/**
 * (c) 2003-2017 MuleSoft, Inc. The software in this package is published under the terms of the Commercial Free Software license V.1 a copy of which has been included with this distribution in the LICENSE.md file.
 */

package org.mule.modules.alexa.api.domain.update;

import java.util.List;
import java.util.Map;

import org.mule.runtime.extension.api.annotation.Expression;
import org.mule.runtime.extension.api.annotation.param.Optional;
import org.mule.runtime.extension.api.annotation.param.Parameter;

public class Events {
	
	public Events() {
		
	}
	@Parameter
	@Expression
	private EndpointInfo endpoint;
	@Parameter
	@Expression
	@Optional
	private List<Map<String, String>> subscriptions;


	public Events(EndpointInfo endpoint, List<Map<String, String>> subscriptions) {
		this.endpoint = endpoint;
		this.subscriptions = subscriptions;
	}

	public EndpointInfo getEndpoint() {
		return endpoint;
	}

	public void setEndpoint(EndpointInfo endpoint) {
		this.endpoint = endpoint;
	}

	public List<Map<String, String>> getSubscriptions() {
		return subscriptions;
	}

	public void setSubscriptions(List<Map<String, String>> subscriptions) {
		this.subscriptions = subscriptions;
	}

}
