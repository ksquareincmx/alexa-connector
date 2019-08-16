/**
 * (c) 2003-2017 MuleSoft, Inc. The software in this package is published under the terms of the Commercial Free Software license V.1 a copy of which has been included with this distribution in the LICENSE.md file.
 */

package org.mule.modules.alexa.api.domain.update;

public class EndpointInfo {

	public EndpointInfo() {

	}

	public EndpointInfo(String uri) {
		this.uri = uri;
		//this.sslCertificateType = "Trusted";
	}

	public EndpointInfo(String uri, String sslCertificateType) {

		this.uri = uri;
		//this.sslCertificateType = sslCertificateType;
	}

	private String uri;
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

}