package com.meritconinc.shiplinx.model;

import java.io.Serializable;

public class Warehouse implements Serializable, Cloneable 
{
	
	private long warehouseId;
	private long businessId;
	private String warehouseCode;
	private String warehouseName;	
	private long addressId;
	
	private Address address;
	
	public Warehouse(){
		address = new Address();
	} 
	
	public long getWarehouseId() {
		return warehouseId;
	}
	public void setWarehouseId(long warehouseId) {
		this.warehouseId = warehouseId;
	}
	
	public long getBusinessId() {
		return businessId;
	}
	public void setBusinessId(long businessId) {
		this.businessId = businessId;
	}
	public String getWarehouseCode() {
		return warehouseCode;
	}
	public void setWarehouseCode(String warehouseCode) {
		this.warehouseCode = warehouseCode;
	}
	public String getWarehouseName() {
		return warehouseName;
	}
	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
	}
	public long getAddressId() {
		return addressId;
	}
	public void setAddressId(long addressId) {
		this.addressId = addressId;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}

}
