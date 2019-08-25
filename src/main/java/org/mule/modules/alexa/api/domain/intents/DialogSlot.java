package org.mule.modules.alexa.api.domain.intents;

import java.util.Collections;
import java.util.Map;

import org.mule.runtime.api.meta.ExpressionSupport;
import org.mule.runtime.extension.api.annotation.Expression;
import org.mule.runtime.extension.api.annotation.param.Optional;
import org.mule.runtime.extension.api.annotation.param.Parameter;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DialogSlot {

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
