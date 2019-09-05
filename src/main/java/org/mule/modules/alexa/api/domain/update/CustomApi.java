package org.mule.modules.alexa.api.domain.update;

import java.util.List;

import org.mule.runtime.extension.api.annotation.Expression;
import org.mule.runtime.extension.api.annotation.param.Optional;
import org.mule.runtime.extension.api.annotation.param.Parameter;

public class CustomApi {

	public CustomApi() {

	}

	@Parameter
	@Expression
	private EndpointInfo endpoint;

	@Parameter
	@Optional
	private List<Interface> interfaces;

	public CustomApi(EndpointInfo endpoint, List<Interface> interfaces) {

		this.endpoint = endpoint;
		this.interfaces = interfaces;

	}

	public EndpointInfo getEndpoint() {
		return endpoint;
	}

	public void setEndpoint(EndpointInfo endpoint) {
		this.endpoint = endpoint;
	}

	public List<Interface> getInterfaces() {
		return interfaces;
	}

	public void setInterfaces(List<Interface> interfaces) {
		this.interfaces = interfaces;
	}

}