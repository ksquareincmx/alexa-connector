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
