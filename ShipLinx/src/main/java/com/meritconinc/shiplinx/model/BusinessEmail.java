package com.meritconinc.shiplinx.model;

/**
 * Model class for Business 
 * Email Contents 
 * @author selva
 *
 */

public class BusinessEmail {

	private long businessEmailId;
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ (int) (businessEmailId ^ (businessEmailId >>> 32));
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof BusinessEmail)) {
			return false;
		}
		BusinessEmail other = (BusinessEmail) obj;
		if (businessEmailId != other.businessEmailId) {
			return false;
		}
		return true;
	}
	
	
	
	private String emailType;
	private String locale;
	private String htmlContent;
	private String msgId;
	private long businessId;
	public long getBusinessEmailId() {
		return businessEmailId;
	}
	public void setBusinessEmailId(long businessEmailId) {
		this.businessEmailId = businessEmailId;
	}
 
	public String getLocale() {
		return locale;
	}
	public void setLocale(String locale) {
		this.locale = locale;
	}
	public String getHtmlContent() {
		return htmlContent;
	}
	public void setHtmlContent(String htmlContent) {
		this.htmlContent = htmlContent;
	}
	public String getMsgId() {
		return msgId;
	}
	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}
	public long getBusinessId() {
		return businessId;
	}
	public void setBusinessId(long businessId) {
		this.businessId = businessId;
	}
	public String getEmailType() {
		return emailType;
	}
	public void setEmailType(String emailType) {
		this.emailType = emailType;
	}
	
}
