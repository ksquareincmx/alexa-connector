/**
 * (c) 2003-2017 MuleSoft, Inc. The software in this package is published under the terms of the Commercial Free Software license V.1 a copy of which has been included with this distribution in the LICENSE.md file.
 */

package org.mule.modules.alexa.internal.operation;

import static org.mule.runtime.api.meta.ExpressionSupport.SUPPORTED;
import static org.mule.runtime.extension.api.annotation.param.MediaType.ANY;

import java.util.List;

import org.mule.modules.alexa.api.domain.AlexaRequestURL;
import org.mule.modules.alexa.api.domain.data.CategoryEnum;
import org.mule.modules.alexa.api.domain.intents.Intent;
import org.mule.modules.alexa.api.domain.intents.InteractionModel;
import org.mule.modules.alexa.api.domain.update.Manifest;
import org.mule.modules.alexa.internal.connection.provider.AlexaConnection;
import org.mule.modules.alexa.internal.exceptions.AlexaApiException;
import org.mule.modules.alexa.internal.util.AlexaRequestBuilder;
import org.mule.runtime.api.meta.ExpressionSupport;
import org.mule.runtime.extension.api.annotation.Alias;
import org.mule.runtime.extension.api.annotation.Expression;
import org.mule.runtime.extension.api.annotation.dsl.xml.ParameterDsl;
import org.mule.runtime.extension.api.annotation.param.Connection;
import org.mule.runtime.extension.api.annotation.param.MediaType;
import org.mule.runtime.extension.api.annotation.param.NullSafe;
import org.mule.runtime.extension.api.annotation.param.Optional;
import org.mule.runtime.http.api.HttpConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class is a container for operations, every public method in this class
 * will be taken as an extension operation.
 */

public class AlexaOperations {

	private static final Logger logger = LoggerFactory.getLogger(AlexaOperations.class);

	private AlexaRequestBuilder requestBuilder = new AlexaRequestBuilder();

	/**
	 * This method used for creating skill with basic information later we can
	 * update with full information using updateSkillManifest,updateSkillManifest
	 * and updateSkill operations, return error received from Alexa Server if some
	 * thing wrong in create request or missed some fields which are required
	 * for skill creations.
	 *  
	 * @param alexaConnection
	 * @param vendorId
	 * @param skillName
	 * 			name of the skill
	 * @param endpoint
	 * 			endpoint where skill events to be handled.
	 * @param category
	 * 			Category of the skill chosen form {@code : CategoryEnum}
	 * @param invocationName
	 * 			Name to invoke the alexa for first time
	 * @param intents
	 * @return String 
	 * 			Json response from Alexa sever
	 */
	@MediaType(value = ANY, strict = false)
	@Alias("createSkill")
	public String createSkill(@Connection AlexaConnection alexaConnection, @Expression(SUPPORTED) String vendorId,
			@Expression(SUPPORTED) String skillName, @Expression(SUPPORTED) String endpoint,
			@Expression(ExpressionSupport.NOT_SUPPORTED) CategoryEnum category,
			@Expression(SUPPORTED) String invocationName,
			@Optional @NullSafe @Expression(ExpressionSupport.NOT_SUPPORTED) @ParameterDsl(allowReferences = false) List<Intent> intents) {

		logger.debug("Access token {}", alexaConnection.getAccessToken());
		String alexaRequest = requestBuilder.buildCreateSkillRequest(vendorId, skillName, endpoint, category);

		String createSkillResponse = alexaConnection.sendRequest(HttpConstants.Method.POST,
				AlexaRequestURL.CREATE_ALEXA_SKILL, alexaRequest);

		logger.info("Create Alexa Skill Response {}", createSkillResponse);
		String skillId = requestBuilder.readValueForKey(createSkillResponse, "skillId");

		logger.info("Alexa SkillId {}", skillId);

		String updateSkillRequest = requestBuilder.buildUpdateSkillRequest(skillId, invocationName, intents);
		String updateUrl = String.format(AlexaRequestURL.UPDATE_ALEXA_SKILL, skillId);
		String updateRes = alexaConnection.sendRequest(HttpConstants.Method.PUT, updateUrl, updateSkillRequest);
		String errorMsg = requestBuilder.readValueForKey(updateRes, "message");
		if (errorMsg != null) {
			return errorMsg;
		}
		return "Alexa Skill created successfully with SkillId:" + skillId;

	}

	/**
	 * This method return skill information for the given skillId.
	 * 
	 * @param alexaConnection
	 * @param skillId
	 * @return String
	 *       skill information in json format
	 * @throws AlexaApiException
	 */

	@MediaType(value = ANY, strict = false)
	@Alias("skillInfo")
	public String getSkillInfo(@Connection AlexaConnection alexaConnection, @Expression(SUPPORTED) String skillId)
			throws AlexaApiException {
		logger.debug("Alexa Authorization  Token {}", alexaConnection.getAccessToken());
		String url = String.format(AlexaRequestURL.GET_ALEXA_INFO, skillId);
		return alexaConnection.sendRequest(HttpConstants.Method.GET, url, null);
	}

	/**
	 * This method deletes the skill for the given skillId
	 * 
	 * @param alexaConnection
	 * @param skillId
	 * @return empty {@code : {}}
	 * @throws Exception
	 */
	@MediaType(value = ANY, strict = false)
	@Alias("deleteSkill")
	public String deleteSkill(@Connection AlexaConnection alexaConnection, @Expression(SUPPORTED) String skillId)
			throws Exception {
		logger.info("Alexa Authorization  Token {}", alexaConnection.getAccessToken());
		String deleteUrl = String.format(AlexaRequestURL.DELETE_SKILL, skillId);
		return alexaConnection.sendRequest(HttpConstants.Method.DELETE, deleteUrl, null);
	}

	/**
	 * This method updates the Manifest schema of skill, refer the manifest schema
	 * of Alexa skill for more information
	 * @see <a href="https://developer.amazon.com/docs/smapi/skill-manifest.html">Skill Manifest</a>
	 * 
	 * 
	 * @param alexaConnection
	 * @param skillId
	 *            SkillId of existing skill
	 * @param manifest
	 *            Manifest model
	 * @return empty json object
	 * @throws AlexaApiException
	 */
	@MediaType(value = ANY, strict = false)
	@Alias("updateSkillManifest")
	public String updateManifest(@Connection AlexaConnection alexaConnection, @Expression(SUPPORTED) String skillId,
			@Optional @NullSafe @Expression Manifest manifest) {
		logger.info("Update Manifest with skillId {}", skillId);
		String result;

		String updateRequest = requestBuilder.createManifestUpdateRequest(skillId, manifest);
		String url = String.format(AlexaRequestURL.UPDATE_SKILL_MANIFEST, skillId);
		result = alexaConnection.sendRequest(HttpConstants.Method.PUT, url, updateRequest);
		logger.info("Update Manifest  response  {}", result);
		return result;
	}

	/**
	 * This is used to update interaction model schema of the Alexa skill.
	 * 
	 * @param alexaConnection
	 * @param model
	 * @param skillId
	 * @return String
	 */
	@MediaType(value = ANY, strict = false)
	@Alias("updateSkillIntents")
	public String updateInteraction(@Connection AlexaConnection alexaConnection,
			@Optional @NullSafe @Expression(SUPPORTED) @ParameterDsl(allowReferences = false) InteractionModel model,
			@Expression(SUPPORTED) String skillId) {

		logger.debug("Updating Interaction model {} , skillId {} ", model, skillId);

		String interactionSchema = requestBuilder.createInteractionUpdateRequest(model);
		String url = String.format(AlexaRequestURL.UPDATE_SKILL_INTERACTION_SCHEMA, skillId);
		String updateRes = alexaConnection.sendRequest(HttpConstants.Method.PUT, url, interactionSchema);
		logger.debug("Update Interaction model response {}", updateRes);
		return updateRes;
	}

	/**
	 * This method updates both Manifest and Interaction schema of existing skill.
	 * Refer links for Manifest and Interactions schema
	 * @see <a href="https://developer.amazon.com/docs/smapi/skill-manifest.html">Skill Manifest</a>
	 * @see <a href="https://developer.amazon.com/docs/smapi/interaction-model-schema.html">Skill Interaction Model schema</a>
	 * 
	 * @param alexaConnection
	 * @param skillId
	 *      skillId which need to be updated
	 * @param model
	 *      data required for interaction schema of skill.
	 * @param manifest
	 *       data required for manifest schema of skill
	 * @return String Update Skill Request Accepeted successfully for success.
	 *         Updating skill having problem with Error message from Alexa server.
	 * @throws AlexaApiException
	 */
	@MediaType(value = ANY, strict = false)
	@Alias("updateSkill")
	public String updateSkill(@Connection AlexaConnection alexaConnection, @Expression(SUPPORTED) String skillId,
			@Optional @NullSafe @Expression InteractionModel model, @Optional @NullSafe @Expression Manifest manifest) {

		String interactionRes = updateInteraction(alexaConnection, model, skillId);

		String manifestRes = updateManifest(alexaConnection, skillId, manifest);
		logger.debug("UpdateSkill respoonse: {}, {}", interactionRes, manifestRes);
		if (interactionRes.equals(AlexaRequestURL.ACCEPTED) && manifestRes.equals(AlexaRequestURL.ACCEPTED)) {
			return "Update Skill Request Accepeted successfully ";
		} else {
			return "Updating  skill having problem : " + interactionRes + " , " + manifestRes;
		}

	}
}
