/**
 * (c) 2003-2017 MuleSoft, Inc. The software in this package is published under the terms of the Commercial Free Software license V.1 a copy of which has been included with this distribution in the LICENSE.md file.
 */
package org.mule.modules.alexa.internal.connection;

import javax.inject.Inject;

import org.mule.runtime.api.connection.ConnectionException;
import org.mule.runtime.api.connection.ConnectionValidationResult;
import org.mule.runtime.api.connection.PoolingConnectionProvider;
import org.mule.runtime.extension.api.annotation.Alias;
import org.mule.runtime.extension.api.annotation.param.Parameter;
import org.mule.runtime.http.api.HttpService;

@Alias("token")
public class TokenConnection implements PoolingConnectionProvider<AlexaConnection> {

	@Parameter
	String token;
	
	@Inject
	HttpService httpService;

	@Override
	public AlexaConnection connect() throws ConnectionException {
		return new AlexaConnection(token,httpService);
	}

	@Override
	public void disconnect(AlexaConnection connection) {
		connection.disconnect();
		
	}

	@Override
	public ConnectionValidationResult validate(AlexaConnection connection) {
		return ConnectionValidationResult.success();
	}

}
