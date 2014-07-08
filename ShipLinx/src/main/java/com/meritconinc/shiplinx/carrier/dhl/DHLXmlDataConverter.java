package com.meritconinc.shiplinx.carrier.dhl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.UUID;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.log4j.Logger;

import com.meritconinc.mmr.utilities.StringUtil;
import com.meritconinc.shiplinx.carrier.dhl.dao.DHLCanadaTariffDAO;
import com.meritconinc.shiplinx.carrier.dhl.model.DHLZone;
import com.meritconinc.shiplinx.carrier.dhl.xml.Billing;
import com.meritconinc.shiplinx.carrier.dhl.xml.BkgDetailsType;
import com.meritconinc.shiplinx.carrier.dhl.xml.BookPickupRequest;
import com.meritconinc.shiplinx.carrier.dhl.xml.CancelPickupRequest;
import com.meritconinc.shiplinx.carrier.dhl.xml.Condition;
import com.meritconinc.shiplinx.carrier.dhl.xml.Consignee;
import com.meritconinc.shiplinx.carrier.dhl.xml.Contact;
import com.meritconinc.shiplinx.carrier.dhl.xml.ContactPickup;
import com.meritconinc.shiplinx.carrier.dhl.xml.CustomerRateBilling;
import com.meritconinc.shiplinx.carrier.dhl.xml.DCTDutiable;
import com.meritconinc.shiplinx.carrier.dhl.xml.DCTFrom;
import com.meritconinc.shiplinx.carrier.dhl.xml.DCTRequest;
import com.meritconinc.shiplinx.carrier.dhl.xml.DCTRequest.GetCapability;
import com.meritconinc.shiplinx.carrier.dhl.xml.DCTRequest.GetQuote;
import com.meritconinc.shiplinx.carrier.dhl.xml.DCTTo;
import com.meritconinc.shiplinx.carrier.dhl.xml.DimensionUnit;
import com.meritconinc.shiplinx.carrier.dhl.xml.Dutiable;
import com.meritconinc.shiplinx.carrier.dhl.xml.DutyTaxPaymentType;
import com.meritconinc.shiplinx.carrier.dhl.xml.PaymentType;
import com.meritconinc.shiplinx.carrier.dhl.xml.Pickup;
import com.meritconinc.shiplinx.carrier.dhl.xml.Piece;
import com.meritconinc.shiplinx.carrier.dhl.xml.PieceType;
import com.meritconinc.shiplinx.carrier.dhl.xml.Pieces;
import com.meritconinc.shiplinx.carrier.dhl.xml.PiecesEnabled;
import com.meritconinc.shiplinx.carrier.dhl.xml.Place;
import com.meritconinc.shiplinx.carrier.dhl.xml.PlacePickup;
import com.meritconinc.shiplinx.carrier.dhl.xml.QtdShpType;
import com.meritconinc.shiplinx.carrier.dhl.xml.QtdShpTypeDCTRes;
import com.meritconinc.shiplinx.carrier.dhl.xml.RatingConsignee;
import com.meritconinc.shiplinx.carrier.dhl.xml.RatingShipmentDetails;
import com.meritconinc.shiplinx.carrier.dhl.xml.RatingShipper;
import com.meritconinc.shiplinx.carrier.dhl.xml.RatingSpecialService;
import com.meritconinc.shiplinx.carrier.dhl.xml.Reference;
import com.meritconinc.shiplinx.carrier.dhl.xml.Request;
import com.meritconinc.shiplinx.carrier.dhl.xml.Requestor;
import com.meritconinc.shiplinx.carrier.dhl.xml.ServiceHeader;
import com.meritconinc.shiplinx.carrier.dhl.xml.ShipmentCustRatingRequest;
import com.meritconinc.shiplinx.carrier.dhl.xml.ShipmentDetails;
import com.meritconinc.shiplinx.carrier.dhl.xml.ShipmentValidateRequest;
import com.meritconinc.shiplinx.carrier.dhl.xml.Shipper;
import com.meritconinc.shiplinx.carrier.dhl.xml.WeightSeg;
import com.meritconinc.shiplinx.carrier.dhl.xml.WeightUnit;
import com.meritconinc.shiplinx.carrier.dhl.xml.YesNo;
import com.meritconinc.shiplinx.carrier.dhl.xml.shipvalidateglobal.request.LabelImageFormat;
import com.meritconinc.shiplinx.carrier.dhl.xml.shipvalidateglobal.request.RegionCode;
import com.meritconinc.shiplinx.carrier.dhl.xml.shipvalidateglobal.request.ShipmentRequest;
import com.meritconinc.shiplinx.carrier.dhl.xml.shipvalidateglobal.request.SpecialService;
import com.meritconinc.shiplinx.carrier.midland.MidlandAPI;
import com.meritconinc.shiplinx.model.Address;
import com.meritconinc.shiplinx.model.CustomerCarrier;
import com.meritconinc.shiplinx.model.Package;
import com.meritconinc.shiplinx.model.PackageType;
import com.meritconinc.shiplinx.model.Rating;
import com.meritconinc.shiplinx.model.ShippingOrder;
import com.meritconinc.shiplinx.utils.ShiplinxConstants;

public class DHLXmlDataConverter {
	private static final Logger logger = Logger.getLogger(DHLXmlDataConverter.class);
//	public static final String YES = "Y";
//	public static final String NO = "N";
	public static final String LANG_ENG = "EN";	//	English American
//	private static final String SHIPPER = "S";	// Shipper
	
	//globalproductcode, productname, productcontentcode, serviceId, packageTypeId, docs/non-docs indicator
	private static final String [][] PRODUCT_CODES  =	{
													{"E", "EXPRESS 9:00",		"TDE", "300", "3", "N"},
													{"E", "EXPRESS 9:00",		"TDE", "307", "3", "N"},
													{"H", "ECONOMY SELECT",		"ESI", "305", "3", "N"},
													{"M", "EXPRESS 10:30",		"TDM", "303", "3", "N"},
													{"P", "EXPRESS WORLDWIDE",	"WPX", "301", "3", "N"},
													{"P", "EXPRESS WORLDWIDE",	"WPX", "306", "3", "N"},
													{"D", "EXPRESS ENVELOPE",	"DOX", "301", "1", "Y"},
													{"D", "EXPRESS ENVELOPE",	"DOX", "306", "1", "Y"},
													{"Y", "EXPRESS 12:00",		"TDY", "304", "3", "N"},
													{"Y", "EXPRESS 12:00",		"TDY", "308", "3", "N"},
													{"D", "EXPRESS WORLDWIDE",	"DOX", "301", "3", "Y"},
													{"D", "EXPRESS WORLDWIDE",	"DOX", "306", "3", "Y"},
													{"K", "EXPRESS 9:00",		"TDK", "300", "3", "Y"},
													{"K", "EXPRESS 9:00",		"TDK", "307", "3", "Y"},
													{"L", "EXPRESS 10:30",		"TDL", "303", "3", "Y"},
													{"T", "EXPRESS 12:00",		"TDT", "304", "3", "Y"},
													{"T", "EXPRESS 12:00",		"TDT", "308", "3", "Y"},
													{"W", "ECONOMY SELECT",		"ESU", "305", "3", "Y"}
													
		 											};
	
//	private static final String DHL_PKG_TYPE_ENV = "EE"; 	// EE (DHL Express Envelope)
															// OD (Other DHL Packaging)
//	private static final String DHL_PKG_TYPE_OTH = "CP";	// CP (Customer-provided packaging)
	private static final String ENVELOPE = "env";	// package_type table "env"
	private static final String INCH = "IN";			// IN (Inches)
	private static final String CENTIMETER = "CM";		// CM (Centimetres)
	protected static final String KG = "KG";				// KG (Kilograms)
	private static final String LB = "LB";				// LB (Pounds)

	private static final String DOCS_ONLY_INDICATOR = "Y";
	private static final String NON_DOCS_ONLY_INDICATOR = "N";


	@SuppressWarnings("unchecked")
	public static ShipmentValidateRequest populateShipmentValidateRequest(ShippingOrder order,	Rating rateInfo, DHLCanadaTariffDAO tariffDAO, CustomerCarrier customerCarrier) {
		// TODO Auto-generated method stub
		ShipmentValidateRequest req = new ShipmentValidateRequest();
		req.setRequest(DHLXmlDataConverter.populateRequest(order, customerCarrier));
		req.setRequestedPickupTime(YesNo.Y);
		req.setNewShipper(YesNo.N);
		req.setLanguageCode(DHLXmlDataConverter.LANG_ENG);
		req.setPiecesEnabled(PiecesEnabled.Y);
		req.setBilling(DHLXmlDataConverter.populateBilling(order, customerCarrier));
		req.setConsignee(DHLXmlDataConverter.populateConsignee(order,tariffDAO));
		
		if ( !StringUtil.isEmpty(order.getReferenceCode()) )
			req.getReference().add(populateReference(order.getReferenceCode()));
		if ( !StringUtil.isEmpty(order.getReferenceOne()) )
			req.getReference().add(populateReference(order.getReferenceOne()));
		if ( !StringUtil.isEmpty(order.getReferenceTwo()) )
			req.getReference().add(populateReference(order.getReferenceTwo()));
		
		req.setShipmentDetails(DHLXmlDataConverter.populateShipmentDetails(order, rateInfo, tariffDAO));
		req.setShipper(DHLXmlDataConverter.populateShipper(order, tariffDAO, customerCarrier));
		req.setPlace(DHLXmlDataConverter.populatePlace(order.getFromAddress()));
		
		req.setDutiable(DHLXmlDataConverter.populateDutiable(order, customerCarrier));
		
		return req;
	}
	
	private static Reference populateReference(String reference) {
		// TODO Auto-generated method stub
		Reference ref = new Reference();
		ref.setReferenceID(reference);
//		ref.setReferenceType(reference);
		
		return ref;
	}

	public static Request populateRequest(ShippingOrder order, CustomerCarrier customerCarrier) {
		// TODO Auto-generated method stub
		Request req = new Request();
		req.setServiceHeader(DHLXmlDataConverter.populateServiceHeader(order, customerCarrier));
		return req;
	}

	private static ServiceHeader populateServiceHeader(ShippingOrder order, CustomerCarrier customerCarrier) {
		// TODO Auto-generated method stub
		ServiceHeader header = new ServiceHeader();
		
		header.setMessageTime(getXmlGCalendar());
		header.setMessageReference(getUniqueID());
		header.setSiteID(customerCarrier.getProperty1());
		header.setPassword(customerCarrier.getProperty2());
		return header;
	}

    public static XMLGregorianCalendar getXmlGCalendar() {
    	try {
    	    return DatatypeFactory.newInstance().newXMLGregorianCalendar(new GregorianCalendar());
    	} catch (DatatypeConfigurationException e) {
    	    throw new Error(e);
    	}
    }
    
    public static XMLGregorianCalendar getXmlGCalendar(Timestamp time) {
    	try {
            GregorianCalendar gc = new GregorianCalendar();
            gc.setTimeInMillis(time.getTime());
            return DatatypeFactory.newInstance().newXMLGregorianCalendar(gc);
    	} catch (DatatypeConfigurationException e) {
    	    throw new Error(e);
    	}
    }    
        

	private static String getUniqueID() {
		// TODO Auto-generated method stub
		String id = UUID.randomUUID().toString();
		id = id.replace("-", "");
		return id;
	}

	public static Billing populateBilling(ShippingOrder order, CustomerCarrier customerCarrier) {
		// TODO Auto-generated method stub
		if (customerCarrier != null) {
			Billing billing = new Billing();
			int shipmentType = DHLAPI.getShipmentType(customerCarrier.getCountry(), order.getFromAddress().getCountryCode(), order.getToAddress().getCountryCode());
			long accountNumber = 0;
			if(shipmentType==ShiplinxConstants.SHIPMENT_TYPE_EXPORT)
				accountNumber = Long.parseLong(customerCarrier.getAccountNumber1());
			else if(shipmentType==ShiplinxConstants.SHIPMENT_TYPE_IMPORT)
				accountNumber = Long.parseLong(customerCarrier.getAccountNumber2());
			else if(shipmentType==ShiplinxConstants.SHIPMENT_TYPE_THIRD_COUNTRY)			
				accountNumber = Long.parseLong(customerCarrier.getProperty5());
			
			billing.setBillingAccountNumber(accountNumber);
			billing.setShipperAccountNumber(accountNumber);
			billing.setShippingPaymentType(PaymentType.S);
			billing.setDutyPaymentType(DutyTaxPaymentType.R);
			return billing;
		}
		
		return null;
	}

	public static Dutiable populateDutiable(ShippingOrder order, CustomerCarrier customerCarrier) {
		// TODO Auto-generated method stub
		if (order.getPackageTypeId().getPackageTypeId() == ShiplinxConstants.PACKAGE_TYPE_PACKAGE || order.getPackageTypeId().getPackageTypeId() == ShiplinxConstants.PACKAGE_TYPE_PALLET) {
			Dutiable dutiable = new Dutiable();
			//Having trouble with DHL API, they want the dutiable amount in format x.xx but if amount is 12.00 the float conversion changes it to 12.0
			//Work around for now is to add $0.01 to the amount to ensure it stays as 2 decimal places
			BigDecimal declaredValue = new BigDecimal(order.getDutiableAmount().doubleValue() + 0.01).setScale(2, RoundingMode.HALF_EVEN);
			dutiable.setDeclaredValue(declaredValue.floatValue());
			if(customerCarrier.getCountry().equalsIgnoreCase(ShiplinxConstants.CANADA))
				dutiable.setDeclaredCurrency("CAD");
			if(customerCarrier.getCountry().equalsIgnoreCase(ShiplinxConstants.US))
				dutiable.setDeclaredCurrency("USD");
			
			return dutiable;
		}
		
		return null;
	}

	@SuppressWarnings("unchecked")
	public static Consignee populateConsignee(ShippingOrder order, DHLCanadaTariffDAO tariffDAO) {
		// TODO Auto-generated method stub
		Consignee consignee = new Consignee();
		consignee.setCompanyName(StringUtil.setWithMaxLength(order.getToAddress().getAbbreviationName(), 35));
		consignee.getAddressLine().add(StringUtil.setWithMaxLength(order.getToAddress().getAddress1(), 35));
		if ( !StringUtil.isEmpty(order.getToAddress().getAddress2()) )
			consignee.getAddressLine().add(StringUtil.setWithMaxLength(order.getToAddress().getAddress2(), 35));
		
		consignee.setCity(order.getToAddress().getCity());
		consignee.setPostalCode(order.getToAddress().getPostalCode());
		consignee.setCountryCode(order.getToAddress().getCountryCode());
		// MyToDo - Fix Country Name
		DHLZone zone = new DHLZone(consignee.getCountryCode());
		zone = tariffDAO.getZone(zone);
		consignee.setCountryName(zone.getCountryName());
		consignee.setDivisionCode(order.getToAddress().getProvinceCode());
		
		Contact c = populateContact(order.getToAddress());
		if (c != null)
			consignee.setContact(c);
		
		return consignee;
	}


	private static Contact populateContact(Address addr) {
		// TODO Auto-generated method stub
		if (addr != null && addr.getContactName() != null && addr.getPhoneNo() != null) {
			Contact c = new Contact();
			c.setPersonName(StringUtil.setWithMaxLength(addr.getContactName(), 35));
			c.setPhoneNumber(addr.getPhoneNo());
			return c;
		}
		return null;
	}

	public static ShipmentDetails populateShipmentDetails(ShippingOrder order, Rating rateInfo, 
				DHLCanadaTariffDAO canadaTariffDAO) {
		// TODO Auto-generated method stub
		ShipmentDetails s = new ShipmentDetails();
		s.setNumberOfPieces(new BigInteger(order.getQuantity().toString()));
		Pieces pieces = populatePieces(order);
		if (pieces != null)
			s.setPieces(populatePieces(order));
		
		BigDecimal weight = null;
		if(order.getPackageTypeId().getPackageTypeId() == ShiplinxConstants.PACKAGE_TYPE_ENVELOPE)
			weight = new BigDecimal(0.5).setScale(1, RoundingMode.HALF_EVEN);
		else
			weight = new BigDecimal(rateInfo.getBillWeight()).setScale(1, RoundingMode.HALF_EVEN);
		s.setWeight(weight);
		DHLZone zone = new DHLZone();
		zone.setCountryCode(order.getFromAddress().getCountryCode());
		zone = canadaTariffDAO.getZone(zone);
//		if (zone != null && zone.getWeightUnit() != null)
			s.setWeightUnit(WeightUnit.L);
		
		int prodTabIndex = getProductCodes(order.getDocsOnly(), rateInfo, order.getPackageTypeId().getPackageTypeId());
		s.setGlobalProductCode(PRODUCT_CODES[prodTabIndex][0]);
		s.setLocalProductCode(PRODUCT_CODES[prodTabIndex][0]); //s.setLocalProductCode(PRODUCT_CODES[prodTabIndex][2]);
//		XMLGregorianCalendar cal = getDhlDate(order.getScheduledShipDate());
		Calendar cal = Calendar.getInstance();
		cal.setTime(order.getScheduledShipDate());
//		s.setDate(getXmlGCalendar(order.getScheduledShipDate()));
		s.setDate(getDhlDate(order.getScheduledShipDate()));
		if (zone != null)
			s.setDimensionUnit(DimensionUnit.C);
		s.setPackageType(getDHLPackageType(order.getPackageTypeId()));
		s.setCurrencyCode(ShiplinxConstants.CURRENCY_CA_STRING);
		s.setContents("Various");
		
		if(order.getInsuranceValue() > 0)
			s.setInsuredAmount(order.getInsuranceValue());
		return s;
	}

	private static XMLGregorianCalendar getDhlDate(Timestamp timestamp) {
		// TODO Auto-generated method stub
		String format = "yyyy-MM-dd";
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		String s = dateFormat.format(timestamp);
		try {
//			Date d = dateFormat.parse(s);
//			Calendar cal = Calendar.getInstance();
//			cal.setTime(d);
			 XMLGregorianCalendar xgc = DatatypeFactory.newInstance().newXMLGregorianCalendar(s);
			 xgc.setTimezone(DatatypeConstants.FIELD_UNDEFINED);
			 xgc.setMillisecond(DatatypeConstants.FIELD_UNDEFINED);
			 xgc.setHour(DatatypeConstants.FIELD_UNDEFINED);
			 xgc.setMinute(DatatypeConstants.FIELD_UNDEFINED);
			 xgc.setSecond(DatatypeConstants.FIELD_UNDEFINED);
//			cal.setTimeZone(javax.xml.datatype.DatatypeConstants.FIELD_UNDEFINED);
			return xgc;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
//	private static Calendar getDhlDate(Timestamp timestamp) {
//		// TODO Auto-generated method stub
//		String format = "yyyy-MM-dd";
//		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
//		String s = dateFormat.format(timestamp);
//		try {
//			Date d = dateFormat.parse(s);
//			Calendar cal = Calendar.getInstance();
//			cal.setTime(d);
//			 XMLGregorianCalendar xgc = DatatypeFactory.newInstance().newXMLGregorianCalendar(s);
//			 xgc.setTimezone(DatatypeConstants.FIELD_UNDEFINED);
////			cal.setTimeZone(javax.xml.datatype.DatatypeConstants.FIELD_UNDEFINED);
//			return cal;
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return null;
//	}	

	@SuppressWarnings("unchecked")
	private static Pieces populatePieces(ShippingOrder order) {
		// TODO Auto-generated method stub
		if (order.getPackages() != null && order.getPackages().size() > 0) {
			Pieces pieces = new Pieces();
			int n=0;
			for (com.meritconinc.shiplinx.model.Package p:order.getPackages()) {
				pieces.getPiece().add(populatePiece(p, ++n, order.getPackageTypeId().getPackageTypeId()));
			}
			return pieces;
		}
		return null;
	}

	private static Piece populatePiece(Package pkg, int n, long pType) {
		// TODO Auto-generated method stub
		Piece p = new Piece();
		p.setPieceID(Integer.toString(n));
		if (pkg.getWeight() != null) {
			BigDecimal weight = null;
			if(pType == ShiplinxConstants.PACKAGE_TYPE_ENVELOPE){
				weight = new BigDecimal(0.5).setScale(1, RoundingMode.HALF_EVEN);
				p.setWeight(weight);
				return p;
			}
			else
				weight = pkg.getWeight().setScale(1, RoundingMode.HALF_EVEN);
			p.setWeight(weight);
//			p.setDimWeight(weight);
		}
		if (pkg.getHeight() != null)
			p.setHeight(new BigInteger(String.valueOf(pkg.getHeight().intValue())));
		if (pkg.getLength() != null)
			p.setDepth(new BigInteger(String.valueOf(pkg.getLength().intValue())));
		if (pkg.getWidth() != null)
			p.setWidth(new BigInteger(String.valueOf(pkg.getWidth().intValue())));
		
		
		// MyToDo: Need to confirm with Rizwan
//		p.setPackageType(com.meritconinc.shiplinx.carrier.dhl.xml.PackageType.EE);
		p.setPackageType(com.meritconinc.shiplinx.carrier.dhl.xml.PackageType.CP);
		
		return p;
	}

	private static com.meritconinc.shiplinx.carrier.dhl.xml.PackageType getDHLPackageType(PackageType pType) {
		if (pType != null && pType.getName() != null)
			if (pType.getName().indexOf(ENVELOPE) >= 0)
				return com.meritconinc.shiplinx.carrier.dhl.xml.PackageType.EE;
		return com.meritconinc.shiplinx.carrier.dhl.xml.PackageType.CP;
	}

	private static int getProductCodes(Boolean docsOnly, Rating rateInfo, Long packageTypeId) {
		String docs_indicator = null;;
		if(docsOnly || packageTypeId==ShiplinxConstants.PACKAGE_TYPE_ENVELOPE)
			docs_indicator = DOCS_ONLY_INDICATOR;
		else
			docs_indicator = NON_DOCS_ONLY_INDICATOR;
		
		long serviceId = rateInfo.getServiceId();
		if(rateInfo.getOriginalServiceId() > 0)
			serviceId = rateInfo.getOriginalServiceId(); //Used in DHL to remember the "real" service. See rateShipment function, line 122
		
		//if pak or pallet is being requested, then treat it like a 'package' to determine the DHL service
		if(packageTypeId == ShiplinxConstants.PACKAGE_TYPE_PAK || packageTypeId == ShiplinxConstants.PACKAGE_TYPE_PALLET)
			packageTypeId = (long)ShiplinxConstants.PACKAGE_TYPE_PACKAGE;
		
		if (rateInfo != null && rateInfo.getServiceName() != null) {
			for (int i=0; i<PRODUCT_CODES.length; i++){				
					if (serviceId == Long.parseLong(PRODUCT_CODES[i][3]) && packageTypeId==Long.parseLong(PRODUCT_CODES[i][4]) && PRODUCT_CODES[i][5].equalsIgnoreCase(docs_indicator))
						return i;
				}
		}
		return 0;
	}

	@SuppressWarnings("unchecked")
	public static Shipper populateShipper(ShippingOrder order, DHLCanadaTariffDAO tariffDAO, CustomerCarrier customerCarrier) {
		// TODO Auto-generated method stub
		Shipper s = new Shipper();
		
		int shipmentType = DHLAPI.getShipmentType(customerCarrier.getCountry(), order.getFromAddress().getCountryCode(), order.getToAddress().getCountryCode());
		if(shipmentType==ShiplinxConstants.SHIPMENT_TYPE_EXPORT)			
			s.setShipperID(customerCarrier.getAccountNumber1());
		else if(shipmentType==ShiplinxConstants.SHIPMENT_TYPE_IMPORT)
			s.setShipperID(customerCarrier.getAccountNumber2());
		else if(shipmentType==ShiplinxConstants.SHIPMENT_TYPE_THIRD_COUNTRY)
			s.setShipperID(customerCarrier.getProperty5());
		
		// MyToDo: No idea about RegisteredAccount, it's in XSD but not in PDF document
		// Setting it Account Number
		s.setRegisteredAccount(Long.parseLong(s.getShipperID()));
		
		s.setCompanyName(StringUtil.setWithMaxLength(order.getFromAddress().getAbbreviationName(), 35));
		s.getAddressLine().add(StringUtil.setWithMaxLength(order.getFromAddress().getAddress1(), 35));
		if ( !StringUtil.isEmpty(order.getFromAddress().getAddress2()) )
			s.getAddressLine().add(StringUtil.setWithMaxLength(order.getFromAddress().getAddress2(), 35));
		
		s.setCity(order.getFromAddress().getCity());
		s.setPostalCode(order.getFromAddress().getPostalCode());
		s.setCountryCode(order.getFromAddress().getCountryCode());
		
		// MyToDo - RegisteredAccount, Division, CountryName are mandatory
		DHLZone zone = new DHLZone(s.getCountryCode());
		zone = tariffDAO.getZone(zone);
		s.setCountryName(zone.getCountryName());
		s.setDivisionCode(order.getFromAddress().getProvinceCode());
		s.setDivision(order.getFromAddress().getProvinceCode());
		
		Contact c = populateContact(order.getToAddress());
		if (c != null)
			s.setContact(c);		
		return s;
	}

	@SuppressWarnings("unchecked")
	public static Place populatePlace(Address addr) {
		// TODO Auto-generated method stub
		Place p = new Place();
		p.setCompanyName(StringUtil.setWithMaxLength(addr.getAbbreviationName(), 35));
		p.getAddressLine().add(StringUtil.setWithMaxLength(addr.getAddress1(), 35));
		if ( !StringUtil.isEmpty(addr.getAddress2()) )
			p.getAddressLine().add(StringUtil.setWithMaxLength(addr.getAddress2(), 35));
		
		p.setCity(addr.getCity());
		p.setPostalCode(addr.getPostalCode());
		p.setCountryCode(addr.getCountryCode());
		p.setDivisionCode(addr.getProvinceCode());
		p.setDivision(addr.getProvinceCode());
		
		return p;
	}

//	public static String customizeRequest(String req) {
		// TODO Auto-generated method stub
		// xsd:date gets converted into Gregorian Calender after jaxb xsd conversion
		// After marshalling it adds GMT with date, DHL does not like it
		// after Discussion with Rizwan - 28 Jan. 2011, we have decided to strip it out from xml string
//		req = req.replace("-05:00</Date>", "</Date>");
//		req = req.replace(".0</DeclaredValue>", ".00</DeclaredValue>");
//		return req;
//	}


//	public static String customizeResponse(String res) {
//		// TODO Auto-generated method stub
//		res = res.replace("</ServiceHeader>", "<Password>qplMNV65as</Password>\n</ServiceHeader>");
//
//		//		res = removeXmlTag(res, "<DHLRoutingCode>", "</DHLRoutingCode>" ); // <DHLRoutingCode>US12345+45001001</DHLRoutingCode>
////		res = removeXmlTag(res, "<DHLRoutingDataId>", "</DHLRoutingDataId>" ); // <DHLRoutingDataId>2L</DHLRoutingDataId>
////		res = removeXmlTag(res, "<ProductContentCode>", "</ProductContentCode>" ); // <ProductContentCode>TDE</ProductContentCode>
////		res = removeXmlTag(res, "<ProductShortName>", "</ProductShortName>" ); //<ProductShortName>EXPRESS 9:00</ProductShortName>
////		res = removeXmlTag(res, "<InternalServiceCode>", "</InternalServiceCode>" ); //<InternalServiceCode>C</InternalServiceCode>		
////		res = res.replace("<DeliveryDateCode/>", "");
////		res = removeXmlTag(res, "<DeliveryTimeCode>", "</DeliveryTimeCode>" ); //<DeliveryTimeCode>X09</DeliveryTimeCode>
//
////		res = moveXmlTag(res, "<Billing>", "</Billing>", "</res:ShipmentValidateResponse>");
//		
////		res = removeXmlTag(res, "<DHLRoutingCode>", "</Pieces>" );
//		return res;
//	}

//	private static String moveXmlTag(String res, String sTag, String eTag, String destTag) {
//		// TODO Auto-generated method stub
//		int n1 = res.indexOf(sTag);
//		int n2 = res.indexOf(eTag, n1 + sTag.length());
//		String s = res.substring(n1, n2+eTag.length());
////		int n3 = res.indexOf(destTag);
//		res = res.replace(s, "");
//		s = "\n" +s + "\n" + destTag;
//		res = res.replace(destTag, s);
//		return res;
//	}
//
//	private static String removeXmlTag(String res, String sTag, String eTag) {
//		// TODO Auto-generated method stub
//		int n1 = res.indexOf(sTag);
//		int n2 = res.indexOf(eTag, n1 + sTag.length());
//		String s = res.substring(n1, n2+eTag.length());
//		res = res.replace(s, "");
//		return res;
//	}
//
//	public static Calendar parseTime(String value) throws Exception {
//		// TODO Auto-generated method stub
//		myDebug("Default parser is unable to parse time:" + value);
//		if (!StringUtil.isEmpty(value) && value.length() == 5 && value.contains(":")) {
//			try {
//				// Time in hours and minutes (HH:MM)
//				int h = Integer.parseInt(value.substring(0, 2));
//				int m = Integer.parseInt(value.substring(3, 5));
//				Calendar cal = Calendar.getInstance();
//				cal.set(Calendar.HOUR_OF_DAY, h);
//				cal.set(Calendar.MINUTE, m);
//				return cal;
//			} catch (Exception e) {
//				e.printStackTrace();
//				throw e;
//			}
//			
//		}
//		return null;
//	}

	public static void myDebug(String msg) {
		// TODO Auto-generated method stub
		System.out.println(msg);
	}

	public static String statusConditionToString(List<Condition> condition) {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		if (condition != null) {
			for(int i=0; i<condition.size(); i++) {
				Condition c = (Condition) condition.get(i);
				sb.append("[Codition Code:" + c.getConditionCode() );
				sb.append("][Data:" + c.getConditionData() + "]");
			}
		}
		return sb.toString();
	}

	public static ShipmentCustRatingRequest populateShipmentCustRatingRequest(ShippingOrder order, CustomerCarrier customerCarrier) {
		// TODO Auto-generated method stub
		ShipmentCustRatingRequest rateReq = new ShipmentCustRatingRequest();
		rateReq.setRequest(DHLXmlDataConverter.populateRequest(order, customerCarrier));
		rateReq.setBilling(DHLXmlDataConverter.populateCustomerRateBilling(order, customerCarrier));
		rateReq.setConsignee(DHLXmlDataConverter.populateRatingConsignee(order));
		rateReq.setShipmentDetails(DHLXmlDataConverter.populateRatingShipmentDetails(order));
		rateReq.setShipper(DHLXmlDataConverter.populateRatingShipper(order));
		rateReq.getSpecialService().add(populateRatingSpecialService(order));
		
		return rateReq;
	}

	private static RatingSpecialService populateRatingSpecialService(ShippingOrder order) {
		// TODO Auto-generated method stub
		//MyToDo: Need to discuss with Rizwan 
		RatingSpecialService s = new RatingSpecialService();
		s.setSpecialServiceType("S");
		return s;
	}

	private static RatingShipper populateRatingShipper(ShippingOrder order) {
		// TODO Auto-generated method stub
		RatingShipper s = new RatingShipper();
		
		s.setCity(order.getFromAddress().getCity());
		s.setPostalCode(order.getFromAddress().getPostalCode());
		s.setCountryCode(order.getFromAddress().getCountryCode());
		s.setDivision(getProvinceName(order.getFromAddress().getProvinceCode()));
		return s;
	}

	private static RatingShipmentDetails populateRatingShipmentDetails(ShippingOrder order) {
		// TODO Auto-generated method stub
		RatingShipmentDetails s = new RatingShipmentDetails();
		s.setNumberOfPieces(new BigInteger(order.getQuantity().toString()));
		Pieces pieces = populatePieces(order);
		if (pieces != null)
			s.setPieces(populatePieces(order));
		BigDecimal weight = new BigDecimal(order.getTotalWeight()).setScale(2, RoundingMode.HALF_EVEN);
		s.setWeight(weight);
		s.setWeightUnit(WeightUnit.L);
		s.setDimensionUnit(DimensionUnit.I);
		s.setProductCode(getRatingProductCode(order.getPackageTypeId().getPackageTypeId()));
		return s;
	}

	private static String getRatingProductCode(Long packageTypeId) {
		// TODO Auto-generated method stub
		// MyToDo: Need Rizwan's help to map these codes
//		D (Doc)
//		W (WPX)
//		X (Express Doc)
//		Y : DHL Second Day Express
//		G : DHL Second Day
//		T : DHL Ground Shipments
//		return "P";
		return "E";
	}

	private static RatingConsignee populateRatingConsignee(ShippingOrder order) {
		// TODO Auto-generated method stub
		RatingConsignee consignee = new RatingConsignee();
		consignee.setCity(order.getToAddress().getCity());
		consignee.setPostalCode(order.getToAddress().getPostalCode());
		consignee.setCountryCode(order.getToAddress().getCountryCode());
		consignee.setDivision(getProvinceName(order.getToAddress().getProvinceCode()));

		return consignee;
	}

	private static String getProvinceName(String provinceCode) {
		// TODO Auto-generated method stub
		//MyToDo: Need to check with Rizwan, it seems like we need to pass full province code in division
		if (provinceCode.equalsIgnoreCase("ON"))
			return "Ontario";
		else if (provinceCode.equalsIgnoreCase("NY"))
			return "New York";
		return provinceCode;
	}

	private static CustomerRateBilling populateCustomerRateBilling(ShippingOrder order, CustomerCarrier customerCarrier) {
		// TODO Auto-generated method stub
		if (customerCarrier != null) {
			CustomerRateBilling billing = new CustomerRateBilling();
			long accountNumber = Long.parseLong(customerCarrier.getAccountNumber1());
			if (order.getToAddress().getCountryCode().equals(ShiplinxConstants.CANADA))
				accountNumber = Long.parseLong(customerCarrier.getAccountNumber2());
//			billing.setShipperAccountNumber(accountNumber);
			if(customerCarrier.getCountry().equalsIgnoreCase(order.getFromAddress().getCountryCode()))
				billing.setShipperAccountNumber(Long.parseLong(customerCarrier.getProperty3())); 	// this is an export shipment
			else
				billing.setShipperAccountNumber(Long.parseLong(customerCarrier.getProperty4())); 	// this is an import shipment
//			billing.setShipperAccountNumber(953708600L);	// 953708600 for import (US to Canada)
			billing.setShippingPaymentType(PaymentType.S);
			return billing;
		}		
		return null;
	}

	public static DCTRequest populateDCTRequest(ShippingOrder order, CustomerCarrier customerCarrier) {
		// TODO Auto-generated method stub
		DCTRequest req = new DCTRequest();
//		req.setGetCapability(populateGetCapability(order));
		req.setGetQuote(populateGetQuote(order, customerCarrier));
		return req;
	}

	private static GetQuote populateGetQuote(ShippingOrder order, CustomerCarrier customerCarrier) {
		// TODO Auto-generated method stub
		GetQuote q = new GetQuote();
		q.setBkgDetails(populateBkgDetailsType(order,customerCarrier));
		q.setDutiable(populateDCTDutiable(order, customerCarrier));
		q.setFrom(populateDCTFrom(order.getFromAddress()));
		q.setTo(populateDCTTo(order.getToAddress()));
		q.setRequest(populateRequest(order, customerCarrier));
		return q;
	}

	private static GetCapability populateGetCapability(ShippingOrder order, CustomerCarrier customerCarrier) {
		// TODO Auto-generated method stub
		GetCapability c = new GetCapability();
		c.setBkgDetails(populateBkgDetailsType(order, customerCarrier));
		c.setDutiable(populateDCTDutiable(order, customerCarrier));
		c.setFrom(populateDCTFrom(order.getFromAddress()));
		c.setRequest(populateRequest(order, customerCarrier));
		c.setTo(populateDCTTo(order.getToAddress()));
		return c;
	}

	private static DCTTo populateDCTTo(Address toAddress) {
		// TODO Auto-generated method stub
		DCTTo s = new DCTTo();
		
		s.setCity(toAddress.getCity());
		s.setPostalcode(toAddress.getPostalCode());
		s.setCountryCode(toAddress.getCountryCode());
//		s.setDivision(getProvinceName(order.getFromAddress().getProvinceCode()));
		return s;
	}

	private static DCTFrom populateDCTFrom(Address fromAddress) {
		// TODO Auto-generated method stub
		DCTFrom s = new DCTFrom();
		
		s.setCity(fromAddress.getCity());
		s.setPostalcode(fromAddress.getPostalCode());
		s.setCountryCode(fromAddress.getCountryCode());
//		s.setDivision(getProvinceName(order.getFromAddress().getProvinceCode()));
		return s;
	}

	private static DCTDutiable populateDCTDutiable(ShippingOrder order, CustomerCarrier customerCarrier) {
		// TODO Auto-generated method stub
		if (order.getPackageTypeId().getPackageTypeId() == ShiplinxConstants.PACKAGE_TYPE_PACKAGE || order.getPackageTypeId().getPackageTypeId() == ShiplinxConstants.PACKAGE_TYPE_PALLET || order.getPackageTypeId().getPackageTypeId() == ShiplinxConstants.PACKAGE_TYPE_PAK) {
			DCTDutiable dutiable = new DCTDutiable();
			
			dutiable.setDeclaredValue(order.getDutiableAmount().floatValue());
			if(customerCarrier.getCountry().equalsIgnoreCase(ShiplinxConstants.CANADA))
				dutiable.setDeclaredCurrency("CAD");
			if(customerCarrier.getCountry().equalsIgnoreCase(ShiplinxConstants.US))
				dutiable.setDeclaredCurrency("USD");
			
			return dutiable;
		}
		
		return null;
	}

	private static BkgDetailsType populateBkgDetailsType(ShippingOrder order, CustomerCarrier customerCarrier) {
		// TODO Auto-generated method stub
		BkgDetailsType bkg = new BkgDetailsType();
		if (customerCarrier != null && customerCarrier.getCountry() != null)
			bkg.setPaymentCountryCode(customerCarrier.getCountry());
		else
			bkg.setPaymentCountryCode(order.getFromAddress().getCountryCode());
		if(customerCarrier.getCountry().equalsIgnoreCase(order.getFromAddress().getCountryCode()))
			bkg.setPaymentAccountNumber(customerCarrier.getProperty3()); 	// this is an export shipment rating
		else
			bkg.setPaymentAccountNumber(customerCarrier.getProperty4()); 	// this is an import shipment rating

		bkg.setDate(getDhlDate(order.getScheduledShipDate()));
		long l = order.getScheduledShipDate().getTime();
		try {
			bkg.setReadyTime(DatatypeFactory.newInstance().newDuration(l));
		} catch (Exception e) {
			e.printStackTrace();
		}
		// MyToDo: Need to set it properly
		bkg.setDimensionUnit(INCH); 	// DimensionUnit.I.name());
		bkg.setWeightUnit(LB);			// WeightUnit.L.name());
		if(order.getPackageTypeId().getPackageTypeId()!=ShiplinxConstants.PACKAGE_TYPE_ENVELOPE)
			bkg.setPieces(DHLXmlDataConverter.populateBkgDetailsPieces(order));
		
		// MyToDo: Discussion needed with Rizwan
		if((order.getDocsOnly() != null && order.getDocsOnly()) || order.getPackageTypeId().getPackageTypeId()==ShiplinxConstants.PACKAGE_TYPE_ENVELOPE)
			bkg.setIsDutiable("N");
		else
			bkg.setIsDutiable("Y");
		
		populateQtdShp(order.getPackageTypeId(), bkg.getQtdShp());
		
		return bkg;
	}

	private static void populateQtdShp(PackageType pkg, List<QtdShpType> qtdShp) {
		// TODO Auto-generated method stub
		if (pkg != null) {
			for (int i=0; i<PRODUCT_CODES.length; i++) {
				if (pkg.getPackageTypeId().longValue() == Long.parseLong(PRODUCT_CODES[i][4])) {
					if ( !isProductCodesExists(qtdShp, PRODUCT_CODES[i][0]) ) {
						QtdShpType qs = new QtdShpType();
						qs.setGlobalProductCode(PRODUCT_CODES[i][0]);
						qtdShp.add(qs);
					}
				}
			}
		}
	}
	private static boolean isProductCodesExists(List<QtdShpType> qtdShp, String pCode) {
		// TODO Auto-generated method stub
		for (QtdShpType qs:qtdShp)
			if (qs.getGlobalProductCode().equals(pCode))
				return true;
		return false;
	}

	private static com.meritconinc.shiplinx.carrier.dhl.xml.BkgDetailsType.Pieces populateBkgDetailsPieces(
						ShippingOrder order) {
		// TODO Auto-generated method stub
		if (order.getPackages() != null && order.getPackages().size() > 0) {
			com.meritconinc.shiplinx.carrier.dhl.xml.BkgDetailsType.Pieces pieces = new 
									com.meritconinc.shiplinx.carrier.dhl.xml.BkgDetailsType.Pieces();
			int n=0;
			for (com.meritconinc.shiplinx.model.Package p:order.getPackages()) {
				pieces.getPiece().add(populatePieceType(p, ++n));
			}
			return pieces;
		}
		return null;
	}

	private static PieceType populatePieceType(Package pkg, int n) {
		// TODO Auto-generated method stub
		PieceType p = new PieceType();
		p.setPieceID(Integer.toString(n));
		if (pkg.getWeight() != null) {
			BigDecimal weight = pkg.getWeight().setScale(2, RoundingMode.HALF_EVEN);
			p.setWeight(weight);
		}
		if (pkg.getHeight() != null)
			p.setHeight(pkg.getHeight());
		if (pkg.getLength() != null)
			p.setDepth(pkg.getLength());
		if (pkg.getWidth() != null)
			p.setWidth(pkg.getWidth());

		return p;
	}

	public static QtdShpTypeDCTRes findQtdShpByServiceId(List<QtdShpTypeDCTRes> resQtdShpTypes, long serviceId, boolean isExport) {
		// TODO Auto-generated method stub
		if (resQtdShpTypes != null && serviceId > 0) {
			for (QtdShpTypeDCTRes qs:resQtdShpTypes) {
				for (int i=0; i<PRODUCT_CODES.length; i++) {
					if (qs.getGlobalProductCode().equals(PRODUCT_CODES[i][0]) && Long.parseLong(PRODUCT_CODES[i][3]) == serviceId) {
						return qs;
					}
				}				
			}
		}
		return null;
	}

	//PICK UPS
	@SuppressWarnings("unchecked")
	public static BookPickupRequest populatePickupRequest(com.meritconinc.shiplinx.model.Pickup pickup) {
		// TODO Auto-generated method stub
		BookPickupRequest req = new BookPickupRequest();
		req.setRequest(DHLXmlDataConverter.populateRequest(pickup));
		req.setRequestor(DHLXmlDataConverter.populateRequestor(pickup));
		req.setPlace(DHLXmlDataConverter.populatePlacePickup(pickup));
		req.setPickup(DHLXmlDataConverter.populatePickup(pickup));
		req.setPickupContact(DHLXmlDataConverter.populatePickupContact(pickup));

		return req;
	}

	public static Requestor populateRequestor(com.meritconinc.shiplinx.model.Pickup pickup) {
		Requestor req = new Requestor();
		req.setAccountType("D"); 
		if (pickup.getCarrierAccount() != null) {
			int shipmentType = DHLAPI.getShipmentType(pickup.getCarrierAccount().getCountry(), pickup.getAddress().getCountryCode(), pickup.getDestinationCountryCode());
			long accountNumber = 0;
			if(shipmentType==ShiplinxConstants.SHIPMENT_TYPE_EXPORT)
				accountNumber = Long.parseLong(pickup.getCarrierAccount().getAccountNumber1());
			else if(shipmentType==ShiplinxConstants.SHIPMENT_TYPE_IMPORT)
				accountNumber = Long.parseLong(pickup.getCarrierAccount().getAccountNumber2());
			else if(shipmentType==ShiplinxConstants.SHIPMENT_TYPE_THIRD_COUNTRY)			
				accountNumber = Long.parseLong(pickup.getCarrierAccount().getProperty5());
			req.setAccountNumber(accountNumber);
		}
		return req;
	}

	public static PlacePickup populatePlacePickup(com.meritconinc.shiplinx.model.Pickup pickup) {
		PlacePickup pp = new PlacePickup();
		if(pickup.getAddress().isResidential())
			pp.setLocationType("R");
		else
			pp.setLocationType("B");
		
		pp.setCompanyName(StringUtil.setWithMaxLength(pickup.getAddress().getAbbreviationName(), 35));
		pp.setAddress1(StringUtil.setWithMaxLength(pickup.getAddress().getAddress1(), 35));
		if(pickup.getAddress().getAddress2()!=null)
			pp.setAddress2(StringUtil.setWithMaxLength(pickup.getAddress().getAddress2(), 35));
		pp.setCity(pickup.getAddress().getCity());
		pp.setStateCode(pickup.getAddress().getProvinceCode());
		pp.setCountryCode(pickup.getAddress().getCountryCode());
		pp.setPostalCode(pickup.getAddress().getPostalCode());
		pp.setPackageLocation(pickup.getPickupLocation());
		
		return pp;
	}
	
	public static Pickup populatePickup(com.meritconinc.shiplinx.model.Pickup pickup) {
		Pickup pu = new Pickup();
		
		pu.setPickupDate(getDhlDate(pickup.getPickupDate()));
		pu.setReadyByTime(pickup.getReadyHour() + ":" + pickup.getReadyMin());
		pu.setCloseTime(pickup.getCloseHour() + ":" + pickup.getCloseMin());
		pu.setPieces(pickup.getQuantity());
		
		BigDecimal weight = new BigDecimal(pickup.getTotalWeight()).setScale(1, RoundingMode.HALF_EVEN);
		WeightSeg ws = new WeightSeg();
		ws.setWeight(weight);
		if(pickup.getWeightUnit().equalsIgnoreCase(ShiplinxConstants.WEIGHT_UNITS_LBS))
			ws.setWeightUnit(WeightUnit.L);
		else
			ws.setWeightUnit(WeightUnit.K);
		pu.setWeight(ws);
		
		pu.setSpecialInstructions(pickup.getInstructions());

		return pu;
	}

	public static ContactPickup populatePickupContact(com.meritconinc.shiplinx.model.Pickup pickup) {
		ContactPickup cp = new ContactPickup();
		cp.setPersonName(pickup.getAddress().getContactName());
		cp.setPhone(pickup.getAddress().getPhoneNo());
		cp.setPhoneExtention(pickup.getAddress().getPhoneExt());
		
		return cp;
		
	}
	
	public static Request populateRequest(com.meritconinc.shiplinx.model.Pickup pickup) {
		// TODO Auto-generated method stub
		Request req = new Request();
		req.setServiceHeader(DHLXmlDataConverter.populatePickupServiceHeader(pickup));
		return req;
	}

	private static ServiceHeader populatePickupServiceHeader(com.meritconinc.shiplinx.model.Pickup pickup) {
		// TODO Auto-generated method stub
		ServiceHeader header = new ServiceHeader();
		
		header.setMessageTime(getXmlGCalendar());
		header.setMessageReference(getUniqueID());
		header.setSiteID(pickup.getCarrierAccount().getProperty1());
		header.setPassword(pickup.getCarrierAccount().getProperty2());
		return header;
	}

	public static CancelPickupRequest populateCancelPickupRequest(com.meritconinc.shiplinx.model.Pickup pickup) {
		// TODO Auto-generated method stub
		CancelPickupRequest req = new CancelPickupRequest();
		req.setRequest(DHLXmlDataConverter.populateRequest(pickup));
		req.setConfirmationNumber(Integer.valueOf(pickup.getConfirmationNum()));
		req.setRequestorName(pickup.getAddress().getContactName());
		req.setReason("006");
		if(pickup.getCarrierReference()!=null && pickup.getCarrierReference().length()>0)
			req.setOriginSvcArea(pickup.getCarrierReference());
		return req;
	}

	//Global API
	
	public static ShipmentRequest populateGlobalShipmentValidateRequest(ShippingOrder order, Rating rateInfo, DHLCanadaTariffDAO tariffDAO, CustomerCarrier customerCarrier) {

		com.meritconinc.shiplinx.carrier.dhl.ShipmentRequest req = new com.meritconinc.shiplinx.carrier.dhl.ShipmentRequest(); 
		req.setSchemaVersion(new BigDecimal("1.0"));
		req.setRequest(DHLXmlDataConverter.populateGlobalRequest(order, customerCarrier));
		req.setRequestedPickupTime(com.meritconinc.shiplinx.carrier.dhl.xml.shipvalidateglobal.request.YesNo.Y);
		req.setNewShipper(com.meritconinc.shiplinx.carrier.dhl.xml.shipvalidateglobal.request.YesNo.N);
		req.setLanguageCode(DHLXmlDataConverter.LANG_ENG);
		DHLZone zone = new DHLZone();
		zone.setCountryCode(order.getFromAddress().getCountryCode());
		zone = tariffDAO.getZone(zone);
		
		if(zone!=null && !StringUtil.isEmpty(zone.getRegion())){
			if(zone.getRegion().equalsIgnoreCase("AM"))
				req.setRegionCode(RegionCode.AM);
			else if(zone.getRegion().equalsIgnoreCase("EU"))
				req.setRegionCode(RegionCode.EU);
			else if(zone.getRegion().equalsIgnoreCase("AP"))
				req.setRegionCode(RegionCode.AP);
			else 
				req.setRegionCode(RegionCode.AM); //default to AM
		}
		else
			req.setRegionCode(RegionCode.AM); //default to AM
		
		req.setPiecesEnabled(com.meritconinc.shiplinx.carrier.dhl.xml.shipvalidateglobal.request.PiecesEnabled.Y);
		req.setBilling(DHLXmlDataConverter.populateGlobalBilling(order, customerCarrier));
		req.setConsignee(DHLXmlDataConverter.populateGlobalConsignee(order,tariffDAO));
		if ( !StringUtil.isEmpty(order.getReferenceCode()) )
			req.getReference().add(populateGlobalReference(order.getReferenceCode()));
		if ( !StringUtil.isEmpty(order.getReferenceOne()) )
			req.getReference().add(populateGlobalReference(order.getReferenceOne()));
		if ( !StringUtil.isEmpty(order.getReferenceTwo()) )
			req.getReference().add(populateGlobalReference(order.getReferenceTwo()));
	
		req.setShipmentDetails(DHLXmlDataConverter.populateGlobalShipmentDetails(order, rateInfo, tariffDAO));
		req.setShipper(DHLXmlDataConverter.populateGlobalShipper(order, tariffDAO, customerCarrier));
		req.setPlace(DHLXmlDataConverter.populateGlobalPlace(order.getFromAddress()));
		req.setDutiable(DHLXmlDataConverter.populateGlobalDutiable(order, customerCarrier));
		req.setLabelImageFormat(LabelImageFormat.PDF);
		
		//below 5 line code changed by mohan.according our need.
		logger.debug("req.getBilling().getDutyAccountNumber()");
		logger.debug(req.getBilling().getDutyAccountNumber());
		logger.debug("Bill To");
		logger.debug(order.getCustomsInvoice().getBillTo());
		logger.debug("DutyPayment Type");
		logger.debug(req.getBilling().getDutyPaymentType());		
		if(!("R".equals(req.getBilling().getDutyPaymentType().toString()))){			
			List<SpecialService> listOfSpecialService=new ArrayList<SpecialService>();
			SpecialService specialService=new SpecialService();
			specialService.setSpecialServiceType("DD");
			listOfSpecialService.add(specialService);
			req.setSpecialService(listOfSpecialService);
		}
		return req;
	}

	public static com.meritconinc.shiplinx.carrier.dhl.xml.shipvalidateglobal.request.Request populateGlobalRequest(ShippingOrder order, CustomerCarrier customerCarrier) {
		com.meritconinc.shiplinx.carrier.dhl.xml.shipvalidateglobal.request.Request req = new com.meritconinc.shiplinx.carrier.dhl.xml.shipvalidateglobal.request.Request();
		req.setServiceHeader(DHLXmlDataConverter.populateGlobalServiceHeader(order, customerCarrier));
		return req;
	}
	
	private static com.meritconinc.shiplinx.carrier.dhl.xml.shipvalidateglobal.request.ServiceHeader populateGlobalServiceHeader(ShippingOrder order, CustomerCarrier customerCarrier) {
		com.meritconinc.shiplinx.carrier.dhl.xml.shipvalidateglobal.request.ServiceHeader header = new com.meritconinc.shiplinx.carrier.dhl.xml.shipvalidateglobal.request.ServiceHeader();
		header.setMessageTime(getXmlGCalendar());
		header.setMessageReference(getUniqueID());
		header.setSiteID(customerCarrier.getProperty1());
		header.setPassword(customerCarrier.getProperty2());
		return header;
	}

	public static com.meritconinc.shiplinx.carrier.dhl.xml.shipvalidateglobal.request.Billing populateGlobalBilling(ShippingOrder order, CustomerCarrier customerCarrier) {
		// TODO Auto-generated method stub
		if (customerCarrier != null) {
			com.meritconinc.shiplinx.carrier.dhl.xml.shipvalidateglobal.request.Billing billing = new com.meritconinc.shiplinx.carrier.dhl.xml.shipvalidateglobal.request.Billing();
			int shipmentType = DHLAPI.getShipmentType(customerCarrier.getCountry(), order.getFromAddress().getCountryCode(), order.getToAddress().getCountryCode());
			long accountNumber = 0;
			if(shipmentType==ShiplinxConstants.SHIPMENT_TYPE_EXPORT)
				accountNumber = Long.parseLong(customerCarrier.getAccountNumber1());
			else if(shipmentType==ShiplinxConstants.SHIPMENT_TYPE_IMPORT)
				accountNumber = Long.parseLong(customerCarrier.getAccountNumber2());
			else if(shipmentType==ShiplinxConstants.SHIPMENT_TYPE_THIRD_COUNTRY)			
				accountNumber = Long.parseLong(customerCarrier.getProperty5());
			
			billing.setBillingAccountNumber(String.valueOf(accountNumber));
			billing.setShipperAccountNumber(String.valueOf(accountNumber));
			billing.setShippingPaymentType(com.meritconinc.shiplinx.carrier.dhl.xml.shipvalidateglobal.request.ShipmentPaymentType.S);
			
			//by default the duties and taxes should be billed to Consignee
			billing.setDutyPaymentType(com.meritconinc.shiplinx.carrier.dhl.xml.shipvalidateglobal.request.DutyTaxPaymentType.R);
			if(order.getCustomsInvoice()!=null && !StringUtil.isEmpty(order.getCustomsInvoice().getBillTo())){
				if(order.getCustomsInvoice().getBillTo().equalsIgnoreCase(ShiplinxConstants.BILL_TO_SHIPPER)){
					billing.setDutyPaymentType(com.meritconinc.shiplinx.carrier.dhl.xml.shipvalidateglobal.request.DutyTaxPaymentType.S);
					order.getCustomsInvoice().setBillToAccountNum(String.valueOf(accountNumber));
				}
				logger.debug("Test DutyAccount Number");
				logger.debug(ShiplinxConstants.BILL_TO_THIRD_PARTY);
				logger.debug(order.getCustomsInvoice().getBillTo());
				if(order.getCustomsInvoice().getBillTo().equalsIgnoreCase(ShiplinxConstants.BILL_TO_THIRD_PARTY)){
					billing.setDutyPaymentType(com.meritconinc.shiplinx.carrier.dhl.xml.shipvalidateglobal.request.DutyTaxPaymentType.T);
					billing.setDutyAccountNumber(order.getCustomsInvoice().getBillToAccountNum());
				}
			}
			
			//if billing shipment to third party, then set accordingly
			//order.setBillToAccountNum("971371066");
			if(!StringUtil.isEmpty(order.getBillToAccountNum())){
				billing.setBillingAccountNumber(order.getBillToAccountNum());
				billing.setShipperAccountNumber(order.getBillToAccountNum());
				billing.setShippingPaymentType(com.meritconinc.shiplinx.carrier.dhl.xml.shipvalidateglobal.request.ShipmentPaymentType.S);
			}
			else
				order.setBillToAccountNum(String.valueOf(accountNumber));
					
			
			return billing;
		}
		
		return null;
	}
	
	public static com.meritconinc.shiplinx.carrier.dhl.xml.shipvalidateglobal.request.Consignee populateGlobalConsignee(ShippingOrder order, DHLCanadaTariffDAO tariffDAO) {
		com.meritconinc.shiplinx.carrier.dhl.xml.shipvalidateglobal.request.Consignee consignee = new com.meritconinc.shiplinx.carrier.dhl.xml.shipvalidateglobal.request.Consignee();
		consignee.setCompanyName(StringUtil.setWithMaxLength(order.getToAddress().getAbbreviationName(), 35));
		consignee.getAddressLine().add(StringUtil.setWithMaxLength(order.getToAddress().getAddress1(), 35));
		if ( !StringUtil.isEmpty(order.getToAddress().getAddress2()) )
			consignee.getAddressLine().add(StringUtil.setWithMaxLength(order.getToAddress().getAddress2(), 35));
		
		consignee.setCity(order.getToAddress().getCity());
		consignee.setPostalCode(order.getToAddress().getPostalCode());
		consignee.setCountryCode(order.getToAddress().getCountryCode());
		// MyToDo - Fix Country Name
		DHLZone zone = new DHLZone(consignee.getCountryCode());
		zone = tariffDAO.getZone(zone);
		consignee.setCountryName(zone.getCountryName());
		consignee.setDivisionCode(order.getToAddress().getProvinceCode());
		
		com.meritconinc.shiplinx.carrier.dhl.xml.shipvalidateglobal.request.Contact c = populateGlobalContact(order.getToAddress());
		if (c != null)
			consignee.setContact(c);
		
		return consignee;
	}
	
	private static com.meritconinc.shiplinx.carrier.dhl.xml.shipvalidateglobal.request.Contact populateGlobalContact(Address addr) {
		if (addr != null && addr.getContactName() != null && addr.getPhoneNo() != null) {
			com.meritconinc.shiplinx.carrier.dhl.xml.shipvalidateglobal.request.Contact c = new com.meritconinc.shiplinx.carrier.dhl.xml.shipvalidateglobal.request.Contact();
			c.setPersonName(StringUtil.setWithMaxLength(addr.getContactName(), 35));
			c.setPhoneNumber(addr.getPhoneNo());
			return c;
		}
		return null;
	}

	private static com.meritconinc.shiplinx.carrier.dhl.xml.shipvalidateglobal.request.Reference populateGlobalReference(String reference) {
		com.meritconinc.shiplinx.carrier.dhl.xml.shipvalidateglobal.request.Reference ref = new com.meritconinc.shiplinx.carrier.dhl.xml.shipvalidateglobal.request.Reference();
		ref.setReferenceID(reference);
//		ref.setReferenceType(reference);
		return ref;
	}

	public static com.meritconinc.shiplinx.carrier.dhl.xml.shipvalidateglobal.request.ShipmentDetails populateGlobalShipmentDetails(ShippingOrder order, Rating rateInfo, 
			DHLCanadaTariffDAO canadaTariffDAO) {

		com.meritconinc.shiplinx.carrier.dhl.xml.shipvalidateglobal.request.ShipmentDetails s = new com.meritconinc.shiplinx.carrier.dhl.xml.shipvalidateglobal.request.ShipmentDetails();
		s.setNumberOfPieces((int)order.getQuantity());
		Pieces pieces = populatePieces(order);
		if (pieces != null)
			s.setPieces(populateGlobalPieces(order));
		
		BigDecimal weight = null;
		if(order.getPackageTypeId().getPackageTypeId() == ShiplinxConstants.PACKAGE_TYPE_ENVELOPE)
			weight = new BigDecimal(0.5).setScale(1, RoundingMode.HALF_EVEN);
		else
			weight = new BigDecimal(rateInfo.getBillWeight()).setScale(1, RoundingMode.HALF_EVEN);
		s.setWeight(weight);
		DHLZone zone = new DHLZone();
		zone.setCountryCode(order.getFromAddress().getCountryCode());
		zone = canadaTariffDAO.getZone(zone);
//		if (zone != null && zone.getWeightUnit() != null)
		s.setWeightUnit(com.meritconinc.shiplinx.carrier.dhl.xml.shipvalidateglobal.request.WeightUnit.L);
		
		int prodTabIndex = getProductCodes(order.getDocsOnly(), rateInfo, order.getPackageTypeId().getPackageTypeId());
		s.setGlobalProductCode(PRODUCT_CODES[prodTabIndex][0]);
		s.setLocalProductCode(PRODUCT_CODES[prodTabIndex][0]); //s.setLocalProductCode(PRODUCT_CODES[prodTabIndex][2]);
//		XMLGregorianCalendar cal = getDhlDate(order.getScheduledShipDate());
		Calendar cal = Calendar.getInstance();
		cal.setTime(order.getScheduledShipDate());
//		s.setDate(getXmlGCalendar(order.getScheduledShipDate()));
		s.setDate(getDhlDate(order.getScheduledShipDate()));
		if (zone != null)
			s.setDimensionUnit(com.meritconinc.shiplinx.carrier.dhl.xml.shipvalidateglobal.request.DimensionUnit.C);
		s.setPackageType(getGlobalPackageType(order.getPackageTypeId().getPackageTypeId()));
		s.setCurrencyCode(ShiplinxConstants.CURRENCY_CA_STRING);
		StringBuilder contents = new StringBuilder("");
		for (Package p:order.getPackages()) {
			if(p.getDescription()!=null && p.getDescription().length()>0)
				contents.append(p.getDescription() + ",");
		}
		if(contents.length() > 0)		
			s.setContents(StringUtil.setWithMaxLength(contents.toString(), 90));
		else
			s.setContents("Various");
		
		if(order.getInsuranceValue() > 0)
			s.setInsuredAmount(order.getInsuranceValue());
		return s;
	
	
	}

	private static com.meritconinc.shiplinx.carrier.dhl.xml.shipvalidateglobal.request.Pieces populateGlobalPieces(ShippingOrder order) {
		// TODO Auto-generated method stub
		if (order.getPackages() != null && order.getPackages().size() > 0) {
			com.meritconinc.shiplinx.carrier.dhl.xml.shipvalidateglobal.request.Pieces pieces = new com.meritconinc.shiplinx.carrier.dhl.xml.shipvalidateglobal.request.Pieces();
			int n=0;
			for (com.meritconinc.shiplinx.model.Package p:order.getPackages()) {
				pieces.getPiece().add(populateGlobalPiece(p, ++n, order.getPackageTypeId().getPackageTypeId()));
			}
			return pieces;
		}
		return null;

	}

	private static com.meritconinc.shiplinx.carrier.dhl.xml.shipvalidateglobal.request.Piece populateGlobalPiece(com.meritconinc.shiplinx.model.Package pkg, int n, long pType) {
		com.meritconinc.shiplinx.carrier.dhl.xml.shipvalidateglobal.request.Piece p = new com.meritconinc.shiplinx.carrier.dhl.xml.shipvalidateglobal.request.Piece();
		p.setPieceID(Integer.toString(n));
		if (pkg.getWeight() != null) {
			BigDecimal weight = null;
			if(pType == ShiplinxConstants.PACKAGE_TYPE_ENVELOPE){
				weight = new BigDecimal(0.5).setScale(1, RoundingMode.HALF_EVEN);
				p.setWeight(weight);
				return p;
			}
			else
				weight = pkg.getWeight().setScale(1, RoundingMode.HALF_EVEN);
			p.setWeight(weight);
//			p.setDimWeight(weight);
		}
		if (pkg.getHeight() != null)
			p.setHeight(new BigInteger(String.valueOf(pkg.getHeight().intValue())));
		if (pkg.getLength() != null)
			p.setDepth(new BigInteger(String.valueOf(pkg.getLength().intValue())));
		if (pkg.getWidth() != null)
			p.setWidth(new BigInteger(String.valueOf(pkg.getWidth().intValue())));
		
		
		// MyToDo: Need to confirm with Rizwan
//		p.setPackageType(com.meritconinc.shiplinx.carrier.dhl.xml.PackageType.EE);
		p.setPackageType(com.meritconinc.shiplinx.carrier.dhl.xml.shipvalidateglobal.request.PackageType.CP);
		
		return p;

	}

	private static com.meritconinc.shiplinx.carrier.dhl.xml.shipvalidateglobal.request.PackageType getGlobalPackageType(Long pTypeId) {
		
		if (pTypeId == ShiplinxConstants.PACKAGE_TYPE_ENVELOPE)
			return com.meritconinc.shiplinx.carrier.dhl.xml.shipvalidateglobal.request.PackageType.EE;
		return com.meritconinc.shiplinx.carrier.dhl.xml.shipvalidateglobal.request.PackageType.CP;
	}

	public static com.meritconinc.shiplinx.carrier.dhl.xml.shipvalidateglobal.request.Shipper populateGlobalShipper(ShippingOrder order, DHLCanadaTariffDAO tariffDAO, CustomerCarrier customerCarrier) {
	
		com.meritconinc.shiplinx.carrier.dhl.xml.shipvalidateglobal.request.Shipper s = new com.meritconinc.shiplinx.carrier.dhl.xml.shipvalidateglobal.request.Shipper();
		
		int shipmentType = DHLAPI.getShipmentType(customerCarrier.getCountry(), order.getFromAddress().getCountryCode(), order.getToAddress().getCountryCode());
		if(shipmentType==ShiplinxConstants.SHIPMENT_TYPE_EXPORT)			
			s.setShipperID(customerCarrier.getAccountNumber1());
		else if(shipmentType==ShiplinxConstants.SHIPMENT_TYPE_IMPORT)
			s.setShipperID(customerCarrier.getAccountNumber2());
		else if(shipmentType==ShiplinxConstants.SHIPMENT_TYPE_THIRD_COUNTRY)
			s.setShipperID(customerCarrier.getProperty5());
		
		// MyToDo: No idea about RegisteredAccount, it's in XSD but not in PDF document
		// Setting it Account Number
		s.setRegisteredAccount(s.getShipperID());
		
		s.setCompanyName(StringUtil.setWithMaxLength(order.getFromAddress().getAbbreviationName(), 35));
		s.getAddressLine().add(StringUtil.setWithMaxLength(order.getFromAddress().getAddress1(), 35));
		if ( !StringUtil.isEmpty(order.getFromAddress().getAddress2()) )
			s.getAddressLine().add(StringUtil.setWithMaxLength(order.getFromAddress().getAddress2(), 35));
		
		s.setCity(order.getFromAddress().getCity());
		s.setPostalCode(order.getFromAddress().getPostalCode());
		s.setCountryCode(order.getFromAddress().getCountryCode());
		
		// MyToDo - RegisteredAccount, Division, CountryName are mandatory
		DHLZone zone = new DHLZone(s.getCountryCode());
		zone = tariffDAO.getZone(zone);
		s.setCountryName(zone.getCountryName());
		s.setDivisionCode(order.getFromAddress().getProvinceCode());
		s.setDivision(order.getFromAddress().getProvinceCode());
		
		com.meritconinc.shiplinx.carrier.dhl.xml.shipvalidateglobal.request.Contact c = populateGlobalContact(order.getFromAddress());
		if (c != null)
			s.setContact(c);		
		return s;

	}

	public static com.meritconinc.shiplinx.carrier.dhl.xml.shipvalidateglobal.request.Place populateGlobalPlace(com.meritconinc.shiplinx.model.Address addr) {

		com.meritconinc.shiplinx.carrier.dhl.xml.shipvalidateglobal.request.Place p = new com.meritconinc.shiplinx.carrier.dhl.xml.shipvalidateglobal.request.Place();
		p.setCompanyName(StringUtil.setWithMaxLength(addr.getAbbreviationName(), 35));
		p.getAddressLine().add(StringUtil.setWithMaxLength(addr.getAddress1(), 35));
		if ( !StringUtil.isEmpty(addr.getAddress2()) )
			p.getAddressLine().add(StringUtil.setWithMaxLength(addr.getAddress2(), 35));
		
		p.setCity(addr.getCity());
		p.setPostalCode(addr.getPostalCode());
		p.setCountryCode(addr.getCountryCode());
		p.setDivisionCode(addr.getProvinceCode());
		p.setDivision(addr.getProvinceCode());

		return p;
	}

	public static com.meritconinc.shiplinx.carrier.dhl.xml.shipvalidateglobal.request.Dutiable populateGlobalDutiable(ShippingOrder order, CustomerCarrier customerCarrier) {
		// TODO Auto-generated method stub
		if (order.getPackageTypeId().getPackageTypeId() == ShiplinxConstants.PACKAGE_TYPE_PAK || order.getPackageTypeId().getPackageTypeId() == ShiplinxConstants.PACKAGE_TYPE_PACKAGE || order.getPackageTypeId().getPackageTypeId() == ShiplinxConstants.PACKAGE_TYPE_PALLET) {
			com.meritconinc.shiplinx.carrier.dhl.xml.shipvalidateglobal.request.Dutiable dutiable = new com.meritconinc.shiplinx.carrier.dhl.xml.shipvalidateglobal.request.Dutiable();
			//Having trouble with DHL API, they want the dutiable amount in format x.xx but if amount is 12.00 the float conversion changes it to 12.0
			//Work around for now is to add $0.01 to the amount to ensure it stays as 2 decimal places
			BigDecimal declaredValue =new BigDecimal(order.getDutiableAmount().doubleValue() + 0.01).setScale(2, RoundingMode.HALF_EVEN);
			dutiable.setDeclaredValue(declaredValue.floatValue());
			if(customerCarrier.getCountry().equalsIgnoreCase(ShiplinxConstants.CANADA))
				dutiable.setDeclaredCurrency("CAD");
			if(customerCarrier.getCountry().equalsIgnoreCase(ShiplinxConstants.US))
				dutiable.setDeclaredCurrency("USD");
			
			return dutiable;
		}
		
		return null;
	}
}
