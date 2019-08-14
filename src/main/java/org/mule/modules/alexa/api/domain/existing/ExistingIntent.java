package org.mule.modules.alexa.api.domain.existing;

import java.util.Map;

public class ExistingIntent {
	
	private String name;
	
	private Map<String, SlotName> slots;
	
	public ExistingIntent(){};

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ExistingIntent(String name) {
		super();
		this.name = name;
	}

	@Override
	public String toString() {
		return "Intent [name=" + name + "]";
	}

	public Map<String, SlotName> getSlots() {
		return slots;
	}

	public void setSlots(Map<String, SlotName> slots) {
		this.slots = slots;
	}

	
	

}
