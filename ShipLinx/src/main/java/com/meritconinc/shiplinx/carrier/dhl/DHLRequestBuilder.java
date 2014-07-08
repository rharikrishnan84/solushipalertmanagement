package com.meritconinc.shiplinx.carrier.dhl;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.meritconinc.shiplinx.carrier.dhl.xml.BookPickupRequest;
import com.meritconinc.shiplinx.carrier.dhl.xml.CancelPickupRequest;
import com.meritconinc.shiplinx.carrier.dhl.xml.DCTRequest;
import com.meritconinc.shiplinx.carrier.dhl.xml.DCTResponse;
import com.meritconinc.shiplinx.carrier.dhl.xml.ErrorResponse;
import com.meritconinc.shiplinx.carrier.dhl.xml.PickupErrorResponse;
import com.meritconinc.shiplinx.carrier.dhl.xml.PickupResponse;
import com.meritconinc.shiplinx.carrier.dhl.xml.QtdSExtrChrgInAdCurType;
import com.meritconinc.shiplinx.carrier.dhl.xml.QtdShpExChrgTypeDCTRes;
import com.meritconinc.shiplinx.carrier.dhl.xml.QtdShpTypeDCTRes;
import com.meritconinc.shiplinx.carrier.dhl.xml.ShipmentCustRatingRequest;
import com.meritconinc.shiplinx.carrier.dhl.xml.ShipmentRatingErrorResponse;
import com.meritconinc.shiplinx.carrier.dhl.xml.ShipmentRatingResponse;
import com.meritconinc.shiplinx.carrier.utils.DHLException;
import com.meritconinc.shiplinx.model.Charge;
import com.meritconinc.shiplinx.model.CustomerCarrier;
import com.meritconinc.shiplinx.model.FuelSurcharge;
import com.meritconinc.shiplinx.model.Pickup;
import com.meritconinc.shiplinx.model.Rating;
import com.meritconinc.shiplinx.model.ShippingOrder;
import com.meritconinc.shiplinx.utils.FormattingUtil;
import com.meritconinc.shiplinx.utils.ShiplinxConstants;

public class DHLRequestBuilder {

	private Logger logger = Logger.getLogger(DHLRequestBuilder.class);
	

	public List<Rating> createAndSendRateRequest(
			ShippingOrder shippingOrder, String serviceCode, List<Rating> rateList, CustomerCarrier customerCarrier, FuelSurcharge f) throws DHLException{
		logger.debug("-----createAndSendRateRequest-DHL----");
//		return createAndSendRateRequestByCustRating(shippingOrder, serviceCode);
		return createAndSendRateRequestByCapabilityAndQuote(shippingOrder, serviceCode, rateList, customerCarrier, f);
	}	
	
	private List<Rating> createAndSendRateRequestByCapabilityAndQuote(ShippingOrder shippingOrder, 
			String serviceCode, List<Rating> rateList, CustomerCarrier customerCarrier, FuelSurcharge f) {
		// TODO Auto-generated method stub
		logger.debug("-----createAndSendRateRequestByCapabilityAndQuote-DHL----");
		DCTRequest dctReq = DHLXmlDataConverter.populateDCTRequest(shippingOrder, customerCarrier);
		DHLXmlWSClient xmlWsClient = new DHLXmlWSClient();
		String sReq = xmlWsClient.transform(dctReq); 
		String servletURL = null;
		if(ShiplinxConstants.isTestMode())
			servletURL = DHLAPI.HTTP_URL; //get rates from live server always
		else
			servletURL = DHLAPI.HTTP_URL;
		 
		if (sReq != null) {
			String sRes = xmlWsClient.submitRequest(sReq, servletURL);
			if (sRes != null) {
				if (sRes.contains("ConditionCode")) {
					DCTResponse errRes = xmlWsClient.processDCTErrorResponse(sRes);
					if (errRes != null && errRes.getGetQuoteResponse()!= null && errRes.getGetQuoteResponse().getNote() != null  
							&& errRes.getGetQuoteResponse().getNote().get(0)!= null && errRes.getGetQuoteResponse().getNote().get(0).getCondition()!=null) {
						
						String errMsg = errRes.getGetQuoteResponse().getNote().get(0).getCondition().get(0).getConditionData();
						logger.debug("ErrorResponse:" + errMsg);
						throw new DHLException(errMsg);
					}
				} else {
					// Valid Response
					DCTResponse res = xmlWsClient.processDCTResponse(sRes);
					if (res != null && res.getGetQuoteResponse() != null && res.getGetQuoteResponse().getBkgDetails() != null && 
										res.getGetQuoteResponse().getBkgDetails().size() > 0) {
						List<QtdShpTypeDCTRes> resQtdShpTypes = res.getGetQuoteResponse().getBkgDetails().get(0).getQtdShp();
						boolean isExport = shippingOrder.getFromAddress().getCountryCode().equalsIgnoreCase(ShiplinxConstants.CANADA) ? true : false;
						
						return mergeRatingResponse(rateList, resQtdShpTypes, isExport, f, customerCarrier);
					}					
//					logger.debug("DCTResponse:" + res);
				}
			}
		}
		
		return null;

	}

	private List<Rating> mergeRatingResponse(List<Rating> rateList,	List<QtdShpTypeDCTRes> resQtdShpTypes, boolean isExport, FuelSurcharge f, CustomerCarrier customerCarrier) {
		// TODO Auto-generated method stub
		List<Rating> ratesToDisplay = new ArrayList<Rating>();
		if (rateList != null && resQtdShpTypes != null && resQtdShpTypes.size() > 0) {
			for (int i=0; i<rateList.size(); i++) {
				Rating r = rateList.get(i);
				QtdShpTypeDCTRes qtdShp = DHLXmlDataConverter.findQtdShpByServiceId(resQtdShpTypes, r.getServiceId(), isExport);
				if (qtdShp != null) {
					if (qtdShp.getDimensionalWeight() != null){
						if(qtdShp.getWeightUnit().equalsIgnoreCase(DHLXmlDataConverter.KG)){
							r.setBillWeight(FormattingUtil.convertKgToLb(qtdShp.getDimensionalWeight()));
						}
						else{
							r.setBillWeight(qtdShp.getDimensionalWeight().doubleValue());
						}
						
					}
					if (qtdShp.getTotalTransitDays() != null)
						r.setTransitDays(qtdShp.getTotalTransitDays());
					
					try{
						//Trying to extract the Remote Area Surcharge
						if(qtdShp.getQtdShpExChrg()!=null){
							for(QtdShpExChrgTypeDCTRes qtdShpExChrg: qtdShp.getQtdShpExChrg()){
								if(qtdShpExChrg.getGlobalServiceName()!=null && qtdShpExChrg.getGlobalServiceName().contains("REMOTE")){
									double remoteChargeValue = 0;
									for(QtdSExtrChrgInAdCurType qtdShpExChrgInAd: qtdShpExChrg.getQtdSExtrChrgInAdCur()){
										if(qtdShpExChrgInAd.getCurrencyRoleTypeCode().equalsIgnoreCase("BILLC")){
											Charge c = new Charge();
											c.setChargeCode(DHLAPI.REMOTE_AREA_SERVICE_CODE);
											remoteChargeValue = qtdShpExChrgInAd.getChargeValue().doubleValue();
											c.setTariffRate(qtdShpExChrgInAd.getChargeValue().doubleValue());
											c.setCost(qtdShpExChrgInAd.getChargeValue().doubleValue());
											c.setCurrency(ShiplinxConstants.CURRENCY_CA_STRING);
											r.getCharges().add(c);
										}
									}
									//we need to add to fuel charge to include remote charge's share of fuel	
									if(remoteChargeValue > 0){
										for(Charge c: r.getCharges()){
											if(c.getChargeCode().equalsIgnoreCase(DHLAPI.FUEL_CHARGE_CODE)){
												double fuelForRemote = remoteChargeValue * f.getValue()/100;
												double totalFuelTariff = (FormattingUtil.add(c.getTariffRate().doubleValue(), fuelForRemote)).doubleValue();
												c.setTariffRate(totalFuelTariff);
												c.setCost(FormattingUtil.add(c.getCost().doubleValue(), fuelForRemote).doubleValue());
												//this fuel for remote should not be marked up/down as no discount on this.
												c.setStaticAmount(FormattingUtil.add(c.getStaticAmount(), fuelForRemote).doubleValue());
												break; //fuel found and modified, leave now
											}
										}
									}
								}
							}
						}
					}
					catch(Exception e){
						logger.error("Unable to extract Remote area rate from DHL Quote response", e);
					}
					
					ratesToDisplay.add(r);
				} else {
					// This rating does not found in Response Booking Details Quoted Shipments, remove it from the list
					//ratesToRemove.add(r);
				}
			}
		}
		return ratesToDisplay;
	}

	private List<Rating> createAndSendRateRequestByCustRating(ShippingOrder shippingOrder, 
			String serviceCode, List<Rating> rateList, CustomerCarrier customerCarrier) throws DHLException{
		logger.debug("-----createAndSendRateRequestByCustRating-DHL----");
		ShipmentCustRatingRequest custRatingReq = DHLXmlDataConverter.populateShipmentCustRatingRequest(shippingOrder, customerCarrier);
		DHLXmlWSClient xmlWsClient = new DHLXmlWSClient();
		String sReq = xmlWsClient.transform(custRatingReq); 
		String servletURL = null;
		if(ShiplinxConstants.isTestMode())
			servletURL = DHLAPI.HTTP_URL; //get rates from live server always
		else
			servletURL = DHLAPI.HTTP_URL;
		
		if (sReq != null) {
			String sRes = xmlWsClient.submitRequest(sReq, servletURL);
			if (sRes != null) {
				logger.debug(sRes);
				if (sRes.contains("ShipmentRatingErrorResponse")) {
					ShipmentRatingErrorResponse errRes = xmlWsClient.processShipmentRatingErrorResponse(sRes);
					if (errRes != null && errRes.getResponse() != null && errRes.getResponse().getStatus()!= null && 
							errRes.getResponse().getStatus().getCondition() != null) {
						String errMsg = DHLXmlDataConverter.statusConditionToString(
									errRes.getResponse().getStatus().getCondition());
						logger.debug("ErrorResponse:" + errMsg);
						throw new DHLException(errMsg);
					}
				} else {
					// Valid Response
					ShipmentRatingResponse res = xmlWsClient.processShipmentRatingResponse(sRes);
					logger.debug("ShipmentRatingResponse:" + res);
				}
			}
		}
		
		return null;
	}

//	public ShipmentAcceptResponseDocument createAndSendShipRequest(ShippingOrder shippingOrder, String serviceCode)  throws DHLException{		
//
//		return null;
//	}
//
//
//	public TrackResponse createAndSendTrackRequest(ShippingOrder order) {	
//
//		TrackResponseDocument response_doc = null;
//
//		return response_doc.getTrackResponse();
//	}
//
//	public boolean createAndSendVoidRequest(ShippingOrder order) {			
//		VoidShipmentResponseDocument response_doc = null;
//		return false;
//	}
	
	public PickupResponse createAndSendPickupRequest(Pickup pickup) throws DHLException{
		logger.debug("-----createAndSendPickupRequest-DHL----");
		BookPickupRequest pickupReq = DHLXmlDataConverter.populatePickupRequest(pickup);
		DHLXmlWSClient xmlWsClient = new DHLXmlWSClient();
		String sReq = xmlWsClient.transform(pickupReq); 
		String servletURL = null;
		if(ShiplinxConstants.isTestMode())
			servletURL = DHLAPI.HTTP_URL; 
		else
			servletURL = DHLAPI.HTTP_URL;
		
		if (sReq != null) {
			String sRes = xmlWsClient.submitRequest(sReq, servletURL);
			if (sRes != null) {
//				logger.debug(sRes);
				if (sRes.contains("PickupErrorResponse")) {
					PickupErrorResponse errRes = xmlWsClient.processPickupErrorResponse(sRes);
					if (errRes != null && errRes.getResponse() != null && errRes.getResponse().getStatus()!= null && 
							errRes.getResponse().getStatus().getCondition() != null) {
						String errMsg = DHLXmlDataConverter.statusConditionToString(
									errRes.getResponse().getStatus().getCondition());
						logger.debug("ErrorResponse:" + errMsg);
						throw new DHLException(errMsg);
					}
				} else {
					// Valid Response
					PickupResponse res = xmlWsClient.processBookPickupResponse(sRes);
					logger.debug("BookPickupResponse:" + res);
					return res;
				}
			}
		}
		
		return null;
	}

	public boolean createAndSendPickupCancelRequest(Pickup pickup) throws DHLException{
		logger.debug("-----createAndSendPickupCancelRequest-DHL----");
		CancelPickupRequest pickupReq = DHLXmlDataConverter.populateCancelPickupRequest(pickup);
		DHLXmlWSClient xmlWsClient = new DHLXmlWSClient();
		String sReq = xmlWsClient.transform(pickupReq); 
		String servletURL = null;
		if(ShiplinxConstants.isTestMode())
			servletURL = DHLAPI.HTTP_URL; //get rates from live server always
		else
			servletURL = DHLAPI.HTTP_URL;
		
		if (sReq != null) {
			String sRes = xmlWsClient.submitRequest(sReq, servletURL);
			if (sRes != null) {
//				logger.debug(sRes);
				if (sRes.contains("PickupErrorResponse")) {
					PickupErrorResponse errRes = xmlWsClient.processPickupErrorResponse(sRes);
					if (errRes != null && errRes.getResponse() != null && errRes.getResponse().getStatus()!= null && 
							errRes.getResponse().getStatus().getCondition() != null) {
						String errMsg = DHLXmlDataConverter.statusConditionToString(
									errRes.getResponse().getStatus().getCondition());
						logger.debug("ErrorResponse:" + errMsg);
						throw new DHLException(errMsg);
					}
				} else {
					// Valid Response
					return true;
				}
			}
		}
		
		return false;
	}

}
