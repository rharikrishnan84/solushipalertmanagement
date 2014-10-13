package com.meritconinc.shiplinx.carrier.purolator;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.meritconinc.mmr.utilities.MmrBeanLocator;
import com.meritconinc.mmr.utilities.StringUtil;
import com.meritconinc.shiplinx.carrier.CarrierService;
import com.meritconinc.shiplinx.carrier.purolator.stub.EstimatingServiceClient;
import com.meritconinc.shiplinx.carrier.purolator.stub.PickUpServiceClient;
import com.meritconinc.shiplinx.carrier.purolator.stub.ShippingDocumentsServiceClient;
import com.meritconinc.shiplinx.carrier.purolator.stub.ShippingServiceClient;
import com.meritconinc.shiplinx.carrier.purolator.stub.TrackingPackageClient;
import com.meritconinc.shiplinx.carrier.purolator.ws.tracking.proxy.DeliveryScan;
import com.meritconinc.shiplinx.carrier.purolator.ws.tracking.proxy.DeliveryScanDetails;
import com.meritconinc.shiplinx.carrier.purolator.ws.tracking.proxy.GetDeliveryDetailsRequestContainer;
import com.meritconinc.shiplinx.carrier.purolator.ws.tracking.proxy.TrackingInformation;
import com.meritconinc.shiplinx.carrier.utils.PurolatorException;
import com.meritconinc.shiplinx.dao.CarrierServiceDAO;
import com.meritconinc.shiplinx.dao.ShippingDAO;
import com.meritconinc.shiplinx.model.CustomerCarrier;
import com.meritconinc.shiplinx.model.Pickup;
import com.meritconinc.shiplinx.model.Rating;
import com.meritconinc.shiplinx.model.Service;
import com.meritconinc.shiplinx.model.ShippingOrder;
import com.meritconinc.shiplinx.utils.CarrierErrorMessage;
import com.meritconinc.shiplinx.utils.FormattingUtil;
import com.meritconinc.shiplinx.utils.PDFRenderer;
import com.meritconinc.shiplinx.utils.ShiplinxConstants;


public class PurolatorAPI implements CarrierService{

	private static Logger logger = Logger.getLogger(PurolatorAPI.class);

	private CarrierServiceDAO carrierServiceDAO;
	private ShippingDAO shippingDAO; 

	public static final String LIVE_URL_RATE = "https://webservices.purolator.com/PWS/V1/Estimating/EstimatingService.asmx";
	public static final String TEST_URL_RATE = "https://devwebservices.purolator.com/PWS/V1/Estimating/EstimatingService.asmx";
	public static final String LIVE_URL_SHIP = "https://webservices.purolator.com/PWS/V1/Shipping/ShippingService.asmx";
	public static final String TEST_URL_SHIP = "https://devwebservices.purolator.com/PWS/V1/Shipping/ShippingService.asmx";
	public static final String TEST_URL_DOC = "https://devwebservices.purolator.com/PWS/V1/ShippingDocuments/ShippingDocumentsService.asmx";
	public static final String LIVE_URL_DOC = "https://webservices.purolator.com/PWS/V1/ShippingDocuments/ShippingDocumentsService.asmx";
	public static final String TEST_URL_TRACK = "https://devwebservices.purolator.com/PWS/V1/Tracking/TrackingService.asmx";
	public static final String LIVE_URL_TRACK = "https://webservices.purolator.com/PWS/V1/Tracking/TrackingService.asmx";
	public static final String TEST_URL_AVAIL = "https://devwebservices.purolator.com/PWS/V1/ServiceAvailability/ServiceAvailabilityService.asmx";
	public static final String LIVE_URL_AVAIL = "https://webservices.purolator.com/PWS/V1/ServiceAvailability/ServiceAvailabilityService.asmx";
	public static final String LIVE_URL_PICKUP = "https://webservices.purolator.com/EWS/V1/PickUp/PickUpService.asmx";
	public static final String TEST_URL_PICKUP = "https://devwebservices.purolator.com/EWS/V1/PickUp/PickUpService.asmx";
	public static final String TEST_URL_ZIPVALIDATION = "https://devwebservices.purolator.com/PWS/V1/ServiceAvailability/ServiceAvailabilityService.asmx";
	public static final String LIVE_URL_ZIPVALIDATION = "https://webservices.purolator.com/PWS/V1/ServiceAvailability/ServiceAvailabilityService.asmx";
	public static final String Domestic_Bill_of_Lading= "DomesticBillOfLadingThermal";
	public static final String Intl_Bill_of_Lading= "InternationalBillOfLadingThermal";
	public static final String Express_Cheque_Receipt= "ExpressChequeReceiptThermal";
	public static String URL_RATE = "";
	public static String URL_SHIP = "";
	public static String URL_DOC = "";
	public static String URL_TRACK = "";
	public static String URL_AVAIL = "";
	public static String URL_ZIPVALIDATION = "";
	public static String URL_PICKUP = "";


	public static final String EXPRESS_ENVELOPE = "ExpressEnvelope";
	public static final String EXPRESS_PACK = "ExpressPack";
	public static final String EXPRESS_BOX = "ExpressBox";
	public static final String CUSTOMER_PACKAGING = "CustomerPackaging";

	public  static final String FREIGHT_CHARGE_CODE = "FRT";
	public  static final String FUEL_CHARGE_CODE = "FUE";
	public  static final String BEYOND_CHARGE_CODE = "BEY";
	public  static final String MULTIPIECE_CHARGE_CODE = "MULTI";
	public  static final String RESIDENTIAL_CHARGE_CODE = "RESI";
	public  static final String SIGNATURE_CHARGE_CODE = "SIG";
	public  static final String INSURANCE_CHARGE_CODE = "INS";
	public  static final String EXPRESS_CHECK_CHARGE_CODE = "EXPC";
	public  static final String SPECIAL_HANDLING_CODE = "SPCLHNDLG";
	public  static final String OTHER_CHARGE_CODE = "OTH";

	public static double AIR_CUBING_FACTOR = 15.0;
	public static double GROUND_CUBING_FACTOR = 10.0;
	public static final String PURO_WEB_TRANSPORT_MODE_GROUND = "Ground";
	public static final String PURO_WEB_TRANSPORT_MODE_AIR = "Air";

	public CarrierServiceDAO getCarrierServiceDAO() {
		return carrierServiceDAO;
	}
	public void setCarrierServiceDAO(CarrierServiceDAO carrierServiceDAO) {
		this.carrierServiceDAO = carrierServiceDAO;
	}
	public void setShippingDAO(ShippingDAO shippingDAO) {
		this.shippingDAO = shippingDAO;
	}

	public long getCarrierId() {
		return ShiplinxConstants.CARRIER_PUROLATOR;
	}

	public List<Rating> rateShipment(ShippingOrder order, List<Service> services, long carrierId, CustomerCarrier customerCarrier) {

		logger.debug("In Puro rating...");
		long start = System.currentTimeMillis();

//		if(order.getPackageTypeId().getPackageTypeId()==ShiplinxConstants.PACKAGE_TYPE_PALLET)
//			return null;

		if(ShiplinxConstants.isTestMode())
			URL_RATE = TEST_URL_RATE;
		else
			URL_RATE = LIVE_URL_RATE;

		if(! ShiplinxConstants.CANADA.equalsIgnoreCase(order.getFromAddress().getCountryCode()))
			return null;

		//if(ratingException.getErrorMessages()!= null && ratingException.getErrorMessages().size()>0)
		//	throw ratingException;

		List<Rating> rates = new ArrayList<Rating>();
		EstimatingServiceClient estimatingPurolatorServiceClient = new EstimatingServiceClient(order, customerCarrier, carrierServiceDAO);

		try{
			rates = estimatingPurolatorServiceClient.estimatingService();

		}catch (PurolatorException e) {
			logger.error("Error while generating Purolator rates!", e);
			throw e;
		}catch (Exception e) {
			logger.error("Error while generating Purolator rates!", e);
			throw new PurolatorException(e.toString());
		}

		long end = System.currentTimeMillis();
		
		logger.debug("Purolator rating took " + (start-end)/1000 + " seconds..");
		return rates;
	
	}

	@Override
	public boolean cancelOrder(ShippingOrder order,
			CustomerCarrier customerCarrier) {
		// TODO Auto-generated method stub

		if(StringUtil.isEmpty(order.getMasterTrackingNum()))
			return true; //if no tracking # was generated, we can safely treat this shipment as cancelled

		ShippingServiceClient shippingServiceClient = new ShippingServiceClient(order, customerCarrier);
		shippingServiceClient.voidShipment();
		return true;
	}

	@Override
	public boolean cancelPickup(Pickup pickup) {
		// TODO Auto-generated method stub
		PickUpServiceClient pickupServiceClient = new PickUpServiceClient();
		pickupServiceClient.voidPickup(pickup);
		return true;
	}

	@Override
	public void generateShippingLabel(OutputStream outputStream, long orderId, CustomerCarrier customerCarrier) {
		logger.debug("-----generateShippingLabel 22--implement here -");

		logger.debug("Attempting to retrieve shipping label for order with id " + orderId);
		ShippingOrder order = shippingDAO.getShippingOrder(orderId);

		ShippingDocumentsServiceClient shippingDocumentsServiceClient = new ShippingDocumentsServiceClient(order, customerCarrier);
		ArrayList<byte[]> raw_labels = shippingDocumentsServiceClient.shippingDocumentsService(order);

		long start = System.currentTimeMillis();

		try{

			int page = 1;
			PDFRenderer pdfRenderer = new PDFRenderer();
			ArrayList<String> srcList = new ArrayList<String>();
			for(byte[] label:raw_labels) {

				String pdfFileName = pdfRenderer.getUniqueTempPDFFileName("label" + order.getId() + page);
				File f = new File(pdfFileName);
				BufferedOutputStream sbos = new BufferedOutputStream(new FileOutputStream(f));
				sbos.write(label);
				sbos.close();
				srcList.add(pdfFileName);
				page++;
			}
			// delete temp files
			pdfRenderer.concatPDF(srcList, outputStream);
			pdfRenderer.deleteFiles(srcList);
		}catch (Exception e) {
			logger.error("Exception" + e);
		}

		long end = System.currentTimeMillis();
		logger.debug("Time (in ms) to generate shipping label: " + (end-start));
		
	}

	@Override
	public List<CarrierErrorMessage> getErrorMessages() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getShippingOrderStatus(ShippingOrder order) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getTrackingURL(ShippingOrder o) {
		// TODO Auto-generated method stub
		StringBuffer tracking_numbers = new StringBuffer();
		List<com.meritconinc.shiplinx.model.Package> packages = o.getPackages();

		for(com.meritconinc.shiplinx.model.Package p:packages){
			tracking_numbers.append(p.getTrackingNumber());
			tracking_numbers.append(",");
		}
		String url = "http://shipnow.purolator.com/shiponline/track/purolatortrack.asp?pinno=" + tracking_numbers;
		return url;
	}

	@Override
	public void requestPickup(Pickup pickup) {

		PickUpServiceClient pickup_request = new PickUpServiceClient();
		pickup_request.schedulePickup(pickup);
		
	}

	@Override
	public void shipOrder(ShippingOrder order, Rating rateInfo,
			CustomerCarrier customerCarrier) {
		logger.debug("------shipOrder from Web service.---");
		if(!validateScheduledShipDate(order.getScheduledShipDate()))
		{
			logger.info("Purolator allows creation of future dated shipments upto 10 calendar days.Scheduled ship date must be less than 11 calendar days from today.");
			throw new PurolatorException("Purolator allows creation of future dated shipments upto 10 calendar days.Scheduled ship date must be less than 11 calendar days from today.");
		}
		long start = System.currentTimeMillis();

		com.meritconinc.shiplinx.model.Service shiplinxService = carrierServiceDAO.getService(rateInfo.getServiceId());
		//get the cubed weight
		double cubingFactor;
		if(shiplinxService.getMode()==ShiplinxConstants.MODE_TRANSPORT_AIR_VALUE && (!(order.getFromAddress().getProvinceCode().equalsIgnoreCase("ON")&& order.getToAddress().getProvinceCode().equalsIgnoreCase("ON")&& shiplinxService.getId()==2002)))
			cubingFactor = AIR_CUBING_FACTOR;
		else
			cubingFactor = GROUND_CUBING_FACTOR;

		List<com.meritconinc.shiplinx.model.Package> packages = order.getPackages();
		for(com.meritconinc.shiplinx.model.Package p:packages){
			float actualWeight = p.getWeight().floatValue();
			float cubedWeight = getCubedWeight(p.getLength().floatValue(), p.getHeight().floatValue(), p.getWidth().floatValue(), cubingFactor);
			
			//determine which is greater, actual weight or cubed weight and set the cubed weight of the package accordingly
			if(actualWeight>=cubedWeight)
				p.setBilledWeight(actualWeight);
			else
				p.setBilledWeight(cubedWeight);
		}

		try{
			ShippingServiceClient shippingServiceClient = new ShippingServiceClient(order, customerCarrier);
			shippingServiceClient.shippingService();
		}catch (Exception e) {
			e.printStackTrace();
			throw new PurolatorException(e.getMessage());
		}

//		//Schedule Pick-up
//		PickUpServiceClient pickupServiceClient = new PickUpServiceClient();
//		ScheduledPickup scheduledPickup = order.getSchedPickup();
//		if(scheduledPickup !=null && scheduledPickup.getCarrierId() == EShipperConstants.CARRIER_PUROLATOR) {
//			logger.debug("Scheduling the pick up request");
//			pickupServiceClient.schedulePickup(order);
//		}
//		else{
//			logger.debug("No pick up request entered!");
//		}
//
		long end = System.currentTimeMillis();
		logger.debug("Time (in ms) to assign tracking numbers: " + (end-start));
		
	}
	
	
	private boolean validateScheduledShipDate(Date scheduledShipDate)
	{
		boolean boolReturn=false;
		long futureDate=777600000;
		try {
			long longScheduledShipDate = FormattingUtil.DATE_FORMAT_WEB.parse(FormattingUtil.DATE_FORMAT_WEB.format(scheduledShipDate)).getTime();
			// Adding the 9 days in Current date so the customer can create the shipment upto next 10 days
			long longCurrentDate=System.currentTimeMillis()+futureDate;
			logger.debug("longScheduledShipDate= "+longScheduledShipDate);
			logger.debug("longCurrentDate= "+longCurrentDate);
			long diff=longCurrentDate-longScheduledShipDate;
			logger.debug("diff= "+diff);
			if(diff >=0)
			{
				boolReturn=true;
			}else
			{
				boolReturn=false;
			}
		} catch (Exception e) {
			boolReturn=false;
		}
		return boolReturn;
	}
	
	public static float getCubedWeight(float length, float height, float width, double cubingFactor){

		double val = Math.ceil((length*height*width*cubingFactor)/1728);
		Double weight = new Double(val);
		return weight.floatValue();
	}

	public static boolean isUSShipment(ShippingOrder order){
		if(order.getFromAddress().getCountryCode().equalsIgnoreCase(ShiplinxConstants.US) || order.getToAddress().getCountryCode().equalsIgnoreCase(ShiplinxConstants.US))
			return true;
		return false;
	}
	public static boolean isDomesticShipment(ShippingOrder order){
		if(order.getFromAddress().getCountryCode().equalsIgnoreCase(ShiplinxConstants.CANADA) && order.getToAddress().getCountryCode().equalsIgnoreCase(ShiplinxConstants.CANADA))
			return true;
		return false;
	}
	
	@Override
	public void uploadRates(Service service, long customerId, long busId, boolean isOverwrite) {
		// TODO Auto-generated method stub
		
	}

	
	public void trackShipmentByPins(List<String> pins){
				try{
				TrackingPackageClient trackingPackageClient=new TrackingPackageClient();
				trackingPackageClient.getdeliveryDetails(pins);
				}catch(Exception e){
				e.printStackTrace();
				}
			}

}