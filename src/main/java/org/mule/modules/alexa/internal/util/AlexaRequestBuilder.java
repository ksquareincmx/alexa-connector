/**
 * (c) 2003-2017 MuleSoft, Inc. The software in this package is published under the terms of the Commercial Free Software license V.1 a copy of which has been included with this distribution in the LICENSE.md file.
 */

package org.mule.modules.alexa.internal.util;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
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
import org.mule.modules.alexa.api.domain.intents.Intent;
import org.mule.modules.alexa.api.domain.intents.InteractionModel;
import org.mule.modules.alexa.api.domain.intents.LanguageModel;
import org.mule.modules.alexa.api.domain.intents.Prompt;
import org.mule.modules.alexa.api.domain.intents.PromptInfo;
import org.mule.modules.alexa.api.domain.intents.Variation;
import org.mule.modules.alexa.api.domain.update.ApiInfo;
import org.mule.modules.alexa.api.domain.update.ApiInfo.CustomApi;
import org.mule.modules.alexa.api.domain.update.Events;
import org.mule.modules.alexa.api.domain.update.Locale;
import org.mule.modules.alexa.api.domain.update.Manifest;
import org.mule.modules.alexa.api.domain.update.PrivacyComplaince;
import org.mule.modules.alexa.api.domain.update.PublishInfo;
import org.mule.modules.alexa.api.domain.update.UpdateSkill;
import org.mule.modules.alexa.internal.error.AlexaApiErrorType;
import org.mule.modules.alexa.internal.exceptions.AlexaApiException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.annotation.JsonFormat.Feature;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.ser.impl.WritableObjectId;

public class AlexaRequestBuilder {

	private static final Logger LOGGER = LoggerFactory.getLogger(AlexaRequestBuilder.class);

	static ObjectMapper mapper = new ObjectMapper();

	public AlexaRequestBuilder() {
		mapper.setSerializationInclusion(Include.NON_NULL);
		mapper.configure(DeserializationFeature.FAIL_ON_UNRESOLVED_OBJECT_IDS, true);
		//mapper.s
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
		locales.put("en-US", locale);
		// create publish information
		PublishInfo publishInfo = createPublishingInfo(locales, "SMART_HOME", false);
		publishInfo.setTestingInstructions("1) Say 'Alexa, discover my devices' 2) Say 'Alexa, turn on sample lights'");
		publishInfo.setDistributionCountries(Arrays.asList("US","GB"));
		
		
		Locale privacyLocale = new Locale("http://www.termsofuse.sampleskill.com", "http://www.myprivacypolicy.sampleskill.com");
		HashMap<String, Locale> localesPrivacy = new HashMap<>();
		localesPrivacy.put("en-US", privacyLocale);
		PrivacyComplaince privacyComplaince = new PrivacyComplaince(localesPrivacy);
		// create apiinfo and set endpoint
		ApiInfo apiInfo = createApiInfo(endpoint);
		
		// create manifest and set api,publish info to manifest
		Manifest manifest = new Manifest();
		manifest.setApis(apiInfo);
		manifest.setPublishingInformation(publishInfo);
		manifest.setPrivacyAndCompliance(privacyComplaince);
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
		
		 List<Prompt> prompts = intents.stream().map(m -> m.getPrompts()).flatMap(m -> m.stream()).collect(Collectors.toList());
	        interactionModel.setPrompts(prompts);
	        
	       
	       prompts.forEach(p -> promtToVariation(p,p.getVmap()));//.flatMap(promt -> prompts.stream())
		// set dialog and language model
	       Dialog dialog = new Dialog();
			dialog.setIntents(intents);
		LanguageModel language = new LanguageModel();
		language.setIntents(intents);
		LOGGER.info("Promts: {}"+prompts);
		interactionModel.setDialog(dialog);
		interactionModel.setLanguageModel(language);
		Map<String, InteractionModel> reqObj = new HashMap<>();
		reqObj.put("interactionModel", interactionModel);
		String updateSkillJson = null;
		try {
			updateSkillJson = mapper.writeValueAsString(reqObj);
			
			JsonNode newNode = removeEmptyFields((ObjectNode)mapper.readTree(updateSkillJson));
			
			updateSkillJson =	mapper.writeValueAsString(newNode);
			LOGGER.info("updateCreatedSkill  json {}", updateSkillJson);
		} catch (IOException e) {
			LOGGER.error("Exception while serializing json in updateCreatedSkill {}", e);
			throw new AlexaApiException(e.getMessage(), AlexaApiErrorType.JSON_PARSER_EXCEPTION);
		}
		return updateSkillJson;
	}
	
	public void promtToVariation(Prompt p ,List<PromptInfo> info) {
		
		List<Variation> variation =  info.stream().map(px -> new Variation(px.getType(), px.getValue())).collect(Collectors.toList());
		
		p.setVariations(variation);
		p.setVmap(null);
		
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
	

	
	 public static ObjectNode removeEmptyFields(final ObjectNode jsonNode) {
		 
		// {annotations:{}}
	        ObjectNode ret = new ObjectMapper().createObjectNode();
	        Iterator<Entry<String, JsonNode>> iter = jsonNode.fields();
	        while (iter.hasNext()) {
	            Entry<String, JsonNode> entry = iter.next();
	            String key = entry.getKey();
	            JsonNode value = entry.getValue();
	           
	            if (value instanceof ObjectNode  ) {
	                Map<String, ObjectNode> map = new HashMap<String, ObjectNode>();
	                map.put(key, removeEmptyFields((ObjectNode)(value)));
	                ret.setAll(map);
	            	
	            }
	            else if (value instanceof ArrayNode) {
	                ret.set(key, removeEmptyFields((ArrayNode)(value)));
	            }
	            else if (value.asText() != null && !value.asText().isEmpty() ) {
	                ret.set(key, value);
	            }
	            boolean a = key.equals("annotations");
	            if(a) {
		        ret.remove("annotations");
	            }
	        }

	       
	        return ret;
	    }
	 
	  public static ArrayNode removeEmptyFields(ArrayNode array) {
	        ArrayNode ret = new ObjectMapper().createArrayNode();
	        Iterator<JsonNode> iter = array.elements();

	        while (iter.hasNext()) {
	            JsonNode value = iter.next();
	            

	            if (value instanceof ArrayNode) {
	                ret.add(removeEmptyFields((ArrayNode)(value)));
	            }
	            else if (value instanceof ObjectNode) {
	            	ObjectNode nullVal = removeEmptyFields((ObjectNode)(value));
	            	if(nullVal.toString().length() !=2) {
	                ret.add(nullVal);
	            	}
	            }
	            else if (value != null && !value.textValue().isEmpty() ){
	                ret.add(value);
	            }
	        }

	        return ret;
	    }

	
	
}
