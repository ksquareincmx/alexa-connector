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
