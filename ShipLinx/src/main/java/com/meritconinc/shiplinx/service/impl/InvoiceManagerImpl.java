package com.meritconinc.shiplinx.service.impl;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import javax.mail.MessagingException;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.meritconinc.mmr.dao.UserDAO;
import com.meritconinc.mmr.model.security.User;
import com.meritconinc.mmr.utilities.MessageUtil;
import com.meritconinc.mmr.utilities.StringUtil;
import com.meritconinc.mmr.utilities.mail.MailHelper;
import com.meritconinc.mmr.utilities.security.UserUtil;
import com.meritconinc.shiplinx.dao.BusinessDAO;
import com.meritconinc.shiplinx.dao.CustomerDAO;
import com.meritconinc.shiplinx.dao.InvoiceDAO;
import com.meritconinc.shiplinx.dao.ShippingDAO;
import com.meritconinc.shiplinx.model.ARTransaction;
import com.meritconinc.shiplinx.model.Attachment;
import com.meritconinc.shiplinx.model.Business;
import com.meritconinc.shiplinx.model.CCTransaction;
import com.meritconinc.shiplinx.model.CarrierChargeCode;
import com.meritconinc.shiplinx.model.Charge;
import com.meritconinc.shiplinx.model.ChargeGroup;
import com.meritconinc.shiplinx.model.Commission;
import com.meritconinc.shiplinx.model.CreditCard;
import com.meritconinc.shiplinx.model.CurrencySymbol;
import com.meritconinc.shiplinx.model.Customer;
import com.meritconinc.shiplinx.model.CustomerSalesUser;
import com.meritconinc.shiplinx.model.Invoice;
import com.meritconinc.shiplinx.model.InvoiceStatus;
import com.meritconinc.shiplinx.model.Package;
import com.meritconinc.shiplinx.model.SalesRecord;
import com.meritconinc.shiplinx.model.Service;
import com.meritconinc.shiplinx.model.ShippingOrder;
import com.meritconinc.shiplinx.service.CreditCardTransactionManager;
import com.meritconinc.shiplinx.model.SubTotal;
import com.meritconinc.shiplinx.service.InvoiceManager;
import com.meritconinc.shiplinx.service.PinBlockManager;
import com.meritconinc.shiplinx.utils.FormattingUtil;
import com.meritconinc.shiplinx.utils.PDFRenderer;
import com.meritconinc.shiplinx.utils.ShiplinxConstants;
import com.meritconinc.shiplinx.model.FutureReference;

import com.meritconinc.shiplinx.model.FutureReferencePackages;
import java.util.Iterator;
import com.meritconinc.mmr.dao.BusinessFilterDAO;
import com.meritconinc.mmr.utilities.MmrBeanLocator;
import com.meritconinc.shiplinx.service.ShippingService;



public class InvoiceManagerImpl implements InvoiceManager {

  private static final Logger log = LogManager.getLogger(InvoiceManagerImpl.class);

  private CustomerDAO customerDAO;
  private ShippingDAO shippingDAO;
  private InvoiceDAO invoiceDAO;
  private BusinessDAO businessDAO;
  private CreditCardTransactionManager ccTransactionManager;
  private PinBlockManager pinBlockManager;
  private UserDAO userDAO;
  
  private List<Invoice> groupedInvoiceCharges;

  public BusinessDAO getBusinessDAO() {
    return businessDAO;
  }

  public void setBusinessDAO(BusinessDAO businessDAO) {
    this.businessDAO = businessDAO;
  }

  public void setCustomerDAO(CustomerDAO customerDAO) {
    this.customerDAO = customerDAO;
  }

  public void setShippingDAO(ShippingDAO shippingDAO) {
    this.shippingDAO = shippingDAO;
  }

  public void setInvoiceDAO(InvoiceDAO invoiceDAO) {
    this.invoiceDAO = invoiceDAO;
  }

  public UserDAO getUserDAO() {
    return userDAO;
  }

  public void setUserDAO(UserDAO userDAO) {
    this.userDAO = userDAO;
  }

  public void setCcTransactionManager(CreditCardTransactionManager ccTransactionManager) {
    this.ccTransactionManager = ccTransactionManager;
  }

  public void setPinBlockManager(PinBlockManager pinBlockManager) {
    this.pinBlockManager = pinBlockManager;
  }

  public List<Invoice> autoGenInvoicesForBusiness(Long businessId, Invoice invoice, String branch) {
    List<ShippingOrder> orders = shippingDAO.getUnbilledShipments(businessId, 0, branch);
    List<Long> orderIds = new ArrayList<Long>();
    for (ShippingOrder order : orders)
      orderIds.add(order.getId());
    return createInvoices(orderIds, invoice);
  }

  // The shipments to be invoiced can be for 1 or more customers, and there
  // might be multiple currencies involved.
  // 1 invoice to be created per customer and currency.
    @SuppressWarnings("rawtypes")
    public List<Invoice> createInvoices(List<Long> orderIds1, Invoice invoice) {
    	  
    	  
    	  List<Long> businessIds=shippingDAO.getBusinessIdsByorderIds(orderIds1);
    	 
          List<Invoice> allinvoices=new ArrayList<Invoice>();
    	  
    	  for(Long busid:businessIds){
    		  List<Long> orderIds=new ArrayList<Long>();
    		  
    			List<ShippingOrder> orders1 =  shippingDAO.getUnbilledShipmentsBySinglebus(busid,0L,
    			        UserUtil.getMmrUser().getBranch());
    			
    			
    			if(orders1!=null && orders1.size()>0 && orderIds1!=null && orderIds1.size()>0){
    				
    				@SuppressWarnings("rawtypes")
    				Iterator sos=orders1.iterator();
    				Iterator oids=orderIds.iterator();
    		        for(Long orderid:orderIds1){
    				    for(ShippingOrder sp: orders1){
    				       if(sp.getId().equals(orderid)){
    				    	   orderIds.add(sp.getId());
    				       }
    			 	   
    				   }
    		        }
    			} 
    			
    			
    	/*	
    			for(ShippingOrder so:orders1){
    				orderIds.add(so.getId());
    			}
    		*/
    		
      
    	  List<Invoice> invoices = new ArrayList<Invoice>();
      
    	  if(orderIds.size()>0){

    List<Long> customerIds = shippingDAO.getCustomerIdsByOrderIds(orderIds);
    List<String> currencies = shippingDAO.getCurrencyByOrderIds(orderIds);
     

    for (Long cus : customerIds) {
      for (String currency : currencies) {
        // the way we are searching shipments here is NOT optimal. Can
        // we extend the "getUnbilledShipments" query to search for
        // shipments belonging to this customer/currency combination
        // instead of injecting the orderIds?
        List<ShippingOrder> orders = shippingDAO.searchShipmentsByOrderIdsAndCustomerAndCurrency(
            orderIds, cus, currency);
        log.info("Found " + orders.size() + " orders to be billed for customer / currency : " + cus
            + " / " + currency);

        if (orders.size() > 0) {
          Invoice i = createInvoice(orders, invoice, cus, currency);
          		groupedInvoiceCharges=getInvoiceChargeDetails(i.getInvoiceId());
          		for(Invoice grpInv:groupedInvoiceCharges){
          			double totalLTL=0.0;
          			double totalFPA=0.0;
          			double totalSPD=0.0;
          			double totalCHB=0.0;
          			double totalFWD=0.0;
          			  if("SPD".equalsIgnoreCase(grpInv.getEmailType())){
          				  invoice.setTotalSPD(FormattingUtil.roundFigureRates(grpInv.getBreakdownTotal(), 2));
          				  System.out.println("SPD TYPE"+invoice.getTotalSPD());
          			  }
          			  if("LTL".equalsIgnoreCase(grpInv.getEmailType())){
          				invoice.setTotalLTL(FormattingUtil.roundFigureRates(grpInv.getBreakdownTotal(), 2));
          				System.out.println("LTL TYPE"+invoice.getTotalLTL());
          			  }
          			  if("CHB".equalsIgnoreCase(grpInv.getEmailType())){
          				invoice.setTotalCHB(FormattingUtil.roundFigureRates(grpInv.getBreakdownTotal(), 2));
          				System.out.println("CHB TYPE"+invoice.getTotalCHB());
          		 }
          			 if("FPA".equalsIgnoreCase(grpInv.getEmailType())){
          					invoice.setTotalFPA(FormattingUtil.roundFigureRates(grpInv.getBreakdownTotal(), 2));
          					System.out.println("FPA TYPE"+invoice.getTotalFPA());
          			 }
          			 if("FWD".equalsIgnoreCase(grpInv.getEmailType())){
          					invoice.setTotalFWD(FormattingUtil.roundFigureRates(grpInv.getBreakdownTotal(), 2));
          				System.out.println("FWD TYPE"+invoice.getTotalFWD());
          			 }
          		
          		totalSPD=invoice.getTotalSPD();
          		totalLTL=invoice.getTotalLTL();
          		totalCHB=invoice.getTotalCHB();
          		totalFWD=invoice.getTotalFWD();
          		totalFPA=invoice.getTotalFPA();
          		invoiceDAO.updateInvoiceTotalByEMail(totalSPD,totalLTL,totalCHB,totalFWD,totalFPA,i.getInvoiceId());
          // Start Issue No:44
          		}
          /*
           * int [] paymentStatus = {Invoice.INVOICE_STATUS_UNPAID};
           * i.setPaymentStatusString("Unpaid"); i.setPaymentStatusList(paymentStatus);
           */
          // End
          if (i != null)
            invoices.add(i);
        }
      
    }

    	  }
    	          if(invoices.size()>0){
    	          	allinvoices.addAll(invoices);
    	          }
    	         
    	      	  }}
    	      	  return allinvoices;
  }

  public Invoice createInvoice(List<ShippingOrder> orders, Invoice invoice, long customerId,
      String currency) {

    Invoice i = new Invoice();
    i.setPaidAmount(0.0);
    Customer customer = customerDAO.getCustomerInfoByCustomerId(customerId, UserUtil.getMmrUser()
        .getBusinessId());

    List<ShippingOrder> ordersInInvoice = new ArrayList<ShippingOrder>();
    try {
      List<Charge> allChargesForInvoice = new ArrayList<Charge>();
      double totalTax=0.0;
      for (ShippingOrder o : orders) {
        boolean added = false;
        double taxableAmount = 0;
        List<CarrierChargeCode> applicableTaxes = new ArrayList<CarrierChargeCode>();
        for (Charge c : o.getCharges()) {
        	if (c.getStatus() == null || c.getStatus() != ShiplinxConstants.CHARGE_READY_TO_INVOICE || c.getType()==0)
            continue;
          // Need to include charges where the charge is 0 but cost is
          // greater than 0 as it affects commissions which is based
          // on invoice amounts
        	if ((c.getCharge() == 0 && c.getCost() == 0) || (c.getCharge() != 0 && c.getCost() == 0)){ // do not
            // include
            // charges that
            // have no value
            // in invoice.
        		c.setEdiInvoiceNumber("Auto Invoiced");
        	}
          allChargesForInvoice.add(c);
          if (!added) {
            ordersInInvoice.add(o);
            added = true;
          }
          Long carrier_id=((c.getCarrierId()!=null && c.getCarrierId()>0)?c.getCarrierId():o.getCarrierId());
          Charge charge = shippingDAO.getChargeById(c.getId());
                              if(charge!=null){
                    	          	ChargeGroup chargeGroup=shippingDAO.getChargeGroup(charge.getChargeGroupId());
                    	          	int chargeGroupId = 0;
                    	          	if(chargeGroup!=null){
                    	          		chargeGroupId = (int)chargeGroup.getGroupId();
                    	          	}
                    	                    CarrierChargeCode ccc = shippingDAO.getChargeByCarrierAndCodesGroup(carrier_id,
                    	              c.getChargeCode(), c.getChargeCodeLevel2(), chargeGroupId);
                    
                              /*CarrierChargeCode ccc = shippingDAO.getChargeByCarrierAndCodes(carrier_id,
                        c.getChargeCode(), c.getChargeCodeLevel2());*/
                    	                    
          log.info(c.getChargeCode() + " " + c.getChargeCodeLevel2());
          if (ccc != null && !ccc.isTax()) {
            double cost = FormattingUtil.add(i.getInvoiceCost(), currencyConversionFromId(c.getCostcurrency(), o.getCurrency(), c.getCost())).doubleValue();
            i.setInvoiceCost(FormattingUtil.roundFigureRates(cost, 2));
            double amount = (FormattingUtil.add(i.getInvoiceAmount(), c.getCharge())).doubleValue();
            i.setInvoiceAmount(FormattingUtil.roundFigureRates(amount, 2));
            taxableAmount = (FormattingUtil.add(taxableAmount, c.getCharge().doubleValue()))
                .doubleValue();
          } else {
            applicableTaxes.add(ccc);
            double cost = FormattingUtil.add(i.getInvoiceTaxCost(), c.getCost()).doubleValue();
            i.setInvoiceTaxCost(FormattingUtil.roundFigureRates(cost, 2));
          }
                              }
        }

        // calculate taxes after all charges are taken into account
        for (Charge c : o.getCharges()) {
        	if (c.getStatus() == null || c.getStatus() != ShiplinxConstants.CHARGE_READY_TO_INVOICE || c.getType() == 0)
            continue;
        	/*CarrierChargeCode ccc = shippingDAO.getChargeByCarrierAndCodes(c.getCarrierId(),
        	              c.getChargeCode(), c.getChargeCodeLevel2());*/
        	        	
        	        	Charge charge = shippingDAO.getChargeById(c.getId());
        	        	            if(charge!=null){
        	        	            	ChargeGroup chargeGroup=shippingDAO.getChargeGroup(charge.getChargeGroupId());
        	        	            	int chargeGroupId= 0;
        	        	            	if(chargeGroup!=null){
        	        	            		chargeGroupId = (int)chargeGroup.getGroupId();
        	        	            	}
        	        	          CarrierChargeCode ccc = shippingDAO.getChargeByCarrierAndCodesGroup(c.getCarrierId(),
        	        	          c.getChargeCode(), c.getChargeCodeLevel2(), chargeGroupId);
        	boolean taxFlag=true;
          if (ccc != null && ccc.isTax()) {
        	  double tax = 0;
        	          	  if(taxableAmount >0 && ccc.getTaxRate() >0){
        	                tax = taxableAmount * ccc.getTaxRate() / 100;
        	          	  }else{
        	          		tax =  ccc.getTaxRate(); 
        	          	  }
           c.setCharge(FormattingUtil.roundFigureRates(tax, 2));
             totalTax = (FormattingUtil.add(i.getInvoiceTax(), c.getCharge())).doubleValue();
            i.setInvoiceTax(FormattingUtil.roundFigureRates(totalTax, 2));
            taxFlag=false;
          }
          Charge charges = shippingDAO.getChargeById(c.getId());
                    if(charges!=null && charges.getChargeGroupId()>0){
                	ChargeGroup chargeGroups=shippingDAO.getChargeGroup(charges.getChargeGroupId());
                          	if(chargeGroups!=null && chargeGroups.isTax() && taxFlag){
                		double tax = taxableAmount * chargeGroup.getTaxRate() / 100;
                        c.setCharge(FormattingUtil.roundFigureRates(tax, 2));
                        try {
                      	  			shippingDAO.updateCharge(c);
                      	  		} catch (Exception e) {
                      	  			// TODO Auto-generated catch block
                      	  			e.printStackTrace();
                      	  		}
                      	            totalTax+=tax;
                      	            //double totalTax = (FormattingUtil.add(i.getInvoiceTax(), c.getCharge())).doubleValue();
                        i.setInvoiceTax(FormattingUtil.roundFigureRates(totalTax, 2));
                	}
                  }
        }
      }
      }
      if (allChargesForInvoice.size() == 0) // did not find any charges
        // that can be invoiced
        return null;
      User user = UserUtil.getMmrUser();
      if (user != null && user.getTimeZone() != null && !user.getTimeZone().isEmpty()) {
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone(user.getTimeZone()));
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setTimeZone(cal.getTimeZone());
        i.setDateCreated(Timestamp.valueOf(dateFormat.format(cal.getTime())));
        i.setInvoiceDate(Timestamp.valueOf(dateFormat.format(cal.getTime())));
      } else {
        i.setDateCreated(new Timestamp(new Date().getTime()));
        i.setInvoiceDate(new Timestamp(new Date().getTime()));
      }

      i.setBusinessId(customer.getBusinessId());
      i.setCustomer(customer);
      i.setCustomerId(customerId);
      i.setPaymentStatus(Invoice.INVOICE_STATUS_UNPAID);
      i.setPayableDays(customer.getPayableDays());
      i.setDateCreated(new Timestamp(new Date().getTime()));
      i.setInvoiceDate(new Timestamp(new Date().getTime()));
      // setting the default currency of the customer to the invoice if it
      // is set
      if (!StringUtil.isEmpty(currency))
        i.setCurrency(currency);
      else
        i.setCurrency(customer.getDefaultCurrency());

      String[] pins = pinBlockManager.getNewPrefixedPinNumbers(
          ShiplinxConstants.PIN_TYPE_INVOICE_NUMBERS, 1, i.getBusinessId());
      i.setInvoiceNum(pins[0]);

      // determine the due date for the invoice
      int payableDays = customer.getPayableDays();
      Date dueDate = FormattingUtil.addDaysToDate(i.getInvoiceDate(), payableDays);
      i.setInvoiceDueDate(new Timestamp(dueDate.getTime()));

      Long invoiceId = invoiceDAO.createInvoice(i);

      for (Charge charge : allChargesForInvoice) {
        invoiceDAO.addShipmentAndChargeToInvoice(i.getInvoiceId(), charge.getOrderId(),
            charge.getId());
        // now add the mapping of invoices, shipments and charges
        charge.setStatus(ShiplinxConstants.CHARGE_INVOICED);
        shippingDAO.updateCharge(charge);
      }

      // For each order in the invoice, we need to determine how much was
      // paid previously and apply it to the invoice
      double invoiceAmt = 0.0;
	    double invoiceCost = 0.0;
	    double invoiceTax = 0.0;
      for (ShippingOrder order : ordersInInvoice) {
        double totalChargeForOrderOnInvoice = 0.0;
        for (Charge charge : allChargesForInvoice) {
          if (charge.getOrderId() != order.getId())
            totalChargeForOrderOnInvoice = FormattingUtil.add(totalChargeForOrderOnInvoice,
                charge.getCharge().doubleValue()).doubleValue();
          /// ============ Exchange Rate =========
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
          /// ======== End ==========
        }
        double unappliedAmount = order.getPaidAmount() - order.getAppliedAmount()
            - order.getRefundedAmount();
        if (unappliedAmount > 0) { // there is balance from what was
          // paid that can be applied to the
          // invoice
          if (totalChargeForOrderOnInvoice < unappliedAmount) { // unapplied
            // is
            // more
            // than
            // what
            // is
            // needed
            order.setAppliedAmount(FormattingUtil.add(order.getAppliedAmount().doubleValue(),
                totalChargeForOrderOnInvoice).doubleValue());
            i.setPaidAmount(FormattingUtil.add(i.getPaidAmount().doubleValue(),
                totalChargeForOrderOnInvoice).doubleValue());
          } else { // unapplied is less that what is needed, only
            // apply the unapplied amount as thats all we
            // have available
            order.setAppliedAmount(FormattingUtil.add(order.getAppliedAmount().doubleValue(),
                unappliedAmount).doubleValue());
            i.setPaidAmount(FormattingUtil.add(i.getPaidAmount().doubleValue(), unappliedAmount)
                .doubleValue());
          }
        }
        order.setBillingStatus(50);
        order.setInvoiced(true);

        shippingDAO.updateShippingOrder(order);
      }
      
 //// Calculate Commission Written By Mohan R on 18-09-2014
      /*List<CustomerSalesUser> customerSalesUserList = new ArrayList<CustomerSalesUser>();
            customerSalesUserList = customerDAO.getCustomerSalesInformation(customerId);
            for(CustomerSalesUser customerSalesUser:customerSalesUserList){
          	  double totalCommissionAmount = 0.0;
          	  double totalCHB=0.0;
          	  double totalLTL=0.0;
          	  double totalSPD=0.0;
          	  Commission commission = new Commission();
          	  for (ShippingOrder order : ordersInInvoice) {
          		  Service service=shippingDAO.getServiceById(order.getServiceId());
          		  for (Charge charge : allChargesForInvoice) {
            Long carrier_id=((charge.getCarrierId()!=null && charge.getCarrierId()>0)?charge.getCarrierId():order.getCarrierId());
            CarrierChargeCode ccc = shippingDAO.getChargeByCarrierAndCodesGroup(carrier_id,
      	              charge.getChargeCode(), charge.getChargeCodeLevel2(), charge.getChargeGroupId());
            double totalCostForOrder = 0, totalChargeForOrder = 0;
            if (ccc == null || ccc.isTax())
                continue;
            if(charge!=null && charge.getCost()!=null){
                totalCostForOrder = FormattingUtil.add(charge.getCost().doubleValue(), totalCostForOrder)
                    .doubleValue();
                totalChargeForOrder = FormattingUtil
                    .add(charge.getCharge().doubleValue(), totalChargeForOrder).doubleValue();
                }
            
            double commissionPerc = 0; // to be determined based on the
            if (service.getEmailType().equalsIgnoreCase(ShiplinxConstants.LTL_EMAIL_TYPE)){
          	  commissionPerc = customerSalesUser.getCommissionPercentagePerPalletService();
          	  totalLTL +=(totalChargeForOrder - totalCostForOrder) * commissionPerc / 100;
            }
        else if (service.getEmailType().equalsIgnoreCase(ShiplinxConstants.SPS_EMAIL_TYPE)){
          commissionPerc = customerSalesUser.getCommissionPercentagePerSkidService();
          totalSPD +=(totalChargeForOrder - totalCostForOrder) * commissionPerc / 100;
        }
        else if(service.getEmailType().equalsIgnoreCase(ShiplinxConstants.CHB_EMAIL_TYPE)){
      	  commissionPerc=customerSalesUser.getCommisionPercentagePerCHB();// default
            totalCHB +=(totalChargeForOrder - totalCostForOrder) * commissionPerc / 100;
        }
           double commissionAmount = (totalChargeForOrder - totalCostForOrder) * commissionPerc / 100;
           
           totalCommissionAmount+=commissionAmount;
          		  }
          	  }
          	  commission.setCommissionPayable(totalCommissionAmount);
          	  commission.setInvoiceId(invoiceId);
          	  commission.setUserId(Long.valueOf(customerSalesUser.getId()));
          	  commission.setCustomerName(customer.getName());
          	  invoiceDAO.updateInvoiceCommissionAmount(commission);
          	  commission.setSalesUser(customerSalesUser.getSalesAgent());
          	  commission.setUserId(new Long(customerSalesUser.getId()));
                commission.setCustomerId(i.getCustomerId());
                commission.setInvoiceNum(i.getInvoiceNum());
                commission.setRepPaid(i.getPaymentStatus());
                commission.setCustomerPaid(i.getPaymentStatus());
                commission.setCostTotal(i.getInvoiceCost());
                commission.setInvoiceTotal(i.getInvoiceAmount());
                commission.setInvoiceId(invoiceId);
                commission.setDateCreated(new Timestamp(new Date().getTime()));
                commission.setTotalCHB(totalCHB);
            	  commission.setTotalLTL(totalLTL);
            	  commission.setTotalSPD(totalSPD);
                if(commission.getCommissionPayable()>0)
                invoiceDAO.createcommission(commission);
            }     */      
            ///End Of Commission Calculation
            // Determine the payment status of the invoice
      if (i.getBalanceDue() == 0)
        i.setPaymentStatus(Invoice.INVOICE_STATUS_PAID);
      else if (i.getBalanceDue() > 0 && i.getBalanceDue() < i.getTotalInvoiceCharge()) {
        log.info("Invoice id: " + invoiceId + " . Total Charge / Balance Due : "
            + i.getTotalInvoiceCharge() + " / " + i.getBalanceDue());
        i.setPaymentStatus(Invoice.INVOICE_STATUS_PARTIAL_PAID);
      }

      invoiceDAO.updateInvoice(i);

      // If this is a credit card customer, we need to charge them for the
      // invoice
      try {
        if (i.getBalanceDue() > 0
            && customer.getPaymentType() == ShiplinxConstants.PAYMENT_TYPE_CREDIT_CARD) {
          List<Long> invoiceIds = new ArrayList<Long>();
          invoiceIds.add(invoiceId);
          processPayment(invoiceIds, customer.getCreditCardActive(), false);
        }
      } catch (Exception e) {
        log.error("Unable to charge customer: " + customer.getName()
            + " credt card for invoice charges", e);
      }
      /// ============= Exchange Rate ===========
     // i.setInvoiceAmount(invoiceAmt);
     // i.setInvoiceCost(invoiceCost);
       if(invoiceTax!=0.0){
         i.setInvoiceTax(invoiceTax);
       }
         CurrencySymbol currencySymbol = new CurrencySymbol();
         if(i.getCustomer().getDefaultCurrency() != null && !i.getCustomer().getDefaultCurrency().equals("")){
   	      currencySymbol = shippingDAO.getSymbolByCurrencycode(i.getCustomer().getDefaultCurrency());
   	      i.setInvoiceCurrencySymbol(currencySymbol.getCurrencySymbol());
          // getSession().put("customerDefaultCurrency", currencySymbol.getCurrencySymbol());
   	    }else{
		    	if(UserUtil.getMmrUser().getLocale() != null){
			    		if(!UserUtil.getMmrUser().getUserRole().equals(ShiplinxConstants.ROLE_ADMIN)){
			    	 currencySymbol = shippingDAO.getCurrencyCodeByCountryName(UserUtil.getMmrUser().getLocale().substring(3,5));
			    	}else if(orders.get(0).getCharges().get(0).getChargecurrency()>0){
			    		currencySymbol = shippingDAO.getCurrencyCodeById(orders.get(0).getCharges().get(0).getChargecurrency());
			    	}
			    	 //---------------------
				      	  if(currencySymbol==null){
				      		  for(int j=0;j<ShiplinxConstants.EURO_UNION_LIST.length;j++){
				      			  if(UserUtil.getMmrUser().getLocale().substring(3,5).equalsIgnoreCase(ShiplinxConstants.EURO_UNION_LIST[j])){
				      				currencySymbol=shippingDAO.getCurrencyCodeByCountryName("EUCG");
				      				break;
				      			  }
				      		  }
				      	  }
			    	 //-------------------------
				      	i.setInvoiceCurrencySymbol(currencySymbol.getCurrencySymbol());
			    	}else{
			    		i.setInvoiceCurrencySymbol(ShiplinxConstants.DefaultCurrencySymbol);
			    	}
			    	
			    }
       /// =============== End ==================
         i.setInvoiceAmount(i.getInvoiceAmount()-i.getInvoiceTax());
         i.setInvoiceCost(i.getInvoiceCost()-i.getInvoiceTax());
    } catch (Exception e) {
      log.error("Unable to create invoice for customer " + customer.getName());
      log.error(e.getMessage(), e);
      return null;
    }
    return i;
  }

  public void calculateInvoiceCharges(Invoice i) {

    i.setInvoiceAmount(new Double(0));
    i.setInvoiceCost(new Double(0));
    i.setInvoiceTax(new Double(0));
    i.setInvoiceTaxCost(new Double(0));

    double totalTax=0.0;
    for (ShippingOrder o : i.getOrders()) {
      double taxableAmount = 0;
      List<CarrierChargeCode> applicableTaxes = new ArrayList<CarrierChargeCode>();
      for (Charge c : o.getChargesForInvoice()) {

    	  /*CarrierChargeCode ccc = shippingDAO.getChargeByCarrierAndCodes(c.getCarrierId(),
    	              c.getChargeCode(), c.getChargeCodeLevel2());*/
    	      	  
    	      	  Charge charge = shippingDAO.getChargeById(c.getId());
    	      	            if(charge!=null){
    	      	  	          	ChargeGroup chargeGroup=shippingDAO.getChargeGroup(charge.getChargeGroupId());
    	      	  	          	int chargeGroupId = 0;
    	      	            	if(chargeGroup!=null){
    	      	  	          		chargeGroupId = (int)chargeGroup.getGroupId();
    	      	  	          	}
    	      	  	                    CarrierChargeCode ccc = shippingDAO.getChargeByCarrierAndCodesGroup(c.getCarrierId(),
    	      	  	              c.getChargeCode(), c.getChargeCodeLevel2(), chargeGroupId);

        log.info(c.getChargeCode() + " " + c.getChargeCodeLevel2());
        if (ccc!=null && !ccc.isTax()) {
          double cost = FormattingUtil.add(i.getInvoiceCost(), c.getCost()).doubleValue();
          i.setInvoiceCost(FormattingUtil.roundFigureRates(cost, 2));
          double amount = (FormattingUtil.add(i.getInvoiceAmount(), c.getCharge())).doubleValue();
          i.setInvoiceAmount(FormattingUtil.roundFigureRates(amount, 2));
          taxableAmount = (FormattingUtil.add(taxableAmount, c.getCharge().doubleValue()))
              .doubleValue();
        } else {
          applicableTaxes.add(ccc);
          double cost = FormattingUtil.add(i.getInvoiceTaxCost(), c.getCost()).doubleValue();
          double invoiceTaxCharge = FormattingUtil.add(i.getInvoiceTax(), c.getCharge()).doubleValue();
          i.setInvoiceTaxCost(FormattingUtil.roundFigureRates(cost, 2));
          i.setInvoiceTax(FormattingUtil.roundFigureRates(invoiceTaxCharge, 2));
        }
    	      	            }
      }

      for (Charge c : o.getChargesForInvoice()) {
    	  Charge charge = shippingDAO.getChargeById(c.getId());
    	      	            if(charge!=null){
    	      	  	          	ChargeGroup chargeGroup=shippingDAO.getChargeGroup(charge.getChargeGroupId());
    	      	  	          	int chargeGroupId = 0;
    	      	  	          	if(chargeGroup!=null){
    	      	  	          		chargeGroupId = (int)chargeGroup.getGroupId();
    	      	  	          	}
    	      	  	                    CarrierChargeCode ccc = shippingDAO.getChargeByCarrierAndCodesGroup(c.getCarrierId(),
    	      	  	              c.getChargeCode(), c.getChargeCodeLevel2(), chargeGroupId);
        boolean taxFlag=true;
        if (ccc!=null && ccc.isTax()) {
        	double tax=0;
        	if(charge.getCalculateTax()==1){
        		tax= taxableAmount * ccc.getTaxRate() / 100;
        		charge.setCharge(FormattingUtil.roundFigureRates(tax, 2));
        	}
          try {
        	  			shippingDAO.updateCharge(charge);
        	  		} catch (Exception e) {
        	  			// TODO Auto-generated catch block
        	  			e.printStackTrace();
        	  		}
                       if(charge.getCalculateTax()==1){
        	            totalTax+=tax;
                       
        	            //double totalTax = (FormattingUtil.add(i.getInvoiceTax(), c.getCharge())).doubleValue();
        	            i.setInvoiceTax(FormattingUtil.roundFigureRates(totalTax, 2));
                       }else{
                    	   totalTax+=charge.getCharge();
                          i.setInvoiceTax(FormattingUtil.roundFigureRates(totalTax, 2));
                       }
          taxFlag=false;
        }
        
                if(charge!=null && charge.getChargeGroupId()>0){
            	ChargeGroup chargeGroups=shippingDAO.getChargeGroup(charge.getChargeGroupId());
                    	if(chargeGroups!=null&& chargeGroups.isTax() && taxFlag){
                    		double tax=0;
                    		if(charge.getCalculateTax()==1){
            		                tax = taxableAmount * chargeGroup.getTaxRate() / 100;
                                    charge.setCharge(FormattingUtil.roundFigureRates(tax, 2));
                    		}
                    try {
                  	  			shippingDAO.updateCharge(charge);
                  	  		} catch (Exception e) {
                  	  			// TODO Auto-generated catch block
                  	  			e.printStackTrace();
                  	  		}
                               if(charge.getCalculateTax()==1){
                  	            totalTax+=tax;
                  	            //double totalTax = (FormattingUtil.add(i.getInvoiceTax(), c.getCharge())).doubleValue();
                  	            i.setInvoiceTax(FormattingUtil.roundFigureRates(totalTax, 2));
                               }else{
                            	   totalTax+=charge.getCharge();
                                   i.setInvoiceTax(FormattingUtil.roundFigureRates(totalTax, 2));
                               }
                    
            	}
                }
    	      	            }
      }
    }
    if (i.getBalanceDue() == 0)
      i.setPaymentStatus(Invoice.INVOICE_STATUS_PAID);
    else if (i.getBalanceDue() > 0 && i.getBalanceDue() < i.getTotalInvoiceCharge()) {
      log.info("Invoice id: " + i.getInvoiceId() + " . Total Charge / Balance Due : "
          + i.getTotalInvoiceCharge() + " / " + i.getBalanceDue());
      i.setPaymentStatus(Invoice.INVOICE_STATUS_PARTIAL_PAID);
    }

    invoiceDAO.updateInvoice(i);
////Calculate Commission Written By Mohan R on 18-09-2014
    List<Commission> commissions = invoiceDAO.getCommissionsByInvoiceId(i.getInvoiceId());
        if(commissions!=null && commissions.size()>0){
    List<CustomerSalesUser> customerSalesUserList = new ArrayList<CustomerSalesUser>();
    customerSalesUserList = customerDAO.getCustomerSalesInformation(i.getCustomerId());
    for(CustomerSalesUser customerSalesUser:customerSalesUserList){
  	  double totalCommissionAmount = 0.0;
  	  double totalCHB=0.0;
	  double totalLTL=0.0;
	  double totalSPD=0.0;
  	  Commission commission = new Commission();
  	  for (ShippingOrder order : i.getOrders()) {
  		  Service service=shippingDAO.getServiceById(order.getServiceId());
  		  List<Charge> charges=shippingDAO.getShippingOrderChargesByInvoice(order.getId(), i.getInvoiceId());
  		  for (Charge charge : charges) {
    Long carrier_id=((charge.getCarrierId()!=null && charge.getCarrierId()>0)?charge.getCarrierId():order.getCarrierId());
    CarrierChargeCode ccc = shippingDAO.getChargeByCarrierAndCodesGroup(carrier_id,
              charge.getChargeCode(), charge.getChargeCodeLevel2(), charge.getChargeGroupId());
    double totalCostForOrder = 0, totalChargeForOrder = 0;
    if (ccc == null || ccc.isTax())
        continue;
    if(charge!=null && charge.getCost()!=null){
        totalCostForOrder = FormattingUtil.add(charge.getCost().doubleValue(), totalCostForOrder)
            .doubleValue();
        totalChargeForOrder = FormattingUtil
            .add(charge.getCharge().doubleValue(), totalChargeForOrder).doubleValue();
        }
    
    double commissionPerc = 0; // to be determined based on the
    if (service.getEmailType().equalsIgnoreCase(ShiplinxConstants.LTL_EMAIL_TYPE)){
  	  commissionPerc = customerSalesUser.getCommissionPercentagePerPalletService();
  	  totalLTL +=(totalChargeForOrder - totalCostForOrder) * commissionPerc / 100;
    }
else if (service.getEmailType().equalsIgnoreCase(ShiplinxConstants.SPS_EMAIL_TYPE)){
  commissionPerc = customerSalesUser.getCommissionPercentagePerSkidService();
  totalSPD +=(totalChargeForOrder - totalCostForOrder) * commissionPerc / 100;
}
else if(service.getEmailType().equalsIgnoreCase(ShiplinxConstants.CHB_EMAIL_TYPE)){
	  commissionPerc=customerSalesUser.getCommisionPercentagePerCHB();// default
    totalCHB +=(totalChargeForOrder - totalCostForOrder) * commissionPerc / 100;
}
   double commissionAmount = (totalChargeForOrder - totalCostForOrder) * commissionPerc / 100;
   
   totalCommissionAmount+=commissionAmount;
  		  }
  	  }
  	  commission.setCostTotal(i.getInvoiceCost());
  	  commission.setInvoiceTotal(i.getInvoiceAmount());
  	  commission.setCommissionPayable(totalCommissionAmount);
  	  commission.setInvoiceId(i.getInvoiceId());
  	  commission.setUserId(Long.valueOf(customerSalesUser.getId()));
  	  commission.setTotalCHB(totalCHB);
 	  commission.setTotalLTL(totalLTL);
 	  commission.setTotalSPD(totalSPD);
  	  if(commission.getCommissionPayable()>0)
  	  invoiceDAO.updateInvoiceCommissionAmount(commission);
    }  
        }
    ///End Of Commission Calculation
  }

  public boolean cancelInvoice(long invoiceId) {
    Invoice invoice = invoiceDAO.getInvoiceById(invoiceId);
    if (invoice == null) {
      log.error("Could not find invoice with id " + invoiceId);
    }
    try {
      if (invoice.getPaymentStatus() != Invoice.INVOICE_STATUS_UNPAID) {
        log.error("Invoice " + invoice.getInvoiceNum() + " cannot be cancelled!");
        return false;
      }

      for (ShippingOrder order : invoice.getOrders()) {
        List<Charge> charges = (shippingDAO.getShippingOrderChargesByInvoice(order.getId(),
            invoice.getInvoiceId()));

        for (Charge charge : charges) {
        	charge.setStatus(ShiplinxConstants.CHARGE_CANCELLED);
          shippingDAO.updateCharge(charge);
          shippingDAO.updateDeletedInvoice(charge);
        }
        shippingDAO.updateShippingOrderBillingStatus(order);
      }

      invoice.setPaymentStatus(Invoice.INVOICE_STATUS_CANCELLED);

      // delete all mappings of the charges to invoice in the
      // invoice_charges table
      invoiceDAO.deleteShipmentAndChargeFromInvoice(invoice.getInvoiceId(), 0, 0);

      invoiceDAO.updateInvoice(invoice);
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

    } catch (Exception e) {
      log.error("Unable to cancel invoice" + invoice.getInvoiceNum(), e);
      return false;
    }

    return true;

  }

  private double determinePreviouslyPaidAmount(long orderId, long invoiceId) {
    // if the order was already billed previously on another invoice, then
    // we do not consider the amounts that were paid on credit card at
    // shipment time
    // as those charges would already have been taken into account when
    // creating the previous invoice. In other words, the new charges coming
    // in are adjustments

    double totalPaid = 0.0;
    List<Long> invoices = invoiceDAO.findPreviousInvoiceId(orderId, invoiceId);
    if (invoices == null || invoices.size() == 0) {
      log.info("Order " + orderId + " is being invoiced for the first time");

      ShippingOrder order = this.shippingDAO.getShippingOrder(orderId);

      for (CCTransaction transaction : order.getCcTransactions()) {
        if (transaction.getStatus() == CCTransaction.PROCESSED_STATUS) {
          totalPaid = FormattingUtil.add(totalPaid, transaction.getAmount()).doubleValue();
        }
      }

    }
    // shipment has been invoiced before
    return totalPaid;
  }

  public List<Invoice> searchInvoices(Invoice invoice) {

    if (invoice.getFromInvoiceDate_web() != null && invoice.getFromInvoiceDate_web().length() > 0) {
      Date from = FormattingUtil.getDate(invoice.getFromInvoiceDate_web(),
          FormattingUtil.DATE_FORMAT_WEB);
      invoice.setFromInvoiceDate(from);
    }
    if (invoice.getToInvoiceDate_web() != null && invoice.getToInvoiceDate_web().length() > 0) {
      Date to = FormattingUtil.getDateEndOfDay(invoice.getToInvoiceDate_web(),
          FormattingUtil.DATE_FORMAT_WEB_ENDOFDAY);
      invoice.setToInvoiceDate(to);
    }

    boolean statusSelected = false;
    for (int i : invoice.getPaymentStatusList()) {
      if (i > 0)
        statusSelected = true;
      break;
    }
    if (!statusSelected)
      invoice.setPaymentStatusList(new int[0]);

    return invoiceDAO.searchInvoices(invoice);
  }

  public Invoice getInvoiceById(long invoiceId) {
    Invoice invoice = invoiceDAO.getInvoiceById(invoiceId);

    // Not all charges of a shipment will necessarily belong to a given
    // invoice. The charges of a shipment may be spread across several
    // invoices.
    // Here we are attaching the charges to the shipments based on a
    // specific invoice.
    for (ShippingOrder order : invoice.getOrders()) {
     /* order.setChargesForInvoice(shippingDAO.getShippingOrderChargesByInvoice(order.getId(),
          invoice.getInvoiceId()));
    }*/
    	List<Charge> charges = shippingDAO.getShippingOrderChargesByInvoice(order.getId(),
    			      	          invoice.getInvoiceId());
    			        if(charges!=null && charges.size()>0){
    			        	
    			        
    			        for(int index=0; index < charges.size(); index++) {
    			      	  int totalsize = charges.size(); 
    			      	  System.out.println(charges.get(index).getName());
    			      	  if((charges.get(index).getName().equals("HST ON"))||(charges.get(index).getName().equals("HST")) ||
    			      		 (charges.get(index).getName().equals("GST"))||(charges.get(index).getName().equals("GST ON"))){
    			      		  Collections.swap(charges, index, totalsize-1);
    			      		  break;
    			      	  } 
    			      	}
    			        }
    			        order.setChargesForInvoice(charges);
    			      }

    return invoice;
  }

  public List<ShippingOrder> getShippingOrders(long invoiceId) {
    return null;
  }

  public List<Invoice> processPayment(List<Long> invoiceIds, CreditCard creditCard,
      boolean sendAdminNotification) {
    List<Invoice> invoices = new ArrayList<Invoice>();

    Invoice i = invoiceDAO.getInvoiceById(invoiceIds.get(0));
    Customer c = i.getCustomer();
    StringBuilder stb = new StringBuilder();
    stb.append("Dear Admin,\n\nThe following payments were processed for invoices belonging to customer: "
        + c.getName() + "\n\n");
    Business b = businessDAO.getBusiessById(i.getBusinessId());

    for (long id : invoiceIds) {
      Invoice invoice = this.invoiceDAO.getInvoiceById(id);
      if (b == null)
        b = businessDAO.getBusiessById(invoice.getBusinessId());

      double balanceDue = invoice.getBalanceDue();
      CCTransaction transaction = new CCTransaction();
      transaction.setEntityId(invoice.getInvoiceId());
      transaction.setEntityType(CCTransaction.ENTITY_TYPE_INVOICE);
      transaction.setBusinessId(invoice.getBusinessId());
      transaction.setCustomerId(invoice.getCustomerId());

      stb.append("Invoice #" + invoice.getInvoiceNum() + " Amount: " + invoice.getCurrency() + " "
          + FormattingUtil.roundFigureRates(balanceDue, 2) + " Result: ");

      try {
        transaction = ccTransactionManager.chargeCard(transaction, balanceDue, creditCard,
            invoice.getCurrency(), invoice.getCustomer());
      } catch (Exception e) {
        log.error("Unable to process payment for invoice " + id, e);
      }

      if (transaction.getStatus() == CCTransaction.PROCESSED_STATUS) {
        stb.append("Payment Processed.\n");
        ARTransaction aRTransaction = new ARTransaction();
        aRTransaction.setModeOfPayment(ShiplinxConstants.PAYMENT_TYPE_CC);
        aRTransaction.setPaymentRefNum(transaction.getAuthNum());
        aRTransaction.setPaymentDate(new Timestamp(transaction.getDateCreated().getTime()));
        aRTransaction.setInvoiceId(invoice.getInvoiceId());
        aRTransaction.setAmount(invoice.getBalanceDue()); // assumption
        // is that
        // customer
        // pays full
        // amount
        // due
        // online,
        // no option
        // of
        // partial
        // paying
        addARTransaction(aRTransaction);
        invoice.setPaidAmount(balanceDue);
        invoice.setBalanceDue(0.0);
      } else {
        stb.append("Payment Not Processed!\n");
      }
      invoice.setTransaction(transaction); // for display purposes only
      invoices.add(invoice);

    }
    stb.append("\nSystem Generated Email.");

    if (sendAdminNotification) {
      try {
        MailHelper.sendEmailNow2(b.getSmtpHost(), b.getSmtpUsername(), b.getSmtpPassword(),
            b.getName(), b.getSmtpPort(), b.getFinanceEmail(), b.getFinanceEmail(), null,
            "Credit Card Payment Notification", stb.toString(), null, false);
      } catch (MessagingException e) {
        log.error("Error sending email - Messaging Exception: ", e);
      }
    }

    return invoices;
  }

  public void processAR(List<Invoice> invoicesToUpdate) {
    for (Invoice i : invoicesToUpdate) {
      ARTransaction transaction = i.getArTransaction();
      transaction.setInvoiceId(i.getInvoiceId());
      if (transaction.getPaymentDate_web() == null
          || transaction.getPaymentDate_web().length() == 0) {
        java.util.Date today = new java.util.Date();
        transaction.setPaymentDate(new Timestamp(today.getTime()));
      } else {
        Date paymentDate = FormattingUtil.getDate(transaction.getPaymentDate_web(),
            FormattingUtil.DATE_FORMAT_WEB);
        transaction.setPaymentDate(new Timestamp(paymentDate.getTime()));
      }
      addARTransactionCommission(transaction);
      addARTransaction(transaction);

    }
  }

  public void addARTransaction(ARTransaction transaction) {
    Invoice invoice = invoiceDAO.getInvoiceById(transaction.getInvoiceId());
    transaction.setBusinessId(invoice.getBusinessId());
    transaction.setCustomerId(invoice.getCustomerId());
    if(transaction.getAmount()!=null){
    invoice.setPaidAmount(FormattingUtil.add(invoice.getPaidAmount().doubleValue(),
        transaction.getAmount().doubleValue()).doubleValue());
    }
    if (invoice.getBalanceDue() == 0)
      invoice.setPaymentStatus(Invoice.INVOICE_STATUS_PAID);
    else if (invoice.getBalanceDue() > 0
        && invoice.getBalanceDue() < invoice.getTotalInvoiceCharge()) {
      log.info("Invoice id: " + invoice.getInvoiceId() + " . Total Charge / Balance Due : "
          + invoice.getTotalInvoiceCharge() + " / " + invoice.getBalanceDue());
      invoice.setPaymentStatus(Invoice.INVOICE_STATUS_PARTIAL_PAID);
    }

    invoiceDAO.updateInvoice(invoice);

    try {
      invoiceDAO.addARTransaction(transaction);
    } catch (Exception e) {
      log.error("Could not update receivable for invoice " + invoice.getInvoiceId());
    }

  }

  public List<ARTransaction> searchARTransaction(ARTransaction arTransaction) {
    if (arTransaction.getFromTransactionDate_web() != null
        && arTransaction.getFromTransactionDate_web().length() > 0) {
      Date from = FormattingUtil.getDate(arTransaction.getFromTransactionDate_web(),
          FormattingUtil.DATE_FORMAT_MMDDYYYY);
      arTransaction.setFromTransactionDate(from);
    }
    if (arTransaction.getToTransactionDate_web() != null
        && arTransaction.getToTransactionDate_web().length() > 0) {
      Date to = FormattingUtil.getDateEndOfDay(arTransaction.getToTransactionDate_web(),
          FormattingUtil.DATE_FORMAT_MMDDYYYY_HHMMSS);
      arTransaction.setToTransactionDate(to);
    }

    return invoiceDAO.searchARTransaction(arTransaction);
  }

  public List<InvoiceStatus> getInvoiceStatusList() {
    return invoiceDAO.getInvoiceStatusList();
  }

  public void getInvoicePdf(Long id, String url, OutputStream outStream, boolean detailed)
      throws Exception {
   // try {
     /* Invoice invoice = getInvoiceById(id);

      PDFRenderer pdfRenderer = new PDFRenderer();

      String mainPDFFileName = pdfRenderer.getUniqueTempPDFFileName("invoice"
          + invoice.getInvoiceNum());
      generateInvoiceMainPage(url, invoice, mainPDFFileName);
      ArrayList<String> srcList = new ArrayList<String>();
      srcList.add(mainPDFFileName);

      if (detailed) {
        // String ediPDFFileName =
        // pdfRenderer.getUniqueTempPDFFileName("ediInvoice");
        // if ( generateInvoiceEdiBackupPages(invoice, ediPDFFileName) )
        // srcList.add(ediPDFFileName);
        //
        // String webPDFFileName =
        // pdfRenderer.getUniqueTempPDFFileName("webInvoice");
        // if ( generateInvoiceWOBackupPages(invoice, webPDFFileName) )
        // srcList.add(webPDFFileName);
      }

      pdfRenderer.concatPDF(srcList, outStream);*/
    	try {
	 	      Invoice invoice = getInvoiceById(id);
		      boolean flag=false;
		      PDFRenderer pdfRenderer = new PDFRenderer();
		     ArrayList<String> srcList = new ArrayList<String>();
		     Business business = getBusinessDAO().getBusiessById(invoice.getBusinessId());
		       String reportPathInvoice = business.getReportPathInvoice();
		       String mainPDFFileName = pdfRenderer.getUniquePDFFileName(reportPathInvoice,"invoice_"
		           + invoice.getInvoiceNum()+"_");
		     String invoiceNum=invoice.getInvoiceNum();
		       File folderPath = new File(reportPathInvoice);
		       if (folderPath.isDirectory()) {
		        File[] fList = folderPath.listFiles();
		        if(fList!=null){
		         for(File file:fList){
		          String fileName=file.getName();
		          String[] splitFileName=fileName.split("_");
		          if(invoiceNum.equals(splitFileName[1])){
		             flag=true;
		             srcList.add(file.toString());
		          }
		         }
		        }
		       }
		       if(flag==false) {
		            generateInvoiceMainPage(url, invoice, mainPDFFileName);
		          srcList.add(mainPDFFileName);
		       }
		      if (detailed) {
		        // String ediPDFFileName =
		        // pdfRenderer.getUniqueTempPDFFileName("ediInvoice");
		        // if ( generateInvoiceEdiBackupPages(invoice, ediPDFFileName) )
		        // srcList.add(ediPDFFileName);
		        //
		        // String webPDFFileName =
		        // pdfRenderer.getUniqueTempPDFFileName("webInvoice");
		        // if ( generateInvoiceWOBackupPages(invoice, webPDFFileName) )
		        // srcList.add(webPDFFileName);
		      }
		      pdfRenderer.concatPDF(srcList, outStream);

   /* } catch (Exception e) {
      e.printStackTrace();
      throw e;
    }
  }*/
    	  //pdfRenderer.concatPDF(srcList, outStream);
    	  	       
    	  	    } catch (Exception e) {
    	   	      e.printStackTrace();
    	   	      throw e;
    	   	    }
    	}
  
  public void getSalesInvoicePdf(Long id, String url, OutputStream outStream, boolean detailed,String salesUser)
	      throws Exception {
	    try {
	      Invoice invoice = getInvoiceById(id);

	      PDFRenderer pdfRenderer = new PDFRenderer();

	      String mainPDFFileName = pdfRenderer.getUniqueTempPDFFileName("invoice"
	          + invoice.getInvoiceNum());
	      generateSalesInvoiceMainPage(url, invoice, mainPDFFileName,salesUser);
	      ArrayList<String> srcList = new ArrayList<String>();
	      srcList.add(mainPDFFileName);
	      pdfRenderer.concatPDF(srcList, outStream);

	      // delete temp files
	      pdfRenderer.deleteFiles(srcList);

	    } catch (Exception e) {
	      e.printStackTrace();
	      throw e;
	    }
	  }

  @SuppressWarnings("unchecked")
  private boolean generateSalesInvoiceMainPage(String url, Invoice invoice, String fileName, String salesUser)
      throws Exception {
    // String fileName = getUniqueTempPDFFileName("mainInvoice");
  Commission commission = invoiceDAO.getcommissionbyId(invoice.getInvoiceId(), salesUser);
  SubTotal subtotals =  invoiceDAO.getcommissionbyIdd(invoice.getInvoiceId());
  boolean flag = true;  // flag for showing totalSPD,totalLTL,totalCHB
    long start = System.currentTimeMillis();
    if (invoice != null && invoice.getCustomer() != null && invoice.getBusinessId() != null
        && invoice.getOrders() != null) {
      Customer customer = invoice.getCustomer();
      Business business = this.businessDAO.getBusiessById(invoice.getBusinessId());
      if (business != null) {
        try {
          InputStream stream = null;

          if (StringUtil.isEmpty(business.getInvoicingTemplate()))
            stream = this
                .getClass()
                .getClassLoader()
                .getResourceAsStream(
                    "./jasperreports/src/main/java/com/meritconinc/shiplinx/service/impl/jasperreports/InvoiceMain.jasper");
          else
            stream = this
                .getClass()
                .getClassLoader()
                .getResourceAsStream(
                    "./jasperreports/src/main/java/com/meritconinc/shiplinx/service/impl/jasperreports/"
                        + business.getInvoicingTemplate() + ".jasper");

          JasperReport jasperReport = (JasperReport) JRLoader.loadObject(stream);
          log.debug("Package");
          List<ShippingOrder> shippingOrder = invoice.getOrders();
          List<Package> pack = null;
          List<String> statusId = new ArrayList<String>();
          List<String> packageHeight = new ArrayList<String>();
          List<String> packageWidth = new ArrayList<String>();
          List<String> packageLength = new ArrayList<String>();
          List<String> packageDimmedString = new ArrayList<String>();
          List<String> packageWeight = new ArrayList<String>();
          List<String> packageBilledWeight = new ArrayList<String>();
          for (ShippingOrder so : shippingOrder) {
            long orderId = so.getId();
            if (so.getStatusId() != null) {
              statusId.add(so.getStatusId().toString());
            } else {
              statusId.add("0");
            }
            pack = shippingDAO.getShippingPackages(orderId);
            if (pack != null) {
              for (Package p : pack) {
                if (p.getHeight() != null && p.getWeight() != null && p.getLength() != null) {
                  packageHeight.add(p.getHeight().toString());
                  packageWidth.add(p.getWidth().toString());
                  packageLength.add(p.getLength().toString());
                } else {
                  packageHeight.add("");
                  packageWidth.add("");
                  packageLength.add("");
                }
                if (p.getDimmedString() != null) {
                  packageDimmedString.add(p.getDimmedString());
                } else {
                  packageDimmedString.add("");
                }
                if (p.getWeight() != null && p.getBilledWeight() != null) {
                  packageWeight.add(p.getWeight().toString());
                  packageBilledWeight.add(p.getBilledWeight().toString());
                } else {
                  packageWeight.add("");
                  packageBilledWeight.add("");
                }
              }
            }
          }
          
          CurrencySymbol currencySymbol = new CurrencySymbol();
	          if(customer.getDefaultCurrency()!=null && !customer.getDefaultCurrency().isEmpty()){
	              currencySymbol = shippingDAO.getSymbolByCurrencycode(customer.getDefaultCurrency());  
	          }/*else{
	        	  
	        	if(UserUtil.getMmrUser().getLocale() != null){
		    		//if(!UserUtil.getMmrUser().getUserRole().equals(ShiplinxConstants.ROLE_ADMIN)){
		    	 currencySymbol = shippingDAO.getCurrencyCodeByCountryName(UserUtil.getMmrUser().getLocale().substring(3,5));
		    	}else if(shippingOrder.get(0).getChargesForInvoice().get(0).getChargecurrency()>0){
		    		currencySymbol = shippingDAO.getCurrencyCodeById(shippingOrder.get(0).getChargesForInvoice().get(0).getChargecurrency());
		    	}
			      	  if(currencySymbol==null){
			      		  for(int j=0;j<ShiplinxConstants.EURO_UNION_LIST.length;j++){
			      			  if(UserUtil.getMmrUser().getLocale().substring(3,5).equalsIgnoreCase(ShiplinxConstants.EURO_UNION_LIST[j])){
			      				currencySymbol=shippingDAO.getCurrencyCodeByCountryName("EUCG");
			      				break;
			      			  }
			      		  }
			      	  }
		    	 //-------------------------
		    	}
	          }*/
	          else{
	        	currencySymbol=shippingDAO.getSymbolByCurrencycode(invoice.getCurrency()); 
	        	if(currencySymbol==null && currencySymbol.getCurrencySymbol() == null || currencySymbol.getCurrencySymbol().isEmpty()){
	      		  for(int j=0;j<ShiplinxConstants.EURO_UNION_LIST.length;j++){
	      			  if(UserUtil.getMmrUser().getLocale().substring(3,5).equalsIgnoreCase(ShiplinxConstants.EURO_UNION_LIST[j])){
	      				currencySymbol=shippingDAO.getCurrencyCodeByCountryName("EUCG");
	      				break;
	      			  }
	      		  }
	      	  }
	          }
	          
	          
          Map parameters = new HashMap();
          parameters.put("Business", business);
          parameters.put("Invoice", invoice);
          parameters.put("Customer", customer);
          parameters.put("PackageHeight", packageHeight);
          parameters.put("PackageWidth", packageWidth);
          parameters.put("PackageLength", packageLength);
          parameters.put("PackageDimmedString", packageDimmedString);
          parameters.put("Status", statusId);
          parameters.put("PackageWeight", packageWeight);
          parameters.put("PackageBilledWeight", packageBilledWeight);
          parameters.put("SalesUserFlag", flag);
          parameters.put("currencySymbol", currencySymbol.getCurrencySymbol());
/*          parameters.put("totalSPD", String.valueOf(commission.getTotalSPD()));
          parameters.put("totalLTL", String.valueOf(commission.getTotalLTL()));
          parameters.put("totalCHB", String.valueOf(commission.getTotalCHB()));*/
          parameters.put("totalSPD", " "+subtotals.getTotalSPD());
          parameters.put("totalLTL", " "+subtotals.getTotalLTL());
          parameters.put("totalCHB", " "+subtotals.getTotalCHB());
          parameters.put("totalFPA", " "+subtotals.getTotalFPA());
          parameters.put("totalFWD", " "+subtotals.getTotalFWD());
          String logoPath = "/images/" + business.getLogoFileName();
          String logo2Path = "/images/" + business.getLogoHiResFileName();
          URL logo = (InvoiceManagerImpl.class.getResource(logoPath));
          URL logo2 = (InvoiceManagerImpl.class.getResource(logo2Path));
          parameters.put("logo", logo);
          parameters.put("logo2", logo2);

          JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(invoice.getOrders());

          JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, ds);

          JasperExportManager.exportReportToPdfFile(jasperPrint, fileName);
          return true;

        } catch (Exception e) {
          log.error("Could not generate Shiplinx Invoice Main !!");
          e.printStackTrace();
          throw e;
        }
      }
    }
    long end = System.currentTimeMillis();
    log.debug("Time to generate Shipment Invoice Main Page : " + (end - start) + " ms");
    return false;
  }
  
  private boolean generateInvoiceMainPage(String url, Invoice invoice, String fileName)
	      throws Exception {
	    // String fileName = getUniqueTempPDFFileName("mainInvoice");
	  SubTotal subtotals =  invoiceDAO.getcommissionbyIdd(invoice.getInvoiceId());
	    boolean flag=true;
	    long start = System.currentTimeMillis();
	    if (invoice != null && invoice.getCustomer() != null && invoice.getBusinessId() != null
	        && invoice.getOrders() != null) {
	      Customer customer = invoice.getCustomer();
	      Business business = this.businessDAO.getBusiessById(invoice.getBusinessId());
	      if (business != null) {
	        try {
	          InputStream stream = null;

	          if (StringUtil.isEmpty(business.getInvoicingTemplate()))
	            stream = this
	                .getClass()
	                .getClassLoader()
	                .getResourceAsStream(
	                    "./jasperreports/src/main/java/com/meritconinc/shiplinx/service/impl/jasperreports/InvoiceMain.jasper");
	          else
	            stream = this
	                .getClass()
	                .getClassLoader()
	                .getResourceAsStream(
	                    "./jasperreports/src/main/java/com/meritconinc/shiplinx/service/impl/jasperreports/"
	                        + business.getInvoicingTemplate() + ".jasper");

	          JasperReport jasperReport = (JasperReport) JRLoader.loadObject(stream);
	          List<ShippingOrder> shippingOrder = invoice.getOrders();
	          List<Package> pack = null;
	          List<String> statusId = new ArrayList<String>();
	          List<String> packageHeight = new ArrayList<String>();
	          List<String> packageWidth = new ArrayList<String>();
	          List<String> packageLength = new ArrayList<String>();
	          List<String> packageDimmedString = new ArrayList<String>();
	          List<String> packageWeight = new ArrayList<String>();
	          List<String> packageBilledWeight = new ArrayList<String>();
	          /////// ============= Exchange Rate ===============
	          
	          CurrencySymbol currencySymbol = new CurrencySymbol();
	          	          if(customer.getDefaultCurrency()!=null && !customer.getDefaultCurrency().isEmpty()){
	          	              currencySymbol = shippingDAO.getSymbolByCurrencycode(customer.getDefaultCurrency());  
	          	          }/*else{
	          	        	  
	          	        	if(UserUtil.getMmrUser().getLocale() != null){
	  				    		//if(!UserUtil.getMmrUser().getUserRole().equals(ShiplinxConstants.ROLE_ADMIN)){
	  				    	 currencySymbol = shippingDAO.getCurrencyCodeByCountryName(UserUtil.getMmrUser().getLocale().substring(3,5));
	  				    	}else if(shippingOrder.get(0).getChargesForInvoice().get(0).getChargecurrency()>0){
	  				    		currencySymbol = shippingDAO.getCurrencyCodeById(shippingOrder.get(0).getChargesForInvoice().get(0).getChargecurrency());
	  				    	}
	  					      	  if(currencySymbol==null){
	  					      		  for(int j=0;j<ShiplinxConstants.EURO_UNION_LIST.length;j++){
	  					      			  if(UserUtil.getMmrUser().getLocale().substring(3,5).equalsIgnoreCase(ShiplinxConstants.EURO_UNION_LIST[j])){
	  					      				currencySymbol=shippingDAO.getCurrencyCodeByCountryName("EUCG");
	  					      				break;
	  					      			  }
	  					      		  }
	  					      	  }
	  				    	 //-------------------------
	  				    	}
	          	          }*/
	          	          else{
	          	        	currencySymbol=shippingDAO.getSymbolByCurrencycode(invoice.getCurrency()); 
	          	        	if(currencySymbol==null && currencySymbol.getCurrencySymbol() == null || currencySymbol.getCurrencySymbol().isEmpty()){
					      		  for(int j=0;j<ShiplinxConstants.EURO_UNION_LIST.length;j++){
					      			  if(UserUtil.getMmrUser().getLocale().substring(3,5).equalsIgnoreCase(ShiplinxConstants.EURO_UNION_LIST[j])){
					      				currencySymbol=shippingDAO.getCurrencyCodeByCountryName("EUCG");
					      				break;
					      			  }
					      		  }
					      	  }
	          	          }
	          //// ===================== End ================
	          for (ShippingOrder so : shippingOrder) {
	            long orderId = so.getId();
	            if (so.getStatusId() != null) {
	              statusId.add(so.getStatusId().toString());
	            } else {
	              statusId.add("0");
	            }
	            pack = shippingDAO.getShippingPackages(orderId);
	            if (pack != null) {
	              for (Package p : pack) {
	                if (p.getHeight() != null && p.getWeight() != null && p.getLength() != null) {
	                  packageHeight.add(p.getHeight().toString());
	                  packageWidth.add(p.getWidth().toString());
	                  packageLength.add(p.getLength().toString());
	                } else {
	                  packageHeight.add("");
	                  packageWidth.add("");
	                  packageLength.add("");
	                }
	                if (p.getDimmedString() != null) {
	                  packageDimmedString.add(p.getDimmedString());
	                } else {
	                  packageDimmedString.add("");
	                }
	                if (p.getWeight() != null && p.getBilledWeight() != null) {
	                  packageWeight.add(p.getWeight().toString());
	                  packageBilledWeight.add(p.getBilledWeight().toString());
	                } else {
	                  packageWeight.add("");
	                  packageBilledWeight.add("");
	                }
	              }
	            }
	            for(Charge charge : so.getChargesForInvoice()){
  	            	if((charge.getExchangerate()==null || charge.getExchangerate().equals(0.0)) && charge.getExchangerate().equals("")){
  	            	charge.setExchangerate(new BigDecimal(1.00));
  	            }else{
  	            	charge.setCharge(charge.getCharge()/** charge.getExchangerate().doubleValue()*/);
  	            }
  	           }
	          }
	         
	         // invoice.setInvoiceAmount(invoice.getInvoiceAmount()*shippingOrder.get(0).getChargesForInvoice().get(0).getExchangerate().doubleValue());
	          //invoice.setInvoiceTax(invoice.getInvoiceTax()*shippingOrder.get(0).getChargesForInvoice().get(0).getExchangerate().doubleValue());
	          Map<String, Object> parameters = new HashMap<String, Object>();
	          parameters.put("Business", business);
	          parameters.put("Invoice", invoice);
	          parameters.put("Customer", customer);
	          parameters.put("PackageHeight", packageHeight);
	          parameters.put("PackageWidth", packageWidth);
	          parameters.put("PackageLength", packageLength);
	          parameters.put("PackageDimmedString", packageDimmedString);
	          parameters.put("Status", statusId);
	          parameters.put("PackageWeight", packageWeight);
	          parameters.put("PackageBilledWeight", packageBilledWeight);
	          /*parameters.put("SalesUserFlag", false);
	          parameters.put("totalSPD","");
	          parameters.put("totalLTL", "");
	          parameters.put("totalCHB", "");*/
	          parameters.put("SalesUserFlag", new Boolean(true));
          	  parameters.put("totalSPD", " "+subtotals.getTotalSPD());
	          parameters.put("totalLTL", " "+subtotals.getTotalLTL());
	          parameters.put("totalCHB", " "+subtotals.getTotalCHB());
	          parameters.put("totalFPA", " "+subtotals.getTotalFPA());
	          parameters.put("totalFWD", " "+subtotals.getTotalFWD());
	          parameters.put("currencySymbol", currencySymbol.getCurrencySymbol());
	          String logoPath = "/images/" + business.getLogoFileName();
	          String logo2Path = "/images/" + business.getLogoHiResFileName();
	          URL logo = (InvoiceManagerImpl.class.getResource(logoPath));
	          URL logo2 = (InvoiceManagerImpl.class.getResource(logo2Path));
	          parameters.put("logo", logo);
	          parameters.put("logo2", logo2);
	          /*if(customer.getDefaultCurrency()==null || customer.getDefaultCurrency().isEmpty()){
	        	  invoice.setCurrency(currencySymbol.getCurrencyCode());
	          }*/
	          JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(invoice.getOrders());

	          JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, ds);

	          JasperExportManager.exportReportToPdfFile(jasperPrint, fileName);
	          return true;

	        } catch (Exception e) {
	          log.error("Could not generate Shiplinx Invoice Main !!");
	          e.printStackTrace();
	          throw e;
	        }
	      }
	    }
	    long end = System.currentTimeMillis();
	    log.debug("Time to generate Shipment Invoice Main Page : " + (end - start) + " ms");
	    return false;
	  }
  

  // Comm report generation
  // Total charge minus total cost gives the commissionable amount. Taxes not
  // included
  public List<Invoice> generateCommReport(Invoice invoice) {
    if (invoice.getFromInvoiceDate_web() != null && invoice.getFromInvoiceDate_web().length() > 0) {
      Date from = FormattingUtil.getDate(invoice.getFromInvoiceDate_web(),
          FormattingUtil.DATE_FORMAT_WEB);
      invoice.setFromInvoiceDate(from);
    }
    if (invoice.getToInvoiceDate_web() != null && invoice.getToInvoiceDate_web().length() > 0) {
      Date to = FormattingUtil.getDateEndOfDay(invoice.getToInvoiceDate_web(),
          FormattingUtil.DATE_FORMAT_WEB_ENDOFDAY);
      invoice.setToInvoiceDate(to);
    }

    /*
     * invoice.setPaymentStatusList(new int[] { Invoice.INVOICE_STATUS_UNPAID,
     * Invoice.INVOICE_STATUS_PARTIAL_PAID, Invoice.INVOICE_STATUS_PAID });
     */
    invoice.setPaymentStatusList(invoice.getPaymentStatusList());
    if (invoice.getSalesUsername() != null) {
      invoice.setSalesRep(invoice.getSalesUsername());
    }
    List<Invoice> invoices;
    if (invoice.getPaymentStatusList()[0] != 50) {
    	invoice.setRepPaid(invoice.getPaymentStatusList()[0]);
    	invoices = invoiceDAO.searchInvoicesCommission(invoice);
    } else {
    	invoice.setRepPaid(50);
      invoices = invoiceDAO.searchPaidToRepInvoices(invoice);
    }
    // sort the list of invoices by customer
    Collections.sort(invoices, Invoice.CustomerComparator);

    CustomerSalesUser csu = new CustomerSalesUser();
    csu.setSalesAgent(invoice.getSalesUsername());

    for (Invoice i : invoices) {

      csu.setCustomerId(i.getCustomerId());
      csu = customerDAO.getCustomerSalesUser(csu).get(0); // there should
      // be exactly 1
      // record

      Invoice in = this.getInvoiceById(i.getInvoiceId()); // in order to
      // set the
      // charges for
      // the shipments
      // in the
      // invoice, we
      // need to
      // retrieve the invoice again by id
      for (ShippingOrder orderInInvoice : in.getOrders()) {

        List<Charge> chargesForOrder = orderInInvoice.getChargesForInvoice();
        double totalCostForOrder = 0, totalChargeForOrder = 0;
        for (Charge c : chargesForOrder) {

          // ignore taxes when calculating commissionable amount
          CarrierChargeCode ccc = shippingDAO.getChargeByCarrierAndCodes(
              orderInInvoice.getCarrierId(), c.getChargeCode(), c.getChargeCodeLevel2());
          if (ccc == null || ccc.isTax())
            continue;
          if(c!=null && c.getCost()!=null){
          totalCostForOrder = FormattingUtil.add(c.getCost().doubleValue(), totalCostForOrder)
              .doubleValue();
          totalChargeForOrder = FormattingUtil
              .add(c.getCharge().doubleValue(), totalChargeForOrder).doubleValue();
          }
        }
        double commissionPerc = 0; // to be determined based on the
        // service type
        if (orderInInvoice.getService().getEmailType().equalsIgnoreCase(ShiplinxConstants.LTL_EMAIL_TYPE))
            commissionPerc = csu.getCommissionPercentagePerPalletService();
          else if (orderInInvoice.getService().getEmailType().equalsIgnoreCase(ShiplinxConstants.SPS_EMAIL_TYPE))
            commissionPerc = csu.getCommissionPercentagePerSkidService();
          else if(orderInInvoice.getService().getEmailType().equalsIgnoreCase(ShiplinxConstants.CHB_EMAIL_TYPE))
           commissionPerc=csu.getCommisionPercentagePerCHB();// default
        // to small
        // package
        // commission
        // percentage

        double commissionAmount = (totalChargeForOrder - totalCostForOrder) * commissionPerc / 100;
        i.setCommissionAmount(FormattingUtil.add(commissionAmount, i.getCommissionAmount())
            .doubleValue());
      }
      i.setPaymentStatusString(invoiceDAO.getinvoicestatusbyId(invoice.getRepPaid()));
    }

    return invoices;

  }

  // Sales report generation
  // taxes not included
  public List<SalesRecord> generateSalesReport(SalesRecord sales) {

    sales.setPaymentStatusList(new int[] { Invoice.INVOICE_STATUS_UNPAID,
        Invoice.INVOICE_STATUS_PARTIAL_PAID, Invoice.INVOICE_STATUS_PAID });
    List<SalesRecord> salesRecords = invoiceDAO.getSalesReport(sales);
    return salesRecords;

  }

  @Override
  public Invoice updateInvoice(Invoice invoice, String[] ids, String[] userCharges,
      String[] userCosts, String[] userNames, String[] trackNos) throws Exception {
    // TODO Auto-generated method stub
    if (invoice != null && ids != null && userCharges != null && userCosts != null
        && userNames != null && trackNos != null) {
      for (int i = 0; i < ids.length; i++) {
        Long id = Long.parseLong(ids[i]);
        ShippingOrder order = getOrder(invoice, trackNos[i]);
        if (order != null) {
          Charge soCharge = getCharge(order, id,invoice);
          if (soCharge != null) {
            Double ch = StringUtil.getDouble(userCharges[i]); // Double.parseDouble(userCharges[i]);
            Double cost = StringUtil.getDouble(userCosts[i]); // Double.parseDouble(userCosts[i]);
            String name = userNames[i];
            String edi = userNames[i];
            if (ch.doubleValue() == 0 && cost.doubleValue() == 0) {
              this.shippingDAO.deleteCharge(soCharge.getId());
              this.invoiceDAO.deleteShipmentAndChargeFromInvoice(
                  invoice.getInvoiceId().longValue(), order.getId().longValue(), soCharge.getId()
                      .longValue());
            }else if (isChargeDirty(soCharge, ch, cost, name, edi)) {
              this.shippingDAO.updateCharge(soCharge);
            }
          }
        }
      }

      long invId = invoice.getInvoiceId().longValue();
      invoice = getInvoiceById(invId);
      calculateInvoiceCharges(invoice);
      invoice = getInvoiceById(invId);
    }

    return invoice;
  }

  private ShippingOrder getOrder(Invoice invoice, String trackNo) {
    // TODO Auto-generated method stub
    if (invoice != null)
      for (ShippingOrder o : invoice.getOrders())
        if (o.getMasterTrackingNum().equals(trackNo))
          return o;

    return null;
  }

  private Charge getCharge(ShippingOrder so, Long id, Invoice invoice) {
    // TODO Auto-generated method stub
	  int count=0;
	  if(invoice.getCalculateTaxId()!=null && invoice.getCalculateTaxId().size()>0){
		  for(int i=0; i< invoice.getCalculateTaxId().size(); i++){
			  count++;
			  for (Charge c : so.getChargesForInvoice()) {
				  
				  if(Long.valueOf(invoice.getCalculateTaxId().get(i)).equals(c.getId())){
					  c.setCalculateTax(1);
				  }
				  if (!c.getIsTax() && c.getId().longValue() == id.longValue())
					  return c;
				  if(c.getIsTax() && count==invoice.getCalculateTaxId().size() && c.getId().longValue() == id.longValue()){
					  return c;
				  }
				  if(c.getCalculateTax()==1 && c.getId().longValue() == id.longValue()){
					  return c;
				  }
			  }
		  }
	  }else{
		  for (Charge c : so.getCharges()) {
		      if (c.getId().longValue() == id.longValue())
		        return c;
		    }
	  }
    return null;
  }

  private boolean isChargeDirty(Charge soCharge, Double charge, Double cost, String name, String edi) {
    // TODO Auto-generated method stub
    boolean isDirty = false;
    if(soCharge.getCalculateTax()==1){
    	isDirty = true;
    	soCharge.setName(name);
    	soCharge.setCharge(charge);
    	soCharge.setCost(cost);
    	soCharge.setCalculateTax(1);
    }else{
    if (!soCharge.getName().equalsIgnoreCase(name)) {
      isDirty = true;
      soCharge.setName(name);
    }
    if ((soCharge.getCharge() == null && charge != null)
        || (charge != null && soCharge.getCharge().doubleValue() != charge.doubleValue())) {
      isDirty = true;
      soCharge.setCharge(charge);
    }
    if ((soCharge.getCost() == null && cost != null)
        || (cost != null && soCharge.getCost().doubleValue() != cost.doubleValue())) {
      isDirty = true;
      soCharge.setCost(cost);
    }
    }
    return isDirty;
  }

  public boolean sendInvoiceEmailNotification(User user, List<Invoice> invoices) {
	    boolean boolret = true;
	    List<String> bccAddresses = new ArrayList<String>();
	    String toAddress = "";
	    int i, j;
	    // issue no:237
	    List<String> invoiceMail = new ArrayList<String>();
	    List<String> email = new ArrayList<String>();
	    String locale = user.getLocale();
	    String subject = MessageUtil.getMessage("message.send.invoice.notification.subject", locale);
	    String body = MessageUtil.getMessage(user.getBusiness().getInvoiceNotificationBody(), locale);
	    boolean flag = true;
	    boolean flagInvoiceMail;
	    boolean flageMail;
	    for (Invoice invoice : invoices) {
	      flagInvoiceMail = false;
	      flageMail = false;

	      if (invoice.getCustomer().getInvoicingEmail() != null
	    		  && !invoice.getCustomer().getInvoicingEmail().equals("")
	          && !invoice.getCustomer().getInvoicingEmail().isEmpty()) {

	        for (i = 0; i < invoiceMail.size(); i++) {
	          if (invoiceMail.get(i).equals(invoice.getCustomer().getInvoicingEmail())) {
	            flagInvoiceMail = true;
	          }
	        }
	      } else {
	        for (i = 0; i < email.size(); i++) {
	          if (email.get(i).equals(invoice.getCustomer().getAddress().getEmailAddress())) {
	            flageMail = true;
	          }
	        }
	      }
	      if (invoice.getCustomer().getInvoicingEmail() != null
	    		  && !invoice.getCustomer().getInvoicingEmail().equals("")
	          && !invoice.getCustomer().getInvoicingEmail().isEmpty()) {
	        if (flag == false && flagInvoiceMail == false) {
	          invoiceMail.add(invoice.getCustomer().getInvoicingEmail());
	        }
	      } else {
	        if (flag == false && flageMail == false) {
	          email.add(invoice.getCustomer().getAddress().getEmailAddress());
	        }
	      }
	      if (flag == true) {
	        if (invoice.getCustomer().getInvoicingEmail() != null
	        		&& !invoice.getCustomer().getInvoicingEmail().equals("")
	            && !invoice.getCustomer().getInvoicingEmail().isEmpty()) {
	          invoiceMail.add(invoice.getCustomer().getInvoicingEmail());
	        } else {
	          email.add(invoice.getCustomer().getAddress().getEmailAddress());
	        }
	        flag = false;
	      }
	    }
	    if (invoiceMail.size() > 0) {
	      for (String mail : invoiceMail) {
	        List<Invoice> invo = new ArrayList<Invoice>();
	        List<Invoice> mailedInvoices = new ArrayList<Invoice>();
	        List<Attachment> lstFileAttachment = new ArrayList<Attachment>();
	        for (Invoice inv : invoices) {
	          if (mail.equals(inv.getCustomer().getInvoicingEmail())) {
	            invo.add(inv);
	          }
	        }
	        try {
	          // String strEmailto = null;
	          // strEmailto =
	          // invoice.getCustomer().getAddress().getEmailAddress().toString().trim();
	          for (i = 0; i < invo.size(); i++) {
	            mailedInvoices.add(this.getInvoiceById(invo.get(i).getInvoiceId()));
	            PDFRenderer pdfRenderer = new PDFRenderer();
	            String mainPDFFileName = pdfRenderer.getUniqueTempPDFFileName("invoice"
	                + mailedInvoices.get(i).getInvoiceNum());
	            File fInvoicePDF = new File(mainPDFFileName);
	            fInvoicePDF.deleteOnExit();
	            BufferedOutputStream invBOS = new BufferedOutputStream(
	                new FileOutputStream(fInvoicePDF));

	            Long l = mailedInvoices.get(i).getInvoiceId();
	            getInvoicePdf(l, null, invBOS, false);
	            invBOS.close();

	            Attachment attachment = new Attachment();
	            attachment.setFile(fInvoicePDF);
	            attachment.setContentType("pdf");
	            lstFileAttachment.add(attachment);
	            // GROUP_EMAIL_ADDRESS_en_CA
	          }
	          subject = new String(subject.replaceAll("%BUSINESSNAME", user.getBusiness().getName()));

	          if (body == null || body.length() == 0) {
	            log.error("Cannot find template to send invoice notification for business "
	                + user.getBusiness().getName());
	            return false;
	          }

	          body = new String(body.replaceAll("%BUSINESSNAME", user.getBusiness().getName()));
	          body = new String(body.replaceAll("%CONTACT", user.getBusiness().getAddress()
	              .getContactName()));
	          body = new String(body.replaceAll("%PHONENO", user.getBusiness().getAddress()
	              .getPhoneNo()));
	          body = new String(body.replaceAll("%EMAIL", user.getBusiness().getAddress()
	              .getEmailAddress()));

	          // bccAddresses.add(user.getBusiness().getAddress().getEmailAddress());
	          String emailAddress = user.getBusiness().getAddress().getEmailAddress();
	          if (emailAddress != null) {
	            String emails[] = emailAddress.split(";");
	            for (String email1 : emails) {
	              bccAddresses.add(email1);
	            }
	          }
	          /*
	           * If the Invoicing Email is null, then the mail is sent to the customer email address
	           */

	          MailHelper.sendEmailNow2(user.getBusiness().getSmtpHost(), user.getBusiness()
	              .getSmtpUsername(), user.getBusiness().getSmtpPassword(), user.getBusiness()
	              .getName(), user.getBusiness().getSmtpPort(), user.getBusiness().getFinanceEmail(),
	              mail, bccAddresses, subject, body, lstFileAttachment, true);

	        } catch (MessagingException e) {
	          log.error("Error sending email - Messaging Exception: ", e);
	          boolret = false;
	        } catch (IOException ioe) {
	          log.error("Error sending email - IOException : ", ioe);
	          boolret = false;
	        } catch (Exception e) {
	          log.error("Error sending email - Exception: " + e.getMessage());
	          boolret = false;
	        }
	      }
	    }
	    if (email.size() > 0) {
	      for (String Email : email) {
	        List<Invoice> invo = new ArrayList<Invoice>();
	        List<Invoice> mailedInvoices = new ArrayList<Invoice>();
	        List<Attachment> lstFileAttachment = new ArrayList<Attachment>();
	        for (Invoice inv : invoices) {
	          if (Email.equals(inv.getCustomer().getAddress().getEmailAddress())) {
	            invo.add(inv);
	          }
	        }
	        try {
	          for (i = 0; i < invo.size(); i++) {
	            mailedInvoices.add(this.getInvoiceById(invo.get(i).getInvoiceId()));
	            PDFRenderer pdfRenderer = new PDFRenderer();
	            String mainPDFFileName = pdfRenderer.getUniqueTempPDFFileName("invoice"
	                + mailedInvoices.get(i).getInvoiceNum());
	            File fInvoicePDF = new File(mainPDFFileName);
	            fInvoicePDF.deleteOnExit();
	            BufferedOutputStream invBOS = new BufferedOutputStream(
	                new FileOutputStream(fInvoicePDF));

	            Long l = mailedInvoices.get(i).getInvoiceId();
	            getInvoicePdf(l, null, invBOS, false);
	            invBOS.close();

	            Attachment attachment = new Attachment();
	            attachment.setFile(fInvoicePDF);
	            attachment.setContentType("pdf");
	            lstFileAttachment.add(attachment);

	          }
	          subject = new String(subject.replaceAll("%BUSINESSNAME", user.getBusiness().getName()));

	          if (body == null || body.length() == 0) {
	            log.error("Cannot find template to send invoice notification for business "
	                + user.getBusiness().getName());
	            return false;
	          }

	          body = new String(body.replaceAll("%BUSINESSNAME", user.getBusiness().getName()));
	          body = new String(body.replaceAll("%CONTACT", user.getBusiness().getAddress()
	              .getContactName()));
	          body = new String(body.replaceAll("%PHONENO", user.getBusiness().getAddress()
	              .getPhoneNo()));
	          body = new String(body.replaceAll("%EMAIL", user.getBusiness().getAddress()
	              .getEmailAddress()));

	          // bccAddresses.add(user.getBusiness().getAddress().getEmailAddress());
	          String emailAddress = user.getBusiness().getAddress().getEmailAddress();
	          if (emailAddress != null) {
	            String emails[] = emailAddress.split(";");
	            for (String email1 : emails) {
	              bccAddresses.add(email1);
	            }
	          }
	          /*
	           * If the Invoicing Email is null, then the mail is sent to the customer email address
	           */

	          MailHelper.sendEmailNow2(user.getBusiness().getSmtpHost(), user.getBusiness()
	              .getSmtpUsername(), user.getBusiness().getSmtpPassword(), user.getBusiness()
	              .getName(), user.getBusiness().getSmtpPort(), user.getBusiness().getFinanceEmail(),
	              Email, bccAddresses, subject, body, lstFileAttachment, true);

	        } catch (MessagingException e) {
	          log.error("Error sending email - Messaging Exception: ", e);
	          boolret = false;
	        } catch (IOException ioe) {
	          log.error("Error sending email - IOException : ", ioe);
	          boolret = false;
	        } catch (Exception e) {
	          log.error("Error sending email - Exception: " + e.getMessage());
	          boolret = false;
	        }
	      }

	    }

	    return boolret;
	  }

  public boolean downloadInvoiceCSV(long invoiceId, OutputStream ostream) {
    Invoice invoice = getInvoiceById(invoiceId);
    Business business = getBusinessDAO().getBusiessById(invoice.getBusinessId());
    Customer customer = invoice.getCustomer();
    int j = 0;

    for (ShippingOrder so : invoice.getOrders()) {
      int i = 0;
      int chargecnt = 0;
      String[] strheader = new String[100];
      String[] strvalues = new String[100];

      StringBuffer stbheader = new StringBuffer();
      StringBuffer stbheadervalues = new StringBuffer();

      strheader[i] = "Invoice Number,";
      if (invoice.getInvoiceNum() != null)
        strvalues[i] = invoice.getInvoiceNum() + ",";
      else
        strvalues[i] = ",";
      i++;

      strheader[i] = "Invoice Date,";
      if (invoice.getInvoiceDate() != null)
        strvalues[i] = invoice.getInvoiceDate() + ",";
      else
        strvalues[i] = ",";
      i++;

      strheader[i] = "Invoice due date,";
      if (invoice.getInvoiceDueDate() != null)
        strvalues[i] = invoice.getInvoiceDueDate() + ",";
      else
        strvalues[i] = ",";
      i++;

      strheader[i] = "Order No,";
      if (so.getOrderNum() != null)
        strvalues[i] = so.getOrderNum() + ",";
      else
        strvalues[i] = ",";
      i++;

      strheader[i] = "Carrier,";
      if (so.getCarrierName() != null)
        strvalues[i] = so.getCarrierName() + ",";
      else
        strvalues[i] = ",";
      i++;

      strheader[i] = "Inv Total,";
      strvalues[i] = invoice.getTotalInvoiceCharge() + ",";
      i++;

      strheader[i] = "Trans Cnt,";
      if (invoice.getOrders() != null)
        strvalues[i] = invoice.getOrders().size() + ",";
      else
        strvalues[i] = ",";
      i++;

      strheader[i] = "Bill-to Account,";
      if (invoice.getCustomer() != null) {
        if (invoice.getCustomer().getAccountNumber() != null)
          strvalues[i] = invoice.getCustomer().getAccountNumber() + ","; // Bill to Account
        else
          strvalues[i] = ",";
      } else
        strvalues[i] = ",";
      i++;

      strheader[i] = "Tracking Number,";
      if (so.getMasterTrackingNum() != null)
        strvalues[i] = so.getMasterTrackingNum() + ",";
      else
        strvalues[i] = ",";
      i++;

      strheader[i] = "Ship Date,";
      if (so.getScheduledShipDate() != null)
        strvalues[i] = so.getScheduledShipDate() + ",";
      else
        strvalues[i] = ",";
      i++;

      strheader[i] = "Service Name,";
      if (so.getService() != null)
        strvalues[i] = so.getService().getName() + ",";
      else
        strvalues[i] = ",";
      i++;

      strheader[i] = "Service Code,";
      if (so.getServiceId() != null)
        strvalues[i] = so.getServiceId() + ",";
      else
        strvalues[i] = ",";
      i++;

      strheader[i] = "Package Type,";
      if (so.getPackageTypeId() != null) {
        if (so.getPackageTypeId().getType() != null)
          strvalues[i] = so.getPackageTypeId().getType() + ",";
        else
          strvalues[i] = ",";
      } else
        strvalues[i] = ",";
      i++;

      strheader[i] = "Package Code,";
      if (so.getPackageTypeId() != null) {
        if (so.getPackageTypeId().getPackageTypeId() != null)
          strvalues[i] = so.getPackageTypeId().getPackageTypeId() + ",";
        else
          strvalues[i] = ",";
      } else
        strvalues[i] = ",";
      i++;

      strheader[i] = "Ref 1,";
      if (so.getReferenceOne() != null)
        strvalues[i] = so.getReferenceOne() + ",";
      else
        strvalues[i] = ",";
      i++;

      strheader[i] = "Ref 2,";
      if (so.getReferenceTwo() != null)
        strvalues[i] = so.getReferenceTwo() + ",";
      else
        strvalues[i] = ",";
      i++;
      /*
       * strheader[i]="Ref 3,"; strvalues[i]=so.getPack().getReference3()+","; i++;
       */
      double netCharge = 0;
      for (Charge c : so.getChargesForInvoice()) // This would repeat for
      // the no of Charges
      // present with an
      // increment by 1 in
      // chargecnt
      {
        netCharge = (FormattingUtil.add(netCharge, c.getCharge().doubleValue())).doubleValue();
      }
      strheader[i] = "Net Chrg,";
      strvalues[i] = netCharge + " ,";
      i++;

      strheader[i] = "Curr,";
      if (invoice.getCurrency() != null)
        strvalues[i] = invoice.getCurrency() + ",";
      else
        strvalues[i] = ",";
      i++;

      for (Charge c : so.getChargesForInvoice()) // This would repeat for
      // the no of Charges
      // present with an
      // increment by 1 in
      // chargecnt
      {
        chargecnt++;
        strheader[i] = "Charge " + chargecnt + " Code,";
        if (c.getChargeCode() != null)
          strvalues[i] = c.getChargeCode() + ",";
        else
          strvalues[i] = ",";
        i++;

        strheader[i] = "Charge " + chargecnt + " Name,";
        if (c.getName() != null)
          strvalues[i] = c.getName() + ",";
        else
          strvalues[i] = ",";
        i++;

        strheader[i] = "Charge " + chargecnt + " Amount,";
        if (c.getCharge() != null)
          strvalues[i] = c.getCharge() + ",";
        else
          strvalues[i] = ",";
        i++;
      }
      chargecnt++;
      for (int a = chargecnt; a <= 10; a++) // Limit to 10 Charge Code /
      // Name / Amount values.
      {
        strheader[i] = "Charge " + a + " Amount,";
        strvalues[i] = ",";
        i++;

        strheader[i] = "Charge " + a + " Name,";
        strvalues[i] = ",";
        i++;

        strheader[i] = "Charge " + a + " Amount,";
        strvalues[i] = ",";
        i++;

      }

      strheader[i] = "Pcs,";
      if (so.getQuantity() != null)
        strvalues[i] = so.getQuantity() + ",";
      else
        strvalues[i] = ",";
      i++;

      strheader[i] = "Bill Weight,";
      if (so.getBilledWeight() != null)
        strvalues[i] = so.getBilledWeight() + ",";
      else
        strvalues[i] = ",";
      i++;

      strheader[i] = "Bill Weight Unit,";
      if (so.getBilledWeightUOM() != null)
        strvalues[i] = so.getBilledWeightUOM() + ",";
      else
        strvalues[i] = ",";
      i++;

      strheader[i] = "Quoted Weight,";
      if (so.getQuotedWeight() != null)
        strvalues[i] = so.getQuotedWeight() + ",";
      else
        strvalues[i] = ",";
      i++;

      strheader[i] = "Quoted Weight UOM,";
      if (so.getQuotedWeightUOM() != null)
        strvalues[i] = so.getQuotedWeightUOM() + ",";
      else
        strvalues[i] = ",";
      i++;

      // From Address Information
      strheader[i] = "Shipper Name,";
      if (so.getFromAddress() != null) {
        if (so.getFromAddress().getContactName() != null)
          strvalues[i] = so.getFromAddress().getContactName() + ",";
        else
          strvalues[i] = ",";
      } else
        strvalues[i] = ",";
      i++;

      strheader[i] = "Shipper Company,";
      if (so.getFromAddress() != null) {
        if (so.getFromAddress().getAbbreviationName() != null)
          strvalues[i] = so.getFromAddress().getAbbreviationName() + ",";
        else
          strvalues[i] = ",";
      } else
        strvalues[i] = ",";
      i++;

      strheader[i] = "Shipper Address 1,";
      if (so.getFromAddress() != null) {
        if (so.getFromAddress().getAddress1() != null
            && so.getFromAddress().getAddress1().contains(","))
          strvalues[i] = StringUtil.replace(so.getFromAddress().getAddress1(), ",", " ", true)
              + ",";
        else if (so.getFromAddress().getAddress1() == null)
          strvalues[i] = ",";
        else
          strvalues[i] = so.getFromAddress().getAddress1() + ",";
      } else
        strvalues[i] = ",";
      i++;

      strheader[i] = "Shipper Address 2,";
      if (so.getFromAddress() != null) {
        if (so.getFromAddress().getAddress2() != null
            && so.getFromAddress().getAddress2().contains(","))
          strvalues[i] = StringUtil.replace(so.getFromAddress().getAddress2(), ",", " ", true)
              + ",";
        else if (so.getFromAddress().getAddress2() == null)
          strvalues[i] = ",";
        else
          strvalues[i] = so.getFromAddress().getAddress2() + ",";
      } else
        strvalues[i] = ",";
      i++;

      strheader[i] = "Shipper City,";
      if (so.getFromAddress() != null) {
        if (so.getFromAddress().getCity() != null)
          strvalues[i] = so.getFromAddress().getCity() + ",";
        else
          strvalues[i] = ",";
      } else
        strvalues[i] = ",";
      i++;

      strheader[i] = "ST,";
      if (so.getFromAddress() != null) {
        if (so.getFromAddress().getProvinceCode() != null)
          strvalues[i] = so.getFromAddress().getProvinceCode() + ",";

        else
          strvalues[i] = ",";
      } else
        strvalues[i] = ",";
      i++;

      strheader[i] = "Postal,";
      if (so.getFromAddress() != null) {
        if (so.getFromAddress().getPostalCode() != null)
          strvalues[i] = so.getFromAddress().getPostalCode() + ",";
        else
          strvalues[i] = ",";
      } else
        strvalues[i] = ",";
      i++;

      strheader[i] = "Cntry1,";
      if (so.getFromAddress() != null) {
        if (so.getFromAddress().getCountryName() != null)
          strvalues[i] = so.getFromAddress().getCountryName() + ",";
        else
          strvalues[i] = ",";
      } else
        strvalues[i] = ",";
      i++;

      // To Address Information
      strheader[i] = "Recipient Name,";
      if (so.getToAddress() != null) {
        if (so.getToAddress().getContactName() != null)
          strvalues[i] = so.getToAddress().getContactName() + ",";
        else
          strvalues[i] = ",";
      } else
        strvalues[i] = ",";
      i++;

      strheader[i] = "Recipient Company,";
      if (so.getToAddress() != null) {
        if (so.getToAddress().getAbbreviationName() != null)
          strvalues[i] = so.getToAddress().getAbbreviationName() + ",";
        else
          strvalues[i] = ",";
      } else
        strvalues[i] = ",";
      i++;

      strheader[i] = "Recipient Address 1,";
      if (so.getToAddress() != null) {
        if (so.getToAddress().getAddress1() != null
            && so.getToAddress().getAddress1().contains(","))
          strvalues[i] = StringUtil.replace(so.getToAddress().getAddress1(), ",", " ", true);
        else if (so.getToAddress().getAddress1() == null)
          strvalues[i] = ",";
        else
          strvalues[i] = so.getToAddress().getAddress1() + ",";
      } else
        strvalues[i] = ",";
      i++;

      strheader[i] = "Recipient Address 2,";
      if (so.getToAddress() != null) {
        if (so.getToAddress().getAddress2() != null
            && so.getToAddress().getAddress2().contains(","))
          strvalues[i] = StringUtil.replace(so.getToAddress().getAddress2(), ",", " ", true);
        else if (so.getToAddress().getAddress2() == null)
          strvalues[i] = ",";
        else
          strvalues[i] = so.getToAddress().getAddress2() + ",";
      } else
        strvalues[i] = ",";
      i++;

      strheader[i] = "Recipient City,";
      if (so.getToAddress() != null) {
        if (so.getToAddress().getCity() != null)
          strvalues[i] = so.getToAddress().getCity() + ",";
        else
          strvalues[i] = ",";
      } else
        strvalues[i] = ",";
      i++;

      strheader[i] = "ST2,";
      if (so.getToAddress() != null) {
        if (so.getToAddress().getProvinceCode() != null)
          strvalues[i] = so.getToAddress().getProvinceCode() + ",";
        else
          strvalues[i] = ",";
      } else
        strvalues[i] = ",";
      i++;

      strheader[i] = "Postal2,";
      if (so.getToAddress() != null) {
        if (so.getToAddress().getPostalCode() != null)
          strvalues[i] = so.getToAddress().getPostalCode() + ",";
        else
          strvalues[i] = ",";
      } else
        strvalues[i] = ",";
      i++;

      strheader[i] = "Cntry2,";
      if (so.getToAddress() != null) {
        if (so.getToAddress().getCountryName() != null)
          strvalues[i] = so.getToAddress().getCountryName() + ",";
        else
          strvalues[i] = ",";
      } else
        strvalues[i] = ",";
      i++;
      /*
       * strheader[i]="Dlvry Date,"; if(so.getDateOfDelivery()!=null)
       * strvalues[i]=so.getDateOfDelivery()+","; else strvalues[i]=","; i++;
       * 
       * strheader[i]="Time,"; if(so.getScheduledAt()!=null) strvalues[i]=so.getScheduledAt()+",";
       * else strvalues[i]=","; i++;
       * 
       * strheader[i]="Signature,"; if(so.getSignatureRequired()!=null)
       * strvalues[i]=so.getSignatureRequired()+","; else strvalues[i]=","; i++;
       */
      strheader[i] = "\n";
      strvalues[i] = "\n";
      i++;
      j++;

      // Copy data from String arrays to String Buffer only once
      if (j == 1) {
        for (int h = 0; h < i; h++) {
          stbheader.append(strheader[h]);
        }
      }// end of condition check for j == 1
      for (int hv = 0; hv < i; hv++) {
        stbheadervalues.append(strvalues[hv]);
      }
      try {
        if (j == 1)
          ostream.write(stbheader.toString().getBytes()); // Write the
        // Header
        // columns
        // only
        // once.
        ostream.write(stbheadervalues.toString().getBytes());
        log.debug("Wrote Line for Order Id: " + so.getId());

      } catch (Exception e) {
        log.error("Could not write header for the Invoice :" + invoiceId);
        continue;
      }
    }
    try {
      ostream.flush();
    } catch (Exception e) {
      log.error("OutputStream Exception :" + e);
      e.printStackTrace();
    }
    return true;
  }

  public void updateInvoiceStatus(List<Invoice> invoicesToUpdate) {
    for (Invoice invoice : invoicesToUpdate) {
      invoice.setPaymentStatus(invoice.INVOICE_STATUS_PAID_TO_REP);
      invoiceDAO.updateInvoiceStatus(invoice);
    }

  }
  public ARTransaction getinvoicebyinvoiceid(long invoiceid) {
	  // TODO Auto-generated method stub
	  return invoiceDAO.getinvoicebyinvoiceid(invoiceid);
	   
	 }
  public void updateInvoiceStatusCommission(List<Invoice> invoicesToUpdate) {
	  	  	    for (Invoice invoice : invoicesToUpdate) {
	  	  	      invoice.setPaymentStatus(invoice.INVOICE_STATUS_PAID_TO_REP);
	  	  	      invoiceDAO.updateInvoiceStatusCommission(invoice);
	  	  	    }
	    
	  	  	  }
	  	    
	  	    public void addARTransactionCommission(ARTransaction transaction) {
	  	  	    Invoice invoice = invoiceDAO.getInvoiceById(transaction.getInvoiceId());
	  	  	    transaction.setBusinessId(invoice.getBusinessId());
	  	  	    transaction.setCustomerId(invoice.getCustomerId());
	  	  	    if(transaction.getAmount()!=null){
	  	  	    invoice.setPaidAmount(FormattingUtil.add(invoice.getPaidAmount().doubleValue(),
	  	  	        transaction.getAmount().doubleValue()).doubleValue());
	  	  	    }
	  	  	    if (invoice.getBalanceDue() == 0)
	  	  	      invoice.setPaymentStatus(Invoice.INVOICE_STATUS_PAID);
	  	  	    else if (invoice.getBalanceDue() > 0
	  	  	        && invoice.getBalanceDue() < invoice.getTotalInvoiceCharge()) {
	  	  	      log.info("Invoice id: " + invoice.getInvoiceId() + " . Total Charge / Balance Due : "
	  	  	          + invoice.getTotalInvoiceCharge() + " / " + invoice.getBalanceDue());
	  	  	      invoice.setPaymentStatus(Invoice.INVOICE_STATUS_PARTIAL_PAID);
	  	  	    }
	  	  
	  	  	    invoiceDAO.updateInvoiceCommission(invoice);
	  	  
	  	  
	  	  	  }
	  	   
	  	    public List<Invoice> searchInvoicesAr(Invoice invoice) {
	  	  
	  	  	    if (invoice.getFromInvoiceDate_web() != null && invoice.getFromInvoiceDate_web().length() > 0) {
	  	  	      Date from = FormattingUtil.getDate(invoice.getFromInvoiceDate_web(),
	  	  	          FormattingUtil.DATE_FORMAT_WEB);
	  	  	      invoice.setFromInvoiceDate(from);
	  	      }
	  	  	    if (invoice.getToInvoiceDate_web() != null && invoice.getToInvoiceDate_web().length() > 0) {
	  	  	      Date to = FormattingUtil.getDateEndOfDay(invoice.getToInvoiceDate_web(),
	  	  	          FormattingUtil.DATE_FORMAT_WEB_ENDOFDAY);
	  	  	      invoice.setToInvoiceDate(to);
	  	  	    }
	  	  
	  	  	    boolean statusSelected = false;
	  	  	    for (int i : invoice.getPaymentStatusList()) {
	  	  	      if (i > 0)
	  	  	        statusSelected = true;
	  	  	      break;
	  	  	    }
	  	  	    if (!statusSelected)
	  	  	      invoice.setPaymentStatusList(new int[0]);
	  	  
	  	  	    return invoiceDAO.searchInvoicesAr(invoice);
	  	  	  }
	  	  	    public List<Invoice> searchInvoicesArSearch(Invoice invoice) {
	  	  	  	  
	  	  	  	    if (invoice.getFromInvoiceDate_web() != null && invoice.getFromInvoiceDate_web().length() > 0) {
	  	  	  	      Date from = FormattingUtil.getDate(invoice.getFromInvoiceDate_web(),
	  	  	  	          FormattingUtil.DATE_FORMAT_WEB);
	  	  	  	      invoice.setFromInvoiceDate(from);
	  	  	  	    }
	  	  	  	    if (invoice.getToInvoiceDate_web() != null && invoice.getToInvoiceDate_web().length() > 0) {
	  	  	  	      Date to = FormattingUtil.getDateEndOfDay(invoice.getToInvoiceDate_web(),
	  	  	  	          FormattingUtil.DATE_FORMAT_WEB_ENDOFDAY);
	  	  	  	      invoice.setToInvoiceDate(to);
	  	  	  	    }
	  	  	  
	  	  	  	    boolean statusSelected = false;
	  	  	  	    for (int i : invoice.getPaymentStatusList()) {
	  	  	  	      if (i > 0)
	  	  	  	        statusSelected = true;
	  	  	  	      break;
	  	  	  	    }
	  	  	  	    if (!statusSelected)
	  	  	  	      invoice.setPaymentStatusList(new int[0]);
	  	  	  	    invoice.setRepPaidList(invoice.getPaymentStatusList());
	  	  	  	    return invoiceDAO.searchInvoicesArSearch(invoice);
	  	  	  	  }
	  	  	    
	  	  	    public List<Invoice> searchInvoicesArSearch1(Invoice invoice) {
	  	  
	  	  	        if (invoice.getFromInvoiceDate_web() != null && invoice.getFromInvoiceDate_web().length() > 0) {
	  	  	          Date from = FormattingUtil.getDate(invoice.getFromInvoiceDate_web(),
	  	  	              FormattingUtil.DATE_FORMAT_WEB);
	  	  	          invoice.setFromInvoiceDate(from);
	  	  	        }
	  	  	        if (invoice.getToInvoiceDate_web() != null && invoice.getToInvoiceDate_web().length() > 0) {
	  	  	          Date to = FormattingUtil.getDateEndOfDay(invoice.getToInvoiceDate_web(),
	  	  	              FormattingUtil.DATE_FORMAT_WEB_ENDOFDAY);
	  	  	          invoice.setToInvoiceDate(to);
	  	          }
	  	  
	  	          boolean statusSelected = false;
	  	  	        for (int i : invoice.getPaymentStatusList()) {
	  	  	          if (i > 0)
	  	  	            statusSelected = true;
	  	  	          break;
	  	  	        }
	  	  	        if (!statusSelected)
	  	  	          invoice.setPaymentStatusList(new int[0]);
	  	  
	  	  	        return invoiceDAO.searchInvoices(invoice);
	  	  	      }
	  	  	  public List<Commission> searchCommissions(Commission commission){
	    		  return invoiceDAO.searchCommissions(commission);
	    	  }
	  	  	public void deleteCommission(long invoiceId){
	  	  		  	  		invoiceDAO.deleteCommission(invoiceId);
	  	  	  	  	}
	  	  	
	  	  public List<Invoice> searchInvoicesBreakdown(Commission commission){
	  			  	  		return invoiceDAO.searchInvoicesBreakdown(commission);
	  			  	  	}
	  	  public List<Invoice> searchInvoicesBreakdownIncludeCanceledInvoice(Commission commission){
	  	  		return invoiceDAO.searchInvoicesBreakdownIncludeCanceledInvoice(commission);
	  	  	}
	  	public List<Invoice> getInvoiceChargeDetails(Long invoiceId){
	  			  		return invoiceDAO.getInvoiceChargeDetails(invoiceId);
	  			  	}
	  	
	  	@Override
	  	public List<Invoice> getInvoiceByEmailType(Long invoiceId){
		  		return invoiceDAO.getInvoiceByEmailType(invoiceId);
		  	}
	  	
	  	@Override
	  				public List<Charge> getChargeExchangeRateByInvoiceId(long invoiceId) {
	  					return invoiceDAO.getChargeExchangeRateByInvoiceId(invoiceId);
	  				}

	  	public Double currencyConversionFromId(int fromCurrency, int toCurrency, double amount){
	  		List<CurrencySymbol> currencyList=shippingDAO.getallCurrencySymbol();
	  		String fromCur = "";
	  		String toCur = "";
	  		
	  		for(CurrencySymbol currencySymbol : currencyList){
	  			if(currencySymbol.getId() == fromCurrency){
	  				fromCur = currencySymbol.getCurrencyCode();
	  			}else if(currencySymbol.getId() == toCurrency){
	  				toCur = currencySymbol.getCurrencyCode();
	  			}
	  		}
	  		
	  		return currencyConversion(fromCur, toCur, amount);
	  	}
	  	
	  	public Double currencyConversionFromId(int fromCurrency, String toCurrency, double amount){
	  		List<CurrencySymbol> currencyList=shippingDAO.getallCurrencySymbol();
	  		String fromCur = "";
	  		String toCur = "";
	  		
	  		for(CurrencySymbol currencySymbol : currencyList){
	  			if(currencySymbol.getId() == fromCurrency){
	  				fromCur = currencySymbol.getCurrencyCode();
	  			}
	  		}
	  		
	  		return currencyConversion(fromCur, toCurrency, amount);
	  	}
	  	
	  	@Override
	  	public Double currencyConversionToId(String fromCurrency, int toCurrency, double amount){
	  		List<CurrencySymbol> currencyList=shippingDAO.getallCurrencySymbol();
	  		String fromCur = "";
	  		String toCur = "";
	  		
	  		for(CurrencySymbol currencySymbol : currencyList){
	  			if(currencySymbol.getId() == toCurrency){
	  				toCur = currencySymbol.getCurrencyCode();
	  			}
	  		}
	  		
	  		return currencyConversion(fromCurrency, toCur, amount);
	  	}
	  	
		@Override
		public Double currencyConversion(String fromCurrency,
				String toCurrency, double amount) {
			if(fromCurrency !=null && toCurrency !=null && fromCurrency.equals(toCurrency)){
				return amount;
			}else{
				Double exchangeRate = shippingDAO.getExchangeRate(fromCurrency, toCurrency);
				if(exchangeRate!=null && exchangeRate != 0){
					return (amount*exchangeRate);
				}else{
					return (amount*1);
				}
			}
		}
		
		@Override
		public void updateBilledUOM(long id1) {
			// TODO Auto-generated method stub
			shippingDAO.updateBilledUOM(id1);
		}
		
		@Override
		public List<FutureReference> getFutureReference(List<Long> businessIds) {
					// TODO Auto-generated method stub
					
			return shippingDAO.getFutureReference(businessIds);
				}
		
				@Override
			public void deleteFutureReference(Long id2) {
					// TODO Auto-generated method stub
					shippingDAO.deleteFutureReference(id2);
				}
		
				@Override
				public FutureReference showFutureReference(Long id1) {
					// TODO Auto-generated method stub
					return shippingDAO.showFutureReference(id1);
				}
				
		
				@Override
				public List<FutureReferencePackages> showFutureReferencePackage(Long id1) {
					// TODO Auto-generated method stub
					return shippingDAO.showFutureReferencePackage(id1);
					
				}
				
				@Override
				public void updateCustomerStatus(long custId,String customerStatus) {
					// TODO Auto-generated method stub
					customerDAO.updateCustomerStatus(custId,customerStatus);
				
				}
		}
