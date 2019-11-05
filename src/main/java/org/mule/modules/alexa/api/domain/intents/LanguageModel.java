/**
 * (c) 2003-2019 MuleSoft, Inc. The software in this package is published under the terms of the Commercial Free Software license V.1 a copy of which has been included with this distribution in the LICENSE.md file.
 */

package org.mule.modules.alexa.api.domain.intents;

import java.util.List;
import java.util.stream.Collectors;

import org.mule.runtime.api.meta.ExpressionSupport;
import org.mule.runtime.extension.api.annotation.Expression;
import org.mule.runtime.extension.api.annotation.param.Optional;
import org.mule.runtime.extension.api.annotation.param.Parameter;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LanguageModel {


	
	public LanguageModel() {
		
	}

	public LanguageModel(List<LanguageIntent> languateIntents2, String invocationName2) {
		this.languateIntents= languateIntents2;
		this.invocationName = invocationName2;
	}
	
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

	
	public static LanguageModel defaultLanguageModel(LanguageModel languageModel) {
		
		return new LanguageModel(languageModel.getLanguateIntents().stream().map(li -> LanguageIntent.defaultLanguageIntentFromLanaguage(li)).collect(Collectors.toList()),
				languageModel.getInvocationName());
	}
	
	
}
