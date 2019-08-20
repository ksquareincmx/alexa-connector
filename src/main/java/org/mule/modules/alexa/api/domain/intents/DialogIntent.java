package org.mule.modules.alexa.api.domain.intents;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DialogIntent extends Intent {

	private String confirmationRequired;

	private String delegationStrategy;

	private List<DialogSlot> dialogSlots;

	private Map<String, String> dialogPrompts ;//= Collections.emptyMap();

	public DialogIntent(String intentName, String confirmationRequired, List<DialogSlot> slots,
			Map<String, String> prompts) {
		super(intentName);
		this.confirmationRequired = confirmationRequired;
		this.dialogSlots = slots;
		this.dialogPrompts = prompts;
		
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
		return "DialogIntent [intentName=" + super.getIntentName() + ", confirmationRequired=" + confirmationRequired
				+ ", delegationStrategy=" + delegationStrategy + ", slots=" + dialogSlots + ", prompts=" + dialogPrompts
				+ "]";
	}

}
