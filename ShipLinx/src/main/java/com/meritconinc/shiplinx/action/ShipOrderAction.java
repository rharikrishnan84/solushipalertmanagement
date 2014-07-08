/**
 * 
 */
package com.meritconinc.shiplinx.action;

import com.meritconinc.mmr.action.BaseAction;
import com.meritconinc.shiplinx.model.ShippingOrder;
import com.meritconinc.shiplinx.service.CarrierServiceManager;


/**
 * @author brinzf2
 *
 */
public class ShipOrderAction extends BaseAction {
	private static final long serialVersionUID	= 18052007;
	
	private CarrierServiceManager carrierServiceManager;
	private ShippingOrder shippingOrder;
	
	
		
	public void setCarrierServiceManager(CarrierServiceManager carrierServiceManager) {
		this.carrierServiceManager = carrierServiceManager;
	} 

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public String execute() throws Exception {
		return SUCCESS;
	}


	public String performRating(){
		
				
		carrierServiceManager.rateShipment(shippingOrder);
		
		return SUCCESS;
		
	}
	
	
	public ShippingOrder getShippingOrder() {
		return shippingOrder;
	}
	public void setShippingOrder(ShippingOrder shippingOrder) {
		this.shippingOrder = shippingOrder;
	}
	
	
	
}
