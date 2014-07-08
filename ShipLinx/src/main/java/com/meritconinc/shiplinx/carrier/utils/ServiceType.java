package com.meritconinc.shiplinx.carrier.utils;

public class ServiceType {

	public ServiceType(){};
	public ServiceType(long id, String name){
		this.serviceId = id;
		this.serviceName = name;
	}
	private long serviceId;
	private String serviceName; 
	
	//Fedex services
	public static final int FEDEX_PRIORITY_VALUE = 1;
	public static final int FEDEX_OVERNIGHT_VALUE = 2;
	public static final int FEDEX_GROUND_VALUE = 3;
	public static final int FEDEX_STANDARD_OVERNIGHT_VALUE = 28;
	public static final int FEDEX_2ND_DAY_VALUE = 29;
	public static final int FEDEX_EXPRESS_SAVER_VALUE = 30;
	public static final int FEDEX_INTL_PRIORITY_FREIGHT_VALUE = 31;
	public static final int FEDEX_INTL_ECONOMY_FREIGHT_VALUE = 32;
	public static final int FEDEX_2DAY_FREIGHT_VALUE = 33;
	public static final int FEDEX_3DAY_FREIGHT_VALUE = 34;
	public static String FEDEX_PRIORITY_STRING  = "Priority";
	public static String FEDEX_OVERNIGHT_STRING  = "First Overnight";
	public static String FEDEX_GROUND_STRING  = "Ground";
	public static String FEDEX_STANDARD_OVERNIGHT_STRING  = "Standard Overnight";
	public static String FEDEX_2ND_DAY_STRING  = "2nd Day";
	public static String FEDEX_EXPRESS_SAVER_STRING  = "Express Saver";
	public static final String FEDEX_INTL_PRIORITY_FREIGHT_STRING = "Intl Priority Freight";
	public static final String FEDEX_INTL_ECONOMY_FREIGHT_STRING = "Intl Economy Freight";
	public static final String FEDEX_2DAY_FREIGHT_STRING = "2Day Freight";
	public static final String FEDEX_3DAY_FREIGHT_STRING = "3Day Freight";

	public static ServiceType FEDEX_PRIORITY = new ServiceType(FEDEX_PRIORITY_VALUE, FEDEX_PRIORITY_STRING);
	public static ServiceType FEDEX_OVERNIGHT = new ServiceType(FEDEX_OVERNIGHT_VALUE, FEDEX_OVERNIGHT_STRING);
	public static ServiceType FEDEX_GROUND = new ServiceType(FEDEX_GROUND_VALUE, FEDEX_GROUND_STRING);
	public static ServiceType FEDEX_STANDARD_OVERNIGHT = new ServiceType(FEDEX_STANDARD_OVERNIGHT_VALUE, FEDEX_STANDARD_OVERNIGHT_STRING);
	public static ServiceType FEDEX_2ND_DAY = new ServiceType(FEDEX_2ND_DAY_VALUE, FEDEX_2ND_DAY_STRING);
	public static ServiceType FEDEX_EXPRESS_SAVER = new ServiceType(FEDEX_EXPRESS_SAVER_VALUE, FEDEX_EXPRESS_SAVER_STRING);
	public static ServiceType FEDEX_INTL_PRIORITY_FREIGHT = new ServiceType(FEDEX_INTL_PRIORITY_FREIGHT_VALUE, FEDEX_INTL_PRIORITY_FREIGHT_STRING);
	public static ServiceType FEDEX_INTL_ECONOMY_FREIGHT = new ServiceType(FEDEX_INTL_ECONOMY_FREIGHT_VALUE, FEDEX_INTL_ECONOMY_FREIGHT_STRING);
	public static ServiceType FEDEX_2DAY_FREIGHT = new ServiceType(FEDEX_2DAY_FREIGHT_VALUE, FEDEX_2DAY_FREIGHT_STRING);
	public static ServiceType FEDEX_3DAY_FREIGHT = new ServiceType(FEDEX_3DAY_FREIGHT_VALUE, FEDEX_3DAY_FREIGHT_STRING);

	//Purolator services
	public static long SERVICE_AIR_VALUE = 4;
	public static long SERVICE_AIR_9AM_VALUE = 5;
	public static long SERVICE_AIR_1030_VALUE = 6;
	public static long SERVICE_PUROLETTER_VALUE = 7;
	public static long SERVICE_PUROLETTER_9AM_VALUE = 8;
	public static long SERVICE_PUROLETTER_1030_VALUE = 9;
	public static long SERVICE_PUROPAK_VALUE = 10;
	public static long SERVICE_PUROPAK_9AM_VALUE = 11;
	public static long SERVICE_PUROPAK_1030_VALUE = 12;
	public static long SERVICE_GROUND_VALUE = 13;
	public static long SERVICE_GROUND_9AM_VALUE = 19;
	public static long SERVICE_GROUND_1030_VALUE = 20;
	public static long SERVICE_EXPRESSCHEQUE_VALUE = 18;
	public static String SERVICE_AIR_STRING = "Air";
	public static String SERVICE_AIR_9AM_STRING = "Air 9AM";
	public static String SERVICE_AIR_1030_STRING = "Air 1030";
	public static String SERVICE_PUROLETTER_STRING = "Puroletter";
	public static String SERVICE_PUROLETTER_9AM_STRING = "Puroletter 9AM";
	public static String SERVICE_PUROLETTER_1030_STRING = "Puroletter 1030";
	public static String SERVICE_PUROPAK_STRING = "PuroPak";
	public static String SERVICE_PUROPAK_9AM_STRING = "PuroPak 9AM";
	public static String SERVICE_PUROPAK_1030_STRING = "PuroPak 1030";
	public static String SERVICE_GROUND_STRING = "Ground";
	public static String SERVICE_GROUND_9AM_STRING = "Ground 9AM";
	public static String SERVICE_GROUND_1030_STRING = "Ground 1030";

	public static ServiceType AIR = new ServiceType(SERVICE_AIR_VALUE, SERVICE_AIR_STRING);
	public static ServiceType AIR_9AM = new ServiceType(SERVICE_AIR_9AM_VALUE, SERVICE_AIR_9AM_STRING);
	public static ServiceType AIR_1030 = new ServiceType(SERVICE_AIR_1030_VALUE, SERVICE_AIR_1030_STRING);
	public static ServiceType PUROLETTER = new ServiceType(SERVICE_PUROLETTER_VALUE, SERVICE_PUROLETTER_STRING);
	public static ServiceType PUROLETTER_9AM = new ServiceType(SERVICE_PUROLETTER_9AM_VALUE, SERVICE_PUROLETTER_9AM_STRING);
	public static ServiceType PUROLETTER_1030 = new ServiceType(SERVICE_PUROLETTER_1030_VALUE, SERVICE_PUROLETTER_1030_STRING);
	public static ServiceType PUROPAK = new ServiceType(SERVICE_PUROPAK_VALUE, SERVICE_PUROPAK_STRING);
	public static ServiceType PUROPAK_9AM = new ServiceType(SERVICE_PUROPAK_9AM_VALUE, SERVICE_PUROPAK_9AM_STRING);
	public static ServiceType PUROPAK_1030 = new ServiceType(SERVICE_PUROPAK_1030_VALUE, SERVICE_PUROPAK_1030_STRING);
	public static ServiceType GROUND = new ServiceType(SERVICE_GROUND_VALUE, SERVICE_GROUND_STRING);
	public static ServiceType GROUND_9AM = new ServiceType(SERVICE_GROUND_9AM_VALUE, SERVICE_GROUND_9AM_STRING);
	public static ServiceType GROUND_1030 = new ServiceType(SERVICE_GROUND_1030_VALUE, SERVICE_GROUND_1030_STRING);

	//CWW services
	public static int SERVICE_SAME_DAY_VALUE = 14;
	public static int SERVICE_NEXT_FLIGHT_OUT_VALUE = 15;
	public static int SERVICE_AIR_FREIGHT_VALUE = 16;
	public static int SERVICE_LTL_VALUE = 17;
	public static String SERVICE_SAME_DAY_STRING = "Same Day";
	public static String SERVICE_NEXT_FLIGHT_OUT_STRING = "Next Flight Out";
	public static String SERVICE_AIR_FREIGHT_STRING = "Air Freight";
	public static String SERVICE_LTL_STRING = "LTL";

	public static ServiceType NFO = new ServiceType(SERVICE_NEXT_FLIGHT_OUT_VALUE, SERVICE_NEXT_FLIGHT_OUT_STRING);
	public static ServiceType AIR_FREIGHT = new ServiceType(SERVICE_AIR_FREIGHT_VALUE, SERVICE_AIR_FREIGHT_STRING);
	public static ServiceType LTL = new ServiceType(SERVICE_LTL_VALUE, SERVICE_LTL_STRING);
	public static ServiceType SAME_DAY = new ServiceType(SERVICE_SAME_DAY_VALUE, SERVICE_SAME_DAY_STRING);
	
	public static int SERVICE_LTL_USA_VALUE = 25;
	public static int SERVICE_AIR_FRIEGHT_USA_PREMIUM_VALUE = 26;
	public static int SERVICE_AIR_FRIEGHT_USA_ECONOMY_VALUE = 27;
	public static String SERVICE_LTL_USA_STRING = "LTL USA";
	public static String SERVICE_AIR_FRIEGHT_USA_PREMIUM_STRING = "Air Freight USA Premium";
	public static String SERVICE_AIR_FRIEGHT_USA_ECONOMY_STRING = "Air Freight USA Economy";
	public static ServiceType SERVICE_LTL_USA = new ServiceType(SERVICE_LTL_USA_VALUE, SERVICE_LTL_USA_STRING);
	public static ServiceType SERVICE_AIR_FRIEGHT_USA_PREMIUM = new ServiceType(SERVICE_AIR_FRIEGHT_USA_PREMIUM_VALUE, SERVICE_AIR_FRIEGHT_USA_PREMIUM_STRING);
	public static ServiceType SERVICE_AIR_FRIEGHT_USA_ECONOMY = new ServiceType(SERVICE_AIR_FRIEGHT_USA_ECONOMY_VALUE, SERVICE_AIR_FRIEGHT_USA_ECONOMY_STRING);
	
	//DHL Services
	public static int DHL_SERVICE_EXPRESS_VALUE = 100;
	public static int DHL_SERVICE_EXPRESS_1030_VALUE = 101;
	public static int DHL_SERVICE_EXPRESS_SATURDAY_VALUE = 102;
	public static int DHL_SERVICE_SECOND_DAY_VALUE = 103;
	public static int DHL_SERVICE_NEXT_AFTERNOON_VALUE = 104;
	public static int DHL_SERVICE_GROUND_VALUE = 105;
	public static int DHL_SERVICE_INTERNATIONAL_EXPRESS_VALUE = 106;
	public static String DHL_SERVICE_EXPRESS_STRING = "DHL Express";
	public static String DHL_SERVICE_EXPRESS_1030_STRING = "DHL Express 10:30";
	public static String DHL_SERVICE_EXPRESS_SATURDAY_STRING = "DHL Express Saturday";
	public static String DHL_SERVICE_SECOND_DAY_STRING = "DHL Second Day";
	public static String DHL_SERVICE_NEXT_AFTERNOON_STRING = "DHL Next Afternoon";
	public static String DHL_SERVICE_GROUND_STRING = "DHL Ground";
	public static String DHL_SERVICE_INTERNATIONAL_EXPRESS_STRING = "DHL International Express";
	public static ServiceType DHL_EXPRESS = new ServiceType(DHL_SERVICE_EXPRESS_VALUE, DHL_SERVICE_EXPRESS_STRING);
	public static ServiceType DHL_EXPRESS_1030 = new ServiceType(DHL_SERVICE_EXPRESS_1030_VALUE, DHL_SERVICE_EXPRESS_1030_STRING);
	public static ServiceType DHL_SATURDAY = new ServiceType(DHL_SERVICE_EXPRESS_SATURDAY_VALUE, DHL_SERVICE_EXPRESS_SATURDAY_STRING);
	public static ServiceType DHL_SECOND_DAY = new ServiceType(DHL_SERVICE_SECOND_DAY_VALUE, DHL_SERVICE_SECOND_DAY_STRING);
	public static ServiceType DHL_NEXT_AFTERNOON = new ServiceType(DHL_SERVICE_NEXT_AFTERNOON_VALUE, DHL_SERVICE_NEXT_AFTERNOON_STRING);
	public static ServiceType DHL_GROUND = new ServiceType(DHL_SERVICE_GROUND_VALUE, DHL_SERVICE_GROUND_STRING);
	public static ServiceType DHL_INTERNATIONAL_EXPRESS = new ServiceType(DHL_SERVICE_INTERNATIONAL_EXPRESS_VALUE, DHL_SERVICE_INTERNATIONAL_EXPRESS_STRING);
	
	//CANADAPOST SERVICES
	public static int PRIORITY_COURIER_VALUE = 500;
	public static int XPRESS_POST_VALUE = 501;
	public static int EXPEDITED_VALUE = 502;
	public static int REGULAR_VALUE = 503;
	public static int XPRESS_POST_USA_VALUE = 504;
	public static int XPRESS_POST_INTERNATIONAL_VALUE =505;
	public static int AIRPARCEL_INTERNATIONAL_VALUE = 506;
	public static int SURFACE_PARCEL_INTERNATIONAL_VALUE = 507;
	public static int EXPEDITED_PARCEL_USA_VALUE= 508;
	public static String PRIORITY_COURIER_STRING=" Priority Courier";
	public static String XPRESS_POST_STRING = "Xpress Post";
	public static String EXPEDITED_STRING = "Expedited";
	public static String  REGULAR_STRING ="Regular";
	public static String XPRESS_POST_USA_STRING = "Xpress Post USA";
	public static String XPRESS_POST_INTERNATIONAL_STRING ="Xpress Post INTL";
	public static String AIRPARCEL_INTERNATIONAL_STRING = "AirParcel INTL";
	public static String SURFACE_PARCEL_INTERNATIONAL_STRING="Surface Parcel INTL";
	public static String EXPEDITED_PARCEL_USA_STRING ="Expedited Parcel USA";
	
	public static ServiceType PC =new ServiceType(PRIORITY_COURIER_VALUE,PRIORITY_COURIER_STRING) ;
	public static ServiceType XP= new ServiceType(XPRESS_POST_VALUE,XPRESS_POST_STRING);
	public static ServiceType EXP= new ServiceType(EXPEDITED_VALUE ,EXPEDITED_STRING);
	public static ServiceType REGULAR = new ServiceType(REGULAR_VALUE,REGULAR_STRING );
	public static ServiceType XPUSA =new ServiceType(XPRESS_POST_USA_VALUE,XPRESS_POST_USA_STRING);
	public static ServiceType XPINTL = new ServiceType(XPRESS_POST_INTERNATIONAL_VALUE,XPRESS_POST_INTERNATIONAL_STRING);
	public static ServiceType AIRINTL = new ServiceType(AIRPARCEL_INTERNATIONAL_VALUE ,AIRPARCEL_INTERNATIONAL_STRING);
	public static ServiceType SURFACEINTL= new ServiceType(SURFACE_PARCEL_INTERNATIONAL_VALUE,SURFACE_PARCEL_INTERNATIONAL_STRING);
	public static ServiceType EXPUSA =new ServiceType(EXPEDITED_PARCEL_USA_VALUE,EXPEDITED_PARCEL_USA_STRING);
	
	//TST SERVICES
	public static int LTL_TRUCKING_VALUE = 1100;
	public static String LTL_TRUCKING_STRING="LTL Trucking";
	
	public static ServiceType TST_LTL_TRUCKING =new ServiceType(LTL_TRUCKING_VALUE,LTL_TRUCKING_STRING) ;
	
	//MERCURY GATE SERVICES
	public static int MG_CHICAGO_SUBURBAN_EXPRESS_VALUE = 1500;
	public static int MG_FEDEX_FREIGHT_EAST_VALUE = 1501;
	public static int MG_FEDEX_FREIGHT_WEST_VALUE = 1502;
	public static int MG_MID_STATES_EXPRESS_VALUE = 1503;
	public static int MG_NEW_ENGLAND_MOTOR_FREIGHT_VALUE = 1504;
	public static int MG_NEW_PENN_VALUE = 1505;
	public static int MG_OAK_HARBOR_VALUE = 1506;
	public static int MG_PITT_OHIO_VALUE = 1507;
	public static int MG_R_AND_L_CARRIERS_VALUE = 1508;
	public static int MG_SAIA_VALUE = 1509;
	public static int MG_USF_REDDAWAY_VALUE = 1510;
	public static int MG_VITRAN_EXPRESS_VALUE = 1511;
	public static int MG_WILSON_TRUCKING_VALUE = 1512;
	public static int MG_YELLOW_TRANSPORTATION_VALUE = 1513;
	public static int MG_ROADWAY_VALUE = 1514;
	public static int MG_FEDEX_NATIONAL_VALUE = 1515;
	
	public static String MG_CHICAGO_SUBURBAN_EXPRESS_STRING="Chicago Suburban Express";
	public static String MG_FEDEX_FREIGHT_EAST_STRING="FedEx Freight East";
	public static String MG_FEDEX_FREIGHT_WEST_STRING="FedEx Freight West";
	public static String MG_MID_STATES_EXPRESS_STRING="Mid-States Express";
	public static String MG_NEW_ENGLAND_MOTOR_FREIGHT_STRING="New England Motor Freight";
	public static String MG_NEW_PENN_STRING="New Penn";
	public static String MG_OAK_HARBOR_STRING="Oak Harbor";
	public static String MG_PITT_OHIO_STRING="Pitt Ohio";
	public static String MG_R_AND_L_CARRIERS_STRING="R&L Carriers";
	public static String MG_SAIA_STRING="SAIA";
	public static String MG_USF_REDDAWAY_STRING="USF Reddaway";
	public static String MG_VITRAN_EXPRESS_STRING="Vitran Express";
	public static String MG_WILSON_TRUCKING_STRING="Wilson Trucking";
	public static String MG_YELLOW_TRANSPORTATION_STRING="Yellow Transportation";
	public static String MG_ROADWAY_STRING="Roadway";
	public static String MG_FEDEX_NATIONAL_STRING="Fedex National";
	
	public static ServiceType MG_CSEQ =new ServiceType(MG_CHICAGO_SUBURBAN_EXPRESS_VALUE,MG_CHICAGO_SUBURBAN_EXPRESS_STRING);
	public static ServiceType MG_FXFE =new ServiceType(MG_FEDEX_FREIGHT_EAST_VALUE,MG_FEDEX_FREIGHT_EAST_STRING);
	public static ServiceType MG_FXFW =new ServiceType(MG_FEDEX_FREIGHT_WEST_VALUE,MG_FEDEX_FREIGHT_WEST_STRING);
	public static ServiceType MG_MSXN =new ServiceType(MG_MID_STATES_EXPRESS_VALUE,MG_MID_STATES_EXPRESS_STRING);
	public static ServiceType MG_NEMF =new ServiceType(MG_NEW_ENGLAND_MOTOR_FREIGHT_VALUE,MG_NEW_ENGLAND_MOTOR_FREIGHT_STRING);
	public static ServiceType MG_NPME =new ServiceType(MG_NEW_PENN_VALUE,MG_NEW_PENN_STRING);
	public static ServiceType MG_OAKH =new ServiceType(MG_OAK_HARBOR_VALUE,MG_OAK_HARBOR_STRING);
	public static ServiceType MG_PITD =new ServiceType(MG_PITT_OHIO_VALUE,MG_PITT_OHIO_STRING);
	public static ServiceType MG_RLNO =new ServiceType(MG_R_AND_L_CARRIERS_VALUE,MG_R_AND_L_CARRIERS_STRING);
	public static ServiceType MG_SAIA =new ServiceType(MG_SAIA_VALUE,MG_SAIA_STRING);
	public static ServiceType MG_RETL =new ServiceType(MG_USF_REDDAWAY_VALUE,MG_USF_REDDAWAY_STRING);
	public static ServiceType MG_VITR =new ServiceType(MG_VITRAN_EXPRESS_VALUE,MG_VITRAN_EXPRESS_STRING);
	public static ServiceType MG_WTVA =new ServiceType(MG_WILSON_TRUCKING_VALUE,MG_WILSON_TRUCKING_STRING);
	public static ServiceType MG_YFSY =new ServiceType(MG_YELLOW_TRANSPORTATION_VALUE,MG_YELLOW_TRANSPORTATION_STRING);
	public static ServiceType MG_RDWY =new ServiceType(MG_ROADWAY_VALUE,MG_ROADWAY_STRING);
	public static ServiceType MG_FXNL =new ServiceType(MG_FEDEX_NATIONAL_VALUE,MG_FEDEX_NATIONAL_STRING);
	

	/**
	 * @return Returns the serviceId.
	 */
	public long getServiceId() {
		return serviceId;
	}
	/**
	 * @param serviceId The serviceId to set.
	 */
	public void setServiceId(long serviceId) {
		this.serviceId = serviceId;
	}
	/**
	 * @return Returns the serviceName.
	 */
	public String getServiceName() {
		return serviceName;
	}
	/**
	 * @param serviceName The serviceName to set.
	 */
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public static ServiceType getServiceType(long serviceId){

		if(serviceId==FEDEX_PRIORITY_VALUE) return FEDEX_PRIORITY;
		if(serviceId==FEDEX_OVERNIGHT_VALUE) return FEDEX_OVERNIGHT;								 
		if(serviceId==FEDEX_GROUND_VALUE) return FEDEX_GROUND;
		if(serviceId==FEDEX_STANDARD_OVERNIGHT_VALUE) return FEDEX_STANDARD_OVERNIGHT;
		if(serviceId==FEDEX_2ND_DAY_VALUE) return FEDEX_2ND_DAY;
		if(serviceId==FEDEX_EXPRESS_SAVER_VALUE) return FEDEX_EXPRESS_SAVER;
		if(serviceId==FEDEX_INTL_PRIORITY_FREIGHT_VALUE) return FEDEX_INTL_PRIORITY_FREIGHT;
		if(serviceId==FEDEX_INTL_ECONOMY_FREIGHT_VALUE) return FEDEX_INTL_ECONOMY_FREIGHT;
		if(serviceId==FEDEX_2DAY_FREIGHT_VALUE) return FEDEX_2DAY_FREIGHT;
		if(serviceId==FEDEX_3DAY_FREIGHT_VALUE) return FEDEX_3DAY_FREIGHT;

		if(serviceId==SERVICE_AIR_VALUE) return AIR;
		if(serviceId==SERVICE_AIR_9AM_VALUE) return AIR_9AM;								 
		if(serviceId==SERVICE_AIR_1030_VALUE) return AIR_1030;
		if(serviceId==SERVICE_PUROLETTER_VALUE) return PUROLETTER;
		if(serviceId==SERVICE_PUROLETTER_9AM_VALUE) return PUROLETTER_9AM;								 
		if(serviceId==SERVICE_PUROLETTER_1030_VALUE) return PUROLETTER_1030;
		if(serviceId==SERVICE_PUROPAK_VALUE) return PUROPAK;
		if(serviceId==SERVICE_PUROPAK_9AM_VALUE) return PUROPAK_9AM;								 
		if(serviceId==SERVICE_PUROPAK_1030_VALUE) return PUROPAK_1030;
		if(serviceId==SERVICE_GROUND_VALUE) return GROUND;	
		if(serviceId==SERVICE_GROUND_9AM_VALUE) return GROUND_9AM;
		if(serviceId==SERVICE_GROUND_1030_VALUE) return GROUND_1030;
		
		if(serviceId==SERVICE_NEXT_FLIGHT_OUT_VALUE) return NFO;
		if(serviceId==SERVICE_AIR_FREIGHT_VALUE) return AIR_FREIGHT;								 
		if(serviceId==SERVICE_LTL_VALUE) return LTL;
		if(serviceId==SERVICE_SAME_DAY_VALUE) return SAME_DAY;
		
		if(serviceId==DHL_SERVICE_EXPRESS_VALUE) return DHL_EXPRESS;
		if(serviceId==DHL_SERVICE_EXPRESS_1030_VALUE) return DHL_EXPRESS_1030;
		if(serviceId==DHL_SERVICE_EXPRESS_SATURDAY_VALUE) return DHL_SATURDAY;
		if(serviceId==DHL_SERVICE_SECOND_DAY_VALUE) return DHL_SECOND_DAY;
		if(serviceId==DHL_SERVICE_NEXT_AFTERNOON_VALUE) return DHL_NEXT_AFTERNOON;
		if(serviceId==DHL_SERVICE_GROUND_VALUE) return DHL_GROUND;
		if(serviceId==DHL_SERVICE_INTERNATIONAL_EXPRESS_VALUE) return DHL_INTERNATIONAL_EXPRESS;
		
		if(serviceId==PRIORITY_COURIER_VALUE)return PC ;
		if(serviceId==XPRESS_POST_VALUE)return XP;
		if(serviceId==EXPEDITED_VALUE)return EXP ;
		if(serviceId==REGULAR_VALUE)return REGULAR ;
		if(serviceId==XPRESS_POST_USA_VALUE )return XPUSA ;
		if(serviceId==XPRESS_POST_INTERNATIONAL_VALUE )return XPINTL ;
		if(serviceId==AIRPARCEL_INTERNATIONAL_VALUE )return AIRINTL ;
		if(serviceId==SURFACE_PARCEL_INTERNATIONAL_VALUE)return SURFACEINTL;
		if(serviceId==EXPEDITED_PARCEL_USA_VALUE)return EXPUSA ;
		
		if(serviceId==LTL_TRUCKING_VALUE) return TST_LTL_TRUCKING;
		return null;
	}
	
	public static boolean is9AMService(long serviceId){
		if(serviceId==SERVICE_AIR_9AM_VALUE || serviceId==SERVICE_PUROLETTER_9AM_VALUE || serviceId==SERVICE_PUROPAK_9AM_VALUE || serviceId==SERVICE_GROUND_9AM_VALUE )
			return true;
		return false;
	}
	
	public static boolean is1030Service(long serviceId){
		if(serviceId==SERVICE_AIR_1030_VALUE || serviceId==SERVICE_PUROLETTER_1030_VALUE || serviceId==SERVICE_PUROPAK_1030_VALUE  || serviceId==SERVICE_GROUND_1030_VALUE)
			return true;
		return false;
	}
	
	public static boolean isPuroLetterService(long serviceId){
		if(serviceId==SERVICE_PUROLETTER_9AM_VALUE || serviceId==SERVICE_PUROLETTER_1030_VALUE || serviceId==SERVICE_PUROLETTER_VALUE)
			return true;
		return false;
	}

	public static boolean isPuropakService(long serviceId){
		if(serviceId==SERVICE_PUROPAK_9AM_VALUE || serviceId==SERVICE_PUROPAK_1030_VALUE || serviceId==SERVICE_PUROPAK_VALUE)
			return true;
		return false;
	}

	public static boolean isPuroGroundService(long serviceId){
		if(serviceId==SERVICE_GROUND_VALUE || serviceId==SERVICE_GROUND_9AM_VALUE || serviceId==SERVICE_GROUND_1030_VALUE)
			return true;
		return false;
	}

}
