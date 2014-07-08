package com.meritconinc.shiplinx.service.impl;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.meritconinc.mmr.utilities.security.UserUtil;
import com.meritconinc.shiplinx.dao.ShippingDAO;
import com.meritconinc.shiplinx.exception.ShiplinxException;
import com.meritconinc.shiplinx.model.Address;
import com.meritconinc.shiplinx.model.Cart;
import com.meritconinc.shiplinx.model.CustomsInvoice;
import com.meritconinc.shiplinx.model.CustomsInvoiceProduct;
import com.meritconinc.shiplinx.model.Package;
import com.meritconinc.shiplinx.model.PackageType;
import com.meritconinc.shiplinx.model.ShippingOrder;
import com.meritconinc.shiplinx.service.CustomerManager;
import com.meritconinc.shiplinx.utils.ShiplinxConstants;

public class CartOrderParser {
	private static final Logger log = LogManager.getLogger(CartOrderParser.class);
	private ShippingDAO shippingDAO;
	private CustomerManager customerService;

	/**
	 * @return the shippingDAO
	 */
	public ShippingDAO getShippingDAO() {
		return shippingDAO;
	} 

	/**
	 * @param shippingDAO the shippingDAO to set
	 */
	public void setShippingDAO(ShippingDAO shippingDAO) {
		this.shippingDAO = shippingDAO;
	}

	/**
	 * @return the customerService
	 */
	public CustomerManager getCustomerService() {
		return customerService;
	}

	/**
	 * @param customerService the customerService to set
	 */
	public void setCustomerService(CustomerManager customerService) {
		this.customerService = customerService;
	}

	public List<PackageType> getAllPackages() {
		return shippingDAO.getAllPackages();
	}

	// We are parsing the XML Orders Using DOM parser
	public List<ShippingOrder> getAllUnshippedOrder(ShippingOrder so, Cart cart, String cartOrderId){
		List<ShippingOrder> orderList = new ArrayList<ShippingOrder>();
		try{
			String orderResponse="";
			if(cart!= null){
				orderResponse = this.getOrderResponse(cart,cartOrderId);
				
				if(orderResponse != null && orderResponse.length() > 0 && orderResponse.contains("<order") ){
					
					DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
					DocumentBuilder db = dbf.newDocumentBuilder();

					Document doc = db.parse(new InputSource(new StringReader(orderResponse)));

					/*File file = new File("D:\\Order_Response_10_Orders.xml");
					Document doc = db.parse(file);*/

					Element docEle = doc.getDocumentElement();
					String countryCode=so.getFromAddress().getCountryCode();
					NodeList nodeUnderOrderResponse = docEle.getChildNodes();
					log.debug("Node name is::"+docEle.getNodeName());
					
					// For All Unshipped Orders 
					if("orders".equalsIgnoreCase(docEle.getNodeName())){
						for(int i=0; i<nodeUnderOrderResponse.getLength(); i++){
							Node childnode = nodeUnderOrderResponse.item(i);
							String nodeName = childnode.getNodeName();
							if("order".equals(nodeName))
							{
								NodeList childNodeOrder = getChildNode(childnode);
								so = populateOrder(childNodeOrder,countryCode,cart.getCartName());
								orderList.add(this.setExtraOrder(so));
							}
						}
					// For Specific Unshipped Order Only	
					}else if("order".equalsIgnoreCase(docEle.getNodeName())){
						so = populateOrder(nodeUnderOrderResponse,countryCode,cart.getCartName());
						orderList.add(this.setExtraOrder(so));
					}
					log.debug("Order List::"+orderList.size());
				}
			}
			return orderList;
		}
		catch(Exception ex){
			log.error("Exception occured to getAllUnshippedOrder() method in CartOrderParser::",ex);
			throw new ShiplinxException(ex.getMessage());
		}
	}


	private ShippingOrder populateOrder(NodeList childNodes, String fromCountryCode, String cartName) throws Exception{

		String referenceCode = "";
		String shopifyOrderId = "";
		String currency = "";

		ShippingOrder order = new ShippingOrder();

		String toCountryCode="";

		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd_hh_mm_ss");

		String todayDate = formatter.format(Calendar.getInstance().getTimeInMillis());  

		log.debug("Date is::"+todayDate);

		StringBuffer batchId = new StringBuffer();
		batchId.append(cartName);
		batchId.append("-");
		batchId.append(todayDate);

		for(int i=0; i<childNodes.getLength(); i++){
			Node childNodeOrder = childNodes.item(i);

			log.debug("Node Name under Order::"+childNodeOrder.getNodeName());

			if("id".equals(childNodeOrder.getNodeName())){
				
				if(childNodeOrder.getChildNodes().item(0) != null){
				log.debug("---Order Id:-"+childNodeOrder.getChildNodes().item(0).getNodeValue());
				referenceCode = childNodeOrder.getChildNodes().item(0).getNodeValue();
				order.setReferenceCode(referenceCode);
				}

				//Setting Unique the Batch_Id
				order.setBatchId(batchId.toString());

				//Setting the Package Type and Schedule Ship Date
				order.setPackageTypeId(getPackageType(ShiplinxConstants.PACKAGE_TYPE_PACKAGE_STRING)); 
				order.setScheduledShipDate(new Timestamp(Calendar.getInstance().getTimeInMillis()));

				// ShippingOrder.Package
				Package p = new Package();
				p.setLength(new BigDecimal(1));  			
				p.setWidth(new BigDecimal(1)); 				
				p.setHeight(new BigDecimal(1)); 			
				p.setWeight(new BigDecimal(1)); 
				p.setInsuranceAmount(new BigDecimal(0)); 	
				p.setCodAmount(new BigDecimal(0));
				p.setDoc(false);
				p.setLength(p.getLength().setScale(1, BigDecimal.ROUND_UNNECESSARY));
				p.setHeight(p.getHeight().setScale(1, BigDecimal.ROUND_UNNECESSARY));
				p.setWidth(p.getWidth().setScale(1, BigDecimal.ROUND_UNNECESSARY));
				order.getPackages().add(p);
				order.setQuantity(order.getPackages().size());
			}
			
			if("name".equals(childNodeOrder.getNodeName())){
				
				if(childNodeOrder.getChildNodes().item(0) != null){
					shopifyOrderId = childNodeOrder.getChildNodes().item(0).getNodeValue();
					order.setReferenceTwo(shopifyOrderId);
				}
			}
			
			if("currency".equals(childNodeOrder.getNodeName())){
				
				if(childNodeOrder.getChildNodes().item(0) != null){
					currency = childNodeOrder.getChildNodes().item(0).getNodeValue();
					order.setCurrency(currency);
				}
			}
			
			if("customer".equals(childNodeOrder.getNodeName())){
				NodeList childNodeCustomer = getChildNode(childNodeOrder);
				populateCustomer(childNodeCustomer,order);
			}

			if("shipping-address".equals(childNodeOrder.getNodeName())){
				NodeList childNodeShippingAddress = getChildNode(childNodeOrder);
				populateShipToAddress(childNodeShippingAddress,order);

				// Fetching Country Code of Shipping address of passed order 
				if(order.getToAddress() != null && order.getToAddress().getCountryCode() != null)
					toCountryCode = order.getToAddress().getCountryCode();
			}

			if("line-items".equals(childNodeOrder.getNodeName())){
				NodeList childNodeLineItems = getChildNode(childNodeOrder);
				populateProductList(childNodeLineItems,order,fromCountryCode);
			}
		}

		//If order is international populate Custom Invoice Product. 
		if(fromCountryCode != null && fromCountryCode.equalsIgnoreCase(toCountryCode) && order.getCustomsInvoice().getProducts() != null && order.getCustomsInvoice().getProducts().size() > 0){
			order.getCustomsInvoice().getProducts().clear();
			order.setCustomsInvoiceRequired(false);
		}
		return order;
	}

	private  void populateCustomer(NodeList childNodes, ShippingOrder order) throws Exception{

		String customerId = "";

		//Always loop for two values name and amount and in the sequence
		for(int m=0; m<childNodes.getLength(); m++){
			Node childnode_order = childNodes.item(m);
			if("id".equals(childnode_order.getNodeName())){
				if(childnode_order.getChildNodes().item(0) != null){
					log.debug("---Customer Id:-"+childnode_order.getChildNodes().item(0).getNodeValue());
					customerId = childnode_order.getChildNodes().item(0).getNodeValue();
					order.setReferenceOne(customerId);
				}
			}
		}
	}

	private  void populateShipToAddress(NodeList childNodes, ShippingOrder order) throws Exception{

		StringBuffer sbCompany = new StringBuffer();

		String firstName = "";
		String lastName = "";
		String address1 = "";
		String address2 = "";
		String city = "";
		String zip = "";
		//String country = "";
		String company = "";
		String phone = "";
		String provinceCode="";
		String contactName="";
		String countryCode="";

		Address address = new Address();

		for(int m=0; m<childNodes.getLength(); m++){
			Node childnode_order = childNodes.item(m);

			if("address1".equals(childnode_order.getNodeName())){
				if(childnode_order.getChildNodes().item(0) != null){
					log.debug("---Ship To Address1:-"+childnode_order.getChildNodes().item(0).getNodeValue());
					address1 = childnode_order.getChildNodes().item(0).getNodeValue();
					address.setAddress1(address1);
				}
			}
			if("address2".equals(childnode_order.getNodeName())){
				if(childnode_order.getChildNodes().item(0) != null){
					log.debug("---Ship To Address2:-"+childnode_order.getChildNodes().item(0).getNodeValue());
					address2 = childnode_order.getChildNodes().item(0).getNodeValue();
					address.setAddress2(address2);
				}
			}
			if("city".equals(childnode_order.getNodeName())){
				if(childnode_order.getChildNodes().item(0) != null){
					log.debug("---Ship To City:-"+childnode_order.getChildNodes().item(0).getNodeValue());
					city = childnode_order.getChildNodes().item(0).getNodeValue();
					address.setCity(city);
				}
			}

			/*if("country".equals(childnode_order.getNodeName())){
				if(childnode_order.getChildNodes().item(0) != null){
					log.debug("---Ship To Id:-"+childnode_order.getChildNodes().item(0).getNodeValue());
					country = childnode_order.getChildNodes().item(0).getNodeValue();
					//address.setC(city);
				}
			}*/

			if("phone".equals(childnode_order.getNodeName())){
				if(childnode_order.getChildNodes().item(0) != null){
					log.debug("---Ship To Phone No.:-"+childnode_order.getChildNodes().item(0).getNodeValue());
					phone = childnode_order.getChildNodes().item(0).getNodeValue();
					address.setPhoneNo(phone);
				}
			}

			if("province-code".equals(childnode_order.getNodeName())){
				if(childnode_order.getChildNodes().item(0) != null){
					log.debug("---Ship To Province Code:-"+childnode_order.getChildNodes().item(0).getNodeValue());
					provinceCode = childnode_order.getChildNodes().item(0).getNodeValue();
					address.setProvinceCode(provinceCode);
				}
			}

			if("zip".equals(childnode_order.getNodeName())){
				if(childnode_order.getChildNodes().item(0) != null){
					log.debug("---Ship To Zip Code:-"+childnode_order.getChildNodes().item(0).getNodeValue());
					zip = childnode_order.getChildNodes().item(0).getNodeValue();
					address.setPostalCode(zip);
				}
			}

			if("name".equals(childnode_order.getNodeName())){
				if(childnode_order.getChildNodes().item(0) != null){
					log.debug("---Ship To Contact Name:-"+childnode_order.getChildNodes().item(0).getNodeValue());
					contactName = childnode_order.getChildNodes().item(0).getNodeValue();
					address.setContactName(contactName);
				}
			}

			if("country-code".equals(childnode_order.getNodeName())){
				if(childnode_order.getChildNodes().item(0) != null){
					log.debug("---Ship To Country Code:-"+childnode_order.getChildNodes().item(0).getNodeValue());
					countryCode = childnode_order.getChildNodes().item(0).getNodeValue();
					address.setCountryCode(countryCode);
				}
			}

			// Here we set Abbreviation Name if company tag is null then use first-name & last-name as Abbreviation Name.
			if("company".equals(childnode_order.getNodeName())){
				if(childnode_order.getChildNodes().item(0) != null){
					log.debug("---Ship To Company Name::-"+childnode_order.getChildNodes().item(0).getNodeValue());
					company = childnode_order.getChildNodes().item(0).getNodeValue();
					address.setAbbreviationName(company);
				}
			}else{

				if("first-name".equals(childnode_order.getNodeName())){
					if(childnode_order.getChildNodes().item(0) != null){
						log.debug("---Ship To First Name:-"+childnode_order.getChildNodes().item(0).getNodeValue());
						firstName = childnode_order.getChildNodes().item(0).getNodeValue();
						sbCompany.append(firstName+" ");
					}
				}
				if("last-name".equals(childnode_order.getNodeName())){
					if(childnode_order.getChildNodes().item(0) != null){
						log.debug("---Ship To Last Name:-"+childnode_order.getChildNodes().item(0).getNodeValue());
						lastName = childnode_order.getChildNodes().item(0).getNodeValue();
						sbCompany.append(lastName);
						address.setAbbreviationName(sbCompany.toString());
					}
				}
			}
		}
		order.setToAddress(address);
	}

	private void populateProductList(NodeList childNodes, ShippingOrder order, String fromCountryCode) throws Exception{

		CustomsInvoiceProduct product = new CustomsInvoiceProduct();

		List<CustomsInvoiceProduct> producList = new ArrayList<CustomsInvoiceProduct>();

		for(int i=0; i<childNodes.getLength(); i++){
			Node childNodeLineItem = childNodes.item(i);

			if("line-item".equals(childNodeLineItem.getNodeName())){
				if(childNodeLineItem.getChildNodes().item(0) != null){
					NodeList childNodeLineItemElement = getChildNode(childNodeLineItem);
					product = populateProduct(childNodeLineItemElement,fromCountryCode);
					producList.add(product);
				}
			}
		}
		log.debug("producList::"+producList.size());
		order.setCustomsInvoice(new CustomsInvoice());
		
		log.debug("Order Currency is::"+order.getCurrency());
		order.getCustomsInvoice().setCurrency(order.getCurrency());
		order.getCustomsInvoice().setBillTo("Consignee");
		order.getCustomsInvoice().setProducts(producList);
		order.setCustomsInvoiceRequired(true);
	}

	private CustomsInvoiceProduct populateProduct(NodeList childNodes, String fromCountryCode) throws Exception{

		CustomsInvoiceProduct product = new CustomsInvoiceProduct();
		long productId = 0;
		String productName = "";
		double unitPrice = 0.0d;
		int quantity = 0;
		long weight = 0;

		for(int i=0; i<childNodes.getLength(); i++){
			Node childNodeLineItemElement = childNodes.item(i);
			if("product-id".equals(childNodeLineItemElement.getNodeName())){
				if(childNodeLineItemElement.getChildNodes().item(0) != null){
					log.debug("---Product Id:-"+childNodeLineItemElement.getChildNodes().item(0).getNodeValue());
					productId = new Long(childNodeLineItemElement.getChildNodes().item(0).getNodeValue());
					product.setProductId(productId);
				}
			}

			if("title".equals(childNodeLineItemElement.getNodeName())){
				if(childNodeLineItemElement.getChildNodes().item(0) != null){
					log.debug("---Product Name:-"+childNodeLineItemElement.getChildNodes().item(0).getNodeValue());
					productName = childNodeLineItemElement.getChildNodes().item(0).getNodeValue();
					product.setProductDesc(productName);
				}
			}

			if("price".equals(childNodeLineItemElement.getNodeName())){
				if(childNodeLineItemElement.getChildNodes().item(0) != null){
					log.debug("---Unit Price:-"+childNodeLineItemElement.getChildNodes().item(0).getNodeValue());
					unitPrice = new Double(childNodeLineItemElement.getChildNodes().item(0).getNodeValue());
					product.setProductUnitPrice(unitPrice);
					//Origin Country Code set to Product
					product.setProductOrigin(fromCountryCode);
				}
			}

			if("quantity".equals(childNodeLineItemElement.getNodeName())){
				if(childNodeLineItemElement.getChildNodes().item(0) != null){
					log.debug("---Product Order Quantity:-"+childNodeLineItemElement.getChildNodes().item(0).getNodeValue());
					quantity = new Integer(childNodeLineItemElement.getChildNodes().item(0).getNodeValue());
					product.setProductQuantity(quantity);

				}
			}

			/*if("grams".equals(childNodeLineItemElement.getNodeName())){
				if(childNodeLineItemElement.getChildNodes().item(0) != null){
					log.debug("---Product Weight:-"+childNodeLineItemElement.getChildNodes().item(0).getNodeValue());
					weight = new Long(childNodeLineItemElement.getChildNodes().item(0).getNodeValue());
					weight = weight/1000; 
					//Convert to KG
					product.setProductWeight(weight);
				}
			}*/
		}
		return product;
	}

	private  NodeList getChildNode(Node node){
		return node.getChildNodes();
	}

	// Setting the data to Shipping Order
	private ShippingOrder setExtraOrder(ShippingOrder order){

		try{
			order.setBusinessId(UserUtil.getMmrUser().getBusinessId());
			order.setCustomerId(UserUtil.getMmrUser().getCustomerId());

			order.setCustomer(customerService.getCustomerInfoByCustomerId(order.getCustomerId()));
			order.setFromAddress(order.getCustomer().getAddress());
		}catch(Exception e){

			log.error("Error occured in setting extra properties to order",e);
		}
		return order;
	}

	private PackageType getPackageType(String pkgType) {
		// TODO Auto-generated method stub
		for(PackageType pt:this.getAllPackages())
			if (pt.getType().equals(pkgType)) 
				return pt;

		return null;
	}

	//Order response retrive from Shopify Cart for Particular Shop
	public String getOrderResponse(Cart cart, String cartOrderId){

		String orderResponse="";
		StringBuffer sbOrderResponse= new StringBuffer();
		URL url;
		String finalURL="";
		String shopURL="";
		
		try {
			StringBuffer apikeyAccessToken=new StringBuffer();
			
			apikeyAccessToken.append(cart.getApiKey());
			apikeyAccessToken.append(":");
			apikeyAccessToken.append(cart.getToken()+"@");
			shopURL = cart.getUrlName();
			finalURL = shopURL.substring(0, 8) + apikeyAccessToken.toString() + shopURL.substring(8, shopURL.length());

			//Retreive Order For Specific Order Id from Shopify
			if(cartOrderId!= null && cartOrderId.length() > 0){
				String shopifySpecificOrderURLString="/orders/"+cartOrderId+".xml";
				finalURL = finalURL.concat(shopifySpecificOrderURLString);
				log.debug("Retreivng Specific Order id: "+cartOrderId+" from Shopify URL Request is:: "+finalURL);
			}
			//Retreive All Orders from Shopify
			else{
				finalURL = finalURL.concat(ShiplinxConstants.SHOPIFY_ALL_ORDERS_URL_STRING);
				log.debug("Retreivng All Orders from Shopify URL Request is:: "+finalURL);
			}
			url = new URL(finalURL);
			HttpsURLConnection con = (HttpsURLConnection)url.openConnection();
			con.setRequestProperty(ShiplinxConstants.SHOPIFY_ACCESS_TOKEN_HEADER_VALUE, cart.getToken());
			BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			while ((orderResponse = br.readLine()) != null){
				sbOrderResponse.append(orderResponse);
			}
			br.close();
			log.debug("After Reading Order Response from Shopify::"+sbOrderResponse.toString());
			
			return sbOrderResponse.toString();
			
		} catch (FileNotFoundException ex) {
			log.error("Order "+cartOrderId+" is not found for "+finalURL+" in getOrderResponse() method in CartOrderParser::",ex);
		}	catch (IOException ex) {
			log.error("Exception occured to getOrderResponse() method in CartOrderParser::",ex);
			throw new ShiplinxException(ex.getMessage());
		}
		return sbOrderResponse.toString();
	}
}
