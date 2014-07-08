package com.meritconinc.shiplinx.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

public class WarehouseInventory implements Serializable, Cloneable 
{
	private long inventoryId;
	private long productId;
	private long locationId;
	private long quantity;
	private Timestamp dateCreated;
	private String batchNum;
	private long batchId;
	
	private WarehouseLocation warehouseLocation;
	
	private List<WarehouseLocation> mapWHWIPLocations;
	
	private Products products;
	 
	public long getInventoryId() {
		return inventoryId;
	}
	public void setInventoryId(long inventoryId) {
		this.inventoryId = inventoryId;
	}
	public long getProductId() {
		return productId;
	}
	public void setProductId(long productId) {
		this.productId = productId;
	}
	public long getLocationId() {
		return locationId;
	}
	public void setLocationId(long locationId) {
		this.locationId = locationId;
	}
	public long getQuantity() {
		return quantity;
	}
	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}
	public Timestamp getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(Timestamp dateCreated) {
		this.dateCreated = dateCreated;
	}
	public String getBatchNum() {
		return batchNum;
	}
	public void setBatchNum(String batchNum) {
		this.batchNum = batchNum;
	}
	public WarehouseLocation getWarehouseLocation() {
		return warehouseLocation;
	}
	public void setWarehouseLocation(WarehouseLocation warehouseLocation) {
		this.warehouseLocation = warehouseLocation;
	}
	public Products getProducts() {
		return products;
	}
	public void setProducts(Products products) {
		this.products = products;
	}
	public long getBatchId() {
		return batchId;
	}
	public void setBatchId(long batchId) {
		this.batchId = batchId;
	}
	public List<WarehouseLocation> getMapWHWIPLocations() {
		return mapWHWIPLocations;
	}
	public void setMapWHWIPLocations(List<WarehouseLocation> mapWHWIPLocations) {
		this.mapWHWIPLocations = mapWHWIPLocations;
	}
	

}
