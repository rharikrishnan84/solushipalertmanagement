package com.meritconinc.shiplinx.carrier.ups.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.cwsi.eshipper.carrier.ups.rate.ErrorDocument;
import com.cwsi.eshipper.carrier.ups.rate.RatingServiceSelectionResponseDocument;
import com.cwsi.eshipper.carrier.ups.rate.ServiceDocument;
import com.cwsi.eshipper.carrier.ups.rate.RatedShipmentDocument.RatedShipment;
import com.cwsi.eshipper.carrier.ups.rate.TransportationChargesDocument.TransportationCharges;
import com.cwsi.eshipper.carrier.ups.track.TrackResponseDocument;
import com.meritconinc.shiplinx.carrier.ups.dao.UPSCanadaTariffDAO;
import com.meritconinc.shiplinx.carrier.ups.ws.ship.proxy.CodeDescriptionType;
import com.meritconinc.shiplinx.carrier.ups.ws.ship.proxy.ShipmentResponse;
import com.meritconinc.shiplinx.carrier.utils.UPSException;
import com.meritconinc.shiplinx.model.Rating;
import com.meritconinc.shiplinx.model.Service;
import com.meritconinc.shiplinx.model.ShippingOrder;

public class UPSResponseHelper {

	private static Logger logger = Logger.getLogger(UPSResponseHelper.class);
	private static String SUCCESS_CODE = "1";

	public UPSResponseHelper() {

	}

	public UPSException getRatingError(RatingServiceSelectionResponseDocument response) {
		UPSException exception = null;
		try{ 
			exception = new UPSException();		
			//sometimes there could be warnings only, in this case no error has occured
			if(response.getRatingServiceSelectionResponse().getResponse().getResponseStatusCode().equalsIgnoreCase(SUCCESS_CODE))
				return exception;
			List<ErrorDocument.Error> errors = response.getRatingServiceSelectionResponse().getResponse().getErrorList();		
			for(ErrorDocument.Error error: errors){
				exception.getErrorMessages().add(error.getErrorCode() + ":" + error.getErrorDescription());		
			}	
		}catch (Exception e) {
			logger.debug("---Exception----------");
			e.printStackTrace();
		}
		return exception;	
	}

	public UPSException getShipmentRequestError(ShipmentResponse response) {

		UPSException exception = new UPSException();		
		//sometimes there could be warnings only, in this case no error has occured
		if(response.getResponse().getResponseStatus().getCode().equalsIgnoreCase(SUCCESS_CODE))
			return exception;

		for(CodeDescriptionType codeDesc : response.getResponse().getAlert()){
			exception.getErrorMessages().add(codeDesc.getCode() + ":" + codeDesc.getDescription());
		}
		
		return exception;	
	}

	public UPSException getTrackError(TrackResponseDocument response) {

		UPSException exception = new UPSException();		
		List<com.cwsi.eshipper.carrier.ups.track.ErrorDocument.Error> errors = response.getTrackResponse().getResponse().getErrorList();		
		for(com.cwsi.eshipper.carrier.ups.track.ErrorDocument.Error error: errors){
			exception.getErrorMessages().add(error.getErrorCode() + ":" + error.getErrorDescription());		
		}		
		return exception;	
	}

//	public UPSException getVoidError(VoidShipmentResponseDocument response) {
//
//		UPSException exception = null;		
//		List<com.cwsi.eshipper.carrier.ups.ship.ErrorDocument.Error> errors = response.getVoidShipmentResponse().getResponse().getErrorList();		
//		for(com.cwsi.eshipper.carrier.ups.ship.ErrorDocument.Error error: errors){
//			exception = new UPSException();
//			exception.getErrorMessages().add(error.getErrorCode() + ":" + error.getErrorDescription());		
//		}		
//		return exception;	
//	}
	
	public Map<Long,Rating> extractRates(RatingServiceSelectionResponseDocument response, ShippingOrder order, UPSCanadaTariffDAO upsCanadaTariffDAO, long carrierId) {
		Map<Long,Rating> ratesMap = new HashMap<Long,Rating>();
		
		List<RatedShipment> rated_shipments = response.getRatingServiceSelectionResponse().getRatedShipmentList();
		
		
		for(RatedShipment rated_shipment: rated_shipments){
			
			Rating rate = new Rating();
			rate.setCarrierId(carrierId);
			
			ServiceDocument.Service service = rated_shipment.getService();
			
			Service s = new Service();
			s.setCode(service.getCode());
			s.setCarrierId(carrierId);
			s = upsCanadaTariffDAO.getServiceCodeByService(s);

			if(s == null){
				logger.error("UPS is returning a rate under a non-registered service code of : " + service.getCode() +". Discarding..");
				continue;
			}
			Long serviceId = s.getId();
			
			List<String> ratedShipmentWarningList = rated_shipment.getRatedShipmentWarningList();
			
			for(String warning : ratedShipmentWarningList) {
				if(warning.startsWith("A Large Package Minimum Surcharge has been applied to Package")) {
					rate.setLargePackageSurcharges(rate.getLargePackageSurcharges() + 1);
				}
				if(warning.startsWith("Additional Handling has automatically been set on Package")) {
					rate.setHandlingCharges(rate.getHandlingCharges() + 1);
				}
				if(warning.startsWith("Ship To Address Classification is changed from Residential to Commercial")) {
					rate.setUps_switch_res_comm(true);
				}
				if(warning.startsWith("An Extended Area surcharge has been added to the service cost")) {
					rate.setUps_extended_area(true);
				}
				if(warning.startsWith("A Delivery Area surcharge has been added to the service cost")) {
					rate.setUps_extended_area(true);
				}
			}

			rate.setServiceId(serviceId);		
			rate.setBillWeight(Double.parseDouble(rated_shipment.getBillingWeight().getWeight()));
			
			TransportationCharges trans_charges = rated_shipment.getTransportationCharges();
			if(trans_charges != null){
				rate.setBaseCharge(Double.parseDouble(trans_charges.getMonetaryValue()));
				rate.setTariffRate(Double.parseDouble(trans_charges.getMonetaryValue()));
				rate.setCurrency(trans_charges.getCurrencyCode());
			}
			if(rated_shipment.getServiceOptionsCharges() != null)
				rate.setServiceOptionsCharges(Double.parseDouble(rated_shipment.getServiceOptionsCharges().getMonetaryValue()));
			/*if(rated_shipment.getHandlingChargeAmount() != null)
				rate.setHandlingCharges(Double.parseDouble(rated_shipment.getHandlingChargeAmount().getMonetaryValue()));	*/		
			if(rated_shipment.getNegotiatedRates() != null)
				rate.setNegotiatedRates(Double.parseDouble(rated_shipment.getNegotiatedRates().getNetSummaryCharges().getGrandTotal().getMonetaryValue()));		
			
			//we need to grab the negotiated rates, but if no negotiated rate is shown, we can show the tariff rate.
			double our_charge = 0;
			if(rate.getNegotiatedRates() > 0) {
				if(rate.getNegotiatedRates() == Double.parseDouble(rated_shipment.getTotalCharges().getMonetaryValue())) {
					logger.debug("Negotiated rate is equals to tariff rate!");
					rate.setDiscounted(false);
					our_charge = (Double.parseDouble(rated_shipment.getTotalCharges().getMonetaryValue()));
				} else {
					our_charge = rate.getNegotiatedRates();
				}
			} else {
				rate.setDiscounted(false);
				our_charge = (Double.parseDouble(rated_shipment.getTotalCharges().getMonetaryValue()));
			}
			
			if(our_charge==0){
				logger.warn("Rate for service " + service.getDescription() + " came back as 0, not displaying to customer!");
				continue;
			}
			
			//We have to consider the negotiated rates, as these are the rates that UPS is charging us
			//but all charges are bundled up in the negotiated rates
			rate.setBaseCharge(our_charge /* - rate.getServiceOptionsCharges() -  rate.getHandlingCharges()*/);
			
			try{
				if(rated_shipment.getGuaranteedDaysToDelivery() != null){
					logger.debug("Guaranteed Days to delivery for " + service.getDescription() + " is " + rated_shipment.getGuaranteedDaysToDelivery());
					//rate.setTransitDays(Integer.valueOf(rated_shipment.getGuaranteedDaysToDelivery()));
					rate.setTransitDays(Integer.valueOf(0));
				}
				else{ //if it is null					
					//As per ticket UDK Support | Case 28800287 | Saturday Delivery [Request: 110203-016064], if Sat delivery is requested, then the Guaranteed Delivery Days must be set.
					if(order.getSatDelivery())						
							rate.setDisabled(true);									
				}
			}
			catch(Exception e){
				logger.debug("Guaranteed Days to delivery for " + service.getCode() + " is null");
				rate.setTransitDays(UPSAPI.DEFAULT_DAYS_FOR_DELIVERY);
			}
			
			ratesMap.put(serviceId, rate);
		}		
		
		return ratesMap;
	}
	
//     public Map<String,String> extractCodeAndTransitDays(TimeInTransitResponseDocument response){
//        
//        HashMap<String,String> transitMap = new HashMap<String,String>();
//		List<TransitResponse> servicecode_transitdays = response.getTimeInTransitResponse().getTransitResponseList();  
//		
//        for(TransitResponse transit_response: servicecode_transitdays){
//       	
//        	List<ServiceSummary> serviceSummarys = transit_response.getServiceSummaryList();
//        	
//        	for(ServiceSummary serviceSummary : serviceSummarys) {
//        		com.cwsi.eshipper.carrier.ups.transit.ServiceDocument.Service service = serviceSummary.getService();
//        		EstimatedArrival estimatedArrival = serviceSummary.getEstimatedArrival();
//        		logger.debug("Service Id : " + service.getCode() + " Transit Days : " + estimatedArrival.getBusinessTransitDays());
//        		transitMap.put(service.getCode(), estimatedArrival.getBusinessTransitDays());
//        	}
//        }     
//        return transitMap;
//     }
//	public UPSException getTimeInTransitError(TimeInTransitResponseDocument response) {
//		
//		UPSException exception = new UPSException();		
//		List<com.cwsi.eshipper.carrier.ups.transit.ErrorDocument.Error> errors = response.getTimeInTransitResponse().getResponse().getErrorList();		
//		for(com.cwsi.eshipper.carrier.ups.transit.ErrorDocument.Error error: errors){
//			logger.error("Error Response from UPS Transit Time : " + error.getErrorCode() + ":" + error.getErrorDescription());
//			exception.getErrorMessages().add(error.getErrorCode() + ":" + error.getErrorDescription());		
//		}		
//		return exception;	
//	}


//	public void processShipmentAcceptResponse(ShipmentAcceptResponseDocument response, ShippingOrder order) {
//		
//		List<PackageResults> pack_results = response.getShipmentAcceptResponse().getShipmentResults().getPackageResultsList();
//		String pickUpConf = null;
//		if(response.getShipmentAcceptResponse().getShipmentResults().getPickupRequestNumber()!=null){
//			pickUpConf = response.getShipmentAcceptResponse().getShipmentResults().getPickupRequestNumber().toString();
//			pickUpConf = pickUpConf.replaceAll("<xml-fragment>", "");
//			pickUpConf = pickUpConf.replaceAll("</xml-fragment>", "");
//		}
//		logger.info("Pick up number is  " + pickUpConf);
//		if(pickUpConf!=null && pickUpConf.length()>0)
//			order.setCarrierPickUpConf(pickUpConf);
//		
//		int i=0; 
//		for(PackageResults pack_result: pack_results){
//			String image = pack_result.getLabelImage().getGraphicImage();
//			String tracking_number = pack_result.getTrackingNumber();
//			
//			if(i==0)
//				order.setMasterTrackingNum(tracking_number);
//			com.meritconinc.shiplinx.model.Package p = order.getPackages().get(i);
//			p.setTrackingNumber(tracking_number);
//			byte[] imageBytes = Base64.decodeBase64(image.getBytes());
//			p.setLabel(imageBytes);
//			ShippingLabel label = new ShippingLabel();
//			label.setLabel(imageBytes);
//			label.setTrackingNumber(tracking_number);
//			p.setLabelInfo(label);
//			
//			i++;
//		}
//		
//	}


}
