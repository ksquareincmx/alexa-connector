/**
 * (c) 2003-2019 MuleSoft, Inc. The software in this package is published under the terms of the Commercial Free Software license V.1 a copy of which has been included with this distribution in the LICENSE.md file.
 */

package org.mule.modules.alexa.api.domain.update;

import org.mule.runtime.api.meta.ExpressionSupport;
import org.mule.runtime.extension.api.annotation.Expression;
import org.mule.runtime.extension.api.annotation.param.Optional;
import org.mule.runtime.extension.api.annotation.param.Parameter;

public class EndpointInfo {

	public EndpointInfo() {

	}


	public EndpointInfo(String uri, String sslCertificateType) {

		this.uri = uri;
	    this.sslCertificateType = sslCertificateType;
	}
	@Parameter
	@Optional
	@Expression(ExpressionSupport.SUPPORTED)
	private String uri;
	
	@Parameter
	@Optional
	@Expression(ExpressionSupport.SUPPORTED)
	private String sslCertificateType ;

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getSslCertificateType() {
		return sslCertificateType;
	}

	public void setSslCertificateType(String sslCertificateType) {
		this.sslCertificateType = sslCertificateType;
	}
	public static EndpointInfo defaultEndPointInfo(EndpointInfo endInfo) {

		return new EndpointInfo(endInfo.getUri(), endInfo.getSslCertificateType());
	}
	
}