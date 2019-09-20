package org.mule.modules.alexa.automation.function;

import org.junit.Test;
import org.mule.functional.junit4.MuleArtifactFunctionalTestCase;
public class AlexaDeleteSkillTestCase extends MuleArtifactFunctionalTestCase{

	@Override
	protected String getConfigFile() {
		// TODO Auto-generated method stub
		return "test-mule-deleteskill.xml";
	}
	
	@Test
	public void testDeletSkill() throws Exception{
		
		 String payloadValue = ((String) flowRunner("delete-skill").run()
                 .getMessage()
                 .getPayload()
                 .getValue());
	}
}
