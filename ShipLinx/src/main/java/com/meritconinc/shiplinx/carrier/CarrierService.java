package com.meritconinc.shiplinx.carrier;

import java.io.OutputStream;
import java.util.List;

import com.meritconinc.shiplinx.model.Customer;
import com.meritconinc.shiplinx.model.CustomerCarrier;
import com.meritconinc.shiplinx.model.Pickup;
import com.meritconinc.shiplinx.model.Rating;
import com.meritconinc.shiplinx.model.Service;
import com.meritconinc.shiplinx.model.ShippingOrder;
import com.meritconinc.shiplinx.utils.CarrierErrorMessage;

public interface CarrierService {
	
	public long getCarrierId();
	
	/*
	 *  Determine the rate(s) for an order based on the parameters.
	 *  
	 *  This can return a rate for a service.
	 *   
	 */
	public List<Rating> rateShipment(ShippingOrder order, List<Service> services, long carrierId, CustomerCarrier customerCarrier) throws RuntimeException;
	
	/*
	 * This method will use the tracking number(s) and determine
	 * the delivery status of the order
	 */
	public Object getShippingOrderStatus(ShippingOrder order);
	

	/*
	 * This method is used to ship an order
	 * 
	 * 1) ship
	 * get back rates, labels
	 */
	public void shipOrder(ShippingOrder order, Rating rateInfo, CustomerCarrier customerCarrier);
	
	public boolean cancelOrder(ShippingOrder order, CustomerCarrier customerCarrier);
	
	public String getTrackingURL(ShippingOrder o);
	
	
	public void generateShippingLabel(OutputStream outputStream, long orderId, CustomerCarrier customerCarrier);

//	public void generatePackageLabel(OutputStream outputStream, long orderId);
//	
//	public void generatePdfManifestSheet(OutputStream outputStream, long customerId, Date scheduledShipDate);
//
//	public void generateVoidSheet(OutputStream outputStream, long customerId, Date scheduledShipDate);
//	
//	public void generatePickupSheet(OutputStream outputStream, long customerId, Date scheduledShipDate, int pickupNum, boolean commit);
//	
//	public void generateShippingLabel(OutputStream outputStream, long orderId, Document document, PdfWriter writer);

	
	public void requestPickup(Pickup pickup);
	public boolean cancelPickup(Pickup pickup);
//	public void modifyPickup(ScheduledPickup pickup);
//	public void cancelPickup(ScheduledPickup pickup);
	
	public List<CarrierErrorMessage> getErrorMessages();

	public void uploadRates(Service service, long customerId, long busId, boolean isOverwrite) throws Exception;
}
