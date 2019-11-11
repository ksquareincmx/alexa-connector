/**
 * (c) 2003-2019 MuleSoft, Inc. The software in this package is published under the terms of the Commercial Free Software license V.1 a copy of which has been included with this distribution in the LICENSE.md file.
 */

package org.mule.modules.alexa.internal.extension;

import org.mule.runtime.extension.api.annotation.Extension;
import org.mule.modules.alexa.internal.config.AlexaConfiguration;
import org.mule.runtime.api.meta.Category;
import org.mule.runtime.extension.api.annotation.Configurations;
import org.mule.runtime.extension.api.annotation.dsl.xml.Xml;
import org.mule.runtime.extension.api.annotation.license.RequiresEnterpriseLicense;

/**
 * This is the main class of an extension, is the entry point from which
 * configurations, connection providers, operations and sources are going to be
 * declared.
 */

@Xml(prefix = "alexa")
@Extension(name = "Alexa" , category=Category.CERTIFIED)
@RequiresEnterpriseLicense(allowEvaluationLicense = true)
@Configurations(AlexaConfiguration.class)
public class AlexaConnector {

}
