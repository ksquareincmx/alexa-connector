/**
 * (c) 2003-2019 MuleSoft, Inc. The software in this package is published under the terms of the Commercial Free Software license V.1 a copy of which has been included with this distribution in the LICENSE.md file.
 */

package org.mule.modules.alexa.internal.util;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.stream.Collectors;

import org.mule.modules.alexa.api.domain.create.CreateSkill;
import org.mule.modules.alexa.api.domain.data.CategoryEnum;
import org.mule.modules.alexa.api.domain.intents.Dialog;
import org.mule.modules.alexa.api.domain.intents.DialogIntent;
import org.mule.modules.alexa.api.domain.intents.DialogSlot;
import org.mule.modules.alexa.api.domain.intents.Intent;
import org.mule.modules.alexa.api.domain.intents.InteractionModel;
import org.mule.modules.alexa.api.domain.intents.LanguageIntent;
import org.mule.modules.alexa.api.domain.intents.LanguageModel;
import org.mule.modules.alexa.api.domain.intents.Prompt;
import org.mule.modules.alexa.api.domain.intents.Slot;
import org.mule.modules.alexa.api.domain.intents.Variation;
import org.mule.modules.alexa.api.domain.update.ApiInfo;
import org.mule.modules.alexa.api.domain.update.Manifest;
import org.mule.modules.alexa.api.domain.update.PrivacyComplaince;
import org.mule.modules.alexa.api.domain.update.PrivacyLocale;
import org.mule.modules.alexa.api.domain.update.PublishInfo;
import org.mule.modules.alexa.api.domain.update.PublishLocale;
import org.mule.modules.alexa.api.domain.update.UpdateSkill;
import org.mule.modules.alexa.internal.error.AlexaApiErrorType;
import org.mule.modules.alexa.internal.exceptions.AlexaApiException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class AlexaRequestBuilder {

	private static final Logger logger = LoggerFactory.getLogger(AlexaRequestBuilder.class);

	static ObjectMapper mapper = new ObjectMapper();

	public AlexaRequestBuilder() {
		mapper.setSerializationInclusion(Include.NON_NULL);
	}

	public ObjectMapper getMapper() {
		return mapper;
	}

	/**
	 * This method builds json string for CreteSkill manifest scheme.
	 * @param vendorId
	 * @param skillName
	 * @param endpoint
	 * @param category
	 * @return String 
	 * @throws AlexaApiException
	 */
	public String buildCreateSkillRequest(String vendorId,String skillName,
			String endpoint, CategoryEnum category) throws AlexaApiException {

		logger.debug("Method parameters are: vendorId: {} , skillName: {} ,endpoint: {} ", vendorId,
				 skillName, endpoint);

		// create locale
		PublishLocale locale = createLocaleInfo(skillName, null, null);

		// create publish information
		PublishInfo publishInfo = createPublishingInfo(false, category, locale, Arrays.asList("US", "GB"));

		PrivacyLocale privacyLocale = new PrivacyLocale();
		Map<String, PrivacyLocale> localesPrivacy = new HashMap<>();
		localesPrivacy.put("en-US", privacyLocale);
		PrivacyComplaince privacyComplaince = PrivacyComplaince.defaultComplaince(localesPrivacy);
		// create apiinfo and set endpoint
		ApiInfo apiInfo = ApiInfo.defaultApi(endpoint);

		// create manifest and set api,publish info to manifest
		Manifest manifest = new Manifest();
		manifest.setApis(apiInfo);
		manifest.setPublishingInformation(publishInfo);
		manifest.setPrivacyAndCompliance(privacyComplaince);
		CreateSkill createSkill = new CreateSkill(vendorId, manifest);
		String jsonStr = null;
		try {
			jsonStr = mapper.writeValueAsString(createSkill);
			JsonNode newNode = removeExtraFields((ObjectNode) mapper.readTree(jsonStr));

			jsonStr = mapper.writeValueAsString(newNode);
			logger.info("Create Alexa Skill request: {}", jsonStr);
		} catch (IOException e) {
			logger.error("Exception while serializing json in buildCreateSkillRequest {}", e);
			throw new AlexaApiException(e.getMessage(), AlexaApiErrorType.JSON_PARSER_EXCEPTION);
		}
		return jsonStr;
	}

	/**
	 * This method used to prepare json schema to update the already created
	 * Skill,by given skillID and intents as parametes
	 * 
	 * @param newskillId
	 * @param intents
	 * @return String -- response from alexa after successfull update.
	 */
	public String buildUpdateSkillRequest(String newskillId,String invocationName, List<Intent> intents) throws AlexaApiException {

		logger.info("updateCreatedSkill  parameters: newSkillId: {} intents : {}", newskillId, intents);
		InteractionModel interactionModel = new InteractionModel();

		List<Prompt> prompts = intents.stream().map(m -> m.getPrompts()).flatMap(m -> m.stream())
				.collect(Collectors.toList());
		List<Variation> variations = prompts.stream().map(m -> m.getVariations()).flatMap(v -> v.stream()).collect(Collectors.toList());
		interactionModel.setPrompts(prompts);

		//
		// set dialog and language model
		Dialog dialog = new Dialog();
		dialog.setDialogIntents(mapIntnetToDialogIntent(intents));
		LanguageModel language = new LanguageModel();
		language.setLanguateIntents(mapIntnetToLanguageIntent(intents));
		language.setInvocationName(invocationName);
		
		interactionModel.setDialog(dialog);
		interactionModel.setLanguageModel(language);
		
		Map<String, InteractionModel> reqObj = new HashMap<>();
		reqObj.put("interactionModel", interactionModel);
		String updateSkillJson = null;
		try {
			updateSkillJson = mapper.writeValueAsString(reqObj);

			JsonNode newNode = removeExtraFields((ObjectNode) mapper.readTree(updateSkillJson));

			updateSkillJson = mapper.writeValueAsString(newNode);
			logger.info("updateCreatedSkill  json {}", updateSkillJson);
		} catch (IOException e) {
			logger.error("Exception while serializing json in updateCreatedSkill {}", e);
			throw new AlexaApiException(e.getMessage(), AlexaApiErrorType.JSON_PARSER_EXCEPTION);
		}
		return updateSkillJson;
	}

	public String createInteractionUpdateRequest(InteractionModel model) {

		InteractionModel m = InteractionModel.defaultInteractionModel(model);
		Map<String, InteractionModel> reqObj = new HashMap<>();
		reqObj.put("interactionModel", m);
		String updateSkillJson = null;
		try {
			updateSkillJson = mapper.writeValueAsString(reqObj);

			JsonNode newNode = removeExtraFields((ObjectNode) mapper.readTree(updateSkillJson));

			updateSkillJson = mapper.writeValueAsString(newNode);
			System.out.println("createInteractionUpdateRequest:" + updateSkillJson);
			logger.info("createInteractionUpdateRequest  json {}", updateSkillJson);
		} catch (IOException e) {
			logger.error("Exception while serializing json in prepareSchemaForInteractionModel {}", e);
			throw new AlexaApiException(e.getMessage(), AlexaApiErrorType.JSON_PARSER_EXCEPTION);
		}
		return updateSkillJson;
	}

	public DialogIntent dialogIntent(DialogIntent intent) {
		return new DialogIntent(intent.getIntentName(), intent.getConfirmationRequired(), intent.getDialogSlots(),
				intent.getDialogPrompts());
	}

	public String readValueForKey(String json, String key) {
		String value =null;
		JsonNode rootNode;
		try {
			rootNode = getMapper().readTree(json);
			JsonNode fieldNode = rootNode.get(key);
			System.out.println(fieldNode +"@@@@"+key);
			if(key.equals("skillId") && Objects.nonNull(fieldNode)) {
				value = fieldNode.asText();
			}
			if (key.equals("skillId") && Objects.isNull(fieldNode)) {
				// when wrong vendorid or data for given while skill creation we get here
				String res = rootNode.asText();
				logger.error("Creation of skill having problem  {} ", res);
				throw new AlexaApiException(res, AlexaApiErrorType.VALIDATIONS);
			}
			if(key.equals("message") && Objects.nonNull(fieldNode)) {
				// sending whole json to client when we got message key
				value = rootNode.toString();
			}

		} catch (IOException e) {
			logger.error("Error while looking for key in json: ",e);
			throw new AlexaApiException(e.getMessage(), AlexaApiErrorType.JSON_PARSER_EXCEPTION);
		}
		return value;
	}



	public String createManifestUpdateRequest(String skillId, Manifest manifest) {

		Manifest manifestCopy = Manifest.defaultManifest(manifest);
		UpdateSkill skill = new UpdateSkill(manifestCopy);

		String res = null;
		try {
			res = mapper.writeValueAsString(skill);
			JsonNode newNode = removeExtraFields((ObjectNode) mapper.readTree(res));

			res = mapper.writeValueAsString(newNode);
			System.out.println("createManifestUpdateRequest:" + res);
			logger.debug("createManifestUpdateRequest json  {}", res);
		} catch (IOException e) {
			logger.error("Exception while serializing json in createUpdateRequest {}", e);
			throw new AlexaApiException(e.getMessage(), AlexaApiErrorType.JSON_PARSER_EXCEPTION);
		}
		return res;

	}

	public PublishInfo createPublishingInfo(boolean isAvailableWorldwide, CategoryEnum category, PublishLocale locales,
			List<String> dcountries) {

		return new PublishInfo(isAvailableWorldwide, category, dcountries, locales);
	}

	private PublishLocale createLocaleInfo(String name, String summary, String description) {

		return new PublishLocale(name, summary, description);

	}

	public static ObjectNode removeExtraFields(final ObjectNode jsonNode) {

		ObjectNode ret = new ObjectMapper().createObjectNode();
		Iterator<Entry<String, JsonNode>> iter = jsonNode.fields();
		while (iter.hasNext()) {
			Entry<String, JsonNode> entry = iter.next();
			String key = entry.getKey();
			JsonNode value = entry.getValue();

			if (value instanceof ObjectNode) {
				Map<String, ObjectNode> map = new HashMap<String, ObjectNode>();
				map.put(key, removeExtraFields((ObjectNode) (value)));
				ret.setAll(map);

			} else if (value instanceof ArrayNode) {
				ret.set(key, removeExtraFields((ArrayNode) (value)));
			} else if (value.asText() != null && !value.asText().isEmpty()) {
				ret.set(key, value);
			}
			// TODO removed {annotations:{}} from json we need to find why the annotations
			// is added
			// to json even if it is not model objects, i assume it is because of @Component
			// annotation from mule
			boolean a = key.equals("annotations");
			if (a) {
				ret.remove("annotations");
			}
		}

		return ret;
	}

	public static ArrayNode removeExtraFields(ArrayNode array) {
		ArrayNode ret = new ObjectMapper().createArrayNode();
		Iterator<JsonNode> iter = array.elements();

		while (iter.hasNext()) {
			JsonNode value = iter.next();

			if (value instanceof ArrayNode) {
				ret.add(removeExtraFields((ArrayNode) (value)));
			} else if (value instanceof ObjectNode) {
				ObjectNode nullVal = removeExtraFields((ObjectNode) (value));
				if (nullVal.toString().length() != 2) {
					ret.add(nullVal);
				}
			} else if (value != null && !value.textValue().isEmpty()) {
				ret.add(value);
			}
		}

		return ret;
	}

	private List<DialogIntent> mapIntnetToDialogIntent(List<Intent> intents) {

		return intents.stream().map(in -> mapIntent(in)).collect(Collectors.toList());

	}

	private List<LanguageIntent> mapIntnetToLanguageIntent(List<Intent> intents) {
		return intents.stream().map(in -> defaultLanguageIntentFromIntent(in))
				.collect(Collectors.toList());

	}
	private  LanguageIntent defaultLanguageIntentFromIntent(Intent intent){
		List<Slot> intentSlots = intent.getSlots().stream().map(s -> Slot.defaultSlot(s)).collect(Collectors.toList());
		return new LanguageIntent(intent.getIntentName(), 
				intentSlots,
				intent.getSamples());
	}
	private DialogIntent mapIntent(Intent intent) {
		// Iterate slots and prepare Dialog slots
		
		List<DialogSlot> dialogSlots = intent.getSlots().stream().map(s -> new DialogSlot(s.getSname(), s.getType()))
				.collect(Collectors.toList());
		return new DialogIntent(intent.getIntentName(), intent.getConfirmationRequired(), dialogSlots,
				Collections.emptyMap());
	}

}
