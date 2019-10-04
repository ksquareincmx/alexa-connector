/**
 * (c) 2003-2017 MuleSoft, Inc. The software in this package is published under the terms of the Commercial Free Software license V.1 a copy of which has been included with this distribution in the LICENSE.md file.
 */
package org.mule.modules.alexa.api.domain.update;

import java.util.List;
import java.util.stream.Collectors;

import org.mule.runtime.extension.api.annotation.Expression;
import org.mule.runtime.extension.api.annotation.param.Optional;
import org.mule.runtime.extension.api.annotation.param.Parameter;

public class CustomApi {

	public CustomApi() {

	}

	public CustomApi(EndpointInfo endpoint, List<Interface> interfaces) {
		
		this.endpoint = endpoint;
		this.interfaces = interfaces;
		
	}
	@Parameter
	@Expression
	private EndpointInfo endpoint;

	@Parameter
	@Optional
	private List<Interface> interfaces;


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

	public static CustomApi defaultCustomApi(CustomApi custom) {

		List<Interface> interfaces = custom.getInterfaces().stream().map(m -> Interface.defaultInterface(m))
				.collect(Collectors.toList());
		return new CustomApi(EndpointInfo.defaultEndPointInfo(custom.getEndpoint()), interfaces);

	}
}