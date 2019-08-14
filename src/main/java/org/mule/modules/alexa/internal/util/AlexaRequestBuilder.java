package org.mule.modules.alexa.internal.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import org.json.JSONArray;
import org.json.simple.JSONObject;
import org.mule.modules.alexa.api.domain.create.CreateSkill;
import org.mule.modules.alexa.api.domain.intents.IntentValueParam;
import org.mule.modules.alexa.api.domain.intents.PromptParams;
import org.mule.modules.alexa.api.domain.intents.SlotParams;
import org.mule.modules.alexa.api.domain.test.Application;
import org.mule.modules.alexa.api.domain.test.Body;
import org.mule.modules.alexa.api.domain.test.Context;
import org.mule.modules.alexa.api.domain.test.Intent;
import org.mule.modules.alexa.api.domain.test.Reqest;
import org.mule.modules.alexa.api.domain.test.Request;
import org.mule.modules.alexa.api.domain.test.Session;
import org.mule.modules.alexa.api.domain.test.SkillRequest;
import org.mule.modules.alexa.api.domain.test.SlotName;
import org.mule.modules.alexa.api.domain.test.Sstem;
import org.mule.modules.alexa.api.domain.test.User;
import org.mule.modules.alexa.api.domain.update.ApiInfo;
import org.mule.modules.alexa.api.domain.update.ApiInfo.CustomApi;
import org.mule.modules.alexa.api.domain.update.Events;
import org.mule.modules.alexa.api.domain.update.Locale;
import org.mule.modules.alexa.api.domain.update.Manifest;
import org.mule.modules.alexa.api.domain.update.PublishInfo;
import org.mule.modules.alexa.api.domain.update.UpdateSkill;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class AlexaRequestBuilder {

	private static final Logger LOGGER = LoggerFactory.getLogger(AlexaRequestBuilder.class);

	ObjectMapper mapper = new ObjectMapper();

	public AlexaRequestBuilder() {
		mapper.setSerializationInclusion(Include.NON_NULL);
	}

	/**
	 * TODO
	 */
	public String createAlexaSkillRequestBuilder(String vendorId, String summary, List<String> examplePhrases,
			List<String> keywords, String skillName, String description, String endpoint)
			throws JsonProcessingException {

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
		String jsonStr = mapper.writeValueAsString(createSkill);
		LOGGER.debug("Create Alexa Skill request: {}", jsonStr);
		return jsonStr;
	}

	/**
	 * TODO
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String updateCreatedSkill(String newskillId, List<IntentValueParam> intents) {

		String request = null;

		//mapper.writeValueAsString(intents);
		try {

			if (intents != null) {
				JSONArray intentArray = new JSONArray();

				for (IntentValueParam valueParam : intents) {
					String intentName = valueParam.getIntentName();

					JSONObject jsonObject = new JSONObject();
					JSONObject jsonObject2 = new JSONObject();
					JSONObject jsonObject3 = new JSONObject();
					JSONObject jsonObject4 = new JSONObject();
					JSONObject subdialogjsonObject = new JSONObject();
					JSONObject promtsobject1 = new JSONObject();
					JSONArray promtarray1 = new JSONArray();
					JSONObject cancelIntentjsonObj = new JSONObject();

					jsonObject.put("samples", valueParam.getSamples());

					// creating slots array object
					List<SlotParams> slots = valueParam.getSlots();

					JSONArray jsonArray = new JSONArray();

					int i = 0;
					for (SlotParams slot : slots) {
						JSONObject slotJsonObject = new JSONObject();

						String slotName = slot.getSlotName();
						String slotType = slot.getSlotType();

						slotJsonObject.put("name", slotName);
						slotJsonObject.put("type", slotType);
						slotJsonObject.putIfAbsent("samples", slot.getSamples());

						jsonArray.put(i, slotJsonObject);
						i = i + 1;
					}

					// creating promt array object
					List<PromptParams> promt = valueParam.getPromts();
					JSONArray promtjsonArray = new JSONArray();
					int a = 0;
					for (PromptParams Promt : promt) {
						JSONObject promtJsonObject = new JSONObject();

						String promtType = Promt.getPromtType();
						String promtValue = Promt.getPromtValue();

						promtJsonObject.put("type", promtType);
						promtJsonObject.put("value", promtValue);

						promtjsonArray.put(a, promtJsonObject);
						a = a + 1;
					}

					// creating dialog array object

					JSONArray dialogJsonArray = new JSONArray();
					JSONObject dialogjsonObject = new JSONObject();

					dialogjsonObject.put("name", intentName);
					dialogjsonObject.put("confirmationRequired", new Boolean(false));
					dialogjsonObject.put("prompts", new JSONObject());

					dialogJsonArray.put(dialogjsonObject);

					subdialogjsonObject.put("intents", dialogJsonArray);
					// subdialogjsonObject1.put("dialog", subdialogjsonObject);

					promtsobject1.put("variations", promtjsonArray);
					promtsobject1.put("id", "123456");

					promtarray1.put(promtsobject1);
					// promtsobject2.put("prompts", promtarray1);

					jsonObject.put("slots", jsonArray);
					jsonObject.put("name", intentName);
					cancelIntentjsonObj.put("name", "AMAZON.CancelIntent");
					cancelIntentjsonObj.put("samples", new JSONArray());
					intentArray.put(cancelIntentjsonObj);
					intentArray.put(jsonObject);

					jsonObject2.put("invocationName", "my space facts");
					jsonObject2.putIfAbsent("intents", intentArray);
					jsonObject3.put("languageModel", jsonObject2);

					jsonObject4.put("interactionModel", jsonObject3);
					jsonObject3.put("dialog", subdialogjsonObject);
					jsonObject3.put("prompts", promtarray1);

					request = jsonObject4.toJSONString();

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return request;
	}

	public String getAlexaRequestJson(String applicationID, String requestType, Map<String, String> slots,
			String intentName) throws JsonProcessingException {

		Application application = new Application(applicationID);
		User user = new User("amzn1.ask.account.12345ABCDEFGH");

		Session session = new Session();
		session.setOne(true);
		session.setSessionId("aaf7b112-434c-11e7-2563-6bbd1672c748");
		session.setApplication(application);
		session.setUser(user);
		Sstem system = new Sstem(application, user);
		Context context = new Context(system);
		Intent intent = new Intent();

		prepareSlotForIntent(slots, intent);
		intent.setName(intentName);

		String nowAsISO = getTime();
		Reqest reqest = new Reqest(requestType, "c03faf54-684d-11e7-6249-6bbd1825c634", nowAsISO, "en-US", intent);
		Body body = new Body("1.0", session, context, reqest);
		SkillRequest skillRequest = new SkillRequest(body);
		Request request = new Request("Default", skillRequest);
		String jsonStr = mapper.writeValueAsString(request);
		LOGGER.debug("Skill reques: {}", jsonStr);
		return jsonStr;
	}

	private Intent prepareSlotForIntent(Map<String, String> slots, Intent intent) {
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
			List<String> permission, String eventEndpoint, List<String> subscriptions) throws JsonProcessingException {

		// create api info and set endpoint
		ApiInfo apiInfo = createApiInfo(apiEndpoint);
		// setting events and subscription
		Events events = new Events(eventEndpoint);
		events.setSubscriptions(listToListMap(subscriptions, "eventName"));

		// setting api,events,permission to manifest
		Manifest manifest = new Manifest();
		manifest.setApis(apiInfo);
		manifest.setEvents(events);
		manifest.setPermissions(listToListMap(permission, "name"));
		UpdateSkill skill = new UpdateSkill(manifest);

		// convert java to json using jackson
		return mapper.writeValueAsString(skill);

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
