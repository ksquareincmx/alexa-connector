package org.mule.modules.alexa.api.domain.intents;

import java.util.LinkedList;
import java.util.List;

import org.mule.runtime.api.meta.ExpressionSupport;
import org.mule.runtime.extension.api.annotation.Expression;
import org.mule.runtime.extension.api.annotation.param.NullSafe;
import org.mule.runtime.extension.api.annotation.param.Optional;
import org.mule.runtime.extension.api.annotation.param.Parameter;

public class SlotParams {

	@Parameter
	@Optional
	@NullSafe
	private String slotName; 
	
	@Parameter
	@Optional
	@NullSafe
	private String slotType;
	
	@Parameter
	@Optional
	@NullSafe
	@Expression(ExpressionSupport.NOT_SUPPORTED)
	private List<String> samples = new LinkedList<>();

	public String getSlotName() {
		return slotName;
	}

	public void setSlotName(String slotName) {
		this.slotName = slotName;
	}

	public String getSlotType() {
		return slotType;
	}

	public void setSlotType(String slotType) {
		this.slotType = slotType;
	}

	public List<String> getSamples() {
		return samples;
	}

	public void setSamples(List<String> samples) {
		this.samples = samples;
	}

	@Override
	public String toString() {
		return "SlotParams [slotName=" + slotName + ", slotType=" + slotType + ", samples=" + samples + "]";
	}

	

	

}
