/**
 * (c) 2003-2017 MuleSoft, Inc. The software in this package is published under the terms of the Commercial Free Software license V.1 a copy of which has been included with this distribution in the LICENSE.md file.
 */

package org.mule.modules.alexa.api.domain.existing;




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
