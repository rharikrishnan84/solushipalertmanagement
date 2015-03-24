package com.meritconinc.mmr.model.common;

import java.io.Serializable;

public class MenuScreenVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5602535434347047232L;
	
	private MenuItemVO menuItemVO;
	
	private int[] business;
	
	private String menuName;
	
	private String menuUrl;
	
	private String menulevel;
	
	private String topLevel;
	
	private String firstLevel;
	
	private String displayOrder;
	
	private String msgKey;
	
	private String msgContent;
	
	private String locale;
	
	private String[] menuRole;
	
	private String parentId;

	public MenuItemVO getMenuItemVO() {
		return menuItemVO;
	}

	public void setMenuItemVO(MenuItemVO menuItemVO) {
		this.menuItemVO = menuItemVO;
	}


	
	public int[] getBusiness() {
		return business;
	}

	public void setBusiness(int[] business) {
		this.business = business;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getMenuUrl() {
		return menuUrl;
	}

	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}

	public String getMenulevel() {
		return menulevel;
	}

	public void setMenulevel(String menulevel) {
		this.menulevel = menulevel;
	}

	public String getTopLevel() {
		return topLevel;
	}

	public void setTopLevel(String topLevel) {
		this.topLevel = topLevel;
	}

	public String getFirstLevel() {
		return firstLevel;
	}

	public void setFirstLevel(String firstLevel) {
		this.firstLevel = firstLevel;
	}

	public String getDisplayOrder() {
		return displayOrder;
	}

	public void setDisplayOrder(String displayOrder) {
		this.displayOrder = displayOrder;
	}

	public String getMsgKey() {
		return msgKey;
	}

	public void setMsgKey(String msgKey) {
		this.msgKey = msgKey;
	}

	public String getMsgContent() {
		return msgContent;
	}

	public void setMsgContent(String msgContent) {
		this.msgContent = msgContent;
	}

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}


	public String[] getMenuRole() {
		return menuRole;
	}

	public void setMenuRole(String[] menuRole) {
		this.menuRole = menuRole;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	
	

}
