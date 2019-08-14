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
