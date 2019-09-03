package org.mule.modules.alexa.automation.function;

import java.util.Map;
import java.util.Properties;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mule.extension.alexaoauth.AlexaoauthOperationsTestCase;
import org.mule.functional.junit4.MuleArtifactFunctionalTestCase;
import org.mule.modules.alexa.api.domain.update.Manifest;
import org.mule.modules.alexa.internal.connection.AlexaConnection;
import org.mule.modules.alexa.internal.operation.AlexaOperations;
import org.mule.modules.alexa.internal.util.AlexaRequestBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;

import junit.framework.Assert;

 public class AlexaGetSkillTestCase extends AlexaoauthOperationsTestCase {
	
 
		// private ObjectMapper mapper = new ObjectMapper();
	  @Override
	  protected String getConfigFile() {
	    return "test-mule-getinfoskill.xml";
	  }
	  
	  

	  @Test
	  public void executeSayHiOperation() throws Exception {
	    String payloadValue = ((String) flowRunner("oauth-testFlow").run()
	                                      .getMessage()
	                                      .getPayload()
	                                      .getValue());
	    
	    ObjectMapper mapper = new ObjectMapper();

		Map<String, Object> jsonObject = mapper.readValue(payloadValue, Map.class);

		 //result = (int) jsonObject.get("uri");
	    
	  Manifest manifest = mapper.readValue(payloadValue, Manifest.class);
	    Assert.assertEquals(payloadValue.contains("https://alexaservice.us-e1.cloudhub.io/test"),true);
	  }

	 /** @Test
	  public void executeRetrieveInfoOperation() throws Exception {
	    String payloadValue = ((String) flowRunner("retrieveInfoFlow")
	                                      .run()
	                                      .getMessage()
	                                      .getPayload()
	                                      .getValue());
	    assertThat(payloadValue, is("Using Configuration [configId] with Connection id [aValue:100]"));
	  } **/

} 
