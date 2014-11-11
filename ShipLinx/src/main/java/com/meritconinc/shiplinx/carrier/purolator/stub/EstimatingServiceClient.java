package com.meritconinc.shiplinx.carrier.purolator.stub;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;

import org.apache.cxf.common.logging.LogUtils;
import org.apache.cxf.configuration.security.AuthorizationPolicy;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.headers.Header;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxb.JAXBDataBinding;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.transport.http.HTTPConduit;
import org.apache.cxf.transports.http.configuration.HTTPClientPolicy;
import org.apache.log4j.Logger;

import com.meritconinc.mmr.model.security.User;
import com.meritconinc.mmr.utilities.MmrBeanLocator;
import com.meritconinc.mmr.utilities.StringUtil;
import com.meritconinc.mmr.utilities.security.UserUtil;
import com.meritconinc.shiplinx.carrier.purolator.PurolatorAPI;
import com.meritconinc.shiplinx.carrier.purolator.ws.estimating.proxy.Address;
import com.meritconinc.shiplinx.carrier.purolator.ws.estimating.proxy.ArrayOfContentDetail;
import com.meritconinc.shiplinx.carrier.purolator.ws.estimating.proxy.ArrayOfOptionIDValuePair;
import com.meritconinc.shiplinx.carrier.purolator.ws.estimating.proxy.ArrayOfOptionPrice;
import com.meritconinc.shiplinx.carrier.purolator.ws.estimating.proxy.ArrayOfPiece;
import com.meritconinc.shiplinx.carrier.purolator.ws.estimating.proxy.ArrayOfShipmentEstimate;
import com.meritconinc.shiplinx.carrier.purolator.ws.estimating.proxy.ArrayOfSurcharge;
import com.meritconinc.shiplinx.carrier.purolator.ws.estimating.proxy.ArrayOfTax;
import com.meritconinc.shiplinx.carrier.purolator.ws.estimating.proxy.BillDutiesToParty;
import com.meritconinc.shiplinx.carrier.purolator.ws.estimating.proxy.BusinessRelationship;
import com.meritconinc.shiplinx.carrier.purolator.ws.estimating.proxy.ContentDetail;
import com.meritconinc.shiplinx.carrier.purolator.ws.estimating.proxy.Dimension;
import com.meritconinc.shiplinx.carrier.purolator.ws.estimating.proxy.DimensionUnit;
import com.meritconinc.shiplinx.carrier.purolator.ws.estimating.proxy.DutyCurrency;
import com.meritconinc.shiplinx.carrier.purolator.ws.estimating.proxy.DutyInformation;
import com.meritconinc.shiplinx.carrier.purolator.ws.estimating.proxy.EstimatingServiceContract;
import com.meritconinc.shiplinx.carrier.purolator.ws.estimating.proxy.EstimatingServiceContractGetFullEstimateValidationFaultFaultFaultMessage;
import com.meritconinc.shiplinx.carrier.purolator.ws.estimating.proxy.GetFullEstimateRequestContainer;
import com.meritconinc.shiplinx.carrier.purolator.ws.estimating.proxy.GetFullEstimateResponseContainer;
import com.meritconinc.shiplinx.carrier.purolator.ws.estimating.proxy.ImportExportType;
import com.meritconinc.shiplinx.carrier.purolator.ws.estimating.proxy.InternationalInformation;
import com.meritconinc.shiplinx.carrier.purolator.ws.estimating.proxy.NotificationInformation;
import com.meritconinc.shiplinx.carrier.purolator.ws.estimating.proxy.ObjectFactory;
import com.meritconinc.shiplinx.carrier.purolator.ws.estimating.proxy.OptionIDValuePair;
import com.meritconinc.shiplinx.carrier.purolator.ws.estimating.proxy.OptionPrice;
import com.meritconinc.shiplinx.carrier.purolator.ws.estimating.proxy.OptionsInformation;
import com.meritconinc.shiplinx.carrier.purolator.ws.estimating.proxy.OtherInformation;
import com.meritconinc.shiplinx.carrier.purolator.ws.estimating.proxy.PackageInformation;
import com.meritconinc.shiplinx.carrier.purolator.ws.estimating.proxy.PaymentInformation;
import com.meritconinc.shiplinx.carrier.purolator.ws.estimating.proxy.PaymentType;
import com.meritconinc.shiplinx.carrier.purolator.ws.estimating.proxy.PhoneNumber;
import com.meritconinc.shiplinx.carrier.purolator.ws.estimating.proxy.PickupInformation;
import com.meritconinc.shiplinx.carrier.purolator.ws.estimating.proxy.PickupType;
import com.meritconinc.shiplinx.carrier.purolator.ws.estimating.proxy.Piece;
import com.meritconinc.shiplinx.carrier.purolator.ws.estimating.proxy.ReceiverInformation;
import com.meritconinc.shiplinx.carrier.purolator.ws.estimating.proxy.RequestContext;
import com.meritconinc.shiplinx.carrier.purolator.ws.estimating.proxy.ResponseInformation;
import com.meritconinc.shiplinx.carrier.purolator.ws.estimating.proxy.SenderInformation;
import com.meritconinc.shiplinx.carrier.purolator.ws.estimating.proxy.Shipment;
import com.meritconinc.shiplinx.carrier.purolator.ws.estimating.proxy.ShipmentEstimate;
import com.meritconinc.shiplinx.carrier.purolator.ws.estimating.proxy.ShortAddress;
import com.meritconinc.shiplinx.carrier.purolator.ws.estimating.proxy.Surcharge;
import com.meritconinc.shiplinx.carrier.purolator.ws.estimating.proxy.Tax;
import com.meritconinc.shiplinx.carrier.purolator.ws.estimating.proxy.TotalWeight;
import com.meritconinc.shiplinx.carrier.purolator.ws.estimating.proxy.Weight;
import com.meritconinc.shiplinx.carrier.purolator.ws.estimating.proxy.WeightUnit;
import com.meritconinc.shiplinx.carrier.purolator.ws.serviceavailability.proxy.ArrayOfOption;
import com.meritconinc.shiplinx.carrier.purolator.ws.serviceavailability.proxy.ArrayOfOptionValue;
import com.meritconinc.shiplinx.carrier.purolator.ws.serviceavailability.proxy.ArrayOfService;
import com.meritconinc.shiplinx.carrier.purolator.ws.serviceavailability.proxy.Option;
import com.meritconinc.shiplinx.carrier.purolator.ws.serviceavailability.proxy.Service;
import com.meritconinc.shiplinx.carrier.utils.PurolatorException;
import com.meritconinc.shiplinx.carrier.utils.UPSException;
import com.meritconinc.shiplinx.dao.CarrierServiceDAO;
import com.meritconinc.shiplinx.dao.MarkupManagerDAO;
import com.meritconinc.shiplinx.dao.ShippingDAO;
import com.meritconinc.shiplinx.model.CarrierChargeCode;
import com.meritconinc.shiplinx.model.Charge;
import com.meritconinc.shiplinx.model.CustomerCarrier;
import com.meritconinc.shiplinx.model.Products;
import com.meritconinc.shiplinx.model.Rating;
import com.meritconinc.shiplinx.model.ShippingOrder;
import com.meritconinc.shiplinx.service.MarkupManager;
import com.meritconinc.shiplinx.utils.FormattingUtil;
import com.meritconinc.shiplinx.utils.ShiplinxConstants;


public class EstimatingServiceClient {
	private static Logger logger = Logger.getLogger(EstimatingServiceClient.class);
	private String dangerousGoodsClass;
	private OptionsInformation optInf = new OptionsInformation();
	private ShippingOrder order=null;
	private CustomerCarrier customerCarrier = null;
	private CarrierServiceDAO carrierServiceDAO;
	private MarkupManagerDAO markupManagerDAO;
	private ShippingDAO shippingDAO;

	public EstimatingServiceClient(ShippingOrder order, CustomerCarrier customerCarrier,  CarrierServiceDAO carrierServiceDAO)
	{
		this.order = order;
		this.customerCarrier = customerCarrier;
		this.carrierServiceDAO = carrierServiceDAO;
	}
	private List<Rating> ratingList;

	private boolean availableDangGoodDeclaration = true;
	private String availableDangerousGoodMode = "";
	private boolean availableDangerousGoods = false;
	private boolean availableSaturdayService = false;
	private boolean availableCODValue = false;
	private boolean dangerousGoodSelectedByUser = false;
	private boolean availableSignatureRequired = false;
	private boolean availableGround=false;
	private boolean isRes = false;


	public List<Rating> estimatingService(){

		ratingList = new ArrayList<Rating>();

		try{
			logger.debug("--------estimatingServiceClient------");

			if(order.getDangerousGoods()!=0){					
				dangerousGoodSelectedByUser = true;
				logger.debug("Dangerous goods!");
			}

			logger.debug("NEED TO INVOKE SERVICE OPTIONS!!");
			
			ServiceAvailabilityWebServiceClient availabilityServiceClient = new ServiceAvailabilityWebServiceClient();
			JAXBElement<ArrayOfService> availableServices = availabilityServiceClient.getServiceOptions(order, customerCarrier);
			int availServiceCount = 0;
			
			if(availableServices==null || availableServices.getValue()==null || availableServices.getValue().getService()==null || availableServices.getValue().getService().size()==0){
				logger.info("No services returned by Puro web service!");
				return ratingList;
			}
				
			for(Service service: availableServices.getValue().getService()){
				//rateMap = callGetQuickEstimate(stub,requestContext);

				logger.debug("--------service.getID()------"+service.getID());
				logger.debug("--------getPackageType------"+service.getPackageType());
				
				if(!service.getPackageType().equalsIgnoreCase(getPackageTypeSelected(order.getPackageTypeId().getPackageTypeId())))
					continue;
				
				com.meritconinc.shiplinx.model.Service shiplinxService = carrierServiceDAO.getServiceByCarrierIdAndCode((long)ShiplinxConstants.CARRIER_PUROLATOR, service.getID());
				
				if(shiplinxService==null){
					logger.error("Purolator returned service code: " + service.getID() + " is not mapped to any service in our database, please investigate....");
					continue;
				}
				
				Long serviceId = shiplinxService.getId();
				//according to Gilles of Purolator, if the services returned do not contain "Ground", then the Express service is 98% of the times Ground, email Aug 17
				if(service.getID().contains("Ground"))
					availableGround = true;
				
				logger.debug("-------serviceId-----"+serviceId);

				boolean checkOptions = checkOptionAvailable(service, order);
				//if the service does not support the selected options then continue with next service
				//check the options available with user input option
				//need to continue with next value if not available and selected by user

				//this service provides the services customer is requesting
				availServiceCount++;
				//rateMap2.putAll(callGetFullEstimate(stub,requestContext,availableServices.getService()[i]));
			}

			if(availServiceCount>0)
				ratingList = callGetFullEstimate();
			return ratingList;

		} 
		catch(PurolatorException pe){
			throw pe;
		}
		catch(Exception e){
			//e.printStackTrace();
			logger.error("--------estimatingServiceClient--e----",e);
			//ratingException.getErrorMessages().add(e.toString());
			throw new PurolatorException(e.getMessage());
		}		
	}
	
	public List<Rating> callGetFullEstimate(){
		try{
			logger.debug("-----CallGetFullEstimate");
			GetFullEstimateRequestContainer reqContainer = new GetFullEstimateRequestContainer(); 
			// Setup the request to perform a full estimate
			Shipment shipment = createShipment();

			reqContainer.setShipment(shipment);
//			if(!dangerousGoodSelectedByUser)
//				reqContainer.setShowAlternativeServicesIndicator(true);
//			else
			reqContainer.setShowAlternativeServicesIndicator(true);
			

			logger.debug("------Request is ----" + reqContainer);
			
			// Call the service
			
			GetFullEstimateResponseContainer respContainer = sendRequest(reqContainer);
			
			ResponseInformation respInf = respContainer.getResponseInformation();
			
			logger.debug("------Response is ----" + respContainer);
			
			if (respInf.getErrors() != null && respInf.getErrors().getError()!=null && respInf.getErrors().getError().size() > 0)
			{
				StringBuilder messages = new StringBuilder();
				for (com.meritconinc.shiplinx.carrier.purolator.ws.estimating.proxy.Error error : respInf.getErrors().getError())
				{
					logger.debug("Error code :"+ error.getCode());
					logger.debug("Error description :"+ error.getDescription());
					logger.debug("Additional Information :"+ error.getAdditionalInformation());
					messages.append(error.getDescription() + "\n");
					
				}
				throw new PurolatorException(messages.toString());
			}
			
			ArrayOfShipmentEstimate estimates = respContainer.getShipmentEstimates().getValue();

			//get the cubed weight
			double cubedWeightGround = 0;
			double cubedWeightAir = 0;

			List<com.meritconinc.shiplinx.model.Package> packages = order.getPackages();
			for(com.meritconinc.shiplinx.model.Package p:packages){
				if(p.getWeight().intValue()<1){
					p.setWeight(new BigDecimal(1));
				}
				float actualWeight = p.getWeight().floatValue();
				double ground = PurolatorAPI.getCubedWeight(p.getLength().floatValue(), p.getHeight().floatValue(), p.getWidth().floatValue(), PurolatorAPI.GROUND_CUBING_FACTOR);
				double air = PurolatorAPI.getCubedWeight(p.getLength().floatValue(), p.getHeight().floatValue(), p.getWidth().floatValue(), PurolatorAPI.AIR_CUBING_FACTOR);
				
				if(actualWeight>ground)
					cubedWeightGround += actualWeight;
				else
					cubedWeightGround += ground;
				
				if(actualWeight>air)
					cubedWeightAir += actualWeight;
				else
					cubedWeightAir += air;

			}

			if (estimates != null && estimates.getShipmentEstimate()!=null && estimates.getShipmentEstimate().size()>0){
				for (ShipmentEstimate estimate : estimates.getShipmentEstimate()){

					com.meritconinc.shiplinx.model.Service shiplinxService = carrierServiceDAO.getServiceByCarrierIdAndCode((long)ShiplinxConstants.CARRIER_PUROLATOR, estimate.getServiceID());
					
					if(shiplinxService==null){
						logger.error("Purolator returned service code: " + estimate.getServiceID() + " is not mapped to any service in our database, please investigate....");
						continue;
					}

					Rating rate = new Rating();
					rate.setServiceId(shiplinxService.getId());		
									
											rate.setCarrierId(ShiplinxConstants.CARRIER_PUROLATOR);
										
//					rate.setCarrierName(ShiplinxConstants.CARRIER_PUROLATOR_STRING);
					rate.setTransitDays(estimate.getEstimatedTransitDays());
					rate.setTotal(estimate.getTotalPrice().doubleValue());
					rate.setDiscounted(true);
					rate.setCustomerCarrier(customerCarrier);
					
					//If Ground service is returned, then Express is Air, as per Gilles@Puro Aug 17 2012
					if(shiplinxService.getMode() == ShiplinxConstants.MODE_TRANSPORT_AIR_VALUE && availableGround)
						rate.setBillWeight(FormattingUtil.roundFigureRates(cubedWeightAir, 2));
					else //ground
						rate.setBillWeight(FormattingUtil.roundFigureRates(cubedWeightGround, 2));

					//Add the freight charge to the charges
					rate.getCharges().add(createCharge(PurolatorAPI.FREIGHT_CHARGE_CODE, ShiplinxConstants.FREIGHT_STRING, estimate.getBasePrice().doubleValue()));

					ArrayOfSurcharge charges = estimate.getSurcharges().getValue();

					if (charges != null && charges.getSurcharge().size() > 0){
						logger.debug("---Surcharges:--");
						for (Surcharge charge : charges.getSurcharge()){
							logger.debug("----Type :"+ charge.getType().toString());
							logger.debug("----Description :"+ charge.getDescription().toString());
							logger.debug("----Amount : "+charge.getAmount().doubleValue());

							if(charge.getType().toUpperCase().contains("FUEL")){
								rate.getCharges().add(createCharge(PurolatorAPI.FUEL_CHARGE_CODE, ShiplinxConstants.FUEL_SURCHARGE, charge.getAmount().doubleValue()));
							}
							else if(charge.getType().toString().equalsIgnoreCase("BeyondDestination")){
								rate.getCharges().add(createCharge(PurolatorAPI.BEYOND_CHARGE_CODE, "", charge.getAmount().doubleValue()));
							}
							else if(charge.getType().toString().equalsIgnoreCase("Multipiece")){
								rate.getCharges().add(createCharge(PurolatorAPI.MULTIPIECE_CHARGE_CODE, "", charge.getAmount().doubleValue()));
							}
							else if(charge.getType().toString().equalsIgnoreCase("ResidentialDelivery")){
								rate.getCharges().add(createCharge(PurolatorAPI.RESIDENTIAL_CHARGE_CODE, "", charge.getAmount().doubleValue()));
							}
							else{
								rate.getCharges().add(createCharge(PurolatorAPI.OTHER_CHARGE_CODE, ShiplinxConstants.OTHER_SURCHARGE, charge.getAmount().doubleValue()));
							}
						}
					}
					else{
						logger.debug("Surcharges not available");
					}

					ArrayOfTax taxes = estimate.getTaxes();

					if (taxes != null && taxes.getTax().size() > 0){
						logger.debug("Taxes:");
						for (Tax tax : taxes.getTax()){
							logger.debug("--tax.getAmount :"+ tax.getAmount().doubleValue());
							logger.debug("---- Type :"+ tax.getType());
							logger.debug("-----Description :"+ tax.getDescription());
							
								//No need to add the taxes as we calculate them separately later after all the taxes are collected (in CarrierServiceManagerImpl.java)
								//surchargesList.add(surcharge);
						}
					}
					else{
						logger.debug("Taxes not available");
					}
					
					ArrayOfOptionPrice prices = estimate.getOptionPrices().getValue();

					logger.debug("OptionPrices:");
					if (prices != null && prices.getOptionPrice().size() > 0){
						for (OptionPrice price : prices.getOptionPrice()){
							logger.debug("ID "+ price.getID());
							logger.debug("price.getAmount()==="+ price.getAmount());
							logger.debug("price.getDescription()==="+ price.getDescription());
							
						 	if(price.getID().toString().equalsIgnoreCase("ResidentialSignatureDomestic") || price.getID().toString().equalsIgnoreCase("ResidentialSignatureIntl")){
								rate.getCharges().add(createCharge(PurolatorAPI.SIGNATURE_CHARGE_CODE, "", price.getAmount().doubleValue()));
								
							}
						 	else if(price.getDescription().toString().equalsIgnoreCase("Declared Value")){
								rate.getCharges().add(createCharge(PurolatorAPI.INSURANCE_CHARGE_CODE, "", price.getAmount().doubleValue()));
								
							}
							else if(price.getID().toString().equalsIgnoreCase("ExpressCheque")){
								rate.getCharges().add(createCharge(PurolatorAPI.EXPRESS_CHECK_CHARGE_CODE, "", price.getAmount().doubleValue()));
								
							}
							else if(price.getID().toString().equalsIgnoreCase("SpecialHandling")){
								CarrierChargeCode carrierCode = new CarrierChargeCode();
								double value = 0;
								carrierCode.setChargeCode(PurolatorAPI.SPECIAL_HANDLING_CODE);
								carrierCode.setCarrierId(ShiplinxConstants.CARRIER_PUROLATOR);								
								carrierCode.setCustomerId(order.getCustomerId());
								markupManagerDAO = (MarkupManagerDAO)MmrBeanLocator.getInstance().findBean("markupManagerDAO");
								CarrierChargeCode carrierChargeCode = markupManagerDAO.getChargesByCode(carrierCode);
								if(carrierChargeCode==null){
									carrierCode.setCustomerId(0);
									carrierChargeCode = markupManagerDAO.getChargesByCode(carrierCode);
								}
								com.meritconinc.shiplinx.model.Charge charge = new com.meritconinc.shiplinx.model.Charge();
								if(carrierChargeCode!=null && carrierChargeCode.getCarrierCost() != null && carrierChargeCode.getCarrierCharge() != null){
									charge.setName(carrierChargeCode.getChargeName());
									charge.setCost(carrierChargeCode.getCarrierCost());
									charge.setCharge(carrierChargeCode.getCarrierCharge());
									charge.setChargeCode(PurolatorAPI.SPECIAL_HANDLING_CODE);
									charge.setTariffRate(price.getAmount().doubleValue());									
									rate.getCharges().add(charge);
								}else{
									rate.getCharges().add(createCharge(PurolatorAPI.SPECIAL_HANDLING_CODE, "", price.getAmount().doubleValue()));
								}
																
							}
							else{
								rate.getCharges().add(createCharge(PurolatorAPI.OTHER_CHARGE_CODE, price.getDescription(), price.getAmount().doubleValue()));
							}
						}
					}
					else{
						logger.debug("OptionPrices not available");
					}
					rate.setServiceName(shiplinxService.getName());

					ratingList.add(rate);

				}
			}

		} catch(Exception e){
			logger.error("Error while generating Purolator rates!", e);
			throw new PurolatorException(e.getMessage());
		}

		return ratingList;
	}

	
	private com.meritconinc.shiplinx.model.Charge createCharge(String code, String name, double value){
		com.meritconinc.shiplinx.model.Charge charge = new com.meritconinc.shiplinx.model.Charge();
		charge.setName(name);
		charge.setCost(value);
		charge.setCharge(value);
		charge.setChargeCode(code);
		charge.setTariffRate(value);
		return charge;
		
	}
//	public Map<Long, RateInfo> callGetQuickEstimate(EstimatingServiceStub estimateStub, RequestContextE context) {
//
//		try{
//			GetQuickEstimateRequest request = new GetQuickEstimateRequest();
//			GetQuickEstimateRequestContainer reqContainer = new GetQuickEstimateRequestContainer();
//
//			ShippingAddress shipFromAddress = order.getShipFromAddress();
//			ShippingAddress shipToAdress = order.getShipToAddress();
//
//			// Setup the request to perform a quick estimate
//			if(carrierData != null && carrierData.getPurolatorAccountNumber()!= null && carrierData.getPurolatorAccountNumber().trim().length()>0)
//			{	
//				reqContainer.setBillingAccountNumber(carrierData.getPurolatorAccountNumber());
//			}	
//			else if(franchise.isUseOwnPuroAccount())
//			{
//				reqContainer.setBillingAccountNumber(franchise.getPurolatorAccountNumber());
//			}	
//			else
//			{
//				reqContainer.setBillingAccountNumber(franchise.getMasterFranchise().getPurolatorAccountNumber());
//			}	
//		
//			reqContainer.setSenderPostalCode(shipFromAddress.getPostalCode());
//			reqContainer.setReceiverAddress(setShortAddress(shipToAdress.getCity(),shipToAdress.getProvince(),
//					shipToAdress.getCountry(),shipToAdress.getPostalCode()));
//			
//			long packageTypeId = order.getPackageTypeId();
//			
//			reqContainer.setPackageType(getPackageTypeSelected(packageTypeId));
//
//			TotalWeight weight = new TotalWeight();
//			weight.setValue(order.getTotalWeight());
//			weight.setWeightUnit(WeightUnit.lb);
//			reqContainer.setTotalWeight(weight);
//
//			request.setGetQuickEstimateRequest(reqContainer);
//
//			// Call the service
//			GetQuickEstimateResponse response = estimateStub.GetQuickEstimate(request,context);
//			GetQuickEstimateResponseContainer respContainer = response.getGetQuickEstimateResponse();
//
//			ResponseInformation respInf = respContainer.getResponseInformation();
//			
//			if (respInf.getErrors() != null && respInf.getErrors().getError()!=null && respInf.getErrors().getError().length > 0)
//			{
//				StringBuilder messages = new StringBuilder();
//				for (Error error : respInf.getErrors().getError())
//				{
//					logger.debug("------Error code +"+ error.getCode());
//					logger.debug("Error description "+ error.getDescription());
//					logger.debug("Additional Information "+ error.getAdditionalInformation());
//					messages.append(error.getDescription() + "\n");
//					ratingException.getErrorMessages().add(error.getDescription());
//				}
//				throw ratingException;
//			}
//			
//			rateMap = new HashMap<Long, RateInfo>();
//			ArrayOfShipmentEstimate estimates = respContainer.getShipmentEstimates();
//
//
//			if (estimates != null && estimates.getShipmentEstimate().length > 0){
//				for (ShipmentEstimate estimate : estimates.getShipmentEstimate()){
//
//					RateInfo rate = new RateInfo(null);
//					rate.setBaseCharge(estimate.getBasePrice().doubleValue());
//					rate.setTransitDays(estimate.getEstimatedTransitDays());
//					rate.setTotal(estimate.getTotalPrice().doubleValue());
//					//rate.setDiscounted(true);
//
//					Long serviceId = (Long)EShipperConstants.service_code_reverse.get(estimate.getServiceID());
//					logger.debug("serviceId------"+serviceId+"------"+estimate.getServiceID());
//
//					if(serviceId == null)
//						continue;
//
//					rate.setServiceId(serviceId);	
//					rate.setCarrierId(EShipperConstants.CARRIER_PUROLATOR);
//
//
//					ArrayOfSurcharge charges = estimate.getSurcharges();
//					List<com.cwsi.eshipper.model.Surcharge> surchargesList = new ArrayList<com.cwsi.eshipper.model.Surcharge>();
//
//					if (charges != null && charges.getSurcharge().length > 0){
//						logger.debug("---Surcharges:--");
//						for (Surcharge charge : charges.getSurcharge()){
//							if(/*charge.getType().equalsIgnoreCase("Fuel") || */charge.getType().toUpperCase().contains("FUEL"))
//								rate.setFuelSurcharge(charge.getAmount().doubleValue());
//							else{
//								com.cwsi.eshipper.model.Surcharge surcharge = new com.cwsi.eshipper.model.Surcharge();
//								surcharge.setAmount(charge.getAmount().doubleValue());
//								surcharge.setDescription(charge.getDescription().toString());
//								surchargesList.add(surcharge);
//							}
//							logger.debug("----Type :"+ charge.getType().toString());
//							logger.debug("----Description =:"+ charge.getDescription().toString());
//							logger.debug("-----Amount : "+charge.getAmount().doubleValue());
//						}
//					}
//					else{
//						logger.debug("Surcharges not available");
//					}
//
//					ArrayOfTax taxes = estimate.getTaxes();
//
//					if (taxes != null && taxes.getTax().length > 0){
//						logger.debug("Taxes:");
//						for (Tax tax : taxes.getTax()){
//							logger.debug("--tax.getAmount()---"+ tax.getAmount().doubleValue());
//							logger.debug("---- Type ::"+ tax.getType());
//							logger.debug("-----Description ::"+ tax.getDescription());
//							
//							if(tax.getAmount().doubleValue()>0){
//								com.cwsi.eshipper.model.Surcharge surcharge = new com.cwsi.eshipper.model.Surcharge();
//								surcharge.setAmount(tax.getAmount().doubleValue());
//								surcharge.setDescription(tax.getDescription().toString());
//								surcharge.setName(tax.getDescription().toString());
//								surchargesList.add(surcharge);
//							}
//						}
//					}
//
//					else{
//						logger.debug("Taxes not available");
//					}
//					
//					ArrayOfOptionPrice prices = estimate.getOptionPrices();
//
//					logger.debug("OptionPrices:--");
//					if (prices != null && prices.getOptionPrice().length > 0){
//						for (OptionPrice price : prices.getOptionPrice()){
//							logger.debug("ID "+ price.getID());
//							logger.debug("price.getAmount():"+ price.getAmount());
//							logger.debug("price.getDescription():"+ price.getDescription());
//							
//							com.cwsi.eshipper.model.Surcharge surcharge = new com.cwsi.eshipper.model.Surcharge();
//							surcharge.setAmount(price.getAmount().doubleValue());
//							surcharge.setDescription(price.getDescription().toString());
//							surcharge.setName(price.getDescription().toString());
//							surchargesList.add(surcharge);
//						}
//					}
//					else{
//						logger.debug("OptionPrices not available");
//					}
//
//					rate.setSurcharges(surchargesList);
//					rateMap.put(serviceId, rate);
//
//				}
//			}
//
//		} catch(Exception e){
//			logger.debug("----CallGetQuickEstimate-------"+e);
//			//ratingException.getErrorMessages().add(e.toString());
//			throw ratingException;
//		}
//		return rateMap;
//	}

	private ShortAddress setShortAddress(java.lang.String city, java.lang.String province, java.lang.String country, java.lang.String postalCode){
		ShortAddress addr = new ShortAddress();
		addr.setCity(city);
		addr.setProvince(province);
		addr.setCountry(country);
		addr.setPostalCode(postalCode.replaceAll(" " , ""));
		
		if(addr.getProvince().equalsIgnoreCase("NF"))
			addr.setProvince("NL");
		if(addr.getProvince().equalsIgnoreCase("PQ"))
			addr.setProvince("QC");

		if(addr.getPostalCode().equalsIgnoreCase("N/A"))
			addr.setPostalCode("");
		if(addr.getProvince().equalsIgnoreCase("N/A"))
			addr.setProvince("");

		return addr;
	}

	private Shipment createShipment(){
		ObjectFactory objectFactory = new ObjectFactory();
		Shipment shipment = new Shipment();
		SenderInformation sender = new SenderInformation();
		sender.setAddress(setSenderAddress());
//			sender.setTaxNumber("123456");
		shipment.setSenderInformation(sender);

		ReceiverInformation receiver = new ReceiverInformation();
		receiver.setAddress(setReceiverAddress());
//			receiver.setTaxNumber("654321");
		shipment.setReceiverInformation(receiver);

		PackageInformation pack = new PackageInformation();
		
		pack.setServiceID(getService());
		
		pack.setDescription(objectFactory.createPackageInformationDescription("Description"));
		TotalWeight weight = new TotalWeight();
		weight.setValue(order.getTotalActualWeight().intValue());
		weight.setWeightUnit(WeightUnit.LB);
		pack.setTotalWeight(weight);
		pack.setTotalPieces(order.getPackages().size());

		List<com.meritconinc.shiplinx.model.Package> packageList = order.getPackages();
		
		pack.setPiecesInformation(objectFactory.createPackageInformationPiecesInformation(new  ArrayOfPiece()));
		for(com.meritconinc.shiplinx.model.Package p :packageList){
			pack.getPiecesInformation().getValue().getPiece().add(setPiece(p.getLength().doubleValue(),p.getWidth().doubleValue(),p.getHeight().doubleValue(),p.getWeight().doubleValue()));
		}
	
		
		if(dangerousGoodSelectedByUser)
			pack.setDangerousGoodsDeclarationDocumentIndicator(availableDangGoodDeclaration);
		else
			pack.setDangerousGoodsDeclarationDocumentIndicator(dangerousGoodSelectedByUser); //i.e. false
		
		pack.setOptionsInformation(objectFactory.createOptionsInformation(getOptionInformation()));
		shipment.setPackageInformation(pack);

	
		if(!PurolatorAPI.isDomesticShipment(order)){
			shipment.setInternationalInformation(objectFactory.createInternationalInformation(getInternationInformation()));	
		}
		
		PaymentInformation pay = new PaymentInformation();
		pay.setPaymentType(PaymentType.SENDER);
		pay.setRegisteredAccountNumber(customerCarrier.getAccountNumber1());
		pay.setBillingAccountNumber(objectFactory.createPaymentInformationBillingAccountNumber(customerCarrier.getAccountNumber1()));

		shipment.setPaymentInformation(pay);

		PickupInformation pickUp = new PickupInformation();
		if(order.getHoldForPickupRequired()!=null && order.getHoldForPickupRequired())
			pickUp.setPickupType(PickupType.PRE_SCHEDULED);
		else
			pickUp.setPickupType(PickupType.DROP_OFF);
		
		shipment.setPickupInformation(pickUp);

		NotificationInformation note = new NotificationInformation();
		note.setConfirmationEmailAddress(objectFactory.createNotificationInformationConfirmationEmailAddress(order.getFromAddress().getEmailAddress()));
		shipment.setNotificationInformation(objectFactory.createNotificationInformation(note));

//			TrackingReferenceInformation track = new TrackingReferenceInformation();
//			track.setReference1(order.getReferenceCode());
//			track.setReference2("Reference2");
//			track.setReference3("Reference3");
//			track.setReference4("Reference4");
//			shipment.setTrackingReferenceInformation(track);

		OtherInformation otherInformation = new OtherInformation();
		otherInformation.setSpecialInstructions(objectFactory.createOtherInformationSpecialInstructions(""));
		shipment.setOtherInformation(objectFactory.createOtherInformation(otherInformation));
		
		
		return shipment;
	}

	private InternationalInformation getInternationInformation() {
		ObjectFactory objectFactory = new ObjectFactory();
		InternationalInformation internationalInformation = new InternationalInformation();
		//DocumentsOnlyIndicator
		boolean docOnlyAvail = false;//!availableDangerousGoods;
		internationalInformation.setDocumentsOnlyIndicator(docOnlyAvail);
		
		//DutyInformation
		DutyInformation dutyInformation = new DutyInformation();
		dutyInformation.setBusinessRelationship(BusinessRelationship.NOT_RELATED);
		dutyInformation.setBillDutiesToParty(BillDutiesToParty.RECEIVER);
		if(order.getCustomsInvoice()==null || order.getCustomsInvoice().getCurrency()==null || order.getCustomsInvoice().getCurrency().equals(ShiplinxConstants.CURRENCY_CA_STRING))
			dutyInformation.setCurrency(DutyCurrency.CAD);
		else
			dutyInformation.setCurrency(DutyCurrency.USD);
		
		internationalInformation.setDutyInformation(objectFactory.createDutyInformation(dutyInformation));
		
		//ContentDetail
		ArrayOfContentDetail arrayOfContentDetail = new ArrayOfContentDetail();
		
		ContentDetail[] contentDetail  = new ContentDetail[1];
		
		if(!docOnlyAvail){
			for(int i=0; i<1;i++){
				logger.debug("------i--------"+i);
				logger.debug("----contentDetail[i]-------"+contentDetail[i]);
				ContentDetail contentDetailTemp = new ContentDetail();
				contentDetailTemp.setCountryOfManufacture(order.getFromAddress().getCountryCode());
				contentDetailTemp.setDescription("desription");
				contentDetailTemp.setHarmonizedCode("123");				
				contentDetailTemp.setProductCode("123456");
				contentDetailTemp.setQuantity(1);
				contentDetailTemp.setUnitValue(new BigDecimal("1"));
				contentDetailTemp.setNAFTADocumentIndicator(true);
				contentDetailTemp.setFDADocumentIndicator(true);
				contentDetailTemp.setFCCDocumentIndicator(true);
				contentDetailTemp.setSenderIsProducerIndicator(true);
				
				contentDetail[i]=contentDetailTemp;
				arrayOfContentDetail.getContentDetail().add(contentDetailTemp);
			}
		}
			
		
		internationalInformation.setContentDetails(objectFactory.createArrayOfContentDetail(arrayOfContentDetail));
		
		//ImportExportType
			internationalInformation.setImportExportType(objectFactory.createImportExportType(ImportExportType.PERMANENT));
	
		//CustomsInvoiceDocumentIndicator
		internationalInformation.setCustomsInvoiceDocumentIndicator(availableDangGoodDeclaration);
		
		return internationalInformation;
	}

	private boolean checkOptionAvailable(Service service, ShippingOrder order){
		
		try{
    		logger.debug("---------------service------------"+service);
     				
    				JAXBElement<ArrayOfOption> options = service.getOptions();
    				if (options != null && options.getValue() != null && options.getValue().getOption() != null){
    						for (Option option2 : options.getValue().getOption()){
    							logger.debug("option2---"+option2.getID());
    							if("SaturdayDelivery".equalsIgnoreCase(option2.getID())){
									availableSaturdayService = true;
								}
    							if("ExpressCheque".equalsIgnoreCase(option2.getID())){
    								availableCODValue = true;
    							}
    							if("ResidentialSignatureDomestic".equalsIgnoreCase(option2.getID())
    									||"ResidentialSignatureIntl".equalsIgnoreCase(option2.getID())){
    								availableSignatureRequired = true;
    								//if residential is not set, then this is a good indicator that the ship to address is residential, so set it
    								if(!order.getToAddress().isResidential())
    									isRes = true; //created a local boolean 'isRes' as we do not want to set this shipment to residential for other carriers during rating time
    							}
    							ArrayOfOptionValue optionvalues = option2.getPossibleValues();
    							JAXBElement<ArrayOfOption> childoptions = option2.getChildServiceOptions();
    							if (childoptions != null && childoptions.getValue() != null && childoptions.getValue().getOption() != null){
    							
    								for (Option choption : childoptions.getValue().getOption()){
    									logger.debug("choption---"+choption.getID());
    									if("DangerousGoodsMode".equalsIgnoreCase(choption.getID())){
    										optionvalues = choption.getPossibleValues();
        									if (optionvalues != null && optionvalues.getOptionValue() != null){ 
        										availableDangerousGoodMode = optionvalues.getOptionValue().get(0).getValue();
        										availableDangerousGoods = true;
        									}
    									}

    									optionvalues = choption.getPossibleValues();
    								}
    							}
    						}
    					}
    					else{
    						 logger.debug("\tOptions not available");
    					}
    					
    				if("".equals(availableDangerousGoodMode)){
    					availableDangerousGoods = false;
					}
    				logger.debug("----service.getID()--"+service.getID()+"----dngMode---------"+availableDangerousGoodMode 
    						+ "------"+availableDangerousGoods+"----availableCODValue---------"+availableCODValue);
    				
    				
    		}catch (Exception e) {
				logger.error("Error in checking Purolator options", e);
			}
		
    		// if user select other than FullyRegulated and service support air mode, then service must not included in rate map
		if(availableDangerousGoodMode.equalsIgnoreCase(PurolatorAPI.PURO_WEB_TRANSPORT_MODE_AIR)
				&& order.getDangerousGoods()!=ShiplinxConstants.DG_FULLY_REGULATED){
			availableDangerousGoods = false;
		}
    		
		logger.debug("---dangGoods---"+availableDangerousGoods);
		
		if(availableDangerousGoodMode.equalsIgnoreCase(PurolatorAPI.PURO_WEB_TRANSPORT_MODE_GROUND))
				availableDangGoodDeclaration = true;
		else
			availableDangGoodDeclaration = false;
		
		if(!availableDangerousGoods){
			availableDangGoodDeclaration = false;
		}
		
		if(!availableDangerousGoods && order.getDangerousGoods()>0)
			return false;
		if(!availableSaturdayService && order.getSatDelivery())
			return false;
		if(!availableCODValue && isCODRequested()){
			logger.debug("Service " + service.getID() +" does not support COD!");
			return false;
		}

		return true;	
			
	}
	
	private OptionsInformation getOptionInformation() {
		ObjectFactory objectFactory = new ObjectFactory();
		ArrayOfOptionIDValuePair arr = new ArrayOfOptionIDValuePair();
		OptionIDValuePair option;
		logger.debug("-----dngMode::"+availableDangerousGoodMode + "----DangerousGoods::"+availableDangerousGoods);
		boolean codSelected = false;
		
		if(dangerousGoodSelectedByUser){
			option = new OptionIDValuePair();
			option.setID("DangerousGoods");
			option.setValue("true");
			arr.getOptionIDValuePair().add(option);
			
			option = new OptionIDValuePair();
			option.setID("DangerousGoodsClass");
			
			//only one value is available for Air
			if(availableDangerousGoodMode.equalsIgnoreCase(PurolatorAPI.PURO_WEB_TRANSPORT_MODE_AIR)){
				dangerousGoodsClass = "FullyRegulated";
			}
			
			if(order.getDangerousGoods()>0 && availableDangerousGoodMode.equalsIgnoreCase(PurolatorAPI.PURO_WEB_TRANSPORT_MODE_GROUND)){
				if(order.getDangerousGoods()==ShiplinxConstants.DG_500KG_EXEMPTION){
					dangerousGoodsClass = "LessThan500kgExempt";
				}
				else if(order.getDangerousGoods()==ShiplinxConstants.DG_FULLY_REGULATED){
					dangerousGoodsClass = "FullyRegulated";
				}
				else if(order.getDangerousGoods()==ShiplinxConstants.DG_LIMITED_QUANTITY){
					dangerousGoodsClass = "LimitedQuantities";
				}
			}
			
			option.setValue(dangerousGoodsClass);
			arr.getOptionIDValuePair().add(option);

			option = new OptionIDValuePair();
			option.setID("DangerousGoodsMode");
			option.setValue(availableDangerousGoodMode);
			arr.getOptionIDValuePair().add(option);
			
		}
		
		if(order.getHoldForPickupRequired()!=null && order.getHoldForPickupRequired()){
			option = new OptionIDValuePair();
			option.setID("HoldForPickup");
			option.setValue("true");
			arr.getOptionIDValuePair().add(option);
		}
		logger.debug("-----order.getSaturdayDeliveryRequired()------" + order.getSatDelivery());
		if(order.getSatDelivery()!=null && order.getSatDelivery()){
			option = new OptionIDValuePair();
			option.setID("SaturdayDelivery");
			option.setValue("true");
			arr.getOptionIDValuePair().add(option);
		}
		logger.debug("-----order.getSignatureRequired()--------"+order.getSignatureRequired());
		if(availableSignatureRequired && (order.getSignatureRequired()!=ShiplinxConstants.SIGNATURE_REQUIRED_NO || isCODRequested())){
			option = new OptionIDValuePair();
			if(PurolatorAPI.isUSShipment(order) || !PurolatorAPI.isDomesticShipment(order))
				option.setID("ResidentialSignatureIntl");
			else
				option.setID("ResidentialSignatureDomestic");
			option.setValue("true");
			arr.getOptionIDValuePair().add(option);
		}
//		Below code is sending OSNR for shipments going to business locations, even though signature is free to business locations
//		Issue with commenting code below is that customer will not be able to request SNR for business locations. Will need to add the SNR instructions in the instructions section
		else if((order.getToAddress().isResidential() || isRes) && availableSignatureRequired && order.getSignatureRequired()==ShiplinxConstants.SIGNATURE_REQUIRED_NO) //residential signature is available but not requested by customer
		{
			if(PurolatorAPI.isDomesticShipment(order))
			{	
				option = new OptionIDValuePair();
				option = new OptionIDValuePair();
				option.setID("OriginSignatureNotRequired");
				option.setValue("true");
				arr.getOptionIDValuePair().add(option);
				logger.debug("--------OriginSignatureNotRequired option is set.----");
			}
		}
		

		
		logger.debug("-----order.getInsuredAmount()--------"+order.getInsuredAmount());
		logger.debug("-----order.getInsuranceValue()--------"+order.getInsuranceValue());
		if(order.getInsuranceValue() > 0){
			logger.debug("In setDeclaredValue");
			option = new OptionIDValuePair();
			option.setID("DeclaredValue");
			option.setValue(String.valueOf(order.getInsuranceValue()));
			arr.getOptionIDValuePair().add(option);
		}
		
		if(order.getCODPayment() !=null && order.getCODValue() > 0 && PurolatorAPI.isDomesticShipment(order)
				&& !"None".equalsIgnoreCase(order.getCODPayment())){
				codSelected = true;
			
			option = new OptionIDValuePair();
			option.setID("ExpressCheque");
			option.setValue("true");
			arr.getOptionIDValuePair().add(option);
			
			option = new OptionIDValuePair();
			option.setID("ExpressChequeMethodOfPayment");
			
			if(ShiplinxConstants.COD_CERTIFIED_CHECK.equalsIgnoreCase(order.getCODPayment()))
				option.setValue("CertifiedCheque");
			else if(ShiplinxConstants.COD_CHECK.equalsIgnoreCase(order.getCODPayment()))
				option.setValue("Cheque");
			
			arr.getOptionIDValuePair().add(option);
			
			option = new OptionIDValuePair();
			option.setID("ExpressChequeAmount");
			option.setValue(String.valueOf(order.getCODValue()));
			arr.getOptionIDValuePair().add(option);
			optInf.setExpressChequeAddress(objectFactory.createAddress(setReceiverAddress()));
		}
		
		//if nothing is selected then set the OriginSignatureNotRequired option
//		if(!dangerousGoodSelectedByUser && !order.getSaturdayDeliveryRequired() 
//				&& order.getSignatureRequired()==1 && !codSelected
//				&& ! order.isInternationalShipment() 
//				&& ! order.isHoldForPickupRequired()){
//			option = new OptionIDValuePair();
//			option.setID("OriginSignatureNotRequired");
//			option.setValue("true");
//			optionList.add(option);
//		}
		
		optInf.setOptions(arr); 
		return optInf;
	}

	private Address setSenderAddress()
	{
		ObjectFactory objectFactory = new ObjectFactory();
		Address addr = new Address();
		com.meritconinc.shiplinx.model.Address shipFromAddress =  order.getFromAddress();

		addr.setName(StringUtil.setWithMaxLength(shipFromAddress.getContactName(),30));
		addr.setCompany(objectFactory.createAddressCompany(StringUtil.setWithMaxLength(shipFromAddress.getAbbreviationName(),30)));
		addr.setStreetName(StringUtil.setWithMaxLength(shipFromAddress.getAddress1(), 30));
		addr.setStreetNumber(shipFromAddress.getAddress2());

		addr.setCity(shipFromAddress.getCity());
		
		addr.setProvince(shipFromAddress.getProvinceCode());
		addr.setCountry(shipFromAddress.getCountryCode());
		addr.setPostalCode(shipFromAddress.getPostalCode().replaceAll(" ", ""));

		PhoneNumber phone = setPhoneNumber(shipFromAddress.getPhoneNo());
		addr.setPhoneNumber(phone);
		if(addr.getProvince().equalsIgnoreCase("NF"))
			addr.setProvince("NL");
		if(addr.getProvince().equalsIgnoreCase("PQ"))
			addr.setProvince("QC");

		if(addr.getPostalCode().equalsIgnoreCase("N/A"))
			addr.setPostalCode("");
		if(addr.getProvince().equalsIgnoreCase("N/A"))
			addr.setProvince("");

		return addr;
	}

	private Address setReceiverAddress()
	{
		Address addr = new Address();
		com.meritconinc.shiplinx.model.Address shipToAddress =  order.getToAddress();
		addr.setName(StringUtil.setWithMaxLength(shipToAddress.getContactName(),30));
		ObjectFactory objectFactory = new ObjectFactory();
		addr.setCompany(objectFactory.createAddressCompany(StringUtil.setWithMaxLength(shipToAddress.getAbbreviationName(), 30)));
		addr.setStreetName(StringUtil.setWithMaxLength(shipToAddress.getAddress1(), 35));
		addr.setStreetNumber(shipToAddress.getAddress2());

		addr.setCity(shipToAddress.getCity());
		addr.setProvince(shipToAddress.getProvinceCode());
		addr.setCountry(shipToAddress.getCountryCode());
		addr.setPostalCode(shipToAddress.getPostalCode().replaceAll(" ", ""));

		PhoneNumber phone = setPhoneNumber(shipToAddress.getPhoneNo());
		addr.setPhoneNumber(phone);
		if(addr.getProvince().equalsIgnoreCase("NF"))
			addr.setProvince("NL");
		if(addr.getProvince().equalsIgnoreCase("PQ"))
			addr.setProvince("QC");

		if(addr.getPostalCode().equalsIgnoreCase("N/A"))
			addr.setPostalCode("");
		if(addr.getProvince().equalsIgnoreCase("N/A"))
			addr.setProvince("");

		return addr;
	}

	private PhoneNumber setPhoneNumber(String phNo)
	{
		PhoneNumber ph = new PhoneNumber();
		if(phNo==null || phNo.length()==0)
			ph.setPhone("8888888888");
		else			
		ph.setPhone(phNo);
		return ph;
	}

	private Piece setPiece(double length,double width,double height,double pweight )
	{
		ObjectFactory objectFactory = new ObjectFactory();
		Piece piece = new Piece();
		logger.debug("----" +length+"--"+width+"--"+height+"---"+pweight);
		//List<Package> pack = order.getPackages();

		Weight weight = new Weight();
		java.math.BigDecimal w = new java.math.BigDecimal(pweight);
		weight.setValue(w);
		weight.setWeightUnit(WeightUnit.LB);
		piece.setWeight(weight);

		if(order.getPackageTypeId().getPackageTypeId() == ShiplinxConstants.PACKAGE_TYPE_PACKAGE || order.getPackageTypeId().getPackageTypeId() == ShiplinxConstants.PACKAGE_TYPE_PALLET){
			piece.setLength(objectFactory.createPieceLength(setDimension(length)));
			piece.setWidth(objectFactory.createPieceWidth(setDimension(width)));
			piece.setHeight(objectFactory.createPieceHeight(setDimension(height)));
		}else{
			piece.setLength(objectFactory.createPieceLength(setDimension(0.0)));
			piece.setWidth(objectFactory.createPieceWidth(setDimension(0.0)));
			piece.setHeight(objectFactory.createPieceHeight(setDimension(0.0)));
		}
		return piece;
	}

	private static Dimension setDimension(double value)
	{
		Dimension dim = new Dimension();
		java.math.BigDecimal val = new java.math.BigDecimal(value);
		dim.setValue(val);
		dim.setDimensionUnit(DimensionUnit.IN);
		return dim;
	}

	private String getDigitsFromPH(String phoneNumber) {
		int length = phoneNumber.length();  
		StringBuffer buffer = new StringBuffer();  
		for(int i = 0; i < length; i++) {  
			char ch = phoneNumber.charAt(i);  
			if (Character.isDigit(ch)) {  
				buffer.append(ch);  
			}  
		}  
		return buffer.toString();  
	}
	
	private String getPackageTypeSelected(long packageTypeId){
		
		if(packageTypeId == ShiplinxConstants.PACKAGE_TYPE_ENVELOPE)
			return PurolatorAPI.EXPRESS_ENVELOPE;
		else if(packageTypeId == ShiplinxConstants.PACKAGE_TYPE_PAK)
			return PurolatorAPI.EXPRESS_PACK;
//		else if(packageTypeId == ShiplinxConstants.PACKAGE_TYPE_PALLET)
//			return PurolatorAPI.EXPRESS_BOX;
		else
			return PurolatorAPI.CUSTOMER_PACKAGING;
	}
	
	protected boolean isCODRequested(){
		
		for(com.meritconinc.shiplinx.model.Package p: order.getPackages()){
			if(p.getCodAmount()!=null && p.getCodAmount().floatValue() > 0)
				return true;
		}
		return false;
	
	}
	
	protected String getService(){
		//Puro has services broken down by product type (env, pack, package, etc) and country of shipment
		//Example, Express within Canada is called PurolatorExpress but Express to US is a different service called PurolatorExpressU.S.
		//here we need to set the service/product accordingly and in the request we set the alternate service indicator to true which will return the various variations of that service such as 9 AM, 1030 AM etc.
		
		String puroService = null;
		
		if(availableGround){
			puroService="PurolatorGround";
			if(PurolatorAPI.isUSShipment(order))
				puroService="PurolatorGroundU.S.";
		}
		else{
			puroService="PurolatorExpress";
			if(PurolatorAPI.isUSShipment(order))
				puroService="PurolatorExpressU.S.";
			else if(!PurolatorAPI.isDomesticShipment(order))
				puroService="PurolatorExpressInternational";
		}
		
		if(order.getPackageTypeId().getPackageTypeId() == ShiplinxConstants.PACKAGE_TYPE_ENVELOPE){
			puroService="PurolatorExpressEnvelope";
			if(PurolatorAPI.isUSShipment(order))
				puroService="PurolatorExpressEnvelopeU.S.";
			else if(!PurolatorAPI.isDomesticShipment(order))
				puroService="PurolatorExpressEnvelopeInternational";
		}
		else if(order.getPackageTypeId().getPackageTypeId() == ShiplinxConstants.PACKAGE_TYPE_PAK){
			puroService="PurolatorExpressPack";
			if(PurolatorAPI.isUSShipment(order))
				puroService="PurolatorExpressPackU.S.";
			else if(!PurolatorAPI.isDomesticShipment(order))
				puroService="PurolatorExpressPackInternational";
		}
		
		//if dangerous goods and going to US, then service should be Express
		if(order.getDangerousGoods()>0 && PurolatorAPI.isUSShipment(order))
			puroService="PurolatorExpressU.S.";
		
		return puroService;
	}
	
	public GetFullEstimateResponseContainer sendRequest(GetFullEstimateRequestContainer request) throws UPSException{

		GetFullEstimateResponseContainer response = null;
		try {
			JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();

			factory.getInInterceptors().add(new LoggingInInterceptor());
			factory.getOutInterceptors().add(new LoggingOutInterceptor());
			factory.setServiceClass(EstimatingServiceContract.class);
			if(ShiplinxConstants.isTestMode())
				factory.setAddress(PurolatorAPI.TEST_URL_RATE);
			else
				factory.setAddress(PurolatorAPI.LIVE_URL_RATE);
			
			EstimatingServiceContract client = (EstimatingServiceContract) factory.create();
			
			LogUtils.setLoggerClass(org.apache.cxf.common.logging.Log4jLogger.class);
			Client clientProxy = ClientProxy.getClient(client); 
			clientProxy.getInInterceptors().add(new LoggingInInterceptor());
			clientProxy.getOutInterceptors().add(new LoggingOutInterceptor());
			HTTPConduit http = (HTTPConduit) clientProxy.getConduit();

			HTTPClientPolicy httpClientPolicy = new HTTPClientPolicy();

			httpClientPolicy.setConnectionTimeout(36000);
			httpClientPolicy.setAllowChunking(false);
			httpClientPolicy.setReceiveTimeout(32000);
			
			AuthorizationPolicy authorization = new AuthorizationPolicy();

			
			// Production URL
			authorization.setUserName(customerCarrier.getProperty1());
			authorization.setPassword(customerCarrier.getProperty2());
			
			http.setAuthorization(authorization);
		
			// ----------- RequestContext
			RequestContext reqContext = getRequestContext();
			
			List<Header> headers = new ArrayList<Header>();
			Header dummyHeader = new Header(new QName("http://purolator.com/pws/datatypes/v1", "RequestContext"), reqContext,
			                                new JAXBDataBinding(RequestContext.class));
			headers.add(dummyHeader);

			
			((BindingProvider)client).getRequestContext().put(Header.HEADER_LIST, headers);

			response = client.getFullEstimate(request);
			
			logger.debug("Response:" + response);
		} 
		catch(EstimatingServiceContractGetFullEstimateValidationFaultFaultFaultMessage sem){
			logger.error("Error sending estimate request to Purolator", sem);
		}
		catch (Exception e) {
			logger.error("Error sending estimate request to Purolator", e);
		}

		return response;
	}	

	private RequestContext getRequestContext() {
		// TODO Auto-generated method stub
		RequestContext reqContext = new RequestContext();
		reqContext.setVersion("1.0");
		reqContext.setLanguage(com.meritconinc.shiplinx.carrier.purolator.ws.estimating.proxy.Language.EN);
		reqContext.setGroupID("1");
		reqContext.setRequestReference("RequestReference");
		return reqContext;
	}

}