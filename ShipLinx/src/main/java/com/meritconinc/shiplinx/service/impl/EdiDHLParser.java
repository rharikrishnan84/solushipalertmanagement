package com.meritconinc.shiplinx.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.meritconinc.mmr.utilities.MmrBeanLocator;
import com.meritconinc.mmr.utilities.StringUtil;
import com.meritconinc.shiplinx.carrier.dhl.DHLAPI;
import com.meritconinc.shiplinx.carrier.dhl.dao.DHLCanadaTariffDAO;
import com.meritconinc.shiplinx.carrier.dhl.model.DHLTariff;
import com.meritconinc.shiplinx.carrier.dhl.model.DHLZone;
import com.meritconinc.shiplinx.dao.AddressDAO;
import com.meritconinc.shiplinx.dao.CustomerDAO;
import com.meritconinc.shiplinx.dao.EdiDAO;
import com.meritconinc.shiplinx.model.Address;
import com.meritconinc.shiplinx.model.CarrierChargeCode;
import com.meritconinc.shiplinx.model.Charge;
import com.meritconinc.shiplinx.model.CustomerCarrier;
import com.meritconinc.shiplinx.model.EdiInfo;
import com.meritconinc.shiplinx.model.EdiItem;
import com.meritconinc.shiplinx.model.LoggedEvent;
import com.meritconinc.shiplinx.model.Package;
import com.meritconinc.shiplinx.model.PackageType;
import com.meritconinc.shiplinx.model.ShippingOrder;
import com.meritconinc.shiplinx.service.LoggedEventService;
import com.meritconinc.shiplinx.service.MarkupManager;
import com.meritconinc.shiplinx.service.ShippingService;
import com.meritconinc.shiplinx.utils.FormattingUtil;
import com.meritconinc.shiplinx.utils.ShiplinxConstants;

public class EdiDHLParser extends EdiParser {
	private static final Logger log = LogManager.getLogger(EdiDHLParser.class); 
	public static final String ACCOUNT_NUMBER		="1_DHL Account #";
	public static final String INVOICE_DATE			="9_Invoice Date";
	public static final String INVOICE_NUMBER		="7_Invoice Number";
	public static final String INVOICE_AMOUNT		="14_Total Invoice Charge Including Tax";
	public static final String TRACKING_NUMBER		="15_DHL Waybill Number";	
	public static final String SHIP_REF_NUMBER_1	="16_Customer Reference Number";
	public static final String PACKAGE_TYPE			="17_Document/Other?";
	public static final String SERVICE_TYPE			="18_PRODUCT";
	public static final String ENTERED_WEIGHT		="22_Declared Weight";
	public static final String ENTERED_LENGTH		="24_DECL L";
	public static final String ENTERED_WIDTH		="25_DECL W";
	public static final String ENTERED_HEIGHT		="26_DECL H";
	public static final String BILLED_WEIGHT		="33_Weight for Pricing";
	public static final String PRICING_WEIGHT_UOM	="34_Pricing Weight UOM";
	public static final String PACKAGE_QUANTITY		="35_Pieces";
	public static final String CHARGE_CURRENCY		="36_Currency";
	public static final String FREIGHT_CHARGE		="37_Freight Charge";
	public static final String SENDER_COMP_NAME		="44_Pickup Customer Name";
	public static final String SENDER_ADDRESS_1		="45_Pickup Address";
	public static final String SENDER_CITY			="46_Pickup City";
	public static final String SENDER_STATE			="47_Pickup Province";
	public static final String SENDER_POSTAL		="48_Pickup Postal Code";
	public static final String SENDER_COUNTRY		="49_Pickup Country";	
	public static final String FROM_ZONE			="51_Origin Pricing Zone";
	public static final String SHIP_DATE			="52_Pickup Date";
	public static final String RECEIVER_COMP_NAME	="53_Destination Customer Name";
	public static final String RECEIVER_ADDRESS_1	="54_Destination Address";
	public static final String RECEIVER_CITY		="55_Destination City";
	public static final String RECEIVER_STATE		="56_Destination State";
	public static final String RECEIVER_POSTAL		="57_Destination Postal Code";
	public static final String RECEIVER_COUNTRY		="58_Destination Country";
	public static final String TO_ZONE				="60_Destination Pricing Zone";
	public static final String POD_DATE				="61_Delivery Date";
	public static final String POD_TIME				="62_Delivery Time";
	public static final String POD_RECEIVER			="63_Receiver Name";
	public static final int CHARGE_CODE_INDEX_START = 64;
	public static final String CHARGE_CODE			="_Charge Code ";
	public static final int CHARGE_COST_INDEX_START = 65;
	public static final String CHARGE_COST			="_Charge Amt ";
	public static final String INVOICE_TYPE			="108_Invoice Type";
	
	
	public static final int TOT_EDI_CHARGES			=19;
	
	public static final String DATE_FORMAT = "yyyyMMdd"; // 20110621, 20110530

	private String [] fields = null;

	private static int DEFAULT_SERVICE_TYPE = 301;
	private static final String [][] serviceTypeMap = { 	
								{"CX", ShiplinxConstants.EXPORT, "301"}, 	//Express Worldwide
								{"CX", ShiplinxConstants.IMPORT, "306"}, 	//Import Express
								{"PA", ShiplinxConstants.EXPORT, "303"}, 	//Express 1030AM														
								{"PA", ShiplinxConstants.IMPORT, "303"}, 	//Express 1030AM
								{"PN", ShiplinxConstants.EXPORT, "304"}, 	//Express 12PM
								{"PN", ShiplinxConstants.IMPORT, "308"}, 	//Import Express 12PM														
								{"9A", ShiplinxConstants.EXPORT, "300"}, 	//Express 9 a.m.
								{"9A", ShiplinxConstants.IMPORT, "307"}, 	//Import Express 9AM
								{"AE", ShiplinxConstants.EXPORT, "305"}, 	//ESI Export														
								{"AE", ShiplinxConstants.IMPORT, "305"} 	//ESI Export
													};

	

	private static String DEFAULT_PACKAGE_TYPE = "type_package";
	private static final String [][] packageTypeMap = { 	
//														{"O", "type_package"}, 	
//														{"D", "type_env"}
														{"O", "type_package"}, 	
														{"D", "type_package"}	

													};

	private static List<PackageType> packageTypes = null;

	public EdiDHLParser(EdiInfo ediInfo, EdiDAO ediDAO, ShippingService shippingService, 
			CustomerDAO customerDAO, AddressDAO addressDAO, MarkupManager markupManagerService,
			LoggedEventService loggedService) {
		super(ediInfo, ediDAO, shippingService, customerDAO, addressDAO, 
				markupManagerService, loggedService);
	}	
	@Override
	protected List<String> performOperation() throws Exception {
		if (ediInfo.getEdiFolder() != null) {
			fields = populateFields();
			if (fields != null) {
				List<String> files = processEdiFiles();
				return files;
			}
		}
		return null;
	}

	@Override
	protected ShippingOrder populateShipment(EdiItem item) throws Exception {
		// TODO Auto-generated method stub
		ShippingOrder shipment = new ShippingOrder();
		
		shipment.setBusinessId(item.getBusinessId());
		shipment.setCarrierId(item.getCarrierId());
		shipment.setScheduledShipDate(getDateTime(getEdiField(SHIP_DATE), null, DATE_FORMAT));
		shipment.setMasterTrackingNum(getEdiField(TRACKING_NUMBER));
		shipment.setReferenceOne(getEdiField(SHIP_REF_NUMBER_1));
		try{
			shipment.setQuantity(StringUtil.getInteger(getEdiField(PACKAGE_QUANTITY)));
		}
		catch(Exception e){
			shipment.setQuantity(0); 
		}
		shipment.setFromZone(getEdiField(FROM_ZONE));
		shipment.setToZone(getEdiField(TO_ZONE));
		shipment.setPodDateTime(getDateTime(getEdiField(POD_DATE), getEdiField(POD_TIME), DATE_FORMAT));
		shipment.setPodReceiver(getEdiField(POD_RECEIVER));
		shipment.setBilledWeight(Float.parseFloat(getEdiField(BILLED_WEIGHT)));
		if(getEdiField(PRICING_WEIGHT_UOM)!=null){
			if(getEdiField(PRICING_WEIGHT_UOM).equalsIgnoreCase("M"))
				shipment.setBilledWeightUOM(ShiplinxConstants.WEIGHT_UNITS_KGS);
			else
				shipment.setBilledWeightUOM(ShiplinxConstants.WEIGHT_UNITS_LBS);
		}
		shipment.setCurrency(getEdiField(CHARGE_CURRENCY));
		shipment.setStatusId((long)ShiplinxConstants.STATUS_DELIVERED);
		
		Address shipFrom = populateShipFromAddress();
		shipment.setFromAddress(shipFrom);

		Address shipTo = populateShipToAddress();
		shipment.setToAddress(shipTo);
		
		ShippingOrder dbShipment = findShipment(shipment);
		if(dbShipment != null){
			// Moved this line in updateShipment method, after applyExceptionRules
//			dbShipment.setStatusId((long)ShiplinxConstants.STATUS_DELIVERED);
			shipment.setDbShipment(dbShipment);
		}
		else{
			shipment.setCreatedBy(getEdiField(INVOICE_NUMBER));
		}

		//first try and set service id from dbShipment
		if(shipment.getDbShipment()!=null && shipment.getDbShipment().getServiceId() > 0){
			shipment.setServiceId(shipment.getDbShipment().getServiceId());
		}
		else{
			String shipType = getShippingType(shipTo);
			shipment.setServiceId(new Long(this.getServiceType(getEdiField(SERVICE_TYPE), shipType)));
		}

		populateCustomer(shipment, item.getAccountNumber());

		Package pkg = populatePackage(item, shipment);
		shipment.getPackages().add(pkg);
		
		populateCharges(shipment, item);
		
		
		
		return shipment;
	}	

	private void populateCharges(ShippingOrder shipment, EdiItem item) {
		// TODO Auto-generated method stub
		Double freightCost = Double.valueOf(getEdiField(FREIGHT_CHARGE));
		Charge freightCharge = null;
		if (freightCost != null && freightCost.doubleValue() != 0) {
			freightCharge = populateCharge(shipment, item, DHLAPI.FREIGHT_CHARGE_CODE, freightCost);
			shipment.getCharges().add(freightCharge);
		}
		

		String codeName = "";
		String costName = "";
		Charge charge = null;
		double resCharge = 0.0;
		
		//find remote area surcharge to include in fuel calculation (fuel applies to RAS, in the EDI file, the fuel chage for freight and RAS is all bundled up together)
		for (int i=0; i<TOT_EDI_CHARGES; i++) {
			codeName = (CHARGE_CODE_INDEX_START + (i*2)) + CHARGE_CODE + (i+1);
			costName = (CHARGE_COST_INDEX_START + (i*2)) + CHARGE_COST + (i+1);
			if(getEdiField(codeName).equalsIgnoreCase(DHLAPI.REMOTE_AREA_SERVICE_CODE))
				resCharge = Double.valueOf(getEdiField(costName));
		}

		
		for (int i=0; i<TOT_EDI_CHARGES; i++) {
			codeName = (CHARGE_CODE_INDEX_START + (i*2)) + CHARGE_CODE + (i+1);
			costName = (CHARGE_COST_INDEX_START + (i*2)) + CHARGE_COST + (i+1);
			charge = populateCharge(shipment, item, getEdiField(codeName), Double.valueOf(getEdiField(costName)));
			if (charge != null) {
				// Markup for Fuel Charge (Reverse calculated from Freight & Fuel Cost)
				if (charge.getChargeCode().equals(DHLAPI.FUEL_CHARGE_CODE) && freightCharge != null) {
					double fuelPercent = charge.getCost() / (FormattingUtil.add(freightCharge.getCost().doubleValue(), resCharge)).doubleValue();
					//hard coded fuel surcharge (charge, not cost) to 15% as per request from Carol Q - May 28 2013
					double chargeAmount = FormattingUtil.add(freightCharge.getCharge() * 0.15, resCharge * 0.15).doubleValue();
					charge.setCharge(FormattingUtil.formatDecimalTo2PlacesDouble(chargeAmount));
					charge.setStaticAmount(resCharge * fuelPercent);
					charge.setCarrierId(shipment.getCarrierId());
					charge.setCarrierName(ShiplinxConstants.CARRIER_DHL_STRING);
					//set the tariff fuel to 15% of freight in order to ensure that if shipment is re-assigned, then fuel charge stays at 15%
					double tariffRate = FormattingUtil.add(freightCharge.getTariffRate() * 0.15, resCharge * 0.15).doubleValue();
					charge.setTariffRate(FormattingUtil.formatDecimalTo2PlacesDouble(tariffRate));
					shipment.setFuelPercent((float)fuelPercent * 100);
				}
				shipment.getCharges().add(charge);
				
			}
		}
	}

	private int getServiceType( String value, String shipType ) {
		for (int i=0; i<serviceTypeMap.length; i++)
			if (serviceTypeMap[i][0].equals(value) && serviceTypeMap[i][1].equals(shipType))
				return Integer.parseInt(serviceTypeMap[i][2]);
		return DEFAULT_SERVICE_TYPE;
	}	
	
	@Override
	protected EdiItem populateEdiItem(String fileName) throws Exception {
		// TODO Auto-generated method stub
		String ediInvoiceDate = getEdiField(INVOICE_DATE);
		String ediInvoiceNumber = getEdiField(INVOICE_NUMBER);
		
		// Testing Code
//		if (ediInvoiceNumber.equals("YUL0000000002")) {
//			System.out.println("Start Debug....");
//		}		
		
		if (ediInvoiceDate != null && ediInvoiceNumber != null) {
			Date invoiceDate = getDateTime(ediInvoiceDate, null, DATE_FORMAT);
			EdiItem item = findEdiItem(ediInvoiceNumber);
			
			Date processedDate = new Date();
			if (item == null) {
				// New record
				item = new EdiItem();
				item.setBusinessId(ediInfo.getBusinessId());
				item.setCarrierId(ediInfo.getCarrierId());
				item.setEdiFileName(fileName);
				item.setMessage("text.message.status.process.inprogress");
				item.setStatus(ShiplinxConstants.STATUS_INPROGRESS);
				item.setInvoiceDate(invoiceDate);
				item.setInvoiceNumber(ediInvoiceNumber);
				item.setProcessedDate(processedDate);
				item.setAccountNumber(getEdiField(ACCOUNT_NUMBER));
				item.setTotInvoiceAmount(Double.parseDouble(getEdiField(INVOICE_AMOUNT)));
				item.setType(getEdiField(INVOICE_TYPE));
				item.setStartTime(new Date());
				
				long key = this.ediDAO.addEdiItem(item);
				if (key > 0) {
					item.setId(key);
					getEdiItems().add(item);
				}
			} else {
				// Update Record 
				if (item.getStatus() != ShiplinxConstants.STATUS_INPROGRESS) {
					item.setMessage("text.message.status.process.inprogress");
					item.setStatus(ShiplinxConstants.STATUS_INPROGRESS);
					item.setInvoiceDate(invoiceDate);
					item.setInvoiceNumber(ediInvoiceNumber);
					item.setProcessedDate(processedDate);
					item.setStartTime(new Date());
					item.setAccountNumber(getEdiField(ACCOUNT_NUMBER));
					item.setTotInvoiceAmount(Double.parseDouble(getEdiField(INVOICE_AMOUNT)));
					
					this.ediDAO.updateEdiItem(item);
				}
			}
			
			return item;
		}
		return null;
	}
	private Package populatePackage(EdiItem item, ShippingOrder ediShipment) {
		// TODO Auto-generated method stub
		Package pkg = new Package();
		pkg.setTrackingNumber(getEdiField(TRACKING_NUMBER));
		pkg.setWeight(new BigDecimal(getEdiField(ENTERED_WEIGHT)));
		pkg.setLength(new BigDecimal(getEdiField(ENTERED_LENGTH)));
		pkg.setWidth(new BigDecimal(getEdiField(ENTERED_WIDTH)));
		pkg.setHeight(new BigDecimal(getEdiField(ENTERED_HEIGHT)));		
		pkg.setBilledWeight(Float.parseFloat(getEdiField(BILLED_WEIGHT)));
		
		String docType = getEdiField(PACKAGE_TYPE).trim();
		if (docType.equals(ShiplinxConstants.DOCUMENT_TYPE_DOC))
			pkg.setDoc(true);
		else
			pkg.setDoc(false);
		PackageType pkgType = getPackageType(docType);
		if (pkgType != null) {
			ediShipment.setPackageTypeId(pkgType);
			pkg.setType(pkgType.getType());
		}
		pkg.setWeightUOM(getEdiField(PRICING_WEIGHT_UOM));

		return pkg;
	}	
	private PackageType getPackageType(String ediPkgType) {
		// TODO Auto-generated method stub
		String pkgType = DEFAULT_PACKAGE_TYPE;
		for (int i=0; i<packageTypeMap.length; i++)
			if (packageTypeMap[i][0].equalsIgnoreCase(ediPkgType))
				pkgType = packageTypeMap[i][1];

		if (pkgType != null) {
			if (packageTypes == null)
				packageTypes = this.shippingService.getAllPackages();
			for(PackageType pt:packageTypes)
				if (pt.getType().equals(pkgType)) 
					return pt;
		}
		
		return null;
	}	
	
	private Charge populateCharge(ShippingOrder shipment, EdiItem item, String chargeCode, Double cost) {
		// TODO Auto-generated method stub
		if ( !isNullOrEmpty(chargeCode) ) {
			Charge charge = new Charge();
			
			String chargeCodeLevel2 = chargeCode;
			String chargeName = "";
			String chargeGroupCode = null;
			List<CarrierChargeCode> chargeCodes = this.shippingService.getChargeListByCarrierAndCodes(
										item.getCarrierId(), chargeCode, chargeCodeLevel2);
			if (chargeCodes != null && chargeCodes.size() > 0) {
				chargeCode = chargeCodes.get(0).getChargeCode();
				chargeCodeLevel2 = chargeCodes.get(0).getChargeCodeLevel2();
				chargeName = chargeCodes.get(0).getChargeName();
				chargeGroupCode = chargeCodes.get(0).getGroupCode();
			} else {
				chargeCode = ShiplinxConstants.CHARGE_CODE_DHL_ACC;
				chargeCodeLevel2 = ShiplinxConstants.CHARGE_CODE_LEVEL_2_DHL_OTH;
				chargeName = ShiplinxConstants.CHARGE_CODE_DHL_ACC;
			}
			
//			charge.setCharge(new Double(0.0));
			charge.setDiscountAmount(new Double(0.0));
			charge.setChargeCode(chargeCode);
			charge.setChargeCodeLevel2(chargeCodeLevel2);
			charge.setName(chargeName);
			charge.setCurrency(getEdiField(CHARGE_CURRENCY));
			charge.setCost(cost); 
			charge.setEdiInvoiceNumber(item.getInvoiceNumber());
			charge.setStatus(ShiplinxConstants.CHARGE_PENDING_RELEASE);
			charge.setCarrierId((long)ShiplinxConstants.CARRIER_DHL);
			charge.setCarrierName(ShiplinxConstants.CARRIER_DHL_STRING);
//			if (chargeGroupCode != null && 
//					(chargeGroupCode.equals(ShiplinxConstants.GROUP_FUEL_CHARGE) ||
//					chargeGroupCode.equals(ShiplinxConstants.GROUP_FREIGHT_CHARGE)	)) {
//				if (chargeGroupCode.equals(ShiplinxConstants.GROUP_FREIGHT_CHARGE)) 
//					populateTariffRate(shipment, charge);
//				charge.setCharge( applyMarkup(shipment, charge, item) );
//			} else {
//				charge.setCharge(charge.getCost());
//			}	
			if (chargeGroupCode != null && chargeGroupCode.equals(ShiplinxConstants.GROUP_FREIGHT_CHARGE)) {
				populateTariffRate(shipment, charge, item.getAccountCountry());
				charge.setCharge( applyMarkup(shipment, charge, item) );
			} else {
				charge.setTariffRate(charge.getCost());
				charge.setCharge(charge.getCost());
			}	
			charge.setType(ShiplinxConstants.CHARGE_TYPE_ACTUAL);
			return charge;
		}
		
		return null;
	}

	private void populateTariffRate(ShippingOrder shipment, Charge charge, String accountCountry) {
		// TODO Auto-generated method stub
		DHLCanadaTariffDAO dhlTariff = (DHLCanadaTariffDAO)MmrBeanLocator.getInstance().findBean("dhlCanadaTariffDAO");
		DHLZone fromZone = new DHLZone(shipment.getFromAddress().getCountryCode());	
		fromZone = dhlTariff.getZone(fromZone);
		DHLZone toZone = new DHLZone(shipment.getToAddress().getCountryCode());	
		toZone = dhlTariff.getZone(toZone);
       
        int shipmentType = DHLAPI.getShipmentType(accountCountry, shipment.getFromAddress().getCountryCode(), shipment.getToAddress().getCountryCode());
        
        DHLTariff tariff = null;
        Package pkg = shipment.getPackages().get(0);
        Double weightKg = 0.0;
        Double weightPound = 0.0;
        if (shipment.getBilledWeightUOM().equalsIgnoreCase(ShiplinxConstants.WEIGHT_UNITS_LBS)) 
        	weightPound = new Double(shipment.getBilledWeight());
        else
        	weightKg = new Double(shipment.getBilledWeight());
        	
        long serviceId = shipment.getServiceId();
        if(shipment.getDbShipment()!=null && shipment.getDbShipment().getOriginalServiceId()!=null && shipment.getDbShipment().getOriginalServiceId().longValue() > 0)
        	serviceId = shipment.getDbShipment().getOriginalServiceId(); //get the original service id, see DHLAPI.java regarding this.
        
        tariff = new DHLTariff(serviceId, shipment.getPackageTypeId().getPackageTypeId(), 
        			weightPound, weightKg, fromZone.getZoneCode(), toZone.getZoneCode(), shipmentType);
        tariff = dhlTariff.getTariffRecord(tariff);
        
        if (tariff != null) {
	        if (pkg.isDoc())
	        	charge.setTariffRate(FormattingUtil.formatDecimalTo2PlacesDouble(tariff.getRateDoc()));
	        else
	        	charge.setTariffRate(FormattingUtil.formatDecimalTo2PlacesDouble(tariff.getRateNonDoc()));
        } else {
        	log.error("Tariff is null - Master Tracking Number:" + shipment.getMasterTrackingNum());
        }
        
	}

	private Address populateShipToAddress() {
		// TODO Auto-generated method stub
		Address shipTo = new Address();
		shipTo.setDefaultFromAddress(false);
		shipTo.setDefaultToAddress(false);
		shipTo.setResidential(true);		
		shipTo.setAbbreviationName(getEdiField(RECEIVER_COMP_NAME));
		shipTo.setAddress1(getEdiField(RECEIVER_ADDRESS_1));
		shipTo.setCity(getEdiField(RECEIVER_CITY));
		shipTo.setProvinceCode(getEdiField(RECEIVER_STATE));
		shipTo.setPostalCode(getEdiField(RECEIVER_POSTAL));
		shipTo.setCountryCode(getEdiField(RECEIVER_COUNTRY));

		return shipTo;
	}

	private Address populateShipFromAddress() {
		// TODO Auto-generated method stub
		Address shipFrom = new Address();
		shipFrom.setDefaultFromAddress(false);
		shipFrom.setDefaultToAddress(false);
		shipFrom.setResidential(true);
		shipFrom.setAbbreviationName(getEdiField(SENDER_COMP_NAME));
		shipFrom.setAddress1(getEdiField(SENDER_ADDRESS_1));
		shipFrom.setCity(getEdiField(SENDER_CITY));
		shipFrom.setProvinceCode(getEdiField(SENDER_STATE));
		shipFrom.setPostalCode(getEdiField(SENDER_POSTAL));
		shipFrom.setCountryCode(getEdiField(SENDER_COUNTRY));

		return shipFrom;
	}
	
	protected void updateShipment(ShippingOrder ediShipment,
			ShippingOrder dbShipment) throws Exception {
		// TODO Auto-generated method stub
		// Update Package
		Package ediPackage = ediShipment.getPackages().get(0);
		if (ediPackage != null) {
			Package dbPackage = findPackage(dbShipment, ediPackage
					.getTrackingNumber());
			if (dbPackage == null) { //only add a package record if this is a new shipment. If the shipment was created on the system, then we cannot reconcile packages in case of DHL as the EDI does not provide detailed information of packages. The total billed weight is recorded at the shipment level for this reason 
				if(dbShipment == null)
					addPackage(dbShipment, ediShipment);
			} else {
				updatePackage(ediPackage, dbPackage);
			}
		}

		// Update Charges
		for(Charge ediCharge: ediShipment.getCharges()){
			if (ediCharge != null) {
				Charge dbCharge = findCharge(dbShipment, ediCharge);
				if (dbCharge == null) {
					ediCharge.setCarrierId(dbShipment.getCarrierId());
					ediCharge.setCarrierName(ShiplinxConstants.CARRIER_DHL_STRING);
					addCharge(dbShipment, ediCharge);
				} else {
					dbCharge.setCarrierId(dbShipment.getCarrierId());
					dbCharge.setCarrierName(ShiplinxConstants.CARRIER_DHL_STRING);
					updateCharge(ediCharge, dbCharge);
				}
			}
		}
		
		//update the shipment to include new billed weight
		if(dbShipment.getBilledWeight()==null || dbShipment.getBilledWeight() < ediShipment.getBilledWeight())
			dbShipment.setBilledWeight(ediShipment.getBilledWeight());
		if(getEdiField(PRICING_WEIGHT_UOM)!=null){
			if(getEdiField(PRICING_WEIGHT_UOM).equalsIgnoreCase("M"))
				dbShipment.setBilledWeightUOM(ShiplinxConstants.WEIGHT_UNITS_KGS);
			else
				dbShipment.setBilledWeightUOM(ShiplinxConstants.WEIGHT_UNITS_LBS);
		}

		shippingService.applyAdditionalHandling(dbShipment, null, ShiplinxConstants.CHARGE_TYPE_ACTUAL);
		
		if (!applyExceptionsRules(ediShipment, dbShipment)) {
			// No exception rules were applied
			dbShipment.setStatusId((long)ShiplinxConstants.STATUS_DELIVERED);
		}
		
		shippingService.updateShippingOrder(dbShipment);

	}

	@Override
	protected boolean applyCustomExceptionRules(ShippingOrder ediShipment,
			ShippingOrder dbShipment, List<LoggedEvent> events) {
		return false;
	}	
}
