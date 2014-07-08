package com.meritconinc.shiplinx.model;

import java.io.Serializable;

public class WarehouseSummary implements Serializable, Cloneable  
{
	private String warehouseName;
	private long inventoryItemCount;
	private long wipItemCount;
	private long quarantineItemCount;
	private long warehouseSubTotal;
	private long warehouseId;
	
	public String getWarehouseName() {
		return warehouseName;
	}
	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
	}
	public long getInventoryItemCount() {
		return inventoryItemCount;
	}
	public void setInventoryItemCount(long inventoryItemCount) {
		this.inventoryItemCount = inventoryItemCount;
	} 
	public long getWipItemCount() {
		return wipItemCount;
	}
	public void setWipItemCount(long wipItemCount) {
		this.wipItemCount = wipItemCount;
	}
	public long getQuarantineItemCount() {
		return quarantineItemCount;
	}
	public void setQuarantineItemCount(long quarantineItemCount) {
		this.quarantineItemCount = quarantineItemCount;
	}
	public long getWarehouseSubTotal() {
		return warehouseSubTotal;
	}
	public void setWarehouseSubTotal(long warehouseSubTotal) {
		this.warehouseSubTotal = warehouseSubTotal;
	}
	public long getWarehouseId() {
		return warehouseId;
	}
	public void setWarehouseId(long warehouseId) {
		this.warehouseId = warehouseId;
	}

}
