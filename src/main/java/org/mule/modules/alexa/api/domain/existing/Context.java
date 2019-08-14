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
