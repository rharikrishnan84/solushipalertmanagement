package com.meritconinc.shiplinx.model;

import java.io.Serializable;

public class WarehouseLocationType implements Serializable, Cloneable 
{
	private long warehouseLocationTypeId;
	private String warehouseLocationTypeName;
	
	
	public long getWarehouseLocationTypeId() {
		return warehouseLocationTypeId;
	}
	public void setWarehouseLocationTypeId(long warehouseLocationTypeId) {
		this.warehouseLocationTypeId = warehouseLocationTypeId;
	}
	public String getWarehouseLocationTypeName() {
		return warehouseLocationTypeName;
	}
	public void setWarehouseLocationTypeName(String warehouseLocationTypeName) {
		this.warehouseLocationTypeName = warehouseLocationTypeName;
	} 
	
	

}
