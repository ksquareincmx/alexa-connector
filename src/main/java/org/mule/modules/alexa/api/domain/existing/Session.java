/**
 * (c) 2003-2017 MuleSoft, Inc. The software in this package is published under the terms of the Commercial Free Software license V.1 a copy of which has been included with this distribution in the LICENSE.md file.
 */

package org.mule.modules.alexa.api.domain.existing;

import java.util.Map;


public class Session {
	
	private  boolean one;
	private String sessionId;
	private Application application;
	private  Map<String,String> attributes;
	private User user;
	public boolean getOne() {
		return one;
	}
	
	public Session(){}
	
	
	public Session(boolean prop, String sessionId, Application application, Map<String, String> attributes, User user) {
		super();
		
		
		this.one = prop;
		this.sessionId = sessionId;
		this.application = application;
		this.attributes = attributes;
		this.user = user;
	}
	public void setOne(boolean one) {
		this.one = one;
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public Application getApplication() {
		return application;
	}
	public void setApplication(Application application) {
		this.application = application;
	}
	public Map<String, String> getAttributes() {
		return attributes;
	}
	public void setAttributes(Map<String, String> attributes) {
		this.attributes = attributes;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@Override
	public String toString() {
		return "Session [one=" + one + ", sessionId=" + sessionId + ", application=" + application + ", attributes="
				+ attributes + ", user=" + user + "]";
	}
	
	
	
	

}
