package org.mule.modules.alexa.api.domain.update;

import java.util.List;
import java.util.Map;

public class Manifest {

	private String manifestVersion;
	private PublishInfo publishingInformation;

	private PrivacyComplaince privacyAndCompliance;

	private Events events;

	private ApiInfo apis;

	private List<Map<String, String>> permissions;

	public Manifest() {
		
	}
	
	public Manifest(String manifestVersion, PublishInfo publishingInformation, ApiInfo apis) {
		super();
		this.manifestVersion = manifestVersion;
		this.publishingInformation = publishingInformation;
		this.apis = apis;
	}

	
	public Manifest(String manifestVersion, PublishInfo publishingInformation, PrivacyComplaince privacyAndCompliance,
			Events events, ApiInfo apis, List<Map<String, String>> permissions) {
		this.manifestVersion = manifestVersion;
		this.publishingInformation = publishingInformation;
		this.privacyAndCompliance = privacyAndCompliance;
		this.events = events;
		this.apis = apis;
		this.permissions = permissions;
	}


	public String getManifestVersion() {
		return manifestVersion;
	}

	public void setManifestVersion(String manifestVersion) {
		this.manifestVersion = manifestVersion;
	}

	public PublishInfo getPublishingInformation() {
		return publishingInformation;
	}

	public void setPublishingInformation(PublishInfo publishingInformation) {
		this.publishingInformation = publishingInformation;
	}

	public PrivacyComplaince getPrivacyAndCompliance() {
		return privacyAndCompliance;
	}

	public void setPrivacyAndCompliance(PrivacyComplaince privacyAndCompliance) {
		this.privacyAndCompliance = privacyAndCompliance;
	}

	public Events getEvents() {
		return events;
	}

	public void setEvents(Events events) {
		this.events = events;
	}

	public ApiInfo getApis() {
		return apis;
	}

	public void setApis(ApiInfo apis) {
		this.apis = apis;
	}

	public List<Map<String, String>> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<Map<String, String>> permissions) {
		this.permissions = permissions;
	}

	
}
