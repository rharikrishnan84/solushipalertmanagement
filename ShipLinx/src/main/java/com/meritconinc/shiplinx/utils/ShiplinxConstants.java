package com.meritconinc.shiplinx.utils;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.collections.KeyValue;
import org.apache.log4j.Logger;

import com.meritconinc.mmr.constants.Constants;
import com.meritconinc.mmr.utilities.MessageUtil;
import com.meritconinc.mmr.utilities.WebUtil;
import com.meritconinc.shiplinx.model.Customer;
import com.meritconinc.shiplinx.model.Service;


public class ShiplinxConstants {

	private static Logger logger = Logger.getLogger(ShiplinxConstants.class);
	
	
	private static List<Service> services;
	public static HashMap service_code = new HashMap();
	public static HashMap service_code_reverse = new HashMap();
	
	public static final long REALLY_LONG_LONG=9999999;
	
	//cookie expiration time (2 months)
	public static final int COOKIE_EXPIRED_TIME = 60*60*24*60;
	public static final int READ_TIME_OUT = 10*1000;
	
	public static final String US = "US";
	public static final String CANADA = "CA";
	public static final String PUERTORICO = "PR";
	
	public static final String NOT_APPLICABLE = "N/A";
	 
	//Canadian provinces and taxes
	public static final String PROVINCE_AB ="AB";
	public static final String PROVINCE_BC = "BC"; 
	public static final String PROVINCE_NB = "NB";
	public static final String PROVINCE_PQ = "PQ";
	public static final String PROVINCE_NF = "NF";
	public static final String PROVINCE_ON = "ON";
	public static final String PROVINCE_PE = "PE";
	public static final String PROVINCE_NS = "NS";
	public static final String TAX_HST = "HST";
	public static final String TAX_GST = "GST";
	public static final String TAX_QST = "QST";

	public static final String FUEL_SURCHARGE = "Fuel";
	public static final String OTHER_SURCHARGE = "Other";

	//package types
	public static final int PACKAGE_TYPE_ENVELOPE = 1;
	public static final int PACKAGE_TYPE_PAK = 2;
	public static final int PACKAGE_TYPE_PACKAGE = 3;
	public static final int PACKAGE_TYPE_PALLET = 4;
	public static final String PACKAGE_TYPE_ENVELOPE_STRING = "type_env";
	public static final String PACKAGE_TYPE_PAK_STRING = "type_pak";
	public static final String PACKAGE_TYPE_PACKAGE_STRING = "type_package";
	public static final String PACKAGE_TYPE_PALLET_STRING = "type_pallet";
	public static final String PACKAGE_PALLET_STRING = "pallet";
	
	//Fedex packaging Types
	public final static String YOUR_PACKAGING = "YOUR_PACKAGING" ;
	public final static String FEDEX_10KG_BOX = "FEDEX_10KG_BOX" ;
	public final static String FEDEX_25KG_BOX = "FEDEX_25KG_BOX" ; 
	public final static String FEDEX_BOX = "FEDEX_BOX" ;     
	public final static String FEDEX_ENVELOPE = "FEDEX_ENVELOPE" ; 
	public final static String FEDEX_PAK = "FEDEX_PAK";       
	public final static String FEDEX_TUBE =	"FEDEX_TUBE" ;   
	
	//Service Types
	public static final long SERVICE_TYPE_SMALL_PACKAGE = 0;
	public static final long SERVICE_TYPE_LTL_POUND = 1;
	public static final long SERVICE_TYPE_LTL_SKID = 2;
	public static final String DEFAULT_IC_SERVICE = "LTL";
	public static final long DEFAULT_IC_SERVICE_ID = 1000;



	
	//cariers
	public static final int CARRIER_FEDEX = 1;
	public static final int CARRIER_UPS = 2;
	public static final int CARRIER_DHL = 3;
	public static final int CARRIER_LOOMIS = 4;
	public static final int CARRIER_UPS_USA = 5;
	public static final int CARRIER_GENERIC = 10;
	public static final int CARRIER_PUROLATOR = 20;
	public static final int CARRIER_AESLOGISTICS = 40;
	public static final long CARRIER_ESHIPPER = 50;
	public static final long CARRIER_QUIKX = 100;
	public static final long CARRIER_POLARIS = 101;
	public static final long CARRIER_MIDLAND = 80;

	public static final String CARRIER_GENERIC_STRING = "Integrated Carriers";
	public static final String CARRIER_FEDEX_STRING = "Federal Express";
	public static final String CARRIER_UPS_STRING = "UPS";
	public static final String CARRIER_DHL_STRING = "DHL";	
	public static final String CARRIER_LOOMIS_STRING = "LOOMIS";	
	public static final String CARRIER_PUROLATOR_STRING = "Purolator";
	public static final String CARRIER_QUIKX_STRING = "QuikX";
	public static final String CARRIER_POLARIS_STRING = "Polaris";
	//user types
	public static final String USER_TYPE_ADMIN = "admin";
	public static final String USER_TYPE_CUSTOMER = "customer";
	public static final String USER_CODE = "code";
	
	//statuses
	public static final int STATUS_DISPATCHED = 10;
	public static final int STATUS_INTRANSIT = 20;
	public static final int STATUS_DELIVERED = 30;
	public static final int STATUS_CANCELLED = 40;
	public static final int STATUS_EXCEPTION = 50;
	public static final int STATUS_PRE_DISPATCHED = 60;
	public static final int STATUS_CLOSED = 70;
	public static final int STATUS_READYTOPROCESS = 80;
	public static final int STATUS_SHIPPED = 90;
	public static final int STATUS_SENT_TOWAREHOUSE = 100;
	public static final int STATUS_RECEIVED_BYWAREHOUSE = 110;
	
	public static final int STATUS_PICKUP_ACTIVE = 10;
	public static final int STATUS_PICKUP_CANCELLED = 40;
	
	public static final int COLLECT_COD_TRANS = 1;
	public static final int REMIT_COD_TRANS = 2;
	
	public static final String BEAN_TIME_ZONES = "timeZones";
	
	//dangerous goods
	public static final String DANGEROUS_GOODS_LIMITED = "Limited Quantity";
	public static final String DANGEROUS_GOODS_500KG_EXEMPTION = "500 Kg Exemption";
	public static final String DANGEROUS_GOODS_FULLY_REGULATED ="Fully Regulated";
	
	//currency types
	public static final int CURRENCY_CA = 1;
	public static final int CURRENCY_US = 2;
	public static final String CURRENCY_CA_STRING = "CAD";
	public static final String CURRENCY_US_STRING = "USD";
	
	//mode of transport
	public static final String MODE_TRANSPORT_AIR = "A";
	public static final String MODE_TRANSPORT_GROUND = "G";
	public static final int MODE_TRANSPORT_AIR_VALUE = 1;
	public static final int MODE_TRANSPORT_GROUND_VALUE = 2;
	
	public static final int LABEL_TYPE_PNG = 1;
	public static final String LABEL_TYPE_PNG_STRING = "PNG";
	public static final int LABEL_TYPE_PDF = 2;
	public static final String LABEL_TYPE_PDF_STRING = "PDF";
	
	// Edi Management
	public static final String IN_FOLDER = "in";
	public static final String OUT_FOLDER = "out";
	public static final String ERROR_FOLDER = "error";
	public static final String HEADER = "HEADER";	
	
	// Charge Status
	public static final int CHARGE_QUOTED = 0;
	public static final int CHARGE_PENDING_RELEASE = 10;
	public static final int CHARGE_READY_TO_INVOICE = 20;
	public static final int CHARGE_INVOICED = 30;
	public static final int CHARGE_CANCELLED = 40;
	public static final String [] CHARGE_STATUS_TEXT = {"Quoted", "Pending Release", "Ready to Invoice", "Invoiced", "Cancelled"};
	
	public static final String CHARGE_QUICK_INVOICE = "Quick Invoice";
	
	// Default UPS Charge Codes
	public static final String CHARGE_CODE_UPS_ACC = "ACC";
	public static final String CHARGE_CODE_LEVEL_2_UPS_OTH = "OTH";
	
	public static final String CHARGE_CODE_DHL_ACC = "ACC";
	public static final String CHARGE_CODE_LEVEL_2_DHL_OTH = "OTH";	

	public static final String CHARGE_CODE_LOOMIS_ACC = "ACC";
	public static final String CHARGE_CODE_LEVEL_2_LOOMIS_OTH = "OTH";	
	
	public static final String CHARGE_CODE_FEDEX_ACC = "ACC";
	public static final String CHARGE_CODE_LEVEL_2_FEDEX_OTH = "OTH";	
	
	public static final String CHARGE_CODE_PURO_ACC = "OTH";
	public static final String CHARGE_CODE_LEVEL_2_PURO_OTH = "OTH";	

	public static final String CHARGE_NAME_ADDITIONAL_HANDLING = "Additional Handling";
	
	private static String instanceName = null;	
	
	// Entity Types for Logging Events
	public static final int ENTITY_TYPE_ORDER_VALUE = 10;
	
	public static final int ENTITY_TYPE_PRODUCT_VALUE = 20;
	
	//
	public static final String XML_VERSION = "3.1.0";
	public static final String ESHIPPER_LIVE_SERVER_URL = "http://192.168.100.144:8080/eshipper/rpc2";
	
	//Header names
	public static final String INVOICE_NUMBER ="1_Invoice Number";
	public static final String INVOICE_TOTAL ="6_Inv Total";
	public static final String INVOICE_DATE ="2_Invoice Date";
	
	//Date
	public static final String DATE_FORMAT = "yyyyMMdd"; //YYYYMMDD
	
	public static final String HH_MM_SS_START = " 00:00:00";
	public static final String HH_MM_SS_END = " 23:59:59";
	
	//Users
	public static final String USER_VALUE_1="4 x 6";
	public static final String USER_VALUE_6="8 x 11";
	
	public static final long DEFAULT_PUROLATOR_PICKUP_SERVICE_ID = 2002;
	public static boolean isTestMode(){
		String mode = WebUtil.getProperty(Constants.SYSTEM_SCOPE, "LIVE_MODE");
		logger.debug("------------"+mode);
		
		if("LIVE".equalsIgnoreCase(mode))
			return false;
		return true;
	}

	public static String getInstanceName() {
		return instanceName;
	}	
	public static void setInstanceName(String value) {
		if(instanceName == null) {
			instanceName = value;
		}
	}	
	
	public static void setServices(List<Service> services) {		
		ShiplinxConstants.services = services;
		for(Service service:services){			
			service_code.put(service.getId(), service.getCode());
			service_code_reverse.put(service.getCode(), service.getId());
		}
	}
	//payment types
	public static final int PAYMENT_TYPE_ON_CREDIT = 1;
	public static final int PAYMENT_TYPE_CREDIT_CARD = 2;
	public static final String PAYMENT_TYPE_CC = "Credit Card";
	public static final int PAYMENT_TYPE_LEVEL_SHIPMENT	= 1;
	public static final int PAYMENT_TYPE_LEVEL_INVOICE	= 2;
	
	//pins
	public static final String PIN_TYPE_CUSTOMER_ACCOUNT_NUMBERS = "CUSTOMER_ACCOUNT_NUM";
	public static final String PIN_TYPE_INVOICE_NUMBERS = "INVOICE_NUM";
	public static final String PIN_TYPE_ORDER_NUMBERS = "ORDER_NUM";

	// for Primary key lookups
	public static final String RANDOM_KEY_MAPPING = "randomKeyMapping";
	public static final String BEAN_PROPERTY_PK = "primaryKey";

	public static final String DIM_UNITS = "in";
	public static final String WEIGHT_UNITS_LBS = "LBS";
	public static final String WEIGHT_UNITS_KGS = "KGS";
	public static final String UOM_IMPERIAL = "I";
	public static final String UOM_METRIC = "M";

	public static final int SIGNATURE_REQUIRED_NO = 1;
	public static final int SIGNATURE_REQUIRED_CONFIRMATION = 2;
	public static final int SIGNATURE_REQUIRED_SIGNATURE = 3;
	public static final int SIGNATURE_REQUIRED_ADULT_SIGNATURE = 4;
	

	public static final String Charge_BaseCharge = "Base Charge";
	public static final String Charge_Total = "Total";
	public static final String FREIGHT_STRING = "Freight";

	public static final Integer TYPE_MARKUP = 1;
	public static final Integer TYPE_MARKDOWN = 2;
	public static final String TYPE_MARKUP_TEXT = "Markup";
	public static final String TYPE_MARKDOWN_TEXT = "Markdown";


	public static final String COUNTRY_ANY = "ANY";


	public static final String GROUP_FUEL_CHARGE = "FUE";
	public static final String GROUP_FREIGHT_CHARGE = "FRT";
	
	public static final Integer BILLING_STATUS_NOT_INVOICED = 0;//-1;
	public static final Integer BILLING_STATUS_AWAITING_CONFIRMATION = 10;//0;
	public static final Integer BILLING_STATUS_CUSTOMER_CONFIRMATION = 20;//1;
	public static final Integer BILLING_STATUS_ORPHAN = 30;//2;
	public static final Integer BILLING_STATUS_READY_TO_INVOICE = 40;//3;
	public static final Integer BILLING_STATUS_INVOICED = 50;//4;
	public static final Integer BILLING_STATUS_THIRD_PARTY = 60;//4;
	public static final Integer BILLING_STATUS_COLLECT = 70;//4;
	public static final String BILLING_STATUS_AWAITING_CONFIRMATION_TEXT = "Awaiting Confirm.";
	public static final String BILLING_STATUS_CUSTOMER_CONFIRMATION_TEXT = "Customer Confirm.";
	public static final String BILLING_STATUS_ORPHAN_TEXT = "Orphan";
	public static final String BILLING_STATUS_READY_TO_INVOICE_TEXT = "Ready to Invoice";
	public static final String BILLING_STATUS_NOT_INVOICED_TEXT = "Not Invoiced";
	public static final String BILLING_STATUS_INVOICED_TEXT = "Invoiced";
	public static final String BILL_TO_THIRD_PARTY = "Third Party";
	public static final String BILL_TO_COLLECT = "Collect";
	public static final String [] BILLING_STATUS_LIST = {	BILLING_STATUS_NOT_INVOICED_TEXT, 
															BILLING_STATUS_AWAITING_CONFIRMATION_TEXT, 
															BILLING_STATUS_CUSTOMER_CONFIRMATION_TEXT,
															BILLING_STATUS_ORPHAN_TEXT, 
															BILLING_STATUS_READY_TO_INVOICE_TEXT,
															BILLING_STATUS_INVOICED_TEXT,
															BILL_TO_THIRD_PARTY, BILL_TO_COLLECT};
	
	
	// EDI Item Status 
	public static final int STATUS_UPLOADED = 1;
	public static final int STATUS_INPROGRESS = 2;
	public static final int STATUS_PROCESSED = 3;
	public static final int STATUS_RELEASED = 4;
	public static final int STATUS_FAILED = 5;
	public static final String UPLOADED = "Uploaded";
	public static final String INPROGRESS = "Inprogress";
	public static final String PROCESSED = "Processed";
	public static final String RELEASED = "Released";
	public static final String FAILED = "Failed";
	public static final String [] EDI_STATUS_MESSAGES = {	"text.message.status.upload.successful", 
															"text.message.status.process.inprogress",
															"text.message.status.process.processed",
															"text.message.status.process.released",
															"text.message.status.process.failed"
														};


	public static final String DOCUMENT_TYPE_DOC = "D";
	public static final String DOCUMENT_TYPE_OTHER = "O";


	public static final String EXPORT = "Export";
	public static final String IMPORT = "Import";


	public static final int SHIPMENT_TYPE_DOMESTIC = 0;
	public static final int SHIPMENT_TYPE_EXPORT = 1;
	public static final int SHIPMENT_TYPE_IMPORT = 2;
	public static final int SHIPMENT_TYPE_THIRD_COUNTRY = 3;
	
	//Roles
	public static final String ROLE_ADMIN = "busadmin";
	public static final String ROLE_CUSTOMER_ADMIN = "customer_admin";
	public static final String ROLE_SALES = "sales";
	public static final String ROLE_CUSTOMER_BASE = "customer_base";
	public static final String ROLE_CUSTOMER_SHIPPER = "customer_shipper";
	public static final int ROLE_TYPE_CUSTOMER = 2;
	public static final int ROLE_TYPE_BUSINESS = 1;


	public static final String ADMIN_USER = "ADMIN_USER";
	public static final String SYSTEM = "SYSTEM";


	public static final String WIP_FILE_EXT = ".WIP";


	public static final int CHARGE_TYPE_QUOTED = 0;
	public static final int CHARGE_TYPE_ACTUAL = 1;


	public static final String BUSINESS = "business";
	public static final String USERNAME = "username";
	
	public static final String LOCATION_BY_FIFO="FIFO";
	public static final String LOCATION_BY_LIFO="LIFO";
	
	public static final String SELECT_LOCATION = "-----Select Location-----";
	public static final String SELECT_WAREHOUSE = "--Select Warehouse--";
	public static final String SELECT = "--Select--";
	public static final String SELECT_ANY = "--Select ANY--";
	
	public static final String COD_CERTIFIED_CHECK = "Certified Check";
	public static final String COD_CHECK = "Check";

	public static final String BILL_TO_SHIPPER = "Shipper";
	
	//Generation of Shipping Label / Customs Invoice - Message text
	public static final String TEXT_SHIPPING_LABEL = "Shipping Label:";
	public static final String TEXT_CUSTOMS_INVOICE = "Customs Invoice:";
	public static final String TEXT_COPIES = "Copies";
	public static final String SPACE = " ";
	public static final String COMMA = ",";

	//Business related css
	public static final String CSS_EXTENSION = ".css";
	public static final String IE_CSS = "_IE";
	public static final String FF_CSS = "_FF";
	public static final String Chrome_CSS = "_Chrome";
	
	public static final String CSV_EXTENSION = ".CSV";
	
	public static String getCarrierName(Integer s) {
		if (s != null) {
			if (s.intValue()==ShiplinxConstants.CARRIER_FEDEX) 
				return ShiplinxConstants.CARRIER_FEDEX_STRING;
			if (s.intValue()==ShiplinxConstants.CARRIER_UPS || s.intValue()==ShiplinxConstants.CARRIER_UPS_USA) 
				return ShiplinxConstants.CARRIER_UPS_STRING;
			if (s.intValue()==ShiplinxConstants.CARRIER_DHL) 
				return ShiplinxConstants.CARRIER_DHL_STRING;
			if (s.intValue()==ShiplinxConstants.CARRIER_LOOMIS) 
				return ShiplinxConstants.CARRIER_LOOMIS_STRING;
			if(s.intValue()==ShiplinxConstants.CARRIER_GENERIC)
				return ShiplinxConstants.CARRIER_GENERIC_STRING;			
		}	
		return "";
	}
	
	//SHOPIFY Cart Related Constants
	
	public static final String SHOPIFY_CART_SCOPE_PARAMETER_NAME = "&scope=";
	public static final String SHOPIFY_CART_CODE_PARAMETER_NAME = "&code=";
	public static final String SHOPIFY_CART_SCOPE_PARAMETER_VALUE = "read_orders,write_orders";
	public static final String SHOPIFY_CART_SHARED_SECRET_PARAMETER_NAME="&client_secret=";
	public static final String SHOPIFY_CART_SHOP_STAGE1_URL="/oauth/authorize?client_id=";
	public static final String SHOPIFY_CART_SHOP_STAGE2_URL="/oauth/access_token";
	public static final String SHOPIFY_CART_VALIDATE_URL=".myshopify.com/admin";
	public static final String SHOPIFY_ACCESS_TOKEN_HEADER_VALUE ="X-Shopify-Access-Token";
	public static final String SHOPIFY_CART_STRING ="Shopify";
	public static final String SHOPIFY_ALL_ORDERS_URL_STRING="/orders.xml?fulfillment_status='unshipped'";
	public static final String COLON_STRING =":";
	public static final String AT_STRING ="@";
	
	//Default page set
	public static final int MENU_ID_NEW_SHIPMENT_PAGE = 221;
	public static final int MENU_ID_CUSTOMER_SEARCH = 121;
	
	//Dangerous Goods
	public static final int DG_NONE = 0;
	public static final int DG_LIMITED_QUANTITY = 1;
	public static final int DG_500KG_EXEMPTION = 2;
	public static final int DG_FULLY_REGULATED = 3;
	public static final String DG_NONE_TEXT = "None";
	
	public static final String USER_GL ="GL";
	
	public static final String LTL_EMAIL_TYPE="LTL";
	 public static final String SPS_EMAIL_TYPE="SPD";
	 public static final String CHB_EMAIL_TYPE="CHB";
}
