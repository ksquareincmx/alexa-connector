/**
 * (c) 2003-2017 MuleSoft, Inc. The software in this package is published under the terms of the Commercial Free Software license V.1 a copy of which has been included with this distribution in the LICENSE.md file.
 */

package org.mule.modules.alexa.api.domain.create;

import org.mule.modules.alexa.api.domain.update.Manifest;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CreateSkill {

	
	public CreateSkill() {
		super();
	}

	private String vendorId;
	
	private Manifest manifest;
	
	

	public CreateSkill(String vendorId, Manifest manifest) {
		this.vendorId = vendorId;
		this.manifest = manifest;
	}

	@JsonProperty("vendorId")
	public String getVendorId() {
		return vendorId;
	}

	public void setVendorId(String vendorId) {
		this.vendorId = vendorId;
	}

	public Manifest getManifest() {
		return manifest;
	}

	public void setManifest(Manifest manifest) {
		this.manifest = manifest;
	}
	
	
}
