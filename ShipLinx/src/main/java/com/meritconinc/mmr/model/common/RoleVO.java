package com.meritconinc.mmr.model.common;

import java.io.Serializable;

/**
 * @author brinzf2
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class RoleVO implements Serializable {
	private static final long serialVersionUID 		= 6272007;

	public static final String ROLE_BUSINESSADMIN = "busadmin";
	private String role;
	private String description;
	private String[] actionIds;
						
						private String[] menuIds;
	/**
	 * 
	 */
	public RoleVO() {
		super();
		// TODO Auto-generated constructor stub
	} 

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
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

	public String[] getActionIds() {
												return actionIds;
											}
										
											public void setActionIds(String[] actionIds) {
												this.actionIds = actionIds;
											}
										
											public String[] getMenuIds() {
												return menuIds;
											}
										
											public void setMenuIds(String[] menuIds) {
												this.menuIds = menuIds;
						 				}
}
