/**
 * (c) 2003-2019 MuleSoft, Inc. The software in this package is published under the terms of the Commercial Free Software license V.1 a copy of which has been included with this distribution in the LICENSE.md file.
 */
package org.mule.modules.alexa.automation.function;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.mule.functional.junit4.MuleArtifactFunctionalTestCase;

public class AlexaDeleteSkillTestCase extends MuleArtifactFunctionalTestCase {

	@Override
	protected String getConfigFile() {
		// TODO Auto-generated method stub
		return "test-mule-deleteskill.xml";
	}

	@Test
	public void testDeletSkill() throws Exception {

		String payloadValue = ((String) flowRunner("delete-skill").run().getMessage().getPayload().getValue());
		// assertEquals("", payloadValue);
	}
}
