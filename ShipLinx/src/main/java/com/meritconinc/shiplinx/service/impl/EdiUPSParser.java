package com.meritconinc.shiplinx.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.meritconinc.mmr.utilities.StringUtil;
import com.meritconinc.shiplinx.carrier.ups.dao.UPSCanadaTariffDAO;
import com.meritconinc.shiplinx.carrier.ups.model.UPSZoneDiscount;
import com.meritconinc.shiplinx.dao.AddressDAO;
import com.meritconinc.shiplinx.dao.CustomerDAO;
import com.meritconinc.shiplinx.dao.EdiDAO;
import com.meritconinc.shiplinx.model.Address;
import com.meritconinc.shiplinx.model.CurrencySymbol;
import com.meritconinc.shiplinx.model.Customer;
import com.meritconinc.shiplinx.model.CarrierChargeCode;
import com.meritconinc.shiplinx.model.Charge;
import com.meritconinc.shiplinx.model.EdiInfo;
import com.meritconinc.shiplinx.model.EdiItem;
import com.meritconinc.shiplinx.model.LoggedEvent;
import com.meritconinc.shiplinx.model.Package;
import com.meritconinc.shiplinx.model.PackageType;
import com.meritconinc.shiplinx.model.Service;
import com.meritconinc.shiplinx.model.ShippingOrder;
import com.meritconinc.shiplinx.service.LoggedEventService;
import com.meritconinc.shiplinx.service.MarkupManager;
import com.meritconinc.shiplinx.service.ShippingService;
import com.meritconinc.shiplinx.utils.FormattingUtil;
import com.meritconinc.shiplinx.utils.ShiplinxConstants;

public class EdiUPSParser extends EdiParser { 
	public static final String ACCOUNT_NUMBER		="3_Account Number";
	public static final String ACCOUNT_COUNTRY		="4_Account Country";
	public static final String INVOICE_DATE			="5_Invoice Date";
	public static final String INVOICE_NUMBER		="6_Invoice Number";
	public static final String INVOICE_CURRENCY		="10_Invoice Currency Code";
	public static final String INVOICE_AMOUNT		="11_Invoice Amount";
	public static final String TRANSACTION_DATE		="12_Transaction Date";
	public static final String LEAD_SHIPMENT_NUMBER	="14_Lead Shipment Number";
	public static final String SHIP_REF_NUMBER_1	="16_Shipment Reference Number 1";
	public static final String SHIP_REF_NUMBER_2	="17_Shipment Reference Number 2";
	public static final String PACKAGE_QUANTITY		="19_Package Quantity";
	public static final String TRACKING_NUMBER		="21_Tracking Number";
	public static final String PACKAGE_REF_NUMBER_1	="22_Package Reference Number 1";
	public static final String PACKAGE_REF_NUMBER_2	="23_Package Reference Number 2";
	public static final String PACKAGE_REF_NUMBER_3	="24_Package Reference Number 3";
	public static final String ENTERED_WEIGHT		="27_Entered Weight";
	public static final String BILLED_WEIGHT		="29_Billed Weight";
	public static final String BILLED_WEIGHT_UOM	="30_Billed Weight UOM";
	public static final String CONTAINER_TYPE		="31_Container Type";
	public static final String PACKAGE_DIMENSIONS	="33_Package Dimensions";
	public static final String ZONE					="34_Zone";
	public static final String CHARGE_CODE_CATEGORY	="35_Charge Code Category";
	public static final String CHARGE_CLASS_CODE	="44_Charge Classification Code";
	public static final String CHARGE_DESC_CODE		="45_Charge Description Code";
	public static final String CHARGE_DESC			="46_Charge Description";
	public static final String TRANS_CURR_CODE		="51_Transaction Currency Code";
	public static final String INCENTIVE_AMOUNT		="52_Incentive Amount";
	public static final String NET_AMOUNT			="53_Net Amount";
	public static final String SENDER_NAME			="67_Sender Name";
	public static final String SENDER_COMP_NAME		="68_Sender Company Name";
	public static final String SENDER_ADDRESS_1		="69_Sender Address Line 1";
	public static final String SENDER_ADDRESS_2		="70_Sender Address Line 2";
	public static final String SENDER_CITY			="71_Sender City";
	public static final String SENDER_STATE			="72_Sender State";
	public static final String SENDER_POSTAL		="73_Sender Postal";
	public static final String SENDER_COUNTRY		="74_Sender Country";	
	public static final String RECEIVER_NAME		="75_Receiver Name";
	public static final String RECEIVER_COMP_NAME	="76_Receiver Company Name";
	public static final String RECEIVER_ADDRESS_1	="77_Receiver Address Line 1";
	public static final String RECEIVER_ADDRESS_2	="78_Receiver Address Line 2";
	public static final String RECEIVER_CITY		="79_Receiver City";
	public static final String RECEIVER_STATE		="80_Receiver State";
	public static final String RECEIVER_POSTAL		="81_Receiver Postal";
	public static final String RECEIVER_COUNTRY		="82_Receiver Country";
	
	public static final String DATE_FORMAT = "yyyy-MM-dd"; // 2008-07-19

	private static final Logger log = LogManager.getLogger(EdiUPSParser.class);

//	private static final Logger log = LogManager.getLogger(EdiUPSParser.class);

	
	
	private static final String [][] serviceTypeMap = { 	
											{"203", "361-362,381-387,601-610,			651-652,655,656-661"}, //Worldwide Expedited
											{"207", "500-512,561-562,581-587,801-810,	751-752,755,756-762"}, //UPS Saver
											{"206", "291-297"												}, //Three-Day Select
											{"205", "491-497,700-712"										}, //Express Early AM
											{"204", "200-212,241-247,					365-370"			}, //Standard
											{"202", "461-462,481-487,901-910,			851-852,855,856-862"}, //Worldwide Express
											{"201", "300-312"												}, //Expedited
											{"200", "400-412"												}  //Express
													};

//	1, 'env', 'type_env', 'env description'
//	2, 'pak', 'type_pak', 'pak description'
//	3, 'package', 'type_package', 'pacakge description'
//	4, 'pallet', 'type_pallet', 'pallate description'	
	private static final String [][] packageTypeMap = { 	
														{"ALL", "type_pallet"}, 
														{"CHQ", "type_pallet"}, 
														{"DOC", "type_pallet"},
														{"LTR", "type_env"},
														{"NDC", "type_pallet"},
														{"PAK", "type_pallet"},
														{"PKG", "type_package"},
														{"UNC", "type_pallet"},
														{"VBX", "type_pallet"},
														{"V10", "type_pallet"},
														{"D10", "type_pallet"},
														{"N10", "type_pallet"},
														{"D25", "type_pallet"},
														{"N25", "type_pallet"}	
													};	

	private static List<PackageType> packageTypes = null;
	
	private UPSCanadaTariffDAO upsCanadaTariffDAO;
	
	
	public EdiUPSParser(EdiInfo ediInfo, EdiDAO ediDAO, ShippingService shippingService, 
			CustomerDAO customerDAO, AddressDAO addressDAO, MarkupManager markupManagerService,
			UPSCanadaTariffDAO upsCanadaTariffDAO,
			LoggedEventService loggedService) {
		super(ediInfo, ediDAO, shippingService, customerDAO, addressDAO, 
				markupManagerService, loggedService);
		this.upsCanadaTariffDAO = upsCanadaTariffDAO;
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
		shipment.setCarrierName(ShiplinxConstants.CARRIER_UPS_STRING);
		shipment.setScheduledShipDate(getDateTime(getEdiField(TRANSACTION_DATE), null, DATE_FORMAT));
		shipment.setMasterTrackingNum(getEdiField(LEAD_SHIPMENT_NUMBER));
		shipment.setReferenceOne(getEdiField(SHIP_REF_NUMBER_1));
		shipment.setReferenceTwo(getEdiField(SHIP_REF_NUMBER_2));
		shipment.setQuantity(Integer.parseInt(getEdiField(PACKAGE_QUANTITY)));
		shipment.setCurrency(getEdiField(INVOICE_CURRENCY));
		shipment.setStatusId((long)ShiplinxConstants.STATUS_DELIVERED);

		Package pkg = populatePackage(item, shipment);
		shipment.getPackages().add(pkg);
		
		// how to set package id in shipment
		
		ShippingOrder dbShipment = findShipment(shipment);
		if(dbShipment != null)
			shipment.setDbShipment(dbShipment);
		
		shipment.setFromZone(getEdiField(ZONE)); //This will be use to findout service
		
		//first try and set service id from dbShipment
		if(shipment.getDbShipment()!=null && shipment.getDbShipment().getServiceId() > 0)
			shipment.setServiceId(shipment.getDbShipment().getServiceId());
		else
			shipment.setServiceId(new Long(this.getServiceType(shipment.getFromZone())));
		
		Address shipFrom = populateShipFromAddress();
		shipment.setFromAddress(shipFrom);

		Address shipTo = populateShipToAddress();
		shipment.setToAddress(shipTo);	
		
//		shipment.setCustomerId(getCustomerId(item.getAccountNumber()));
		populateCustomer(shipment, item.getAccountNumber());
		
		Charge charge = populateCharge(shipment, item);
		if(charge!=null && charge.getCost()>0) //if cost is 0, then don't add charge. Example: Freight Collect
			shipment.getCharges().add(charge); 

		if(shipment.getCharges()!=null && shipment.getCharges().size()>0)
					 {
		shipment.setCurrency(shipment.getCharges().get(0).getCurrency());
					 }
		//trying to determine the billed weight
		if(getEdiField(BILLED_WEIGHT_UOM)!=null && getEdiField(BILLED_WEIGHT_UOM).length()>0){
			if(getEdiField(BILLED_WEIGHT_UOM).equalsIgnoreCase("L"))
					shipment.setBilledWeightUOM(ShiplinxConstants.WEIGHT_UNITS_LBS);
			else
				if(getEdiField(BILLED_WEIGHT_UOM).equalsIgnoreCase("K"))
					shipment.setBilledWeightUOM(ShiplinxConstants.WEIGHT_UNITS_KGS);
				
		}
		if(shipment.getBilledWeight()==null)
			shipment.setBilledWeight(new Float(0));
		if(getEdiField(CHARGE_CLASS_CODE)!=null && getEdiField(CHARGE_CLASS_CODE).equalsIgnoreCase("FRT") && getEdiField(CHARGE_DESC_CODE)!=null && getEdiField(CHARGE_CLASS_CODE).length()>0){
			//as per RizM oberservation, billed weights are sent only when this condition is met
			//also, if there is an adjustment, identified by "charge code category" value "ADJ", then this line is the entire adjustment for all packages in shipment
			shipment.setBilledWeight(Float.parseFloat(getEdiField(BILLED_WEIGHT)));	
		}
		
		return shipment;
	}



	private Address populateShipToAddress() {
		// TODO Auto-generated method stub
		Address shipTo = new Address();
		shipTo.setDefaultFromAddress(false);
		shipTo.setDefaultToAddress(false);
		shipTo.setResidential(true);		
		shipTo.setContactName(getEdiField(RECEIVER_NAME));
		shipTo.setAbbreviationName(getEdiField(RECEIVER_COMP_NAME));
		shipTo.setAddress1(getEdiField(RECEIVER_ADDRESS_1));
		shipTo.setAddress2(getEdiField(RECEIVER_ADDRESS_2));
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
		shipFrom.setContactName(getEdiField(SENDER_NAME));
		shipFrom.setAbbreviationName(getEdiField(SENDER_COMP_NAME));
		shipFrom.setAddress1(getEdiField(SENDER_ADDRESS_1));
		shipFrom.setAddress2(getEdiField(SENDER_ADDRESS_2));
		shipFrom.setCity(getEdiField(SENDER_CITY));
		shipFrom.setProvinceCode(getEdiField(SENDER_STATE));
		shipFrom.setPostalCode(getEdiField(SENDER_POSTAL));
		shipFrom.setCountryCode(getEdiField(SENDER_COUNTRY));
		
		if(shipFrom.getCountryCode()==null || shipFrom.getCountryCode().length()==0){ //from address not in EDI file. Make assumption that shipment origin is same as country of account
			shipFrom.setCountryCode(getEdiField(ACCOUNT_COUNTRY));
		}

		return shipFrom;
	}

	private Charge populateCharge(ShippingOrder shipment, EdiItem item) {
		// TODO Auto-generated method stub
		Charge charge = new Charge();
		String chargeGroupCode = null;
		
		String chargeCode = getEdiField(CHARGE_CLASS_CODE);
		String chargeCodeLevel2 = getEdiField(CHARGE_DESC_CODE);
		
		//UPS is sending charge class code as FRT and empty charge desc code in some cases, we have added are record in carrier_charge_code table with FRT as charge code and charge code level 2 
		if(!StringUtil.isEmpty(chargeCode) && chargeCode.equalsIgnoreCase(ShiplinxConstants.GROUP_FREIGHT_CHARGE) && StringUtil.isEmpty(chargeCodeLevel2)){
			chargeCodeLevel2 = ShiplinxConstants.GROUP_FREIGHT_CHARGE;
		}
			
		
		List<CarrierChargeCode> chargeCodes = shippingService.getChargeListByCarrierAndCodes(
									item.getCarrierId(), chargeCode, chargeCodeLevel2);
		if (chargeCodes != null && chargeCodes.size() > 0) {
			chargeCode = chargeCodes.get(0).getChargeCode();
			chargeCodeLevel2 = chargeCodes.get(0).getChargeCodeLevel2();
			chargeGroupCode = chargeCodes.get(0).getGroupCode(); 			
		} else {
			if(!(chargeCode.contains("TAX")))
						{
			chargeCode = ShiplinxConstants.CHARGE_CODE_UPS_ACC;
			chargeCodeLevel2 = ShiplinxConstants.CHARGE_CODE_LEVEL_2_UPS_OTH;
						}
		}
		
		//if this is HST,  we need to set to appropriate HST (ON,BC,NS,NB,NF) as the charge code in UPS EDI file does not indicate which HST
		if(chargeCodeLevel2.equalsIgnoreCase(ShiplinxConstants.TAX_HST)){
			chargeCodeLevel2 = new String(chargeCodeLevel2 + " " + shipment.getToAddress().getProvinceCode());
			
			chargeCodes = this.shippingService.getChargeListByCarrierAndCodes(
					item.getCarrierId(), chargeCode, chargeCodeLevel2);

			if(chargeCodes==null || chargeCodes.size()==0){
				chargeCodeLevel2 = new String(ShiplinxConstants.TAX_HST + " " + shipment.getFromAddress().getProvinceCode());
				chargeCodes = this.shippingService.getChargeListByCarrierAndCodes(
						item.getCarrierId(), chargeCode, chargeCodeLevel2);
			}
			
			if (chargeCodes != null && chargeCodes.size() > 0) {
				chargeCode = chargeCodes.get(0).getChargeCode();
				chargeCodeLevel2 = chargeCodes.get(0).getChargeCodeLevel2();
				chargeGroupCode = chargeCodes.get(0).getGroupCode(); 			
			}		
		}
		
//		charge.setCharge(new Double(0.0));
		charge.setChargeCode(chargeCode);
		charge.setChargeCodeLevel2(chargeCodeLevel2);
		charge.setName(getEdiField(CHARGE_DESC));
		charge.setCurrency(getEdiField(TRANS_CURR_CODE));
		charge.setDiscountAmount(Double.valueOf(getEdiField(INCENTIVE_AMOUNT))); // 
		charge.setCost(Double.valueOf(getEdiField(NET_AMOUNT))); 
		
		if(chargeGroupCode!=null && chargeGroupCode.equals(ShiplinxConstants.GROUP_FREIGHT_CHARGE) && (charge.getDiscountAmount()==null || charge.getDiscountAmount().doubleValue()==0)){
			//there is a bug in the UPS EDI file whereby the adj scc frt charge for a shipment does NOT contain the discount amount
			//as a result we cannot correctly calculate the tariff and hence the charge
			//we need to try and determine the discount by going through the previous charges for this shipment in the shipment record and determine what the discount levels were for freight
			//this will only work if the shipment already exists in the database, ie. dbShipment is set
			if(shipment.getDbShipment()!=null)
				correctFreightDiscount(shipment.getDbShipment(), charge);
		}
		
		//Tariff=DiscountAmount+cost
		charge.setTariffRate(FormattingUtil.add(charge.getCost(), charge.getDiscountAmount()).doubleValue());
		charge.setEdiInvoiceNumber(item.getInvoiceNumber());
		charge.setStatus(ShiplinxConstants.CHARGE_PENDING_RELEASE);
		charge.setCarrierId(shipment.getCarrierId());
		charge.setCarrierName(ShiplinxConstants.CARRIER_UPS_STRING);
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

		if (chargeGroupCode != null && (chargeGroupCode.equals(ShiplinxConstants.GROUP_FUEL_CHARGE) ||
										chargeGroupCode.equals(ShiplinxConstants.GROUP_FREIGHT_CHARGE)	)) {
			/*charge.setCharge( applyMarkup(shipment, charge, item) );
		} else {
			charge.setCharge(charge.getCost());*/
			if(currencyCode!=null && !currencyCode.isEmpty()){
								if(shipment.getCurrency().equals(currencyCode)){
									charge.setCharge( applyMarkup(shipment, charge, item) );
									charge.setExchangerate(BigDecimal.valueOf(1));
								}
								else{
									Double exchangeRate=shippingService.getExchangeRate(shipment.getCurrency(), currencyCode);
									charge.setCharge( applyMarkup(shipment, charge, item)*exchangeRate );
									charge.setCurrency(currencyCode);
									charge.setExchangerate(BigDecimal.valueOf(exchangeRate));
								}
							}
							
						} 
						else {
							if(currencyCode!=null && !currencyCode.isEmpty()){
								if(shipment.getCurrency().equals(currencyCode)){
									charge.setCharge(charge.getCost());
									charge.setExchangerate(BigDecimal.valueOf(1));
								}
								else{
									Double exchangeRate=shippingService.getExchangeRate(shipment.getCurrency(), currencyCode);
									charge.setCharge(charge.getCost()*exchangeRate );
									charge.setCurrency(currencyCode);
									charge.setExchangerate(BigDecimal.valueOf(exchangeRate));
								}
							}
		}
		charge.setType(ShiplinxConstants.CHARGE_TYPE_ACTUAL);
		return charge;
	}

	//We find a previous freight charge and determine the discount that was applied, and then set the discount for the new charge accordingly
	private void correctFreightDiscount(ShippingOrder shipment, Charge charge){
		List<Charge> existingCharges = shipment.getActualCharges();
		double discountAppliedPerc = 0;
		
		for(Charge c: existingCharges){
			if(c.getChargeCode().equalsIgnoreCase(ShiplinxConstants.GROUP_FREIGHT_CHARGE)){
				double cost = c.getCost().doubleValue();
				double tariff = c.getTariffRate().doubleValue();
				discountAppliedPerc = (tariff-cost)*100/tariff;
				break; //found the freight charge
			}
		}
		double tariff = (charge.getCost().doubleValue() * 100) / (100 - discountAppliedPerc);
		charge.setDiscountAmount(FormattingUtil.add(tariff, -1*charge.getCost().doubleValue()).doubleValue());
		
	}
	
	private Package populatePackage(EdiItem item, ShippingOrder ediShipment) {
		// TODO Auto-generated method stub
		Package pkg = new Package();
		pkg.setTrackingNumber(getEdiField(TRACKING_NUMBER));
		pkg.setReference1(getEdiField(PACKAGE_REF_NUMBER_1));
		pkg.setReference2(getEdiField(PACKAGE_REF_NUMBER_2));
		pkg.setReference3(getEdiField(PACKAGE_REF_NUMBER_3));
		pkg.setWeight(new BigDecimal(getEdiField(ENTERED_WEIGHT)));
		pkg.setBilledWeight(Float.parseFloat(getEdiField(BILLED_WEIGHT)));
		
		PackageType pkgType = getPackageType(getEdiField(CONTAINER_TYPE));
		if (pkgType != null) {
			ediShipment.setPackageTypeId(pkgType);
			pkg.setType(pkgType.getType());
		}
		
		if(getEdiField(PACKAGE_DIMENSIONS)!=null && getEdiField(PACKAGE_DIMENSIONS).length()>0)
			pkg.setDimmedString(getEdiField(PACKAGE_DIMENSIONS));
		return pkg;
	}

	@Override
	protected EdiItem populateEdiItem(String fileName) throws Exception {
		// TODO Auto-generated method stub
		String ediInvoiceDate = getEdiField(INVOICE_DATE);
		String ediInvoiceNumber = getEdiField(INVOICE_NUMBER);
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
	private int getServiceType( String value ) {
		
		long service_id = -1;
		
		String trackingNumber = getEdiField(LEAD_SHIPMENT_NUMBER);
		String serviceCode = null;

		if(value==null || value.length()==0){
			log.info("Returning -1 for zone : " + value);
			return (int)service_id;
		}
		
		log.info("Zone : " + value);
		
//		WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext((ServletContext)ActionContext.getContext().get(ServletActionContext.SERVLET_CONTEXT));
//		UPSCanadaTariffDAO upsCanadaTariffDAO = (UPSCanadaTariffDAO)context.getBean("upsCanadaTariffDAO");
		UPSZoneDiscount upsZoneDiscount = new UPSZoneDiscount();
		upsZoneDiscount.setZone(value);
		upsZoneDiscount.setBusinessId(new Long(0)); 
		upsZoneDiscount.setPackageId(new Long(0));
		upsZoneDiscount.setAccountCountry(getEdiField(ACCOUNT_COUNTRY));
		upsZoneDiscount = upsCanadaTariffDAO.getDiscountByZoneAndPackage(upsZoneDiscount);

		
		if(upsZoneDiscount!=null)
			service_id = upsZoneDiscount.getServiceId();
		
		if(service_id==-1){ //could not determine from the ups_zone_bus_discount table, in case of US for example
			//try to determine from the tracking # as per http://osiris.978.org/~alex/ups.html
			if(trackingNumber!=null && trackingNumber.length() >=10){
				serviceCode = trackingNumber.substring(8, 10);	
				Service s = new Service();
				s.setCarrierId(ediInfo.getCarrierId());
				s.setCode(serviceCode);
				s = upsCanadaTariffDAO.getServiceCodeByService(s);
				if(s!=null)
					return s.getId().intValue();
			}
		}
		
		
		log.info("Service id mapped for zone " + value + " : " + service_id);
		
		
		
		return (int)service_id;
	}
	
	//override EDIParser method
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
		
		//if service id is set in existing shipment, and not in edi record, then set it
		if(dbShipment.getServiceId()>0 && ediShipment.getServiceId()<=0)
			ediShipment.setServiceId(dbShipment.getServiceId());

		// Update Charge
		if(ediShipment.getCharges().size()==0)
			return;
		Charge ediCharge = ediShipment.getCharges().get(0); //this makes the assumption that only 1 charge per record, not the case for DHL and Loomis, modified DHL implementation
		if (ediCharge != null) {
			
			//If the charge is generic HST, try to convert to specific HST (ON,BC,etc.). this is happening in UPS EDI file when the second/adjustment set of HST charges are coming in and address columns are blank
			if(ediCharge.getChargeCodeLevel2().trim().equalsIgnoreCase(ShiplinxConstants.TAX_HST) && dbShipment!=null){
				String taxName = ShiplinxConstants.TAX_HST + " " + dbShipment.getToAddress().getProvinceCode();
				ediCharge.setChargeCodeLevel2(taxName);				
			}
			
			Charge dbCharge = findCharge(dbShipment, ediCharge);
			if (dbCharge == null) {
				ediCharge.setStatus(ShiplinxConstants.CHARGE_PENDING_RELEASE);
				ediCharge.setCarrierId(dbShipment.getCarrierId());
				ediCharge.setCarrierName(ShiplinxConstants.CARRIER_UPS_STRING);
				addCharge(dbShipment, ediCharge);
			} else {
				dbCharge.setStatus(ShiplinxConstants.CHARGE_PENDING_RELEASE);
				dbCharge.setCarrierId(dbShipment.getCarrierId());
				dbCharge.setCarrierName(ShiplinxConstants.CARRIER_UPS_STRING);
				updateCharge(ediCharge, dbCharge);
			}
		}
		
		if(dbShipment.getBilledWeight()==null)
			dbShipment.setBilledWeight(new Float(0));
		if(getEdiField(CHARGE_CLASS_CODE)!=null && getEdiField(CHARGE_CLASS_CODE).equalsIgnoreCase("FRT") && getEdiField(CHARGE_DESC_CODE)!=null && getEdiField(CHARGE_CLASS_CODE).length()>0){
			if(getEdiField(CHARGE_CODE_CATEGORY)!=null && getEdiField(CHARGE_CODE_CATEGORY).equalsIgnoreCase("ADJ")){
				dbShipment.setBilledWeight(Float.parseFloat(getEdiField(BILLED_WEIGHT)));						
			}
			else{ //in this case this is the billed weight returned for a package, not an adjustment
				//dbShipment.setBilledWeight(dbShipment.getBilledWeight() + Float.parseFloat(getEdiField(BILLED_WEIGHT)));
				dbShipment.setBilledWeight(Float.parseFloat(getEdiField(BILLED_WEIGHT)));
			}
		}
		if(dbShipment.getBilledWeightUOM()==null){
			if(getEdiField(BILLED_WEIGHT_UOM)!=null && getEdiField(BILLED_WEIGHT_UOM).length()>0){
				if(getEdiField(BILLED_WEIGHT_UOM).equalsIgnoreCase("L"))
					dbShipment.setBilledWeightUOM(ShiplinxConstants.WEIGHT_UNITS_LBS);
				else
					if(getEdiField(BILLED_WEIGHT_UOM).equalsIgnoreCase("K"))
						dbShipment.setBilledWeightUOM(ShiplinxConstants.WEIGHT_UNITS_KGS);
					
			}
		}
		
		//copy the zone over
		if(dbShipment.getFromZone()==null && getEdiField(ZONE)!=null)
			dbShipment.setFromZone(getEdiField(ZONE));

		shippingService.applyAdditionalHandling(dbShipment, null, ShiplinxConstants.CHARGE_TYPE_ACTUAL);
		
		if (!applyExceptionsRules(ediShipment, dbShipment)) {
			// No exception rules were applied
			dbShipment.setStatusId((long)ShiplinxConstants.STATUS_DELIVERED);
		}
		this.shippingService.updateShippingOrder(dbShipment);

	}
	@Override
	protected boolean applyCustomExceptionRules(ShippingOrder ediShipment,
			ShippingOrder dbShipment, List<LoggedEvent> events) {
		return false;
	}

}
