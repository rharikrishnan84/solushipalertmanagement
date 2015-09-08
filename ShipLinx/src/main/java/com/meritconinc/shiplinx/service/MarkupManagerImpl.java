package com.meritconinc.shiplinx.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.meritconinc.mmr.constants.Constants;
import com.meritconinc.mmr.utilities.MmrBeanLocator;
import com.meritconinc.mmr.utilities.WebUtil;
import com.meritconinc.mmr.utilities.security.UserUtil;
import com.meritconinc.shiplinx.dao.CarrierServiceDAO;
import com.meritconinc.shiplinx.dao.CustomerDAO;
import com.meritconinc.shiplinx.dao.MarkupManagerDAO;
import com.meritconinc.shiplinx.model.Address;
import com.meritconinc.shiplinx.model.BusinessMarkup;
import com.meritconinc.shiplinx.model.Carrier;
import com.meritconinc.shiplinx.model.CarrierChargeCode;
import com.meritconinc.shiplinx.model.Charge;
import com.meritconinc.shiplinx.model.ChargeGroup;
import com.meritconinc.shiplinx.model.Customer;
import com.meritconinc.shiplinx.model.LtlPoundRate;
import com.meritconinc.shiplinx.model.LtlSkidRate;
import com.meritconinc.shiplinx.model.Markup;
import com.meritconinc.shiplinx.model.Service;
import com.meritconinc.shiplinx.model.ShippingOrder;
import com.meritconinc.shiplinx.model.Zone;
import com.meritconinc.shiplinx.utils.FormattingUtil;
import com.meritconinc.shiplinx.utils.ShiplinxConstants;
import com.soluship.businessfilter.util.BusinessFilterUtil;
import com.opensymphony.xwork2.ActionContext;

public class MarkupManagerImpl implements MarkupManager {

  private static final Logger log = LogManager.getLogger(MarkupManagerImpl.class);

  private MarkupManagerDAO markupDAO;
  private CustomerDAO customerDAO;
  private CarrierServiceDAO carrierServiceDAO;
  private CarrierServiceManager carrierServiceManager;
  private long ltlServiceId;
  private long ltlCustomerId;
  private long ltlBusId;
  private boolean isOverwrite;

  public void setCarrierServiceDAO(CarrierServiceDAO carrierServiceDAO) {
    this.carrierServiceDAO = carrierServiceDAO;
  }

  public MarkupManagerDAO getMarkupDAO() {
    return markupDAO;
  }

  public void setMarkupDAO(MarkupManagerDAO markupDAO) {
    this.markupDAO = markupDAO;
  }

  public CustomerDAO getCustomerDAO() {
    return customerDAO;
  }

  public void setCustomerDAO(CustomerDAO customerDAO) {
    this.customerDAO = customerDAO;
  }

  public CarrierServiceManager getCarrierServiceManager() {
    return carrierServiceManager;
  }

  public void setCarrierServiceManager(CarrierServiceManager carrierServiceManager) {
    this.carrierServiceManager = carrierServiceManager;
  }

  // public List<Markup> getMarkupListForCustomer(Markup markup) {
  // // TODO Auto-generated method stub
  // if (markupDAO != null && markup != null) {
  // return markupDAO.getMarkupListForCustomer(markup);
  // }
  // return null;
  // }
  public List<Markup> getMarkupListForCustomer(Markup markup) {
    // TODO Auto-generated method stub
	  if (markupDAO != null && markup != null) {
	  if (markup.getCustomerId() > 0) {
        Long orgCustId = markup.getCustomerId();
        List<Long> custIds = markup.getCustomerIds();
        List<Markup> custMarkupList = markupDAO.getMarkupListForCustomer(markup);
      //if (custMarkupList != null) {
                if(custMarkupList==null  || custMarkupList.size()==0){
                	        	        	custMarkupList=BusinessFilterUtil.getMarkupListForCustomer(markupDAO,markup);	
                	        	           }
             if (custMarkupList != null && !custMarkupList.isEmpty()) {
                	        	markup.setCustomerId(0L);
                  List<Markup> defMarkupList = markupDAO.getMarkupListForCustomer(markup);
                 if(defMarkupList==null  || defMarkupList.size()==0){
               	          	          	  defMarkupList=BusinessFilterUtil.getMarkupListForCustomer(markupDAO,markup);	
                	          	               }
        	        	        	         
          
          for (Markup m : custMarkupList) {
            int n = findMarkup(defMarkupList, m);
            if (n == -1) {
              // this situation should not happen
              defMarkupList.add(m);
            } else {
              defMarkupList.remove(n); // There is already a customized markup, therfore remove
                                       // default Markup
              defMarkupList.add(m); // add customized markup in the list
            }
          }
          custMarkupList.clear();
          markup.setCustomerId(orgCustId);
          /*if(custIds!=null && custIds.size() >0){
        	          	          	          	  for(Long custId : custIds){
        	          	          	          		  boolean flag = markupDAO.isCustomerMarkupByDisabled(custId);  
        	          	          	          		  for(Markup m : defMarkupList){                                   
        	          	          	          			  if(!flag && m.getDisabled()==0)       
        	          	          	         				  m.setDisabled(1);            
        	          	          	          		  }                                                                
        	          	          	          	  }
        	          	          	          	  
        	          	          	            }      */    
          boolean flag = markupDAO.isCustomerMarkupByDisabled(orgCustId);  
                                          		  for(Markup m : defMarkupList){                                   
                                          			  if(!flag && m.getDisabled()==0)       
                                         				  m.setDisabled(1);            
                                         		  }  
        	                                                                           
          return defMarkupList;
        }
      }
	  if (markup.getCustomerId() > 0){
		  		  long cus = markup.getCustomerId();
		  		  markup.setCustomerId(0l);
		  		  List<Markup> mList=markupDAO.getMarkupListForCustomer(markup);
		            if(mList==null  || mList.size()==0){
		                mList=BusinessFilterUtil.getMarkupListForCustomer(markupDAO,markup);	
		              }
		            markup.setCustomerId(cus);
		            return mList;
		  	  }else{
		  		  List<Markup> mList=markupDAO.getMarkupListForCustomer(markup);
		            if(mList==null  || mList.size()==0){
		                mList=BusinessFilterUtil.getMarkupListForCustomer(markupDAO,markup);	
		              }
		            return mList;
		  	  }
    }
    return null;
  }

  private int findMarkup(List<Markup> markupList, Markup markup) {
    // TODO Auto-generated method stub
    if (markupList != null) {
      for (int i = 0; i < markupList.size(); i++) {
        Markup m = markupList.get(i);
        if (m.getServiceId().equals(markup.getServiceId())
            && m.getFromCountryCode().equals(markup.getFromCountryCode())
            && m.getToCountryCode().equals(markup.getToCountryCode())
            && m.getFromWeight().longValue() == markup.getFromWeight().longValue()
            && m.getToWeight().longValue() == markup.getToWeight().longValue()) {
          return i;
        }
      }
    }
    return -1;
  }

  public Double[] getFlatMarkups() {
    // TODO Auto-generated method stub
    if (markupDAO != null)
      return markupDAO.getFlatMarkups();
    return null;
  }

  public Integer[] getPercentageMarkups() {
    // TODO Auto-generated method stub
    if (markupDAO != null)
      return markupDAO.getPercentageMarkups();
    return null;
  }

  @Override
  public void deleteMarkup(Markup markup) {
    // TODO Auto-generated method stub
    if (markupDAO != null && markup != null)
      markupDAO.deleteMarkup(markup);
  }

  //
  // @Override
  // public void addMarkup(Markup markup) throws MarkupAlreadyExistsException{
  // // TODO Auto-generated method stub
  // if (markupDAO != null && markup != null) {
  // List<Markup> mList = markupDAO.getMarkupListForCustomer(markup);
  // if (mList == null || mList.size() == 0) {
  // markupDAO.addMarkup(markup);
  // } else {
  // throw new MarkupAlreadyExistsException("Markup already exists - " +
  // mList.get(0).getServiceName());
  // }
  // }
  // }

  @Override
  public void applyToAllCustomersMarkup(Markup markup) {
    // TODO Auto-generated method stub
    if (markupDAO != null && customerDAO != null && markup != null) {
      Customer customer = new Customer();
      customer.setBusinessId(UserUtil.getMmrUser().getBusinessId());
      List<Customer> cList = customerDAO.search(customer);
      if (cList != null) {
        Long orgCustomerId = markup.getCustomerId();
        for (Customer c : cList) {
          if (c != null) {
            markup.setCustomerId(c.getId());
            List<Markup> mList = markupDAO.getMarkupListForCustomer(markup);
            if(mList==null  || mList.size()==0){
            	            	            	             mList=BusinessFilterUtil.getMarkupListForCustomer(markupDAO,markup);	
            	            	            	            }
            if (mList != null && mList.size() > 0) {
              for (Markup m : mList) {
                if (m != null) {
                  // Markup already exists, therefore update it
                  m.setMarkupPercentage(markup.getMarkupPercentage());
                  m.setMarkupFlat(markup.getMarkupFlat());
                  m.setDisabled(markup.getDisabled());
                  markupDAO.updateMarkup(m);
                }
              }
            } else {
              // this doesn't exist add it
              markupDAO.addMarkup(markup);
            }
          }
        }
        markup.setCustomerId(orgCustomerId);
      }
    }
  }

  @Override
  public void updateMarkup(Markup markup) {
    // TODO Auto-generated method stub
    if (markup != null && markupDAO != null)
      markupDAO.updateMarkup(markup);
  }

  @Override
  public void calculatMarkup(ShippingOrder shipment, Charge charge) {
    // TODO Auto-generated method stub
    // call thismethod when only fuel and freith
    // List<CarrierChargeCode> carrierChargeCodes =
    // shippingServer.getChargeByCarrierAndCodes();
    //
    // for(CarrierChargeCode ccc:carrierCharg)
    // // get group and if (group is friegt and fuel)
    // //
    // // then markup using percentage and update charge property
    // // friet and fuel will be marked up
    // //
    // // incase

  }

  @Override
  public Markup addMarkup(Markup markup) {
    // TODO Auto-generated method stub
    if (markupDAO != null && markup != null) {
      Markup m = getUniqueMarkup(markup);
      if (m == null || m.getCustomerId().longValue() != markup.getCustomerId().longValue()
          || !m.getFromCountryCode().equals(markup.getFromCountryCode())
          || !m.getToCountryCode().equals(markup.getToCountryCode())) {
        markupDAO.addMarkup(markup);
      } else {
        return m;
      }
    }

    return null;
  }

  @Override
  public Markup getUniqueMarkup(Markup markup) {
    // TODO Auto-generated method stub
	  //vivek hide
	 // boolean addMark=false;
    if (markupDAO != null && markup != null) {
      List<Markup> mList = markupDAO.getMarkupListForUniqueMarkup(markup);
      if(mList==null  || mList.size()==0){
    	      	      	            mList=BusinessFilterUtil.getMarkupListForUniqueMarkup(markupDAO,markup);	
    	      	      	           }
      if (mList != null && mList.size() > 0) {
    	  
    	  //vivek hide
    	  /*try{
    		  	  	  	    
    		  	  	   	   addMark= (Boolean) ActionContext.getContext().getSession().get("addMarkup");
    		  	  	   	    }catch(Exception e)
    		  	  	   	    {
    		    	   	    	addMark=false;
    		  	  	   	    }
    		  	  	    	if(addMark)
    		  	  	    	{
    		  	  	    		return applyRulesForAdd(mList,markup);
    		  	  	    	}else{
*/
        return applyRules(mList, markup);
    		  	  	    	//}
      }
    }
    return null;
  }
  public Markup getUniqueMarkupList(Markup markup) {
	  	    // TODO Auto-generated method stub
	  	    if (markupDAO != null && markup != null) {
	  	      List<Markup> mList = markupDAO.getMarkupList(markup);
	  	    if(mList==null  || mList.size()==0){
	  	    		  	    		  	    		  	    	mList=BusinessFilterUtil.getMarkupList(markupDAO,markup);	
	  	    		  	    		  	    		              }
	  	      if (mList != null && mList.size() > 0) {
	  	        return applyRules(mList, markup);
	  	      }
	      }
	  	    return null;
	  	  }
  public Double applyMarkup(ShippingOrder shipment, Charge charge, boolean setShipmentMarkup) {
    // TODO Auto-generated method stub
    Markup markup = null;
    double amount = 0.0;

    int markPerc = 0;
    int markType = 0;
    if (shipment != null && charge != null) {
      if (shipment.getMarkPercent() == null) {
        Markup searchMarkup = getMarkupObj(shipment);
        markup = getUniqueMarkup(searchMarkup);
        if (markup != null && setShipmentMarkup) {
        	shipment.setMarkPercent((markup.getMarkupPercentage() == null) ? 0 : markup
              .getMarkupPercentage());
          shipment.setMarkType((markup.getType() == null) ? 0 : markup.getType());

        }
        if (markup == null) {// if the markup is null (could not find mark up object for this
                             // shipment/charge), return the cost
          log.warn("Could not find mark up record for business/customer/service : "
              + searchMarkup.getBusinessId() + " / " + searchMarkup.getCustomerId() + " / "
              + searchMarkup.getServiceId());
          return charge.getCost();
        }
        markPerc = (markup != null && markup.getMarkupPercentage() != null) ? markup
        			.getMarkupPercentage() : 0;
        	        markType = (markup != null && markup.getType() != null) ? markup.getType() : 0;


      } else { // already set in the shipment
    	  markPerc = (shipment.getMarkPercent() == null) ? 0 : shipment.getMarkPercent();
    	  markType = (shipment.getMarkType() == null) ? 0 : shipment.getMarkType();

      }

      if (markPerc >= 0) {
        if (markType > 0) {
          if (markType == ShiplinxConstants.TYPE_MARKDOWN) {
            // after introduction of static component, we remove the static component before
            // calculating the marked up or down value
            double f = ((FormattingUtil.subtract(charge.getTariffRate().doubleValue(),
                charge.getStaticAmount())).doubleValue())
                * markPerc / 100;
            amount = (FormattingUtil.subtract(charge.getTariffRate().doubleValue(), f))
                .doubleValue();
          } else { // marking up
            double f = (FormattingUtil.subtract(charge.getCost().floatValue(),
                charge.getStaticAmount())).doubleValue()
                * markPerc / 100;
            amount = (FormattingUtil.add(charge.getCost().doubleValue(), f)).doubleValue();
          }
        }
      }   
      /*
       * else if(markPerc >=0 && typeText.equals("Flat")){ if (markType ==
       * ShiplinxConstants.TYPE_MARKDOWN){ double f =markPerc; amount =
       * (FormattingUtil.subtract(charge.getTariffRate().doubleValue(), f)).doubleValue(); }else{
       * double f =markPerc; amount = (FormattingUtil.add(charge.getCost().doubleValue(),
       * f)).doubleValue(); } }
       */
    }
    return FormattingUtil.formatDecimalTo2PlacesDouble(amount);
  }

  public Markup getMarkupObj(ShippingOrder shipment) {
    // TODO Auto-generated method stub
    Markup m = new Markup();
    m.setBusinessId(shipment.getBusinessId());
    m.setCustomerId(shipment.getCustomerId());
    if (shipment.getFromAddress() != null)
      m.setFromCountryCode(shipment.getFromAddress().getCountryCode());
    if (shipment.getToAddress() != null)
      m.setToCountryCode(shipment.getToAddress().getCountryCode());

    Double weight = null;
    if (shipment.getWeightToMarkup() != null && shipment.getWeightToMarkup().doubleValue() > 0)
      weight = shipment.getWeightToMarkup();
    else
      weight = shipment.getTotalWeight();
    if (weight != null && weight.doubleValue() > 0) {
      m.setFromWeight(weight);
      m.setToWeight(weight);
    }

    m.setServiceId(shipment.getServiceId());

    return m;
  }
  
  private Markup applyRulesForAdd(List<Markup> mList, Markup markup) {
	  	  
	  	  boolean bCus = false;
	  	  boolean addMark=false;
	   	    if (markup.getCustomerId() != null && markup.getCustomerId().longValue() > 0) {
	   	      // Customer based markup
	   	      bCus = true;
	   	    }
	      try{
	  	    
	     addMark= (Boolean) ActionContext.getContext().getSession().get("addMarkup");
	  	    }catch(Exception e)
	  	    {
	  	    	addMark=false;
	      }
	  	   
	  	   if(addMark)
	  	   {
	  		   for(Markup m:mList){
	  			   if(m.getServiceId().equals(markup.getServiceId()) && isCompareCountries(m, markup)&&bCus && m.getCustomerId().equals(markup.getCustomerId())&&m.getVariable()==markup.getVariable()){
	  		   if (m.getServiceId().equals(markup.getServiceId()) && isCompareCountries(m, markup)&&isWeightsMatched(m, markup)) {
	  					    if (bCus && m.getCustomerId().equals(markup.getCustomerId()))
	  				      return m;
	  					    /*if (m.getCustomerId().longValue() == 0)
	  				      return m;*/
	  					  }
	  		   }
	  			   else{
	  				   if (m.getServiceId().equals(markup.getServiceId()) && isCompareCountries(m, markup)) {
	  					    if (bCus && m.getCustomerId().equals(markup.getCustomerId()))
	  					      return m;
	  					    /*if (m.getCustomerId().longValue() == 0)
	  					      return m;*/
	  					  }
	  				   
	  			   }
	  		   }
	  		   
	  	   }
	  	   
	    
	  	  return null;
	    
	    }


  private Markup applyRules(List<Markup> mList, Markup markup) {
    // TODO Auto-generated method stub
	  boolean bCus = false;
	  
	    if (markup.getCustomerId() != null && markup.getCustomerId().longValue() > 0) {
	      // Customer based markup
	      bCus = true;
	    }
	    
	    
	    		   
	    		   /* for (Markup m : mList) {
	    		    		    	      if (m.getCustomerId().equals(markup.getCustomerId()) &&m.getServiceId().equals(markup.getServiceId()) 
	    		    		    	          && m.getFromCountryCode().equals(ShiplinxConstants.COUNTRY_ANY)
	    		    		    	          && m.getToCountryCode().equals(ShiplinxConstants.COUNTRY_ANY)&&m.getToWeight()>=markup.getToWeight()) {
	    	    		    	        if (bCus && m.getCustomerId().equals(markup.getCustomerId()))
	    		    		    	          return m;
	    		    		    	        if (m.getCustomerId().longValue() == 0)
	    		    		    	          return m;
	    		    		    	      }
	    		    		    	    }*/

	    
	    for (Markup m : mList) {
	    	if(bCus){
				  // Rule # 1 - All Fields Match
				  if (m.getServiceId().equals(markup.getServiceId()) && isCompareCountries(m, markup)
				      && isWeightsMatched(m, markup)) {
				    if (bCus && m.getCustomerId().equals(markup.getCustomerId()))
				      return m;
				    /*if (m.getCustomerId().longValue() == 0)
				      return m;*/
				  }
				
				  // Rule # 2 - match without Weights
				  if (m.getServiceId().equals(markup.getServiceId()) && isCompareCountries(m, markup)
				      && m.getFromWeight().floatValue() == 0) {
				    if (bCus && m.getCustomerId().equals(markup.getCustomerId()))
				      return m;
				    /*if (m.getCustomerId().longValue() == 0)
				      return m;*/
				  }
				
				  // Rule # 3 - All Fields Match - To Country is "ANY"
				  if (m.getServiceId().equals(markup.getServiceId())
				      && m.getFromCountryCode().equals(markup.getFromCountryCode())
				      && m.getToCountryCode().equals(ShiplinxConstants.COUNTRY_ANY)
				      && isWeightsMatched(m, markup)) {
				    if (bCus && m.getCustomerId().equals(markup.getCustomerId()))
				      return m;
				    /*if (m.getCustomerId().longValue() == 0)
				      return m;*/
				  }
				
				  // Rule # 4 - match without Weights and To Country is "ANY"
				  if (m.getServiceId().equals(markup.getServiceId()) && m.getFromWeight().floatValue() == 0
				      && m.getFromCountryCode().equals(markup.getFromCountryCode())
				      && m.getToCountryCode().equals(ShiplinxConstants.COUNTRY_ANY)) {
				    if (bCus && m.getCustomerId().equals(markup.getCustomerId()))
				      return m;
				    /*if (m.getCustomerId().longValue() == 0)
				      return m;*/
				  }
				
				  // Rule # 5 - All Fields Match - From Country is "ANY"
				  if (m.getServiceId().equals(markup.getServiceId())
				      && m.getFromCountryCode().equals(ShiplinxConstants.COUNTRY_ANY)
				      && m.getToCountryCode().equals(markup.getToCountryCode()) && isWeightsMatched(m, markup)) {
				    if (bCus && m.getCustomerId().equals(markup.getCustomerId()))
				      return m;
				    /*if (m.getCustomerId().longValue() == 0)
				      return m;*/
				  }
				
				  // Rule # 6 - match without Weights - From Country is "ANY"
				  if (m.getServiceId().equals(markup.getServiceId()) && m.getFromWeight().floatValue() == 0
				      && m.getFromCountryCode().equals(ShiplinxConstants.COUNTRY_ANY)
				      && m.getToCountryCode().equals(markup.getToCountryCode())) {
				    if (bCus && m.getCustomerId().equals(markup.getCustomerId()))
				      return m;
				    /*if (m.getCustomerId().longValue() == 0)
				      return m;*/
				  }
				
				  // Rule # 7 - All Fields Match - From Country and To Country both are "ANY"
				  if (m.getServiceId().equals(markup.getServiceId())
				      && m.getFromCountryCode().equals(ShiplinxConstants.COUNTRY_ANY)
				      && m.getToCountryCode().equals(ShiplinxConstants.COUNTRY_ANY)
				      && isWeightsMatched(m, markup)) {
				    if (bCus && m.getCustomerId().equals(markup.getCustomerId()))
				      return m;
				    /*if (m.getCustomerId().longValue() == 0)
				      return m;*/
				  }
				
				  // Rule # 8 - match without Weights - From Country and To Country both are "ANY"
				  /*if (m.getServiceId().equals(markup.getServiceId())
					      && m.getFromCountryCode().equals(ShiplinxConstants.COUNTRY_ANY)
					      && m.getToCountryCode().equals(ShiplinxConstants.COUNTRY_ANY)
					      ) {
					    if (bCus && m.getCustomerId().equals(markup.getCustomerId()))
					      return m;
					    if (m.getCustomerId().longValue() == 0)
					      return m;
					  }*/
	      
	    	}
	    }

	    for (Markup m : mList) {
	    	
				      // Rule # 8 - All Fields Match with customerId 0
				      if (m.getServiceId().equals(markup.getServiceId()) && isCompareCountries(m, markup)
				            && isWeightsMatched(m, markup)) {
				          /*if (bCus && m.getCustomerId().equals(markup.getCustomerId()))
				            return m;*/
				          if (m.getCustomerId().longValue() == 0)
				            return m;
				        }
			
				        // Rule # 9 - match without Weights with customerId 0
				        if (m.getServiceId().equals(markup.getServiceId()) && isCompareCountries(m, markup)
				            && m.getFromWeight().floatValue() == 0) {
				          /*if (bCus && m.getCustomerId().equals(markup.getCustomerId()))
				            return m;*/
				          if (m.getCustomerId().longValue() == 0)
				            return m;
				        }
			
				        // Rule # 10 - All Fields Match - To Country is "ANY" with customerId 0
				        if (m.getServiceId().equals(markup.getServiceId())
				            && m.getFromCountryCode().equals(markup.getFromCountryCode())
				            && m.getToCountryCode().equals(ShiplinxConstants.COUNTRY_ANY)
				            && isWeightsMatched(m, markup)) {
				          /*if (bCus && m.getCustomerId().equals(markup.getCustomerId()))
				            return m;*/
				          if (m.getCustomerId().longValue() == 0)
				            return m;
				        }
			
				        // Rule # 11 - match without Weights and To Country is "ANY" with customerId 0
				        if (m.getServiceId().equals(markup.getServiceId()) && m.getFromWeight().floatValue() == 0
				            && m.getFromCountryCode().equals(markup.getFromCountryCode())
				            && m.getToCountryCode().equals(ShiplinxConstants.COUNTRY_ANY)) {
				          /*if (bCus && m.getCustomerId().equals(markup.getCustomerId()))
				            return m;*/
				          if (m.getCustomerId().longValue() == 0)
				            return m;
				        }
			
				        // Rule # 12 - All Fields Match - From Country is "ANY" with customerId 0
				        if (m.getServiceId().equals(markup.getServiceId())
				            && m.getFromCountryCode().equals(ShiplinxConstants.COUNTRY_ANY)
				            && m.getToCountryCode().equals(markup.getToCountryCode()) && isWeightsMatched(m, markup)) {
				          /*if (bCus && m.getCustomerId().equals(markup.getCustomerId()))
				            return m;*/
				          if (m.getCustomerId().longValue() == 0)
				            return m;
				        }
			
				        // Rule # 13 - match without Weights - From Country is "ANY" with customerId 0
				        if (m.getServiceId().equals(markup.getServiceId()) && m.getFromWeight().floatValue() == 0
				            && m.getFromCountryCode().equals(ShiplinxConstants.COUNTRY_ANY)
				            && m.getToCountryCode().equals(markup.getToCountryCode())) {
				          /*if (bCus && m.getCustomerId().equals(markup.getCustomerId()))
				            return m;*/
				          if (m.getCustomerId().longValue() == 0)
				            return m;
				        }
			
				        // Rule # 14 - All Fields Match - From Country and To Country both are "ANY" with customerId 0
				        if (m.getServiceId().equals(markup.getServiceId())
				            && m.getFromCountryCode().equals(ShiplinxConstants.COUNTRY_ANY)
				            && m.getToCountryCode().equals(ShiplinxConstants.COUNTRY_ANY)
				            && isWeightsMatched(m, markup)) {
				          /*if (bCus && m.getCustomerId().equals(markup.getCustomerId()))
				            return m;*/
				          if (m.getCustomerId().longValue() == 0)
				            return m;
				        }
				        
				     // Rule # 15 - match without Weights - From Country and To Country both are "ANY" with customerId 0
				        if (m.getServiceId().equals(markup.getServiceId())
					            && m.getFromCountryCode().equals(ShiplinxConstants.COUNTRY_ANY)
					            && m.getToCountryCode().equals(ShiplinxConstants.COUNTRY_ANY)
					            && isWeightsMatched(m, markup)) {
					          /*if (bCus && m.getCustomerId().equals(markup.getCustomerId()))
					            return m;*/
					          if (m.getCustomerId().longValue() == 0)
					            return m;
					     }
				        
				      
	    	}
	    
	    // Default
	    for (Markup m : mList) {
	      if (m.getServiceId().equals(markup.getServiceId()) && m.getFromWeight().floatValue() == 0
	          && m.getFromCountryCode().equals(ShiplinxConstants.COUNTRY_ANY)
	          && m.getToCountryCode().equals(ShiplinxConstants.COUNTRY_ANY)) {
	        if (bCus && m.getCustomerId().equals(markup.getCustomerId()))
	          return m;
	        if (m.getCustomerId().longValue() == 0)
	          return m;
	      }
	    }
	    return null;
  }

  private boolean isCompareCountries(Markup m, Markup markup) {
    // TODO Auto-generated method stub
    if (m.getFromCountryCode().equals(markup.getFromCountryCode())
        && m.getToCountryCode().equals(markup.getToCountryCode()))
      return true;
    return false;
  }

  private boolean isWeightsMatched(Markup m, Markup markup) {
    // TODO Auto-generated method stub
    if (m.getToWeight() == null || markup.getFromWeight() == null || markup.getToWeight() == null)
      return false;
    // if ( m.getFromWeight().doubleValue() >= markup.getFromWeight().doubleValue() &&
    // m.getToWeight().doubleValue() <= markup.getToWeight().doubleValue() )
    if (m.getFromWeight().doubleValue() <= markup.getFromWeight().doubleValue()
        && m.getToWeight().doubleValue() >= markup.getToWeight().doubleValue())
      return true;
    /*if (m.getFromWeight().doubleValue() >= markup.getFromWeight().doubleValue()
    		            && m.getToWeight().doubleValue() >= markup.getToWeight().doubleValue())
    		          return true;*/
    return false;
  }

  // private boolean isCompareWeights(Markup m, Markup markup) {
  // // TODO Auto-generated method stub
  // if (m.getToWeight() == null || markup.getFromWeight() == null || markup.getToWeight() == null)
  // return false;
  // // if ( m.getFromWeight().doubleValue() >= markup.getFromWeight().doubleValue() &&
  // // m.getToWeight().doubleValue() <= markup.getToWeight().doubleValue() )
  // if ( m.getToWeight().doubleValue() < markup.getFromWeight().doubleValue() &&
  // m.getToWeight().doubleValue() < markup.getToWeight().doubleValue() )
  // return false;
  // return true;
  // }

  public void copyCustomerMarkup(Long sourceCusId, Long targetCusId, long businessId) {
    // TODO Auto-generated method stub
    if (sourceCusId != null && targetCusId != null
        && sourceCusId.longValue() != targetCusId.longValue()) {
      this.markupDAO.deleteCustomerMarkup(targetCusId, businessId);
      Markup srcMarkup = new Markup();
      srcMarkup.setCustomerId(sourceCusId);
      List<Long> tempCutomerIds = new ArrayList<Long>();
                        tempCutomerIds.add(sourceCusId);
                       srcMarkup.setCustomerIds(tempCutomerIds);
      srcMarkup.setBusinessId(businessId);
      List<Markup> srcMarkupList = markupDAO.getMarkupListForCustomer(srcMarkup);
      if(srcMarkupList==null  || srcMarkupList.size()==0){
    	      	      	      	  srcMarkupList=BusinessFilterUtil.getMarkupListForCustomer(markupDAO,srcMarkup);	
    	      	      	           }
      if (srcMarkupList != null) {
        for (Markup m : srcMarkupList) {
          m.setCustomerId(targetCusId);
          markupDAO.addMarkup(m);
        }
      }

    }
  }

  public void disableOrEnableAllServicesForCustomerAndCarrier(long customerId, long carrierId,
      boolean disable) {
    // get All services for this carrier
    List<Service> services = carrierServiceDAO.getServicesForCarrier(carrierId);
    long[] serviceIds = new long[services.size()];
    int i = 0;
    for (Service s : services) {
      serviceIds[i] = s.getId();
      i++;
    }
    markupDAO.disableOrEnableServicesForCustomer(customerId, serviceIds, disable);

  }

  public List<CarrierChargeCode> searchCharges(CarrierChargeCode carrierChargeCode) {
    List<CarrierChargeCode> ccclist = new ArrayList<CarrierChargeCode>();
    ccclist = markupDAO.searchCharges(carrierChargeCode);
    return ccclist;
  }

  public void deleteCharges(long chargeId) {
    markupDAO.deleteCharges(chargeId);
  }

  public List<CarrierChargeCode> getCharges(CarrierChargeCode carrierChargeCode) {
    List<CarrierChargeCode> ccclist = new ArrayList<CarrierChargeCode>();
    ccclist = markupDAO.getCharges(carrierChargeCode);
    return ccclist;

  }

  public List<ChargeGroup> getChargeGroups() {
    List<ChargeGroup> cglist = new ArrayList<ChargeGroup>();
    cglist = markupDAO.getChargeGroups();
    return cglist;
  }

  public void addOrUpdateCharge(CarrierChargeCode carrierChargeCode, boolean add) {
    markupDAO.addOrUpdateCharge(carrierChargeCode, add);
  }

  public Service uploadRateTemplateFile(Service service, Long customerId, Long busId,
      String uploadFileName, InputStream uploadStream, boolean bOverwrite) {
    try {
      ltlServiceId = service.getId().longValue();
      ltlCustomerId = customerId.longValue();
      ltlBusId = busId.longValue();
      isOverwrite = bOverwrite;
      if (service != null && service.getCarrierId() > 0 && uploadFileName != null
          && uploadStream != null) {
        if (uploadFileName.toUpperCase().endsWith(ShiplinxConstants.CSV_EXTENSION)) {
          String uploadFolder = WebUtil.getProperty(Constants.SYSTEM_SCOPE, "BASE_PATH")
              + File.separator + Constants.RATE_TEMPLATE_FOLDER;
          File outFile = new File(uploadFolder + File.separator + ShiplinxConstants.IN_FOLDER
              + File.separator + uploadFileName);
          if (isWIPFile(outFile)) {
            // this file is currently under processing, WIP FILE
            return null;
          }
          OutputStream bos = null;
          int bytesRead = 0;
          byte[] buffer = new byte[8192];

          boolean bFirstTime = true;
          while ((bytesRead = uploadStream.read(buffer, 0, 8192)) != -1) {
            if (bFirstTime) {
              bFirstTime = false;
              bos = new FileOutputStream(outFile);
            }
            bos.write(buffer, 0, bytesRead);
          }
          bos.close();
          uploadStream.close();

          if (true) { // startNow
            new Thread() {
              public void run() {
                beginProcessing(ltlServiceId, ltlCustomerId, ltlBusId, isOverwrite);
              }
            }.start();
          }

          return service;

        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    return null;
  }

  private boolean isWIPFile(File file) {
    File f = new File(file.getAbsolutePath() + ShiplinxConstants.WIP_FILE_EXT);
    return f.exists();
  }

  private synchronized void beginProcessing(long ltlServicecId, long ltlCustomerId, long ltlBusId,
      boolean isOverwrite) {
    try {
      if (this.carrierServiceManager == null) {
        setCarrierServiceManager((CarrierServiceManager) MmrBeanLocator.getInstance().findBean(
            "carrierServiceManager"));
      }
      getCarrierServiceManager().uploadRates(ltlServiceId, ltlCustomerId, ltlBusId, isOverwrite);
    } catch (Exception e) {
      log.error("Error uploading rate file!", e);
      // throw e;
    }
  }

  public CarrierChargeCode getChargesByCode(CarrierChargeCode carrierChargeCode) {
    return markupDAO.getChargesByCode(carrierChargeCode);
  }

  // commented for issue 137
  /*
   * public List<LtlSkidRate> groupingLTLSkidRate(long serviceId, String fromZoneName, String
   * toZoneName) { return markupDAO.groupingLTLSkidRate(serviceId, fromZoneName, toZoneName); }
   */

  public List<LtlSkidRate> groupingLTLSkidRate(long serviceId, long maxLength, long maxWidth,
      long maxHeight, long maxWeight, String fromZoneName, String toZoneName) {
    return markupDAO.groupingLTLSkidRate(serviceId, maxLength, maxWidth, maxHeight, maxWeight,
        fromZoneName, toZoneName);

  }

  public Zone findZone(Long zoneStructureId, Address address) {
    return markupDAO.findZone(zoneStructureId, address);
  }

  public LtlPoundRate getPoundRate(LtlPoundRate poundRateTobeSearched, Double totalWeight) {
    return markupDAO.getPoundRate(poundRateTobeSearched, totalWeight);
  }

  @Override
  public List<LtlPoundRate> groupingLTLPoundRate(long serviceId, LtlPoundRate ltlPoundRate) {
    return markupDAO.groupingLTLPoundRate(serviceId, ltlPoundRate);
  }

@Override
public LtlSkidRate getSkidRate(LtlSkidRate skidRateTobeSearched) {
	// TODO Auto-generated method stub
	return null;
}
public boolean getMarkupListForCustomerAndCarrier(Markup markup) {

    return markupDAO.getMarkupListForCustomerAndCarrier(markup);
  }

  public List<Markup> getMarkupListForCustomerForFilter(Markup markup) {
    return markupDAO.getMarkupListForCustomerForFilter(markup);
  }
    public List<Markup> getMarkupList(Markup markup) {
	      // TODO Auto-generated method stub
	      if (markupDAO != null && markup != null) {
	         if (markup.getCustomerId() > 0) {
	          Long orgCustId = markup.getCustomerId();
	           List<Markup> custMarkupList = markupDAO.getMarkupList(markup);
	           if(custMarkupList==null  || custMarkupList.size()==0){
	        	   	        	   	        	   	        	   custMarkupList=BusinessFilterUtil.getMarkupList(markupDAO,markup);	
	        	   	        	   	        	   	              }
	           if (custMarkupList != null) {
	             markup.setCustomerId(0L);
	             List<Markup> defMarkupList = markupDAO.getMarkupList(markup);
	             if(defMarkupList==null  || defMarkupList.size()==0){
	            	 	            	 	            	 	            	 defMarkupList=BusinessFilterUtil.getMarkupList(markupDAO,markup);	
	            	 	            	 	            	 		              }
	             for (Markup m : custMarkupList) {
	               int n = findMarkup(defMarkupList, m);
	               if (n == -1) {
	                 // this situation should not happen
	                 defMarkupList.add(m);
	               } else {
	                 defMarkupList.remove(n); // There is already a customized markup, therfore remove
	                                          // default Markup
	                 defMarkupList.add(m); // add customized markup in the list
	               }
	            }
	             custMarkupList.clear();
	             markup.setCustomerId(orgCustId);
	             return defMarkupList;
	          }
	         }
	         List<Markup> mList=markupDAO.getMarkupList(markup);
	         	         	         	         if(mList==null  || mList.size()==0){
	         	         	         	        	 mList=BusinessFilterUtil.getMarkupList(markupDAO,markup);	
	         	         	         	              }
	         	         	         	         return  mList;
	       }
	       return null;
	    }
    
    public Markup findBaseMarkup(Markup markup){
    	Markup markup1= markupDAO.findBaseMarkup(markup);
    	    	    	    	
    	    	    	    	if(markup1==null && markupDAO!=null){
    	    	    	    		markup1=BusinessFilterUtil.findBaseMarkup(markupDAO,markup);
    	    	    	    				
    	    	    	    	}
    	    	    		  return markupDAO.findBaseMarkup(markup1);
    	  }

	@Override
	public boolean isCustomerMarkupByDisabled(long customerId) {
		return markupDAO.isCustomerMarkupByDisabled(customerId);
	}
	
	public List<Markup> getAllMarkupsForCustomer(Long customerId,long businessId){
		return markupDAO.getAllMarkupsForCustomer(customerId,businessId);
	}
	
	@Override
	public List<Zone> getOverallZones(String City, Long ZoneStructureId, String Country, String Province){
					return markupDAO.getOverallZones(City,ZoneStructureId,Country,Province);
		 		}
	
	public boolean getEshipCarriersbyCustomerId(String carrierScac,
			Long customerId) {
		return markupDAO.getEshipCarriersbyCustomerId(carrierScac, customerId);
	}

	public List<CarrierChargeCode> getChargesByCarrierIdAndGroupCode(
			long carrierId, long chargeGroupId) {
		return markupDAO.getChargesByCarrierIdAndGroupCode(carrierId,
				chargeGroupId);
	}

	/*public List<CarrierChargeCode> getChargesByChargeCodeAndCarrier(*/
	public CarrierChargeCode getChargesByChargeCodeAndCarrier(
			long carrierId, String chargeCode, long customerId) {
		return markupDAO.getChargesByChargeCodeAndCarrier(carrierId,
				chargeCode, customerId);
	}

	public Markup getUniqueMarkupUsingCost(Markup markup) {
		// TODO Auto-generated method stub
		if (markupDAO != null && markup != null) {
			List<Markup> mList = markupDAO
					.findMarkupListForUniqueMarkupUsingCostRange(markup);
			if (mList != null && mList.size() > 0) {
				return applyRules(mList, markup);
			}
		}
		return null;
	}
	public boolean isAllLevelMarkupDisabled(long businessId){
				return BusinessFilterUtil.isAllLevelMarkupDisabled(businessId);
			}
	@Override
	public BusinessMarkup getuniqueBusinessMarkup(BusinessMarkup businessMarkup) {
			businessMarkup = markupDAO.getuniqueBusinessMarkup(businessMarkup);
			return businessMarkup;
		}
	
	@Override
	public List<BusinessMarkup> getBusinessMarkupListForCustomer(BusinessMarkup businessMarkup) {
		List<BusinessMarkup> businessMarkups=markupDAO.getAllBusinessMarkupsForCustomer(businessMarkup);
			return businessMarkups;
		}
	
	@Override
	public BusinessMarkup getuniqueBusinessToBusinessMarkup(BusinessMarkup businessMarkup) {
		BusinessMarkup businessMarkup2= markupDAO.getuniqueBusinessToBusinessMarkup(businessMarkup);
		return businessMarkup2;
	}
	
	@Override
	public List<BusinessMarkup> getAllBusinessMarkups(BusinessMarkup businessMarkup) {
		List<BusinessMarkup> businessMarkups=markupDAO.getAllBusinessMarkups(businessMarkup);
		return getAdditionalInfoForBusinessMarkup(businessMarkups);
	}
	
	@Override
	public List<BusinessMarkup> getBusinessMarkups(BusinessMarkup businessMarkup) {
		BusinessMarkup businessMarkup2=new BusinessMarkup();
		if(businessMarkup.getBusinessId()!=null && businessMarkup.getBusinessId()>=0){
			businessMarkup2.setBusinessId(businessMarkup.getBusinessId());
		}
		if(businessMarkup.getBusinessToId()!=null && businessMarkup.getBusinessToId()>=0){
			businessMarkup2.setBusinessToId(businessMarkup.getBusinessToId());
		}
		if(businessMarkup.getCarrierId()!=null && businessMarkup.getCarrierId()>=0){
			businessMarkup2.setCarrierId(businessMarkup.getCarrierId());
		}
		if(businessMarkup.getServiceId()!=null && businessMarkup.getServiceId()>=0){
			businessMarkup2.setServiceId(businessMarkup.getServiceId());
		}
		if(businessMarkup.getCustomerId()!=null && businessMarkup.getCustomerId()>=0){
			businessMarkup2.setCustomerId(businessMarkup.getCustomerId());
		}
		if(!businessMarkup.getFromCountryCode().equalsIgnoreCase("ANY")){
			businessMarkup2.setFromCountryCode(businessMarkup.getFromCountryCode());
		}
		if(!businessMarkup.getToCountryCode().equalsIgnoreCase("ANY")){
			businessMarkup2.setToCountryCode(businessMarkup.getFromCountryCode());
		}
		//businessMarkup2.setVariable(businessMarkup.getVariable());
		List<BusinessMarkup> businessMarkups=markupDAO.getBusinessMarkups(businessMarkup2);
		return getAdditionalInfoForBusinessMarkup(businessMarkups);
		}

	/**
	 * This method is for updating the business mark-up list with required values
	 * for business mark-up screen 
	 * @param businessMarkups
	 * @return
	 */
	private List<BusinessMarkup> getAdditionalInfoForBusinessMarkup(List<BusinessMarkup> businessMarkups) {
		BusinessMarkup businessMarkup=new BusinessMarkup();
		if(businessMarkups!=null && businessMarkups.size()>0){
			for(BusinessMarkup businessMarkup2:businessMarkups){
				if(businessMarkup2.getBusinessId()!=null && businessMarkup2.getBusinessId()>0){
					if(businessMarkup2.getBusinessToId()!=null && businessMarkup2.getBusinessToId()>0){
						if(businessMarkup2.getServiceId()!=null && businessMarkup2.getServiceId()>0){
							if(businessMarkup2.getCustomerId()!=null && businessMarkup2.getCustomerId()>0){
									businessMarkup=markupDAO.getAdditionalInfoForBusinessMarkup(businessMarkup2);
									businessMarkup2.setCustomerBusName(businessMarkup.getCustomerBusName());
									businessMarkup2.setFromBusiness(businessMarkup.getFromBusiness());
									businessMarkup2.setToBusiness(businessMarkup.getToBusiness());
									businessMarkup2.setServiceName(businessMarkup.getServiceName());
									businessMarkup2.setCarrierName(businessMarkup.getCarrierName());
									continue;
								}
								businessMarkup=markupDAO.getAdditionalInfoForBusinessMarkup(businessMarkup2);
								businessMarkup2.setCustomerBusName("ANY");
								businessMarkup2.setFromBusiness(businessMarkup.getFromBusiness());
								businessMarkup2.setToBusiness(businessMarkup.getToBusiness());
								businessMarkup2.setServiceName(businessMarkup.getServiceName());
								businessMarkup2.setCarrierName(businessMarkup.getCarrierName());
								continue;
							}
						businessMarkup=markupDAO.getAdditionalInfoForBusinessMarkup(businessMarkup2);
						businessMarkup2.setFromBusiness(businessMarkup.getFromBusiness());
						businessMarkup2.setToBusiness(businessMarkup.getToBusiness());
						if(businessMarkup2.getCustomerId()!=null && businessMarkup2.getCustomerId()>0){
							businessMarkup2.setCustomerBusName(businessMarkup.getCustomerBusName());
						}else{
							businessMarkup2.setCustomerBusName("ANY");
						}
						businessMarkup2.setCarrierName("ANY");
						businessMarkup2.setServiceName("ANY");
						continue;
					}
					businessMarkup=markupDAO.getAdditionalInfoForBusinessMarkup(businessMarkup2);
					businessMarkup2.setFromBusiness(businessMarkup.getFromBusiness());
					businessMarkup2.setToBusiness("ANY");
					if(businessMarkup2.getCustomerId()!=null && businessMarkup2.getCustomerId()>0){
						businessMarkup2.setCustomerBusName(businessMarkup.getCustomerBusName());
					}else{
						businessMarkup2.setCustomerBusName("ANY");
					}
					businessMarkup2.setCarrierName("ANY");
					businessMarkup2.setServiceName("ANY");
					continue;
				}
			}	
		}
		return businessMarkups;
	}

	@Override
	public List<BusinessMarkup> addBusinessMarkup(BusinessMarkup businessMarkup) {
		List<BusinessMarkup> businessMarkups =new ArrayList<BusinessMarkup>();
		businessMarkup=frameBusinessMarkupRequest(businessMarkup);
		businessMarkups=markupDAO.addBusinessMarkup(businessMarkup);
		CustomerManager customerService = (CustomerManager) MmrBeanLocator.getInstance().findBean("customerService");
		if(customerService!=null){
			customerService.createCustomerForParentBusiness(businessMarkup);
		}
		return getAdditionalInfoForBusinessMarkup(businessMarkups);
	}
	
	/**
	 * This method is created to frame the business mark-up object to perform insert in collection
	 * @param businessMarkup
	 * @return
	 */
	public BusinessMarkup frameBusinessMarkupRequest(BusinessMarkup businessMarkup) {
		BusinessMarkup businessMarkup2=new BusinessMarkup();
		if(businessMarkup.getBusinessId()!=null && businessMarkup.getBusinessId()>=0){
			businessMarkup2.setBusinessId(businessMarkup.getBusinessId());
		}else{
			businessMarkup2.setBusinessId(0l);
		}
		if(businessMarkup.getBusinessToId()!=null && businessMarkup.getBusinessToId()>=0){
			businessMarkup2.setBusinessToId(businessMarkup.getBusinessToId());
		}else{
			businessMarkup2.setBusinessToId(0l);
		}
		if(businessMarkup.getCarrierId()!=null && businessMarkup.getCarrierId()>=0){
			businessMarkup2.setCarrierId(businessMarkup.getCarrierId());
		}else{
			businessMarkup2.setCarrierId(0l);
		}
		if(businessMarkup.getServiceId()!=null && businessMarkup.getServiceId()>=0){
			businessMarkup2.setServiceId(businessMarkup.getServiceId());
		}else{
			businessMarkup2.setServiceId(0l);
		}
		if(businessMarkup.getCustomerId()!=null && businessMarkup.getCustomerId()>=0){
			businessMarkup2.setCustomerId(businessMarkup.getCustomerId());
		}else{
			businessMarkup2.setCustomerId(0l);
		}
		if(businessMarkup.getMarkupFlat()!=null && businessMarkup.getMarkupFlat()>=0){
			businessMarkup2.setMarkupFlat(businessMarkup.getMarkupFlat());
		}else{
			businessMarkup2.setMarkupFlat(0.0);
		}
		if(businessMarkup.getMarkupPercentage()!=null){
			businessMarkup2.setMarkupPercentage(businessMarkup.getMarkupPercentage());
		}else{
			businessMarkup2.setMarkupPercentage(0);
		}
		if(businessMarkup.getFromRange()!=null && businessMarkup.getFromRange()>=0){
			businessMarkup2.setFromRange(businessMarkup.getFromRange());
		}
		else{
			businessMarkup2.setFromRange(0.0);
		}
		if(businessMarkup.getToRange()!=null && businessMarkup.getToRange()>=0){
			businessMarkup2.setToRange(businessMarkup.getToRange());
		}
		else{
			businessMarkup2.setToRange(0.0);
		}
		businessMarkup2.setFromCountryCode(businessMarkup.getFromCountryCode());
		businessMarkup2.setToCountryCode(businessMarkup.getToCountryCode());
		businessMarkup2.setType(businessMarkup.getType());
		businessMarkup2.setDisabled(0);
		businessMarkup2.setVariable(businessMarkup.getVariable());
		return businessMarkup2;
	}
	
	@Override
	public List<BusinessMarkup> deleteBusinessMarkup(BusinessMarkup businessMarkup) {
		List<BusinessMarkup> businessMarkups=markupDAO.deleteBusinessMarkup(businessMarkup);
		return getAdditionalInfoForBusinessMarkup(businessMarkups);	
	}
	
	@Override
	public void updateBusinessMarkup(BusinessMarkup businessMarkup) {
		List<BusinessMarkup> businessMarkup1=markupDAO.updateBusinessMarkup(businessMarkup);
	}

	@Override
	public List<Carrier> getListCarriers(String customerId, String businessId) {
		List<Carrier> carriers=new ArrayList<Carrier>();
		carriers=carrierServiceDAO.getListCarriers(customerId,businessId);
		if(carriers!=null && carriers.size()>0){
			return carriers;
		}
		return new ArrayList<Carrier>();
	}

	@Override
	public List<Service> getListCarrierServices(long carrierId, long customerId) {
		return this.carrierServiceDAO.getListCarrierServices(carrierId,customerId);
	}

	@Override
	public BusinessMarkup getUniqueCustomBusinessMarkup(BusinessMarkup businessMarkup) {
		BusinessMarkup businessMarkup2= markupDAO.getUniqueCustomBusinessMarkup(businessMarkup);
		return businessMarkup2;
	}

}