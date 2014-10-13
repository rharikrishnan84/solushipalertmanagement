package com.meritconinc.shiplinx.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.meritconinc.mmr.model.admin.UserSearchCriteria;
import com.meritconinc.mmr.model.security.User;
import com.meritconinc.shiplinx.dao.InvoiceDAO;
import com.meritconinc.mmr.service.UserService;
import com.meritconinc.mmr.utilities.StringUtil;
import com.meritconinc.mmr.utilities.security.UserUtil;
import com.meritconinc.shiplinx.model.ARTransaction;
import com.meritconinc.shiplinx.model.Charge;
import com.meritconinc.shiplinx.model.Commission;
import com.meritconinc.shiplinx.model.CreditCard;
import com.meritconinc.shiplinx.model.Customer;
import com.meritconinc.shiplinx.model.Invoice;
import com.meritconinc.shiplinx.model.InvoiceCharge;
import com.meritconinc.shiplinx.model.InvoiceStatus;
import com.meritconinc.shiplinx.model.SalesRecord;
import com.meritconinc.shiplinx.model.ShippingOrder;
import com.meritconinc.shiplinx.service.CustomerManager;
import com.meritconinc.shiplinx.service.InvoiceManager;
import com.meritconinc.shiplinx.service.ShippingService;
import com.meritconinc.shiplinx.utils.FormattingUtil;
import com.meritconinc.shiplinx.utils.ShiplinxConstants;
import com.opensymphony.xwork2.Preparable;

public class InvoiceManagerAction extends BaseAction implements Preparable, ServletRequestAware,
    ServletResponseAware {
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
  public List<Invoice> getInvoicesNew() {
	  	return invoicesNew;
	  }
	  
	  public void setInvoicesNew(List<Invoice> invoicesNew) {
	  	this.invoicesNew = invoicesNew;
	  }
	  
	  private List<InvoiceStatus> statusList;
  private Customer customer;
  private List<ShippingOrder> selectedOrders;
  private List<Invoice> selectedInvoices;
  private List<User> salesUsers;
  private UserService userService;

  private List<Boolean> select;
  private CreditCard creditCard;
  private List<ARTransaction> arTransactions;

  private Integer statusId;

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
	  getSession().remove("invoice");
	  Customer c = new Customer();
	    c.setBusinessId(UserUtil.getMmrUser().getBusinessId());
	    customers = customerService.search(c);
	    Invoice invoice = new Invoice();
	      invoice.setBusinessId(UserUtil.getMmrUser().getBusinessId());
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
    log.debug("In execute of InvoiceAction");
    this.statusList = invoiceManager.getInvoiceStatusList();
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
	  	    c.setBusinessId(UserUtil.getMmrUser().getBusinessId());
	  	    customers = customerService.search(c);

	  	  if (search != null && search.equalsIgnoreCase("outstanding")) {
	  			      i = new Invoice();
	  			      i.setPaymentStatusList(new int[] { Invoice.INVOICE_STATUS_UNPAID,
	  			          Invoice.INVOICE_STATUS_PARTIAL_PAID });
		} else {
			int a[] = new int[this.statusList.size()]; // statusList does'nt go
														// as null back // to
														// the form.
			for (int j = 0; j < this.statusList.size(); j++) {
				a[j] = this.statusList.get(j).getId().intValue();
			}
			i.setPaymentStatusList(a);
	  			    }

	  	i.setBusinessId(UserUtil.getMmrUser().getBusinessId());
	  		    if (UserUtil.getMmrUser().getCustomerId() > 0)
	  		      i.setCustomerId(UserUtil.getMmrUser().getCustomerId());
	  		    if (!StringUtil.isEmpty(UserUtil.getMmrUser().getBranch()))
	  		      i.setBranch(UserUtil.getMmrUser().getBranch());
	  		     
	  		      
	  		  if(i.getPaymentStatusList()[0]==50){ 
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
	    c.setBusinessId(UserUtil.getMmrUser().getBusinessId());
	    customers = customerService.search(c);
    log.debug("In generateInvoice");
    getSession().remove("invoice");
    Invoice i = this.getInvoice();
    i.setBusinessId(UserUtil.getMmrUser().getBusinessId());
    i.setCustomerId(new Long(0));

    orders = shippingService.getUnbilledShipments(i.getBusinessId(), i.getCustomerId().longValue(),
        UserUtil.getMmrUser().getBranch());
    log.debug("Found : " + orders.size() + " shipments that can be billed");
    return SUCCESS;
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

  public String createInvoice() {

	    log.debug("In createInvoice");
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

	    return SUCCESS;
	  }

  public String autoGenInvoices() {

    log.debug("In autogen Invoices");
    Invoice invoice = this.getInvoice();

    try {
      invoices = invoiceManager.autoGenInvoicesForBusiness(UserUtil.getMmrUser().getBusinessId(),
          invoice, UserUtil.getMmrUser().getBranch());
    } catch (Exception e) {
      getActionErrors().add(getText("invoice.autoGen.failed"));
    }

    this.statusList = invoiceManager.getInvoiceStatusList();
    String args[] = new String[1];
    args[0] = String.valueOf(invoices.size());
    this.addActionMessage(getText("invoice.created"));

    return SUCCESS;
  }

  public String payInvoices() {

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
   if(invoiceIds.size()>0)

    invoices = invoiceManager.processPayment(invoiceIds, creditCard, true);
/*   String args[] = new String[1];
       args[0] = String.valueOf(invoices.size());*/
   for(Invoice invoice:invoices){
    	   if(invoice.getTransaction().getStatus()!=10 && invoice.getTransaction().getProcessorTransactionId()!=null){
    		   addActionMessage("Payment has been Approved and your Receipt Id is: "+invoice.getTransaction().getReceiptId());
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
	  	    String method = request.getParameter("method");
	  	    log.debug("In update A/R-----method is:" + method);
	  	    Customer c = new Customer();
	    c.setBusinessId(UserUtil.getMmrUser().getBusinessId());
	    customers = customerService.search(c);
	    if (method != null){	  
	    		    Invoice i = this.getInvoice();
	    		    if((i.getCustomerId() == null || i.getCustomerId()==0 )&&( i.getInvoiceId()==null || i.getInvoiceId()==0)){
	    Invoice invoice = new Invoice();
	      invoice.setBusinessId(UserUtil.getMmrUser().getBusinessId());
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
    i.setBusinessId(UserUtil.getMmrUser().getBusinessId());
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
	    String id_list = request.getParameter("InvoiceIdList");
	    ids = id_list.split(","); 
	    
	    for(int i=0;i<ids.length;i++){
	     long id = Long.valueOf(ids[i]);
	     Invoice invoice = new Invoice();
	     invoice = invoiceManager.getInvoiceById(id);
	     invoice.getArTransaction().setAmount(invoice.getTotalInvoiceCharge());
	     invoice.getArTransaction().setModeOfPayment(modeofpay[i]);
	     invoice.getArTransaction().setPaymentRefNum(payrefnum[i]);
	     try{     
	      invoicesToUpdate.add(invoice);
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
    Customer c = new Customer();
    c.setBusinessId(UserUtil.getMmrUser().getBusinessId());
    customers = customerService.search(c);
    if (method != null){
    	        getSession().remove("arTransaction");
    ARTransaction arTransaction = new ARTransaction();

    arTransaction.setBusinessId(UserUtil.getMmrUser().getBusinessId());
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
      if (invoiceId != null) {
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Content-Disposition","attachment; filename=label.pdf");
        response.setHeader("Expires", "0");
        response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
        response.setHeader("Pragma", "public");
        response.setContentType("application/pdf");        
        Long l = Long.parseLong(invoiceId);
        if(salesUser!=null){
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

        invoice = invoiceManager.updateInvoice(invoice, ids, userCharges, userCosts, userNames,
            trackNos);
        this.setInvoice(invoice);
        addActionMessage("Charge Updated Successfully..");
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
    UserSearchCriteria criteria = new UserSearchCriteria();
    criteria.setBusinessId(UserUtil.getMmrUser().getBusinessId());
    criteria.setRoleCode(ShiplinxConstants.ROLE_SALES);
    criteria.setSortBy(UserSearchCriteria.SORT_BY_FIRSTNAME);
    if (!StringUtil.isEmpty(UserUtil.getMmrUser().getBranch()))
      criteria.setBranch(UserUtil.getMmrUser().getBranch());
    salesUsers = userService.findUsers(criteria, 0, 0);
    if(salesRep!=null && invoiceId!=null){
    	    	updateInvoiceStatus(salesRep,invoiceId);
    	    }
    if (method != null) {
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
        statusId = commission.getRepPaid();
        findPaymentStaus(statusId);
    return SUCCESS;
  }

  public String salesReport() throws Exception {
	  Customer c = new Customer();
	   c.setBusinessId(UserUtil.getMmrUser().getBusinessId());
	   customers = customerService.search(c);

    String method = request.getParameter("method");

    if (method != null) {
      getSession().remove("salesRecord");
      SalesRecord sales = getSalesRecord();
      return SUCCESS;
    }

    SalesRecord sales = getSalesRecord();
    sales.setBusinessId(UserUtil.getMmrUser().getBusinessId());
    sales.setMonth(Integer.valueOf(sales.getMonthName()));
    if (!StringUtil.isEmpty(UserUtil.getMmrUser().getBranch()))
      sales.setBranch(UserUtil.getMmrUser().getBranch());

    salesRecords = invoiceManager.generateSalesReport(sales);
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
      String strFile = "Invoice_" + invoiceId + ".csv";
      if (invoiceId != null) {
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename=" + strFile);
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
		
		if("xml".equalsIgnoreCase(type)){
			String shippingLabelFileName = getUniqueTempxmlFileNamesale("sales");
			write_XML_File_sales(sale,shippingLabelFileName);
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
				generateCsvFileSales(sale,writer);
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
				
				createxlfilesales(sale,shippingLabelFileName);
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
	
	public String commissionprint() throws IOException
	{
		
String method=request.getParameter("method");
String type=request.getParameter("type");
		UserSearchCriteria criteria = new UserSearchCriteria();
		criteria.setBusinessId(UserUtil.getMmrUser().getBusinessId());
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
		         amount.appendChild(doc.createTextNode(removeNull(String.valueOf(commission.getInvoiceTotal()))));
		         log1.appendChild(amount);
		         
		         if(!UserUtil.getMmrUser().getUserRole().equalsIgnoreCase("sales")){
		         Element cost = doc.createElement("Cost");
		         cost.appendChild(doc.createTextNode(removeNull(String.valueOf(commission.getCostTotal()))));
		         log1.appendChild(cost);
		         }
		         
		         Element status = doc.createElement("Status");
		         findPaymentStaus(commission.getRepPaid());
		         status.appendChild(doc.createTextNode(removeNull(String.valueOf(paymentStatus))));
		         log1.appendChild(status);
		         
		         Element SPD = doc.createElement("SPD");
		         SPD.appendChild(doc.createTextNode(removeNull(String.valueOf(commission.getTotalSPD()))));
		         log1.appendChild(SPD);
	
		         Element LTL = doc.createElement("LTL");
		         LTL.appendChild(doc.createTextNode(removeNull(String.valueOf(commission.getTotalLTL()))));
		         log1.appendChild(LTL);
		         
		         Element CHB = doc.createElement("CHB");
		         CHB.appendChild(doc.createTextNode(removeNull(String.valueOf(commission.getTotalCHB()))));
		         log1.appendChild(CHB);
		         totalSPD+=commission.getTotalSPD();
		         totalLTL+=commission.getTotalLTL();
		         totalCHB+=commission.getTotalCHB();
		       
		}
		 Element log1 = doc.createElement("Commission");
         shipingList.appendChild(log1);
		 Element tSPD = doc.createElement("TotalSPD");
         tSPD.appendChild(doc.createTextNode(removeNull(String.valueOf(totalSPD))));
         log1.appendChild(tSPD);

         Element tLTL = doc.createElement("TotalLTL");
         tLTL.appendChild(doc.createTextNode(removeNull(String.valueOf(totalLTL))));
         log1.appendChild(tLTL);
         
         Element tCHB = doc.createElement("TotalCHB");
         tCHB.appendChild(doc.createTextNode(removeNull(String.valueOf(totalCHB))));
         log1.appendChild(tCHB);
		 
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

		public  void write_XML_File_sales(List<SalesRecord> salesrecord,String shippingLabelFileName){
			
			 
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
			         Currency.appendChild(doc.createTextNode(removeNull(srecord.getCurrency())));
			         log1.appendChild(Currency);
		
			         Element Cost = doc.createElement("Totalcost");
			         Cost.appendChild(doc.createTextNode(removeNull(String.valueOf(srecord.getTotalCost()))));
			         log1.appendChild(Cost);
			         Element amount = doc.createElement("Totalcost");
			         amount.appendChild(doc.createTextNode(removeNull(String.valueOf(srecord.getTotalAmount()))));
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
	
		
		 private  void generateCsvFile(List<Commission> commissionList,FileWriter writer)
		   {
			try
			{  
				double totalSPD=0.0;
			    double totalLTL=0.0;
			    double totalCHB=0.0;
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
		        writer.append(removeNull(String.valueOf(commission.getTotalSPD())));   
		        writer.append(',');
		        writer.append(removeNull(String.valueOf(commission.getTotalLTL())));   
		        writer.append(',');
		        writer.append(removeNull(String.valueOf(commission.getTotalCHB())));
		        writer.append('\n');
		    
			 
		        totalSPD+=commission.getTotalSPD();
		        totalLTL+=commission.getTotalLTL();
		        totalCHB+=commission.getTotalCHB();
		         
			    //generate whatever data you want
		 
			   
			}
			writer.append("Total SPD");
			writer.append(',');
			writer.append(String.valueOf(totalSPD));
			writer.append(',');
			writer.append("Total LTL");
			writer.append(',');
			writer.append(String.valueOf(totalLTL));
			writer.append(',');
			writer.append("Total CHB");
			writer.append(',');
			writer.append(String.valueOf(totalCHB));
			writer.flush();
		    writer.close();
		    System.out.println("csv generated successfully");
			}
			catch(IOException e)
			{
			     e.printStackTrace();
			} 
		    }
		 
		 private  void generateCsvFileSales(List<SalesRecord> salesList,FileWriter writer)
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
		        writer.append(removeNull(salesreport.getCurrency()));   
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
		 
		 
		 public void createxlfilesales(List<SalesRecord> salesList,String shippingLabelFileName) throws IOException{
				
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
		        row.createCell((short) 2).setCellValue(removeNull(sList.getCurrency()));
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
		        }else{
			        rowhead.createCell((short) 5).setCellValue("status");
			        rowhead.createCell((short) 6).setCellValue("SPD");
			        rowhead.createCell((short) 7).setCellValue("LTL");
			        rowhead.createCell((short) 8).setCellValue("CHB");
		        }
		        int i=1;
		        double totalSPD=0;
		        double totalLTL=0;
		        double totalCHB=0;
		        for(Commission commission :commissionList){
		        	
		        HSSFRow row=   sheet.createRow((short)i);
		        row.createCell((short) 0).setCellValue(removeNull(commission.getInvoiceNum()));
		        row.createCell((short) 1).setCellValue(removeNull(commission.getCustomerName()));
		        row.createCell((short) 2).setCellValue(removeNull(String.valueOf(commission.getDateCreated())));
		        row.createCell((short) 3).setCellValue(removeNull(String.valueOf((commission.getCommissionPayable()))));
		        row.createCell((short) 4).setCellValue(removeNull(String.valueOf(commission.getInvoiceTotal())));
		        if(!UserUtil.getMmrUser().getUserRole().equalsIgnoreCase("sales")){
		        row.createCell((short) 5).setCellValue(removeNull(String.valueOf(commission.getCostTotal())));		       
		        findPaymentStaus(commission.getRepPaid());
		        row.createCell((short) 6).setCellValue(removeNull(String.valueOf(paymentStatus)));
		        row.createCell((short) 7).setCellValue(removeNull(String.valueOf(commission.getTotalSPD())));
		        row.createCell((short) 8).setCellValue(removeNull(String.valueOf(commission.getTotalLTL())));
		        row.createCell((short) 9).setCellValue(removeNull(String.valueOf(commission.getTotalCHB())));
		        }else{
		        	findPaymentStaus(commission.getRepPaid());
			        row.createCell((short) 5).setCellValue(removeNull(String.valueOf(paymentStatus)));
			        row.createCell((short) 6).setCellValue(removeNull(String.valueOf(commission.getTotalSPD())));
			        row.createCell((short) 7).setCellValue(removeNull(String.valueOf(commission.getTotalLTL())));
			        row.createCell((short) 8).setCellValue(removeNull(String.valueOf(commission.getTotalCHB())));
		        }
		        totalSPD+=commission.getTotalSPD();
		        totalLTL+=commission.getTotalLTL();
		        totalCHB+=commission.getTotalCHB();
		        i++;
				 }
		        if(commissionList!=null && commissionList.size()>1){
		        HSSFRow rowheads=   sheet.createRow((short)commissionList.size()+1);
		        rowheads.createCell((short) 0).setCellValue("Total SPD");
		        rowheads.createCell((short) 1).setCellValue(String.valueOf(totalSPD));
		        rowheads.createCell((short) 2).setCellValue("Total LTL");
		        rowheads.createCell((short) 3).setCellValue(String.valueOf(totalLTL));
		        rowheads.createCell((short) 4).setCellValue("Total CHB");
		        rowheads.createCell((short) 5).setCellValue(String.valueOf(totalCHB));
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
}
