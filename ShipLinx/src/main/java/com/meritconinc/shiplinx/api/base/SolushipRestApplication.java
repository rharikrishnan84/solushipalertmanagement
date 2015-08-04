package com.meritconinc.shiplinx.api.base;

import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.routing.Router;

import com.meritconinc.shiplinx.api.controller.UpdateShippingOrderController;
import com.meritconinc.shiplinx.api.controller.CreateShipmentAPIController;
import com.meritconinc.shiplinx.api.controller.GetRatesAPIController;
import com.meritconinc.shiplinx.api.controller.cancelShipmentController;
import com.meritconinc.shiplinx.api.controller.ShopifyAppManager;
public class SolushipRestApplication extends Application {

	/**
	* Creates a root Restlet that will receive all incoming calls.
	*/
	@Override
	public synchronized Restlet createInboundRoot() {
		Router router = new Router(getContext());
		// Defines only one route
		router.attach("/updateorder", UpdateShippingOrderController.class);
		router.attach("/createShipment",CreateShipmentAPIController.class);
		router.attach("/genericrates", GetRatesAPIController.class);
		router.attach("/cancelshipment",cancelShipmentController.class);
		router.attach("/uninstallShopify",ShopifyAppManager.class);
		return router;
	}	

}
