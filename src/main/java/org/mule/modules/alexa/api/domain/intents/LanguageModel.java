package org.mule.modules.alexa.api.domain.intents;

import java.util.List;

public class LanguageModel {

	public List<Intent> intents;
	
	private String invocationName;

	public List<Intent> getIntents() {
		return intents;
	}

	public void setIntents(List<Intent> intents) {
		this.intents = intents;
	}

	public String getInvocationName() {
		return invocationName;
	}

	public void setInvocationName(String invocationName) {
		this.invocationName = invocationName;
	}
	
	
}
