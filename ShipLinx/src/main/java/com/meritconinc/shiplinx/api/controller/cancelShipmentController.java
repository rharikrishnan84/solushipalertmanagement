package com.meritconinc.shiplinx.api.controller;
/**
 * @author SELVA GANESH
 * @DATE 29/07/2015
 */
import java.io.IOException;
import java.security.InvalidKeyException;
import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.json.JSONException;
import org.json.JSONObject;
import org.restlet.resource.Post;

import com.meritconinc.mmr.utilities.MmrBeanLocator;
import com.meritconinc.shiplinx.api.base.GenericRestServerResource;
import com.meritconinc.shiplinx.dao.ShippingDAO;
import com.meritconinc.shiplinx.model.ShippingOrder;
import com.meritconinc.shiplinx.service.CarrierServiceManager;
import com.meritconinc.shiplinx.utils.ShiplinxConstants;
 
public class cancelShipmentController extends GenericRestServerResource implements ServletRequestAware{

	private HttpServletRequest request;
	private static final Logger log = LogManager
			.getLogger(cancelShipmentController.class);
	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		this.request=arg0;
	}
	
	@Post("json")
	public String cancelShipmentJson(String entity) throws JsonParseException,
			JsonMappingException, IOException, ParseException, JSONException, InvalidKeyException {
		
		CarrierServiceManager carrierServiceManager = (CarrierServiceManager) MmrBeanLocator
				.getInstance().findBean("carrierServiceManager");
		 ShippingDAO shippingDAO = (ShippingDAO)
				  MmrBeanLocator.getInstance().findBean("shippingDAO"); 
		System.out.println(entity);
		JSONObject j=new JSONObject(entity);
		Long id=j.getLong("id");
		if(id!=null){
			ShippingOrder so=shippingDAO.getShippingOrderByReferenceOne(id,ShiplinxConstants.SHIPMENT_CANCELLED);
			if(so!=null && !so.getStatusId().equals("40")){
				log.debug("======== INTO THE CANCEL THE SHIPPING FROM SHOPIFY =============== order Id :"+so.getId());
				carrierServiceManager.cancelOrder(so.getId(), true);
				
			}else{
				return null;
			}
		}
		return entity;
	}

}
