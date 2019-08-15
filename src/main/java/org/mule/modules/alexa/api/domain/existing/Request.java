/**
 * (c) 2003-2017 MuleSoft, Inc. The software in this package is published under the terms of the Commercial Free Software license V.1 a copy of which has been included with this distribution in the LICENSE.md file.
 */

package org.mule.modules.alexa.api.domain.existing;

public class Request {

	private String endpointRegion;
	private SkillRequest skillRequest;

	public Request(String endpointRegion, SkillRequest skillRequest) {
		this.endpointRegion = endpointRegion;
		this.skillRequest = skillRequest;
	}

	public String getEndpointRegion() {
		return endpointRegion;
	}

	public void setEndpointRegion(String endpointRegion) {
		this.endpointRegion = endpointRegion;
	}

	public SkillRequest getSkillrequest() {
		return skillRequest;
	}

}
