
package org.mule.modules.alexa.api.domain.update;

import org.mule.runtime.extension.api.annotation.Expression;
import org.mule.runtime.extension.api.annotation.param.Parameter;

/**
 * (c) 2003-2017 MuleSoft, Inc. The software in this package is published under the terms of the Commercial Free Software license V.1 a copy of which has been included with this distribution in the LICENSE.md file.
 */

public class PrivacyLocale {

	@Parameter
	private String privacyPolicyUrl;
	
	@Parameter
	@Expression
	private String termsOfUseUrl;
	
	
	
	public PrivacyLocale() {
		
	}
	
	


	public PrivacyLocale(String privacyPolicyUrl, String termsOfUseUrl) {
		super();
		this.privacyPolicyUrl = privacyPolicyUrl;
		this.termsOfUseUrl = termsOfUseUrl;
	}




	public String getPrivacyPolicyUrl() {
		return privacyPolicyUrl;
	}
	public void setPrivacyPolicyUrl(String privacyPolicyUrl) {
		this.privacyPolicyUrl = privacyPolicyUrl;
	}
	public String getTermsOfUseUrl() {
		return termsOfUseUrl;
	}
	public void setTermsOfUseUrl(String termsOfUseUrl) {
		this.termsOfUseUrl = termsOfUseUrl;
	}

	
	
	
	
	public static PrivacyLocale defaultPrivacyLocale(PrivacyLocale pricacyLocale) {
		
		return new PrivacyLocale(pricacyLocale.getPrivacyPolicyUrl(), 
				pricacyLocale.getTermsOfUseUrl());
	}


	
	
}
