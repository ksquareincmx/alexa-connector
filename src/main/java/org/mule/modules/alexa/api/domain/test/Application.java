package org.mule.modules.alexa.api.domain.test;

public class Application {
	
	private String applicationId;

	public String getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}

	public Application(String applicationId) {
		super();
		this.applicationId = applicationId;
	}

	@Override
	public String toString() {
		return "Application [applicationId=" + applicationId + "]";
	}

	

}
