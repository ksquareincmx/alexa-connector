package org.mule.modules.alexa.api.domain.update;

import org.mule.runtime.extension.api.annotation.Alias;
import org.mule.runtime.extension.api.annotation.param.Parameter;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Permission {

	
	
	public Permission() {
		super();
	}

	@Parameter
	@Alias("PermissionName")
	private String pname;

	@JsonProperty("name")
	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	
	
	
}
