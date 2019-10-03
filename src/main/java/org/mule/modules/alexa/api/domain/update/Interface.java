/**
 * (c) 2003-2017 MuleSoft, Inc. The software in this package is published under the terms of the Commercial Free Software license V.1 a copy of which has been included with this distribution in the LICENSE.md file.
 */
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
