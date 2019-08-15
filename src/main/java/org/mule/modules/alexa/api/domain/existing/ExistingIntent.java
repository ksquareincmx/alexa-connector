/**
 * (c) 2003-2017 MuleSoft, Inc. The software in this package is published under the terms of the Commercial Free Software license V.1 a copy of which has been included with this distribution in the LICENSE.md file.
 */

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
