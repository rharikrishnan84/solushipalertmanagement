package com.meritconinc.shiplinx.service;

import java.util.List;

import com.meritconinc.mmr.exception.QtyGreaterThanFromLocationQtyException;
import com.meritconinc.mmr.exception.SelectedFromLocBatchInvalidException;
import com.meritconinc.mmr.exception.updateProductInventoryException;
import com.meritconinc.shiplinx.model.LoggedEvent;
import com.meritconinc.shiplinx.model.Warehouse;
import com.meritconinc.shiplinx.model.WarehouseInventory;
import com.meritconinc.shiplinx.model.WarehouseLocation;
import com.meritconinc.shiplinx.model.WarehouseLocationType;
import com.meritconinc.shiplinx.model.WarehouseSummary;

public interface WarehouseManager {
	
	public void addNewWarehouse(Warehouse warehouse,boolean bool);
	
	public List<Warehouse> findWarehouseByBusinessId(long businessId);
	
	public List<Warehouse> searchWarehouseById(long warehouseId);
	
	public void deleteWarehouse(long warehouseId);
	
	public List<WarehouseLocation> getWarehouseLocationAisleByWarehouseId(long warehouseId);
	
	public List<WarehouseLocation> getWarehouseLocationRowByWarehouseId(WarehouseLocation warehouseLocation);
	
	public List<WarehouseLocation> getWarehouseLocationInfo(WarehouseLocation warehouseLocation);
	
	public List<WarehouseLocation> getWarehouseLocationInfoByLocationId(WarehouseLocation warehouseLocation);
	
	public List<WarehouseLocation> getAllLocationsForWarehouse(WarehouseLocation warehouseLocation);
		
	public List<WarehouseLocationType> getWarehouseLocationTypeInfo(); 
	
	public void addOrUpdateWarehouseLocation(WarehouseLocation warehouseLocation,boolean add);
	
	public void deleteWarehouseLocation(WarehouseLocation warehouseLocation);
	
	//Warehouse Inventory
	
	public List<WarehouseInventory> getWarehouseInventoryByProdId(WarehouseInventory warehouseInventory);
	
	public List<WarehouseInventory> getWarehouseInventoryByProdIdAndLocId(WarehouseInventory warehouseInventory);
	
	public List<WarehouseInventory> getAllBatches();
	
	public List<WarehouseInventory> getDistinctBatchesByLocId(WarehouseInventory warehouseInventory);
	
	public List<WarehouseLocation> getAllLocations(WarehouseLocation warehouseLocation);
	
	public List<WarehouseLocation> getAllLocationsProdSelected(WarehouseLocation warehouseLocation);
			
	public void addProductToInventory(WarehouseInventory warehouseInventory);
	
	public int updateProductToInventory(WarehouseInventory warehouseInventory);
	
	public void moveProductInInventory(WarehouseInventory WIfrom, WarehouseInventory WIto, LoggedEvent loggedEvent) throws QtyGreaterThanFromLocationQtyException, SelectedFromLocBatchInvalidException, updateProductInventoryException;
	
	public List<WarehouseSummary> generateSummaryByWarehouse(WarehouseInventory warehouseInventory);
	
	public List<WarehouseLocation> getAllWIPLocationsForWarehouse(long warehouseId);

}
