/**
 * (c) 2003-2017 MuleSoft, Inc. The software in this package is published under the terms of the Commercial Free Software license V.1 a copy of which has been included with this distribution in the LICENSE.md file.
 */

package org.mule.modules.alexa.api.domain.intents;

import java.util.List;
import java.util.stream.Collectors;

import org.mule.runtime.api.meta.ExpressionSupport;
import org.mule.runtime.extension.api.annotation.Expression;
import org.mule.runtime.extension.api.annotation.param.Optional;
import org.mule.runtime.extension.api.annotation.param.Parameter;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Dialog {

	public Dialog() {
		// TODO Auto-generated constructor stub
	}

	public Dialog(List<DialogIntent> dialogIntents, String delegationStrategy) {
		super();
		this.dialogIntents = dialogIntents;
		this.delegationStrategy = delegationStrategy;
	}

	@Parameter
	@Optional
	private List<DialogIntent> dialogIntents;

	@Parameter
	@Optional
	@Expression(ExpressionSupport.SUPPORTED)
	private String delegationStrategy = "ALWAYS";

	@JsonProperty("intents")
	public List<DialogIntent> getDialogIntents() {
		return dialogIntents;
	}

	public void setDialogIntents(List<DialogIntent> dialogIntents) {
		this.dialogIntents = dialogIntents;
	}

	public String getDelegationStrategy() {
		return delegationStrategy;
	}

	public void setDelegationStrategy(String delegationStrategy) {
		this.delegationStrategy = delegationStrategy;
	}

	public static Dialog defaultDialog(Dialog dialog) {
		List<DialogIntent> dintents = dialog.getDialogIntents().stream().map(di -> DialogIntent.defaultDialogIntent(di))
				.collect(Collectors.toList());
		return new Dialog(dintents, dialog.delegationStrategy);
	}

}
