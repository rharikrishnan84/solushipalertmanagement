package com.meritconinc.shiplinx.action;

import com.meritconinc.mmr.exception.QtyGreaterThanFromLocationQtyException;
import com.meritconinc.mmr.exception.SelectedFromLocBatchInvalidException;
import com.meritconinc.mmr.exception.updateProductInventoryException;
import com.meritconinc.mmr.model.security.User;
import com.meritconinc.mmr.utilities.MessageUtil;
import com.meritconinc.mmr.utilities.security.UserUtil;
import com.meritconinc.shiplinx.model.Address;
import com.meritconinc.shiplinx.model.LoggedEvent;
import com.meritconinc.shiplinx.model.ProductLine;
import com.meritconinc.shiplinx.model.Products;
import com.meritconinc.shiplinx.model.Province;
import com.meritconinc.shiplinx.model.Warehouse;
import com.meritconinc.shiplinx.model.WarehouseInventory;
import com.meritconinc.shiplinx.model.WarehouseLocation;
import com.meritconinc.shiplinx.model.WarehouseLocationType;
import com.meritconinc.shiplinx.model.WarehouseSummary;
import com.meritconinc.shiplinx.service.AddressManager;
import com.meritconinc.shiplinx.service.LoggedEventService;
import com.meritconinc.shiplinx.service.ProductManager;
import com.meritconinc.shiplinx.service.WarehouseManager;
import com.opensymphony.xwork2.Preparable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;

public class WarehouseManagerAction extends BaseAction
  implements Preparable, ServletRequestAware
{
  private static final long serialVersionUID = 1306201213001L;
  private static final Logger log = LogManager.getLogger(CustomerManagerAction.class);
  public HttpServletRequest request;
  private WarehouseManager warehouseManagerService;
  private AddressManager addressService;
  private List<Province> provinces;
  private List<Warehouse> warehouseList;
  private List<WarehouseLocation> warehouseLocationAisleList;
  private List<WarehouseLocation> warehouseLocationList;
  private List<WarehouseLocation> warehouseProductLocationList;
  private List<WarehouseLocationType> warehouseLocationTypeList;
  private List<Products> productsList;
  private List<ProductLine> productLineList;
  private ProductManager productService;
  private List<WarehouseInventory> warehouseInventoryList;
  private List<WarehouseSummary> warehouseInventorySummaryList;
  private List<WarehouseInventory> warehouseInventoryCheckList;
  private List<Map<Long, String>> ListWIPLocations;
  private LoggedEventService loggedEventService;
  private List<LoggedEvent> loggedList;
  private Map<String, Long> locationsSearchResult = new HashMap();
 
  private Map<String, String> locationsWarehousesSearchResult = new HashMap();

  private Map<String, Long> locationWHSearchResult = new HashMap();

  private Map<String, Long> SortedlocationWHSearchResult = new HashMap();

  private Map<String, Long> WHSearchResult = new HashMap();

  private Map<String, Long> batchesSearchResult = new HashMap();
  List<String> lstBatches;
  List<String> lstLocations;

  public void setServletRequest(HttpServletRequest request)
  {
    this.request = request;
  }

  public void prepare()
    throws Exception
  {
  }

  public String listWarehouses()
  {
    log.debug("Inside-----------listWarehouses() method--------------");
    try {
      Warehouse warehouse = getWarehouse();

      this.warehouseList = this.warehouseManagerService.findWarehouseByBusinessId(UserUtil.getMmrUser().getBusinessId());
      for (Warehouse w : this.warehouseList)
      {
        Address add = this.addressService.findAddressById(w.getAddressId() + "");

        w.setAddress(add);
      }
    }
    catch (Exception e) {
      log.debug("---------------Exception in listWarehouses()..." + e);
    }

    return "success";
  }

  public String addNewWarehouse()
  {
    log.debug("Inside-----------addNewWarehouse() method--------------");
    getSession().remove("warehouse");
    getSession().remove("edit");
    return "success";
  }

  public WarehouseManager getWarehouseManagerService() {
    return this.warehouseManagerService;
  }

  public void setWarehouseManagerService(WarehouseManager warehouseManagerService) {
    this.warehouseManagerService = warehouseManagerService;
  }

  public String listWarehouseProvince() throws Exception
  {
    String country = this.request.getParameter("value");
    if ((country == null) || ("".equals(country)))
      country = "US";
    this.provinces = this.addressService.getProvinces(country);

    getSession().put("provinces", this.provinces);
    return "success";
  }

  public String edit()
  {
    log.debug("Inside-----------edit() method--------------");
    try {
      getSession().put("edit", "true");

      String strWHid = this.request.getParameter("id");

      Warehouse warehouse = getWarehouse();
      warehouse.setWarehouseId(Long.valueOf(strWHid).longValue());
      List<Warehouse> warehouseList = this.warehouseManagerService.searchWarehouseById(Long.valueOf(strWHid).longValue());

      for (Warehouse w : warehouseList)
      {
        Address add = this.addressService.findAddressById(w.getAddressId() + "");
        warehouse.getAddress().copyAddress(add);
        warehouse.setWarehouseCode(w.getWarehouseCode());
        warehouse.setWarehouseName(w.getWarehouseName());
        warehouse.setBusinessId(w.getBusinessId());
        warehouse.setAddressId(w.getAddressId());
      }
    }
    catch (Exception e)
    {
      Warehouse warehouse;
      log.debug("---------------Exception in edit()..." + e);
    }

    return "success";
  }

  public String NewOrEditWarehouse()
  {
    log.debug("--------------------Inside NewOrEditWarehouse() method --------------------------------------");
    try
    {
      if (getSession().get("edit") != null)
      {
        Warehouse warehouse = getWarehouse();
        this.warehouseManagerService.addNewWarehouse(warehouse, false);

        addActionMessage(getText("warehouse.save.successfully"));
      }
      else
      {
        Warehouse warehouse = getWarehouse();

        this.warehouseManagerService.addNewWarehouse(warehouse, true);

        addActionMessage(getText("warehouse.create.successfully"));
      }

      getSession().remove("warehouse");
    }
    catch (Exception e) {
      log.debug("---------------Exception in NewOrEditWarehouse()..." + e);
      addActionError(getText("warehouse.save.failed"));
    }

    return "success";
  }

  public String delete()
  {
    log.debug("Inside-----------delete() method--------------");
    try {
      getSession().remove("edit");

      String StrId = this.request.getParameter("id");
      this.warehouseManagerService.deleteWarehouse(Long.valueOf(StrId).longValue());

      addActionMessage(getText("warehouse.delete.successfully"));
    } catch (Exception e) {
      log.debug("---------------Exception in delete()..." + e);
      addActionError(getText("warehouse.delete.failed"));
    }

    return listWarehouses();
  }

  public String manageProducts()
  {
    log.debug("Inside-----------manageProducts() method--------------");
    try {
      String strRole = UserUtil.getMmrUser().getUserRole();
      log.debug("##############################-------Role is--------------" + strRole);
      if (strRole.equals("busadmin"))
      {
        getSession().put("role", "busadmin");
        if (this.request.getParameter("cid") != null)
        {
          long lCustomerId = Long.valueOf(this.request.getParameter("cid").toString()).longValue();
          showProductsByCustomer(lCustomerId);
        }
      }
      else
      {
        long lCustomerId = UserUtil.getMmrUser().getCustomerId();
        showProductsByCustomer(lCustomerId);
      }
      getSession().remove("customerName");
    }
    catch (Exception e) {
      log.debug("---------------Exception in manageProducts()..." + e);
    }

    return "success";
  }

  public void showProductsByCustomer(long customerId)
  {
    Products products = new Products();
    products.setCustomerId(customerId);
    this.productsList = this.productService.getProductsList(products);
  }

  public String manageProductsLine()
  {
    log.debug("Inside-----------manageProductsLine() method--------------");
    try {
      String strRole = UserUtil.getMmrUser().getUserRole();
      log.debug("##############################-------Role is--------------" + strRole);
      if (strRole.equals("busadmin"))
      {
        this.request.setAttribute("role", "busadmin");
        if (this.request.getParameter("cid") != null)
        {
          long lCustomerId = Long.valueOf(this.request.getParameter("cid").toString()).longValue();
          showProductsLineByCustomer(lCustomerId);
        }
      }
      else
      {
        long lCustomerId = UserUtil.getMmrUser().getCustomerId();
        showProductsLineByCustomer(lCustomerId);
      }
    }
    catch (Exception e) {
      log.debug("---------------Exception in manageProductsLine()..." + e);
    }

    return "success";
  }

  public String manageLocations()
  {
    log.debug("Inside-----------manageLocations() method--------------");
    try {
      this.warehouseList = this.warehouseManagerService.findWarehouseByBusinessId(UserUtil.getMmrUser().getBusinessId());
      for (Warehouse w : this.warehouseList)
      {
        Address add = this.addressService.findAddressById(w.getAddressId() + "");

        w.setAddress(add);
      }

      this.warehouseLocationAisleList = new ArrayList();
      getSession().put("WHLIST", this.warehouseList);
      getSession().put("AISLELIST", this.warehouseLocationAisleList);
      getSession().remove("warehouseLocation");
    }
    catch (Exception e) {
      log.debug("---------------Exception in manageLocations()..." + e);
    }

    return "success";
  }

  public void showProductsLineByCustomer(long customerId)
  {
    ProductLine productLine = new ProductLine();
    productLine.setCustomerId(customerId);
    this.productLineList = this.productService.getProductLineList(productLine);
  }

  public String populateAisleOrRow()
  {
    log.debug("Inside-----------populateAisleOrRow() method--------------");
    try {
      String strmethod = this.request.getParameter("method");
      String strSelected = this.request.getParameter("value");

      WarehouseLocation warehouseLocation = getWarehouseLocation();

      if ("aisle".equals(strmethod))
      {
        warehouseLocation.setWarehouseId(Long.valueOf(strSelected).longValue());
        this.warehouseLocationAisleList = this.warehouseManagerService.getWarehouseLocationAisleByWarehouseId(Long.valueOf(strSelected).longValue());
      }
      else
      {
        warehouseLocation.setAisle(strSelected);
        this.warehouseLocationAisleList = this.warehouseManagerService.getWarehouseLocationRowByWarehouseId(warehouseLocation);
        this.request.setAttribute("row", new Boolean(false));
      }

      getSession().put("AISLELIST", this.warehouseLocationAisleList);
    } catch (Exception e) {
      log.debug("---------------Exception in populateAisleOrRow()..." + e);
    }

    return "success";
  }

  public String searchwarehouselocations()
  {
    log.debug("Inside-----------searchwarehouselocations() method--------------");
    try {
      WarehouseLocation warehouseLocation = getWarehouseLocation();
      if (warehouseLocation.getAisle().equals("-1"))
        warehouseLocation.setAisle("");
      if (warehouseLocation.getRow().equals("-1"))
        warehouseLocation.setRow("");
      this.warehouseLocationList = this.warehouseManagerService.getWarehouseLocationInfo(warehouseLocation);

      this.warehouseLocationAisleList = this.warehouseManagerService.getWarehouseLocationAisleByWarehouseId(warehouseLocation.getWarehouseId());

      getSession().put("AISLELIST", this.warehouseLocationAisleList);
    } catch (Exception e) {
      log.debug("---------------Exception in searchwarehouselocations()..." + e);
    }

    return "success";
  }

  public String goToAddEditWarehouseLocation()
  {
    log.debug("Inside-----------goToAddEditWarehouseLocation() method--------------");
    try {
      String strmethod = this.request.getParameter("method");
      String strlocId = this.request.getParameter("locationId");
      if (strlocId != null)
      {
        WarehouseLocation warehouseLocation = getWarehouseLocation();
        WarehouseLocationType warehouseLocationType = getWarehouseLocationType();
        warehouseLocation.setLocationId(Long.valueOf(strlocId).longValue());

        this.warehouseLocationList = this.warehouseManagerService.getWarehouseLocationInfoByLocationId(warehouseLocation);
        for (WarehouseLocation wl : this.warehouseLocationList)
        {
          warehouseLocation.setLocationTypeId(wl.getLocationTypeId());
          warehouseLocationType.setWarehouseLocationTypeId(warehouseLocation.getLocationTypeId());
          warehouseLocation.setWarehouseId(wl.getWarehouseId());
          warehouseLocation.setAisle(wl.getAisle());
          warehouseLocation.setLevel(wl.getLevel());
          warehouseLocation.setRow(wl.getRow());
          warehouseLocation.setSection(wl.getSection());
          warehouseLocation.setLocationName(wl.getLocationName());
        }

        getSession().put("edit", "true");
      }
      if (strmethod != null)
      {
        getSession().remove("warehouseLocation");
        getSession().remove("warehouseLocationType");
        getSession().remove("edit");
      }
      this.warehouseLocationTypeList = this.warehouseManagerService.getWarehouseLocationTypeInfo();
      getSession().put("WHLTList", this.warehouseLocationTypeList);
    }
    catch (Exception e) {
      log.debug("---------------Exception in goToAddEditWarehouseLocation()..." + e);
    }

    return "success";
  }

  public String createEditWHL()
  {
    log.debug("Inside-----------createEditWHL() method--------------");
    try {
      String strmethod = this.request.getParameter("method");
      boolean booladd = true;
      if ("edit".equals(strmethod)) {
        booladd = false;
      }
      WarehouseLocation warehouseLocation = getWarehouseLocation();

      warehouseLocation.setLocationName(warehouseLocation.getAisle() + "-" + warehouseLocation.getRow() + "-" + warehouseLocation.getLevel() + "-" + warehouseLocation.getSection());
      WarehouseLocationType warehouseLocationType = getWarehouseLocationType();
      if (booladd)
      {
        this.warehouseManagerService.addOrUpdateWarehouseLocation(warehouseLocation, true);
        addActionMessage(getText("warehouselocation.add.successfully"));
      }
      else
      {
        this.warehouseManagerService.addOrUpdateWarehouseLocation(warehouseLocation, false);
        addActionMessage(getText("warehouselocation.edit.successfully"));
      }
    } catch (Exception e) {
      log.debug("---------------Exception in createEditWHL()..." + e);
      addActionError(getText("warehouselocation.save.failed"));
    }

    return "success";
  }

  public String deleteWHLocations()
  {
    log.debug("Inside-----------deleteWHLocations() method--------------");
    try {
      String strlocId = this.request.getParameter("locationId");
      WarehouseLocation warehouseLocation = getWarehouseLocation();
      warehouseLocation.setLocationId(Long.valueOf(strlocId).longValue());

      this.warehouseLocationList = this.warehouseManagerService.getWarehouseLocationInfoByLocationId(warehouseLocation);
      for (WarehouseLocation wl : this.warehouseLocationList)
      {
        warehouseLocation.setWarehouseId(wl.getWarehouseId());
        warehouseLocation.setAisle(wl.getAisle());
        warehouseLocation.setRow(wl.getRow());
      }

      this.warehouseManagerService.deleteWarehouseLocation(warehouseLocation);

      addActionMessage(getText("warehouselocation.delete.successfully"));

      this.warehouseLocationList = this.warehouseManagerService.getWarehouseLocationInfo(warehouseLocation);

      getSession().remove("warehouseLocation");
    } catch (Exception e) {
      log.debug("---------------Exception in deleteWHLocations()..." + e);
      addActionError(getText("warehouselocation.delete.failed"));
    }

    return "success";
  }

  public String gotoProductInventory()
  {
    log.debug("---------------Inside.-------gotoProductInventory()------");
    long wid = 0L;
    String strProductId = null;
    String strCustId = null;
    try
    {
      this.lstBatches = new ArrayList();
      this.lstLocations = new ArrayList();
      this.warehouseInventorySummaryList = new ArrayList();

      strProductId = this.request.getParameter("productId");

      strCustId = this.request.getParameter("cid");

      WarehouseInventory warehouseInventory = getWarehouseInventory();
      warehouseInventory.setProductId(Long.valueOf(strProductId).longValue());
      this.warehouseInventoryList = this.warehouseManagerService.getWarehouseInventoryByProdId(warehouseInventory);

      WarehouseLocation warehouseLocation = getWarehouseLocation();
      warehouseLocation.setWarehouseHiddenId(0L);
      for (WarehouseInventory whinv : this.warehouseInventoryList)
      {
        warehouseInventory.setQuantity(whinv.getQuantity());

        warehouseLocation.setLocationId(whinv.getLocationId());
        this.warehouseLocationList = this.warehouseManagerService.getWarehouseLocationInfoByLocationId(warehouseLocation);
        for (WarehouseLocation wl : this.warehouseLocationList)
        {
          warehouseLocation.setWarehouseLocationType(wl.getWarehouseLocationType());
          warehouseLocation.setWarehouse(wl.getWarehouse());
          warehouseLocation.setLocationName(wl.getLocationName());
        }
      }
      warehouseInventory.setWarehouseLocation(warehouseLocation);

      Products products = new Products();
      products.setProductId(Long.valueOf(strProductId).longValue());
      products.setCustomerId(Long.valueOf(strCustId).longValue());

      this.productsList = this.productService.searchProducts(products, true);
      for (Products p : this.productsList)
      {
        warehouseInventory.setProducts(p);
      }
      this.warehouseList = this.warehouseManagerService.findWarehouseByBusinessId(UserUtil.getMmrUser().getBusinessId());

      LoggedEvent loggedEvent = getLoggedEvent();

      loggedEvent.setEntityType(20L);
      loggedEvent.setEntityId(warehouseInventory.getProducts().getProductId());

      if (this.request.getParameter("productsummary") == null)
      {
        for (WarehouseInventory winv : this.warehouseInventoryList)
        {
          boolean contains = false;
          if (this.warehouseInventorySummaryList.size() == 0)
          {
            this.warehouseInventorySummaryList = this.warehouseManagerService.generateSummaryByWarehouse(winv);
          }
          else
          {
            for (int i = 0; i < this.warehouseInventorySummaryList.size(); i++)
            {
              if (((WarehouseSummary)this.warehouseInventorySummaryList.get(i)).getWarehouseId() != winv.getWarehouseLocation().getWarehouse().getWarehouseId())
                continue;
              contains = true;
              break;
            }

            if (!contains)
            {
              this.warehouseInventorySummaryList = this.warehouseManagerService.generateSummaryByWarehouse(winv);
            }
          }

        }

      }
      else
      {
        for (WarehouseInventory wv : this.warehouseInventoryList)
        {
          List lstWHLocs = new ArrayList();
          this.warehouseLocationList = this.warehouseManagerService.getAllWIPLocationsForWarehouse(wv.getWarehouseLocation().getWarehouse().getWarehouseId());
          if (this.warehouseLocationList.size() > 0)
          {
            for (WarehouseLocation wl : this.warehouseLocationList)
            {
              lstWHLocs.add(wl);
            }
          }
          wv.setMapWHWIPLocations(lstWHLocs);
        }

      }

      if (!UserUtil.getMmrUser().getUserRole().equals("busadmin"))
      {
        loggedEvent.setPrivateMessage(false);
        loggedEvent.setDeletedMessage(false);
        this.loggedList = this.loggedEventService.getLoggedEventInfo(loggedEvent, false);
      }
      else
      {
        this.loggedList = this.loggedEventService.getLoggedEventInfo(loggedEvent, true);
      }
      getSession().put("LSTBatches", this.lstBatches);
      getSession().put("LSTLocations", this.lstLocations);

      getSession().put("WHLIST", this.warehouseList);

      getSession().remove("loggedEvent");
    } catch (Exception e) {
      e.printStackTrace();
    }
    if (this.request.getParameter("productsummary") != null) {
      return "success2";
    }
    return "success";
  }

  public String addProductToInventory()
  {
    log.debug("---------------Inside.-------addProductToInventory()------");
    String strlocId = this.request.getParameter("locid");
    String strQty = this.request.getParameter("quantity");
    String strBatch = this.request.getParameter("batch");
    String strComment = this.request.getParameter("comment");
    boolean boolprivate = Boolean.valueOf(this.request.getParameter("private")).booleanValue();
    int iret = 0;
    this.warehouseInventorySummaryList = new ArrayList();
    try
    {
      String locale = UserUtil.getMmrUser().getLocale();
      String systemLog = null;
      WarehouseInventory warehouseInventory = getWarehouseInventory();
      WarehouseLocation warehouseLocation = getWarehouseLocation();
      warehouseLocation.setLocationId(Long.valueOf(strlocId).longValue());
      this.warehouseLocationList = this.warehouseManagerService.getWarehouseLocationInfoByLocationId(warehouseLocation);
      for (WarehouseLocation wl : this.warehouseLocationList)
      {
        warehouseLocation.setWarehouseLocationType(wl.getWarehouseLocationType());
        warehouseLocation.setWarehouse(wl.getWarehouse());
        warehouseLocation.setLocationName(wl.getLocationName());
      }
      warehouseInventory.setWarehouseLocation(warehouseLocation);
      if ((strBatch != null) && (strBatch.trim().length() > 0))
        warehouseInventory.setBatchNum(strBatch);
      else
        warehouseInventory.setBatchNum(null);
      warehouseInventory.setLocationId(Long.valueOf(strlocId).longValue());
      warehouseInventory.setQuantity(Long.valueOf(strQty).longValue());
      warehouseInventory.setDateCreated(new Timestamp(new Date().getTime()));

      LoggedEvent loggedEvent = getLoggedEvent();

      this.warehouseInventoryCheckList = this.warehouseManagerService.getWarehouseInventoryByProdIdAndLocId(warehouseInventory);

      if (this.warehouseInventoryCheckList.size() > 0)
      {
        long lquantity = ((WarehouseInventory)this.warehouseInventoryCheckList.get(0)).getQuantity() + Long.valueOf(strQty).longValue();
        warehouseInventory.setQuantity(lquantity);
        iret = this.warehouseManagerService.updateProductToInventory(warehouseInventory);
        if (iret <= 0)
        {
          throw new Exception();
        }
        systemLog = MessageUtil.getMessage("label.update.existing.batch", locale);
        systemLog = new String(systemLog.replaceAll("%PRODUCT", ""));
        systemLog = new String(systemLog.replaceAll("%LOCATION", "'" + warehouseLocation.getLocationName() + "'"));
        systemLog = new String(systemLog.replaceAll("%UNITS", strQty));
        if ((warehouseInventory.getBatchNum() != null) && (warehouseInventory.getBatchNum().trim().length() > 0))
          systemLog = new String(systemLog.replaceAll("%BATCH", "'" + warehouseInventory.getBatchNum() + "'"));
        else {
          systemLog = new String(systemLog.replaceAll("%BATCH", ""));
        }
        loggedEvent.setEntityType(20L);
        loggedEvent.setEntityId(warehouseInventory.getProducts().getProductId());
        loggedEvent.setEventDateTime(new Date());
        loggedEvent.setEventUsername(UserUtil.getMmrUser().getUsername());
        loggedEvent.setMessage(strComment);
        loggedEvent.setPrivateMessage(boolprivate);
        loggedEvent.setSystemLog(systemLog);

        this.loggedEventService.addLoggedEventInfo(loggedEvent);
        addActionMessage(getText("product.updated.inventory.successfully"));
      }
      else
      {
        this.warehouseManagerService.addProductToInventory(warehouseInventory);
        systemLog = MessageUtil.getMessage("label.add.new.batch", locale);
        systemLog = new String(systemLog.replaceAll("%PRODUCT", ""));
        systemLog = new String(systemLog.replaceAll("%LOCATION", "'" + warehouseLocation.getLocationName() + "'"));
        systemLog = new String(systemLog.replaceAll("%UNITS", strQty));
        if ((warehouseInventory.getBatchNum() != null) && (warehouseInventory.getBatchNum().trim().length() > 0))
          systemLog = new String(systemLog.replaceAll("%BATCH", "'" + warehouseInventory.getBatchNum() + "'"));
        else {
          systemLog = new String(systemLog.replaceAll("%BATCH", ""));
        }
        loggedEvent.setEntityType(20L);
        loggedEvent.setEntityId(warehouseInventory.getProducts().getProductId());
        loggedEvent.setEventDateTime(new Date());
        loggedEvent.setEventUsername(UserUtil.getMmrUser().getUsername());
        loggedEvent.setMessage(strComment);
        loggedEvent.setPrivateMessage(boolprivate);
        loggedEvent.setSystemLog(systemLog);

        this.loggedEventService.addLoggedEventInfo(loggedEvent);
        addActionMessage(getText("product.add.inventory.successfully"));
      }

      if (warehouseLocation.getWarehouseLocationType().getWarehouseLocationTypeId() == 1L)
        warehouseInventory.getProducts().setLocationCount(Integer.parseInt(String.valueOf(warehouseInventory.getProducts().getLocationCount())) + Integer.parseInt(String.valueOf(Long.valueOf(strQty))));
      else if (warehouseLocation.getWarehouseLocationType().getWarehouseLocationTypeId() == 2L)
        warehouseInventory.getProducts().setWipCount(Integer.parseInt(String.valueOf(warehouseInventory.getProducts().getWipCount())) + Integer.parseInt(String.valueOf(String.valueOf(Long.valueOf(strQty)))));
      else if (warehouseLocation.getWarehouseLocationType().getWarehouseLocationTypeId() == 3L) {
        warehouseInventory.getProducts().setQuarantineCount(Integer.parseInt(String.valueOf(warehouseInventory.getProducts().getQuarantineCount())) + Integer.parseInt(String.valueOf(String.valueOf(Long.valueOf(strQty)))));
      }
      this.productService.addOrUpdate(warehouseInventory.getProducts(), false);

      this.warehouseInventoryList = this.warehouseManagerService.getWarehouseInventoryByProdId(warehouseInventory);

      for (WarehouseInventory winv : this.warehouseInventoryList)
      {
        boolean contains = false;
        if (this.warehouseInventorySummaryList.size() == 0)
        {
          this.warehouseInventorySummaryList = this.warehouseManagerService.generateSummaryByWarehouse(winv);
        }
        else
        {
          for (int i = 0; i < this.warehouseInventorySummaryList.size(); i++)
          {
            if (((WarehouseSummary)this.warehouseInventorySummaryList.get(i)).getWarehouseId() != winv.getWarehouseLocation().getWarehouse().getWarehouseId())
              continue;
            contains = true;
            break;
          }

          if (!contains)
          {
            this.warehouseInventorySummaryList = this.warehouseManagerService.generateSummaryByWarehouse(winv);
          }
        }
      }

      if (!UserUtil.getMmrUser().getUserRole().equals("busadmin"))
      {
        loggedEvent.setPrivateMessage(false);
        loggedEvent.setDeletedMessage(false);
        this.loggedList = this.loggedEventService.getLoggedEventInfo(loggedEvent, false);
      }
      else
      {
        this.loggedList = this.loggedEventService.getLoggedEventInfo(loggedEvent, true);
      }
      getSession().remove("loggedEvent");
    } catch (Exception e) {
      e.printStackTrace();
      log.error("", e);
      addActionError(getText("product.add.move.inventory.failed"));
    }
    return "success";
  }

  public String moveProductInInventory()
  {
    log.debug("---------------Inside.-------moveProductInInventory()------");
    LoggedEvent loggedEvent = new LoggedEvent();
    WarehouseInventory warehouseInventory = new WarehouseInventory();

    long fromLocId = Long.valueOf(this.request.getParameter("flocid")).longValue();
    long toLocId = Long.valueOf(this.request.getParameter("tlocid")).longValue();
    long toWHId = Long.valueOf(this.request.getParameter("twhid")).longValue();

    long lqty = Long.valueOf(this.request.getParameter("qty")).longValue();
    String strBatch = this.request.getParameter("batch");
    String strBatchTo = this.request.getParameter("batchto");

    long lCustomerId = UserUtil.getMmrUser().getCustomerId();
    if (strBatchTo.equals("--Select--"))
      strBatchTo = "-1";
    int ichk = 0;
    this.warehouseInventorySummaryList = new ArrayList();
    try
    {
      warehouseInventory = getWarehouseInventory();

      WarehouseInventory warehouseInventoryfrom = new WarehouseInventory();

      WarehouseInventory warehouseInventoryto = new WarehouseInventory();

      if ((strBatch != null) && (Long.valueOf(strBatch).longValue() != -1L))
        warehouseInventoryfrom.setBatchNum(strBatch);
      else
        warehouseInventoryfrom.setBatchNum(null);
      warehouseInventoryfrom.setLocationId(fromLocId);
      warehouseInventoryfrom.setWarehouseLocation(warehouseInventory.getWarehouseLocation());
      warehouseInventoryfrom.setQuantity(lqty);
      warehouseInventoryfrom.setProducts(warehouseInventory.getProducts());

      if ((strBatchTo != null) && (Long.valueOf(strBatchTo).longValue() != -1L))
        warehouseInventoryto.setBatchNum(strBatchTo);
      else
        warehouseInventoryto.setBatchNum(null);
      warehouseInventoryto.setLocationId(toLocId);
      warehouseInventoryto.setWarehouseLocation(warehouseInventory.getWarehouseLocation());
      warehouseInventoryto.getWarehouseLocation().setWarehouseId(toWHId);
      warehouseInventoryto.setQuantity(lqty);
      warehouseInventoryto.setProducts(warehouseInventory.getProducts());

      loggedEvent = getLoggedEvent();
      loggedEvent.setEntityType(20L);
      loggedEvent.setEntityId(warehouseInventory.getProducts().getProductId());

      this.warehouseManagerService.moveProductInInventory(warehouseInventoryfrom, warehouseInventoryto, loggedEvent);

      generateResults(warehouseInventory, loggedEvent);
    }
    catch (QtyGreaterThanFromLocationQtyException qe)
    {
      ichk++;
      String[] args = new String[1];
      args[0] = String.valueOf(lqty);
      addActionError(getText("quantity.greater.inventory.quantity", new String[] { args[0] }));
      generateResults(warehouseInventory, loggedEvent);
    }
    catch (SelectedFromLocBatchInvalidException se)
    {
      ichk++;
      addActionError(getText("selected.flocation.fbatch.invalid"));
      generateResults(warehouseInventory, loggedEvent);
    }
    catch (updateProductInventoryException ue)
    {
      ichk++;
      addActionError(getText("product.update.inventory.failed"));
      generateResults(warehouseInventory, loggedEvent);
    }
    if (ichk == 0)
      addActionMessage(getText("product.move.succuessfully"));
    return "success";
  }

  public String moveProductInInventoryAjax()
  {
    log.debug("---------------Inside.-------moveProductInInventoryAjax()------");
    LoggedEvent loggedEvent = new LoggedEvent();
    WarehouseInventory warehouseInventorytemp = new WarehouseInventory();

    long fromLocId = Long.valueOf(this.request.getParameter("flocid")).longValue();
    long toLocId = Long.valueOf(this.request.getParameter("tlocid")).longValue();
    long toWHId = Long.valueOf(this.request.getParameter("twhid")).longValue();
    long lqty = Long.valueOf(this.request.getParameter("qty")).longValue();
    String strBatch = this.request.getParameter("batch");
    String strBatchTo = this.request.getParameter("batchto");
    String strProductId = null;
    if (strBatchTo.equals("--Select--"))
      strBatchTo = "-1";
    int ichk = 0;
    this.warehouseInventoryList = new ArrayList();
    try
    {
      warehouseInventorytemp = getWarehouseInventory();

      WarehouseInventory warehouseInventoryfrom = new WarehouseInventory();

      WarehouseInventory warehouseInventoryto = new WarehouseInventory();

      if ((strBatch != null) && (Long.valueOf(strBatch).longValue() != -1L))
        warehouseInventoryfrom.setBatchNum(strBatch);
      else
        warehouseInventoryfrom.setBatchNum(null);
      warehouseInventoryfrom.setLocationId(fromLocId);
      warehouseInventoryfrom.setWarehouseLocation(warehouseInventorytemp.getWarehouseLocation());
      warehouseInventoryfrom.setQuantity(lqty);
      warehouseInventoryfrom.setProducts(warehouseInventorytemp.getProducts());

      if ((strBatchTo != null) && (Long.valueOf(strBatchTo).longValue() != -1L))
        warehouseInventoryto.setBatchNum(strBatchTo);
      else
        warehouseInventoryto.setBatchNum(null);
      warehouseInventoryto.setLocationId(toLocId);
      warehouseInventoryto.setWarehouseLocation(warehouseInventorytemp.getWarehouseLocation());
      warehouseInventoryto.getWarehouseLocation().setWarehouseId(toWHId);
      warehouseInventoryto.setQuantity(lqty);
      warehouseInventoryto.setProducts(warehouseInventorytemp.getProducts());

      loggedEvent = getLoggedEvent();
      loggedEvent.setEntityType(20L);
      loggedEvent.setEntityId(warehouseInventorytemp.getProducts().getProductId());

      this.warehouseManagerService.moveProductInInventory(warehouseInventoryfrom, warehouseInventoryto, loggedEvent);

      this.lstBatches = new ArrayList();
      this.lstLocations = new ArrayList();

      strProductId = this.request.getParameter("productId");

      WarehouseInventory warehouseInventory = getWarehouseInventory();
      warehouseInventory.setProductId(Long.valueOf(strProductId).longValue());
      this.warehouseInventoryList = this.warehouseManagerService.getWarehouseInventoryByProdId(warehouseInventory);

      WarehouseLocation warehouseLocation = getWarehouseLocation();
      warehouseLocation.setWarehouseHiddenId(0L);
      for (WarehouseInventory whinv : this.warehouseInventoryList)
      {
        warehouseInventory.setQuantity(whinv.getQuantity());

        warehouseLocation.setLocationId(whinv.getLocationId());
        this.warehouseLocationList = this.warehouseManagerService.getWarehouseLocationInfoByLocationId(warehouseLocation);
        for (WarehouseLocation wl : this.warehouseLocationList)
        {
          warehouseLocation.setWarehouseLocationType(wl.getWarehouseLocationType());
          warehouseLocation.setWarehouse(wl.getWarehouse());
          warehouseLocation.setLocationName(wl.getLocationName());
        }
      }
      warehouseInventory.setWarehouseLocation(warehouseLocation);

      LoggedEvent loggedEvent2 = getLoggedEvent();

      loggedEvent2.setEntityType(20L);
      loggedEvent2.setEntityId(warehouseInventory.getProducts().getProductId());

      this.warehouseInventoryList = new ArrayList();
      this.warehouseInventoryList = this.warehouseManagerService.getWarehouseInventoryByProdId(warehouseInventory);

      for (WarehouseInventory wv : this.warehouseInventoryList)
      {
        List lstWHLocs = new ArrayList();
        this.warehouseLocationList = this.warehouseManagerService.getAllWIPLocationsForWarehouse(wv.getWarehouseLocation().getWarehouse().getWarehouseId());
        if (this.warehouseLocationList.size() > 0)
        {
          for (WarehouseLocation wl : this.warehouseLocationList)
          {
            lstWHLocs.add(wl);
          }
        }
        wv.setMapWHWIPLocations(lstWHLocs);
      }

      if (!UserUtil.getMmrUser().getUserRole().equals("busadmin"))
      {
        loggedEvent2.setPrivateMessage(false);
        loggedEvent2.setDeletedMessage(false);
        this.loggedList = this.loggedEventService.getLoggedEventInfo(loggedEvent2, false);
      }
      else
      {
        this.loggedList = this.loggedEventService.getLoggedEventInfo(loggedEvent2, true);
      }
      getSession().put("LSTBatches", this.lstBatches);
      getSession().put("LSTLocations", this.lstLocations);

      getSession().put("WHLIST", this.warehouseList);

      getSession().remove("loggedEvent");
    }
    catch (QtyGreaterThanFromLocationQtyException qe)
    {
      ichk++;
      String[] args = new String[1];
      args[0] = String.valueOf(lqty);
      addActionError(getText("quantity.greater.inventory.quantity", new String[] { args[0] }));
    }
    catch (SelectedFromLocBatchInvalidException se)
    {
      ichk++;
      addActionError(getText("selected.flocation.fbatch.invalid"));
    }
    catch (updateProductInventoryException ue)
    {
      ichk++;
      addActionError(getText("product.update.inventory.failed"));
    }

    if (ichk == 0)
      addActionMessage(getText("product.move.succuessfully"));
    return "success";
  }

  private void generateResults(WarehouseInventory warehouseInventory, LoggedEvent loggedEvent)
  {
    this.warehouseInventoryList = this.warehouseManagerService.getWarehouseInventoryByProdId(warehouseInventory);

    for (WarehouseInventory winv : this.warehouseInventoryList)
    {
      boolean contains = false;
      if (this.warehouseInventorySummaryList.size() == 0)
      {
        this.warehouseInventorySummaryList = this.warehouseManagerService.generateSummaryByWarehouse(winv);
      }
      else
      {
        for (int i = 0; i < this.warehouseInventorySummaryList.size(); i++)
        {
          if (((WarehouseSummary)this.warehouseInventorySummaryList.get(i)).getWarehouseId() != winv.getWarehouseLocation().getWarehouse().getWarehouseId())
            continue;
          contains = true;
          break;
        }

        if (!contains)
        {
          this.warehouseInventorySummaryList = this.warehouseManagerService.generateSummaryByWarehouse(winv);
        }
      }
    }

    if (!UserUtil.getMmrUser().getUserRole().equals("busadmin"))
    {
      loggedEvent.setPrivateMessage(false);
      loggedEvent.setDeletedMessage(false);
      this.loggedList = this.loggedEventService.getLoggedEventInfo(loggedEvent, false);
    }
    else
    {
      this.loggedList = this.loggedEventService.getLoggedEventInfo(loggedEvent, true);
    }
    getSession().remove("loggedEvent");
  }

  public String getDistinctBatchesforLocation()
  {
    log.debug("---------------Inside.-------getDistinctBatchesforLocation()------");

    long lwarehouseId = 0L;
    String strllocationId = null;
    if (this.request.getParameter("prodid") != null)
      this.request.setAttribute("prodid", this.request.getParameter("prodid"));
    if (this.request.getParameter("index") != null) {
      this.request.setAttribute("index", this.request.getParameter("index"));
    }
    if (this.request.getParameter("locId") != null) {
      strllocationId = this.request.getParameter("locId");
    }
    if (strllocationId.contains("-")) {
      strllocationId = strllocationId.substring(0, strllocationId.indexOf(45));
    }

    WarehouseInventory warehouseInventory = getWarehouseInventory();
    if (strllocationId.length() > 0)
      warehouseInventory.setLocationId(Long.valueOf(strllocationId).longValue());
    else {
      warehouseInventory.setLocationId(0L);
    }

    this.warehouseInventoryList = this.warehouseManagerService.getDistinctBatchesByLocId(warehouseInventory);
    this.lstBatches = new ArrayList();
    this.batchesSearchResult.put("--Select--", Long.valueOf(-1L));
    for (WarehouseInventory wi : this.warehouseInventoryList)
    {
      this.lstBatches.add(wi.getBatchNum());
      this.batchesSearchResult.put(wi.getBatchNum(), Long.valueOf(wi.getLocationId()));
    }
    this.batchesSearchResult = sortByComparator(this.batchesSearchResult);
    getSession().put("LSTBatches", this.lstBatches);
    getSession().put("lngWHId", Long.valueOf(lwarehouseId));
    return "success";
  }

  public String getAllLocations()
  {
    log.debug("---------------Inside.-------getAllLocations()------");

    String searchParameter = this.request.getParameter("searchString");
    log.debug("Search string is : " + searchParameter);

    WarehouseLocation warehouseLocation = new WarehouseLocation();
    if (searchParameter != null) {
      warehouseLocation.setLocationName(searchParameter);
    }
    this.warehouseLocationList = this.warehouseManagerService.getAllLocations(warehouseLocation);
    for (WarehouseLocation wl : this.warehouseLocationList) {
      this.locationsSearchResult.put(wl.getLocationName() + " : " + wl.getWarehouse().getWarehouseName(), Long.valueOf(wl.getLocationId()));
    }

    return "success";
  }

  public String getAllWarehouses()
  {
    log.debug("---------------Inside.-------getAllWarehouses()------");
    try
    {
      this.warehouseList = this.warehouseManagerService.findWarehouseByBusinessId(UserUtil.getMmrUser().getBusinessId());
      if (this.request.getParameter("any") == null)
        this.WHSearchResult.put("--Select Warehouse--", Long.valueOf(-1L));
      else
        this.WHSearchResult.put("--Select ANY--", Long.valueOf(-1L));
      for (Warehouse w : this.warehouseList)
      {
        this.WHSearchResult.put(w.getWarehouseName(), Long.valueOf(w.getWarehouseId()));
      }
      this.WHSearchResult = sortByComparator(this.WHSearchResult);
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    return "success";
  }

  public String getAllLocationsByProduct()
  {
    log.debug("---------------Inside.-------getAllLocationsByProduct()------");

    String searchParameter = this.request.getParameter("searchString");
    log.debug("Search string is : " + searchParameter);
    try
    {
      WarehouseInventory warehouseInventory = getWarehouseInventory();

      WarehouseLocation warehouseLocation = getWarehouseLocation();
      if (searchParameter != null)
        warehouseLocation.setLocationName(searchParameter);
      else
        warehouseLocation.setLocationName(null);
      this.warehouseInventoryList = this.warehouseManagerService.getWarehouseInventoryByProdId(warehouseInventory);
      for (WarehouseInventory wi : this.warehouseInventoryList)
      {
        warehouseLocation.setLocationId(wi.getLocationId());
        this.warehouseProductLocationList = this.warehouseManagerService.getAllLocationsProdSelected(warehouseLocation);
        for (WarehouseLocation wloc : this.warehouseProductLocationList)
        {
          if (wi.getWarehouseLocation().getWarehouse() != null)
          {
            this.locationsWarehousesSearchResult.put("-----Select Location-----", "-1");
            if (wi.getWarehouseLocation().getWarehouse().getWarehouseName() != null)
              this.locationsWarehousesSearchResult.put(wloc.getLocationName() + " :: " + wi.getWarehouseLocation().getWarehouse().getWarehouseName(), wloc.getLocationId() + "-" + wi.getWarehouseLocation().getWarehouse().getWarehouseId());
            else
              this.locationsWarehousesSearchResult.put(wloc.getLocationName() + " ::", wloc.getLocationId() + "-");
          }
          else {
            this.locationsWarehousesSearchResult.put(wloc.getLocationName() + " ::", wloc.getLocationId() + "-");
          }
        }
        this.locationsWarehousesSearchResult = sortByComparator(this.locationsWarehousesSearchResult);
      }
    }
    catch (Exception e)
    {
      WarehouseLocation warehouseLocation;
      e.printStackTrace();
    }
    return "success";
  }

  public String getAllLocationsForWarehouse()
  {
    log.debug("---------------Inside.-------getAllLocationsForWarehouse()------");
    String strwhkey2 = null;
    String strwhkey = this.request.getParameter("toWareHouseKey");
    if (strwhkey.equals(""))
    {
      strwhkey2 = this.request.getParameter("fromlocation");
      strwhkey2 = strwhkey2.substring(strwhkey2.indexOf(45) + 1);
    }

    try
    {
      WarehouseLocation warehouseLocation = getWarehouseLocation();
      if ((strwhkey != null) && (strwhkey.trim().length() > 0))
        warehouseLocation.setWarehouseId(Long.valueOf(strwhkey).longValue());
      else if ((strwhkey2 != null) && (strwhkey2.trim().length() > 0))
        warehouseLocation.setWarehouseId(Long.valueOf(strwhkey2).longValue());
      else
        warehouseLocation.setWarehouseId(0L);
      this.locationWHSearchResult.put("-----Select Location-----", Long.valueOf(-1L));
      this.warehouseLocationList = this.warehouseManagerService.getAllLocationsForWarehouse(warehouseLocation);
      for (WarehouseLocation wl : this.warehouseLocationList)
      {
        this.locationWHSearchResult.put(wl.getLocationName(), Long.valueOf(wl.getLocationId()));
      }
      this.SortedlocationWHSearchResult = sortByComparator(this.locationWHSearchResult);
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    return "success";
  }

  public String getAllWIPLocationsForWarehouse()
  {
    log.debug("---------------Inside.-------getAllLocationsForWarehouse()------");
    String strwhid = this.request.getParameter("wh_id");

    return "success";
  }

  private static Map sortByComparator(Map unsortMap)
  {
    List list = new LinkedList(unsortMap.entrySet());

    Collections.sort(list, new Comparator() {
      public int compare(Object o1, Object o2) {
        return ((Comparable)((Map.Entry)(Map.Entry)o1).getValue()).compareTo(((Map.Entry)(Map.Entry)o2).getValue());
      }
    });
    Map sortedMap = new LinkedHashMap();
    for (Iterator it = list.iterator(); it.hasNext(); ) {
      Map.Entry entry = (Map.Entry)it.next();
      sortedMap.put(entry.getKey(), entry.getValue());
    }
    return sortedMap;
  }

  public String goTolistLocationsByWarehouse()
  {
    String strWID = this.request.getParameter("wid");
    this.request.setAttribute("WHID", strWID);

    return "success";
  }

  public String getAllBatches()
  {
    log.debug("---------------Inside.-------getAllBatches()------");
    this.warehouseInventoryList = this.warehouseManagerService.getAllBatches();
    for (WarehouseInventory wi : this.warehouseInventoryList)
    {
      this.batchesSearchResult.put(wi.getBatchNum(), Long.valueOf(wi.getLocationId()));
    }
    return "success";
  }

  public Warehouse getWarehouse() {
    Warehouse warehouse = (Warehouse)getSession().get("warehouse");
    if (warehouse == null) {
      warehouse = new Warehouse();
      warehouse.setBusinessId(UserUtil.getMmrUser().getBusinessId());
      setWarehouse(warehouse);
    }
    return warehouse;
  }

  public void setWarehouse(Warehouse warehouse)
  {
    getSession().put("warehouse", warehouse);
  }

  public WarehouseLocation getWarehouseLocation()
  {
    WarehouseLocation warehouseLocation = (WarehouseLocation)getSession().get("warehouseLocation");
    if (warehouseLocation == null) {
      warehouseLocation = new WarehouseLocation();
      setWarehouseLocation(warehouseLocation);
    }
    return warehouseLocation;
  }

  public void setWarehouseLocation(WarehouseLocation warehouseLocation)
  {
    getSession().put("warehouseLocation", warehouseLocation);
  }

  public WarehouseLocationType getWarehouseLocationType() {
    WarehouseLocationType warehouseLocationType = (WarehouseLocationType)getSession().get("warehouseLocationType");
    if (warehouseLocationType == null) {
      warehouseLocationType = new WarehouseLocationType();
      setWarehouseLocationType(warehouseLocationType);
    }
    return warehouseLocationType;
  }

  public void setWarehouseLocationType(WarehouseLocationType warehouseLocationType)
  {
    getSession().put("warehouseLocationType", warehouseLocationType);
  }

  public WarehouseInventory getWarehouseInventory() {
    WarehouseInventory warehouseInventory = (WarehouseInventory)getSession().get("warehouseInventory");
    if (warehouseInventory == null) {
      warehouseInventory = new WarehouseInventory();
      setWarehouseInventory(warehouseInventory);
    }
    return warehouseInventory;
  }

  public void setWarehouseInventory(WarehouseInventory warehouseInventory)
  {
    getSession().put("warehouseInventory", warehouseInventory);
  }

  public LoggedEvent getLoggedEvent() {
    LoggedEvent loggedEvent = (LoggedEvent)getSession().get("loggedEvent");
    if (loggedEvent == null) {
      loggedEvent = new LoggedEvent();
      setLoggedEvent(loggedEvent);
    }
    return loggedEvent;
  }

  public void setLoggedEvent(LoggedEvent loggedEvent) {
    getSession().put("loggedEvent", loggedEvent);
  }

  public AddressManager getAddressService() {
    return this.addressService;
  }

  public void setAddressService(AddressManager addressService) {
    this.addressService = addressService;
  }

  public ProductManager getProductService() {
    return this.productService;
  }

  public void setProductService(ProductManager productService) {
    this.productService = productService;
  }

  public List<Warehouse> getWarehouseList() {
    return this.warehouseList;
  }

  public void setWarehouseList(List<Warehouse> warehouseList) {
    this.warehouseList = warehouseList;
  }

  public List<Products> getProductsList() {
    return this.productsList;
  }

  public void setProductsList(List<Products> productsList) {
    this.productsList = productsList;
  }

  public List<ProductLine> getProductLineList() {
    return this.productLineList;
  }

  public void setProductLineList(List<ProductLine> productLineList) {
    this.productLineList = productLineList;
  }

  public List<WarehouseLocation> getWarehouseLocationAisleList() {
    return this.warehouseLocationAisleList;
  }

  public void setWarehouseLocationAisleList(List<WarehouseLocation> warehouseLocationAisleList)
  {
    this.warehouseLocationAisleList = warehouseLocationAisleList;
  }

  public List<WarehouseLocation> getWarehouseLocationList() {
    return this.warehouseLocationList;
  }

  public void setWarehouseLocationList(List<WarehouseLocation> warehouseLocationList)
  {
    this.warehouseLocationList = warehouseLocationList;
  }

  public List<WarehouseLocationType> getWarehouseLocationTypeList() {
    return this.warehouseLocationTypeList;
  }

  public void setWarehouseLocationTypeList(List<WarehouseLocationType> warehouseLocationTypeList)
  {
    this.warehouseLocationTypeList = warehouseLocationTypeList;
  }

  public List<WarehouseInventory> getWarehouseInventoryList() {
    return this.warehouseInventoryList;
  }

  public void setWarehouseInventoryList(List<WarehouseInventory> warehouseInventoryList)
  {
    this.warehouseInventoryList = warehouseInventoryList;
  }

  public Map<String, Long> getLocationsSearchResult() {
    return this.locationsSearchResult;
  }

  public void setLocationsSearchResult(Map<String, Long> locationsSearchResult) {
    this.locationsSearchResult = locationsSearchResult;
  }

  public List<WarehouseInventory> getWarehouseInventoryCheckList() {
    return this.warehouseInventoryCheckList;
  }

  public void setWarehouseInventoryCheckList(List<WarehouseInventory> warehouseInventoryCheckList)
  {
    this.warehouseInventoryCheckList = warehouseInventoryCheckList;
  }

  public List<WarehouseLocation> getWarehouseProductLocationList() {
    return this.warehouseProductLocationList;
  }

  public void setWarehouseProductLocationList(List<WarehouseLocation> warehouseProductLocationList)
  {
    this.warehouseProductLocationList = warehouseProductLocationList;
  }

  public Map<String, Long> getBatchesSearchResult() {
    return this.batchesSearchResult;
  }

  public void setBatchesSearchResult(Map<String, Long> batchesSearchResult) {
    this.batchesSearchResult = batchesSearchResult;
  }

  public LoggedEventService getLoggedEventService() {
    return this.loggedEventService;
  }

  public void setLoggedEventService(LoggedEventService loggedEventService) {
    this.loggedEventService = loggedEventService;
  }

  public List<LoggedEvent> getLoggedList() {
    return this.loggedList;
  }

  public void setLoggedList(List<LoggedEvent> loggedList) {
    this.loggedList = loggedList;
  }

  public Map<String, Long> getLocationWHSearchResult() {
    return this.locationWHSearchResult;
  }

  public void setLocationWHSearchResult(Map<String, Long> locationWHSearchResult) {
    this.locationWHSearchResult = locationWHSearchResult;
  }

  public Map<String, Long> getWHSearchResult() {
    return this.WHSearchResult;
  }

  public void setWHSearchResult(Map<String, Long> searchResult) {
    this.WHSearchResult = searchResult;
  }

  public Map<String, String> getLocationsWarehousesSearchResult() {
    return this.locationsWarehousesSearchResult;
  }

  public void setLocationsWarehousesSearchResult(Map<String, String> locationsWarehousesSearchResult)
  {
    this.locationsWarehousesSearchResult = locationsWarehousesSearchResult;
  }

  public Map<String, Long> getSortedlocationWHSearchResult() {
    return this.SortedlocationWHSearchResult;
  }

  public void setSortedlocationWHSearchResult(Map<String, Long> sortedlocationWHSearchResult)
  {
    this.SortedlocationWHSearchResult = sortedlocationWHSearchResult;
  }

  public List<WarehouseSummary> getWarehouseInventorySummaryList() {
    return this.warehouseInventorySummaryList;
  }

  public void setWarehouseInventorySummaryList(List<WarehouseSummary> warehouseInventorySummaryList)
  {
    this.warehouseInventorySummaryList = warehouseInventorySummaryList;
  }

  public List<Map<Long, String>> getListWIPLocations() {
    return this.ListWIPLocations;
  }

  public void setListWIPLocations(List<Map<Long, String>> listWIPLocations) {
    this.ListWIPLocations = listWIPLocations;
  }
}