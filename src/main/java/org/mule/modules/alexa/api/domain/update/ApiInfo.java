/**
 * (c) 2003-2017 MuleSoft, Inc. The software in this package is published under the terms of the Commercial Free Software license V.1 a copy of which has been included with this distribution in the LICENSE.md file.
 */

package org.mule.modules.alexa.api.domain.update;

import org.mule.modules.alexa.api.domain.AlexaRequestURL;
import org.mule.runtime.extension.api.annotation.Expression;
import org.mule.runtime.extension.api.annotation.param.Parameter;

public class ApiInfo {

	@Parameter
	@Expression
	private CustomApi custom;

	public ApiInfo() {

	}

	public ApiInfo(CustomApi custom) {
		this.custom = custom;
	}

	public CustomApi getCustom() {
		return custom;
	}

	public void setCustom(CustomApi custom) {
		this.custom = custom;
	}

	public static  ApiInfo defaultApi(String endpoint) {
	EndpointInfo endpointInfo = 	new EndpointInfo(endpoint, AlexaRequestURL.TRUSTED_CERT);
	return new ApiInfo(new CustomApi(endpointInfo,null));
	}
	
}
