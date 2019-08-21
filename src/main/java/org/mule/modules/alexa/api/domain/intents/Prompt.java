/**
 * (c) 2003-2017 MuleSoft, Inc. The software in this package is published under the terms of the Commercial Free Software license V.1 a copy of which has been included with this distribution in the LICENSE.md file.
 */

package org.mule.modules.alexa.api.domain.intents;

import java.util.List;

import org.mule.runtime.extension.api.annotation.param.NullSafe;
import org.mule.runtime.extension.api.annotation.param.Optional;
import org.mule.runtime.extension.api.annotation.param.Parameter;

public class Prompt {

	@Parameter
	@Optional
	@NullSafe
	private String id;

	@Parameter
	@NullSafe
	@Optional
	List<PromptInfo> vmap;
	
	private List<Variation> variations;

	public List<PromptInfo> getVmap() {
		return vmap;
	}

	public void setVmap(List<PromptInfo> vmap) {
		
		this.vmap = vmap;
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<Variation> getVariations() {
		return variations;
	}

	public void setVariations(List<Variation> variations) {
		this.variations = variations;
	}

	@Override
	public String toString() {
		return "Prompt [id=" + id + ", vmap=" + vmap + ", variations=" + variations + "]";
	}
	
	
	
	
	
}
