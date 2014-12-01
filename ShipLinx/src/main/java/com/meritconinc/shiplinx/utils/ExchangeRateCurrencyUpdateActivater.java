package com.meritconinc.shiplinx.utils;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.MediaType;
import org.json.JSONObject;

import com.meritconinc.mmr.utilities.MmrBeanLocator;
import com.meritconinc.shiplinx.dao.ShippingDAO;
import com.meritconinc.shiplinx.model.ExchangeRateCurrency;
import com.meritconinc.shiplinx.service.ShippingService;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

//import java.util.List;
public class ExchangeRateCurrencyUpdateActivater {

	private ShippingService shippingService;
	private ShippingDAO shippingDAO;
	public void updateExchangeRateTable() {
		// TODO Auto-generated method stub
		try{
			shippingDAO = (ShippingDAO) MmrBeanLocator.getInstance().findBean("shippingDAO");
			String fromCurr;
			String toCurr;
			Double exchRate,tempExchRate;
			String url;
			List<ExchangeRateCurrency> ExcRateCurrList=shippingDAO.getAllExchangeRateCurrency();
			Client client = Client.create();
			if(ExcRateCurrList!=null && ExcRateCurrList.size()>0){
				for(ExchangeRateCurrency exchangeRate:ExcRateCurrList){
					fromCurr=exchangeRate.getCurrencyFrom();
					toCurr=exchangeRate.getCurrencyTo();
					tempExchRate=exchangeRate.getExchangeRate();
					if(fromCurr!=null && toCurr!=null && !"".equalsIgnoreCase(fromCurr) && !"".equalsIgnoreCase(toCurr)){
						url ="http://rate-exchange.herokuapp.com/fetchRate?from="+fromCurr+"&to="+toCurr;
						try{
							WebResource webResource = client.resource(url);
							String response = webResource.accept(MediaType.APPLICATION_JSON_TYPE).get(String.class);
							JSONObject jo = new JSONObject(response);
							System.out.println("Exchange Rate Response "+response);
							exchRate=Double.parseDouble(jo.getString("Rate"));
							if(exchRate!=null && !exchRate.equals(tempExchRate)){
								exchangeRate.setExchangeRate(exchRate);
								shippingDAO.updateExchangeRateCurrency(exchangeRate);
							}
						}catch(Exception e){
							e.printStackTrace();
						}
					}
			  		
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
