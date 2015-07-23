package com.meritconinc.shiplinx.carrier.fedex;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import org.apache.axis.types.NonNegativeInteger;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.fedex.rate.stub.Address;
import com.fedex.rate.stub.CodCollectionType;
import com.fedex.rate.stub.CodDetail;
import com.fedex.rate.stub.ContactAndAddress;
import com.fedex.rate.stub.CustomerReference;
import com.fedex.rate.stub.DangerousGoodsAccessibilityType;
import com.fedex.rate.stub.DangerousGoodsDetail;
import com.fedex.rate.stub.Dimensions;
import com.fedex.rate.stub.DropoffType;
import com.fedex.rate.stub.HazardousCommodityContent;
import com.fedex.rate.stub.HazardousCommodityOptionType;
import com.fedex.rate.stub.HoldAtLocationDetail;
import com.fedex.rate.stub.LinearUnits;
import com.fedex.rate.stub.Money;
import com.fedex.rate.stub.Notification;
import com.fedex.rate.stub.NotificationSeverityType;
import com.fedex.rate.stub.PackageSpecialServiceType;
import com.fedex.rate.stub.PackageSpecialServicesRequested;
import com.fedex.rate.stub.PackagingType;
import com.fedex.rate.stub.Party;
import com.fedex.rate.stub.Payment;
import com.fedex.rate.stub.PaymentType;
import com.fedex.rate.stub.RatePortType;
import com.fedex.rate.stub.RateReply;
import com.fedex.rate.stub.RateReplyDetail;
import com.fedex.rate.stub.RateRequest;
import com.fedex.rate.stub.RateRequestType;
import com.fedex.rate.stub.RateServiceLocator;
import com.fedex.rate.stub.RatedShipmentDetail;
import com.fedex.rate.stub.RequestedPackageDetailType;
import com.fedex.rate.stub.RequestedPackageLineItem;
import com.fedex.rate.stub.RequestedShipment;
import com.fedex.rate.stub.ShipmentRateDetail;
import com.fedex.rate.stub.ShipmentSpecialServiceType;
import com.fedex.rate.stub.ShipmentSpecialServicesRequested;
import com.fedex.rate.stub.SignatureOptionDetail;
import com.fedex.rate.stub.SignatureOptionType;
import com.fedex.rate.stub.SpecialRatingAppliedType;
import com.fedex.rate.stub.Surcharge;
import com.fedex.rate.stub.TransactionDetail;
import com.fedex.rate.stub.TransitTimeType;
import com.fedex.rate.stub.VersionId;
import com.fedex.rate.stub.Weight;
import com.fedex.rate.stub.WeightUnits;
import com.meritconinc.mmr.utilities.Common;
import com.meritconinc.shiplinx.carrier.utils.FedExException;
import com.meritconinc.shiplinx.model.CustomerCarrier;
import com.meritconinc.shiplinx.model.Package;
import com.meritconinc.shiplinx.model.Rating;
import com.meritconinc.shiplinx.model.Service;
import com.meritconinc.shiplinx.model.ShippingOrder;
import com.meritconinc.shiplinx.utils.FormattingUtil;
import com.meritconinc.shiplinx.utils.ShiplinxConstants;


/** 
 * Sample code to call Rate Available Web Service with Axis 
 * <p>
 * com.fedex.rate.stub is generated via WSDL2Java, like this:<br>
 * <pre>
 * java org.apache.axis.wsdl.WSDL2Java -w -p com.fedex.rate.stub http://www.fedex.com/...../RateService?wsdl
 * </pre>
 * 
 * This sample code has been tested with JDK 5 and Apache Axis 1.4
 */
public class RateAvailableWebServiceClient extends FedExRequestHelper{ 
 
	private List<Rating> ratingList;
	private static final Logger log = LogManager.getLogger(RateAvailableWebServiceClient.class);
	private HashMap<String, Long> fedexServiceMap = new HashMap<String, Long>();
	RateRequest request = new RateRequest();
	private PackageSpecialServicesRequested pssr= new PackageSpecialServicesRequested();


	public RateAvailableWebServiceClient(ShippingOrder order, List<Service> services, CustomerCarrier customerCarrier)
	{
		super(order, customerCarrier);
		for(Service s: services){
			fedexServiceMap.put(s.getCode(),s.getId()); 
		}
	}
	
	//
	public RateReply buildRateRequest() {   
		authenticationInfo();
		request.setClientDetail(createClientDetail());
        request.setWebAuthenticationDetail(createWebAuthenticationDetail());
        if(request!=null){
       	 if(request.getClientDetail()!=null){
       		 if(request.getClientDetail().getAccountNumber()==null || request.getClientDetail().getAccountNumber().isEmpty()){
       			 return null;
       		 }else if(request.getClientDetail().getMeterNumber()==null || request.getClientDetail().getMeterNumber().isEmpty()){
       			 return null;
       		 }
       	 }else{
       		 return null;
       	 }
        }
        /* if(request!=null){
        	 if(request.getClientDetail()!=null){
        		 if(request.getClientDetail().getAccountNumber()==null || request.getClientDetail().getAccountNumber().isEmpty()){
        			 return null;
        		 }else if(request.getClientDetail().getMeterNumber()==null || request.getClientDetail().getMeterNumber().isEmpty()){
        			 return null;
        		 }
        	 }else{
        		 return null;
        	 }
        	 if(request.getWebAuthenticationDetail()!=null){
        		 if(request.getWebAuthenticationDetail().getUserCredential()!=null && request.getWebAuthenticationDetail().getUserCredential().getKey()==null || request.getWebAuthenticationDetail().getUserCredential().getKey().isEmpty()){
        			 return null;
        		 }else if(request.getWebAuthenticationDetail().getUserCredential()!=null && request.getWebAuthenticationDetail().getUserCredential().getPassword()==null || request.getWebAuthenticationDetail().getUserCredential().getPassword().isEmpty()){
        			 return null;
        		 }else if(request.getWebAuthenticationDetail().getUserCredential()==null){
        			 return null;
        		 }
        	 }else{
        		 return null;
        	 }
         }*/
        // set transactionDetail
	    TransactionDetail transactionDetail = new TransactionDetail();
	    transactionDetail.setCustomerTransactionId("java sample - Rate Request"); // The client will get the same value back in the response
	    request.setTransactionDetail(transactionDetail);

        // set versionId
        VersionId versionId = new VersionId("crs", 9, 0, 0);
        request.setVersion(versionId);
        request.setReturnTransitAndCommit(true);
        //set order details
        setOrder();       
        return sendRequest();
	}

	public RateReply sendRequest() {  
		log.info("In sendRequest, accout/meter/key/password: " + request.getClientDetail().getAccountNumber() + " / "  + request.getClientDetail().getMeterNumber() + " / " + request.getWebAuthenticationDetail().getUserCredential().getKey() + " / " + request.getWebAuthenticationDetail().getUserCredential().getPassword());
		RateReply reply=null;
		try {
			// Initialize the service
			RateServiceLocator service;
			RatePortType port;
			service = new RateServiceLocator();
			updateEndPoint(service);
			port = service.getRateServicePort();
			log.debug("request:::"+request.getWebAuthenticationDetail().getUserCredential().getKey());
			log.debug("REQUEST:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::/n"+ Common.getXMLOfObject(request));
			log.debug("/nREQUEST:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::/n");
			// This is the call to the web service passing in a RateRequest and returning a RateReply
			reply = port.getRates(request); // Service call
			log.debug("RESPONSE:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::/n"+ Common.getXMLOfObject(reply));
			log.debug("/nRESPONSE:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::/n");
			if (!isResponseOk(reply.getHighestSeverity())) // check if the call was successful
			{
				StringBuffer stb = new StringBuffer("");
				for(com.fedex.rate.stub.Notification n: reply.getNotifications()){
					log.error("FedEx errors: " + n.getMessage());
					stb.append(" + " + n.getMessage());
				}
				throw new FedExException(stb.toString());
			}
			log.debug(reply);
			determineCharges(reply);
		} catch (Exception e) {
		    e.printStackTrace();
		    log.info("error:"+e.getMessage());
		}
		return reply;
		
	}
	
	private void determineCharges(RateReply reply){
		
		ratingList = new ArrayList<Rating>();
		RateReplyDetail[]  rrds=reply.getRateReplyDetails();
			if(rrds!=null && rrds.length>0)
			{
				for(int i=0;i<rrds.length;i++)
				{ 
					RateReplyDetail rrd=rrds[i];
					
					//ground transit time
					TransitTimeType transit = rrd.getTransitTime();
					String timeInTransit = null;
					
					if(transit!=null)
						timeInTransit = transit.getValue();
					
					//Express
					else if(transit==null && rrd.getDeliveryTimestamp()!=null){
						//order.getScheduledShipDate()
						long timeDiff = (rrd.getDeliveryTimestamp().getTime().getTime() - order.getScheduledShipDate().getTime())/86400000; //dividing by num of milliseconds per day
						
						long transitDays = new Long(timeDiff).longValue();
						Calendar cal = Calendar.getInstance();
						cal.setTime(order.getScheduledShipDate());
						//if the scheduled ship date is Fri and this is Express, then we need to deduct 2 from transit days to get # of business days

						if(cal.get(java.util.Calendar.DAY_OF_WEEK) == Calendar.FRIDAY && transitDays>2){
							transitDays -= 2;
						}
						timeInTransit = String.valueOf(transitDays);
					}
					
						
										
					RatedShipmentDetail[] rsds = rrd.getRatedShipmentDetails();
					if(rsds!=null && rsds.length>0)
					{
						for (int j = 0; j < rsds.length; j++) {
							RatedShipmentDetail rsd = rsds[j];
							ShipmentRateDetail srd = rsd.getShipmentRateDetail(); 
							if(srd.getRateType().toString().equals("PAYOR_ACCOUNT_SHIPMENT") || srd.getRateType().toString().equals("PAYOR_ACCOUNT_PACKAGE"))
							{							 
								Rating rating = new Rating();
								rating.setCarrierId(ShiplinxConstants.CARRIER_FEDEX);
//								rating.setServiceName(rrd.getServiceType().getValue());
								rating.setBillWeight(srd.getTotalBillingWeight().getValue().doubleValue());
								rating.setCustomerCarrier(customerCarrier); //remember the account that was used to rate, we will use the same one at shipping time
								rating.setCurrency(srd.getTotalNetCharge().getCurrency());
								log.debug("\nPROCESSING SERVICE : " + rating.getServiceName());
								String serviceType = rrd.getServiceType().getValue();
								Long l = fedexServiceMap.get(serviceType);
								if(l==null){
									log.warn("FedEx service needs to be mapped to system > " + serviceType);
									continue;
								}
									
								rating.setServiceId(l);
								
								determineTimeInTransit(rating, timeInTransit);
								
								rating.setTotalCost(getFormattedValue(srd.getTotalNetCharge().getAmount().doubleValue()));
								rating.setBaseCharge(getFormattedValue(srd.getTotalNetFreight().getAmount().doubleValue()));
								double totalFreightDiscounts = getFormattedValue(srd.getTotalFreightDiscounts().getAmount().doubleValue());

								//Add the freight charge to the charges
								com.meritconinc.shiplinx.model.Charge charge = new com.meritconinc.shiplinx.model.Charge();
								double surchargeValue = rating.getBaseCharge();
								String surchargeName = FedEx.FREIGHT_CHARGE_CODE;
								charge.setName(ShiplinxConstants.FREIGHT_STRING);
								charge.setCost(surchargeValue);
								charge.setCharge(surchargeValue);
								charge.setChargeCode(surchargeName);
								charge.setTariffRate((FormattingUtil.add(surchargeValue, totalFreightDiscounts)).doubleValue());
								double freightTariff = charge.getTariffRate();
								rating.getCharges().add(charge);
								
								double fuelSurchargePercentage = srd.getFuelSurchargePercent().doubleValue()/100;
								log.debug("Base rate is " + rating.getBaseCharge());
								log.debug("Fuel surcharge perc=" + fuelSurchargePercentage);
								
								//FedEx Charge Look up Logic: The rating API returns the rates along with the name of the charge. But the EDI file uses charge codes to identify the charges.
								//We will set the charge code as the name returned in the API response. The carrier_charge_code table will store both codes for FedEx, i.e charge_code will contain the name returned by the API and the charge_code_level_2 will contain the code returned in the EDI file
								
								Surcharge [] surcharges_Fedex = srd.getSurcharges();
								//Charge charge = new Charge();
								for(Surcharge s: srd.getSurcharges()){
									log.debug("Charge type/amount/description : " + s.getSurchargeType() + " / " + s.getAmount().getAmount().doubleValue() + " / "+ s.getDescription());

									charge = new com.meritconinc.shiplinx.model.Charge();

									surchargeValue = s.getAmount().getAmount().doubleValue();
									surchargeName = s.getSurchargeType().getValue();

									charge.setName(s.getDescription());
									charge.setCost(surchargeValue);
									charge.setCharge(surchargeValue);
									charge.setChargeCode(surchargeName);
									
									if(charge.getChargeCode().equalsIgnoreCase(FedEx.FUEL_CHARGE_CODE))
										charge.setTariffRate(freightTariff * fuelSurchargePercentage);
									else
										charge.setTariffRate(charge.getCost());

									rating.getCharges().add(charge);
									rating.setTotalSurcharge(getFormattedValue(rating.getTotalSurcharge()+surchargeValue));

								}
//Leave taxes out for now
//								if(srd.getTaxes()!=null){
//									for(Tax tax :srd.getTaxes()){
//										log.debug("Charge type/amount/description : " + tax.getTaxType() + " / " + tax.getAmount().getAmount().doubleValue() + " / "+ tax.getDescription());
//
//										double taxAmount = tax.getAmount().getAmount().doubleValue();
//										charge = new com.meritconinc.shiplinx.model.Charge();
//										charge.setName(tax.getDescription());
//										charge.setCost(taxAmount);
//										charge.setCharge(taxAmount);
//										charge.setChargeCode(tax.getTaxType().getValue());
//										rating.getCharges().add(charge);
//										rating.setTotalSurcharge(getFormattedValue(rating.getTotalSurcharge()+taxAmount));
//
//									}
//								}
								log.debug("-------getSpecialRatingApplied---------"+srd.getSpecialRatingApplied());
								if(srd.getSpecialRatingApplied()!=null){
									for(SpecialRatingAppliedType spRatingType :srd.getSpecialRatingApplied()){
										log.debug("-------getValue---------"+spRatingType.getValue());
										log.debug("-------spRatingType---------"+spRatingType.getTypeDesc());
									}
								}

								//rating.setCarrierName(ShiplinxConstants.CARRIER_FEDEX_STRING);
								ratingList.add(rating);

							}
						}
					}
				}
				
			}
	}

	
//	public void breakDownRates(RateReply reply) {
//		RateReplyDetail[] rrds = reply.getRateReplyDetails();
//
//
//		ratingList = new ArrayList<Rating>();
//
//		try{
//			if(rrds!=null){
//				for (int i = 0; i < rrds.length; i++) {
//					RateReplyDetail rrd = rrds[i];
//					Rating rating = new Rating();
//
//					//rating.setCarrierName("FEDEX");
//					rating.setCarrierId(ShiplinxConstants.CARRIER_FEDEX);
//					RatedShipmentDetail[] rsds = rrd.getRatedShipmentDetails();
//					rating.setServiceName(rrd.getServiceType().getValue());
//					rating.setServiceId(fedexServiceMap.get(rrd.getServiceType().getValue()));
//					rating.setTransitDays((rrd.getTransitTime()== null ? 1 : Integer.parseInt(rrd.getTransitTime().getValue())));
//
//					for (int j = 0; j < rsds.length; j++) {
//
//						RatedShipmentDetail rsd = rsds[j];
//						ShipmentRateDetail srd = rsd.getShipmentRateDetail();
//						
//						rating.setTotalSurcharge(getFormattedValue(srd.getTotalSurcharges().getAmount().doubleValue()));
//						rating.setTotalCost(getFormattedValue(srd.getTotalNetCharge().getAmount().doubleValue()));
//						rating.setBaseCharge(getFormattedValue(srd.getTotalBaseCharge().getAmount().doubleValue()));
//						rating.setFuel_perc(getFormattedValue(srd.getFuelSurchargePercent().doubleValue()));
//						BigDecimal totalCost = srd.getTotalNetCharge().getAmount().setScale(2,BigDecimal.ROUND_CEILING); // new BigDecimal();
//						//BigDecimal totalNetCharge = srd.getTotalNetCharge().getAmount().setScale(2,BigDecimal.ROUND_CEILING);
//						BigDecimal fuelSurchargePercent = srd.getFuelSurchargePercent().setScale(2,BigDecimal.ROUND_CEILING);
//						//BigDecimal baseCharge = srd.getTotalBaseCharge().getAmount().setScale(2,BigDecimal.ROUND_CEILING);
//
//						Surcharge [] surcharges_Fedex = srd.getSurcharges();
//						//Charge charge = new Charge();
//						log.debug("---surcharges_Fedex.length------"+surcharges_Fedex.length);
//						for(int k=0;k<surcharges_Fedex.length;k++){
//							//com.meritconinc.shiplinx.model.Surcharge surcharge = new com.meritconinc.shiplinx.model.Surcharge();
//							com.meritconinc.shiplinx.model.Charge surcharge = new com.meritconinc.shiplinx.model.Charge();
//
//							double surchargeValue = surcharges_Fedex[k].getAmount().getAmount().doubleValue();
//							String surchargeName = surcharges_Fedex[k].getSurchargeType().getValue();
//
//							surcharge.setName(surcharges_Fedex[k].getDescription());
//							surcharge.setAmount(surchargeValue);
//							if(surchargeName.equalsIgnoreCase("OTHER"))
//								surcharge.setName("Other");
//
//							rating.getCharges().add(surcharge);
//
//						}
//
//						//rating.setCharge(charge);
//
//						FreightRateDetail frd = srd.getFreightRateDetail();
//
//						RateDiscount[] rd = srd.getFreightDiscounts();
//
//						log.debug("-------getTaxes---------"+srd.getTaxes());
//						Tax[] taxArray = srd.getTaxes();
//
//						if(srd.getTaxes()!=null){
//							for(Tax tax :srd.getTaxes()){
//								log.debug("-------getDescription---------"+tax.getDescription());
//								log.debug("-------getAmount---------"+tax.getAmount().getAmount().doubleValue());
//								log.debug("-------getTaxType---------"+tax.getTaxType());
//
//								double taxAmount = tax.getAmount().getAmount().doubleValue();
//								com.meritconinc.shiplinx.model.Charge charge = new com.meritconinc.shiplinx.model.Charge();
//								charge.setName(tax.getTaxType().getValue());
//								charge.setAmount(taxAmount);
//								rating.getCharges().add(charge);
//								rating.setTotalSurcharge(getFormattedValue(rating.getTotalSurcharge()+taxAmount));
//
//							}
//						}
//						log.debug("-------getSpecialRatingApplied---------"+srd.getSpecialRatingApplied());
//						if(srd.getSpecialRatingApplied()!=null){
//							for(SpecialRatingAppliedType spRatingType :srd.getSpecialRatingApplied()){
//								log.debug("-------getValue---------"+spRatingType.getValue());
//								log.debug("-------spRatingType---------"+spRatingType.getTypeDesc());
//							}
//						}
//
//						if(rd!=null){
//							for (int rdcounter = 0; rdcounter<rd.length; rdcounter++ ){
//								RateDiscount rdtest = rd[rdcounter];
//							}
//						}
//						BigDecimal fuelCharge = new BigDecimal("0.00");
//						fuelCharge = totalCost.multiply(fuelSurchargePercent).setScale(2,BigDecimal.ROUND_CEILING);
//						fuelCharge = fuelCharge.divide(new BigDecimal("100.00"),BigDecimal.ROUND_CEILING).setScale(2,BigDecimal.ROUND_CEILING);
//						//rating.setFuelSurcharge(fuelCharge.doubleValue());
//
//
//						RatedPackageDetail[] rpds = rsd.getRatedPackages();
//
//
//						if (rpds != null && rpds.length > 0) {
//							log.debug("-------RatedPackageDetail---------");
//							for (int k = 0; k < rpds.length; k++) {
//								RatedPackageDetail rpd = rpds[k];
//								PackageRateDetail prd = rpd.getPackageRateDetail();
//
//								if (prd != null) {
//
//									//rating.setBaseCharge( prd.getBaseCharge().getAmount().doubleValue());
//									Surcharge[] surcharges = prd.getSurcharges();
//									if (surcharges != null && surcharges.length > 0) {
//										List<com.meritconinc.shiplinx.model.Surcharge> surchargeList = new ArrayList<com.meritconinc.shiplinx.model.Surcharge>();
//										for (int m = 0; m < surcharges.length; m++) {
//
//											com.meritconinc.shiplinx.model.Surcharge surchargeRating  = new com.meritconinc.shiplinx.model.Surcharge();
//
//											Surcharge surchargeTemp = surcharges[m];
//											surchargeRating.setName(surchargeTemp.getDescription());
//											surchargeRating.setCharges(surchargeTemp.getAmount().getAmount().doubleValue());
//											surchargeList.add(surchargeRating);
//										}
//										//rating.setSurcharges(surchargeList);
//									}
//								}
//							}
//						}
//						rating.setCarrierName(ShiplinxConstants.CARRIER_FEDEX_STRING);
//						ratingList.add(rating);
//						//}
//
//					}
//				}
//			}
//		}catch (Exception e) {
//			//e.printStackTrace();
//			log.debug("----Exception------"+e);
//		}
//	}
//
	private void getNotifications(Notification[] notifications) {
		for (int i=0; i < notifications.length; i++){
			Notification n = notifications[i];
			if (n == null) 
				continue;

			NotificationSeverityType nst = n.getSeverity();

		}
	}


	private boolean isResponseOk(NotificationSeverityType notificationSeverityType) {
		if (notificationSeverityType == null) {
			return false;
		}
		if (notificationSeverityType.equals(NotificationSeverityType.WARNING) ||
				notificationSeverityType.equals(NotificationSeverityType.NOTE)    ||
				notificationSeverityType.equals(NotificationSeverityType.SUCCESS)) {
			return true;
		}
		return false;
	}

	private static void updateEndPoint(RateServiceLocator serviceLocator) {
		
		String endPoint;
		
		if(ShiplinxConstants.isTestMode()){
			log.debug("Sending FedEx request to TEST server");
			endPoint=TEST_URL_RATE;	
		}
		else{
			log.debug("Sending FedEx request to LIVE server");
			endPoint=LIVE_URL_RATE;
		}
		if (endPoint != null) {
			serviceLocator.setRateServicePortEndpointAddress(endPoint);
		}
	}
	

	public List<Rating> getRatingList() {
		return ratingList;
	}

	public void setRatingList(List<Rating> ratingList) {
		this.ratingList = ratingList;
	}

	private FedExException getRatingError(RateReply rateReply) {
		FedExException exception = null;
		try{
			exception = new FedExException();	
			Notification[] n= (Notification[])rateReply.getNotifications();
			for(int i=0; i<n.length;i++){
				log.debug("---n[i].getSeverity()---111----"+n[i].getSeverity());
				if(n[i].getSeverity()==NotificationSeverityType.ERROR || n[i].getSeverity()==NotificationSeverityType.FAILURE){
					log.debug("---n[i].getMessage()----------"+n[i].getMessage());
					exception.getErrorMessages().add(n[i].getMessage());
				}
				/*if("ERROR".equals(n[i].getSeverity())||"FAILURE".equals(n[i].getSeverity())){
					log.debug("---n[i].getMessage()----222------"+n[i].getMessage());
					exception.getErrorMessages().add(n[i].getMessage());
				}*/
			}

		}catch (Exception e) {
			log.debug("---getRatingError----------"+e);
			//e.printStackTrace();
		}
		return exception;	
	}
	
	private double getFormattedValue(double amount){
		DecimalFormat twoDForm = new DecimalFormat("#.##");
		return Double.valueOf(twoDForm.format(amount));
	}
	
	public void setOrder() {
		String currency=null;
		try{
		//setting customer reference
		CustomerReference custRef=new CustomerReference();
		custRef.setValue(order.getReferenceCode());
		RequestedShipment requestedShipment = new RequestedShipment();
		//setting ship from info
		Party shipper = new Party();
	    Address shipperAddress = new Address(); // Origin information
	    shipperAddress.setStateOrProvinceCode(order.getFromAddress().getProvinceCode());
	    shipperAddress.setPostalCode(order.getFromAddress().getPostalCode());
	    shipperAddress.setCountryCode(order.getFromAddress().getCountryCode());
	    shipperAddress.setCity(order.getFromAddress().getCity());
        shipper.setAddress(shipperAddress);
        requestedShipment.setShipper(shipper);
         //setting ship to info
        Party recipient = new Party();
	    Address recipientAddress = new Address(); // Destination information
	    recipientAddress.setStateOrProvinceCode(order.getToAddress().getProvinceCode());
	    recipientAddress.setPostalCode(order.getToAddress().getPostalCode());
	    recipientAddress.setCountryCode(order.getToAddress().getCountryCode());
	    recipientAddress.setCity(order.getToAddress().getCity());
	    recipient.setAddress(recipientAddress);
	    requestedShipment.setRecipient(recipient);
	    //setting payment type as third party
	    Payment shippingChargesPayment = new Payment();
	    shippingChargesPayment.setPaymentType(PaymentType.SENDER);
	   // shippingChargesPayment.setPaymentType(PaymentType.fromValue("THIRD_PARTY"));
	    requestedShipment.setShippingChargesPayment(shippingChargesPayment);
	    
	    //SET PACKAGE count
	    requestedShipment.setPackageCount(new NonNegativeInteger(Integer.toString(order.getPackages().size())));
	    requestedShipment.setRateRequestTypes(new RateRequestType[] {RateRequestType.ACCOUNT});
	    requestedShipment.setPackageDetail(RequestedPackageDetailType.INDIVIDUAL_PACKAGES);
		if(order.getFromAddress().getCountryCode().equalsIgnoreCase(ShiplinxConstants.CANADA))
			currency=FedExConstants.CURRENCY_TYPE_VALUE_CAD;
		else if(order.getFromAddress().getCountryCode().equalsIgnoreCase(ShiplinxConstants.US))
			currency=FedExConstants.CURRENCY_TYPE_VALUE_USD;
		else if(order.getToAddress().getCountryCode().equalsIgnoreCase(ShiplinxConstants.US))
			currency=FedExConstants.CURRENCY_TYPE_VALUE_USD;
		else if(order.getToAddress().getCountryCode().equalsIgnoreCase(ShiplinxConstants.CANADA))
			currency=FedExConstants.CURRENCY_TYPE_VALUE_CAD;
		//set package details
		com.fedex.rate.stub.RequestedPackageLineItem[] requestedPackageLineItems;
	    requestedPackageLineItems = new RequestedPackageLineItem[order.getPackages().size()];
	    
	    //signature Required
	    List<PackageSpecialServiceType> psstList  = new ArrayList<PackageSpecialServiceType>();
	    psstList.add(PackageSpecialServiceType.SIGNATURE_OPTION);
	    SignatureOptionDetail sod = new SignatureOptionDetail();
			
	    if(order.getSignatureRequired() == 1)
			sod.setOptionType(SignatureOptionType.NO_SIGNATURE_REQUIRED);
	    else if(order.getSignatureRequired() == 2)
				sod.setOptionType(SignatureOptionType.DIRECT);
		else if(order.getSignatureRequired() == 3)
				sod.setOptionType(SignatureOptionType.INDIRECT);
		else if(order.getSignatureRequired() == 4)
			sod.setOptionType(SignatureOptionType.ADULT);
		else
			sod.setOptionType(SignatureOptionType.SERVICE_DEFAULT);
	    
	 	pssr.setSignatureOptionDetail(sod);
	 	if(order.getDangerousGoods()!=0){
			log.debug("---getDangerousGoods------"+order.getDangerousGoods());
			DangerousGoodsDetail dgDetail = new DangerousGoodsDetail();
			dgDetail.setAccessibility(DangerousGoodsAccessibilityType.ACCESSIBLE);
//	 		HazardousCommodityOptionType[] hcotype = new HazardousCommodityOptionType[1];
//	 		hcotype[0] = HazardousCommodityOptionType.HAZARDOUS_MATERIALS;
//	 		dgDetail.setOptions(hcotype);
			psstList.add(PackageSpecialServiceType.DANGEROUS_GOODS);
			pssr.setDangerousGoodsDetail(dgDetail);
		}
	    PackageSpecialServiceType[] psst = new PackageSpecialServiceType[psstList.size()];
	    pssr.setSpecialServiceTypes(psstList.toArray(psst));

		    
	    int i=0;
	    for(Package p : order.getPackages()) {
			RequestedPackageLineItem rp = new RequestedPackageLineItem();
			//set weight
		    rp.setWeight(new Weight(WeightUnits.LB, p.getWeight()));
		    if(order.getPackageTypeId().getPackageTypeId()==ShiplinxConstants.PACKAGE_TYPE_ENVELOPE)
		    	//rp.setWeight(new Weight(WeightUnits.LB, new BigDecimal(0.5)));
		    rp.setWeight(new Weight(WeightUnits.LB, p.getWeight()));
		    // set insurance value
	    	rp.setInsuredValue(new Money(currency, p.getInsuranceAmount()));		    
		    rp.setDimensions(new Dimensions(new NonNegativeInteger(Integer.toString(p.getLength().intValue())), new NonNegativeInteger(Integer.toString(p.getWidth().intValue())), new NonNegativeInteger(Integer.toString(p.getHeight().intValue())), LinearUnits.IN));
		    rp.setSpecialServicesRequested(pssr);
		    requestedPackageLineItems[i]=rp;
		    i++;
		    
		}   
		requestedShipment.setRequestedPackageLineItems(requestedPackageLineItems);
	    //set package type		
		if(order.getPackageTypeId().getPackageTypeId() ==  ShiplinxConstants.PACKAGE_TYPE_PACKAGE || order.getPackageTypeId().getPackageTypeId() ==  ShiplinxConstants.PACKAGE_TYPE_PALLET) {
			requestedShipment.setPackagingType(PackagingType.YOUR_PACKAGING);
		} else if(order.getPackageTypeId().getPackageTypeId() == ShiplinxConstants.PACKAGE_TYPE_ENVELOPE) {
			requestedShipment.setPackagingType(PackagingType.FEDEX_ENVELOPE);
		}else if(order.getPackageTypeId().getPackageTypeId() == ShiplinxConstants.PACKAGE_TYPE_PAK) {
			requestedShipment.setPackagingType(PackagingType.FEDEX_PAK);	
		}
		Calendar c = Calendar.getInstance();
		c.setTime(order.getScheduledShipDate());
		requestedShipment.setShipTimestamp(c);
		requestedShipment.setDropoffType(DropoffType.REGULAR_PICKUP);
		
		ShipmentSpecialServicesRequested ssr=new ShipmentSpecialServicesRequested();
		List<ShipmentSpecialServiceType> sstList  = new ArrayList<ShipmentSpecialServiceType>();
		BigDecimal codValue = new BigDecimal(order.getCODValue());
		log.debug("---codValue-----------------"+codValue.doubleValue());

		if(codValue.doubleValue() >0){
			sstList.add(ShipmentSpecialServiceType.COD);
			ssr.setCodDetail(new CodDetail());
			ssr.getCodDetail().setCodCollectionAmount(new Money("USD",codValue));
			ssr.getCodDetail().setCollectionType(CodCollectionType.ANY);
		}	
		
		  
		if(order.getHoldForPickupRequired() != null && order.getHoldForPickupRequired()){
			log.debug("---order.getHoldForPickupRequired()---------"+order.getHoldForPickupRequired());
			sstList.add(ShipmentSpecialServiceType.HOLD_AT_LOCATION);
			HoldAtLocationDetail holdAtLocationDetail = new HoldAtLocationDetail();
			ContactAndAddress contactAndAddress=new ContactAndAddress();
			contactAndAddress.setAddress(recipientAddress);
			holdAtLocationDetail.setLocationContactAndAddress(contactAndAddress);
			holdAtLocationDetail.setPhoneNumber(order.getToAddress().getPhoneNo());
			ssr.setHoldAtLocationDetail(holdAtLocationDetail);
		}

		if(order.getToTailgate() != null && order.getToTailgate()){
			log.debug("---getToTailgate------"+order.getToTailgate());
			sstList.add(ShipmentSpecialServiceType.LIFTGATE_DELIVERY);
		}

		if(order.getFromTailgate() != null && order.getFromTailgate()){
			log.debug("---getFromTailgate------"+order.getFromTailgate());
			sstList.add(ShipmentSpecialServiceType.LIFTGATE_PICKUP);
		}

		if(order.getSatDelivery() != null && order.getSatDelivery()){
			log.debug("---getSatDelivery------"+order.getSatDelivery());
			sstList.add(ShipmentSpecialServiceType.SATURDAY_DELIVERY);
		}

		ShipmentSpecialServiceType[] sst = new ShipmentSpecialServiceType[sstList.size()];
		ssr.setSpecialServiceTypes(sstList.toArray(sst));

		if(sstList.size()>0)
			requestedShipment.setSpecialServicesRequested(ssr);
	    request.setRequestedShipment(requestedShipment);
		}catch(Exception e){
			e.printStackTrace();
			log.info(e.getMessage());
		}
	}
	
	private void determineTimeInTransit(Rating r, String timeInTransit){
		if(timeInTransit==null){
			log.error("FedEx did not return transit time!");
			return;
		}
		log.debug("FedEx returned transit time : " + timeInTransit);
		
		if(timeInTransit.contains("_")){ //if the string contains underscore "_", then we need to determine based on string comparison
		
			if(timeInTransit.contains("ONE"))
				r.setTransitDays(1);
			else if(timeInTransit.contains("TWO"))
				r.setTransitDays(2);
			else if(timeInTransit.contains("THREE"))
				r.setTransitDays(3);
			else if(timeInTransit.contains("FOUR"))
				r.setTransitDays(4);
			else if(timeInTransit.contains("FIVE"))
				r.setTransitDays(5);
			else if(timeInTransit.contains("SIX"))
				r.setTransitDays(6);
			else 
				r.setTransitDays(7);
		}
		else{ //the string has been set as a number
			r.setTransitDays(new Integer(timeInTransit));
		}
	}


}
