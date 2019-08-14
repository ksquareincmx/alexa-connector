package org.mule.modules.alexa.api.domain.existing;

public class SkillRequest {

	private Body body;

	public SkillRequest(Body body) {
		super();
		this.body = body;
	}

	

	public Body getBody() {
		return body;
	}

	public void setBody(Body body) {
		this.body = body;
	}
	
}
