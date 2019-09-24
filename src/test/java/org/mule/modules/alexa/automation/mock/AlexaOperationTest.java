/**
 * (c) 2003-2017 MuleSoft, Inc. The software in this package is published under the terms of the Commercial Free Software license V.1 a copy of which has been included with this distribution in the LICENSE.md file.
 */
package org.mule.modules.alexa.automation.mock;

import static org.junit.Assert.assertEquals;

import javax.annotation.meta.When;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.runners.MockitoJUnitRunner;
import org.mule.modules.alexa.internal.connection.AlexaConnection;
import org.mule.modules.alexa.internal.operation.AlexaOperations;
import org.mule.modules.alexa.internal.util.AlexaRequestBuilder;
import org.mule.runtime.http.api.HttpConstants;
import org.mule.runtime.http.api.HttpService;

@RunWith(MockitoJUnitRunner.class)
public class AlexaOperationTest {

	
	@Mock
	private AlexaOperations operations;
	
	@Mock
	private AlexaRequestBuilder requestBuilder;
	
	@Mock
	AlexaConnection connection;
	
	@Mock
	HttpService service;
	
	@Before
	public void beforeTest() {
		
	}
	
	
	//@Test
	public void getSkillTest() {
		
	Mockito.when(connection.sendRequest(Mockito.any(HttpConstants.Method.GET.getClass()), Mockito.anyString(), Mockito.anyString())).thenReturn("{}");
	String empty=	operations.getSkillInfo(connection, "8445");
	assertEquals("{}", empty);
	}
	
}
