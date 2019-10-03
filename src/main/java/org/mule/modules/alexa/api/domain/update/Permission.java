/**
 * (c) 2003-2017 MuleSoft, Inc. The software in this package is published under the terms of the Commercial Free Software license V.1 a copy of which has been included with this distribution in the LICENSE.md file.
 */
package org.mule.modules.alexa.api.domain.update;

import org.mule.runtime.extension.api.annotation.Alias;
import org.mule.runtime.extension.api.annotation.param.Parameter;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Permission {

	
	
	public Permission() {
		super();
	}

	public Permission(String pname) {
		this.pname = pname;
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

	public static Permission defaultPermision(Permission per) {
		return new Permission(per.getPname());
	}
	
	
}
