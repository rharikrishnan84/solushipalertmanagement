package com.meritconinc.mmr.model.common;

import java.io.Serializable;
import java.util.List;

public class RoleTagVO implements Serializable {
	private static final long serialVersionUID 		= 6272007;

	private String role;
	
	/**
	 * 
	 */
	public RoleTagVO() {
		super();
	}

	/**
	 * @return the role
	 */
	public String getRole() {
		return role;
	} 

	/**
	 * @param role the role to set
	 */
	public void setRole(String role) {
		this.role = role;
	}


}
