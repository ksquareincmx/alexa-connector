/**
 * (c) 2003-2017 MuleSoft, Inc. The software in this package is published under the terms of the Commercial Free Software license V.1 a copy of which has been included with this distribution in the LICENSE.md file.
 */

package org.mule.modules.alexa.internal.config;

import org.mule.modules.alexa.internal.connection.provider.AlexaOauthConnectionProvider;
import org.mule.modules.alexa.internal.connection.provider.TokenConnectionProvider;
import org.mule.modules.alexa.internal.operation.AlexaOperations;
import org.mule.runtime.extension.api.annotation.Operations;
import org.mule.runtime.extension.api.annotation.connectivity.ConnectionProviders;

/**
 * This class represents an extension configuration, values set in this class
 * are commonly used across multiple operations since they represent something
 * core from the extension.
 */
@Operations(AlexaOperations.class)
@ConnectionProviders({TokenConnectionProvider.class, AlexaOauthConnectionProvider.class})
public class AlexaConfiguration {

	
	
}
