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
		// TODO Auto-generated method stub no proxy here
		return new AlexaConnection(token,httpService);
	}

	@Override
	public void disconnect(AlexaConnection connection) {
		// TODO Auto-generated method stub
		connection.disconnect();
		
	}

	@Override
	public ConnectionValidationResult validate(AlexaConnection connection) {
		// TODO Auto-generated method stub
		return ConnectionValidationResult.success();
	}

}
