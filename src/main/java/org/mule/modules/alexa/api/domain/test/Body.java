package org.mule.modules.alexa.api.domain.test;




public class Body {

	private String version;
	private Session session;
	private Context context;
	private Reqest request;
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public Session getSession() {
		return session;
	}
	public void setSession(Session session) {
		this.session = session;
	}
	public Context getContext() {
		return context;
	}
	public void setContext(Context context) {
		this.context = context;
	}
	public Reqest getRequest() {
		return request;
	}
	public void setRequest(Reqest request) {
		this.request = request;
	}
	public Body(String version, Session session, Context context, Reqest request) {
		super();
		this.version = version;
		this.session = session;
		this.context = context;
		this.request = request;
	}
	@Override
	public String toString() {
		return "Body [version=" + version + ", session=" + session + ", context=" + context + ", request=" + request
				+ "]";
	}
	
	
	

	
	
}
