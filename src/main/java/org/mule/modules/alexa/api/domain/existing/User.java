package org.mule.modules.alexa.api.domain.existing;

public class User {

	private String userId;

	public User(String userId) {
		super();
		this.userId = userId;
	}
	
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + "]";
	}

	
}
