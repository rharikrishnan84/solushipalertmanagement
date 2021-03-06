package com.meritconinc.shiplinx.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeSet;
import com.meritconinc.shiplinx.model.BusinessContact;

import com.opensymphony.xwork2.ActionContext;

import javax.mail.MessagingException;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

import com.meritconinc.mmr.model.security.User;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.meritconinc.mmr.exception.CardProcessingException;
import com.meritconinc.mmr.exception.CreditOverrunException;
import com.meritconinc.mmr.model.admin.UserSearchCriteria;
import com.meritconinc.shiplinx.model.CurrencySymbol;
import com.meritconinc.shiplinx.model.Package;
import com.soluship.businessfilter.util.BusinessFilterUtil;
import com.meritconinc.mmr.service.UserService;
import com.meritconinc.mmr.utilities.MessageUtil;
import com.meritconinc.mmr.utilities.MmrBeanLocator;
import com.meritconinc.mmr.utilities.StringUtil;
import com.meritconinc.mmr.utilities.mail.MailHelper;
import com.meritconinc.mmr.utilities.security.UserUtil;
import com.meritconinc.shiplinx.carrier.CarrierService;
import com.meritconinc.shiplinx.carrier.dhl.DHLAPI;
import com.meritconinc.shiplinx.carrier.purolator.PurolatorAPI;
import com.meritconinc.shiplinx.dao.AddressDAO;
import com.meritconinc.shiplinx.dao.BusinessDAO;
import com.meritconinc.shiplinx.dao.CarrierServiceDAO;
import com.meritconinc.shiplinx.dao.MarkupManagerDAO;
import com.meritconinc.shiplinx.dao.ShippingDAO;
import com.meritconinc.shiplinx.exception.ShiplinxException;
import com.meritconinc.shiplinx.model.Address;
import com.meritconinc.shiplinx.model.Business;
import com.meritconinc.shiplinx.model.BusinessMarkup;
import com.meritconinc.shiplinx.model.CCTransaction;
import com.meritconinc.shiplinx.model.Carrier;
import com.meritconinc.shiplinx.model.CarrierChargeCode;
import com.meritconinc.shiplinx.model.Charge;
import com.meritconinc.shiplinx.model.ChargeGroup;
import com.meritconinc.shiplinx.model.CreditUsageReport;
import com.meritconinc.shiplinx.model.CustomerCarrier;
import com.meritconinc.shiplinx.model.CustomsInvoice;
import com.meritconinc.shiplinx.model.LtlPoundRate;
import com.meritconinc.shiplinx.model.LtlSkidRate;
import com.meritconinc.shiplinx.model.Markup;
import com.meritconinc.shiplinx.model.Pickup;
import com.meritconinc.shiplinx.model.Province;
import com.meritconinc.shiplinx.model.Rating;
import com.meritconinc.shiplinx.model.Service;
import com.meritconinc.shiplinx.model.ShippingOrder;
import com.meritconinc.shiplinx.model.Zone;
import com.meritconinc.shiplinx.service.impl.InvoiceManagerImpl;
import com.meritconinc.shiplinx.utils.CarrierErrorMessage;
import com.meritconinc.shiplinx.utils.FormattingUtil;
import com.meritconinc.shiplinx.utils.PDFRenderer;
import com.meritconinc.shiplinx.utils.ShiplinxConstants;
import com.meritconinc.shiplinx.model.BusinessEmail;
import com.soluship.businessfilter.util.BusinessFilterUtil;
public class CarrierServiceManagerImpl implements CarrierServiceManager, Runnable {
  private static final Logger log = LogManager.getLogger(CarrierServiceManagerImpl.class);
  private List<CarrierService> carrierServices; // configure the list in
  private AddressDAO addressDAO; // application context file
  private CarrierServiceDAO carrierServiceDAO;
  private List<CarrierErrorMessage> errorMessages;
  private CustomerManager customerService;
  private ShippingService shippingService;
  private AddressManager addressService;
  private BusinessManager businessService;
  protected MarkupManager markupManagerService;
  private CreditCardTransactionManager creditCardService;
  private PinBlockManager pinBlockManager;
  private PickupManager pickupService;
  String errorLog = null;
  public CarrierService carrierServiceThread;
  public CustomerCarrier customerCarrierThread;
  public ShippingOrder orderThread = new ShippingOrder();
  public List<Rating> rateListThread = new ArrayList<Rating>();
  public List<Service> carrierServicesList = new ArrayList<Service>();
  public List<Rating> ratesThread = new ArrayList<Rating>();
  int count=0;
  private UserSearchCriteria criteria;
  private MarkupManagerDAO markupDAO;
  private ShippingDAO shippingDAO;
  private BusinessDAO businessDAO;
  private CreditUsageReport cur;

  private CarrierServiceManagerImpl parentThread;
  private FuelSurchargeService fuelSurchargeService = null;
  private static final long SERVICE_TYPE_LTL_POUND = 1;
  private static final long SERVICE_TYPE_LTL_SKID = 2;
  CarrierErrorMessage errorLogObj;

  private CarrierServiceManager carrierServiceManager;

//code for boostup rate list speed
  HashMap orderThreadMap = new HashMap();
  HashMap servicesMapToApply=new HashMap();
  HashMap markupMapForCustomer=new HashMap();
  private List<Service> listOfAllServices=new ArrayList<Service>();
  List<CustomerCarrier> customerCarriers=new ArrayList<CustomerCarrier>();
  //----------------------------------
  
  private boolean addDummyRateForLTL = false;
  public List<Rating> fromRatingList = new ArrayList<Rating>();

public List<Rating> toRatingList = new ArrayList<Rating>();
  public CarrierServiceManagerImpl(CarrierService carrierService,
      List<Service> carrierServicesList, ShippingOrder shippingOrder, List<Rating> rateList,
      CustomerCarrier customerCarrier, CarrierServiceManagerImpl parentThread) {
    this.carrierServiceThread = carrierService;
    this.carrierServicesList = carrierServicesList;
    this.orderThread = shippingOrder;
    this.rateListThread = rateList;
    this.customerCarrierThread = customerCarrier;
    this.parentThread = parentThread;
  }
  
  public List<Service> getListOfAllServices() {
	  return listOfAllServices;
  }


  public void setListOfAllServices(List<Service> listOfAllServices) {
	  this.listOfAllServices = listOfAllServices;
  }

  public CarrierServiceManagerImpl() {
  }

  public void setBusinessService(BusinessManager businessService) {
    this.businessService = businessService;
  }

  public void setMarkupManagerService(MarkupManager markupManagerService) {
    this.markupManagerService = markupManagerService;
  }

  public void setCreditCardService(CreditCardTransactionManager creditCardService) {
    this.creditCardService = creditCardService;
  }

  public void setPinBlockManager(PinBlockManager pinBlockManager) {
    this.pinBlockManager = pinBlockManager;
  }

  public void setPickupService(PickupManager pickupService) {
    this.pickupService = pickupService;
  }

  public boolean cancelOrder(Long orderId, boolean isAdmin) {
    List<CarrierErrorMessage> cancelErrorMessages = new ArrayList();

    boolean isOrderCanceled = false;
    try {
      ShippingOrder order = shippingService.getShippingOrder(orderId);
      order.setBusiness(businessService.getBusinessById(order.getCustomer().getBusinessId()));
      // get the appropriate account to be used to cancel the shipment
      // Check if the Carrier is null - it will be null for a
      // warehouseOrder
      if (order.getCarrierId() != null) {
        CustomerCarrier customerCarrier = getCarrierAccount(order.getCustomerId(),
            order.getBusinessId(), order.getCarrierId(), order.getFromAddress().getCountryCode(),
            order.getToAddress().getCountryCode());
        Carrier carrier = this.carrierServiceDAO.getCarrier(order.getCarrierId());

        CarrierService carrierService = getCarrierServiceBean(carrier.getImplementingClass());
        try {
          isOrderCanceled = carrierService.cancelOrder(order, customerCarrier);
        } catch (Exception e) {
          log.error("-------Exception----", e);
        }
      } else
        isOrderCanceled = true;

      // we need to refund the $$ back to the customer
     // if (isOrderCanceled || isAdmin) {
      if (isOrderCanceled || (isOrderCanceled&&isAdmin)) {
        order.setStatusId((long) ShiplinxConstants.STATUS_CANCELLED);
        if (order.getCcTransactions() != null) {

          for (CCTransaction transaction : order.getCcTransactions()) {
            if (transaction.getStatus() == CCTransaction.PROCESSED_STATUS) {
              boolean refunded = this.creditCardService
                  .voidCharge(transaction, order.getCustomer());
              order.setPaidAmount(FormattingUtil.add(order.getPaidAmount().doubleValue(),
                  transaction.getAmount() * -1).doubleValue());
            }
          }
        }
        shippingService.updateShippingOrder(order);
        if(order.getBillToType()!=null&&!order.getBillToType().equalsIgnoreCase(ShiplinxConstants.BILL_TO_THIRD_PARTY) && !order.getBillToType().equalsIgnoreCase(ShiplinxConstants.BILL_TO_COLLECT)){
        	if(order.getCustomer().getCreditLimit().doubleValue() > 0){
	        	if(order.getCustomerId() != null && order.getCustomerId() > 0){
	        		double avlCredit= customerService.getAvailableCredit(order.getCustomerId());
	        		double updatedCredit = new BigDecimal(avlCredit + order.getQuoteTotalCharge()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			        customerService.updateAvailableCredit(updatedCredit,order.getCustomerId());
	        	}
        	}
        }
        	        
      }
    } catch (Exception e) {
      log.error("-------Exception----", e);
    }
    return isOrderCanceled;
  }

  public Object getShippingOrderStatus(ShippingOrder order) {
    // TODO Auto-generated method stub
    return null;
  }

  public List<Rating> rateShipment(ShippingOrder order) {
    List<Rating> ratingList = new ArrayList<Rating>();
    ArrayList threadList = new ArrayList();
    cleanupInput(order);
    if (order.getToAddress().getProvinceCode() == null)
      order.getToAddress().setProvinceCode("");

    errorMessages = new ArrayList<CarrierErrorMessage>();
    try {
      // get the carrier available to customer

      if (order.getCustomer() == null)
        order.setCustomer(customerService.getCustomerInfoByCustomerId(order.getCustomerId()));
      order.setBusinessId(order.getCustomer().getBusinessId());
      order.setBusiness(businessService.getBusinessById(order.getBusinessId()));

      if (order.getScheduledShipDate_web() != null && order.getScheduledShipDate_web().length() > 0) {
        Date date = FormattingUtil.getDate(order.getScheduledShipDate_web(),
            FormattingUtil.DATE_FORMAT_WEB);
        order.setScheduledShipDate(new Timestamp(date.getTime()));
      } else
        order.setScheduledShipDate(new Timestamp(Calendar.getInstance().getTimeInMillis()));

      order.setDateCreated(new Timestamp(Calendar.getInstance().getTimeInMillis()));

      // On Tue, Apr 24, 2012 at 7:25 PM, Rizwan Merchant
      // <rizwan.merchant@meritconinc.com> wrote:
      // 1. During Shipping web service request, we need to perform rating
      // using the service id sent in the ship request "Service" object
      // - When rating is performed in the CarrierServiceImpl, if the
      // service id is present in the order, then invoke only the carrier
      // associated with that service
      // - Loop throuh all the rates and return only the one where the
      // service id mathes
      List<Carrier> carriersForBusiness = new ArrayList<Carrier>();
      long shipServiceId = -1;
      if (order.getService() != null && order.getService().getCarrier() != null) {
        shipServiceId = order.getService().getId();
        carriersForBusiness = new ArrayList<Carrier>();
        carriersForBusiness.add(order.getService().getCarrier());
      } else {
        if (order.getCarrierId_web() != null && order.getCarrierId_web() > 0)// if a carrier is
          // selected for Quick
          // shipment
          carriersForBusiness.add(this.carrierServiceDAO.getCarrier(order.getCarrierId_web()));
        else
          carriersForBusiness = this.getCarriersForBusiness(order.getBusinessId());
      }
      log.debug("CARRIER ID WEB::::" + order.getCarrierId_web()
          + ":::::::CARIER ID BUSINESS:::::::::" + order.getBusinessId());
      List<Carrier> tempCarriersForBusiness = new ArrayList<Carrier>();
      Markup markup = new Markup();
      markup.setCustomerId(order.getCustomerId());
      markup.setDisabled(0);
      boolean flagCarrierList;
      boolean flagm = false;
      List<Markup> myMarkups1;
      List<Markup> myMarkups=markupManagerService.getAllMarkupsForCustomer(order.getCustomerId(),order.getBusinessId());
    	  	long cus = order.getCustomerId();
          	  order.setCustomerId(0l);
          	  myMarkups1=BusinessFilterUtil.getAllMarkupsForCustomer(markupManagerService,order.getCustomerId(),order.getBusinessId());
          	  myMarkups.addAll(myMarkups1);
          	  order.setCustomerId(cus);
      customerCarriers = carrierServiceDAO.getCutomerCarrier(order.getCustomerId());
      for(Carrier carrier:carriersForBusiness){
    	  List<Markup> localMarkup=new ArrayList<Markup>();
    	  for(Markup myMarkupSlice:myMarkups){
    		  if(myMarkupSlice.getCarrierId().equals(carrier.getId())){
    			  localMarkup.add(myMarkupSlice);
    		  }
    	  }
    	  if(localMarkup!=null && localMarkup.size()>0){
    		  markupMapForCustomer.put(carrier.getId(),localMarkup);
    	  }
      }
      Set markupSet = markupMapForCustomer.entrySet(); 
      for (Carrier carrier : carriersForBusiness) {
        CarrierService carrierService = getCarrierServiceBean(carrier.getImplementingClass());
        Iterator i1 = markupSet.iterator();
        List<Markup> markupLst=new ArrayList<Markup>();
        while(i1.hasNext()){
        	Map.Entry markupParm = (Map.Entry)i1.next();
        	Long key=(Long) markupParm.getKey();
        	if(key.equals(carrier.getId())){
        		markupLst=(List<Markup>) markupParm.getValue();
        		break;
        	}
        }
        flagCarrierList=false;
        for(Markup markup1:markupLst){
        	if(!markup1.getCustomerId().equals(0)){
        		flagCarrierList=true;
        	}
        }
        markup.setCarrierId(carrier.getId());
        //flagCarrierList = markupManagerService.getMarkupListForCustomerAndCarrier(markup);
        if (markup != null
            && (flagCarrierList == true || carrier.getId() == ShiplinxConstants.CARRIER_GENERIC)) {
          tempCarriersForBusiness.add(carrier);
        }
      }
      ShippingOrder upsShippingOrderThread = new ShippingOrder();
      carriersForBusiness = tempCarriersForBusiness;
      listOfAllServices=carrierServiceDAO.getAllServices();

      //servicesMapToApply.put(0, serviceList);
      for(Carrier carrier:carriersForBusiness){
    	  List<Service> serviceList = new ArrayList<Service>();
    	  for(Service service:listOfAllServices){
    		  if(service.getMasterCarrierId().equals(carrier.getId())){
    			  serviceList.add(service);
    		  }
    		  else if(service.getCarrierId().equals(carrier.getId())){
	      			  serviceList.add(service);
	      		  }
    		  else if(service.getCarrierId()==ShiplinxConstants.CARRIER_ESHIPPLUS){
    			      			  serviceList.add(service);
    			      		  }
    		      		
    	  }
    	  //servicesToApply
    	  if(serviceList.size()>0){
    		  servicesMapToApply.put(carrier.getId(), serviceList);
    	  }
      }
      for (Carrier carrier : carriersForBusiness) {        
        // get the appropriate account to be used to generate/rate the
        // shipment
    	  CarrierService carrierService = getCarrierServiceBean(carrier.getImplementingClass());
    	      	    ShippingOrder shippingorderThread = new ShippingOrder();
    	          //////New UPS Issue Mohan
        CustomerCarrier customerCarrier = null;
        String fromCountryCode;
        		String toCountryCode;
        		ArrayList groupingThreadList = new ArrayList();
                if(!(order.getFromAddress().getCountryCode().equalsIgnoreCase(order.getToAddress()
                        .getCountryCode())) &&  (carrier.getId()==2 || carrier.getId() == 5)){
                	fromCountryCode=order.getFromAddress().getCountryCode();
               	toCountryCode=order.getToAddress().getCountryCode();
                
               	for(int i=0;i<2;i++){        		
                		if(i==1){
                			fromCountryCode=order.getToAddress().getCountryCode();
                			toCountryCode=order.getFromAddress().getCountryCode();
                		}
                		try {
                	          customerCarrier = getCarrierAccount(order.getCustomerId(), order.getBusinessId(),
                	              carrier.getId(), fromCountryCode, toCountryCode);
                	        } catch (Exception e) {
                	          log.error("Could not determine the account to use for shipment!", e);
                	          customerCarrier = null;
                	        }
        
                	        if (customerCarrier == null) {
                	          log.warn("No account foound for customer " + order.getCustomerId()
               	              + " to use for rating of carrier " + carrier.getName());
                	          continue;
                	        }
                	        customerCarrier.setBusinessCarrierDiscount(carrier.getBusinessCarrierDiscount());
               	        customerCarrier.setCount(i);
                	        // get the rates for all services
               	        carrierServicesList = getCarrierServicesList(order,customerCarrier,carrier);
                	        List<Rating> tempRatingList = new ArrayList<Rating>();
                	        try {          
               	          if (carrierServicesList != null && carrierServicesList.size() > 0) {
                	            shippingorderThread = order;
                	            String key=(i==1)?carrier.getId().toString()+"123":carrier.getId().toString();
                	            orderThreadMap.put(key, shippingorderThread);
                	            CarrierServiceManagerImpl carrierServiceManagerImpl = new CarrierServiceManagerImpl(
                	                carrierService, carrierServicesList, shippingorderThread, ratingList,
                	                customerCarrier, this);
                	            Thread t = new Thread(carrierServiceManagerImpl);
                	            threadList.add(t);
                	            t.start();   
                	            
                	          }
                	        } catch (ShiplinxException e) {
                	          for (String s : e.getErrorMessages()) {
                	            CarrierErrorMessage message = new CarrierErrorMessage(carrier.getId(),
               	                customerCarrier.getCarrierName() + " : " + s);
                	            this.getParentThread().getErrorMessages().add(message);
               	          }
                	          continue;
                	        }
                	}
                }
                
                
                else{
        try {
          customerCarrier = getCarrierAccount(order.getCustomerId(), order.getBusinessId(),
              carrier.getId(), order.getFromAddress().getCountryCode(), order.getToAddress()
                  .getCountryCode());
        } catch (Exception e) {
          log.error("Could not determine the account to use for shipment!", e);
          customerCarrier = null;
        }

        if (customerCarrier == null) {
          log.warn("No account foound for customer " + order.getCustomerId()
              + " to use for rating of carrier " + carrier.getName());
          continue;
        }

        // if the discount from the carrier is flat across all services,
        // this information is stored in business_carrier table
        // example DHL, Loomis for business 1
        customerCarrier.setBusinessCarrierDiscount(carrier.getBusinessCarrierDiscount());
        // get the rates for all services
        carrierServicesList = getCarrierServicesList(order,customerCarrier,carrier);
                
                List<Rating> tempRatingList = new ArrayList<Rating>();
                try {          
                  if (carrierServicesList != null && carrierServicesList.size() > 0) {
                    shippingorderThread = order;
                    CarrierServiceManagerImpl carrierServiceManagerImpl = new CarrierServiceManagerImpl(
                        carrierService, carrierServicesList, shippingorderThread, ratingList,
                        customerCarrier, this);
                    Thread t = new Thread(carrierServiceManagerImpl);
                    threadList.add(t);
                    t.start();
          }
                } catch (ShiplinxException e) {
                	          for (String s : e.getErrorMessages()) {
                	            CarrierErrorMessage message = new CarrierErrorMessage(carrier.getId(),
                	                customerCarrier.getCarrierName() + " : " + s);
                	            this.getParentThread().getErrorMessages().add(message);
                	          }
                	          continue;
        }

                }
      }
      for (int i = 0; i < threadList.size(); i++) {
        Thread t1 = (Thread) threadList.get(i);
        try {
          t1.join();
        } catch (InterruptedException e) {
          log.debug("Thread is Interrupted" + e.getMessage());
          // TODO Auto-generated catch block
          e.printStackTrace();
        }

      }
      Set upsSet= orderThreadMap.entrySet();
      Iterator upsIterator = upsSet.iterator();
      while(upsIterator.hasNext()){
    	  Map.Entry threadOrder = (Map.Entry)upsIterator.next();
    	  String key=(String) threadOrder.getKey();
    	  if(key!=null && (key.equals(Long.toString(ShiplinxConstants.CARRIER_UPS)) || key.equals(Long.toString(ShiplinxConstants.CARRIER_UPS_USA)))){
    		  orderThread=(ShippingOrder) threadOrder.getValue();
    		  break;
    	  }
      }
      upsShippingOrderThread = orderThread;
      if(orderThread.getFromRatingList()!=null && orderThread.getToRatingList()!=null){
    	  findCheapestRate(ratingList, orderThread);	         	
      }
      if(upsShippingOrderThread!=null && upsShippingOrderThread.getToRatingList()!=null && upsShippingOrderThread.getToRatingList().size() == 0 &&  upsShippingOrderThread.getFromRatingList() !=null && upsShippingOrderThread.getFromRatingList().size()>0){
    	  ratingList.addAll(upsShippingOrderThread.getFromRatingList());
      }else if(upsShippingOrderThread!=null && upsShippingOrderThread.getFromRatingList()!=null && upsShippingOrderThread.getFromRatingList().size() == 0 && upsShippingOrderThread.getToRatingList() !=null && upsShippingOrderThread.getToRatingList().size()>0){
    	  ratingList.addAll(upsShippingOrderThread.getToRatingList());
      }
      setRateListThread(rateListThread);
      setOrderThread(orderThread);
      setErrorMessages(errorMessages);
      // process the rates before presenting
      if (order.getServiceId_web() != null && order.getServiceId_web() > 0)
        shipServiceId = order.getServiceId_web();
      try{
    	  businessMarkupForRates(ratingList,order);
      }catch(Exception e){
    	  log.error("ERROR OCCURED WHILE CHECKING BUSINESS MARKUP");
    	  log.error(e);
      }
      processRates(ratingList, order, shipServiceId);
      Collections.sort(ratingList, Rating.PriceComparator);// Sorting the
      // Total of
      // Rate
     // List<Rating> ltlRateList = new ArrayList<Rating>();
      log.debug("PACKAGE TYPE::::::::::" + order.getPackageTypeId().getName());
      groupingServices(ratingList, order);
      groupingServicesPoundRate(ratingList, order);
      for (int i = 0; i < ratingList.size(); i++) {
        long serviceId = ratingList.get(i).getServiceId();
        Service service = getService(serviceId);
        if (service != null && service.getServiceType() == SERVICE_TYPE_LTL_POUND) {
          long length = 0l, width = 0l, height = 0l, weight = 0l;
          long maxLength = 0l, maxWidth = 0l, maxHeight = 0l, maxWeight = 0l;
          List<com.meritconinc.shiplinx.model.Package> packageList = order.getPackages();
          List<BigDecimal> lengthList = new ArrayList<BigDecimal>();
          List<BigDecimal> widthList = new ArrayList<BigDecimal>();
          List<BigDecimal> heightList = new ArrayList<BigDecimal>();
          List<Float> weightList = new ArrayList<Float>();
          for (com.meritconinc.shiplinx.model.Package package1 : packageList) {
            lengthList.add(package1.getLength());
            widthList.add(package1.getWidth());
            weightList.add(package1.getWeight().floatValue());
            heightList.add(package1.getHeight());
          }

          if (lengthList != null && lengthList.size() > 0) {
            maxLength = Collections.max(lengthList).longValue();
          }
          if (widthList != null && widthList.size() > 0) {
            maxWidth = Collections.max(widthList).longValue();
          }

          if (heightList != null && heightList.size() > 0) {
            maxHeight = Collections.max(heightList).longValue();
          }

          if (weightList != null && weightList.size() > 0) {
            maxWeight = Collections.max(weightList).longValue();
          }
          Double defaultValue = 0.0;
          Integer defaultPercentage = 0;
          if ((service.getMaxWidth() != width && (maxWidth > service.getMaxWidth()))
              || (service.getMaxHeight() != height && (maxHeight > service.getMaxHeight()))
              || (service.getMaxLength() != length && (maxLength > service.getMaxLength()))
              || (service.getMaxWeight() != weight && (maxWeight > service.getMaxWeight()))) {
            ratingList.get(i).setCarrierId(ShiplinxConstants.CARRIER_GENERIC);
            ratingList.get(i).setServiceId(ShiplinxConstants.DEFAULT_IC_SERVICE_ID);
            ratingList.get(i).setServiceName(ShiplinxConstants.DEFAULT_IC_SERVICE);
            ratingList.get(i).setCarrierName(order.getBusiness().getName());
            ratingList.get(i).setTotal(defaultValue);
            ratingList.get(i).setTotalCost(defaultValue);
            ratingList.get(i).setMarkupPercentage(defaultPercentage);
            ratingList.get(i).setMarkupTypeText(ShiplinxConstants.TYPE_MARKUP_TEXT);
            Charge charge = new Charge();
            charge.setChargeCode(ShiplinxConstants.GROUP_FREIGHT_CHARGE);
            charge.setName(ShiplinxConstants.FREIGHT_STRING);
            charge.setCost(defaultValue);
            charge.setTariffRate(defaultValue);
            charge.setCharge(defaultValue);
            ratingList.get(i).setCharge(charge);
            Charge chargeFuel = new Charge();
            chargeFuel.setChargeCode(ShiplinxConstants.GROUP_FUEL_CHARGE);
            chargeFuel.setName(ShiplinxConstants.FUEL_SURCHARGE);
            chargeFuel.setCost(defaultValue);
            chargeFuel.setCharge(defaultValue);
            chargeFuel.setTariffRate(defaultValue);
            List<Charge> chargesList = new ArrayList<Charge>();
            chargesList.add(chargeFuel);
            chargesList.add(charge);
            ratingList.get(i).setCharges(chargesList);
            ratingList.get(i).setTransitDays(defaultValue.intValue());
            ratingList.get(i).setBillWeight(defaultValue);
          }
        }
      }
      List<Rating> removeIcService = new ArrayList<Rating>();
      boolean isICRateOccured = false;
      boolean isIcCarrier = false;
      // iterating the rating list
      for (Rating rating : ratingList) {
        if (rating != null && rating.getTotal() == 0 && rating.getTotalCost() == 0
            && ((new Long(rating.getCarrierId()).intValue()) == ShiplinxConstants.CARRIER_GENERIC)) {
          removeIcService.add(rating);
          isIcCarrier = true;
        } else if (((new Long(rating.getCarrierId()).intValue()) == ShiplinxConstants.CARRIER_GENERIC)) {
          isICRateOccured = true;
          isIcCarrier = true;
        }
      }
      if (removeIcService != null && removeIcService.size() > 0) {
        ratingList.removeAll(removeIcService);
      }
      if (!isICRateOccured && isIcCarrier) {
        ratingList.add(setDefaultIC());
      }
      Collections.sort(ratingList, Rating.PriceComparator);
      if (ratingList.size() > 0 && (order.isCheapestMethod() || order.isFastestMethod()))// filter
        // only
        // if
        // one
        // of
        // the
        // options:
        // cheapest
        // or
        // fastest
        // is
        // selected.
        ratingList = quickShipFilter(ratingList, order);

    } catch (Exception e) {
      log.debug("-------Exception----" + e);
      e.printStackTrace();
    }

    try {
      boolean genericCarrierInListAlready = false;
      boolean quickShipWithGenericCarrier = true;
      if (ratingList.size() > 0) {
    	  quickShipWithGenericCarrier = false;
        for (int i = 0; i < ratingList.size(); i++) {
          if (ratingList.get(i).getCarrierId() == ShiplinxConstants.CARRIER_GENERIC) {
            genericCarrierInListAlready = true;
          }
        }
        if (!genericCarrierInListAlready) {
          addDummyRateForLTL = true;
        }

      }
      if ((addDummyRateForLTL == true || quickShipWithGenericCarrier == true)
          && order.getPackageTypeId().getName().equals(ShiplinxConstants.PACKAGE_PALLET_STRING)
          && !genericCarrierInListAlready) {
        Double defaultValue = 0.0;
        Integer defaultPercentage = 0;
        Rating EmptyRate = new Rating();
        EmptyRate.setCarrierId(ShiplinxConstants.CARRIER_GENERIC);
        /*EmptyRate.setServiceId(ShiplinxConstants.DEFAULT_IC_SERVICE_ID);
        EmptyRate.setServiceName(ShiplinxConstants.DEFAULT_IC_SERVICE);*/
        if(order.getServiceId_web() != null && order.getServiceId_web() > 0){
        	
        	EmptyRate.setServiceName(this.getService(order.getServiceId_web()).getName());
            EmptyRate.setServiceId(order.getServiceId_web());
        } else{
        	EmptyRate.setServiceId(ShiplinxConstants.DEFAULT_IC_SERVICE_ID);
        	EmptyRate.setServiceName(ShiplinxConstants.DEFAULT_IC_SERVICE);
        }
        EmptyRate.setCarrierName(order.getBusiness().getName());
        EmptyRate.setTotal(defaultValue);
        EmptyRate.setTotalCost(defaultValue);
        EmptyRate.setMarkupPercentage(defaultPercentage);
        EmptyRate.setMarkupTypeText(ShiplinxConstants.TYPE_MARKUP_TEXT);
        Charge charge = new Charge();
        charge.setChargeCode(ShiplinxConstants.GROUP_FREIGHT_CHARGE);
        charge.setName(ShiplinxConstants.FREIGHT_STRING);
        charge.setCost(defaultValue);
        charge.setTariffRate(defaultValue);
        charge.setCharge(defaultValue);
        EmptyRate.setCharge(charge);
        Charge chargeFuel = new Charge();
        chargeFuel.setChargeCode(ShiplinxConstants.GROUP_FUEL_CHARGE);
        chargeFuel.setName(ShiplinxConstants.FUEL_SURCHARGE);
        chargeFuel.setCost(defaultValue);
        chargeFuel.setCharge(defaultValue);
        chargeFuel.setTariffRate(defaultValue);
        List<Charge> chargesList = new ArrayList<Charge>();
        chargesList.add(chargeFuel);
        chargesList.add(charge);
        EmptyRate.setCharges(chargesList);
        EmptyRate.setTransitDays(defaultValue.intValue());
        EmptyRate.setBillWeight(defaultValue);
        ratingList.add(EmptyRate);
        Collections.sort(ratingList, Rating.PriceComparator);
      }
    } catch (Exception e) {
      log.debug("-------Exception----" + e);
      e.printStackTrace();
    }
    boolean flagRate = false;
    if(ratingList.size()>0){
    	flagRate = markupManagerService.isCustomerMarkupByDisabled(order.getCustomerId());
    	        if(!flagRate){
    	        	flagRate = markupManagerService.isAllLevelMarkupDisabled(order.getBusinessId());
    	        }
    	    }
    if(!flagRate)                       // restricting rates for    
     ratingList.clear();                // direct new customer
    List<Rating> rateL = new ArrayList<Rating>();
    List<Long> serviceIds = new ArrayList<Long>();
 
    for(Rating rating : ratingList){
  	  if(!serviceIds.contains(rating.getServiceId())){
  	  serviceIds.add(rating.getServiceId());
  	  rateL.add(rating);
  	  } 
    }
    ratingList = new ArrayList<Rating>();
    ratingList.addAll(rateL);
    return ratingList;
  }

  private Zone getZone(Long zoneStructureId, Address address) {
    Zone z = this.markupDAO.findZone(zoneStructureId, address);
    if (z == null) {
      // Add as new zone
      // z = Zone.getObject(zoneStructureId, address);
      // z = this.markupDAO.addZone(z);
      return new Zone();
    }
    return z;
  }

  private List<Rating> quickShipFilter(List<Rating> ratingList, ShippingOrder order) {
    log.debug("Inside quickShipFilter method---------------------------");
    List<Rating> returnRatingList = new ArrayList<Rating>();

    if (order.isFastestMethod() && order.isCheapestMethod()) {
      // find the fastest transit time
      int fastestTime = 999999;
      for (Rating r : ratingList) {
        if (r.getTransitDays() > 0 && r.getTransitDays() < fastestTime)
          fastestTime = r.getTransitDays();
      }
      for (Rating r : ratingList) {
        // now get all those that are the "fastest"
        if (r.getTransitDays() == fastestTime)
          returnRatingList.add(r);
      }
      // now get the cheapest of all rates for the selected carrier.
      return findMinimumRating(returnRatingList);
    } else if (!order.isFastestMethod() && order.isCheapestMethod()) {
      return findMinimumRating(ratingList);
    } else if (order.isFastestMethod() && !order.isCheapestMethod()) {
      // find the fastest transit time
      int fastestTime = 999999;
      for (Rating r : ratingList) {
        if (r.getTransitDays() > 0 && r.getTransitDays() < fastestTime)
          fastestTime = r.getTransitDays();
      }
      for (Rating r : ratingList) {
        // now get all those that are the "fastest"
        if (r.getTransitDays() == fastestTime)
          returnRatingList.add(r);
      }
      // According to Jay, if fastest is chosen, then return the fastest
      // cheapest - Jan 22 2013
      return findMinimumRating(returnRatingList);
    }

    return returnRatingList;
  }

  private List<Rating> findMinimumRating(List<Rating> RatingList) {
    List<Rating> minRatingList = new ArrayList<Rating>();
    double temp = 0.0;
    if (RatingList.size() > 0) {
      temp = RatingList.get(0).getTotal();

      for (Rating r : RatingList) {
        if (r.getTotal() <= temp)
          minRatingList.add(r);
        else
          break;
      }
    }
    return minRatingList;
  }

  private List<CustomerCarrier> getCarrierAccounts(Long customerId, String countryName) {
    return getCarrierServiceDAO().getCarrierAccounts(customerId, countryName);
  }

  public long createPickup(Pickup pickup) throws Exception {

    long pickupId;
    try {
      errorMessages = new ArrayList<CarrierErrorMessage>();
      Carrier carrier = this.carrierServiceDAO.getCarrier(pickup.getCarrierId());
      Service service = carrierServiceDAO.getService(pickup.getServiceId());
      pickup.setServiceCode(service.getCode());
      CarrierService carrierService = getCarrierServiceBean(carrier.getImplementingClass());
      if(pickup.getCarrierAccount() == null){
    	  CustomerCarrier customerCarrier = new CustomerCarrier();
    	      	   if(pickup.getOrderId()>0){
    	      	   ShippingOrder shippingOrder = shippingService.getShippingOrder(pickup.getOrderId());
    	      	   pickup.setCarrierId(shippingOrder.getCarrierId());
    	      	   pickup.setServiceId(shippingOrder.getServiceId());
    	      	   pickup.setAddress(shippingOrder.getFromAddress());
    	      	   pickup.setBusinessId(shippingOrder.getBusinessId());
    	      	   pickup.setCustomerId(shippingOrder.getCustomerId());
    	      	   pickup.setDestinationCountryCode(shippingOrder.getToAddress().getCountryCode());
    	      	   pickup.setPickupDate(shippingOrder.getScheduledShipDate());
    	      	   pickup.setPickupRequired(true);
    	      	   pickup.setQuantity(shippingOrder.getQuantity());
    	      	   pickup.setTotalWeight(shippingOrder.getBilledWeight().doubleValue());
    	      	   pickup.setWeightUnit(shippingOrder.getBilledWeightUOM());
    	      	   customerCarrier = getCarrierAccount(shippingOrder.getCustomerId(),
    	      	   shippingOrder.getBusinessId(), shippingOrder.getCarrierId(), shippingOrder.getFromAddress().getCountryCode(),
    	      	   shippingOrder.getToAddress().getCountryCode());
    	      	   }else{
    	      	   customerCarrier = getCarrierAccount(pickup.getCustomerId(),
          pickup.getBusinessId(), pickup.getCarrierId(), pickup.getAddress().getCountryCode(),
          pickup.getDestinationCountryCode());
    	      	 }
      pickup.setCarrierAccount(customerCarrier);
      }
      carrierService.requestPickup(pickup);

      // do not associate this address with customer, otherwise it shows
      // up on address book
      pickup.getAddress().setCustomerId(0);
      pickup.getAddress().setDefaultFromAddress(false);
      pickup.getAddress().setDefaultToAddress(false);

      // save the pick up information
      long addressId = addressService.add(pickup.getAddress());
      pickup.setAddressId(addressId);
      pickup.setStatus(ShiplinxConstants.STATUS_PICKUP_ACTIVE);
      pickupId = pickupService.add(pickup);
      if (!StringUtil.isEmpty(pickup.getConfirmationNum()))
    	  sendPickupNotificationMail(pickup,service);
    } catch (ShiplinxException e) {
      log.error("Error in pick up request!", e);
      for (String s : e.getErrorMessages()) {
        CarrierErrorMessage message = new CarrierErrorMessage(pickup.getCarrierId(), " : " + s);
        errorMessages.add(message);
      }
      throw e;
    } catch (Exception e) {
      log.error("Pick up creation error", e);
      throw e;
    }
    return pickupId;
  }

  private boolean sendPickupNotificationMail(Pickup pickup, Service service) {
    boolean retval = false;
    String toAddress = null;
    Business business = businessService.getBusinessById(pickup.getBusinessId());

    toAddress = pickup.getAddress().getEmailAddress();
    if (toAddress == null || toAddress.length() == 0) {
      log.error("User's email address is not sent, cannot send an email quote!");
      return false;
    }
    try {
      // GROUP_EMAIL_ADDRESS_en_CA

      String subject = MessageUtil.getMessage("label.subject.pickup.notification");

      String body=BusinessFilterUtil.getEmailBody(pickup.getBusinessId(),ShiplinxConstants.MSGID_EMAIL_SHIP_PICKUP);
            if (body == null || body.length() == 0) {
          	  body = MessageUtil.getMessage("mail.pickup.notification.body");
            }      

      if (body == null || body.length() == 0) {
        log.error("Cannot find template to send pickup notification");
        return false;
      }
      
      BusinessDAO businessDAO=(BusinessDAO)MmrBeanLocator.getInstance().findBean("businessDAO");
            Business b=businessDAO.getBusiessById(pickup.getBusinessId());
           BusinessContact bc=businessDAO.getbusinessContactByBusiness(pickup.getBusinessId());
            
            
           body = new String(body.replaceAll("%BUSINESSCOLOR", b.getCssVO().getBarFirstColor()));
            body = new String(body.replaceAll("%BUSINESSABBRIVATION", bc.getBusinesssAbbrivation()));
            body = new String(body.replaceAll("%BUSINESSLOGOUTURL", b.getLogoutURL()));
            body = new String(body.replaceAll("%BUSINESSNAME", b.getName()));
		    //  body = new String(body.replaceAll("%YEAR",  BusinessFilterUtil.getYear()));
			  body = new String (body.replaceAll("%FOOTER", b.getCssVO().getFooter1()));
      body = new String(body.replaceAll("%CONF", pickup.getConfirmationNum()));
      body = new String(body.replaceAll("%ATTENTION", pickup.getAddress().getContactName()));
      body = new String(body.replaceAll("%ShipDate",
          FormattingUtil.getFormattedDate(pickup.getPickupDate(), FormattingUtil.DATE_FORMAT_WEB)
              + ""));
      body = new String(body.replaceAll("%SFROMCOMPANY", pickup.getAddress().getAbbreviationName()));
      body = new String(body.replaceAll("%SFROMADDRESS1", pickup.getAddress().getAddress1()));
      body = new String(body.replaceAll("%SFROMADDRESS2%", pickup.getAddress().getAddress2()));
      body = new String(body.replaceAll("%SFROMZIP", pickup.getAddress().getPostalCode()));
      body = new String(body.replaceAll("%SFROMPROVINCE", pickup.getAddress().getProvinceCode()));
      body = new String(body.replaceAll("%SFROMCOUNTRY", pickup.getAddress().getCountryCode()));
      body = new String(body.replaceAll("%SFROMCITY", pickup.getAddress().getCity()));

      body = new String(body.replaceAll("%READYHR", pickup.getReadyHour()));
      body = new String(body.replaceAll("%READYMIN", pickup.getReadyMin()));
      body = new String(body.replaceAll("%CLOSEHR", pickup.getCloseHour()));
      body = new String(body.replaceAll("%CLOSEMIN", pickup.getCloseMin()));
      body = new String(body.replaceAll("%LOCATION", pickup.getPickupLocation()));
      body = new String(body.replaceAll("%REFERENCE", pickup.getPickupReference()));
      body = new String(body.replaceAll("%INSTRUCTIONS", pickup.getInstructions()));

      body = new String(body.replaceAll("%CARRIERSERVICE",
          carrierServiceDAO.getCarrier(pickup.getCarrierId()).getName() + " - "
              + shippingService.getServiceById(pickup.getServiceId()).getName()));
      body = new String(body.replaceAll("%QUANTITY", pickup.getQuantity() + " Pcs"));
      body = new String(body.replaceAll("%TOTALWEIGHT", pickup.getTotalWeight() + ""));
      body = new String(body.replaceAll("%WEIGHTUNITS", pickup.getWeightUnit()));
      String fromAddress[] = business.getAddress().getEmailAddress().split(";");
      List<String> bccAddress = new ArrayList<String>();
            bccAddress.add(business.getLtlEmail());
      if(service!=null && service.getEmailType() != null && service.getEmailType().equalsIgnoreCase("LTL")){
      	 retval = MailHelper.sendEmailNow2(business.getSmtpHost(), business.getSmtpUsername(),
      	          business.getSmtpPassword(), business.getName(), business.getSmtpPort(), fromAddress[0],
      	          toAddress, bccAddress, subject, body, null, true);
      }else{
      retval = MailHelper.sendEmailNow2(business.getSmtpHost(), business.getSmtpUsername(),
          business.getSmtpPassword(), business.getName(), business.getSmtpPort(), fromAddress[0],
          toAddress, null, subject, body, null, true);
      }
    } catch (MessagingException e) {
      log.error("Error sending email - Messaging Exception: ", e);
      retval = false;
    } catch (Exception e) {
      log.error("Error sending email - Exception: ", e);
      retval = false;
    }

    return retval;
  }

  public boolean cancelPickup(Pickup pickup) throws Exception {

    try {
      CustomerCarrier customerCarrier = getCarrierAccount(pickup.getCustomerId(),
          pickup.getBusinessId(), pickup.getCarrierId(), pickup.getAddress().getCountryCode(),
          pickup.getDestinationCountryCode());
      Carrier carrier = this.carrierServiceDAO.getCarrier(pickup.getCarrierId());

      CarrierService carrierService = getCarrierServiceBean(carrier.getImplementingClass());

      pickup.setCarrierAccount(customerCarrier);
      pickup.setStatus(ShiplinxConstants.STATUS_PICKUP_CANCELLED);
      // We will set the pick up
      // status to cancelled
      // irrespective of what the
      // carrier returns

      boolean cancelled = carrierService.cancelPickup(pickup);
      pickupService.updatePickup(pickup);
      return cancelled;

    } catch (Exception e) {
      log.error("Error in cancel pick up with id" + pickup.getPickupId(), e);
      throw e;

    }
  }

  private CustomerCarrier getCarrierAccount(long customerId, long businessId, long carrierId,
      String fromCountryCode, String toCountryCode) {

    CustomerCarrier carrierAccount = null;

    // First look for customer specific
    carrierAccount = carrierServiceDAO.getCarrierAccount(customerId, businessId, carrierId,
        fromCountryCode, toCountryCode);
    if (carrierAccount != null)
      return carrierAccount;

    carrierAccount = carrierServiceDAO.getCarrierAccount(customerId, businessId, carrierId,
        fromCountryCode, null);
    if (carrierAccount != null)
      return carrierAccount;
    if(carrierId==2 && fromCountryCode.equalsIgnoreCase(toCountryCode) || (carrierId !=2 && carrierId != 5)){
    	if(fromCountryCode == ShiplinxConstants.CANADA || (carrierId !=2 && carrierId != 5)){
    		carrierAccount = carrierServiceDAO.getCarrierAccount(customerId, businessId, carrierId,
    		        toCountryCode, null);
    		    if (carrierAccount != null)
    		      return carrierAccount;

    		    carrierAccount = carrierServiceDAO.getCarrierAccount(customerId, businessId, carrierId, null,
    		        null);
    		    if (carrierAccount != null)
    		      return carrierAccount;
    	}
    }
    // Check the defaults now
    carrierAccount = carrierServiceDAO.getCarrierAccount(new Long(0), businessId, carrierId,
        fromCountryCode, toCountryCode);
    if (carrierAccount != null)
      return carrierAccount;

    carrierAccount = carrierServiceDAO.getCarrierAccount(new Long(0), businessId, carrierId,
        fromCountryCode, null);
    if (carrierAccount != null)
      return carrierAccount;
    if(carrierId==2 && fromCountryCode.equalsIgnoreCase(toCountryCode) || (carrierId !=2 && carrierId != 5)){
    	if(fromCountryCode == ShiplinxConstants.CANADA || (carrierId !=2 && carrierId != 5)){
    		carrierAccount = carrierServiceDAO.getCarrierAccount(new Long(0), businessId, carrierId,
    		        toCountryCode, null);
    		    if (carrierAccount != null)
    		      return carrierAccount;

    		    carrierAccount = carrierServiceDAO.getCarrierAccount(new Long(0), businessId, carrierId, null,
    		        null);
    		    if (carrierAccount != null)
    		      return carrierAccount;
    	}
    }
    return carrierAccount;
  }

  public CarrierService shipOrder(ShippingOrder order, Rating rate) throws Exception {
    log.debug("-------shipOrder----");
    long startTime = System.currentTimeMillis();
    CarrierService cs = null;
    if (errorMessages != null) {
      errorMessages.clear();
      errorMessages = null;
    }
    errorMessages = new ArrayList<CarrierErrorMessage>();

    if (rate == null) {
      // Most likely this call is from web service
      // On Tue, Apr 24, 2012 at 7:25 PM, Rizwan Merchant
      // <rizwan.merchant@meritconinc.com> wrote:
      // 1. During Shipping web service request, we need to perform rating
      // using the service id sent in the ship request "Service" object
      // - When rating is performed in the CarrierServiceImpl, if the
      // service id is present in the order, then invoke only the carrier
      // associated with that service
      // - Loop throuh all the rates and return only the one where the
      // service id mathes
      if (order != null && order.getService() != null && order.getService().getId() != null) {
        // Run address validation to ensure we have the right
        // information
        addressService.runAddressValidation(order.getFromAddress());
        addressService.runAddressValidation(order.getToAddress());

        // Service code is service id, therefore retrieve service based
        // on service id
        Service service = this.shippingService.getServiceById(order.getService().getId());
        if (service != null && service.getCarrier() != null) {
          order.setCarrierId(service.getCarrierId());
          order.setCarrierName(service.getCarrier().getName());
          order.setService(service);
          List<Rating> ratings = this.rateShipment(order);
          if (ratings != null && ratings.size() > 0) {
            rate = ratings.get(0);
            // order.setCustomerCarrier(rate.getCustomerCarrier());
          }
        }
      }
    }
    // Make sure rates are retrieved
    if (rate == null) {
      if (order.getCarrierId() != null)
        errorMessages.add(new CarrierErrorMessage(order.getCarrierId(), MessageUtil.getMessage(
            "rate.not.found.for.specified.service", MessageUtil.getLocale())));
      else
        errorMessages.add(new CarrierErrorMessage(order.getCarrierId(), MessageUtil.getMessage(
            "rate.not.found.for.specified.service", MessageUtil.getLocale())));
      throw new ShiplinxException();
    }
    // ----------------------------------------------------------------

    cleanupInput(order);

    if (order.getCustomer() == null)
      order.setCustomer(customerService.getCustomerInfoByCustomerId(order.getCustomerId()));

    // If the credit card information has not been set on the payment page,
    // it means that either customer is a credit customer or has an active
    // cc profile on file, try to retrieve the active cc profile
    if (order.getCreditCard() == null && order.getCustomer().getCreditCardActive() != null) {
      order.setPaymentRequired(true);
      order.setCreditCard(order.getCustomer().getCreditCardActive());
    }

    // check if customer has crossed credit limit, if credit limit is set to
    // 0 means no limit
	long startTime1 = System.currentTimeMillis();
	if(order.getBillToType()!=null&&!order.getBillToType().equalsIgnoreCase(ShiplinxConstants.BILL_TO_THIRD_PARTY) && !order.getBillToType().equalsIgnoreCase(ShiplinxConstants.BILL_TO_COLLECT)){
    int unpaidInvoiceCount = customerService.findUnpaidInvoiceDuration(order.getCustomer().getId(),order.getCustomer().getHoldTerms());
    log.info("total unpaid invoice count "+unpaidInvoiceCount);
    long elapsedTimeSec1 = (System.currentTimeMillis() - startTime1) / 1000;
    log.info(" Total Elapsed Time for unpaidInvoiceCount (seconds):" + elapsedTimeSec1);
    if(order.getCustomer().getCreditLimit().doubleValue() > 0){
    	cur = this.customerService.getCreditUsageReport(order.getCustomerId(),
    			order.getCustomer().getBusinessId());
    if (cur.getTotalCreditUsed() > order.getCustomer().getCreditLimit().doubleValue() ) {
        errorMessages.add(new CarrierErrorMessage(order.getCarrierId(), MessageUtil.getMessage(
            "error.credit.overrun", MessageUtil.getLocale())));
        throw new CreditOverrunException(cur);
      }else if(unpaidInvoiceCount > 0){
    	errorMessages.add(new CarrierErrorMessage(order.getCarrierId(), MessageUtil.getMessage(
                "shippingOrder.unpaid.invoice.error", MessageUtil.getLocale())));
            throw new CreditOverrunException(cur);
      }
    double availableCredit = 0.0;
        	if(cur.getTotalCreditUsed() > 0){
                	availableCredit = order.getCustomer().getCreditLimit().doubleValue() - (cur.getTotalCreditUsed() + order.getTotalChargeQuoted()) ;
        	}else{
        	  	availableCredit = order.getCustomer().getCreditLimit().doubleValue() - order.getTotalChargeQuoted();
        	}
              this.customerService.updateAvailableCredit(availableCredit, order.getCustomerId());
        }else if(unpaidInvoiceCount > 0){
        	errorMessages.add(new CarrierErrorMessage(order.getCarrierId(), MessageUtil.getMessage(
                    "shippingOrder.unpaid.invoice.error", MessageUtil.getLocale())));
                throw new CreditOverrunException(cur);
        }
	}

    Carrier carrier = this.carrierServiceDAO.getCarrier(rate.getCarrierId());
    CarrierService carrierService = getCarrierServiceBean(carrier.getImplementingClass());
    if (!StringUtil.isEmpty(rate.getCurrency()))
      order.setCurrency(rate.getCurrency());
    else
      order.setCurrency(ShiplinxConstants.CURRENCY_CA_STRING); // hard-coding
    // for
    // now,
    // should
    // be
    // based
    // on
    // business
    // default

    // get customer Carreir for cust id and carrier id
    // CustomerCarrier carrierData =
    // getCarrierServiceDAO().getCutomerCarrierDefaultAccount(order.getCarrierId(),order.getCustomerId());

    // if (rate.getInstanceAPIName().indexOf(apiName) > 0) {
    // ship the order
    cs = carrierService;
    order.setCarrierId(rate.getCarrierId());
    order.setServiceName(rate.getServiceName());
    order.setServiceId(rate.getServiceId());
    order.setOriginalServiceId(rate.getOriginalServiceId());
    order.setService(getService(order.getServiceId()));
    order.setStatusId(new Long(ShiplinxConstants.STATUS_DISPATCHED));
    // order.getCharges().addAll(rate.getCharges());
		List<Charge> charges = rate.getCharges();
		List<Charge> tempCharge = new ArrayList<Charge>();
		for (Charge charge : charges) {

			CarrierChargeCode chargeGroupCode;
			if (rate.getCarrierId() == ShiplinxConstants.CARRIER_ESHIPPLUS) {
				chargeGroupCode = this.shippingService
						.getChargeByCarrierAndCodes(rate.getCarrierId(), null,
								charge.getChargeCodeLevel2());
			} else {
				chargeGroupCode = this.shippingService
						.getChargeByCarrierAndCodes(rate.getCarrierId(),
								charge.getChargeCode(),
								charge.getChargeCodeLevel2());
			}

			if (chargeGroupCode == null) {

				chargeGroupCode = this.shippingService
						.getChargeByChargeGroupId(rate.getCarrierId(),
								charge.getChargeGroupId());
			}
			double chargeValue = 0.0;
			double cost = 0.0;
			if (chargeGroupCode != null) {
				chargeValue = chargeGroupCode.getCharge();
				cost = chargeGroupCode.getCost();
			}

			if (chargeValue == 0.0) {

				charge.setCharge(charge.getCharge());
			} else {
				charge.setCharge(chargeValue);
			}
			if (cost == 0.0) {
				charge.setCost(charge.getCost());

			} else {
				charge.setCost(cost);
			}
			tempCharge.add(charge);
		}

		order.setCharges(tempCharge);
		// order.setCharges(rate.getCharges());
         
    order.setFuelPercent((float) rate.getFuel_perc());

    order.setEdiVerified(false);

    // order.setCustomerCarrier(rate.getCustomerCarrier());
    String[] pins = this.pinBlockManager.getNewPrefixedPinNumbers(
        ShiplinxConstants.PIN_TYPE_ORDER_NUMBERS, 1, order.getBusinessId());
    order.setOrderNum(pins[0]);

    // if customer account is inactive, do not allow shipment
    if (order.getCustomer().getActive() == false) {
      errorMessages.add(new CarrierErrorMessage(order.getCarrierId(), MessageUtil.getMessage(
          "error.account.notactive", MessageUtil.getLocale())));
      throw new ShiplinxException();
    }

    try {
      if (order.isPaymentRequired()) {
        CCTransaction transaction = this.creditCardService.authorizeCard(1.0,
            order.getCreditCard(), order.getCustomer());
        if (transaction == null)
          throw new CardProcessingException("");
        if (transaction.getStatus() == CCTransaction.DECLINED_STATUS)
          throw new CardProcessingException(transaction.getProcMessage());
      }
      // authorization went through, card is good

      long pickupId = 0;
      // schedule pick up if requested
      /*
       * if (order.getPickup() != null && order.getPickup().isPickupRequired()) {
       * copyFromOrderToPickup(order, rate); pickupId = createPickup(order.getPickup()); }
       */
      if (order.getCarrierId() != ShiplinxConstants.CARRIER_GENERIC) {
          carrierService.shipOrder(order, rate, rate.getCustomerCarrier());                  
        }
      // save the markup information
      order.setMarkPercent(rate.getMarkupPercentage());
      order.setMarkType(rate.getMarkupType());
      order.setQuotedWeight((float) rate.getBillWeight());
      order.setQuotedWeightUOM(rate.getBillWeightUOM());
      order.setTrackingURL(cs.getTrackingURL(order));

      // *FKhan* - 4 Jan. 2012 - LTL Per Pound Shipment
      if (rate.getRatedAsWeight() > 0)
        order.setRatedAsWeight(rate.getRatedAsWeight());
      // ---------------------------------------------------

      if (order.getId() != null && order.getId().longValue() > 0) {
        // Most likely this order is from Batch Shipment and it was
        // already saved
        // therefore order needs to be updated

        shippingService.update(order);
      } else {
        shippingService.save(order);
      }
      // if no trackin # is assigned, then use the order Id
      if (StringUtil.isEmpty(order.getMasterTrackingNum())) {
        order.setMasterTrackingNum(order.getId().toString());
      } else {
        order.setTrackingURL(carrierService.getTrackingURL(order));
      }
      shippingService.updateShippingOrder(order);

      order.setPaymentRequired(shippingService.isPaymentRequired(order));
      // if pickup was scheduled, then save order id in the pick up record

      pickupId = 0;
      // schedule pick up if requested
      if (order.getPickup() != null && order.getPickup().isPickupRequired()) {
    	  if(order.getCarrierId() != null && (order.getCarrierId() == 2 || order.getCarrierId() == 5)){
    		      		  if(order.getMasterTrackingNum() != null){
    		          		  order.getPickup().setMasterTrackingNum(order.getMasterTrackingNum());
    		      		  }
    		      	  }
        copyFromOrderToPickup(order, rate);
        order.getPickup().setCarrierAccount(rate.getCustomerCarrier());
        pickupId = createPickup(order.getPickup());
      }
      // if pickup was scheduled, then save order id in the pick up record
      if (pickupId > 0) {
        order.getPickup().setOrderId(order.getId());
        order.getPickup().setStatus(ShiplinxConstants.STATUS_PICKUP_ACTIVE);
        order.getPickup().setCarrierAccount(rate.getCustomerCarrier());
        pickupService.updatePickup(order.getPickup());
      }

      // shipment went through, charge card if needed
      if (order.isPaymentRequired()) {
        CCTransaction transaction = shippingService.processPayment(order, null);
        order.getCcTransactions().add(transaction);
      }
      // check if template is set in business, then only send mail if it
      // is set.
      /*if(order.getCustomer().isChbCustomer() && order.getFromAddress().getCountryCode() != order.getToAddress().getCountryCode()){*/
      if(order.getCustomer().isChbCustomer() && !(order.getFromAddress().getCountryCode().equals(order.getToAddress().getCountryCode()))){
    	  if(UserUtil.getMmrUser()==null && order.getBusiness()!=null){
    		      	      	 
    		      	      //Mail to internal CHB
    		      	     
    		      	      	  if(order.getToAddress().isSendNotification()||order.getFromAddress().isSendNotification())
    		      	      	    		      	      	  		      	      	  { 
    		      	      	   		      	      	    		      	      	boolean tadd=order.getToAddress().isSendNotification();
    		      	      	    		      	      	    		      	      	boolean fadd=order.getFromAddress().isSendNotification();
    		      	      	   		      	      	  		      	      		 order.getToAddress().setSendNotification(false);
    		      	      	    		      	      	  		      	      		  order.getFromAddress().setSendNotification(false);
    		      	      	   		      	      	  		      	      		shippingService.sendShipmentNotificationMail(order,order.getBusiness());
    		      	      	   		      	      	  		                                             int mailCount=2;
    		      	      	   		      	      	  			      					      	      	 ActionContext.getContext().getSession().put("MailCount",mailCount );
    		      	      	   		      	      	  	    		      	      	  		      	   
    		      	      	    		      	      	  		      	      		if(tadd)
    		      	      	    		      	      	  		      	      		 order.getToAddress().setSendNotification(true);
    		      	      	   		      	      	  		      	      		 if(fadd)
    		      	      	    		      	      	    		      	      		  order.getFromAddress().setSendNotification(true);
    		      	      	    		      	      	  		      	      		
    		      	      	    		      	      	  		      	      	  }
    		      	      	 //Mail to Ship from or Ship To customer
    		      	      	 shippingService.sendShipmentNotificationMail(order,order.getBusiness());
    		      	  }else if(UserUtil.getMmrUser()!=null){
    		      		 
    		      		//Mail to internal CHB
    		      	   		      
    		      		  if(order.getToAddress().isSendNotification()||order.getFromAddress().isSendNotification())
    		      			    		      					      	      	  {
    		      			    		      			    		      			boolean tadd=order.getToAddress().isSendNotification();
    		      			    		      			    		      	      	boolean fadd=order.getFromAddress().isSendNotification();
    		      			    		      			    		      			order.getToAddress().setSendNotification(false);
    		      			   		      					      	      		  order.getFromAddress().setSendNotification(false);
    		      			    		      					      	      		shippingService.sendShipmentNotificationMail(order,order.getBusiness());
    		      			    		      					      	               int mailCount=2;
    		      			    		      				    		      		 ActionContext.getContext().getSession().put("MailCount",mailCount );
    		      			    		      					      	      	if(tadd)
    		      			    		      			 		      	      		 order.getToAddress().setSendNotification(true);
    		      			   		      			 		      	      		 if(fadd)
    		      			    		      			   		      	      		  order.getFromAddress().setSendNotification(true);
    		      			    		      					      	      	  }
    		      		//Mail to Ship from or Ship To customer 
    		      		  		      		  shippingService.sendShipmentNotificationMail(order,UserUtil.getMmrUser().getBusiness());
    		      		     		      	
    		      	  }
    	        }else{
    	        	if(order.getCustomer().isChbCustomer() && (order.getFromAddress().getCountryCode().equals(order.getToAddress().getCountryCode()))){
    	        		    	        	 if(UserUtil.getMmrUser()==null && order.getBusiness()!=null){
    	        				      	      	  shippingService.sendShipmentNotificationMail(order,order.getBusiness());
    	        				      	      	 }else if(UserUtil.getMmrUser()!=null){
    	        				      		  shippingService.sendShipmentNotificationMail(order,UserUtil.getMmrUser().getBusiness());
    	        				      	                }  
    	        		    	         }else if(!(order.getCustomer().isChbCustomer())){
    	        		       
      Business business = businessService.getBusinessById(order.getBusinessId());
      if (!StringUtil.isEmpty(business.getShipOrderNotificationBody())) {
    	      	
    	                       sendOrderShippedEmailNotification(order);
    	      	                      }
	  
      }
    	        }
    } catch (ShiplinxException e) {
      log.error("Error in ship order!", e);
      if (order.getPickup().getPickupId() > 0) // pick up was scheduled,
        // need to cancel it
        try {
          cancelPickup(order.getPickup());
        } catch (Exception ex) {
        }
      for (String s : e.getErrorMessages()) {
        CarrierErrorMessage message = new CarrierErrorMessage(carrier.getId(), " : " + s);
        errorMessages.add(message);
      }
      throw e;
    } catch (Exception e) {
      log.debug("------------------Exception----------------", e);
      if (order.getPickup().getPickupId() > 0) // pick up was scheduled,
        // need to cancel it
        try {
          cancelPickup(order.getPickup());
        } catch (Exception ex) {
        }
      throw e;
    }

    if (order.getCarrierId() == ShiplinxConstants.CARRIER_GENERIC) {
      carrierService.shipOrder(order, rate, rate.getCustomerCarrier());
    }
    long elapsedTimeSec = (System.currentTimeMillis() - startTime) / 1000;
    log.info(" Total Elapsed Time for ship order  (seconds):" + elapsedTimeSec);
    return cs;
  }

  private boolean sendOrderShippedEmailNotification(ShippingOrder so) {
    boolean retval = true;
    String toAddress = null;
    int mailCount=0;
       /* if(so.getCustomer().isChbCustomer() && so.getFromAddress().getCountryCode() != so.getToAddress().getCountryCode()){
        	toAddress = "customsdistribution@integratedcarriers.com";
        }else if(so.getToAddress()!=null && so.getToAddress().isSendNotification()){*/
    if(so.getCustomer().isChbCustomer() && !(so.getFromAddress().getCountryCode().equals(so.getToAddress().getCountryCode()))){
    	        	//toAddress = "customsdistribution@integratedcarriers.com";
    	        //}else if(so.getToAddress()!=null && so.getToAddress().isSendNotification() && !(so.getFromAddress().getCountryCode().equals(so.getToAddress().getCountryCode()))){
    	toAddress = "customsdistribution@integratedcarriers.com";
    	mailCount=2;
    	    	        }else if(so.getToAddress()!=null && so.getToAddress().isSendNotification()&& so.getFromAddress() != null && !so.getFromAddress().isSendNotification()  ){
       		toAddress =  so.getToAddress().getEmailAddress();
        
  } else if (so.getFromAddress() != null && so.getFromAddress().isSendNotification()
		                  && so.getToAddress() != null && !so.getToAddress().isSendNotification()) {
		              toAddress = so.getFromAddress().getEmailAddress();
		            
		            } else if (so.getToAddress() != null && so.getFromAddress() != null
		                    && so.getToAddress().isSendNotification() && so.getFromAddress().isSendNotification()) {
		                toAddress = so.getFromAddress().getEmailAddress() + ";" + so.getToAddress().getEmailAddress();
		               
		              /*}else if(so.getCustomer().isChbCustomer() && so.getFromAddress().getCountryCode() != so.getToAddress().getCountryCode()){
		              	    	toAddress = "customsdistribution@integratedcarriers.com";*/
		                
		              }
		  
		     
		      
    if (toAddress == null || toAddress.length() == 0) {
      log.error("User's email address is not set, cannot send an shipment notification!");
      return false;
    }
    try {
      // GROUP_EMAIL_ADDRESS_en_CA
      // String locale = user.getLocale();

      String subject = MessageUtil.getMessage(so.getBusiness().getShipOrderNotificationSubject());
      
      BusinessDAO businessDAO=(BusinessDAO)MmrBeanLocator.getInstance().findBean("businessDAO");
      Business b=businessDAO.getBusiessById(so.getBusinessId());
      BusinessContact bc=businessDAO.getbusinessContactByBusiness(so.getBusinessId());
      subject = new String(subject.replaceAll("%COMPANYNAME", so.getFromAddress()
          .getAbbreviationName()));

      String body = MessageUtil.getMessage(so.getBusiness().getShipOrderNotificationBody());

      if (body == null || body.length() == 0) {
        log.error("Cannot find template to send order creation notification for customer "
            + so.getCustomer().getName());
        return false;
      }

      /*body = new String(body.replaceAll("%TOADDRESSNAME", so.getToAddress().getContactName()));
      body = new String(body.replaceAll("%FROMCOMPANYNAME", so.getFromAddress()
          .getAbbreviationName()));
      body = new String(body.replaceAll("%CARRIERNAME", so.getCarrierName()));
      if (!StringUtil.isEmpty(so.getReferenceCode()))
        body = new String(body.replaceAll("%SHIPMENTNO", "#" + so.getReferenceCode()));
      else
        body = new String(body.replaceAll("%SHIPMENTNO", "#" + String.valueOf(so.getId())));
      body = new String(body.replaceAll("%ORDERNO", String.valueOf(so.getId())));
      body = new String(body.replaceAll("%TOADD_ABBREVIATION", so.getToAddress()
          .getAbbreviationName()));
      body = new String(body.replaceAll("%SERVICENAME", so.getService().getName()));
      body = new String(body.replaceAll("%TOADD_ADDRESS1", so.getToAddress().getAddress1()));
      body = new String(body.replaceAll("%TOADD_ADDRESS2", so.getToAddress().getAddress2()));
      body = new String(body.replaceAll("%TOADD_CITY", so.getToAddress().getCity()));
      body = new String(body.replaceAll("%TOADD_PROVINCECODE", so.getToAddress().getProvinceCode()));
      body = new String(body.replaceAll("%TOADD_POSTALCODE", so.getToAddress().getPostalCode()));
      body = new String(body.replaceAll("%TOADD_COUNTRYCODE", so.getToAddress().getCountryCode()));
      body = new String(body.replaceAll("%TOADD_CONTACTNAME", so.getToAddress().getContactName()));
      body = new String(body.replaceAll("%TOADD_PHONENO", so.getToAddress().getPhoneNo()));
      body = new String(body.replaceAll("%CARRIER", so.getCarrierName()));
      body = new String(body.replaceAll("%SERVICE", so.getServiceName()));
      body = new String(body.replaceAll("%TRACKINGNUMBER", so.getMasterTrackingNum()));
      body = new String(body.replaceAll("%TRACKINGURL", "\"" + so.getTrackingURL() + "\""));
      body = new String(body.replaceAll("%BUSINESSLOGO", "\"" + so.getBusiness().getLogoURL()
          + "\""));*/
      
      body = new String(body.replaceAll("%BUSINESSCOLOR", b.getCssVO().getBarFirstColor()));
            body = new String(body.replaceAll("%BUSINESSABBRIVATION", bc.getBusinesssAbbrivation()));
            body = new String(body.replaceAll("%BUSINESSLOGOUTURL", b.getLogoutURL()));
           body = new String(body.replaceAll("%BUSINESSNAME", b.getName()));
            body = new String(body.replaceAll("%BUSINESSQUICKSTARTURL", bc.getQuickStartUrl()));
         body = new String(body.replaceAll("%BUSINESSADMINEMAIL", bc.getAdminEmail()));
	      //body = new String(body.replaceAll("%YEAR",  BusinessFilterUtil.getYear()));
		  body = new String (body.replaceAll("%FOOTER", b.getCssVO().getFooter1()));
      body = new String(body.replaceAll("%ATTENTION", "Customer"));
      body = new String(
          body.replaceAll(
              "%ShipDate",
              FormattingUtil.getFormattedDate(so.getScheduledShipDate(),
                  FormattingUtil.DATE_FORMAT_WEB) + ""));
      body = new String(body.replaceAll("%SFROMCOMPANY", so.getFromAddress().getAbbreviationName()));
      body = new String(body.replaceAll("%SFROMADDRESS1", so.getFromAddress().getAddress1()));
      body = new String(body.replaceAll("%SFROMADDRESS2%", so.getFromAddress().getAddress2()));
      body = new String(body.replaceAll("%SFROMZIP", so.getFromAddress().getPostalCode()));
      body = new String(body.replaceAll("%SFROMPROVINCE", so.getFromAddress().getProvinceCode()));
      body = new String(body.replaceAll("%SFROMCOUNTRY", so.getFromAddress().getCountryCode()));
      body = new String(body.replaceAll("%SFROMCITY", so.getFromAddress().getCity()));
      body = new String(body.replaceAll("%STOCOMPANY", so.getToAddress().getAbbreviationName()));
      body = new String(body.replaceAll("%STOADDRESS1", so.getToAddress().getAddress1()));
      body = new String(body.replaceAll("%STOADDRESS2%", so.getToAddress().getAddress2()));
      body = new String(body.replaceAll("%STOCITY", so.getToAddress().getCity()));
      body = new String(body.replaceAll("%STOZIP", so.getToAddress().getPostalCode()));
      body = new String(body.replaceAll("%STOPROVINCE", so.getToAddress().getProvinceCode()));
      body = new String(body.replaceAll("%STOCOUNTRY", so.getToAddress().getCountryCode()));
      body = new String(body.replaceAll("%CARRIERSERVICE", so.getCarrierName() + " "
          + so.getService().getName()));
      body = new String(body.replaceAll("%TOTALPIECES", so.getQuantity() + " Pcs"));
      body = new String(body.replaceAll("%TOTALWEIGHT", so.getRateList().get(0).getBillWeight()
          + " " + so.getBilledWeightUOM()));
      /*if (so.getTrackingURL() != null) {
        body = new String(body.replaceAll("%TRACKINGURL", so.getTrackingURL()));
      } else {
        body = new String(body.replaceAll("%TRACKINGURL", ""));
      }*/
      
      if (so.getMasterTrackingNum() != null && so.getTrackingURL()!= null) {
    	            // body = new String(body.replaceAll("%TRACKINGURL", so.getTrackingURL()));
    	         	  body = new String(body.replaceAll("%TRACKINGURL", "<a href="+so.getTrackingURL()+">"+so.getMasterTrackingNum()+"</a>"));//Change the tracking url to tracking number
    	           } else {
    	             body = new String(body.replaceAll("%TRACKINGURL", ""));
    	          }
      List<String> bccAddresses = new ArrayList<String>();
      // bccAddresses.add(user.getBusiness().getAddress().getEmailAddress());
      mailCount=0;

      retval = MailHelper.sendEmailNow2(so.getBusiness().getSmtpHost(), so.getBusiness()
          .getSmtpUsername(), so.getBusiness().getSmtpPassword(), so.getBusiness().getName(), so
          .getBusiness().getSmtpPort(), so.getBusiness().getAddress().getEmailAddress(), toAddress,
          bccAddresses, subject, body, null, true);
      if(retval==true)
    	      	        {
    	      	      	         mailCount++;
    	      	      	  ActionContext.getContext().getSession().put("MailCount",mailCount );
    	      	      	  
    	     	        }
      else
    	       {
    	     	  mailCount=5;
    	     	  ActionContext.getContext().getSession().put("MailCount",mailCount );
    	       }
    } catch (MessagingException e) {
      log.error("Error sending email - Messaging Exception: ", e);
      retval = false;
    } catch (Exception e) {
      log.error("Error sending email - Exception: ", e);
      retval = false;
    }
    return retval;
  }

  public List<CarrierService> getCarrierServices() {
    return carrierServices;
  }

  public void setCarrierServices(List<CarrierService> carrierServices) {
    this.carrierServices = carrierServices;
  }

  public List<CustomerCarrier> getCutomerCarrier(Long customerId) {
    return getCarrierServiceDAO().getCutomerCarrier(customerId);
  }

  public List<CustomerCarrier> getAllCutomerCarrier(long businessId, long customerId) {
    return getCarrierServiceDAO().getAllCutomerCarrier(businessId, customerId);
  }

  public List<CustomerCarrier> getAllCustomersCarrier(long businessId, long carrierId) {
    return getCarrierServiceDAO().getAllCustomersCarrier(businessId, carrierId);
  }

  public List<Carrier> getCarriersForBusiness(long businessId) {
	  List<Carrier> carriers=getCarrierServiceDAO().getCarriersForBusiness(businessId);
	  	  if((carriers==null || carriers.size()==0) &&  businessId>0){
	  		  carriers=BusinessFilterUtil.getCarriersForBusinessByBusinessLevel(businessId);
	  	  }
	      return carriers;
  }

  public CustomerCarrier getOrderCutomerCarrier(Long orderId) {
    return getCarrierServiceDAO().getOrderCutomerCarrier(orderId);
  }

  public CarrierServiceDAO getCarrierServiceDAO() {
    return carrierServiceDAO;
  }

  public void setCarrierServiceDAO(CarrierServiceDAO carrierServiceDAO) {
    this.carrierServiceDAO = carrierServiceDAO;
  }

  public List<Service> getServicesForCarrier(Long carrierId) {
    return getCarrierServiceDAO().getServicesForCarrier(carrierId);
  }

  public Service getService(Long serviceId) {
    return getCarrierServiceDAO().getService(serviceId);
  }

  public List<CarrierErrorMessage> getErrorMessages() {
    return errorMessages;
  }

  public void setErrorMessages(List<CarrierErrorMessage> errorMessages) {
    this.errorMessages = errorMessages;
  }

  /**
   * Shipping Label
   */
  public void getShippingLabel(ShippingOrder shippingOrder, OutputStream outputStream) {

    try {
    /*  // get the carrier available to customer
      // List<CustomerCarrier> customerCarrierList =
      // getCutomerCarrier(shippingOrder.getCustomerId());
      PDFRenderer pdfRenderer = new PDFRenderer();
      ArrayList<String> srcList = new ArrayList<String>();
      String shippingLabelFileName = pdfRenderer.getUniqueTempPDFFileName("ICBOL"
          + shippingOrder.getId());
      File fLabelPDF = new File(shippingLabelFileName);
      fLabelPDF.deleteOnExit();
      BufferedOutputStream labelBOS = new BufferedOutputStream(new FileOutputStream(fLabelPDF));

      errorMessages = new ArrayList<CarrierErrorMessage>();
      Carrier carrier = carrierServiceDAO.getCarrier(shippingOrder.getCarrierId());
      CustomerCarrier customerCarrier = getCarrierAccount(shippingOrder.getCustomerId(),
          shippingOrder.getBusinessId(), carrier.getId(), shippingOrder.getFromAddress()
              .getCountryCode(), shippingOrder.getToAddress().getCountryCode());
      // for(CustomerCarrier customerCarrier : customerCarrierList){
      CarrierService carrierService = getCarrierServiceBean(carrier.getImplementingClass());
      carrierService.generateShippingLabel(labelBOS, shippingOrder.getId(), customerCarrier);
      srcList.add(shippingLabelFileName);

      String customsInvoicePDF = pdfRenderer.getUniqueTempPDFFileName("customsInvoice"
          + shippingOrder.getId());
      if (getCustomsInvoice(shippingOrder, customsInvoicePDF)) {
        srcList.add(customsInvoicePDF);
      }
      pdfRenderer.concatPDF(srcList, outputStream);
      // carrierService.generateShippingLabel(outputStream,54);
*/  
    	boolean flag=false;
    	boolean flag1=false;
    	ArrayList<String> srcList = new ArrayList<String>();
    	        PDFRenderer pdfRenderer = new PDFRenderer();
    	        Business business = getBusinessDAO().getBusiessById(shippingOrder.getBusinessId());
    	  	      String reportPath = business.getReportPath();
    	  	      String shippingLabelFileName = pdfRenderer.getUniquePDFFileName(reportPath,"ICBOL_"
    	  	          + shippingOrder.getId()+"_");
    	  	    File fLabelPDF = new File(shippingLabelFileName);
    	  	      long id=shippingOrder.getId();
    	  	      File folderPath = new File(reportPath);
    	  	      if (folderPath.isDirectory()) {
    	  		   	  File[] fList = folderPath.listFiles();
    	  		    	  if(fList!=null){
    	  		    		  for(File file:fList){
    	  		    			  String fileName=file.getName();
    	  		    			  String[] splitFileName=fileName.split("_");
    	  		    			  if(id==Long.parseLong(splitFileName[1]) && splitFileName[0].equals("ICBOL")){
    	  		    				  flag=true;
    	  		    				  srcList.add(file.toString());
    	  		    				fLabelPDF = new File(file.toString());
    	  		    			  }
    	  		    		  }
    	  		    	  }
    	  	      }if(flag==false) {
    	  	      		  	fLabelPDF = new File(shippingLabelFileName);
    	  	                //fLabelPDF.deleteOnExit();
    	  	                BufferedOutputStream labelBOS = new BufferedOutputStream(new FileOutputStream(fLabelPDF));
    	  	                errorMessages = new ArrayList<CarrierErrorMessage>();
    	  	                Carrier carrier = carrierServiceDAO.getCarrier(shippingOrder.getCarrierId());
    	  	
    	  	                CustomerCarrier customerCarrier = getCarrierAccount(shippingOrder.getCustomerId(),
    	  	                		                      shippingOrder.getBusinessId(), carrier.getId(), shippingOrder.getFromAddress()
    	  	                		                          .getCountryCode(), shippingOrder.getToAddress().getCountryCode());
    	  	                // for(CustomerCarrier customerCarrier :
    	  	                // customerCarrierList){
    	  	                CarrierService carrierService = getCarrierServiceBean(carrier.getImplementingClass());
    	  	                carrierService.generateShippingLabel(labelBOS, shippingOrder.getId(), customerCarrier);
    	  	                srcList.add(shippingLabelFileName);
    	  	       }// carrierService.generateShippingLabel(outputStream,54);
    	  	       
    	  	      	String reportPathc = business.getReportPath();
    	  	      	String customsInvoicePDF = pdfRenderer.getUniquePDFFileName(reportPathc,"customsInvoice_"
    	  	  	          + shippingOrder.getId()+"_");
    	  	      	long id1=shippingOrder.getId();
    	  		      File folderPathc = new File(reportPathc);
    	  		      if (folderPathc.isDirectory()) {
    	  			   	  File[] fList = folderPathc.listFiles();
    	  			    	  if(fList!=null){
    	  			    		  for(File file:fList){
    	  			    			  String fileName=file.getName();
    	  			    			  String[] splitFileName=fileName.split("_");
    	  			    			  if(id1==Long.parseLong(splitFileName[1]) && splitFileName[0].equals("customsInvoice")){
    	  			    				  flag1=true;
    	  			    					  srcList.add(file.toString());
    	  			    			  }
    	  			    		  }
    	  			    	  }
    	  		      }if(flag1==false){
    	  		    		  if (getCustomsInvoice(shippingOrder, customsInvoicePDF)) {
    	  		    			  srcList.add(customsInvoicePDF);
    	  		    	  }
    	  		      }
    	  		 // if the label size is 0 then it should not store it
      		      if(fLabelPDF!=null && fLabelPDF.length() > 0){
    	    		pdfRenderer.concatPDF(srcList, outputStream);
      		      }else{
    	    		pdfRenderer.deleteFiles(srcList);
      		      }
             // carrierService.generateShippingLabel(outputStream,54);
    	    	                        		          
    } catch (ShiplinxException e) {
      log.error("-------LabelGenerationException----------", e);
    }

    catch (Exception e) {
      log.debug("-------Exception----" + e);
      e.printStackTrace();
    }

  }

  /**
   * Shipping Label
   */
  public void getShippingLabels(List<String> lstOrders, OutputStream outputStream, int scopies,
      int ccopies) {

    try {
    	boolean flag=false;
    	boolean flag1=false;
    	boolean customs=false;
    	    	boolean shippingLabel=false;
    	    	boolean summaryLabel=false;
    	    	int shippingCopies=0;
    	    	boolean flag2=false;
      ArrayList<String> srcList = new ArrayList<String>();
      User user1 = UserUtil.getMmrUser();
      
      UserService userService=(UserService)MmrBeanLocator.getInstance().findBean("userService");
      UserSearchCriteria criteria=new UserSearchCriteria();
           
      for (int i = 0; i < lstOrders.size(); i++) {

        if (lstOrders.get(i).length() > 0) {

          ShippingOrder shippingOrder = shippingService
              .getShippingOrder(new Long(lstOrders.get(i)));


					if (user1 == null && shippingOrder!=null) {
						criteria.setBusinessId(shippingOrder.getBusinessId());
						criteria.setCustomerId(shippingOrder.getCustomerId());
						List<User> users=userService.findUserByCustomer(criteria);
						if(users!=null && users.size()>0){
							user1=users.get(0);
						}else{
							user1=new User();
						}
					}

          
          if (shippingOrder.isPaymentRequired()) { // do not return
            // label if
            // payment not
            // captured
            log.warn("Payment required for order with id: " + shippingOrder.getId()
                + ". Not generating label!");
            continue;
          }

          // get the carrier available to customer
          // List<CustomerCarrier> customerCarrierList =
          // getCutomerCarrier(shippingOrder.getCustomerId());
          PDFRenderer pdfRenderer = new PDFRenderer();
         /* String shippingLabelFileName = pdfRenderer.getUniqueTempPDFFileName("ICBOL"
              + shippingOrder.getId());
          File fLabelPDF = new File(shippingLabelFileName);
          fLabelPDF.deleteOnExit();
          BufferedOutputStream labelBOS = new BufferedOutputStream(new FileOutputStream(fLabelPDF));

          errorMessages = new ArrayList<CarrierErrorMessage>();
          Carrier carrier = carrierServiceDAO.getCarrier(shippingOrder.getCarrierId());

          CustomerCarrier customerCarrier = getCarrierAccount(shippingOrder.getCustomerId(),
              shippingOrder.getBusinessId(), carrier.getId(), shippingOrder.getFromAddress()
                  .getCountryCode(), shippingOrder.getToAddress().getCountryCode());
          // for(CustomerCarrier customerCarrier :
          // customerCarrierList){
          CarrierService carrierService = getCarrierServiceBean(carrier.getImplementingClass());
          carrierService.generateShippingLabel(labelBOS, Long.valueOf(lstOrders.get(i)),
              customerCarrier);
          for (int s = 0; s < scopies; s++) // generate Shipping Label
          // for the no of copies
          // selected.
          {
            srcList.add(shippingLabelFileName);
          }

          String customsInvoicePDF = pdfRenderer.getUniqueTempPDFFileName("customsInvoice"
              + shippingOrder.getId());

          for (int c = 0; c < ccopies; c++) {
            if (getCustomsInvoice(shippingOrder, customsInvoicePDF)) {
              srcList.add(customsInvoicePDF);
            }
          }
          pdfRenderer.concatPDF(srcList, outputStream);

          // carrierService.generateShippingLabel(outputStream,54);
*/        
          Business business = getBusinessDAO().getBusiessById(shippingOrder.getBusinessId());
          	      String reportPath = business.getReportPath();
          	      String shippingLabelFileName = pdfRenderer.getUniquePDFFileName(reportPath,"ICBOL_"
          	          + shippingOrder.getId()+"_");
          	    File fLabelPDF = new File(shippingLabelFileName);
          	      long id=shippingOrder.getId();
          	      File folderPath = new File(reportPath);
          	      if (folderPath.isDirectory()) {
          		   	  File[] fList = folderPath.listFiles();
          		    	  if(fList!=null){
          		    		  for(File file:fList){
          		    			  String fileName=file.getName();
          		    			  String[] splitFileName=fileName.split("_");
          		    			  if(id==Long.parseLong(splitFileName[1]) && splitFileName[0].equals("ICBOL")){
          		    				if(shippingOrder.getCarrierId() == 20 
          		    						&& user1.getPreferredLabelSize().equals("8 x 11")) {
										if(file.length() > 0) {
											flag = true;
											shippingCopies = scopies;
											for (int s = 0; s < scopies; s++) {
												srcList.add(file.toString());
												fLabelPDF = new File(file.toString());
												shippingLabel = true;
											}
										} else 
											file.delete();
									} else {
          		    				  flag=true;
          		    				  shippingCopies=scopies;
          		    				  for (int s = 0; s < scopies; s++){
          		    					  srcList.add(file.toString());
          		    					fLabelPDF = new File(file.toString());
          		    					shippingLabel=true;
          		    				  }
          		    			  } 
          		    			  }
          		    		  }
          		    	  }
          	      }if(flag==false) {
          	      		  	fLabelPDF = new File(shippingLabelFileName);
          	                //fLabelPDF.deleteOnExit();
          	                BufferedOutputStream labelBOS = new BufferedOutputStream(new FileOutputStream(fLabelPDF));
          	                errorMessages = new ArrayList<CarrierErrorMessage>();
          	                Carrier carrier = carrierServiceDAO.getCarrier(shippingOrder.getCarrierId());
          	
          	                CustomerCarrier customerCarrier = getCarrierAccount(shippingOrder.getCustomerId(),
          	                		                      shippingOrder.getBusinessId(), carrier.getId(), shippingOrder.getFromAddress()
          	                		                          .getCountryCode(), shippingOrder.getToAddress().getCountryCode());
          	                // for(CustomerCarrier customerCarrier :
          	                // customerCarrierList){
          	                CarrierService carrierService = getCarrierServiceBean(carrier.getImplementingClass());
          	                carrierService.generateShippingLabel(labelBOS, Long.valueOf(lstOrders.get(i)), customerCarrier);
          	                
          	              /*condition to check is it purolator and it's label size is 8x11*/	
          	              if(shippingOrder.getCarrierId() == 20 && user1.getPreferredLabelSize().equals("8 x 11")) {
          	              File checkFile = new File(fLabelPDF.getAbsolutePath());
          	              if(checkFile.length() <= 0)
          	              checkFile.delete();
          	              }
          	              shippingCopies=scopies;
          	                for (int s = 0; s < scopies; s++) // generate Shipping Label
                          // for the no of copies
          	                // selected.
          	                {
          	                	srcList.add(shippingLabelFileName);
          	                	shippingLabel=true;
          	                }
          	                		                	          	  
          	       }// carrierService.generateShippingLabel(outputStream,54);
          	       
          	      	String reportPathc = business.getReportPath();
          	      	String customsInvoicePDF = pdfRenderer.getUniquePDFFileName(reportPathc,"customsInvoice_"
          	  	          + shippingOrder.getId()+"_");
          	      	long id1=shippingOrder.getId();
          		      File folderPathc = new File(reportPathc);
          		      if (folderPathc.isDirectory()) {
          			   	  File[] fList = folderPathc.listFiles();
          			    	  if(fList!=null){
          			    		  for(File file:fList){
          			    			  String fileName=file.getName();
          			    			  String[] splitFileName=fileName.split("_");
          			    			  if(id1==Long.parseLong(splitFileName[1]) && splitFileName[0].equals("customsInvoice")){
          			    				  flag1=true;
          			    				  for (int c = 0; c < ccopies; c++) {
          			    					  srcList.add(file.toString());
          			    					  customs=true;
          			    				  }
          			    			  }
          			    		  }
          			    	  }
          		      }if(flag1==false){
          		    	  for (int c = 0; c < ccopies; c++) {
          		    		  if (getCustomsInvoice(shippingOrder, customsInvoicePDF)) {
          		    			  srcList.add(customsInvoicePDF);
          		    			customs=true;
          		    		  }
          		    	  }
          		      }
          		      
          		    /*if(((customs==true&&shippingLabel==true)||(shippingLabel==true&&customs==false)) && shippingOrder!=null && shippingOrder.getCarrierId()!=null && (shippingOrder.getCarrierId()==ShiplinxConstants.CARRIER_FEDEX || shippingOrder.getCarrierId()==ShiplinxConstants.CARRIER_UPS || shippingOrder.getCarrierId()==ShiplinxConstants.CARRIER_PUROLATOR || shippingOrder.getCarrierId()==ShiplinxConstants.CARRIER_DHL))
          		    	          		      {
          		    	          		    String reportPathc1 = business.getReportPath();
          		    	          	      	String summaryPDF = pdfRenderer.getUniquePDFFileName(reportPathc1,"summary_"
          		    	          	  	          + shippingOrder.getId()+"_");
          		    	          	      	long id2=shippingOrder.getId();
          		    	          		      File folderPathc1 = new File(reportPathc1);
          		    	          		      if (folderPathc1.isDirectory()) {
          		    	          			   	  File[] fList = folderPathc1.listFiles();
          		    	          			    	  if(fList!=null){
          		    	          			    		  for(File file:fList){
          		    	          			    			  String fileName=file.getName();
          		    	          			    			  String[] splitFileName=fileName.split("_");
          		    	          			    			  if(id2==Long.parseLong(splitFileName[1]) && splitFileName[0].equals("summary")){
          		    	          			    				  flag1=true;
          		    	          			    				summaryLabel=true;
          		    	          			    					  srcList.add(file.toString());
          		    	          			    			  }
          		    	          			    		  }
          		    	          			    	  }
          		    	          		      }if(flag1==false){
          		    	          		    	BusinessFilterUtil.reloadUser( UserUtil.getMmrUser().getUsername());
          		    	          		    	User user1 = UserUtil.getMmrUser();
          		    	          		    		if(user1.isSummaryLabel() && !shippingOrder.getCarrierName().equalsIgnoreCase(ShiplinxConstants.CARRIER_GENERIC_STRING)){
          		    	          		    		  if (getSummaryLabel(shippingOrder,summaryPDF,outputStream,user1)) {
          		    	          		    			  srcList.add(summaryPDF);
          		    	          		    			summaryLabel=true;
          		    	          		    		  }
          		    	          		    		}
          		    	          		      }
          		    	          		      }
          		    	          		    User user1 = UserUtil.getMmrUser();
          		    	          		    if(user1.isSummaryLabel() && !shippingOrder.getCarrierName().equalsIgnoreCase(ShiplinxConstants.CARRIER_GENERIC_STRING)){
          		    	    			    	  getSummaryLabel(shippingOrder,shippingLabelFileName,outputStream,user1);
          		    	    			    	  srcList.add(shippingLabelFileName);
          		    	          		    } 	
          		    	          		      
          		    	          		      // if the label size is 0 then it should not store it
          		    	          		    
          		    	          		    if(customs==true&&shippingLabel==true&&summaryLabel==true)
          		    	          		    {
          		    	          		    	
          		    	          		    	Collections.swap(srcList, shippingCopies, srcList.size()-1);
          		    	          		    }*/
          		      
          		    if(((customs==true&&shippingLabel==true)||(shippingLabel==true&&customs==false)) && shippingOrder!=null && shippingOrder.getCarrierId()!=null && (shippingOrder.getCarrierId()==ShiplinxConstants.CARRIER_FEDEX || shippingOrder.getCarrierId()==ShiplinxConstants.CARRIER_UPS || shippingOrder.getCarrierId()==ShiplinxConstants.CARRIER_PUROLATOR || shippingOrder.getCarrierId()==ShiplinxConstants.CARRIER_DHL))
          		    	          		             		      {
          		    	          		              		    String reportPathc1 = business.getReportPath();
          		    	          		              	      	String summaryPDF = pdfRenderer.getUniquePDFFileName(reportPathc1,"summary_"
          		    	          		              	  	          + shippingOrder.getId()+"_");
          		    	          		              	      	long id2=shippingOrder.getId();
          		    	          		              		      File folderPathc1 = new File(reportPathc1);
          		    	          		              		      if (folderPathc1.isDirectory()) {
          		    	          		             			   	  File[] fList = folderPathc1.listFiles();
          		    	          		              			    	  if(fList!=null){
          		    	          		              			    	 if( UserUtil.getMmrUser()!=null)
          		    	          		              			    		BusinessFilterUtil.reloadUser( UserUtil.getMmrUser().getUsername());
          		    	          		              			    		  for(File file:fList){
          		    	          		              			    			  String fileName=file.getName();
          		    	          		              			    			  String[] splitFileName=fileName.split("_");
          		    	          		              			    			  if(id2==Long.parseLong(splitFileName[1]) && splitFileName[0].equals("summary")){
          		    	          		              			    				if(user1.isSummaryLabel())
          		    	          		              			    				{          			    				  
          		    	          		              			    				flag2=true;
          		    	          		             			    				summaryLabel=true;
          		    	          		              			    					  srcList.add(file.toString());
          		    	          		              			    				}
          		    	          		              			    				else
          		    	          		              			    				{
          		    	          		              			    				summaryLabel=false;
          		    	          		              			    			    srcList.remove(file.toString());
          		    	          		              			    			flag2=true;
          		    	          		              			    				}
          		    	          		              			    			  }
          		    	          		              			    		  }
          		    	          		             			    	  }
          		    	          		              		      }if(flag2==false){
          		    	          		              		    if(UserUtil.getMmrUser()!=null)
          		    	          		              		    	BusinessFilterUtil.reloadUser( UserUtil.getMmrUser().getUsername());
          		    	          		              		        if(user1!=null && user1.isSummaryLabel() && !shippingOrder.getCarrierName().equalsIgnoreCase(ShiplinxConstants.CARRIER_GENERIC_STRING)){
          		    	          		              		    		  if (getSummaryLabel(shippingOrder,summaryPDF,outputStream,user1)) {
          		    	          		             		    			  srcList.add(summaryPDF);
          		    	          		              		    			summaryLabel=true;
          		    	          		              		    		  }
          		    	          		              		    		}
          		    	          		             		      }
          		    	          		              		      }
          		    	          		              		    /*User user1 = UserUtil.getMmrUser();
          		    	          		              		    if(user1.isSummaryLabel() && !shippingOrder.getCarrierName().equalsIgnoreCase(ShiplinxConstants.CARRIER_GENERIC_STRING)){
          		    	          		        			    	  getSummaryLabel(shippingOrder,shippingLabelFileName,outputStream,user1);
          		    	          		        			    	  srcList.add(shippingLabelFileName);
          		    	          		              		    } 	
          		    	          		              		      */
          		    	          		              		      // if the label size is 0 then it should not store it
          		    	          		              		    
          		    	          		             		    if(customs==true&&shippingLabel==true&&summaryLabel==true)
          		    	          		              		    {
          		    	          		              		    	
          		    	          		              		    	Collections.swap(srcList, shippingCopies, srcList.size()-1);
          		    	          		              		    }
          		    	          		 // if the label size is 0 then it should not store it
          		    	          		             		if((fLabelPDF!=null && fLabelPDF.length() > 0)||customs){
		    		pdfRenderer.concatPDF(srcList, outputStream);
		    	}else{
		    		pdfRenderer.deleteFiles(srcList);
		    	}
                    		                	          	  
           // carrierService.generateShippingLabel(outputStream,54);
          
        }// End of If
      }// End of for
    } catch (ShiplinxException e) {
      log.error("-------LabelGenerationException----------", e);
    }

    catch (Exception e) {
      log.debug("-------Exception----" + e);
      e.printStackTrace();
    }

  }

  /**
   * Shipping Label
   */
  private boolean getCustomsInvoice(ShippingOrder shippingOrder, String fileName) {

    CustomsInvoice ci = shippingService.getCustomsInvoiceByOrderId(shippingOrder.getId());
    if (ci == null)
      return false;
    shippingOrder.setCustomsInvoice(ci);
    Business business = businessService.getBusinessById(shippingOrder.getBusinessId());
    try {
      InputStream stream = this
          .getClass()
          .getClassLoader()
          .getResourceAsStream(
              "./jasperreports/src/main/java/com/meritconinc/shiplinx/service/impl/jasperreports/CustomsInvoice.jasper");
      JasperReport jasperReport = (JasperReport) JRLoader.loadObject(stream);
      String buyerAddressId = String.valueOf(shippingOrder.getCustomsInvoice().getBuyerAddressId());
      Address buyerAddress = addressService.findAddressById(buyerAddressId);
      String consigneeTaxId = shippingOrder.getToAddress().getTaxId();
      Map parameters = new HashMap();
      parameters.put("CustomsInvoice", ci);
      parameters.put("ShippingOrder", shippingOrder);
      parameters.put("BuyerAddress", buyerAddress);
      parameters.put("ConsigneeTaxId", consigneeTaxId);
      String logoPath = "/images/" + business.getLogoFileName();
      String logo2Path = "/images/" + business.getLogoHiResFileName();
      URL logo = (InvoiceManagerImpl.class.getResource(logoPath));
      URL logo2 = (InvoiceManagerImpl.class.getResource(logo2Path));

      parameters.put("logo", logo);
      parameters.put("logo2", logo2);

      JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(shippingOrder
          .getCustomsInvoice().getProducts());

      JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, ds);

      JasperExportManager.exportReportToPdfFile(jasperPrint, fileName);
      return true;

    } catch (Exception e) {
      log.error("Could not generate Shiplinx Invoice Main !!", e);
      return false;
    }

  }

  protected void cleanupInput(ShippingOrder order) {
    // make sure that for each of the packages, the length is set as the
    // dimension of the longest side
    List<com.meritconinc.shiplinx.model.Package> packages = (List<com.meritconinc.shiplinx.model.Package>) order
        .getPackages();

    order.setBillingStatus(ShiplinxConstants.BILLING_STATUS_READY_TO_INVOICE); // if
    // the
    // shipment
    // is
    // created
    // on
    // the
    // system,
    // then
    // this
    // value
    // is
    // pre-set
    // so
    // when
    // edi
    // processing
    // occurs
    // this
    // field
    // does
    // not
    // need
    // to
    // be
    // set
    order.setInsuranceValue(0f);
    order.setCODValue(0d);
    if (!(order.getFromAddress().getCountryCode().equalsIgnoreCase(ShiplinxConstants.CANADA) || order
        .getFromAddress().getCountryCode().equalsIgnoreCase(ShiplinxConstants.US))) {
      order.setDimType(ShippingOrder.DIM_TYPE_METRIC);
    }

    // if(order.getPackageTypeId().getPackageTypeId() ==
    // ShiplinxConstants.PACKAGE_TYPE_PALLET)
    // return;

    for (com.meritconinc.shiplinx.model.Package p : packages) {

    	 if (p.getCodAmount() == null)
       	  p.setCodAmount(new BigDecimal(0.0));
         if (p.getInsuranceAmount() == null)
       	  p.setInsuranceAmount(new BigDecimal(0.0));

      order.setInsuranceValue(order.getInsuranceValue() + p.getInsuranceAmount().floatValue());
      order.setCODValue(order.getCODValue() + p.getCodAmount().floatValue());

      p.setLength(p.getLength().setScale(2, BigDecimal.ROUND_UP));
      p.setHeight(p.getHeight().setScale(2, BigDecimal.ROUND_UP));
      p.setWidth(p.getWidth().setScale(2, BigDecimal.ROUND_UP));
      // p.setCodAmount(p.getCodAmount().setScale(1,
      // BigDecimal.ROUND_UNNECESSARY));
      // p.setInsuranceAmount(p.getInsuranceAmount().setScale(1,
      // BigDecimal.ROUND_UNNECESSARY));

      // do not arrange the dims if type is pallet.
      if (order.getPackageTypeId().getPackageTypeId() != ShiplinxConstants.PACKAGE_TYPE_PALLET) {
        double length = p.getLength().doubleValue();
        double height = p.getHeight().doubleValue();
        double width = p.getWidth().doubleValue();
        if (length < height) {
          double temp = length;
          p.setLength(new BigDecimal(height).setScale(2, BigDecimal.ROUND_UP));
          p.setHeight(new BigDecimal(temp).setScale(2, BigDecimal.ROUND_UP));
          length = p.getLength().doubleValue();
        }
        if (length < width) {
          double temp = length;
          p.setLength(new BigDecimal(width).setScale(2, BigDecimal.ROUND_UP));
          p.setWidth(new BigDecimal(temp).setScale(2, BigDecimal.ROUND_UP));
        }
      }
      p.setBilledWeight(p.getWeight().floatValue());
    }

    if (order.getQuantity() == null || order.getQuantity().intValue() <= 0)
      order.setQuantity(packages.size());

    if (order.getFromAddress().getPostalCode() != null)
      order.getFromAddress().setPostalCode(
          (order.getFromAddress().getPostalCode().replaceAll(" ", "")));
    if (order.getToAddress().getPostalCode() != null)
      order.getToAddress()
          .setPostalCode((order.getToAddress().getPostalCode().replaceAll(" ", "")));

    if (order.getFromAddress().getPhoneNo() != null)
      order.getFromAddress().setPhoneNo(
          order.getFromAddress().getPhoneNo().replaceAll("[^\\d]", ""));
    if (order.getToAddress().getPhoneNo() != null)
      order.getToAddress().setPhoneNo(order.getToAddress().getPhoneNo().replaceAll("[^\\d]", ""));

  }

  private void processRates(List<Rating> ratingList, ShippingOrder order, long shipServiceId) {

    // List<Integer> disabledServices = new ArrayList<Integer>();
    int index = 0;
    List<Rating> ratesToRemove = new ArrayList<Rating>();
    boolean isICRequestQuote = false;
    boolean errorMsg = false;
    boolean isRateAvailable = false;

    boolean isLTLRateAvailable = false;
    // List<Rating> rateTotal = new ArrayList<Rating>();
    for (Rating rate : ratingList) {
      log.debug("Rating List Service Id" + rate.getServiceId());
      log.debug("Rating List Carrier Id" + rate.getCarrierId());
      // if this is a new service then add it first
      if (rate.getNewService() != null) {
        long serviceId = addNewService(rate.getNewService());
        rate.setServiceId(serviceId);
      }

      if (shipServiceId != -1 && rate.getServiceId() != shipServiceId) {
        // - Loop throuh all the rates and return only the one where the
        // service id matches
        log.debug("Rate with index " + index + " and service " + rate.getServiceName()
            + " not being requested for customer " + order.getCustomer().getName() + " / "
            + order.getCustomerId());
        ratesToRemove.add(rate);
        continue;
      }

      // if the shipment is a "LTL PER SKID" service, then markup should
      // be based on # of skids.
      // Using the same weight fields to store skid range
      Service service=null;
      for(Service serviceTmp:listOfAllServices){
    	  if(rate.getServiceId()==serviceTmp.getId()){
    		  service=serviceTmp;
    	  }
      }
      if(service==null){
    	  service = carrierServiceDAO.getService(rate.getServiceId());
      }
      log.debug("Service ID 1" + service.getId());
      if (service != null && service.getServiceType() == ShiplinxConstants.SERVICE_TYPE_LTL_SKID) {
        log.debug("Applying markup based on # of skids: " + order.getPackages().size());
        order.setWeightToMarkup((double) order.getPackages().size());
      } else
        order.setWeightToMarkup(rate.getBillWeight());
      // mark all disabled services
      Markup markup = markupManagerService.getMarkupObj(order);
      markup.setServiceId(rate.getServiceId());
      markup = markupManagerService.getUniqueMarkup(markup);

      if (markup == null && service.getMasterCarrierId() == ShiplinxConstants.CARRIER_GENERIC
          && service.getMasterServiceId() > 0 && service.getMasterServiceId() != null
          && rate.getCharges().size() > 0) {
        int count = 0;
        for (Rating rates : ratingList) {
          if (rates.getServiceId() == service.getMasterServiceId()
              && rates.getCharges().size() == 0) {
            rates.setCharge(rate.getCharge());
            rates.setCharges(rate.getCharges());
            rates.setBillWeight(rate.getBillWeight());
            rates.setTotal(rate.getTotal());
            rates.setTotalCost(rate.getTotalCost());
            rates.setTransitDays(rate.getTransitDays());
            rates.setTransitDaysMin(rate.getTransitDaysMin());
          } else if (rates.getServiceId() != service.getMasterServiceId()
              && rates.getCharges().size() > 0) {
            count++;
          }
        }
        if (count == ratingList.size() && service.getMasterServiceId() != null) {
        	Service findService=null;
        	for(Service serviceTmp:listOfAllServices){
        		if(service.getMasterServiceId()==serviceTmp.getId()){
        			findService=serviceTmp;
        		}
        	}
        	if(findService==null){
        		findService = carrierServiceDAO.getService(service.getMasterServiceId());
        	}
          Markup markups = markupManagerService.getMarkupObj(order);
          markups.setServiceId(findService.getId());
          markup = markupManagerService.getUniqueMarkup(markups);
          rate.setCarrierId(findService.getCarrierId());
          rate.setCarrierName(findService.getCarrier().getName());
          // rates.setMarkupFlat(markup.getMarkupFlat());
          // rates.setMarkupPercentage(markup.getMarkupPercentage());
          rate.setServiceId(findService.getId());
          rate.setServiceName(findService.getName());
          rate.setSlaveServiceId(service.getId());
          rate.setZoneStructureId(service.getZoneStructureId());
          /*
           * rates.setBillWeight(rate.getBillWeight()); rates.setTotal(rate.getTotal());
           * rates.setTotalCost(rate.getTotalCost()); rates.setTransitDays(rate.getTransitDays());
           * rates.setTransitDaysMin(rate.getTransitDaysMin());
           */
        }
      }
      if (markup == null || markup.isDisabledFlag()) {
        log.debug("Rate with index " + index + " and service " + rate.getServiceName()
            + " disabled or mark up record not found for customer " + order.getCustomer().getName()
            + " / " + order.getCustomerId());

        // disabledServices.add(index);
        // As per JQ Mar 29, 2013. If shipping TP or Collect, then
        // disabled should not matter
        if (StringUtil.isEmpty(order.getBillToAccountNum()))
          ratesToRemove.add(rate);
        else
          rate.setCharges(new ArrayList<Charge>()); // remove all the
        // charges if
        // this is TP or
        // Collect
        continue;
      }

      shippingService.applyAdditionalHandling(order, rate, ShiplinxConstants.CHARGE_TYPE_QUOTED);
     rate.setMarkupPercentage(markup.getMarkupPercentage());
     rate.setMarkupFlat(markup.getMarkupFlat());
      rate.setMarkupTypeText(markup.getTypeText());
      rate.setMarkupType(markup.getType());
      /*if (markup != null) {
    	            rate.setMarkupPercentage(markup.getMarkupPercentage());
    	            rate.setMarkupTypeText(markup.getTypeText());
    	            rate.setMarkupType(markup.getType());
    	          } else {
    	            rate.setMarkupPercentage(0);
    	          }
    	          if (markup.getMarkupPercentage() == 0
    	                  && markup.getMarkupFlat() != 0) {                 	             
    	             	  rate.setMarkupFlat(markup.getMarkupFlat());          	                  
    	                rate.setMarkupTypeText("Flat");
    	              }*/
      rate.setVariable(markup.getVariable());
      // Issue No:112
      /*
       * if(markup.getServiceType() == SERVICE_TYPE_LTL_SKID) {
       * if((markup.getMarkupPercentage()!=0)||(markup.getMarkupPercentage()==0 &&
       * markup.getMarkupFlat()==0) ){ rate.setMarkupPercentage(markup.getMarkupPercentage());
       * rate.setMarkupTypeText(markup.getTypeText()); rate.setMarkupType(markup.getType()); }else
       * if(markup.getMarkupFlat()!=0){ rate.setMarkupPercentage(markup.getMarkupFlat().intValue());
       * rate.setMarkupTypeText("Flat"); rate.setMarkupType(markup.getType()); } }else{
       * rate.setMarkupPercentage(markup.getMarkupPercentage());
       * rate.setMarkupTypeText(markup.getTypeText()); rate.setMarkupType(markup.getType()); }
       */
      // End

      rate.setTotal(0);
      rate.setTotalCost(0);
      /*if (StringUtil.isEmpty(rate.getCarrierName()))
        rate.setCarrierName(this.carrierServiceDAO.getCarrierByBusiness(rate.getCarrierId(),
            order.getBusinessId()).getName());
*/
      
      if (StringUtil.isEmpty(rate.getCarrierName())){
    	      	      	      	  Carrier carrier=this.carrierServiceDAO.getCarrierByBusiness(rate.getCarrierId(),
    	      	      	      	            order.getBusinessId());
    	      	      	      	  if(carrier==null){
    	      	      	      		carrier=BusinessFilterUtil.getCarrierByBusinessFromSuperBusiness(rate.getCarrierId(), order.getBusinessId()); 
    	      	      	      	  }
    	      	      	          rate.setCarrierName(carrier.getName());
    	      	      	        }
    	   
      List<Charge> removeCharge = new ArrayList<Charge>();
      if (StringUtil.isEmpty(rate.getServiceName()))
        rate.setServiceName(shippingService.getServiceById(rate.getServiceId()).getName());
      order.setServiceId(rate.getServiceId());
      for (Charge c : rate.getCharges()) {
    	  if (order.getFromAddress().getCountryCode().equalsIgnoreCase(ShiplinxConstants.CANADA)
    			      			   && order.getToAddress().getCountryCode().equalsIgnoreCase(ShiplinxConstants.CANADA) && c.getChargeCode().equalsIgnoreCase(ShiplinxConstants.TAX_TAX) && rate.getCarrierId()==ShiplinxConstants.CARRIER_PUROLATOR_FREIGHT){
    			      			   removeCharge.add(c);
    			      			   continue;
    			      			   }
        c.setStatus(ShiplinxConstants.CHARGE_QUOTED);
        // log.info("Looking up charge for carrier/code/code2: " +
        // rate.getCarrierId() + " / " + c.getChargeCode() + " / " +
        // c.getChargeCodeLevel2());
        /* CarrierChargeCode chargeGroupCode = this.shippingService.getChargeByCarrierAndCodes(
            rate.getCarrierId(), c.getChargeCode(), c.getChargeCodeLevel2());
        // this should really return only 1 row
      ///Written By Mohan
        if(chargeGroupCode==null){
        	
        	chargeGroupCode = this.shippingService.getChargeByChargeGroupId(
                    rate.getCarrierId(), c.getChargeGroupId());
        }
        */
        CarrierChargeCode chargeGroupCode;
                              if(rate.getCarrierId() == ShiplinxConstants.CARRIER_ESHIPPLUS ){
                                 chargeGroupCode = this.shippingService.getChargeByCarrierAndCodes(
                                    rate.getCarrierId(), null, c.getChargeCodeLevel2());
                        	 if(chargeGroupCode == null && c.getChargeCode()!= null && c.getChargeCode().equalsIgnoreCase(ShiplinxConstants.FUEL_SURCHARGE)){
                                	 chargeGroupCode = this.shippingService.getChargeByCarrierAndCodes(
                                             rate.getCarrierId(), c.getChargeCode(), null);
                                 }
                                }else{
                                	int k=shippingService.checkAccessorial(c.getChargeCodeLevel2());
                                if(k>0)
                                	chargeGroupCode = this.shippingService.getChargeByCarrierAndCodes(
                                			                                    /*rate.getCarrierId(), c.getChargeCode(), c.getChargeCodeLevel2());*/
                                			c.getCarrierId(), c.getChargeCode(), c.getChargeCodeLevel2());
                                else{
                                	                                		chargeGroupCode = this.shippingService.getChargeByCarrierAndCodes(
                                	                                				rate.getCarrierId(), c.getChargeCode(), c.getChargeCodeLevel2());
                                	                                	} 
                                	                                	
                                	                                }
                                			                        
                 
        ///End
        if (chargeGroupCode != null
            && (chargeGroupCode.getGroupCode()
                .equalsIgnoreCase(ShiplinxConstants.GROUP_FUEL_CHARGE) || chargeGroupCode
                .getGroupCode().equalsIgnoreCase(ShiplinxConstants.GROUP_FREIGHT_CHARGE))) {
          // String typeText=rate.getMarkupTypeText();
        	order.setBilledWeight((float)rate.getBillWeight());
          c.setCharge(markupManagerService.applyMarkup(order, c, false));
          if(rate.getMarkupFlat() > 0 && (rate.getMarkupPercentage() == null 
        		          		  || rate.getMarkupPercentage() == 0)){
        	  if(c.getChargeCode().equalsIgnoreCase("FRT")||c.getChargeCode().equalsIgnoreCase("050"))
        		       	  {
        		  	          double ch = c.getCharge() + rate.getMarkupFlat();
        		  	          c.setCharge(ch);
        		       	}
        	          	  else{
        	          		  c.setCharge(c.getCharge());
        	          	  }
        		            }
          /*Markup searchMarkup = markupManagerService.getMarkupObj(order);
                    if(searchMarkup!=null && rate.getCarrierId()==ShiplinxConstants.CARRIER_GENERIC){
                        searchMarkup.setServiceId(service.getMasterServiceId());  
                        Markup baseMarkup=markupManagerService.findBaseMarkup(searchMarkup);
                        double baseAmount=0;
                        boolean baseMarkupFlatText=false;
                                    if(baseMarkup==null){
                                    	Markup mark=searchMarkup;
                                    	mark.setCustomerId(0l);
                                   	baseMarkup=markupManagerService.findBaseMarkup(mark);
                                    }
                                    if (baseMarkup!=null && baseMarkup.getMarkupPercentage() == 0
                                            && baseMarkup.getMarkupFlat() != 0) {  
                                  	  double baseFlat=baseMarkup.getMarkupFlat();
                                  	  baseAmount=(FormattingUtil.add(c.getCost().doubleValue(),baseFlat)).doubleValue();
                                        }else{
                                      	  if(baseMarkup!=null){
                                      	  double baseMarkupAmt = (FormattingUtil.subtract(c.getCost().floatValue(),
                                                    c.getStaticAmount())).doubleValue()
                                                    * baseMarkup.getMarkupPercentage() / 100;
                                      	  baseAmount = (FormattingUtil.add(c.getCost().doubleValue(),baseMarkupAmt)).doubleValue();
                                      	  }
                                        }
                                 if(c.getCharge()<baseAmount){
                              	   c.setCharge(baseAmount);
                              	   if (baseMarkup!=null && baseMarkup.getMarkupPercentage() == 0
                                             && baseMarkup.getMarkupFlat() != 0) { 
                              		   rate.setMarkupTypeText("Flat");
                              		   rate.setMarkupFlat(baseMarkup.getMarkupFlat());
                              	   }else{
                              		   rate.setMarkupPercentage(baseMarkup.getMarkupPercentage());
                              		   rate.setMarkupTypeText(baseMarkup.getTypeText());
                              	   }
                                 }
                        
                       }*/
        } else {
          if (!(PurolatorAPI.SPECIAL_HANDLING_CODE).equals(c.getChargeCode())) {
        	  if (c.getTariffRate() != null && c.getTariffRate()>0)
              c.setCharge(c.getTariffRate());
            else
              c.setCharge(c.getCost());
          }
        }
        double charge=0.0;
        double cost=0.0;
        if (chargeGroupCode != null){
					c.setDisplayOrder(chargeGroupCode.getDisplayOrder());
					charge = chargeGroupCode.getCharge();
					cost = chargeGroupCode.getCost();
                          }
        else {
          log.error("Could not set display order for this charge!");
          c.setDisplayOrder(Integer.MAX_VALUE); // this shouldn't
          // happen, but if it
          // does then set the
          // value to high
          // number
        }
        /*  c.setCharge(FormattingUtil.roundFigureRates(c.getCharge(), 2));
                        +        c.setCost(FormattingUtil.roundFigureRates(c.getCost(), 2));*/
                                
                                if(charge==0.0){
                               	 c.setCharge(FormattingUtil.roundFigureRates(c.getCharge(), 2));
                                 }else{
                                	 c.setCharge(FormattingUtil.roundFigureRates(charge, 2));
                                 }
                                 if(cost==0.0){
                               	 c.setCost(FormattingUtil.roundFigureRates(c.getCost(), 2));
                                 }else{
                        
                                	 c.setCost(FormattingUtil.roundFigureRates(cost, 2));
                                 }
                              
                        
                              /*  if (c.getTariffRate() != null)
          c.setTariffRate(FormattingUtil.roundFigureRates(c.getTariffRate(), 2));
        rate.setTotal(FormattingUtil.add(rate.getTotal(), c.getCharge().doubleValue())
            .doubleValue());
        rate.setTotalCost(FormattingUtil.add(rate.getTotalCost(), c.getCost().doubleValue())
            .doubleValue());*/
                         if (c.getTariffRate() != null)
                        	              c.setTariffRate(FormattingUtil.roundFigureRates(c.getTariffRate(), 2));
                        	           
                         if(charge==0.0){
                        	 rate.setTotal(FormattingUtil.add(rate.getTotal(), c.getCharge().doubleValue())
                        	                .doubleValue());
                        	            }
                         else{
                        	    rate.setTotal(FormattingUtil.add(rate.getTotal(),charge)
                        	            	            .doubleValue());
                        	  }
                        
                         if(cost==0.0){
                        	    rate.setTotalCost(FormattingUtil.add(rate.getTotalCost(), c.getCost().doubleValue())
                        	                .doubleValue());
                        	            }
                         else{
                        	   rate.setTotalCost(FormattingUtil.add(rate.getTotalCost(), cost)
                        	            	            .doubleValue());
                        	 }

        if (chargeGroupCode != null) {
          c.setChargeId(chargeGroupCode.getId());
          if (c.getName() == null || c.getName().length() == 0)
            c.setName(chargeGroupCode.getChargeName());
        }
      }

      if(removeCharge.size()>0){
    	      	   for(int i=0;i<removeCharge.size(); i++)
    	      	   rate.getCharges().remove(removeCharge.get(i));
    	      	   }
      Service findIcPoundService = carrierServiceDAO.getService(rate.getServiceId());
      if (findIcPoundService.getServiceType() == SERVICE_TYPE_LTL_POUND
          && findIcPoundService.getCarrierId().intValue() == ShiplinxConstants.CARRIER_GENERIC) {
        long serviceId;
        if (service != null && service.getMasterServiceId() > 0) {
          serviceId = service.getMasterServiceId();
        } else {
          serviceId = service.getId();
        }
        Service masters=null;
        for(Service serviceTmp:listOfAllServices){
        	if(serviceId==serviceTmp.getId()){
        		masters=serviceTmp;
        	}
        }
        if(masters==null){
        	masters = carrierServiceDAO.getService(serviceId);
        }
        if (masters == null) {
          masters = service;
        }
        long maxLength = 0l, maxWidth = 0l, maxHeight = 0l, maxWeight = 0l;
        List<Service> masterService = new ArrayList<Service>();
        List<Long> lengthList = new ArrayList<Long>();
        List<Long> widthList = new ArrayList<Long>();
        List<Long> heightList = new ArrayList<Long>();
        List<Long> weightList = new ArrayList<Long>();
        if (service != null && service.getMasterServiceId() > 0) {
          masterService = carrierServiceDAO.findMasterServiceId(service.getMasterServiceId());
        }

        if (masterService != null && masterService.size() > 0) {
          for (Service master : masterService) {
            lengthList.add(master.getMaxLength());
            widthList.add(master.getMaxWidth());
            heightList.add(master.getMaxHeight());
            weightList.add(master.getMaxWeight());
          }
        }
        if (masterService == null && findIcPoundService != null) {
          lengthList.add(findIcPoundService.getMaxLength());
          widthList.add(findIcPoundService.getMaxWidth());
          heightList.add(findIcPoundService.getMaxHeight());
          weightList.add(findIcPoundService.getMaxWeight());
        }
        if (lengthList != null && lengthList.size() > 0) {
          maxLength = Collections.max(lengthList).longValue();
        }
        if (widthList != null && widthList.size() > 0) {
          maxWidth = Collections.max(widthList).longValue();
        }

        if (heightList != null && heightList.size() > 0) {
          maxHeight = Collections.max(heightList).longValue();
        }

        if (weightList != null && weightList.size() > 0) {
          maxWeight = Collections.max(weightList).longValue();
        }

        errorLog = MessageUtil.getMessage("error.skidrate.availability", MessageUtil.getLocale());
        errorLog = new String(errorLog.replaceAll("%SERVICE", masters.getName()));
        List<com.meritconinc.shiplinx.model.Package> packages = order.getPackages();
        for (com.meritconinc.shiplinx.model.Package pack : packages) {
          if (((maxLength > 0 && (pack.getLength().longValue() > maxLength)) && (maxWidth > 0 && (pack
              .getWidth().longValue() > maxWidth)))
              || ((maxLength > 0 && (pack.getLength().longValue() > maxLength)) && (maxHeight > 0 && (pack
                  .getHeight().longValue() > maxHeight)))
              || ((maxLength > 0 && (pack.getLength().longValue() > maxLength)) && (maxWeight > 0 && (pack
                  .getWeight().longValue() > maxWeight)))
              || ((maxWidth > 0 && (pack.getWidth().longValue() > maxWidth)) && (maxHeight > 0 && (pack
                  .getHeight().longValue() > maxHeight)))
              || ((maxWidth > 0 && (pack.getWidth().longValue() > maxWidth)))
              && (maxWeight > 0 && (pack.getWeight().longValue() > maxWeight))
              || ((maxHeight > 0 && (pack.getHeight().longValue() > maxHeight)))
              && (maxWeight > 0 && (pack.getWeight().longValue() > maxWeight))) {
            errorLog = errorLog.substring(0, 0);
            errorLog = MessageUtil.getMessage("error.skid.availability.ratemsg",
                MessageUtil.getLocale());
            errorLog = new String(errorLog.replaceAll("%SERVICE", masters.getName()));
            errorLog = new String(errorLog.replaceAll("%MAXWIDTH",
                String.valueOf(masters.getMaxWidth())));
            errorLog = new String(errorLog.replaceAll("%MAXLENGTH",
                String.valueOf(masters.getMaxLength())));
            errorLog = new String(errorLog.replaceAll("%MAXHEIGHT",
                String.valueOf(masters.getMaxHeight())));
            errorLog = new String(errorLog.replaceAll("%MAXWEIGHT",
                String.valueOf(masters.getMaxWeight())));
          } else if (maxWidth > 0 && pack.getWidth().longValue() > maxWidth) {
            errorLog = new String(errorLog.replaceAll("%TYPE", "Width"));
            errorLog = new String(errorLog.replaceAll("%VALUE",
                String.valueOf(masters.getMaxWidth())));
            errorLog = new String(errorLog.replaceAll("%UOM", "inches"));
          } else if (maxLength > 0 && pack.getLength().longValue() > maxLength) {
            errorLog = new String(errorLog.replaceAll("%TYPE", "Length"));
            errorLog = new String(errorLog.replaceAll("%VALUE",
                String.valueOf(masters.getMaxLength())));
            errorLog = new String(errorLog.replaceAll("%UOM", "inches"));
          } else if (maxHeight > 0 && pack.getHeight().longValue() > maxHeight) {
            errorLog = new String(errorLog.replaceAll("%TYPE", "Height"));
            errorLog = new String(errorLog.replaceAll("%VALUE",
                String.valueOf(masters.getMaxHeight())));
            errorLog = new String(errorLog.replaceAll("%UOM", "inches"));
          } else if (maxWeight > 0 && pack.getWeight().longValue() > maxWeight) {
            errorLog = new String(errorLog.replaceAll("%TYPE", "Weight"));
            errorLog = new String(errorLog.replaceAll("%VALUE",
                String.valueOf(masters.getMaxWeight())));
            errorLog = new String(errorLog.replaceAll("%UOM", "pounds"));
          } else {
            errorLog = null;
          }
        }
        if (errorLog != null && !errorLog.isEmpty()) {
          errorLogObj = new CarrierErrorMessage(masters.getCarrierId(), errorLog);
          if (!errorMessages.contains(errorLogObj)) {
            errorMessages.add(new CarrierErrorMessage(masters.getCarrierId(), errorLog));
          }
        }

      }
      // Start code for fixing issue # 108
      if (rate.getTotal() == 0 && rate.getTotalCost() == 0
          && rate.getCarrierId() == ShiplinxConstants.CARRIER_GENERIC) {
        // ratesToRemove.add(rate);
        Service masterService = carrierServiceDAO.getService(rate.getServiceId());
        if (masterService != null && rate.getServiceId() != masterService.getId()) {
          ratesToRemove.add(rate);
        }
        isICRequestQuote = true;
        continue;
      } else if (rate.getTotal() > 0 && rate.getTotalCost() > 0
          && rate.getCarrierId() == ShiplinxConstants.CARRIER_GENERIC) {
        isRateAvailable = true;
      }
      // End code for fixing issue # 108
      Collections.sort(rate.getCharges(), Charge.ChargeComparator);

      // calculate taxes (for Canadian Shipments)
      if (order.getFromAddress().getCountryCode().equalsIgnoreCase(ShiplinxConstants.CANADA)
          && order.getToAddress().getCountryCode().equalsIgnoreCase(ShiplinxConstants.CANADA))
        applyTaxes(rate, order);

      rate.setBillWeightUOM(ShiplinxConstants.WEIGHT_UNITS_LBS); // always
      // calculating
      // and
      // quoting
      // in
      // LBS
      // for
      // now
      rate.setTotal(FormattingUtil.roundFigureRates(rate.getTotal(), 2));
      rate.setTotalCost(FormattingUtil.roundFigureRates(rate.getTotalCost(), 2));
      index++;
      // rateTotal.add(rate);
    }
    // Collections.sort(rateTotal, Rating.PriceComparator);//Sorting the
    // Total of Rate
    // remove the rates that we could not find markup records for, and those
    // that are disabled
    ratingList.removeAll(ratesToRemove);

    // Start code for fixing issue # 108
    if (isICRequestQuote && !isRateAvailable) {
      Double defaultValue = 0.0;
      Integer defauktPercentage = 0;
      Rating rating = new Rating();

      double rate = 0;
      for (Rating ratingListRemove : ratesToRemove) {
        Service service = carrierServiceDAO.getService(ratingListRemove.getServiceId());
        long serviceId;
        if (service != null && service.getMasterServiceId() > 0) {
          serviceId = service.getMasterServiceId();
        } else {
          serviceId = service.getId();
        }
        Service masters = carrierServiceDAO.getService(serviceId);
        if (masters == null) {
          masters = service;
        }
        Set<Long> masterServiceSet = new TreeSet<Long>();
        if (masters != null) {

          if (masterServiceSet.contains(masters.getId())) {
            continue;
          }
          masterServiceSet.add(masters.getId());

          if (ratingListRemove.getTotal() == 0 && ratingListRemove.getTotalCost() == 0
              && masters.getCarrierId() == ShiplinxConstants.CARRIER_GENERIC) {

            Zone fromZone = markupManagerService.findZone(service.getZoneStructureId(),
                order.getFromAddress());
            Zone toZone = markupManagerService.findZone(service.getZoneStructureId(),
                order.getToAddress());

            LtlSkidRate skidRateTobeSearched = LtlSkidRate.getObject(order.getCustomerId(),
                order.getBusinessId(), ratingListRemove.getServiceId(), fromZone.getZoneName(),
                toZone.getZoneName());
            LtlSkidRate sr = this.markupManagerService.getSkidRate(skidRateTobeSearched);
            long maxLength = 0l, maxWidth = 0l, maxHeight = 0l, maxWeight = 0l;
            List<Service> masterService = new ArrayList<Service>();
            List<Long> lengthList = new ArrayList<Long>();
            List<Long> widthList = new ArrayList<Long>();
            List<Long> heightList = new ArrayList<Long>();
            List<Long> weightList = new ArrayList<Long>();
            if (masterService != null && masterService.size() > 0) {
              for (Service master : masterService) {
                lengthList.add(master.getMaxLength());
                widthList.add(master.getMaxWidth());
                heightList.add(master.getMaxHeight());
                weightList.add(master.getMaxWeight());
              }
            }
            if (masterService == null && service != null) {
              lengthList.add(service.getMaxLength());
              widthList.add(service.getMaxWidth());
              heightList.add(service.getMaxHeight());
              weightList.add(service.getMaxWeight());
            }
            if (lengthList != null && lengthList.size() > 0) {
              maxLength = Collections.max(lengthList).longValue();
            }
            if (widthList != null && widthList.size() > 0) {
              maxWidth = Collections.max(widthList).longValue();
            }

            if (heightList != null && heightList.size() > 0) {
              maxHeight = Collections.max(heightList).longValue();
            }

            if (weightList != null && weightList.size() > 0) {
              maxWeight = Collections.max(weightList).longValue();
            }

            if (sr == null) {
              // Customer Rate did not found, try retrieving default rate
              skidRateTobeSearched.setCustomerId(0L);
              sr = this.markupManagerService.getSkidRate(skidRateTobeSearched);
              if (sr == null) {
                List<LtlSkidRate> groupLtlSkidRate = new ArrayList<LtlSkidRate>();
                // long maxLength = 0l, maxWidth = 0l, maxHeight = 0l, maxWeight = 0l;
                /*
                 * List<Service> masterService = new ArrayList<Service>(); List<Long> lengthList =
                 * new ArrayList<Long>(); List<Long> widthList = new ArrayList<Long>(); List<Long>
                 * heightList = new ArrayList<Long>(); List<Long> weightList = new
                 * ArrayList<Long>();
                 */
                if (service != null && service.getMasterServiceId() > 0) {
                  masterService = carrierServiceDAO.findMasterServiceId(service
                      .getMasterServiceId());
                }
                /*
                 * if (masterService != null && masterService.size() > 0) { for (Service master :
                 * masterService) { lengthList.add(master.getMaxLength());
                 * widthList.add(master.getMaxWidth()); heightList.add(master.getMaxHeight());
                 * weightList.add(master.getMaxWeight()); }
                 * 
                 * if (lengthList != null && lengthList.size() > 0) { maxLength =
                 * Collections.max(lengthList).longValue(); } if (widthList != null &&
                 * widthList.size() > 0) { maxWidth = Collections.max(widthList).longValue(); }
                 */

                if (fromZone != null && toZone != null && fromZone.getZoneName() != null
                    && toZone.getZoneName() != null) {
                  groupLtlSkidRate = markupManagerService.groupingLTLSkidRate(
                      ratingListRemove.getServiceId(), maxLength, maxWidth, maxHeight, maxWeight,
                      fromZone.getZoneName(), toZone.getZoneName());
                }
                if (groupLtlSkidRate != null && groupLtlSkidRate.size() > 0) {
                  Double totWeight = order.getTotalWeight();

                  if (totWeight != null && totWeight > 0) {
                    if (totWeight != null && totWeight > 0 && totWeight < 500 && maxLength > 0
                        && maxWidth > 0 && maxHeight > 0 && maxLength < 49 && maxWidth < 49
                        && maxHeight < 49 && sr.getRateFlatWeight() != null
                        && sr.getRateFlatWeight().doubleValue() > 0) {
                      rate = sr.getRateFlatWeight().doubleValue();
                    } else if (totWeight != null && totWeight > 0 && totWeight < 500
                        && maxLength > 0 && maxWidth > 0 && maxHeight > 0
                        && maxLength <= service.getMaxLength() && maxWidth <= service.getMaxWidth()
                        && maxHeight <= service.getMaxHeight()) {
                      rate = sr.getRateSkid1();
                    } else if (order.getQuantity() != null && order.getQuantity().intValue() > 0) {
                      rate = sr.getSkidRate(order.getQuantity().intValue());
                    }
                  }
                }
                if (rate > 0.0) {
                  errorMsg = false;
                  isLTLRateAvailable = true;
                  break;
                }
              }
              if (sr != null) {
                Double totWeight = order.getTotalWeight();

                if (totWeight != null && totWeight > 0) {
                  if (totWeight != null && totWeight > 0 && totWeight < 500 && maxLength > 0
                      && maxWidth > 0 && maxHeight > 0 && maxLength < 49 && maxWidth < 49
                      && maxHeight < 49 && sr.getRateFlatWeight() != null
                      && sr.getRateFlatWeight().doubleValue() > 0) {
                    rate = sr.getRateFlatWeight().doubleValue();
                  } else if (totWeight != null && totWeight > 0 && totWeight < 500 && maxLength > 0
                      && maxWidth > 0 && maxHeight > 0 && maxLength <= service.getMaxLength()
                      && maxWidth <= service.getMaxWidth() && maxHeight <= service.getMaxHeight()) {
                    rate = sr.getRateSkid1();
                  } else if (order.getQuantity() != null && order.getQuantity().intValue() > 0) {
                    rate = sr.getSkidRate(order.getQuantity().intValue());
                  }
                }
                if (rate > 0.0) {
                  errorMsg = false;
                  isLTLRateAvailable = true;
                  break;
                }
              }
            }
            if (sr != null) {
              Double totWeight = order.getTotalWeight();

              if (totWeight != null && totWeight > 0) {
                if (totWeight != null && totWeight > 0 && totWeight < 500 && maxLength > 0
                    && maxWidth > 0 && maxHeight > 0 && maxLength < 49 && maxWidth < 49
                    && maxHeight < 49 && sr.getRateFlatWeight() != null
                    && sr.getRateFlatWeight().doubleValue() > 0) {
                  rate = sr.getRateFlatWeight().doubleValue();
                } else if (totWeight != null && totWeight > 0 && totWeight < 500 && maxLength > 0
                    && maxWidth > 0 && maxHeight > 0 && maxLength <= service.getMaxLength()
                    && maxWidth <= service.getMaxWidth() && maxHeight <= service.getMaxHeight()) {
                  rate = sr.getRateSkid1();
                } else if (order.getQuantity() != null && order.getQuantity().intValue() > 0) {
                  rate = sr.getSkidRate(order.getQuantity().intValue());
                }
              }
              if (rate > 0.0) {
                isLTLRateAvailable = true;
                break;
              }
            }
          }
        }
      }
      if (rate == 0.0 && !isLTLRateAvailable) {
        rating.setCarrierId(ShiplinxConstants.CARRIER_GENERIC);
        rating.setCarrierName(order.getBusiness().getName());
        rating.setServiceId(ShiplinxConstants.DEFAULT_IC_SERVICE_ID);
        rating.setServiceName(ShiplinxConstants.DEFAULT_IC_SERVICE);
        rating.setTotal(defaultValue);
        rating.setTotalCost(defaultValue);
        rating.setMarkupPercentage(defauktPercentage);
        rating.setMarkupTypeText(ShiplinxConstants.TYPE_MARKUP_TEXT);
        Charge charge = new Charge();
        charge.setChargeCode(ShiplinxConstants.GROUP_FREIGHT_CHARGE);
        charge.setName(ShiplinxConstants.FREIGHT_STRING);
        charge.setCost(defaultValue);
        charge.setTariffRate(defaultValue);
        charge.setCharge(defaultValue);
        rating.setCharge(charge);
        Charge chargeFuel = new Charge();
        chargeFuel.setChargeCode(ShiplinxConstants.GROUP_FUEL_CHARGE);
        chargeFuel.setName(ShiplinxConstants.FUEL_SURCHARGE);
        chargeFuel.setCost(defaultValue);
        chargeFuel.setCharge(defaultValue);
        chargeFuel.setTariffRate(defaultValue);
        List<Charge> chargesList = new ArrayList<Charge>();
        chargesList.add(chargeFuel);
        chargesList.add(charge);
        rating.setCharges(chargesList);
        ratingList.add(rating);
        errorLog = MessageUtil.getMessage("error.skid.availability.rate", MessageUtil.getLocale());
        errorLogObj = new CarrierErrorMessage(errorLog);
        if (!errorMessages.contains(errorLogObj)) {
          errorMessages.add(new CarrierErrorMessage(errorLog));
          errorMsg = true;
        }
      }
      /*
       * for(Rating ratingListErrorMessage:ratesToRemove){ if (ratingListErrorMessage.getTotal() ==
       * 0 && ratingListErrorMessage.getTotalCost() == 0 && ratingListErrorMessage.getCarrierId() ==
       * ShiplinxConstants.CARRIER_GENERIC) { Service service =
       * carrierServiceDAO.getService(ratingListErrorMessage.getServiceId()); errorLog =
       * MessageUtil.getMessage("error.skidrate.availability", MessageUtil.getLocale()); errorLog =
       * new String(errorLog.replaceAll("%SERVICE", ratingListErrorMessage.getServiceName()));
       * List<com.meritconinc.shiplinx.model.Package> packages = order.getPackages();
       * for(com.meritconinc.shiplinx.model.Package pack : packages){ if ((service.getMaxWidth() >0
       * && (pack.getWidth().longValue() > service.getMaxWidth())) && (service.getMaxHeight() > 0 &&
       * (pack.getHeight().longValue() > service.getMaxHeight())) && (service.getMaxLength() > 0 &&
       * (pack.getLength().longValue() > service.getMaxLength())) && (service.getMaxWeight() > 0 &&
       * (pack.getWeight().longValue() > service.getMaxWeight()))) { errorLog =
       * errorLog.substring(0,0); errorLog = MessageUtil.getMessage("error.skid.availability.rate",
       * MessageUtil.getLocale()); }else if(service.getMaxWidth() > 0 && pack.getWidth().longValue()
       * > service.getMaxWidth()){ errorLog = new String(errorLog.replaceAll("%TYPE", "Width"));
       * errorLog = new String(errorLog.replaceAll("%VALUE",
       * String.valueOf(service.getMaxWidth()))); errorLog = new String(errorLog.replaceAll("%UOM",
       * "inches")); }else if(service.getMaxLength()> 0 && pack.getLength().longValue() >
       * service.getMaxLength()){ errorLog = new String(errorLog.replaceAll("%TYPE", "Length"));
       * errorLog = new String(errorLog.replaceAll("%VALUE",
       * String.valueOf(service.getMaxLength()))); errorLog = new String(errorLog.replaceAll("%UOM",
       * "inches")); }else if(service.getMaxHeight() > 0 && pack.getHeight().longValue() >
       * service.getMaxHeight()){ errorLog = new String(errorLog.replaceAll("%TYPE", "Height"));
       * errorLog = new
       * String(errorLog.replaceAll("%VALUE",String.valueOf(service.getMaxHeight()))); errorLog =
       * new String(errorLog.replaceAll("%UOM", "inches")); }else if(service.getMaxWeight()> 0 &&
       * pack.getWeight().longValue() > service.getMaxWeight()){ errorLog = new
       * String(errorLog.replaceAll("%TYPE", "Weight")); errorLog = new
       * String(errorLog.replaceAll("%VALUE", String.valueOf(service.getMaxWeight()))); errorLog =
       * new String(errorLog.replaceAll("%UOM", "pounds")); }else{ errorLog =
       * errorLog.substring(0,0); errorLog = MessageUtil.getMessage("error.skid.availability.rate",
       * MessageUtil.getLocale()); } } errorLogObj = new
       * CarrierErrorMessage(ratingListErrorMessage.getCarrierId(),errorLog);
       * if(!errorMessages.contains(errorLogObj)){ errorMessages.add(new
       * CarrierErrorMessage(ratingListErrorMessage.getCarrierId(), errorLog)); }
       * 
       * } }
       */
      // throw new ShiplinxException();
    }
    // End code for fixing issue # 108
    // DHL Deferred - set transit days to same as UPS Standard, this is
    // hard-code for IC
    if (isICRequestQuote && !errorMsg) {
      for (Rating ratingListErrorMessage : ratesToRemove) {
        Service service = carrierServiceDAO.getService(ratingListErrorMessage.getServiceId());
        long serviceId;
        if (service != null && service.getMasterServiceId() > 0) {
          serviceId = service.getMasterServiceId();
        } else {
          serviceId = service.getId();
        }
        Service masters = carrierServiceDAO.getService(serviceId);
        if (masters == null) {
          masters = service;
        }
        Set<Long> masterServiceSet = new TreeSet<Long>();
        if (masters != null) {

          if (masterServiceSet.contains(masters.getId())) {
            continue;
          }
          masterServiceSet.add(masters.getId());
          if (ratingListErrorMessage.getTotal() == 0.0
              && ratingListErrorMessage.getTotalCost() == 0.0
              && masters.getCarrierId() == (long) ShiplinxConstants.CARRIER_GENERIC) {
            List<LtlSkidRate> groupLtlSkidRate = new ArrayList<LtlSkidRate>();
            long maxLength = 0l, maxWidth = 0l, maxHeight = 0l, maxWeight = 0l;
            List<Service> masterService = new ArrayList<Service>();
            List<Long> lengthList = new ArrayList<Long>();
            List<Long> widthList = new ArrayList<Long>();
            List<Long> heightList = new ArrayList<Long>();
            List<Long> weightList = new ArrayList<Long>();
            if (service != null && service.getMasterServiceId() > 0) {
              masterService = carrierServiceDAO.findMasterServiceId(service.getMasterServiceId());
            }
            if (masterService != null && masterService.size() > 0) {
              for (Service master : masterService) {
                lengthList.add(master.getMaxLength());
                widthList.add(master.getMaxWidth());
                heightList.add(master.getMaxHeight());
                weightList.add(master.getMaxWeight());
              }
            }
            if (masterService == null && service != null) {
              lengthList.add(service.getMaxLength());
              widthList.add(service.getMaxWidth());
              heightList.add(service.getMaxHeight());
              weightList.add(service.getMaxWeight());
            }
            if (lengthList != null && lengthList.size() > 0) {
              maxLength = Collections.max(lengthList).longValue();
            }
            if (widthList != null && widthList.size() > 0) {
              maxWidth = Collections.max(widthList).longValue();
            }

            if (heightList != null && heightList.size() > 0) {
              maxHeight = Collections.max(heightList).longValue();
            }

            if (weightList != null && weightList.size() > 0) {
              maxWeight = Collections.max(weightList).longValue();
            }

            /*
             * if (service != null && service.getMasterServiceId() > 0) { masterService =
             * carrierServiceDAO.getService(service.getMasterServiceId()); } if (service != null) {
             * masterService = service; } if (masterService != null && masterService.getId() > 0) {
             */
            errorLog = MessageUtil.getMessage("error.skidrate.availability",
                MessageUtil.getLocale());
            errorLog = new String(errorLog.replaceAll("%SERVICE", masters.getName()));
            List<com.meritconinc.shiplinx.model.Package> packages = order.getPackages();
            for (com.meritconinc.shiplinx.model.Package pack : packages) {
              if (((maxLength > 0 && (pack.getLength().longValue() > maxLength)) && (maxWidth > 0 && (pack
                  .getWidth().longValue() > maxWidth)))
                  || ((maxLength > 0 && (pack.getLength().longValue() > maxLength)) && (maxHeight > 0 && (pack
                      .getHeight().longValue() > maxHeight)))
                  || ((maxLength > 0 && (pack.getLength().longValue() > maxLength)) && (maxWeight > 0 && (pack
                      .getWeight().longValue() > maxWeight)))
                  || ((maxWidth > 0 && (pack.getWidth().longValue() > maxWidth)) && (maxHeight > 0 && (pack
                      .getHeight().longValue() > maxHeight)))
                  || ((maxWidth > 0 && (pack.getWidth().longValue() > maxWidth)))
                  && (maxWeight > 0 && (pack.getWeight().longValue() > maxWeight))
                  || ((maxHeight > 0 && (pack.getHeight().longValue() > maxHeight)))
                  && (maxWeight > 0 && (pack.getWeight().longValue() > maxWeight))) {
                errorLog = errorLog.substring(0, 0);
                errorLog = MessageUtil.getMessage("error.skid.availability.ratemsg",
                    MessageUtil.getLocale());
                errorLog = new String(errorLog.replaceAll("%SERVICE", masters.getName()));
                errorLog = new String(errorLog.replaceAll("%MAXWIDTH",
                    String.valueOf(masters.getMaxWidth())));
                errorLog = new String(errorLog.replaceAll("%MAXLENGTH",
                    String.valueOf(masters.getMaxLength())));
                errorLog = new String(errorLog.replaceAll("%MAXHEIGHT",
                    String.valueOf(masters.getMaxHeight())));
                errorLog = new String(errorLog.replaceAll("%MAXWEIGHT",
                    String.valueOf(masters.getMaxWeight())));
              } else if (maxWidth > 0 && pack.getWidth().longValue() > maxWidth) {
                errorLog = new String(errorLog.replaceAll("%TYPE", "Width"));
                errorLog = new String(errorLog.replaceAll("%VALUE",
                    String.valueOf(masters.getMaxWidth())));
                errorLog = new String(errorLog.replaceAll("%UOM", "inches"));
              } else if (maxLength > 0 && pack.getLength().longValue() > maxLength) {
                errorLog = new String(errorLog.replaceAll("%TYPE", "Length"));
                errorLog = new String(errorLog.replaceAll("%VALUE",
                    String.valueOf(masters.getMaxLength())));
                errorLog = new String(errorLog.replaceAll("%UOM", "inches"));
              } else if (maxHeight > 0 && pack.getHeight().longValue() > maxHeight) {
                errorLog = new String(errorLog.replaceAll("%TYPE", "Height"));
                errorLog = new String(errorLog.replaceAll("%VALUE",
                    String.valueOf(masters.getMaxHeight())));
                errorLog = new String(errorLog.replaceAll("%UOM", "inches"));
              } else if (maxWeight > 0 && pack.getWeight().longValue() > maxWeight) {
                errorLog = new String(errorLog.replaceAll("%TYPE", "Weight"));
                errorLog = new String(errorLog.replaceAll("%VALUE",
                    String.valueOf(masters.getMaxWeight())));
                errorLog = new String(errorLog.replaceAll("%UOM", "pounds"));
              } else {
                errorLog = null;
                /*
                 * errorLog = MessageUtil.getMessage("error.skid.availability.rate",
                 * MessageUtil.getLocale());
                 */
                log.debug("There IS No Rate is Available for the Service"
                    + ratingListErrorMessage.getServiceName());
              }
            }
            if (errorLog != null && !errorLog.isEmpty()) {
              errorLogObj = new CarrierErrorMessage(masters.getCarrierId(), errorLog);
              if (!errorMessages.contains(errorLogObj)) {
                errorMessages.add(new CarrierErrorMessage(masters.getCarrierId(), errorLog));
              }
            }
          }
        }
      }
    }
    Rating dhlDeferred = null;
    Rating upsStandard = null;
    for (Rating rating : ratingList) {
      if (rating.getCarrierId() == ShiplinxConstants.CARRIER_UPS
          && (rating.getServiceId() == 204 || rating.getServiceId() == 208)) {
        upsStandard = rating;
      }
      if (rating.getCarrierId() == ShiplinxConstants.CARRIER_DHL
          && rating.getServiceId() == DHLAPI.SERVICE_INTL_GROUND) {
        dhlDeferred = rating;
      }
    }
    if (dhlDeferred != null && upsStandard != null)
      dhlDeferred.setTransitDays(upsStandard.getTransitDays());

    if (order.getInsideDelivery() == null) {
      order.setInsideDelivery(false);
    }

    try {
    	Map<String, String> serviceError = new HashMap<String, String>();
      if (order != null && ratingList != null) {
        if (order.getFromAddress().isResidential() == true
            || order.getToAddress().isResidential() == true || order.isTradeShowPickup() == true
            || order.isTradeShowDelivery() == true || order.isInsidePickup() == true
            || order.getInsideDelivery() == true || order.isAppointmentPickup() == true
            || order.isAppointmentDelivery() == true || order.isFromTailgate() == true
            || order.isToTailgate() == true || order.getSatDelivery() == true
            || order.getHoldForPickupRequired() == true || order.getCODValue() > 0.0
            || order.getInsuredAmount() > 0.0) {
          for (int i = 0; i < ratingList.size(); i++) {
        	  if (ratingList.get(i).getTotal() <= 0.0) {
        		  Boolean flag=checkServiceErrorExistance(ratingList.get(i),serviceError);
        		  if(flag){
              String subject = MessageUtil.getMessage("error.additional.zero.charge",
                  MessageUtil.getLocale());
              String serviceName = "";
              String carrierName = "";
              if (ratingList.get(i) != null && ratingList.get(i).getServiceName() != null) {
                serviceName = ratingList.get(i).getServiceName();
              }

              if (ratingList.get(i) != null && ratingList.get(i).getCarrierName() != null) {
                carrierName = ratingList.get(i).getCarrierName();
              }
              subject = new String(subject.replaceAll("%SERVICE", serviceName));
              subject = new String(subject.replaceAll("%CARRIER", carrierName));

              errorLogObj = new CarrierErrorMessage(subject);
              errorMessages.add(new CarrierErrorMessage(subject));
              log.debug("Additional services request quote error added for ");
              log.debug("service name: "+ratingList.get(i).getServiceName()+"& id: "+ratingList.get(i).getServiceId());
              serviceError.put(serviceName,carrierName);
        		  }
        		  }
          }

        }
      }
    } catch (Exception e) {
      log.debug("Error in showing the error message while the rate is zero for additional service : "
          + e.getMessage());
    }

  }

  public List<Carrier> getCarriers() {
    return getCarrierServiceDAO().getCarriers();
  }

  public CustomerManager getCustomerService() {
    return customerService;
  }

  public void setCustomerService(CustomerManager customerManager) {
    this.customerService = customerManager;
  }

  public ShippingService getShippingService() {
    return shippingService;
  }

  public void setShippingService(ShippingService shippingService) {
    this.shippingService = shippingService;
  }

  public CustomerCarrier getDefAccountByCountry(long carrierId, long customerId, String country) {
    return getCarrierServiceDAO().getDefAccountByCountry(carrierId, customerId, country);
  }

  public AddressManager getAddressService() {
    return addressService;
  }

  public void setAddressService(AddressManager addressService) {
    this.addressService = addressService;
  }

  private void copyFromOrderToPickup(ShippingOrder order, Rating rate) {
    order.getPickup().setDestinationCountryCode(order.getToAddress().getCountryCode());
    order.getPickup().setServiceCode(order.getService().getCode());
    order.getPickup().setQuantity(order.getPackages().size());
    order.getPickup().setAddress(order.getFromAddress());
    order.getPickup().setCarrierId(order.getCarrierId());
    order.getPickup().setServiceId(order.getServiceId());
    if (order != null && order.getPickup().getCarrierId() == ShiplinxConstants.CARRIER_PUROLATOR
        && ("CA").equals(order.getFromAddress().getCountryCode())) {
      order.getPickup().setServiceId(ShiplinxConstants.DEFAULT_PUROLATOR_PICKUP_SERVICE_ID);
    } else {
      order.getPickup().setServiceId(order.getServiceId());
    }
    order.getPickup().setCustomerId(order.getCustomerId());
    order.getPickup().setBusinessId(order.getBusinessId());
    order.getPickup().setPickupDate(order.getScheduledShipDate());
    order.getPickup().setPackageTypeId(order.getPackageTypeId().getPackageTypeId());
    order.getPickup().setTotalWeight(order.getTotalWeight());
    order.getPickup().setTotalActualWeight(order.getTotalActualWeight());
    order.getPickup().setOrderId(order.getId());
  }

  private CarrierService getCarrierServiceBean(String implementingClass) {
    // TODO Auto-generated method stub
    // when it invoked from a thread, it does not work.
    // WebApplicationContext context =
    // WebApplicationContextUtils.getWebApplicationContext((ServletContext)ActionContext.getContext().get(ServletActionContext.SERVLET_CONTEXT));
    // CarrierService carrierService =
    // (CarrierService)context.getBean(carrier.getImplementingClass());
    CarrierService carrierService = (CarrierService) MmrBeanLocator.getInstance().findBean(
        implementingClass);

    return carrierService;
  }

  public void run() {
    try {
      log.debug("Inside--the--run--method--");

      ratesThread = carrierServiceThread.rateShipment(orderThread, carrierServicesList,
          carrierServiceThread.getCarrierId(), customerCarrierThread);
      // logger.debug("rates"+ratesThread+" for carrier ="+carrierServiceThread.getCarrierId());
      if (ratesThread != null) {
        log.debug("inside if rate!=null");
        for(Rating rating : ratesThread){
        	       rating.setAccountCountry(customerCarrierThread.getCountry());
        	        }
        if(!(orderThread.getFromAddress().getCountryCode().equals(orderThread.getToAddress().getCountryCode()))&&(carrierServiceThread.getCarrierId()==2 || carrierServiceThread.getCarrierId()==5)){
        	        	if(customerCarrierThread.getCount()==0){ 
        	        	  	for (int x = 0; x < ratesThread.size(); x++) {
        	        	  			 fromRatingList.add(ratesThread.get(x));
        	        	         }
        	        	  
        	        	  			orderThread.setFromRatingList(fromRatingList);
        	        	  	}else{
        	        	  		for (int x = 0; x < ratesThread.size(); x++) {
        	        	     		 toRatingList.add(ratesThread.get(x));
        	        	            }
        	        	  		orderThread.setToRatingList(toRatingList);
        	        	  	}
        	           /* if(orderThread.getFromRatingList()!=null && orderThread.getToRatingList()!=null){
        		        	 findCheapestRate(rateListThread, orderThread);	         	
        		          }*/
        	            
        	        }else{
        for (int x = 0; x < ratesThread.size(); x++) {
          rateListThread.add(ratesThread.get(x));
        }
      }
      }
    } catch (ShiplinxException e) {
      for (String s : e.getErrorMessages()) {
        CarrierErrorMessage message = new CarrierErrorMessage(carrierServiceThread.getCarrierId(),
            customerCarrierThread.getCarrierName() + " : " + s);
        getParentThread().getErrorMessages().add(message);
      }
    }

  }

  public ShippingOrder getOrderThread() {
    return orderThread;
  }

  public void setOrderThread(ShippingOrder orderThread) {
    this.orderThread = orderThread;
  }

  public List<Rating> getRateListThread() {
    return rateListThread;
  }

  public void setRateListThread(List<Rating> rateListThread) {
    this.rateListThread = rateListThread;
  }

  private void applyTaxes(Rating rate, ShippingOrder order) {

    Province destinationProvince = addressService.getProvince(order.getToAddress()
        .getProvinceCode());
    // tax applies based on province destination
    ChargeGroup taxChargeGroup = shippingService.getChargeGroup(destinationProvince
        .getTaxChargeGroup());

    Charge c = new Charge();
    c.setStatus(ShiplinxConstants.CHARGE_QUOTED);
    c.setName(taxChargeGroup.getGroupCode());
    double tax_cost = rate.getTotalCost() * taxChargeGroup.getTaxRate() / 100;
    double tax_charge = rate.getTotal() * taxChargeGroup.getTaxRate() / 100;
    double tax_tariff = rate.getTariffRate() * taxChargeGroup.getTaxRate() / 100;
    c.setCharge(tax_charge);
    c.setCost(tax_cost);
    c.setTariffRate(tax_tariff);
    c.setChargeGroupId((int)taxChargeGroup.getGroupId());

    c.setCharge(FormattingUtil.roundFigureRates(c.getCharge(), 2));
    c.setCost(FormattingUtil.roundFigureRates(c.getCost(), 2));
    if (c.getTariffRate() != null)
      c.setTariffRate(FormattingUtil.roundFigureRates(c.getTariffRate(), 2));
    rate.getCharges().add(c);
    rate.setTotal(FormattingUtil.add(rate.getTotal(), c.getCharge().doubleValue()).doubleValue());
    rate.setTotalCost(FormattingUtil.add(rate.getTotalCost(), c.getCost().doubleValue())
        .doubleValue());
    rate.setTariffRate(FormattingUtil.add(rate.getTariffRate(), c.getTariffRate().doubleValue())
        .doubleValue());

    // special case, for shipments going from Quebec to Quebec, we need to
    // add QST as well
    if (order.getFromAddress().getProvinceCode().equalsIgnoreCase("PQ")
        && order.getToAddress().getProvinceCode().equalsIgnoreCase("PQ")) {
      taxChargeGroup = shippingService.getChargeGroup(12); // QST
      c = new Charge();
      c.setStatus(ShiplinxConstants.CHARGE_QUOTED);
      c.setName(taxChargeGroup.getGroupCode());
      tax_cost = rate.getTotalCost() * taxChargeGroup.getTaxRate() / 100;
      tax_charge = rate.getTotal() * taxChargeGroup.getTaxRate() / 100;
      tax_tariff = rate.getTariffRate() * taxChargeGroup.getTaxRate() / 100;
      c.setCharge(tax_charge);
      c.setCost(tax_cost);
      c.setTariffRate(tax_tariff);

      c.setCharge(FormattingUtil.roundFigureRates(c.getCharge(), 2));
      c.setCost(FormattingUtil.roundFigureRates(c.getCost(), 2));
      if (c.getTariffRate() != null)
        c.setTariffRate(FormattingUtil.roundFigureRates(c.getTariffRate(), 2));
      rate.getCharges().add(c);
      rate.setTotal(FormattingUtil.add(rate.getTotal(), c.getCharge().doubleValue()).doubleValue());
      rate.setTotalCost(FormattingUtil.add(rate.getTotalCost(), c.getCost().doubleValue())
          .doubleValue());
      rate.setTariffRate(FormattingUtil.add(rate.getTariffRate(), c.getTariffRate().doubleValue())
          .doubleValue());

    }
  }

  public Service getServiceByCarrierIdAndCode(Long carrierId, String code) {
    return this.carrierServiceDAO.getServiceByCarrierIdAndCode(carrierId, code);
  }

  public long addNewService(Service service) {
    // first determine the service id to be assigned to this service
    Long maxServiceIdforCarrier = carrierServiceDAO.getMaxServiceIdForCarrier(service
        .getCarrierId());
    if (maxServiceIdforCarrier == null || maxServiceIdforCarrier.longValue() == 0)
      service.setId(service.getCarrierId() * 100);
    else
      service.setId(maxServiceIdforCarrier + 1);

    try {
      carrierServiceDAO.addService(service);
      log.info("Added service " + service.getName() + " for carrier " + service.getCarrierId());
    } catch (Exception e) {
      log.error("Unable to add service with name " + service.getName() + " and carrier "
          + service.getCarrierId());
    }

    // add a new default markup record for this service
    Markup markup = new Markup();
    markup.setCustomerId(new Long(0));
    markup.setBusinessId(service.getBusinessId());
    markup.setFromCountryCode("ANY");
    markup.setToCountryCode("ANY");
    markup.setMarkupPercentage(0);
    markup.setMarkupFlat(0.0);
    markup.setDisabled(1);
    markup.setType(ShiplinxConstants.TYPE_MARKUP);
    markup.setFromWeight(0.0);
    markup.setToWeight(0.0);
    markup.setServiceId(service.getId());
    this.markupManagerService.addMarkup(markup);

    log.info("Added markup for service " + service.getName());

    return service.getId();
  }

  public UserSearchCriteria getCriteria() {
    return criteria;
  }

  public void setCriteria(UserSearchCriteria criteria) {
    this.criteria = criteria;
  }

  public CarrierServiceManagerImpl getParentThread() {
    return parentThread;
  }

  @Override
  public void uploadRates(long serviceId, long customerId, long busId, boolean isOverwrite)
      throws Exception {
    Service service = this.getService(serviceId);
    // Customer customer =
    // this.customerService.getCustomerInfoByCustomerId(customerId);
    if (service != null && service.getCarrier() != null) {
      CarrierService carrierService = getCarrierServiceBean(service.getCarrier()
          .getImplementingClass());
      if (carrierService != null) {
        carrierService.uploadRates(service, customerId, busId, isOverwrite);
      }
    }
  }

  public void checkForShipmentStatusUpdates() {

    long start = System.currentTimeMillis();
    log.info("Starting Status Update Job...");

    // shippingService.runBillingUpdate();

    //
    // ShippingOrder order = new ShippingOrder();
    // Integer[] statusIds = new Integer[3];
    // statusIds[0] = ShiplinxConstants.STATUS_DISPATCHED;
    // statusIds[1] = ShiplinxConstants.STATUS_INTRANSIT;
    // statusIds[2] = ShiplinxConstants.STATUS_EXCEPTION;
    // order.setStatusIds(statusIds);
    //
    // //only in the last month
    // int month = Calendar.getInstance().get(Calendar.MONTH);
    // int year = Calendar.getInstance().get(Calendar.YEAR);
    // int today = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
    //
    // Calendar calendar = new GregorianCalendar(year, month, today);
    // order.setToDate(FormattingUtil.getFormattedDate(calendar.getTime(),FormattingUtil.DATE_FORMAT_WEB));
    //
    // calendar.add(Calendar.DATE, -31);
    // order.setFromDate(FormattingUtil.getFormattedDate(calendar.getTime(),FormattingUtil.DATE_FORMAT_WEB));
    //
    // List<ShippingOrder> orders = shippingService.getShipments(order);
    //
    // for(ShippingOrder so: orders){
    // log.debug("Attempting to update status of order #" + so.getId());
    // CarrierService carrierService =
    // getCarrierServiceBean(so.getCarrier().getImplementingClass());
    // //get the appropriate account to be used to generate/rate the
    // shipment
    // CustomerCarrier customerCarrier =
    // getCarrierAccount(so.getCustomerId(), so.getBusinessId(),
    // so.getCarrier().getId(), so.getFromAddress().getCountryCode(),
    // so.getToAddress().getCountryCode());
    //
    // OrderStatus status = carrierService.checkForShipmentStatusUpdates(so,
    // customerCarrier);
    //
    // if(status==null)
    // continue;
    //
    // //update the shipment and the order status
    // //shipment contains POD information
    // shippingService.updateOrderStatus(so.getId(), status.getId(),
    // status.getComment(), false, so.getPodReceiver(),
    // so.getPodDateTime());
    //
    // }
    //
    long end = System.currentTimeMillis();
    log.info("Update status process took " + (end - start) / 1000 + " seconds!");

  }

  public void groupingServices(List<Rating> ratingList, ShippingOrder order) {
    if (order.getPackageTypeId().getName()
        .equalsIgnoreCase(ShiplinxConstants.PACKAGE_PALLET_STRING.trim())
        && ratingList != null) {
      int i, x, j;
      for (i = 0; i < ratingList.size(); i++) {
        long serviceId = ratingList.get(i).getServiceId();
        Service service = getService(serviceId);
        if(service.getServiceType() == ShiplinxConstants.SERVICE_TYPE_LTL_SKID){
        if (service != null && service.getServiceType() == ShiplinxConstants.SERVICE_TYPE_LTL_SKID) {
          log.debug("Applying markup based on # of skids: " + order.getPackages().size());
          order.setWeightToMarkup((double) order.getPackages().size());
        } else {
          order.setWeightToMarkup(ratingList.get(i).getBillWeight());
        }

        Markup markup = markupManagerService.getMarkupObj(order);
        if(service.getId().equals(service.getMasterServiceId())){
        markup.setServiceId(service.getMasterServiceId());
        }
        else{
	        markup.setServiceId(serviceId);
	        }
        markup = markupManagerService.getUniqueMarkup(markup);
        if (markup != null) {
          ratingList.get(i).setMarkupFlat(markup.getMarkupFlat());
          ratingList.get(i).setMarkupPercentage(markup.getMarkupPercentage());
        }
        if (service != null && service.getServiceType() == SERVICE_TYPE_LTL_SKID) {
          Zone fromZone = null, toZone = null;
          if (ratingList.get(i).getZoneStructureId() > 0) {
            fromZone = markupManagerService.findZone(ratingList.get(i).getZoneStructureId(),
                order.getFromAddress());
            toZone = markupManagerService.findZone(ratingList.get(i).getZoneStructureId(),
                order.getToAddress());
          } else {
            fromZone = markupManagerService.findZone(service.getZoneStructureId(),
                order.getFromAddress());
            toZone = markupManagerService.findZone(service.getZoneStructureId(),
                order.getToAddress());
          }
          String fromZoneName = "";
          String toZoneName = "";
          String fromCity = "";
                    String toCity ="";
                    Long fromZoneStructureId=0l;
                    Long toZoneStructureId=0l;
                    String fromCountry = "";
                                        String toCountry = "";
                                        String fromProvince = "";
                                        String toProvince = "";
          if (fromZone != null) {
            fromZoneName = fromZone.getZoneName();
            fromCity = fromZone.getCityName();
                        fromZoneStructureId = fromZone.getZoneStructureId();
          }
          if (toZone != null) {
            toZoneName = toZone.getZoneName();
            toCity = toZone.getCityName();
                        toZoneStructureId = toZone.getZoneStructureId();
          }
                    
                    if(fromZone != null){
                  	  fromCountry = fromZone.getCountryCode();
                    }
                    
                    if (toZone != null){
                  	  toCountry = toZone.getCountryCode();
                    }
                    
                    if(fromZone != null){
                  	  fromProvince = fromZone.getProvinceCode();
                    }
                    
                    if(toZone != null){
                  	  toProvince = toZone.getProvinceCode();
          }
          List<LtlSkidRate> groupLtlSkidRate = new ArrayList<LtlSkidRate>();
          List<LtlSkidRate> tempgroupLtlSkidRate = new ArrayList<LtlSkidRate>();
          List<com.meritconinc.shiplinx.model.Package> packageList = order.getPackages();
          List<BigDecimal> lengthList = new ArrayList<BigDecimal>();
          List<BigDecimal> widthList = new ArrayList<BigDecimal>();
          List<BigDecimal> heightList = new ArrayList<BigDecimal>();
          List<Float> weightList = new ArrayList<Float>();
          for (com.meritconinc.shiplinx.model.Package pack : packageList) {
            lengthList.add(pack.getLength());
            widthList.add(pack.getWidth());
            weightList.add(pack.getWeight().floatValue());
            heightList.add(pack.getHeight());
          }
          long maxLength = 0l, maxWidth = 0l, maxHeight = 0l, maxWeight = 0l;
          if (lengthList != null && lengthList.size() > 0) {
            maxLength = Collections.max(lengthList).longValue();
          }
          if (widthList != null && widthList.size() > 0) {
            maxWidth = Collections.max(widthList).longValue();
          }

          if (heightList != null && heightList.size() > 0) {
            maxHeight = Collections.max(heightList).longValue();
          }

          if (weightList != null && weightList.size() > 0) {
            maxWeight = Collections.max(weightList).longValue();
          }
          LtlSkidRate ltlSkidRate = null;
          /*if (fromZoneName != null && toZoneName != null && !fromZoneName.isEmpty()
              && !toZoneName.isEmpty()) {
            groupLtlSkidRate = markupManagerService.groupingLTLSkidRate(serviceId, maxLength,
                maxWidth, maxHeight, maxWeight, fromZoneName, toZoneName);*/
          List<Zone> overAllfromZones = new ArrayList<Zone>();
                    List<Zone> overAlltoZones = new ArrayList<Zone>();
                    /*overAllfromZones=markupManagerService.getOverallZones(fromCity,fromZoneStructureId);
                    overAlltoZones=markupManagerService.getOverallZones(toCity,toZoneStructureId);*/
                    overAllfromZones=markupManagerService.getOverallZones(fromCity,fromZoneStructureId,fromCountry,fromProvince);
                                        overAlltoZones=markupManagerService.getOverallZones(toCity,toZoneStructureId,toCountry,toProvince);
          
                    if (overAllfromZones != null && overAlltoZones != null && !overAllfromZones.isEmpty()
                        && !overAlltoZones.isEmpty()) {
                  	  for(Zone fz : overAllfromZones){
                  		  for(Zone tz : overAlltoZones ){
                  			  tempgroupLtlSkidRate = markupManagerService.groupingLTLSkidRate(serviceId, maxLength,
                  		                maxWidth, maxHeight, maxWeight, fz.getZoneName(), tz.getZoneName());
                  			  if(tempgroupLtlSkidRate!=null && !tempgroupLtlSkidRate.isEmpty() && tempgroupLtlSkidRate.size()>0){
                  				  for(LtlSkidRate lsr:tempgroupLtlSkidRate){
                  				  groupLtlSkidRate.add(lsr);
                  				  }
                  			  }
                  		  }
                  	  } 
          }

          if (groupLtlSkidRate != null && groupLtlSkidRate.size() > 0) {
            for (j = 0; j < groupLtlSkidRate.size(); j++) {
              for (x = 0; x < ratingList.size(); x++) {
                if (!groupLtlSkidRate.get(j).getServiceId().equals(service.getMasterServiceId())
                    && groupLtlSkidRate.get(j).getServiceId()
                        .equals(ratingList.get(x).getServiceId())
                    && service.getMasterServiceId() > 0) {
                  groupLtlSkidRate.remove(j);
                  break;
                }
              }
            }
            
            /*if(groupLtlSkidRate != null && groupLtlSkidRate.size()>1){
            	                Collections.sort(groupLtlSkidRate, Rating.PriceComparatorForPound);
            	            }*/
            if (groupLtlSkidRate.size() > 1) {
              Collections.sort(groupLtlSkidRate, LtlSkidRate.LtlSkidFscPercentage);
            }
            if (groupLtlSkidRate.size() > 1 && ratingList.get(i).getBillWeight() <= 500
                && order.getQuantity().intValue() == 1) {
              Collections.sort(groupLtlSkidRate, LtlSkidRate.LtlSkidFlatRateComparator);
             /* for (LtlSkidRate chepestFlatRate : groupLtlSkidRate) {
                if (chepestFlatRate.getRateFlatWeight() == 0) {
                  groupLtlSkidRate.remove(chepestFlatRate);
                }
              }*/
        	  Iterator<LtlSkidRate> ite = groupLtlSkidRate.iterator();
        	          	  while(ite.hasNext()) {
        	          		 LtlSkidRate chepestFlatRate = ite.next();		  
        	          		 if(chepestFlatRate.getRateFlatWeight() == 0)
        	          			 ite.remove();	  
        	          		 }
            } else if (groupLtlSkidRate.size() > 1 && ratingList.get(i).getBillWeight() > 500) {
              // Collections.sort(groupLtlSkidRate, LtlSkidRate.LtlSkidRateComparator);
              rateCompare(order.getQuantity().intValue(), groupLtlSkidRate);
            }
            if (groupLtlSkidRate != null && !groupLtlSkidRate.isEmpty()) {
              ltlSkidRate = groupLtlSkidRate.get(0);
            }
          }

          double rate = 0;
          long width = 0l;
          long length = 0l;
          long height = 0l;
          long weight = 0l;
          Double totWeight = order.getTotalWeight();
          if (service.getServiceType() == SERVICE_TYPE_LTL_SKID) {

            /* List<com.meritconinc.shiplinx.model.Package> packageList = order.getPackages(); */
            for (com.meritconinc.shiplinx.model.Package package1 : packageList) {
              if ((service.getMaxWidth() != width && (package1.getWidth().longValue() > service
                  .getMaxWidth()))
                  || (service.getMaxHeight() != height && (package1.getHeight().longValue() > service
                      .getMaxHeight()))
                  || (service.getMaxLength() != length && (package1.getLength().longValue() > service
                      .getMaxLength()))
                  || (service.getMaxWeight() != weight && (package1.getWeight().longValue() > service
                      .getMaxWeight()))) {
                totWeight = new Double(0L);
                break;
              }

              /*
               * else if ((package1.getWidth().longValue() > 48) ||
               * (package1.getHeight().longValue() > 90) || (package1.getLength().longValue() > 48))
               * { totWeight =new Double(0L); break; }
               */
            }
          }
          if (ltlSkidRate != null) {
            /*
             * if (totWeight != null && totWeight > 0) { if (totWeight != null && totWeight > 0 &&
             * totWeight < 500 && (length < 49 || width < 49) && height > 48) { rate =
             * ltlSkidRate.getRateSkid1(); } else if (totWeight != null && totWeight > 0 &&
             * totWeight < 500 && ltlSkidRate.getRateFlatWeight() != null &&
             * ltlSkidRate.getRateFlatWeight().doubleValue() > 0) { rate =
             * ltlSkidRate.getRateFlatWeight().doubleValue(); } else if (order.getQuantity() != null
             * && order.getQuantity().intValue() > 0) { rate =
             * ltlSkidRate.getSkidRate(order.getQuantity().intValue()); } }
             */
            if (totWeight != null && totWeight > 0 && totWeight < 500 && maxLength > 0
                && maxWidth > 0 && maxHeight > 0 && maxLength < 49 && maxWidth < 49
                && maxHeight < 49 && ltlSkidRate.getRateFlatWeight() != null
                && ltlSkidRate.getRateFlatWeight().doubleValue() > 0) {
              rate = ltlSkidRate.getRateFlatWeight().doubleValue();
            } else if (totWeight != null && totWeight > 0 && totWeight < 500 && maxLength > 0
                && maxWidth > 0 && maxHeight > 0 && maxLength <= service.getMaxLength()
                && maxWidth <= service.getMaxWidth() && maxHeight <= service.getMaxHeight()) {
              rate = ltlSkidRate.getRateSkid1();
            } else if (order.getQuantity() != null && order.getQuantity().intValue() > 0) {
              rate = ltlSkidRate.getSkidRate(order.getQuantity().intValue());
            }
            
            Charge freightCharge = new Charge();
            Charge fuelCharge = new Charge();
            freightCharge.setChargeCode(ShiplinxConstants.GROUP_FREIGHT_CHARGE);
            freightCharge.setName(ShiplinxConstants.FREIGHT_STRING);
            freightCharge.setCost(rate);
           // fuelCharge.setChargeCode(ShiplinxConstants.GROUP_FUEL_CHARGE);
            fuelCharge.setChargeCode(ShiplinxConstants.GROUP_FUEL_SURCHARGE);
                        fuelCharge.setName(ShiplinxConstants.FUEL_SURCHARGE);
                        fuelCharge.setCost(rate * ltlSkidRate.getFscPercent().doubleValue() / 100);
                        double totalCost = freightCharge.getCost()+fuelCharge.getCost();
                                    List<Charge> charges = ratingList.get(i).getCharges();
                                    ratingList.get(i).getCharges().removeAll(ratingList.get(i).getCharges());
                                    Markup markup1 = new Markup(); 
                                    markup1 = markupManagerService.getMarkupObj(order);
                                    markup1.setServiceId(service.getMasterServiceId());  
                                    Markup baseMarkup=markupManagerService.findBaseMarkup(markup1);
                                    boolean baseMarkupFlatText=false;
                                                if(baseMarkup==null){
                                                	Markup mark=markup1;
                                                	mark.setCustomerId(0l);
                                                	baseMarkup=markupManagerService.findBaseMarkup(mark);
                                                }
                                                Charge baseFreightCharge=new Charge();
                                                Charge baseFuelCharge=new Charge();
                                   if(ratingList.get(i).getVariable()==1){
                                    	markup1.setFromWeight(totalCost);
                                        markup1.setToWeight(totalCost);
                                        markup1 = markupManagerService.getUniqueMarkupList(markup1);         
                                        if (markup1 != null) {
                                            ratingList.get(i).setMarkupPercentage(markup1.getMarkupPercentage());
                                            ratingList.get(i).setMarkupTypeText(markup1.getTypeText());
                                            ratingList.get(i).setMarkupType(markup1.getType());
                                            ratingList.get(i).setMarkupFlat(markup1.getMarkupFlat());
                                          }            
                                          else {
                                            ratingList.get(i).setMarkupPercentage(0);
                                          }                   	
                                                	if(markup1 !=null && totalCost<=markup1.getToWeight()){
                                                		/*if (markup1.getMarkupPercentage() != 0
                                                                || (markup1.getMarkupPercentage() == 0 && markup1
                                                                    .getMarkupFlat() == 0)) {
                                                              freightCharge.setCharge(freightCharge.getCost()
                                                                  + (freightCharge.getCost() * markup1.getMarkupPercentage() / 100));
                                                              fuelCharge.setCharge(fuelCharge.getCost()
                                                                  + (fuelCharge.getCost() * markup1.getMarkupPercentage() / 100));
                                                            }
                                                		if (markup1.getMarkupPercentage() == 0
                                                                && markup1.getMarkupFlat() != 0) {
                                                		freightCharge.setCharge(freightCharge.getCost() + markup1.getMarkupFlat());
                             if(fuelCharge.getCost()>0){
                            	 fuelCharge.setCharge(fuelCharge.getCost() + markup1.getMarkupFlat());
                               	}else{
                               		 fuelCharge.setCharge(0.0);
                               	}
                                 ratingList.get(i).setMarkupTypeText("Flat");
                                                		}
                                                		if(baseMarkup!=null){
                                                			                        			if (baseMarkup.getMarkupPercentage() != 0
                                                			                                            || (baseMarkup.getMarkupPercentage() == 0 && baseMarkup
                                                			                                                .getMarkupFlat() == 0)) {
                                                			                        				baseFreightCharge.setCharge(freightCharge.getCost()
                                                			                                                + (freightCharge.getCost() * baseMarkup.getMarkupPercentage() / 100));
                                                			                        				baseFuelCharge.setCharge(fuelCharge.getCost()
                                                			                                               + (fuelCharge.getCost() * baseMarkup.getMarkupPercentage() / 100));  
                                                			                        				
                                                			                                          }else if (baseMarkup.getMarkupPercentage() == 0
                                                			                                              && baseMarkup.getMarkupFlat() != 0) {
                                                			                                        	  baseFreightCharge.setCharge(freightCharge.getCost() + baseMarkup.getMarkupFlat());
                                                			                                        	  if(fuelCharge.getCost()>0){
                                                			                                        		  baseFuelCharge.setCharge(fuelCharge.getCost() + baseMarkup.getMarkupFlat());
                                                			                                                	}else{
                                                			                                                		baseFuelCharge.setCharge(0.0);
                                                			                                                	}
                                                			                                       	  baseMarkupFlatText=true;                        				
                                                			                        			}
                                                			                        			if(baseFreightCharge.getCharge()>freightCharge.getCharge()){
                                                			                        				freightCharge.setCharge(baseFreightCharge.getCharge());
                                                			                        				if(fuelCharge.getCost()>0){
                                                			                        				fuelCharge.setCharge(baseFuelCharge.getCharge());
                                                			                        					}
                                                			                        				if(baseMarkupFlatText){
                                                			                        					ratingList.get(i).setMarkupTypeText("Flat");
                                                			                        				}else{
                                                			                        					ratingList.get(i).setMarkupTypeText(baseMarkup.getTypeText());
                                                			                        				}
                                                			                        				ratingList.get(i).setMarkupPercentage(baseMarkup.getMarkupPercentage());                                       
                                                			                                        ratingList.get(i).setMarkupType(baseMarkup.getType());                                        
                                                			                                        ratingList.get(i).setMarkupFlat(baseMarkup.getMarkupFlat());*/
                                                			                        	//	}   
                                                		//}
                                                		if (markup1.getMarkupPercentage() != 0 && markup1
                                                                .getMarkupFlat() == 0) {
                                                          freightCharge.setCharge(freightCharge.getCost()
                                                              + (freightCharge.getCost() * markup1.getMarkupPercentage() / 100));
                                                          fuelCharge.setCharge(fuelCharge.getCost()
                                                              + (fuelCharge.getCost() * markup1.getMarkupPercentage() / 100));
                                                        }
                                            		
                                            		if (markup1.getMarkupPercentage() == 0
                                                            && markup1.getMarkupFlat() != 0) {
                                            		freightCharge.setCharge(freightCharge.getCost() + markup1.getMarkupFlat());
                        /* if(fuelCharge.getCost()>0){
                        	 fuelCharge.setCharge(fuelCharge.getCost() + markup1.getMarkupFlat());
                           	}else{
                           		 fuelCharge.setCharge(0.0);
                           	}*/
                             ratingList.get(i).setMarkupTypeText("Flat");
                                            		}
                                            		if(markup1.getMarkupPercentage() != 0 && markup1.getMarkupFlat() != 0){
                                            			
                                            			 double freightCharge1,fuelCharge1;
                     	                            	   freightCharge1= (freightCharge.getCost() + (freightCharge.getCost() * markup1.getMarkupPercentage() / 100));
                     	                            	   fuelCharge1= (fuelCharge.getCost() + (fuelCharge.getCost() * markup1.getMarkupPercentage() / 100));
                     	                            	   double frieghtChargeFlat,fuelChargeFlat;
                     	                            	   frieghtChargeFlat=  (freightCharge.getCost() + markup1.getMarkupFlat());
                     	                            	   fuelChargeFlat=  (fuelCharge.getCost() + markup1.getMarkupFlat());
                     	                            	   
                     	                            	   double totalcostPercentage;
                    	                            	   double totalFlat;
                     	                            	   totalcostPercentage=freightCharge1+fuelCharge1;
                     	                            	   totalFlat=freightCharge.getCost()+fuelCharge.getCost()+markup1.getMarkupFlat();
                     	                            	   if(totalcostPercentage>totalFlat){
                     	                            		   freightCharge.setCharge(freightCharge1);
                    	                            		   fuelCharge.setCharge(fuelCharge1);
                     	                            		   
                     	                            		  
                     	                            	   }
                     	                            	   else{
                     	                            		   frieghtChargeFlat=freightCharge.getCost()+markup1.getMarkupFlat();
                     	                            		   freightCharge.setCharge(frieghtChargeFlat);
                    	                            		   fuelCharge.setCharge(fuelCharge.getCost());
                     	                            	   }
                                            		}
                                            		
                                            		 if(markup1.getMarkupPercentage() == 0 && markup1.getMarkupFlat() == 0){
              	                            	   freightCharge.setCharge(freightCharge.getCost());
                	                               fuelCharge.setCharge(fuelCharge.getCost());
              	                            	   
              	                               }
                                            		 
                                            		if(baseMarkup!=null){
            	                        			if (baseMarkup.getMarkupPercentage() != 0
            	                                            || (baseMarkup.getMarkupPercentage() == 0 && baseMarkup
            	                                                .getMarkupFlat() == 0)) {
            	                        				baseFreightCharge.setCharge(freightCharge.getCost()
            	                                                + (freightCharge.getCost() * baseMarkup.getMarkupPercentage() / 100));
            	                        				baseFuelCharge.setCharge(fuelCharge.getCost()
            	                                               + (fuelCharge.getCost() * baseMarkup.getMarkupPercentage() / 100));  
            	                        				
            	                                          }else if (baseMarkup.getMarkupPercentage() == 0
            	                                              && baseMarkup.getMarkupFlat() != 0) {
            	                                        	  baseFreightCharge.setCharge(freightCharge.getCost() + baseMarkup.getMarkupFlat());
            	                                        	 /* if(fuelCharge.getCost()>0){
            	                                        		  baseFuelCharge.setCharge(fuelCharge.getCost() + baseMarkup.getMarkupFlat());
            	                                                	}else{
            	                                                		baseFuelCharge.setCharge(0.0);
            	                                                	}*/
            	                                       	  baseMarkupFlatText=true;                        				
            	                        			}
            	                        			
            	                        			//vivek hide
            	                        		/*	if(baseMarkup.getMarkupPercentage()!=0 && baseMarkup.getMarkupFlat()!=0)
            	                        				            	                        				            	                        				            	                                    {
            	                        				            	                        				            	                        				            	                                  	  double totalpercentage;
            	                        				            	                        				           	                        				            	                                  	  totalpercentage=freightCharge.getCost()
            	                        				            	                        				            	                        				            	                                                + (freightCharge.getCost() * baseMarkup.getMarkupPercentage() / 100)+(fuelCharge.getCost()
            	                        				            	                        				            	                        				            	                                                + (fuelCharge.getCost() * baseMarkup.getMarkupPercentage() / 100));
            	                        				            	                        				            	                        				            	                                  	  double totalFlat;
            	                        				            	                        				            	                        				            	                                  	  totalFlat=freightCharge.getCost() + baseMarkup.getMarkupFlat()+fuelCharge.getCost();
            	                        				            	                        				            	                        				            	                                  	  if(totalpercentage>totalFlat)
            	                        				            	                        				            	                        				            	                                  	  {
            	                        				            	                        				            	                        				            	                                  	  baseFreightCharge.setCharge(freightCharge.getCost()
            	                        				            	                        				            	                        				            	                                                + (freightCharge.getCost() * baseMarkup.getMarkupPercentage() / 100));
            	                        				            	                        				            	                        				            	                        				baseFuelCharge.setCharge(fuelCharge.getCost()
            	                        				            	                        				            	                        				            	                                                + (fuelCharge.getCost() * baseMarkup.getMarkupPercentage() / 100)); 
            	                        				            	                        				            	                        				            	                                  	  }
            	                        				            	                        				            	                        				            	                                  	  else
            	                        				            	                        				            	                        				            	                                  	  {
            	                        				            	                        				            	                        				            	                                  		  baseFreightCharge.setCharge(freightCharge.getCost() + baseMarkup.getMarkupFlat());
            	                        				            	                        				            	                        				            	                                  		  baseFuelCharge.setCharge(fuelCharge.getCost());
            	                        				            	                        				            	                        				            	                                  		  baseMarkupFlatText=true;   
            	                        				            	                        				            	                        				            	                                  	  }
            	                        				            	                        				            	                        				            	                        				
            	                        				           	                        				            	                        				            	                                    }*/

            	                        			if(baseFreightCharge.getCharge()>freightCharge.getCharge()){
            	                        				freightCharge.setCharge(baseFreightCharge.getCharge());
            	                        				if(fuelCharge.getCost()>0){
            	                        				fuelCharge.setCharge(baseFuelCharge.getCharge());
            	                        					}
            	                        				if(baseMarkupFlatText){
            	                        					ratingList.get(i).setMarkupTypeText("Flat");
            	                        				}else{
            	                        					ratingList.get(i).setMarkupTypeText(baseMarkup.getTypeText());
            	                        				}
            	                        				ratingList.get(i).setMarkupPercentage(baseMarkup.getMarkupPercentage());                                       
            	                                        ratingList.get(i).setMarkupType(baseMarkup.getType());                                        
            	                                        ratingList.get(i).setMarkupFlat(baseMarkup.getMarkupFlat());
            	                        		}   
                                        }
                        }
                                   }else{                        	           
                                	                               	/*markup1 = markupManagerService.getUniqueMarkup(markup1);            
                                	                              if (markup1 != null) {
                                	                                 ratingList.get(i).setMarkupPercentage(markup1.getMarkupPercentage());
                                	                                 ratingList.get(i).setMarkupTypeText(markup1.getTypeText());
                                	                                 ratingList.get(i).setMarkupType(markup1.getType());
                                	                                 ratingList.get(i).setMarkupFlat(markup1.getMarkupFlat());
                                	                               }            
                                	                               else {
                                	                                 ratingList.get(i).setMarkupPercentage(0);
                                	                               } 
                                	                               if (ratingList.get(i).getMarkupPercentage() != 0
                                	                                       || (ratingList.get(i).getMarkupPercentage() == 0 && ratingList.get(i)
                                	                                           .getMarkupFlat() == 0)) {
                                	                                     freightCharge.setCharge(freightCharge.getCost()
                                	                                         + (freightCharge.getCost() * ratingList.get(i).getMarkupPercentage() / 100));
                                	                                     fuelCharge.setCharge(fuelCharge.getCost()
                                	                                         + (fuelCharge.getCost() * ratingList.get(i).getMarkupPercentage() / 100));
                                	                                   } else if (ratingList.get(i).getMarkupPercentage() == 0
                                	                                       && ratingList.get(i).getMarkupFlat() != 0) {
                                	                                   	freightCharge.setCharge(freightCharge.getCost() + ratingList.get(i).getMarkupFlat());
                                	                                  	              if(fuelCharge.getCost()>0){
                                	                                  	            	                  fuelCharge.setCharge(fuelCharge.getCost() + ratingList.get(i).getMarkupFlat());
                                	                                   	            	                	}else{
                                	                                   	            	                		 fuelCharge.setCharge(0.0);
                                	                                   	            	                	}
                                	                                     ratingList.get(i).setMarkupTypeText("Flat");
                                	                                   }
                                	                               if(baseMarkup!=null){
                                	                            	                       			if (baseMarkup.getMarkupPercentage() != 0
                                	                            	                                           || (baseMarkup.getMarkupPercentage() == 0 && baseMarkup
                                	                            	                                              .getMarkupFlat() == 0)) {
                                	                            	                       				baseFreightCharge.setCharge(freightCharge.getCost()
                                	                            	                                               + (freightCharge.getCost() * baseMarkup.getMarkupPercentage() / 100));
                                	                            	                       				baseFuelCharge.setCharge(fuelCharge.getCost()
                                	                            	                                               + (fuelCharge.getCost() * baseMarkup.getMarkupPercentage() / 100));  
                                	                            	                       				
                                	                            	                                         }else if (baseMarkup.getMarkupPercentage() == 0
                                	                            	                                             && baseMarkup.getMarkupFlat() != 0) {
                                	                            	                                       	  baseFreightCharge.setCharge(freightCharge.getCost() + baseMarkup.getMarkupFlat());
                                	                            	                                       	  if(fuelCharge.getCost()>0){
                                	                            	                                       		  baseFuelCharge.setCharge(fuelCharge.getCost() + baseMarkup.getMarkupFlat());
                                	                            	                                               	}else{
                                	                            	                                               		baseFuelCharge.setCharge(0.0);
                                	                            	                                               	}
                                	                            	                                       	  baseMarkupFlatText=true;                        				
                                	                            	                       			}
                                	                            	                       			if(baseFreightCharge.getCharge()>freightCharge.getCharge()){
                                	                            	                       				freightCharge.setCharge(baseFreightCharge.getCharge());
                                	                            	                       				if(fuelCharge.getCost()>0){
                                	                            	                      				fuelCharge.setCharge(baseFuelCharge.getCharge());
                                	                            	                       					}
                                	                            	                       				if(baseMarkupFlatText){
                                	                            	                       					ratingList.get(i).setMarkupTypeText("Flat");
                                	                            	                       				}else{
                                	                            	                       					ratingList.get(i).setMarkupTypeText(baseMarkup.getTypeText());
                                	                            	                      				}
                                	                            	                       				ratingList.get(i).setMarkupPercentage(baseMarkup.getMarkupPercentage());                                       
                                	                            	                                       ratingList.get(i).setMarkupType(baseMarkup.getType());                                        
                                	                            	                                       ratingList.get(i).setMarkupFlat(baseMarkup.getMarkupFlat());
                                	                            	                      		}
                                	                            	                       }
                                	                            	                          }*/
                                	   markup1 = markupManagerService.getUniqueMarkup(markup1);            
     	                              if (markup1 != null) {
     	                                 ratingList.get(i).setMarkupPercentage(markup1.getMarkupPercentage());
     	                                 ratingList.get(i).setMarkupTypeText(markup1.getTypeText());
     	                                 ratingList.get(i).setMarkupType(markup1.getType());
     	                                 ratingList.get(i).setMarkupFlat(markup1.getMarkupFlat());
     	                               }            
     	                               else {
     	                                 ratingList.get(i).setMarkupPercentage(0);
     	                               } 
     	                               if ((ratingList.get(i).getMarkupPercentage() != 0 && ratingList.get(i).getMarkupFlat() == 0)) {
     	                                     freightCharge.setCharge(freightCharge.getCost()
     	                                         + (freightCharge.getCost() * ratingList.get(i).getMarkupPercentage() / 100));
     	                                     fuelCharge.setCharge(fuelCharge.getCost()
     	                                         + (fuelCharge.getCost() * ratingList.get(i).getMarkupPercentage() / 100));
     	                                   } else if (ratingList.get(i).getMarkupPercentage() == 0&& ratingList.get(i).getMarkupFlat() != 0) {
     	                                   	freightCharge.setCharge(freightCharge.getCost() + ratingList.get(i).getMarkupFlat());
     	                                  	            /*  if(fuelCharge.getCost()>0){
     	                                  	            	                  fuelCharge.setCharge(fuelCharge.getCost() + ratingList.get(i).getMarkupFlat());
     	                                   	            	                	}else{
     	                                   	            	                		 fuelCharge.setCharge(0.0);
     	                                   	            	                	}*/
     	                                     ratingList.get(i).setMarkupTypeText("Flat");
     	                                   }
     	                               
     	                               if(ratingList.get(i).getMarkupPercentage() == 0 && ratingList.get(i).getMarkupFlat() == 0){
     	                            	   freightCharge.setCharge(freightCharge.getCost()
       	                                         + (freightCharge.getCost() * ratingList.get(i).getMarkupPercentage() / 100));
       	                                     fuelCharge.setCharge(fuelCharge.getCost()
       	                                         + (fuelCharge.getCost() * ratingList.get(i).getMarkupPercentage() / 100));
     	                            	   
     	                               }
     	                               
     	                               if(ratingList.get(i).getMarkupPercentage()!= 0 && ratingList.get(i).getMarkupFlat() != 0){
     	                            	   
    	                            	   double freightCharge1,fuelCharge1;
    	                            	   freightCharge1= (freightCharge.getCost() + (freightCharge.getCost() * ratingList.get(i).getMarkupPercentage() / 100));
    	                            	   fuelCharge1= (fuelCharge.getCost() + (fuelCharge.getCost() * ratingList.get(i).getMarkupPercentage() / 100));
    	                            	   double frieghtChargeFlat,fuelChargeFlat;
    	                            	   frieghtChargeFlat=  (freightCharge.getCost() + ratingList.get(i).getMarkupFlat());
    	                            	   fuelChargeFlat=  (fuelCharge.getCost() + ratingList.get(i).getMarkupFlat());
    	                            	   
    	                            	   double totalcostPercentage;
   	                            	   double totalFlat;
    	                            	   totalcostPercentage=freightCharge1+fuelCharge1;
    	                            	   totalFlat=freightCharge.getCost()+fuelCharge.getCost()+ratingList.get(i).getMarkupFlat();
    	                            	   
    	                            	   if(totalcostPercentage>totalFlat){
    	                            		   freightCharge.setCharge(freightCharge1);
   	                            		   fuelCharge.setCharge(fuelCharge1);
    	                            		   
    	                            		  } else{
    	                            		   frieghtChargeFlat=freightCharge.getCost()+ratingList.get(i).getMarkupFlat();
    	                            		   freightCharge.setCharge(frieghtChargeFlat);
   	                            		   fuelCharge.setCharge(fuelCharge.getCost());
    	                            	   }
    	                            	                         	   
                            	   }
                             	      if(baseMarkup!=null){
                			if (baseMarkup.getMarkupPercentage() != 0
                                    || (baseMarkup.getMarkupPercentage() == 0 && baseMarkup
                                       .getMarkupFlat() == 0)) {
                				baseFreightCharge.setCharge(freightCharge.getCost()
                                        + (freightCharge.getCost() * baseMarkup.getMarkupPercentage() / 100));
                				baseFuelCharge.setCharge(fuelCharge.getCost()
                                        + (fuelCharge.getCost() * baseMarkup.getMarkupPercentage() / 100));  
                				
                                  }else if (baseMarkup.getMarkupPercentage() == 0
                                      && baseMarkup.getMarkupFlat() != 0) {
                                	  baseFreightCharge.setCharge(freightCharge.getCost() + baseMarkup.getMarkupFlat());
                                	  /*if(fuelCharge.getCost()>0){
                                		  baseFuelCharge.setCharge(fuelCharge.getCost() + baseMarkup.getMarkupFlat());
                                        	}else{
                                        		baseFuelCharge.setCharge(0.0);
                                        	}*/
                                	  baseMarkupFlatText=true;                        				
                			}
                			
                			//vivek hide
                			/*if(baseMarkup.getMarkupPercentage()!=0 && baseMarkup.getMarkupFlat()!=0)
                				                				                				                                  {
                				                				                				                                	  double totalpercentage;
                				               				                				                                	  totalpercentage=freightCharge.getCost()
                				                				                				                                              + (freightCharge.getCost() * baseMarkup.getMarkupPercentage() / 100)+(fuelCharge.getCost()
                				                				                				                                              + (fuelCharge.getCost() * baseMarkup.getMarkupPercentage() / 100));
                				                				                				                                	  double totalFlat;
                				                				                				                                	  totalFlat=freightCharge.getCost() + baseMarkup.getMarkupFlat()+fuelCharge.getCost();
                				                				                				                                	  if(totalpercentage>totalFlat)
                				                				                				                                	  {
                				                				                				                                	  baseFreightCharge.setCharge(freightCharge.getCost()
                				                				                				                                              + (freightCharge.getCost() * baseMarkup.getMarkupPercentage() / 100));
                				                				                				                      				baseFuelCharge.setCharge(fuelCharge.getCost()
                				               				                				                                              + (fuelCharge.getCost() * baseMarkup.getMarkupPercentage() / 100)); 
                				                				                				                                	  }
                				                				                				                                	  else
                				                				               				                                	  {
                				                				                				                                		  baseFreightCharge.setCharge(freightCharge.getCost() + baseMarkup.getMarkupFlat());
                				                				                				                                		  baseFuelCharge.setCharge(fuelCharge.getCost());
                				                				                				                                		  baseMarkupFlatText=true;   
                				                				                				                                	  }
                				                				                				                      				
                				               				                				                                  }*/

                			if(baseFreightCharge.getCharge()>freightCharge.getCharge()){
                				freightCharge.setCharge(baseFreightCharge.getCharge());
                				if(fuelCharge.getCost()>0){
               				fuelCharge.setCharge(baseFuelCharge.getCharge());
                					}
                				if(baseMarkupFlatText){
                					ratingList.get(i).setMarkupTypeText("Flat");
                				}else{
                					ratingList.get(i).setMarkupTypeText(baseMarkup.getTypeText());
               				}
                				ratingList.get(i).setMarkupPercentage(baseMarkup.getMarkupPercentage());                                       
                                ratingList.get(i).setMarkupType(baseMarkup.getType());                                        
                                ratingList.get(i).setMarkupFlat(baseMarkup.getMarkupFlat());
               		}
                }
            }
                               if(freightCharge!=null && freightCharge.getCharge()==null){
                            	   freightCharge.setCharge(0.0);
                            	   ratingList.get(i).getCharges().add(freightCharge); 
                               }else{
                        ratingList.get(i).getCharges().add(freightCharge);   
                               }
                               if(fuelCharge!=null && fuelCharge.getCharge()==null){
                            	   fuelCharge.setCharge(0.0);
                            	   ratingList.get(i).getCharges().add(fuelCharge);
                               }else{
                            	   ratingList.get(i).getCharges().add(fuelCharge);
                               }
            ratingList.get(i).setCharges(ratingList.get(i).getCharges());
            double total = freightCharge.getCharge() + fuelCharge.getCharge();
            ratingList.get(i).setTotalCost(freightCharge.getCost() + fuelCharge.getCost());
            ratingList.get(i).setTotal(total);
          }
          else{
        	  
              
           	 if(ratingList.get(i).getMarkupPercentage()!=null && ratingList.get(i).getMarkupPercentage()!= 0 &&  ratingList.get(i).getMarkupFlat() != 0 &&ratingList.get(i).getTotalCost()!=0.0){
             	  double totalCost=ratingList.get(i).getTotalCost();
             	  double totalCostPer=0;
             	  double totalCostFlat=0;
             	
             	           	  
             	             	  
             	   totalCostPer=totalCostPer+(totalCost+(totalCost*ratingList.get(i).getMarkupPercentage()/100));
             	  totalCostFlat=totalCostFlat+totalCost+ratingList.get(i).getMarkupFlat();
                	   if(totalCostPer>totalCostFlat){
                		for(Charge cha:ratingList.get(i).getCharges()){
               		
                			if(cha.getChargeCodeLevel2() != null && !(cha.getChargeCodeLevel2().isEmpty())){
                					                				int k=shippingService.checkAccessorial(cha.getChargeCodeLevel2());
                					                				if(k==0)
                					                           		  cha.setCharge(cha.getCost()+cha.getCost()*ratingList.get(i).getMarkupPercentage()/100);
                					                			}else{
                					                				cha.setCharge(cha.getCost()+cha.getCost()*ratingList.get(i).getMarkupPercentage()/100);
                					                			}
                					                		}
                		   ratingList.get(i).setTotal(totalCostPer);
                	   }else{
                		
                   	ratingList.get(i).getCharges().get(0).setCharge(ratingList.get(i).getCharges().get(0).getCost()+ratingList.get(i).getMarkupFlat());
                 		  
                 	 ratingList.get(i).getCharges().get(1).setCharge(ratingList.get(i).getCharges().get(1).getCost());    
                		
                		  ratingList.get(i).setTotal(totalCostFlat);
                		   
                	   }
                	   
                	             	                         	   
        	   }
           	
           }
        }
      }
      }
      Collections.sort(ratingList, Rating.PriceComparator);
    }
  }

  public void rateCompare(int quantity, List<LtlSkidRate> groupLtlSkidRate) {
    switch (quantity) {
    case 1:
      Collections.sort(groupLtlSkidRate, LtlSkidRate.LtlSkidRateComparator1);
      for (LtlSkidRate chepestRate : groupLtlSkidRate) {
        if (chepestRate.getRateSkid1() == 0) {
          groupLtlSkidRate.remove(chepestRate);
        }

      }
      break;
    case 2:
      Collections.sort(groupLtlSkidRate, LtlSkidRate.LtlSkidRateComparator2);
      for (LtlSkidRate chepestRate : groupLtlSkidRate) {
        if (chepestRate.getRateSkid2() == 0) {
          groupLtlSkidRate.remove(chepestRate);
        }
      }
      break;
    case 3:
      Collections.sort(groupLtlSkidRate, LtlSkidRate.LtlSkidRateComparator3);
      for (LtlSkidRate chepestRate : groupLtlSkidRate) {
        if (chepestRate.getRateSkid3() == 0) {
          groupLtlSkidRate.remove(chepestRate);
        }
      }
      break;
    case 4:
      Collections.sort(groupLtlSkidRate, LtlSkidRate.LtlSkidRateComparator4);
      for (LtlSkidRate chepestRate : groupLtlSkidRate) {
        if (chepestRate.getRateSkid4() == 0) {
          groupLtlSkidRate.remove(chepestRate);
        }
      }
      break;
    case 5:
      Collections.sort(groupLtlSkidRate, LtlSkidRate.LtlSkidRateComparator5);
      for (LtlSkidRate chepestRate : groupLtlSkidRate) {
        if (chepestRate.getRateSkid5() == 0) {
          groupLtlSkidRate.remove(chepestRate);
        }
      }
      break;
    case 6:
      Collections.sort(groupLtlSkidRate, LtlSkidRate.LtlSkidRateComparator6);
      for (LtlSkidRate chepestRate : groupLtlSkidRate) {
        if (chepestRate.getRateSkid6() == 0) {
          groupLtlSkidRate.remove(chepestRate);
        }
      }
      break;
    case 7:
      for (LtlSkidRate chepestRate : groupLtlSkidRate) {
        if (chepestRate.getRateSkid7() == 0) {
          groupLtlSkidRate.remove(chepestRate);
        }
      }
      break;
    case 8:
      Collections.sort(groupLtlSkidRate, LtlSkidRate.LtlSkidRateComparator8);
      for (LtlSkidRate chepestRate : groupLtlSkidRate) {
        if (chepestRate.getRateSkid8() == 0) {
          groupLtlSkidRate.remove(chepestRate);
        }
      }
      break;
    case 9:
      Collections.sort(groupLtlSkidRate, LtlSkidRate.LtlSkidRateComparator9);
      for (LtlSkidRate chepestRate : groupLtlSkidRate) {
        if (chepestRate.getRateSkid9() == 0) {
          groupLtlSkidRate.remove(chepestRate);
        }
      }
      break;
    case 10:
      Collections.sort(groupLtlSkidRate, LtlSkidRate.LtlSkidRateComparator10);
      for (LtlSkidRate chepestRate : groupLtlSkidRate) {
        if (chepestRate.getRateSkid10() == 0) {
          groupLtlSkidRate.remove(chepestRate);
        }
      }
      break;
    case 11:
      Collections.sort(groupLtlSkidRate, LtlSkidRate.LtlSkidRateComparator11);
      for (LtlSkidRate chepestRate : groupLtlSkidRate) {
        if (chepestRate.getRateSkid11() == 0) {
          groupLtlSkidRate.remove(chepestRate);
        }
      }
      break;
    case 12:
      Collections.sort(groupLtlSkidRate, LtlSkidRate.LtlSkidRateComparator12);
      for (LtlSkidRate chepestRate : groupLtlSkidRate) {
        if (chepestRate.getRateSkid12() == 0) {
          groupLtlSkidRate.remove(chepestRate);
        }
      }
      break;
    case 13:
      Collections.sort(groupLtlSkidRate, LtlSkidRate.LtlSkidRateComparator13);
      for (LtlSkidRate chepestRate : groupLtlSkidRate) {
        if (chepestRate.getRateSkid13() == 0) {
          groupLtlSkidRate.remove(chepestRate);
        }
      }
      break;
    case 14:
      Collections.sort(groupLtlSkidRate, LtlSkidRate.LtlSkidRateComparator14);
      for (LtlSkidRate chepestRate : groupLtlSkidRate) {
        if (chepestRate.getRateSkid14() == 0) {
          groupLtlSkidRate.remove(chepestRate);
        }
      }
      break;
    case 15:
      Collections.sort(groupLtlSkidRate, LtlSkidRate.LtlSkidRateComparator15);
      for (LtlSkidRate chepestRate : groupLtlSkidRate) {
        if (chepestRate.getRateSkid15() == 0) {
          groupLtlSkidRate.remove(chepestRate);
        }
      }
      break;
    case 16:
      Collections.sort(groupLtlSkidRate, LtlSkidRate.LtlSkidRateComparator16);
      for (LtlSkidRate chepestRate : groupLtlSkidRate) {
        if (chepestRate.getRateSkid16() == 0) {
          groupLtlSkidRate.remove(chepestRate);
        }
      }
      break;
    case 17:
      Collections.sort(groupLtlSkidRate, LtlSkidRate.LtlSkidRateComparator17);
      for (LtlSkidRate chepestRate : groupLtlSkidRate) {
        if (chepestRate.getRateSkid17() == 0) {
          groupLtlSkidRate.remove(chepestRate);
        }
      }
      break;
    case 18:
      Collections.sort(groupLtlSkidRate, LtlSkidRate.LtlSkidRateComparator18);
      for (LtlSkidRate chepestRate : groupLtlSkidRate) {
        if (chepestRate.getRateSkid18() == 0) {
          groupLtlSkidRate.remove(chepestRate);
        }
      }
      break;
    case 19:
      Collections.sort(groupLtlSkidRate, LtlSkidRate.LtlSkidRateComparator19);
      for (LtlSkidRate chepestRate : groupLtlSkidRate) {
        if (chepestRate.getRateSkid19() == 0) {
          groupLtlSkidRate.remove(chepestRate);
        }
      }
      break;
    case 20:
      Collections.sort(groupLtlSkidRate, LtlSkidRate.LtlSkidRateComparator20);
      for (LtlSkidRate chepestRate : groupLtlSkidRate) {
        if (chepestRate.getRateSkid20() == 0) {
          groupLtlSkidRate.remove(chepestRate);
        }
      }
      break;
    case 21:
      Collections.sort(groupLtlSkidRate, LtlSkidRate.LtlSkidRateComparator21);
      for (LtlSkidRate chepestRate : groupLtlSkidRate) {
        if (chepestRate.getRateSkid21() == 0) {
          groupLtlSkidRate.remove(chepestRate);
        }
      }
      break;
    case 22:
      Collections.sort(groupLtlSkidRate, LtlSkidRate.LtlSkidRateComparator22);
      for (LtlSkidRate chepestRate : groupLtlSkidRate) {
        if (chepestRate.getRateSkid22() == 0) {
          groupLtlSkidRate.remove(chepestRate);
        }
      }
      break;
    case 23:
      Collections.sort(groupLtlSkidRate, LtlSkidRate.LtlSkidRateComparator23);
      for (LtlSkidRate chepestRate : groupLtlSkidRate) {
        if (chepestRate.getRateSkid23() == 0) {
          groupLtlSkidRate.remove(chepestRate);
        }
      }
      break;
    case 24:
      Collections.sort(groupLtlSkidRate, LtlSkidRate.LtlSkidRateComparator24);
      for (LtlSkidRate chepestRate : groupLtlSkidRate) {
        if (chepestRate.getRateSkid24() == 0) {
          groupLtlSkidRate.remove(chepestRate);
        }
      }
      break;
    case 25:
      Collections.sort(groupLtlSkidRate, LtlSkidRate.LtlSkidRateComparator25);
      for (LtlSkidRate chepestRate : groupLtlSkidRate) {
        if (chepestRate.getRateSkid25() == 0) {
          groupLtlSkidRate.remove(chepestRate);
        }
      }
      break;
    case 26:
      Collections.sort(groupLtlSkidRate, LtlSkidRate.LtlSkidRateComparator26);
      for (LtlSkidRate chepestRate : groupLtlSkidRate) {
        if (chepestRate.getRateSkid26() == 0) {
          groupLtlSkidRate.remove(chepestRate);
        }
      }
      break;
    }
  }

  public void groupingServicesPoundRate(List<Rating> ratingList, ShippingOrder order) {
    int i, x, j;
    double icTotalCost = 0,purolatorFriTotalCost = 0;
    int removeIndex=0;
    for (i = 0; i < ratingList.size(); i++) {
      long serviceId = ratingList.get(i).getServiceId();
      Service service = getService(serviceId);
      if(serviceId==1005 && ratingList.get(i).getCarrierId()==ShiplinxConstants.CARRIER_GENERIC){
          // double total=ratingList.get(i).getTotal();
            icTotalCost=ratingList.get(i).getTotalCost();
           
          }
          if(serviceId==4100 && ratingList.get(i).getCarrierId()==ShiplinxConstants.CARRIER_PUROLATOR_FREIGHT){
              // double total=ratingList.get(i).getTotal();
                purolatorFriTotalCost=ratingList.get(i).getTotalCost();
                removeIndex=i;
              }
      if (service != null && service.getServiceType() == SERVICE_TYPE_LTL_POUND) {
        List<LtlPoundRate> groupLtlPoundRate = new ArrayList<LtlPoundRate>();
        LtlPoundRate ltlPoundRate = null;
        Zone fromZone = null, toZone = null;
        if (ratingList.get(i).getZoneStructureId() > 0) {
          fromZone = markupManagerService.findZone(ratingList.get(i).getZoneStructureId(),
              order.getFromAddress());
          toZone = markupManagerService.findZone(ratingList.get(i).getZoneStructureId(),
              order.getToAddress());
        } else {
          fromZone = markupManagerService.findZone(service.getZoneStructureId(),
              order.getFromAddress());
          toZone = markupManagerService
              .findZone(service.getZoneStructureId(), order.getToAddress());
        }
        List<com.meritconinc.shiplinx.model.Package> packages = order.getPackages();
        // for (int pack=0; pack<packages.size(); pack++) {
        LtlPoundRate poundRateTobeSearched = null;
        Double totalWeight = null;
        for (int pack = 0; pack < packages.size(); pack++) {
          String fClass = packages.get(pack).getFreightClass();
          if (fromZone != null && toZone != null) {
            if (ratingList.get(i).getSlaveServiceId() > 0) {
              poundRateTobeSearched = LtlPoundRate.getObject(order.getCustomerId(),
                  order.getBusinessId(), ratingList.get(i).getSlaveServiceId(),
                  fromZone.getZoneName(), toZone.getZoneName(), fClass);
            } else {
              poundRateTobeSearched = LtlPoundRate.getObject(order.getCustomerId(),
                  order.getBusinessId(), service.getId(), fromZone.getZoneName(),
                  toZone.getZoneName(), fClass);
            }
          }

          LtlPoundRate ltlPoundRateTobeSearched = null;
          if (poundRateTobeSearched != null) {
            ltlPoundRateTobeSearched = markupManagerService.getPoundRate(poundRateTobeSearched,
            		ratingList.get(i).getBillWeight());
          }
          if (ltlPoundRateTobeSearched == null) {
            poundRateTobeSearched.setCustomerId(0L);
            ltlPoundRateTobeSearched = markupManagerService.getPoundRate(poundRateTobeSearched,
            		ratingList.get(i).getBillWeight());
            if (ltlPoundRateTobeSearched != null
            		&& ratingList.get(i).getBillWeight() >= order
                    .getTotalWeightWithDimFactor(ltlPoundRateTobeSearched.getDimFactor())) {
            	totalWeight = ratingList.get(i).getBillWeight();
            } else if (ltlPoundRateTobeSearched != null) {
              totalWeight = order.getTotalWeightWithDimFactor(ltlPoundRateTobeSearched
                  .getDimFactor());

            }

            ltlPoundRateTobeSearched = markupManagerService.getPoundRate(poundRateTobeSearched,
            		ratingList.get(i).getBillWeight());
          }
          if (ltlPoundRateTobeSearched != null) {
            ltlPoundRateTobeSearched.setFreightClass(fClass);
          }
          if (ltlPoundRateTobeSearched != null) {
            groupLtlPoundRate = markupManagerService.groupingLTLPoundRate(serviceId,
                ltlPoundRateTobeSearched);

            // }

            if (groupLtlPoundRate != null && groupLtlPoundRate.size() > 0) {
              for (j = 0; j < groupLtlPoundRate.size(); j++) {
                for (x = 0; x < ratingList.size(); x++) {
                  long masterId = service.getMasterServiceId();
                  if ((groupLtlPoundRate.get(j).getServiceId() != masterId)
                      && (groupLtlPoundRate.get(j).getServiceId() == ratingList.get(x)
                          .getServiceId()) && (service.getMasterServiceId() > 0)) {
                    groupLtlPoundRate.remove(j);
                    break;
                  }
                }
              }
              if (groupLtlPoundRate.size() > 1) {
                Collections.sort(groupLtlPoundRate, LtlPoundRate.LtlPoundRateComparator);
              }
              ltlPoundRate = groupLtlPoundRate.get(0);
            }

            double rate = 0;
            Double totWeight = ratingList.get(i).getBillWeight();

            if (ltlPoundRate != null) {
              if (ltlPoundRate.getDimFactor() != null && ltlPoundRate.getDimFactor().signum() != 0) {
                if (ltlPoundRate.getDimFactor() != null) {
                  if (ltlPoundRate.getDimFactor().signum() != 0) {
                	  if (ratingList.get(i).getBillWeight() >= order.getTotalWeightWithDimFactor(ltlPoundRate
                        .getDimFactor())) {
                		  totWeight = ratingList.get(i).getBillWeight();
                    } else {
                      totWeight = order.getTotalWeightWithDimFactor(ltlPoundRate.getDimFactor());
                    }
                  }
                }
              }
              // ltlPoundRate = markupManagerService.getPoundRate(poundRateTobeSearched, totWeight);
              LtlPoundRate prRatedAs = null;
              double weightAsRated = 0;
              if (ltlPoundRate != null && ltlPoundRate.getRangeTo() != null) {
                weightAsRated = ltlPoundRate.getRangeTo().intValue() + 1;
                poundRateTobeSearched.setServiceId(ltlPoundRate.getServiceId());
                poundRateTobeSearched.setFromZone(ltlPoundRate.getFromZone());
                poundRateTobeSearched.setToZone(ltlPoundRate.getToZone());

                prRatedAs = markupManagerService.getPoundRate(poundRateTobeSearched, weightAsRated);
              }

              ratingList.get(i).getCharges().removeAll(ratingList.get(i).getCharges());
              Markup markup = markupManagerService.getMarkupObj(order);
              markup.setServiceId(service.getMasterServiceId());
              markup = markupManagerService.getUniqueMarkup(markup);
              if (markup != null) {
                ratingList.get(i).setMarkupPercentage(markup.getMarkupPercentage());
                ratingList.get(i).setMarkupTypeText(markup.getTypeText());
                ratingList.get(i).setMarkupType(markup.getType());
              } else {
                ratingList.get(i).setMarkupPercentage(0);
              }
             /* if (ratingList.get(i).getMarkupPercentage() == 0
            		                        && ratingList.get(i).getMarkupFlat() != 0) {                 	             
            		                   	  ratingList.get(i).setMarkupFlat(markup.getMarkupFlat());          	                  
            		                      ratingList.get(i).setMarkupTypeText("Flat");
            		                    }*/
              // Frieght Charge
              Charge c = new Charge();
              c.setChargeCode(ShiplinxConstants.GROUP_FREIGHT_CHARGE);
              c.setName(ShiplinxConstants.FREIGHT_STRING);
              c.setCost(ltlPoundRate.getRate());
              c.setCurrency(ltlPoundRate.getCurrency());
              c.setCost(c.getCost() * totWeight);
              ratingList.get(i).setCharge(c);
              if (prRatedAs != null) {
                Charge chargeRatedAs = new Charge();
                chargeRatedAs.setChargeCode(ShiplinxConstants.GROUP_FREIGHT_CHARGE);
                chargeRatedAs.setName(ShiplinxConstants.FREIGHT_STRING);
                chargeRatedAs.setCost(prRatedAs.getRate());
                chargeRatedAs.setCurrency(ltlPoundRate.getCurrency());
                chargeRatedAs.setCost(chargeRatedAs.getCost() * weightAsRated);
                if (chargeRatedAs.getCost().doubleValue() < c.getCost().doubleValue()) {
                  c = chargeRatedAs;
                  ltlPoundRate = prRatedAs;
                  ratingList.get(i).setRatedAsWeight(weightAsRated);
                }
              }

              if (c.getCost() < ltlPoundRate.getMinimum())
                c.setCost(ltlPoundRate.getMinimum());
              /*if (ratingList.get(i).getMarkupPercentage() == 0
            		                        && ratingList.get(i).getMarkupFlat() != 0) {  
            		              	  c.setCharge(c.getCost()+ratingList.get(i).getMarkupFlat());
            		                }else{*/
              c.setCharge(c.getCost()
                  + (c.getCost() * ratingList.get(i).getMarkupPercentage() / 100));
            		                /*}*/
              ratingList.get(i).getCharges().add(c);

              // Fuel Charge
              Charge charge = new Charge();
              charge.setChargeCode(ShiplinxConstants.GROUP_FUEL_CHARGE);
              charge.setName(ShiplinxConstants.FUEL_SURCHARGE);
              charge.setCost(c.getCost().doubleValue() * ltlPoundRate.getFscPercent().doubleValue()
                  / 100);
           /*   if (ratingList.get(i).getMarkupPercentage() == 0
            		                        && ratingList.get(i).getMarkupFlat() != 0) { 
            		              	  charge.setCharge(charge.getCost()+ratingList.get(i).getMarkupFlat());
            		                }else{*/
              charge.setCharge(charge.getCost()
                  + (charge.getCost() * ratingList.get(i).getMarkupPercentage() / 100));
            		                /*}*/
              charge.setCurrency(ltlPoundRate.getCurrency());
              ratingList.get(i).getCharges().add(charge);
              ratingList.get(i).setBillWeight((Long) Math.round(totWeight + 0.4));
              if (ltlPoundRate.getTtm1() != null)
                ratingList.get(i).setTransitDaysMin(ltlPoundRate.getTtm1());
              if (ltlPoundRate.getTtm2() != null && ltlPoundRate.getTtm2() > 0)
                ratingList.get(i).setTransitDays(ltlPoundRate.getTtm2());
              else
                ratingList.get(i).setTransitDays(ltlPoundRate.getTtm1());

              double total = c.getCharge() + charge.getCharge();
              ratingList.get(i).setTotalCost(FormattingUtil.roundFigureRates((c.getCost() + charge.getCost()), 2));
              ratingList.get(i).setTotal(total);

            }

          }

        }
        Collections.sort(ratingList, Rating.PriceComparator);

      }
    }
    if(icTotalCost !=0 && icTotalCost<purolatorFriTotalCost){
        ratingList.remove(removeIndex);
       }
  }

  public Rating setDefaultIC() {
    Rating rating = new Rating();
    Double defaultValue = 0.0;
    Integer defauktPercentage = 0;
    rating.setCarrierId(ShiplinxConstants.CARRIER_GENERIC);
    rating.setCarrierName(ShiplinxConstants.CARRIER_GENERIC_STRING);
    rating.setServiceId(ShiplinxConstants.DEFAULT_IC_SERVICE_ID);
    rating.setServiceName(ShiplinxConstants.DEFAULT_IC_SERVICE);
    rating.setTotal(defaultValue);
    rating.setTotalCost(defaultValue);
    rating.setMarkupPercentage(defauktPercentage);
    rating.setMarkupTypeText(ShiplinxConstants.TYPE_MARKUP_TEXT);
    Charge charge = new Charge();
    charge.setChargeCode(ShiplinxConstants.GROUP_FREIGHT_CHARGE);
    charge.setName(ShiplinxConstants.FREIGHT_STRING);
    charge.setCost(defaultValue);
    charge.setTariffRate(defaultValue);
    charge.setCharge(defaultValue);
    rating.setCharge(charge);
    Charge chargeFuel = new Charge();
    chargeFuel.setChargeCode(ShiplinxConstants.GROUP_FUEL_CHARGE);
    chargeFuel.setName(ShiplinxConstants.FUEL_SURCHARGE);
    chargeFuel.setCost(defaultValue);
    chargeFuel.setCharge(defaultValue);
    chargeFuel.setTariffRate(defaultValue);
    List<Charge> chargesList = new ArrayList<Charge>();
    chargesList.add(chargeFuel);
    chargesList.add(charge);
    rating.setCharges(chargesList);
    return rating;
    // ratingList.add(rating);
  }

  
  
  public boolean getSummaryLabel(ShippingOrder order,String shippingLabelFileName, OutputStream outputStream, User user){
		/*Customer customer = customerService.getCustomerInfoByCustomerId(order.getCustomerId(),
		        order.getBusinessId());*/
		ShippingDAO shippingDAO=(ShippingDAO)MmrBeanLocator.getInstance().findBean("shippingDAO");
		CurrencySymbol currencySymbol = new CurrencySymbol();
        if(order.getCurrency()!=null && !order.getCurrency().isEmpty()){
            currencySymbol =shippingDAO.getSymbolByCurrencycode(order.getCurrency());  
        }
		   String carrierName="";
		   int id=order.getCarrierId().intValue();
			   switch (id) {
			case ShiplinxConstants.CARRIER_FEDEX:
				carrierName="fedex";
				break;

			case ShiplinxConstants.CARRIER_UPS:
				carrierName="ups";
				break;
			
			case ShiplinxConstants.CARRIER_DHL:
				carrierName="dhl";
				break;
			
			case ShiplinxConstants.CARRIER_PUROLATOR:
				carrierName="purolator";
				break;
			default:
				break;
			}
			try {
				 InputStream stream=null;
				if(!carrierName.equalsIgnoreCase("dhl")){
				if(user.getPreferredLabelSize().equalsIgnoreCase("8 x 11")){
					if(carrierName.equalsIgnoreCase("purolator")){
						stream = this
						          .getClass()
						          .getClassLoader()
						          .getResourceAsStream(
						              "./jasperreports/src/main/java/com/meritconinc/shiplinx/carrier/generic/jasperreports/purolatorSummaryLabel.jasper");
					}
					else if(carrierName.equalsIgnoreCase("ups")){
						 stream = this
						          .getClass()
						          .getClassLoader()
						          .getResourceAsStream(
						              "./jasperreports/src/main/java/com/meritconinc/shiplinx/carrier/generic/jasperreports/summaryLabel.jasper");
					}
					else{
					stream = this
					          .getClass()
					          .getClassLoader()
					          .getResourceAsStream(
					              "./jasperreports/src/main/java/com/meritconinc/shiplinx/carrier/generic/jasperreports/summaryLabel2.jasper");
				}}
				else{
		      stream = this
		          .getClass()
		          .getClassLoader()
		          .getResourceAsStream(
		              "./jasperreports/src/main/java/com/meritconinc/shiplinx/carrier/generic/jasperreports/summaryLabel.jasper");
				}
				}else{
						if(user.getPreferredLabelSize().equalsIgnoreCase("8 x 11")){
							stream = this
							          .getClass()
							          .getClassLoader()
							          .getResourceAsStream(
							              "./jasperreports/src/main/java/com/meritconinc/shiplinx/carrier/generic/jasperreports/dhl2SummaryLabel.jasper");
						}
						else{
				      stream = this
				          .getClass()
				          .getClassLoader()
				          .getResourceAsStream(
				              "./jasperreports/src/main/java/com/meritconinc/shiplinx/carrier/generic/jasperreports/dhl2SummaryLabel.jasper");
						}
					
				}
		      if (stream == null) {
		        log.error("Stream is NULL!");
		      }
		      JasperReport jasperReport = (JasperReport) JRLoader.loadObject(stream);
		      Map<String, Object> parameters = new HashMap<String, Object>();
		      Business business = businessDAO.getBusiessById(order.getBusinessId());
		      String logoPath = "/images/" + business.getLogoHiResFileName();
		      URL logo = (InvoiceManagerImpl.class.getResource(logoPath));

		      String packageType = "", type,weight = "";
		      ArrayList<String> trackingNumber = new ArrayList<String>();
		      for (Package p : order.getPackages()) {
		        trackingNumber.add(p.getTrackingNumber());
		        weight=p.getWeight().toString();
		      }
		      parameters.put("ic_logo", logo);
		      parameters.put("carrier_name", order.getCarrierName());
		      parameters.put("service_name", order.getService().getName());
		      parameters.put("ship_to_company", order.getToAddress().getAbbreviationName());
		     // parameters.put("tracking_number", order.getPackages().get(0).getTrackingNumber());
		      parameters.put("Package", order.getPackages());
		      /*parameters.put("reference2",order.getReferenceOneName());
		      parameters.put("reference3",order.getReferenceTwoName());
		      parameters.put("reference1",order.getReferenceCodeName());*/
		      parameters.put("reference2",order.getReferenceOne()); 
		      parameters.put("reference3",order.getReferenceTwo()); 
		      parameters.put("reference1",order.getReferenceCode()); 

		      parameters.put("quantity",order.getQuantity().toString());
		      parameters.put("weight",order.getQuotedWeight());
		      //parameters.put("cost",currencySymbol.getCurrencySymbol()+order.getQuoteTotalCost());
		      parameters.put("cost",currencySymbol.getCurrencySymbol()+order.getQuoteTotalCharge());
		      //String summaryLabelFileName="/home/system4/temp/soluship/solushipfiles/summaryLabel/summaryLabel.pdf";
		      //String summaryLabelFileName="/home/system4/temp/soluship/solushipfiles/labels";
		      //OutputStream output = new FileOutputStream(new File(summaryLabelFileName));
		      OutputStream output = new FileOutputStream(new File(shippingLabelFileName));
		      JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(order.getPackages());
		      JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, ds);
		      /*jasperPrint.setPageHeight(430);
		      jasperPrint.setPageWidth(289);*/
		      JasperExportManager.exportReportToPdfStream(jasperPrint, output);
		      
		      /*PDFRenderer pdfRenderer = new PDFRenderer();
		      ArrayList<String> srcList = new ArrayList<String>();
		            // Source pdfs
		      srcList.add((new File(shippingLabelFileName)).toString());
		      srcList.add((new File(summaryLabelFileName)).toString());
		      srcList.add((new File(shippingLabelFileName)).toString());
		      //srcList.add((new File(summaryLabelFileName)).toString());
		      pdfRenderer.mergePDF(srcList, outputStream);*/
		      return true;
		      
		    } catch (Exception e) {
		      log.error("Could not generate label for order with id " + order.getId() + " and customer "
		         /* + customer.getName()*/, e);
		      return false;
		      //throw new ShiplinxException();
		    }

	}

  public MarkupManagerDAO getMarkupDAO() {
    return markupDAO;
  }

  public void setMarkupDAO(MarkupManagerDAO markupDAO) {
    this.markupDAO = markupDAO;
  }

  public void setShippingDAO(ShippingDAO shippingDAO) {
    this.shippingDAO = shippingDAO;
  }

  public List<Carrier> getCustomerCarriersForBusiness(long customerId) {

    return getCarrierServiceDAO().getCustomerCarriersForBusiness(customerId);
  }

  @Override
  public List getCustomerServicesForCarrier(Long carrierId, long customerId) {
    return getCarrierServiceDAO().getCustomerServicesForCarrier(carrierId, customerId);
  }
  
  public List<Service> getServicesForCarrierAdmin(Long carrierId) {
	  	    return getCarrierServiceDAO().getServicesForCarrierAdmin(carrierId);
	  	  }
  
  public List<Rating> getFromRatingList() {
		return fromRatingList;
	}

	public void setFromRatingList(List<Rating> fromRatingList) {
		this.fromRatingList = fromRatingList;
	}

	public List<Rating> getToRatingList() {
		return toRatingList;
	}

	public void setToRatingList(List<Rating> toRatingList) {
		this.toRatingList = toRatingList;
	}
	
	private List<Service> getCarrierServicesList(ShippingOrder order,CustomerCarrier customerCarrier,Carrier carrier) {
			    boolean ltlAvailable = false;
			    boolean ltlNoRatesSingleError = false;
			    int count=0;
			    ArrayList threadList = new ArrayList();
			    List<Markup> currentMarkupList=new ArrayList<Markup>();
			    List<Rating> ratingList = new ArrayList<Rating>();
			    Set serviceSet = servicesMapToApply.entrySet();
			    Iterator itr = serviceSet.iterator();
			    List<Service> carrierServicesList= new ArrayList<Service>();
			    while(itr.hasNext()){
			    	Map.Entry entry = (Map.Entry)itr.next();
			    	Long key=(Long) entry.getKey();
			    	if(key.equals(carrier.getId())){
			    		carrierServicesList = (List<Service>) entry.getValue();
			    	}
			    }
			    Set MarkupSet = markupMapForCustomer.entrySet();
			    Iterator mitr = MarkupSet.iterator();
			    while(mitr.hasNext()){
			    	Map.Entry entry = (Map.Entry)mitr.next();
			    	Long key=(Long) entry.getKey();
			    	if(key.equals(carrier.getId())){
			    		currentMarkupList = (List<Markup>) entry.getValue();
			    	}
			    }
		     List<Service> fullCarrierServicesList = carrierServicesList;
		      List<Service> tempCarrierServiceList = new ArrayList<Service>();
		      List<Service> tempCarrierServiceListToCopy = new ArrayList<Service>();
		      List<Service> tempCarrierServiceListForFilter = new ArrayList<Service>();
		      Service tempService = new Service();
		      carrierServiceManager = (CarrierServiceManager) MmrBeanLocator.getInstance().findBean(
		         "carrierServiceManager");
		      boolean flagCheckLimit = true;
		      boolean flagCheckForErrorAlert = true;
		      Markup markupForFilter = new Markup();
		      Boolean markupResult=false;
		   // Filter out disabled services for the current carrier to reduce unwanted ltl table lookup
		      for (int i = 0; i < carrierServicesList.size(); i++) {
		    	  tempService = carrierServicesList.get(i);
		        markupForFilter.setServiceId(tempService.getId());
		        markupForFilter.setCustomerId(order.getCustomerId());
		        if(currentMarkupList!=null && currentMarkupList.size()>0){
		        	markupResult=true;
		        }
		        //markupResult = markupManagerService.getMarkupListForCustomerForFilter(markupForFilter);
		        if (markupResult
		            && (carrierServicesList.get(i).getId()
		                .equals(carrierServicesList.get(i).getMasterServiceId()) || carrierServicesList
		                .get(i).getMasterCarrierId() != ShiplinxConstants.CARRIER_GENERIC)) {
		
		          tempCarrierServiceListForFilter.add(carrierServicesList.get(i));
		        }
		      //for direct carrier setup
			if (customerCarrier.getCarrierId() != ShiplinxConstants.CARRIER_GENERIC) {
				for (CustomerCarrier cc : customerCarriers) {
					if (markupResult
							&& cc.getCarrierId().equals(
									carrierServicesList.get(i).getCarrierId())) {
						tempCarrierServiceListForFilter.add(carrierServicesList
								.get(i));
					}
				}
			}
		      }
		      if(carrierServicesList.size()<1){
		      	       		if(customerCarriers != null && customerCarriers.size()>0){
		      	        		for (CustomerCarrier customerCarrier2 : customerCarriers) {
		      	        			if(carrier.getId().equals(customerCarrier2.getCarrierId())){
		      	       				carrierServicesList = carrierServiceDAO.getServicesByCarrierId(carrier.getId());
		      	                	}
		      					}
		      	        		for (int i = 0; i < carrierServicesList.size(); i++) {
		      	        			tempService = carrierServicesList.get(i);
		      	        	          markupForFilter.setServiceId(tempService.getId());
		      	        	          markupForFilter.setCustomerId(order.getCustomerId());
		      	        	        markupResult=false;
		      	        	        if(currentMarkupList!=null && currentMarkupList.size()>0){
		      	        	        	markupResult=true;
		      	        	        }
		      	        	        //markupResult = markupManagerService.getMarkupListForCustomerForFilter(markupForFilter);
	if (markupResult){
		      	        	        	  tempCarrierServiceListForFilter.add(carrierServicesList.get(i));
		      	        	          }
		      	       	}
		      	        	
		      	        }
		      	        }
		      carrierServicesList = tempCarrierServiceListForFilter;
		   // checking packages whether exceeded size or not for filter out
		      for (int i = 0; i < carrierServicesList.size(); i++) {
		    	  tempService = carrierServicesList.get(i);
		        carrierServicesList.get(i).setMasterServiceId(tempService.getMasterServiceId());
		        flagCheckLimit = true;
		     // iterating the packages for checking the maximum length,weight,height
		        for (int j = 0; j < order.getPackages().size(); j++) {
		          if ((order.getPackages().get(j).getLength().longValue() >= tempService.getMaxLength() && tempService
		              .getMaxLength() != 0)
		             || (order.getPackages().get(j).getWidth().longValue() >= tempService.getMaxWidth() && tempService
		                  .getMaxWidth() != 0)
		              || (order.getPackages().get(j).getHeight().longValue() >= tempService
		                  .getMaxHeight() && tempService.getMaxHeight() != 0)
		              || (order.getPackages().get(j).getWeight().longValue() >= tempService
		                  .getMaxWeight() && tempService.getMaxWeight() != 0)) {
		            flagCheckLimit = false;
		          }
		        }
		        if (!flagCheckLimit) {
		      	  flagCheckForErrorAlert = false;
		
		                } else {
		
		
		          markupForFilter.setServiceId(tempService.getId());
		          markupForFilter.setCustomerId(order.getCustomerId());
		          markupResult=false;
		          if(currentMarkupList!=null && currentMarkupList.size()>0){
		        	  markupResult=true;
		          }
		          //markupResult = markupManagerService.getMarkupListForCustomerForFilter(markupForFilter);
		          if (markupResult) {
		            tempCarrierServiceList.add(carrierServicesList.get(i));
		          }
		        }
		      }
		      if (flagCheckForErrorAlert == true && carrier.getId() == 10 && count==carrierServicesList.size()) {
		        addDummyRateForLTL = true;
		        errorLog = MessageUtil.getMessage("error.servicepackage.maxrange",
		            MessageUtil.getLocale());
		        errorLog = new String(errorLog.replaceAll("%CARRIER", carrier.getName()));
		        CarrierErrorMessage errorMsg = new CarrierErrorMessage(errorLog);
		        errorMessages.add(errorMsg);
		      } else if (!flagCheckForErrorAlert && carrier.getId() != 10) {
		        addDummyRateForLTL = true;
		       errorLog = MessageUtil.getMessage("error.servicepackage.maxrange",
		            MessageUtil.getLocale());
		        errorLog = new String(errorLog.replaceAll("%CARRIER", carrier.getName()));
		        errorLog = new String(errorLog.replaceAll(
		           "Please click request quote to verify service availability and rates.", ""));
		        CarrierErrorMessage errorMsg = new CarrierErrorMessage(errorLog);
		        errorMessages.add(errorMsg);
		      } else {
		        if (carrierServicesList.size() > 0
		            && order.getPackageTypeId().getPackageTypeId() == ShiplinxConstants.PACKAGE_TYPE_PALLET) {
		        boolean repitive = false;
		          Map<Long, Boolean> checkServiceRemoval = new HashMap<Long, Boolean>();
		          carrierServicesList = tempCarrierServiceList;
		         tempCarrierServiceList = carrierServicesList;
		          boolean zoneError = false;
		          boolean rateError = false;
				          if (carrier.getId() == ShiplinxConstants.CARRIER_GENERIC) {
		            for (int serviceSlice = 0; serviceSlice < carrierServicesList.size(); serviceSlice++) {
		          	  List<Service> tempServiceListForLTL = new ArrayList<Service>();
		              Boolean ltlMasterServiceFlag = true;
		              boolean availableGroupRate = false;
		              boolean repitiveRate = false;
		              boolean isMasterServiceZone = true;
		
		              repitive = false;
		              // adding the child service for the above master id.
		             for (Service ltlService : fullCarrierServicesList) {
		                if (ltlService.getMasterServiceId() != null
		                    && carrierServicesList.get(serviceSlice) != null
		                    && ltlService.getMasterServiceId().equals(
		                        carrierServicesList.get(serviceSlice).getId())) {
		                  tempServiceListForLTL.add(ltlService);
		                }
		              }
		
		              Zone fromZone = getZone(carrierServicesList.get(serviceSlice).getZoneStructureId(),
		                  order.getFromAddress());
		              Zone toZone = getZone(carrierServicesList.get(serviceSlice).getZoneStructureId(),
		                 order.getToAddress());
		              // checking for master service
		              if (carrierServicesList.get(serviceSlice).getId() == carrierServicesList.get(
		                  serviceSlice).getMasterServiceId()) {
		                if (fromZone == null
		                    || toZone == null
		                    || (fromZone.getCityName() == null && fromZone.getCountryCode() == null
		                        && fromZone.getFromPostalCode() == null
		                        && fromZone.getProvinceCode() == null
		                        && fromZone.getToPostalCode() == null && fromZone.getZoneId() == null
		                        && fromZone.getZoneName() == null && fromZone.getZoneStructureId() == null)
		                    || (toZone.getCityName() == null && toZone.getCountryCode() == null
		                        && toZone.getFromPostalCode() == null && toZone.getProvinceCode() == null
		                        && toZone.getToPostalCode() == null && toZone.getZoneId() == null
		                        && toZone.getZoneName() == null && toZone.getZoneStructureId() == null)) {
		                  isMasterServiceZone = false;
		                }
		                // checking for the list is empty
		                if (!isMasterServiceZone && tempServiceListForLTL.size() > 0) {
		                  // iterating the child service for the corresponding master service
		                  for (Service serviceGroup : tempServiceListForLTL) {
		                    Zone fromZoneTemp = getZone(serviceGroup.getZoneStructureId(),
		                        order.getFromAddress());
		                    Zone toZoneTemp = getZone(serviceGroup.getZoneStructureId(),
		                        order.getToAddress());
		                    if ((fromZoneTemp != null && toZoneTemp != null)
		                        && (fromZoneTemp.getCityName() != null
		                            || fromZoneTemp.getCountryCode() != null
		                             || fromZoneTemp.getFromPostalCode() != null
		                            || fromZoneTemp.getProvinceCode() != null
		                            || fromZoneTemp.getToPostalCode() != null
		                            || fromZoneTemp.getZoneId() != null
		                            || fromZoneTemp.getZoneName() != null || fromZoneTemp
		                            .getZoneStructureId() != null)
		                       && (toZoneTemp.getCityName() != null
		                            || toZoneTemp.getCountryCode() != null
		                            || toZoneTemp.getFromPostalCode() != null
		                            || toZoneTemp.getProvinceCode() != null
		                            || toZoneTemp.getToPostalCode() != null
		                            || toZoneTemp.getZoneId() != null || toZoneTemp.getZoneName() != null || toZoneTemp
		                            .getZoneStructureId() != null)) {
		                      ltlMasterServiceFlag = false;
		                    }
		                  }
		                }
		              }
		              // checking whether the master service and child service has any error to display
		              if (ltlMasterServiceFlag && !isMasterServiceZone) {
		
		                log.debug("Test");
		                zoneError = true;
		                repitive = true;
		              } else { // if zone is present and checking the rate occurs for the zone.
		                boolean addService = true;
		                // checking for the pound rate condition
		
		                if (carrierServicesList.get(serviceSlice).getServiceType() == 1) {
		              	// checking the rate for the master service has rates
		                  for (int pack = 0; pack < order.getPackages().size(); pack++) {
		                    String fClass = order.getPackages().get(pack).getFreightClass();
		                    LtlPoundRate poundRateTobeSearched = LtlPoundRate.getObject(
		                        order.getCustomerId(), order.getBusinessId(),
		                        carrierServicesList.get(serviceSlice).getId(), fromZone.getZoneName(),
		                        toZone.getZoneName(), fClass);
		                    LtlPoundRate pr = this.markupDAO.getPoundRate(poundRateTobeSearched,
		                        order.getTotalWeight());
		                    if(pr==null){
		        	          	pr=BusinessFilterUtil.getPoundRate(markupDAO,poundRateTobeSearched,order.getTotalWeight());
		        	          }
		                    if (pr == null) {
		                      poundRateTobeSearched.setCustomerId(0L);
		                      pr = this.markupDAO.getPoundRate(poundRateTobeSearched,
		                          order.getTotalWeight());
		                      if(pr==null){
			        	          	pr=BusinessFilterUtil.getPoundRate(markupDAO,poundRateTobeSearched,order.getTotalWeight());
			        	          }
		                   // if no rates present set the error message
		                      if (pr == null || pr.getRate() == 0 && pr.getMinimum() == 0) {
		                        rateError = true;
		                        repitive = true;
		                      }
		                    }
		                  }
		               // if there is no pound rates described for the master service then checking for
		                 // the child service
		                 if (rateError) {
		                    // looping the child service for the corresponding master service
		                    for (Service poundService : tempServiceListForLTL) {
		                      long poundChildServiceId = poundService.getId();
		                      boolean poundChildRateError = false;
		
		                      // checking the fromzone and tozone for the child service.
		                      fromZone = getZone(poundService.getZoneStructureId(),
		                          order.getFromAddress());
		                      toZone = getZone(poundService.getZoneStructureId(), order.getToAddress());
		                      // checking for the packages added for the order
		                      for (int pack = 0; pack < order.getPackages().size(); pack++) {
		                        String fClass = order.getPackages().get(pack).getFreightClass();
		                        LtlPoundRate poundRateTobeSearched = LtlPoundRate.getObject(
		                            order.getCustomerId(), order.getBusinessId(), poundChildServiceId,
		                            fromZone.getZoneName(), toZone.getZoneName(), fClass);
		                        LtlPoundRate pr = this.markupDAO.getPoundRate(poundRateTobeSearched,
		                            order.getTotalWeight());
		                        if(pr==null){
			        	          	pr=BusinessFilterUtil.getPoundRate(markupDAO,poundRateTobeSearched,order.getTotalWeight());
			        	          }
		                        if (pr == null) {
		                          poundRateTobeSearched.setCustomerId(0L);
		                         pr = this.markupDAO.getPoundRate(poundRateTobeSearched,
		                             order.getTotalWeight());
		                         if(pr==null){
				        	          	pr=BusinessFilterUtil.getPoundRate(markupDAO,poundRateTobeSearched,order.getTotalWeight());
				        	          }
		                         // if no rates present set the error message
		                         if (pr == null || pr.getRate() == 0 && pr.getMinimum() == 0) {
		                            poundChildRateError = true;
		                            repitive = true;
		                            addService = false;
		                          }
		                        }
		                      }
		                      if (!poundChildRateError) {
		                        rateError = false;
		                        addService = true;
		                        break;
		                     }
		                   }
		                 }
		                } else { // checking for the skid rate
				                  LtlSkidRate skidRateTobeSearched = LtlSkidRate.getObject(order.getCustomerId(),
		                      order.getBusinessId(), carrierServicesList.get(serviceSlice).getId(),
		                      fromZone.getZoneName(), toZone.getZoneName());
		                  LtlSkidRate pr = this.markupDAO.getSkidRate(skidRateTobeSearched);
		                  if(pr==null){
		                	  pr=  BusinessFilterUtil.getSkidRate(markupDAO,skidRateTobeSearched);
    	  		          }
 /* List <LtlSkidRate> pr = new ArrayList<LtlSkidRate>();
				                 	                  pr = this.markupDAO.getSkidRate(skidRateTobeSearched);*/
		                  // if pr is null for the customer id then set the customer to zero.
		                  if (pr == null) {
		                    skidRateTobeSearched.setCustomerId(0L);
		                    pr = this.markupDAO.getSkidRate(skidRateTobeSearched);
		                    if(pr==null){
			                	  pr=  BusinessFilterUtil.getSkidRate(markupDAO,skidRateTobeSearched);
	    	  		          }
		                    if (pr == null) {
		                  	  rateError = true;
		                      repitive = true;
		                    } /*
		                       * else { repitive = false; availableGroupRate = true; repitiveRate = true;
		                       * }
		                       */
		                }
		                  // if the master service has no rates then checking for the child services
		                  if (rateError) {
		                    // iterating the child service for corresponding master service
		                    for (Service skidService : tempServiceListForLTL) {
		                      /*fromZone = getZone(skidService.getZoneStructureId(), order.getFromAddress());
		                      toZone = getZone(skidService.getZoneStructureId(), order.getToAddress());*/
		                    	String fromCity = "";
			                      String toCity ="";
			                      Long fromZoneStructureId=0l;
			                      Long toZoneStructureId=0l;
			                      String fromCountry = "";
			                      			                      String toCountry = "";
			                      			                      String fromProvince = "";
			                      			                      String toProvince = "";
			                      fromCity = order.getFromAddress().getCity();
			                      /*toCity =  order.getToAddress().getCity();*/ 
			                      toCity =  order.getToAddress().getCity();
			                      			                      fromProvince = order.getFromAddress().getProvinceCode();
			                      			                      toProvince = order.getToAddress().getProvinceCode();
			                      fromZoneStructureId = skidService.getZoneStructureId(); 
			                      toZoneStructureId = skidService.getZoneStructureId();
			                      fromCountry = order.getFromAddress().getCountryCode();
			                      			                      toCountry = order.getToAddress().getCountryCode();
			                      List<Zone> overAllfromZones = new ArrayList<Zone>();
			                      List<Zone> overAlltoZones = new ArrayList<Zone>();
			                      /*overAllfromZones=markupManagerService.getOverallZones(fromCity,fromZoneStructureId);
			                      overAlltoZones=markupManagerService.getOverallZones(toCity,toZoneStructureId);*/
			                      overAllfromZones=markupManagerService.getOverallZones(fromCity,fromZoneStructureId,fromCountry,fromProvince);
			                      			                      overAlltoZones=markupManagerService.getOverallZones(toCity,toZoneStructureId,toCountry,toProvince);
			                      if (overAllfromZones != null && overAlltoZones != null && !overAllfromZones.isEmpty() && !overAlltoZones.isEmpty()) {
	            		          	  for(Zone fromZoneVar : overAllfromZones){
	            		          		  for(Zone toZoneVar : overAlltoZones ){
		                      LtlSkidRate childServiceSkid = LtlSkidRate.getObject(order.getCustomerId(),
		                          order.getBusinessId(), skidService.getId(), fromZoneVar.getZoneName(),
		                          toZoneVar.getZoneName());
		                      pr = this.markupDAO.getSkidRate(childServiceSkid);
		                      if(pr==null){
			                	  pr=  BusinessFilterUtil.getSkidRate(markupDAO,childServiceSkid);
	    	  		          }
		                      boolean skidChildRateError = false;
		                      // if pr is null for the customer id then set the customer to zero.
		                      if (pr == null) {
		                        childServiceSkid.setCustomerId(0L);
		                        pr = this.markupDAO.getSkidRate(childServiceSkid);
		                        if(pr==null){
				                	  pr=  BusinessFilterUtil.getSkidRate(markupDAO,childServiceSkid);
		    	  		          }
		                        if (pr == null) {
		                          rateError = true;
		                          repitive = true;
		                          skidChildRateError = true;
		                          addService = false;
		                        } /*
		                           * else { repitive = false; availableGroupRate = true; repitiveRate =
		                           * true; }
		                           */
		                      }
		                     if (!skidChildRateError) {
		                        rateError = false;
		                        addService = true;
		                        if(!tempCarrierServiceListToCopy.contains(carrierServicesList.get(serviceSlice))){
		                        	tempCarrierServiceListToCopy.add(carrierServicesList.get(serviceSlice));
			                	}
		                        break;
		                     }
	            		          		  }
	            		          	  }
		                      }
		                    }
		
		                  }
		                }
		                if (addService) {
		                  //tempCarrierServiceListToCopy.add(carrierServicesList.get(serviceSlice));
		                	if(!tempCarrierServiceListToCopy.contains(carrierServicesList.get(serviceSlice))){
		                	 tempCarrierServiceListToCopy.add(carrierServicesList.get(serviceSlice));
		                	}
		                } /*
		                   * else if (repitiveRate && availableGroupRate) {
		                   * tempCarrierServiceListToCopy.add(carrierServicesList.get(serviceSlice)); }
		                   */
		
		              }
		            }
		            carrierServicesList = tempCarrierServiceListToCopy;
		            if (carrierServicesList.size() <= 0) {
		              addDummyRateForLTL = true;
		            }
		            if (rateError == true
		          	  && carrierServicesList != null
		                && carrierServicesList.size() > 0
		                && order.getPackageTypeId().getPackageTypeId()
		                    .equals(ShiplinxConstants.PACKAGE_TYPE_PALLET)) {
		
		              errorLog = MessageUtil.getMessage("error.service.rate.notallowed",
		                  MessageUtil.getLocale());
		              errorLog = new String(errorLog.replaceAll("%CARRIER", carrier.getName()));
		              CarrierErrorMessage errorMsg = new CarrierErrorMessage(errorLog);
		              errorMessages.add(errorMsg);
		            } else if (rateError == true
		                && carrierServicesList.size() <= 0
		                && order.getPackageTypeId().getPackageTypeId().intValue() == ShiplinxConstants.PACKAGE_TYPE_PALLET) {
		              ltlNoRatesSingleError = true;
		              errorLog = MessageUtil.getMessage("error.skid.availability.rate",
		                  MessageUtil.getLocale());
		              errorLog = new String(errorLog.replaceAll("%CARRIER", carrier.getName()));
		              CarrierErrorMessage errorMsg = new CarrierErrorMessage(errorLog);
		              errorMessages.add(errorMsg);
		            }
		            if (zoneError == true
		                && carrierServicesList.size() > 0
		                && order.getPackageTypeId().getPackageTypeId()
		                    .equals(ShiplinxConstants.PACKAGE_TYPE_PALLET)) {
		
		              errorLog = MessageUtil.getMessage("error.service.zones.notallowed",
		                  MessageUtil.getLocale());
		              errorLog = new String(errorLog.replaceAll("%CARRIER", carrier.getName()));
		              CarrierErrorMessage errorMsg = new CarrierErrorMessage(errorLog);
		              errorMessages.add(errorMsg);
		            } else if (zoneError == true
		               && carrierServicesList.size() <= 0
		              		  && order.getPackageTypeId().getPackageTypeId()
		                    .equals(ShiplinxConstants.PACKAGE_TYPE_PALLET)) {
		
		              ltlNoRatesSingleError = true;
		              errorLog = MessageUtil.getMessage("error.skid.availability.rate",
		                  MessageUtil.getLocale());
		              errorLog = new String(errorLog.replaceAll("%CARRIER", carrier.getName()));
		              CarrierErrorMessage errorMsg = new CarrierErrorMessage(errorLog);
		              errorMessages.add(errorMsg);
		            }
		          }
		       }
		        // order.setCustomerCarrier(customerCarrier);
		       
		      }
		      return carrierServicesList;
		 }
		  public void findCheapestRate(List<Rating> ratingList,ShippingOrder orderThread){
			  if(orderThread.getFromRatingList().size()<orderThread.getToRatingList().size()){
					List<Rating>tempRatingList = new ArrayList<Rating>();
					tempRatingList = orderThread.getFromRatingList();
					orderThread.setFromRatingList(orderThread.getToRatingList());
					orderThread.setToRatingList(tempRatingList);
					
				 }
				for(int from=0;from<orderThread.getFromRatingList().size();from++){
					int countFlag=0;
					for(int to=0;to<orderThread.getToRatingList().size();to++){
						if(orderThread.getFromRatingList().get(from).getServiceName().equals(orderThread.getToRatingList().get(to).getServiceName())){
						double fromTotalCharge=orderThread.getFromRatingList().get(from).getBaseCharge();
							double toTotalCharge=orderThread.getToRatingList().get(to).getBaseCharge();
							if(fromTotalCharge<toTotalCharge){
								ratingList.add(orderThread.getFromRatingList().get(from));
							}else{
								ratingList.add(orderThread.getToRatingList().get(to));
							}
							break;
						}else{
							countFlag++;
						}
						
					}
					if(countFlag>0 && orderThread.getToRatingList().size()>0&& countFlag==orderThread.getToRatingList().size()){
						ratingList.add(orderThread.getFromRatingList().get(from));
					}
				}	
		  }
		  
		public BusinessDAO getBusinessDAO() {
  			return businessDAO;
  		}
  
  		public void setBusinessDAO(BusinessDAO businessDAO) {
  			this.businessDAO = businessDAO;
  		}

		public CreditUsageReport getCur() {
			return cur;
		}

		public void setCur(CreditUsageReport cur) {
			this.cur = cur;
		}
		
	@Override
	public Carrier getCarrierBycarrierId(Long carrierId) {
		// TODO Auto-generated method stub
		return this.carrierServiceDAO.getCarrierBycarrierId(carrierId);
	}
	
	/*This method is for handling service error repetition(Additional service request quote) 
	if true then this is new service error,it should be added.
	if true then this is existing service error,it should be omitted.*/
	public Boolean checkServiceErrorExistance(Rating rating, Map serviceError){
		String key;
		String value;
		Iterator it = serviceError.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry)it.next();
			key = pair.getKey().toString();
			if(key.equals(rating.getServiceName())){
			value=pair.getValue().toString();
			  if(value.equals(rating.getCarrierName())){
				  return false;
				   }
			}
		    }
		return true;
		
	}
	
	/* For business markup - start*/
	 
	/**
	  * This method will call the business mark-up applying methods.
	  * Condition 1: If the current business is not the root business 
	  * then execute if block
	  *   inside if block-take the root business of current business for mark-up apply
	  * else execute else block
	  *   inside else block-use current business for mark-up apply
	  * Condition 2: order of mark-up apply is Service level,Business level and custom(business to any) level
	  * Condition 3: Once one level of mark-up applied in rates then next mark-up level should omitted 
	  * @param ratingList
	  * @param order
	  */
	private void businessMarkupForRates(List<Rating> ratingList, ShippingOrder order) {
		//Check for the availability of business_custom_markup for this business
		int cutomMarkup=0;
		double tempCost=0.0;
		//Check business level,If it's not root level then find root business
		if(order.getBusiness().getParentBusinessId()!=0){
			Business bus=businessDAO.getSuperParentBusiness(order.getBusiness().getId());
			BusinessMarkup businessMarkup=new BusinessMarkup();
			businessMarkup.setBusinessId(0l);
			businessMarkup.setBusinessToId(bus.getId());
			businessMarkup.setFromCountryCode(order.getFromAddress().getCountryCode());
			businessMarkup.setToCountryCode(order.getToAddress().getCountryCode());
			//service business markup code starts here
			ratingList=applyServiceBusinessMarkup(ratingList,order,businessMarkup);
			//service business markup code ends here
			
			//Business to business markup code starts here
			ratingList=applyBusinessToBusinessMarkup(ratingList,order,businessMarkup);
			//Business to business markup code ends here
			
			//custom business markup code starts here
			ratingList=applycustomBusinessMarkup(ratingList,order,businessMarkup);
			//custom business markup code ends here
		}else{
			BusinessMarkup businessMarkup=new BusinessMarkup();
			businessMarkup.setBusinessId(0l);
			businessMarkup.setBusinessToId(order.getBusinessId());
			businessMarkup.setFromCountryCode(order.getFromAddress().getCountryCode());
			businessMarkup.setToCountryCode(order.getToAddress().getCountryCode());
			//service business markup code starts here
			ratingList=applyServiceBusinessMarkup(ratingList,order,businessMarkup);
			//service business markup code ends here
			
			//Business to business markup code starts here
			ratingList=applyBusinessToBusinessMarkup(ratingList,order,businessMarkup);
			//Business to business markup code ends here
			
			//custom business markup code starts here
			ratingList=applycustomBusinessMarkup(ratingList,order,businessMarkup);
			//custom business markup code ends here
		}
	}	
		
	 /**
	  * This method is used to get business mark-up for specific business,customer and service
	  * @param ratingList
	  * @param order
	  * @param businessMarkup
	  * @return
	  */
	private List<Rating> applyServiceBusinessMarkup(List<Rating> ratingList,
			ShippingOrder order, BusinessMarkup businessMarkup) {
		int i=0;
		log.debug("<===Service-Business Markup===>");
		for(Rating rate:ratingList){
			log.debug("CARRIER ID : "+rate.getCarrierId()+" CARRIER NAME : "+rate.getCarrierName()+" SERVICE :"+rate.getServiceId());			businessMarkup.setServiceId((String.valueOf(rate.getServiceId())!=null)?rate.getServiceId():0L);
			businessMarkup.setDisabled(0);
			businessMarkup.setCustomerId(order.getCustomerId());
			BusinessMarkup businessMarkup2=markupManagerService.getuniqueBusinessMarkup(businessMarkup);
			if(businessMarkup2!=null){
				businessMarkup=businessMarkup2;
			}else{
				businessMarkup.setCustomerId(0l);
				businessMarkup2=markupManagerService.getuniqueBusinessMarkup(businessMarkup);
				if(businessMarkup2!=null)
					businessMarkup=businessMarkup2;
				else
					continue;
			}
			if(businessMarkup!=null && !businessMarkup.isDisabledFlag()){
				int markType=businessMarkup.getType();
				double markPerc=businessMarkup.getMarkupPercentage();
				double markFlat=(businessMarkup.getMarkupFlat()!=null)?businessMarkup.getMarkupFlat():0;
				double amount,flatAmount;
				for(Charge charge:rate.getCharges()){
					boolean addMarkup=checkCostAndWeightLimit(businessMarkup,charge,order);
					if(addMarkup){
						CarrierChargeCode chargeGroupCode;
						boolean chargeCode=false;
						boolean type=false;
						if((ShiplinxConstants.CARRIER_UPS==rate.getCarrierId()) || (ShiplinxConstants.CARRIER_DHL==rate.getCarrierId()) || (ShiplinxConstants.CARRIER_FEDEX==rate.getCarrierId())){ 
						chargeGroupCode = this.shippingService.getChargeByCarrierAndCodes(
		                          rate.getCarrierId(), null,charge.getChargeCode());
						if(ShiplinxConstants.CARRIER_FEDEX==rate.getCarrierId() && chargeGroupCode==null){
							chargeGroupCode = this.shippingService.getChargeByCarrierAndCodes(
			                          rate.getCarrierId(),charge.getChargeCode(),null);
						}
						if(chargeGroupCode!=null && (chargeGroupCode.getGroupId()==1 || chargeGroupCode.getGroupId()==3)){
							chargeCode=true;
						}
					}
					if((charge.getChargeCode().equalsIgnoreCase(ShiplinxConstants.GROUP_FREIGHT_CHARGE) 
								|| charge.getChargeCode().equalsIgnoreCase(ShiplinxConstants.GROUP_FUEL_CHARGE))
								|| charge.getChargeCode().equalsIgnoreCase(ShiplinxConstants.GROUP_FUEL_SURCHARGE) || chargeCode==true){
						charge.setCostWithNoBM(charge.getCost());
						if (markType == ShiplinxConstants.TYPE_MARKDOWN) {
							double f = ((FormattingUtil.subtract(charge.getCost().doubleValue(),
									charge.getStaticAmount())).doubleValue())
									* markPerc / 100;
							amount = (FormattingUtil.subtract(charge.getCost().doubleValue(), f))
									.doubleValue();
						} else { 
							double f = (FormattingUtil.subtract(charge.getCost().floatValue(),
									charge.getStaticAmount())).doubleValue()
									* markPerc / 100;
							amount = (FormattingUtil.add(charge.getCost().doubleValue(), f)).doubleValue();
						}
						if(markFlat>0){
							flatAmount=charge.getCostWithNoBM()+markFlat;
							if(amount<=flatAmount)
								amount=flatAmount;
							type=true;
						}
						charge.setCost(amount);
						charge.setChargeWithBM(amount);
						rate.setBMBusinessId(businessMarkup.getBusinessId());
						rate.setBusinessMarkup(true);
						if(!type){
							rate.setBMValue(markPerc);
							if (markType == ShiplinxConstants.TYPE_MARKDOWN) {
								rate.setBMType(ShiplinxConstants.BUSINESS_MARKUP_TYPE_2);
							}else{
								rate.setBMType(ShiplinxConstants.BUSINESS_MARKUP_TYPE_1);
							}
							log.debug("Carrier cost : "+charge.getCostWithNoBM()+" + ("+markPerc+"%) ==>"+charge.getCost());
						}else{
							rate.setBMValue(markFlat);
							rate.setBMType(ShiplinxConstants.BUSINESS_MARKUP_TYPE_0);
							log.debug("Carrier cost : "+charge.getCostWithNoBM()+" + ("+markFlat+"$) ==>"+charge.getCost());
						}
					}
					}
				}
			}
		}
		return ratingList;
	}
		
	/**
	  * This method is used to get business mark-up for specific business 
	  * @param ratingList
	  * @param order
	  * @param businessMarkup
	  * @return
	  */
	private List<Rating> applyBusinessToBusinessMarkup(List<Rating> ratingList,
			ShippingOrder order, BusinessMarkup businessMarkup) {
		int i=0;
		log.debug("<===Business-Business Markup===>");
		for(Rating rate:ratingList){
			log.debug("CARRIER ID : "+rate.getCarrierId()+" CARRIER NAME : "+rate.getCarrierName()+" SERVICE :"+rate.getServiceId());			businessMarkup.setServiceId((String.valueOf(rate.getServiceId())!=null)?rate.getServiceId():0L);
			if(!rate.isBusinessMarkup()){
				businessMarkup.setCustomerId(order.getCustomerId());
				businessMarkup.setServiceId(0l);
				BusinessMarkup businessMarkup1 = markupManagerService.getuniqueBusinessToBusinessMarkup(businessMarkup);
				if(businessMarkup1!=null){
					businessMarkup=businessMarkup1;
				}else{
					businessMarkup.setCustomerId(0l);
					businessMarkup1=markupManagerService.getuniqueBusinessToBusinessMarkup(businessMarkup);
					if(businessMarkup1!=null)
						businessMarkup=businessMarkup1;
					else
						continue;
				}
				if(businessMarkup!=null && !businessMarkup.isDisabledFlag()){
				int markType=businessMarkup.getType();
				double markPerc=businessMarkup.getMarkupPercentage();
				double markFlat=(businessMarkup.getMarkupFlat()!=null)?businessMarkup.getMarkupFlat():0;
				double amount,flatAmount;
				for(Charge charge:rate.getCharges()){
					boolean addMarkup=checkCostAndWeightLimit(businessMarkup,charge,order);
					if(addMarkup){
						CarrierChargeCode chargeGroupCode;
						boolean chargeCode=false;
						boolean type=false;
						if((ShiplinxConstants.CARRIER_UPS==rate.getCarrierId()) || (ShiplinxConstants.CARRIER_DHL==rate.getCarrierId()) || (ShiplinxConstants.CARRIER_FEDEX==rate.getCarrierId())){ 
							chargeGroupCode = this.shippingService.getChargeByCarrierAndCodes(
		                          rate.getCarrierId(), null,charge.getChargeCode());
							if(ShiplinxConstants.CARRIER_FEDEX==rate.getCarrierId() && chargeGroupCode==null){
								chargeGroupCode = this.shippingService.getChargeByCarrierAndCodes(
			                          rate.getCarrierId(),charge.getChargeCode(),null);
							}
							if(chargeGroupCode!=null && (chargeGroupCode.getGroupId()==1 || chargeGroupCode.getGroupId()==3)){
							chargeCode=true;
							}
						}
						if((charge.getChargeCode().equalsIgnoreCase(ShiplinxConstants.GROUP_FREIGHT_CHARGE) 
								|| charge.getChargeCode().equalsIgnoreCase(ShiplinxConstants.GROUP_FUEL_CHARGE))
								|| charge.getChargeCode().equalsIgnoreCase(ShiplinxConstants.GROUP_FUEL_SURCHARGE) || chargeCode==true){
							charge.setCostWithNoBM(charge.getCost());
							if (markType == ShiplinxConstants.TYPE_MARKDOWN) {
								double f = ((FormattingUtil.subtract(charge.getCost().doubleValue(),
										charge.getStaticAmount())).doubleValue())
										* markPerc / 100;
								amount = (FormattingUtil.subtract(charge.getCost().doubleValue(), f))
										.doubleValue();
							} else { 
								double f = (FormattingUtil.subtract(charge.getCost().floatValue(),
										charge.getStaticAmount())).doubleValue()
										* markPerc / 100;
								amount = (FormattingUtil.add(charge.getCost().doubleValue(), f)).doubleValue();
							}
							if(markFlat>0){
								flatAmount=charge.getCostWithNoBM()+markFlat;
								if(amount<=flatAmount)
									amount=flatAmount;
									type=true;
							}
							charge.setCost(amount);
							charge.setChargeWithBM(amount);
							rate.setBMBusinessId(businessMarkup.getBusinessId());
							rate.setBusinessMarkup(true);
							if(!type){
								rate.setBMValue(markPerc);
								if (markType == ShiplinxConstants.TYPE_MARKDOWN) {
									rate.setBMType(ShiplinxConstants.BUSINESS_MARKUP_TYPE_2);
								}else{
										rate.setBMType(ShiplinxConstants.BUSINESS_MARKUP_TYPE_1);
								}
								log.debug("Carrier cost : "+charge.getCostWithNoBM()+" + ("+markPerc+"%) ==>"+charge.getCost());
							}else{
								rate.setBMValue(markFlat);
								rate.setBMType(ShiplinxConstants.BUSINESS_MARKUP_TYPE_0);
								log.debug("Carrier cost : "+charge.getCostWithNoBM()+" + ("+markFlat+"$) ==>"+charge.getCost());
							}
						}
					}
				}
				}
			}
		}
		return ratingList;
	}

	 /**
	  * This method is used to get business mark-up which applied for all business by one business
	  * @param ratingList
	  * @param order
	  * @param businessMarkup
	  * @return
	  */
	private List<Rating> applycustomBusinessMarkup(List<Rating> ratingList,ShippingOrder order, BusinessMarkup businessMarkup) {
		int i=0;
		log.debug("<===Custom-Business Markup===>");
		for(Rating rate:ratingList){
			log.debug("CARRIER ID : "+rate.getCarrierId()+" CARRIER NAME : "+rate.getCarrierName()+" SERVICE :"+rate.getServiceId());			businessMarkup.setServiceId((String.valueOf(rate.getServiceId())!=null)?rate.getServiceId():0L);
			if(!rate.isBusinessMarkup()){
				businessMarkup.setCustomerId(order.getCustomerId());
				businessMarkup.setServiceId(0l);
				BusinessMarkup businessMarkup1 = markupManagerService.getUniqueCustomBusinessMarkup(businessMarkup);
				if(businessMarkup1!=null && businessMarkup.getBusinessToId()!=businessMarkup1.getBusinessId()){
					businessMarkup=businessMarkup1;
				}else{
					businessMarkup.setCustomerId(0l);
					businessMarkup1=markupManagerService.getUniqueCustomBusinessMarkup(businessMarkup);
					if(businessMarkup1!=null && businessMarkup.getBusinessToId()!=businessMarkup1.getBusinessId())
						businessMarkup=businessMarkup1;
					else
						continue;
				}
				if(businessMarkup!=null && !businessMarkup.isDisabledFlag()){
				int markType=businessMarkup.getType();
				double markPerc=businessMarkup.getMarkupPercentage();
				double markFlat=(businessMarkup.getMarkupFlat()!=null)?businessMarkup.getMarkupFlat():0;
				double amount,flatAmount;
				for(Charge charge:rate.getCharges()){
					boolean addMarkup=checkCostAndWeightLimit(businessMarkup,charge,order);
					if(addMarkup){
						CarrierChargeCode chargeGroupCode;
						boolean chargeCode=false;
						boolean type=false;
						if((ShiplinxConstants.CARRIER_UPS==rate.getCarrierId()) || (ShiplinxConstants.CARRIER_DHL==rate.getCarrierId()) || (ShiplinxConstants.CARRIER_FEDEX==rate.getCarrierId())){ 
							chargeGroupCode = this.shippingService.getChargeByCarrierAndCodes(
		                          rate.getCarrierId(), null,charge.getChargeCode());
							if(ShiplinxConstants.CARRIER_FEDEX==rate.getCarrierId() && chargeGroupCode==null){
								chargeGroupCode = this.shippingService.getChargeByCarrierAndCodes(
			                          rate.getCarrierId(),charge.getChargeCode(),null);
							}
							if(chargeGroupCode!=null && (chargeGroupCode.getGroupId()==1 || chargeGroupCode.getGroupId()==3)){
							chargeCode=true;
							}
						}
						if((charge.getChargeCode().equalsIgnoreCase(ShiplinxConstants.GROUP_FREIGHT_CHARGE) 
								|| charge.getChargeCode().equalsIgnoreCase(ShiplinxConstants.GROUP_FUEL_CHARGE))
								|| charge.getChargeCode().equalsIgnoreCase(ShiplinxConstants.GROUP_FUEL_SURCHARGE) || chargeCode==true){
							charge.setCostWithNoBM(charge.getCost());
							if (markType == ShiplinxConstants.TYPE_MARKDOWN) {
								double f = ((FormattingUtil.subtract(charge.getCost().doubleValue(),
										charge.getStaticAmount())).doubleValue())
										* markPerc / 100;
								amount = (FormattingUtil.subtract(charge.getCost().doubleValue(), f))
										.doubleValue();
							} else { 
								double f = (FormattingUtil.subtract(charge.getCost().floatValue(),
										charge.getStaticAmount())).doubleValue()
										* markPerc / 100;
								amount = (FormattingUtil.add(charge.getCost().doubleValue(), f)).doubleValue();
							}
							if(markFlat>0){
								flatAmount=charge.getCostWithNoBM()+markFlat;
								if(amount<=flatAmount)
									amount=flatAmount;
									type=true;
							}
							charge.setCost(amount);
							charge.setChargeWithBM(amount);
							rate.setBMBusinessId(businessMarkup.getBusinessId());
							rate.setBusinessMarkup(true);
							if(!type){
								rate.setBMValue(markPerc);
								if (markType == ShiplinxConstants.TYPE_MARKDOWN) {
									rate.setBMType(ShiplinxConstants.BUSINESS_MARKUP_TYPE_2);
								}else{
										rate.setBMType(ShiplinxConstants.BUSINESS_MARKUP_TYPE_1);
								}
								log.debug("Carrier cost : "+charge.getCostWithNoBM()+" + ("+markPerc+"%) ==>"+charge.getCost());
							}else{
								rate.setBMValue(markFlat);
								rate.setBMType(ShiplinxConstants.BUSINESS_MARKUP_TYPE_0);
								log.debug("Carrier cost : "+charge.getCostWithNoBM()+" + ("+markFlat+"$) ==>"+charge.getCost());
							}
						}
					}
				}
				}
			}
		}
		return ratingList;
	}
		
	/**
	  * This method is for checking that whether cost/weight limit is satisfied or not for business mark-up apply 
	  * @param businessMarkup
	  * @param charge
	  * @param order
	  * @return
	  */
	private boolean checkCostAndWeightLimit(BusinessMarkup businessMarkup,Charge charge, ShippingOrder order) {
		double totalWeight = 0;
		if(businessMarkup.getVariable()==1){
			if((businessMarkup.getFromRange()<=charge.getCost() && businessMarkup.getToRange()>=charge.getCost()) || (businessMarkup.getFromRange()==0 && businessMarkup.getToRange()==0)){
				return true;
			}else {
				return false;
			}
		}else {
			for(Package p:order.getPackages()){
				totalWeight=totalWeight+p.getWeight().doubleValue();
			}	
			if((businessMarkup.getFromRange()<=totalWeight && businessMarkup.getToRange()>=totalWeight) || (businessMarkup.getFromRange()==0 && businessMarkup.getToRange()==0)){
				return true;
			}else {
				return false;
			}
		}
	}

/* For business markup - end*/
}
