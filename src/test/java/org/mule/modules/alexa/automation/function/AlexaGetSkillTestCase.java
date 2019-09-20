package org.mule.modules.alexa.automation.function;

import org.junit.Assert;
import org.junit.Test;
import org.mule.functional.junit4.MuleArtifactFunctionalTestCase;

public class AlexaGetSkillTestCase extends MuleArtifactFunctionalTestCase {

	@Override
	protected String getConfigFile() {
		return "test-mule-getinfoskill.xml";
	}

	@Test
	public void executeSayHiOperation() throws Exception {
		String payloadValue = ((String) flowRunner("get-skill").run().getMessage().getPayload().getValue());
		Assert.assertEquals(payloadValue.contains("manifest"), true);
	}

}
