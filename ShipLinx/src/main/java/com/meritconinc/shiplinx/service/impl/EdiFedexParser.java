package com.meritconinc.shiplinx.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.meritconinc.mmr.utilities.StringUtil;
import com.meritconinc.shiplinx.dao.AddressDAO;
import com.meritconinc.shiplinx.dao.CustomerDAO;
import com.meritconinc.shiplinx.dao.EdiDAO;
import com.meritconinc.shiplinx.model.Address;
import com.meritconinc.shiplinx.model.CarrierChargeCode;
import com.meritconinc.shiplinx.model.Charge;
import com.meritconinc.shiplinx.model.EdiInfo;
import com.meritconinc.shiplinx.model.EdiItem;
import com.meritconinc.shiplinx.model.LoggedEvent;
import com.meritconinc.shiplinx.model.Package;
import com.meritconinc.shiplinx.model.CurrencySymbol;
import com.meritconinc.shiplinx.model.Customer;
import com.meritconinc.shiplinx.model.PackageType;
import com.meritconinc.shiplinx.model.ShippingOrder;
import com.meritconinc.shiplinx.service.LoggedEventService;
import com.meritconinc.shiplinx.service.MarkupManager;
import com.meritconinc.shiplinx.service.ShippingService;
import com.meritconinc.shiplinx.utils.FormattingUtil;
import com.meritconinc.shiplinx.utils.ShiplinxConstants;

public class EdiFedexParser extends EdiParser {
	public static final String INVOICE_NUMBER		="2_Invoice Number"; 
	public static final String INVOICE_DATE			="3_Invoice Date";
	public static final String INVOICE_TYPE			="4_Type";
	public static final String INVOICE_AMOUNT		="6_Inv Charge";
	public static final String ACCOUNT_NUMBER		="8_Bill-to Account";
	public static final String ACCOUNT_COUNTRY		="9_Cntry";	
	public static final String GRD_PREFIX			="13_Grd Prefix";
	public static final String LEAD_SHIPMENT_NUMBER	="14_Tracking Number";
	public static final String TRACKING_NUMBER		="14_Tracking Number";
	
	public static final String SHIP_DATE			="17_Ship Date";
	public static final String SERVICE_TYPE			="18_Svc";
	public static final String PACKAGE_TYPE			="19_Pkg";
	
	public static final String SHIP_REF_NUMBER_1	="23_Ref 1";
	public static final String SHIP_REF_NUMBER_2	="24_Ref 2";
	
	public static final String NET_AMOUNT			="34_Net Chrg";
	public static final String TRANS_CURR_CODE		="35_Curr";	
	
	// Freight
	public static final String FREIGHT_AMOUNT			="37_Freight Amt";
	public static final String DISC_VOL_AMOUNT			="39_Vol Disc Amt";
	public static final String DISC_EARNED_AMOUNT		="41_Earned Disc Amt";
	public static final String DISC_AUTO_AMOUNT			="43_Auto Disc Amt";
	public static final String DISC_PERF_PRICE_AMOUNT	="45_Perf Price Amt";
	
	// Fuel
	public static final String FUEL_CHARGE_6_CODE		="46_Chrg 6";
	public static final String FUEL_AMOUNT				="47_Fuel Amt";
	
	// Charge 7 to Charge 21
	public static final String [][] OTHER_CHARGES = {
														{"48_Chrg 7",		"49_Resi Amt"}, 
														{"50_Chrg 8",		"51_DAS Amt"},
														{"52_Chrg 9",		"53_On-Call Amt"},
														{"54_Chrg 10",		"55_D.V. Amt"},
														{"56_Chrg 11",		"57_Sign Svc Amt"},
														{"58_Chrg 12",		"59_Sat Amt"},
														{"60_Chrg 13",		"61_Addn Hndlg Amt"},
														{"62_Chrg 14",		"63_Adr Corr Amt"},
														{"64_Chrg 15",		"65_GST Amt"},
														{"66_Chrg 16",		"67_Duty Amt"},
														{"68_Chrg 17",		"69_Adv Fee Amt"},
														{"70_Chrg 18",		"71_Orig VAT Amt"},
														{"72_Chrg 19",		"73_Misc 1 Amt"},
														{"74_Chrg 20",		"75_Misc 2 Amt"},
														{"76_Chrg 21",		"77_Misc 3 Amt"}
	};
	
//	public static final String CHARGE_7_CODE			="48_Chrg 7";
//	public static final String CHARGE_AMOUNT_Resi		="49_Resi Amt";	
//	public static final String CHARGE_8_CODE			="50_Chrg 8";
//	public static final String CHARGE_AMOUNT_DAS		="51_DAS Amt";	
//	public static final String CHARGE_9_CODE			="52_Chrg 9";
//	public static final String CHARGE_AMOUNT_OnCall		="53_On-Call Amt";	
//	public static final String CHARGE_10_CODE			="54_Chrg 10";
//	public static final String CHARGE_AMOUNT_DV			="55_D.V. Amt";	
//	public static final String CHARGE_11_CODE			="56_Chrg 11";
//	public static final String CHARGE_AMOUNT_SignSvc	="57_Sign Svc Amt";	
//	public static final String CHARGE_12_CODE			="58_Chrg 12";
//	public static final String CHARGE_AMOUNT_Sat		="59_Sat Amt";	
//	public static final String CHARGE_13_CODE			="60_Chrg 13";
//	public static final String CHARGE_AMOUNT_AddnHndlg	="61_Addn Hndlg Amt";	
//	public static final String CHARGE_14_CODE			="62_Chrg 14";
//	public static final String CHARGE_AMOUNT_AdrCorr	="63_Adr Corr Amt";	
//	public static final String CHARGE_15_CODE			="64_Chrg 15";
//	public static final String CHARGE_AMOUNT_GST		="65_GST Amt";	
//	public static final String CHARGE_16_CODE			="66_Chrg 16";
//	public static final String CHARGE_AMOUNT_Duty		="67_Duty Amt";	
//	public static final String CHARGE_17_CODE			="68_Chrg 17";
//	public static final String CHARGE_AMOUNT_AdvFee		="69_Adv Fee Amt";	
//	public static final String CHARGE_18_CODE			="70_Chrg 18";
//	public static final String CHARGE_AMOUNT_OrigVAT	="71_Orig VAT Amt";	
//	public static final String CHARGE_19_CODE			="72_Chrg 19";
//	public static final String CHARGE_AMOUNT_Misc1		="73_Misc 1 Amt";	
//	public static final String CHARGE_20_CODE			="74_Chrg 20";
//	public static final String CHARGE_AMOUNT_Misc2		="75_Misc 2 Amt";	
//	public static final String CHARGE_21_CODE			="76_Chrg 21";
//	public static final String CHARGE_AMOUNT_Misc3		="77_Misc 3 Amt";	
	public static final String CHARGE_CURRENCY			="79_Exc Curr";
	
	public static final String PACKAGE_QUANTITY		="90_Pcs";
	public static final String ENTERED_WEIGHT		="92_Orig Wt";
	public static final String BILLED_WEIGHT		="91_Bill Wt";	
	public static final String PRICING_WEIGHT_UOM	="94_Pricing Weight UOM";
	public static final String LENGTH				="95_Length";
	public static final String WIDTH				="96_Width";
	public static final String HEIGHT				="97_Height";

	public static final String SENDER_NAME			="103_Shipper Name";
	public static final String SENDER_COMP_NAME		="104_Shipper Company";
	public static final String SENDER_ADDRESS_1		="106_Shipper Address 1";
	public static final String SENDER_ADDRESS_2		="107_Shipper Address 2";
	public static final String SENDER_CITY			="108_Shipper City";
	public static final String SENDER_STATE			="109_ST";
	public static final String SENDER_POSTAL		="110_Postal";
	public static final String SENDER_COUNTRY		="112_Cntry1";	
	public static final String RECEIVER_NAME		="114_Recipient Name";
	public static final String RECEIVER_COMP_NAME	="115_Recipient Company";
	public static final String RECEIVER_ADDRESS_1	="116_Recipient Address 1";
	public static final String RECEIVER_ADDRESS_2	="117_Recipient Address 2";
	public static final String RECEIVER_CITY		="118_Recipient City";
	public static final String RECEIVER_STATE		="119_ST2";
	public static final String RECEIVER_POSTAL		="120_Postal2";
	public static final String RECEIVER_COUNTRY		="121_Cntry2";	
	
	public static final String DATE_FORMAT = "yyyyMMdd"; // 20100621
	
	private static int DEFAULT_SERVICE_TYPE = 1;	//Fedex Priority
	private static final String [][] serviceTypeMap = { 	
														{"01", "1"}, 	//Fedex Priority
														{"06", "2"}, 	//Fedex First Overnight
														{"92", "3"}, 	//Fedex Ground														
														{"05", "4"}, 	//Standard Overnight
														{"03", "5"} 	//2nd Day
													};	

	private static String DEFAULT_PACKAGE_TYPE = "type_package";
	private static final String [][] packageTypeMap = { 	
														{"1", "type_env"}, 	
														{"2", "type_pak"}, 	
														{"6", "type_package"}	
													};	
	private static List<PackageType> packageTypes = null;
	
	public EdiFedexParser(EdiInfo ediInfo, EdiDAO ediDAO, ShippingService shippingService, 
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
	protected EdiItem populateEdiItem(String fileName) throws Exception {
		// TODO Auto-generated method stub
		// In Fedex EDI File, first record is a header, therefore needs to be ignored
		if (this.rowData[0].equalsIgnoreCase("Master EDI No"))
			return null;
		
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

	@Override
	protected ShippingOrder populateShipment(EdiItem item) throws Exception {
		// TODO Auto-generated method stub
		ShippingOrder shipment = new ShippingOrder();
		
		shipment.setBusinessId(item.getBusinessId());
		shipment.setCarrierId(item.getCarrierId());
		shipment.setCarrierName(ShiplinxConstants.CARRIER_FEDEX_STRING);
		shipment.setScheduledShipDate(getDateTime(getEdiField(SHIP_DATE), null, DATE_FORMAT));
		shipment.setMasterTrackingNum(getEdiField(TRACKING_NUMBER));
		shipment.setReferenceOne(getEdiField(SHIP_REF_NUMBER_1));
		shipment.setQuantity(StringUtil.getInteger(getEdiField(PACKAGE_QUANTITY)));
		shipment.setBilledWeight(Float.parseFloat(getEdiField(BILLED_WEIGHT)));
		if(getEdiField(PRICING_WEIGHT_UOM)!=null){
			if(getEdiField(PRICING_WEIGHT_UOM).equalsIgnoreCase("M"))
				shipment.setBilledWeightUOM(ShiplinxConstants.WEIGHT_UNITS_KGS);
			else
				shipment.setBilledWeightUOM(ShiplinxConstants.WEIGHT_UNITS_LBS);
		}
		
		shipment.setCurrency(getEdiField(TRANS_CURR_CODE));
		shipment.setStatusId((long)ShiplinxConstants.STATUS_DELIVERED);

		ShippingOrder dbShipment = findShipment(shipment);
		if(dbShipment==null && !StringUtil.isEmpty(getEdiField(GRD_PREFIX))){ //try finding the shipment by appending prefix to tracking #
			shipment.setMasterTrackingNum(getEdiField(GRD_PREFIX)+getEdiField(TRACKING_NUMBER));
			dbShipment = findShipment(shipment);
		}		
		if(dbShipment != null)
			shipment.setDbShipment(dbShipment);

		Address shipFrom = populateShipFromAddress();
		shipment.setFromAddress(shipFrom);

		Address shipTo = populateShipToAddress();
		shipment.setToAddress(shipTo);
		
		String shipType = getShippingType(shipTo);
		shipment.setServiceId(new Long(this.getServiceType(getEdiField(SERVICE_TYPE), shipType)));

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
		Double freightTariff = StringUtil.getDouble(getEdiField(FREIGHT_AMOUNT));
		Double freightCost = getFreightCost(freightTariff);
		
		// Freight Charge
		Charge freightCharge = null;
		if (freightCost != null && freightCost.doubleValue() != 0) {
			freightCharge = populateCharge(shipment, item, "050", null, freightCost, freightTariff);
			shipment.getCharges().add(freightCharge);
		}
		
		// Fuel Charge
		Double fuelCost = StringUtil.getDouble(getEdiField(FUEL_AMOUNT));
		Charge fuelCharge = populateCharge(shipment, item, "FUEL", "010", fuelCost, null);
		// Markup for Fuel Charge (Reverse calculated from Freight & Fuel Cost)
		if (fuelCharge != null && freightCharge != null) {
			double fuelPercent = fuelCharge.getCost() / freightCharge.getCost();
			double chargeAmount = freightCharge.getCharge() * fuelPercent;
			fuelCharge.setCharge(FormattingUtil.formatDecimalTo2PlacesDouble(chargeAmount));
			
			double tariffRate = freightCharge.getTariffRate() * fuelPercent;
			fuelCharge.setTariffRate(FormattingUtil.formatDecimalTo2PlacesDouble(tariffRate));
			shipment.getCharges().add(fuelCharge);
		}
		
		// Other Charges
		String codeName = "";
		String amountName = "";
		Charge charge = null;
		for (int i=0; i<OTHER_CHARGES.length; i++) {
			codeName = OTHER_CHARGES[i][0];
			amountName = OTHER_CHARGES[i][1];
			charge = populateCharge(shipment, item, getEdiField(codeName), null, StringUtil.getDouble(getEdiField(amountName)), null);
			if (charge != null) {
				shipment.getCharges().add(charge);
			}
		}
	}	
	
	private Charge populateCharge(ShippingOrder shipment, EdiItem item, String chargeCode, String chargeCodeLevel2, Double cost, Double tariff) {
		// TODO Auto-generated method stub
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
				chargeCode = ShiplinxConstants.CHARGE_CODE_FEDEX_ACC;
				chargeCodeLevel2 = ShiplinxConstants.CHARGE_CODE_LEVEL_2_FEDEX_OTH;
				chargeName = ShiplinxConstants.CHARGE_CODE_FEDEX_ACC;
			}
			
//			charge.setCharge(new Double(0.0));
			charge.setDiscountAmount(new Double(0.0));
			charge.setChargeCode(chargeCode);
			charge.setChargeCodeLevel2(chargeCodeLevel2);
			charge.setName(chargeName);
			charge.setCurrency(getEdiField(TRANS_CURR_CODE));
			charge.setCost(cost); 
			charge.setEdiInvoiceNumber(item.getInvoiceNumber());
			charge.setStatus(ShiplinxConstants.CHARGE_PENDING_RELEASE);
			charge.setCarrierId(shipment.getCarrierId());
			charge.setCarrierName(ShiplinxConstants.CARRIER_FEDEX_STRING);
			/*if (chargeGroupCode != null && chargeGroupCode.equals(ShiplinxConstants.GROUP_FREIGHT_CHARGE)) {
				charge.setTariffRate(tariff);
				charge.setCharge( applyMarkup(shipment, charge, item) );
			} else {
				charge.setCharge(charge.getCost());
				charge.setTariffRate(charge.getCost());
			}	*/
			
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

//cHARGE AND TARIF CALCULATION STARTS HERE
			if (chargeGroupCode != null && chargeGroupCode.equals(ShiplinxConstants.GROUP_FREIGHT_CHARGE)) {
				if(currencyCode!=null && !currencyCode.isEmpty()){
					if(shipment.getCurrency().equals(currencyCode)){
						charge.setTariffRate(tariff);
						charge.setCharge(applyMarkup(shipment, charge, item) );
						charge.setExchangerate(BigDecimal.valueOf(1));
						}
						else{
							Double exchangeRate=shippingService.getExchangeRate(shipment.getCurrency(), currencyCode);
							charge.setTariffRate(tariff*exchangeRate);
							charge.setCharge( applyMarkup(shipment, charge, item)*exchangeRate );
							charge.setCurrency(currencyCode);
							charge.setExchangerate(BigDecimal.valueOf(exchangeRate));
							}
					}
				} 
				else{
					if(currencyCode!=null && !currencyCode.isEmpty()){
						if(shipment.getCurrency().equals(currencyCode)){
							charge.setCharge(charge.getCost());
							charge.setTariffRate(charge.getCost());
							charge.setExchangerate(BigDecimal.valueOf(1));
							}
							else{
								Double exchangeRate=shippingService.getExchangeRate(shipment.getCurrency(), currencyCode);
								charge.setCharge(charge.getCost()*exchangeRate);
								charge.setTariffRate(charge.getCost()*exchangeRate);
								charge.setCurrency(currencyCode);
								charge.setExchangerate(BigDecimal.valueOf(exchangeRate));
								}
						}
					}
//CHARGE AND TARIF CALCULATION ENDED HERE
			
			charge.setType(ShiplinxConstants.CHARGE_TYPE_ACTUAL);
			return charge;
		}
		
		return null;
	}	
	
	private Double getFreightCost(Double freightTariff) {
		// TODO Auto-generated method stub
		if (freightTariff != null && freightTariff.doubleValue() != 0) {
			double tariff = freightTariff.doubleValue();
			double discVolAmt 		= StringUtil.getDouble(getEdiField(DISC_VOL_AMOUNT));
			double discEarnedAmt 	= StringUtil.getDouble(getEdiField(DISC_EARNED_AMOUNT));
			double discAutoAmt 		= StringUtil.getDouble(getEdiField(DISC_AUTO_AMOUNT));
			double discPerfPriceAmt = StringUtil.getDouble(getEdiField(DISC_PERF_PRICE_AMOUNT));
			tariff += (discVolAmt + discEarnedAmt + discAutoAmt + discPerfPriceAmt);
			
			return tariff;
		}
		return null;
	}

	private Package populatePackage(EdiItem item, ShippingOrder ediShipment) {
		// TODO Auto-generated method stub
		Package pkg = new Package();
		pkg.setTrackingNumber(getEdiField(TRACKING_NUMBER));
		pkg.setWeight(new BigDecimal(getEdiField(ENTERED_WEIGHT)));
		pkg.setLength(StringUtil.getBigDecimal(getEdiField(LENGTH)));
		pkg.setWidth(StringUtil.getBigDecimal(getEdiField(WIDTH)));
		pkg.setHeight(StringUtil.getBigDecimal(getEdiField(HEIGHT)));		
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
//		pkg.setWeightUOM(getEdiField(PRICING_WEIGHT_UOM));

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
	
	private int getServiceType( String value, String shipType ) {
		for (int i=0; i<serviceTypeMap.length; i++)
			if (serviceTypeMap[i][0].equals(value))	// && serviceTypeMap[i][1].equals(shipType))
				return Integer.parseInt(serviceTypeMap[i][1]);	//[2]);
		return DEFAULT_SERVICE_TYPE;
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
					ediCharge.setCarrierName(ShiplinxConstants.CARRIER_FEDEX_STRING);
					addCharge(dbShipment, ediCharge);
				} else {
					dbCharge.setCarrierId(dbShipment.getCarrierId());
					dbCharge.setCarrierName(ShiplinxConstants.CARRIER_FEDEX_STRING);
					updateCharge(ediCharge, dbCharge);
				}
			}
		}
		
		//update the shipment to include new billed weight
		if(dbShipment.getBilledWeight()==null || dbShipment.getBilledWeight() < ediShipment.getBilledWeight())
			dbShipment.setBilledWeight(ediShipment.getBilledWeight());
		if(dbShipment.getBilledWeightUOM()==null && getEdiField(PRICING_WEIGHT_UOM)!=null){
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
