package org.mule.modules.alexa.automation.function;

import org.junit.Assert;
import org.junit.Test;
import org.mule.functional.junit4.MuleArtifactFunctionalTestCase;

public class AlexaUpdateSkillTestCase extends MuleArtifactFunctionalTestCase {

	@Override
	protected String getConfigFile() {
		// TODO Auto-generated method stub
		return "test-mule-updateskill.xml";
	}

	@Test
	public void testUpdateSkill() throws Exception {

		String payloadValue = ((String) flowRunner("update-skill").run().getMessage().getPayload().getValue());
		Assert.assertEquals(payloadValue.contains("Update Skill Request Accepeted successfully"), true);
	}

}
