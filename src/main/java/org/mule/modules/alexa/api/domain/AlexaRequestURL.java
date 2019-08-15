/**
 * (c) 2003-2017 MuleSoft, Inc. The software in this package is published under the terms of the Commercial Free Software license V.1 a copy of which has been included with this distribution in the LICENSE.md file.
 */

package org.mule.modules.alexa.api.domain;

public interface AlexaRequestURL {

	String GET_ALEXA_INFO = "https://api.amazonalexa.com/v1/skills/%s/stages/development/manifest";
	String CREATE_ALEXA_SKILL = "https://api.amazonalexa.com/v1/skills";
	String UPDATE_ALEXA_SKILL =  "https://api.amazonalexa.com/v1/skills/%s/stages/development/interactionModel/locales/en-US";
	String TEST_ALEXA_SKILL = "https://api.amazonalexa.com/v2/skills/%s/stages/%s/invocations";
	String UPDATE_EXISTING_SKILL = "https://api.amazonalexa.com/v1/skills/%s/stages/development/manifest";
}
