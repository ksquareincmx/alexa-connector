package org.mule.modules.alexa.api.domain;

public interface AlexaRequestURL {

	String GET_ALEXA_INFO = "https://api.amazonalexa.com/v1/skills/%s/stages/development/manifest";
	String CREATE_ALEXA_SKILL = "https://api.amazonalexa.com/v1/skills";
	String UPDATE_ALEXA_SKILL =  "https://api.amazonalexa.com/v1/skills/%s/stages/development/interactionModel/locales/en-US";
	String TEST_ALEXA_SKILL = "https://api.amazonalexa.com/v2/skills/%s/stages/%s/invocations";
	String UPDATE_EXISTING_SKILL = "https://api.amazonalexa.com/v1/skills/%s/stages/development/manifest";
}
