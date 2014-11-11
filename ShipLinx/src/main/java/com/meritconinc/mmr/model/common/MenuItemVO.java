package com.meritconinc.mmr.model.common;

import java.io.Serializable;
import java.util.List;

import com.meritconinc.shiplinx.action.MenuAction;

public class MenuItemVO  implements Serializable{
	private static final long serialVersionUID 		= 3022007;

	public static final String TOP_LEVEL = "TOP";
	public static final String LEVEL_ONE = "LEVEL_1";
	public static final String LEVEL_TWO = "LEVEL_2";
	
	private int id;
	private String name;
	private String url;
	private String level;
	private int parentId;
	private boolean selected;
	private List subMenuItems=null;
	private String image;
	private String imageOver;
	
	private String helptag;
	private String supporttag;
	private String msgContent;
	public String getMsgContent() {
		return msgContent;
	}
	public void setMsgContent(String msgContent) {
		this.msgContent = msgContent;
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
	 * @return the level
	 */
	public String getLevel() {
		return level;
	}
	/**
	 * @param level the level to set
	 */
	public void setLevel(String level) {
		this.level = level;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the parentId
	 */
	public int getParentId() {
		return parentId;
	}
	/**
	 * @param parentId the parentId to set
	 */
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	/**
	 * @return the selected
	 */
	public boolean isSelected() {
		return selected;
	}
	/**
	 * @param selected the selected to set
	 */
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	/**
	 * @return the subMenuItems
	 */
	public List getSubMenuItems() {
		return subMenuItems;
	}
	/**
	 * @param subMenuItems the subMenuItems to set
	 */
	public void setSubMenuItems(List subMenuItems) {
		this.subMenuItems = subMenuItems;
	}
	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getImageOver() {
		return imageOver;
	}
	public void setImageOver(String imageOver) {
		this.imageOver = imageOver;
	}
	public String getHelptag() {
		return helptag;
	}
	public void setHelptag(String helptag) {
		this.helptag = helptag;
	}
	public String getSupporttag() {
		return supporttag;
	}
	public void setSupporttag(String supporttag) {
		this.supporttag = supporttag;
	}
	
	
}
