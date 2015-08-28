package com.meritconinc.shiplinx.ws;

import java.io.ByteArrayOutputStream;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.meritconinc.mmr.utilities.MmrBeanLocator;
import com.meritconinc.mmr.utilities.StringUtil;
import com.meritconinc.shiplinx.dao.BusinessDAO;
import com.meritconinc.shiplinx.dao.CustomerDAO;
import com.meritconinc.shiplinx.dao.ShippingDAO;
import com.meritconinc.shiplinx.model.Customer;
import com.meritconinc.shiplinx.model.PackageType;
import com.meritconinc.shiplinx.model.Rating;
import com.meritconinc.shiplinx.model.ShippingOrder;
import com.meritconinc.shiplinx.service.AddressManager;
import com.meritconinc.shiplinx.service.CarrierServiceManager;
import com.meritconinc.shiplinx.service.ShippingService;
import com.meritconinc.shiplinx.utils.CarrierErrorMessage;
import com.meritconinc.shiplinx.utils.ShiplinxConstants;
import com.meritconinc.shiplinx.ws.proxy.datatypes.ErrorsWSType;
import com.meritconinc.shiplinx.ws.proxy.datatypes.SecurityWSType;
import com.meritconinc.shiplinx.ws.proxy.ratingrequest.RatingRequestWSType;
import com.meritconinc.shiplinx.ws.proxy.ratingresponse.RatingResponseWSType;
import com.meritconinc.shiplinx.ws.proxy.shippingrequest.ShippingRequestWSType;
import com.meritconinc.shiplinx.ws.proxy.shippingresponse.ShippingResponseWSType;
public class ShiplinxWebServiceAdaptor {
	private static final Logger log = LogManager.getLogger(ShiplinxWebServiceAdaptor.class);
	private static final String ACTION_CREATE = "CREATE";
	private CarrierServiceManager carrierServiceManager;
	private ShippingService shippingService;
	private CustomerDAO customerDAO;
	private ShippingDAO shippingDAO;
	private BusinessDAO businessDAO;
	private AddressManager addressService;
	private static List<PackageType> packageTypes = null;
	
	public CarrierServiceManager getCarrierServiceManager() {
		return carrierServiceManager;
	} 

	public void setCarrierServiceManager(CarrierServiceManager carrierServiceManager) {
		this.carrierServiceManager = carrierServiceManager;
	}

	public ShippingService getShippingService() {
		return shippingService;
	}

	public void setShippingService(ShippingService shippingService) {
		this.shippingService = shippingService;
	}

	public void setAddressService(AddressManager addressService) {
		this.addressService = addressService;
	}

	public CustomerDAO getCustomerDAO() {
		return customerDAO;
	}

	public void setCustomerDAO(CustomerDAO customerDAO) {
		this.customerDAO = customerDAO;
	}	

	public ShippingDAO getShippingDAO() {
		return shippingDAO;
	}

	public void setShippingDAO(ShippingDAO shippingDAO) {
		this.shippingDAO = shippingDAO;
	}
	

	public BusinessDAO getBusinessDAO() {
		return businessDAO;
	}

	public void setBusinessDAO(BusinessDAO businessDAO) {
		this.businessDAO = businessDAO;
	}

	public RatingResponseWSType rateShipment(RatingRequestWSType request, SecurityWSType security) {
		// TODO Auto-generated method stub
		initBeans();
		RatingResponseWSType response = new RatingResponseWSType();
		response.setErrors(new ErrorsWSType());
		response.setSecurity(security);
		if (this.carrierServiceManager != null &&  request != null && request.getOrder() != null) {
			if (packageTypes == null) {
				packageTypes = shippingDAO.getAllPackages();
			}
			ShippingOrder so = ShiplinxWebServiceModelHelper.convertOrder(request.getOrder(), packageTypes, response.getErrors());
			so.setCustomer(getCustomer(security));
			if (so.getCustomer() != null) {
				so.setCustomerId(so.getCustomer().getId());
				List<Rating> ratings = carrierServiceManager.rateShipment(so);
				if (ratings != null)
					response.setRatings(ShiplinxWebServiceModelHelper.convertRatings(ratings, shippingService));
				
				//Get the error messages returned from the carriers
				for(CarrierErrorMessage carrierErrorMessage  :carrierServiceManager.getErrorMessages()) {
					ShiplinxWebServiceModelHelper.addError(	ShiplinxWebServiceModelHelper.CARRIER_ERROR_CODE, 
														carrierErrorMessage.getMessage(),
														response.getErrors().getError()); 
				}
				
			} else {
				ShiplinxWebServiceModelHelper.addError(ShiplinxWebServiceModelHelper.INVALID_API_CUSTOMER_INFO, 
													response.getErrors().getError());
			}
		} else {
			ShiplinxWebServiceModelHelper.addError(ShiplinxWebServiceModelHelper.SYSTEM_ERROR, 
																		response.getErrors().getError());
		}
		if (response.getErrors() != null && response.getErrors().getError().size() > 0)
			response.setIsSuccess(false);
		else
			response.setIsSuccess(true);
			
		return response;
	}
	
	public ShippingResponseWSType shipOrder(ShippingRequestWSType request, SecurityWSType security) {
		// TODO Auto-generated method stub
		initBeans();
		ShippingResponseWSType response = new ShippingResponseWSType();
		response.setErrors(new ErrorsWSType());
		response.setSecurity(security);
		if (this.carrierServiceManager != null &&  request != null && request.getOrder() != null) {
			if (packageTypes == null) {
				packageTypes = shippingDAO.getAllPackages();
			}
			ShippingOrder so = ShiplinxWebServiceModelHelper.convertOrder(request.getOrder(), packageTypes, response.getErrors());
			so.setCustomer(getCustomer(security));
			if (so.getCustomer() != null) {
				so.setCustomerId(so.getCustomer().getId());
				so.setBusinessId(so.getCustomer().getBusinessId());
				so.setBusiness(this.businessDAO.getBusiessById(so.getBusinessId()));
			} else {
				ShiplinxWebServiceModelHelper.addError(ShiplinxWebServiceModelHelper.INVALID_API_CUSTOMER_INFO, 
																response.getErrors().getError());
			}
			//run address validation
			addressService.runAddressValidation(so.getFromAddress());
			addressService.runAddressValidation(so.getToAddress());
			if (!StringUtil.isEmpty(request.getAction()) && request.getAction().equalsIgnoreCase(ACTION_CREATE)) {
				saveOrder(so, response);
			} else {
				shipWSOrder(so, response);
			}
		} else {
			ShiplinxWebServiceModelHelper.addError(ShiplinxWebServiceModelHelper.SYSTEM_ERROR, 
																response.getErrors().getError());
		}
		if (response.getErrors() != null && response.getErrors().getError().size() > 0)
			response.setIsSuccess(false);
		else
			response.setIsSuccess(true);
			
		return response;
	}	
	
	private void shipWSOrder(ShippingOrder so, ShippingResponseWSType response) {
		if (so.getService() != null && so.getService().getId() != null) {
			try {
				carrierServiceManager.shipOrder(so, null);	//rating);
				
				//Get the error messages returned from the carriers, if there are any
				for(CarrierErrorMessage carrierErrorMessage  :carrierServiceManager.getErrorMessages()) {
					ShiplinxWebServiceModelHelper.addError(	ShiplinxWebServiceModelHelper.CARRIER_ERROR_CODE, 
														carrierErrorMessage.getMessage(),
														response.getErrors().getError()); 
				}					
				
				// Order shipped successfully
				// retrieve order info from the database, ideally shipOrder should return it
				ShippingOrder soOut = this.shippingDAO.getShippingOrder(so.getId());
				if (soOut != null) {
					response.setOrder(ShiplinxWebServiceModelHelper.convertOrder(soOut, shippingService));
				
					// Generate Labels
					ByteArrayOutputStream baos = new ByteArrayOutputStream();
					carrierServiceManager.getShippingLabel(soOut, baos);
					// Base 64 Encode
					response.getOrder().setShippingLabel(Base64.encodeBase64(baos.toByteArray()));
				}
			} catch (Exception e) {
				e.printStackTrace();
				log.error(e.getMessage(), e);
				ShiplinxWebServiceModelHelper.addError(ShiplinxWebServiceModelHelper.SYSTEM_ERROR, 
														response.getErrors().getError());
				
				//Get the error messages returned from the carriers, if there are any
				for(CarrierErrorMessage carrierErrorMessage  :carrierServiceManager.getErrorMessages()) {
					ShiplinxWebServiceModelHelper.addError(	ShiplinxWebServiceModelHelper.CARRIER_ERROR_CODE, 
																		carrierErrorMessage.getMessage(),
																		response.getErrors().getError()); 
				}							
			}

		} else {
			ShiplinxWebServiceModelHelper.addError(ShiplinxWebServiceModelHelper.SERVICE_CODE_MISSING, 
										response.getErrors().getError());
		}
		
	}

	private void saveOrder(ShippingOrder so, ShippingResponseWSType response) {
		try { 
			if (so != null) {
				so.setStatusId(Long.valueOf(ShiplinxConstants.STATUS_READYTOPROCESS));
				so.setCustomsInvoiceRequired(true);
				if (this.shippingService != null) {
					so.setGeneratedBy(ShiplinxConstants.GENERATEDBYODBC);
					this.shippingService.save(so);
					// Order saved successfully
					// retrieve order info from the database, ideally saveOrder should return it
					ShippingOrder soOut = this.shippingDAO.getShippingOrder(so.getId());
					if (soOut != null) 
						response.setOrder(ShiplinxWebServiceModelHelper.convertOrder(soOut, shippingService));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
			ShiplinxWebServiceModelHelper.addError(ShiplinxWebServiceModelHelper.SYSTEM_ERROR, 
													response.getErrors().getError());
		}
	}

	private void initBeans() {
		// TODO Auto-generated method stub
		if (this.carrierServiceManager == null) {
			setCarrierServiceManager((CarrierServiceManager) 
					MmrBeanLocator.getInstance().findBean(
							"carrierServiceManager"));
		}
		if (this.shippingService == null) {
			setShippingService((ShippingService) 
					MmrBeanLocator.getInstance().findBean(
							"shippingService"));
		}		
		if (this.customerDAO == null) {
			setCustomerDAO((CustomerDAO) 
					MmrBeanLocator.getInstance().findBean(
							"customermanagerDAO"));
		}
		if (this.shippingDAO == null) {
			setShippingDAO((ShippingDAO) MmrBeanLocator.getInstance().findBean("shippingDAO"));
		}
		if (this.businessDAO == null)
			setBusinessDAO((BusinessDAO) MmrBeanLocator.getInstance().findBean("businessDAO"));
		
		if (this.addressService == null)
			this.setAddressService((AddressManager) MmrBeanLocator.getInstance().findBean("addressService"));

	}

	private Customer getCustomer(SecurityWSType security) {
		// TODO Auto-generated method stub
		if (this.customerDAO != null && security != null) {
			return this.customerDAO.getCustomerByApiInfo(
				security.getAPIUserName(), security.getAPIUserPassword());
		}
		return null;
	}

	public ShippingResponseWSType getOrderInfo(long orderId, SecurityWSType security) {
		if (orderId > 0 && security != null && !StringUtil.isEmpty(security.getAPIUserName())) {
			initBeans();
			ShippingResponseWSType response = new ShippingResponseWSType();
			response.setErrors(new ErrorsWSType());
			response.setSecurity(security);
			if (carrierServiceManager != null && shippingService != null) {
				Customer customer = getCustomer(security);
				if (customer != null) {
					ShippingOrder so = shippingService.getShippingOrder(orderId);
					if (so != null) {
						response.setOrder(ShiplinxWebServiceModelHelper.convertOrder(so, shippingService));
						
						// Generate Labels
						ByteArrayOutputStream baos = new ByteArrayOutputStream();
						carrierServiceManager.getShippingLabel(so, baos);
						// Base 64 Encode
						response.getOrder().setShippingLabel(Base64.encodeBase64(baos.toByteArray()));
					} else {
						ShiplinxWebServiceModelHelper.addError(ShiplinxWebServiceModelHelper.ORDER_NOT_FOUND, 
								response.getErrors().getError());
					}
				} else {
					ShiplinxWebServiceModelHelper.addError(ShiplinxWebServiceModelHelper.INVALID_API_CUSTOMER_INFO, 
																	response.getErrors().getError());
				}
			} else {
				ShiplinxWebServiceModelHelper.addError(ShiplinxWebServiceModelHelper.SYSTEM_ERROR, 
																	response.getErrors().getError());
			}
			if (response.getErrors() != null && response.getErrors().getError().size() > 0)
				response.setIsSuccess(false);
			else
				response.setIsSuccess(true);
				
			return response;
		}
		return null;

	}	

}
