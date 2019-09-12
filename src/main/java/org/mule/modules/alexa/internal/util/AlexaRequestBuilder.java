/**
 * (c) 2003-2017 MuleSoft, Inc. The software in this package is published under the terms of the Commercial Free Software license V.1 a copy of which has been included with this distribution in the LICENSE.md file.
 */

package org.mule.modules.alexa.internal.util;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TimeZone;
import java.util.stream.Collectors;

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
import org.mule.modules.alexa.api.domain.intents.DialogIntent;
import org.mule.modules.alexa.api.domain.intents.DialogSlot;
import org.mule.modules.alexa.api.domain.intents.Intent;
import org.mule.modules.alexa.api.domain.intents.InteractionModel;
import org.mule.modules.alexa.api.domain.intents.LanguageIntent;
import org.mule.modules.alexa.api.domain.intents.LanguageModel;
import org.mule.modules.alexa.api.domain.intents.Prompt;
import org.mule.modules.alexa.api.domain.intents.Variation;
import org.mule.modules.alexa.api.domain.update.ApiInfo;
import org.mule.modules.alexa.api.domain.update.Manifest;
import org.mule.modules.alexa.api.domain.update.PrivacyComplaince;
import org.mule.modules.alexa.api.domain.update.PublishInfo;
import org.mule.modules.alexa.api.domain.update.PublishLocale;
import org.mule.modules.alexa.api.domain.update.UpdateSkill;
import org.mule.modules.alexa.internal.error.AlexaApiErrorType;
import org.mule.modules.alexa.internal.exceptions.AlexaApiException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

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
	 * 
	 * @param vendorId
	 * @param summary
	 * @param examplePhrases
	 * @param keywords
	 * @param skillName
	 * @param description
	 * @param endpoint
	 * @return
	 * @throws AlexaApiException
	 */
	public String buildCreateSkillRequest(String vendorId, String summary,
			String skillName, String description, String endpoint) throws AlexaApiException {

		LOGGER.debug("Method parameters are: vendorId: {} , summary: {}  , skillName: {} ,endpoint: {} ",
				vendorId, summary, skillName, endpoint);

		// create locale
		PublishLocale locale = createLocaleInfo(skillName, summary, description);
		HashMap<String, PublishLocale> locales = new HashMap<>();
		locales.put("en-US", locale);
		// create publish information
		PublishInfo publishInfo = createPublishingInfo(locales, "SMART_HOME", false);
		publishInfo.setTestingInstructions("1) Say 'Alexa, discover my devices' 2) Say 'Alexa, turn on sample lights'");
		publishInfo.setDistributionCountries(Arrays.asList("US", "GB"));

		PublishLocale privacyLocale = new PublishLocale(skillName,summary,description);
		HashMap<String, PublishLocale> localesPrivacy = new HashMap<>();
		localesPrivacy.put("en-US", privacyLocale);
		PrivacyComplaince privacyComplaince =PrivacyComplaince.defaultComplaince();
		// create apiinfo and set endpoint
		ApiInfo apiInfo =  ApiInfo.defaultApi(endpoint);

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
			LOGGER.debug("Create Alexa Skill request: {}", jsonStr);
		} catch (IOException e) {
			// TODO: handle exception
			LOGGER.error("Exception while serializing json in createAlexaSkillRequestBuilder {}", e);
			throw new AlexaApiException(e.getMessage(), AlexaApiErrorType.JSON_PARSER_EXCEPTION);
		}
		return jsonStr;
	}

	/**
	 * This method used to prepare json schema to update the already created Skill,by given skillID and intents as parametes
	 * @param newskillId
	 * @param intents
	 * @return String -- response from alexa after successfull update.
	 */
	public String buildUpdateSkillRequest(String newskillId, List<Intent> intents) throws AlexaApiException {

		LOGGER.info("updateCreatedSkill  parameters: newSkillId: {} intents : {}", newskillId, intents);
		InteractionModel interactionModel = new InteractionModel();

		List<Prompt> prompts = intents.stream().map(m -> m.getPrompts()).flatMap(m -> m.stream())
				.collect(Collectors.toList());
		
		List<Variation> variations = prompts.stream().map(m -> m.getVariations()).flatMap(v -> v.stream()).collect(Collectors.toList());
		interactionModel.setPrompts(prompts);
		
		// set dialog and language model
		Dialog dialog = new Dialog();
		dialog.setDialogIntents(mapIntnetToDialogIntent(intents));
		LanguageModel language = new LanguageModel();
		language.setLanguateIntents(mapIntnetToLanguageIntent(intents));
		language.setInvocationName("my test case");
		interactionModel.setDialog(dialog);
		interactionModel.setLanguageModel(language);
		Map<String, InteractionModel> reqObj = new HashMap<>();
		reqObj.put("interactionModel", interactionModel);
		String updateSkillJson = null;
		try {
			updateSkillJson = mapper.writeValueAsString(reqObj);

			JsonNode newNode = removeExtraFields((ObjectNode) mapper.readTree(updateSkillJson));

			updateSkillJson = mapper.writeValueAsString(newNode);
			LOGGER.info("updateCreatedSkill  json {}", updateSkillJson);
		} catch (IOException e) {
			LOGGER.error("Exception while serializing json in updateCreatedSkill {}", e);
			throw new AlexaApiException(e.getMessage(), AlexaApiErrorType.JSON_PARSER_EXCEPTION);
		}
		return updateSkillJson;
	}

	public String prepareSchemaForInteractionModel(InteractionModel model) {
		InteractionModel m = new InteractionModel(model.getDialog(), model.getLanguageModel(), model.getPrompts());
		Map<String, InteractionModel> reqObj = new HashMap<>();
		reqObj.put("interactionModel", m);
		String updateSkillJson = null;
		try {
	updateSkillJson = mapper.writeValueAsString(reqObj);

			JsonNode newNode = removeExtraFields((ObjectNode) mapper.readTree(updateSkillJson));

			updateSkillJson = mapper.writeValueAsString(newNode);
			LOGGER.info("prepareSchemaForInteractionModel  json {}", updateSkillJson);
		} catch (IOException e) {
			LOGGER.error("Exception while serializing json in prepareSchemaForInteractionModel {}", e);
			throw new AlexaApiException(e.getMessage(), AlexaApiErrorType.JSON_PARSER_EXCEPTION);
		}
		return updateSkillJson;
	}
	
	
	
	private InteractionModel copy(InteractionModel model) {
        // TODO Auto-generated method stub
        
        InteractionModel m = new InteractionModel();
        m.setDialog(createDialog(model.getDialog()));
        m.setLanguageModel(PreparelanguageModel(m.getLanguageModel()));
        m.setPrompts(model.getPrompts());
        return m;
        
    }
    public LanguageModel PreparelanguageModel(LanguageModel languageModel) {
    	System.out.println("languagemodel"+languageModel.toString());
    	System.err.print(languageModel.toString());
        return new LanguageModel(languageModel.getLanguateIntents(),languageModel.getInvocationName());
    }
    
    private Dialog createDialog(Dialog dialog) {
        // TODO Auto-generated method stub
        
        Dialog d = new Dialog();
        d.setDelegationStrategy(dialog.getDelegationStrategy());
        d.setDialogIntents(prepareDialogIntents(dialog.getDialogIntents()));
        
        return d;
    }
    private List<DialogIntent> prepareDialogIntents(List<DialogIntent> dialogIntents) {
        // TODO Auto-generated method stub
        return dialogIntents.stream().map(m -> dialogIntent(m)).collect(Collectors.toList());
    }
    
    public DialogIntent dialogIntent(DialogIntent intent) {
        return new DialogIntent(intent.getIntentName(), intent.getConfirmationRequired(), intent.getDialogSlots(), intent.getDialogPrompts());
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

	public String createUpdateRequest(String skillId, Manifest manifest) {

		UpdateSkill skill = new UpdateSkill(manifest);

		String res = null;
		try {
			res = mapper.writeValueAsString(skill);
			JsonNode newNode = removeExtraFields((ObjectNode) mapper.readTree(res));

			res = mapper.writeValueAsString(newNode);
			LOGGER.debug("createUpdateRequest json  {}", res);
		} catch (IOException e) {
			LOGGER.error("Exception while serializing json in createUpdateRequest {}", e);
			throw new AlexaApiException(e.getMessage(), AlexaApiErrorType.JSON_PARSER_EXCEPTION);
		}
		return res;

	}

	

	

	public PublishInfo createPublishingInfo(Map<String, PublishLocale> locales, String category,
			boolean isAvailableWorldwide) {

		return new PublishInfo(isAvailableWorldwide, category,null,null,locales);
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
			// TODO removed  {annotations:{}} from json we need to find why the annotations is added
			// to json even if it is not model objects, i assume it is because of @Component annotation from mule
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

		return intents.stream().map(in -> new LanguageIntent(in.getIntentName(),in.getSlots(),in.getSamples())).collect(Collectors.toList());

	}

	private DialogIntent mapIntent(Intent intent) {
		// Iterate slots and prepare Dialog slots
				List<DialogSlot> dialogSlots = intent.getSlots().stream().map(s -> new DialogSlot(s.getSname(), s.getType()))
						.collect(Collectors.toList());
		return new DialogIntent(intent.getIntentName(),intent.getConfirmationRequired(),dialogSlots,Collections.emptyMap());
	}

	
	
	
}
