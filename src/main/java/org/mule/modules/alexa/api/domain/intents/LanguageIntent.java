/**
 * (c) 2003-2019 MuleSoft, Inc. The software in this package is published under the terms of the Commercial Free Software license V.1 a copy of which has been included with this distribution in the LICENSE.md file.
 */
package org.mule.modules.alexa.api.domain.intents;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
	private List<Slot> slots = new ArrayList<Slot>();


	@Parameter
	@Optional
	@NullSafe
	@Expression(ExpressionSupport.SUPPORTED)
	private List<String> samples = new ArrayList<>();

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
	
	public static LanguageIntent defaultLanguageIntentFromLanaguage(LanguageIntent languageIntent){
		List<Slot> languageSlot = languageIntent.getSlots().stream().map(s -> Slot.defaultSlot(s)).collect(Collectors.toList());
		return new LanguageIntent(languageIntent.getIntentName(), 
				languageSlot, 
				languageIntent.getSamples());
	}

	
	
}
