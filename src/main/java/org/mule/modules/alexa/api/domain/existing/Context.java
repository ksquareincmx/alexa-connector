/**
 * (c) 2003-2017 MuleSoft, Inc. The software in this package is published under the terms of the Commercial Free Software license V.1 a copy of which has been included with this distribution in the LICENSE.md file.
 */

package org.mule.modules.alexa.api.domain.existing;



public class Context {
	
	private Sstem System;

	public Sstem getSystem() {
		return System;
	}

	public void setSystem(Sstem system) {
		System = system;
	}

	@Override
	public String toString() {
		return "Context [System=" + System + "]";
	}

	public Context(Sstem system) {
		super();
		System = system;
	}
	
	
	

}
