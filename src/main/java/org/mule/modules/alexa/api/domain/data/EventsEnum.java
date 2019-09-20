package org.mule.modules.alexa.api.domain.data;

public enum EventsEnum {
	SKILL_ENABLED("SKILL_ENABLED"),
	SKILL_DISABLED("SKILL_DISABLED"),
	SKILL_PERMISSION_ACCEPTED("SKILL_PERMISSION_ACCEPTED"),
	SKILL_PERMISSION_CHANGED("SKILL_PERMISSION_CHANGED"),
	SKILL_ACCOUNT_LINKED("SKILL_ACCOUNT_LINKED");
	
	
	String eventName;
	
	EventsEnum(String eventName){
		this.eventName = eventName;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	
}
