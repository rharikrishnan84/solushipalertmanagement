package com.meritconinc.shiplinx.action;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.meritconinc.shiplinx.service.ShippingService;
import com.meritconinc.mmr.utilities.MmrBeanLocator;
import com.meritconinc.shiplinx.dao.BusinessDAO;
import com.meritconinc.shiplinx.dao.MarkupManagerDAOImpl;
import com.meritconinc.shiplinx.model.EshipplusCarrierFilter;
import com.meritconinc.mmr.exception.MarkupAlreadyExistsException;
import com.meritconinc.mmr.model.common.CountryVO;
import com.meritconinc.mmr.model.security.User;
import com.meritconinc.mmr.utilities.MessageUtil;
import com.meritconinc.mmr.utilities.StringUtil;
import com.meritconinc.mmr.utilities.security.UserUtil;
import com.meritconinc.shiplinx.model.Business;
import com.meritconinc.shiplinx.model.BusinessMarkup;
import com.meritconinc.shiplinx.model.Carrier;
import com.meritconinc.shiplinx.model.CarrierChargeCode;
import com.meritconinc.shiplinx.model.ChargeGroup;
import com.meritconinc.shiplinx.model.Customer;
import com.meritconinc.shiplinx.model.Markup;
import com.meritconinc.shiplinx.model.Service;
import com.meritconinc.shiplinx.service.CarrierServiceManager;
import com.meritconinc.shiplinx.service.CustomerManager;
import com.meritconinc.shiplinx.service.MarkupManager;
import com.opensymphony.xwork2.Preparable;
import com.meritconinc.mmr.constants.Constants;
import com.meritconinc.shiplinx.utils.ShiplinxConstants;
import com.opensymphony.xwork2.ActionContext;
import com.soluship.businessfilter.util.BusinessFilterUtil;
public class MarkupManagerAction extends BaseAction implements Preparable, ServletRequestAware {
  private static final long serialVersionUID = 25092786;
  private MarkupManager markupManagerService;
  private CarrierServiceManager carrierServiceManager;
  private CustomerManager customerService;
  private List<Markup> markupList;
  private List<Carrier> carriers;
  private List<Service> services;
  private List<CountryVO> countries;

  private List<CarrierChargeCode> carrierChargecodeList;
  private List<ChargeGroup> chargeGroupList;
  private String[] percentageMarkups;
  private String[] flatMarkups;
  // private Markup markup;
  private String[] weightList;
  private Long carrierId;
  private String uploadFileName;
  private File upload;
  private Map<String, Long> customerSearchResult = new HashMap<String, Long>();
  private ShippingService shippingService;
  public Map<String, Long> getCustomerSearchResult() {
	return customerSearchResult;
}

public void setCustomerSearchResult(Map<String, Long> customerSearchResult) {
	this.customerSearchResult = customerSearchResult;
}

private static final Logger log = LogManager.getLogger(MarkupManagerAction.class);
  public HttpServletRequest request;

  public Long getCarrierId() {
    return carrierId;
  }

  public void setCarrierId(Long selectedCarrierId) {
    this.carrierId = selectedCarrierId;
  }

  public CarrierServiceManager getCarrierServiceManager() {
    return carrierServiceManager;
  }

  public void setCarrierServiceManager(CarrierServiceManager carrierServiceManager) {
    this.carrierServiceManager = carrierServiceManager;
  }

  public void setCustomerService(CustomerManager customerService) {
    this.customerService = customerService;
  }

  public String[] getPercentageMarkups() {
    return percentageMarkups;
  }

  public void setPercentageMarkups(String[] percentageMarkups) {
    this.percentageMarkups = percentageMarkups;
  }

  public String[] getFlatMarkups() {
    return flatMarkups;
  }

  public void setFlatMarkups(String[] flatMarkups) {
    this.flatMarkups = flatMarkups;
  }

  public List<Carrier> getCarriers() {
    return carriers;
  }

  public void setCarriers(List<Carrier> carriers) {
    this.carriers = carriers;
  }

  public List<Service> getServices() {
    return services;
  }

  public void setServices(List<Service> services) {
    this.services = services;
  }

  public List<CountryVO> getCountries() {
    return countries;
  }

  public void setCountries(List<CountryVO> countries) {
    this.countries = countries;
  }

  public Markup getMarkup() {
    return (Markup) getSession().get("markup");
  }

  @SuppressWarnings("unchecked")
  public void setMarkup(Markup markup) {
    getSession().put("markup", markup);
  }

  public List<Markup> getMarkupList() {
    return markupList;
  }

  public void setMarkupList(List<Markup> markupList) {
    this.markupList = markupList;
  }

  public MarkupManager getMarkupManagerService() {
    return markupManagerService;
  }

  public void setMarkupManagerService(MarkupManager markupManagerService) {
    this.markupManagerService = markupManagerService;
  }

  public String getCountryCode(String code) {
    if (this.getCountries() != null) {
      for (CountryVO c : this.getCountries()) {
        if (c.getCountryCode().equalsIgnoreCase(code))
          return c.getCountryCode();
      }
    }
    return code;
  }

  public void prepare() throws Exception {
    // TODO Auto-generated method stub

  }

  public void setServletRequest(HttpServletRequest request) {
    // TODO Auto-generated method stub
    this.request = request;
  }

  public String defaultMarkup() throws Exception {
    getSession().remove("markup");
    // try {
    // Markup markup = this.getMarkup();
    // if (markup != null) {
    // markup.setCustomerId(0L);
    // markup.setCustomerBusName("Default");
    // }
    // } catch (Exception e) {
    // e.printStackTrace();
    // addActionError(getText("content.error.unexpected"));
    // }
    return init();
  }

  public String listService() throws Exception {
    String strReturn = null;
    try {
      String carrierId = request.getParameter("value");
      User user = UserUtil.getMmrUser();
      if (carrierId != null && !carrierId.isEmpty()) {
        if (user != null && user.getUserRole().equalsIgnoreCase("BusAdmin") || user.getUserRole().equalsIgnoreCase("SysAdmin")) {
          services = getMarkupServices(Long.parseLong(carrierId));
        } else {
          long carrierLongId = 0L;
          try {
            carrierLongId = Long.parseLong(carrierId);

          } catch (Exception e) {
            log.error("Error in converting the carrier id in to long value : " + carrierId);
          }
          List<Service> services = null;
          if (carrierLongId != 0) {
            services = getCustomerCarrierServices(carrierLongId, user.getCustomerId());
          } else {
            services = new ArrayList<Service>();
          }
          getSession().put("SERVICES", services);
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
      addActionError(getText("content.error.unexpected"));
    }
    if (request.getParameter("pickup") != null)
      strReturn = "success2";
    else if(request.getParameter("call")!=null)
      strReturn = "success3";
    else
      strReturn = "success";
    return strReturn;
  }

  private List<Service> getCustomerCarrierServices(Long carrierCode, long customerId) {
    // TODO Auto-generated method stub
    // ShippingOrder so = this.getShippingOrder();
    List<Service> sList = this.carrierServiceManager.getCustomerServicesForCarrier(carrierCode,
        customerId);
    Service s = new Service();
    s.setId(-1L);
    s.setName("ANY");
    s.setCarrierId(carrierCode);
    sList.add(0, s);
    getSession().put("SERVICES", sList);

    return sList;
  }

  public String init() throws Exception {
	    try {
	      Markup markup = this.getMarkup();
	      List<Customer> customers = (List<Customer>)getSession().get(ShiplinxConstants.SESSION_BUSINESSFILTER_CUSTOMERID);
	                        
	                        /*Long businessId=(Long) ActionContext.getContext().getSession().get(Constants.BUSINESS_ID_SESSION);
	                        Long partnerId=(Long) ActionContext.getContext().getSession().get(Constants.PARTNER_ID_SESSION);
	                        Long countryPartnerId=(Long) ActionContext.getContext().getSession().get(Constants.NATION_ID_SESSION);
	                        Long branchId=(Long) ActionContext.getContext().getSession().get(Constants.BRANCH_ID_SESSION);
	                     */
	                     /*  if(customers!=null && customers.size()>0){
	                      	for(Customer customer : customers){
	                      		customerIds.add(customer.getId());
	                      	}*/
	                        
	                      	 // customerIds.add(0L);
	                     
	            
	      if (markup == null) {
	        markup = new Markup();
	        //markup.setCustomerIds(customerIds);
	                               /*if(businessId!=null){
	                                	markup.setBusinessId(businessId);
	                                }else{
	                                	markup.setBusinessId(UserUtil.getMmrUser().getBusinessId());
	                               }
	                        
	                           markup.setBusinessIds(BusinessFilterUtil.getBusIdParentId(markup.getBusinessId()));
	                              */ // markup.setBusinessId(UserUtil.getMmrUser().getBusinessId());
	        
	        String sCustomerId = request.getParameter("customerId");
	                                if(sCustomerId!=null){
	                                Long customerId=Long.parseLong(sCustomerId);
	                                if(customerId!=null && customerId>0){
	                                	Customer c=customerService.getCustomerInfoByCustomerId(customerId);
	                                	if(c!=null){
	                                	markup.setBusinessId(c.getBusinessId());
	                                	}
	                                }
	                                }else{
	                                	Long userLevelBusId=BusinessFilterUtil.setBusinessIdbyUserLevel();
	                                	if(userLevelBusId!=null && userLevelBusId>0){
	                            		markup.setBusinessId(BusinessFilterUtil.setBusinessIdbyUserLevel());
	                                	}else{
	                                		markup.setBusinessId(UserUtil.getMmrUser().getBusinessId());
	                                	}
	                                }
	                
	                markup.setCustomerId(0L);
	        markup.setDisabledFlag(false);
	        markup.setCustomerBusName("Default");
	        // markup.setType(ShiplinxConstants.TYPE_MARKUP);
	        this.setMarkup(markup);
	      } else {
	        if (markup.getFromCountryCode() != null && markup.getFromCountryCode().isEmpty())
	          markup.setFromCountryCode(null);
	        if (markup.getToCountryCode() != null && markup.getToCountryCode().isEmpty())
	          markup.setToCountryCode(null);
	        if (markup.getServiceId() != null && markup.getServiceId().equals(-1L))
	          markup.setServiceId(null);

	        if (!validateWeightParameters(markup)) {
	          addActionError(getText("error.markup.invalid.weight"));
	          return INPUT;
	        }

	      }
	      String sCustomerId = request.getParameter("customerId");
	      if (sCustomerId != null) {
	    	  Long customerId=Long.parseLong(sCustomerId);
	    	            if(customerId!=null && customerId>0){
	    	            	Customer c=customerService.getCustomerInfoByCustomerId(customerId);
	    	            	if(c!=null){
	    	            	markup.setBusinessId(c.getBusinessId());
	    	            	}
	    	            }
	        markup.setCustomerId(Long.parseLong(sCustomerId));	
	        /*customerIds.clear();
	                customerIds.add(Long.parseLong(sCustomerId));
	                markup.setCustomerIds(customerIds);*/
	        if (markup.getCustomerId() == 0)
	          markup.setCustomerBusName("Default");
	        else
	          markup.setCustomerBusName(customerService.getCustomerInfoByCustomerId(
	        		  Long.parseLong(sCustomerId)).getName());
	      }

	      initWeightList();

	      if (this.markupManagerService != null) {
	        /*markupList = this.markupManagerService.getMarkupListForCustomer(markup);
	        if (markupList != null)
	          getSession().put("MARKUPLIST", markupList);*/
	    	  BusinessDAO businessDAO=(BusinessDAO)MmrBeanLocator.getInstance().findBean("businessDAO");
	    	      		  long busId = BusinessFilterUtil.setBusinessIdbyUserLevel();
	    	      		  Business bus = businessDAO.getBusiessById(busId);
	    	      		  if(!bus.isCopyMarkup() && (sCustomerId == null || markup.getCustomerId() == 0)){
	    	      			  markupList = this.markupManagerService.getMarkupListForCustomer(markup);
	    	      		  }
	    	      		  
	    	      	if(sCustomerId != null && markup.getCustomerId() > 0){
	    	      		Customer cus = customerService.getCustomerInfoByCustomerId(markup.getCustomerId());
	    	      			markupList = this.markupManagerService.getMarkupListForCustomer(markup);
	    	      			bus.setCopyMarkup(false);
	    	      	}
	    	          if (markupList != null && !markupList.isEmpty()){
	    	          	getSession().put("MARKUPLIST", markupList);
	    	          }
	    	          
	    	          if(sCustomerId != null && (markupList == null || markupList.isEmpty()) ){
	    	          	getSession().put("CopyDisable", "copy");
	    	          }

	        setFlatMarkups(this.markupManagerService.getFlatMarkups());

	        setPercentageMarkups(this.markupManagerService.getPercentageMarkups());

	        countries = MessageUtil.getCountriesList();
	        getSession().put("COUNTRIES", countries);

	        populateCountryNamesInMarkupList();
	        this.populateCustomersList();
	        if (this.carrierServiceManager != null) {
	          if (getSession().get("CARRIERS") == null) {
	        	  /*if(businessId!=null){
	        		          		          		                	carriers = this.carrierServiceManager.getCarriersForBusiness(businessId);
	        		          		          		                }else{
	        		         		          		                	carriers = this.carrierServiceManager.getCarriersForBusiness(UserUtil.getMmrUser()
	        		          		          		                			.getBusinessId());
	        		          		          		                }*/
	        	  long businessId=BusinessFilterUtil.setBusinessIdbyUserLevel();
	        	          	          	          	 if(businessId>0){
	        	          	          	          	 carriers = this.carrierServiceManager.getCarriersForBusiness(businessId);
	        	          	          	          	 }else{
	        	          	          	          		 carriers = this.carrierServiceManager.getCarriersForBusiness(UserUtil.getMmrUser().getBusinessId());
	        	          	          	          	 }
	            getSession().put("CARRIERS", carriers);
	            setCarrierId(1L);
	          }
	          if (getSession().get("SERVICES") == null) {
	            services = getMarkupServices(1L);
	            getSession().put("SERVICES", services);
	          }
	        }

	      }
	      if(sCustomerId == null || sCustomerId.isEmpty() || markup.getCustomerId() == 0){
	    	  	      BusinessDAO businessDAO=(BusinessDAO)MmrBeanLocator.getInstance().findBean("businessDAO");
	    	  	      long businessId=BusinessFilterUtil.setBusinessIdbyUserLevel();
	    	  	      Business bus = businessDAO.getBusiessById(businessId);
	    	  	      getSession().put("MarkupCopyCheck", bus.isCopyMarkup());
	    	  	     //long id = (Long)
	    	  	      getSession().put("ParentIdAvailable", bus.getParentBusinessId());
	    	        }
	    } catch (Exception e) {
	      e.printStackTrace();
	      addActionError(getText("content.error.unexpected"));
	    }
	    return SUCCESS;
	  }

  private void initWeightList() {
    // TODO Auto-generated method stub
    if (getSession().get("WEIGHTLIST") == null) {
      weightList = new String[201];
      weightList[0] = "";
      for (int i = 0; i < 200; i++)
        weightList[i + 1] = Float.toString(i + 1);

      getSession().put("WEIGHTLIST", weightList);
    }
  }

  private void populateCountryNamesInMarkupList() {
    // TODO Auto-generated method stub
    if (this.getMarkupList() != null && this.getCountries() != null) {
      for (Markup m : this.getMarkupList()) {
        if (m != null && m.getFromCountryCode() != null)
          m.setFromCountryName(this.getCountryCode(m.getFromCountryCode()));
        if (m != null && m.getToCountryCode() != null)
          m.setToCountryName(this.getCountryCode(m.getToCountryCode()));
      }
    }
  }

  private List<Service> getMarkupServices(long carrierCode) {
    // TODO Auto-generated method stub
	  List<Service> sList = new ArrayList<Service>();
	  	  if(UserUtil.getMmrUser().getUserRole().equals("busadmin")){
	  		  sList = this.carrierServiceManager.getServicesForCarrierAdmin(carrierCode);
	  	  }else{
	  		  sList = this.carrierServiceManager.getServicesForCarrier(carrierCode);
	  	  }
    Service s = new Service();
    s.setId(-1L);
    s.setName("ANY");
    s.setCarrierId(carrierCode);
    sList.add(0, s);
    getSession().put("SERVICES", sList);

    return sList;
  }

  // private List<CountryVO> getMarkupCountriesList() {
  // // TODO Auto-generated method stub
  // List<CountryVO> cList = (List<CountryVO>) getSession().get("markupCountries");
  // if (cList == null) {
  // cList = MessageUtil.getCountriesList();
  // CountryVO any = new CountryVO();
  // any.setCountryCode(ShiplinxConstants.COUNTRY_ANY);
  // any.setCountryName(ShiplinxConstants.COUNTRY_ANY);
  // // any.setId(0L);
  // cList.add(0, any);
  //
  // CountryVO c = new CountryVO();
  // c.setCountryCode("");
  // c.setCountryName("");
  // // c.setId(-1L);
  // cList.add(0, c);
  // getSession().put("markupCountries", cList);
  //
  // }
  // return cList;
  // }

  private void loadListsFromSession() {
    if (this.markupList == null)
      this.markupList = (List<Markup>) getSession().get("MARKUPLIST");
    // if (this.carriers == null)
    // this.carriers = (List<Carrier>) getSession().get("carriers");
    // if (this.services == null)
    // this.services = (List<Service>) getSession().get("services");
  }

  public String deleteMarkup() throws Exception {
    try {
      loadListsFromSession();

      String s = request.getParameter("serviceId");
      Long serviceId = 0L;
      if (s != null)
        serviceId = Long.parseLong(s);

      String fromCountryCode = request.getParameter("fromCountryCode");
      String toCountryCode = request.getParameter("toCountryCode");
      Markup markup = findMarkup(serviceId, fromCountryCode, toCountryCode);
      if (markup != null)
        this.markupManagerService.deleteMarkup(markup);
    } catch (Exception e) {
      e.printStackTrace();
      addActionError(getText("content.error.unexpected"));
    }
    return init();
  }

  public String applyToAllCustomersMarkup() throws Exception {
    try {
      loadListsFromSession();

      String s = request.getParameter("serviceId");
      Long serviceId = 0L;
      if (s != null)
        serviceId = Long.parseLong(s);

      String fromCountryCode = request.getParameter("fromCountryCode");
      String toCountryCode = request.getParameter("toCountryCode");
      Markup markup = findMarkup(serviceId, fromCountryCode, toCountryCode);
      if (markup != null) {
        this.markupManagerService.applyToAllCustomersMarkup(markup);
      }
    } catch (Exception e) {
      e.printStackTrace();
      addActionError(getText("content.error.unexpected"));
    }
    return init();
  }

  /*public String addMarkup() throws Exception {*/
  @SuppressWarnings("deprecation")
  public String addMarkup() throws Exception {
    try {
    	//vivek hide
    	//boolean addmarkup=false;
      Markup markup = this.getMarkup();
      if (markup.getFromCountryCode() == null || markup.getFromCountryCode().length() == 0)
        markup.setFromCountryCode("ANY");
      if (markup.getToCountryCode() == null || markup.getToCountryCode().length() == 0)
        markup.setToCountryCode("ANY");

      if (markup != null && this.markupManagerService != null
          && markup.getFromCountryCode() != null && (!markup.getFromCountryCode().isEmpty())
          && markup.getToCountryCode() != null && (!markup.getToCountryCode().isEmpty())
          && markup.getServiceId() != null && (!markup.getServiceId().equals(-1L))) {

        if (!validateWeightParameters(markup)) {
          addActionError(getText("error.markup.invalid.weight"));
          return INPUT;
        }
//vivek hide
       /* addmarkup=true;
              request.getSession().putValue("addMarkup", addmarkup);*/
        Markup m = this.markupManagerService.addMarkup(markup);
        //vivek hide
       // request.getSession().removeValue("addMarkup");
        if (m != null) {
          // this.getMarkupList().add(0, m);
        	//vivek hide
        	//request.getSession().removeValue("addMarkup");
        	//vivek hide end
          throw new MarkupAlreadyExistsException("Markup already exists - " + m.toString());
        }
      }
    } catch (MarkupAlreadyExistsException ue) {
    	//vivek hide
    	//request.getSession().removeValue("addMarkup");
      addActionError(getText("error.markup.exists") + " - " + ue.getMessage());
      return INPUT;
    } catch (Exception e) {
    	//vivek hide
    	//request.getSession().removeValue("addMarkup");
      e.printStackTrace();
      addActionError(getText("content.error.unexpected"));
      return INPUT;
    }
    //vivek hide
    //request.getSession().removeValue("addMarkup");
    return init();
  }

  private boolean validateWeightParameters(Markup markup) {
    // TODO Auto-generated method stub
    if (markup != null) {
      if (markup.getFromWeight() != null) {
        if (markup.getToWeight() != null) {
          if (markup.getFromWeight().floatValue() <= markup.getToWeight().floatValue())
            return true;
          else
            return false;
        } else {
          return false;
        }
      } else if (markup.getToWeight() != null)
        return false;
    }
    return true;
  }

  /*public String saveMarkupList() throws Exception {
    try {
      loadListsFromSession();
      String selectedItem=request.getParameter("selectedItem");    	   
          	      	String percentage=request.getParameter("percentage");
          	    	  	String flat=request.getParameter("flat");
          	    	  	String disabledFlag=request.getParameter("disabledFlag");
          	    	  	String variable=request.getParameter("variable");
          	    	  	String item[]= selectedItem.split(",");
          	    	  	String mPs[]= percentage.split(",");
          	    	  	String mFs[]=flat.split(",");
          	    	  	String mDs[]=disabledFlag.split(",");    	  
          	    	  	String mVs[]=variable.split(",");
          	         // if (this.getMarkupList().size() != item.length)
          	    	  	for(int i1=0;i1<item.length;i1++)
          	          {
          	            //for (int i = 0; i < this.getMarkupList().size(); i++) {
          	              Markup m = this.getMarkupList().get(Integer.parseInt(item[i1]));
          	              int p = Integer.parseInt(mPs[i1]);
          	              double f = Double.parseDouble(mFs[i1]);
          	              boolean d = convertStringToBoolean(mDs[i1]);
          	              int v = Integer.parseInt(mVs[i1]);
                  if (isMarkupDirty(m, p, f, d, v)) {
              m.setMarkupPercentage(p);
              m.setMarkupFlat(f);
              m.setDisabledFlag(d);
              m.setVariable(v);
              // If user is in Customer Markup and modified default markup
              // it should be added as a new record customer specific markup
              if (m.getCustomerId().longValue() == 0
                  && getMarkup().getCustomerId().longValue() != 0) {
                m.setCustomerId(getMarkup().getCustomerId());
                this.markupManagerService.addMarkup(m);
              } else {
                this.markupManagerService.updateMarkup(m);
              }
            }
          }
    } catch (Exception e) {
      e.printStackTrace();
      addActionError(getText("content.error.unexpected"));
      return INPUT;
    }
    return init();
  }
*/
  /*private boolean isMarkupDirty(Markup m, int p, double f, boolean d, int v) {*/
  private boolean isMarkupDirty(Markup m, int p, double f, boolean d, int v,double fWt,double tWt) {
    // TODO Auto-generated method stub
    if (m.getMarkupPercentage().intValue() != p || m.getMarkupFlat().doubleValue() != f
    		|| m.getDisabledFlag() != d || m.getVariable() != v||m.getFromWeight()!=fWt||m.getToWeight()!=tWt)
      return true;
    return false;
  }

  private boolean convertStringToBoolean(String s) {
    // TODO Auto-generated method stub
    s = s.trim().toLowerCase();
    if (s.equals("true") || s.equals("yes"))
      return true;
    return false;
  }

  private Markup findMarkup(Long serviceId, String fromCountryCode, String toCountryCode) {
    // TODO Auto-generated method stub
    if (this.markupList != null) {
      for (Markup m : this.markupList) {
        if (m.getServiceId().equals(serviceId) && m.getFromCountryCode().equals(fromCountryCode)
            && m.getToCountryCode().equals(toCountryCode)) {
          return m;
        }
      }
    }
    return null;
  }

  private void setPercentageMarkups(Integer[] pMarkups) {
    // TODO Auto-generated method stub
    if (pMarkups != null) {
      ArrayList<String> alist = new ArrayList<String>();
      for (Integer i : pMarkups) {
        if (i != null)
          alist.add(i.toString());
      }

      // setPercentageMarkups((String []) alist.toArray());
      setPercentageMarkups(convertToStringArray(alist));
    }
  }

  private void setFlatMarkups(Double[] fMarkups) {
    // TODO Auto-generated method stub
    if (fMarkups != null) {
      ArrayList<String> alist = new ArrayList<String>();
      for (Double d : fMarkups) {
        if (d != null) {
          alist.add(d.toString());
        }
      }
      setFlatMarkups(convertToStringArray(alist));
      // setFlatMarkups((String []) alist.toArray());
    }
  }

  private String[] convertToStringArray(ArrayList<String> alist) {
    // TODO Auto-generated method stub
    if (alist != null) {
      String[] sList = new String[alist.size()];
      int i = 0;
      for (String s : alist) {
        sList[i++] = s;
      }
      return sList;
    }
    return null;
  }

  public String copyCustomerMarkup() throws Exception {
    try {
      Long sourceCusId = this.getMarkup().getSourceCustomerId();
      Long targetCusId = this.getMarkup().getCustomerId();
      System.out.println("Source:" + sourceCusId + " Target:" + targetCusId);
      if (sourceCusId != null && targetCusId != null
          && sourceCusId.longValue() != targetCusId.longValue()) {
        this.markupManagerService.copyCustomerMarkup(sourceCusId, targetCusId, UserUtil
            .getMmrUser().getBusiness().getId());
      }

    } catch (Exception e) {
      e.printStackTrace();
      addActionError(getText("content.error.unexpected"));
      return INPUT;
    }
    return init();
  }

  public String searchCharges() {
    log.debug("Inside-=----------searchCharges()---------");
    String strSearch = request.getParameter("search");

    if (strSearch == null)
      populateChargeGroup();

    if (strSearch != null) {
      CarrierChargeCode carrierChargeCode = getCarrierChargeCode();
      if (carrierChargeCode.getGroupId() == -1)
        carrierChargeCode.setGroupId(0);
      carrierChargecodeList = this.markupManagerService.searchCharges(carrierChargeCode);
    }
    getSession().remove("carrierchargecode");
    return SUCCESS;
  }

  public String deleteCharge() {
    log.debug("Inside------------deleteCharge()---------");

    String ChargeId = request.getParameter("id");

    this.markupManagerService.deleteCharges(Long.valueOf(ChargeId));

    return searchCharges();
  }

  public String goToAddNewCharge() {
    log.debug("Inside------------addNewCharge()---------");
    getSession().remove("carrierchargecode");
    getSession().remove("edit2");
    populateChargeGroup();
    return SUCCESS;
  }

  public String editCharge() {
    log.debug("Inside------------editCharge()---------");

    String ChargeId = request.getParameter("id");
    String strEdit = request.getParameter("method");
    if (strEdit != null)
      getSession().put("edit2", true);

    CarrierChargeCode carrierChargeCode = new CarrierChargeCode();
    carrierChargeCode.setId(Long.valueOf(ChargeId));

    carrierChargecodeList = this.markupManagerService.getCharges(carrierChargeCode);

    for (CarrierChargeCode ccc : carrierChargecodeList) {
      carrierChargeCode.setChargeCode(ccc.getChargeCode());
      carrierChargeCode.setChargeCodeLevel2(ccc.getChargeCodeLevel2());
      carrierChargeCode.setChargeName(ccc.getChargeName());
      carrierChargeCode.setChargeDesc(ccc.getChargeDesc());
      carrierChargeCode.setChargeGroup(ccc.getChargeGroup());
      carrierChargeCode.setGroupName(ccc.getChargeGroup().getGroupName());
      carrierChargeCode.setGroupId(ccc.getChargeGroup().getGroupId());
      carrierChargeCode.setCarrierCharge(ccc.getCarrierCharge());
      carrierChargeCode.setCarrierCost(ccc.getCarrierCost());
    }

    populateChargeGroup();

    this.setCarrierChargeCode(carrierChargeCode);

    return SUCCESS;
  }

  private void populateChargeGroup() {
    chargeGroupList = this.markupManagerService.getChargeGroups();
    Map<Long, String> mapCharge = new HashMap<Long, String>();
    for (ChargeGroup cg : chargeGroupList) {
      mapCharge.put(cg.getGroupId(), cg.getGroupName());
    }
    getSession().put("listChargeGroups", mapCharge);
  }

  public String AddOrEditCharges() {
    log.debug("Inside------------AddOrEditCharges()---------");
    String strchargeId = request.getParameter("chid");
    CarrierChargeCode carrierChargeCode = getCarrierChargeCode();
    try {
      if (strchargeId != null)// Edit mode
      {
        this.markupManagerService.addOrUpdateCharge(carrierChargeCode, false); // False if edit only
        addActionMessage(getText("charge.edited.successfully"));
      } else// Add mode
      {
        this.markupManagerService.addOrUpdateCharge(carrierChargeCode, true); // true if you want to
                                                                              // add a new entry
        addActionMessage(getText("charge.added.successfully"));
      }

    } catch (Exception e) {
      log.error("Exception occured in Add or Update of Charge ----" + e);
      addActionError(getText("charge.addorupdate.failed"));
    }

    getSession().remove("edit2");
    getSession().remove("carrierchargecode");

    return SUCCESS;
  }

  public List<CarrierChargeCode> getCarrierChargecodeList() {
    return carrierChargecodeList;
  }

  public void setCarrierChargecodeList(List<CarrierChargeCode> carrierChargecodeList) {
    this.carrierChargecodeList = carrierChargecodeList;
  }

  public CarrierChargeCode getCarrierChargeCode() {
    CarrierChargeCode carrierChargeCode = (CarrierChargeCode) getSession().get("carrierchargecode");
    if (carrierChargeCode == null) {
      carrierChargeCode = new CarrierChargeCode();
      carrierChargeCode.setCarrierId(UserUtil.getMmrUser().getBusiness().getBusinessCarrierId());
      setCarrierChargeCode(carrierChargeCode);
    }
    return carrierChargeCode;
  }

  public void setCarrierChargeCode(CarrierChargeCode carrierChargeCode) {
    carrierChargeCode.setCarrierId(UserUtil.getMmrUser().getBusiness().getBusinessCarrierId());
    getSession().put("carrierchargecode", carrierChargeCode);
  }

  public List<ChargeGroup> getChargeGroupList() {
    return chargeGroupList;
  }

  public void setChargeGroupList(List<ChargeGroup> chargeGroupList) {
    this.chargeGroupList = chargeGroupList;
  }

  @SuppressWarnings("unchecked")
  public String viewUploadRateTemplate() {
    String serviceId = request.getParameter("serviceId");
    String customerId = request.getParameter("customerId");
    try {
      if (serviceId != null) {
        Service service = this.getCarrierServiceManager().getService(Long.parseLong(serviceId));
        if (service != null) {
          setLtlService(service);
          // Markup markup = getMarkup();
          // if (markup != null) {
          // getSession().put("LTL_CUSTOMER_ID", markup.getCustomerId());
          // getSession().put("LTL_CUSTOMER_NAME", markup.getCustomerBusName());
          // }
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
      String errMsg = "Exception occured in retrieving LTL Service ----" + e.getMessage();
      log.error(errMsg);
      addActionError(errMsg);
    }
    return SUCCESS;
  }

  // @SuppressWarnings("unchecked")
  public void setLtlService(Service service) {
    if (service != null)
      getSession().put("ltlService", service);
  }

  // @SuppressWarnings("unchecked")
  public Service getLtlService() {
    return (Service) getSession().get("ltlService");
  }

  public String getUploadFileName() {
    return uploadFileName;
  }

  public void setUploadFileName(String uploadFileName) {
    this.uploadFileName = uploadFileName;
  }

  public File getUpload() {
    return upload;
  }

  public void setUpload(File upload) {
    this.upload = upload;
  }

  public String uploadRateTemplate() throws Exception {
    try {
      Service service = getLtlService();
      if (service != null && service.getCarrierId() > 0 && this.getUpload() != null
          && this.getUploadFileName() != null) {
        InputStream is = new DataInputStream(new FileInputStream(this.getUpload()));
        if (is != null) {
          Service s = this.markupManagerService.uploadRateTemplateFile(service, getMarkup()
              .getCustomerId(), getMarkup().getBusinessId(), this.getUploadFileName(), is,
              isOverwrite());
          if (s == null) {
            addActionError(getText("content.error.unexpected"));
          } else {
            addActionMessage(getText("ratetemplatefile.uploaded.successfully.process.started"));
          }
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
      // addActionError(getText("content.error.unexpected"));
      addActionError(e.getMessage());
    }
    return viewUploadRateTemplate();
  }

  private boolean isOverwrite() {
    String checkboxOverwrite = request.getParameter("checkboxOverwrite");
    return StringUtil.toBoolean(checkboxOverwrite);
  }
  public void populateCustomersList() {
	  	    String searchParameter = "";
	  
	  	    Customer c = new Customer();
	  	    c.setName(searchParameter);
	  	    c.setBusinessId(UserUtil.getMmrUser().getBusinessId());

	  	  List<Customer> customers =	  (List<Customer>)getSession().get(ShiplinxConstants.SESSION_BUSINESSFILTER_CUSTOMERID);
	  	  if(customers==null || customers.size()==0){
	  	      customers = this.customerService.search(c);
	  	  }
	  	    // First record is empty
	  	    customerSearchResult.put("", 0L);
	  
	  	    for (Customer cust : customers) {
	  	    	String withoutQuotesCustomer = cust.getName().replace("\"", "");
	  	    		      customerSearchResult.put(withoutQuotesCustomer, cust.getId());
	  	    }
	  
	  	    getSession().put("fromcustomersList", customerSearchResult);
	  	  }
  public String searchEshipPlusMarkup() throws Exception {
	  		shippingService = (ShippingService) MmrBeanLocator.getInstance()
	  				.findBean("shippingService");
	  	//session.put("eshipCarrierFound", "false");
	  		getSession().put("eshipCarrierFound", "false");
	  		Markup markup = new Markup();
	  		markup = getMarkup();
	  		String carrierIdStr, customerIdStr;
	  		Long carrierId, customerId;
	  		if (markup == null || markup.getCustomerId() == null
	  				|| markup.getCarrierId() == null) {
	  			carrierIdStr = request.getParameter("carrierId");
	  			customerIdStr = request.getParameter("customerId");
	  			carrierId = Long.parseLong(carrierIdStr);
	  
	  			customerId = Long.parseLong(customerIdStr);
	  		} else {
	  			carrierId = markup.getCarrierId();
	  			customerId = markup.getCustomerId();
	  		}
	  		eshipCarrierList = shippingService.getEshipPlusCarrierByCustomerId(customerId);
	  		session.put("eshipCustomerCarrier", eshipCarrierList);
	  		this.setEshipCarrierList(eshipCarrierList);
	  		if(eshipCarrierList!=null && eshipCarrierList.size()>0){
	  			getSession().put("eshipCarrierFound", "true");
	  		}
	  		return init();
	  	}
	  
	  
	  
	  public String saveMarkupList() throws Exception {
	  		try {
	  			long busId = BusinessFilterUtil.setBusinessIdbyUserLevel();
	  			long businessId = 0;
	  			if(this.getMarkup().getCustomerId() > 0 ){
	  				Customer cus = customerService.getCustomerInfoByCustomerId(this.getMarkup().getCustomerId());
	  				 businessId = cus.getBusinessId();
	  			}
	  			Boolean eshipCarrierUpdated=false;
	  			shippingService = (ShippingService) MmrBeanLocator.getInstance()
	  					.findBean("shippingService");
	  		loadListsFromSession();
	  			User user = UserUtil.getMmrUser();
	  			String carrierId = request.getParameter("carrierId");
	  		if (carrierId != null) {
	  				if (carrierId.equalsIgnoreCase("6")) {
	  				EshipplusCarrierFilter eshipplusCarrierFilter = new EshipplusCarrierFilter();
	  					String carrierName = request.getParameter("carrierName");
	  					carrierName=carrierName.replace('~', '&');
	  					String carrierInactive = request
	  
	  							.getParameter("carrierInactive");
	  					String eshipCarLt[] = carrierName.split(",");
	  					String eshipCarInAc[] = carrierInactive.split(",");
	  				for (int i1 = 0; i1 < eshipCarLt.length; i1++) {
	  						if(eshipCarInAc[i1] !=null && !eshipCarInAc[i1].equalsIgnoreCase("undefined")){
	  							eshipplusCarrierFilter
	  							.setCarrierDisabledFlag(convertStringToBoolean(eshipCarInAc[i1]));
	  							eshipplusCarrierFilter
	  						.setEshipCarrierName(eshipCarLt[i1]);
	  							shippingService.updateEshipCarrierFilter(
	  									eshipplusCarrierFilter, String.valueOf(this
	  											.getMarkup().getCustomerId()));
	  						eshipCarrierUpdated=true;
	  						}
	  
	  					}
	  				}
	  		}
	  			if(eshipCarrierUpdated!=null && eshipCarrierUpdated){
	  				addActionMessage("Carrier Updated Successfully");
	  			}
	  			String selectedItem = request.getParameter("selectedItem");
	  			String percentage = request.getParameter("percentage");
	  			String fromWeight=request.getParameter("fromWeight");
	  			  			String toWeight=request.getParameter("toWeight");
	  		String flat = request.getParameter("flat");
	  			String disabledFlag = request.getParameter("disabledFlag");
	  			String variable = request.getParameter("variable");
	  			String item[] = selectedItem.split(",");
	  			String mPs[] = percentage.split(",");
	  			String mFs[] = flat.split(",");
	  			String mDs[] = disabledFlag.split(",");
	  			String mVs[] = variable.split(",");
	  			String mFromWeight[]=fromWeight.split(",");
	  					String mToWeight[]=toWeight.split(",");
	  			// if (this.getMarkupList().size() != item.length)
	  			for (int i1 = 0; i1 < item.length; i1++) {
	  				if (!item[i1].equalsIgnoreCase("")) {
	  					// for (int i = 0; i < this.getMarkupList().size(); i++) {
	  					Markup m = this.getMarkupList().get(Integer.parseInt(item[i1]));
	  					m.setBusinessId(busId);
	  					int p = Integer.parseInt(mPs[i1]);
	  					double f = Double.parseDouble(mFs[i1]);
	  					double fWt=Double.parseDouble(mFromWeight[i1]);
	  							double tWt=Double.parseDouble(mToWeight[i1]);
	  				boolean d = convertStringToBoolean(mDs[i1]);
	  					int v = Integer.parseInt(mVs[i1]);
	  					/*if (isMarkupDirty(m, p, f, d, v)) {*/
	  					if (isMarkupDirty(m, p, f, d, v,fWt,tWt)) {
	  						m.setMarkupPercentage(p);
	  					m.setMarkupFlat(f);
	  						m.setDisabledFlag(d);
	  						m.setVariable(v);
	  						m.setFromWeight(fWt);
	  										m.setToWeight(tWt);
	  						// If user is in Customer Markup and modified default
	  					// markup
	  						// it should be added as a new record customer specific
	  						// markup
	  						if (m.getCustomerId().longValue() == 0
	  							&& getMarkup().getCustomerId().longValue() != 0) {
	  							m.setCustomerId(getMarkup().getCustomerId());
	  							this.markupManagerService.addMarkup(m);
	  							addActionMessage("Added Successfully");
	  						} else {
	  							if(businessId > 0){
	  								m.setBusinessId(businessId);
	  							}
	  						this.markupManagerService.updateMarkup(m);
	  							addActionMessage("Updated Successfully");
	  						}
	  				}
	  				}
	  			}
	  			eshipCarrierList = shippingService
	  					.getEshipPlusCarrierByCustomerId(this
	  							.getMarkup().getCustomerId());
	  			session.put("eshipCustomerCarrier", eshipCarrierList);
	  			this.setEshipCarrierList(eshipCarrierList);
	  		} catch (Exception e) {
	  			e.printStackTrace();
	  		addActionError(getText("content.error.unexpected"));
	  			return INPUT;
	  		}
	  		return init();
	  	}
	  
	  
	  
	   private List<EshipplusCarrierFilter> eshipCarrierList;
	   private List<BusinessMarkup> businessMarkupList;
	private BusinessDAO businessDAO;
	  public List<EshipplusCarrierFilter> getEshipCarrierList() {
	  
	  	return eshipCarrierList;
	  
	  }
	  
	  public void setEshipCarrierList(List<EshipplusCarrierFilter> eshipCarrierList) {
	  	this.eshipCarrierList = eshipCarrierList;
	  }
	  
	//Method to Copy Parent Markup to child
	  	  public String copyParentMarkup(){
	  		  long customerId = Long.parseLong(request.getParameter("customerId"));
	  		  long businessId = BusinessFilterUtil.setBusinessIdbyUserLevel();
	  		  BusinessDAO businessDAO=(BusinessDAO)MmrBeanLocator.getInstance().findBean("businessDAO");
	  		MarkupManagerDAOImpl markupDAO = (MarkupManagerDAOImpl)MmrBeanLocator.getInstance().findBean("markupManagerDAO");
	  		  Business bus = businessDAO.getBusiessById(businessId);
	  		  
	  		  
	  		  try{
	  			  if(customerId == 0 ){
	  		  //
	  				  if(bus!=null && !bus.isPartnerLevel() && !bus.isNationLevel() && !bus.isBranchLevel() && bus.getPartnerId()==0 
	  							&& bus.getParentBusinessId()==0 && bus.getBranchId()==0 && bus.getCountryPartnerId()==0){
	  					 
	  					  long i = markupDAO.ismarkupavailable(1);
	  					  if(i > 0){
	  						  markupDAO.insertCustomerMarkupByBusiness(businessId, 1);
	  						  businessDAO.setCopyMarkupFlag(businessId);
	  						  addActionMessage("Default markup of parent business added successfully");
	  					  } else {
	  						  addActionError("No markup available to copy");
	  						  return INPUT;
	  					  }
	  					 
	  					}else if(bus!=null && bus.isPartnerLevel()  ){  //partner level business  to get the business objects  of child business under the tree
	  						
	  						copyParentMarkup(bus.getId());
	  						
	  					}else if(bus!=null&&bus.isNationLevel()){   //nation level business  to get the business objects  of child business under the tree
	  						
	  						copyParentMarkup(bus.getId());
	  						 
	  					}else if(bus!=null&&bus.isBranchLevel() ){    //branch level business  to get the business objects  of child business under the tree
	  		
	  						copyParentMarkup(bus.getId());
	  					}
	  				  
	  				  init();
	  			  }
	  		  }catch(Exception e){
	  			  e.printStackTrace();
	  		  }
	  			
	  		  return SUCCESS;
	  	  }  
	  	  
	  	  // Recursive method to copy available parent markup to child based on customer and business
	  	  public void copyParentMarkup(long businessId){
	  		  long businessId1 = BusinessFilterUtil.setBusinessIdbyUserLevel();
	  		  BusinessDAO businessDAO=(BusinessDAO)MmrBeanLocator.getInstance().findBean("businessDAO");
	  		  MarkupManagerDAOImpl markupDAO = (MarkupManagerDAOImpl)MmrBeanLocator.getInstance().findBean("markupManagerDAO");
	  		  Business bus = businessDAO.getBusiessById(businessId);
	  		  long i=0;
	  		  if(bus.getParentBusinessId() > 0){
	  			 i = markupDAO.ismarkupavailable(bus.getParentBusinessId());
	  			if(i > 0){
	  					markupDAO.insertCustomerMarkupByBusiness(businessId1, bus.getParentBusinessId());
	  					businessDAO.setCopyMarkupFlag(businessId1);
	  					addActionMessage("Default markup of parent business added successfully");
	  					return;
	  				
	  			}else{
	  					 businessId = bus.getParentBusinessId();
	  					 copyParentMarkup(businessId);
	  			}
	  		  }else{
	  				i = markupDAO.ismarkupavailable(1);
	  				if(i > 0){
	  					markupDAO.insertCustomerMarkupByBusiness(businessId1, 1);
	  					businessDAO.setCopyMarkupFlag(businessId1);
	  					addActionMessage("Default markup of parent business added successfully");
	  					return;
	  				}else{
	  					addActionError("No markup available to copy");
	  				}
	  		  }
	  	  }
	  	  
	  	  
	  	//code for Business Markup starts here 
		  	
		  	public BusinessMarkup getBusinessMarkup() {
		  		return (BusinessMarkup) getSession().get("businessMarkup");
		  	}
		  	
		  	@SuppressWarnings("unchecked")
		  	public void setBusinessMarkup(BusinessMarkup markup) {
		  		getSession().put("businessMarkup", markup);
		  	}
		  	
			/**
			 * This method is for getting all the business mark-up records from collection
			 * @return
			 */
			@SuppressWarnings("unchecked")
			public String getAllBusinessMarkups() {
				BusinessMarkup businessMarkup=(BusinessMarkup) getSession().get("businessMarkup");
				request.getSession().removeAttribute("businessMarkup");
				businessDAO = (BusinessDAO) MmrBeanLocator.getInstance().findBean("businessDAO");
				List<Business> fromBusinesses=businessDAO.getAllBusiness();
				List<Business> toBusinesses=businessDAO.getAllBusiness();
				getSession().put("TO_BUSINESS", toBusinesses);
				getSession().put("BUSINESS", fromBusinesses);
				countries = MessageUtil.getCountriesList();
				getSession().put("COUNTRIES", countries);
				if(request.getParameter("call")==null && !("search").equalsIgnoreCase(request.getParameter("call"))){
					List<Customer> customers =(List<Customer>) getSession().get(ShiplinxConstants.SESSION_BUSINESSFILTER_CUSTOMERID);
					if(customers!=null){
						getSession().put("CUSTOMERS", customers);
					}else{
						getSession().put("CUSTOMERS", new ArrayList<Customer>());
					}
				}
				getSession().put("CARRIERS",new ArrayList<Carrier>());
				getSession().put("SERVICES", new ArrayList<Service>());
				carriers=this.markupManagerService.getListCarriers("0","0");
				if(carriers!=null){
					getSession().put("CARRIERS",carriers);
					services=this.markupManagerService.getListCarrierServices(0,0);
					if(services!=null){
						getSession().put("SERVICES", services);
					}
				}
				List<BusinessMarkup> businessMarkupList =new ArrayList<BusinessMarkup>();
				businessMarkupList = this.markupManagerService.getAllBusinessMarkups(businessMarkup);
				getSession().put("MARKUPLIST", businessMarkupList);
				return SUCCESS;
			}
			
			/**
			 * This method is for getting business mark-up records for selected values from collection
			 * @return
			 */
			@SuppressWarnings("unchecked")
			public String getBusinessMarkups() {
				BusinessMarkup businessMarkup=(BusinessMarkup) getSession().get("businessMarkup");
				businessDAO = (BusinessDAO) MmrBeanLocator.getInstance().findBean("businessDAO");
				List<Business> fromBusinesses=businessDAO.getAllBusiness();
				List<Business> toBusinesses=businessDAO.getAllBusiness();
				getSession().put("TO_BUSINESS", toBusinesses);
				getSession().put("BUSINESS", fromBusinesses);
				countries = MessageUtil.getCountriesList();
				getSession().put("COUNTRIES", countries);
				if(request.getParameter("call")==null && !("search").equalsIgnoreCase(request.getParameter("call"))){
					List<Customer> customers =(List<Customer>) getSession().get(ShiplinxConstants.SESSION_BUSINESSFILTER_CUSTOMERID);
					getSession().put("CUSTOMERS", customers);
				}
				List<BusinessMarkup> businessMarkupList =new ArrayList<BusinessMarkup>();
				businessMarkupList = this.markupManagerService.getBusinessMarkups(businessMarkup);
				getSession().put("MARKUPLIST", businessMarkupList);
				if(businessMarkupList!=null && businessMarkupList.size()==0){
					addActionMessage("No records found");
				}
				return SUCCESS;
			}
					  	
			/**
			 * This method is for inserting business mark-up record into collection
			 * @return
			 * @throws Exception
			 */
			@SuppressWarnings("unchecked")
			public String addBusinessMarkup() throws Exception {
				try {
					BusinessMarkup businessMarkup = this.getBusinessMarkup();
					boolean flag=validateObject(businessMarkup);
					if(!flag){
						return INPUT;
					}
					List<BusinessMarkup> businessMarkupList =new ArrayList<BusinessMarkup>();
					businessMarkupList = this.markupManagerService.addBusinessMarkup(businessMarkup);
					if(businessMarkupList!=null && businessMarkupList.size()>0){
						addActionMessage("Business markup inserted successfully");
						getSession().put("MARKUPLIST", businessMarkupList);
					}else{
						addActionError("Business markup already exist.");
						getSession().put("MARKUPLIST", new ArrayList<BusinessMarkup>());
					}
				} catch (Exception e) {
					e.printStackTrace();
					addActionError(getText("content.error.unexpected"));
					return INPUT;
				}
				return SUCCESS;
			}
			
			/**
			 * This method is for validating the business mark-up object for insert
			 * condition 1:If from business selected and to business not selected then other fields should be ANY 
			 * condition 2:If carrier selected the service should be selected as well
			 * @param businessMarkup
			 * @return
			 */
			private boolean validateObject(BusinessMarkup businessMarkup) {
				int i=0;
				List<BusinessMarkup> businessMarkups=this.markupManagerService.getAllBusinessMarkups(new BusinessMarkup());
				if(businessMarkup.getBusinessId()!=null && businessMarkup.getBusinessId()>0){
					if(businessMarkup.getBusinessToId()!=null && businessMarkup.getBusinessToId()>0){
						for(BusinessMarkup businessMarkup2:businessMarkups){
							if(!businessMarkup2.getBusinessId().equals(businessMarkup.getBusinessId())){
								if(businessMarkup2.getBusinessToId().equals(businessMarkup.getBusinessToId())){
									addActionError("Already "+businessMarkup2.getFromBusiness()+" applied Markup for "+businessMarkup2.getToBusiness());
									return false;
								}
							}
						}
						if(businessMarkup.getCarrierId()>0){
							if(businessMarkup.getServiceId()==0){
								addActionError("Please select service to proceed");
								i++;
							}
						}
						if(businessMarkup.getMarkupFlat()==null && businessMarkup.getMarkupPercentage()==null){
							addActionError("Please insert markup percentage or flat to proceed");
							i++;
						}
					}else{
						for(BusinessMarkup businessMarkup2:businessMarkups){
							if(businessMarkup2.getBusinessToId()==0){
								addActionError("Already "+businessMarkup2.getFromBusiness()+" applied Markup for all business");
								return false;
							}
						}
						if(businessMarkup.getCarrierId()>0){
							addActionError("Please select carrier as ANY to proceed");
							i++;
						}
						if(businessMarkup.getServiceId()>0){
							addActionError("Please select service as ANY to proceed");
							i++;
						}
						if(businessMarkup.getCustomerId()>0){
							addActionError("Please select customer as ANY to proceed");
							i++;
						}
						if(!businessMarkup.getFromCountryCode().equals("ANY")){
							addActionError("Please select from country as ANY to proceed");
							i++;
						}
						if(!businessMarkup.getToCountryCode().equals("ANY")){
							addActionError("Please select To country as ANY to proceed");
							i++;
						}
					}
				}else{
					addActionError("Please select From-business to proceed");
					i++;
				}
				if(i>0){
					return false;
				}else{
					return true;
				}
			}

			/**
			 * This method is for deleting business mark-up record from collection
			 * @return
			 * @throws Exception
			 */
			@SuppressWarnings("unchecked")
			public String deleteBusinessMarkup() throws Exception {
				try {
					loadBusinessMarkupLists();
					getSession().remove("MARKUPLIST");
					BusinessMarkup businessMarkup = this.getBusinessMarkup();
					String id = request.getParameter("id");
					if (id != null){
						businessMarkup.setId(id);
					}
					List<BusinessMarkup> businessMarkupList =new ArrayList<BusinessMarkup>();
					businessMarkupList=this.markupManagerService.deleteBusinessMarkup(businessMarkup);
					if(businessMarkupList!=null && businessMarkupList.size()>0){
						addActionMessage("Business markup deleted successfully.");
						getSession().put("MARKUPLIST", businessMarkupList);
					}else if(businessMarkupList!=null && businessMarkupList.size()==0){
						addActionMessage("No business markup record found.");
						getSession().put("MARKUPLIST", businessMarkupList);
					}else{
						addActionError("Error occurred while deleting business markup.");
						getSession().put("MARKUPLIST", new ArrayList<BusinessMarkup>());
					}
				} catch (Exception e) {
					e.printStackTrace();
					addActionError(getText("content.error.unexpected"));
				}
				return SUCCESS;
			}
					  
			/**
			 * This method is for loading to business drop-down with business which
			 * contains from business id as parent business and
			 * other root business with no parent business mapping
			 * @return
			 * @throws Exception
			 */
			@SuppressWarnings("unchecked")
			public String listToBusiness() throws Exception {
				try {
					businessDAO = (BusinessDAO) MmrBeanLocator.getInstance().findBean("businessDAO");
					long businessId = (request.getParameter("value")!=null?Long.parseLong(request.getParameter("value")):0l);
					List<Business> toBusinesses=new ArrayList<Business>();
					toBusinesses =businessDAO.getBusinessForSelectedBusiness(businessId);
					if(toBusinesses!=null && toBusinesses.size()>0){
						
					/*List<BusinessMarkup> businessMarkups=this.markupManagerService.getAllBusinessMarkups(new BusinessMarkup());
						List<Business> temp=new ArrayList<Business>();
						 for(Business business:toBusinesses){
							int i=0;
							for(BusinessMarkup businessMarkup:businessMarkups){
								if(business.getId()==businessMarkup.getBusinessToId()){
									i++;
								}
								if(businessMarkup.getBusinessToId()==0){
									i++;
								}
							}
							if(i!=2){
								temp.add(business);
							}
						}
						for(Business business:toBusinesses){
							for(BusinessMarkup businessMarkup:businessMarkups){
								if(business.getId()==businessMarkup.getBusinessToId() && businessId==businessMarkup.getBusinessId())
								temp.add(business);
							}
						}
						toBusinesses=temp;*/
					}
					getSession().put("TO_BUSINESS", toBusinesses);
				} catch (Exception e) {
					e.printStackTrace();
					addActionError(getText("content.error.unexpected"));
				}
				return SUCCESS;
			}
			
			/**
			 * This method is used to list the business mark-up record from business mark-up screen	
			 */
			@SuppressWarnings("unchecked")
			private void loadBusinessMarkupLists() {
				if (this.businessMarkupList == null)
					this.businessMarkupList = (List<BusinessMarkup>) getSession().get("MARKUPLIST");
			}
			
			/**
			 * This method is for updating the business mark-up record in collection
			 * This function needs the id of the record to update
			 * @return
			 * @throws Exception
			 */
			public String saveBusinessMarkupList() throws Exception {
				try {
					shippingService = (ShippingService) MmrBeanLocator.getInstance()
							.findBean("shippingService");
					loadBusinessMarkupLists();
					String selectedItem = request.getParameter("selectedItem");
					String percentage = request.getParameter("percentage");
					String flat = request.getParameter("flat");
					String disabledFlag = request.getParameter("disabledFlag");
					String variable = request.getParameter("variable");
					String item[] = selectedItem.split(",");
					String mPs[] = percentage.split(",");
					String mFs[] = flat.split(",");
					String mDs[] = disabledFlag.split(",");
					String mVs[] = variable.split(",");
					for (int i1 = 0; i1 < item.length; i1++) {
						if (!item[i1].equalsIgnoreCase("")) {
							BusinessMarkup m = this.businessMarkupList.get(Integer.parseInt(item[i1]));					int p = Integer.parseInt(mPs[i1]);
							double f = Double.parseDouble(mFs[i1]);
							boolean d = convertStringToBoolean(mDs[i1]);
							int v = Integer.parseInt(mVs[i1]);
							if (isBusinessMarkupDirty(m, p, f, d, v)) {
								m.setMarkupPercentage(p);
								m.setMarkupFlat(f);
								m.setDisabledFlag(d);
								m.setVariable(v);
								if (m.getCustomerId().longValue() == 0
										&& getMarkup().getCustomerId().longValue() != 0) {
									m.setCustomerId(getMarkup().getCustomerId());
									this.markupManagerService.addBusinessMarkup(m);
									addActionMessage("Added Successfully");
								} else {
									this.markupManagerService.updateBusinessMarkup(m);
									addActionMessage("Updated Successfully");
								}
							}
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
					addActionError(getText("content.error.unexpected"));
					return INPUT;
				}
				return SUCCESS;
			}
			
			/**
			 * This method is used to check whether we changed the content of business mark-up record 
			 * in business mark-up screen
			 * @param m business mark-up
			 * @param p mark-up percentage
			 * @param f mark-up flat
			 * @param d disable mark-up
			 * @param v mark-up variable
			 * @return
			 */
			private boolean isBusinessMarkupDirty(BusinessMarkup m, int p, double f, boolean d, int v) {
				if (m.getMarkupPercentage().intValue() != p || m.getMarkupFlat().doubleValue() != f
						|| m.getDisabledFlag() != d || m.getVariable() != v){
					return true;
				}
				return false;
			}
			
			/**
			 * This method is for listing the customer for the selected To_Business for which 
			 * we are applying this business mark-up
			 * @return
			 * @throws Exception
			 */
			@SuppressWarnings("unchecked")
			public String listCustomers() throws Exception {
				String strReturn = null;
				try {
					String businessId = request.getParameter("value");
					List<Customer> customers=new ArrayList<Customer>();
					customers=customerService.getAllCustomerForBusiness(Long.parseLong(businessId));
					getSession().put("CUSTOMERS", customers);
					getSession().remove("CARRIERS");
					List<Carrier> carrierList=new ArrayList<Carrier>();
					carrierList =this.markupManagerService.getListCarriers("0",businessId);
					if(carrierList!=null){
						carriers=carrierList;
					}
					getSession().put("CARRIERS",carriers);
				} catch (Exception e) {
					e.printStackTrace();
					addActionError(getText("content.error.unexpected"));
				}
				if (request.getParameter("pickup") != null)
					strReturn = "success2";
				else if(request.getParameter("method") != null)
					strReturn = "success1";
				else
					strReturn = "success";
				return strReturn;
			}
			
			/**
			  * This method is for getting list of carriers from collection
			  * @return
			  * @throws Exception
			  */
			@SuppressWarnings("unchecked")
			public String listCarriers() throws Exception {
				String strReturn = null;
				try {
					String customerId = request.getParameter("customerId");
					String businessId = request.getParameter("businessToId");
					if(customerId!=null && businessId !=null){
						if(customerId.equals("-1")){
							customerId="0";
						}
						carriers =this.markupManagerService.getListCarriers("0",businessId);	
					}else{
						carriers = getCarriers();
					}
					getSession().put("CARRIERS", carriers);
				} catch (Exception e) {
					e.printStackTrace();
					addActionError(getText("content.error.unexpected"));
				}
				if (request.getParameter("pickup") != null)
					strReturn = "success2";
				else
					strReturn = "success";
				return strReturn;
			}
		  
	}

