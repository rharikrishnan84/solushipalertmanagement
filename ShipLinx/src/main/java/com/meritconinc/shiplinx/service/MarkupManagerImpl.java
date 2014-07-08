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
        List<Markup> custMarkupList = markupDAO.getMarkupListForCustomer(markup);
        if (custMarkupList != null) {
          markup.setCustomerId(0L);
          List<Markup> defMarkupList = markupDAO.getMarkupListForCustomer(markup);
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
      return markupDAO.getMarkupListForCustomer(markup);
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
          || !m.getToCountryCode().equals(markup.getToCountryCode())
          || m.getToWeight().doubleValue() < markup.getFromWeight().doubleValue()) {
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
    if (markupDAO != null && markup != null) {
      List<Markup> mList = markupDAO.getMarkupListForUniqueMarkup(markup);
      if (mList != null && mList.size() > 0) {
        return applyRules(mList, markup);
      }
    }
    return null;
  }

  public Double applyMarkup(ShippingOrder shipment, Charge charge, boolean setShipmentMarkup) {
    // TODO Auto-generated method stub
    Markup markup = null;
    double amount = new Double(0.0);

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

  private Markup applyRules(List<Markup> mList, Markup markup) {
    // TODO Auto-generated method stub
    boolean bCus = false;
    if (markup.getCustomerId() != null && markup.getCustomerId().longValue() > 0) {
      // Customer based markup
      bCus = true;
    }
    for (Markup m : mList) {
      // Rule # 1 - All Fields Match
      if (m.getServiceId().equals(markup.getServiceId()) && isCompareCountries(m, markup)
          && isWeightsMatched(m, markup)) {
        if (bCus && m.getCustomerId().equals(markup.getCustomerId()))
          return m;
        if (m.getCustomerId().longValue() == 0)
          return m;
      }

      // Rule # 2 - match without Weights
      if (m.getServiceId().equals(markup.getServiceId()) && isCompareCountries(m, markup)
          && m.getFromWeight().floatValue() == 0) {
        if (bCus && m.getCustomerId().equals(markup.getCustomerId()))
          return m;
        if (m.getCustomerId().longValue() == 0)
          return m;
      }

      // Rule # 3 - All Fields Match - To Country is "ANY"
      if (m.getServiceId().equals(markup.getServiceId())
          && m.getFromCountryCode().equals(markup.getFromCountryCode())
          && m.getToCountryCode().equals(ShiplinxConstants.COUNTRY_ANY)
          && isWeightsMatched(m, markup)) {
        if (bCus && m.getCustomerId().equals(markup.getCustomerId()))
          return m;
        if (m.getCustomerId().longValue() == 0)
          return m;
      }

      // Rule # 4 - match without Weights and To Country is "ANY"
      if (m.getServiceId().equals(markup.getServiceId()) && m.getFromWeight().floatValue() == 0
          && m.getFromCountryCode().equals(markup.getFromCountryCode())
          && m.getToCountryCode().equals(ShiplinxConstants.COUNTRY_ANY)) {
        if (bCus && m.getCustomerId().equals(markup.getCustomerId()))
          return m;
        if (m.getCustomerId().longValue() == 0)
          return m;
      }

      // Rule # 5 - All Fields Match - From Country is "ANY"
      if (m.getServiceId().equals(markup.getServiceId())
          && m.getFromCountryCode().equals(ShiplinxConstants.COUNTRY_ANY)
          && m.getToCountryCode().equals(markup.getToCountryCode()) && isWeightsMatched(m, markup)) {
        if (bCus && m.getCustomerId().equals(markup.getCustomerId()))
          return m;
        if (m.getCustomerId().longValue() == 0)
          return m;
      }

      // Rule # 6 - match without Weights - From Country is "ANY"
      if (m.getServiceId().equals(markup.getServiceId()) && m.getFromWeight().floatValue() == 0
          && m.getFromCountryCode().equals(ShiplinxConstants.COUNTRY_ANY)
          && m.getToCountryCode().equals(markup.getToCountryCode())) {
        if (bCus && m.getCustomerId().equals(markup.getCustomerId()))
          return m;
        if (m.getCustomerId().longValue() == 0)
          return m;
      }

      // Rule # 7 - All Fields Match - From Country and To Country both are "ANY"
      if (m.getServiceId().equals(markup.getServiceId())
          && m.getFromCountryCode().equals(ShiplinxConstants.COUNTRY_ANY)
          && m.getToCountryCode().equals(ShiplinxConstants.COUNTRY_ANY)
          && isWeightsMatched(m, markup)) {
        if (bCus && m.getCustomerId().equals(markup.getCustomerId()))
          return m;
        if (m.getCustomerId().longValue() == 0)
          return m;
      }

      // Rule # 8 - match without Weights - From Country and To Country both are "ANY"
      // if (m.getServiceId().equals(markup.getServiceId()) && m.getFromWeight().floatValue() == 0
      // &&
      // m.getFromCountryCode().equals(ShiplinxConstants.COUNTRY_ANY) &&
      // m.getToCountryCode().equals(ShiplinxConstants.COUNTRY_ANY)) {
      // if (bCus && m.getCustomerId().equals(markup.getCustomerId()))
      // return m;
      // if (m.getCustomerId().longValue() == 0)
      // return m;
      // }
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
      srcMarkup.setBusinessId(businessId);
      List<Markup> srcMarkupList = markupDAO.getMarkupListForCustomer(srcMarkup);
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

}
