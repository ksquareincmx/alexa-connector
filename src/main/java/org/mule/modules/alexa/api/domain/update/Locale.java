package org.mule.modules.alexa.api.domain.update;

import java.util.List;

public class Locale {

	private String privacyPolicyUrl;
	private String termsOfUseUrl;
	private String name;
	private String summary;
	private String description;
	private String smallIconUri;
	private String  largeIconUri;
	private List<String> keywords;
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
	
	
}
