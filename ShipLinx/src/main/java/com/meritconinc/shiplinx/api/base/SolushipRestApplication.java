package com.meritconinc.shiplinx.api.base;

import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.routing.Router;

import com.meritconinc.shiplinx.api.controller.UpdateShippingOrderController;

public class SolushipRestApplication extends Application {

	/**
	* Creates a root Restlet that will receive all incoming calls.
	*/
	@Override
	public synchronized Restlet createInboundRoot() {
		Router router = new Router(getContext());
		// Defines only one route
		router.attach("/updateorder", UpdateShippingOrderController.class);
		return router;
	}	

}
