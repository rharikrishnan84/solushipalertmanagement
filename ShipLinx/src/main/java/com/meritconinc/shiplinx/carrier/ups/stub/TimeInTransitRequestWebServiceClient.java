package com.meritconinc.shiplinx.carrier.ups.stub;

import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.log4j.Logger;

import com.meritconinc.shiplinx.carrier.ups.service.UPSAPI;
import com.meritconinc.shiplinx.carrier.ups.ws.tnt.proxy.ErrorDetailType;
import com.meritconinc.shiplinx.carrier.ups.ws.tnt.proxy.Errors;
import com.meritconinc.shiplinx.carrier.ups.ws.tnt.proxy.InvoiceLineTotalType;
import com.meritconinc.shiplinx.carrier.ups.ws.tnt.proxy.PickupType;
import com.meritconinc.shiplinx.carrier.ups.ws.tnt.proxy.RequestShipFromAddressType;
import com.meritconinc.shiplinx.carrier.ups.ws.tnt.proxy.RequestShipFromType;
import com.meritconinc.shiplinx.carrier.ups.ws.tnt.proxy.RequestShipToAddressType;
import com.meritconinc.shiplinx.carrier.ups.ws.tnt.proxy.RequestShipToType;
import com.meritconinc.shiplinx.carrier.ups.ws.tnt.proxy.RequestType;
import com.meritconinc.shiplinx.carrier.ups.ws.tnt.proxy.ShipmentWeightType;
import com.meritconinc.shiplinx.carrier.ups.ws.tnt.proxy.TNTCodeDescriptionType;
import com.meritconinc.shiplinx.carrier.ups.ws.tnt.proxy.TimeInTransitErrorMessage;
import com.meritconinc.shiplinx.carrier.ups.ws.tnt.proxy.TimeInTransitPortType;
import com.meritconinc.shiplinx.carrier.ups.ws.tnt.proxy.TimeInTransitRequest;
import com.meritconinc.shiplinx.carrier.ups.ws.tnt.proxy.TimeInTransitResponse;
import com.meritconinc.shiplinx.carrier.ups.ws.tnt.proxy.TransactionReferenceType;
import com.meritconinc.shiplinx.carrier.ups.ws.tnt.proxy.UPSSecurity;
import com.meritconinc.shiplinx.carrier.ups.ws.tnt.proxy.UPSSecurity.ServiceAccessToken;
import com.meritconinc.shiplinx.carrier.ups.ws.tnt.proxy.UPSSecurity.UsernameToken;
import com.meritconinc.shiplinx.carrier.utils.UPSException;
import com.meritconinc.shiplinx.model.CustomerCarrier;
import com.meritconinc.shiplinx.model.ShippingOrder;
import com.meritconinc.shiplinx.utils.FormattingUtil;
import com.meritconinc.shiplinx.utils.ShiplinxConstants;


public class TimeInTransitRequestWebServiceClient{  
	private static Logger logger = Logger.getLogger(TimeInTransitRequestWebServiceClient.class);
	TimeInTransitRequest request = new TimeInTransitRequest();
	private ShippingOrder order=null;
	private CustomerCarrier customerCarrier=null;
	String currency=null;
	
	

	/**
	 * @return the currency
	 */
	public String getCurrency() {
		return currency;
	} 

	/**
	 * @param currency the currency to set
	 */
	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public TimeInTransitRequestWebServiceClient(ShippingOrder order, CustomerCarrier customerCarrier)
	{
		this.order = order;
		this.customerCarrier = customerCarrier;
		
	}
	
	public TimeInTransitRequest buildRequest() {   
	    
        setOrder(); 
        
        return request;
       // return sendRequest();
        
        
	}
	
	public TimeInTransitResponse sendRequest() throws UPSException{

		TimeInTransitResponse response = null;
		try {
			JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();

			factory.getInInterceptors().add(new LoggingInInterceptor());
			factory.getOutInterceptors().add(new LoggingOutInterceptor());
			factory.setServiceClass(TimeInTransitPortType.class);
			
			if(ShiplinxConstants.isTestMode())
				factory.setAddress(UPSAPI.TEST_URL_WS_TNT);		// Test
			else
				factory.setAddress(UPSAPI.LIVE_URL_WS_TNT);		// Test

			// SSL Setup
			TimeInTransitPortType client = (TimeInTransitPortType) factory.create();
			
			response = client.processTimeInTransit(request, getUpsSecurity());

		} 
		catch(TimeInTransitErrorMessage sem){
			StringBuilder stb = new StringBuilder();
			Errors errors = sem.getFaultInfo(); 
			for(ErrorDetailType edt: errors.getErrorDetail()){
				stb.append(edt.getPrimaryErrorCode().getCode() + ": ");
				stb.append(edt.getPrimaryErrorCode().getDescription() + ". ");
			}
						
			throw new UPSException(stb.toString());
		}
		catch (Exception e) {
			throw new UPSException(e.getMessage());
		}

		return response;
	}	
	
	
	private UPSSecurity getUpsSecurity() {
		// TODO Auto-generated method stub
		UPSSecurity upsSecurity = new UPSSecurity();
		upsSecurity.setServiceAccessToken(new ServiceAccessToken());
		upsSecurity.getServiceAccessToken().setAccessLicenseNumber(customerCarrier.getProperty1());
		upsSecurity.setUsernameToken(new UsernameToken());
		upsSecurity.getUsernameToken().setUsername(customerCarrier.getProperty2());
		upsSecurity.getUsernameToken().setPassword(customerCarrier.getProperty3());
		return upsSecurity;
	}

	public void setOrder() {
		
		try{
			
			com.meritconinc.shiplinx.model.Address from = order.getFromAddress();
			com.meritconinc.shiplinx.model.Address to = order.getToAddress();

			RequestType type = new RequestType(); 
			type.getRequestOption().add("validate");
			TransactionReferenceType tran_ref = new TransactionReferenceType();
			tran_ref.setTransactionIdentifier(order.getBusiness().getShortCode());
			type.setTransactionReference(tran_ref);
			request.setRequest(type);
			
			request.setShipFrom(new RequestShipFromType());
			request.getShipFrom().setAddress(new RequestShipFromAddressType());
			request.getShipFrom().getAddress().setCity(from.getCity());
			request.getShipFrom().getAddress().setPostalCode(from.getPostalCode());
			request.getShipFrom().getAddress().setStateProvinceCode(from.getProvinceCode());
			request.getShipFrom().getAddress().setCountryCode(from.getCountryCode());

			request.setShipTo(new RequestShipToType());
			request.getShipTo().setAddress(new RequestShipToAddressType());
			request.getShipTo().getAddress().setCity(to.getCity());
			request.getShipTo().getAddress().setPostalCode(to.getPostalCode());
			request.getShipTo().getAddress().setStateProvinceCode(to.getProvinceCode());
			request.getShipTo().getAddress().setCountryCode(to.getCountryCode());

			request.setPickup(new PickupType());
			request.getPickup().setDate(FormattingUtil.getFormattedDate(order.getScheduledShipDate(), FormattingUtil.DATE_FORMAT_yyyyMMDD));
        
			request.setShipmentWeight(new ShipmentWeightType());
			request.getShipmentWeight().setUnitOfMeasurement(new TNTCodeDescriptionType());
			
			if(order.getDimType()==ShippingOrder.DIM_TYPE_US)
				request.getShipmentWeight().getUnitOfMeasurement().setCode(UPSAPI.UNIT_LBS_STRING);
			else
				request.getShipmentWeight().getUnitOfMeasurement().setCode(UPSAPI.UNIT_KGS_STRING);
			
			request.getShipmentWeight().getUnitOfMeasurement().setDescription("");
			request.getShipmentWeight().setWeight(String.valueOf(order.getTotalWeight()));
			request.setTotalPackagesInShipment(String.valueOf(order.getQuantity()));
		       
	        if(!from.getCountryCode().equals(ShiplinxConstants.US) || !to.getCountryCode().equals(ShiplinxConstants.US)){
	        	request.setInvoiceLineTotal(new InvoiceLineTotalType());
	        	if(from.getCountryCode().equals(ShiplinxConstants.CANADA)) {
	        		request.getInvoiceLineTotal().setCurrencyCode(ShiplinxConstants.CURRENCY_CA_STRING);
	        	} else {
	        		request.getInvoiceLineTotal().setCurrencyCode(ShiplinxConstants.CURRENCY_CA_STRING);
	        	}	
	        	request.getInvoiceLineTotal().setMonetaryValue("9999.99");
	        	
	        }
			
			
		}catch(Exception e){
			e.printStackTrace();
			logger.info(e.getMessage());
		}
	}

}
