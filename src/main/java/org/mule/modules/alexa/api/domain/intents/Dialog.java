/**
 * (c) 2003-2017 MuleSoft, Inc. The software in this package is published under the terms of the Commercial Free Software license V.1 a copy of which has been included with this distribution in the LICENSE.md file.
 */

package org.mule.modules.alexa.api.domain.intents;

import java.util.List;

public class Dialog {


	private List<DialogIntent> intents;

	private String delegationStrategy = "ALWAYS";

	public List<DialogIntent> getIntents() {
		return intents;
	}
	

	public void setIntents(List<DialogIntent> intents) {
		this.intents = intents;
	}

	public String getDelegationStrategy() {
		return delegationStrategy;
	}

	public void setDelegationStrategy(String delegationStrategy) {
		this.delegationStrategy = delegationStrategy;
	}
	
}
