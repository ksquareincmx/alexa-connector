/**
 * (c) 2003-2017 MuleSoft, Inc. The software in this package is published under the terms of the Commercial Free Software license V.1 a copy of which has been included with this distribution in the LICENSE.md file.
 */

package org.mule.modules.alexa.api.domain.intents;

import java.util.List;

import org.mule.runtime.api.meta.ExpressionSupport;
import org.mule.runtime.extension.api.annotation.Expression;
import org.mule.runtime.extension.api.annotation.param.Optional;
import org.mule.runtime.extension.api.annotation.param.Parameter;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LanguageModel {

	@Parameter
	@Optional
	@Expression(ExpressionSupport.SUPPORTED)
	public List<LanguageIntent> languateIntents;
	
	@Parameter
	@Optional
	@Expression(ExpressionSupport.SUPPORTED)
	private String invocationName;

	

	@JsonProperty("intents")
	public List<LanguageIntent> getLanguateIntents() {
		return languateIntents;
	}

	public void setLanguateIntents(List<LanguageIntent> languateIntents) {
		this.languateIntents = languateIntents;
	}

	public String getInvocationName() {
		return invocationName;
	}

	public void setInvocationName(String invocationName) {
		this.invocationName = invocationName;
	}

}
