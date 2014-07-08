package com.meritconinc.shiplinx.carrier.utils;


public class SurchargeType {

	public SurchargeType(){};
	public SurchargeType(int id, String name){
		this.surchargeId = id;
		this.surchargeName = name;
	}
	private int surchargeId;
	private String surchargeName; 
	
	/*
	 * Note: If new charge is added then make the entry in the database table "surcharge_type" for added charge.
	 */
	
	private static int SURCHARGE_9AM_VALUE = 1;
	private static int SURCHARGE_1030_VALUE = 2;
	private static int SURCHARGE_NON_PACKAGED_GOODS_VALUE = 5;
	private static int SURCHARGE_SATURDAY_SERVICE_VALUE = 7;
	private static int SURCHARGE_DANGEROUS_GOODS_VALUE = 8;
	private static int SURCHARGE_INSURANCE_CHARGE_PERCENT_VALUE = 10;
	private static int SURCHARGE_INSURANCE_MINIMUM_VALUE = 11;
	private static int SURCHARGE_COLLECT_VALUE = 14;
	private static int SURCHARGE_BEYOND_CHARGE_VALUE = -1;
	private static int SURCHARGE_RURAL_CHARGE_VALUE = -2;
	private static int SURCHARGE_EXPRESS_CHEQUE_VALUE = 15;
	private static int SURCHARGE_RESIDENTIAL_DELIVERY_VALUE = 16;
	private static int SURCHARGE_OUT_OF_DELIVERY_AREA_VALUE = 17;
	private static int SURCHARGE_OUT_OF_PICKUP_AREA_VALUE = 18;
	private static int SURCHARGE_SATURDAY_PICKUP_VALUE = 19;
	private static int SURCHARGE_OTHER_VALUE = 20;
	private static int SURCHARGE_COD_VALUE = 21;
	private static int SURCHARGE_OVERWEIGHT_VALUE = 22;
	private static int SURCHARGE_PICKUP_TAILGATE_VALUE = 23;
	private static int SURCHARGE_DELIVERY_TAILGATE_VALUE = 24;
	private static int SURCHARGE_INSIDE_DELIVERY_VALUE = 25;
	private static int SURCHARGE_DELIVERY_APPT_VALUE = 26;
	
	private static int SURCHARGE_FREIGHT_VALUE = 30;
	private static int SURCHARGE_FUEL_VALUE = 31;
	private static int SURCHARGE_GST_VALUE = 35;
	private static int SURCHARGE_PST_VALUE = 36;
	private static int SURCHARGE_HST_VALUE = 37;
	private static int SURCHARGE_QST_VALUE = 38;
	private static int SURCHARGE_OFFSHORE_VALUE = 39;
	
	private static int SURCHARGE_SIGNATURE_REQUIRED_VALUE = 40;
	private static int SURCHARGE_MISCELLANEOUS_VALUE = 41;
	private static int SURCHARGE_SCHEDULE_PICKUP_VALUE = 42;
	private static int SURCHARGE_EXIBITION_CONVENTION_SITE_VALUE  = 43;
	private static int SURCHARGE_CUSTOME_INBOUND_FRIEGHT_VALUE  = 44;
	private static int SURCHARGE_CANADIAN_BORDER_SECURITY_VALUE = 45;
	private static int SURCHARGE_EARLY_AM_VALUE =46;
	private static int SURCHARGE_DELIVERY_AREA_VALUE = 47;
	
	
	private static String SURCHARGE_9AM_STRING = "9AM";
	private static String SURCHARGE_1030_STRING = "1030";
	private static String SURCHARGE_NON_PACKAGED_GOODS_STRING = "Non Packaged Goods";
	private static String SURCHARGE_SATURDAY_SERVICE_STRING = "Saturday Service";
	private static String SURCHARGE_DANGEROUS_GOODS_STRING = "Dangerous Goods";
	private static String SURCHARGE_INSURANCE_CHARGE_PERCENT_STRING = "Insurance";
	private static String SURCHARGE_INSURANCE_MINIMUM_STRING = "Insurance";
	private static String SURCHARGE_COLLECT_STRING = "Collect";
	private static String SURCHARGE_BEYOND_CHARGE_STRING = "Beyond";
	private static String SURCHARGE_RURAL_CHARGE_STRING = "Rural";
	private static String SURCHARGE_SURCHARGE_EXPRESS_CHEQUE_STRING = "Express Cheque";
	private static String SURCHARGE_RESIDENTIAL_DELIVERY_STRING = "Residential Delivery";
	private static String SURCHARGE_OUT_OF_DELIVERY_AREA_STRING = "Out of Delivery Area";
	private static String SURCHARGE_OUT_OF_PICKUP_AREA_STRING = "Out of Pickup Area";
	private static String SURCHARGE_SATURDAY_PICKUP_STRING = "Saturday Pickup";
	private static String SURCHARGE_OTHER_STRING = "Other";
	private static String SURCHARGE_COD_STRING = "COD Fee";
	private static String SURCHARGE_OVERWEIGHT_STRING = "Over Weight/Size Surcharge";
	private static String SURCHARGE_DELIVERY_TAILGATE_STRING = "Delivery Tailgate";
	private static String SURCHARGE_PICKUP_TAILGATE_STRING = "Pickup Tailgate";
	private static String SURCHARGE_INSIDE_DELIVERY_STRING = "Inside Delivery";
	private static String SURCHARGE_DELIVERY_APPT_STRING = "Delivery Appointment";
	private static String SURCHARGE_FREIGHT_STRING = "Freight or Base";
	private static String SURCHARGE_FUEL_STRING = "Fuel";	
	private static String SURCHARGE_OFFSHORE_STRING = "Offshore";
	private static String SURCHARGE_SIGNATURE_REQUIRED_STRING = "Signature Required";
	private static String SURCHARGE_MISCELLANEOUS_STRING = "Miscellaneous Charge";
	private static String SURCHARGE_SCHEDULE_PICKUP_STRING = "Pickup Charge";
	private static String SURCHARGE_EXIBITION_CONVENTION_STRING = "Exibition Convention Site";
	private static String SURCHARGE_CUSTOME_INBOUND_FRIEGHT_STRING = "Customs Inbound Frieght";
	private static String SURCHARGE_CANADIAN_BORDER_SECURITY_STRING = "Canadian Border and Security";
	private static String SURCHARGE_EARLY_AM_STRING = "Early A.M. Surcharge";
	private static String SURCHARGE_DELIVERY_AREA_STRING = "Delivery Area Surcharge";
	
	public static SurchargeType SURCHARGE_9AM = new SurchargeType(SURCHARGE_9AM_VALUE, SURCHARGE_9AM_STRING);
	public static SurchargeType SURCHARGE_1030 = new SurchargeType(SURCHARGE_1030_VALUE, SURCHARGE_1030_STRING);
	public static SurchargeType SURCHARGE_NON_PACKAGED_GOODS = new SurchargeType(SURCHARGE_NON_PACKAGED_GOODS_VALUE, SURCHARGE_NON_PACKAGED_GOODS_STRING);
	public static SurchargeType SURCHARGE_SATURDAY_SERVICE = new SurchargeType(SURCHARGE_SATURDAY_SERVICE_VALUE, SURCHARGE_SATURDAY_SERVICE_STRING);
	public static SurchargeType SURCHARGE_DANGEROUS_GOODS = new SurchargeType(SURCHARGE_DANGEROUS_GOODS_VALUE, SURCHARGE_DANGEROUS_GOODS_STRING);
	public static SurchargeType SURCHARGE_INSURANCE_CHARGE_PERCENT = new SurchargeType(SURCHARGE_INSURANCE_CHARGE_PERCENT_VALUE, SURCHARGE_INSURANCE_CHARGE_PERCENT_STRING);
	public static SurchargeType SURCHARGE_INSURANCE_MINIMUM = new SurchargeType(SURCHARGE_INSURANCE_MINIMUM_VALUE, SURCHARGE_INSURANCE_MINIMUM_STRING);
	public static SurchargeType SURCHARGE_COLLECT = new SurchargeType(SURCHARGE_COLLECT_VALUE, SURCHARGE_COLLECT_STRING);
	public static SurchargeType SURCHARGE_BEYOND_CHARGE = new SurchargeType(SURCHARGE_BEYOND_CHARGE_VALUE, SURCHARGE_BEYOND_CHARGE_STRING);
	public static SurchargeType SURCHARGE_RURAL_CHARGE = new SurchargeType(SURCHARGE_RURAL_CHARGE_VALUE, SURCHARGE_RURAL_CHARGE_STRING);
	public static SurchargeType SURCHARGE_EXPRESS_CHEQUE = new SurchargeType(SURCHARGE_EXPRESS_CHEQUE_VALUE, SURCHARGE_SURCHARGE_EXPRESS_CHEQUE_STRING);
	public static SurchargeType SURCHARGE_RESIDENTIAL_DELIVERY= new SurchargeType(SURCHARGE_RESIDENTIAL_DELIVERY_VALUE, SURCHARGE_RESIDENTIAL_DELIVERY_STRING);

	public static SurchargeType SURCHARGE_OUT_OF_DELIVERY_AREA= new SurchargeType(SURCHARGE_OUT_OF_DELIVERY_AREA_VALUE, SURCHARGE_OUT_OF_DELIVERY_AREA_STRING);
	public static SurchargeType SURCHARGE_OUT_OF_PICKUP_AREA= new SurchargeType(SURCHARGE_OUT_OF_PICKUP_AREA_VALUE, SURCHARGE_OUT_OF_PICKUP_AREA_STRING);
	public static SurchargeType SURCHARGE_SATURDAY_PICKUP= new SurchargeType(SURCHARGE_SATURDAY_PICKUP_VALUE, SURCHARGE_SATURDAY_PICKUP_STRING);
	public static SurchargeType SURCHARGE_OTHER= new SurchargeType(SURCHARGE_OTHER_VALUE, SURCHARGE_OTHER_STRING);
	public static SurchargeType SURCHARGE_COD= new SurchargeType(SURCHARGE_COD_VALUE, SURCHARGE_COD_STRING);
	public static SurchargeType SURCHARGE_OVERWEIGHT= new SurchargeType(SURCHARGE_OVERWEIGHT_VALUE, SURCHARGE_OVERWEIGHT_STRING);
	public static SurchargeType SURCHARGE_DELIVERY_TAILGATE= new SurchargeType(SURCHARGE_DELIVERY_TAILGATE_VALUE, SURCHARGE_DELIVERY_TAILGATE_STRING);
	public static SurchargeType SURCHARGE_PICKUP_TAILGATE= new SurchargeType(SURCHARGE_PICKUP_TAILGATE_VALUE, SURCHARGE_PICKUP_TAILGATE_STRING);
	public static SurchargeType SURCHARGE_INSIDE_DELIVERY= new SurchargeType(SURCHARGE_INSIDE_DELIVERY_VALUE, SURCHARGE_INSIDE_DELIVERY_STRING);
	public static SurchargeType SURCHARGE_DELIVERY_APPT = new SurchargeType(SURCHARGE_DELIVERY_APPT_VALUE, SURCHARGE_DELIVERY_APPT_STRING);
	public static SurchargeType SURCHARGE_FREIGHT = new SurchargeType(SURCHARGE_FREIGHT_VALUE, SURCHARGE_FREIGHT_STRING);
	public static SurchargeType SURCHARGE_FUEL = new SurchargeType(SURCHARGE_FUEL_VALUE, SURCHARGE_FUEL_STRING);
	public static SurchargeType SURCHARGE_OFFSHORE = new SurchargeType(SURCHARGE_OFFSHORE_VALUE, SURCHARGE_OFFSHORE_STRING);
	public static SurchargeType SURCHARGE_SIGNATURE_REQUIRED = new SurchargeType(SURCHARGE_SIGNATURE_REQUIRED_VALUE, SURCHARGE_SIGNATURE_REQUIRED_STRING);
	public static SurchargeType SURCHARGE_MISCELLANEOUS = new SurchargeType(SURCHARGE_MISCELLANEOUS_VALUE, SURCHARGE_MISCELLANEOUS_STRING);
	public static SurchargeType SURCHARGE_SCHEDULE_PICKUP = new SurchargeType(SURCHARGE_SCHEDULE_PICKUP_VALUE, SURCHARGE_SCHEDULE_PICKUP_STRING);
	public static SurchargeType SURCHARGE_CUSTOME_INBOUND_FREIGHT = new SurchargeType(SURCHARGE_CUSTOME_INBOUND_FRIEGHT_VALUE, SURCHARGE_CUSTOME_INBOUND_FRIEGHT_STRING);
	public static SurchargeType SURCHARGE_CANADIAN_BORDER_SECURITY= new SurchargeType(SURCHARGE_CANADIAN_BORDER_SECURITY_VALUE, SURCHARGE_CANADIAN_BORDER_SECURITY_STRING);
	public static SurchargeType SURCHARGE_EARLY_AM = new SurchargeType(SURCHARGE_EARLY_AM_VALUE,SURCHARGE_EARLY_AM_STRING);
	public static SurchargeType SURCHARGE_DELIVERY_AREA = new SurchargeType(SURCHARGE_DELIVERY_AREA_VALUE,SURCHARGE_DELIVERY_AREA_STRING);

	

	/**
	 * @return Returns the puroSurchargeId.
	 */
	public int getSurchargeId() {
		return surchargeId;
	}
	/**
	 * @param puroSurchargeId The puroSurchargeId to set.
	 */
	public void setSurchargeId(int puroSurchargeId) {
		this.surchargeId = puroSurchargeId;
	}
	/**
	 * @return Returns the surchargeName.
	 */
	public String getSurchargeName() {
		return surchargeName;
	}
	/**
	 * @param surchargeName The surchargeName to set.
	 */
	public void setSurchargeName(String surchargeName) {
		this.surchargeName = surchargeName;
	}
	
	/*public static SurchargeType getSurchargeByTax(String taxName){
		if(taxName.equals(ProvinceTax.TAX_GST)){
			return SurchargeType.SURCHARGE_GST;
		}
		else if(taxName.equals(ProvinceTax.TAX_HST)){
			return SurchargeType.SURCHARGE_HST;
		}
		else if(taxName.equals(ProvinceTax.TAX_QST)){
			return SurchargeType.SURCHARGE_QST;
		}
		
		//this shouldnt happen!
		return SurchargeType.SURCHARGE_GST;
		
	}*/
	
	
}
