package org.mule.modules.alexa.automation.function;

import org.junit.Test;
import org.mule.functional.junit4.MuleArtifactFunctionalTestCase;

/** public class AlexaUpdateSkillIntentsTestCase extends MuleArtifactFunctionalTestCase {
	
	@Override
	protected String getConfigFile() {
		// TODO Auto-generated method stub
		return "test-mule-updateskillintents.xml";
	}
	
	@Test
	public void testUpdateSkillIntent() throws Exception{
		
		 String payloadValue = ((String) flowRunner("updateskillIntent-flow").run()
                 .getMessage()
                 .getPayload()
                 .getValue());
	}

}
**/