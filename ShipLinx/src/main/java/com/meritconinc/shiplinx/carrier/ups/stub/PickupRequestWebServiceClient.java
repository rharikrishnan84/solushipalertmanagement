package com.meritconinc.shiplinx.carrier.ups.stub;

import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.log4j.Logger;

import com.meritconinc.mmr.utilities.StringUtil;
import com.meritconinc.shiplinx.carrier.ups.service.UPSAPI;
import com.meritconinc.shiplinx.carrier.ups.ws.pickup.proxy.AccountType;
import com.meritconinc.shiplinx.carrier.ups.ws.pickup.proxy.ErrorDetailType;
import com.meritconinc.shiplinx.carrier.ups.ws.pickup.proxy.Errors;
import com.meritconinc.shiplinx.carrier.ups.ws.pickup.proxy.NotificationType;
import com.meritconinc.shiplinx.carrier.ups.ws.pickup.proxy.PhoneType;
import com.meritconinc.shiplinx.carrier.ups.ws.pickup.proxy.PickupAddressType;
import com.meritconinc.shiplinx.carrier.ups.ws.pickup.proxy.PickupCancelErrorMessage;
import com.meritconinc.shiplinx.carrier.ups.ws.pickup.proxy.PickupCancelRequest;
import com.meritconinc.shiplinx.carrier.ups.ws.pickup.proxy.PickupCancelResponse;
import com.meritconinc.shiplinx.carrier.ups.ws.pickup.proxy.PickupCreationErrorMessage;
import com.meritconinc.shiplinx.carrier.ups.ws.pickup.proxy.PickupCreationRequest;
import com.meritconinc.shiplinx.carrier.ups.ws.pickup.proxy.PickupCreationResponse;
import com.meritconinc.shiplinx.carrier.ups.ws.pickup.proxy.PickupDateInfoType;
import com.meritconinc.shiplinx.carrier.ups.ws.pickup.proxy.PickupPieceType;
import com.meritconinc.shiplinx.carrier.ups.ws.pickup.proxy.PickupPortType;
import com.meritconinc.shiplinx.carrier.ups.ws.pickup.proxy.RequestType;
import com.meritconinc.shiplinx.carrier.ups.ws.pickup.proxy.ShipperType;
import com.meritconinc.shiplinx.carrier.ups.ws.pickup.proxy.TransactionReferenceType;
import com.meritconinc.shiplinx.carrier.ups.ws.pickup.proxy.UPSSecurity;
import com.meritconinc.shiplinx.carrier.ups.ws.pickup.proxy.WeightType;
import com.meritconinc.shiplinx.carrier.ups.ws.pickup.proxy.UPSSecurity.ServiceAccessToken;
import com.meritconinc.shiplinx.carrier.ups.ws.pickup.proxy.UPSSecurity.UsernameToken;
import com.meritconinc.shiplinx.carrier.utils.UPSException;
import com.meritconinc.shiplinx.model.Pickup;
import com.meritconinc.shiplinx.utils.FormattingUtil;
import com.meritconinc.shiplinx.utils.ShiplinxConstants;


public class PickupRequestWebServiceClient{  
	private static Logger logger = Logger.getLogger(PickupRequestWebServiceClient.class);
	PickupCreationRequest request = new PickupCreationRequest();
	PickupCancelRequest cancelRequest = new PickupCancelRequest();
	private Pickup pickup=null;


	public PickupRequestWebServiceClient(Pickup pickup)
	{
		this.pickup = pickup;
	}
	 
	public PickupCreationRequest buildRequest() {   
	    
        setPickup();         
        return request;
       // return sendRequest();
        
        
	}
	
	public PickupCancelRequest buildCancelRequest() {   
	    
        setCancelPickup();         
        return cancelRequest;
       // return sendRequest();
        
        
	}

	
	public PickupCreationResponse sendRequest() throws UPSException{

		PickupCreationResponse response = null;
		try {
			JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();

			factory.getInInterceptors().add(new LoggingInInterceptor());
			factory.getOutInterceptors().add(new LoggingOutInterceptor());
			factory.setServiceClass(PickupPortType.class);
			
			if(ShiplinxConstants.isTestMode())
				factory.setAddress(UPSAPI.TEST_URL_WS_PICKUP);		// Test
			else
				factory.setAddress(UPSAPI.LIVE_URL_WS_PICKUP);		// Live

			// SSL Setup
			PickupPortType client = (PickupPortType) factory.create();
			
			response = client.processPickupCreation(request, getUpsSecurity());

		} 
		catch(PickupCreationErrorMessage sem){
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
	
	public PickupCancelResponse sendCancelRequest() throws UPSException{

		PickupCancelResponse response = null;
		try {
			JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();

			factory.getInInterceptors().add(new LoggingInInterceptor());
			factory.getOutInterceptors().add(new LoggingOutInterceptor());
			factory.setServiceClass(PickupPortType.class);
			
			if(ShiplinxConstants.isTestMode())
				factory.setAddress(UPSAPI.TEST_URL_WS_PICKUP);		// Test
			else
				factory.setAddress(UPSAPI.LIVE_URL_WS_PICKUP);		// Live

			// SSL Setup
			PickupPortType client = (PickupPortType) factory.create();
			
			response = client.processPickupCancel(cancelRequest, getUpsSecurity());

		} 
		catch(PickupCancelErrorMessage sem){
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
		upsSecurity.getServiceAccessToken().setAccessLicenseNumber(pickup.getCarrierAccount().getProperty1());
		upsSecurity.setUsernameToken(new UsernameToken());
		upsSecurity.getUsernameToken().setUsername(pickup.getCarrierAccount().getProperty2());
		upsSecurity.getUsernameToken().setPassword(pickup.getCarrierAccount().getProperty3());
		return upsSecurity;
	}

	public void setPickup() {
		
		RequestType type = new RequestType(); 
		TransactionReferenceType tran_ref = new TransactionReferenceType();
		tran_ref.setTransactionIdentifier(pickup.getPickupReference());
		type.setTransactionReference(tran_ref);
		request.setRequest(type);
		
		request.setRatePickupIndicator("Y");

		//set the shipper address information
		request.setShipper(new ShipperType());
		request.getShipper().setAccount(new AccountType());
		request.getShipper().getAccount().setAccountCountryCode(pickup.getCarrierAccount().getCountry());
		request.getShipper().getAccount().setAccountNumber(pickup.getCarrierAccount().getAccountNumber1());
		String accountNum = pickup.getCarrierAccount().getAccountNumber1();
		if(accountNum!=null && accountNum.length()>6){ //the EDI file is sending back the account # padded with zeros on the left. WE are storing the padded zeros in our customerCarrier record, so need to remove them before sending for rating/shipping
			int length = accountNum.length();				
			request.getShipper().getAccount().setAccountNumber(accountNum.substring(length-6, length));
		}
		else
			request.getShipper().getAccount().setAccountNumber(accountNum);    
		
		//set the pick up date info
		request.setPickupDateInfo(new PickupDateInfoType());
		request.getPickupDateInfo().setCloseTime(pickup.getCloseHour() + pickup.getCloseMin());
		request.getPickupDateInfo().setReadyTime(pickup.getReadyHour() + pickup.getReadyMin());
		request.getPickupDateInfo().setPickupDate(FormattingUtil.getFormattedDate(pickup.getPickupDate(), FormattingUtil.DATE_FORMAT_yyyyMMDD));
		
		//set the pick up address information
		request.setPickupAddress(new PickupAddressType());
		request.getPickupAddress().setCompanyName(StringUtil.setWithMaxLength(pickup.getAddress().getAbbreviationName(),27));
		request.getPickupAddress().setContactName(StringUtil.setWithMaxLength(pickup.getAddress().getContactName(),22));
		StringBuilder address = new StringBuilder(pickup.getAddress().getAddress1());
		
		if(pickup.getAddress().getAddress2()!=null && pickup.getAddress().getAddress2().length()>0){
			address.append(", ");
			address.append(pickup.getAddress().getAddress2());
		}
		request.getPickupAddress().getAddressLine().add(0,address.toString());

		request.getPickupAddress().setCity(pickup.getAddress().getCity());
		request.getPickupAddress().setStateProvince(pickup.getAddress().getProvinceCode());
		if(request.getPickupAddress().getStateProvince().equalsIgnoreCase("PQ"))
			request.getPickupAddress().setStateProvince("QC");
		request.getPickupAddress().setPostalCode(pickup.getAddress().getPostalCode());
		request.getPickupAddress().setCountryCode(pickup.getAddress().getCountryCode());
		if(pickup.getAddress().isResidential())
			request.getPickupAddress().setResidentialIndicator("Y");
		else
			request.getPickupAddress().setResidentialIndicator("N");
		request.getPickupAddress().setPickupPoint(pickup.getPickupLocation());
		request.getPickupAddress().setPhone(new PhoneType());
		request.getPickupAddress().getPhone().setNumber(pickup.getAddress().getPhoneNo());
		request.getPickupAddress().getPhone().setExtension(pickup.getAddress().getPhoneExt());
		//end pickup address
		
		request.setAlternateAddressIndicator("Y");
		
		//set the piece information
		PickupPieceType p = new PickupPieceType();
		p.setServiceCode(FormattingUtil.padWithLeading(pickup.getServiceCode(), 3, "0"));
		p.setQuantity(String.valueOf(pickup.getQuantity()));
		p.setDestinationCountryCode(pickup.getDestinationCountryCode());
		if(pickup.getPackageTypeId()==ShiplinxConstants.PACKAGE_TYPE_ENVELOPE || pickup.getPackageTypeId()==ShiplinxConstants.PACKAGE_TYPE_PAK)
			p.setContainerCode("02");
		else
			p.setContainerCode("01");
		
		request.getPickupPiece().add(p);
		
		request.setTotalWeight(new WeightType());
		request.getTotalWeight().setWeight(String.valueOf(FormattingUtil.roundFigureRates(pickup.getTotalWeight(),0)));
		request.getTotalWeight().setUnitOfMeasurement(ShiplinxConstants.WEIGHT_UNITS_LBS);
		
		//overweight indicator
		if(pickup.isOverweight() || pickup.getOversizeQuantity() > 0)
			request.setOverweightIndicator("Y");
		else		
			request.setOverweightIndicator("N");
		
		//set payment method
		request.setPaymentMethod("01");
		
		//pick up instructions and reference
		request.setSpecialInstruction(pickup.getInstructions());
		request.setReferenceNumber(pickup.getPickupReference());
		
		//email notification
		request.setNotification(new NotificationType());
		request.getNotification().getConfirmationEmailAddress().add(pickup.getAddress().getEmailAddress());
		request.getNotification().setUndeliverableEmailAddress(pickup.getAddress().getEmailAddress());
			
	}

	public void setCancelPickup() {
		
		try{
			
			RequestType type = new RequestType(); 
			TransactionReferenceType tran_ref = new TransactionReferenceType();
			tran_ref.setTransactionIdentifier(pickup.getPickupReference());
			type.setTransactionReference(tran_ref);
			cancelRequest.setRequest(type);
			
			cancelRequest.setCancelBy("02");
			cancelRequest.setPRN(pickup.getConfirmationNum());
			
		}catch(Exception e){
			logger.error(e.getMessage(),e);
		}
	}


}
