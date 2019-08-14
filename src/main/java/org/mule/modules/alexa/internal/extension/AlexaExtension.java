package org.mule.modules.alexa.internal.extension;

import org.mule.runtime.extension.api.annotation.Extension;
import org.mule.modules.alexa.internal.config.AlexaConfiguration;
import org.mule.runtime.extension.api.annotation.Configurations;
import org.mule.runtime.extension.api.annotation.dsl.xml.Xml;

/**
 * This is the main class of an extension, is the entry point from which
 * configurations, connection providers, operations and sources are going to be
 * declared.
 */

@Xml(prefix = "alexa")
@Extension(name = "Alexa")
@Configurations(AlexaConfiguration.class)
public class AlexaExtension {

}
