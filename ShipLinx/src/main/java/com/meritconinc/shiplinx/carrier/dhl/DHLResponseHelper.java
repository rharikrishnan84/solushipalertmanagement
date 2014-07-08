package com.meritconinc.shiplinx.carrier.dhl;

import org.apache.log4j.Logger;

import com.cwsi.eshipper.carrier.ups.rate.RatingServiceSelectionResponseDocument;
import com.meritconinc.shiplinx.carrier.utils.DHLException;

public class DHLResponseHelper {

	private static Logger logger = Logger.getLogger(DHLResponseHelper.class);
	private static String SUCCESS_CODE = "1";

	public DHLResponseHelper() {

	}

	public DHLException getRatingError(RatingServiceSelectionResponseDocument response) {
		DHLException exception = null;
		return exception;	
	}

//	public DHLException getShipmentConfirmError(ShipmentConfirmResponseDocument response) {
//
//		DHLException exception = new DHLException();		
//		return exception;	
//	}
//
//	public DHLException getShipmentAcceptError(ShipmentAcceptResponseDocument response) {
//
//		DHLException exception = new DHLException();	
//
//		return exception;	
//	}
// 
//	public DHLException getTrackError(TrackResponseDocument response) {
//
//		DHLException exception = new DHLException();		
//		return exception;	
//	}
//
//	public DHLException getVoidError(VoidShipmentResponseDocument response) {
//
//		DHLException exception = null;		
//		return exception;	
//	}
//	
//	public Map<Long,Rating> extractRates(RatingServiceSelectionResponseDocument response, ShippingOrder order) {
//		Map<Long,Rating> ratesMap = new HashMap<Long,Rating>();
//		
//		return ratesMap;
//	}
//	
//     public Map<String,String> extractCodeAndTransitDays(TimeInTransitResponseDocument response){
//        
//        return null;
//     }
//
//	public void processShipmentAcceptResponse(ShipmentAcceptResponseDocument response, ShippingOrder order) {
//		
//		
//	}


}
