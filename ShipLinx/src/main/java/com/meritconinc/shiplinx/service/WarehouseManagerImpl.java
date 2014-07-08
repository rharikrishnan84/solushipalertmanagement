package com.meritconinc.shiplinx.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.meritconinc.mmr.exception.QtyGreaterThanFromLocationQtyException;
import com.meritconinc.mmr.exception.SelectedFromLocBatchInvalidException;
import com.meritconinc.mmr.exception.updateProductInventoryException;
import com.meritconinc.mmr.utilities.MessageUtil;
import com.meritconinc.mmr.utilities.security.UserUtil;
import com.meritconinc.shiplinx.dao.AddressDAO;
import com.meritconinc.shiplinx.dao.LoggedEventDAO;
import com.meritconinc.shiplinx.dao.ProductManagerDAO;
import com.meritconinc.shiplinx.dao.WarehouseManagerDAO;
import com.meritconinc.shiplinx.model.LoggedEvent;
import com.meritconinc.shiplinx.model.Warehouse;
import com.meritconinc.shiplinx.model.WarehouseInventory;
import com.meritconinc.shiplinx.model.WarehouseLocation;
import com.meritconinc.shiplinx.model.WarehouseLocationType;
import com.meritconinc.shiplinx.model.WarehouseSummary;
import com.meritconinc.shiplinx.utils.ShiplinxConstants;

public class WarehouseManagerImpl implements WarehouseManager
{
	private WarehouseManagerDAO warehouseManagerDAO;
	private AddressDAO addressDAO;
	
	private LoggedEventDAO loggedEventDAO;
	private ProductManagerDAO productManagerDAO;
	
	List<WarehouseSummary> warehouseSummaryList = new ArrayList<WarehouseSummary>();
	public WarehouseManagerDAO getWarehouseManagerDAO() {
		return warehouseManagerDAO;
	}

	public void setWarehouseManagerDAO(WarehouseManagerDAO warehouseManagerDAO) {
		this.warehouseManagerDAO = warehouseManagerDAO;
	}

	public void addNewWarehouse(Warehouse warehouse, boolean bool)
	{
		if (warehouse.getAddress() != null) 
		{
				warehouse.getAddress().setDefaultFromAddress(true);
				warehouse.getAddress().setAbbreviationName(warehouse.getWarehouseName());
				Long id = this.addressDAO.add(warehouse.getAddress());
				warehouse.setAddressId(id);
		} 
		
		warehouseManagerDAO.addNewWarehouse(warehouse, bool);
	}
	
	public List<Warehouse> findWarehouseByBusinessId(long businessId)
	{
		List<Warehouse> wList = new ArrayList<Warehouse>();	
		
		wList = warehouseManagerDAO.findWarehouseByBusinessId(businessId);
		
		return wList;
	}
	
	public List<Warehouse> searchWarehouseById(long warehouseId)
	{
		List<Warehouse> wlist = new ArrayList<Warehouse>();
		wlist = warehouseManagerDAO.findWarehouseByWarehouseId(warehouseId);
		
		return wlist;
	}
	
	public List<WarehouseLocation> getWarehouseLocationAisleByWarehouseId(long warehouseId)
	{
		List<WarehouseLocation> whlList = new ArrayList<WarehouseLocation>();
		whlList= warehouseManagerDAO.getWarehouseLocationAisleByWarehouseId(warehouseId);
		return whlList;
	}
	
	public List<WarehouseLocation> getWarehouseLocationRowByWarehouseId(WarehouseLocation warehouseLocation)
	{
		List<WarehouseLocation> whlList = new ArrayList<WarehouseLocation>();
		whlList= warehouseManagerDAO.getWarehouseLocationRowByWarehouseId(warehouseLocation);
		return whlList;
	}
	
	public List<WarehouseLocation> getWarehouseLocationInfo(WarehouseLocation warehouseLocation)
	{
		List<WarehouseLocation> whlList = new ArrayList<WarehouseLocation>();
		whlList = warehouseManagerDAO.getWarehouseLocationInfo(warehouseLocation);
		return whlList;
	}
	
	public List<WarehouseLocationType> getWarehouseLocationTypeInfo()
	{
		List<WarehouseLocationType> whltList = new ArrayList<WarehouseLocationType>();
		whltList = warehouseManagerDAO.getWarehouseLocationTypeInfo();
			return whltList; 
	}
	
	public List<WarehouseLocation> getWarehouseLocationInfoByLocationId(WarehouseLocation warehouseLocation)
	{
		List<WarehouseLocation> whlList = new ArrayList<WarehouseLocation>();
		whlList = warehouseManagerDAO.getWarehouseLocationInfoByLocationId(warehouseLocation);
		return whlList;
	}
	
	public void addOrUpdateWarehouseLocation(WarehouseLocation warehouseLocation,boolean add)
	{
		warehouseManagerDAO.addOrUpdateWarehouseLocation(warehouseLocation, add);
	}
	
	public void deleteWarehouseLocation(WarehouseLocation warehouseLocation)
	{
		warehouseManagerDAO.deleteWarehouseLocation(warehouseLocation);
	}
	
	public void deleteWarehouse(long warehouseId)
	{
		warehouseManagerDAO.deleteWarehouse(warehouseId);
	}

	//Warehouse Inventory
	public List<WarehouseInventory> getWarehouseInventoryByProdId(WarehouseInventory warehouseInventory)
	{
		List<WarehouseInventory> whiList = new ArrayList<WarehouseInventory>();
		try 
		{
			whiList = warehouseManagerDAO.getWarehouseInventoryByProdId(warehouseInventory);
		} catch (Exception e) {
		e.printStackTrace();
		}
		return whiList;
	}
	
	public List<WarehouseInventory> getWarehouseInventoryByProdIdAndLocId(WarehouseInventory warehouseInventory)
	{
		List<WarehouseInventory> whiList = new ArrayList<WarehouseInventory>();
		try 
		{
			whiList = warehouseManagerDAO.getWarehouseInventoryByProdIdAndLocId(warehouseInventory);
		} catch (Exception e) {
		e.printStackTrace();
		}
		return whiList;
		
	}
	
	public List<WarehouseLocation> getAllLocations(WarehouseLocation warehouseLocation)
	{
		List<WarehouseLocation> whlList = new ArrayList<WarehouseLocation>();
		try 
		{
			whlList = warehouseManagerDAO.getAllLocations(warehouseLocation);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return whlList;
	}
	
	public List<WarehouseLocation> getAllLocationsProdSelected(WarehouseLocation warehouseLocation)
	{
		List<WarehouseLocation> whlList = new ArrayList<WarehouseLocation>();
		try 
		{
			whlList = warehouseManagerDAO.getAllLocationsProdSelected(warehouseLocation);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return whlList;
	}
	
	public List<WarehouseLocation> getAllLocationsForWarehouse(WarehouseLocation warehouseLocation)
	{
		List<WarehouseLocation> whlList = new ArrayList<WarehouseLocation>();
		try 
		{
			whlList = warehouseManagerDAO.getAllLocationsForWarehouse(warehouseLocation);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return whlList;
		
	}
	
	public List<WarehouseLocation> getAllWIPLocationsForWarehouse(long warehouseId)
	{
		List<WarehouseLocation> whlList = new ArrayList<WarehouseLocation>();
		try 
		{
			whlList = warehouseManagerDAO.getAllWIPLocationsForWarehouse(warehouseId);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return whlList;
		
	}
	
	public List<WarehouseInventory> getAllBatches()
	{
		List<WarehouseInventory> whiList = new ArrayList<WarehouseInventory>();
		try 
		{
			whiList = warehouseManagerDAO.getAllBatches();
		} catch (Exception e) {
		e.printStackTrace();
		}
		return whiList;
		
	}
	
	public List<WarehouseInventory> getDistinctBatchesByLocId(WarehouseInventory warehouseInventory)
	{
		List<WarehouseInventory> whiList = new ArrayList<WarehouseInventory>();
		try 
		{
			whiList = warehouseManagerDAO.getDistinctBatchesByLocId(warehouseInventory);
		} catch (Exception e) {
		e.printStackTrace();
		}
		return whiList;
	}
	
	public void moveProductInInventory(WarehouseInventory WIfrom, WarehouseInventory WIto, LoggedEvent loggedEvent) throws QtyGreaterThanFromLocationQtyException, SelectedFromLocBatchInvalidException, updateProductInventoryException
	{
		String locale = UserUtil.getMmrUser().getLocale();
		String systemLog=null;
		String strFromLocation = null;
		String strToLocation = null;
		String strToWarehouseName = null;
		long lToLocType = 0;
		long lFromLocType = 0;
		long lQty = WIfrom.getQuantity();
		List<WarehouseLocation> lstFromLocationList;
		List<WarehouseLocation> lstToLocationList;
		List<Warehouse> lstWarehouseList;
		List<WarehouseInventory> lstWarehouseInventoryList;
		List<WarehouseInventory> lstWarehouseIListFrom;
		List<WarehouseInventory> lstWarehouseIListTo;
		int iret = 0;
		
		//Check if the selected from-location and batch (if not null) is valid or exists
		WarehouseInventory witemp = new WarehouseInventory(); 
		witemp.setLocationId(WIfrom.getLocationId());
		witemp.setProductId(WIfrom.getProducts().getProductId());
		witemp.setBatchNum(WIfrom.getBatchNum());
		lstWarehouseInventoryList=warehouseManagerDAO.getWarehouseInventoryByProdIdAndLocId(witemp);
		//Fails to retrieve a match for the selected Product, from-Location and from-Batch Number.
		if(lstWarehouseInventoryList.size() == 0)
			throw new SelectedFromLocBatchInvalidException();
		//If the selected quantity is lesser than available quantity for the selected product, location and batch(if any or else checks for null), 
		//then throw error and display message. 
		else if (lstWarehouseInventoryList.get(0).getQuantity() - WIfrom.getQuantity() < 0)
			throw new QtyGreaterThanFromLocationQtyException();
		
		
		WarehouseLocation warehouseLocation = new WarehouseLocation();
			//From Location Information
			warehouseLocation.setLocationId(WIfrom.getLocationId());
			lstFromLocationList = warehouseManagerDAO.getWarehouseLocationInfoByLocationId(warehouseLocation);
			for(WarehouseLocation wl: lstFromLocationList)//Retrieves only ONE record
			{
				strFromLocation = wl.getLocationName();
				lFromLocType = wl.getLocationTypeId();
			}
			
			//To Location Information
			warehouseLocation.setLocationId(WIto.getLocationId());
			lstToLocationList = warehouseManagerDAO.getWarehouseLocationInfoByLocationId(warehouseLocation);
			for(WarehouseLocation wl: lstToLocationList)//Retrieves only ONE record
			{
				strToLocation = wl.getLocationName();
				lToLocType = wl.getLocationTypeId();
			}
			
			//To Warehouse Name
			lstWarehouseList=warehouseManagerDAO.findWarehouseByWarehouseId(WIto.getWarehouseLocation().getWarehouseId());
			for(Warehouse w: lstWarehouseList)//Retrieves only ONE record
			{
				strToWarehouseName = w.getWarehouseName();
			}
			
			//If the From and To Location types are different, then update the counts in Products table
			if(lFromLocType!=lToLocType)
			{
				
				if(lToLocType==1)//Inventory
					WIto.getProducts().setLocationCount(Integer.parseInt(String.valueOf(WIto.getProducts().getLocationCount())) + Integer.parseInt(String.valueOf(lQty)));
				else if(lToLocType==2)//WIP
					WIto.getProducts().setWipCount(Integer.parseInt(String.valueOf(WIto.getProducts().getWipCount())) + Integer.parseInt(String.valueOf(lQty)));
				else if(lToLocType==3)//Quarantine
					WIto.getProducts().setQuarantineCount(Integer.parseInt(String.valueOf(WIto.getProducts().getQuarantineCount())) + Integer.parseInt(String.valueOf(lQty)));
				
				
				//Check for the type and assign the Qty for the respective type count
				if(lFromLocType==1)//Inventory
				{
					//If the no of units user entered is lesser than the available, set the count, else throw exception
					if(Integer.parseInt(String.valueOf(WIfrom.getQuantity())) <= Integer.parseInt(String.valueOf(WIfrom.getProducts().getLocationCount())))
						WIfrom.getProducts().setLocationCount(Integer.parseInt(String.valueOf(WIfrom.getProducts().getLocationCount())) - Integer.parseInt(String.valueOf(lQty)));
					else
						throw new QtyGreaterThanFromLocationQtyException();
				}
				else if(lFromLocType==2)//WIP
				{
					//If the no of units user entered is lesser than the available, set the count, else throw exception
					if(Integer.parseInt(String.valueOf(WIfrom.getQuantity())) <= Integer.parseInt(String.valueOf(WIfrom.getProducts().getWipCount())))
						WIfrom.getProducts().setWipCount(Integer.parseInt(String.valueOf(WIfrom.getProducts().getWipCount())) - Integer.parseInt(String.valueOf(lQty)));
					else
						throw new QtyGreaterThanFromLocationQtyException();
				}
				else if(lFromLocType==3)//Quarantine
				{
					//If the no of units user entered is lesser than the available, set the count, else throw exception
					if(Integer.parseInt(String.valueOf(WIfrom.getQuantity())) <= Integer.parseInt(String.valueOf(WIfrom.getProducts().getQuarantineCount())))
						WIfrom.getProducts().setQuarantineCount(Integer.parseInt(String.valueOf(WIfrom.getProducts().getQuarantineCount())) - Integer.parseInt(String.valueOf(lQty)));
					else 
						throw new QtyGreaterThanFromLocationQtyException();
				}
				
				//Logic to update the counts in Product Table
				productManagerDAO.addOrUpdate(WIto.getProducts(), false);//Update to-location corresponding type count
				productManagerDAO.addOrUpdate(WIfrom.getProducts(), false); //Update from-location corresponding type count
			}
			
			//Implementation of Moving a product in Inventory
			WIfrom.setProductId(WIfrom.getProducts().getProductId());
			lstWarehouseIListFrom = warehouseManagerDAO.getWarehouseInventoryByProdIdAndLocId(WIfrom);
			WIfrom.setQuantity(lstWarehouseIListFrom.get(0).getQuantity() - WIfrom.getQuantity());
			if(WIfrom.getQuantity()>0)
				iret = warehouseManagerDAO.updateProductToInventory(WIfrom);
			//Delete WarehouseInventory if the Quantity becomes 0 after moving the item.
			else if(WIfrom.getQuantity() == 0)
				warehouseManagerDAO.deleteWarehouseInventory(WIfrom);
			
			
			
			WIto.setProductId(WIto.getProducts().getProductId());
			lstWarehouseIListTo = warehouseManagerDAO.getWarehouseInventoryByProdIdAndLocId(WIto);
			if(lstWarehouseIListTo.size() > 0)
			{
				WIto.setQuantity(lstWarehouseIListTo.get(0).getQuantity() + WIto.getQuantity());
				iret = warehouseManagerDAO.updateProductToInventory(WIto);
			}
			else //If the To: Batch, location and product selected does not exist, then create a new entry in Inventory. 
			{
				WIto.setQuantity(lQty);
				WIto.setDateCreated(new Timestamp(new Date().getTime()));
				warehouseManagerDAO.addProductToInventory(WIto);
				iret=1;
			}
			
			if(iret<=0)
				throw new updateProductInventoryException();
			
			//Generating the Log Message.
			systemLog = MessageUtil.getMessage("label.moved.loc.batch.to.warehouse.location", locale);
			systemLog = new String(systemLog.replaceAll("%UNITS", lQty+""));
			systemLog = new String(systemLog.replaceAll("%FLOCATION", "'"+strFromLocation+"'"));
			if(WIfrom.getBatchNum()!=null)
				systemLog = new String(systemLog.replaceAll("%FBATCH", "'"+WIfrom.getBatchNum()+"'"));
			else 
				systemLog = new String(systemLog.replaceAll("%FBATCH", "an empty"));
			systemLog = new String(systemLog.replaceAll("%TWAREHOUSE", "'"+strToWarehouseName+"'"));
			systemLog = new String(systemLog.replaceAll("%TLOCATION", "'"+strToLocation+"'"));
			if(WIto.getBatchNum() !=null)
			{
				systemLog = new String(systemLog.replaceAll("%NEW", "the"));
				systemLog = new String(systemLog.replaceAll("%TBATCH", "'"+WIto.getBatchNum()+"'"));
			}
			else
			{
				systemLog = new String(systemLog.replaceAll("%NEW", "an empty"));
				systemLog = new String(systemLog.replaceAll("%TBATCH", ""));
			}
			// Set the loggedEvent Details
			loggedEvent.setEntityType(ShiplinxConstants.ENTITY_TYPE_PRODUCT_VALUE); //Entity - Warehouse
			loggedEvent.setEntityId(WIfrom.getProductId());//Product ID
			loggedEvent.setEventDateTime(new Date()); //Current Date
			loggedEvent.setEventUsername(UserUtil.getMmrUser().getUsername()); //Current User
			loggedEvent.setSystemLog(systemLog); //System generated Message Log
			//Log the Event into the DB
			loggedEventDAO.addLoggedEventInfo(loggedEvent); //Added Event Log into DB
	}
	
	public List<WarehouseSummary> generateSummaryByWarehouse(WarehouseInventory warehouseInventory)
	{
		long linventorySum = 0;
		long lwipSum = 0;
		long lquarantineSum = 0;
		WarehouseSummary warehouseSummary = new WarehouseSummary();
		warehouseSummary.setWarehouseId(warehouseInventory.getWarehouseLocation().getWarehouse().getWarehouseId());
		warehouseSummary.setWarehouseName(warehouseInventory.getWarehouseLocation().getWarehouse().getWarehouseName());
		//Fetch the count of the different location types for the warehouse fetched
		linventorySum = warehouseManagerDAO.getStockCountForSummaryByWarehouse(warehouseInventory.getWarehouseLocation().getWarehouse().getWarehouseId(), warehouseInventory.getProductId(), 1);
		lwipSum = warehouseManagerDAO.getStockCountForSummaryByWarehouse(warehouseInventory.getWarehouseLocation().getWarehouse().getWarehouseId(), warehouseInventory.getProductId(), 2);
		lquarantineSum = warehouseManagerDAO.getStockCountForSummaryByWarehouse(warehouseInventory.getWarehouseLocation().getWarehouse().getWarehouseId(), warehouseInventory.getProductId(), 3);
		
		//Inject values into the warehouseSummary object
		warehouseSummary.setInventoryItemCount(linventorySum);
		warehouseSummary.setWipItemCount(lwipSum);
		warehouseSummary.setQuarantineItemCount(lquarantineSum);
		warehouseSummary.setWarehouseSubTotal(linventorySum+lwipSum+lquarantineSum);
		//Add the object to the list.
		warehouseSummaryList.add(warehouseSummary);	
		return warehouseSummaryList;
	}
	
	public void addProductToInventory(WarehouseInventory warehouseInventory)
	{
		warehouseManagerDAO.addProductToInventory(warehouseInventory);
	}
	
	public int updateProductToInventory(WarehouseInventory warehouseInventory)
	{
		int ireturn = warehouseManagerDAO.updateProductToInventory(warehouseInventory);
		return ireturn;
	}
	
	public AddressDAO getAddressDAO() {
		return addressDAO;
	}

	public void setAddressDAO(AddressDAO addressDAO) {
		this.addressDAO = addressDAO;
	}

	public LoggedEventDAO getLoggedEventDAO() {
		return loggedEventDAO;
	}

	public void setLoggedEventDAO(LoggedEventDAO loggedEventDAO) {
		this.loggedEventDAO = loggedEventDAO;
	}

	public ProductManagerDAO getProductManagerDAO() {
		return productManagerDAO;
	}

	public void setProductManagerDAO(ProductManagerDAO productManagerDAO) {
		this.productManagerDAO = productManagerDAO;
	}

}
