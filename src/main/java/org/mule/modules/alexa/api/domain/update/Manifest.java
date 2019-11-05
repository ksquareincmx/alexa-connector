/**
 * (c) 2003-2019 MuleSoft, Inc. The software in this package is published under the terms of the Commercial Free Software license V.1 a copy of which has been included with this distribution in the LICENSE.md file.
 */

package org.mule.modules.alexa.api.domain.update;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.mule.runtime.extension.api.annotation.Expression;
import org.mule.runtime.extension.api.annotation.param.Optional;
import org.mule.runtime.extension.api.annotation.param.Parameter;

public class Manifest {

	public Manifest() {
		super();
	}
	
	public Manifest(String manifestVersion, PublishInfo publishingInformation, PrivacyComplaince privacyAndCompliance,
			Events events, ApiInfo apis, List<Permission> permissions) {
		super();
		this.manifestVersion = manifestVersion;
		this.publishingInformation = publishingInformation;
		this.privacyAndCompliance = privacyAndCompliance;
		this.events = events;
		this.apis = apis;
		this.permissions = permissions;
	}
	private String manifestVersion;

	@Parameter
	private PublishInfo publishingInformation;

	@Parameter
	@Optional
	private PrivacyComplaince privacyAndCompliance;

	@Parameter
	@Expression
	private Events events;

	@Parameter
	private ApiInfo apis;

	@Parameter
	private List<Permission> permissions = new ArrayList<Permission>();


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

	public static Manifest defaultManifest(Manifest manifest) {

		// TODO: Because of CGLIB invoking by mule need to recreate the structure//
		// manually, bug
		// PublishInfo
		PublishInfo publishInfo = PublishInfo.defaultPublishInfo(manifest.getPublishingInformation());
		// PrivacyComplaince
		PrivacyComplaince complaince = PrivacyComplaince.defaultComplaince(manifest.getPrivacyAndCompliance());
		// Events
		Events events = Events.defaultEvents(manifest.getEvents());
		// ApiInfo
		ApiInfo apiInfo = ApiInfo.defaultApi(manifest.getApis());
		// Build Manifest
		List<Permission> permissions = manifest.getPermissions().stream().map(p -> Permission.defaultPermision(p))
				.collect(Collectors.toList());
		return new Manifest(manifest.getManifestVersion(), publishInfo, complaince, events, apiInfo, permissions);
	}

}
