package org.mule.modules.alexa.api.domain.update;

import org.mule.modules.alexa.api.domain.data.InterfacesEnum;
import org.mule.runtime.api.meta.ExpressionSupport;
import org.mule.runtime.extension.api.annotation.Expression;
import org.mule.runtime.extension.api.annotation.param.Optional;
import org.mule.runtime.extension.api.annotation.param.Parameter;

public class Interface {

	public Interface(InterfacesEnum type) {
		this.type = type;
	}

	public Interface() {

	}

	@Parameter
	@Optional
	@Expression(ExpressionSupport.NOT_SUPPORTED)
	private InterfacesEnum type;

	

	public InterfacesEnum getType() {
		return type;
	}

	public void setType(InterfacesEnum type) {
		this.type = type;
	}

	public static Interface defaultInterface(Interface interFace) {
		return new Interface(interFace.getType());
	}

}
