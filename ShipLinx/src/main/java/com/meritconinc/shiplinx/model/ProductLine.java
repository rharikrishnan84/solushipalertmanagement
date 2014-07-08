package com.meritconinc.shiplinx.model;

import java.io.Serializable;

public class ProductLine implements Serializable, Cloneable
{
	static final long serialVersionUID = 20062012;
	
	private long productLineId;
	private String lineCode;
	private String lineName;
	private String lineDescription;
	private long customerId;
	
	public String getProductlineName()
	{
		return lineName;
	}
	
	public long getProductLineId() {
		return productLineId;
	}
	public void setProductLineId(long productLineId) {
		this.productLineId = productLineId;
	}
	public String getLineCode() {
		return lineCode;
	} 
	public void setLineCode(String lineCode) {
		this.lineCode = lineCode;
	}
	public String getLineName() {
		return lineName;
	}
	public void setLineName(String lineName) {
		this.lineName = lineName;
	}
	public String getLineDescription() {
		return lineDescription;
	}
	public void setLineDescription(String lineDescription) {
		this.lineDescription = lineDescription;
	}
	public long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}
	
	

}
