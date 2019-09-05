/**
 * (c) 2003-2017 MuleSoft, Inc. The software in this package is published under the terms of the Commercial Free Software license V.1 a copy of which has been included with this distribution in the LICENSE.md file.
 */

package org.mule.modules.alexa.api.domain.update;

import java.util.HashMap;
import java.util.Map;

import org.mule.runtime.extension.api.annotation.Expression;
import org.mule.runtime.extension.api.annotation.param.Optional;
import org.mule.runtime.extension.api.annotation.param.Parameter;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PrivacyComplaince {

	@Parameter
	@Expression
	private boolean allowsPurchases;
	@Parameter
	@Expression
	private boolean usesPersonalInfo;
	@Parameter
	@Expression
	private boolean isChildDirected;
	@Parameter
	@Expression
	private boolean isExportCompliant;
	@Parameter
	@Expression
	private boolean containsAds;
	
	@Parameter
	@Optional
	private Map<String, Locale> localemap;
	
	public PrivacyComplaince() {
		
	}

	

	public PrivacyComplaince(boolean allowsPurchases, boolean usesPersonalInfo, boolean isChildDirected,
			boolean isExportCompliant, boolean containsAds, Map<String, Locale> localemap) {
		super();
		this.allowsPurchases = allowsPurchases;
		this.usesPersonalInfo = usesPersonalInfo;
		this.isChildDirected = isChildDirected;
		this.isExportCompliant = isExportCompliant;
		this.containsAds = containsAds;
		this.localemap = localemap;
	}



	public PrivacyComplaince(Map<String, Locale> localesPrivacy) {
		// TODO Auto-generated constructor stub
		this.localemap = localesPrivacy;
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


	@JsonProperty("locales")
	public Map<String, Locale> getLocalemap() {
		return localemap;
	}



	public void setLocalemap(Map<String, Locale> localemap) {
		this.localemap = localemap;
	}

	


}
