/**
 * (c) 2003-2017 MuleSoft, Inc. The software in this package is published under the terms of the Commercial Free Software license V.1 a copy of which has been included with this distribution in the LICENSE.md file.
 */

package org.mule.modules.alexa.api.domain.intents;

import java.util.List;

import org.mule.runtime.extension.api.annotation.param.Parameter;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Prompt {

	public Prompt() {
		// TODO Auto-generated constructor stub
	}
	
	@Parameter
	private String pid;

	
	
	@Parameter
	private List<Variation> variations;

	
	
	public List<Variation> getVariations() {
		return variations;
	}

	@JsonProperty("id")
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public void setVariations(List<Variation> variations) {
		this.variations = variations;
	}

	@Override
	public String toString() {
		return "Prompt [id=" + pid + ", variations=" + variations + "]";
	}
	
	
	
	
	
}
