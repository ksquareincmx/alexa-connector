/**
 * (c) 2003-2017 MuleSoft, Inc. The software in this package is published under the terms of the Commercial Free Software license V.1 a copy of which has been included with this distribution in the LICENSE.md file.
 */
package org.mule.modules.alexa.api.domain.intents;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
	private List<DialogSlot> dialogSlots = new ArrayList<DialogSlot>();

	@Parameter
	@Optional
	@Expression(ExpressionSupport.SUPPORTED)
	private Map<String, String> dialogPrompts = java.util.Collections.emptyMap();

	public DialogIntent(String intentName, String confirmationRequired, List<DialogSlot> slots,
			Map<String, String> dialogPrompts) {
		this.intentName = intentName;
		this.confirmationRequired = confirmationRequired;
		this.dialogSlots = slots;
		this.dialogPrompts = dialogPrompts;
		
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
				+ ", slots=" + dialogSlots + ", prompts=" + dialogPrompts
				+ "]";
	}

	public static DialogIntent defaultDialogIntent(DialogIntent dialogIntnet) {
		//slots
		List<DialogSlot> dslots=	dialogIntnet.getDialogSlots().stream().map(dslot -> DialogSlot.defaultDialogSlot(dslot)).collect(Collectors.toList());
		return new DialogIntent(dialogIntnet.getIntentName(), 
				dialogIntnet.getConfirmationRequired(), dslots, dialogIntnet.getDialogPrompts()); 
		
	}
}
