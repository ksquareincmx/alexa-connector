package org.mule.modules.alexa.internal.connection;

import org.mule.runtime.api.connection.ConnectionException;
import org.mule.runtime.api.connection.ConnectionValidationResult;
import org.mule.runtime.api.connection.PoolingConnectionProvider;
import org.mule.runtime.extension.api.annotation.connectivity.oauth.AuthorizationCode;
import org.mule.runtime.extension.api.connectivity.oauth.AuthorizationCodeState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@AuthorizationCode(authorizationUrl = "https://www.amazon.com/ap/oa", accessTokenUrl = "https://api.amazon.com/auth/o2/token")
public class AlexaOauthConnectionProvider implements PoolingConnectionProvider<AlexaConnection> {

	private final Logger LOGGER = LoggerFactory.getLogger(AlexaOauthConnectionProvider.class);

	private AuthorizationCodeState state;
   
	@Override
	public AlexaConnection connect() throws ConnectionException {
		if (state.getAccessToken() == null) {
			throw new ConnectionException("Unable to get aws access token");
		}

		return new AlexaConnection(state.getAccessToken());
	}
    
	@Override
	public void disconnect(AlexaConnection connection) {
		try {
			// connection.invalidate();
		} catch (Exception e) {
			LOGGER.error("Error while disconnecting", e);
		}
	}  
   
	@Override
	public ConnectionValidationResult validate(AlexaConnection connection) {
		return ConnectionValidationResult.success();
	}
}
