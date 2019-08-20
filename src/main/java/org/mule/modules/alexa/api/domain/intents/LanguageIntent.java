package org.mule.modules.alexa.api.domain.intents;

import java.util.List;

public class LanguageIntent extends Intent{
	
	public LanguageIntent(String name ,List<Slot> slot, List<String> samples){
		super(name,slot,samples);
	}

}
