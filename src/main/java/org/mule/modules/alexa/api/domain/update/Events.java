/**
 * (c) 2003-2017 MuleSoft, Inc. The software in this package is published under the terms of the Commercial Free Software license V.1 a copy of which has been included with this distribution in the LICENSE.md file.
 */

package org.mule.modules.alexa.api.domain.update;

import java.util.List;
import java.util.Map;

public class Events {
	
	public Events() {
		
	}
	
	public Events(String endPoint) {
		this.endpoint = new EndpointInfo(endPoint, "");
	}
	

	private EndpointInfo endpoint;
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
