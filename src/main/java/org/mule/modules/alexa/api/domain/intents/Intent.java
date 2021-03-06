/**
 * (c) 2003-2017 MuleSoft, Inc. The software in this package is published under the terms of the Commercial Free Software license V.1 a copy of which has been included with this distribution in the LICENSE.md file.
 */
package org.mule.modules.alexa.api.domain.intents;

import java.util.List;

import org.mule.runtime.extension.api.annotation.param.NullSafe;
import org.mule.runtime.extension.api.annotation.param.Optional;
import org.mule.runtime.extension.api.annotation.param.Parameter;

import com.fasterxml.jackson.annotation.JsonProperty;



public class Intent { // NOSONAR

	public Intent() {
	}


	@Parameter
	private String intentName;  
	
	@Parameter
	private String confirmationRequired;  
	

	@Parameter
	@Optional
	@NullSafe
	private List<Slot> slots ;


	@Parameter
	@Optional
	@NullSafe
	private List<String> samples;

	@Parameter
	@Optional
	@NullSafe
	private List<Prompt> prompts ;
	
	

	public Intent(String intentName, String confirmationRequired, List<Slot> slots, List<String> samples,
			List<Prompt> prompts) {
		this.intentName = intentName;
		this.confirmationRequired = confirmationRequired;
		this.slots = slots;
		this.samples = samples;
		this.prompts = prompts;
	}

	@JsonProperty("prompts")
	public List<Prompt> getPrompts() {
		return prompts;
	}

	public void setPrompts(List<Prompt> prompts) {
		this.prompts = prompts;
	}

	public String getConfirmationRequired() {
		return confirmationRequired;
	}

	public void setConfirmationRequired(String confirmationRequired) {
		this.confirmationRequired = confirmationRequired;
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
