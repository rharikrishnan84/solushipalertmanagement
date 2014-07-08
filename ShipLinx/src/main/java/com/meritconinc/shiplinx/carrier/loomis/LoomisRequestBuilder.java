package com.meritconinc.shiplinx.carrier.loomis;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.log4j.Logger;

import com.cwsi.eshipper.carrier.ups.rate.RatingServiceSelectionResponseDocument;
import com.cwsi.eshipper.carrier.ups.track.TrackResponseDocument;
import com.cwsi.eshipper.carrier.ups.track.TrackResponseDocument.TrackResponse;
import com.meritconinc.shiplinx.carrier.loomis.ws.rateinq.proxy.ArrayOfPiece;
import com.meritconinc.shiplinx.carrier.loomis.ws.rateinq.proxy.Piece;
import com.meritconinc.shiplinx.carrier.loomis.ws.rateinq.proxy.RateInqSoap;
import com.meritconinc.shiplinx.carrier.loomis.ws.rateinq.proxy.ReturnRates;
import com.meritconinc.shiplinx.carrier.utils.LoomisException;
import com.meritconinc.shiplinx.model.CustomerCarrier;
import com.meritconinc.shiplinx.model.Service;
import com.meritconinc.shiplinx.model.ShippingOrder;

public class LoomisRequestBuilder {

	private static final Logger logger = Logger.getLogger(LoomisRequestBuilder.class);
	public static final String TEST_URL_RATEINQ = "http://canadacert.dhl.com/rateinq/RateInq.asmx";
	public static final String PROD_URL_RATEINQ = "http://canadacert.dhl.com/rateinq/RateInq.asmx";
	public static final String USERNAME = "LMRQtest";
	public static final String PASSWORD = "LMRQtest";
	

	public RatingServiceSelectionResponseDocument createAndSendRateRequest(ShippingOrder shippingOrder, String serviceCode, CustomerCarrier customerCarrier) 
		throws LoomisException{
		logger.debug("-----createAndSendRateRequest-Loomis----");
		ReturnRates response = null;
		try { 
			JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();

			factory.getInInterceptors().add(new LoggingInInterceptor());
			factory.getOutInterceptors().add(new LoggingOutInterceptor());
			factory.setServiceClass(RateInqSoap.class);
			factory.setAddress(TEST_URL_RATEINQ);
//			factory.setAddress(PROD_URL_RATEINQ);
			
			RateInqSoap client = (RateInqSoap) factory.create();
			
			response = client.rateInq(	
					USERNAME, 												// loginName, 
					PASSWORD, 												// password, 
//					shippingOrder.getCustomerCarrier().getAccountNumber1(), // shipperAccountNumber, 
					customerCarrier.getAccountNumber2(), // shipperAccountNumber,
					shippingOrder.getFromAddress().getPostalCode(), 		// fromPostalCode, 
					shippingOrder.getToAddress().getPostalCode(),			// toPostalCode, 
					shippingOrder.getToAddress().getCity(),					// toCity, 
					shippingOrder.getToAddress().getProvinceCode(), 		// toProvinceCode, 
					shippingOrder.getToAddress().getCountryCode(),  		// toCountryCode, 
					getServiceType(serviceCode, shippingOrder.getService()), 	// serviceType, 
					getXmlDate(shippingOrder.getScheduledShipDate()), 		// pickupDate, 
					getUOM(shippingOrder.getPackages().get(0).getWeightUOM()), 	// uom, 
					getPieces(shippingOrder),						   		// pieces, 
					shippingOrder.getInsuredAmount(),				   		// valuationAmount, 
					true,											   		// isNonPack, 
					false,											   		// isDangerousGood, 
					false,											   		// isSaturdayDelivery, 
					false,											   		// isFragile, 
					false,											   		// isResidential, 
					false,											   		// isDutiable, 
					false,											   		// isDTP, 
					"en");										       		// language);
			if (response != null) {
				String msg = "Response - [ReturnCode:" + response.getReturnCode() + "][Message:" + response.getReturnMessage() + "]";
				logger.debug(msg);
				if (response.getReturnCode() != 1) {
					throw new LoomisException(msg);
				}
			}

		} catch (LoomisException le) {
			throw le;
		} catch (Exception e) {
			e.printStackTrace();
		}

//		return response;
		
		return null;
	}

	private String getUOM(String uom) {
		// TODO Auto-generated method stub
		// MyToDo - Need to implement this after discussion with Rizwan
//		I - Imperial (lbs/inch)
//		M - Metric (kg/cm)
		if (uom == null)
			return "I";
		else if (uom.startsWith("K"))
			return "M";
		return "I";
	}

	private String getServiceType(String serviceCode, Service service) {
		// TODO Auto-generated method stub
		// MyToDo - Need to implement this after discussion with Rizwan
//		Service types for Domestic:
//			• DD
//			• DE
//			• D9
//			• DN
//			Service types for US Ground:
//			• WD
//			Service types for International:
//			• WW
//			• W9
//			• WM
//			• WN	
		
		
		return "WD";
	}

	private ArrayOfPiece getPieces(ShippingOrder order) {
		// TODO Auto-generated method stub
		ArrayOfPiece pieces = new ArrayOfPiece();
		
		for (com.meritconinc.shiplinx.model.Package p:order.getPackages()) {
			Piece piece = new Piece();
			piece.setHeight(p.getHeight().doubleValue());
			piece.setLength(p.getLength().doubleValue());
			piece.setWeight(p.getWeight().doubleValue());
			piece.setWidth(p.getWidth().doubleValue());
			
			pieces.getPiece().add(piece);
		}
		return pieces;
	}

//	private static XMLGregorianCalendar getXmlDate(Timestamp timestamp) {
//		// TODO Auto-generated method stub
//		String format = "yyyy-MM-dd";
//		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
//		String s = dateFormat.format(timestamp);
//		try {
////			Date d = dateFormat.parse(s);
////			Calendar cal = Calendar.getInstance();
////			cal.setTime(d);
//			 XMLGregorianCalendar xgc = DatatypeFactory.newInstance().newXMLGregorianCalendar(s);
//			 xgc.setTimezone(DatatypeConstants.FIELD_UNDEFINED);
//			 xgc.setMillisecond(DatatypeConstants.FIELD_UNDEFINED);
//			 xgc.setHour(DatatypeConstants.FIELD_UNDEFINED);
//			 xgc.setMinute(DatatypeConstants.FIELD_UNDEFINED);
//			 xgc.setSecond(DatatypeConstants.FIELD_UNDEFINED);
////			cal.setTimeZone(javax.xml.datatype.DatatypeConstants.FIELD_UNDEFINED);
//			return xgc;
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
	
    public static XMLGregorianCalendar getXmlDate(Timestamp timestamp) {
    	try {
	        if (timestamp == null) {
	            return null;
	        } else {
	            GregorianCalendar gc = new GregorianCalendar();
	            gc.setTimeInMillis(timestamp.getTime());
	            return DatatypeFactory.newInstance().newXMLGregorianCalendar(gc);
	        }
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	return null;
    }
	

	public TrackResponse createAndSendTrackRequest(ShippingOrder order) {	

		TrackResponseDocument response_doc = null;

		return response_doc.getTrackResponse();
	}

}
