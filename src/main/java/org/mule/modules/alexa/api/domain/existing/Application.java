/**
 * (c) 2003-2017 MuleSoft, Inc. The software in this package is published under the terms of the Commercial Free Software license V.1 a copy of which has been included with this distribution in the LICENSE.md file.
 */

package org.mule.modules.alexa.api.domain.existing;

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
