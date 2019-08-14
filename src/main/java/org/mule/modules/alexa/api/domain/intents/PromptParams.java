package org.mule.modules.alexa.api.domain.intents;

import org.mule.runtime.extension.api.annotation.param.NullSafe;
import org.mule.runtime.extension.api.annotation.param.Optional;
import org.mule.runtime.extension.api.annotation.param.Parameter;

public class PromptParams {

	@Parameter
	@Optional
	@NullSafe
	private String promtSlotName;

	@Parameter
	@Optional
	@NullSafe
	private String PromtType;

	@Parameter
	@Optional
	@NullSafe
	private String PromtValue;

	public String getPromtType() {
		return PromtType;
	}

	public void setPromtType(String promtType) {
		PromtType = promtType;
	}

	public String getPromtValue() {
		return PromtValue;
	}

	public void setPromtValue(String promtValue) {
		PromtValue = promtValue;
	}

	public String getPromtSlotName() {
		return promtSlotName;
	}

	public void setPromtSlotName(String promtSlotName) {
		this.promtSlotName = promtSlotName;
	}

	@Override
	public String toString() {
		return "PromptParams [promtSlotName=" + promtSlotName + ", PromtType=" + PromtType + ", PromtValue="
				+ PromtValue + "]";
	}

}
