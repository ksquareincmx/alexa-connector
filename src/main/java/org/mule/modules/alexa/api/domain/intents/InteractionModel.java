/**
 * (c) 2003-2017 MuleSoft, Inc. The software in this package is published under the terms of the Commercial Free Software license V.1 a copy of which has been included with this distribution in the LICENSE.md file.
 */

package org.mule.modules.alexa.api.domain.intents;

import java.util.List;

import org.mule.runtime.api.meta.ExpressionSupport;
import org.mule.runtime.extension.api.annotation.Expression;
import org.mule.runtime.extension.api.annotation.param.Optional;
import org.mule.runtime.extension.api.annotation.param.Parameter;

public class InteractionModel {

	
	
	
	public InteractionModel() {
		super();
	}
	public InteractionModel(Dialog dialog, LanguageModel languageModel, List<Prompt> prompts) {
		super();
		this.dialog = dialog;
		this.languageModel = languageModel;
		this.prompts = prompts;
	}
	@Parameter
	@Optional
	@Expression(ExpressionSupport.SUPPORTED)
	private Dialog dialog;
	@Parameter
	@Optional
	@Expression(ExpressionSupport.SUPPORTED)
	private LanguageModel languageModel;
	
	
	@Parameter
	@Optional
	@Expression(ExpressionSupport.SUPPORTED)
	private List<Prompt> prompts;
	
	public Dialog getDialog() {
		return dialog;
	}
	public void setDialog(Dialog dialog) {
		this.dialog = dialog;
	}
	public LanguageModel getLanguageModel() {
		return languageModel;
	}
	public void setLanguageModel(LanguageModel languageModel) {
		this.languageModel = languageModel;
	}
	public List<Prompt> getPrompts() {
		return prompts;
	}
	public void setPrompts(List<Prompt> prompts) {
		this.prompts = prompts;
	}
	
	
}
