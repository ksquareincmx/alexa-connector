package org.mule.modules.alexa.api.domain.update;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mule.modules.alexa.api.domain.AlexaRequestURL;
import org.mule.runtime.extension.api.annotation.Expression;
import org.mule.runtime.extension.api.annotation.param.Parameter;

/**
 * (c) 2003-2017 MuleSoft, Inc. The software in this package is published under the terms of the Commercial Free Software license V.1 a copy of which has been included with this distribution in the LICENSE.md file.
 */

public class Locale {

	@Parameter
	@Expression
	private String privacyPolicyUrl;
	@Parameter
	@Expression
	private String termsOfUseUrl;
	@Parameter
	@Expression
	private String name;
	@Parameter
	@Expression
	private String summary;
	@Parameter
	@Expression
	private String description;
	@Parameter
	@Expression
	private String smallIconUri;
	@Parameter
	@Expression
	private String  largeIconUri;
	@Parameter
	@Expression
	private List<String> keywords;
	@Parameter
	@Expression
	private List<String>examplePhrases;
	
	public Locale(String privacyPolicyUrl, String termsOfUseUrl) {
		this.privacyPolicyUrl = privacyPolicyUrl;
		this.termsOfUseUrl = termsOfUseUrl;
	}
	
	public Locale(String name, String summary, String description, List<String> keywords, List<String> examplePhrases) {
		this.name = name;
		this.summary = summary;
		this.description = description;
		this.keywords = keywords;
		this.examplePhrases = examplePhrases;
	}
	
	public Locale(String name, String summary, String description) {
		this.name = name;
		this.summary = summary;
		this.description = description;
	}

	public Locale(String name, String summary, String description, String smallIconUri, String largeIconUri,
			List<String> keywords, List<String> examplePhrases) {
		this.name = name;
		this.summary = summary;
		this.description = description;
		this.smallIconUri = smallIconUri;
		this.largeIconUri = largeIconUri;
		this.keywords = keywords;
		this.examplePhrases = examplePhrases;
	}

	public String getPrivacyPolicyUrl() {
		return privacyPolicyUrl;
	}
	public void setPrivacyPolicyUrl(String privacyPolicyUrl) {
		this.privacyPolicyUrl = privacyPolicyUrl;
	}
	public String getTermsOfUseUrl() {
		return termsOfUseUrl;
	}
	public void setTermsOfUseUrl(String termsOfUseUrl) {
		this.termsOfUseUrl = termsOfUseUrl;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getSmallIconUri() {
		return smallIconUri;
	}
	public void setSmallIconUri(String smallIconUri) {
		this.smallIconUri = smallIconUri;
	}
	public String getLargeIconUri() {
		return largeIconUri;
	}
	public void setLargeIconUri(String largeIconUri) {
		this.largeIconUri = largeIconUri;
	}
	
	public List<String> getKeywords() {
		return keywords;
	}
	public void setKeywords(List<String> keywords) {
		this.keywords = keywords;
	}
	public List<String> getExamplePhrases() {
		return examplePhrases;
	}
	public void setExamplePhrases(List<String> examplePhrases) {
		this.examplePhrases = examplePhrases;
	}
	
	public Map<String, Locale> defalutLocale() {
		Map<String, Locale> localeObj = new HashMap<String, Locale>();
		localeObj.put(AlexaRequestURL.DEFAULT_LOCALE, this);
		return localeObj;
	}
	
}
