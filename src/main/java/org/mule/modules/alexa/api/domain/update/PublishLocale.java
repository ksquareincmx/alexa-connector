/**
 * (c) 2003-2017 MuleSoft, Inc. The software in this package is published under the terms of the Commercial Free Software license V.1 a copy of which has been included with this distribution in the LICENSE.md file.
 */
package org.mule.modules.alexa.api.domain.update;

import java.util.List;

import org.mule.runtime.extension.api.annotation.param.Parameter;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * (c) 2003-2017 MuleSoft, Inc. The software in this package is published under
 * the terms of the Commercial Free Software license V.1 a copy of which has
 * been included with this distribution in the LICENSE.md file.
 */

public class PublishLocale {
	
	public PublishLocale() {
		
	}
	
	public PublishLocale(String skillName, String summary, String description) {
		super();
		this.skillName = skillName;
		this.summary = summary;
		this.description = description;
	}
	
	public PublishLocale(String skillName, String summary, String description, List<String> keywords,
			List<String> examplePhrases) {
		super();
		this.skillName = skillName;
		this.summary = summary;
		this.description = description;
		this.keywords = keywords;
		this.examplePhrases = examplePhrases;
	}

	@Parameter
	private String skillName;
	
	@Parameter
	private String summary;
	@Parameter
	private String description;

	@Parameter
	private List<String> keywords;

	@Parameter
	private List<String> examplePhrases;


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

	@JsonProperty("name")
	public String getSkillName() {
		return skillName;
	}

	public void setSkillName(String skillName) {
		this.skillName = skillName;
	}
	public static PublishLocale defaultPublishLocale(PublishLocale plocale) {
		return new PublishLocale(plocale.getSkillName(), plocale.getSummary(), 
				plocale.getDescription(),plocale.getKeywords(),plocale.getExamplePhrases());
	}

	@Override
	public String toString() {
		return "PublishLocale [skillName=" + skillName + ", summary=" + summary + ", description=" + description
				+ ", keywords=" + keywords + ", examplePhrases=" + examplePhrases + "]";
	}
	
}
