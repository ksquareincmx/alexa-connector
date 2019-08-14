package org.mule.modules.alexa.api.domain.create;

import org.mule.modules.alexa.api.domain.update.Manifest;

public class CreateSkill {

	private String vendorId;
	
	private Manifest manifest;
	
	

	public CreateSkill(String vendorId, Manifest manifest) {
		this.vendorId = vendorId;
		this.manifest = manifest;
	}

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
