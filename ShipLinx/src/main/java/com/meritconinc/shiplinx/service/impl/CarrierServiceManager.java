package com.meritconinc.shiplinx.service.impl;
//package com.meritconinc.shiplinx.service;

import java.io.OutputStream;
import java.util.List;

import com.meritconinc.shiplinx.carrier.CarrierService;
import com.meritconinc.shiplinx.model.Carrier;
import com.meritconinc.shiplinx.model.CustomerCarrier;
import com.meritconinc.shiplinx.model.Pickup;
import com.meritconinc.shiplinx.model.Rating;
import com.meritconinc.shiplinx.model.Service;
import com.meritconinc.shiplinx.model.ShippingOrder;
import com.meritconinc.shiplinx.utils.CarrierErrorMessage;

public interface CarrierServiceManager {
	
	public List<Rating> rateShipment(ShippingOrder order);
	public Object getShippingOrderStatus(ShippingOrder order);
	public CarrierService shipOrder(ShippingOrder order, Rating rateInfo) throws Exception;	
	public boolean cancelOrder(Long orderId, boolean isAdmin);	

	public List<CarrierService> getCarrierServices();
 
	public List getCutomerCarrier(Long customerId);
	public List getServicesForCarrier(Long carrierId);
	public Service getService(Long serviceId);
	public List<CarrierErrorMessage> getErrorMessages();
	/**
	 * 
	 * @param shippingOrder
	 * @param servletOutputStream
	 */
	public void getShippingLabel(ShippingOrder shippingOrder,OutputStream outputStream);
	public void getShippingLabels(List<String> listOrders,OutputStream outputStream, int sl_copies, int ci_copies);
	public List<CustomerCarrier> getAllCutomerCarrier(long businessId, long customerId);
	
	public List<CustomerCarrier> getAllCustomersCarrier(long businessId, long carrierId);
	public List<Carrier> getCarriers();
	public List<Carrier> getCarriersForBusiness(long businessId);
	
	public CustomerCarrier getDefAccountByCountry(long carrierId,long customerId,String country);
	
	public Service getServiceByCarrierIdAndCode(Long carrierId, String code);
	
	public long addNewService(Service service);
	
	public long createPickup(Pickup pickup) throws Exception;
	
	public boolean cancelPickup(Pickup pickup) throws Exception;
	public void uploadRates(long serviceId, long customerId, long busId, boolean isOverwrite) throws Exception;	
	public void checkForShipmentStatusUpdates();
	
}
