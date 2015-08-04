package com.meritconinc.shiplinx.api.controller;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.util.Date;
import java.sql.Timestamp;
import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.json.JSONException;
import org.json.JSONObject;
import org.restlet.resource.Post;

import com.meritconinc.mmr.dao.EcommerceDAO;
import com.meritconinc.mmr.model.admin.EcommerceStore;
import com.meritconinc.mmr.utilities.MmrBeanLocator;
import com.meritconinc.shiplinx.api.base.GenericRestServerResource;
import com.meritconinc.shiplinx.dao.ShippingDAO;
import com.meritconinc.shiplinx.model.ShippingOrder;
import com.meritconinc.shiplinx.service.CarrierServiceManager;
import com.meritconinc.shiplinx.utils.ShiplinxConstants;

/**
 * 
 * @author SELVA GANESH
 * 
 *
 */
public class ShopifyAppManager extends GenericRestServerResource implements ServletRequestAware{

	private HttpServletRequest request;
	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		request=arg0;
	}
	
	@Post("json")
	public String unInstallApp(String entity) throws JsonParseException,
			JsonMappingException, IOException, ParseException, JSONException, InvalidKeyException {
 
		EcommerceDAO eCommerceDAO = (EcommerceDAO) MmrBeanLocator.getInstance()
				.findBean("eCommerceDAO");
 		JSONObject js=new JSONObject(entity);
		String domain=js.getString("domain");
	    Long id=js.getLong("id");
		domain="https://"+domain;
		EcommerceStore store=eCommerceDAO.getEcomStorebyStoreUrl(domain);
		if(store!=null){
			store.setCancelShipWebhookId(null);
			store.setCreateShipWebhookId(null);
			store.setRateServiceId(null);
			store.setActive(false);
			store.setUpdatedAt(new Timestamp(new Date().getTime()));
			store.setWebhookUnistallId(id);
			store.setAccessKey(null);
			eCommerceDAO.UpdateServices(store);
		}
		return entity;
	}
	
	
}
