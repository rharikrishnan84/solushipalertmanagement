/**
 * 
 */
package com.meritconinc.mmr.model.common;

import java.io.Serializable;
import java.util.Date;

/**
 * @author brinzf2
 *
 */
public class LoginStatusVO implements Serializable{
	private static final long serialVersionUID 		= 9172006;

	private int id;
	private String loginStatus;
	private String userName;
	private String password;
	private String ipAddress;
	private String hostName;
    private Date createDate;
    
	/**
	 * @return the createDate
	 */
	public Date getCreateDate() {
		return createDate;
	} 
	/**
	 * @param createDate the createDate to set
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	/**
	 * @return the hostName
	 */
	public String getHostName() {
		return hostName;
	}
	/**
	 * @param hostName the hostName to set
	 */
	public void setHostName(String hostName) {
		this.hostName = hostName;
	}
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the ipAddress
	 */
	public String getIpAddress() {
		return ipAddress;
	}
	/**
	 * @param ipAddress the ipAddress to set
	 */
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	/**
	 * @return the loginStatus
	 */
	public String getLoginStatus() {
		return loginStatus;
	}
	/**
	 * @param loginStatus the loginStatus to set
	 */
	public void setLoginStatus(String loginStatus) {
		this.loginStatus = loginStatus;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

}
