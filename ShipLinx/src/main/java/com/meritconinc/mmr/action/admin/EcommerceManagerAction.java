package com.meritconinc.mmr.action.admin;
/**
 * 
 * @author selva
 * E-commerce soluship integration ( 5/06/2015)
 *
 */
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.UrlValidator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.meritconinc.mmr.action.BaseAction;
import com.meritconinc.mmr.dao.EcommerceDAO;
import com.meritconinc.mmr.dao.PropertyDAO;
import com.meritconinc.mmr.model.admin.EcommerceConfig;
import com.meritconinc.mmr.model.admin.EcommerceStore;
import com.meritconinc.mmr.utilities.MmrBeanLocator;
import com.meritconinc.mmr.utilities.security.UserUtil;
import com.meritconinc.shiplinx.api.Constant.EcommerceAPIConstant;
import com.meritconinc.shiplinx.api.Util.ShopifyUtil;
import com.meritconinc.shiplinx.model.Customer;
import com.meritconinc.shiplinx.model.Rating;
import com.meritconinc.shiplinx.service.CustomerManager;
import com.meritconinc.shiplinx.utils.ShiplinxConstants;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.Preparable;
import com.soluship.businessfilter.util.BusinessFilterUtil;
import sun.org.mozilla.javascript.internal.EcmaError;


public class EcommerceManagerAction extends BaseAction implements  
ServletRequestAware, ServletResponseAware {
    
	private static final Logger log = LogManager.getLogger(EcommerceManagerAction.class);
	private static final long serialVersionUID = 6460589319204638767L;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private EcommerceStore ecommerceStore;
 	private List<EcommerceStore> ecommerceStoreList;
 	private List<Customer> customerList;
	private  EcommerceDAO eCommerceDAO;
	private CustomerManager customerService;
	private int shipCustomerFlag;
	private int packageMapFlag;
	
	
	private String freeShipLable;
	private String markupLable;
	private Customer customer;
	
	
	
	
	@Override
	public void setServletResponse(HttpServletResponse arg0) {
		// TODO Auto-generated method stub
		this.setResponse(arg0);
	}

	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		this.setRequest(arg0);
	}
 
	

	public EcommerceStore getEcommerceStore() {
		return ecommerceStore;
	}

	public void setEcommerceStore(EcommerceStore ecommerceStore) {
		this.ecommerceStore = ecommerceStore;
	}
	public List<EcommerceStore> getEcommerceStoreList() {
		return ecommerceStoreList;
	}
	public void setEcommerceStoreList(List<EcommerceStore> ecommerceStoreList) {
		this.ecommerceStoreList = ecommerceStoreList;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	public HttpServletResponse getResponse() {
		return response;
	}
	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	public CustomerManager getCustomerService() {
		return customerService;
	}

	public void setCustomerService(CustomerManager customerService) {
		this.customerService = customerService;
	}

 

	/**
	 * list the stores
	 * @return
	 */
	public String listStore(){
		
		
		EcommerceStore st=new EcommerceStore();
		
		if(UserUtil.getMmrUser()!=null && (UserUtil.getMmrUser().getUserRole().equals(ShiplinxConstants.ROLE_SYSADMIN) ||
				UserUtil.getMmrUser().getUserRole().equals(ShiplinxConstants.ROLE_BUSINESSADMIN ))){
			st.setCustomerId(null);//get all ecommerce store for admins
			ecommerceStoreList=eCommerceDAO.listEcommerceStores(st);
		}else if(UserUtil.getMmrUser()!=null && UserUtil.getMmrUser().getUserRole().equals(ShiplinxConstants.ROLE_CUSTOMER_ADMIN)){
			st.setCustomerId(UserUtil.getMmrUser().getCustomerId());
			try {
								customer=customerService.getCustomerInfoByCustomerId(UserUtil.getMmrUser().getCustomerId());
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
			
			ecommerceStoreList=eCommerceDAO.listEcommerceStores(st);
			if(ecommerceStoreList!=null && UserUtil.getMmrUser()!=null && UserUtil.getMmrUser().getUserRole().equals(ShiplinxConstants.ROLE_CUSTOMER_ADMIN) 
												&& ecommerceStoreList.size()>0){
											this.ecommerceStore=ecommerceStoreList.get(0);
											Long storeId=ecommerceStore.getEcommerceStoreId();
											getSession().put("storeId",storeId.toString());
									if(storeId!=null){
										 editStore();
										 return "success1";
									}
								}
		}
		return SUCCESS;
	}
   
	public String newStore(){
		ecommerceStore=new EcommerceStore();
		ecommerceStore.setCancelShipmentUrl("https://soluship.com/api/v1/cancelshipment");
				ecommerceStore.setRateServiceUrl("https://soluship.com/api/v1/genericrates");
				ecommerceStore.setCreateShipmentUrl("https://soluship.com/api/v1/createShipment");
		getSession().remove("edit");
		return SUCCESS;
	}
	
	public String saveStore(){
 		 log.debug(" CHECK METHOD IN SAVE STORE ***********   " + request.getParameter("method"));
 		 ecommerceStore=this.getEcommerceStore();
 		 
 	 if (request.getParameter("method").equals("add")) {
 		 boolean isvalid=valiateStore(ecommerceStore);
 		 if(isvalid){
 		 if(shipCustomerFlag==1){
 			 ecommerceStore.setChepest(true);
 		 }else if(shipCustomerFlag==2){
 			 ecommerceStore.setFastest(true);
 		 }else if(shipCustomerFlag==3){
 			 ecommerceStore.setBothService(true);
 		 }
 		if(packageMapFlag==1){
			 ecommerceStore.setPackageMap(true);
		 }else if(packageMapFlag==2){
			 ecommerceStore.setPackageMap(false);
		 } 
 		PropertyDAO propertyDAO = (PropertyDAO) MmrBeanLocator.getInstance()
				.findBean("propertyDAO");
 	    
 		 ecommerceStore.setApiKey(propertyDAO.readProperty("SHOPIFY","SHOPIFY_API_KEY"));
 		 ecommerceStore.setScopes(propertyDAO.readProperty("SHOPIFY","SHOPIFY_REQUEST_SCOPES"));
 		 ecommerceStore.setSharedSceret(propertyDAO.readProperty("SHOPIFY","SHOPIFY_SHARED_SCRECT"));
 		 ecommerceStore.setInstallUrl(propertyDAO.readProperty("SHOPIFY","SHOPIFY_REDIRECT_URL"));
 		 if(UserUtil.getMmrUser()!=null){
 			 ecommerceStore.setCreatedBy(UserUtil.getMmrUser().getUsername());
 		 }
 		 Long businessId = null;
		try {
			businessId = customerService.getCustomerInfoByCustomerId(ecommerceStore.getCustomerId()).getBusinessId();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		 if(businessId==null){
 			businessId=BusinessFilterUtil.setBusinessIdbyUserLevel();
 		 }
 		 
 		 ecommerceStore.setBusinessId(businessId);
 		 if(ecommerceStore.getUrl().charAt(ecommerceStore.getUrl().length()-1)=='/'){
 			 StringBuilder sb=new StringBuilder(ecommerceStore.getUrl());
 			 sb.deleteCharAt(ecommerceStore.getUrl().length()-1);
 			 ecommerceStore.setUrl(sb.toString());
 		 }
 		 eCommerceDAO.addEcommerceStore(ecommerceStore);
 		  addActionMessage("Store Added Succesffuly");
 		 listStore();
 		 }else{
 			ecommerceStore=new EcommerceStore();
 			ecommerceStore.setCancelShipmentUrl("https://soluship.com/api/v1/cancelshipment");
 			 			ecommerceStore.setRateServiceUrl("https://soluship.com/api/v1/genericrates");
 			 			ecommerceStore.setCreateShipmentUrl("https://soluship.com/api/v1/createShipment");
 			
 			getSession().remove("edit");
 			 return INPUT;
 		 }

	} else if (request.getParameter("method").equals("update")) {
		String ecomStoreId=(String) getSession().get("storeId");
		EcommerceStore store=this.getEcommerceStore();
		ecommerceStore=new EcommerceStore();
		 if(shipCustomerFlag==1){
 			 ecommerceStore.setChepest(true);
 			 ecommerceStore.setBothService(false);
 			 ecommerceStore.setFastest(false);
 		 }else if(shipCustomerFlag==2){
 			 ecommerceStore.setFastest(true);
 			 ecommerceStore.setBothService(false);
 			 ecommerceStore.setChepest(false);
 		 }else if(shipCustomerFlag==3){
 			ecommerceStore.setBothService(true);
 			ecommerceStore.setFastest(false);
 			ecommerceStore.setChepest(false);
 		 }
		 if(packageMapFlag==1){
			 ecommerceStore.setPackageMap(true);
		 }else if(packageMapFlag==2){
			 ecommerceStore.setPackageMap(false);
		 } 
		 
		 if(store.isSinglePack()){
			 			 ecommerceStore.setSinglePack(true);
			 		 }
			 		 ecommerceStore.setMaxPackWeight(store.getMaxPackWeight());
			 		 
		 
		if(ecomStoreId!=null){
			ecommerceStore.setEcommerceStoreId(Long.parseLong(ecomStoreId));
		}
		ecommerceStore.setCustomerId(store.getCustomerId());
		 Timestamp updatedAt = new Timestamp(new Date().getTime()); 
 		 ecommerceStore.setUpdatedAt(updatedAt);
		eCommerceDAO.updateEcommerceStore(ecommerceStore);
		
		if(UserUtil.getMmrUser()!=null && UserUtil.getMmrUser().getUserRole().equals(ShiplinxConstants.ROLE_CUSTOMER_ADMIN)){
								boolean b=validateCustomerStore(store);
								if(b){
								 //add free ship details:
									
									 if(store.isFreeshipRequired() && store.getFreeShipType()!=null && store.getFreeShipType()==1){
										 store.setWeight(store.getFlatRate());
										 store.setFlatRate(null);
									 }
									 if(store.getMarkupLevel()!=null && store.getMarkupLevel()==2){
										 store.setMarkupPerc(store.getFlatMarkup());
										 store.setFlatMarkup(null); 
									 }
								
									 store.setEcommerceStoreId(ecommerceStore.getEcommerceStoreId());
									eCommerceDAO.updateFreeShiping(store);
								}else{
									ecommerceStore=store;
									//getSession().remove("edit");
									return INPUT;
								}
						 }
					
		
		
	    if(UserUtil.getMmrUser()!=null &&eCommerceDAO.getEcommerceStoreById(ecommerceStore.getEcommerceStoreId()).getAccessKey()!=null 
	    		&& !UserUtil.getMmrUser().getUserRole().equals(ShiplinxConstants.ROLE_CUSTOMER_ADMIN)){
	    	//UpdateEcommerceService(ecommerceStore.getEcommerceStoreId());
	    }
		addActionMessage("Store Updated Succesffuly");
		getSession().remove("edit");
		getSession().remove("storeId");
		 return listStore();
	}
		return SUCCESS;
	}

	
	
	
	

	private boolean validateCustomerStore(EcommerceStore ecommerceStore2) {
				// TODO Auto-generated method stub
				int i=0;
		 			if(ecommerceStore2!=null){
		 				if(ecommerceStore2.isFreeshipRequired()){
					if(ecommerceStore2.getFlatRate()!=null && ecommerceStore2.getFlatRate()<0  && ecommerceStore2.getFreeShipType()==1){
						freeShipLable="Weight(lb)";
						addActionError("Invalid Value for Weight(lb).");
						i++;
					}else if(ecommerceStore2.getFlatRate()!=null && ecommerceStore2.getFlatRate()<0   && ecommerceStore2.getFreeShipType()==2){
						freeShipLable="Cost ($)";
						addActionError("Invalid Value for Cost ($).");
						i++;
					}  
					
					if(ecommerceStore2.getFlatRate()==null && ecommerceStore2.getFlatRate()<0  && ecommerceStore2.getFreeShipType()!=null &&ecommerceStore2.getFreeShipType()==1){
						freeShipLable="Weight (lb)";
						addActionError("Please Enter  Value for Weight (lb).");
						i++;
					}else if(ecommerceStore2.getFlatRate()==null && ecommerceStore2.getFlatRate()<0  &&ecommerceStore2.getFreeShipType()!=null && ecommerceStore2.getFreeShipType()==1){
						freeShipLable="Cost ($)";
						addActionError("Please Enter  Value for Cost ($).");
						i++;
					} 
					
					
					
		 		}
					if(ecommerceStore2.getFlatMarkup()!=null && ecommerceStore2.getFlatMarkup()<0  && ecommerceStore2.getMarkupLevel()==1){
						addActionError("Enter invalid Value for Flat Rate ($).");
						i++;
					}else if(ecommerceStore2.getFlatMarkup() !=null && ecommerceStore2.getFlatMarkup()<0   && ecommerceStore2.getMarkupLevel()==2){
						addActionError("Enter invalid Value for Percentage (%).");
						i++;
					}
					
					if(ecommerceStore2.getFlatMarkup()==null && ecommerceStore2.getFlatMarkup()<0  &&  ecommerceStore2.getMarkupLevel()==1){
						addActionError("Please Enter invalid Value for FlatRate ($).");
						i++;
					}else if(ecommerceStore2.getFlatMarkup()==null && ecommerceStore2.getFlatMarkup()<0  &&  ecommerceStore2.getMarkupLevel()==2){
						addActionError("Please Enter invalid Value for Percentage (%).");
						i++;
					}
				}
				if(i==0){
					return true;
				}
				
				if(ecommerceStore2.getMarkupLevel()==1){
					markupLable="Flat Rate ($)";
					
				}else{
					markupLable="Percentage (%)";
				}
				return false;
		 	}




	
	
	/**
	 * validate store details
	 * @param ecommerceStore2
	 * @return
	 */
	private boolean valiateStore(EcommerceStore ecommerceStore2) {
		// TODO Auto-generated method stub
		PropertyDAO propertyDAO = (PropertyDAO) MmrBeanLocator.getInstance()
				.findBean("propertyDAO");
 	    
		int i=0;
		if(ecommerceStore2.getCustomerId()==null ||ecommerceStore2.getCustomerId()==-1){
		   addActionError("Please Select Any Customer");
		   i++;
		}
			
		if(ecommerceStore2.getUrl()==null ||ecommerceStore2.getUrl().equals("")){
			addActionError("Rate Service Url is Requiered.");
			i++;
		}else if(ecommerceStore2.getUrl()!=null && !isvalidUrl(ecommerceStore2.getUrl())){
			addActionError("Rate Service Url is invalid. (https://)");
			i++;
		}
		if(ecommerceStore2.getRateServiceUrl()==null || ecommerceStore2.getRateServiceUrl().equals("")){
			addActionError("Rate Service Url is Requiered.");
			i++;
		}else if(ecommerceStore2.getRateServiceUrl()!=null && !isvalidUrl(ecommerceStore2.getRateServiceUrl())){
			addActionError("Rate Service Url is invalid. (https://)");
			i++;
		}
		
		if(ecommerceStore2.getCreateShipmentUrl()==null || ecommerceStore2.getCreateShipmentUrl().equals("")){
			addActionError("Create Shipment Url is Requiered.");
			i++;
		}else if(ecommerceStore2.getCreateShipmentUrl()!=null && !isvalidUrl(ecommerceStore2.getCreateShipmentUrl())){
			addActionError("Create Shipment Url is invalid. (https://)");
			i++;
		}
		if(ecommerceStore2.getCancelShipmentUrl()==null || ecommerceStore2.getCancelShipmentUrl().equals("") ){
			addActionError("Create Shipment Url is Requiered.");
			i++;
		}else if(ecommerceStore2.getCancelShipmentUrl()!=null && !isvalidUrl(ecommerceStore2.getCancelShipmentUrl())){
			addActionError("Create Shipment Url is invalid. (https://)");
			i++;
		}
		
		if(shipCustomerFlag==0){
			addActionError("Select Shipping Service.");
			i++;
		}
		if(packageMapFlag==0){
			addActionError("Select Package Map.");
			i++;
		}
		
		if(propertyDAO.readProperty("SHOPIFY","SHOPIFY_API_KEY")==null
				||propertyDAO.readProperty("SHOPIFY","SHOPIFY_REQUEST_SCOPES")==null
				||propertyDAO.readProperty("SHOPIFY","SHOPIFY_SHARED_SCRECT")==null
				||propertyDAO.readProperty("SHOPIFY","SHOPIFY_REDIRECT_URL")==null){
			addActionError("Property Missing Error.");
			i++;
		}
		
		
		if(ecommerceStore2.isSinglePack()){
						if(ecommerceStore2.getMaxPackWeight()==null){
							addActionError("Enter Valid Maximum Single Package Weight.");
							i++;
						}else if(ecommerceStore2.getMaxPackWeight().equals("")
								|| ecommerceStore2.getMaxPackWeight()<=0){
							
							addActionError("Enter Valid Maximum Single Package Weight.");
							i++;
						}
					}
	 
		
		if(i>0){
			return false;
		}
		return true;
	}

	private boolean isvalidUrl(String url) {
		// TODO Auto-generated method stub
	    
		String[] schemes = {"http","https"}; // DEFAULT schemes = "http", "https", "ftp"
		UrlValidator urlValidator = new UrlValidator(schemes);
		if (urlValidator.isValid(url)) {
			return true;
		} else {
		   return false;
		}
		 
	}

	/**
	 * updating the service url to
	 * Ecommerce platforms
	 * @param ecomId
	 */
	private void UpdateEcommerceService(Long ecomId) {
		// TODO Auto-generated method stub
		EcommerceStore ecommerceStore2=eCommerceDAO.getEcommerceStoreById(ecomId);
		ShopifyUtil.updateCreateShipmentService(ecommerceStore2);
		String rateServiceId=ShopifyUtil.updateRateService(ecommerceStore2);
		ShopifyUtil.updateCancelShipmentService(ecommerceStore2);
		ecommerceStore2.setRateServiceId(rateServiceId);
		ecommerceStore2.setWebhookUnistallId(null);
		eCommerceDAO.UpdateServices(ecommerceStore2);
		
	}

	public EcommerceDAO geteCommerceDAO() {
		return eCommerceDAO;
	}

	public void seteCommerceDAO(EcommerceDAO eCommerceDAO) {
		this.eCommerceDAO = eCommerceDAO;
	}
	
	@SuppressWarnings("unchecked")
	public String editStore(){
		
		String storeId=request.getParameter("storeId");
		if(storeId==null){
									storeId=(String) getSession().get("storeId");
								}
		getSession().put("edit","true");
		getSession().put("storeId",storeId);
		if(storeId!=null){
			ecommerceStore=eCommerceDAO.getEcommerceStoreById(Long.parseLong(storeId));
			if(ecommerceStore!=null){
				if(ecommerceStore.isBothService()){
					shipCustomerFlag=3;
					
				}else if(ecommerceStore.isChepest()){
					shipCustomerFlag=1;
				}else if(ecommerceStore.isFastest()){
					shipCustomerFlag=2;
				}
				
				if(ecommerceStore.isPackageMap()){
					packageMapFlag=1;

				}else{
					packageMapFlag=2;
				}
				

			}
						if(ecommerceStore.getFreeShipType()!=null && ecommerceStore.getFreeShipType()==2){
							freeShipLable="Cost $";
						}else{
						freeShipLable="Weight (lb)";
						}
						if(ecommerceStore.getMarkupLevel()!=null && ecommerceStore.getMarkupLevel()==2){
							
							markupLable="Percentage (%)";	
						}else{
							markupLable="Flat Rate ($)";
						}
						 if(ecommerceStore.getFlatRate()==null && ecommerceStore.getWeight()!=null){
								ecommerceStore.setFlatRate(ecommerceStore.getWeight());
								
					     }
						 if(ecommerceStore.getFlatMarkup()==null &&  ecommerceStore.getMarkupPerc()!=null){
							 ecommerceStore.setFlatMarkup(ecommerceStore.getMarkupPerc());
						 }
						 try {
							customer=customerService.getCustomerInfoByCustomerId(ecommerceStore.getCustomerId());
						} catch (Exception e) {
							// TODO Auto-generated catch block
			 
			 
				
			}
		}
		
		return SUCCESS;
	}
	public String deleteStore(){
		String storeId=request.getParameter("storeId");
		if(storeId!=null){
			eCommerceDAO.deleteEcommerceStoreById(Long.parseLong(storeId));
		}
		addActionMessage("Store Deleleted Succesffuly");
		return SUCCESS;
	}
	 

	 
	@SuppressWarnings("unchecked")
	public List<Customer> getCustomerList() {
		customerList=(List<Customer>) ActionContext.getContext().getSession().get(ShiplinxConstants.SESSION_BUSINESSFILTER_CUSTOMERID);
		if(customerList==null){
			customerList=customerService.getAllCustomers();
		}
		Collections.sort(customerList, Customer.customerSort);
		return customerList;
	}

	public void setCustomerList(List<Customer> customerList) {
		this.customerList = customerList;
	}

	public int getShipCustomerFlag() {
		return shipCustomerFlag;
	}

	public void setShipCustomerFlag(int shipCustomerFlag) {
		this.shipCustomerFlag = shipCustomerFlag;
	}

	public int getPackageMapFlag() {
		return packageMapFlag;
	}

	public void setPackageMapFlag(int packageMapFlag) {
		this.packageMapFlag = packageMapFlag;
	}
	

	public String getFreeShipLable() {
		return freeShipLable;
	}

	public void setFreeShipLable(String freeShipLable) {
		this.freeShipLable = freeShipLable;
	}

	public String getMarkupLable() {
		return markupLable;
	}

	public void setMarkupLable(String markupLable) {
		this.markupLable = markupLable;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	

}
