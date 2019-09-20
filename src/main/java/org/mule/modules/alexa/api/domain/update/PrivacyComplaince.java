/**
 * (c) 2003-2017 MuleSoft, Inc. The software in this package is published under the terms of the Commercial Free Software license V.1 a copy of which has been included with this distribution in the LICENSE.md file.
 */

package org.mule.modules.alexa.api.domain.update;

import java.util.HashMap;
import java.util.Map;

import org.mule.runtime.extension.api.annotation.Expression;
import org.mule.runtime.extension.api.annotation.param.Parameter;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
	@JsonIgnore
	private PrivacyLocale plocale;

	private Map<String, PrivacyLocale> priLocalemap = new HashMap<>();

	public PrivacyComplaince() {

	}

	public PrivacyComplaince(boolean allowsPurchases, boolean usesPersonalInfo, boolean isChildDirected,
			boolean isExportCompliant, boolean containsAds) {
		super();
		this.allowsPurchases = allowsPurchases;
		this.usesPersonalInfo = usesPersonalInfo;
		this.isChildDirected = isChildDirected;
		this.isExportCompliant = isExportCompliant;
		this.containsAds = containsAds;
		priLocalemap.put("en-US", getPlocale());
	}
	
	public PrivacyComplaince(boolean allowsPurchases, boolean usesPersonalInfo, boolean isChildDirected,
			boolean isExportCompliant, boolean containsAds,Map<String, PrivacyLocale> priLocalemap) {
		super();
		this.priLocalemap = priLocalemap;
		this.allowsPurchases = allowsPurchases;
		this.usesPersonalInfo = usesPersonalInfo;
		this.isChildDirected = isChildDirected;
		this.isExportCompliant = isExportCompliant;
		this.containsAds = containsAds;
	}

	

	@JsonProperty("allowsPurchases")
	public boolean isAllowsPurchases() {
		return allowsPurchases;
	}

	@JsonProperty("locales")
	public Map<String, PrivacyLocale> getPriLocalemap() {
		return priLocalemap;
	}

	public void setPriLocalemap(Map<String, PrivacyLocale> priLocalemap) {
		this.priLocalemap = priLocalemap;
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

	public PrivacyLocale getPlocale() {
		return plocale;
	}

	public void setPlocale(PrivacyLocale plocale) {
		this.plocale = plocale;
	}

	public static PrivacyComplaince defaultComplaince(Map<String,PrivacyLocale> privacyLocale) {
		return new PrivacyComplaince(false, false, false, false, false,privacyLocale);
	}
	
	public static PrivacyComplaince defaultComplaince(PrivacyComplaince complaince) {
		Map<String,PrivacyLocale> locales = new HashMap<String, PrivacyLocale>();
		locales.put("en-US", PrivacyLocale.defaultPrivacyLocale(complaince.getPlocale()));
		return new PrivacyComplaince(false, false, false, false, false,locales);
	}
	
	
	

}
