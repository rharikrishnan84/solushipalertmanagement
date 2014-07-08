package com.meritconinc.shiplinx.carrier.ups.stub;

import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.log4j.Logger;

import com.meritconinc.shiplinx.carrier.ups.service.UPSAPI;
import com.meritconinc.shiplinx.carrier.ups.ws.voidws.proxy.ErrorDetailType;
import com.meritconinc.shiplinx.carrier.ups.ws.voidws.proxy.Errors;
import com.meritconinc.shiplinx.carrier.ups.ws.voidws.proxy.RequestType;
import com.meritconinc.shiplinx.carrier.ups.ws.voidws.proxy.TransactionReferenceType;
import com.meritconinc.shiplinx.carrier.ups.ws.voidws.proxy.UPSSecurity;
import com.meritconinc.shiplinx.carrier.ups.ws.voidws.proxy.VoidErrorMessage;
import com.meritconinc.shiplinx.carrier.ups.ws.voidws.proxy.VoidPortType;
import com.meritconinc.shiplinx.carrier.ups.ws.voidws.proxy.VoidShipmentRequest;
import com.meritconinc.shiplinx.carrier.ups.ws.voidws.proxy.VoidShipmentResponse;
import com.meritconinc.shiplinx.carrier.ups.ws.voidws.proxy.UPSSecurity.ServiceAccessToken;
import com.meritconinc.shiplinx.carrier.ups.ws.voidws.proxy.UPSSecurity.UsernameToken;
import com.meritconinc.shiplinx.carrier.ups.ws.voidws.proxy.VoidShipmentRequest.VoidShipment;
import com.meritconinc.shiplinx.carrier.utils.UPSException;
import com.meritconinc.shiplinx.model.CustomerCarrier;
import com.meritconinc.shiplinx.model.ShippingOrder;
import com.meritconinc.shiplinx.utils.ShiplinxConstants;

public class VoidRequestWebServiceClient{  
	private static Logger logger = Logger.getLogger(VoidRequestWebServiceClient.class);
	VoidShipmentRequest request = new VoidShipmentRequest();
	private ShippingOrder order=null;
	private CustomerCarrier customerCarrier = null;


	public VoidRequestWebServiceClient(ShippingOrder order, CustomerCarrier customerCarrier)
	{
		this.order = order;
		this.customerCarrier = customerCarrier;
		
	} 
	
	public VoidShipmentRequest buildRequest() {   
	    
        setOrder(); 
        
        return request;
       // return sendRequest();
        
        
	}
	

	
	public VoidShipmentResponse sendRequest() throws UPSException{

		VoidShipmentResponse response = null;
		try {
			JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();

			factory.getInInterceptors().add(new LoggingInInterceptor());
			factory.getOutInterceptors().add(new LoggingOutInterceptor());
			factory.setServiceClass(VoidPortType.class);
			
			if(ShiplinxConstants.isTestMode())
				factory.setAddress(UPSAPI.TEST_URL_WS_VOID);		// Test
			else
				factory.setAddress(UPSAPI.LIVE_URL_WS_VOID);		// Test

			// SSL Setup
			VoidPortType client = (VoidPortType) factory.create();
			
			response = client.processVoid(request, getUpsSecurity());

		} 
		catch(VoidErrorMessage sem){
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
			
			RequestType type = new RequestType(); 
			TransactionReferenceType tran_ref = new TransactionReferenceType();
			tran_ref.setTransactionIdentifier(order.getBusiness().getShortCode());
			type.setTransactionReference(tran_ref);
			request.setRequest(type);
			
			request.setVoidShipment(new VoidShipment());
			request.getVoidShipment().setShipmentIdentificationNumber(order.getMasterTrackingNum());

		}catch(Exception e){
			e.printStackTrace();
			logger.info(e.getMessage());
		}
	}

}
