package org.mule.modules.alexa.api.domain.intents;

import org.mule.runtime.extension.api.annotation.param.NullSafe;
import org.mule.runtime.extension.api.annotation.param.Optional;
import org.mule.runtime.extension.api.annotation.param.Parameter;

public class PromptInfo {

	@Parameter
	@Optional
	@NullSafe
	private String type;

	@Parameter
	@Optional
	@NullSafe
	private String value;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "PromptInfo [type=" + type + ", value=" + value + "]";
	}
	
	
}
