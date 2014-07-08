package com.meritconinc.shiplinx.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.meritconinc.shiplinx.model.Warehouse;
import com.meritconinc.shiplinx.model.WarehouseInventory;
import com.meritconinc.shiplinx.model.WarehouseLocation;
import com.meritconinc.shiplinx.model.WarehouseLocationType;

public class WarehouseManagerDAOImpl extends SqlMapClientDaoSupport implements WarehouseManagerDAO
{
	public void addNewWarehouse(Warehouse warehouse, boolean bool)
	{
		if(bool) //new Warehouse
		{
			getSqlMapClientTemplate().insert("createWarehouse", warehouse);
		}
		else //edit existing
		{
			getSqlMapClientTemplate().update("updateWarehouseById", warehouse);
		}
		
	} 
	
	public List<Warehouse> findWarehouseByBusinessId(long businessId)
	{
		List<Warehouse> wList = new ArrayList<Warehouse>();
		Map<String, Object> paramObj = new HashMap<String, Object>(1);
		try {
			paramObj.put("businessId", businessId);
			
			wList = (List)getSqlMapClientTemplate().queryForList("getWarehouseList", paramObj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return wList;
	}
	
	public List<Warehouse> findWarehouseByWarehouseId(long warehouseId)
	{
		List<Warehouse> wList = new ArrayList<Warehouse>();
		Map<String, Object> paramObj = new HashMap<String, Object>(1);
		try {
			paramObj.put("warehouseId", warehouseId);
			
			wList = (List)getSqlMapClientTemplate().queryForList("getWarehouseById", paramObj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return wList;
	}
	
	public void deleteWarehouse(long warehouseId)
	{
		Map<String, Object> paramObj = new HashMap<String, Object>(1);
		try {
			paramObj.put("warehouseId", warehouseId);
			getSqlMapClientTemplate().delete("deleteWarehouse", paramObj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<WarehouseLocation> getWarehouseLocationAisleByWarehouseId(long warehouseId)
	{
		List<WarehouseLocation> wlList = new ArrayList<WarehouseLocation>();
		Map<String, Object> paramObj = new HashMap<String, Object>(1);
		try {
			paramObj.put("warehouseId", warehouseId);
			wlList = (List)getSqlMapClientTemplate().queryForList("getWarehouseLocationAisleByWarehouseId", paramObj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return wlList;
	}
	
	public List<WarehouseLocation> getWarehouseLocationRowByWarehouseId(WarehouseLocation warehouseLocation)
	{
		List<WarehouseLocation> wlList = new ArrayList<WarehouseLocation>();
		try {
			wlList= (List)getSqlMapClientTemplate().queryForList("getWarehouseLocationRowByWarehouseId", warehouseLocation);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return wlList;
	}
	
	public List<WarehouseLocation> getWarehouseLocationInfo(WarehouseLocation warehouseLocation)
	{
		List<WarehouseLocation> wlList = new ArrayList<WarehouseLocation>();
		try {
			wlList= (List)getSqlMapClientTemplate().queryForList("getWarehouseLocationInfo", warehouseLocation);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return wlList;
	}
	
	public List<WarehouseLocationType> getWarehouseLocationTypeInfo()
	{
		List<WarehouseLocationType> whltList = new ArrayList<WarehouseLocationType>();
		try {
			whltList= (List)getSqlMapClientTemplate().queryForList("getWarehouseLocationTypeInfo");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return whltList;
	}
	
	public void addOrUpdateWarehouseLocation(WarehouseLocation warehouseLocation,boolean add)
	{
		if(add)
			getSqlMapClientTemplate().update("createWarehouseLocation", warehouseLocation);
		else
			getSqlMapClientTemplate().insert("updateWarehouseLocation", warehouseLocation);
	}
	
	public List<WarehouseLocation> getWarehouseLocationInfoByLocationId(WarehouseLocation warehouseLocation)
	{
		List<WarehouseLocation> wlList = new ArrayList<WarehouseLocation>();
		try {
			wlList= (List)getSqlMapClientTemplate().queryForList("getWarehouseLocationInfoByLocationId", warehouseLocation);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return wlList;
	}
	
	public List<WarehouseLocation> getAllLocations(WarehouseLocation warehouseLocation)
	{
		List<WarehouseLocation> wlList = new ArrayList<WarehouseLocation>();
		try {
			wlList= (List)getSqlMapClientTemplate().queryForList("getAllLocations", warehouseLocation);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return wlList;
	}
	
	public void deleteWarehouseLocation(WarehouseLocation warehouseLocation)
	{
		getSqlMapClientTemplate().delete("deleteWarehouseLocation", warehouseLocation);
	}
	
	public List<WarehouseInventory> getWarehouseInventoryByProdId(WarehouseInventory warehouseInventory)
	{
		List<WarehouseInventory> whList = new ArrayList<WarehouseInventory>();
		try 
		{
			whList = (List)getSqlMapClientTemplate().queryForList("getWarehouseInventoryByProdId", warehouseInventory);
		} catch (Exception e) {
		e.printStackTrace();
		}
		return whList;
	}
	
	public List<WarehouseLocation> getAllLocationsProdSelected(WarehouseLocation warehouseLocation)
	{
		List<WarehouseLocation> wlList = new ArrayList<WarehouseLocation>();
		try {
			wlList= (List)getSqlMapClientTemplate().queryForList("getAllLocationsProdSelected", warehouseLocation);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return wlList;
	}
	
	public List<WarehouseLocation> getAllLocationsForWarehouse(WarehouseLocation warehouseLocation)
	{
		List<WarehouseLocation> wlList = new ArrayList<WarehouseLocation>();
		try {
			wlList= (List)getSqlMapClientTemplate().queryForList("getAllLocationsForWarehouse",warehouseLocation);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return wlList;
	}
	
	public List<WarehouseLocation> getAllWIPLocationsForWarehouse(long warehouseId)
	{
		List<WarehouseLocation> wlList = new ArrayList<WarehouseLocation>();
		Map<String, Object> paramObj = new HashMap<String, Object>(1);
		try 
		{
			paramObj.put("warehouseId", warehouseId);
			wlList= (List)getSqlMapClientTemplate().queryForList("getAllWIPLocationsForWarehouse",paramObj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return wlList;
		
	}
	
	public List<WarehouseInventory> getWarehouseInventoryByProdIdAndLocId(WarehouseInventory warehouseInventory)
	{
		List<WarehouseInventory> whList = new ArrayList<WarehouseInventory>();
		try 
		{
			whList = (List)getSqlMapClientTemplate().queryForList("getWarehouseInventoryByProdIdAndLocId", warehouseInventory);
		} catch (Exception e) {
		e.printStackTrace();
		}
		return whList;
	}
	
	public List<WarehouseInventory> getAllBatches()
	{
		List<WarehouseInventory> whList = new ArrayList<WarehouseInventory>();
		try 
		{
			whList = (List)getSqlMapClientTemplate().queryForList("getAllBatches");
		} catch (Exception e) {
		e.printStackTrace();
		}
		return whList;
	}
	
	public List<WarehouseInventory> getDistinctBatchesByLocId(WarehouseInventory warehouseInventory)
	{
		List<WarehouseInventory> whList = new ArrayList<WarehouseInventory>();
		try 
		{
			whList = (List)getSqlMapClientTemplate().queryForList("getBatchesByLocId",warehouseInventory);
		} catch (Exception e) {
		e.printStackTrace();
		}
		return whList;
	}
	
	public void addProductToInventory(WarehouseInventory warehouseInventory)
	{
		getSqlMapClientTemplate().insert("createWarehouseInventory", warehouseInventory);
	}
	
	public int updateProductToInventory(WarehouseInventory warehouseInventory)
	{
		int ireturn=0;
		ireturn = getSqlMapClientTemplate().update("updateWarehouseInventory", warehouseInventory);
		return ireturn;
	}
	
	public void deleteWarehouseInventory(WarehouseInventory warehouseInventory)
	{
		getSqlMapClientTemplate().delete("deleteWarehouseInventory", warehouseInventory);
	}
	
	public long getStockCountForSummaryByWarehouse(long warehouseId, long productId, long locationTypeId)
	{
		long sumRet = 0;
		List<Long> retList = new ArrayList<Long>();
		Map<String, Object> paramObj = new HashMap<String, Object>(3);
		try 
		{
			paramObj.put("warehouseId", warehouseId);
			paramObj.put("productId", productId);
			paramObj.put("locationTypeId", locationTypeId);
			retList = (List)getSqlMapClient().queryForList("generateSummary", paramObj);
			if(retList.size()>0)
			{
				if(retList.get(0)!=null)
					sumRet = retList.get(0);
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return sumRet;
	}
}
