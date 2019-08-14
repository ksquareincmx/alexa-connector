package org.mule.modules.alexa.api.domain.update;

public class UpdateSkill {
	
	private Manifest manifest;

	public UpdateSkill(Manifest manifest) {
		this.manifest = manifest;
	}

	public Manifest getManifest() {
		return manifest;
	}

	public void setManifest(Manifest manifest) {
		this.manifest = manifest;
	}
	
}
