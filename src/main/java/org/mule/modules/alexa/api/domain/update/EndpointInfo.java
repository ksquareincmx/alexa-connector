package org.mule.modules.alexa.api.domain.update;

public class EndpointInfo {

	public EndpointInfo() {

	}

	public EndpointInfo(String uri) {
		this.uri = uri;
		this.sslCertificateType = "Trusted";
	}

	public EndpointInfo(String uri, String sslCertificateType) {

		this.uri = uri;
		this.sslCertificateType = sslCertificateType;
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