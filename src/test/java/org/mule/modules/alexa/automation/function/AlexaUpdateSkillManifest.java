/**
 * (c) 2003-2019 MuleSoft, Inc. The software in this package is published under the terms of the Commercial Free Software license V.1 a copy of which has been included with this distribution in the LICENSE.md file.
 */
package org.mule.modules.alexa.automation.function;

import org.junit.Assert;
import org.junit.Test;
import org.mule.functional.junit4.MuleArtifactFunctionalTestCase;

 public class AlexaUpdateSkillManifest  extends MuleArtifactFunctionalTestCase{
	
	
	@Override
	protected String getConfigFile() {
		return "test-mule-updateskillmanifest.xml";
	}
	
	
	@Test
	public void testUpdateSkillManifest() throws Exception{
		
		 String payloadValue = ((String) flowRunner("update-skill-manifest").run()
                 .getMessage()
                 .getPayload()
                 .getValue());
		 Assert.assertEquals("{}", payloadValue);
		 
	}

} 
