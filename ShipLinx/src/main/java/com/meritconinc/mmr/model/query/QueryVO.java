package com.meritconinc.mmr.model.query;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class QueryVO implements Serializable{
	private static final long serialVersionUID = 3850627846229018386L;
	private int id = -1;
	private String datasource;
	private String name;
	private String sql;
	private Date creationDate;
	private String createdBy;
	private Date lastUpdated;
	private String updatedBy;
	private List<QueryParameterVO> parameters = new ArrayList<QueryParameterVO>();	
	private int jdbcFetchSize;
	private int maxRows;
	
	public int getId() {
		return id;
	} 
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSql() {
		return sql;
	}
	public void setSql(String sql) {
		this.sql = sql;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public List<QueryParameterVO> getParameters() {
		return parameters;
	}
	public void setParameters(List<QueryParameterVO> parameters) {		
		this.parameters = parameters;
	}
	public Date getLastUpdated() {
		return lastUpdated;
	}
	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public String getDatasource() {
		return datasource;
	}
	public void setDatasource(String datasource) {
		this.datasource = datasource;
	}
	public int getJdbcFetchSize() {
		return jdbcFetchSize;
	}
	public void setJdbcFetchSize(int jdbcFetchSize) {
		this.jdbcFetchSize = jdbcFetchSize;
	}
	public int getMaxRows() {
		return maxRows;
	}
	public void setMaxRows(int maxRows) {
		this.maxRows = maxRows;
	}
	
	
}
