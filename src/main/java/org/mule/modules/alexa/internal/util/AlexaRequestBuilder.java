/**
 * (c) 2003-2017 MuleSoft, Inc. The software in this package is published under the terms of the Commercial Free Software license V.1 a copy of which has been included with this distribution in the LICENSE.md file.
 */

package org.mule.modules.alexa.internal.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import org.mule.modules.alexa.api.domain.create.CreateSkill;
import org.mule.modules.alexa.api.domain.existing.Application;
import org.mule.modules.alexa.api.domain.existing.Body;
import org.mule.modules.alexa.api.domain.existing.Context;
import org.mule.modules.alexa.api.domain.existing.ExistingIntent;
import org.mule.modules.alexa.api.domain.existing.Reqest;
import org.mule.modules.alexa.api.domain.existing.Request;
import org.mule.modules.alexa.api.domain.existing.Session;
import org.mule.modules.alexa.api.domain.existing.SkillRequest;
import org.mule.modules.alexa.api.domain.existing.SlotName;
import org.mule.modules.alexa.api.domain.existing.Sstem;
import org.mule.modules.alexa.api.domain.existing.User;
import org.mule.modules.alexa.api.domain.intents.Dialog;
import org.mule.modules.alexa.api.domain.intents.Intent;
import org.mule.modules.alexa.api.domain.intents.InteractionModel;
import org.mule.modules.alexa.api.domain.intents.LanguageModel;
import org.mule.modules.alexa.api.domain.update.ApiInfo;
import org.mule.modules.alexa.api.domain.update.ApiInfo.CustomApi;
import org.mule.modules.alexa.api.domain.update.Events;
import org.mule.modules.alexa.api.domain.update.Locale;
import org.mule.modules.alexa.api.domain.update.Manifest;
import org.mule.modules.alexa.api.domain.update.PublishInfo;
import org.mule.modules.alexa.api.domain.update.UpdateSkill;
import org.mule.modules.alexa.internal.error.AlexaApiErrorType;
import org.mule.modules.alexa.internal.exceptions.AlexaApiException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class AlexaRequestBuilder {

	private static final Logger LOGGER = LoggerFactory.getLogger(AlexaRequestBuilder.class);

	static ObjectMapper mapper = new ObjectMapper();

	public AlexaRequestBuilder() {
		mapper.setSerializationInclusion(Include.NON_NULL);
	}

	public ObjectMapper getMapper() {
		return mapper;
	}

	/**
	 * TODO
	 */
	public String createAlexaSkillRequestBuilder(String vendorId, String summary, List<String> examplePhrases,
			List<String> keywords, String skillName, String description, String endpoint) throws AlexaApiException{

		LOGGER.debug("Method parameters are: vendorId: {} , summary: {} ,keyword: {} , skillName: {} ,endpoint: {} ",
				vendorId, summary, keywords, skillName, endpoint);

		// create locale
		Locale locale = createLocaleInfo(skillName, summary, description, keywords, examplePhrases);
		HashMap<String, Locale> locales = new HashMap<>();
		locales.put("locales", locale);
		// create publish information
		PublishInfo publishInfo = createPublishingInfo(locales, "SMART_HOME", true);
		// create apiinfo and set endpoint
		ApiInfo apiInfo = createApiInfo(endpoint);

		// create manifest and set api,publish info to manifest
		Manifest manifest = new Manifest();
		manifest.setApis(apiInfo);
		manifest.setPublishingInformation(publishInfo);
		CreateSkill createSkill = new CreateSkill(vendorId, manifest);
		String jsonStr = null;
		try {
			jsonStr = mapper.writeValueAsString(createSkill);
			LOGGER.debug("Create Alexa Skill request: {}", jsonStr);
		} catch (JsonProcessingException e) {
			// TODO: handle exception
			LOGGER.error("Exception while serializing json in createAlexaSkillRequestBuilder {}", e);
			throw new AlexaApiException(e.getMessage(), AlexaApiErrorType.JSON_PARSER_EXCEPTION);
		}
		return jsonStr;
	}

	/**
	 * TODO
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String updateCreatedSkill(String newskillId, List<Intent> intents) throws AlexaApiException{

		LOGGER.info("updateCreatedSkill  parameters: newSkillId: {} intents : {}", newskillId, intents);
		InteractionModel interactionModel = new InteractionModel();
		Dialog dialog = new Dialog();
		dialog.setIntents(intents);
		LanguageModel language = new LanguageModel();
		language.setIntents(intents);
		// set dialog and language model
		interactionModel.setDialog(dialog);
		interactionModel.setLanguageModel(language);
		Map<String, InteractionModel> reqObj = new HashMap<>();
		reqObj.put("interactionModel", interactionModel);
		String updateSkillJson = null;
		try {
			updateSkillJson = mapper.writeValueAsString(reqObj);
			LOGGER.info("updateCreatedSkill  json ", updateSkillJson);
		} catch (JsonProcessingException e) {
			LOGGER.error("Exception while serializing json in updateCreatedSkill {}", e);
			throw new AlexaApiException(e.getMessage(), AlexaApiErrorType.JSON_PARSER_EXCEPTION);
		}
		return updateSkillJson;
	}

	public String getAlexaRequestJson(String applicationID, String requestType, Map<String, String> slots,
			String intentName) throws AlexaApiException {

		LOGGER.debug("getAlexaRequestJson params applicationID:{} requestType {} slots {} intentName:{} ",
				applicationID, requestType, slots, intentName);
		Application application = new Application(applicationID);
		User user = new User("amzn1.ask.account.12345ABCDEFGH");

		Session session = new Session();
		session.setOne(true);
		session.setSessionId("aaf7b112-434c-11e7-2563-6bbd1672c748");
		session.setApplication(application);
		session.setUser(user);
		Sstem system = new Sstem(application, user);
		Context context = new Context(system);
		ExistingIntent intent = new ExistingIntent();

		prepareSlotForIntent(slots, intent);
		intent.setName(intentName);

		String nowAsISO = getTime();
		Reqest reqest = new Reqest(requestType, "c03faf54-684d-11e7-6249-6bbd1825c634", nowAsISO, "en-US", intent);
		Body body = new Body("1.0", session, context, reqest);
		SkillRequest skillRequest = new SkillRequest(body);
		Request request = new Request("Default", skillRequest);
		String jsonStr = null;
		try {
			jsonStr = mapper.writeValueAsString(request);
			LOGGER.debug("getAlexaRequestJson json  {}", jsonStr);
		} catch (JsonProcessingException e) {
			LOGGER.error("Exception while serializing json in updateCreatedSkill {}", e);
			throw new AlexaApiException(e.getMessage(), AlexaApiErrorType.JSON_PARSER_EXCEPTION);
		}
		return jsonStr;
	}

	private ExistingIntent prepareSlotForIntent(Map<String, String> slots, ExistingIntent intent) {
		Map<String, SlotName> slotNames = new HashMap<>();

		for (Map.Entry<String, String> slot : slots.entrySet()) {
			SlotName slotname = new SlotName();
			slotname.setConfirmationStatus("NONE");
			slotname.setName(slot.getKey());
			slotname.setValue(slot.getValue());

			slotNames.put(slot.getKey(), slotname);
		}
		intent.setSlots(slotNames);
		return intent;
	}

	private String getTime() {
		TimeZone tz = TimeZone.getTimeZone("CST");
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'");
		df.setTimeZone(tz);
		String nowAsISO = df.format(new Date());
		return nowAsISO;
	}

	public String createUpdateRequest(String skillId, String apiEndpoint, List<String> interfaces,
			List<String> permission, String eventEndpoint, List<String> subscriptions)  {

		ApiInfo apiInfo = createApiInfo(apiEndpoint);
		Events events = new Events(eventEndpoint);
		events.setSubscriptions(listToListMap(subscriptions, "eventName"));
		// setting api,events,permission to manifest
		Manifest manifest = new Manifest();
		manifest.setApis(apiInfo);
		manifest.setEvents(events);
		manifest.setPermissions(listToListMap(permission, "name"));
		UpdateSkill skill = new UpdateSkill(manifest);

		String res = null;
		try {
			res = mapper.writeValueAsString(skill);
			LOGGER.debug("createUpdateRequest json  {}", res);
		} catch (JsonProcessingException e) {
			LOGGER.error("Exception while serializing json in createUpdateRequest {}", e);
			throw new AlexaApiException(e.getMessage(), AlexaApiErrorType.JSON_PARSER_EXCEPTION);
		}
		return res;

	}

	private List<Map<String, String>> listToListMap(List<String> data, String key) {

		List<Map<String, String>> listmap = new ArrayList<>();
		for (String string : data) {
			Map<String, String> map = new HashMap<String, String>();
			map.put(key, string);
			listmap.add(map);
		}

		return listmap;
	}

	public ApiInfo createApiInfo(String endpoint) {

		return new ApiInfo(createCustomApi(endpoint));
	}

	public PublishInfo createPublishingInfo(Map<String, Locale> locales, String category,
			boolean isAvailableWorldwide) {

		return new PublishInfo(locales, isAvailableWorldwide, category);
	}

	private Locale createLocaleInfo(String name, String summary, String description, List<String> keywords,
			List<String> examplePhrases) {

		return new Locale(name, summary, description, keywords, examplePhrases);

	}

	private CustomApi createCustomApi(String endpoint) {
		return new CustomApi(endpoint);
	}

}
