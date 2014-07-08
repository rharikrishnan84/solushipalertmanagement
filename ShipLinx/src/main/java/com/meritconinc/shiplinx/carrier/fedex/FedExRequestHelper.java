package com.meritconinc.shiplinx.carrier.fedex;

import org.apache.log4j.Logger;

import com.fedex.rate.stub.ClientDetail;
import com.fedex.rate.stub.WebAuthenticationCredential;
import com.fedex.rate.stub.WebAuthenticationDetail;
import com.meritconinc.shiplinx.model.CustomerCarrier;
import com.meritconinc.shiplinx.model.ShippingOrder;

public class FedExRequestHelper {

	private Logger log = Logger.getLogger(FedExRequestHelper.class);
	protected ShippingOrder order=null;
	protected CustomerCarrier customerCarrier = null;
	private String thirdPartyAccountNumber=null;
	private String thirdPartyCountryCode=null;
	private String accountNumber="";
	private String meterNumber=null;
	private String key=null; 
	private String password=null;
	public boolean flagCustomerAcct= false;
	public static final String LIVE_URL_RATE = "https://ws.fedex.com:443/web-services/rate";
	public static final String TEST_URL_RATE = "https://wsbeta.fedex.com:443/web-services/rate";
	public static final String LIVE_URL_SERVICE = "https://ws.fedex.com:443/web-services";
	public static final String LIVE_URL_PICKUP = "https://ws.fedex.com:443/web-services";
	public static final String TEST_URL_PICKUP = "https://wsbeta.fedex.com:443/web-services";
	
	public static final String SERVICE_PRIORITY_OVERNIGHT = "PRIORITY_OVERNIGHT";
	public static final String SERVICE_FIRST_OVERNIGHT = "FIRST_OVERNIGHT";
	public static final String SERVICE_FEDEX_GROUND = "FEDEX_GROUND";
	public static final String SERVICE_STANDARD_OVERNIGHT = "STANDARD_OVERNIGHT";
	public static final String SERVICE_FEDEX_2_DAY = "FEDEX_2_DAY";
	public static final String SERVICE_FEDEX_EXPRESS_SAVER = "FEDEX_EXPRESS_SAVER";
	public static final String SERVICE_INTERNATIONAL_PRIORITY_FREIGHT = "INTERNATIONAL_PRIORITY_FREIGHT";
	public static final String SERVICE_INTERNATIONAL_ECONOMY_FREIGHT = "INTERNATIONAL_ECONOMY_FREIGHT";
	public static final String SERVICE_FEDEX_2_DAY_FREIGHT = "FEDEX_2_DAY_FREIGHT";
	public static final String SERVICE_INTERNATIONAL_GROUND = "INTERNATIONAL_GROUND";
	public static final String SERVICE_EUROPE_FIRST_INTERNATIONAL_PRIORITY = "EUROPE_FIRST_INTERNATIONAL_PRIORITY";
	public static final String SERVICE_GROUND_HOME_DELIVERY = "GROUND_HOME_DELIVERY";
	public static final String SERVICE_INTERNATIONAL_ECONOMY = "INTERNATIONAL_ECONOMY";
	public static final String SERVICE_INTERNATIONAL_FIRST = "INTERNATIONAL_FIRST";
	public static final String SERVICE_FEDEX_FREIGHT = "FEDEX_FREIGHT";
	public static final String SERVICE_FEDEX_3_DAY_FREIGHT = "FEDEX_3_DAY_FREIGHT";
	public static final String SERVICE_FEDEX_1_DAY_FREIGHT = "FEDEX_1_DAY_FREIGHT";
	public static final String SERVICE_SMART_POST = "SMART_POST";
	public static final String SERVICE_FEDEX_NATIONAL_FREIGHT = "FEDEX_NATIONAL_FREIGHT";
	public static final String SERVICE_INTERNATIONAL_PRIORITY = "INTERNATIONAL_PRIORITY";
	

	public FedExRequestHelper(ShippingOrder order, CustomerCarrier customerCarrier)
	{
		this.order=order;
		this.customerCarrier=customerCarrier;
	}
	
	public FedExRequestHelper(CustomerCarrier customerCarrier)
	{
		this.customerCarrier=customerCarrier;
	}
	
	public void authenticationInfo() {
        
		accountNumber = customerCarrier.getAccountNumber1(); 
		meterNumber = customerCarrier.getProperty1(); 
		key = customerCarrier.getProperty2(); 
		password = customerCarrier.getProperty3(); 

	}
	
	public ClientDetail createClientDetail() {
        ClientDetail clientDetail = new ClientDetail();   
        clientDetail.setAccountNumber(accountNumber);
        clientDetail.setMeterNumber(meterNumber);
        return clientDetail;
	}
	
	public com.meritconinc.shiplinx.carrier.fedex.stub.cancelshipment.ClientDetail createCancelClientDetail() {
		com.meritconinc.shiplinx.carrier.fedex.stub.cancelshipment.ClientDetail clientDetail = new com.meritconinc.shiplinx.carrier.fedex.stub.cancelshipment.ClientDetail();   
        clientDetail.setAccountNumber(accountNumber);
        clientDetail.setMeterNumber(meterNumber);
        return clientDetail;
	}

	public com.fedex.ship.stub.ClientDetail createShipClientDetail() {
        com.fedex.ship.stub.ClientDetail clientDetail = new com.fedex.ship.stub.ClientDetail();   
        clientDetail.setAccountNumber(accountNumber);
        clientDetail.setMeterNumber(meterNumber);
        return clientDetail;
	}


	public com.fedex.ship.stub.WebAuthenticationDetail createShipWebAuthenticationDetail() {
        com.fedex.ship.stub.WebAuthenticationCredential wac = new com.fedex.ship.stub.WebAuthenticationCredential();
        wac.setKey(key);
        wac.setPassword(password);
		return new com.fedex.ship.stub.WebAuthenticationDetail(wac);
	}

	public com.meritconinc.shiplinx.carrier.fedex.stub.cancelshipment.WebAuthenticationDetail createCancelWebAuthenticationDetail() {
		com.meritconinc.shiplinx.carrier.fedex.stub.cancelshipment.WebAuthenticationCredential wac = new com.meritconinc.shiplinx.carrier.fedex.stub.cancelshipment.WebAuthenticationCredential();
		wac.setKey(key);
        wac.setPassword(password);
		return new com.meritconinc.shiplinx.carrier.fedex.stub.cancelshipment.WebAuthenticationDetail(wac);
	}


	
//	public com.fedex.ship.stub.ClientDetail createShipClientDetail() {
//        com.fedex.ship.stub.ClientDetail clientDetail = new com.fedex.ship.stub.ClientDetail();   
////        if(EShipperConstants.isTestMode())
////        	accountNumber=testAccountNumber;	
////        else
////        	accountNumber=prodAccountNumber;
//        clientDetail.setAccountNumber(accountNumber);
//        clientDetail.setMeterNumber(meterNumber);
//        return clientDetail;
//	}
//	
//	public com.fedex.track.stub.ClientDetail createTrackClientDetail() {
//        com.fedex.track.stub.ClientDetail clientDetail = new com.fedex.track.stub.ClientDetail();
////        if(EShipperConstants.isTestMode())
////        	accountNumber=testAccountNumber;	
////        else
////        	accountNumber=prodAccountNumber;
//        clientDetail.setAccountNumber(accountNumber);
//        clientDetail.setMeterNumber(meterNumber);
//        return clientDetail;
//	}
	
	public WebAuthenticationDetail createWebAuthenticationDetail() {
        WebAuthenticationCredential wac = new WebAuthenticationCredential();
        wac.setKey(key);
        wac.setPassword(password);
		return new WebAuthenticationDetail(wac);
	}
	
	//Fedex-pickup WebAuthenticationDetail creation
	public com.fedex.ws.pickup.v3.WebAuthenticationDetail createPickupkWebAuthenticationDetail() {
		com.fedex.ws.pickup.v3.WebAuthenticationCredential wac = new com.fedex.ws.pickup.v3.WebAuthenticationCredential();
        wac.setKey(key);
        wac.setPassword(password);
		return new com.fedex.ws.pickup.v3.WebAuthenticationDetail(wac);
	}
	
	//Fedex-Pickup Client details creation method
	 public com.fedex.ws.pickup.v3.ClientDetail createPickupClientDetail() {
		 com.fedex.ws.pickup.v3.ClientDetail clientDetail = new com.fedex.ws.pickup.v3.ClientDetail();
        clientDetail.setAccountNumber(accountNumber);
        clientDetail.setMeterNumber(meterNumber);
        return clientDetail;
	}
	
//	public com.fedex.ship.stub.WebAuthenticationDetail createShipWebAuthenticationDetail() {
//        com.fedex.ship.stub.WebAuthenticationCredential wac = new com.fedex.ship.stub.WebAuthenticationCredential();
//        wac.setKey(key);
//        wac.setPassword(pass);
//		return new com.fedex.ship.stub.WebAuthenticationDetail(wac);
//	}
//	
//	public com.fedex.track.stub.WebAuthenticationDetail createTrackWebAuthenticationDetail() {
//        com.fedex.track.stub.WebAuthenticationCredential wac = new com.fedex.track.stub.WebAuthenticationCredential();
//        wac.setKey(key);
//        wac.setPassword(pass);
//		return new com.fedex.track.stub.WebAuthenticationDetail(wac);
//	}


	/**
	 * @return the thirdPartyAccountNumber
	 */
	public String getThirdPartyAccountNumber() {
		return thirdPartyAccountNumber;
	}

	/**
	 * @param thirdPartyAccountNumber the thirdPartyAccountNumber to set
	 */
	public void setThirdPartyAccountNumber(String thirdPartyAccountNumber) {
		this.thirdPartyAccountNumber = thirdPartyAccountNumber;
	}

	/**
	 * @return the thirdPartyCountryCode
	 */
	public String getThirdPartyCountryCode() {
		return thirdPartyCountryCode;
	}

	/**
	 * @param thirdPartyCountryCode the thirdPartyCountryCode to set
	 */
	public void setThirdPartyCountryCode(String thirdPartyCountryCode) {
		this.thirdPartyCountryCode = thirdPartyCountryCode;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
		
	
}
