package org.mule.modules.alexa.api.domain.intents;

import java.util.List;

import org.mule.runtime.api.meta.ExpressionSupport;
import org.mule.runtime.extension.api.annotation.Expression;
import org.mule.runtime.extension.api.annotation.param.NullSafe;
import org.mule.runtime.extension.api.annotation.param.Optional;
import org.mule.runtime.extension.api.annotation.param.Parameter;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LanguageIntent {
	
	public LanguageIntent() {
		
	}
	

	@Parameter
	private String intentName;  
	
	@Parameter
	@Optional
	@NullSafe
	@Expression(ExpressionSupport.SUPPORTED)
	private List<Slot> slots ;


	@Parameter
	@Optional
	@NullSafe
	@Expression(ExpressionSupport.SUPPORTED)
	private List<String> samples;

	public LanguageIntent(String name ,List<Slot> slots, List<String> samples){
		this.intentName = name;
		this.slots = slots;
		this.samples = samples;
				
	}

	@JsonProperty("name")
	public String getIntentName() {
		return intentName;
	}

	public void setIntentName(String intentName) {
		this.intentName = intentName;
	}

	public List<Slot> getSlots() {
		return slots;
	}

	public void setSlots(List<Slot> slots) {
		this.slots = slots;
	}

	public List<String> getSamples() {
		return samples;
	}

	public void setSamples(List<String> samples) {
		this.samples = samples;
	}
	
	

}
