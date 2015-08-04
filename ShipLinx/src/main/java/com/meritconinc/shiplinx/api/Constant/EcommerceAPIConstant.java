package com.meritconinc.shiplinx.api.Constant;

import java.util.Collection;

/**
 * Constants related to ECOMMERECE API
 * @author SELVA
 * 01-07-2015
 */
public class EcommerceAPIConstant {
	public static final String SHOPIFY_SCOPES="read_products, write_products,read_customers ,read_orders,   ,write_shipping,read_fulfillments, write_fulfillments";
	public static final String SHOPIFY_REDIRECTION_URL="https://test2.soluship.com/ShipLinx/ecomSignup.action";
	//String url="https://"+""+"/admin/oauth/authorize?client_id=a76d9d5646b2eb37a2fdb9e781c1ffdc&scope="+SHOPIFY_SCOPES+"&redirect_uri="+SHOPIFY_REDIRECTION_URL;
	
	// http://61.12.78.170:8080/solushipRates/rest/soluship/getRates
	// http://61.12.78.170:8080/solushipRates/rest/soluship/createShipment
    public static final String	SHOPIFY_API_KEY="a76d9d5646b2eb37a2fdb9e781c1ffdc";
    public static final String	SHOPIFY_SHARED_SCRECT="cdf05280b09cc480740a967fb265e0dc";
    public static final String	SHOPIFY_API_PASSWORD="";
	public static final String SHOPIFY_GETRATES_API_URL = "http://61.12.78.170:8080/solushipRates/rest/soluship/getRates";
	public static final String SHOPIFY_SOLUHSIP_API_NAME = "SOLUSHIP SHIPPING API";
	public static final String SHOPIFY_API_CARRIERSERVICE_FORMAT = "json";
	public static final String SHOPIFY_WEBHOOK_ORDER_CREATE_URL = "http://61.12.78.170:8080/solushipRates/rest/soluship/createShipment";
    
}
	