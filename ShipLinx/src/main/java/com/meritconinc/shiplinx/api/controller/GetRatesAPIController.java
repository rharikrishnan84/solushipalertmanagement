package com.meritconinc.shiplinx.api.controller;

/**
 * Rest API service for getting the rates .
 * @author selva
 * Date 22-06-2015
 * 
 * 
 */

import java.io.IOException;
import java.security.InvalidKeyException;
import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.json.JSONException;
import org.json.JSONObject;
import org.restlet.data.Form;
import org.restlet.resource.Post;

import com.meritconinc.mmr.dao.EcommerceDAO;
import com.meritconinc.mmr.model.admin.EcommerceStore;
import com.meritconinc.mmr.utilities.MmrBeanLocator;
import com.meritconinc.shiplinx.api.Util.ShopifyUtil;
import com.meritconinc.shiplinx.api.base.GenericRestServerResource;
import com.meritconinc.shiplinx.api.model.ShopifyRateRequest;
import com.meritconinc.shiplinx.model.Rating;
import com.meritconinc.shiplinx.model.ShippingOrder;
import com.meritconinc.shiplinx.service.CarrierServiceManager;
import com.opensymphony.xwork2.ActionContext;

public class GetRatesAPIController extends GenericRestServerResource implements
		ServletRequestAware {

	private static final Logger log = LogManager
			.getLogger(GetRatesAPIController.class);

	private CarrierServiceManager carrierServiceManager = (CarrierServiceManager) MmrBeanLocator
			.getInstance().findBean("carrierServiceManager");

	HttpServletRequest request;

	private Form headers;
	Thread t1 = null;
	private ShopifyController shopifyShop;

	/**
	 * webservice gets the request from shopify send the rates as response
	 * 
	 * @param entity
	 *            (shopify request JSON string)
	 * @return rates to shopify
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 * @throws ParseException
	 * @throws JSONException
	 * @throws InvalidKeyException
	 */
	@Post("json")
	public String getRatesForshopify(String entity) throws JsonParseException,
			JsonMappingException, IOException, ParseException, JSONException,
			InvalidKeyException {

		ActionContext.getContext().getSession()
				.put("startTime", System.currentTimeMillis());
		

		headers = (Form) getRequestAttributes().get("org.restlet.http.headers");

		if (ShopifyUtil.authedicateRequest(
				headers.getValues("x-shopify-hmac-sha256"), entity)) {
			EcommerceDAO eCommerceDAO = (EcommerceDAO) MmrBeanLocator
					.getInstance().findBean("eCommerceDAO");

			String storename = headers.getValues("x-shopify-shop-domain");
			EcommerceStore store = eCommerceDAO
					.getEcomStorebyStoreUrl("https://" + storename);
			log.debug("----geting THE RATES FOR SHOPIFY-----for store "+storename);
			JSONObject json = new JSONObject(entity);
			ShopifyRateRequest shopifyRateRequest = new com.google.gson.Gson()
					.fromJson(json.get("rate").toString(),
							ShopifyRateRequest.class);

			if (shopifyRateRequest != null && store != null) {
				shopifyRateRequest.setStoreName(storename);
				ShippingOrder order = null;
				shopifyShop = new ShopifyController(shopifyRateRequest);
				Long customerId = ShopifyUtil
						.getCustomerIdForShopifyStore("https://"
								+ shopifyRateRequest.getStoreName());
				shopifyShop.setCustomerId(customerId);
				shopifyShop.setReqItems(shopifyRateRequest.getItems());
				shopifyShop.setPackageMap(store.isPackageMap());
				t1 = new Thread(shopifyShop);
				t1.start();
				try {
					ShopifyUtil.setStoreDetails(shopifyRateRequest
							.getStoreName());
					order = shopifyShop
							.castShopifyRequestToShippingOrder(shopifyRateRequest);
					t1.join();
					order.getToAddress().setCity(shopifyShop.getCity());
					if (store.isPackageMap()) {
						if(shopifyShop.getShipPackes()!=null && shopifyShop.getShipPackes().size()>0)
						order.setPackages(shopifyShop.getShipPackes());
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if (order != null) {

					List<Rating> ratingList = getCarrierServiceManager()
							.rateShipment(order);

					
					String rateJson = null;
					try {
						String storeurl = "https://"
								+ shopifyRateRequest.getStoreName();
						rateJson = shopifyShop.castResponceForShopify(
								ratingList, order, storeurl);
					} catch (org.apache.struts2.json.JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					log.debug("RATE RESPONSE SUCCESS "+ratingList.size()+" Rates found");
					return rateJson;
				}
			}

		}
		return entity;

	}

	public CarrierServiceManager getCarrierServiceManager() {
		return carrierServiceManager;
	}

	public void setCarrierServiceManager(
			CarrierServiceManager carrierServiceManager) {
		this.carrierServiceManager = carrierServiceManager;
	}

	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		this.request = arg0;
	}

	public ShopifyController getShopifyShop() {
		return shopifyShop;
	}

	public void setShopifyShop(ShopifyController shopifyShop) {
		this.shopifyShop = shopifyShop;
	}

}
