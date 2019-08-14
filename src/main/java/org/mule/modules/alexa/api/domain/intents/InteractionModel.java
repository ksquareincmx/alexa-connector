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
