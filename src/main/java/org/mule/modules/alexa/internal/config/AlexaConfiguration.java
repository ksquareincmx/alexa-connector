package org.mule.modules.alexa.internal.config;

import org.mule.modules.alexa.internal.connection.AlexaOauthConnectionProvider;
import org.mule.modules.alexa.internal.operation.AlexaOperations;
import org.mule.runtime.extension.api.annotation.Operations;
import org.mule.runtime.extension.api.annotation.connectivity.ConnectionProviders;

/**
 * This class represents an extension configuration, values set in this class
 * are commonly used across multiple operations since they represent something
 * core from the extension.
 */
@Operations(AlexaOperations.class)
@ConnectionProviders(AlexaOauthConnectionProvider.class)
public class AlexaConfiguration {

}
