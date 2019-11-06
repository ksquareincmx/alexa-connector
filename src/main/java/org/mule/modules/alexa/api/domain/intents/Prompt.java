/**
 * (c) 2003-2019 MuleSoft, Inc. The software in this package is published under the terms of the Commercial Free Software license V.1 a copy of which has been included with this distribution in the LICENSE.md file.
 */

package org.mule.modules.alexa.api.domain.intents;

import java.util.List;
import java.util.stream.Collectors;

import org.mule.runtime.extension.api.annotation.param.Parameter;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Prompt {

	public Prompt() {
	}
	
	
	
	public Prompt(String pid, List<Variation> variations) {
		super();
		this.pid = pid;
		this.variations = variations;
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
	
	
	public static Prompt defaultPrompt(Prompt prompt) {
		return new Prompt(prompt.getPid(),
				prompt.getVariations().stream().map(v -> Variation.defaultVariation(v)).collect(Collectors.toList()));
	}
	
	
}
