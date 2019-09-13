package org.mule.modules.alexa.automation.function;

import org.junit.Assert;
import org.junit.Test;
import org.mule.extension.alexaoauth.AlexaoauthOperationsTestCase;


/** public class AlexaCreateSkillTestCase extends AlexaoauthOperationsTestCase{

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
		 Assert.assertEquals(payloadValue.contains("{}"),true);
	} 
} **/

