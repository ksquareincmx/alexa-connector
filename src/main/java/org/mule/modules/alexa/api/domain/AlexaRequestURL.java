/**
 * (c) 2003-2017 MuleSoft, Inc. The software in this package is published under the terms of the Commercial Free Software license V.1 a copy of which has been included with this distribution in the LICENSE.md file.
 */

package org.mule.modules.alexa.api.domain;
/**
 * 
 * Class used to maintain constants of module
 *
 */
public interface AlexaRequestURL {

	String BASE_URL = "https://api.amazonalexa.com/v1/skills";
	String GET_ALEXA_INFO = BASE_URL+"/%s/stages/development/manifest";
	String CREATE_ALEXA_SKILL = BASE_URL;
	String UPDATE_ALEXA_SKILL =  BASE_URL+"/%s/stages/development/interactionModel/locales/en-US";
	String TEST_ALEXA_SKILL = BASE_URL+"/%s/stages/%s/invocations";
	String UPDATE_EXISTING_SKILL = BASE_URL+"/%s/stages/development/manifest";
	String UPDATE_INTERACTION_SCHEMA = BASE_URL+"/%s/stages/development/interactionModel/locales/en-US";
	
}
