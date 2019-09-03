package org.mule.modules.alexa.internal.connection;

import org.mule.runtime.api.connection.ConnectionException;
import org.mule.runtime.api.connection.ConnectionValidationResult;
import org.mule.runtime.api.connection.PoolingConnectionProvider;
import org.mule.runtime.extension.api.annotation.Alias;
import org.mule.runtime.extension.api.annotation.connectivity.oauth.AuthorizationCode;
import org.mule.runtime.extension.api.annotation.param.Parameter;
import org.mule.runtime.extension.api.connectivity.oauth.AuthorizationCodeState;


@Alias("token")
public class TokenConnection implements PoolingConnectionProvider<AlexaConnection> {
	
	@Parameter
	String token;
	
	

	@Override
	public AlexaConnection connect() throws ConnectionException {
		// TODO Auto-generated method stub
		return new AlexaConnection(token);
	}

	@Override
	public void disconnect(AlexaConnection connection) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ConnectionValidationResult validate(AlexaConnection connection) {
		// TODO Auto-generated method stub
		return null;
	}

}
