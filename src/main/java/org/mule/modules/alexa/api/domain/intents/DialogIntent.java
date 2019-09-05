package org.mule.modules.alexa.api.domain.intents;

import java.util.List;
import java.util.Map;

import org.mule.runtime.api.meta.ExpressionSupport;
import org.mule.runtime.extension.api.annotation.Expression;
import org.mule.runtime.extension.api.annotation.param.Optional;
import org.mule.runtime.extension.api.annotation.param.Parameter;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DialogIntent {

	public DialogIntent() {
	}
	
	@Parameter
	private String intentName;  
	

	@Parameter
	@Optional
	@Expression(ExpressionSupport.SUPPORTED)
	private String confirmationRequired;

	@Parameter
	@Optional
	@Expression(ExpressionSupport.SUPPORTED)
	private String delegationStrategy;

	@Parameter
	@Optional
	@Expression(ExpressionSupport.SUPPORTED)
	private List<DialogSlot> dialogSlots;

	@Parameter
	@Optional
	@Expression(ExpressionSupport.SUPPORTED)
	private Map<String, String> dialogPrompts ;//= Collections.emptyMap();

	public DialogIntent(String intentName, String confirmationRequired, List<DialogSlot> slots,
			Map<String, String> prompts) {
		this.intentName = intentName;
		this.confirmationRequired = confirmationRequired;
		this.dialogSlots = slots;
		this.dialogPrompts = prompts;
		
	}
	@JsonProperty("name")
	public String getIntentName() {
		return intentName;
	}

	public void setIntentName(String intentName) {
		this.intentName = intentName;
	}

	public String getConfirmationRequired() {
		return confirmationRequired;
	}

	public void setConfirmationRequired(String confirmationRequired) {
		this.confirmationRequired = confirmationRequired;
	}

	public String getDelegationStrategy() {
		return delegationStrategy;
	}

	public void setDelegationStrategy(String delegationStrategy) {
		this.delegationStrategy = delegationStrategy;
	}

	@JsonProperty("slots")
	public List<DialogSlot> getDialogSlots() {
		return dialogSlots;
	}

	public void setDialogSlots(List<DialogSlot> dialogSlots) {
		this.dialogSlots = dialogSlots;
	}

	@JsonProperty("prompts")
	public Map<String, String> getDialogPrompts() {
		return dialogPrompts;
	}

	public void setDialogPrompts(Map<String, String> dialogPrompts) {
		this.dialogPrompts = dialogPrompts;
	}

	@Override
	public String toString() {
		return "DialogIntent [intentName=" + intentName + ", confirmationRequired=" + confirmationRequired
				+ ", delegationStrategy=" + delegationStrategy + ", slots=" + dialogSlots + ", prompts=" + dialogPrompts
				+ "]";
	}

}
