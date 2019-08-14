package org.mule.modules.alexa.api.domain.test;

public class Reqest {

	private String type;
	private String requestId;
	private String timestamp;
	private String locale;
	private Intent intent;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getRequestId() {
		return requestId;
	}
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public String getLocale() {
		return locale;
	}
	public void setLocale(String locale) {
		this.locale = locale;
	}
	public Intent getIntent() {
		return intent;
	}
	public void setIntent(Intent intent) {
		this.intent = intent;
	}
	public Reqest(String type, String requestId, String timestamp, String locale, Intent intent) {
		super();
		this.type = type;
		this.requestId = requestId;
		this.timestamp = timestamp;
		this.locale = locale;
		this.intent = intent;
	}
	@Override
	public String toString() {
		return "Reqest [type=" + type + ", requestId=" + requestId + ", timestamp=" + timestamp + ", locale=" + locale
				+ ", intent=" + intent + ", getType()=" + getType() + ", getRequestId()=" + getRequestId()
				+ ", getTimestamp()=" + getTimestamp() + ", getLocale()=" + getLocale() + ", getIntent()=" + getIntent()
				+ "]";
	}
	
	
}
