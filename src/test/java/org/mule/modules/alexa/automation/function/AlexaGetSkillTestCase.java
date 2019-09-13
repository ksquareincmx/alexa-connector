package org.mule.modules.alexa.automation.function;

import java.util.Map;
import java.util.Properties;


import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mule.extension.alexaoauth.AlexaoauthOperationsTestCase;
//import org.mule.extension.alexaoauth.AlexaoauthOperationsTestCase;
import org.mule.functional.junit4.MuleArtifactFunctionalTestCase;
import org.mule.modules.alexa.api.domain.update.Manifest;
import org.mule.modules.alexa.internal.connection.AlexaConnection;
import org.mule.modules.alexa.internal.operation.AlexaOperations;
import org.mule.modules.alexa.internal.util.AlexaRequestBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.*;


/** public class AlexaGetSkillTestCase extends  MuleArtifactFunctionalTestCase {
	
 
	  @Override
	  protected String getConfigFile() {
	    return "test-mule-getinfoskill.xml";
	  }
	  
	  

	  @Test
	  public void executeSayHiOperation() throws Exception {
	    String payloadValue = ((String) flowRunner("get-skill").run()
	                                      .getMessage()
	                                      .getPayload()
	                                      .getValue());
	    Assert.assertEquals(payloadValue.contains("manifest"),true);
	  }

	

} **/
