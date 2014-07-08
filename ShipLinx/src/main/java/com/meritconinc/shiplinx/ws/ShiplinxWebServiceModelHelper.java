package com.meritconinc.shiplinx.ws;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.Duration;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.meritconinc.mmr.utilities.MessageUtil;
import com.meritconinc.mmr.utilities.StringUtil;
import com.meritconinc.shiplinx.model.Address;
import com.meritconinc.shiplinx.model.CarrierChargeCode;
import com.meritconinc.shiplinx.model.Charge;
import com.meritconinc.shiplinx.model.CustomsInvoice;
import com.meritconinc.shiplinx.model.CustomsInvoiceProduct;
import com.meritconinc.shiplinx.model.Package;
import com.meritconinc.shiplinx.model.PackageType;
import com.meritconinc.shiplinx.model.Pickup;
import com.meritconinc.shiplinx.model.Rating;
import com.meritconinc.shiplinx.model.Service;
import com.meritconinc.shiplinx.model.ShippingOrder;
import com.meritconinc.shiplinx.service.ShippingService;
import com.meritconinc.shiplinx.utils.ShiplinxConstants;
import com.meritconinc.shiplinx.ws.proxy.datatypes.AccessorialWSType;
import com.meritconinc.shiplinx.ws.proxy.datatypes.AddressWSType;
import com.meritconinc.shiplinx.ws.proxy.datatypes.CODWSType;
import com.meritconinc.shiplinx.ws.proxy.datatypes.CarrierWSType;
import com.meritconinc.shiplinx.ws.proxy.datatypes.ChargeListWSType;
import com.meritconinc.shiplinx.ws.proxy.datatypes.ChargeWSType;
import com.meritconinc.shiplinx.ws.proxy.datatypes.ChargesWSType;
import com.meritconinc.shiplinx.ws.proxy.datatypes.CustomsInvoiceProductWSType;
import com.meritconinc.shiplinx.ws.proxy.datatypes.CustomsInvoiceWSType;
import com.meritconinc.shiplinx.ws.proxy.datatypes.ErrorWSType;
import com.meritconinc.shiplinx.ws.proxy.datatypes.ErrorsWSType;
import com.meritconinc.shiplinx.ws.proxy.datatypes.MetaDataWSType;
import com.meritconinc.shiplinx.ws.proxy.datatypes.OrderWSType;
import com.meritconinc.shiplinx.ws.proxy.datatypes.PODWSType;
import com.meritconinc.shiplinx.ws.proxy.datatypes.PackageWSType;
import com.meritconinc.shiplinx.ws.proxy.datatypes.PackagesWSType;
import com.meritconinc.shiplinx.ws.proxy.datatypes.PickupWSType;
import com.meritconinc.shiplinx.ws.proxy.datatypes.RatingWSType;
import com.meritconinc.shiplinx.ws.proxy.datatypes.RatingsWSType;
import com.meritconinc.shiplinx.ws.proxy.datatypes.ServiceWSType;
import com.meritconinc.shiplinx.ws.proxy.datatypes.StatusWSType;
import com.meritconinc.shiplinx.ws.proxy.datatypes.WeightWSType;

public class ShiplinxWebServiceModelHelper {
	private static final Logger log = LogManager.getLogger(ShiplinxWebServiceModelHelper.class);
	public static final String [] SIGN_REQ_VALUES = {"YES", "NO", "ADULT"};
	public static final String [] PACKAGE_TYPES = {"ENV", "PACK", "PACKAGE", "PALLET"};
	public static final int PACKAGE_TYPE_PALLET_ID = 4;		// 4, 'pallet', 'type_pallet', 'pallate description'
	public static final int PACKAGE_TYPE_PACKAGE_ID = 3;	// 3, 'package', 'type_package', 'pacakge description'
	private static final String [][] ERRORS = 	
				{  
					{"100", "ws.error.invalid.package.type"},				// INVALID_PACKAGE_TYPE
					{"101", "ws.error.delivery.tailgate.missing"},			// DELIVERY_TAILGATE_MISSING
					{"102", "ws.error.inside.delivery.missing"},			// INSIDE_DELIVERY_MISSING
					{"103", "ws.error.package.list.missing"},				// PACKAGE_LIST_MISSING
					{"104", "ws.error.from.address.missing"},				// FROM_ADDRESS_MISSING
					{"105", "ws.error.from.address.comp.name.missing"},		// FROM_ADDRESS_COMP_NAME_MISSING
					{"106", "ws.error.from.address.address1.missing"},		// FROM_ADDRESS_ADDRESS1_MISSING
					{"107", "ws.error.from.address.city.missing"},			// FROM_ADDRESS_CITY_MISSING
					{"108", "ws.error.from.address.contact.name.missing"},	// FROM_ADDRESS_CONTACT_NAME_MISSING
					{"109", "ws.error.from.address.country.code.missing"},	// FROM_ADDRESS_COUNTRY_CODE_MISSING
					{"110", "ws.error.from.address.phone.no.missing"},		// FROM_ADDRESS_PHONE_NO_MISSING
					{"111", "ws.error.from.address.postal.code.missing"},	// FROM_ADDRESS_POSTAL_CODE_MISSING
					{"112", "ws.error.to.address.missing"},					// TO_ADDRESS_MISSING
					{"113", "ws.error.to.address.comp.name.missing"},		// TO_ADDRESS_COMP_NAME_MISSING
					{"114", "ws.error.to.address.address1.missing"},		// TO_ADDRESS_ADDRESS1_MISSING
					{"115", "ws.error.to.address.city.missing"},			// TO_ADDRESS_CITY_MISSING
					{"116", "ws.error.to.address.contact.name.missing"},	// TO_ADDRESS_CONTACT_NAME_MISSING
					{"117", "ws.error.to.address.country.code.missing"},	// TO_ADDRESS_COUNTRY_CODE_MISSING
					{"118", "ws.error.to.address.phone.no.missing"},		// TO_ADDRESS_PHONE_NO_MISSING
					{"119", "ws.error.to.address.postal.code.missing"},		// TO_ADDRESS_POSTAL_CODE_MISSING
					{"120", "ws.error.package.desc.missing"},				// PACKAGE_DESC_MISSING
					{"121", "ws.error.freight.class.missing"},				// FREIGHT_CLASS_MISSING
					{"122", "ws.error.package.height.missing"},				// PACKAGE_HEIGHT_MISSING
					{"123", "ws.error.package.length.missing"},				// PACKAGE_LENGTH_MISSING
					{"124", "ws.error.package.width.missing"},				// PACKAGE_WIDTH_MISSING
					{"125", "ws.error.package.weight.missing"},				// PACKAGE_WEIGHT_MISSING
					{"126", "ws.error.invalid.api.customer.info"},			// INVALID_API_CUSTOMER_INFO
					{"127", "ws.error.system.error"},						// SYSTEM_ERROR
					{"128", "ws.error.pickup.tailgate.missing"},			// PICKUP_TAILGATE_MISSING
					{"129", "ws.error.customer.carrier.not.found"},			// CUSTOMER_CARRIER_NOT_FOUND
					{"130", "ws.error.service.code.missing"},				// SERVICE_CODE_MISSING
					{"131", "ws.error.service.order.not.found"}				// ORDER_NOT_FOUND
				};
	public static final String CARRIER_ERROR_CODE				= "1000";
	
	private static final int INVALID_PACKAGE_TYPE 				= 0;
	private static final int DELIVERY_TAILGATE_MISSING 			= 1;
	private static final int INSIDE_DELIVERY_MISSING 			= 2;
	private static final int PACKAGE_LIST_MISSING 				= 3;
	private static final int FROM_ADDRESS_MISSING 				= 4;
	private static final int FROM_ADDRESS_COMP_NAME_MISSING 	= 5;
	private static final int FROM_ADDRESS_ADDRESS1_MISSING 		= 6;
	private static final int FROM_ADDRESS_CITY_MISSING 			= 7;
	private static final int FROM_ADDRESS_CONTACT_NAME_MISSING 	= 8;
	private static final int FROM_ADDRESS_COUNTRY_CODE_MISSING 	= 9;
	private static final int FROM_ADDRESS_PHONE_NO_MISSING 		= 10;
	private static final int FROM_ADDRESS_POSTAL_CODE_MISSING 	= 11;
	private static final int TO_ADDRESS_MISSING 				= 12;
	private static final int TO_ADDRESS_COMP_NAME_MISSING 		= 13;
	private static final int TO_ADDRESS_ADDRESS1_MISSING 		= 14;
	private static final int TO_ADDRESS_CITY_MISSING 			= 15;
	private static final int TO_ADDRESS_CONTACT_NAME_MISSING 	= 16;
	private static final int TO_ADDRESS_COUNTRY_CODE_MISSING 	= 17;
	private static final int TO_ADDRESS_PHONE_NO_MISSING  		= 18;
	private static final int TO_ADDRESS_POSTAL_CODE_MISSING 	= 19;
	private static final int PACKAGE_DESC_MISSING 				= 20;
	private static final int FREIGHT_CLASS_MISSING 				= 21;
	private static final int PACKAGE_HEIGHT_MISSING 			= 22;
	private static final int PACKAGE_LENGTH_MISSING 			= 23;
	private static final int PACKAGE_WIDTH_MISSING 				= 24;
	private static final int PACKAGE_WEIGHT_MISSING 			= 25;
	public static final int INVALID_API_CUSTOMER_INFO 			= 26;
	public static final int SYSTEM_ERROR 						= 27;
	private static final int PICKUP_TAILGATE_MISSING 			= 28;
	public static final int CUSTOMER_CARRIER_NOT_FOUND			= 29;
	public static final int SERVICE_CODE_MISSING				= 30;
	public static final int ORDER_NOT_FOUND 					= 31;
	
	public static ShippingOrder convertOrder(OrderWSType o, List<PackageType> packageTypes, ErrorsWSType errors) {
		if (errors == null)
			errors = new ErrorsWSType();
		return convertOrder(o, packageTypes, errors.getError());
	}
	private static ShippingOrder convertOrder(OrderWSType o, List<PackageType> packageTypes, List<ErrorWSType> errors) {
		// TODO Auto-generated method stub
		boolean isPallet = false;
		boolean isPackage = false;
		
		if (o != null) {
			ShippingOrder so = new ShippingOrder();
			if (o.getOrderID() != null )
				so.setId(o.getOrderID());
			so.setPackageTypeId(convertPackageType(o.getPackageType(), packageTypes));
			if (so.getPackageTypeId() == null) 
				addError(INVALID_PACKAGE_TYPE, errors);
			else if (so.getPackageTypeId().getPackageTypeId().intValue() == PACKAGE_TYPE_PALLET_ID)
				isPallet = true;
			else if (so.getPackageTypeId().getPackageTypeId().intValue() == PACKAGE_TYPE_PACKAGE_ID)
				isPackage = true;
			
			if (o.getAccessorial() != null)
				convertAccessorial(o.getAccessorial(), so, isPallet, errors);
			if (o.getBilledWeight() != null) {
				so.setBilledWeight(new Float(o.getBilledWeight().getWeight()));
				so.setBilledWeightUOM(o.getBilledWeight().getUOM());
			}
			if (o.getQuotedWeight() != null) {
				so.setQuotedWeight(new Float(o.getQuotedWeight().getWeight()));
				so.setQuotedWeightUOM(o.getQuotedWeight().getUOM());
			}
			if (o.getCarrier() != null) {
				so.setCarrierId(o.getCarrier().getID());
				so.setCarrierName(o.getCarrier().getName());
			}
//			if (o.getCharges() != null)
//				so.setCharges(convertWSCharges(o.getCharges()));
			so.setCurrency(o.getCurrency());
			if (o.getDutiableAmount() != null)
				so.setDutiableAmount(o.getDutiableAmount());
			so.setFromAddress(convertAddress(o.getFromAddress()));
			validateFromAddress(so.getFromAddress(), errors);
			
			so.setOrderNum(o.getOrderNumber());
			so.setPackages(convertPackages(o.getPackages(), isPallet, isPackage, errors));
			if (so.getPackages() == null)
				addError(PACKAGE_LIST_MISSING, errors);
			else
				so.setQuantity(so.getPackages().size());

			so.setPickup(convertPickup(o.getPickup()));
			if (o.getPOD() != null)
				convertPOD(o.getPOD(), so);
			
			if (o.getReferences() != null)
				convertReferences(o.getReferences(), so);
			
			if (o.getRequiredDeliveryDate() != null)
				so.setReqDeliveryDate(convertXmlCalendarToString(o.getRequiredDeliveryDate()));
			
			if (o.getScheduledShipDate() != null)
				so.setScheduledShipDate(convertXmlCalendarToTimeStamp(o.getScheduledShipDate()));
			
			if (o.getService() != null)
				so.setService(convertService(o.getService()));
			
			if (o.getToAddress() != null)
				so.setToAddress(convertAddress(o.getToAddress()));
			validateToAddress(so.getToAddress(), errors);
			
			so.setMasterTrackingNum(o.getTrackingNumber());
			
			if (o.getCustomsInvoice() != null)
				so.setCustomsInvoice(convertCustomsInvoice(o.getCustomsInvoice()));

			so.setTrackingURL(o.getTrackingURL());
			
//			if (o.getCreatedDate() != null) {
//				so.setDateCreated(convertXmlCalendarToTimeStamp(o.getCreatedDate()));
//			}
//			if (o.getStatus() != null && o.getStatus().getCode() != null) {
//				so.setStatusId(Long.parseLong(o.getStatus().getCode()));
//				so.setStatusName(o.getStatus().getDescription());
//			}
			
			return so;
		}
		return null;
	}


	private static CustomsInvoiceWSType convertCustomsInvoice(CustomsInvoice ci) {
		if (ci != null) {
			CustomsInvoiceWSType wsCI = new CustomsInvoiceWSType();
			wsCI.setBillTo(ci.getBillTo());
			wsCI.setBillToAccountNum(ci.getBillToAccountNum());
			wsCI.setBillToAddress(convertAddress(ci.getBillToAddress()));
			wsCI.setBrokerAddress(convertAddress(ci.getBrokerAddress()));
			wsCI.setReference(ci.getReference());
			wsCI.setCurrency(ci.getCurrency());
			convertCustomsInvoiceProducts(wsCI.getProducts(), ci.getProducts());
			wsCI.setReference(ci.getReference());
			wsCI.setTaxId(ci.getTaxId());
			if (ci.getTotalValue() != null)
				wsCI.setTotalValue(ci.getTotalValue());
			if (ci.getTotalWeight() != null)
				wsCI.setTotalWeight(ci.getTotalWeight().intValue());
			if ( !StringUtil.isEmpty(ci.getExportData()) ) {
				MetaDataWSType metaData = new MetaDataWSType();
				metaData.setKey(ci.getExportDataName());
				metaData.setValue(ci.getExportData());
				wsCI.getMetaData().add(metaData);
			}
			
			return wsCI;			
		}
		return null;
	}

	private static CustomsInvoice convertCustomsInvoice(CustomsInvoiceWSType wsCI) {
		if (wsCI != null) {
			CustomsInvoice ci = new CustomsInvoice();
			ci.setBillTo(wsCI.getBillTo());
			ci.setBillToAccountNum(wsCI.getBillToAccountNum());
			ci.setBillToAddress(convertAddress(wsCI.getBillToAddress()));
			ci.setBrokerAddress(convertAddress(wsCI.getBrokerAddress()));
			ci.setCurrency(wsCI.getCurrency());
			ci.setProducts(convertCustomsInvoiceProducts(wsCI.getProducts()));
			ci.setReference(wsCI.getReference());
			ci.setTaxId(wsCI.getTaxId());
			ci.setTotalValue(wsCI.getTotalValue());
			ci.setTotalWeight(new Long(wsCI.getTotalWeight()));
			if (wsCI.getMetaData() != null && wsCI.getMetaData().size() > 0) {
				ci.setExportDataName(wsCI.getMetaData().get(0).getKey());
				ci.setExportData(wsCI.getMetaData().get(0).getValue());
			}
			
			return ci;
		}
		return null;
	}
	

	private static List<CustomsInvoiceProductWSType> convertCustomsInvoiceProducts(
			List<CustomsInvoiceProductWSType> wsProducts, List<CustomsInvoiceProduct> products) {
		if (products != null && products.size() > 0) {
			for (CustomsInvoiceProduct cip:products) {
				wsProducts.add(convertCustomsInvoiceProduct(cip));
			}
			return wsProducts;
		}
		return null;
	}	

	private static List<CustomsInvoiceProduct> convertCustomsInvoiceProducts(List<CustomsInvoiceProductWSType> wsProducts) {
		if (wsProducts != null && wsProducts.size() > 0) {
			List<CustomsInvoiceProduct> products = new ArrayList<CustomsInvoiceProduct>();
			for (CustomsInvoiceProductWSType wsCip:wsProducts) {
				products.add(convertCustomsInvoiceProduct(wsCip));
			}
			return products;
		}
		return null;
	}
	private static CustomsInvoiceProductWSType convertCustomsInvoiceProduct(CustomsInvoiceProduct cip) {
		if (cip != null) {
			CustomsInvoiceProductWSType wscip = new CustomsInvoiceProductWSType();
			wscip.setDescription(cip.getProductDesc());
			wscip.setHarmCode(cip.getProductHC());
			wscip.setOrigin(cip.getProductOrigin());
			wscip.setQuantity(cip.getProductQuantity());
			wscip.setUnitPrice(cip.getProductUnitPrice());
			if (cip.getProductWeight() != null) 
				wscip.setWeight(cip.getProductWeight().intValue());;
			return wscip;
			
		}
		return null;
	}
	
	private static CustomsInvoiceProduct convertCustomsInvoiceProduct(CustomsInvoiceProductWSType wsCip) {
		if (wsCip != null) {
			CustomsInvoiceProduct cip = new CustomsInvoiceProduct();
			cip.setProductDesc(wsCip.getDescription());
			cip.setProductHC(wsCip.getHarmCode());
			cip.setProductOrigin(wsCip.getOrigin());
			cip.setProductQuantity(wsCip.getQuantity());
			cip.setProductUnitPrice(wsCip.getUnitPrice());
			cip.setProductWeight(new Long(wsCip.getWeight()));;
			return cip;
			
		}
		return null;
	}
	private static Service convertService(ServiceWSType st) {
		// TODO Auto-generated method stub
		if (st != null) {
			Service s = new Service();
			if (st.getCode() != null)
				s.setId(Long.parseLong(st.getCode()));	//s.setCode(st.getCode());
			s.setDescription(st.getDescription());
			s.setName(st.getName());
			
			return s;
		}
		
		return null;
	}

	private static void convertReferences(List<String> references, ShippingOrder so) {
		// TODO Auto-generated method stub
		if (references != null) {
			if (references.size() > 0) {
				so.setReferenceCode(references.get(0));
				if (references.size() > 1) {
					so.setReferenceOne(references.get(1));
					if (references.size() > 2) {
						so.setReferenceTwo(references.get(2));
					}
				}
			}
		}
	}

	private static void convertPOD(PODWSType pod, ShippingOrder so) {
		// TODO Auto-generated method stub
		if (pod != null) {
			if (pod.getDeliveryDateTime() != null)
				so.setPodDateTime(convertXmlCalendarToDate(pod.getDeliveryDateTime()));
			so.setPodReceiver(pod.getReceiver());
//			pod.getSignature()	// It will be added in Shipping Order later
		}
	}

	private static Pickup convertPickup(PickupWSType pt) {
		// TODO Auto-generated method stub
		if (pt != null) {
			Pickup p = new Pickup();
			if (pt.getCloseTime() != null) {
				p.setCloseHour(Integer.toString(pt.getCloseTime().getHours()));
				p.setCloseMin(Integer.toString(pt.getCloseTime().getMinutes()));
			}
			p.setInstructions(pt.getInstructions());
			p.setPickupLocation(pt.getLocation());
			if (pt.getReadyTime() != null) {
				p.setReadyHour(Integer.toString(pt.getReadyTime().getHours()));
				p.setReadyMin(Integer.toString(pt.getReadyTime().getMinutes()));
			}
			p.setPickupReference(pt.getReference());
			
			return p;
		}
		
		return null;
	}

	public static void addError(int errIndex, List<ErrorWSType> errors) {
		// TODO Auto-generated method stub
//		if (errors == null)
//			errors = new ArrayList<ErrorWSType>();
		
		ErrorWSType err = new ErrorWSType();
		err.setCode(ERRORS[errIndex][0]);
		err.setDescription(MessageUtil.getMessage(ERRORS[errIndex][1]));
		errors.add(err);
	}
	
	public static void addError(String errCode, String errMsg, List<ErrorWSType> errors) {
		// TODO Auto-generated method stub
		ErrorWSType err = new ErrorWSType();
		err.setCode(errCode);
		err.setDescription(errMsg);
		errors.add(err);
	}	

	private static PackageType convertPackageType(String pt, List<PackageType> packageTypes) {
		// TODO Auto-generated method stub
		if (pt != null) {
			for (PackageType p:packageTypes)
				if (p.getName().equalsIgnoreCase(pt))
					return p;
			
		}
		return null;
	}
	
	private static List<Package> convertPackages(PackagesWSType packages, boolean isPallet, boolean isPackage, List<ErrorWSType> errors) {
		// TODO Auto-generated method stub
		if (packages != null && packages.getPackage() != null && packages.getPackage().size() > 0) {
			return convertPackages(packages.getPackage(), isPallet, isPackage, errors);
		}
		return null;
	}	

	private static List<Package> convertPackages(List<PackageWSType> packages, boolean isPallet, boolean isPackage, List<ErrorWSType> errors) {
		// TODO Auto-generated method stub
		if (packages != null) {
			List<Package> pList = new ArrayList<Package>();
			for (PackageWSType sp:packages) {
				if (sp != null) {
					pList.add(convertPackage(sp, isPallet, isPackage, errors));
				}
			}
			return pList;
		}
		return null;
	}

	private static Package convertPackage(PackageWSType sp, boolean isPallet, boolean isPackage, List<ErrorWSType> errors) {
		// TODO Auto-generated method stub
		if (sp != null) {
			Package p = new Package();
			p.setCodAmount(sp.getCODAmount());
			p.setDescription(sp.getDescription());
			if (isPallet && StringUtil.isEmpty(p.getDescription()))
				addError(PACKAGE_DESC_MISSING, errors);
			p.setFreightClass(sp.getFreightClass());
			if (isPallet && StringUtil.isEmpty(p.getFreightClass()))
				addError(FREIGHT_CLASS_MISSING, errors);
			p.setHeight(sp.getHeight());
			if ((isPallet || isPackage) && p.getHeight() == null)
				addError(PACKAGE_HEIGHT_MISSING, errors);
			p.setLength(sp.getLength());
			if ((isPallet || isPackage) && p.getLength() == null)
				addError(PACKAGE_LENGTH_MISSING, errors);
			p.setWidth(sp.getWidth());
			if ((isPallet || isPackage) && p.getWidth() == null)
				addError(PACKAGE_WIDTH_MISSING, errors);
			if (sp.getWeight() != null)
				p.setWeight(sp.getWeight());
			else if (isPallet || isPackage)
				addError(PACKAGE_WEIGHT_MISSING, errors);
		
			p.setTrackingNumber(sp.getTrackingNumber());
			p.setType(sp.getType());

			p.setWeightUOM(sp.getWeightUOM());
			if (p.getWeightUOM() == null)
				p.setWeightUOM(ShiplinxConstants.WEIGHT_UNITS_LBS);
			p.setInsuranceAmount(sp.getInsuranceAmount());
		
			return p;
		}
		return null;
	}
	
	private static void validateToAddress(Address toAddress, List<ErrorWSType> errors) {
		// TODO Auto-generated method stub
		if (toAddress == null)
			addError(TO_ADDRESS_MISSING, errors);
		else {
			if (StringUtil.isEmpty(toAddress.getAbbreviationName()))
				addError(TO_ADDRESS_COMP_NAME_MISSING, errors);
			if (StringUtil.isEmpty(toAddress.getAddress1()))
				addError(TO_ADDRESS_ADDRESS1_MISSING, errors);
			if (StringUtil.isEmpty(toAddress.getCity()))
				addError(TO_ADDRESS_CITY_MISSING, errors);
			if (StringUtil.isEmpty(toAddress.getContactName()))
				addError(TO_ADDRESS_CONTACT_NAME_MISSING, errors);
			if (StringUtil.isEmpty(toAddress.getPhoneNo()))
				addError(TO_ADDRESS_PHONE_NO_MISSING, errors);
			if (StringUtil.isEmpty(toAddress.getCountryCode()))
				addError(TO_ADDRESS_COUNTRY_CODE_MISSING, errors);
			else if ((toAddress.getCountryCode().equalsIgnoreCase(ShiplinxConstants.US) ||
					toAddress.getCountryCode().equalsIgnoreCase(ShiplinxConstants.CANADA)) &&
					 StringUtil.isEmpty(toAddress.getPostalCode()))
				addError(TO_ADDRESS_POSTAL_CODE_MISSING, errors);
		}
	}	
	
	private static void validateFromAddress(Address fromAddress, List<ErrorWSType> errors) {
		// TODO Auto-generated method stub
		if (fromAddress == null)
			addError(FROM_ADDRESS_MISSING, errors);
		else {
			if (StringUtil.isEmpty(fromAddress.getAbbreviationName()))
				addError(FROM_ADDRESS_COMP_NAME_MISSING, errors);
			if (StringUtil.isEmpty(fromAddress.getAddress1()))
				addError(FROM_ADDRESS_ADDRESS1_MISSING, errors);
			if (StringUtil.isEmpty(fromAddress.getCity()))
				addError(FROM_ADDRESS_CITY_MISSING, errors);
			if (StringUtil.isEmpty(fromAddress.getContactName()))
				addError(FROM_ADDRESS_CONTACT_NAME_MISSING, errors);
			if (StringUtil.isEmpty(fromAddress.getPhoneNo()))
				addError(FROM_ADDRESS_PHONE_NO_MISSING, errors);
			if (StringUtil.isEmpty(fromAddress.getCountryCode()))
				addError(FROM_ADDRESS_COUNTRY_CODE_MISSING, errors);
			else if ((fromAddress.getCountryCode().equalsIgnoreCase(ShiplinxConstants.US) ||
					  fromAddress.getCountryCode().equalsIgnoreCase(ShiplinxConstants.CANADA)) &&
					 StringUtil.isEmpty(fromAddress.getPostalCode()))
				addError(FROM_ADDRESS_POSTAL_CODE_MISSING, errors);
		}
	}	

	private static Address convertAddress(AddressWSType sa) {
		// TODO Auto-generated method stub
		if (sa != null) {
			Address a = new Address();
			a.setAbbreviationName(sa.getCompanyName());
			a.setAddress1(sa.getAddress1());
			a.setAddress2(sa.getAddress2());
			a.setCity(sa.getCity());
			a.setContactName(sa.getContactName());
			a.setCountryCode(sa.getCountryCode());
			a.setEmailAddress(sa.getEmailAddress());
			a.setFaxNo(sa.getFaxNo());
			a.setMobilePhoneNo(sa.getMobilePhoneNo());
			a.setPhoneNo(sa.getPhoneNo());
			a.setPostalCode(sa.getPostalCode());
			a.setProvinceCode(sa.getProvinceCode());
//			sa.getInstructions()
			if (sa.isIsRes() != null)
				a.setResidential(sa.isIsRes());
			
			return a;
		}
		return null;
	}
	
//	private static List<Charge> convertCharges(ChargesWSType charges) {
//		// TODO Auto-generated method stub
//		if (charges != null && charges.getCharge() != null && charges.getCharge().size() > 0) {
//			return convertCharges(charges.getCharge());
//		}
//		return null;
//	}	

//	private static List<Charge> convertCharges(List<ChargeWSType> charges) {
//		// TODO Auto-generated method stub
//		if (charges != null) {
//			List<Charge> cList = new ArrayList<Charge>();
//			for (ChargeWSType c:charges) {
//				Charge ch = convertCharge(c);
//				if (ch != null)
//					cList.add(ch);
//			}
//			return cList;
//		}
//		return null;
//	}
//
//	private static Charge convertCharge(ChargeWSType sc) {
//		// TODO Auto-generated method stub
//		if (sc != null) {
//			Charge c = new Charge();
//			c.setCharge(sc.getCharge());
//			c.setChargeCode(sc.getChargeCode());
//			c.setChargeCodeLevel2(sc.getChargeCodeLevel2());
//			c.setCurrency(sc.getCurrency());
//			c.setName(sc.getName());
//			c.setTariffRate(sc.getTariffRate());
//			return c;
//		}
//		return null;
//	}

	private static void convertAccessorial(AccessorialWSType acc, ShippingOrder so, boolean isPallet, List<ErrorWSType> errors) {
		// TODO Auto-generated method stub
		if (acc != null) {
			if (acc.getCOD() != null) {
				so.setCODCurrency(acc.getCOD().getCurrency());
				so.setCODPayableTO(acc.getCOD().getPayableTo());
				so.setCODPayment(acc.getCOD().getPaymentType());
			}
			if (acc.getDangerousGoods() != null)
				so.setDangerousGoodsName(acc.getDangerousGoods());
			if (acc.getSignatureRequired() != null)
				so.setSignatureRequired(convertSignatureRequired(acc.getSignatureRequired()));
			
			so.setConfirmDelivery(acc.isConfirmDelivery());
			so.setToTailgate(acc.isDeliveryTailgate());
			if (isPallet && so.getToTailgate() == null) 
				addError(DELIVERY_TAILGATE_MISSING, errors);
			
	//		acc.isHoldForPickup()
			so.setHoldForPickupRequired(acc.isHoldForPickupRequired());
			so.setInsideDelivery(acc.isInsideDelivery());
			if (isPallet && so.getInsideDelivery() == null)
				addError(INSIDE_DELIVERY_MISSING, errors);
			
			so.setNotifyRecipient(acc.isNotifyRecipient());
			so.setFromTailgate(acc.isPickupTailgate());
			if (isPallet && so.getFromTailgate() == null)
				addError(PICKUP_TAILGATE_MISSING, errors);
			
			so.setSatDelivery(acc.isSaturdayDelivery());
	//		acc.isSaturdayPickup()
			if (acc.isSpecialEquipment() != null)
				so.setSpecialEquipment(acc.isSpecialEquipment().toString());
			
			
			if (acc.isTradeShowDelivery() != null)
				so.setTradeShowDelivery(acc.isTradeShowDelivery());
			if (acc.isTradeShowPickup() != null) 
				so.setTradeShowPickup(acc.isTradeShowPickup());
			if (acc.isInsidePickup() != null)
				so.setInsidePickup(acc.isInsidePickup());
			if (acc.isAppointmentDelivery() != null)
				so.setAppointmentDelivery(acc.isAppointmentDelivery());
			if (acc.isAppointmentPickup() != null)
				so.setAppointmentPickup(acc.isAppointmentPickup());			
		}
		
	}

	private static Short convertSignatureRequired(String sr) {
		// TODO Auto-generated method stub
		if (sr != null) {
			for (short i=0; i<SIGN_REQ_VALUES.length; i++) {
				if (SIGN_REQ_VALUES[i].equalsIgnoreCase(sr)) {
					return ++i;
				}
			}
		}
		return null;
	}
	
	public static RatingsWSType convertRatings(List<Rating> ratings, ShippingService shippingService) {
		if (ratings != null && ratings.size() > 0) {
			RatingsWSType rList = new RatingsWSType();
			for (Rating r:ratings) {
				rList.getRating().add(convertRating(r, shippingService));
			}
			return rList;
		}
			
		return null;
	}	
	
//	public static void convertRatings(List<Rating> ratings, RatingsWSType outRatings) {
//		if (outRatings == null)
//			outRatings = new RatingsWSType();
//		convertRatings(ratings, outRatings.getRating());
//	}
//	private static void convertRatings(List<Rating> ratings, List<RatingWSType> outRatings) {
//		// TODO Auto-generated method stub
//		if (ratings != null) {
////			outRatings = new ArrayList<RatingWSType>();
//			for (Rating r:ratings) {
//				outRatings.add(convertRating(r));
//			}
//		}
//	}
	
	private static RatingWSType convertRating(Rating r, ShippingService shippingService) {
		if (r != null) {
			RatingWSType rt = new RatingWSType();
			rt.setBillWeight(r.getBillWeight());
			rt.setCarrierName(r.getCarrierName());
			rt.setCurrency(r.getCurrency());
			rt.setModeTransport(r.getModeTransport());
			rt.setService(convertService(r));
			rt.setTariffRate(r.getTariffRate());
			rt.setTotal(r.getTotal());
			rt.setTransitDays(r.getTransitDays());
			rt.setCharges(convertChargeList(r.getCharges(), r.getCarrierId(), shippingService)); 
			
			return rt;
		}
		return null;
	}



	private static ChargesWSType convertCharges(ShippingOrder o, ShippingService shippingService) {
		if ((o.getQuotedCharges() != null && o.getQuotedCharges().size() > 0) ||
			(o.getActualCharges() != null && o.getActualCharges().size() > 0) ) {
				
			ChargesWSType charges = new ChargesWSType();
			if ((o.getQuotedCharges() != null && o.getQuotedCharges().size() > 0)) {
				charges.setQuotedCharges(convertChargeList(o.getQuotedCharges(), o.getCarrierId(), shippingService));
			}
			if ((o.getActualCharges() != null && o.getActualCharges().size() > 0)) {
				charges.setActualCharges(convertChargeList(o.getActualCharges(), o.getCarrierId(), shippingService));
			}
			return charges;
		}
		return null;
	}

	private static ChargeListWSType convertChargeList(List<Charge> charges, Long carrierId, ShippingService shippingService) {
		if (charges != null && charges.size() > 0) {
			ChargeListWSType cList = new ChargeListWSType();
			double total = 0;
			double totalExclTaxes = 0;
			for(Charge c:charges) {
				CarrierChargeCode chargeGroupCode = null;
				if (shippingService != null) {
					chargeGroupCode = shippingService.getChargeByCarrierAndCodes(
										carrierId, c.getChargeCode(), c.getChargeCodeLevel2());
				}
				ChargeWSType wsCharge = convertCharge(c, chargeGroupCode);
				
				if (wsCharge.getCharge() != null) {
					total += wsCharge.getCharge().doubleValue();
					if ( !wsCharge.isTax() )
						totalExclTaxes += wsCharge.getCharge().doubleValue();
				}
				cList.getCharge().add(wsCharge);
			}
			cList.setTotal(total);
			cList.setTotalExcludingTaxes(totalExclTaxes);
			
			return cList;
		}
		return null;
	}

	private static ChargeWSType convertCharge(Charge c, CarrierChargeCode chargeGroupCode) {
		if (c != null) {
			ChargeWSType cw = new ChargeWSType();
			cw.setCharge(c.getCharge());
			if (chargeGroupCode != null) {
				cw.setChargeCode(chargeGroupCode.getGroupCode());
				cw.setTax(chargeGroupCode.isTax());
				cw.setGroupName(chargeGroupCode.getGroupName());
			} else {
				cw.setTax(false);
				cw.setChargeCode(c.getChargeCode());
				cw.setGroupName("UNKNOWN_GROUP");
			}
			cw.setChargeCodeLevel2(c.getChargeCodeLevel2());
			cw.setCurrency(c.getCurrency());
			cw.setName(c.getName());
			cw.setTariffRate(c.getTariffRate());

			
			return cw;
		}
		return null;
	}
	private static ServiceWSType convertService(Rating r) {
		// TODO Auto-generated method stub
		if (r != null) {
			ServiceWSType s = new ServiceWSType();
			s.setCode(Long.toString(r.getServiceId()));
			s.setDescription(r.getServiceName());
			s.setName(r.getServiceName());
			return s;
		}
		return null;
	}



	private static Date convertXmlCalendarToDate(XMLGregorianCalendar xmlCalendar) {
		// TODO Auto-generated method stub
		if (xmlCalendar != null) {
			return xmlCalendar.toGregorianCalendar().getTime();
		}

		return null;
	}
	
	private static Timestamp convertXmlCalendarToTimeStamp(XMLGregorianCalendar xmlCalendar) {
		// TODO Auto-generated method stub
		if (xmlCalendar != null) {
			Date dt = xmlCalendar.toGregorianCalendar().getTime();
			Timestamp t = new Timestamp(dt.getTime());
			return t;
		}

		return null;
	}

	private static String convertXmlCalendarToString(XMLGregorianCalendar xmlCalendar) {
		// TODO Auto-generated method stub
		if (xmlCalendar != null)
			return xmlCalendar.toString();
		return null;
	}	
	
	public static OrderWSType convertOrder(ShippingOrder o, ShippingService shippingService) {
		if (o != null) {
			OrderWSType ot = new OrderWSType();
			ot.setOrderID(o.getId());
			ot.setAccessorial(convertAccessorial(o));
			if (o.getBilledWeight() != null) {
				WeightWSType bw = new WeightWSType();
				bw.setWeight(o.getBilledWeight());
				bw.setUOM(o.getBilledWeightUOM());
				ot.setBilledWeight(bw);
			}
			if (o.getQuotedWeight() != null) {
				WeightWSType qw = new WeightWSType();
				qw.setWeight(o.getQuotedWeight());
				qw.setUOM(o.getQuotedWeightUOM());
				ot.setQuotedWeight(qw);
			}
			if (o.getCarrier() != null) {
				CarrierWSType c = new CarrierWSType();
				c.setID(o.getCarrier().getId());
				c.setName(o.getCarrier().getName());
				ot.setCarrier(c);
			}
			ot.setCurrency(o.getCurrency());
			ot.setDutiableAmount(o.getDutiableAmount());
			ot.setFromAddress(convertAddress(o.getFromAddress()));
			ot.setOrderNumber(o.getOrderNum());
			ot.setPackageType(o.getPackageTypeId().getName());
			ot.setPickup(convertPickup(o.getPickup()));
			ot.setPOD(convertPOD(o));
			ot.setRequiredDeliveryDate(convertStringToXmlCalendar(o.getReqDeliveryDate()));
			ot.setScheduledShipDate(convertTimestampToXmlCalendar(o.getScheduledShipDate()));
			ot.setService(convertService(o.getService()));
			ot.setToAddress(convertAddress(o.getToAddress()));
			ot.setTrackingNumber(o.getMasterTrackingNum());	
//			convertPackages(o.getPackages(), ot.getPackages());
			ot.setPackages(convertPacakges(o.getPackages()));
			ot.setCharges(convertCharges(o, shippingService));
			
			if (o.getCustomsInvoice() != null)
				ot.setCustomsInvoice(convertCustomsInvoice(o.getCustomsInvoice()));
			
			ot.setTrackingURL(o.getTrackingURL());
			
//			if (o.getLabels())
			ot.setCreatedDate(convertTimestampToXmlCalendar(new Timestamp(o.getDateCreated().getTime())));
			ot.setStatus(convertStatus(o));
			
			return ot;
		}
		return null;
	}
	
private static StatusWSType convertStatus(ShippingOrder o) {
		if (o != null && o.getStatusId() != null) {
			StatusWSType s = new StatusWSType();
			s.setCode(o.getStatusId().toString());
			s.setDescription(o.getStatusName());
			return s;
		}
		return null;
	}
//	private static void convertPackages(List<Package> packages, PackagesWSType outPackages) {
//		// TODO Auto-generated method stub
//		if (packages != null && packages.size() > 0) {
//			if (outPackages.getPackage() == null)
//			convertPackages(packages, outPackages.getPackage());
//		}
//		
//	}	

	private static PackagesWSType convertPacakges(List<Package> packages) {
		// TODO Auto-generated method stub
		if (packages != null && packages.size() > 0) {
			PackagesWSType pList = new PackagesWSType();
			for (Package p:packages) {
				pList.getPackage().add(convertPackage(p));
			}
			
			return pList;
		}
			
		return null;
	}
//	private static void convertPackages(List<Package> packages, List<PackageWSType> outPackages) {
//		// TODO Auto-generated method stub
//		if (packages != null) {
//			for (Package p:packages)
//				outPackages.add(convertPackage(p));
//		}
//		
//	}

	private static PackageWSType convertPackage(Package p) {
		// TODO Auto-generated method stub
		if (p != null) {
			PackageWSType pt = new PackageWSType();
			
			pt.setCODAmount(p.getCodAmount());
			pt.setDescription(p.getDescription());
			pt.setFreightClass(p.getFreightClass());
			pt.setHeight(p.getHeight());
			pt.setInsuranceAmount(p.getInsuranceAmount());
			pt.setLength(p.getLength());
			pt.setTrackingNumber(p.getTrackingNumber());
			pt.setType(p.getType());
			if (p.getWeight() != null)
				pt.setWeight(p.getWeight());
			pt.setWeightUOM(p.getWeightUOM());
			pt.setWidth(p.getWidth());
			
			return pt;
		}
		return null;
	}

	private static ServiceWSType convertService(Service s) {
		// TODO Auto-generated method stub
		if (s != null) {
			ServiceWSType st = new ServiceWSType();
			if (s.getId() != null)
				st.setCode(s.getId().toString());	//s.getCode());
			st.setDescription(s.getDescription());
			st.setName(s.getName());
			
			return st;
		}
		return null;
	}

	private static PODWSType convertPOD(ShippingOrder o) {
		// TODO Auto-generated method stub
		if (o != null) {
			PODWSType pod = new PODWSType();
			pod.setDeliveryDateTime(convertDateToXmlCalendar(o.getPodDateTime()));
			pod.setReceiver(o.getPodReceiver());
//			pod.setSignature(value)
			
			return pod;
		}
		return null;
	}
	
	private static PickupWSType convertPickup(Pickup p) {
		// TODO Auto-generated method stub
		if (p != null) {
			PickupWSType pt = new PickupWSType();
			pt.setCloseTime(getDuration(p.getCloseHour(), p.getCloseMin()));
			pt.setInstructions(p.getInstructions());
			pt.setLocation(p.getPickupLocation());
			pt.setReadyTime(getDuration(p.getReadyHour(), p.getReadyMin()));
			pt.setReference(p.getPickupReference());
			
			return pt;
		}
		return null;
	}

	private static Duration getDuration(String h, String m) {
		// TODO Auto-generated method stub
		try {
			if (!StringUtil.isEmpty(h) && !StringUtil.isEmpty(m)) {
				Duration d = DatatypeFactory.newInstance().newDurationDayTime(true, 0, Integer.parseInt(h), Integer.parseInt(m), 0);
				return d;
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		return null;
	}

	private static AddressWSType convertAddress(Address a) {
		// TODO Auto-generated method stub
		if (a != null) {
			AddressWSType at = new AddressWSType();
			at.setAddress1(a.getAddress1());
			at.setAddress2(a.getAddress2());
			at.setCity(a.getCity());
			at.setCompanyName(a.getAbbreviationName());
			at.setContactName(a.getContactName());
			at.setCountryCode(a.getCountryCode());
			at.setEmailAddress(a.getEmailAddress());
			at.setFaxNo(a.getFaxNo());
//			at.setInstructions()
			at.setIsRes(a.getResidential());
			at.setMobilePhoneNo(a.getMobilePhoneNo());
			at.setPhoneNo(a.getPhoneNo());
			at.setPostalCode(a.getPostalCode());
			at.setProvinceCode(a.getProvinceCode());
			
			return at;
		}
		return null;
	}

	private static AccessorialWSType convertAccessorial(ShippingOrder o) {
		// TODO Auto-generated method stub
		if (o != null) {
			AccessorialWSType a = new AccessorialWSType();
			a.setCOD(convertCOD(o));
			a.setConfirmDelivery(o.getConfirmDelivery());
			a.setDangerousGoods(o.getDangerousGoodsName());
			a.setDeliveryTailgate(o.getToTailgate());
			a.setHoldForPickup(o.getHoldForPickupRequired());
			a.setHoldForPickupRequired(o.getHoldForPickupRequired());
			a.setInsideDelivery(o.getInsideDelivery());
			a.setNotifyRecipient(o.getNotifyRecipient());
			a.setPickupTailgate(o.getFromTailgate());
			a.setSaturdayDelivery(o.getSatDelivery());
//			a.setSaturdayPickup()
			a.setTradeShowDelivery(o.isTradeShowDelivery());
			a.setTradeShowPickup(o.isTradeShowPickup());
			a.setInsidePickup(o.isInsidePickup());
			a.setAppointmentDelivery(o.isAppointmentDelivery());
			a.setAppointmentPickup(o.isAppointmentPickup());			
			return a;
		}
		return null;
	}

	private static CODWSType convertCOD(ShippingOrder o) {
		// TODO Auto-generated method stub
		if (o != null) {
			CODWSType cod = new CODWSType();
			cod.setCurrency(o.getCODCurrency());
			cod.setPayableTo(o.getCODPayableTO());
			cod.setPaymentType(o.getCODPayment());
			
			return cod;
		}
		return null;
	}
	
	private static XMLGregorianCalendar convertStringToXmlCalendar(String sDate) {
		// TODO Auto-generated method stub
		// MyToDo:Need to evaulate the format before conversion
		System.out.println(sDate);
		return null;
	}	
	
    private static XMLGregorianCalendar convertTimestampToXmlCalendar(Timestamp timestamp) {
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
    		log.error(e);
    	}
    	return null;
    }
	
	private static XMLGregorianCalendar convertDateToXmlCalendar(Date d) {
		// TODO Auto-generated method stub
    	try {
	        if (d == null) {
	            return null;
	        } else {
	            GregorianCalendar gc = new GregorianCalendar();
	            gc.setTimeInMillis(d.getTime());
	            return DatatypeFactory.newInstance().newXMLGregorianCalendar(gc);
	        }
    	} catch (Exception e) {
    		e.printStackTrace();
    		log.error(e);
    	}
    	return null;
	}

//	public static List<Rating> convertRatings(RatingsWSType ratings) {
//		return convertRatings(ratings.getRating());
//	}


//	private static List<Rating> convertRatings(List<RatingWSType> ratings) {
//		if (ratings != null) {
//			List<Rating> rList = new ArrayList<Rating>();
//			for (RatingWSType r:ratings) {
//				rList.add(convertRating(r));
//			}
//			return rList;
//		}
//		return null;
//	}
//
//	public static Rating convertRating(RatingWSType rt) {
//		// TODO Auto-generated method stub
//		if (rt != null) {
//			Rating r = new Rating(); 
//			r.setBillWeight(rt.getBillWeight());
//			r.setCarrierName(rt.getCarrierName());
//			r.setCarrierId(getCarrierId(rt.getCarrierName()));
////			r.setCharges(convertWSCharges(rt.getCharges()));
//			r.setCurrency(rt.getCurrency());
//			r.setModeTransport(rt.getModeTransport());
//			convertService(rt.getService(), r);
//			r.setTariffRate(rt.getTariffRate());
//			r.setTotal(rt.getTotal());
//			r.setTransitDays(rt.getTransitDays());
//			return r;
//		}
//		return null;
//	}

	private static long getCarrierId(String name) {
		// TODO Auto-generated method stub
		if (!StringUtil.isEmpty(name)) {
			if (name.equalsIgnoreCase(ShiplinxConstants.CARRIER_FEDEX_STRING))
				return ShiplinxConstants.CARRIER_FEDEX;
			if (name.equalsIgnoreCase(ShiplinxConstants.CARRIER_UPS_STRING))
				return ShiplinxConstants.CARRIER_UPS;
			if (name.equalsIgnoreCase(ShiplinxConstants.CARRIER_DHL_STRING))
				return ShiplinxConstants.CARRIER_DHL;
			if (name.equalsIgnoreCase(ShiplinxConstants.CARRIER_LOOMIS_STRING))
				return ShiplinxConstants.CARRIER_LOOMIS;
		}
		return ShiplinxConstants.CARRIER_GENERIC;
	}



	private static void convertService(ServiceWSType s, Rating r) {
		// TODO Auto-generated method stub
		if (s != null) {
			if (s.getCode() != null)
				r.setServiceId(Long.parseLong(s.getCode()));
//			s.getDescription()
			r.setServiceName(s.getName());
		}
	}
	
//	private static List<Charge> convertWSCharges(ChargesWSType charges) {
//		// TODO Auto-generated method stub
//		if (charges != null && charges.getCharge() != null && charges.getCharge().size() > 0) {
//			return convertWSCharges(charges.getCharge());
//		}
//		return null;
//	}	
//
//	private static List<Charge> convertWSCharges(List<ChargeWSType> charges) {
//		// TODO Auto-generated method stub
//		if (charges != null) {
//			List<Charge> cList = new ArrayList<Charge>();
//			for (ChargeWSType c:charges) {
//				cList.add(convertCharge(c));
//			}
//			return cList;
//		}
//		return null;
//	}

//	private static Charge convertCharge(ChargeWSType ct) {
//		// TODO Auto-generated method stub
//		if (ct != null) {
//			Charge c = new Charge();
//			c.setCharge(ct.getCharge());
//			c.setChargeCode(ct.getChargeCode());
//			c.setChargeCodeLevel2(ct.getChargeCodeLevel2());
//			c.setCurrency(ct.getCurrency());
//			c.setName(ct.getName());
//			c.setTariffRate(ct.getTariffRate());
//			
//			return c;
//		}
//		return null;
//	}
	

}
