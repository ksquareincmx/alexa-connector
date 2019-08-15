package org.mule.modules.alexa.internal.operation;

import static org.mule.runtime.api.meta.ExpressionSupport.SUPPORTED;
import static org.mule.runtime.extension.api.annotation.param.MediaType.ANY;

import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.mule.modules.alexa.api.domain.AlexaRequestURL;
import org.mule.modules.alexa.api.domain.intents.Intent;
import org.mule.modules.alexa.api.domain.intents.IntentParameter;
import org.mule.modules.alexa.internal.connection.AlexaConnection;
import org.mule.modules.alexa.internal.util.AlexaRequestBuilder;
import org.mule.modules.alexa.internal.util.AlexaRequestUtility;
import org.mule.runtime.api.meta.ExpressionSupport;
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
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * This class is a container for operations, every public method in this class
 * will be taken as an extension operation.
 */
public class AlexaOperations {

	private static final Logger LOGGER = LoggerFactory.getLogger(AlexaOperations.class);

	private AlexaRequestUtility alexaRequestUtility = new AlexaRequestUtility();
	private AlexaRequestBuilder alexaRequestBuilder = new AlexaRequestBuilder();

	@MediaType(value = ANY, strict = false)
	public String createSkill(@Connection AlexaConnection alexaConnection, @Expression(SUPPORTED) String vendorId,
			@Expression(SUPPORTED) String summary, @Expression(SUPPORTED) List<String> examplePhrases,
			@Expression(SUPPORTED) List<String> keywords, @Expression(SUPPORTED) String skillName,
			@Expression(SUPPORTED) String description, @Expression(SUPPORTED) String endpoint,
			@Optional @NullSafe @Expression(ExpressionSupport.NOT_SUPPORTED) @ParameterDsl(allowReferences = false) List<Intent> intents) throws Exception {

		LOGGER.debug("Access token {}", alexaConnection.getAccessToken());
		try {
			String alexaRequest = alexaRequestBuilder.createAlexaSkillRequestBuilder(vendorId, summary, examplePhrases,
					keywords, skillName, description, endpoint);
			String createSkillResponse = alexaRequestUtility.doPost(AlexaRequestURL.CREATE_ALEXA_SKILL,
					alexaConnection.getAccessToken(), alexaRequest);
			LOGGER.debug("Create Alexa Skill Response {}", createSkillResponse);

			Map<String, String> response = alexaRequestBuilder.getMapper().readValue(createSkillResponse,
					new TypeReference<Map<String, String>>() {
					});
			String skillId = response.get("skillId");
			LOGGER.info("Skill ID after parsing", skillId);

			String updateSkillRequest = alexaRequestBuilder.updateCreatedSkill(skillId, intents);
			return alexaRequestUtility.doPut(String.format(AlexaRequestURL.UPDATE_ALEXA_SKILL, skillId),
					alexaConnection.getAccessToken(), updateSkillRequest);

		} catch (Exception e) {
			LOGGER.error("Got error while Creating skill {}",e);
			throw e;
		}

	}

	@MediaType(value = ANY, strict = false)
	public String getSkillInfo(@Connection AlexaConnection alexaConnection, @Expression(SUPPORTED) String skillId) throws Exception {
		LOGGER.debug("Alexa Authorization  Token {}" ,alexaConnection.getAccessToken());
		return alexaRequestUtility.doGet(AlexaRequestURL.GET_ALEXA_INFO, alexaConnection.getAccessToken(), skillId);
	}

	@MediaType(value = ANY, strict = false)
	public String UseExistingSkill(@Connection AlexaConnection alexaConnection, @Expression(SUPPORTED) String skillId,
			@Expression(SUPPORTED) String stage, @Expression(SUPPORTED) String requestType,
			@Expression(SUPPORTED) String intentName, @Expression(SUPPORTED) String inputString,
			@Optional @NullSafe @Expression(ExpressionSupport.NOT_SUPPORTED) @ParameterDsl(allowReferences = false) Map<String, String> testSlots)
			throws Exception {

		LOGGER.debug("Alexa Authorization  Token: " + alexaConnection.getAccessToken());

		try {
			String testAlexaSkillJsonRequest = alexaRequestBuilder.getAlexaRequestJson(skillId, requestType, testSlots,
					intentName);
			String response = alexaRequestUtility.doPost(
					String.format(AlexaRequestURL.TEST_ALEXA_SKILL, skillId, stage), alexaConnection.getAccessToken(),
					testAlexaSkillJsonRequest);

			JsonNode node =	alexaRequestBuilder.getMapper().readTree(response);
			JsonNode content =  node.findValue("content");
			LOGGER.info("UseExistingSkill Response: {}", content);
			return content.asText();
		} catch (Exception e) {
			LOGGER.error("Got error while UseExistingSkill  {}",e);
			throw e;
		}

	
	}

	
	
	
	
	@MediaType(value = ANY, strict = false)
	public String DeleteSkill(@Connection AlexaConnection alexaConnection, @Expression(SUPPORTED) String skillId) throws Exception {
		System.out.println("Alexa Authorization  Token ======> " + alexaConnection.getAccessToken());
		return alexaRequestUtility.doGet(AlexaRequestURL.GET_ALEXA_INFO, alexaConnection.getAccessToken(), skillId);
	}

	

	@MediaType(value = ANY, strict = false)
	public String updateSkill(@Connection AlexaConnection alexaConnection, @Expression(SUPPORTED) String skillId,
			String apiEndpoint, List<String> interfaces, List<String> permissions, String eventEndpoint,
			List<String> subscriptions) throws Exception {
		LOGGER.info("Update skill method with skillId {}",skillId);
		String result;

			String updateRequest = alexaRequestBuilder.createUpdateRequest(skillId, apiEndpoint, interfaces,
					permissions, eventEndpoint, subscriptions);
			String url = String.format(AlexaRequestURL.UPDATE_EXISTING_SKILL, skillId);
			result = alexaRequestUtility.doPut(url, alexaConnection.getAccessToken(), updateRequest);

		LOGGER.info("Update skill method response  {}",result);
		return result;
	}

	
	/*@MediaType(value = ANY, strict = false)
	public String customSkill(
			@Expression(ExpressionSupport.NOT_SUPPORTED) @ParameterDsl(allowReferences = false) List<String> handlers) {

		List<GenericRequestHandler> handlerObjects = new ArrayList<GenericRequestHandler>();

		try {
			List<RequestHandler> handlerObjects1 = new ArrayList<RequestHandler>();

			String className = "com.alexa.sdfc.handlers.LaunchRequestHandler";

			// Get Class instance
			Class<?> child = Class.forName(className);
			ClassLoader classLoader = child.getClassLoader();
			System.out.println("The system classLoader = " + ClassLoader.getSystemClassLoader()
					+ "\n The classLoader loading our classes is = " + classLoader);

			Class<?> parent = classLoader.loadClass("com.amazon.ask.dispatcher.request.handler.RequestHandler");
			System.out.println("Child -----> " + child.getClassLoader());
			System.out.println("Parent -----> " + parent.getClassLoader());

			// Class<?> requestHandler = (Class<?>) child.newInstance();
			RequestHandler requestHandler = (RequestHandler) parent.cast(child.newInstance());
			System.out.println("Object ---> " + requestHandler);

			// RequestHandler requestHandler = (RequestHandler)
			// parent.cast(child.newInstance());
			// System.out.println("Object ---> "+ requestHandler);

			// Skill skill =
			// Skills.standard().addRequestHandler((GenericRequestHandler<HandlerInput,
			// java.util.Optional<Response>>) handlerObjects1).build();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "";
	}*/
}
