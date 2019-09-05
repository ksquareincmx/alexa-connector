/**
 * (c) 2003-2017 MuleSoft, Inc. The software in this package is published under the terms of the Commercial Free Software license V.1 a copy of which has been included with this distribution in the LICENSE.md file.
 */

package org.mule.modules.alexa.api.domain.update;

import java.util.List;

import org.mule.runtime.extension.api.annotation.Expression;
import org.mule.runtime.extension.api.annotation.param.Optional;
import org.mule.runtime.extension.api.annotation.param.Parameter;

public class Manifest {

	private String manifestVersion;
	
	@Parameter
	@Expression
	private PublishInfo publishingInformation;

	@Parameter
	@Expression
	@Optional
	private PrivacyComplaince privacyAndCompliance;

	@Parameter
	@Expression
	private Events events;

	@Parameter
	@Expression
	private ApiInfo apis;

	@Parameter
	private List<Permission> permissions;

	public Manifest() {
		
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

	public List<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}

	
	
}
