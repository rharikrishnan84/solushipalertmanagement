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
        if (user != null && user.getUserRole().equalsIgnoreCase("BusAdmin")) {
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
    s.setName("");
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
    s.setName("");
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
	  		@SuppressWarnings("unchecked")
	  		public String businessMarkup() {
	  			User user = UserUtil.getMmrUser();
	  			BusinessMarkup businessMarkup=(BusinessMarkup) getSession().get("businessMarkup");
	  			request.getSession().removeAttribute("businessMarkup");
	  			businessDAO = (BusinessDAO) MmrBeanLocator.getInstance().findBean("businessDAO");
	  			List<Business> businesses=businessDAO.getAllBusiness();
	  			/*HashSet<Business> toBusList = new HashSet<Business>();
	  			toBusList.addAll(businesses);*/
	  			getSession().put("BUSINESS", businesses);
	  			countries = MessageUtil.getCountriesList();
	  			getSession().put("COUNTRIES", countries);
	  			if(request.getParameter("call")==null && !("search").equalsIgnoreCase(request.getParameter("call"))){
	  			List<Customer> customers =(List<Customer>) getSession().get(ShiplinxConstants.SESSION_BUSINESSFILTER_CUSTOMERID);
	  			getSession().put("CUSTOMERS", customers);
	  			}
	  			/*List<String> toProvince =new ArrayList<String>();
	  			toProvince.add("ON");
	  			toProvince.add("NW");
	  			List<String> fromProvince =new ArrayList<String>();
	  			fromProvince.add("ON");
	  			fromProvince.add("NW");
	  		    getSession().put("TO_PROVINCE",toProvince);
	  			getSession().put("FROM_PROVINCE",fromProvince);*/
	  			if(request.getParameter("call")!=null && request.getParameter("call").equalsIgnoreCase("search")){
	  				setBusinessMarkup(businessMarkup);
	  				if(businessMarkup.getFromCountryCode().equalsIgnoreCase("ANY"))
	  					businessMarkup.setFromCountryCode(null);
	  				if(businessMarkup.getToCountryCode().equalsIgnoreCase("ANY"))
	  					businessMarkup.setToCountryCode(null);
	  				}
	  			else {
	  				businessMarkup=new BusinessMarkup();
	  				businessMarkup.setBusinessId(user.getBusinessId());
	  			}
	  			List<BusinessMarkup> businessMarkupList =new ArrayList<BusinessMarkup>();
	  			if (this.markupManagerService != null) {
	  				businessMarkupList = this.markupManagerService
	  						.getBusinessMarkupListForCustomer(businessMarkup);
	  			}
	  				if (businessMarkupList != null && businessMarkupList.size()>0)
	  					getSession().put("MARKUPLIST", businessMarkupList);
	  				else
	  					getSession().put("MARKUPLIST", businessMarkupList);
	  			return SUCCESS;
	  		}
	  		
	  		  public String listCarriers() throws Exception {
	  			    String strReturn = null;
	  			    try {
	  			      String CustomerId = request.getParameter("value");
	  			      User user = UserUtil.getMmrUser();
	  			      if (CustomerId != null && !CustomerId.isEmpty()) {
	  			        if (user != null && user.getUserRole().equalsIgnoreCase("BusAdmin")) {
	  			          carriers =getCarriers();
	  			          getSession().put("CARRIERS", carriers);
	  			        }
	  			      }
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
	  		  
	  		public String listCustomers() throws Exception {
	  			String strReturn = null;
	  			try {
	  				String businessId = request.getParameter("value");
	  				List<Customer> customers=new ArrayList<Customer>();
	  				customers=customerService.getAllCustomerForBusiness(Long.parseLong(businessId));
	  				getSession().put("CUSTOMERS", customers);
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
	  	
	  	/*	public String listProvinces() throws Exception {
	  			String callFor = request.getParameter("for");
	  			String countryCode = request.getParameter("value");
	  			List<String> province =new ArrayList<String>();
	  			province.add("ON");
	  			province.add("NW");
	  			if(callFor!=null && ("toCountry").equals(callFor))
	  				getSession().put("TO_PROVINCE", province);
	  			else
	  			getSession().put("FROM_PROVINCE", province);
	  			return SUCCESS;
	  		}
	  	*/
	  		public String addBusinessMarkup() throws Exception {
	  			try {
	  				User user = UserUtil.getMmrUser();
	  				BusinessMarkup markup = this.getBusinessMarkup();
	  				markup.setBusinessId(user.getBusinessId());
	  				if(markup.getMarkupPercentage()==null){
	  					markup.setMarkupPercentage(0);
	  				}
	  				if(markup.getMarkupFlat()==null){
	  					markup.setMarkupFlat(0.0);
	  				}
	  				if(markup.getType()==null){
	  					markup.setType(1);
	  				}
	  				if(markup.getFromCost()==null){
	  					markup.setFromCost(0.0);
	  				}
	  				if(markup.getToCost()==null){
	  					markup.setToCost(0.0);
	  				}
	  				if(markup.getCustomerId()<0){
	  					markup.setCustomerId(0L);
	  				}
	  				if(markup.getBusinessToId()<0){
	  					addActionError("Please select business for which you are applying markup");
	  					return INPUT;
	  				}
	  				if(markup.getCarrierId()<0){
	  					addActionError("Please select carrier and its service");
	  					return INPUT;
	  				}
	  				if(markup.getMarkupPercentage()==0 && markup.getMarkupFlat()==0){
	  					addActionError("Please give value > 0 for flat or markuppercentage");
	  					return INPUT;
	  				}
	  				
	  				if (markup != null && this.markupManagerService != null
	  						&& markup.getFromCountryCode() != null
	  						&& (!markup.getFromCountryCode().isEmpty())
	  						&& markup.getToCountryCode() != null
	  						&& (!markup.getToCountryCode().isEmpty())
	  						&& markup.getServiceId() != null
	  						&& (!markup.getServiceId().equals(-1L))) {
	  	
	  					/*if (!validateWeightParameters(markup)) {
	  						addActionError(getText("error.markup.invalid.weight"));
	  						return INPUT;
	  					}*/
	  					BusinessMarkup m = this.markupManagerService.addBusinessMarkup(markup);
	  					if (m != null) {
	  						// this.getMarkupList().add(0, m);
	  						throw new MarkupAlreadyExistsException(
	  								"Markup already exists - " + m.toString());
	  					}
	  					List<BusinessMarkup> businessMarkupList =new ArrayList<BusinessMarkup>();
	  					if (this.markupManagerService != null) {
	  						businessMarkupList = this.markupManagerService
	  								.getBusinessMarkupListForCustomer(markup);
	  					}
	  						if (businessMarkupList != null && businessMarkupList.size()>0)
	  							getSession().put("MARKUPLIST", businessMarkupList);
	  						else
	  							getSession().put("MARKUPLIST", businessMarkupList);
	  				}
	  			} catch (MarkupAlreadyExistsException ue) {
	  				addActionError(getText("error.markup.exists") + " - "
	  						+ ue.getMessage());
	  				return INPUT;
	  			} catch (Exception e) {
	  				e.printStackTrace();
	  				addActionError(getText("content.error.unexpected"));
	  				return INPUT;
	  			}
	  			addActionMessage("successfull inserted");
	  			return SUCCESS;
	  		}
	  	
	  		public String deleteBusinessMarkup() throws Exception {
	  			try {
	  				loadBusinessMarkupLists();
	  				String serString = request.getParameter("serviceId");
	  				Long serviceId = 0L;
	  				if (serString != null)
	  					serviceId = Long.parseLong(serString);
	  				String busString1 = request.getParameter("businessId");
	  				Long busId = 0L;
	  				if (busString1 != null)
	  					busId=Long.parseLong(busString1);
	  				String busString2 = request.getParameter("businessToId");
	  				Long busToId = 0L;
	  				if (busString2 != null)
	  					busToId=Long.parseLong(busString2);
	  				BusinessMarkup markup = this.getBusinessMarkup();
	  				if (markup.getFromCountryCode() == null
	  						|| markup.getFromCountryCode().length() == 0)
	  					markup.setFromCountryCode("ANY");
	  				if (markup.getToCountryCode() == null
	  						|| markup.getToCountryCode().length() == 0)
	  					markup.setToCountryCode("ANY");
	  				if (markup.getFromCountryProvince() == null
	  						|| markup.getFromCountryProvince().length() == 0)
	  					markup.setFromCountryProvince("ANY");
	  				if (markup.getToCountryProvince() == null
	  						|| markup.getToCountryProvince().length() == 0)
	  					markup.setToCountryProvince("ANY");
	  				if(markup.getCustomerId()<0)
	  					markup.setCustomerId(0L);
	  				if(markup.getBusinessId()!=null)
	  					markup.setBusinessId(1L);
	  				if(markup.getBusinessToId()!=null)
	  					markup.setBusinessToId(0L);
	  				String fromCountryCode = request.getParameter("fromCountryCode");
	  				String toCountryCode = request.getParameter("toCountryCode");
	  				markup = findBusinessMarkup(serviceId, fromCountryCode,
	  						toCountryCode,busString1,busString2);
	  				if (markup != null)
	  					this.markupManagerService.deleteBusinessMarkup(markup);
	  			} catch (Exception e) {
	  				e.printStackTrace();
	  				addActionError(getText("content.error.unexpected"));
	  			}
	  			return businessMarkup();
	  		}
	  		
	  		private BusinessMarkup findBusinessMarkup(Long serviceId,
	  				String fromCountryCode, String toCountryCode, String busString1,
	  				String busString2) {
	  			// TODO Auto-generated method stub
	  			if (this.businessMarkupList != null) {
	  				for (BusinessMarkup m : this.businessMarkupList) {
	  					if (m.getServiceId().equals(serviceId)
	  							&& m.getFromCountryCode().equals(fromCountryCode)
	  							&& m.getToCountryCode().equals(toCountryCode)) {
	  						if (m.getBusinessId()==Long.parseLong(busString1) && m.getBusinessToId()==Long.parseLong(busString2)) {
	  							return m;
	  						}
	  					}
	  				}
	  			}
	  			return null;
	  		}
	  	
	  		 private void loadBusinessMarkupLists() {
	  			    if (this.businessMarkupList == null)
	  			      this.businessMarkupList = (List<BusinessMarkup>) getSession().get("MARKUPLIST");
	  			  }
	  		 public String saveBusinessMarkupList() throws Exception {
	  		  		try {
	  		  		Boolean eshipCarrierUpdated=false;
	  		  			shippingService = (ShippingService) MmrBeanLocator.getInstance()
	  		  					.findBean("shippingService");
	  		  			loadBusinessMarkupLists();
	  		  			User user = UserUtil.getMmrUser();
	  		  			String carrierId = request.getParameter("carrierId");
	  		  		if (carrierId != null) {
	  		  				if (carrierId.equalsIgnoreCase("6")) {
	  		  				EshipplusCarrierFilter eshipplusCarrierFilter = new EshipplusCarrierFilter();
	  		  					String carrierName = request.getParameter("carrierName");
	  		  					carrierName=carrierName.replace('~', '&');
	  		  					String carrierInactive = request.getParameter("carrierInactive");
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
	  		  		String flat = request.getParameter("flat");
	  		  			String disabledFlag = request.getParameter("disabledFlag");
	  		  			String variable = request.getParameter("variable");
	  		  			String item[] = selectedItem.split(",");
	  		  			String mPs[] = percentage.split(",");
	  		  			String mFs[] = flat.split(",");
	  		  			String mDs[] = disabledFlag.split(",");
	  		  			String mVs[] = variable.split(",");
	  		  			// if (this.getMarkupList().size() != item.length)
	  		  			for (int i1 = 0; i1 < item.length; i1++) {
	  		  				if (!item[i1].equalsIgnoreCase("")) {
	  		  					// for (int i = 0; i < this.getMarkupList().size(); i++) {
	  		  					BusinessMarkup m = this.businessMarkupList.get(Integer.parseInt(item[i1]));					int p = Integer.parseInt(mPs[i1]);
	  		  					double f = Double.parseDouble(mFs[i1]);
	  		  				boolean d = convertStringToBoolean(mDs[i1]);
	  		  					int v = Integer.parseInt(mVs[i1]);
	  		  					if (isBusinessMarkupDirty(m, p, f, d, v)) {
	  		  						m.setMarkupPercentage(p);
	  		  					m.setMarkupFlat(f);
	  		  						m.setDisabledFlag(d);
	  		  						m.setVariable(v);
	  		  						// If user is in Customer Markup and modified default
	  		  					// markup
	  		  						// it should be added as a new record customer specific
	  		  						// markup
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
	  		  		return SUCCESS;
	  		  	}
	  		 private boolean isBusinessMarkupDirty(BusinessMarkup m, int p, double f, boolean d, int v) {
	  			 if (m.getMarkupPercentage().intValue() != p || m.getMarkupFlat().doubleValue() != f
	  					 || m.getDisabledFlag() != d || m.getVariable() != v){
	  				 return true;
	  			 }
	  			 return false;
	  		 }
	  	 
}
