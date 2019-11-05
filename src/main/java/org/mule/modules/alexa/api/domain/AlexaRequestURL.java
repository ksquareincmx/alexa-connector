/**
 * (c) 2003-2019 MuleSoft, Inc. The software in this package is published under the terms of the Commercial Free Software license V.1 a copy of which has been included with this distribution in the LICENSE.md file.
 */

package org.mule.modules.alexa.api.domain;

/**
 * 
 * Class used to maintain constants of module
 *
 */
public final class AlexaRequestURL {

	private AlexaRequestURL() {
		
	}
	public static final String BASE_URL = "https://api.amazonalexa.com/v1/skills";
	public static final String GET_ALEXA_INFO = BASE_URL + "/%s/stages/development/manifest";
	public static final String CREATE_ALEXA_SKILL = BASE_URL;
	public static final String UPDATE_ALEXA_SKILL = BASE_URL + "/%s/stages/development/interactionModel/locales/en-US";
	public static final String TEST_ALEXA_SKILL = BASE_URL + "/%s/stages/%s/invocations";
	public static final String UPDATE_SKILL_MANIFEST = BASE_URL + "/%s/stages/development/manifest";
	public static final String UPDATE_SKILL_INTERACTION_SCHEMA = BASE_URL + "/%s/stages/development/interactionModel/locales/en-US";
	public static final String DELETE_SKILL = BASE_URL  + "/%s/";
	public static final String TRUSTED_CERT = "Trusted";
	public static final String ACCEPTED = "{}";
	public static final String STATUS_HEADER = "Location";
	public static final String DEFAULT_LOCALE ="en-US";

}
