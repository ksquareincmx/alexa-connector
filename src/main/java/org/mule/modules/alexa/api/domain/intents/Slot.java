/**
 * (c) 2003-2019 MuleSoft, Inc. The software in this package is published under the terms of the Commercial Free Software license V.1 a copy of which has been included with this distribution in the LICENSE.md file.
 */

package org.mule.modules.alexa.api.domain.intents;

import java.util.LinkedList;
import java.util.List;

import org.mule.runtime.api.meta.ExpressionSupport;
import org.mule.runtime.extension.api.annotation.Expression;
import org.mule.runtime.extension.api.annotation.param.NullSafe;
import org.mule.runtime.extension.api.annotation.param.Optional;
import org.mule.runtime.extension.api.annotation.param.Parameter;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Slot {

	public Slot() {
		
	}
	
	public Slot(String sname, String type, List<String> samples) {
		super();
		this.sname = sname;
		this.type = type;
		this.samples = samples;
	}

	@Parameter
	@Optional
	@NullSafe
	private String sname; 
	
	@JsonProperty("name")
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

	@Parameter
	@Optional
	@NullSafe
	private String type;
	
	@Parameter
	@Optional
	@NullSafe
	@Expression(ExpressionSupport.NOT_SUPPORTED)
	private List<String> samples = new LinkedList<>();

	

	@Override
	public String toString() {
		return "Slot [name=" + sname + ", type=" + type + ", samples=" + samples + "]";
	}

	public List<String> getSamples() {
		return samples;
	}
	

	public void setSamples(List<String> samples) {
		this.samples = samples;
	}
	public static Slot defaultSlot(Slot slot) {

	return new Slot(slot.getSname(), slot.getType(),null);
	}

	

}
