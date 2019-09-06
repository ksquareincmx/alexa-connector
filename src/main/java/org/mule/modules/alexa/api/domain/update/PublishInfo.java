/**
 * (c) 2003-2017 MuleSoft, Inc. The software in this package is published under the terms of the Commercial Free Software license V.1 a copy of which has been included with this distribution in the LICENSE.md file.
 */

package org.mule.modules.alexa.api.domain.update;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mule.runtime.extension.api.annotation.Expression;
import org.mule.runtime.extension.api.annotation.param.Parameter;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PublishInfo {

	@Parameter
	@Expression
	private Boolean isAvailableWorldwide;

	@Parameter
	@Expression
	private String testingInstructions;

	@Parameter
	@Expression
	private String category;

	@Parameter
	@Expression
	private List<String> distributionCountries;

	private Map<String, PublishLocale> localeval;

	@Parameter
	@JsonIgnore
	private PublishLocale locale;

	@JsonProperty("locales")
	public Map<String, PublishLocale> getLocaleval() {
		return localeval;
	}

	public void setLocaleval(Map<String, PublishLocale> localeval) {
		Map<String, PublishLocale> locale = new HashMap<String, PublishLocale>();
		locale.put("en-US", this.getLocale());
		this.localeval = locale;
	}

	public PublishInfo() {

	}

	public PublishInfo(Boolean isAvailableWorldwide, String testingInstructions, String category,
			List<String> distributionCountries, Map<String, PublishLocale> localeval) {
		this.localeval = localeval;
		this.isAvailableWorldwide = isAvailableWorldwide;
		this.testingInstructions = testingInstructions;
		this.category = category;
		this.distributionCountries = distributionCountries;
	}

	public PublishLocale getLocale() {
		return locale;
	}

	public void setLocale(PublishLocale locale) {
		this.locale = locale;
	}

	public Boolean getIsAvailableWorldwide() {
		return isAvailableWorldwide;
	}

	public void setIsAvailableWorldwide(Boolean isAvailableWorldwide) {
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
