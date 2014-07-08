package com.meritconinc.shiplinx.model;

import java.io.Serializable;
import java.util.Date;

public class LoggedEvent implements Serializable, Cloneable  
{
	private long id;
	private long entityType;
	private long entityId;
	private String eventUsername;
	private Date eventDateTime;
	private String message;
	private boolean privateMessage;
	private boolean deletedMessage;
	private String systemLog;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getEntityType() {
		return entityType;
	} 
	public void setEntityType(long entityType) {
		this.entityType = entityType;
	}
	public long getEntityId() {
		return entityId;
	}
	public void setEntityId(long entityId) {
		this.entityId = entityId;
	}
	public String getEventUsername() {
		return eventUsername;
	}
	public void setEventUsername(String eventUsername) {
		this.eventUsername = eventUsername;
	}
	public Date getEventDateTime() {
		return eventDateTime;
	}
	public void setEventDateTime(Date eventDateTime) {
		this.eventDateTime = eventDateTime;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public boolean isPrivateMessage() {
		return privateMessage;
	}
	public void setPrivateMessage(boolean privateMessage) {
		this.privateMessage = privateMessage;
	}
	public boolean isDeletedMessage() {
		return deletedMessage;
	}
	public void setDeletedMessage(boolean deletedMessage) {
		this.deletedMessage = deletedMessage;
	}
	public String getSystemLog() {
		return systemLog;
	}
	public void setSystemLog(String systemLog) {
		this.systemLog = systemLog;
	}
	
	
	
}
