/**
 * (c) 2003-2017 MuleSoft, Inc. The software in this package is published under the terms of the Commercial Free Software license V.1 a copy of which has been included with this distribution in the LICENSE.md file.
 */

package org.mule.modules.alexa.api.domain.update;

import java.util.List;
import java.util.Map;


public class ApiInfo {

	private CustomApi custom;

	public ApiInfo() {
		
	}
	
	public ApiInfo(CustomApi custom) {
		this.custom = custom;
	}

	public CustomApi getCustom() {
		return custom;
	}

	public void setCustom(CustomApi custom) {
		this.custom = custom;
	}

	public static class CustomApi {

		public CustomApi() {

		}

		EndpointInfo endpoint;

		public CustomApi(String endPoint) {

			this.endpoint = new EndpointInfo(endPoint, "");

		}

		List<Map<String, String>> interfaces;

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
}
