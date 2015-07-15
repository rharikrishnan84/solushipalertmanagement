package com.meritconinc.shiplinx.carrier.eshipplus.eshipplusservice;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.WordUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.meritconinc.mmr.utilities.MmrBeanLocator;
import com.meritconinc.shiplinx.carrier.eshipplus.AuthenticationToken;
import com.meritconinc.shiplinx.carrier.eshipplus.EShipPlusWSv4;
import com.meritconinc.shiplinx.carrier.eshipplus.EShipPlusWSv4Soap;
import com.meritconinc.shiplinx.carrier.eshipplus.WSBillingDetail;
import com.meritconinc.shiplinx.carrier.eshipplus.WSRate2;
import com.meritconinc.shiplinx.carrier.eshipplus.WSShipment2;
import com.meritconinc.shiplinx.dao.ShippingDAO;
import com.meritconinc.shiplinx.model.Carrier;
import com.meritconinc.shiplinx.model.CustomerCarrier;
import com.meritconinc.shiplinx.model.Markup;
import com.meritconinc.shiplinx.model.Rating;
import com.meritconinc.shiplinx.model.Service;
import com.meritconinc.shiplinx.model.ShippingOrder;
import com.meritconinc.shiplinx.service.MarkupManager;
import com.meritconinc.shiplinx.utils.FormattingUtil;
import com.meritconinc.shiplinx.utils.ShiplinxConstants;


public class EshipPlusRequestConnector {
	protected MarkupManager markupManagerService;
	private static final Logger log = LogManager.getLogger(EShipPlusAPI.class);
	private final static String Username ="ryan.blakey"; 
	//private final static String Password = "Reynard1";
	private final static String Password = "Reynard1";
	private final static String Accesscode ="TENANT1"; 
	//private final static String AccessKey ="dd9c2694-672b-4498-9a96-2a96f3f82364";
	private final static String AccessKey ="a33b98de-a066-4766-ac9e-1eab39ce6806";
	public static String LIVE_URL_RATE = "http://www.eshipplus.com/services/eShipPlusWSv4.asmx";
	EShipPlusWSv4 eshipPluswv4 = null;
	private CustomerCarrier customerCarrier;
	AuthenticationToken authenticationToken = null;
	/*
	 * setting the eship splus authenticating token in the constructor
	 */
	public EshipPlusRequestConnector(){
		authenticationToken = authentication();
	}
	
	public AuthenticationToken authentication() {
		
		AuthenticationToken authenticationtoken = new AuthenticationToken();
		authenticationtoken.setUsername(Username);
		authenticationtoken.setPassword(Password);
		authenticationtoken.setAccessCode(Accesscode);
		authenticationtoken.setAccessKey(AccessKey);
		return authenticationtoken;
	}
	
	public EshipPlusRequestConnector(CustomerCarrier customerCarrier)
	{
		this.customerCarrier=customerCarrier;
		this.LIVE_URL_RATE=customerCarrier.getProperty5();
		authenticationToken = authentication(this.customerCarrier);
	}
	
	private AuthenticationToken authentication(CustomerCarrier customerCarrier) {
		AuthenticationToken authenticationtoken = new AuthenticationToken();
		authenticationtoken.setUsername(customerCarrier.getProperty1());
		authenticationtoken.setPassword(customerCarrier.getProperty2());
		authenticationtoken.setAccessCode(customerCarrier.getProperty3());
		authenticationtoken.setAccessKey(customerCarrier.getProperty4());
		return authenticationtoken;
	}
	
	/*
	 * return the eshipplus v4 soap service
	 */
	public EShipPlusWSv4Soap getEshipPlusWS(){
		eshipPluswv4 = new EShipPlusWSv4();
		return eshipPluswv4.getEShipPlusWSv4Soap();
	}
	
	//getting the rates from the eship plus 
	public WSShipment2 getEshipPlusRate(WSShipment2 shipmentDetails){
		WSShipment2 rateShipment= new WSShipment2();
		try {
				rateShipment = getEshipPlusWS().rate(shipmentDetails, authenticationToken);
			return rateShipment;
		} catch (Exception e) {

			log.error("Error occured in rateShipment Pickup",e);
		}
		return rateShipment;
	}
	
	/*
	 * converting the rate from request to rating list
	 */
	public List<Rating> buildRateFromRequest(ShippingOrder order,WSShipment2 shipment2, List<Rating> ratingList, CustomerCarrier customerCarrier, List<Service> services){
		
		List<WSRate2> rateList = shipment2.getAvailableRates().getWSRate2();
		/*********************************************************Filtering carrier for this customer**************************************************************************/
		markupManagerService = (MarkupManager) MmrBeanLocator.getInstance().findBean("markupManagerService");
		List<WSRate2> newRatingTemp=new ArrayList<WSRate2>();
		Markup markup=new Markup();
		markup.setCustomerId(order.getCustomerId());
	    markup.setDisabled(0);
		for(WSRate2 rating:rateList){
	        boolean flagCarrierList = markupManagerService.getEshipCarriersbyCustomerId(rating.getCarrierScac(),order.getCustomerId());
	        if (flagCarrierList){
	        	newRatingTemp.add(rating);
	        }
		}
		rateList=newRatingTemp;
		ratingList = new ArrayList<Rating>();
		List<WSRate2> newRating=new ArrayList<WSRate2>();
		/********************************************Grouping Received Rates based on transit time and cheapest Rate**********************************************************/
		for(int i=0;i<20;i++){
			WSRate2 smallRate=null;
			for(WSRate2 rating:rateList){
				if(rating.getTransitTime()==i){
					if(smallRate==null || ((smallRate.getTotalCharges().compareTo(rating.getTotalCharges()))==1)){
						smallRate=rating;
					}
				}
			}
			if(smallRate!=null){
				newRating.add(smallRate);
			}
		}
        if(newRating.size()>0){
        	rateList=newRating;
        }	
        /**********************************************Assign all the values of received rate into local ratelist bean************************************************/
		if(rateList != null && services!=null)
			{
					for (int j = 0; j < rateList.size() && j!=3; j++) {
						Integer transit=0;
						String timeInTransit = null;
						if(timeInTransit !=  null || timeInTransit == null)
							timeInTransit = String.valueOf(rateList.get(j).getTransitTime());
						
						if(rateList != null)
						{				
							//-------------------------------Enough needed for ship---------------
							Rating rating = new Rating();
							//ASSIGNING ESHIP PLUS CARRIER ID
							rating.setCarrierId(ShiplinxConstants.CARRIER_ESHIPPLUS);
							//ASSIGNING ESHIP PLUS CARRIER NAME(masking as integrated carriers)
						    rating.setCarrierName(ShiplinxConstants.CARRIER_ESHIPPLUS_STRING);
							//ASSIGNING CARRIER KEY WHICH REQUIRED FOR SHIP ORDER
							rating.setCarrierKeyLP(rateList.get(j).getCarrierKey());
							///Original Carrier Name
							rating.setCarrierNameLP(rateList.get(j).getCarrierName());
						    //CarrierScac
							rating.setCarrierScacLP(rateList.get(j).getCarrierScac());
							//Bill weight
							rating.setBillWeight(rateList.get(j).getBilledWeight().doubleValue());
							//Tracsit Days
							rating.setTransitDays(rateList.get(j).getTransitTime());
							//Service Mode
							rating.setServiceMode(rateList.get(j).getServiceMode().name());
							//Freight charge
						    rating.setBaseCharge(Double.parseDouble(rateList.get(j).getFreightCharges().toString()));
						    //Fuel Charge
						    rating.setFuelSurcharge(Double.parseDouble(rateList.get(j).getFuelCharges().toString()));
						    //Accessorial Charge
							rating.setAccessorialChargeLP(Double.parseDouble(rateList.get(j).getAccessorialCharges().toString()));
							//Service Charge
							Double serviceCh=Double.parseDouble(rateList.get(j).getServiceCharges().toString());
						    rating.setServiceCharge(serviceCh.longValue());
						    //Total charge
						    rating.setTotalSurcharge(rateList.get(j).getTotalCharges().longValue());
							//Mileage
							rating.setMileageLP(Long.parseLong(rateList.get(j).getMileage().toString()));
							//Mileage sourcekey
							rating.setMileageSourceKey(rateList.get(j).getMileageSourceKey());
							//Service Id
							if(j>2){
								rating.setServiceId(services.get(2).getId());
								//Service name
								rating.setServiceName(services.get(2).getName());
								//ASSIGNING CUSTOMER CARRIER WHICH IS RESPONSIBLE FOR ACCOUNT DETAILS
								rating.setCustomerCarrier(customerCarrier); //remember the account that was used to rate, we will use the same one at shipping time
								//Currency
							}else{
								rating.setServiceId(services.get(j).getId());
								//Service name
								rating.setServiceName(services.get(j).getName());
								//ASSIGNING CUSTOMER CARRIER WHICH IS RESPONSIBLE FOR ACCOUNT DETAILS
							}
							rating.setCustomerCarrier(customerCarrier); //remember the account that was used to rate, we will use the same one at shipping time
							//Currency
							if(order.getCurrency()==null || "".equalsIgnoreCase(order.getCurrency())){
								rating.setCurrency("USD");
							}else{
							rating.setCurrency(order.getCurrency());
							}
							//Total cost
							rating.setTotalCost(getFormattedValue(rateList.get(j).getTotalCharges().doubleValue()));
							
							double totalFreightDiscounts = getFormattedValue(0.0);//Hard coded
							//ADDING FREIGHT CHARGE DETAILS
							com.meritconinc.shiplinx.model.Charge charge = new com.meritconinc.shiplinx.model.Charge();
							double surchargeValue = rating.getBaseCharge();
							String surchargeName = EShipPlusAPI.FREIGHT_CHARGE_CODE;
							charge.setName(ShiplinxConstants.FREIGHT_STRING);
							charge.setCarrierName(rating.getCarrierNameLP());
							charge.setCost(surchargeValue);
							charge.setCharge(surchargeValue);
							charge.setChargeCode(surchargeName);
							charge.setTariffRate((FormattingUtil.add(surchargeValue, totalFreightDiscounts)).doubleValue());
							double freightTariff = charge.getTariffRate();
							rating.getCharges().add(charge);
							double fuelSurchargePercentage = rateList.get(j).getFuelCharges().doubleValue()/100;
							log.debug("Base rate is " + rating.getBaseCharge());
							log.debug("Fuel surcharge perc=" + fuelSurchargePercentage);
							
							Integer noOfCharges=new Integer(rateList.get(j).getBillingDetails().getWSBillingDetail().size());
							Integer index=0;
							for(WSBillingDetail wsrating: rateList.get(j).getBillingDetails().getWSBillingDetail()){
								log.debug("Charge type/amount/description : ");
								//Skip the freight charge which already added in bean and skip the charge which have zero charge 
								BigDecimal zeroBigdecimal = new BigDecimal(0);
								if(wsrating.getCategory().name().equalsIgnoreCase(ShiplinxConstants.FREIGHT_STRING)){
									continue;
								}
								charge = new com.meritconinc.shiplinx.model.Charge();
								String name=wsrating.getCategory().name().toLowerCase();
									charge.setName(WordUtils.capitalize(name));
									charge.setCarrierName(rating.getCarrierNameLP());
									charge.setCost(Double.parseDouble(wsrating.getAmountDue().toString()));
									charge.setCharge(Double.parseDouble(wsrating.getAmountDue().toString()));
									charge.setChargeCode(wsrating.getDescription());
									charge.setTariffRate(charge.getCost());
									charge.setChargeCodeLevel2(wsrating.getBillingCode());
									rating.getCharges().add(charge);
							}
							ratingList.add(rating);
						}
				}
			}
			return ratingList;
	}
	private double getFormattedValue(double amount){
		DecimalFormat twoDForm = new DecimalFormat("#.##");
		return Double.valueOf(twoDForm.format(amount));
	}
}
