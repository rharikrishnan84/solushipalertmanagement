package com.meritconinc.mmr.model.common;

import java.io.Serializable;

/**
 * RequestTrackParamVO class is a VO to represents 
 *  request parameter name and value
 * @author Sapient Corporation
 */
public class RequestTrackParamVO implements Serializable{
		
	/**
	 * request track id
	 */
	private Integer id;
		
	/**
	 * navigation log id
	 */
	private Integer navigationLogId;
	 	
	/**
	 * request parameter name
	 */
	private String paramName;
	
	/**
	 * request parameter value
	 */
	private String paramValue;
	
	
	/**
	 * accessor for id
	 * @return id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * mutator for id
	 * @param id
	 */
	public void setId(Integer id) {
		this.id = id;
	}	
	/**
	 * accessor for paramName
	 * @return
	 */
	public String getParamName() {
		return paramName;
	}
	/**
	 * mutator for paramName
	 * @param paramName
	 */
	public void setParamName(String paramName) {
		this.paramName = paramName;
	}
	/**
	 * accessor for paramValue
	 * @return
	 */
	public String getParamValue() {
		return paramValue;
	}
	/**
	 * mutator for paramValue
	 * @param paramValue
	 */
	public void setParamValue(String paramValue) {
		this.paramValue = paramValue;
	}
	/**
	 * accessor for navigationLogId
	 * @return
	 */
	public Integer getNavigationLogId() {
		return navigationLogId;
	}
	/**
	 * mutator for navigationLogId
	 * @param navigationLogId
	 */
	public void setNavigationLogId(Integer navigationLogId) {
		this.navigationLogId = navigationLogId;
	}
	
	public String toString(){
		StringBuilder l_stringBuilder = new StringBuilder();
		l_stringBuilder.append("\n<< "+this.getClass()+" >> {");
		l_stringBuilder.append("\nid: "+ this.getId());
		l_stringBuilder.append("\nnavigationLogId: "+ this.getNavigationLogId());
		l_stringBuilder.append("\nparamName: "+ this.getParamName());
		l_stringBuilder.append("\nparamValue: "+ this.getParamValue());
		l_stringBuilder.append(" } ");
		return l_stringBuilder.toString();
	}
}
