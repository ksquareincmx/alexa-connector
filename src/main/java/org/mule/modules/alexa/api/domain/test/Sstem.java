package org.mule.modules.alexa.api.domain.test;



public class Sstem {

	private Application application;
	
	private User user;

	public Application getApplication() {
		return application;
	}

	public void setApplication(Application application) {
		this.application = application;
	}

	@Override
	public String toString() {
		return "Sstem [application=" + application + ", user=" + user + "]";
	}

	public Sstem(Application application, User user) {
		super();
		this.application = application;
		this.user = user;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
