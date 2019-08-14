package org.mule.modules.alexa.api.domain.update;

import java.util.Map;

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
		
		this.allowsPurchases = true;
		this.usesPersonalInfo = true;
		this.isChildDirected = true;
		this.isExportCompliant = true;
		this.containsAds = true;
		this.locales = locales;
	}



	public boolean isAllowsPurchases() {
		return allowsPurchases;
	}

	public void setAllowsPurchases(boolean allowsPurchases) {
		this.allowsPurchases = allowsPurchases;
	}

	public boolean isUsesPersonalInfo() {
		return usesPersonalInfo;
	}

	public void setUsesPersonalInfo(boolean usesPersonalInfo) {
		this.usesPersonalInfo = usesPersonalInfo;
	}

	public boolean isChildDirected() {
		return isChildDirected;
	}

	public void setChildDirected(boolean isChildDirected) {
		this.isChildDirected = isChildDirected;
	}

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
