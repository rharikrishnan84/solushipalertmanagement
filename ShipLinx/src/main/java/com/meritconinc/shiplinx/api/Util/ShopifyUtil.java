package com.meritconinc.shiplinx.api.Util;

/**
 * Utility class for Shopify Related Functions
 * @author SELVAGANESH
 * 30-06-2015
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.InvalidKeyException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Base64;
import org.apache.struts2.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfImportedPage;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfWriter;
import com.meritconinc.mmr.dao.EcommerceDAO;
import com.meritconinc.mmr.model.admin.EcommerceStore;
import com.meritconinc.mmr.utilities.MmrBeanLocator;
import com.meritconinc.shiplinx.action.ShipmentAction;
import com.meritconinc.shiplinx.api.Constant.EcommerceAPIConstant;
import com.meritconinc.shiplinx.api.model.ShopifyCarrierService;
import com.meritconinc.shiplinx.api.model.ShopifyProduct;
import com.meritconinc.shiplinx.api.model.ShopifyShop;
import com.meritconinc.shiplinx.api.model.ShopifySolushipCarrier;
import com.meritconinc.shiplinx.dao.CarrierServiceDAO;
import com.meritconinc.shiplinx.model.Address;
import com.meritconinc.shiplinx.model.Charge;
import com.meritconinc.shiplinx.model.Customer;
import com.meritconinc.shiplinx.model.Rating;
import com.meritconinc.shiplinx.model.Service;
import com.meritconinc.shiplinx.service.CustomerManager;
import com.meritconinc.shiplinx.service.ShippingService;
import com.meritconinc.shiplinx.utils.FormattingUtil;
import com.opensymphony.xwork2.ActionContext;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class ShopifyUtil /*implements Runnable */ {

	/*
	 * ShippingDAO shippingDAO = (ShippingDAO)
	 * MmrBeanLocator.getInstance().findBean("shippingDAO"); PickupDAO pickupDAO
	 * = (PickupDAO) MmrBeanLocator.getInstance().findBean("pickupDAO");
	 * 
	 * AddressDAO addressDAO = (AddressDAO)
	 * MmrBeanLocator.getInstance().findBean("addressDAO"); BusinessDAO
	 * businessDAO = (BusinessDAO)
	 * MmrBeanLocator.getInstance().findBean("businessDAO"); CarrierServiceDAO
	 * carrierServiceDAO = (CarrierServiceDAO)
	 * MmrBeanLocator.getInstance().findBean("carrierServiceDAO");
	 */
	static ShippingService shippingService = (ShippingService) MmrBeanLocator
			.getInstance().findBean("shippingService");

	 
	/************************************* GET RATES ****************************/
	 
 
	/**
	 * getFastestRate for among the 
	 * rates (AIR service)
	 * @param ratingList
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public  static List<Rating> getFastestRate(List<Rating> ratingList) {
		// TODO Auto-generated method stub
		CarrierServiceDAO	carrierServiceDAO = (CarrierServiceDAO)
				 MmrBeanLocator.getInstance().findBean("carrierServiceDAO");
		List<Rating> rates=new ArrayList<Rating>();
		for(Rating rate:ratingList){
			Service service=carrierServiceDAO.getService(rate.getServiceId());
			if(service.getMode()==1){
				rates.add(rate);
			}
				
			
			
		}
		 Collections.sort(rates, Rating.PriceComparator);
		return rates;
	}

	@SuppressWarnings("unchecked")
	public static List<Rating> getChepestRate(List<Rating> ratingList) {
		// TODO Auto-generated method stub
		CarrierServiceDAO	carrierServiceDAO = (CarrierServiceDAO)
				 MmrBeanLocator.getInstance().findBean("carrierServiceDAO");
		List<Rating> rates=new ArrayList<Rating>();
		for(Rating rate:ratingList){
			Service service=carrierServiceDAO.getService(rate.getServiceId());
			if(service.getMode()==2){
				rates.add(rate);
			}
			
		}
		 Collections.sort(rates, Rating.PriceComparator);
		return rates;
 	}

	public static double getTotalCharge(List<Charge> charges) {
		// TODO Auto-generated method stub
		double tt=0;
		if(charges!=null && charges.size()>0){
									
									for(Charge ch:charges){
										tt+=ch.getCharge();
									}
									return tt*100;
								}else{
									return 0.0d;// free shipping
		}
	}
	
	
	public static String getmaxDeliveryDate(int transitDays) {
		// TODO Auto-generated method stub

		SimpleDateFormat sdf;
		// date.setDate(date);
		sdf = new SimpleDateFormat("yyyy-dd-mm hh:mm:ss Z");

		Calendar c = Calendar.getInstance();
		c.setTime(new Date()); // Now use today date.
		//System.out.println(sdf.format(c.getTime()));
		c.add(Calendar.DATE, transitDays); // Adding transitDays days
		String date = sdf.format(c.getTime());
		//System.out.println(sdf.format(c.getTime()));
		return date;

	}

	public static String getminDeliveryDate(int transitDays) {
		// TODO Auto-generated method stub

		SimpleDateFormat sdf;
		// date.setDate(date);		
		sdf = new SimpleDateFormat("yyyy-dd-mm hh:mm:ss Z");

		Calendar c = Calendar.getInstance();
		c.setTime(new Date()); // Now use today date.
		System.out.println(sdf.format(c.getTime()));
		c.add(Calendar.DATE, transitDays); // Adding transitDays days
		String date = sdf.format(c.getTime());
		System.out.println(sdf.format(c.getTime()));
		return date;

	}

	 
	/************************************* CREATE ORDER ****************************/

	
	/**
	 * getCustomerId For ShopifyStore
	 * @param storeUrl
	 * @return
	 */
	public static Long getCustomerIdForShopifyStore(String storeUrl) {
		// TODO Auto-generated method stub
		 if(storeUrl!=null){
			 
			EcommerceDAO eCommerceDAO = (EcommerceDAO) MmrBeanLocator.getInstance()
					.findBean("eCommerceDAO");
			EcommerceStore store = eCommerceDAO.getEcomStorebyStoreUrl(storeUrl);
		       if(store!=null){
				return store.getCustomerId();
		       }else{
		    	   return 0L;
		       }
		  }else{
	    	   return 0L;
	      }
	}


	 
 
	
  /**
   *  get Date In Timestamp
   * @param date
   * @return
   */
	public static Timestamp getDateInTimestamp(String date) {
		Timestamp ts = null;
		if (date != null) {
			Date dd = FormattingUtil.getDate(date.toString(),
					FormattingUtil.DATE_FORMAT_WEB);
			ts = new Timestamp(dd.getTime());
		}
		return ts;

	}

	/*public static Rating getRatingForShopifyOrder(ShippingOrder order) {
		// TODO Auto-generated method stub
		List<Rating> rating = null;
		List<Service> services = new ArrayList<Service>();

		services.add(order.getService());
		CarrierServiceDAO carrierServiceDAO = (CarrierServiceDAO) MmrBeanLocator
				.getInstance().findBean("carrierServiceDAO");
		CustomerCarrier customerCarrier = carrierServiceDAO.getCutomerCarrierDefaultAccount(order.getCarrierId(),
						order.getCustomerId());
		if(order.getCarrier()!=null && order.getCarrier()
				.getBusinessCarrierDiscount()!=0){
		customerCarrier.setBusinessCarrierDiscount(order.getCarrier()
				.getBusinessCarrierDiscount());
		}
		DHLAPI dhlService = (DHLAPI) MmrBeanLocator.getInstance().findBean(
				"dhlService");
		FedEx fedexService = (FedEx) MmrBeanLocator.getInstance().findBean(
				"fedexService");
		UPSAPI upsService = (UPSAPI) MmrBeanLocator.getInstance().findBean(
				"upsService");
		LoomisAPI loomisService = (LoomisAPI) MmrBeanLocator.getInstance()
				.findBean("loomisService");
		PurolatorAPI purolatorService = (PurolatorAPI) MmrBeanLocator
				.getInstance().findBean("purolatorService");
		MidlandAPI midlandService = (MidlandAPI) MmrBeanLocator.getInstance()
				.findBean("midlandService");
		if (dhlService != null
				&& order.getCarrierId() == dhlService.getCarrierId()) {
			rating = dhlService.rateShipment(order, services,
					order.getCarrierId(), customerCarrier);
		} else if (fedexService != null
				&& order.getCarrierId() == fedexService.getCarrierId()) {
			rating = fedexService.rateShipment(order, services,
					order.getCarrierId(), customerCarrier);
		} else if (upsService != null
				&& order.getCarrierId() == upsService.getCarrierId()) {
			rating = upsService.rateShipment(order, services,
					order.getCarrierId(), customerCarrier);
		} else if (loomisService != null
				&& order.getCarrierId() == loomisService.getCarrierId()) {
			rating = loomisService.rateShipment(order, services,
					order.getCarrierId(), customerCarrier);
		} else if (purolatorService != null
				&& order.getCarrierId() == purolatorService.getCarrierId()) {
			rating = purolatorService.rateShipment(order, services,
					order.getCarrierId(), customerCarrier);
		} else if (midlandService != null
				&& order.getCarrierId() == midlandService.getCarrierId()) {
			rating = midlandService.rateShipment(order, services,
					order.getCarrierId(), customerCarrier);
		}

		if (rating != null && rating.size() > 0) {
			return rating.get(0);
		}

		return null;

	}*/

	 

	public static void addAccesKeyForStore(String shop2, String accessKey) {
		// TODO Auto-generated method stub
		EcommerceDAO eCommerceDAO = (EcommerceDAO) MmrBeanLocator.getInstance()
				.findBean("eCommerceDAO");
		eCommerceDAO.UpdateAccessKeyForStore(shop2,accessKey);
		
	}
 
	
	/**
	 * SHOPIFY WEBHOOKS CREATION FOR CRETAE ORDER
	 * @param shopUrl
	 * @param accessKey2
	 */
	public static String setOrderCreationWebhooksForShopify(String shopUrl,String accessKey2
			 ) {
		// TODO Auto-generated method stub
		long  ordercreateid =0l;
		if (accessKey2 != null && shopUrl != null) {

			String url = shopUrl + "/admin/webhooks.json";
 			Client client = Client.create();
			WebResource webResource = client.resource(url);
			String orderCreationJson = getOrderCreationJson(shopUrl);
			ClientResponse response = webResource.type("application/json")
					.header("Content-Type", "application/json;")
					.header("username", "ranger").header("password", "ranger")
					.header("Accept-Language", "en_us")
					.header("Accept-Encoding", "gzip, deflate")
					.header("Accept", "application/json")
					.header("X-Shopify-Access-Token", accessKey2)
					.post(ClientResponse.class, orderCreationJson);
			String output = response.getEntity(String.class);
			try {
				JSONObject js=new JSONObject(output);
				JSONObject dd=js.getJSONObject("webhook");
				ordercreateid=dd.getLong("id");
			} catch (org.json.JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Output from Server .... \n" + output);
 		}
		return Long.toString(ordercreateid);

	}

	private static String getOrderCreationJson(String shopUrl) {
		// TODO Auto-generated method stub
		EcommerceDAO eCommerceDAO = (EcommerceDAO) MmrBeanLocator.getInstance()
				.findBean("eCommerceDAO");
		EcommerceStore store=eCommerceDAO.getEcomStorebyStoreUrl(shopUrl);
		String json1 = null;
		if(store!=null){
		json1 = "{\"webhook\":{\"topic\" : \"orders\\/create\",\"address\" : \""
				+ getSecuredUrl(store.getCreateShipmentUrl(),store.getCustomerId())
				+ "\",\"format\" :\"json\"}}";
		}
		return json1;
	}

	 
	
	/*
	 * public static void main(String gss[]){
	 * 
	 * String accc="1f96113c5868bdf2a403abc8dc885d0c"; EcommerceStore ec=new
	 * EcommerceStore(); ec.setUrl("https://shipshop-4.myshopify.com"); //
	 * setOrderCreationWebhooksForShopify(accc,ec);
	 * setCustomerCarrierForShopify(accc, ec); }
	 */
	public static String setCustomerCarrierForShopify(String shopUrl,String accessKey2) {
		// TODO Auto-generated method stub
	  long createId=0L;
		if (accessKey2 != null && shopUrl != null) {
            
			String url = shopUrl+ "/admin/carrier_services.json";
			Client client = Client.create();
			WebResource webResource = client.resource(url);
			String carrierserviceJson = getCarrierSericeJson(shopUrl);

			ClientResponse response = webResource.type("application/json")
					.header("Content-Type", "application/json;")
					.header("username", "ranger").header("password", "ranger")
					.header("Accept-Language", "en_us")
					.header("Accept-Encoding", "gzip, deflate")
					.header("Accept", "application/json")
					.header("X-Shopify-Access-Token", accessKey2)
					.post(ClientResponse.class, carrierserviceJson);

			String output = response.getEntity(String.class);
			System.out.println("Output from Server .... \n" + output);
			try {
				JSONObject js=new JSONObject(output);
				JSONObject dd=js.getJSONObject("carrier_service");
				createId=dd.getLong("id");
			} catch (org.json.JSONException e) {
				// TODO Auto-generated catch block
				
				e.printStackTrace();
				
			}

		}
		return Long.toString(createId);
	}

	private static String getCarrierSericeJson(String shopUrl) {
		// TODO Auto-generated method stub
		if(!shopUrl.startsWith("https://")){
			shopUrl="https://"+shopUrl;
		}
		EcommerceDAO eCommerceDAO = (EcommerceDAO) MmrBeanLocator.getInstance()
				.findBean("eCommerceDAO");
		 EcommerceStore store=eCommerceDAO.getEcomStorebyStoreUrl(shopUrl);
		 
		ShopifyCarrierService shopifyCarrier = new ShopifyCarrierService();
		ShopifySolushipCarrier scc = new ShopifySolushipCarrier();
		scc.setCallback_url(getSecuredUrl(store.getRateServiceUrl(),store.getCustomerId()));
		scc.setName(EcommerceAPIConstant.SHOPIFY_SOLUHSIP_API_NAME);
		scc.setFormat(EcommerceAPIConstant.SHOPIFY_API_CARRIERSERVICE_FORMAT);
		scc.setService_discovery("true");
		shopifyCarrier.setCarrier_service(scc);
		Gson gson = new Gson();
		String json = gson.toJson(shopifyCarrier);
		return json;
	}

 
	 

	/**********************************
	 * API AUTHENDICATION
	 * 
	 * @throws IOException
	 ******************************/
    /**
     * API AUTHENTICATION
     * @param shopifyHmac
     * @param data
     * @return
     * @throws InvalidKeyException
     * @throws IOException
     */
	public static boolean authedicateRequest(String shopifyHmac, String data)
			throws InvalidKeyException, IOException {
		// TODO Auto-generated method stub

		String calucaltedHmac = CalculateHmac(data);
		if (shopifyHmac != null && calucaltedHmac != null
				&& shopifyHmac.equals(calucaltedHmac)) {
			return true;
		}
 		return true;

	}
   
	
	/**
    * Calculate Hmac for Shopify
    * @param input
    * @return
    * @throws IOException
    */
	public static String CalculateHmac(String input) throws IOException {

		String input1 = null;
		try {
			String secret = EcommerceAPIConstant.SHOPIFY_SHARED_SCRECT;
 			Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
			SecretKeySpec secret_key = new SecretKeySpec(secret.getBytes(),
					"HmacSHA256");
			sha256_HMAC.init(secret_key);
			byte[] hash = Base64.encodeBase64(sha256_HMAC.doFinal(input
					.getBytes()));
			input1 = new String(hash, "UTF-8");

		} catch (Exception e) {
			System.out.println("Error");
		}
		return input1;
	}
	
	
    /**
     * GET HEADER HASMAP FROM REQUEST
     * @param request
     * @return
     */
	@SuppressWarnings("rawtypes")
	public static Map<String, String> getHeadersInfo(HttpServletRequest request) {

		Map<String, String> map = new HashMap<String, String>();

		Enumeration headerNames = request.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String key = (String) headerNames.nextElement();
			String value = request.getHeader(key);
			map.put(key, value);
		}

		return map;
	}
 
	 

	  
	
	 
	  public static void concatPDFs(List<InputStream> streamOfPDFFiles, OutputStream outputStream, boolean paginate) {

		    Document document = new Document();
		    try {
		      List<InputStream> pdfs = streamOfPDFFiles;
		      List<PdfReader> readers = new ArrayList<PdfReader>();
		      int totalPages = 0;
		      Iterator<InputStream> iteratorPDFs = pdfs.iterator();

		      // Create Readers for the pdfs.
		      while (iteratorPDFs.hasNext()) {
		        InputStream pdf = iteratorPDFs.next();
		        PdfReader pdfReader = new PdfReader(pdf);
		        readers.add(pdfReader);
		        totalPages += pdfReader.getNumberOfPages();
		      }
		      // Create a writer for the outputstream
		      PdfWriter writer = PdfWriter.getInstance(document, outputStream);

		      document.open();
		      BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
		      PdfContentByte cb = writer.getDirectContent(); // Holds the PDF
		      // data

		      PdfImportedPage page;
		      int currentPageNumber = 0;
		      int pageOfCurrentReaderPDF = 0;
		      Iterator<PdfReader> iteratorPDFReader = readers.iterator();

		      // Loop through the PDF files and add to the output.
		      while (iteratorPDFReader.hasNext()) {
		        PdfReader pdfReader = iteratorPDFReader.next();

		        // Create a new page in the target for each source page.
		        while (pageOfCurrentReaderPDF < pdfReader.getNumberOfPages()) {
		          document.newPage();
		          pageOfCurrentReaderPDF++;
		          currentPageNumber++;
		          page = writer.getImportedPage(pdfReader, pageOfCurrentReaderPDF);
		          cb.addTemplate(page, 0, 0);

		          // Code for pagination.
		          if (paginate) {
		            cb.beginText();
		            cb.setFontAndSize(bf, 9);
		            cb.showTextAligned(PdfContentByte.ALIGN_CENTER, "" + currentPageNumber + " of " + totalPages, 520, 5, 0);
		            cb.endText();
		          }
		        }
		        pageOfCurrentReaderPDF = 0;
		      }
		      outputStream.flush();
		      document.close();
		      outputStream.close();
		    } catch (Exception e) {
		      e.printStackTrace();
		    } finally {
		      if (document.isOpen())
		        document.close();
		      try {
		        if (outputStream != null)
		          outputStream.close();
		      } catch (IOException ioe) {
		        ioe.printStackTrace();
		      }
		    }
		  }
	   
	  
	  
	  /**
	   *  find city using zip and country code
	   * @param zipCode
	   * @param countryCode
	   * @return
	   * @throws Exception
	   */
	  public static String findCity(String zipCode,String countryCode) throws Exception{
		  ShipmentAction shipmentAction = (ShipmentAction) MmrBeanLocator.getInstance().findBean("shipmentAction");
		 Address add= shipmentAction.findAddressSuggest(zipCode, countryCode);
		 return (add.getCity());
		  
	  }
	  
	  
	  /**
	   * Get AccessKey  of shop
	   * @param shop
	   * @param code
	   * @return
	   * @throws JSONException
	   */
	  public static String getAccessKeyForShopify(String shop, String code) throws JSONException {
			// TODO Auto-generated method stub
		  if(!shop.startsWith(("https://"))){
			  shop="https://"+shop;
		  }
 			String url =   shop + "/admin/oauth/access_token";
			Client client = Client.create();
			WebResource webResource = client.resource(url);

			String input = "{\"client_id\":\""
					+ EcommerceAPIConstant.SHOPIFY_API_KEY
					+ "\",\"client_secret\":\""
					+ EcommerceAPIConstant.SHOPIFY_SHARED_SCRECT + "\",\"code\":\""
					+ code + "\"}";

			ClientResponse response = webResource
					.type("application/json")
					// .header("code", code)
					// .header("Accept-Encoding", "gzip, deflate")
					.header("Content-Type", "application/json;")
					.header("Accept-Language", "en_us").header("charset", "utf-8")

					.post(ClientResponse.class, input);

			String output = response.getEntity(String.class);
			String accessKey = null;
			JSONObject json = null;
			try {
				json = new JSONObject(output);
			} catch (org.json.JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				accessKey = (String) json.get("access_token");
			} catch (org.json.JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Shopify ACCESSEd");



			return accessKey;
		}

 
	 
 
	
	/**
	 * Get shopify Product
	 * @param accessKey
	 * @param shopUrl
	 * @return
	 */
	public static ShopifyProduct synchProducts(String accessKey, String shopUrl) {
		// TODO Auto-generated method stub
		String url =   shopUrl + "/admin/products.json";
		Client client = Client.create();
		WebResource webResource = client.resource(url);
		ClientResponse response = webResource
				.type("application/json")
				.header("X-Shopify-Access-Token", accessKey)
				.get(ClientResponse.class);

		String output = response.getEntity(String.class);
		 Gson gs=new Gson();
		 ShopifyProduct shp=gs.fromJson(output, ShopifyProduct.class);
		return shp;
		 
		
	}
 
   /**
    * Get Store Details
    * @param storeName
    * @return
    */
    public static  ShopifyShop getStore(String storeName) {
		// TODO Auto-generated method stub
		if(storeName!=null){
			String storeUrl="https://"+storeName;
			EcommerceDAO eCommerceDAO = (EcommerceDAO) MmrBeanLocator.getInstance()
					.findBean("eCommerceDAO");
			EcommerceStore store = eCommerceDAO.getEcomStorebyStoreUrl(storeUrl);
			if(store!=null){
				 
					String url = storeUrl + "/admin/shop.json";
					Client client = Client.create();
					WebResource webResource = client.resource(url);
				 
					ClientResponse response = webResource.type("application/json")
 							.header("X-Shopify-Access-Token", store.getAccessKey())
							.get(ClientResponse.class);
					String output = response.getEntity(String.class);
					Gson gs=new Gson();
					ShopifyShop shop=gs.fromJson(output,ShopifyShop.class);
				    return shop;
				 
			}
		}
		return null;
	}
	
    /**
     * set Store details in session
     * @param storeName
     */
	public static void setStoreDetails(String storeName) {
		// TODO Auto-generated method stub
		 Long customerId=ShopifyUtil.getCustomerIdForShopifyStore("https://"+storeName);
		 if(customerId!=0L){
			 ShopifyShop store=ShopifyUtil.getStore(storeName);
			 ActionContext.getContext().getSession().put("SHOPIFY_STORE",store);
			 ActionContext.getContext().getSession().put("SHOPIFY_CUSTOMER_ID",customerId);
		 }
	}
	
	
	/**
	 * Adding the security headers
	 * to the Service call back url 
	 * @param url
	 * @param customerId
	 * @return
	 */
  public static String getSecuredUrl(String url,long customerId){
	  CustomerManager customerService = (CustomerManager) MmrBeanLocator
				.getInstance().findBean("customerService");
	  Customer cs=null;
	  try {
		cs=customerService.getCustomerInfoByCustomerId(customerId);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  if(cs!=null){
	  url+="?username="+cs.getApiUsername()+"&password="+cs.getApiPassword();
	  }
	  return url;
  }
  
  
 /**
  * validate the shop's request
  * with API USERNAME, API PASSWORD
  * of mapped with the customer
  * @param shopUrl
  * @param username
  * @param password
  * @return
  */
 public static boolean isValidRequest(String shopUrl, String username, String password) {
		// TODO Auto-generated method stub
		  long customerId=ShopifyUtil.getCustomerIdForShopifyStore("https://"+shopUrl);
		  if(customerId==0){
			  return false;
		  }else{
			  CustomerManager customerService = (CustomerManager) MmrBeanLocator
						.getInstance().findBean("customerService");
			  try {
				Customer customer=customerService.getCustomerInfoByCustomerId(customerId);
				if(customer!=null){
					if( customer.getApiPassword()!=null && customer.getApiUsername()!=null && customer.getApiUsername().equals(username) && customer.getApiPassword().equals(password)){
						return true;
					}
				} 
			} catch (Exception e) {
				// TODO Auto-generated catch block
			}
		  }
		 System.out.println("ACCESS DEINED ON"+new Date());
		return false;
	}
 
/**
 * kgsToLBS
 * @param parseDouble
 * @return
 */
public static float kgsToLBS(double parseDouble) {
	// TODO Auto-generated method stub
	double dd = (Double) parseDouble;

	return (float) (dd * 2.20462); 
}

/**
 * ozToLBS
 * @param parseDouble
 * @return
 */
public static float ozToLBS(double parseDouble) {
	// TODO Auto-generated method stub
	
	double dd = (Double) parseDouble;

	return (float) (dd * 0.0625);
}


/**
* gram to LB
* @param total_weight
* @return
*/
public static double gramToLBS(Double total_weight) {
	// TODO Auto-generated method stub
	double dd = (Double) total_weight;

	return (dd / 1000) * 2.20462;
}

/**
 * PDF documents merge
 * @throws IOException 
 * @throws DocumentException 
 */
public static void PdfMerger(List<File> files,File destination) throws DocumentException, IOException{
 
	List<InputStream> insList=new ArrayList<InputStream>();
	for(File f:files){
		insList.add(new FileInputStream(f));
	}
        // Resulting pdf
        OutputStream out;
		out = new FileOutputStream(destination);
		doMerge(insList, out);
}

public static void doMerge(List<InputStream> list, OutputStream outputStream)
        throws DocumentException, IOException {
    Document document = new Document();
    PdfWriter writer = PdfWriter.getInstance(document, outputStream);
    document.open();
    PdfContentByte cb = writer.getDirectContent();
   
    for (InputStream in : list) {
        PdfReader reader = new PdfReader(in);
        for (int i = 1; i <= reader.getNumberOfPages(); i++) {
            document.newPage();
            //import the page from source pdf
            PdfImportedPage page = writer.getImportedPage(reader, i);
            //add the page to the destination pdf
            cb.addTemplate(page, 0, 0);
        }
    }
   
    outputStream.flush();
    document.close();
    outputStream.close();
}

public static void FilesToZip(final List<File> files, final File targetZipFile) throws IOException {
    try {
      FileOutputStream   fos = new FileOutputStream(targetZipFile);
      ZipOutputStream zos = new ZipOutputStream(fos);
      byte[] buffer = new byte[128];
      for (File file:files) {
        File currentFile = file;
        if (!currentFile.isDirectory()) {
          ZipEntry entry = new ZipEntry(currentFile.getName());
          FileInputStream fis = new FileInputStream(currentFile);
          zos.putNextEntry(entry);
          int read = 0;
          while ((read = fis.read(buffer)) != -1) {
            zos.write(buffer, 0, read);
          }
          zos.closeEntry();
          fis.close();
        }
      }
      zos.close();
      fos.close();
    } catch (FileNotFoundException e) {
      System.out.println("File not found : " + e);
    }

  }

/**
 * Updating the create shipment (Webhook) in 
 * Shopify
 * @param ecommerceStore2
 */
public static void updateCreateShipmentService(EcommerceStore ecommerceStore2) {
	// TODO Auto-generated method stub
	if (ecommerceStore2!=null && ecommerceStore2.getAccessKey() != null && ecommerceStore2.getUrl() != null) {

		String shopUrl=ecommerceStore2.getUrl();
		if(!shopUrl.startsWith("https://")){
			shopUrl="https://"+shopUrl;
		}
		String url = shopUrl + "/admin/webhooks/"+ecommerceStore2.getCreateShipWebhookId()+".json";
			Client client = Client.create();
		WebResource webResource = client.resource(url);
		String orderCreationJson = getUpdateWebhookJson(ecommerceStore2.getCreateShipWebhookId(),ecommerceStore2.getCustomerId(),ecommerceStore2.getCreateShipmentUrl());
		ClientResponse response = webResource.type("application/json")
 				    .header("Accept", "application/json")
     				.header("X-Shopify-Access-Token", ecommerceStore2.getAccessKey())
				.put(ClientResponse.class, orderCreationJson);
		String output = response.getEntity(String.class);
		System.out.println("Output from Server .... \n" + output);
	}
}

private static String getUpdateWebhookJson(String webhookId,Long customerId,String url) {
	// TODO Auto-generated method stub
	String json1 = null;
	json1 = "{\"webhook\":{\"id\":"+webhookId+",\"address\" : \""
			+ getSecuredUrl(url,customerId)
			+ "\"}}";
	return json1;
}

/**
 * Updating the Rate service url to shopify
 * @param ecommerceStore2
 */
public static String updateRateService(EcommerceStore ecommerceStore2) {
	// TODO Auto-generated method stub
	if (ecommerceStore2!=null && ecommerceStore2.getAccessKey() != null && ecommerceStore2.getUrl() != null) {

		String shopUrl=ecommerceStore2.getUrl();
		if(!shopUrl.startsWith("https://")){
			shopUrl="https://"+shopUrl;
		}
		String url = shopUrl + "/admin/carrier_services/"+ecommerceStore2.getRateServiceId()+".json";
		Client client = Client.create();
		WebResource webResource = client.resource(url);
 		ClientResponse response = webResource.type("application/json")
				.header("Content-Type", "application/json;")
				.header("X-Shopify-Access-Token", ecommerceStore2.getAccessKey())
				.delete(ClientResponse.class);
 		String output=null;
 		if(response.getResponseStatus().getStatusCode()==200){
		  output = setCustomerCarrierForShopify(ecommerceStore2.getUrl(),ecommerceStore2.getAccessKey());
		  return output;
 		}
		System.out.println("Output from Server .... \n" + output);
	}
	return null;
}
 
/**
 * Updating the cancel shipment(Webhook) in 
 * Shopify
 * @param ecommerceStore2
 */
public static void updateCancelShipmentService(EcommerceStore ecommerceStore2) {
	// TODO Auto-generated method stub
	if (ecommerceStore2!=null && ecommerceStore2.getAccessKey() != null && ecommerceStore2.getUrl() != null) {

		String shopUrl=ecommerceStore2.getUrl();
		if(!shopUrl.startsWith("https://")){
			shopUrl="https://"+shopUrl;
		}
		String url = shopUrl + "/admin/webhooks/"+ecommerceStore2.getCancelShipWebhookId()+".json";
			Client client = Client.create();
		WebResource webResource = client.resource(url);
		String orderCreationJson = getUpdateWebhookJson(ecommerceStore2.getCancelShipWebhookId(),ecommerceStore2.getCustomerId(),ecommerceStore2.getCancelShipmentUrl());
		ClientResponse response = webResource.type("application/json")
				.header("Content-Type", "application/json;")
				.header("Accept-Language", "en_us")
				.header("Accept-Encoding", "gzip, deflate")
				.header("Accept", "application/json")
				.header("X-Shopify-Access-Token", ecommerceStore2.getAccessKey())
				.put(ClientResponse.class, orderCreationJson);
		String output = response.getEntity(String.class);
		System.out.println("Output from Server .... \n" + output);
	}
}

public static String setOrderCancelWebhooksForShopify(String shopUrl, String accessKey2) {
	// TODO Auto-generated method stub
	long  ordercreateid =0l;
	if (accessKey2 != null && shopUrl != null) {
		if(!shopUrl.startsWith("https://")){
			shopUrl="https://";
		}
		String url = shopUrl + "/admin/webhooks.json";
			Client client = Client.create();
		WebResource webResource = client.resource(url);
		String orderCreationJson = getOrderCancelJson(shopUrl);
		ClientResponse response = webResource.type("application/json")
				.header("Content-Type", "application/json;")
 				.header("Accept-Language", "en_us")
				.header("Accept-Encoding", "gzip, deflate")
				.header("Accept", "application/json")
				.header("X-Shopify-Access-Token", accessKey2)
				.post(ClientResponse.class, orderCreationJson);
		String output = response.getEntity(String.class);
		try {
			JSONObject js=new JSONObject(output);
			JSONObject dd=js.getJSONObject("webhook");
			ordercreateid=dd.getLong("id");
		} catch (org.json.JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
		System.out.println("Output from Server .... \n" + output);
		}
	return Long.toString(ordercreateid);
	
}

private static String getAppUnintallJson(String shopUrl) {
	// TODO Auto-generated method stub
	EcommerceDAO eCommerceDAO = (EcommerceDAO) MmrBeanLocator.getInstance()
			.findBean("eCommerceDAO");
	EcommerceStore store=eCommerceDAO.getEcomStorebyStoreUrl(shopUrl);
	String json1 = null;
	if(store!=null){
	json1 = "{\"webhook\":{\"topic\" : \"app\\/uninstalled\",\"address\" : \""
			+ getSecuredUrl(store.getUninstallUrl(),store.getCustomerId())
			+ "\",\"format\" :\"json\"}}";
	}
	return json1;
}

private static String getOrderCancelJson(String shopUrl) {
	// TODO Auto-generated method stub
	EcommerceDAO eCommerceDAO = (EcommerceDAO) MmrBeanLocator.getInstance()
			.findBean("eCommerceDAO");
	EcommerceStore store=eCommerceDAO.getEcomStorebyStoreUrl(shopUrl);
	String json1 = null;
	if(store!=null){
	json1 = "{\"webhook\":{\"topic\" : \"orders\\/cancelled\",\"address\" : \""
			+ getSecuredUrl(store.getCancelShipmentUrl(),store.getCustomerId())
			+ "\",\"format\" :\"json\"}}";
	}
	return json1;
 }

public static void updateServices(EcommerceStore store) {
	// TODO Auto-generated method stub
	EcommerceDAO eCommerceDAO = (EcommerceDAO) MmrBeanLocator.getInstance()
			.findBean("eCommerceDAO");
	EcommerceStore store1=eCommerceDAO.getEcomStorebyStoreUrl(store.getUrl());
	store.setEcommerceStoreId(store1.getEcommerceStoreId());
	store.setActive(true);
    Timestamp updatedAt = new Timestamp(new Date().getTime()); 
    store.setUpdatedAt(updatedAt);
    store.setInstalledAt(updatedAt);
    store.setWebhookUnistallId(null);
	eCommerceDAO.UpdateServices(store);
}

public static String ShopifyAppUninstallWebhook(String shopUrl, String accessKey2) {
	// TODO Auto-generated method stub
	
	long  ordercreateid =0l;
	if (accessKey2 != null && shopUrl != null) {
		if(!shopUrl.startsWith("https://")){
			shopUrl="https://";
		}
		String url = shopUrl + "/admin/webhooks.json";
			Client client = Client.create();
		WebResource webResource = client.resource(url);
		String orderCreationJson = getAppUnintallJson(shopUrl);
		ClientResponse response = webResource.type("application/json")
				.header("Content-Type", "application/json;")
 				.header("Accept-Language", "en_us")
				.header("Accept-Encoding", "gzip, deflate")
				.header("Accept", "application/json")
				.header("X-Shopify-Access-Token", accessKey2)
				.post(ClientResponse.class, orderCreationJson);
		String output = response.getEntity(String.class);
		try {
			JSONObject js=new JSONObject(output);
			JSONObject dd=js.getJSONObject("webhook");
			ordercreateid=dd.getLong("id");
		} catch (org.json.JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
		System.out.println("Output from Server .... \n" + output);
		}
	return Long.toString(ordercreateid);
}
}
	
 
