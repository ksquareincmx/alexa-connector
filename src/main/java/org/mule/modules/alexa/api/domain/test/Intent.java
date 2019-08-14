package org.mule.modules.alexa.api.domain.test;

import java.util.Map;

public class Intent {
	
	private String name;
	
	private Map<String, SlotName> slots;
	
	public Intent(){};

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Intent(String name) {
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
