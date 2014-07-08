package com.meritconinc.shiplinx.model;

import java.io.Serializable;

public class WarehouseLocation implements Serializable, Cloneable 
{
	private long locationId;
	private long warehouseId;
	private String aisle;
	private String row;
	private String level;	
	private String section;	
	private String locationName;	
	private long locationTypeId;
	
	private Warehouse warehouse;
	private WarehouseLocationType warehouseLocationType;
	
	//For Hidden value in JSP
	private long warehouseHiddenId;
	
	public long getLocationId() {
		return locationId;
	} 
	public void setLocationId(long locationId) {
		this.locationId = locationId;
	}
	public long getWarehouseId() {
		return warehouseId;
	}
	public void setWarehouseId(long warehouseId) {
		this.warehouseId = warehouseId;
	}
	public String getAisle() {
		return aisle;
	}
	public void setAisle(String aisle) {
		this.aisle = aisle;
	}
	public String getRow() {
		return row;
	}
	public void setRow(String row) {
		this.row = row;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getSection() {
		return section;
	}
	public void setSection(String section) {
		this.section = section;
	}
	public String getLocationName() {
		return locationName;
	}
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	public long getLocationTypeId() {
		return locationTypeId;
	}
	public void setLocationTypeId(long locationTypeId) {
		this.locationTypeId = locationTypeId;
	}
	public Warehouse getWarehouse() {
		return warehouse;
	}
	public void setWarehouse(Warehouse warehouse) {
		this.warehouse = warehouse;
	}
	public WarehouseLocationType getWarehouseLocationType() {
		return warehouseLocationType;
	}
	public void setWarehouseLocationType(WarehouseLocationType warehouseLocationType) {
		this.warehouseLocationType = warehouseLocationType;
	}
	public long getWarehouseHiddenId() {
		return warehouseHiddenId;
	}
	public void setWarehouseHiddenId(long warehouseHiddenId) {
		this.warehouseHiddenId = warehouseHiddenId;
	}	

}
