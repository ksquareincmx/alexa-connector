/**
 * (c) 2003-2019 MuleSoft, Inc. The software in this package is published under the terms of the Commercial Free Software license V.1 a copy of which has been included with this distribution in the LICENSE.md file.
 */
package org.mule.modules.alexa.automation.function;

import org.junit.Assert;
import org.junit.Test;
import org.mule.functional.junit4.MuleArtifactFunctionalTestCase;
import org.mule.modules.alexa.internal.exceptions.AlexaApiException;


 public class AlexaCreateSkillTestCase extends MuleArtifactFunctionalTestCase{

	@Override
	protected String getConfigFile() {
		// TODO Auto-generated method stub
		return "test-mule-createskill.xml";
	}
	
	@Test
	public void testCreateSkill() throws Exception{
		 String payloadValue = ((String)flowRunner("create-skill").run()
                 .getMessage()
                 .getPayload()
                 .getValue());
		 Assert.assertEquals(payloadValue.contains("Alexa Skill created successfully"),true);
	} 
	
	@Test(expected = Exception.class)
	public void testWrongCreateSkill_NoskillId() throws Exception{
		 String payloadValue = ((String)flowRunner("wrong-skill").run()
                 .getMessage()
                 .getPayload()
                 .getValue());
		 System.out.println(payloadValue);
	} 
	
	@Test
	public void testWrongCreateSkill_Intents() throws Exception{
		 String payloadValue = ((String)flowRunner("wrong-skill-intents").run()
                 .getMessage()
                 .getPayload()
                 .getValue());
		 Assert.assertEquals(payloadValue.contains("message"),true);
	} 
	
} 

