package org.mule.modules.alexa.automation.function;

import org.junit.Assert;
import org.junit.Test;
import org.mule.functional.junit4.MuleArtifactFunctionalTestCase;


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
} 

