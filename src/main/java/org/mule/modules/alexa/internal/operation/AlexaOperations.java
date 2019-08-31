/**
 * (c) 2003-2017 MuleSoft, Inc. The software in this package is published under the terms of the Commercial Free Software license V.1 a copy of which has been included with this distribution in the LICENSE.md file.
 */

package org.mule.modules.alexa.internal.operation;

import static org.mule.runtime.api.meta.ExpressionSupport.SUPPORTED;
import static org.mule.runtime.extension.api.annotation.param.MediaType.ANY;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.mule.modules.alexa.api.domain.AlexaRequestURL;
import org.mule.modules.alexa.api.domain.intents.Intent;
import org.mule.modules.alexa.api.domain.intents.InteractionModel;
import org.mule.modules.alexa.api.domain.update.Manifest;
import org.mule.modules.alexa.internal.connection.AlexaConnection;
import org.mule.modules.alexa.internal.error.AlexaApiErrorType;
import org.mule.modules.alexa.internal.exceptions.AlexaApiException;
import org.mule.modules.alexa.internal.util.AlexaRequestBuilder;
import org.mule.modules.alexa.internal.util.AlexaRequestUtility;
import org.mule.runtime.api.meta.ExpressionSupport;
import org.mule.runtime.extension.api.annotation.Alias;
import org.mule.runtime.extension.api.annotation.Expression;
import org.mule.runtime.extension.api.annotation.dsl.xml.ParameterDsl;
import org.mule.runtime.extension.api.annotation.param.Connection;
import org.mule.runtime.extension.api.annotation.param.MediaType;
import org.mule.runtime.extension.api.annotation.param.NullSafe;
import org.mule.runtime.extension.api.annotation.param.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;

/**
 * This class is a container for operations, every public method in this class
 * will be taken as an extension operation.
 */

public class AlexaOperations {

	private static final Logger LOGGER = LoggerFactory.getLogger(AlexaOperations.class);

	private AlexaRequestUtility alexaRequestUtility = new AlexaRequestUtility();
	private AlexaRequestBuilder alexaRequestBuilder = new AlexaRequestBuilder();

	/**
	 * This method used for creating skill
	 * @param alexaConnection
	 * @param vendorId  
	 * @param summary
	 * @param examplePhrases
	 * @param keywords
	 * @param skillName
	 * @param description
	 * @param endpoint
	 * @param intents
	 * @return String
	 */ 
	@MediaType(value = ANY, strict = false)
	@Alias("createSkill")
	public String createSkill(@Connection AlexaConnection alexaConnection,  @Expression(SUPPORTED) String vendorId,
			@Expression(SUPPORTED) String summary,  @Expression(SUPPORTED) String skillName,
			@Expression(SUPPORTED) String description,  @Expression(SUPPORTED) String endpoint,
			@Optional @NullSafe @Expression(ExpressionSupport.NOT_SUPPORTED) @ParameterDsl(allowReferences = false) List<Intent> intents) {

		LOGGER.debug("Access token {}", alexaConnection.getAccessToken());
		String alexaRequest = alexaRequestBuilder.createAlexaSkillRequestBuilder(vendorId, summary, skillName, description, endpoint);
		String createSkillResponse = alexaRequestUtility.doPost(AlexaRequestURL.CREATE_ALEXA_SKILL,
				alexaConnection.getAccessToken(), alexaRequest);
		LOGGER.info("Create Alexa Skill Response {}", createSkillResponse);
		Map<String, String> response ;
		try {
			response = alexaRequestBuilder.getMapper().readValue(createSkillResponse,
					new TypeReference<Map<String, String>>() {
					});
		} catch (IOException io) {
			LOGGER.error("Excption while processing create SKill response {} ", io);
			throw new AlexaApiException(io.getMessage(), AlexaApiErrorType.JSON_PARSER_EXCEPTION);
		}
		String skillId = response.get("skillId");
		if(skillId == null || Objects.isNull(skillId)) {
			throw new AlexaApiException("SkillId not found in response "+createSkillResponse , AlexaApiErrorType.VALIDATIONS);
		}
		LOGGER.info("Skill ID after parsing", skillId);

		String updateSkillRequest = alexaRequestBuilder.updateCreatedSkill(skillId, intents);
		return alexaRequestUtility.doPut(String.format(AlexaRequestURL.UPDATE_ALEXA_SKILL, skillId),
				alexaConnection.getAccessToken(), updateSkillRequest);

	}

	@MediaType(value = ANY, strict = false)
	@Alias("skillInfo")
	public String getSkillInfo(@Connection AlexaConnection alexaConnection,  @Expression(SUPPORTED) String skillId)
			throws AlexaApiException {
		LOGGER.debug("Alexa Authorization  Token {}", alexaConnection.getAccessToken());
		return alexaRequestUtility.doGet(AlexaRequestURL.GET_ALEXA_INFO, alexaConnection.getAccessToken(), skillId);
	}

	@MediaType(value = ANY, strict = false)
	@Alias("modifyExistingSkill")
	public String UseExistingSkill(@Connection AlexaConnection alexaConnection,  @Expression(SUPPORTED) String skillId,
			@Expression(SUPPORTED) String stage,  @Expression(SUPPORTED) String requestType,
			 @Expression(SUPPORTED) String intentName, @Expression(SUPPORTED) String inputString,
			@Optional @NullSafe @Expression(ExpressionSupport.NOT_SUPPORTED) @ParameterDsl(allowReferences = false) Map<String, String> testSlots)
			throws AlexaApiException {

		LOGGER.debug("Alexa Authorization  Token: " + alexaConnection.getAccessToken());

		String testAlexaSkillJsonRequest = alexaRequestBuilder.getAlexaRequestJson(skillId, requestType, testSlots,
				intentName);
		String response = alexaRequestUtility.doPost(String.format(AlexaRequestURL.TEST_ALEXA_SKILL, skillId, stage),
				alexaConnection.getAccessToken(), testAlexaSkillJsonRequest);
		JsonNode node, content ;
		try {
			node = alexaRequestBuilder.getMapper().readTree(response);
			content  = node.findValue("content");
			LOGGER.info("UseExistingSkill Response: {}", content);
		} catch (IOException e) {
			LOGGER.error("Exception while processing useExising skill response {}", e);
			throw new AlexaApiException(e.getMessage(), AlexaApiErrorType.JSON_PARSER_EXCEPTION);
		}
		return content.asText();

	}

	@MediaType(value = ANY, strict = false)
	@Alias("deleteSkill")
	public String DeleteSkill(@Connection AlexaConnection alexaConnection,  @Expression(SUPPORTED) String skillId)
			throws Exception {
		LOGGER.info("Alexa Authorization  Token {}", alexaConnection.getAccessToken());
		return alexaRequestUtility.doGet(AlexaRequestURL.GET_ALEXA_INFO, alexaConnection.getAccessToken(), skillId);
	}

	@MediaType(value = ANY, strict = false)
	@Alias("updateSkillManifest")
	public String updateManifest(@Connection AlexaConnection alexaConnection,  @Expression(SUPPORTED) String skillId,
			@Optional @NullSafe	@Expression Manifest manifest) throws AlexaApiException {
		LOGGER.info("Update Manifest with skillId {}", skillId);
		String result; 

		String updateRequest = alexaRequestBuilder.createUpdateRequest(skillId, manifest);
		String url = String.format(AlexaRequestURL.UPDATE_EXISTING_SKILL, skillId);
		result = alexaRequestUtility.doPut(url, alexaConnection.getAccessToken(), updateRequest);

		LOGGER.info("Update Manifest  response  {}", result);
		return result;
	}
	
	@MediaType(value = ANY, strict = false)
	@Alias("updateSkillIntents")
	public  String updateInteraction(@Connection AlexaConnection alexaConnection, 
			@Optional @NullSafe @Expression(SUPPORTED) InteractionModel model ,
			@Expression(SUPPORTED) String skillId) {
		
		LOGGER.debug("Updating Interaction model {} , skillId {} ",model,skillId);
		
		String interactionSchema = 	alexaRequestBuilder.prepareSchemaForInteractionModel(model);
		String url = String.format(AlexaRequestURL.UPDATE_INTERACTION_SCHEMA , skillId);
		String updateRes = 	alexaRequestUtility.doPut(url, alexaConnection.getAccessToken(), interactionSchema);
		
		LOGGER.debug("Update Interaction model response {}",updateRes);
		return updateRes;
	}

	@MediaType(value = ANY, strict = false)
	@Alias("updateSkill")
	public String updateSkill(@Connection AlexaConnection alexaConnection, 
			@Expression(SUPPORTED) String skillId,
			@Optional @NullSafe @Expression InteractionModel interactionmodel, 
			@Optional @NullSafe @Expression Manifest manifest
			) throws AlexaApiException{
		
		String interactionRes =	updateInteraction(alexaConnection,interactionmodel,skillId);
		
		String manifestRes  =   updateManifest(alexaConnection,skillId,manifest);
		LOGGER.debug("UpdateSkill respoonse: {}, {}",interactionRes,manifestRes);
		if(interactionRes.equals(AlexaRequestURL.SUCCESS) &&
				manifestRes.equals(AlexaRequestURL.SUCCESS)) {
			return "";
		}
		return null;
		
	}
}
