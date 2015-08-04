package com.meritconinc.shiplinx.api.base;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.restlet.data.Form;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.ServerResource;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.meritconinc.shiplinx.api.Util.ShopifyUtil;
import com.meritconinc.shiplinx.model.Customer;

public class GenericRestServerResource extends ServerResource {
	private Form headers = null;
	private static final Logger log = LogManager.getLogger(GenericRestServerResource.class);
	SolushipErrorObject errorObject = new SolushipErrorObject();
	 Customer customer;
	@Override
	
	  public Representation handle() {

	    Representation representation = null;
	    Representation reqRepresentation = getRequestEntity();
	    
	    headers = (Form) getRequestAttributes().get("org.restlet.http.headers");
	 if(  ShopifyUtil.isValidRequest(headers.getValues("x-shopify-shop-domain"),getQuery().getValues("username"),getQuery().getValues("password"))){
		 log.debug("===============================     VAILD REQUEST FROM SHOPIFY ========================================");
	    super.handle();
	   }
	    return representation;
	  }

	/*
	   * getting the error object based on the json content
	   */
	  @SuppressWarnings("unchecked")
	  public SolushipErrorObject getErrorObject(final JSONObject jsonObject) {
	    final Iterator<String> keyIterator = jsonObject.keys();
	    for (final Iterator<String> iterator2 = keyIterator; iterator2.hasNext();) {
	      final String key = iterator2.next();
	      String value = "";
	      try {
	        value = jsonObject.get(key).toString();
	      } catch (final JSONException e) {
	        e.printStackTrace();
	      }
	      try {
	    	  errorObject.setStatusCode(Integer.parseInt(key));
	    	  errorObject.setMessage(value);
	      } catch (final Exception e) {
	        e.printStackTrace();
	      }
	    }
	    return errorObject;
	  }
 

}
