/**
 * (c) 2003-2017 MuleSoft, Inc. The software in this package is published under the terms of the Commercial Free Software license V.1 a copy of which has been included with this distribution in the LICENSE.md file.
 */

package org.mule.modules.alexa.api.domain.intents;

import java.util.List;

public class InteractionModel {

	private Dialog dialog;
	private LanguageModel languageModel;
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
