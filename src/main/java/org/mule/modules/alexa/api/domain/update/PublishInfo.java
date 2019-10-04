/**
 * (c) 2003-2017 MuleSoft, Inc. The software in this package is published under the terms of the Commercial Free Software license V.1 a copy of which has been included with this distribution in the LICENSE.md file.
 */

package org.mule.modules.alexa.api.domain.update;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mule.modules.alexa.api.domain.data.CategoryEnum;
import org.mule.runtime.api.meta.ExpressionSupport;
import org.mule.runtime.extension.api.annotation.Expression;
import org.mule.runtime.extension.api.annotation.param.Parameter;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PublishInfo {
	
	public PublishInfo() {
		
	}
	
	public PublishInfo(Boolean isAvailableWorldwide,  CategoryEnum category,
			List<String> distributionCountries, PublishLocale locale) {
		this.locale = locale;
		this.isAvailableWorldwide = isAvailableWorldwide;
		this.category = category;
		this.distributionCountries = distributionCountries;
		localeval.put("en-US", getLocale());
	}
	
	public PublishInfo(Boolean isAvailableWorldwide, String testingInstructions, CategoryEnum category,
			List<String> distributionCountries, Map<String,PublishLocale> localeval) {
		this.localeval = localeval;
		this.isAvailableWorldwide = isAvailableWorldwide;
		this.testingInstructions = testingInstructions;
		this.category = category;
		this.distributionCountries = distributionCountries;
	}

	@Parameter
	@Expression
	private boolean isAvailableWorldwide;

	@Parameter
	@Expression
	private String testingInstructions;

	@Parameter
	@Expression(ExpressionSupport.NOT_SUPPORTED)
	private CategoryEnum category;

	@Parameter
	@Expression
	private List<String> distributionCountries;

	private Map<String, PublishLocale> localeval = new HashMap<>();

	@Parameter
	@JsonIgnore
	private PublishLocale locale;

	@JsonProperty("locales")
	public Map<String, PublishLocale> getLocaleval() {
		return localeval;
	}

	public void setLocaleval(Map<String, PublishLocale> localeval) {
		
		this.localeval = localeval;
	}


	public PublishLocale getLocale() {
		return locale;
	}

	public void setLocale(PublishLocale locale) {
		this.locale = locale;
	}

	public boolean getIsAvailableWorldwide() {
		return isAvailableWorldwide;
	}

	public void setIsAvailableWorldwide(boolean isAvailableWorldwide) {
		this.isAvailableWorldwide = isAvailableWorldwide;
	}

	public String getTestingInstructions() {
		return testingInstructions;
	}

	public void setTestingInstructions(String testingInstructions) {
		this.testingInstructions = testingInstructions;
	}

	

	

	public CategoryEnum getCategory() {
		return category;
	}

	public void setCategory(CategoryEnum category) {
		this.category = category;
	}

	public List<String> getDistributionCountries() {
		return distributionCountries;
	}

	public void setDistributionCountries(List<String> distributionCountries) {
		this.distributionCountries = distributionCountries;
	}
	public static PublishInfo defaultPublishInfo(PublishInfo info) {
		Map<String, PublishLocale> locale = new HashMap<String, PublishLocale>();
		locale.put("en-US", PublishLocale.defaultPublishLocale(info.getLocale()));
		return new PublishInfo(info.getIsAvailableWorldwide(), 
				info.getTestingInstructions(), info.getCategory(), info.getDistributionCountries(),locale );
	}
}
