/**
 * (c) 2003-2017 MuleSoft, Inc. The software in this package is published under the terms of the Commercial Free Software license V.1 a copy of which has been included with this distribution in the LICENSE.md file.
 */

package org.mule.modules.alexa.api.domain.update;

import java.util.List;
import java.util.Map;

public class PublishInfo {
	
	private Map<String,Locale> locales;
	private boolean isAvailableWorldwide;
	private String testingInstructions;
	private String category;
	private List<String> distributionCountries;
	
	
	
	public PublishInfo(boolean isAvailableWorldwide, String testingInstructions, String category) {
		this.isAvailableWorldwide = isAvailableWorldwide;
		this.testingInstructions = testingInstructions;
		this.category = category;
	}


	public PublishInfo(Map<String, Locale> locales, boolean isAvailableWorldwide, String category,
			List<String> distributionCountries) {
		this.locales = locales;
		this.isAvailableWorldwide = isAvailableWorldwide;
		this.category = category;
		this.distributionCountries = distributionCountries;
	}
	
	
	public PublishInfo(Map<String, Locale> locales, boolean isAvailableWorldwide, String category) {
		super();
		this.locales = locales;
		this.isAvailableWorldwide = isAvailableWorldwide;
		this.category = category;
	}


	public PublishInfo(Map<String, Locale> locales, boolean isAvailableWorldwide, String testingInstructions,
			String category, List<String> distributionCountries) {
		this.locales = locales;
		this.isAvailableWorldwide = isAvailableWorldwide;
		this.testingInstructions = testingInstructions;
		this.category = category;
		this.distributionCountries = distributionCountries;
	}


	public Map<String, Locale> getLocales() {
		return locales;
	}
	public void setLocales(Map<String, Locale> locales) {
		this.locales = locales;
	}
	public boolean isAvailableWorldwide() {
		return isAvailableWorldwide;
	}
	public void setAvailableWorldwide(boolean isAvailableWorldwide) {
		this.isAvailableWorldwide = isAvailableWorldwide;
	}
	public String getTestingInstructions() {
		return testingInstructions;
	}
	public void setTestingInstructions(String testingInstructions) {
		this.testingInstructions = testingInstructions;
	}
	
	
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public List<String> getDistributionCountries() {
		return distributionCountries;
	}
	public void setDistributionCountries(List<String> distributionCountries) {
		this.distributionCountries = distributionCountries;
	}
	
	
}
