package com.meritconinc.shiplinx.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.meritconinc.mmr.utilities.MmrBeanLocator;
import com.meritconinc.mmr.utilities.StringUtil;
import com.meritconinc.shiplinx.carrier.loomis.LoomisAPI;
import com.meritconinc.shiplinx.carrier.loomis.dao.LoomisTariffDAO;
import com.meritconinc.shiplinx.carrier.loomis.model.LoomisTariff;
import com.meritconinc.shiplinx.dao.AddressDAO;
import com.meritconinc.shiplinx.model.CurrencySymbol;
import com.meritconinc.shiplinx.model.Customer;
import com.meritconinc.shiplinx.dao.CustomerDAO;
import com.meritconinc.shiplinx.dao.EdiDAO;
import com.meritconinc.shiplinx.model.Address;
import com.meritconinc.shiplinx.model.CarrierChargeCode;
import com.meritconinc.shiplinx.model.Charge;
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

public class EdiLoomisParser extends EdiParser {
	
	private static final Logger log = LogManager.getLogger(EdiLoomisParser.class); 
	
	public static final String ACCOUNT_NUMBER		="1_Loomis or DHL Account #";
	public static final String INVOICE_DATE			="9_Invoice Date";
	public static final String INVOICE_NUMBER		="7_Invoice Number";
	public static final String INVOICE_AMOUNT		="14_Total Invoice Charge Including Tax";
	public static final String TRACKING_NUMBER		="15_Loomis Exp Waybill Number";	
	public static final String SHIP_REF_NUMBER_1	="16_Customer Reference Number";
	public static final String SERVICE_TYPE			="17_Service";
	public static final String PACKAGE_TYPE			="18_Product";
	public static final String ENTERED_WEIGHT		="22_Declared Weight";
	public static final String ENTERED_LENGTH		="24_DECL L";
	public static final String ENTERED_WIDTH		="25_DECL W";
	public static final String ENTERED_HEIGHT		="26_DECL H";
	public static final String BILLED_WEIGHT		="33_Weight for Pricing";
	public static final String PRICING_WEIGHT_UOM	="34_Pricing Weight UOM";
	public static final String PACKAGE_QUANTITY		="35_Pieces";
	public static final String CHARGE_CURRENCY		="36_Currency";
	public static final String FREIGHT_CHARGE		="37_Waybill Charge Less Tax";
	public static final String GST_CHARGE			="39_Waybill GST";
	public static final String HST_CHARGE			="40_Waybill HST";
	public static final String PST_CHARGE			="41_Waybill PST";
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
	public static final String RECEIVER_STATE		="56_Destination Province";
	public static final String RECEIVER_POSTAL		="57_Destination Postal Code";
	public static final String RECEIVER_COUNTRY		="58_Destination Country";
	public static final String TO_ZONE				="60_Destination Pricing Zone";
	public static final String POD_DATE				="61_Delivery Date";
	public static final String POD_TIME				="62_Delivery Time";
	public static final String POD_RECEIVER			="63_Receiver Name";
	
	public static final String CHARGE_AIR_FUEL			="64_AF";  		//AF = Air Fuel Surcharge fixed field
	public static final String CHARGE_AIR_FUEL_CHG		="65_AF CHG";  	
	public static final String CHARGE_COLLECT			="66_CO";  		//CO = Collect Surcharge fixed field
	public static final String CHARGE_COLLECT_CHG		="67_CO CHG";  	
	public static final String CHARGE_DANGEROUS			="68_DG";  		//DG = Dangerous Goods Surcharge fixed field
	public static final String CHARGE_DANGEROUS_CHG		="69_DG CHG";  	
	public static final String CHARGE_FRAGILE			="70_FR";  		//FR = Fragile Surcharge fixed field
	public static final String CHARGE_FRAGILE_CHG		="71_FR CHG";  	
	public static final String CHARGE_GROUND			="72_GF";  		//GF = Ground Fuel Surcharge fixed field
	public static final String CHARGE_GROUND_CHG		="73_GF CHG";  	
	public static final String CHARGE_OVERWEIGHT		="74_OV";  		//OV = Overweight/ RP = Re-Pack/ NP = Non-pack fixed field
	public static final String CHARGE_OVERWEIGHT_CHG	="75_OV CHG";  	
	public static final String CHARGE_ACC_FEE			="76_MA";  		//MA = Missing Account Fee Surcharge fixed field
	public static final String CHARGE_ACC_FEE_CHG		="77_MA CHG";  	
	public static final String CHARGE_INVALID_ACC		="78_IA/RD";  	//IA/RD = Invalid Account/Re-Delivery Fee Surcharge fixed field
	public static final String CHARGE_INVALID_ACC_CHG	="79_IA/RD CHG";  	
	public static final String CHARGE_NAV_CANADA		="80_NV";  		//NV = NAV Canada Surcharge fixed field
	public static final String CHARGE_NAV_CANADA_CHG	="81_NV CHG";  	
	public static final String CHARGE_RESIDENTIAL		="82_R";  		//R = Residential Surcharge fixed field
	public static final String CHARGE_RESIDENTIAL_CHG	="83_R CHG";  	
	public static final String CHARGE_RET_CHEQUE		="84_RC";  		//RC = Return Cheque fixed field
	public static final String CHARGE_RET_CHEQUE_CHG	="85_RC CHG";  	
	public static final String CHARGE_AVI_INSURANCE		="86_AF";  		//AI = Aviation Insurance fixed field
	public static final String CHARGE_AVI_INSURANCE_CHG	="87_AF CHG";  	
	public static final String CHARGE_SAT_DELIVERY		="88_SD";  		//SD = Saturday Delivery fixed field
	public static final String CHARGE_SAT_DELIVERY_CHG	="89_SD CHG";  	
	public static final String CHARGE_SEC_SHIPMENT		="90_SS";  		//SS = Security Shipment fixed field
	public static final String CHARGE_SEC_SHIPMENT_CHG	="91_SS CHG";  	
	public static final String CHARGE_UNABLE_PICKUP		="92_NR";  		//NR = Unable to Pick Up E-Return fixed field
	public static final String CHARGE_UNABLE_PICKUP_CHG	="93_NR CHG";  	
	public static final String CHARGE_OTHER				="94_XN";  		//XN = Other Surcharge fixed field
	public static final String CHARGE_OTHER_CHG			="95_XN CHG";  	
	public static final String CHARGE_DUTIES_TAX		="96_XY";  		//XY = Duties and Taxes Surcharge fixed field
	public static final String CHARGE_DUTIES_TAX_CHG	="97_XY CHG";  	
	public static final String CHARGE_I18N_FUEL			="98_IF";  		//IF = International Fuel Surcharge fixed field
	public static final String CHARGE_I18N_FUEL_CHG		="99_IF CHG";  	
	public static final String CHARGE_RURAL				="100_RA";  	//RA = Remote Area/ RU = Rural Surcharge fixed field
	public static final String CHARGE_RURAL_CHG			="101_RA CHG";  	
	public static final String INVOICE_TYPE				="108_Invoice Type";
	public static final int CHARGE_CODE_INDEX_START = 64;
	public static final int CHARGE_COST_INDEX_START = 65;
	
	public static final String FREIGHT_CHARGE_CODE	="FRT"; // 4023, 4, 'FRT', 'FRT', 'Freight', 'Freight Charge', 1 - Column 
	
	public static final int TOT_EDI_CHARGES			=19;
	
	public static final String DATE_FORMAT = "yyyyMMdd"; // 20110621, 20110530

	private String [] fields = null;

	private static final String [][] serviceTypeMap = { 	
														{"DD", "400"}, 	//Domestic Ground
														{"DE", "401"}, 	//Domestic Express
														{"D9", "402"}, 	//Domestic Express 09:00
														{"DN", "403"} 	//Domestic Express 12:00														
													};
	
	private static final String [][] packageTypeMap = { 	
														{"PA", "type_package"}, 	// 1 EN Envelope (Standard Domestic) EN
														{"EN", "type_env"}			// 2 PA Parcel (Standard Domestic) PA
													};	
	
	private static List<PackageType> packageTypes = null;

	public EdiLoomisParser(EdiInfo ediInfo, EdiDAO ediDAO, ShippingService shippingService, 
			CustomerDAO customerDAO, AddressDAO addressDAO, 
			MarkupManager markupManagerService,
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
		shipment.setCarrierName(ShiplinxConstants.CARRIER_LOOMIS_STRING);
		shipment.setScheduledShipDate(getDateTime(getEdiField(SHIP_DATE), null, DATE_FORMAT));
		shipment.setMasterTrackingNum(getEdiField(TRACKING_NUMBER));
		shipment.setReferenceOne(getEdiField(SHIP_REF_NUMBER_1));
		shipment.setQuantity(StringUtil.getInteger(getEdiField(PACKAGE_QUANTITY)));
		shipment.setServiceId(new Long(this.getServiceType(getEdiField(SERVICE_TYPE))));
		shipment.setFromZone(getEdiField(FROM_ZONE));
		shipment.setToZone(getEdiField(TO_ZONE));
		shipment.setPodDateTime(getDateTime(getEdiField(POD_DATE), getEdiField(POD_TIME), DATE_FORMAT));
		shipment.setPodReceiver(getEdiField(POD_RECEIVER));
		shipment.setCurrency(getEdiField(CHARGE_CURRENCY));
		shipment.setStatusId((long)ShiplinxConstants.STATUS_DELIVERED);
		
		shipment.setBilledWeight(Float.parseFloat(getEdiField(BILLED_WEIGHT)));
		if(getEdiField(PRICING_WEIGHT_UOM)!=null){
			if(getEdiField(PRICING_WEIGHT_UOM).equalsIgnoreCase("M"))
				shipment.setBilledWeightUOM(ShiplinxConstants.WEIGHT_UNITS_KGS);
			else
				shipment.setBilledWeightUOM(ShiplinxConstants.WEIGHT_UNITS_LBS);
		}

//		if (shipment.getMasterTrackingNum().equals("7841354136")) {
//			System.out.println("Start Debug....");
//		}
		Address shipFrom = populateShipFromAddress();
		shipment.setFromAddress(shipFrom);

		Address shipTo = populateShipToAddress();
		shipment.setToAddress(shipTo);
		
//		shipment.setCustomerId(getCustomerId(item.getAccountNumber()));
		populateCustomer(shipment, item.getAccountNumber());
		
		Package pkg = populatePackage(item, shipment);
		shipment.getPackages().add(pkg);
		
		populateCharges(shipment, item);
		if(shipment.getCharges()!=null && shipment.getCharges().size()>0)
					{
						shipment.setCurrency(shipment.getCharges().get(0).getCurrency());
					}
		return shipment;
	}	
	
	private void populateCharges(ShippingOrder shipment, EdiItem item) {
		// TODO Auto-generated method stub
		Double freightCost = Double.valueOf(getEdiField(FREIGHT_CHARGE));
		Charge freightCharge = null;
		if (freightCost != null && freightCost.doubleValue() != 0) {
			freightCharge = populateCharge(shipment, item, LoomisAPI.FREIGHT_CHARGE_CODE, null, freightCost);
			shipment.getCharges().add(freightCharge);
		}
		
		Double fuelCost = Double.valueOf(getEdiField(CHARGE_AIR_FUEL_CHG));
		if(fuelCost!=null && fuelCost.doubleValue()!=0){
			Charge charge = populateCharge(shipment, item, LoomisAPI.AIR_FUEL_CHARGE_CODE, null, fuelCost);
			double fuelPercent = charge.getCost() / freightCharge.getCost();
			double chargeAmount = freightCharge.getCharge() * fuelPercent;
			charge.setCharge(FormattingUtil.formatDecimalTo2PlacesDouble(chargeAmount));			
			double tariffRate = freightCharge.getTariffRate() * fuelPercent;
			charge.setTariffRate(FormattingUtil.formatDecimalTo2PlacesDouble(tariffRate));
			shipment.getCharges().add(charge);

		}
		fuelCost = Double.valueOf(getEdiField(CHARGE_GROUND_CHG));
		if(fuelCost!=null && fuelCost.doubleValue()!=0){
			Charge charge = populateCharge(shipment, item, LoomisAPI.GROUND_FUEL_CHARGE_CODE, null, fuelCost);
			double fuelPercent = charge.getCost() / freightCharge.getCost();
			double chargeAmount = freightCharge.getCharge() * fuelPercent;
			charge.setCharge(FormattingUtil.formatDecimalTo2PlacesDouble(chargeAmount));			
			double tariffRate = freightCharge.getTariffRate() * fuelPercent;
			charge.setTariffRate(FormattingUtil.formatDecimalTo2PlacesDouble(tariffRate));
			shipment.getCharges().add(charge);

		}
		
//		populateCharge(shipment, item, CHARGE_AIR_FUEL, 		CHARGE_AIR_FUEL_CHG);
		populateCharge(shipment, item, CHARGE_COLLECT, 			CHARGE_COLLECT_CHG);
		populateCharge(shipment, item, CHARGE_DANGEROUS, 		CHARGE_DANGEROUS_CHG);
		populateCharge(shipment, item, CHARGE_FRAGILE, 			CHARGE_FRAGILE_CHG);
//		populateCharge(shipment, item, CHARGE_GROUND, 			CHARGE_GROUND_CHG);
		populateCharge(shipment, item, CHARGE_OVERWEIGHT, 		CHARGE_OVERWEIGHT_CHG);
		populateCharge(shipment, item, CHARGE_ACC_FEE, 			CHARGE_ACC_FEE_CHG);
		populateCharge(shipment, item, CHARGE_INVALID_ACC, 		CHARGE_INVALID_ACC_CHG);
		populateCharge(shipment, item, CHARGE_NAV_CANADA, 		CHARGE_NAV_CANADA_CHG);
		populateCharge(shipment, item, CHARGE_RESIDENTIAL, 		CHARGE_RESIDENTIAL_CHG);
		populateCharge(shipment, item, CHARGE_RET_CHEQUE, 		CHARGE_RET_CHEQUE_CHG);
		populateCharge(shipment, item, CHARGE_AVI_INSURANCE, 	CHARGE_AVI_INSURANCE_CHG);
		populateCharge(shipment, item, CHARGE_SAT_DELIVERY, 	CHARGE_SAT_DELIVERY_CHG);
		populateCharge(shipment, item, CHARGE_SEC_SHIPMENT, 	CHARGE_SEC_SHIPMENT_CHG);
		populateCharge(shipment, item, CHARGE_UNABLE_PICKUP, 	CHARGE_UNABLE_PICKUP_CHG);
		populateCharge(shipment, item, CHARGE_OTHER, 			CHARGE_OTHER_CHG);
		populateCharge(shipment, item, CHARGE_DUTIES_TAX, 		CHARGE_DUTIES_TAX_CHG);
		populateCharge(shipment, item, CHARGE_I18N_FUEL, 		CHARGE_I18N_FUEL_CHG);
		populateCharge(shipment, item, CHARGE_RURAL, 			CHARGE_RURAL_CHG);
		
		//handle taxes
		Double gst_tax = Double.valueOf(getEdiField(GST_CHARGE));
		if(gst_tax!=null && gst_tax.doubleValue()!=0){
			Charge charge = populateCharge(shipment, item, "TAX", "GST", gst_tax);
			shipment.getCharges().add(charge);
		}
		Double hst_tax = Double.valueOf(getEdiField(HST_CHARGE));
		if(hst_tax!=null && hst_tax.doubleValue()!=0){
			StringBuilder stb = new StringBuilder("HST ");
			stb.append(shipment.getToAddress().getProvinceCode());
			Charge charge = populateCharge(shipment, item, "TAX", stb.toString(), hst_tax);
			shipment.getCharges().add(charge);
		}
		Double qst_tax = Double.valueOf(getEdiField(PST_CHARGE));
		if(qst_tax!=null && qst_tax.doubleValue()!=0){
			Charge charge = populateCharge(shipment, item, "TAX", "QST", qst_tax);
			shipment.getCharges().add(charge);
		}
	}

	private void populateCharge(ShippingOrder shipment, EdiItem item, String codeName, String costName) {
		// TODO Auto-generated method stub
		Charge charge = populateCharge(shipment, item, getEdiField(codeName), null, Double.valueOf(getEdiField(costName)));
		if(charge!=null)
			shipment.getCharges().add(charge);
		
	}
	
	private void populateTariffRate(ShippingOrder shipment, Charge charge) {
		LoomisTariffDAO loomisTariffDAO = (LoomisTariffDAO)MmrBeanLocator.getInstance().findBean("loomisTariffDAO");

		LoomisTariff tariff = null;
		
		try{
			tariff = new LoomisTariff(shipment.getServiceId(), shipment.getPackageTypeId().getPackageTypeId(), Math.ceil(shipment.getBilledWeight()), null, shipment.getFromAddress().getPostalCode(), shipment.getToAddress().getPostalCode(), null, null);
			tariff = loomisTariffDAO.getTariffRecord(tariff);
		}
		catch(Exception e){
			tariff = null;
		}
		if(tariff == null){
			log.error("Could not find Loomis tariff record for service: " + (getEdiField(SERVICE_TYPE)));
			return;
		}
		charge.setTariffRate(tariff.getRate());

	}
	
	private int getServiceType( String value ) {
		for (int i=0; i<serviceTypeMap.length; i++)
			if (serviceTypeMap[i][0].equals(value))
				return Integer.parseInt(serviceTypeMap[i][1]);
		return -1;
	}	
	
	@Override
	protected EdiItem populateEdiItem(String fileName) throws Exception {
		// TODO Auto-generated method stub
		String ediInvoiceDate = getEdiField(INVOICE_DATE);
		String ediInvoiceNumber = getEdiField(ACCOUNT_NUMBER) + " " + getEdiField(INVOICE_NUMBER);
		
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
		
		PackageType pkgType = getPackageType(getEdiField(PACKAGE_TYPE).trim());
		if (pkgType != null) {
			ediShipment.setPackageTypeId(pkgType);
			pkg.setType(pkgType.getType());
		}

		return pkg;
	}	
	private PackageType getPackageType(String ediPkgType) {
		// TODO Auto-generated method stub
		String pkgType = null;
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
	
	private Charge populateCharge(ShippingOrder shipment, EdiItem item, String chargeCode, String chargeCodeLevel2, Double cost) {
		// TODO Auto-generated method stub
		if(cost==null || cost==0)
			return null;
		if ( !isNullOrEmpty(chargeCode) ) {
			Charge charge = new Charge();
			
			if(chargeCodeLevel2==null)
				chargeCodeLevel2 = chargeCode;
			
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
				chargeCode = ShiplinxConstants.CHARGE_CODE_LOOMIS_ACC;
				chargeCodeLevel2 = ShiplinxConstants.CHARGE_CODE_LEVEL_2_LOOMIS_OTH;
				chargeName = ShiplinxConstants.CHARGE_CODE_LOOMIS_ACC;
			}
			
			charge.setChargeCode(chargeCode);
			charge.setChargeCodeLevel2(chargeCodeLevel2);
			charge.setName(chargeName);
			charge.setCurrency(getEdiField(CHARGE_CURRENCY));
			charge.setCost(cost); 
			charge.setEdiInvoiceNumber(item.getInvoiceNumber());
			charge.setStatus(ShiplinxConstants.CHARGE_PENDING_RELEASE);
			charge.setDiscountAmount(new Double(0.0));
			charge.setCarrierId(shipment.getCarrierId());
			charge.setCarrierName(ShiplinxConstants.CARRIER_LOOMIS_STRING);
			//COST CURRENCY CODE FIX STARTS HERE
			String currencyCode=null;
			if(shipment.getCurrency()!=null && !(shipment.getCurrency()).equals("")){
				CurrencySymbol costCurrency = shippingService.getSymbolByCurrencycode(shipment.getCurrency());
				charge.setCostcurrency(costCurrency.getId());
			}else{
				String currency=shippingService.getCurrencyByAccountNumber(item.getAccountNumber());
				CurrencySymbol CurrencySymbol = shippingService.getSymbolByCurrencycode(currency);
				charge.setCostcurrency(CurrencySymbol.getId());
				charge.setCurrency(CurrencySymbol.getCurrencyCode());
				shipment.setCurrency(CurrencySymbol.getCurrencyCode());
			}
//COST CURRENCY CODE FIX ENDED HERE			
			
//CHARGE CURRENCY CODE FIX STARTS HERE
			if(shipment.getCustomer()!=null){
				currencyCode=shipment.getDbShipment().getCustomer().getDefaultCurrency();
				}
			if((currencyCode ==null || ("").equals(currencyCode)) && shipment.getCustomerId()!=null){
				Customer customerInfo=customerDAO.getCustomerInfoByCustomerId(shipment.getCustomerId(), shipment.getBusinessId());
				currencyCode=customerInfo.getDefaultCurrency();
				}
			if(currencyCode!=null && !currencyCode.isEmpty()){
				CurrencySymbol Currency = shippingService.getSymbolByCurrencycode(currencyCode);
				charge.setChargecurrency(Currency.getId());
				charge.setCurrency(currencyCode);
				}
				else{
					charge.setChargecurrency(charge.getCostcurrency());
					currencyCode=shipment.getCurrency();
					charge.setCurrency(currencyCode);
					}
			if(currencyCode == null || ("").equals(currencyCode)){
				currencyCode = "CAD";
				charge.setCurrency("CAD");
			}
//CHARGE CURRENCY CODE FIX ENDED HERE
						if (chargeGroupCode != null && 
								(chargeGroupCode.equals(ShiplinxConstants.GROUP_FUEL_CHARGE) ||
								chargeGroupCode.equals(ShiplinxConstants.GROUP_FREIGHT_CHARGE)	)) {
							if(currencyCode!=null && !currencyCode.isEmpty()){
								if(shipment.getCurrency().equals(currencyCode)){
									populateTariffRate(shipment, charge);
									charge.setCharge( applyMarkup(shipment, charge, item));
									charge.setExchangerate(BigDecimal.valueOf(1));
								}
								else{
									Double exchangeRate=shippingService.getExchangeRate(shipment.getCurrency(), currencyCode);
									populateTariffRate(shipment, charge);
									charge.setCharge( applyMarkup(shipment, charge, item)*exchangeRate );
									charge.setTariffRate(charge.getTariffRate()*exchangeRate);
									charge.setCurrency(currencyCode);
									charge.setExchangerate(BigDecimal.valueOf(exchangeRate));
								}
							}
							
						} 
						else {
							if(currencyCode!=null && !currencyCode.isEmpty()){
								if(shipment.getCurrency().equals(currencyCode)){
									charge.setCharge(charge.getCost());
									charge.setTariffRate(charge.getCost());
									charge.setExchangerate(BigDecimal.valueOf(1));
								}
								else{
									Double exchangeRate=shippingService.getExchangeRate(shipment.getCurrency(), currencyCode);
									charge.setCharge(charge.getCost()*exchangeRate );
									charge.setTariffRate(charge.getCost()*exchangeRate);
									charge.setCurrency(currencyCode);
									charge.setExchangerate(BigDecimal.valueOf(exchangeRate));
								}
							}
						}
			charge.setType(ShiplinxConstants.CHARGE_TYPE_ACTUAL);
			return charge;
		}
		
		return null;
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
			if (dbPackage == null) {
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
					ediCharge.setCarrierName(ShiplinxConstants.CARRIER_LOOMIS_STRING);
					addCharge(dbShipment, ediCharge);
				} else {
					dbCharge.setCarrierId(dbShipment.getCarrierId());
					dbCharge.setCarrierName(ShiplinxConstants.CARRIER_LOOMIS_STRING);
					updateCharge(ediCharge, dbCharge);
				}
			}
		}
		
		//for Loomis this should never happen as we are currently not creating the shipments on the system
		//Will need to check/modify once the system is able to create Loomis shipments through their web services
		//update the shipment to include new billed weight
		if(dbShipment.getBilledWeight()==null || dbShipment.getBilledWeight() < ediShipment.getBilledWeight())
			dbShipment.setBilledWeight(ediShipment.getBilledWeight());
		if(dbShipment.getBilledWeightUOM()==null && getEdiField(PRICING_WEIGHT_UOM)!=null){
			if(getEdiField(PRICING_WEIGHT_UOM).equalsIgnoreCase("M"))
				dbShipment.setBilledWeightUOM(ShiplinxConstants.WEIGHT_UNITS_KGS);
			else
				dbShipment.setBilledWeightUOM(ShiplinxConstants.WEIGHT_UNITS_LBS);
		}
		dbShipment.setStatusId((long)ShiplinxConstants.STATUS_DELIVERED);

	}
	@Override
	protected boolean applyCustomExceptionRules(ShippingOrder ediShipment,
			ShippingOrder dbShipment, List<LoggedEvent> events) {
		return false;
	}
	
}
