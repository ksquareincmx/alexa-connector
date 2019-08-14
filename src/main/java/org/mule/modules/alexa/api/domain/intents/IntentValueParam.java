package org.mule.modules.alexa.api.domain.intents;

import java.util.LinkedList;
import java.util.List;

import org.mule.runtime.api.meta.ExpressionSupport;
import org.mule.runtime.extension.api.annotation.Expression;
import org.mule.runtime.extension.api.annotation.param.ExclusiveOptionals;
import org.mule.runtime.extension.api.annotation.param.NullSafe;
import org.mule.runtime.extension.api.annotation.param.Optional;
import org.mule.runtime.extension.api.annotation.param.Parameter;

@ExclusiveOptionals
public class IntentValueParam {

	@Parameter
	private String intentName;  

	@Parameter
	@Optional
	@NullSafe
	@Expression(ExpressionSupport.NOT_SUPPORTED)
	private List<SlotParams> slots = new LinkedList<>();

	@Parameter
	@Optional
	@NullSafe
	@Expression(ExpressionSupport.NOT_SUPPORTED)
	private List<String> samples = new LinkedList<>();

	@Parameter
	@Optional
	@NullSafe
	@Expression(ExpressionSupport.NOT_SUPPORTED)
	private List<PromptParams> promts = new LinkedList<>();

	public List<PromptParams> getPromts() {
		return promts;
	}

	public void setPromts(List<PromptParams> promts) {
		this.promts = promts;
	}

	public String getIntentName() {
		return intentName;
	}

	public void setIntentName(String intentName) {
		this.intentName = intentName;
	}

	public List<SlotParams> getSlots() {
		return slots;
	}

	public void setSlots(List<SlotParams> slots) {
		this.slots = slots;
	}

	public List<String> getSamples() {
		return samples;
	}

	public void setSamples(List<String> samples) {
		this.samples = samples;
	}

	@Override
	public String toString() {
		return "IntentValueParam [intentName=" + intentName + ", slots=" + slots + ", samples=" + samples + ", promts="
				+ promts + "]";
	}

}
