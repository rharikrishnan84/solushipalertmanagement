package com.meritconinc.shiplinx.carrier.loomis;

import org.apache.log4j.Logger;

import com.cwsi.eshipper.carrier.ups.rate.RatingServiceSelectionResponseDocument;
import com.meritconinc.shiplinx.carrier.utils.LoomisException;

public class LoomisResponseHelper {

	private static Logger logger = Logger.getLogger(LoomisResponseHelper.class);
	private static String SUCCESS_CODE = "1";

	public LoomisResponseHelper() {

	}

	public LoomisException getRatingError(RatingServiceSelectionResponseDocument response) {
		LoomisException exception = null;
		return exception;	
	} 

//	public LoomisException getShipmentConfirmError(ShipmentConfirmResponseDocument response) {
//
//		LoomisException exception = new LoomisException();		
//		return exception;	
//	}
//
//	public LoomisException getShipmentAcceptError(ShipmentAcceptResponseDocument response) {
//
//		LoomisException exception = new LoomisException();	
//
//		return exception;	
//	}
//
//	public LoomisException getTrackError(TrackResponseDocument response) {
//
//		LoomisException exception = new LoomisException();		
//		return exception;	
//	}
//
//	public LoomisException getVoidError(VoidShipmentResponseDocument response) {
//
//		LoomisException exception = null;		
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
