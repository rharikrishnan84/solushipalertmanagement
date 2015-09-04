package com.meritconinc.shiplinx.api.controller;

/**
 * Rest API service for creating the shipment.
 * @author SELVA GANESH
 * Date 23-06-2015
 */

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.restlet.data.Form;
import org.restlet.ext.servlet.ServletUtils;
import org.restlet.resource.Post;

import com.google.gson.Gson;
import com.meritconinc.mmr.dao.EcommerceDAO;
import com.meritconinc.mmr.model.admin.EcommerceLog;
import com.meritconinc.mmr.model.admin.EcommerceStore;
import com.meritconinc.mmr.utilities.MmrBeanLocator;
import com.meritconinc.shiplinx.api.Util.ShopifyUtil;
import com.meritconinc.shiplinx.api.base.GenericRestServerResource;
import com.meritconinc.shiplinx.api.model.ShopifyRateRequest;
import com.meritconinc.shiplinx.api.model.ShopifyShippingOrder;
import com.meritconinc.shiplinx.dao.ShippingDAO;
import com.meritconinc.shiplinx.model.Charge;
import com.meritconinc.shiplinx.model.LoggedEvent;
import com.meritconinc.shiplinx.model.Rating;
import com.meritconinc.shiplinx.model.ShippingOrder;
import com.meritconinc.shiplinx.service.CarrierServiceManager;
import com.meritconinc.shiplinx.service.LoggedEventService;
import com.meritconinc.shiplinx.utils.ShiplinxConstants;
/*import com.meritconinc.shiplinx.service.impl.CarrierServiceManager;*/
import com.opensymphony.xwork2.ActionContext;

import java.util.ArrayList;
import com.meritconinc.shiplinx.model.Customer;
import com.meritconinc.shiplinx.service.CustomerManager;
import com.meritconinc.shiplinx.service.ShippingService;

public class CreateShipmentAPIController extends GenericRestServerResource {

	private static final Logger log = LogManager.getLogger(CreateShipmentAPIController.class);
	private Form headers;
    private ShopifyController shopifyShop;

    org.restlet.Request restletRequest = getRequest();
    HttpServletRequest request = ServletUtils.getRequest(restletRequest);
    ShippingDAO shippingDAO = (ShippingDAO) MmrBeanLocator.getInstance()
	.findBean("shippingDAO");

	 /**
	  * It will receive the Shipping order request from 
	  * shopify and process the create shipment
	  * @param entity
	  * @return
	  * @throws Exception
	  */
	@Post("json")
	public String CreateShipment(String entity) throws Exception {
		log.debug("=================================== ACCESS TO CREATE SHIPMENT ============================");
		Gson gson = new Gson();
		ShopifyShippingOrder shopifyOrder = gson.fromJson(entity,
				ShopifyShippingOrder.class);
		saveOrderLog(shopifyOrder.getId());
		headers = (Form) getRequestAttributes().get("org.restlet.http.headers"); 
		if(shopifyOrder!=null)
		shopifyOrder.setStoreName(headers.getValues("x-shopify-shop-domain"));
		log.debug("ACCESSING STORE :====>"+shopifyOrder.getStoreName());
		EcommerceDAO eCommerceDAO = (EcommerceDAO) MmrBeanLocator.getInstance()
				.findBean("eCommerceDAO");
		
		EcommerceStore store = eCommerceDAO.getEcomStorebyStoreUrl("https://"+shopifyOrder.getStoreName());
		
		if (headers != null
				&& com.meritconinc.shiplinx.api.Util.ShopifyUtil
						.authedicateRequest(
								headers.getValues("x-shopify-hmac-sha256"),
								entity)) {

			
			CarrierServiceManager carrierServiceManager = (CarrierServiceManager) MmrBeanLocator
					.getInstance().findBean("carrierServiceManager");

			if (shopifyOrder != null && store!=null) {
				// shop=new ShopifyController();
				String city = ShopifyUtil.findCity(shopifyOrder.getShipping_address().getZip(), shopifyOrder.getShipping_address().getCountry_code());
				log.debug("==================  city ===============  "+ city);
				shopifyShop = new ShopifyController();
				ShopifyRateRequest shopifyReq = shopifyShop
						.getShopifyreq(shopifyOrder);
				shopifyShop = new ShopifyController(shopifyReq);
				Long customerId = ShopifyUtil
						.getCustomerIdForShopifyStore("https://"
								+ shopifyOrder.getStoreName());
				log.debug(customerId+" IS THE CUSTOMER MAPPED FOR THE STORE  ===> "+shopifyOrder.getStoreName());
				shopifyShop.setCustomerId(customerId);
				shopifyShop.setReqItems(shopifyReq.getItems());
				shopifyShop.setPackageMap(store.isPackageMap());
				Thread t1 = new Thread(shopifyShop);
				t1.start();
				shopifyReq.setOrigin(null);
				 if (isCreatedShipment(
						Long.parseLong(shopifyOrder.getId().toString()),
						shopifyOrder.getStoreName())
					)  {
				 
					ActionContext
							.getContext()
							.getSession()
							.put("SHOPIFY_ORDER_ID",
									shopifyOrder.getId().toString());
					ShippingOrder order2 = shopifyShop
							.getShippingOrderFromShopify(shopifyOrder);

					ActionContext
							.getContext()
							.getSession()
							.put("SHOPIFY_STORE",
									ShopifyUtil.getStore(shopifyOrder
											.getStoreName()));
					ShippingOrder so = shopifyShop
							.castShopifyRequestToShippingOrder(shopifyReq);
					
					CustomerManager customerService=(CustomerManager)MmrBeanLocator.getInstance().findBean("customerService");
										Customer c=customerService.getCustomerInfoByCustomerId(store.getCustomerId());
									so.getFromAddress().setAbbreviationName(c.getName());
										order2.setFromAddress(so.getFromAddress());
					
					t1.join();
					if(store.isPackageMap()){
						if(shopifyShop.getShipPackes()!=null && shopifyShop.getShipPackes().size()>0)
						so.setPackages(shopifyShop.getShipPackes());
					}
					so.getToAddress().setCity(shopifyShop.getCity());
					if (order2 != null) {

						Rating orderRate = null;
						// rating1 =
						// ShopifyUtil.getRatingForShopifyOrder(order2);
						so.getToAddress().setCity(city);
						List<Rating> ratingList = carrierServiceManager
								.rateShipment(so);
						for (Rating rate : ratingList) {
							if (rate.getServiceId() == order2.getServiceId()) {
								orderRate = rate;
							}
						}
						if (order2 != null && orderRate != null) {
							log.debug("================ RATE REBUILD +"+orderRate.getTotal());
							for (Charge ch : orderRate.getCharges()) {
								ch.setCarrierId(orderRate.getCarrierId());
								ch.setCarrierName(orderRate.getCarrierName());
								ch.setCurrency(order2.getCurrency());
								if (order2.getCurrency()
										.equalsIgnoreCase("CAD")) {
									ch.setCostcurrency(1);
									ch.setChargecurrency(1);
								} else if (order2.getCurrency()
										.equalsIgnoreCase("USD")) {
									ch.setCostcurrency(2);
									ch.setChargecurrency(2);
								}
								if (ch.getName() != null
										&& ch.getName().contains("HST")) {
									ch.setChargeCode("TAX");
								}
							}
							order2.setGeneratedBy("SHOPIFY");
							order2.setPackages(so.getPackages());
							order2.setCharges(orderRate.getCharges());
							order2.setReferenceOneName("SHOPIFY SHIPPING");
							order2.setReferenceOne(shopifyOrder.getId()
									.toString());
							System.out.println(order2.getToAddress()
									.getPhoneNo()
									+ "  "
									+ order2.getFromAddress().getPhoneNo());
							
							log.debug("CREATING ORDER ");

							order2.getToAddress().setEmailAddress(shopifyOrder.getEmail());
							log.debug(order2.getToAddress().getEmailAddress());
							List<Rating> rates = new ArrayList<Rating>();
							rates.add(orderRate);
							order2.setRateList(rates);

							carrierServiceManager.shipOrder(order2, orderRate);
							String refno=order2.getReferenceOne();
							order2=shippingDAO.getShippingOrderByReferenceOne(Long.parseLong(order2.getReferenceOne()),ShiplinxConstants.SHIPMENT_CANCELLED);
							order2.setReferenceOne(refno);
							String fulfilId = shopifyShop
									.fulfilltheShopifyOrder(order2, store);
							order2.setReferenceTwo(fulfilId);
							order2.setReferenceTwoName("SHOPIFY ORDER FULFILMENT");
							ShippingService shippingService = (ShippingService) MmrBeanLocator
									.getInstance().findBean("shippingService");
							shippingService.updateShippingOrder(order2);

							addLoggedEvent(order2,shopifyOrder);

						}

					}
				}
			}
		}
		return "success";
	}

   
	private void addLoggedEvent(ShippingOrder order2, ShopifyShippingOrder shopifyOrder) {
		// TODO Auto-generated method stub
		 LoggedEventService loggedEventService=(LoggedEventService) MmrBeanLocator
			.getInstance().findBean("loggedEventService");
		 Date currentDate = new Date();
		    String userName = order2.getCustomer().getName();
		    SimpleDateFormat ft= new SimpleDateFormat("EEE MMM dd HH:mm:ss yyyy");
		    String cd=ft.format(currentDate);
		   	LoggedEvent loggedEvent = new LoggedEvent();
			loggedEvent.setEntityId(Long.valueOf(order2.getId()));
			loggedEvent.setEventDateTime(currentDate);
			loggedEvent.setEntityType(Long.valueOf(80));		
			loggedEvent.setEventUsername(userName); 
			loggedEvent.setMessage(ShiplinxConstants.SHIPMENT_CREATED+" "+"on"+" "+cd+"using Ecommerce store "+" "+shopifyOrder.getStoreName());
			loggedEvent.setPrivateMessage(false);
			loggedEvent.setDeletedMessage(false);
			loggedEvent.setSystemLog("The Order "+ Long.valueOf(order2.getId())+" has been Created");
			loggedEventService.addLoggedEventInfo(loggedEvent);
	}


	private void saveOrderLog(Number id) throws InterruptedException {
		// TODO Auto-generated method stub
		EcommerceLog log=new EcommerceLog();
		log.setShopifyOrderId(Long.parseLong(id.toString()));
		EcommerceDAO eCommerceDAO = (EcommerceDAO) MmrBeanLocator.getInstance()
				.findBean("eCommerceDAO");
		
		 List  synchedList = Collections.synchronizedList(new LinkedList());
		   synchronized (synchedList) {
			   while (synchedList.isEmpty()) {
				   System.out.println("List is empty...");
				 
				   synchedList.wait(3000);
				   eCommerceDAO.insertEcommerceLog(log);
				   System.out.println("Waiting...");
				   break;
				   }
		   }
	}


	private boolean isCreatedShipment(Long id, String storeName) {
		// TODO Auto-generated method stub
		EcommerceDAO eCommerceDAO = (EcommerceDAO) MmrBeanLocator.getInstance()
				.findBean("eCommerceDAO");
		boolean reval=false;
		
		
		ShippingDAO shippingDAO = (ShippingDAO) MmrBeanLocator.getInstance()
				.findBean("shippingDAO");
	
 	 
		 List<EcommerceLog> logs=eCommerceDAO.getEcommerceLogs(id);
		/* if(logs!=null && logs.size()==1){
			reval=true;
		 }else*/ if(logs!=null && logs.size()==1){
			 reval=true;
		 }else{
			 reval=false;
		 }
		 
		 
	     /*ShippingOrder so = shippingDAO.getShippingOrderByReferenceOne(id);
		if(so==null){
			EcommerceLog log=new EcommerceLog();
			log.setShopifyOrderId(id);
			//eCommerceDAO.insertEcommerceLog(log);
			reval=true;
 		} 
			*/
		return reval;
	}


	public ShopifyController getShopifyShop() {
		return shopifyShop;
	}


	public void setShopifyShop(ShopifyController shopifyShop) {
		this.shopifyShop = shopifyShop;
	}


 

 
 

	/*private List<Charge> setChargerFromshopify(
			ShopifyShippingOrder shopifyOrder, ShippingOrder so) {
		// TODO Auto-generated method stub

		Charge ch = new Charge();
		ch.setCarrierId(so.getCarrierId());
		ch.setCarrierName(so.getCarrierName());
		ch.setCharge(Double.parseDouble(shopifyOrder.getShipping_lines().get(0)
				.getPrice()));
		ch.setChargeCode("FRT");
		ch.setCustomerTarrifRate(ch.getCharge());
		ch.setTariffRate(ch.getCharge());
		ch.setExchangerate(new BigDecimal(1));
		ch.setCost(ch.getCharge());
		ch.setName("Total charges from shopify");
		ch.setCostInLocalCurrency(ch.getCharge());
		List<Charge> charges = new ArrayList<Charge>();
		charges.add(ch);
		return charges;
	}
*/ 

}
