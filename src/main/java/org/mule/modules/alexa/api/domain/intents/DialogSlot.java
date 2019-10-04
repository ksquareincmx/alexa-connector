/**
 * (c) 2003-2017 MuleSoft, Inc. The software in this package is published under the terms of the Commercial Free Software license V.1 a copy of which has been included with this distribution in the LICENSE.md file.
 */
package org.mule.modules.alexa.api.domain.intents;

import java.util.Map;

import org.mule.runtime.api.meta.ExpressionSupport;
import org.mule.runtime.extension.api.annotation.Expression;
import org.mule.runtime.extension.api.annotation.param.Optional;
import org.mule.runtime.extension.api.annotation.param.Parameter;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DialogSlot {

	
	public DialogSlot() {
		
	}
	
	public DialogSlot(String sname, String type, Map<String, String> slotprompt) {
		super();
		this.sname = sname;
		this.type = type;
		this.slotprompt = slotprompt;
	}

	@JsonProperty("name")
	@Parameter
	@Expression(ExpressionSupport.SUPPORTED)
	private String sname;

	@Optional
	@Parameter
	@Expression(ExpressionSupport.SUPPORTED)
	private String type;
	@Optional
	@Parameter
	@Expression(ExpressionSupport.SUPPORTED)
	private Map<String, String> slotprompt;


	@JsonProperty("prompts")
	public Map<String, String> getSlotprompt() {
		return slotprompt;
	}

	public void setSlotprompt(Map<String, String> slotprompt) {
		this.slotprompt = slotprompt;
	}

	public DialogSlot(String sname, String type) {
		super();
		this.sname = sname;
		this.type = type;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	public static DialogSlot defaultDialogSlot(DialogSlot dialogSlot) {
		return new DialogSlot(dialogSlot.getSname(), dialogSlot.getType(), dialogSlot.getSlotprompt());
	}
}
