/**
 * (c) 2003-2017 MuleSoft, Inc. The software in this package is published under the terms of the Commercial Free Software license V.1 a copy of which has been included with this distribution in the LICENSE.md file.
 */

package org.mule.modules.alexa.api.domain.intents;

import org.mule.runtime.extension.api.annotation.param.Parameter;

public class Variation {

	
	
	public Variation() {
		
	}

	
	@Parameter
	private String type;
	
	@Parameter
	private String value;
	

	public Variation(String type2, String value2) {
		this.type = type2;
		this.value = value2;
	}



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
		return "Variation [type=" + type + ", value=" + value + "]";
	}
	
	public static Variation defaultVariation(Variation variation) {
		return new Variation(variation.getType(),variation.getValue());
	}
}
