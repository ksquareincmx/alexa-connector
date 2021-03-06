/**
 * (c) 2003-2017 MuleSoft, Inc. The software in this package is published under the terms of the Commercial Free Software license V.1 a copy of which has been included with this distribution in the LICENSE.md file.
 */

package org.mule.modules.alexa.internal.connection.provider;

import javax.inject.Inject;

import org.mule.runtime.api.connection.ConnectionException;
import org.mule.runtime.api.connection.ConnectionValidationResult;
import org.mule.runtime.api.connection.PoolingConnectionProvider;
import org.mule.runtime.extension.api.annotation.connectivity.oauth.AuthorizationCode;
import org.mule.runtime.extension.api.connectivity.oauth.AuthorizationCodeState;
import org.mule.runtime.http.api.HttpService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@AuthorizationCode(authorizationUrl = "https://www.amazon.com/ap/oa", accessTokenUrl = "https://api.amazon.com/auth/o2/token")
public class AlexaOauthConnectionProvider implements PoolingConnectionProvider<AlexaConnection> { // NOSONAR

	private static final Logger logger = LoggerFactory.getLogger(AlexaOauthConnectionProvider.class);

	private AuthorizationCodeState state;
	
	@Inject
	HttpService httpService;
   
	@Override
	public AlexaConnection connect() throws ConnectionException {
		if (state.getAccessToken() == null) {
			throw new ConnectionException("Unable to get aws access token");
		}
		logger.debug("Auth Token state:"+state.getState());
		return new AlexaConnection(state.getAccessToken(),httpService);
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
