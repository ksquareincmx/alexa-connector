package org.mule.modules.alexa.api.domain.intents;

import java.util.Collections;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DialogSlot {

	@JsonProperty("name")
	private String sname; 
	
	private String type;
	private Map<String,String> prompts = Collections.emptyMap();
	
	public DialogSlot() {
		
	}
	
	
	
	public Map<String, String> getPrompts() {
		return prompts;
	}


	public void setPrompts(Map<String, String> prompts) {
		this.prompts = prompts;
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


}
