/**
 * (c) 2003-2017 MuleSoft, Inc. The software in this package is published under the terms of the Commercial Free Software license V.1 a copy of which has been included with this distribution in the LICENSE.md file.
 */

package org.mule.modules.alexa.api.domain.update;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PrivacyComplaince {

	private boolean allowsPurchases;
	private boolean usesPersonalInfo;
	private boolean isChildDirected;
	private boolean isExportCompliant;
	private boolean containsAds;
	private Map<String, Locale> locales;
	
	

	public PrivacyComplaince(boolean allowsPurchases, boolean usesPersonalInfo, boolean isChildDirected,
			boolean isExportCompliant, boolean containsAds) {
		super();
		this.allowsPurchases = allowsPurchases;
		this.usesPersonalInfo = usesPersonalInfo;
		this.isChildDirected = isChildDirected;
		this.isExportCompliant = isExportCompliant;
		this.containsAds = containsAds;
	}
	
	

	public PrivacyComplaince(final Map<String, Locale> locales) {
		
		this.allowsPurchases = false;
		this.usesPersonalInfo = false;
		this.isChildDirected = false;
		this.isExportCompliant = true;
		//this.containsAds = false;
		this.locales = locales;
	}


	@JsonProperty("allowsPurchases")
	public boolean isAllowsPurchases() {
		return allowsPurchases;
	}

	public void setAllowsPurchases(boolean allowsPurchases) {
		this.allowsPurchases = allowsPurchases;
	}

	@JsonProperty("usesPersonalInfo")
	public boolean isUsesPersonalInfo() {
		return usesPersonalInfo;
	}

	public void setUsesPersonalInfo(boolean usesPersonalInfo) {
		this.usesPersonalInfo = usesPersonalInfo;
	}

	@JsonProperty("isChildDirected")
	public boolean isChildDirected() {
		return isChildDirected;
	}

	public void setChildDirected(boolean isChildDirected) {
		this.isChildDirected = isChildDirected;
	}
	@JsonProperty("isExportCompliant")
	public boolean isExportCompliant() {
		return isExportCompliant;
	}

	public void setExportCompliant(boolean isExportCompliant) {
		this.isExportCompliant = isExportCompliant;
	}

	public boolean isContainsAds() {
		return containsAds;
	}

	public void setContainsAds(boolean containsAds) {
		this.containsAds = containsAds;
	}

	public Map<String, Locale> getLocales() {
		return locales;
	}

	public void setLocales(Map<String, Locale> locales) {
		this.locales = locales;
	}


}
