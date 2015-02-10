package com.meritconinc.shiplinx.api.base;

import java.util.Iterator;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;
import org.restlet.data.Form;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.ServerResource;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.meritconinc.mmr.utilities.MmrBeanLocator;
import com.meritconinc.shiplinx.dao.CustomerDAO;
import  com.meritconinc.shiplinx.model.Customer;
import com.meritconinc.shiplinx.service.CustomerManager;

public class SolushipRestServerResource extends ServerResource {
	private Form headers = null;
	private static final Logger log = LogManager.getLogger(SolushipRestServerResource.class);
	SolushipErrorObject errorObject = new SolushipErrorObject();
	 Customer customer;
	@Override
	  public Representation handle() {

	    Representation representation = null;
	    headers = (Form) getRequestAttributes().get("org.restlet.http.headers");

	    // getting the representation entity from the request
	    Representation reqRepresentation = getRequestEntity();

	    try {
	      JSONObject jsonObject = validateHeaders();
	      if (jsonObject.length() != 0) {
	        representation = new JsonRepresentation(getErrorObject(jsonObject));
	        getResponse().setEntity(representation);
	        return representation;
	      }
	    } catch (JSONException e) {
	      // TODO Auto-generated catch block
	      e.printStackTrace();
	    }
	    if(customer!=null){
	    super.handle();
	    }
	    else{
	    	log.debug("Cannot access web serivice");
	    }
	    return representation;
	  }
	
	 /*
	   * validating the header values
	   */
	  public JSONObject validateHeaders() throws JSONException {
	    Map<String, String> headerValues = headers.getValuesMap();
	    String username = headerValues.get("username");
	    String password = headerValues.get("password");
	    JSONObject validateJsonObject = new JSONObject();
	    CustomerDAO customerDAO = (CustomerDAO) MmrBeanLocator.getInstance().findBean("customermanagerDAO");
	    if(!(username==null) && !(password==null))
	    {	
	    customer=customerDAO.getCustomerByApiInfo(username, password);
        if(customer==null)
        {
	      validateJsonObject.put("401", " UNAUTHORIZED ACCESS");
	      log.debug("Access Denied For User "+username );
        }
	    }
	    return validateJsonObject;
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
