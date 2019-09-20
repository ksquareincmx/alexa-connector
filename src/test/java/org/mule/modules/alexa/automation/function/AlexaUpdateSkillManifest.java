package org.mule.modules.alexa.automation.function;

import org.junit.Test;
import org.mule.functional.junit4.MuleArtifactFunctionalTestCase;

 public class AlexaUpdateSkillManifest  extends MuleArtifactFunctionalTestCase{
	
	
	@Override
	protected String getConfigFile() {
		// TODO Auto-generated method stub
		return "test-mule-updateskillmanifest.xml";
	}
	
	
	@Test
	public void testUpdateSkillManifest() throws Exception{
		
		 String payloadValue = ((String) flowRunner("update-skill-manifest").run()
                 .getMessage()
                 .getPayload()
                 .getValue());
		 System.out.println(payloadValue);
		 
	}

} 
