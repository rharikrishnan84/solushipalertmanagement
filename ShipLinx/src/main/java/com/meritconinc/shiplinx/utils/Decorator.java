package com.meritconinc.shiplinx.utils;

import java.text.DecimalFormat;

import org.apache.commons.lang.time.FastDateFormat;
import org.displaytag.decorator.TableDecorator;

/**
 * This class is a decorator of the TestObjects that we keep in our List. This class provides a number of methods for
 * formatting data, creating dynamic links, and exercising some aspects of the display:table API functionality.
 * @author epesh
 * @author Fabrizio Giustina
 * @version $Revision: 1.12 $ ($Author: fgiust $)
 */
public class Decorator extends TableDecorator
{

    /**
     * FastDateFormat used to format dates in getDate().
     */
    private FastDateFormat dateFormat;

    /**
     * DecimalFormat used to format money in getMoney().
     */
    private DecimalFormat moneyFormat; 

    /**
     * Creates a new Wrapper decorator who's job is to reformat some of the data located in our TestObject's.
     */
    public Decorator()
    {
        super();

        // Formats for displaying dates and money.

        this.dateFormat = FastDateFormat.getInstance("d MMM, yyyy"); //$NON-NLS-1$
        this.moneyFormat = new DecimalFormat("$ #,###,###.00"); //$NON-NLS-1$
    }

    /**
     * Test method which always returns a null value.
     * @return <code>null</code>
     */
    public String getNullValue()
    {
        return null;
    }

    /**
     * Returns the date as a String in MM/dd/yy format.
     * @return formatted date
     */
   /* public String getScheduledShipDate()
    {
        return this.dateFormat.format(((Order) this.getCurrentRowObject()).getScheduledShipDate());
    }*/
    
    /*public String getAllSurcharges(){
    	StringBuffer stb = new StringBuffer();
    	RateInfo rate = ((DistributionListRateInfo)this.getCurrentRowObject()).getRateInfo();
    	if(rate==null || rate.getSurcharges()==null)
    		return "";
    	
    	List<Surcharge> surcharges = rate.getSurcharges();
    	int i=0;
    	for(Surcharge s: surcharges){
    		if(i!=0)
    			stb.append("<br/>");
    		stb.append(s.getName() + " : $" + moneyFormat.format(s.getAmount()));
    		i++;
    	}
    	return stb.toString();
    }
    
    public String getFullAddress(){
    	StringBuffer stb = new StringBuffer();
    	ShippingAddress address = ((DistributionListRateInfo)this.getCurrentRowObject()).getShipTo();
    	
    	stb.append(address.getCompany() + ", " + address.getAddress1() + " " + address.getAddress2());
    	stb.append("<br/>" + address.getCity() + ", " + address.getProvince() + ", " + address.getPostalCode() + ", " + address.getCountry());
    	
    	return stb.toString();
    }*/

}