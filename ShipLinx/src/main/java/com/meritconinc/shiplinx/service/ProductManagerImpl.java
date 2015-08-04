package com.meritconinc.shiplinx.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.struts2.components.ActionError;

import com.meritconinc.mmr.utilities.MessageUtil;
import com.meritconinc.mmr.utilities.security.UserUtil;
import com.meritconinc.shiplinx.action.BaseAction;
import com.meritconinc.shiplinx.dao.LoggedEventDAO;
import com.meritconinc.shiplinx.dao.ProductManagerDAO;
import com.meritconinc.shiplinx.dao.ShippingDAO;
import com.meritconinc.shiplinx.model.LoggedEvent;
import com.meritconinc.shiplinx.model.PackageTypes;
import com.meritconinc.shiplinx.model.ProductLine;
import com.meritconinc.shiplinx.model.Products;
import com.meritconinc.shiplinx.model.ShippingOrder;
import com.meritconinc.shiplinx.utils.ShiplinxConstants;

import com.meritconinc.shiplinx.api.Util.ShopifyUtil;
import com.meritconinc.shiplinx.api.model.ProductElement;
import com.meritconinc.shiplinx.api.model.ShopifyProduct;
import com.meritconinc.shiplinx.api.model.VariantElement;
import com.meritconinc.shiplinx.dao.AddressDAO;
import com.meritconinc.mmr.utilities.MmrBeanLocator;
import com.meritconinc.shiplinx.model.ProductPackageMap;
import com.meritconinc.shiplinx.model.Address;
import java.sql.SQLException;

public class ProductManagerImpl extends BaseAction implements ProductManager {
	
	private static final Logger log = LogManager.getLogger(ShippingServiceImpl.class);
	
	private ProductManagerDAO productManagerDAO;
	
	private LoggedEventDAO loggedEventDAO;
	
	private ShippingDAO shippingDAO;

	public ProductManagerDAO getProductManagerDAO() {
		return productManagerDAO;
	}

	public void setProductManagerDAO(ProductManagerDAO productManagerDAO) {
		this.productManagerDAO = productManagerDAO;
	}
	
	public LoggedEventDAO getLoggedEventDAO() {
		return loggedEventDAO;
	} 

	public void setLoggedEventDAO(LoggedEventDAO loggedEventDAO) {
		this.loggedEventDAO = loggedEventDAO;
	}

	public List<Products> searchProducts(Products p,boolean both){
		return productManagerDAO.searchProductList(p,both);
	}
	
	public List<Products> getProductsList(Products product){
		return productManagerDAO.getListOfProducts(product);
	}
	
	public ShippingDAO getShippingDAO() {
		return shippingDAO;
	}

	public void setShippingDAO(ShippingDAO shippingDAO) {
		this.shippingDAO = shippingDAO;
	}

	public void deleteProducts(Products product){
		productManagerDAO.deleteProducts(product);
	}
	
	public void addOrUpdate(Products product, boolean add)
	{
		productManagerDAO.addOrUpdate(product,add);
	}
	
	public List<ProductLine> getProductLineList(ProductLine productLine)
	{
		return productManagerDAO.getProductLineList(productLine);
	}
	
	public void addOrUpdateProductLine(ProductLine productLine, boolean add)
	{
		productManagerDAO.addOrUpdateProductLine(productLine, add);
	}
	
	public List<ProductLine> getProductLineListById(ProductLine productLine)
	{
		return productManagerDAO.getProductLineById(productLine);
	}
	
	public void deleteProductLine(ProductLine productLine)
	{
		productManagerDAO.deleteProductLine(productLine);
	}
	
	public List<Products> getProductsByProductLineId(Products products)
	{
		return productManagerDAO.getProductsByProductLineId(products);
	}
	
	public List<Products> getProductsByOrderAndCustomer(long orderId, long customerId)
	{
		return productManagerDAO.getProductsByOrderAndCustomer(orderId, customerId);	
	}
	
	public Products getProductByProductIdAndCustomerId(long productId, long customerId)
	{
		return productManagerDAO.getProductByProductIdAndCustomerId(productId, customerId);
	}
	
	public boolean updateProductsCounts(ShippingOrder shippingOrder, int status)
	{
		boolean boolret = true;
		String systemLog = null;
		String errorLog = null;
		String locale = UserUtil.getMmrUser().getLocale();
		int ipreviousInqueueCount = 0;
		int ipreviousBackOrderCount = 0;
		int ipreviousWIPCount = 0;
		int icount = 0;
		try 
		{	
			for(Products products : shippingOrder.getWarehouseProducts())
			{
				if(status==ShiplinxConstants.STATUS_SENT_TOWAREHOUSE)// If new warehouse order is created
				{
					//previous inqueue count
					ipreviousInqueueCount = products.getInqueueCount();
					//previous backorder count
					ipreviousBackOrderCount = products.getBackOrderCount();
					//Update the in-queue count and the Back-order count
					products.setInqueueCount(Integer.parseInt(String.valueOf(products.getInqueueCount() + products.getOrderedQuantity())));
					//calculate the back order count
					calculateBackOrder(products);
					//log messages
					systemLog = MessageUtil.getMessage("log.update.product.count.for.order.creation", locale);
					systemLog = new String(systemLog.replaceAll("%OLD_IQ", ipreviousInqueueCount+""));
					systemLog = new String(systemLog.replaceAll("%NEW_IQ", products.getInqueueCount()+""));
					systemLog = new String(systemLog.replaceAll("%OLD_BCK", ipreviousBackOrderCount+""));
					systemLog = new String(systemLog.replaceAll("%NEW_BCK", products.getBackOrderCount()+""));
					systemLog = new String(systemLog.replaceAll("%NEW_ORDER", shippingOrder.getId()+""));
					systemLog = new String(systemLog.replaceAll("%DATETIME", "'"+new Date()+"'"));
					systemLog = new String(systemLog.replaceAll("%PRODUCT", "'"+products.getProductName()+"'"));
					//logUpdateProductCounts
					logUpdateAction(products.getProductId(), systemLog, "");
					
				}
				else if(status==ShiplinxConstants.STATUS_CANCELLED) //If canceled
				{
					//previous inqueue count
					ipreviousInqueueCount = products.getInqueueCount();
					//previous backorder count
					ipreviousBackOrderCount = products.getBackOrderCount();
					//update the in-queue count to the value decreased by the no of products requested.
					//if in-queue count is 0, then directly set in-queue count to 0, there is no need to deduct.
					if(products.getInqueueCount()==0)
						products.setInqueueCount(0);
					else
						products.setInqueueCount(Integer.parseInt(String.valueOf(products.getInqueueCount()-products.getOrderedQuantity())));
					//calculate the back-order count.
					calculateBackOrder(products);
					//After the implementation of the Ordered Quantity of the product.
					//Setting the ordered Quantity to 0 as fulfilled is the ordered_quantity value.
					products.setOrderedQuantity(0);
					//Update fulfilled in order_product
					shippingDAO.updateOrderFulfilled(products, shippingOrder.getId());
					
					//log messages
					systemLog = MessageUtil.getMessage("log.update.product.count.for.order.cancellation", locale);
					systemLog = new String(systemLog.replaceAll("%OLD_IQ", ipreviousInqueueCount+""));
					systemLog = new String(systemLog.replaceAll("%NEW_IQ", products.getInqueueCount()+""));
					systemLog = new String(systemLog.replaceAll("%OLD_BCK", ipreviousBackOrderCount+""));
					systemLog = new String(systemLog.replaceAll("%NEW_BCK", products.getBackOrderCount()+""));
					systemLog = new String(systemLog.replaceAll("%NEW_ORDER", shippingOrder.getId()+""));
					systemLog = new String(systemLog.replaceAll("%DATETIME", "'"+new Date()+"'"));
					systemLog = new String(systemLog.replaceAll("%PRODUCT", "'"+products.getProductName()+"'"));
					//logUpdateProductCounts
					logUpdateAction(products.getProductId(), systemLog, "");
				}
				else if(status==ShiplinxConstants.STATUS_SHIPPED)// If Shipped
				{
					//If the WIP count contains the no of products requested, only then proceed
					if(products.getWipCount()>=products.getOrderedQuantity())
					{
						//previous inqueue count
						ipreviousInqueueCount = products.getInqueueCount();
						//previous backorder count
						ipreviousWIPCount = products.getWipCount();
						//deduct the quantity from the current wip count
						//if wip count is 0, then directly set wip count to 0, there is no need to deduct.
						if(products.getWipCount()==0)
							products.setWipCount(0);
						else
							products.setWipCount(Integer.parseInt(String.valueOf(products.getWipCount()-products.getOrderedQuantity())));
						//deduct the quantity from the current inqueue count
						//if in-queue count is 0, then directly set in-queue count to 0, there is no need to deduct.
						if(products.getInqueueCount()==0)
							products.setInqueueCount(0);
						else
							products.setInqueueCount(Integer.parseInt(String.valueOf(products.getInqueueCount()-products.getOrderedQuantity())));
						
						//Update fulfilled in order_product
						shippingDAO.updateOrderFulfilled(products, shippingOrder.getId());
						//log Messages
						systemLog = MessageUtil.getMessage("log.update.product.count.for.order.shipped", locale);
						systemLog = new String(systemLog.replaceAll("%OLD_IQ", ipreviousInqueueCount+""));
						systemLog = new String(systemLog.replaceAll("%NEW_IQ", products.getInqueueCount()+""));
						systemLog = new String(systemLog.replaceAll("%OLD_WIP", ipreviousWIPCount+""));
						systemLog = new String(systemLog.replaceAll("%NEW_WIP", products.getWipCount()+""));
						systemLog = new String(systemLog.replaceAll("%NEW_ORDER", shippingOrder.getId()+""));
						systemLog = new String(systemLog.replaceAll("%DATETIME", "'"+new Date()+"'"));
						systemLog = new String(systemLog.replaceAll("%PRODUCT", "'"+products.getProductName()+"'"));
						//logUpdateProductCounts
						logUpdateAction(products.getProductId(), systemLog, "");
					}
					else
					{
						//error message displaying which product(s) are not sufficient in WIP to continue with the Shipment
						errorLog = MessageUtil.getMessage("error.log.update.product.count", locale);
						errorLog = new String(errorLog.replaceAll("%PRODUCT", products.getProductName()));
						addActionError(errorLog);
						return false;
					}
				}
				//update the product table if everything is ok
				if(boolret)
				productManagerDAO.addOrUpdate(products, false);
				icount++;
			}
		}
		catch (Exception e) 
		{
			log.error("Exception occured while updating the counts of the product during order shipment");
			e.printStackTrace();
			boolret= false;
		}
		
		return boolret;
	}
	
	public List<PackageTypes> searchPackageTypes(PackageTypes packageTypes)
	{
		List<PackageTypes> packageTypeList = new ArrayList<PackageTypes>();
		try {
			
			packageTypeList = productManagerDAO.searchPackageTypes(packageTypes);
			
		} catch (Exception e) {
			log.error("Exception occured in searchPackageTypes of ProductManagerImpl class",e);
		}
		
		return packageTypeList;
	}
	
	public void addOrUpdatePackageTypes(PackageTypes packageTypes, boolean add)
	{
		try {
			productManagerDAO.addOrUpdatePackageTypes(packageTypes, add);
		} catch (Exception e) {
			log.error("Exception occured in addOrUpdatePackageTypes method-",e);
		}
	}
	
	public List<PackageTypes> fetchAPackageById(long packageTypeId)
	{
		List<PackageTypes> ptlist = new ArrayList<PackageTypes>();
		try {
			ptlist = productManagerDAO.fetchAPackageById(packageTypeId);
			
		} catch (Exception e) {
			log.error("Exception occured in fetchAPackageById method-",e);
			return null;
		}
		return ptlist;
	}
	
	public boolean deletePackageType(long packageTypeId)
	{
		boolean boolret = false;
		try {
			boolret = productManagerDAO.deletePackageType(packageTypeId);
		} catch (Exception e) {
			log.error("Exception occured in deletePackageType method-",e);
		}
		return boolret;
	}
	
	private void calculateBackOrder(Products products)
	{
		//Get the total count = Inventorycount + wipcount + quarantinecount.
		int totalCount = Integer.parseInt(String.valueOf(products.getLocationCount()+products.getWipCount()+products.getQuarantineCount()));
		//Get the backOrder count.
		int backOrderCount = totalCount - products.getInqueueCount();
		//If backorder count is greater than 0, set it to 0, else take the absolute value of it.
		if(backOrderCount >= 0)
			backOrderCount=0;
		else if(backOrderCount < 0)
			backOrderCount = Math.abs(backOrderCount);
		products.setBackOrderCount(backOrderCount);
	}
	
	private void logUpdateAction(long productId,String systemLog, String Comment)
	{
		//Logged Event Initialization
		LoggedEvent loggedEvent = new LoggedEvent();
		// Set the loggedEvent Details
		loggedEvent.setEntityType(ShiplinxConstants.ENTITY_TYPE_PRODUCT_VALUE); //Entity - Product of the Warehouse Order
		loggedEvent.setEntityId(productId);//Product ID
		loggedEvent.setEventDateTime(new Date()); //Current Date
		loggedEvent.setEventUsername(UserUtil.getMmrUser().getUsername()); //Current User
		loggedEvent.setSystemLog(systemLog); //System generated Message Log
		loggedEvent.setMessage(Comment);
		//Log the Event into the DB
		loggedEventDAO.addLoggedEventInfo(loggedEvent); //Added Event Log into DB
		
	}
	

 @Override
 public void sycnShopifyProducts(ShopifyProduct products) {
   // TODO Auto-generated method stub
   AddressDAO addressDAO = (AddressDAO) MmrBeanLocator.getInstance()
       .findBean("addressDAO");
   Address address=new Address();
   address.setCustomerId(products.getCustomerId());
   List<Address> addressList=addressDAO.searchAddresses(address);
   String country=null;
   if(addressList!=null && addressList.size()>0){
     country=addressList.get(0).getCountryName();
   }
   List<Products> productsList=new ArrayList<Products>();
   List<Products> ptsListtoUpdate=new ArrayList<Products>();
   List<Products> ptsListtoAdd=new ArrayList<Products>();
   List<Products> ptsListtoDelete=new ArrayList<Products>();
   List<Long> productIds=new ArrayList<Long>();
   List<Long> productIdsd=new ArrayList<Long>();
   List<Products> ptsListForCustomer=new ArrayList<Products>();
    ptsListForCustomer=getproductsByCustomer(products.getCustomerId());
   if(products!=null && products.getProducts()!=null&& products.getProducts().size()>0){
        for(ProductElement pdt:products.getProducts()){
          Products pt=new Products();
          pt.setProductName(pdt.getTitle());
          pt.setProductDescription(pdt.getBody_html());
          for(VariantElement varient:pdt.getVariants()){
            pt.setUnitPrice(Double.parseDouble(varient.getPrice()));
            //pt.setWeightUnit(varient.getWeight_unit());
            float weight=getWeightFromShopify(varient.getWeight_unit(),varient.getWeight().toString());
            pt.setUnitmeasureId(1);//defaulted to pounds
            pt.setWeight(weight);
            pt.setCustomerId(products.getCustomerId());
            pt.setSkuId(varient.getSku());
            pt.setProductCode(pt.getSkuId());
            pt.setReference1(varient.getId().toString());
            pt.setReference1Name("SHOPIFY_PRODUCT_ID");
            pt.setProductCode(pdt.getId().toString());
            pt.setProductHarmonizedCode(pt.getSkuId());
            if(country!=null)
        pt.setOriginCountry(country);
            productsList.add(pt);
          }
        }
         for(Products pts1:productsList){
           Products p=productManagerDAO.getProductBySKUorRef1(pts1.getSkuId(),pts1.getReference1(),products.getCustomerId());
           if(p!=null){
             ptsListtoUpdate.add(pts1);
             productIds.add(p.getProductId());
           }else{
             ptsListtoAdd.add(pts1);
           }
         }
         for(Products rmp:ptsListForCustomer){
           productIdsd.add(rmp.getProductId());
         }
           productIdsd.removeAll(productIds);
           //add new products
         if(productIdsd.size()>0){
            if(productIdsd.size()==1) 
           addActionMessage(productIds.size()+" Product Deleted");
            else
              addActionMessage(productIds.size()+" Products Deleted");
         }
         if(ptsListtoUpdate.size()>0){
           if(ptsListtoUpdate.size()==1)
           addActionMessage(ptsListtoUpdate.size()+" Product Updated");
           addActionMessage(ptsListtoUpdate.size()+" Products Updated");
              }
         if(ptsListtoAdd.size()>0){
           if(ptsListtoAdd.size()==1)
           addActionMessage(ptsListtoUpdate.size()+" Product Added");
           addActionMessage(ptsListtoUpdate.size()+" Products Added");
              }
          try {
       sycnProductsInBatch(ptsListtoAdd,ptsListtoUpdate,productIdsd);
     } catch (SQLException e) {
       // TODO Auto-generated catch block
       e.printStackTrace();
     }
   
   }
   
 }

 

 private float getWeightFromShopify(String weight_unit, String weight) {
   // TODO Auto-generated method stub
   float wt=0f;
   if(weight_unit!=null && weight!=null){
     if(weight_unit.contains("lb")){
       wt=Float.parseFloat(weight);
       return wt;
     }else if(weight_unit.contains("g")){
     wt=(float) ShopifyUtil.gramToLBS(Double.parseDouble(weight));
     }else if(weight_unit.contains("kg")){
       wt=(float) ShopifyUtil.kgsToLBS(Double.parseDouble(weight));
     }else if(weight_unit.contains("oz")){
       wt=(float) ShopifyUtil.ozToLBS(Double.parseDouble(weight));
     }
     
   }
   return wt;
 }

 private void sycnProductsInBatch(List<Products> ptsListtoAdd,
     List<Products> ptsListtoUpdate, List<Long> productIdsd) throws SQLException {
   // TODO Auto-generated method stub
   productManagerDAO.sycnProductsInBatch(ptsListtoAdd,
       ptsListtoUpdate,  productIdsd);
   
 }

 private List<Products> getproductsByCustomer(Long customerId) {
   // TODO Auto-generated method stub
   Products ps=new Products();
   ps.setCustomerId(customerId);
   
   return getProductsList(ps);
 }

 @Override
 public List<ProductPackageMap> searchProductPackageMap(
     ProductPackageMap packagemap) {
   // TODO Auto-generated method stub
   
   List<ProductPackageMap> prpackList= productManagerDAO.searchProductPackageMap(packagemap);
   return prpackList;
 }

 @Override
 public void addPackageMap(ProductPackageMap productPackageMap) {
   // TODO Auto-generated method stub
   productManagerDAO.addPackageMap(productPackageMap);
 }

 @Override
 public ProductPackageMap getProductPackageMapById(long parseLong) {
   // TODO Auto-generated method stub
   ProductPackageMap ppm=productManagerDAO.getProductPackageMapById(parseLong);
   return ppm;
 }

 @Override
 public void updateProductPackageMap(ProductPackageMap productPackageMap) {
   // TODO Auto-generated method stub
   productManagerDAO.updateProductPackageMap(productPackageMap);
 } 

 @Override
 public void deleteProductPackageMap(long parseLong) {
   // TODO Auto-generated method stub
   productManagerDAO.deleteProductPackageMap(parseLong);
 }

 
	
}
