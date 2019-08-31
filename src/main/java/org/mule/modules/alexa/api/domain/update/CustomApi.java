package org.mule.modules.alexa.api.domain.update;

import java.util.List;
import java.util.Map;

import org.mule.runtime.extension.api.annotation.Expression;
import org.mule.runtime.extension.api.annotation.param.Parameter;

public class CustomApi {

	public CustomApi() {

	}

	@Parameter
	@Expression
	private EndpointInfo endpoint;

	@Parameter
	@Expression
	private List<Map<String, String>> interfaces;

	public CustomApi(EndpointInfo endpoint, List<Map<String, String>> interfaces) {

		this.endpoint = endpoint;
		this.interfaces = interfaces;

	}

	public EndpointInfo getEndpoint() {
		return endpoint;
	}

	public void setEndpoint(EndpointInfo endpoint) {
		this.endpoint = endpoint;
	}

	public List<Map<String, String>> getInterfaces() {
		return interfaces;
	}

	public void setInterfaces(List<Map<String, String>> interfaces) {
		this.interfaces = interfaces;
	}

}