package com.meritconinc.shiplinx.utils;

import org.displaytag.decorator.TableDecorator;

import com.meritconinc.shiplinx.model.ShippingOrder;

/**
 * This class is a decorator of the TestObjects that we keep in our List. This class provides a number of methods for
 * formatting data, creating dynamic links, and exercising some aspects of the display:table API functionality.
 * @author epesh
 * @author Fabrizio Giustina
 * @version $Revision: 1.12 $ ($Author: fgiust $)
 */
public class ShippingOrderDecorator extends TableDecorator
{
	public String getQuotedWeight(){
		ShippingOrder shippingOrder = (ShippingOrder)getCurrentRowObject();
		return (shippingOrder.getQuotedWeight().toString() + " " + (shippingOrder.getQuotedWeightUOM()== null ? "" : shippingOrder.getQuotedWeightUOM()));
		
	}
 
	public String getBilledWeight(){
		ShippingOrder shippingOrder = (ShippingOrder)getCurrentRowObject();
		if(shippingOrder.getBilledWeight()==null || shippingOrder.getBilledWeight()==0)
			return "";
		return (shippingOrder.getBilledWeight().toString() + " " + (shippingOrder.getBilledWeightUOM()==null ? "" : shippingOrder.getBilledWeightUOM()));
		
	} 

}