package com.meritconinc.shiplinx.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Currency;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Locale;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.cwsi.eshipper.carrier.ups.rate.SubTotalDocument.SubTotal;
import com.meritconinc.mmr.model.admin.UserSearchCriteria;
import com.meritconinc.mmr.model.security.User;
import com.meritconinc.mmr.service.UserService;
import com.meritconinc.mmr.utilities.MmrBeanLocator;
import com.meritconinc.mmr.utilities.StringUtil;
import com.meritconinc.mmr.utilities.security.UserUtil;
import com.meritconinc.shiplinx.dao.BusinessDAO;
import com.meritconinc.shiplinx.dao.ShippingDAO;
import com.meritconinc.shiplinx.model.ARTransaction;
import com.meritconinc.shiplinx.model.Business;
import com.meritconinc.shiplinx.model.CarrierChargeCode;
import com.meritconinc.shiplinx.model.Charge;
import com.meritconinc.shiplinx.model.ChargeGroup;
import com.meritconinc.shiplinx.model.Commission;
import com.meritconinc.shiplinx.model.CreditCard;
import com.meritconinc.shiplinx.model.CurrencySymbol;
import com.meritconinc.shiplinx.model.Customer;
import com.meritconinc.shiplinx.model.Invoice;
import com.meritconinc.shiplinx.model.InvoiceCharge;
import com.meritconinc.shiplinx.model.InvoiceStatus;
import com.meritconinc.shiplinx.model.SalesRecord;
import com.meritconinc.shiplinx.model.ShippingOrder;
import com.meritconinc.shiplinx.model.UserBusiness;
import com.meritconinc.shiplinx.service.CustomerManager;
import com.meritconinc.shiplinx.service.InvoiceManager;
import com.meritconinc.shiplinx.service.ShippingService;
import com.meritconinc.shiplinx.utils.FormattingUtil;
import com.meritconinc.shiplinx.utils.PDFRenderer;
import com.meritconinc.shiplinx.utils.ShiplinxConstants;
import com.meritconinc.shiplinx.model.FutureReference;
import com.meritconinc.shiplinx.model.FutureReferencePackages;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

import java.util.Locale;
import java.util.Iterator;

import com.meritconinc.mmr.constants.Constants;
import com.meritconinc.shiplinx.dao.BusinessDAO;
import com.meritconinc.shiplinx.model.Business;
import com.opensymphony.xwork2.ActionContext;
import com.soluship.businessfilter.util.BusinessFilterUtil;

public class InvoiceManagerAction extends BaseAction implements Preparable, ServletRequestAware,
    ServletResponseAware{
  private static final long serialVersionUID = 250927861;
  private List<ShippingOrder> orders;
  private List<Charge> chargeList;
  private List<InvoiceCharge> invoiceChargeList;
  
  private List<Invoice> invoicesNew;

  private static final Logger log = LogManager.getLogger(InvoiceManagerAction.class);
  private InvoiceManager invoiceManager;
  private ShippingService shippingService;
  public HttpServletRequest request;
  public HttpServletResponse response;
  private CustomerManager customerService;
  private List<Invoice> invoices;
  private List<SalesRecord> salesRecords;
  private ShippingDAO shippingDAO;
  private List<InvoiceStatus> statusList;
  private Customer customer;
  private List<ShippingOrder> selectedOrders;
  private List<Invoice> selectedInvoices;
  private List<User> salesUsers;
  private UserService userService;
  private List<CurrencySymbol> currencyList;
  private List<Boolean> select;
  private CreditCard creditCard;
  private List<ARTransaction> arTransactions;
  
  private SubTotal subtotals;

  private SalesRecord salesRecord;
  private Integer statusId;

  private List<FutureReference> fCustomers=new ArrayList<FutureReference>();
  private List<FutureReference> showFutureCust=new ArrayList<FutureReference>();
  private List<FutureReferencePackages>showfutRefPackageList=new ArrayList<FutureReferencePackages>();
  public static final List<String> MONTH_LIST = createMonthList();

  private String salesRep;

  private List<Customer> customers;
  private String id;
  private String arInvoiceId;
  private String customerName;
  private String invoiceNumber;
  
  private List<String> InvoiceIdList = new ArrayList<String>();
  private List<Commission> commissions;
  private String paymentStatus;
  private List<Invoice> invoiceBreakdown;
  private FutureReference fc;
  private List<Invoice> invoiceBreakdownList;
  private BusinessDAO businessDAO;
  Invoice invoiceModel = new  Invoice();
  private User loginedUser;
  

public List<Invoice> getInvoiceBreakdown() {
	return invoiceBreakdown;
}

public void setInvoiceBreakdown(List<Invoice> invoiceBreakdown) {
	this.invoiceBreakdown = invoiceBreakdown;
}

public List<Invoice> getInvoiceBreakdownList() {
	return invoiceBreakdownList;
}

public void setInvoiceBreakdownList(List<Invoice> invoiceBreakdownList) {
	this.invoiceBreakdownList = invoiceBreakdownList;
}

public List<Invoice> getInvoicesNew() {
	  	return invoicesNew;
	  }
	  
	  public void setInvoicesNew(List<Invoice> invoicesNew) {
	  	this.invoicesNew = invoicesNew;
	  }
  public ShippingDAO getShippingDAO() {
		return shippingDAO;
	}

	public void setShippingDAO(ShippingDAO shippingDAO) {
		this.shippingDAO = shippingDAO;
	}

public List<Commission> getCommissions() {
	return commissions;
}

public void setCommissions(List<Commission> commissions) {
	this.commissions = commissions;
}

public String getPaymentStatus() {
	return paymentStatus;
}

public void setPaymentStatus(String paymentStatus) {
	this.paymentStatus = paymentStatus;
}

public String getSalesRep() {
    return salesRep;
  }

  public void setSalesRep(String salesRep) {
    this.salesRep = salesRep;
  }

  private Map<String, Long> invoiceSearchResult = new HashMap<String, Long>();

  private InputStream fileInputStream;

  @Override
  public void prepare() throws Exception {
    // TODO Auto-generated method stub

  }

  @Override
  public void setServletRequest(HttpServletRequest request) {
    // TODO Auto-generated method stub
    this.request = request;
  }

  /**
   * sets the HttpServletRequest
   * 
   * @param httpServletRequest
   */
  public void setServletResponse(HttpServletResponse httpServletResponse) {
    this.response = httpServletResponse;
  }

  public void setInvoiceManager(InvoiceManager invoiceManager) {
    this.invoiceManager = invoiceManager;
  }

  public void setCustomerService(CustomerManager customerService) {
    this.customerService = customerService;
  }

  public void setShippingService(ShippingService shippingService) {
    this.shippingService = shippingService;
  }

  public InputStream getFileInputStream() {
    return fileInputStream;
  }

  public void setFileInputStream(InputStream fileInputStream) {
    this.fileInputStream = fileInputStream;
  }

  public UserService getUserService() {
    return userService;
  }

  public void setUserService(UserService userService) {
    this.userService = userService;
  }

  public List<Customer> getCustomers() {
    return customers;
  }

  public void setCustomers(List<Customer> customers) {
    this.customers = customers;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getCustomerName() {
    return customerName;
  }

  public void setCustomerName(String customerName) {
    this.customerName = customerName;
  }

  public String getInvoiceNumber() {
    return invoiceNumber;
  }

  public void setInvoiceNumber(String invoiceNumber) {
    this.invoiceNumber = invoiceNumber;
  }

  public String getArInvoiceId() {
    return arInvoiceId;
  }

  public void setArInvoiceId(String arInvoiceId) {
    this.arInvoiceId = arInvoiceId;
  }

  public String execute() throws Exception {
	  String method = request.getParameter("method");
	  if(method!=null){
		  return searchInvoice();
	  }
	  getSession().remove("invoice");
	  Customer c = new Customer();
	    /*c.setBusinessId(UserUtil.getMmrUser().getBusinessId());
	    customers = customerService.search(c);*/
	  Long businessId=BusinessFilterUtil.setBusinessIdbyUserLevel();
	  	  	   BusinessDAO businessDAO=(BusinessDAO)MmrBeanLocator.getInstance().findBean("businessDAO");
	  	  	  if(businessId==0 && UserUtil.getMmrUser().getUserRole().equals(Constants.SYS_ADMIN_ROLE_CODE)){
	  	  			List<Business>	allbusList=businessDAO.getHoleBusinessList();
	  	  				if(allbusList!=null && allbusList.size()>0){
	  	  					 List<Long> busids=new ArrayList<Long>();
	  	  					for(Business bs:allbusList){
	  	  						 busids.add(bs.getId());
	  	  					}
	  	  					 
	  	  				}
	  	  				businessId=UserUtil.getMmrUser().getBusinessId();
	  	  			} 
	  	  	  	  if(businessId!=null && !businessId.equals("")){
	  	  	  		  c.setBusinessId(businessId);
	  	  	  	  }else{
	  	  	  		  c.setBusinessId(UserUtil.getMmrUser().getBusinessId());
	  	  	  	  }
	  	  	  	  
	  	    	  
	  	  	    customers = (List<Customer>)ActionContext.getContext().getSession().get(ShiplinxConstants.SESSION_BUSINESSFILTER_CUSTOMERID);
	    Invoice invoice = new Invoice();
	    if(businessId!=null && !businessId.equals("")){
	    		    		    				  invoice.setBusinessId(businessId);
	    		    		    			  }else{
	    		    		    				  invoice.setBusinessId(UserUtil.getMmrUser().getBusinessId());
	    		    		    			  }
	      if (UserUtil.getMmrUser().getCustomerId() > 0)
	        invoice.setCustomerId(UserUtil.getMmrUser().getCustomerId());
	      if (!StringUtil.isEmpty(UserUtil.getMmrUser().getBranch()))
	        invoice.setBranch(UserUtil.getMmrUser().getBranch());
	     // invoices = this.invoiceManager.searchInvoices(invoice);
	      //invoicesNew = this.invoiceManager.searchInvoices(invoice);
	      	      /*for (Invoice i : invoices) {
	        invoiceSearchResult.put(i.getInvoiceNum(), i.getInvoiceId());
	      	    }*/
	      	    	     /* User user = UserUtil.getMmrUser();
	      	    	      if(user.getUserRole().equals("busadmin")){
	      	    	    	  invoices.removeAll(invoices);
	      }
	      	      
    getSession().remove("invoice");*/
	   // To find the credit available for the customer
	      try{  
  	      		if(UserUtil.getMmrUser().getCustomerId() > 0){
  	      	    	  c = this.customerService.getCustomerInfoByCustomerId(invoice.getCustomerId());
  	      	    	  CurrencySymbol cur = new CurrencySymbol();
  	      	    	  double availableCredit = 0.00;
  	      		    	  
        			    		  if(c.getDefaultCurrency() != null && !(c.getDefaultCurrency().isEmpty())){
        			    			 cur = this.shippingService.getSymbolByCurrencycode(c.getDefaultCurrency());
        			    		  } else {
  	      			    			cur = this.shippingService.getSymbolByCurrencycode("CAD");
        			    		  }
  	      			    		Locale locale = null;
  				    	          if (cur != null){
  				    	          	locale = new Locale(cur.getLanguageCode()!=null?cur.getLanguageCode():"en", cur.getCountryCode());
  				    	          }else{
  				    	          	locale = new Locale("en", "CA");
  				    	          }
  				    	   if((c.getCreditLimit() != null && c.getCreditLimit().compareTo(new BigDecimal(0.00))!=0) ){
  			    	      	NumberFormat currencyFormatter = NumberFormat.getNumberInstance(locale);
  			    	      	availableCredit=this.customerService.getAvailableCredit(invoice.getCustomerId());
  			    	      	getSession().put("availableCredit",currencyFormatter.format(availableCredit));
  			    	      	getSession().put("currencyS", cur.getCurrencySymbol());
  	      		    	  }else{
  	      		    		getSession().put("availableCredit",availableCredit);
  			    	      	getSession().put("currencyS", cur.getCurrencySymbol());
  	      		    	  }
  	      	      }
  	      	    } catch(Exception e){
  	      	    	e.printStackTrace();
  	      	    }
    log.debug("In execute of InvoiceAction");
    this.statusList = invoiceManager.getInvoiceStatusList();
    return SUCCESS;
  }

  public String showFutureRef()
    {
  	  String id=request.getParameter("id");
  	  long id1=Long.valueOf(id);
  	  fc=invoiceManager.showFutureReference(id1);
  	  showfutRefPackageList=invoiceManager.showFutureReferencePackage(id1);
  	 	 
  	  return SUCCESS;
    }    
  public String deletefutureRef()
    {
		String id = request.getParameter("futureRefId");
		String[] ids = id.split(",");

		for (int i = 0; i < ids.length; i++) {
			long id2 = Long.valueOf(ids[i]);
			System.out.println(id2);
			invoiceManager.deleteFutureReference(id2);

		}

		return SUCCESS;
    }

    
    public String futureRef(){
    	List<Long> businessIds=BusinessFilterUtil.getBusIdParentId(BusinessFilterUtil.setBusinessIdbyUserLevel());
    	  	fCustomers=invoiceManager.getFutureReference(businessIds);
  	  return SUCCESS;
    }

    public String searchInvoice() {

	  log.debug("In searchInvoice");
	  	    String search = request.getParameter("search");
	  	    log.debug("Search:-----" + search);
	  	    Invoice i = this.getInvoice();
	  	    this.statusList = invoiceManager.getInvoiceStatusList(); // This is to make sure the list
	  	                                                             // statusList does'nt go as null back
	  	                                                             // to the form.
	  	    Customer c = new Customer();
	  	    /*c.setBusinessId(UserUtil.getMmrUser().getBusinessId());
	  	    customers = customerService.search(c);*/
	  	  Long businessId=BusinessFilterUtil.setBusinessIdbyUserLevel();
	  		  		  		  	  if(businessId!=null &&businessId>0){
	  		  		  		  		  c.setBusinessId(businessId);
	  		  		  		  	  }
	  		  	  	   customers = (List<Customer>)ActionContext.getContext().getSession().get(ShiplinxConstants.SESSION_BUSINESSFILTER_CUSTOMERID);
	  	  if (search != null && search.equalsIgnoreCase("outstanding")) {
	  			      i = new Invoice();
	  			      i.setPaymentStatusList(new int[] { Invoice.INVOICE_STATUS_UNPAID,
	  			          Invoice.INVOICE_STATUS_PARTIAL_PAID });
		} /*else {
			int a[] = new int[this.statusList.size()]; // statusList does'nt go
														// as null back // to
														// the form.
			for (int j = 0; j < this.statusList.size(); j++) {
				a[j] = this.statusList.get(j).getId().intValue();
			}
			i.setPaymentStatusList(a);
	  			    }*/

	  	BusinessDAO businessDAO=(BusinessDAO)MmrBeanLocator.getInstance().findBean("businessDAO");
	  		  		  	if(businessId!=null &&businessId>0){
	  		  		  			  		  i.setBusinessId(businessId);
	  		  		  			  	  }else{
	  		  		  			  		  i.setBusinessId(UserUtil.getMmrUser().getBusinessId());
	  		  		  			  	  }
	  		  		    	//i.setBusinessIds(BusinessFilterUtil.getBusIdParentId(businessId));
	  		  		    	if(i.getBusinessId()==0 && UserUtil.getMmrUser().getUserRole().equals(Constants.SYS_ADMIN_ROLE_CODE)){
	  		  					List<Business>	allbusList=businessDAO.getHoleBusinessList();
	  		  						if(allbusList!=null && allbusList.size()>0){
	  		  							 List<Long> busids=new ArrayList<Long>();
	  		  						for(Business bs:allbusList){
	  		  								 busids.add(bs.getId());
	  		  							}
	  		  							i.setBusinessIds(busids);
	  		  						}
	  		  					}else{
	  		  					i.setBusinessIds(BusinessFilterUtil.getBusIdParentId(i.getBusinessId()));
	  		  				}
	  		  		    	
	  		  		    List<Long> businessIds=null;
	  		  			  		            if(i.getBusinessIds()!=null & i.getBusinessIds().size()>0){
	  		  			  		            	businessIds=i.getBusinessIds();
	  		  			  		            }
	  		  			  		            List<UserBusiness> ubs=null;
	  		  			  			    	if(UserUtil.getMmrUser()!=null){
	  		  			  		    	    	ubs=BusinessFilterUtil.getUserBusinessByUserName(UserUtil.getMmrUser().getUsername());
	  		  			  		    	    	
	  		  			  		    	    	if(businessIds!=null && businessIds.size()>0 && ubs!=null && ubs.size()>0){
	  		  			  		    	    		List<Long> userBusIds=new ArrayList<Long>();
	  		  			  		    	    		userBusIds.addAll(businessIds);
	  		  			  		    	    		businessIds.clear();
	  		  			  		    	    		userBusIds.addAll(BusinessFilterUtil.getUserBusinessIds(UserUtil.getMmrUser().getUsername(), ubs));
	  		  			  		    	    		businessIds.addAll(BusinessFilterUtil.getvalidatedBusIds(userBusIds));
	  		  			  		    	    		i.setBusinessIds(businessIds);
	  		  			  		    	    	}
	  		  			  			    	}
	  		  			  		  		  
	  		  		    	
	  		  		    	
	  		    if (UserUtil.getMmrUser().getCustomerId() > 0)
	  		      i.setCustomerId(UserUtil.getMmrUser().getCustomerId());
	  		    if (!StringUtil.isEmpty(UserUtil.getMmrUser().getBranch()))
	  		      i.setBranch(UserUtil.getMmrUser().getBranch());
	  		     
	  		      
	  		  if(i.getPaymentStatusList() !=null && i.getPaymentStatusList().length>0 && i.getPaymentStatusList()[0]==50){ 
	  				  				  		  invoices = invoiceManager.searchInvoicesAr(i);
	  				  				  		     }else{
	  				  			  		    	 invoices = invoiceManager.searchInvoicesArSearch1(i);
	  				  				  		     }
	  		    
	  		    log.debug("Found : " + invoices.size() + " invoices");

	  		  if (search != null && search.equalsIgnoreCase("outstanding")) {
	  				      request.setAttribute("postPayment", new Boolean(false));
	  				      this.statusList = invoiceManager.getInvoiceStatusList();
	  				      return "paylist";
	  				    }
	  		  /// Exchange Rate 
	  		/*List<Charge> chargesList = new ArrayList<Charge>();
	  			    double invoiceAmt = 0.0;
	  			    double invoiceCost = 0.0;
	  			    double invoiceTax = 0.0;
	  			    int currencyId=0;
	  			    for(Invoice invoiceResult : invoices){
	  			    	System.out.println("Mohan ======="+invoiceResult.getInvoiceId());
	  			      chargesList =  invoiceManager.getChargeExchangeRateByInvoiceId(invoiceResult.getInvoiceId());
	  			      if(chargesList==null || chargesList.size()==0){
	  			    	   continue;
	  			      }
	  			    currencyId = chargesList.get(0).getChargecurrency();
	  			    for(Charge charge : chargesList){
	  			        
	  			    	if(charge.getExchangerate() != null && !charge.getExchangerate().equals(0.0) && !charge.getExchangerate().equals("")){
	  			    	invoiceAmt += charge.getCharge() * charge.getExchangerate().doubleValue();
	  			        invoiceCost += charge.getCost() * charge.getExchangerate().doubleValue();
	  			          if(charge.getIsTax()){
	  			            invoiceTax += charge.getCharge() * charge.getExchangerate().doubleValue();	       
	  			          }
	  			    	}else{
	  			    		double exRate = 1.0;
	  			    		invoiceAmt += charge.getCharge() * exRate;
	  				        invoiceCost += charge.getCost() * exRate;
	  				          if(charge.getIsTax()){
	  				            invoiceTax += charge.getCharge() * exRate;	       
	  				          }
	  			    	}
	  			      }
	  			       invoiceResult.setInvoiceAmount(invoiceAmt);
	  			       invoiceResult.setInvoiceCost(invoiceCost);
	  			        if(invoiceTax!=0.0){
	  			          invoiceResult.setInvoiceTax(invoiceTax);
	  			        }
	  			    }
	  		  			Invoice invoiceObj = invoiceManager.getInvoiceById(invoices.get(0).getInvoiceId());
	  				    shippingDAO = (ShippingDAO) MmrBeanLocator.getInstance().findBean("shippingDAO");
	  				    CurrencySymbol currencySymbol = new CurrencySymbol();
	  				    if(invoiceObj.getCustomer().getDefaultCurrency()!=null && !invoiceObj.getCustomer().getDefaultCurrency().equals("")){
	  				      currencySymbol = shippingDAO.getSymbolByCurrencycode(invoiceObj.getCustomer().getDefaultCurrency());
	  			        getSession().put("customerDefaultCurrency", currencySymbol.getCurrencySymbol());
	  				    }else{
	  				    	if(UserUtil.getMmrUser().getLocale() != null){
	  				    		if(!UserUtil.getMmrUser().getUserRole().equals(ShiplinxConstants.ROLE_ADMIN)){
	  				    	 currencySymbol = shippingDAO.getCurrencyCodeByCountryName(UserUtil.getMmrUser().getLocale().substring(3,5));
	  				    	}else if(currencyId>0){
	  				    		currencySymbol = shippingDAO.getCurrencyCodeById(currencyId);
	  				    	}
	  				    	 //---------------------
	  					      	  if(currencySymbol==null){
	  					      		  for(int j=0;j<ShiplinxConstants.EURO_UNION_LIST.length;j++){
	  					      			  if(UserUtil.getMmrUser().getLocale().substring(3,5).equalsIgnoreCase(ShiplinxConstants.EURO_UNION_LIST[j])){
	  					      				currencySymbol=shippingService.getCurrencyCodeByCountryName("EUCG");
	  					      				break;
	  					      			  }
	  					      		  }
	  					      	  }
	  				    	 //-------------------------
	  				    	 getSession().put("customerDefaultCurrency", currencySymbol.getCurrencySymbol());
	  				    	}else{
	  				    		getSession().put("customerDefaultCurrency", "$");
	  				    	}
	  				    	
	  				    }*/
	  		  
	  		  
	  		    /// New Code
	  		 for(Invoice invoiceResult : invoices){
	  			 
	  		/*	List<Charge> chargesList =  invoiceManager.getChargeExchangeRateByInvoiceId(invoiceResult.getInvoiceId());
			      if(chargesList==null || chargesList.size()==0){
			    	   continue;
			      }
			      if(chargesList.get(0).getExchangerate() != null && !chargesList.get(0).getExchangerate().equals(0.0) && !chargesList.get(0).getExchangerate().equals("")){
			    	 // invoiceResult.setInvoiceAmount(invoiceResult.getInvoiceAmount() * chargesList.get(0).getExchangerate().doubleValue() );
			    	  //invoiceResult.setInvoiceCost(invoiceResult.getInvoiceCost() * chargesList.get(0).getExchangerate().doubleValue());
			    	  //invoiceResult.setInvoiceTax(invoiceResult.getInvoiceTax() * chargesList.get(0).getExchangerate().doubleValue());
			      }*/
	  			 //Invoice invoiceObj = invoiceManager.getInvoiceById(invoiceResult.getInvoiceId());
				    shippingDAO = (ShippingDAO) MmrBeanLocator.getInstance().findBean("shippingDAO");
				    CurrencySymbol currencySymbol = new CurrencySymbol();
				    if(invoiceResult.getCustomer()!=null){
				    if(invoiceResult.getCustomer().getDefaultCurrency()!=null && !invoiceResult.getCustomer().getDefaultCurrency().isEmpty()){
				      currencySymbol = shippingDAO.getSymbolByCurrencycode(invoiceResult.getCustomer().getDefaultCurrency());
			        getSession().put("customerDefaultCurrency", currencySymbol.getCurrencySymbol());
				    }else{
				    	if(UserUtil.getMmrUser().getLocale() != null){
				    	 currencySymbol = shippingDAO.getCurrencyCodeByCountryName(UserUtil.getMmrUser().getLocale().substring(3,5));
				    	 //---------------------
					      	  if(currencySymbol==null && currencySymbol.getCurrencySymbol()==null && currencySymbol.getCurrencySymbol().isEmpty()){
					      		  for(int j=0;j<ShiplinxConstants.EURO_UNION_LIST.length;j++){
					      			  if(UserUtil.getMmrUser().getLocale().substring(3,5).equalsIgnoreCase(ShiplinxConstants.EURO_UNION_LIST[j])){
					      				currencySymbol=shippingService.getCurrencyCodeByCountryName("EUCG");
					      				break;
					      			  }
					      		  }
					      	  }
				    	 //-------------------------
				    	 getSession().put("customerDefaultCurrency", currencySymbol.getCurrencySymbol());
				    	}else{
				    		getSession().put("customerDefaultCurrency", "$");
				    	}
				    	
				    }
				    }}
	  		   
	  		    ///
	  				  ///// End
	  				  return SUCCESS;
	  		  }
	  			   
  

  public void setInvoices(List<Invoice> invoices) {
    this.invoices = invoices;
  }

  public List<Invoice> getInvoices() {
    return invoices;
  }

  public String generateInvoice() {
	  Customer c = new Customer();
	  Long businessId=(Long) ActionContext.getContext().getSession().get(Constants.BUSINESS_ID_SESSION);
	  	  	  businessId=BusinessFilterUtil.setBusinessIdbyUserLevel();
	  	  	    if(businessId!=null && businessId>0){
	  	  	    	c.setBusinessId(businessId);
	  	  	    } else{
	  	  	   	  	  	    	businessId=BusinessFilterUtil.setBusinessIdbyUserLevel();
	  	  	    }
	  	  	    
	  	  	    customers =(List<Customer>) getSession().get(ShiplinxConstants.SESSION_BUSINESSFILTER_CUSTOMERID);
    log.debug("In generateInvoice");
    getSession().remove("invoice");
    Invoice i = this.getInvoice();
    /*    if(businessId!=null && !businessId.equals("")){
            	i.setBusinessId(businessId);
            }else{
            	i.setBusinessId(UserUtil.getMmrUser().getBusinessId());
            	
            }*/
            if(businessId!=null && businessId>0){
            	i.setBusinessId(businessId);
            	i.setBusinessIds(BusinessFilterUtil.getBusIdParentId(businessId));
            }else{
            	i.setBusinessId(businessId);
            }
            
            List<Long> businessIds=null;
                        if(i.getBusinessIds()!=null & i.getBusinessIds().size()>0){
                        	businessIds=i.getBusinessIds();
                        }
                        List<UserBusiness> ubs=null;
            	    	if(UserUtil.getMmrUser()!=null){
                	    	ubs=BusinessFilterUtil.getUserBusinessByUserName(UserUtil.getMmrUser().getUsername());
                	    	
                	    	if(businessIds!=null && businessIds.size()>0 && ubs!=null && ubs.size()>0){
                	    		List<Long> userBusIds=new ArrayList<Long>();
                	    		userBusIds.addAll(businessIds);
                	    		businessIds.clear();
                	    		userBusIds.addAll(BusinessFilterUtil.getUserBusinessIds(UserUtil.getMmrUser().getUsername(), ubs));
                	    		businessIds.addAll(BusinessFilterUtil.getvalidatedBusIds(userBusIds));
                	    		i.setBusinessIds(businessIds);
                	    	}
            	    	}
                        
    i.setCustomerId(new Long(0));

    if(i.getBusinessId()!=null){
    	    	     orders = shippingService.getUnbilledShipments(i.getBusinessId(), i.getCustomerId().longValue(),
        UserUtil.getMmrUser().getBranch());
    }
    this.setOrders(filterShipments(this.getOrders()));
    log.debug("Found : " + orders.size() + " shipments that can be billed");
    return SUCCESS;
  }

  private List<ShippingOrder> filterShipments(List<ShippingOrder> shipments) {
	  	  		// TODO Auto-generated method stub
	  	  		List<Customer> filCus=(List<Customer>) getSession().get(ShiplinxConstants.SESSION_BUSINESSFILTER_CUSTOMERID);
	  	  		List<ShippingOrder> filteredShippments=new ArrayList<ShippingOrder>();
	  	  		/*Iterator<ShippingOrder> ships=shipments.iterator();
	  	  		Iterator<Customer> customers=filCus.iterator();*/
	  	  		if(filCus!=null && filCus.size()>0 && shipments.size()>0 && shipments!=null){
	  	  			for(ShippingOrder so: shipments){
	  	  				for(Customer c:filCus){
	  	  					if(so.getCustomerId()!=null && c.getId()!=0){
	  	  					if(so.getCustomerId()==c.getId()){
	  	  						
	  	  					filteredShippments.add(so);
	  	  						
	  	  					}
	  	  				}
	  	  				}
	  	  			}
	  	  		
	  	  		}
	  	  		
	  	  		return filteredShippments;
	  	  }
  
  public String searchInvoiceableShipments() {

    Invoice i = this.getInvoice();
    i.setBusinessId(UserUtil.getMmrUser().getBusinessId());
    if (i.getCustomerId() == null)
      i.setCustomerId(new Long(0));
    orders = shippingService.getUnbilledShipments(i.getBusinessId(), i.getCustomerId().longValue(),
        UserUtil.getMmrUser().getBranch());

    log.debug("Found : " + orders.size() + " shipments that can be billed");

    if (i.getCustomerId() > 0) {
      try {
        customer = customerService.getCustomerInfoByCustomerId(i.getCustomerId());
      } catch (Exception e) {
      }
    }

    String type = request.getParameter("type");
    if (type != null && type.equalsIgnoreCase("refresh"))
      return type;
    else
      return SUCCESS;
  }

  private void updateStatus(String ids[]){
		  ShippingOrder order =new ShippingOrder();
		  
		  Set<Long> s = new HashSet<Long>();
		    for(int i=0;i<ids.length;i++){
		    	long custId;
		    	long id2=Long.valueOf(ids[i]);
		    	order=this.shippingService.getShippingOrder(id2);
		    	
		    	custId=order.getCustomerId();
		    	if(!s.contains(custId)){
		    		invoiceManager.updateCustomerStatus(custId,ShiplinxConstants.CUSTOMER_STATUS);
		    		
		    	}
		    	s.add(custId);
		    		    	
		    }
	  
	}
  
  public String createInvoice() {

	    log.debug("In createInvoice");
	    ShippingOrder order = new ShippingOrder();
	    Invoice invoice = this.getInvoice();
	    List<Long> orderIds = new ArrayList<Long>();
	    String id = request.getParameter("InvoiceIdList");
	    String[] ids = id.split(",");
	    for(int i=0;i<ids.length;i++){
	     orderIds.add(Long.valueOf(ids[i]));
	    }
	    
	   /* for (int i = 0; i < select.size(); i++) {
	      // If this checkbox was selected:
	      if (select.get(i) != null && select.get(i)) {
	        // Get the matching test scenario:
	        ShippingOrder order = this.getSelectedOrders().get(i);
	        // ...and launch it:
	        orderIds.add(order.getId());
	      }
	    }*/

	    if (orderIds.size() > 0)
	      invoices = invoiceManager.createInvoices(orderIds, invoice);
	    String args[] = new String[1];
	    args[0] = String.valueOf(invoices.size());
	    addActionMessage(getText("invoice.created", new String[] { args[0] }));
	    this.statusList = invoiceManager.getInvoiceStatusList();
	    getSession().put("customerDefaultCurrency", invoices.get(0).getInvoiceCurrencySymbol());
	    for (int i = 0; i < ids.length; i++) {
			long id1 = Long.valueOf(ids[i]);
			order = this.shippingService.getShippingOrder(id1);
			int unitOfMeasure;
	
			unitOfMeasure = order.getUnitmeasure();
			if (order!=null && order.getCarrierId() == 3
					&& (order.getServiceId() == 306
							|| order.getServiceId() == 308 || order
							.getServiceId() == 309)) {
				
						
	
						invoiceManager.updateBilledUOM(id1);
    				}
    	
    			}
	    updateStatus(ids);
	    return SUCCESS;
	  }

  public String autoGenInvoices() {

    log.debug("In autogen Invoices");
    ShippingOrder order = new ShippingOrder();
    List<Long> orderIds = new ArrayList<Long>();
    Invoice invoice = this.getInvoice();
    String id = request.getParameter("InvoiceIdList1");
    String[] ids = id.split(",");
    for (int i = 0; i < ids.length; i++) {
    	orderIds.add(Long.valueOf(ids[i]));
    }
    try {
      invoices = invoiceManager.autoGenInvoicesForBusiness(UserUtil.getMmrUser().getBusinessId(),
          invoice, UserUtil.getMmrUser().getBranch());
    } catch (Exception e) {
      getActionErrors().add(getText("invoice.autoGen.failed"));
    }

    this.statusList = invoiceManager.getInvoiceStatusList();
    String args[] = new String[1];
    args[0] = String.valueOf(invoices.size());
    for (int i = 0; i < ids.length; i++) {
    			long id1 = Long.valueOf(ids[i]);
    			order = this.shippingService.getShippingOrder(id1);
    			int unitOfMeasure;
    	
    			unitOfMeasure = order.getUnitmeasure();
    			if (order!=null && order.getCarrierId() == 3
    					&& (order.getServiceId() == 306
    							|| order.getServiceId() == 308 || order
    							.getServiceId() == 309)) {
    				
    						
    						invoiceManager.updateBilledUOM(id1);
    			}
    	
    		}
    
    updateStatus(ids);
    this.addActionMessage(getText("invoice.created"));

    return SUCCESS;
  }

  public String payInvoices() {

	  boolean payInvoice=false;
	    Map session = (Map) ActionContext.getContext().getSession();
	    HttpServletRequest request = ServletActionContext.getRequest();
    log.debug("In pay Invoices");
    List<Long> invoiceIds = new ArrayList<Long>();
    String id = request.getParameter("invoiceIdselect");
        String[] ids = id.split(",");
        for(int i=0;i<ids.length;i++){
        	invoiceIds.add(Long.valueOf(ids[i]));
        }
        /*for (int i = 0; i < select.size(); i++) {
      // If this checkbox was selected:
      if (select.get(i) != null && select.get(i)) {
        // Get the matching test scenario:
        Invoice invoice = this.getSelectedInvoices().get(i);
        // ...and launch it:
        invoiceIds.add(invoice.getInvoiceId());
      }
   }*/
   if(invoiceIds.size()>0){
	   payInvoice=true;
   	   session.put("payInvoiceFlag",payInvoice);
    invoices = invoiceManager.processPayment(invoiceIds, creditCard, true);
   }
	   
/*   String args[] = new String[1];
       args[0] = String.valueOf(invoices.size());*/
   for(Invoice invoice:invoices){
    	   if(invoice.getTransaction().getStatus()!=10 && invoice.getTransaction().getProcessorTransactionId()!=null){
    		   addActionMessage("Payment has been Approved and your Receipt Id is: "+invoice.getTransaction().getReceiptId());
    		   if(invoice.getCustomer().getCreditLimit().doubleValue() > 0){
  	    		   long cusId=invoice.getCustomerId();
  	    		   double actualCredit=customerService.getAvailableCredit(cusId);
  	    		   double availableCredit=actualCredit+invoice.getPaidAmount().doubleValue();
  	    		   this.customerService.updateAvailableCredit(availableCredit, cusId);
      		   }
    	   }else{
    		   if(invoice.getTransaction().getProcessorTransactionId()!=null){
    		   addActionError("Payment has been declined and your Declined Receipt Id is: "+invoice.getTransaction().getReceiptId());
    		   }else{
    			   addActionError("Payment has been declined");
    		   }
    	   }
   }
       //request.setAttribute("postPayment", new Boolean(true));
   
    return SUCCESS;
  }

  public String updateAR() {
	  log.debug("In update A/R");
	  getSession().remove("payInvoiceFlag");
	// ActionContext.getContext().getSession().remove("payInvoiceFlag");
	  	    String method = request.getParameter("method");
	  	    log.debug("In update A/R-----method is:" + method);
	  	    /*Customer c = new Customer();
	    c.setBusinessId(UserUtil.getMmrUser().getBusinessId());*/
	  	  Customer c=new Customer();
	  		  	  Long businessId=(Long) ActionContext.getContext().getSession().get(Constants.BUSINESS_ID_SESSION);
	  		  		  	  businessId=BusinessFilterUtil.setBusinessIdbyUserLevel();
	  		  		      if(businessId!=null && !businessId.equals("")){
	  		  		      	c.setBusinessId(businessId);
	  		  		      }else{
	  		  		      	c.setBusinessId(UserUtil.getMmrUser().getBusinessId());
	  		  	      }
	  		  		    //customers = customerService.search(c);
	  		  		      if(UserUtil.getMmrUser().getUserRole().equals(ShiplinxConstants.ROLE_SYSADMIN) && businessId == null){
	  		  		      	customers = BusinessFilterUtil.getSysadminLevelCustomers();
	  		  		      }else{
	  		  		      	customers=(List<Customer>) getSession().get(ShiplinxConstants.SESSION_BUSINESSFILTER_CUSTOMERID);
	  		  		      }
	  		  		  if(customers==null){
	  		  			            	customers=new ArrayList<Customer>();
	  		  			            }
	    if (method != null){	  
	    		    Invoice i = this.getInvoice();
	    		    if((i.getCustomerId() == null || i.getCustomerId()==0 )&&( i.getInvoiceId()==null || i.getInvoiceId()==0)){
	    Invoice invoice = new Invoice();
	    if(businessId!=null && businessId>0){
	    		    		    	invoice.setBusinessId(businessId);
	    		    		    	invoice.setBusinessIds(BusinessFilterUtil.getBusIdParentId(businessId));
	    		    		      }else if(businessId==0){
	    		    		    	  BusinessDAO businessDAO=(BusinessDAO)MmrBeanLocator.getInstance().findBean("businessDAO");
	    		    		  		   List<Business>	allbusList=businessDAO.getHoleBusinessList();
	    		    		    	   List<Long> busids=new ArrayList<Long>();
	    		    					for(Business bs:allbusList){
	    		    						 busids.add(bs.getId());
	    		    					}
	    		    					invoice.setBusinessIds(busids);
	    		    	    	  invoice.setBusinessId(UserUtil.getMmrUser().getBusinessId());
	    		    		      }
	    List<Long> businessIds=null;
	           if(invoice.getBusinessIds()!=null & invoice.getBusinessIds().size()>0){
	            	businessIds=invoice.getBusinessIds();
	            }
	            List<UserBusiness> ubs=null;
	        	if(UserUtil.getMmrUser()!=null){
	    	    	ubs=BusinessFilterUtil.getUserBusinessByUserName(UserUtil.getMmrUser().getUsername());
	    	    	
	    	    	if(businessIds!=null && businessIds.size()>0 && ubs!=null && ubs.size()>0){
	    	    		List<Long> userBusIds=new ArrayList<Long>();
	    	    		userBusIds.addAll(businessIds);
	    	    		businessIds.clear();
	    	    		userBusIds.addAll(BusinessFilterUtil.getUserBusinessIds(UserUtil.getMmrUser().getUsername(), ubs));
	    	    		businessIds.addAll(BusinessFilterUtil.getvalidatedBusIds(userBusIds));
	    	    		invoice.setBusinessIds(businessIds);
	    	    	}
	        	}
	    	  
	    
	    
	      if (UserUtil.getMmrUser().getCustomerId() > 0)
	        invoice.setCustomerId(UserUtil.getMmrUser().getCustomerId());
	      if (!StringUtil.isEmpty(UserUtil.getMmrUser().getBranch()))
	        invoice.setBranch(UserUtil.getMmrUser().getBranch());
	      /*invoices = this.invoiceManager.searchInvoices(invoice);
	      for (Invoice inv : invoices) {
	    	  	        invoiceSearchResult.put(inv.getInvoiceNum(), inv.getInvoiceId());
	      }*/

	    		    }
    i.setPaymentStatusList(new int[] { Invoice.INVOICE_STATUS_UNPAID,
        Invoice.INVOICE_STATUS_PARTIAL_PAID });
    if(businessId!=null && businessId>0){
    	    	    	i.setBusinessId(businessId);
    	    	    	i.setBusinessIds(BusinessFilterUtil.getBusIdParentId(businessId));
    	    	      }else if(businessId==0 ){
    	    	    	  BusinessDAO businessDAO=(BusinessDAO)MmrBeanLocator.getInstance().findBean("businessDAO");
    	    	 		   List<Business>	allbusList=businessDAO.getHoleBusinessList();
    	    	   	   List<Long> busids=new ArrayList<Long>();
    	    				for(Business bs:allbusList){
    	    					 busids.add(bs.getId());
    	    				}
    	    				i.setBusinessIds(busids);
    	    	   	  i.setBusinessId(UserUtil.getMmrUser().getBusinessId());
    	    	      }
    

    List<Long> businessIds=null;
    if(i.getBusinessIds()!=null & i.getBusinessIds().size()>0){
    	businessIds=i.getBusinessIds();
    }
    List<UserBusiness> ubs=null;
	if(UserUtil.getMmrUser()!=null){
    	ubs=BusinessFilterUtil.getUserBusinessByUserName(UserUtil.getMmrUser().getUsername());
    	
    	if(businessIds!=null && businessIds.size()>0 && ubs!=null && ubs.size()>0){
    		List<Long> userBusIds=new ArrayList<Long>();
    		userBusIds.addAll(businessIds);
    		businessIds.clear();
    		userBusIds.addAll(BusinessFilterUtil.getUserBusinessIds(UserUtil.getMmrUser().getUsername(), ubs));
    		businessIds.addAll(BusinessFilterUtil.getvalidatedBusIds(userBusIds));
    		//i.getBusinessIds().clear();
    		i.setBusinessIds(businessIds);
    	}
	}
    
    
    invoices = invoiceManager.searchInvoicesAr(i);
   // log.debug("Found : " + invoices.size() + " invoices");

    if (invoices.size() == 0)
      this.addActionMessage(getText("AR.noInvoices"));
	    		    }
	    		    	    getSession().remove("invoice");
    return SUCCESS;
  }

  public String processAR()  {
	  log.debug("In process AR");
	    List<Invoice> invoicesToUpdate = new ArrayList<Invoice>();
	    String[] ids = null;
	    //this is code is to set value in transaction table
	    	    String modeofpay[]=request.getParameter("modeOfPay").split(",");
	    	    String payrefnum[]=request.getParameter("payRefNum").split(",");
	    	    String payAmount[]=request.getParameter("payAmounts").split(",");
	    String id_list = request.getParameter("InvoiceIdList");
	    ids = id_list.split(","); 
	    
	    for(int i=0;i<ids.length;i++){
	     long id = Long.valueOf(ids[i]);
	     Invoice invoice = new Invoice();
	     invoice = invoiceManager.getInvoiceById(id);
	     invoice.getArTransaction().setAmount(invoice.getTotalInvoiceCharge());
	     invoice.getArTransaction().setModeOfPayment(modeofpay[i]);
	     invoice.getArTransaction().setPaymentRefNum(payrefnum[i]);
	     invoice.getArTransaction().setPayAmount(Double.parseDouble(payAmount[i]));
	     try{     
	      invoicesToUpdate.add(invoice);
	      if(invoicesToUpdate.get(i).getCustomer()!=null && invoicesToUpdate.get(i).getCustomer().getCreditLimit().doubleValue() > 0){
	    	   double actualCredit=customerService.getAvailableCredit(invoicesToUpdate.get(i).getCustomerId());
	    	   double availableCredit=actualCredit+invoicesToUpdate.get(i).getArTransaction().getAmount().doubleValue();
	    	   this.customerService.updateAvailableCredit(availableCredit, invoicesToUpdate.get(i).getCustomerId());
	      }
	     }catch(Exception e){
	      log.error("Error in converting the Invoice id :"+ids[i]);
	     }
	    }

	    /*for ( int i = 0; i < select.size(); i++ ) {
	        // If this checkbox was selected:
	        if ( select.get(i) != null && select.get(i) ) {
	         // Get the matching test scenario:
	         Invoice invoice = this.getSelectedInvoices().get(i);
	         // ...and launch it:
	         invoicesToUpdate.add(invoice);
	        }   
	    }*/
	    
	    if(invoicesToUpdate.size()==0){
	     this.addActionError(getText("AR.process.minInvoices"));
	     return updateAR();
	    }
	    
	    invoiceManager.processAR(invoicesToUpdate);  
	    this.addActionMessage(getText("AR.processed"));
	    return updateAR();
	  
  }

  public String searchAR() {

    log.debug("In search AR");
    String method = request.getParameter("method");    
    /*Customer c = new Customer();
    c.setBusinessId(UserUtil.getMmrUser().getBusinessId());*/
    Long businessId=(Long) ActionContext.getContext().getSession().get(Constants.BUSINESS_ID_SESSION);
            Customer c=new Customer();
            if(businessId!=null && !businessId.equals("")){
            	c.setBusinessId(businessId);
            }else{
            	c.setBusinessId(UserUtil.getMmrUser().getBusinessId());
            }
            
           //customers = customerService.search(c);
            if(UserUtil.getMmrUser().getUserRole().equals(ShiplinxConstants.ROLE_SYSADMIN) && businessId == null){
            	customers = BusinessFilterUtil.getSysadminLevelCustomers();
            }else{
            	customers=(List<Customer>) getSession().get(ShiplinxConstants.SESSION_BUSINESSFILTER_CUSTOMERID);
            }
    customers = customerService.search(c);
    if (method != null){
    	        getSession().remove("arTransaction");
    ARTransaction arTransaction = new ARTransaction();

    Long busId=BusinessFilterUtil.setBusinessIdbyUserLevel();
            if(busId!=null && busId>0){
            	arTransaction.setBusinessIds(BusinessFilterUtil.getBusIdParentId(busId));
            }else if(busId==0 && busId!=null){
            	    BusinessDAO businessDAO=(BusinessDAO)MmrBeanLocator.getInstance().findBean("businessDAO");
            	    List<Long> businessIds=new ArrayList<Long>();
            		List<Business>	allbusList=businessDAO.getHoleBusinessList();
            		if(allbusList!=null && allbusList.size()>0){
            			for(Business bs:allbusList){
            				businessIds.add(bs.getId());
            			}
            			arTransaction.setBusinessIds(businessIds);
           		}
            }
            arTransaction.setBusinessId(busId);
    if (id != null && !id.isEmpty()) {
      arTransaction.setCustomerId(Long.valueOf(id));
      try {
        customer = customerService.getCustomerInfoByCustomerId(Long.valueOf(id));
      } catch (NumberFormatException e) {
        log.debug("Error in getting the customer value for Id : " + id);
        log.debug(e.getMessage());
      } catch (Exception e) {
        // TODO Auto-generated catch block
        log.debug("Error in customer :" + e.getMessage());
      }
      customerName = customer.getName();
    }
    
    Invoice i = this.getInvoice();
    if(i.getInvoiceNum() != null &&!i.getInvoiceNum().isEmpty()){
    	arTransaction.setInvoiceNum(Long.valueOf(i.getInvoiceNum()));
    	
    }

   /* if (arInvoiceId != null && !arInvoiceId.isEmpty()) {
      arTransaction.setInvoiceId(Long.valueOf(arInvoiceId));
      try {
        Invoice invoice = invoiceManager.getInvoiceById(Long.valueOf(arInvoiceId));
        invoiceNumber = invoice.getInvoiceNum();
      } catch (NumberFormatException e) {
        log.debug("Error in getting the invoice value for Id : " + id);
        log.debug(e.getMessage());
      } catch (Exception e) { 
        // TODO Auto-generated catch block
        log.debug("Error in invoice :" + e.getMessage());
      }
    }*/
    arTransactions = invoiceManager.searchARTransaction(arTransaction);
    }
    return SUCCESS;
  }

  public Invoice getInvoice() {
    Invoice invoice = (Invoice) getSession().get("invoice");
    if (invoice == null) {
      invoice = new Invoice();
      setInvoice(invoice);
    }
    return invoice;
  }

  public void setInvoice(Invoice invoice) {

    getSession().put("invoice", invoice);
  }
  public Commission getCommission(){
	  	 Commission commission = (Commission) getSession().get("commission");
	  	    if (commission == null) {
	  	      commission = new Commission();
	  	      setCommission(commission);
	  	    }
	  	    return commission;
	  }
	   
	  
	  public void setCommission(Commission commission) {
	  
	    getSession().put("commission", commission);
	  }
  public SalesRecord getSalesRecord() {
    SalesRecord salesRecord = (SalesRecord) getSession().get("salesRecord");
    if (salesRecord == null) {
      salesRecord = new SalesRecord();
      setSalesRecord(salesRecord);
    }
    return salesRecord;
  }

  public void setSalesRecord(SalesRecord salesRecord) {
	  this.salesRecord = salesRecord;
    getSession().put("salesRecord", salesRecord);
  }

  public ARTransaction getARTransaction() {
    ARTransaction arT = (ARTransaction) getSession().get("arTransaction");
    if (arT == null) {
      arT = new ARTransaction();
      setARTransaction(arT);
    }
    return arT;
  }

  public void setARTransaction(ARTransaction arT) {
    getSession().put("arTransaction", arT);
  }

  public String listInvoices() {
    String searchParameter = request.getParameter("searchStringInvoices");
    List<Invoice> invoices = null;
    if (searchParameter != null) {
      log.debug("Search string is : " + searchParameter);

      Invoice invoice = new Invoice();
      invoice.setBusinessId(UserUtil.getMmrUser().getBusinessId());
      if (UserUtil.getMmrUser().getCustomerId() > 0)
        invoice.setCustomerId(UserUtil.getMmrUser().getCustomerId());
      if (!StringUtil.isEmpty(UserUtil.getMmrUser().getBranch()))
        invoice.setBranch(UserUtil.getMmrUser().getBranch());
      invoices = this.invoiceManager.searchInvoices(invoice);
      for (Invoice i : invoices) {
        invoiceSearchResult.put(i.getInvoiceNum(), i.getInvoiceId());
      }
    }

    return SUCCESS;
  }


  public List<InvoiceStatus> getStatusList() {
    return statusList;
  }

  public void setStatusList(List<InvoiceStatus> statusList) {
    this.statusList = statusList;
  }

  public List<ShippingOrder> getOrders() {
    return orders;
  }

  public void setOrders(List<ShippingOrder> orders) {
    this.orders = orders;
  }

  public Customer getCustomer() {
    return customer;
  }

  public void setCustomer(Customer customer) {
    this.customer = customer;
  }

  public List<ShippingOrder> getSelectedOrders() {
    return selectedOrders;
  }

  public void setSelectedOrders(List<ShippingOrder> selectedOrders) {
    this.selectedOrders = selectedOrders;
  }

  public List<Boolean> getSelect() {
    return select;
  }

  public void setSelect(List<Boolean> select) {
    this.select = select;
  }

  public CreditCard getCreditCard() {
    return creditCard;
  }

  public void setCreditCard(CreditCard creditCard) {
    this.creditCard = creditCard;
  }

  public List<Invoice> getSelectedInvoices() {
    return selectedInvoices;
  }

  public void setSelectedInvoices(List<Invoice> selectedInvoices) {
    this.selectedInvoices = selectedInvoices;
  }

  public List<ARTransaction> getArTransactions() {
    return arTransactions;
  }

  public void setArTransactions(List<ARTransaction> arTransactions) {
    this.arTransactions = arTransactions;
  }

  private static List<String> createMonthList() {
    List<String> result = new ArrayList<String>();
    result.add("01");
    result.add("02");
    result.add("03");
    result.add("04");
    result.add("05");
    result.add("06");
    result.add("07");
    result.add("08");
    result.add("09");
    result.add("10");
    result.add("11");
    result.add("12");
    return result;
  }

  public Map<String, Long> getInvoiceSearchResult() {
    return invoiceSearchResult;
  }

  public void setInvoiceSearchResult(Map<String, Long> invoiceSearchResult) {
    this.invoiceSearchResult = invoiceSearchResult;
  }

  public String getInvoicePdf() {
    try {
      String invoiceId = request.getParameter("invoiceId");
      String salesUser = request.getParameter("salesUser");
      String downloadId = request.getParameter("downloadId");
      final String cookiePath = "/";
      if (invoiceId != null) {
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Content-Disposition","attachment; filename=label.pdf");
        response.setHeader("Expires", "0");
        response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
        response.setHeader("Pragma", "public");
        response.setContentType("application/pdf");
		Cookie myCookie = new Cookie("downloadId", downloadId);
		myCookie.setPath(cookiePath);
		response.addCookie(myCookie);

        Long l = Long.parseLong(invoiceId);
        if(salesUser!=null){
        	 /*this.invoiceManager.getSalesInvoicePdf(l, request.getContextPath(), response.getOutputStream(),
        	            false,salesUser);
        }else{*/
        	 this.invoiceManager.getSalesInvoicePdf(l, request.getContextPath(), response.getOutputStream(),
           	            false,salesUser);
       }else{
        this.invoiceManager.getInvoicePdf(l, request.getContextPath(), response.getOutputStream(),
            false);
        }
      }

    } catch (Exception e) {
      e.printStackTrace();
      log.error(e);
    }

    return null;
  }

  public String cancelInvoice() throws Exception {
	    String invoiceId = request.getParameter("invoiceId");
	    boolean cancelled = false;
	    if (invoiceId != null) {
	      Long l = Long.parseLong(invoiceId);
	      cancelled = invoiceManager.cancelInvoice(l);
	    }

	    if (cancelled) {
	      this.addActionMessage(getText("invoice.cancelled"));
			if (invoiceId != null && !invoiceId.isEmpty()) {
				invoiceManager.deleteCommission(Long.valueOf(invoiceId));
			}
	      // For cancelled invoice duplication
	      invoiceId = request.getParameter("invoiceId");
	      int l = Integer.parseInt(invoiceId);

	      invoiceChargeList = shippingService.getChargeandOrderByInvoiceId(l);

	      for (InvoiceCharge invoiceCharge : invoiceChargeList) {
	        Charge charges = new Charge();
	        charges = shippingService.getChargesByOrderandInvoiceId(invoiceCharge.getChargeId(),
	            invoiceCharge.getOrderId());
	        charges.setStatus(20);
	        shippingService.saveCharge(charges);
	      }

	    } else {
	      this.addActionError(getText("invoice.notCancelled"));
	    }

	    this.statusList = invoiceManager.getInvoiceStatusList();
	    return this.searchInvoice();
	  }

  public String editInvoice() {
    String invoiceId = request.getParameter("invoiceId");
    if(invoiceId !=null){
    getSession().put("invoiceId", invoiceId);
    }
    if (invoiceId != null) {
      long l = Long.parseLong(invoiceId);
      Invoice invoice = invoiceManager.getInvoiceById(l);
      for(ShippingOrder order:invoice.getOrders()){
    	  			for(Charge charge:order.getChargesForInvoice()){
    	  				shippingDAO = (ShippingDAO) MmrBeanLocator.getInstance().findBean("shippingDAO");
    	  				Charge chargeTemp = shippingDAO.getChargeById(charge.getId());
    	  				if(chargeTemp!=null){
    	  					ChargeGroup chargeGroup=shippingDAO.getChargeGroup(chargeTemp.getChargeGroupId());
    	  					int chargeGroupId = 0;
    	  					if(chargeGroup!=null){
    	  						chargeGroupId = (int)chargeGroup.getGroupId();
    	  					}
    	  					CarrierChargeCode ccc = shippingDAO.getChargeByCarrierAndCodesGroup(charge.getCarrierId(),
    	  							charge.getChargeCode(), charge.getChargeCodeLevel2(), chargeGroupId);
    	  					if((ccc!=null && ccc.isTax())||(charge.getChargeCode()!= null &&charge.getChargeCode().equalsIgnoreCase("TAX"))){
    	  						charge.setIsTax(true);
    	  					}else{
    	  						charge.setIsTax(false);
    	  					}
    	  				}
    	  			}
    	  		}
      this.setInvoice(invoice);
      return SUCCESS;
    }

    return ERROR;
  }

  public String updateInvoice() {
    try {
      Invoice invoice = this.getInvoice();
      if (invoice != null) {
        String[] ids = this.request.getParameterValues("actualChargeIds");
        String[] userCharges = this.request.getParameterValues("actualCharge");
        String[] userCosts = this.request.getParameterValues("actualChargeCost");
        String[] userNames = this.request.getParameterValues("actualChargeName");
        String[] trackNos = this.request.getParameterValues("trackingNumbers");
        String checkdTaxId = request.getParameter("calculateTaxId");
        String[] calculateTaxId=checkdTaxId.split(",");
        if(!calculateTaxId[0].isEmpty()){
        	invoice.setCalculateTaxId(Arrays.asList(calculateTaxId));
        }
        invoice = invoiceManager.updateInvoice(invoice, ids, userCharges, userCosts, userNames,
            trackNos);
        this.setInvoice(invoice);
        addActionMessage("Charge Updated Successfully..");
        PDFRenderer pdfRenderer = new PDFRenderer();
        ArrayList<String> srcList = new ArrayList<String>();
  	    Business business = businessDAO.getBusiessById(invoice.getBusinessId());
  	    String reportPathInvoice = business.getReportPathInvoice();
  	    String invoiceNum=invoice.getInvoiceNum();
	      File folderPath = new File(reportPathInvoice);
	      if (folderPath.isDirectory()) {
	    	  File[] fList = folderPath.listFiles();
	    	  if(fList!=null){
	    		  for(File file:fList){
	    			  String fileName=file.getName();
	    			  String[] splitFileName=fileName.split("_");
			  			  if(invoiceNum.equals(splitFileName[1])){
			    				  srcList.add(file.toString());
			    				pdfRenderer.deleteFiles(srcList);
			  			  }
	    		  }
	    	  }
	      }
      }
    } catch (Exception e) {
      e.printStackTrace();
      addActionError(getText("content.error.unexpected"));
    }

    return searchInvoice();
  }

  public String commReport() throws Exception {

    String method = request.getParameter("method");
    String salesRep = request.getParameter("salesRep");
    String invoiceId = request.getParameter("invoiceIdlist");
    Commission commission = this.getCommission();
    UserSearchCriteria criteria = new UserSearchCriteria();
    Long businessId=(Long) ActionContext.getContext().getSession().get(Constants.BUSINESS_ID_SESSION);
            businessId=BusinessFilterUtil.setBusinessIdbyUserLevel();
            
            if(businessId!=null && businessId>0){
            	criteria.setBusinessId(null);
            	criteria.setBusinessIds(BusinessFilterUtil.getBusIdParentId(businessId));
            }else if(businessId==0){
            	  BusinessDAO businessDAO=(BusinessDAO)MmrBeanLocator.getInstance().findBean("businessDAO");
          	      List<Business>	allbusList=businessDAO.getHoleBusinessList();
        			if(allbusList!=null && allbusList.size()>0){
        				 List<Long> busids=new ArrayList<Long>();
        				for(Business bs:allbusList){
        				 busids.add(bs.getId());
        				}
        				criteria.setBusinessIds(busids);
          	            criteria.setBusinessId(null);
        			}
            }
    criteria.setRoleCode(ShiplinxConstants.ROLE_SALES);
    criteria.setSortBy(UserSearchCriteria.SORT_BY_FIRSTNAME);
    if (!StringUtil.isEmpty(UserUtil.getMmrUser().getBranch()))
      criteria.setBranch(UserUtil.getMmrUser().getBranch());
    if(method == null){
    	    if(commission.getSalesUser().equals("-1"))
    	    	commission.setSalesUser(null);
    	    }
    salesUsers = userService.findUsers(criteria, 0, 0);
    if(salesRep!=null && invoiceId!=null){
    	    	updateInvoiceStatus(salesRep,invoiceId);
    	    }
    if (method != null) {
    	getSession().remove("commission");
    	 commission = getCommission();
    	 currencyList=shippingService.getallCurrencySymbol();
      return SUCCESS;
    }


    
        commission.setFromDate(FormattingUtil.getDate(commission.getFromDate_web(),
                FormattingUtil.DATE_FORMAT_WEB));
        commission.setToDate(FormattingUtil.getDate(commission.getToDate_web(),
                FormattingUtil.DATE_FORMAT_WEB));
        String toCurrency = commission.getCurrency();
        commissions = invoiceManager.searchCommissions(commission);
        
        /// ===================== Exchange Rate =========================== 
        for(Commission commissionReport : commissions){
        	
        	Double exRate = 0.0;
        	if(commissionReport.getInvoiceCurrency() != null && !commissionReport.getInvoiceCurrency().isEmpty() &&  toCurrency != null && !toCurrency.isEmpty()){
        		if(commissionReport.getInvoiceCurrency().equals(toCurrency)){
        			exRate = 1.0;
        		}else{
        			exRate = shippingService.getExchangeRate(commissionReport.getInvoiceCurrency(),toCurrency);
        		}
        		if(exRate == null){
        			addActionError("There is no exchange rate in database for selected currency " + toCurrency +", or invoice currency "+ commissionReport.getCurrency());
        			currencyList=shippingService.getallCurrencySymbol();
        			salesRecords.clear();
        			return SUCCESS;
        		}
        		commissionReport.setCommissionPayable(commissionReport.getCommissionPayable() * exRate);
        		commissionReport.setCostTotal(commissionReport.getCostTotal() * exRate);
        		commissionReport.setInvoiceTotal(commissionReport.getInvoiceTotal() * exRate);
        		commissionReport.setTotalSPD(commissionReport.getTotalSPD() * exRate);
        		commissionReport.setTotalCHB(commissionReport.getTotalCHB() * exRate);
        		commissionReport.setTotalLTL(commissionReport.getTotalLTL() * exRate);
        		commissionReport.setTotalFPA(commissionReport.getTotalFPA() * exRate);
        		commissionReport.setTotalFWD(commissionReport.getTotalFWD() * exRate);
        	}
        
        }
      /// ===================== End ===========================
        
        statusId = commission.getRepPaid();
        findPaymentStaus(statusId);
        currencyList=shippingService.getallCurrencySymbol();
        CurrencySymbol currencySymbol = shippingService.getSymbolByCurrencycode(toCurrency);
        getSession().put("commissionCurrencySymbol", currencySymbol.getCurrencySymbol());
    return SUCCESS;
  }
  
  public String invoiceBreakdown() throws Exception{
	  	  String inclCancelldInv=request.getParameter("inclCancelledInv");
	  	String method = request.getParameter("method");
	  	loginedUser=UserUtil.getMmrUser();
	  		  	  if(method!=null){
	  		  		currencyList=shippingService.getallCurrencySymbol();
	  		  		  return SUCCESS;
	  		  	  }
	  	  Commission commission = this.getCommission();
	     String currency = commission.getCurrency();
	     Long businessId=(Long) ActionContext.getContext().getSession().get(Constants.BUSINESS_ID_SESSION);
	     	     	     businessId=BusinessFilterUtil.setBusinessIdbyUserLevel();
	     	     	     if(businessId!=null&&  businessId>0){
	     	     	    	 commission.setBusinessIds(BusinessFilterUtil.getBusIdParentId(businessId));
	     	     	     }else if(businessId!=null && businessId==0){
	     	     	    	   BusinessDAO businessDAO=(BusinessDAO)MmrBeanLocator.getInstance().findBean("businessDAO");
	     	     	    	    List<Business>	allbusList=businessDAO.getHoleBusinessList();
	     	     				if(allbusList!=null && allbusList.size()>0){
	     	     					 List<Long> busids=new ArrayList<Long>();
	     	     				for(Business bs:allbusList){
	     	     						 busids.add(bs.getId());
	     	     					}
	     	     					commission.setBusinessIds(busids);
	     	     	    	 
	     	     				}
	     	     	     }
	          	     if(businessId!=null && businessId>0){
	     	     	    	 commission.setBusinessId(businessId);
	     	     	     }else  if(businessId !=null && businessId==0){
	     	         	 commission.setBusinessId(UserUtil.getMmrUser().getBusinessId());
	     	     	     }
	  	  commission.setFromDate(FormattingUtil.getDate(commission.getFromDate_web(),
	  			  FormattingUtil.DATE_FORMAT_WEB));
	  	  commission.setToDate(FormattingUtil.getDate(commission.getToDate_web(),
	  			  FormattingUtil.DATE_FORMAT_WEB));
	  	if(inclCancelldInv.equalsIgnoreCase("true")){
	  			  		invoiceBreakdown=invoiceManager.searchInvoicesBreakdownIncludeCanceledInvoice(commission);  
	  			  	  }else{
	  	  invoiceBreakdown=invoiceManager.searchInvoicesBreakdown(commission);
	  			  	  }
	  	  List<Invoice> tempInvoiceList=new ArrayList<Invoice>();
	  	  //invoiceBreakdown=invoiceManager.searchCommissions(commission);
	  	  List<Invoice> groupedInvoiceCharges = new ArrayList<Invoice>(); 
	  	  if(inclCancelldInv!=null){
	  		  for(Invoice invoice:invoiceBreakdown){
	  			 double invoiceAmt=0;
	  			 //invoice.setCurrency(currency);
	  				 // groupedInvoiceCharges=invoiceManager.getInvoiceChargeDetails(invoice.getInvoiceId());
	  				  /*for(Invoice grpInv:groupedInvoiceCharges){
	  					  if("SPD".equalsIgnoreCase(grpInv.getEmailType())){
	  						invoice.setTotalSPD(FormattingUtil.roundFigureRates(grpInv.getBreakdownTotal(), 2));
	  					  }
	  					  if("LTL".equalsIgnoreCase(grpInv.getEmailType())){
	  						invoice.setTotalLTL(FormattingUtil.roundFigureRates(grpInv.getBreakdownTotal(), 2));
	  					  }
	  					  if("CHB".equalsIgnoreCase(grpInv.getEmailType())){
	  						invoice.setTotalCHB(FormattingUtil.roundFigureRates(grpInv.getBreakdownTotal(), 2));
	  					  }
	  					if("FPA".equalsIgnoreCase(grpInv.getEmailType())){
	  								  						invoice.setTotalFPA(FormattingUtil.roundFigureRates(grpInv.getBreakdownTotal(), 2));
	  								  						System.out.println("FPA TYPE"+invoice.getTotalFPA());
	  								  				 }
	  							  					 if("FWD".equalsIgnoreCase(grpInv.getEmailType())){
	  								  						invoice.setTotalFWD(FormattingUtil.roundFigureRates(grpInv.getBreakdownTotal(), 2));
	  								  					System.out.println("FWD TYPE"+invoice.getTotalFWD());
	  								  				 }
	  				  }
	  				  if(invoice.getTotalSPD()>0 || invoice.getTotalLTL()>0 || invoice.getTotalCHB()>0 || invoice.getTotalFPA()>0 || invoice.getTotalFWD()>0){
	  					invoiceAmt=invoice.getTotalSPD()+invoice.getTotalLTL()+invoice.getTotalCHB()+invoice.getTotalFPA()+invoice.getTotalFWD();*/
	  			if(invoice.getTotalSPD()>0 || invoice.getTotalLTL()>0 || invoice.getTotalCHB()>0 || invoice.getTotalFWD()>0 || invoice.getTotalFPA()>0){
	  				if(currency!=null && invoice.getCurrency()!=null && currency.equals(invoice.getCurrency())){
	  					invoiceAmt=invoice.getTotalSPD()+invoice.getTotalLTL()+invoice.getTotalCHB()+invoice.getTotalFPA()+invoice.getTotalFWD();
	  				}else{
	  					double tempSPD = invoiceManager.currencyConversion(invoice.getCurrency(), currency, invoice.getTotalSPD());
	  					double tempLTL = invoiceManager.currencyConversion(invoice.getCurrency(), currency, invoice.getTotalLTL());
	  					double tempCHB = invoiceManager.currencyConversion(invoice.getCurrency(), currency, invoice.getTotalCHB());
	  					double tempFPA = invoiceManager.currencyConversion(invoice.getCurrency(), currency, invoice.getTotalFPA());
	  					double tempFWD = invoiceManager.currencyConversion(invoice.getCurrency(), currency, invoice.getTotalFWD());
	  					invoice.setTotalSPD(tempSPD);
	  					invoice.setTotalLTL(tempLTL);
	  					invoice.setTotalCHB(tempCHB);
	  					invoice.setTotalFWD(tempFWD);
	  					invoice.setTotalFPA(tempFPA);
	  					invoiceAmt=invoice.getTotalSPD()+invoice.getTotalLTL()+invoice.getTotalCHB()+invoice.getTotalFPA()+invoice.getTotalFWD();
	  					invoice.setCurrency(currency);
	  					
	  				}
	  					  invoice.setInvoiceAmount(invoiceAmt);
	  				  tempInvoiceList.add(invoice);
	  				  }
	  		  }
	  		  invoiceBreakdown.clear();
	  		currencyList=shippingService.getallCurrencySymbol();
	  		  invoiceBreakdown.addAll(tempInvoiceList);
	  	  }
	  	  return SUCCESS;
	    }
    
  public String salesReport() throws Exception {
	  Customer c = new Customer();
	  Long businessId=(Long) ActionContext.getContext().getSession().get(Constants.BUSINESS_ID_SESSION);
	  	  	   	   
	  	  	   if(UserUtil.getMmrUser().getUserRole().equals(ShiplinxConstants.ROLE_SYSADMIN) && businessId==null){
	  	  		   
	  	  		   customers=BusinessFilterUtil.getSysadminLevelCustomers();
	  	  		   
	  	  	   }else if(businessId!=null || !UserUtil.getMmrUser().getUserRole().equals(ShiplinxConstants.ROLE_SYSADMIN)) {
	  	       	   customers = (List<Customer>) getSession().get(ShiplinxConstants.SESSION_BUSINESSFILTER_CUSTOMERID);
	  	  	   }

    String method = request.getParameter("method");

    if (method != null) {
      /*getSession().remove("salesRecord");
      SalesRecord sales = getSalesRecord();
      currencyList=shippingService.getallCurrencySymbol();
      return SUCCESS;*/
    	getSession().remove("salesRecord");
       if(getSalesRecord() !=null){
    	   Calendar gc=new GregorianCalendar();
	       int year = gc.get(Calendar.YEAR);
	    	int month = gc.get(Calendar.MONTH);
	    	getSalesRecord().setMonth(month+1);
	    	getSalesRecord().setMonthName(String.valueOf(month+1));
	    	getSalesRecord().setYear(String.valueOf(year));
	    	SalesRecord sales = getSalesRecord();
	       currencyList=shippingService.getallCurrencySymbol();     
	    	return SUCCESS;
        }
    }
    Long busId=BusinessFilterUtil.setBusinessIdbyUserLevel();
            BusinessDAO businessDAO=(BusinessDAO)MmrBeanLocator.getInstance().findBean("businessDAO");
            List<Long> businessIds=new ArrayList<Long>();
    SalesRecord sales = getSalesRecord();
    if(busId!=null && busId>0){
    	    	    	sales.setBusinessIds(BusinessFilterUtil.getBusIdParentId(busId));
    	    	    	sales.setBusinessId(busId);
    	    	   }else if(busId==null || busId==0){
    	    	    	
    	    	    	List<Business>	allbusList=businessDAO.getHoleBusinessList();
    	    			if(allbusList!=null && allbusList.size()>0){
    	    				for(Business bs:allbusList){
    	    					businessIds.add(bs.getId());
    	    				}
    	    			}
    	    			if(businessIds.size()>0){
    	    				sales.setBusinessIds(businessIds);
    	    			}
    	    			
    	    			sales.setBusinessId(UserUtil.getMmrUser().getBusinessId());
    	    	    }
    sales.setMonth(Integer.valueOf(sales.getMonthName()));
    if (!StringUtil.isEmpty(UserUtil.getMmrUser().getBranch()))
      sales.setBranch(UserUtil.getMmrUser().getBranch());

    CurrencySymbol curSymbol = shippingService.getSymbolByCurrencycode(sales.getCurrency());
        Locale locale = null;
        if (curSymbol != null){
        	locale = new Locale(curSymbol.getLanguageCode()!=null?curSymbol.getLanguageCode():"en", curSymbol.getCountryCode());
        }else{
        	locale = new Locale("en", "CA");
        }
    	//NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
    	NumberFormat currencyFormatter = NumberFormat.getNumberInstance(locale);
    salesRecords = invoiceManager.generateSalesReport(sales);
    
  /// ===================== Exchange Rate =========================== 
    
   /* String toCurrency = null;
    for(SalesRecord salesRecord : salesRecords){
    	Double exRate = 0.0;
    	toCurrency = sales.getCurrency();
    	if(salesRecord.getCurrency() != null && !salesRecord.getCurrency().isEmpty() &&  toCurrency != null && !toCurrency.isEmpty()){
    		if(salesRecord.getCurrency().equals(toCurrency)){
    			exRate = 1.0;
    		}else{
    			exRate = shippingService.getExchangeRate(salesRecord.getCurrency(),toCurrency);
    		}
    		if(exRate == null){
    			addActionError("There is no exchange rate in database for selected currency " + toCurrency +", or invoice currency "+ salesRecord.getCurrency());
    			currencyList=shippingService.getallCurrencySymbol();
    			salesRecords.clear();
    			return SUCCESS;
    		}
    		salesRecord.setTotalCost(salesRecord.getTotalCost() * exRate);
    		salesRecord.setTotalAmount(salesRecord.getTotalAmount() * exRate);
    	}
    }
    List<SalesRecord> tempSalesRecords=new ArrayList<SalesRecord>();
    for(SalesRecord report:salesRecords){
    	boolean repAvoid=false;
    	for(SalesRecord tmp:tempSalesRecords){
    		if(report.getMonth().equals(tmp.getMonth())){
    			repAvoid=true;
    		}
    	}
    	if(!repAvoid){
    		tempSalesRecords.add(report);
    	}
    }
    if(tempSalesRecords.size()>0){
    	for(SalesRecord report:tempSalesRecords){
    		double totalAmt=0.0;
    		double totalCost=0.0;
    		for(SalesRecord sTmp1:salesRecords){
    			if(sTmp1.getMonth().equals(report.getMonth())){
    				totalAmt+=sTmp1.getTotalAmount();
    				totalCost+=sTmp1.getTotalCost();
    			}
    		}
    		report.setTotalAmount(totalAmt);
    		report.setTotalCost(totalCost);
    	}
    }
    salesRecords.clear();
    salesRecords.addAll(tempSalesRecords);
    */
  /// ===================== End =========================== 
    
    BigDecimal totcost = new BigDecimal(0.0).setScale(2,RoundingMode.CEILING);
        BigDecimal totcharge = new BigDecimal(0.0).setScale(2,RoundingMode.CEILING);
        for (SalesRecord sale : salesRecords) {
        	BigDecimal tcost = new BigDecimal(sale.getTotalCost()).setScale(2,RoundingMode.CEILING);
        	totcost = totcost.add(tcost);
        	BigDecimal tcharge = new BigDecimal(sale.getTotalAmount()).setScale(2,RoundingMode.CEILING);
        	totcharge = totcharge.add(tcharge);
        	sale.setTotalCostDisplay(currencyFormatter.format(tcost.doubleValue()));
        	sale.setTotalAmountDisplay(currencyFormatter.format(tcharge.doubleValue()));
    	}
    
    currencyList=shippingService.getallCurrencySymbol();
    CurrencySymbol currencySymbol = shippingService.getSymbolByCurrencycode(sales.getCurrency());
    getSession().put("salesCurrencySymbol", currencySymbol.getCurrencySymbol());
    getSession().put("totalCost", currencyFormatter.format(totcost.doubleValue()));
    getSession().put("totalCharge", currencyFormatter.format(totcharge.doubleValue()));
    return SUCCESS;
  }

  public List<User> getSalesUsers() {
    return salesUsers;
  }

  public Integer getStatusId() {
    return statusId;
  }

  /* Start: Method to send EMail Notification to the Customer of the requested Invoice */
public String sendEmailNotificationForInvoice() {
	     log.debug("In Send Email Notification method.");
	     // List<Long> invoiceIds = new ArrayList<Long>();
	     List<Invoice> invoice = new ArrayList<Invoice>();
	     boolean emailSent = false;
	     String invoiceid=request.getParameter("stored_value");
	     String ids[] = invoiceid.split(",");
	     
	     try {
	       for (int i = 0; i < ids.length; i++) {
	         // If this checkbox was selected:
	         long id=Long.valueOf(ids[i]);
	         
	         invoice.add(invoiceManager.getInvoiceById(id));
	         
	         
	         invoice.get(i).setInvoiceId(id);

	         }
	       
	       for (int i = 0; i < invoice.size(); i++) {
	    	   Customer customer = customerService.getCustomerInfoByCustomerId(invoice.get(i).getCustomerId());
	             
	         invoice.get(i).getCustomer().setInvoicingEmail(customer.getInvoicingEmail());
	         invoice.get(i).getCustomer().getAddress()
	             .setEmailAddress(customer.getAddress().getEmailAddress());
	       }
	       emailSent = invoiceManager.sendInvoiceEmailNotification(UserUtil.getMmrUser(), invoice);

	       if (emailSent)
	         this.addActionMessage(getText("email.invoice.notification.sent"));
	       else
	         this.addActionError(getText("email.invoice.notification.not.sent"));
	     } catch (Exception e) {
	       System.out.println(e);
	     }

	     return searchInvoice();
	   }

  public List<SalesRecord> getSalesRecords() {
    return salesRecords;
  }

  public void setSalesRecords(List<SalesRecord> salesRecords) {
    this.salesRecords = salesRecords;
  }

  /* End: Method to send EMail Notification to the Customer of the requested Invoice */

  public String downloadInvoiceCSV() {
    log.debug("In downloadInvoiceCSV method.");
    boolean CSVDownload = false;
    try {
      String invoiceId = request.getParameter("invoiceId");
      String downloadId = request.getParameter("downloadId");
      //log.debug(downloadId);
      final String cookiePath = "/";
     
      String strFile = "Invoice_" + invoiceId + ".csv";
      if (invoiceId != null) {
        //response.setContentType("application/octet-stream");
    	response.setContentType("application/csv");
        response.setHeader("Content-Disposition", "attachment; filename=" + strFile);
		Cookie myCookie = new Cookie("downloadId", downloadId);
		myCookie.setPath(cookiePath);
		response.addCookie(myCookie);
        Long l = Long.parseLong(invoiceId);
        CSVDownload = invoiceManager.downloadInvoiceCSV(l, response.getOutputStream());
        if (CSVDownload)
          this.addActionMessage(getText("download.invoice.csv.success"));
        else
          this.addActionError(getText("download.invoice.csv.failure"));
      }
    } catch (Exception e) {
      // TODO: handle exception
    }

    return null;
  }

  public String updateInvoiceStatus(String salesRep,String invoiceId) {

    log.debug("Update Invoice Status");
    String[] ids = invoiceId.split(",");
    if (salesRep != null && !salesRep.isEmpty()) {
      try {
        List<Invoice> invoicesToUpdate = new ArrayList<Invoice>();
        for (int i = 0; i < ids.length; i++) {
          // If this checkbox was selected:
        	Invoice invoice = new Invoice();
        	invoice.setInvoiceId(Long.valueOf(ids[i]));
            invoice.setSalesUsername(salesRep);
            invoice.setSalesRep(salesRep);
            // ...and launch it:
            invoicesToUpdate.add(invoice);
          }

      if (invoicesToUpdate.size() > 0) {
    	  invoiceManager.updateInvoiceStatusCommission(invoicesToUpdate);
          invoiceManager.updateInvoiceStatus(invoicesToUpdate);

        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    return SUCCESS;
  }
  
  public String salesprint() throws IOException
	{
		
		String method=request.getParameter("method");
		String type=request.getParameter("type");
		if(method!=null){
			getSession().remove("salesRecord");
			SalesRecord sales = getSalesRecord();			
			return SUCCESS;
		}
		
		SalesRecord sales = getSalesRecord();
		sales.setBusinessId(UserUtil.getMmrUser().getBusinessId());
		sales.setMonth(Integer.valueOf(sales.getMonthName()));
		if(!StringUtil.isEmpty(UserUtil.getMmrUser().getBranch()))
			sales.setBranch(UserUtil.getMmrUser().getBranch());
		List<SalesRecord> sale=invoiceManager.generateSalesReport(sales);	
		String currency=sales.getCurrency();
		if("xml".equalsIgnoreCase(type)){
			String shippingLabelFileName = getUniqueTempxmlFileNamesale("sales");
			//write_XML_File_sales(sale,shippingLabelFileName);
			write_XML_File_sales(sale,shippingLabelFileName,currency);
			response.setContentType("application/xml");
			response.setHeader("Content-Disposition",
					"attachment;filename=sales.xml");
			File xmlFile = new File(shippingLabelFileName);
			FileInputStream fileInputStream = new FileInputStream(xmlFile);
			/*ServletContext ctx =ServletActionContext.getServletContext();
			InputStream is = ctx.getResourceAsStream(shippingLabelFileName)*/
			int read=0;
			byte[] bytes = new byte[1024];
			OutputStream os = response.getOutputStream();
		 
			while((read = fileInputStream.read(bytes))!= -1){
				os.write(bytes, 0, read);
			}
			os.flush();
			os.close();	
			
			}else if("csv".equalsIgnoreCase(type)){
				String shippingLabelFileName = getUniqueTempcsvFileNamesale("sales");
				
				FileWriter writer = new FileWriter(shippingLabelFileName);
				//generateCsvFileSales(sale,writer);
				generateCsvFileSales(sale,writer,currency);
				response.setContentType("application/csv");
				response.setHeader("Content-Disposition",
						"attachment;filename=sales.csv");
				File csvFile = new File(shippingLabelFileName);
				FileInputStream fileInputStream = new FileInputStream(csvFile);
				/*ServletContext ctx =ServletActionContext.getServletContext();
				InputStream is = ctx.getResourceAsStream(shippingLabelFileName)*/
				int read=0;
				byte[] bytes = new byte[1024];
				OutputStream os = response.getOutputStream();
			 
				while((read = fileInputStream.read(bytes))!= -1){
					os.write(bytes, 0, read);
				}
				os.flush();
				os.close();	
				
			}else if("xl".equalsIgnoreCase(type)){
				String shippingLabelFileName = getUniqueTempxlFileNamesale("sales");
				
				//createxlfilesales(sale,shippingLabelFileName);
				createxlfilesales(sale,shippingLabelFileName,currency);
				response.setContentType("application/msexcel");
				response.setHeader("Content-Disposition",
						"attachment;filename=sales.xls");
				File xlFile = new File(shippingLabelFileName);
				FileInputStream fileInputStream = new FileInputStream(xlFile);
				/*ServletContext ctx =ServletActionContext.getServletContext();
				InputStream is = ctx.getResourceAsStream(shippingLabelFileName)*/
				int read=0;
				byte[] bytes = new byte[1024];
				OutputStream os = response.getOutputStream();
			 
				while((read = fileInputStream.read(bytes))!= -1){
					os.write(bytes, 0, read);
				}
				os.flush();
				os.close();	
				
			}
	return SUCCESS;
	}
	
  public String invoiceBreakdownPrint() throws IOException{
	  	
	  	try{  
	  	  String method=request.getParameter("method");
	  	  String type=request.getParameter("type");
	  	String inclCancelldInv= request.getParameter("inclCancelldInv");
	  	  UserSearchCriteria criteria = new UserSearchCriteria();
	  	criteria.setBusinessId(null);
	  		  	  criteria.setBusinessIds(BusinessFilterUtil.getBusIdParentId(BusinessFilterUtil.setBusinessIdbyUserLevel()));
	  	  criteria.setRoleCode(ShiplinxConstants.ROLE_SALES);
	  	  criteria.setSortBy(UserSearchCriteria.SORT_BY_FIRSTNAME);
	  	  if(!StringUtil.isEmpty(UserUtil.getMmrUser().getBranch()))
	  		  criteria.setBranch(UserUtil.getMmrUser().getBranch());
	  	  salesUsers = userService.findUsers(criteria, 0, 0);
	  	  
	  	  if(method!=null){
	  		  getSession().remove("commission");
	  		  Commission commission = getCommission();
	  
	  		  return SUCCESS;
	  	  }
	  	  Commission commission = this.getCommission();
	  	  commission.setFromDate(FormattingUtil.getDate(commission.getFromDate_web(),
	  			  FormattingUtil.DATE_FORMAT_WEB));
	  	  commission.setToDate(FormattingUtil.getDate(commission.getToDate_web(),
	  			  FormattingUtil.DATE_FORMAT_WEB));
	  	if(inclCancelldInv.equalsIgnoreCase("true")){
	  			  		invoiceBreakdownList=invoiceManager.searchInvoicesBreakdownIncludeCanceledInvoice(commission);  
	  			  	  }else{
	  			  		invoiceBreakdownList=invoiceManager.searchInvoicesBreakdown(commission);
	  			  	  }
	  	List<Invoice> tempInvoiceList=new ArrayList<Invoice>();
	    for(Invoice invoice:invoiceBreakdownList){
	             if(invoice.getTotalSPD()>0 || invoice.getTotalLTL()>0 || invoice.getTotalCHB()>0 || invoice.getTotalFWD()>0 || invoice.getTotalFPA()>0){
	            	 tempInvoiceList.add(invoice);
	             }
	           }
	                  invoiceBreakdownList.clear();
	                  invoiceBreakdownList.addAll(tempInvoiceList);
	  	  if("xml".equalsIgnoreCase(type)){
	  		  String shippingLabelFileName =getUniqueTempxmlFileNamecomm("InvoiceBreakdown");
	  		  write_XML_File_invoiceBreakdown(invoiceBreakdownList,shippingLabelFileName);
	  		  response.setContentType("application/xml");
	  		  response.setHeader("Content-Disposition",
	  				  "attachment;filename=InvoiceBreakdown.xml");
	  		  File xmlFile = new File(shippingLabelFileName);
	  		  FileInputStream fileInputStream = new FileInputStream(xmlFile);
	  		  /*ServletContext ctx =ServletActionContext.getServletContext();
	  	  	InputStream is = ctx.getResourceAsStream(shippingLabelFileName)*/
	  		  int read=0;
	  		  byte[] bytes = new byte[1024];
	  		  OutputStream os = response.getOutputStream();
	  
	  		  while((read = fileInputStream.read(bytes))!= -1){
	  			  os.write(bytes, 0, read);
	  		  }
	  		  os.flush();
	  		  os.close();	
	  
	  	  }else if("csv".equalsIgnoreCase(type)){
	  		  String shippingLabelFileName = getUniqueTempcsvFileNamecomm("InvoiceBreakdown");
	  
	  		  FileWriter writer = new FileWriter(shippingLabelFileName);
	  		  generateCsvFileInvoiceBreakdown(invoiceBreakdownList,writer);
	  		  response.setContentType("application/csv");
	  		  response.setHeader("Content-Disposition",
	  				  "attachment;filename=InvoiceBreakdown.csv");
	  		  File csvFile = new File(shippingLabelFileName);
	  		  FileInputStream fileInputStream = new FileInputStream(csvFile);
	  		  /*ServletContext ctx =ServletActionContext.getServletContext();
	  	  	InputStream is = ctx.getResourceAsStream(shippingLabelFileName)*/
	  		  int read=0;
	  		  byte[] bytes = new byte[1024];
	  		  OutputStream os = response.getOutputStream();
	  
	  		  while((read = fileInputStream.read(bytes))!= -1){
	  		  os.write(bytes, 0, read);
	  		  }
	  		  os.flush();
	  		  os.close();	
	  
	  
	  	  }else if("xl".equalsIgnoreCase(type)){
	  		  String shippingLabelFileName = getUniqueTempxlFileNamecomm("InvoiceBreakdown");
	  
	  		  createxlfileInvoiceBreakdown(invoiceBreakdownList,shippingLabelFileName);
	  		  response.setContentType("application/msexcel");
	  		  response.setHeader("Content-Disposition",
	  				  "attachment;filename=InvoiceBreakdown.xls");
	  		  File xlFile = new File(shippingLabelFileName);
	  		  FileInputStream fileInputStream = new FileInputStream(xlFile);
	  		  /*ServletContext ctx =ServletActionContext.getServletContext();
	  	  	InputStream is = ctx.getResourceAsStream(shippingLabelFileName)*/
	  		  int read=0;
	  		  byte[] bytes = new byte[1024];
	  		  OutputStream os = response.getOutputStream();
	  
	  		  while((read = fileInputStream.read(bytes))!= -1){
	  			  os.write(bytes, 0, read);
	  		  }
	  		  os.flush();
	  		  os.close();	
	  	  }
	  	}
	  	catch(Exception e){
	  		e.printStackTrace();
	  	}
	  	  return SUCCESS;
	     }
	public String commissionprint() throws IOException
	{
		
String method=request.getParameter("method");
String type=request.getParameter("type");
		UserSearchCriteria criteria = new UserSearchCriteria();
		criteria.setBusinessId(null);
				criteria.setBusinessIds(BusinessFilterUtil.getBusIdParentId(BusinessFilterUtil.setBusinessIdbyUserLevel()));
		criteria.setRoleCode(ShiplinxConstants.ROLE_SALES);
		criteria.setSortBy(UserSearchCriteria.SORT_BY_FIRSTNAME);
		if(!StringUtil.isEmpty(UserUtil.getMmrUser().getBranch()))
			criteria.setBranch(UserUtil.getMmrUser().getBranch());
		salesUsers = userService.findUsers(criteria, 0, 0);

		if(method!=null){
			getSession().remove("commission");
			Commission commission = getCommission();
			
			return SUCCESS;
		}
		Commission commission = this.getCommission();
				 commission.setFromDate(FormattingUtil.getDate(commission.getFromDate_web(),
				            FormattingUtil.DATE_FORMAT_WEB));
				    commission.setToDate(FormattingUtil.getDate(commission.getToDate_web(),
				            FormattingUtil.DATE_FORMAT_WEB));
				    
				    commissions = invoiceManager.searchCommissions(commission);
		if("xml".equalsIgnoreCase(type)){
			String shippingLabelFileName =getUniqueTempxmlFileNamecomm("commission");
			write_XML_File_commission(commissions,shippingLabelFileName);
			response.setContentType("application/xml");
			response.setHeader("Content-Disposition",
					"attachment;filename=commission.xml");
			File xmlFile = new File(shippingLabelFileName);
			FileInputStream fileInputStream = new FileInputStream(xmlFile);
			/*ServletContext ctx =ServletActionContext.getServletContext();
			InputStream is = ctx.getResourceAsStream(shippingLabelFileName)*/
			int read=0;
			byte[] bytes = new byte[1024];
			OutputStream os = response.getOutputStream();
		 
			while((read = fileInputStream.read(bytes))!= -1){
				os.write(bytes, 0, read);
			}
			os.flush();
			os.close();	
		
		}else if("csv".equalsIgnoreCase(type)){
			String shippingLabelFileName = getUniqueTempcsvFileNamecomm("commission");
			
			FileWriter writer = new FileWriter(shippingLabelFileName);
			generateCsvFile(commissions,writer);
			response.setContentType("application/csv");
			response.setHeader("Content-Disposition",
					"attachment;filename=commission.csv");
			File csvFile = new File(shippingLabelFileName);
			FileInputStream fileInputStream = new FileInputStream(csvFile);
			/*ServletContext ctx =ServletActionContext.getServletContext();
			InputStream is = ctx.getResourceAsStream(shippingLabelFileName)*/
			int read=0;
			byte[] bytes = new byte[1024];
			OutputStream os = response.getOutputStream();
		 
			while((read = fileInputStream.read(bytes))!= -1){
				os.write(bytes, 0, read);
			}
			os.flush();
			os.close();	
		
		
		}else if("xl".equalsIgnoreCase(type)){
			String shippingLabelFileName = getUniqueTempxlFileNamecomm("commission");
			
			createxlfilecomm(commissions,shippingLabelFileName);
			response.setContentType("application/msexcel");
			response.setHeader("Content-Disposition",
					"attachment;filename=commission.xls");
			File xlFile = new File(shippingLabelFileName);
			FileInputStream fileInputStream = new FileInputStream(xlFile);
			/*ServletContext ctx =ServletActionContext.getServletContext();
			InputStream is = ctx.getResourceAsStream(shippingLabelFileName)*/
			int read=0;
			byte[] bytes = new byte[1024];
			OutputStream os = response.getOutputStream();
		 
			while((read = fileInputStream.read(bytes))!= -1){
				os.write(bytes, 0, read);
			}
			os.flush();
			os.close();	
			
		}
	
		return SUCCESS;
	}
	
	public void write_XML_File_invoiceBreakdown(List<Invoice> invoiceBreakdownList,String shippingLabelFileName){
				DocumentBuilderFactory docBuilder = DocumentBuilderFactory.newInstance();
				DocumentBuilder builder=null;
				try {
					builder = docBuilder.newDocumentBuilder();
				} catch (ParserConfigurationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				Document doc = builder.newDocument();
				 Element shipingList = doc.createElement("Reports");
				 doc.appendChild(shipingList);
				 double totalSPD=0.0;
				 double totalLTL=0.0;
				 double totalCHB=0.0;
				 double totalAmount=0.0;
				 double totalTax=0.0;
				 double totalFPA=0.0;
				 double totalFWD=0.0;
				 List<Invoice> groupedInvoiceCharges = new ArrayList<Invoice>(); 
				 for(Invoice invoice :invoiceBreakdownList){
					 double invoiceAmt=0;
					 					 groupedInvoiceCharges=invoiceManager.getInvoiceChargeDetails(invoice.getInvoiceId());
					 					 for(Invoice grpInv:groupedInvoiceCharges){
					 	  					  if("SPD".equalsIgnoreCase(grpInv.getEmailType())){
					 	  						invoice.setTotalSPD(FormattingUtil.roundFigureRates(grpInv.getBreakdownTotal(), 2));
					 	  					  }
					 	  					  if("LTL".equalsIgnoreCase(grpInv.getEmailType())){
					 	  						 invoice.setTotalLTL(FormattingUtil.roundFigureRates(grpInv.getBreakdownTotal(), 2));
					 	  					  }
					 	  					  if("CHB".equalsIgnoreCase(grpInv.getEmailType())){
					 	  						 invoice.setTotalCHB(FormattingUtil.roundFigureRates(grpInv.getBreakdownTotal(), 2));
					 	  					  }
					 	  					  
					 	  					if("FPA".equalsIgnoreCase(grpInv.getEmailType())){
					 	  											 	  						 invoice.setTotalFPA(FormattingUtil.roundFigureRates(grpInv.getBreakdownTotal(), 2));
					 	  											 	  					  }
					 	  											 	  					if("FWD".equalsIgnoreCase(grpInv.getEmailType())){
					 	  											 	  						 invoice.setTotalFWD(FormattingUtil.roundFigureRates(grpInv.getBreakdownTotal(), 2));
					 	  											 	  					  }
					 	  				  }
					 					if(invoice.getTotalSPD()>0 || invoice.getTotalLTL()>0 || invoice.getTotalCHB()>0 || invoice.getTotalFPA()>0 || invoice.getTotalFWD()>0){
					 							invoiceAmt=invoice.getTotalSPD()+invoice.getTotalLTL()+invoice.getTotalCHB()+invoice.getTotalFPA()+invoice.getTotalFWD();
					 		  					invoice.setInvoiceAmount(invoiceAmt);
					 Element log1 = doc.createElement("Invoice");
			         shipingList.appendChild(log1);
				         Element number = doc.createElement("InvoiceNumber");
				         number.appendChild(doc.createTextNode(removeNull(invoice.getInvoiceNum())));
				         log1.appendChild(number);
				         Element Company = doc.createElement("Company");
				         
				         
				         Company.appendChild(doc.createTextNode(removeNull(invoice.getInvoiceNum())));
				         log1.appendChild(Company);
				         
				         Element datecreated = doc.createElement("DateCreated");
				         datecreated.appendChild(doc.createTextNode(removeNull(String.valueOf(invoice.getDateCreated()))));
				         log1.appendChild(datecreated);
				         
				         Element amount = doc.createElement("Amount");
				         amount.appendChild(doc.createTextNode(removeNull(String.valueOf(FormattingUtil.roundFigureRates(invoiceAmt,2)))));
				         log1.appendChild(amount);
				         
				         Element tax = doc.createElement("Tax");
				         tax.appendChild(doc.createTextNode(removeNull(String.valueOf(invoice.getInvoiceTax()))));
				         log1.appendChild(tax);
				         
				         Element SPD = doc.createElement("SPD");
				         SPD.appendChild(doc.createTextNode(removeNull(String.valueOf(FormattingUtil.roundFigureRates(invoice.getTotalSPD(),2)))));
				         log1.appendChild(SPD);
				         
				         Element LTL = doc.createElement("LTL");
				         /*SPD.appendChild(doc.createTextNode(removeNull(String.valueOf(invoice.getTotalSPD()))));
				         log1.appendChild(SPD);*/
				         LTL.appendChild(doc.createTextNode(removeNull(String.valueOf(FormattingUtil.roundFigureRates(invoice.getTotalLTL(),2)))));
				         log1.appendChild(LTL);
				         
				         Element CHB = doc.createElement("CHB");
				         /*SPD.appendChild(doc.createTextNode(removeNull(String.valueOf(invoice.getTotalSPD()))));
				         log1.appendChild(SPD);*/
				         CHB.appendChild(doc.createTextNode(removeNull(String.valueOf(FormattingUtil.roundFigureRates(invoice.getTotalCHB(),2)))));
				         log1.appendChild(CHB);
				         Element FPA = doc.createElement("FPA");
				         FPA.appendChild(doc.createTextNode(removeNull(String.valueOf(FormattingUtil.roundFigureRates(invoice.getTotalFPA(),2)))));
				         log1.appendChild(FPA);
				         Element FWD = doc.createElement("FWD");
				         FWD.appendChild(doc.createTextNode(removeNull(String.valueOf(FormattingUtil.roundFigureRates(invoice.getTotalFWD(),2)))));
				         log1.appendChild(FWD);
				         totalAmount+=invoiceAmt;
				         totalTax+=invoice.getInvoiceTax();
				         totalSPD+=invoice.getTotalSPD();
				         totalLTL+=invoice.getTotalLTL();
				         totalCHB+=invoice.getTotalCHB();
				         totalFPA+=invoice.getTotalFPA();
				         totalFWD+=invoice.getTotalFWD();
					 					 }
				 }
				 Element log1 = doc.createElement("Invoice");
		         shipingList.appendChild(log1);
		         Element tamt = doc.createElement("TotalAmount");
		         tamt.appendChild(doc.createTextNode(removeNull(String.valueOf(FormattingUtil.roundFigureRates(totalAmount,2)))));
		         log1.appendChild(tamt);
		         Element tTax = doc.createElement("TotalTax");
		         tTax.appendChild(doc.createTextNode(removeNull(String.valueOf(FormattingUtil.roundFigureRates(totalTax,2)))));
		         log1.appendChild(tTax);
				 Element tSPD = doc.createElement("TotalSPD");
		         tSPD.appendChild(doc.createTextNode(removeNull(String.valueOf(FormattingUtil.roundFigureRates(totalSPD,2)))));
		         log1.appendChild(tSPD);
		
		         Element tLTL = doc.createElement("TotalLTL");
		         tLTL.appendChild(doc.createTextNode(removeNull(String.valueOf(FormattingUtil.roundFigureRates(totalLTL,2)))));
		         log1.appendChild(tLTL);
		         
		         Element tCHB = doc.createElement("TotalCHB");
		         tCHB.appendChild(doc.createTextNode(removeNull(String.valueOf(FormattingUtil.roundFigureRates(totalCHB,2)))));
		         log1.appendChild(tCHB);
		         
		         Element tFPA = doc.createElement("TotalFPA");
		         tFPA.appendChild(doc.createTextNode(removeNull(String.valueOf(FormattingUtil.roundFigureRates(totalFPA,2)))));
		         log1.appendChild(tFPA);
		          				 
		         Element tFWD = doc.createElement("TotalFWD");
		         tFWD.appendChild(doc.createTextNode(removeNull(String.valueOf(FormattingUtil.roundFigureRates(totalFWD,2)))));
		         log1.appendChild(tFWD);
				 
					try{
					TransformerFactory transformerFactory = TransformerFactory.newInstance();
			          Transformer transformer = transformerFactory.newTransformer();
			           transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			           DOMSource source = new DOMSource(doc);
			           StreamResult result = new StreamResult(new File(shippingLabelFileName));
			           transformer.transform(source, result);
		//	           response.setContentType("application/octet-stream");
		//	           response.setHeader("Content-Disposition", "attachement; filename=xyz.xml");
					}catch(Exception e){
						e.printStackTrace();
					}
					
					System.out.println("File saved for Invoice Breakdown!");
				
			}
	
	public  void write_XML_File_commission(List<Commission> commissionList,String shippingLabelFileName){
		 
			
		  
		 DocumentBuilderFactory docBuilder = DocumentBuilderFactory.newInstance();
		 DocumentBuilder builder=null;
		try {
			builder = docBuilder.newDocumentBuilder();
		} catch (ParserConfigurationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		 
		 
		 Document doc = builder.newDocument();
		 Element shipingList = doc.createElement("Reports");
		 doc.appendChild(shipingList);
		 double totalSPD=0.0;
		 double totalLTL=0.0;
		 double totalCHB=0.0;
		 
		 double totalFPA=0.0;
		 double totalFWD=0.0;
		 
		 for(Commission commission :commissionList){
	
		         /*Attr attr = doc.createAttribute("id");
		         attr.setValue(removeNull(Long.toString(sOrder.getId())));*/
		         
		         //.appendChild(log1);
		         
			 Element log1 = doc.createElement("Commission");
	         shipingList.appendChild(log1);
		         Element number = doc.createElement("InvoiceNumber");
		         
		         
		         number.appendChild(doc.createTextNode(removeNull(commission.getInvoiceNum())));
		         log1.appendChild(number);
		         
		         Element Company = doc.createElement("Company");
		         
		         
		         Company.appendChild(doc.createTextNode(removeNull(commission.getInvoiceNum())));
		         log1.appendChild(Company);
		         
		         Element datecreated = doc.createElement("DateCreated");
		         datecreated.appendChild(doc.createTextNode(removeNull(String.valueOf(commission.getDateCreated()))));
		         log1.appendChild(datecreated);
	
		         Element commissionElement = doc.createElement("Commisssion");
		         commissionElement.appendChild(doc.createTextNode(removeNull(String.valueOf(commission.getCommissionPayable()))));
		         log1.appendChild(commissionElement);
	
		         Element amount = doc.createElement("Amount");
		         amount.appendChild(doc.createTextNode(removeNull(String.valueOf(FormattingUtil.roundFigureRates(commission.getInvoiceTotal(),2)))));
		         log1.appendChild(amount);
		         
		         if(!UserUtil.getMmrUser().getUserRole().equalsIgnoreCase("sales")){
		         Element cost = doc.createElement("Cost");
		         cost.appendChild(doc.createTextNode(removeNull(String.valueOf(FormattingUtil.roundFigureRates(commission.getCostTotal(),2)))));
		         log1.appendChild(cost);
		         }
		         
		         Element status = doc.createElement("Status");
		         findPaymentStaus(commission.getRepPaid());
		         status.appendChild(doc.createTextNode(removeNull(String.valueOf(paymentStatus))));
		         log1.appendChild(status);
		         
		         Element SPD = doc.createElement("SPD");
		         SPD.appendChild(doc.createTextNode(removeNull(String.valueOf(FormattingUtil.roundFigureRates(commission.getTotalSPD(),2)))));
		         log1.appendChild(SPD);
	
		         Element LTL = doc.createElement("LTL");
		         LTL.appendChild(doc.createTextNode(removeNull(String.valueOf(FormattingUtil.roundFigureRates(commission.getTotalLTL(),2)))));
		         log1.appendChild(LTL);
		         
		         Element CHB = doc.createElement("CHB");
		         CHB.appendChild(doc.createTextNode(removeNull(String.valueOf(FormattingUtil.roundFigureRates(commission.getTotalCHB(),2)))));
		         log1.appendChild(CHB);
		         
		         Element FPA = doc.createElement("FPA");
		         FPA.appendChild(doc.createTextNode(removeNull(String.valueOf(FormattingUtil.roundFigureRates(commission.getTotalFPA(),2)))));
		         log1.appendChild(FPA);
		         Element FWD = doc.createElement("FWD");
		         FWD.appendChild(doc.createTextNode(removeNull(String.valueOf(FormattingUtil.roundFigureRates(commission.getTotalFWD(),2)))));
		         log1.appendChild(FWD);
		         totalSPD+=commission.getTotalSPD();
		         totalLTL+=commission.getTotalLTL();
		         totalCHB+=commission.getTotalCHB();
		         totalFPA+=commission.getTotalFPA();
		         totalFWD+=commission.getTotalFWD();
		       
		}
		 Element log1 = doc.createElement("Commission");
         shipingList.appendChild(log1);
		 Element tSPD = doc.createElement("TotalSPD");
         tSPD.appendChild(doc.createTextNode(removeNull(String.valueOf(FormattingUtil.roundFigureRates(totalSPD,2)))));
         log1.appendChild(tSPD);

         Element tLTL = doc.createElement("TotalLTL");
         tLTL.appendChild(doc.createTextNode(removeNull(String.valueOf(FormattingUtil.roundFigureRates(totalLTL,2)))));
         log1.appendChild(tLTL);
         
         Element tCHB = doc.createElement("TotalCHB");
         tCHB.appendChild(doc.createTextNode(removeNull(String.valueOf(FormattingUtil.roundFigureRates(totalCHB,2)))));
         log1.appendChild(tCHB);
         
         Element tFPA = doc.createElement("TotalFPA");
         tFPA.appendChild(doc.createTextNode(removeNull(String.valueOf(FormattingUtil.roundFigureRates(totalFPA,2)))));
         log1.appendChild(tFPA);
         
         Element tFWD = doc.createElement("TotalFWD");
         tFWD.appendChild(doc.createTextNode(removeNull(String.valueOf(FormattingUtil.roundFigureRates(totalFWD,2)))));
         log1.appendChild(tFWD);
		 
			try{
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
	          Transformer transformer = transformerFactory.newTransformer();
	           transformer.setOutputProperty(OutputKeys.INDENT, "yes");
	           DOMSource source = new DOMSource(doc);
	           StreamResult result = new StreamResult(new File(shippingLabelFileName));
	           transformer.transform(source, result);
//	           response.setContentType("application/octet-stream");
//	           response.setHeader("Content-Disposition", "attachement; filename=xyz.xml");
			}catch(Exception e){
				e.printStackTrace();
			}
			
			System.out.println("File saved for commission!");
	 }

	 
		private String removeNull(String text){
			if(null==text){
				return "";
			}
			return text;
		}
		private void generateCsvFileInvoiceBreakdown(List<Invoice> InvoiceBreakdownList,FileWriter writer){
					try{
						double totalSPD=0.0;
						double totalLTL=0.0;
						double totalCHB=0.0;
						double totalFPA=0.0;
						double totalFWD=0.0;
						double totalAmount=0.0;
						double totalTax=0.0;
						ArrayList<String> srcList = new ArrayList<String>();
						List<Invoice> groupedInvoiceCharges = new ArrayList<Invoice>();
						writer.append("invoicenumber");
						writer.append(',');
						writer.append("company");
						writer.append(',');
						writer.append("dateCreater");
						writer.append(',');
						writer.append("Amount");
						writer.append(',');
						writer.append("Tax");
						writer.append(',');
						writer.append("SPD");
						writer.append(',');
						writer.append("LTL");
						writer.append(',');
						writer.append("CHB");
						writer.append(',');
						writer.append("FPA");
						writer.append(',');
						writer.append("FWD");
						writer.append('\n');
						for(Invoice invoice :InvoiceBreakdownList){
							double invoiceAmt=0;
														 groupedInvoiceCharges=invoiceManager.getInvoiceChargeDetails(invoice.getInvoiceId());
														 for(Invoice grpInv:groupedInvoiceCharges){
										  					  if("SPD".equalsIgnoreCase(grpInv.getEmailType())){
										  						invoice.setTotalSPD(FormattingUtil.roundFigureRates(grpInv.getBreakdownTotal(), 2));
										  					  }
										  					  if("LTL".equalsIgnoreCase(grpInv.getEmailType())){
										  						 invoice.setTotalLTL(FormattingUtil.roundFigureRates(grpInv.getBreakdownTotal(), 2));
										  					  }
										  					  if("CHB".equalsIgnoreCase(grpInv.getEmailType())){
										  						 invoice.setTotalCHB(FormattingUtil.roundFigureRates(grpInv.getBreakdownTotal(), 2));
										  					  }
										  					  
										  					if("FPA".equalsIgnoreCase(grpInv.getEmailType())){
										  						invoice.setTotalFPA(FormattingUtil.roundFigureRates(grpInv.getBreakdownTotal(), 2));
										  					}
										  					if("FWD".equalsIgnoreCase(grpInv.getEmailType())){
										  						invoice.setTotalFWD(FormattingUtil.roundFigureRates(grpInv.getBreakdownTotal(), 2));
										  					}
										  				  }
														 if(invoice.getTotalSPD()>0 || invoice.getTotalLTL()>0 || invoice.getTotalCHB()>0|| invoice.getTotalFPA()>0|| invoice.getTotalFWD()>0){
															 invoiceAmt=invoice.getTotalSPD()+invoice.getTotalLTL()+invoice.getTotalCHB()+invoice.getTotalFPA()+invoice.getTotalFWD();
											  					invoice.setInvoiceAmount(invoiceAmt);
							writer.append(removeNull(invoice.getInvoiceNum()));
							writer.append(',');
							writer.append(removeNull(invoice.getCustomer().getName()));
							writer.append(',');
							writer.append(removeNull(String.valueOf(invoice.getDateCreated())));
							writer.append(',');
							writer.append(removeNull(String.valueOf(invoiceAmt)));   
							writer.append(',');
							writer.append(removeNull(String.valueOf(FormattingUtil.roundFigureRates(invoice.getInvoiceTax(),2))));   
							writer.append(',');
							writer.append(removeNull(String.valueOf(FormattingUtil.roundFigureRates(invoice.getTotalSPD(),2))));   
							writer.append(',');
							writer.append(removeNull(String.valueOf(FormattingUtil.roundFigureRates(invoice.getTotalLTL(),2))));   
							writer.append(',');
							writer.append(removeNull(String.valueOf(FormattingUtil.roundFigureRates(invoice.getTotalCHB(),2))));
							writer.append(',');
							writer.append(removeNull(String.valueOf(FormattingUtil.roundFigureRates(invoice.getTotalFPA(),2))));
							writer.append(',');
							writer.append(removeNull(String.valueOf(FormattingUtil.roundFigureRates(invoice.getTotalFWD(),2))));
							writer.append('\n');
			
							totalAmount+=invoiceAmt;
							totalTax+=invoice.getInvoiceTax();
							totalSPD+=invoice.getTotalSPD();
							totalLTL+=invoice.getTotalLTL();
							totalCHB+=invoice.getTotalCHB();
							totalFPA+=invoice.getTotalFPA();
							totalFWD+=invoice.getTotalFWD();
							}
						}
						writer.append("Total Amount");
						writer.append(',');
						writer.append(String.valueOf(FormattingUtil.roundFigureRates(totalAmount,2)));
						writer.append(',');
						writer.append("Total Tax");
						writer.append(',');
						writer.append(String.valueOf(FormattingUtil.roundFigureRates(totalTax,2)));
						writer.append(',');
						writer.append("Total SPD");
						writer.append(',');
						writer.append(String.valueOf(FormattingUtil.roundFigureRates(totalSPD,2)));
						writer.append(',');
						writer.append("Total LTL");
						writer.append(',');
						writer.append(String.valueOf(FormattingUtil.roundFigureRates(totalLTL,2)));
						writer.append(',');
						writer.append("Total CHB");
						writer.append(',');
						writer.append(String.valueOf(FormattingUtil.roundFigureRates(totalCHB,2)));
						writer.append(',');
						writer.append("Total FPA");
						writer.append(',');
						writer.append(String.valueOf(FormattingUtil.roundFigureRates(totalFPA,2)));
						writer.append(',');
						writer.append("Total FWD");
						writer.append(',');
						writer.append(String.valueOf(FormattingUtil.roundFigureRates(totalFWD,2)));
						writer.flush();
						writer.close();
						System.out.println("csv generated successfully");
					}catch(Exception e){
						e.printStackTrace();
					}
				}
					
		public  void write_XML_File_sales(List<SalesRecord> salesrecord,String shippingLabelFileName,String currency){
			
			 
			 DocumentBuilderFactory docBuilder = DocumentBuilderFactory.newInstance();
			 DocumentBuilder builder=null;
			try {
				builder = docBuilder.newDocumentBuilder();
			} catch (ParserConfigurationException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			 
			 
			 Document doc = builder.newDocument();
			 Element shipingList = doc.createElement("Reports");
			 doc.appendChild(shipingList);
				for(SalesRecord srecord :salesrecord){
			  
		
			         Element log1 = doc.createElement("Sales");
			         shipingList.appendChild(log1);
		
			         /*Attr attr = doc.createAttribute("id");
			         attr.setValue(removeNull(Long.toString(sOrder.getId())));*/
			         
			         //.appendChild(log1);
			         
			         
			         Element Year = doc.createElement("Year");
			         
			         
			         Year.appendChild(doc.createTextNode(removeNull(srecord.getYear())));
			         log1.appendChild(Year);
			         
			         Element Month = doc.createElement("Month");
			         Month.appendChild(doc.createTextNode(removeNull(srecord.getMonthName())));
			         log1.appendChild(Month);
		
			         Element Currency = doc.createElement("Currency");
			         //Currency.appendChild(doc.createTextNode(removeNull(srecord.getCurrency())));
			         Currency.appendChild(doc.createTextNode(removeNull(currency)));
			         log1.appendChild(Currency);
		
			         Element Cost = doc.createElement("Totalcost");
			         Cost.appendChild(doc.createTextNode(removeNull(String.valueOf(FormattingUtil.roundFigureRates(srecord.getTotalCost(),2)))));
			         log1.appendChild(Cost);
			         Element amount = doc.createElement("Totalcost");
			         amount.appendChild(doc.createTextNode(removeNull(String.valueOf(FormattingUtil.roundFigureRates(srecord.getTotalAmount(),2)))));
			         log1.appendChild(amount);
			         
			         
			        
			         
			       
			       
			        
			          
			           
		           
			}
				try{
				TransformerFactory transformerFactory = TransformerFactory.newInstance();
		          Transformer transformer = transformerFactory.newTransformer();
		           transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		           DOMSource source = new DOMSource(doc);
		           StreamResult result = new StreamResult(new File(shippingLabelFileName));
		           transformer.transform(source, result);
				}catch(Exception e){
					e.printStackTrace();
				}
				
				System.out.println("File saved for sale!");
		}
		public void createxlfileInvoiceBreakdown(List<Invoice> invoicebreakdownList,String shippingLabelFileName) throws IOException{
					HSSFWorkbook workbook=new HSSFWorkbook();
					HSSFSheet sheet =  workbook.createSheet("FirstSheet");  
					HSSFRow rowhead=   sheet.createRow((short)0);
					rowhead.createCell((short) 0).setCellValue("Invoice Number");
					rowhead.createCell((short) 1).setCellValue("Name");
					rowhead.createCell((short) 2).setCellValue("Date Created");
					rowhead.createCell((short) 3).setCellValue("Amount");
					rowhead.createCell((short) 4).setCellValue("Tax");
					rowhead.createCell((short) 5).setCellValue("SPD");
					rowhead.createCell((short) 6).setCellValue("LTL");
					rowhead.createCell((short) 7).setCellValue("CHB");
					rowhead.createCell((short) 8).setCellValue("FPA");
					rowhead.createCell((short) 9).setCellValue("FWD");
					int i=1;
					double totalSPD=0;
					double totalLTL=0;
					double totalCHB=0;
					double totalFPA=0;
					double totalFWD=0;
					double totalAmount=0;
					double totalTax=0;
					List<Invoice> groupedInvoiceCharges = new ArrayList<Invoice>();
					
					
					for(Invoice invoice :invoicebreakdownList){
						double invoiceAmt=0;
												 groupedInvoiceCharges=invoiceManager.getInvoiceChargeDetails(invoice.getInvoiceId());
												 for(Invoice grpInv:groupedInvoiceCharges){
								  					  if("SPD".equalsIgnoreCase(grpInv.getEmailType())){
								  						invoice.setTotalSPD(FormattingUtil.roundFigureRates(grpInv.getBreakdownTotal(), 2));
								  					  }
								  					  if("LTL".equalsIgnoreCase(grpInv.getEmailType())){
								  						 invoice.setTotalLTL(FormattingUtil.roundFigureRates(grpInv.getBreakdownTotal(), 2));
								  					  }
								  					  if("CHB".equalsIgnoreCase(grpInv.getEmailType())){
								  						 invoice.setTotalCHB(FormattingUtil.roundFigureRates(grpInv.getBreakdownTotal(), 2));
								  					  }
								  					if("FPA".equalsIgnoreCase(grpInv.getEmailType())){
								  						invoice.setTotalFPA(FormattingUtil.roundFigureRates(grpInv.getBreakdownTotal(), 2));
								  					}
								  					if("FWD".equalsIgnoreCase(grpInv.getEmailType())){
								  						invoice.setTotalFWD(FormattingUtil.roundFigureRates(grpInv.getBreakdownTotal(), 2));
								  					}
								  				  }
												 if(invoice.getTotalSPD()>0 || invoice.getTotalLTL()>0 || invoice.getTotalCHB()>0|| invoice.getTotalFPA()>0|| invoice.getTotalFWD()>0){
													 invoiceAmt=invoice.getTotalSPD()+invoice.getTotalLTL()+invoice.getTotalCHB()+invoice.getTotalFPA()+invoice.getTotalFWD();
									  					invoice.setInvoiceAmount(invoiceAmt);
						HSSFRow row=   sheet.createRow((short)i);
						row.createCell((short) 0).setCellValue(removeNull(invoice.getInvoiceNum()));
						row.createCell((short) 1).setCellValue(removeNull(invoice.getCustomer().getName()));
					row.createCell((short) 2).setCellValue(removeNull(String.valueOf(invoice.getDateCreated())));
						row.createCell((short) 3).setCellValue(removeNull(String.valueOf(FormattingUtil.roundFigureRates(invoiceAmt,2))));
						row.createCell((short) 4).setCellValue(removeNull(String.valueOf(FormattingUtil.roundFigureRates(invoice.getInvoiceTax(),2))));
						row.createCell((short) 5).setCellValue(removeNull(String.valueOf(FormattingUtil.roundFigureRates(invoice.getTotalSPD(),2))));
						row.createCell((short) 6).setCellValue(removeNull(String.valueOf(FormattingUtil.roundFigureRates(invoice.getTotalLTL(),2))));
						row.createCell((short) 7).setCellValue(removeNull(String.valueOf(FormattingUtil.roundFigureRates(invoice.getTotalCHB(),2))));
						row.createCell((short) 8).setCellValue(removeNull(String.valueOf(FormattingUtil.roundFigureRates(invoice.getTotalFPA(),2))));
						row.createCell((short) 9).setCellValue(removeNull(String.valueOf(FormattingUtil.roundFigureRates(invoice.getTotalFWD(),2))));
						totalAmount+=invoiceAmt;
						totalTax+=invoice.getInvoiceTax();
						totalSPD+=invoice.getTotalSPD();
					    totalLTL+=invoice.getTotalLTL();
						totalCHB+=invoice.getTotalCHB();
						totalFPA+=invoice.getTotalFPA();
						totalFWD+=invoice.getTotalFWD();
						i++;
						}
					}
					if(invoicebreakdownList!=null && invoicebreakdownList.size()>1){
						HSSFRow rowheads=   sheet.createRow((short)invoicebreakdownList.size()+1);
						rowheads.createCell((short) 0).setCellValue("Total Amount");
						rowheads.createCell((short) 1).setCellValue(String.valueOf(FormattingUtil.roundFigureRates(totalAmount,2)));
						rowheads.createCell((short) 2).setCellValue("Total Tax");
						rowheads.createCell((short) 3).setCellValue(String.valueOf(FormattingUtil.roundFigureRates(totalTax,2)));
					    rowheads.createCell((short) 4).setCellValue("Total SPD");
						rowheads.createCell((short) 5).setCellValue(String.valueOf(FormattingUtil.roundFigureRates(totalSPD,2)));
						rowheads.createCell((short) 6).setCellValue("Total LTL");
						rowheads.createCell((short) 7).setCellValue(String.valueOf(FormattingUtil.roundFigureRates(totalLTL,2)));
						rowheads.createCell((short) 8).setCellValue("Total CHB");
						rowheads.createCell((short) 9).setCellValue(String.valueOf(FormattingUtil.roundFigureRates(totalCHB,2)));
						rowheads.createCell((short) 10).setCellValue("Total FPA");
						rowheads.createCell((short) 11).setCellValue(String.valueOf(FormattingUtil.roundFigureRates(totalFPA,2)));
						rowheads.createCell((short) 12).setCellValue("Total FWD");
						rowheads.createCell((short) 13).setCellValue(String.valueOf(FormattingUtil.roundFigureRates(totalFWD,2)));
					}
					FileOutputStream fileOut =  new FileOutputStream(shippingLabelFileName);
					workbook.write(fileOut);
					fileOut.close();
					System.out.println("Your excel file has been generated!");
				}
		
		 private  void generateCsvFile(List<Commission> commissionList,FileWriter writer)
		   {
			try
			{  
				double totalSPD=0.0;
			    double totalLTL=0.0;
			    double totalCHB=0.0;
			    double totalFPA=0.0;
			    double totalFWD=0.0;
				ArrayList<String> srcList = new ArrayList<String>();
			    writer.append("invoicenumber");
			    writer.append(',');
			    writer.append("company");
			    writer.append(',');
			    writer.append("dateCreated");
			    writer.append(',');
			    writer.append("commission");
			    writer.append(',');
			    writer.append("amount");
			    writer.append(',');
			    if(!UserUtil.getMmrUser().getUserRole().equalsIgnoreCase("sales")){
			    writer.append("cost");
			    writer.append(',');
			    }
			    writer.append("status");
			    writer.append(',');
			    writer.append("SPD");
			    writer.append(',');
			    writer.append("LTL");
			    writer.append(',');
			    writer.append("CHB");
			    writer.append(',');
			    writer.append("FPA");
			    writer.append(',');
			    writer.append("FWD");
			    writer.append('\n');
			    for(Commission commission :commissionList){
			   
			    
		 
			    writer.append(removeNull(commission.getInvoiceNum()));
			    writer.append(',');
			    writer.append(removeNull(commission.getCustomerName()));
			    writer.append(',');
		        writer.append(removeNull(String.valueOf(commission.getDateCreated())));
		        writer.append(',');
		        writer.append(removeNull(String.valueOf(commission.getCommissionPayable())));   
		        writer.append(',');
		        writer.append(removeNull(String.valueOf(commission.getInvoiceTotal())));   
		        writer.append(',');
		        if(!UserUtil.getMmrUser().getUserRole().equalsIgnoreCase("sales")){
		        writer.append(removeNull(String.valueOf(commission.getCostTotal()))); 
		        writer.append(',');
		        }
		        findPaymentStaus(commission.getRepPaid());
		        writer.append(removeNull(String.valueOf(paymentStatus)));   
		        writer.append(',');
		        writer.append(removeNull(String.valueOf(FormattingUtil.roundFigureRates(commission.getTotalSPD(),2))));   
		        writer.append(',');
		        writer.append(removeNull(String.valueOf(FormattingUtil.roundFigureRates(commission.getTotalLTL(),2))));   
		        writer.append(',');
		        writer.append(removeNull(String.valueOf(FormattingUtil.roundFigureRates(commission.getTotalCHB(),2))));
		        writer.append(',');
		        writer.append(removeNull(String.valueOf(FormattingUtil.roundFigureRates(commission.getTotalFPA(),2))));
		        writer.append(',');
		        writer.append(removeNull(String.valueOf(FormattingUtil.roundFigureRates(commission.getTotalFWD(),2))));
		        writer.append('\n');
		    
			 
		        totalSPD+=commission.getTotalSPD();
		        totalLTL+=commission.getTotalLTL();
		        totalCHB+=commission.getTotalCHB();
		        totalFPA+=commission.getTotalFPA();
		        totalFWD+=commission.getTotalFWD();
		         
			    //generate whatever data you want
		 
			   
			}
			writer.append("Total SPD");
			writer.append(',');
			writer.append(String.valueOf(FormattingUtil.roundFigureRates(totalSPD,2)));
			writer.append(',');
			writer.append("Total LTL");
			writer.append(',');
			writer.append(String.valueOf(FormattingUtil.roundFigureRates(totalLTL,2)));
			writer.append(',');
			writer.append("Total CHB");
			writer.append(',');
			writer.append(String.valueOf(FormattingUtil.roundFigureRates(totalCHB,2)));
			writer.append(',');
			writer.append("Total FPA");
			writer.append(',');
			writer.append(String.valueOf(FormattingUtil.roundFigureRates(totalFPA,2)));
			writer.append(',');
			writer.append("Total FWD");
			writer.append(',');
			writer.append(String.valueOf(FormattingUtil.roundFigureRates(totalFWD,2)));
			writer.flush();
		    writer.close();
		    System.out.println("csv generated successfully");
			}
			catch(IOException e)
			{
			     e.printStackTrace();
			} 
		    }
		 
		 private  void generateCsvFileSales(List<SalesRecord> salesList,FileWriter writer,String currency)
		   {
			try
			{
			  
			    writer.append("year");
			    writer.append(',');
			    writer.append("month");
			    writer.append(',');
			    writer.append("currency");
			    writer.append(',');
			    writer.append("totalCost");
			    writer.append(',');
			    writer.append("totalrevenue");
			    writer.append('\n');
			    for(SalesRecord salesreport :salesList){
			  
			    
		 
			    writer.append(removeNull(salesreport.getYear()));
			    writer.append(',');
		        writer.append(removeNull(salesreport.getMonthName()));
		        writer.append(',');
		        writer.append(removeNull(currency)); 
		        writer.append(',');
		        writer.append(removeNull(String.valueOf(salesreport.getTotalCost()))); 
		        writer.append(',');
		        writer.append(removeNull(String.valueOf(salesreport.getTotalAmount()))); 
		        writer.append('\n');
		    
			 
		 
			    //generate whatever data you want
		 
			   
			}
			
			writer.flush();
		    writer.close();
		    System.out.println("csv generated successfully");
			}
			
			
			
			catch(IOException e)
			{
			     e.printStackTrace();
			} 
		    }
		 
		 
		 public void createxlfilesales(List<SalesRecord> salesList,String shippingLabelFileName,String currency) throws IOException{
				
		        HSSFWorkbook workbook=new HSSFWorkbook();
		        HSSFSheet sheet =  workbook.createSheet("FirstSheet");  
		        HSSFRow rowhead=   sheet.createRow((short)0);
		        rowhead.createCell((short) 0).setCellValue("year");
		        rowhead.createCell((short) 1).setCellValue("month");
		        rowhead.createCell((short) 2).setCellValue("currency");
		        rowhead.createCell((short) 3).setCellValue("cost");
		        rowhead.createCell((short) 4).setCellValue("amount");
		        int i=1;
		        for(SalesRecord sList :salesList){
		        	
		        HSSFRow row=   sheet.createRow((short)i);
		        row.createCell((short) 0).setCellValue(removeNull(sList.getYear()));
		        row.createCell((short) 1).setCellValue(removeNull(sList.getMonthName()));
		        row.createCell((short) 2).setCellValue(removeNull(currency));
		        row.createCell((short) 3).setCellValue(removeNull(String.valueOf((sList.getTotalCost()))));
		        row.createCell((short) 4).setCellValue(removeNull(String.valueOf(sList.getTotalAmount())));
		        i++;
				 }
		        
		        FileOutputStream fileOut =  new FileOutputStream(shippingLabelFileName);
		        workbook.write(fileOut);
		        fileOut.close();
		        System.out.println("Your excel file has been generated!");
		        

		    }
		 
		 public void createxlfilecomm(List<Commission> commissionList,String shippingLabelFileName) throws IOException{
				
		        HSSFWorkbook workbook=new HSSFWorkbook();
		        HSSFSheet sheet =  workbook.createSheet("FirstSheet");  
		        HSSFRow rowhead=   sheet.createRow((short)0);
		        
		        rowhead.createCell((short) 0).setCellValue("invoice number");
		        rowhead.createCell((short) 1).setCellValue("name");
		        rowhead.createCell((short) 2).setCellValue("datecreated");
		        rowhead.createCell((short) 3).setCellValue("Amount");
		        rowhead.createCell((short) 4).setCellValue("invoice amount");
		        if(!UserUtil.getMmrUser().getUserRole().equalsIgnoreCase("sales")){
		        	rowhead.createCell((short) 5).setCellValue("invoice cost");
			        rowhead.createCell((short) 6).setCellValue("status");
			        rowhead.createCell((short) 7).setCellValue("SPD");
			        rowhead.createCell((short) 8).setCellValue("LTL");
			        rowhead.createCell((short) 9).setCellValue("CHB");
			        rowhead.createCell((short) 10).setCellValue("FPA");
			        rowhead.createCell((short) 11).setCellValue("FWD");
		        }else{
			        rowhead.createCell((short) 5).setCellValue("status");
			        rowhead.createCell((short) 6).setCellValue("SPD");
			        rowhead.createCell((short) 7).setCellValue("LTL");
			        rowhead.createCell((short) 8).setCellValue("CHB");
			        rowhead.createCell((short) 9).setCellValue("FPA");
			        rowhead.createCell((short) 10).setCellValue("FWD");
		        }
		        int i=1;
		        double totalSPD=0;
		        double totalLTL=0;
		        double totalCHB=0;
		        double totalFPA=0;
		        double totalFWD=0;
		        for(Commission commission :commissionList){
		        	
		        HSSFRow row=   sheet.createRow((short)i);
		        row.createCell((short) 0).setCellValue(removeNull(commission.getInvoiceNum()));
		        row.createCell((short) 1).setCellValue(removeNull(commission.getCustomerName()));
		        row.createCell((short) 2).setCellValue(removeNull(String.valueOf(commission.getDateCreated())));
		        row.createCell((short) 3).setCellValue(removeNull(String.valueOf((FormattingUtil.roundFigureRates(commission.getCommissionPayable(),2)))));
		        row.createCell((short) 4).setCellValue(removeNull(String.valueOf(FormattingUtil.roundFigureRates(commission.getInvoiceTotal(),2))));
		        if(!UserUtil.getMmrUser().getUserRole().equalsIgnoreCase("sales")){
		        row.createCell((short) 5).setCellValue(removeNull(String.valueOf(FormattingUtil.roundFigureRates(commission.getCostTotal(),2))));		       
		        findPaymentStaus(commission.getRepPaid());
		        row.createCell((short) 6).setCellValue(removeNull(String.valueOf(paymentStatus)));
		        row.createCell((short) 7).setCellValue(removeNull(String.valueOf(FormattingUtil.roundFigureRates(commission.getTotalSPD(),2))));
		        row.createCell((short) 8).setCellValue(removeNull(String.valueOf(FormattingUtil.roundFigureRates(commission.getTotalLTL(),2))));
		        row.createCell((short) 9).setCellValue(removeNull(String.valueOf(FormattingUtil.roundFigureRates(commission.getTotalCHB(),2))));
		        row.createCell((short) 10).setCellValue(removeNull(String.valueOf(FormattingUtil.roundFigureRates(commission.getTotalFPA(),2))));
		        row.createCell((short) 11).setCellValue(removeNull(String.valueOf(FormattingUtil.roundFigureRates(commission.getTotalFWD(),2))));
		        }else{
		        	findPaymentStaus(commission.getRepPaid());
			        row.createCell((short) 5).setCellValue(removeNull(String.valueOf(paymentStatus)));
			        row.createCell((short) 6).setCellValue(removeNull(String.valueOf(FormattingUtil.roundFigureRates(commission.getTotalSPD(),2))));
			        row.createCell((short) 7).setCellValue(removeNull(String.valueOf(FormattingUtil.roundFigureRates(commission.getTotalLTL(),2))));
			        row.createCell((short) 8).setCellValue(removeNull(String.valueOf(FormattingUtil.roundFigureRates(commission.getTotalCHB(),2))));
			        row.createCell((short) 9).setCellValue(removeNull(String.valueOf(FormattingUtil.roundFigureRates(commission.getTotalFPA(),2))));
			        row.createCell((short) 10).setCellValue(removeNull(String.valueOf(FormattingUtil.roundFigureRates(commission.getTotalFWD(),2))));
		        }
		        totalSPD+=commission.getTotalSPD();
		        totalLTL+=commission.getTotalLTL();
		        totalCHB+=commission.getTotalCHB();
		        totalFPA+=commission.getTotalFPA();
		        totalFWD+=commission.getTotalFWD();
		        i++;
				 }
		        if(commissionList!=null && commissionList.size()>1){
		        HSSFRow rowheads=   sheet.createRow((short)commissionList.size()+1);
		        rowheads.createCell((short) 0).setCellValue("Total SPD");
		        rowheads.createCell((short) 1).setCellValue(String.valueOf(FormattingUtil.roundFigureRates(totalSPD,2)));
		        rowheads.createCell((short) 2).setCellValue("Total LTL");
		        rowheads.createCell((short) 3).setCellValue(String.valueOf(FormattingUtil.roundFigureRates(totalLTL,2)));
		        rowheads.createCell((short) 4).setCellValue("Total CHB");
		        rowheads.createCell((short) 5).setCellValue(String.valueOf(FormattingUtil.roundFigureRates(totalCHB,2)));
		        rowheads.createCell((short) 6).setCellValue("Total FPA");
		        rowheads.createCell((short) 7).setCellValue(String.valueOf(FormattingUtil.roundFigureRates(totalFPA,2)));
		        rowheads.createCell((short) 8).setCellValue("Total FWD");
		        rowheads.createCell((short) 9).setCellValue(String.valueOf(FormattingUtil.roundFigureRates(totalFWD,2)));
		        }
		        FileOutputStream fileOut =  new FileOutputStream(shippingLabelFileName);
		        workbook.write(fileOut);
		        fileOut.close();
		        System.out.println("Your excel file has been generated!");
		        

		    }
		 
		 public String getUniqueTempcsvFileNamecomm(String fName)
			{
				Date curDateTime = new Date();
				String tempPath = System.getProperty("java.io.tmpdir");
				File path = new File( tempPath );
				if ( !path.exists() )
					path.mkdirs();
				
				return tempPath + File.separator + fName + curDateTime.getTime() + ".csv";
			}	 
			
			public String getUniqueTempxmlFileNamecomm(String fName)
			{
				Date curDateTime = new Date();
				String tempPath = System.getProperty("java.io.tmpdir");
				File path = new File( tempPath );
				if ( !path.exists() )
					path.mkdirs();
				
				return tempPath + File.separator + fName + curDateTime.getTime() + ".xml";
			}	 
			
			public String getUniqueTempxlFileNamecomm(String fName)
			{
				Date curDateTime = new Date();
				String tempPath = System.getProperty("java.io.tmpdir");
				File path = new File( tempPath );
				if ( !path.exists() )
					path.mkdirs();
				
				return tempPath + File.separator + fName + curDateTime.getTime() + ".xls";
			}	
			
			public String getUniqueTempcsvFileNamesale(String fName)
			{
				Date curDateTime = new Date();
				String tempPath = System.getProperty("java.io.tmpdir");
				File path = new File( tempPath );
				if ( !path.exists() )
					path.mkdirs();
				
				return tempPath + File.separator + fName + curDateTime.getTime() + ".csv";
			}	 
			
			public String getUniqueTempxmlFileNamesale(String fName)
			{
				Date curDateTime = new Date();
				String tempPath = System.getProperty("java.io.tmpdir");
				File path = new File( tempPath );
				if ( !path.exists() )
					path.mkdirs();
				
				return tempPath + File.separator + fName + curDateTime.getTime() + ".xml";
			}	 
			
			public String getUniqueTempxlFileNamesale(String fName)
			{
				Date curDateTime = new Date();
				String tempPath = System.getProperty("java.io.tmpdir");
				File path = new File( tempPath );
				if ( !path.exists() )
					path.mkdirs();
				
				return tempPath + File.separator + fName + curDateTime.getTime() + ".xls";
			}

			public List<String> getInvoiceIdList() {
				return InvoiceIdList;
			}

			public void setInvoiceIdList(List<String> invoiceIdList) {
				InvoiceIdList = invoiceIdList;
			}	
  public List<Charge> getChargeList() {
    return chargeList;
  }

  public void setChargeList(List<Charge> chargeList) {
    this.chargeList = chargeList;
  }

  public List<InvoiceCharge> getInvoiceChargeList() {
    return invoiceChargeList;
  }

  public void setInvoiceChargeList(List<InvoiceCharge> invoiceChargeList) {
    this.invoiceChargeList = invoiceChargeList;
  }
  public void findPaymentStaus(int statusId){
	  	if(statusId==10){
	  		paymentStatus="Unpaid";		
	  	}else if(statusId==30){
	  		paymentStatus="Paid";
	  	}else{
	  		paymentStatus="PaidToRep";
	  	}
}

public List<CurrencySymbol> getCurrencyList() {
	return currencyList;
}

public void setCurrencyList(List<CurrencySymbol> currencyList) {
	this.currencyList = currencyList;
}
  
public SubTotal getSubtotals() {
	return subtotals;
}

public void setSubtotals(SubTotal subtotals) {
	this.subtotals = subtotals;
} 

public List<FutureReference> getfCustomers() {
	return fCustomers;
}

public void setfCustomers(List<FutureReference> fCustomers) {
	this.fCustomers = fCustomers;
}

public List<FutureReference> getShowFutureCust() {
	return showFutureCust;
}

public void setShowFutureCust(List<FutureReference> showFutureCust) {
	this.showFutureCust = showFutureCust;
 } 

public List<FutureReferencePackages> getShowfutRefPackageList() {
	return showfutRefPackageList;
}

public void setShowfutRefPackageList(List<FutureReferencePackages> showfutRefPackageList) {
	this.showfutRefPackageList = showfutRefPackageList;
}

public FutureReference getFc() {
	return fc;
}

public void setFc(FutureReference fc) {
	this.fc = fc;
}

public BusinessDAO getBusinessDAO() {
	return businessDAO;
}

public void setBusinessDAO(BusinessDAO businessDAO) {
	this.businessDAO = businessDAO;
 } 

public User getLoginedUser() {
	return loginedUser;
}

public void setLoginedUser(User loginedUser) {
	this.loginedUser = loginedUser;
 } 

//method to delete the pdf in report path
public String deleteInvoicePdf() {
    try {
    	boolean flag=true;
      //Invoice invoice = this.getInvoice();
      String invId = request.getParameter("stored_value");
      String ids[] = invId.split(",");
      for (int i = 0; i < ids.length; i++) {      
    	  long invoiceId = Long.parseLong(ids[i]);
      Invoice invoice = invoiceManager.getInvoiceById(invoiceId);
      if (invoice != null) {
        PDFRenderer pdfRenderer = new PDFRenderer();
        ArrayList<String> srcList = new ArrayList<String>();
  	    Business business = businessDAO.getBusiessById(invoice.getBusinessId());
  	    String reportPathInvoice = business.getReportPathInvoice();
  	    String invoiceNum=invoice.getInvoiceNum();
	      File folderPath = new File(reportPathInvoice);
	      if (folderPath.isDirectory()) {
	    	  File[] fList = folderPath.listFiles();
	    	  if(fList!=null){
	    		  for(File file:fList){
	    			  String fileName=file.getName();
	    			  String[] splitFileName=fileName.split("_");
			  			  if(invoiceNum.equals(splitFileName[1])){
			  				  	flag= false;
			    				srcList.add(file.toString());
			    				pdfRenderer.deleteFiles(srcList);
			  			  }
	    		  }
	    	  }
	      }
      }
      }
      if(flag == true){
    	  addActionError(getText("There is no pdf available in report path for this selection"));
      }else{
    	  addActionMessage("The Selected Invoice pdf(s) deleted Successfully");
      }
    } catch (Exception e) {
      e.printStackTrace();
      addActionError(getText("content.error.unexpected"));
    }

    return searchInvoice();
  }

}
