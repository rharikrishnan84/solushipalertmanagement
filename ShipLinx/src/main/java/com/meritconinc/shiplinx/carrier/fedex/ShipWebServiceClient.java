package com.meritconinc.shiplinx.carrier.fedex;

import static com.meritconinc.shiplinx.utils.ShiplinxConstants.USER_VALUE_1;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import org.apache.axis.types.NonNegativeInteger;
import org.apache.axis.types.PositiveInteger;
import org.apache.log4j.Logger;

import com.fedex.ship.stub.Address;
import com.fedex.ship.stub.CodCollectionType;
import com.fedex.ship.stub.CodDetail;
import com.fedex.ship.stub.Commodity;
import com.fedex.ship.stub.Contact;
import com.fedex.ship.stub.ContactAndAddress;
import com.fedex.ship.stub.CustomerReference;
import com.fedex.ship.stub.CustomerReferenceType;
import com.fedex.ship.stub.CustomsClearanceDetail;
import com.fedex.ship.stub.Dimensions;
import com.fedex.ship.stub.DropoffType;
import com.fedex.ship.stub.EdtRequestType;
import com.fedex.ship.stub.ExportDetail;
import com.fedex.ship.stub.ExpressFreightDetail;
import com.fedex.ship.stub.HazardousCommodityOptionType;
import com.fedex.ship.stub.HoldAtLocationDetail;
import com.fedex.ship.stub.InternationalDocumentContentType;
import com.fedex.ship.stub.LabelFormatType;
import com.fedex.ship.stub.LabelSpecification;
import com.fedex.ship.stub.LabelStockType;
import com.fedex.ship.stub.LinearUnits;
import com.fedex.ship.stub.Money;
import com.fedex.ship.stub.PackageSpecialServiceType;
import com.fedex.ship.stub.PackageSpecialServicesRequested;
import com.fedex.ship.stub.PackagingType;
import com.fedex.ship.stub.Party;
import com.fedex.ship.stub.Payment;
import com.fedex.ship.stub.PaymentType;
import com.fedex.ship.stub.Payor;
import com.fedex.ship.stub.ProcessShipmentReply;
import com.fedex.ship.stub.ProcessShipmentRequest;
import com.fedex.ship.stub.RateRequestType;
import com.fedex.ship.stub.RequestedPackageDetailType;
import com.fedex.ship.stub.RequestedPackageLineItem;
import com.fedex.ship.stub.RequestedShipment;
import com.fedex.ship.stub.ServiceType;
import com.fedex.ship.stub.ShipPortType;
import com.fedex.ship.stub.ShipServiceLocator;
import com.fedex.ship.stub.ShipmentSpecialServiceType;
import com.fedex.ship.stub.ShipmentSpecialServicesRequested;
import com.fedex.ship.stub.ShippingDocumentImageType;
import com.fedex.ship.stub.SignatureOptionDetail;
import com.fedex.ship.stub.SignatureOptionType;
import com.fedex.ship.stub.TransactionDetail;
import com.fedex.ship.stub.VersionId;
import com.fedex.ship.stub.Weight;
import com.fedex.ship.stub.WeightUnits;
import com.meritconinc.mmr.model.security.User;
import com.meritconinc.mmr.service.UserService;
import com.meritconinc.mmr.utilities.StringUtil;
import com.meritconinc.mmr.utilities.security.UserUtil;
import com.meritconinc.shiplinx.carrier.utils.FedExException;
import com.meritconinc.shiplinx.model.CustomerCarrier;
import com.meritconinc.shiplinx.model.CustomsInvoiceProduct;
import com.meritconinc.shiplinx.model.ShippingOrder;
import com.meritconinc.shiplinx.utils.ShiplinxConstants;

public class ShipWebServiceClient extends FedExRequestHelper {
  private UserService userService;
  private static Logger logger = Logger.getLogger(ShipWebServiceClient.class);
  ProcessShipmentRequest request = new ProcessShipmentRequest();
  String currency = null;

  /**
   * @return the currency
   */
  public String getCurrency() {
    return currency;
  }

  /**
   * @param currency
   *          the currency to set
   */
  public void setCurrency(String currency) {
    this.currency = currency;
  }

  public ShipWebServiceClient(ShippingOrder order, CustomerCarrier customerCarrier) {
    super(order, customerCarrier);
  }

  public ProcessShipmentRequest buildRequest() {

    authenticationInfo();
    request.setClientDetail(createShipClientDetail());
    request.setWebAuthenticationDetail(createShipWebAuthenticationDetail());

    // set transactionDetail
    TransactionDetail transactionDetail = new TransactionDetail();
    transactionDetail.setCustomerTransactionId("java sample - Rate Request"); // The client will get
                                                                              // the same value back
                                                                              // in the response
    request.setTransactionDetail(transactionDetail);

    // set versionId
    VersionId versionId = new VersionId("ship", 9, 0, 0);
    request.setVersion(versionId);
    // set order details
    setOrder();
    return request;

  }

  public ProcessShipmentReply sendRequest(ProcessShipmentRequest request, String SequenceNumber) {
    this.request = request;
    request.getRequestedShipment().getRequestedPackageLineItems(0)
        .setSequenceNumber(new PositiveInteger(SequenceNumber));
    logger.debug("Ship request for service: "
        + request.getRequestedShipment().getServiceType().getValue() + ", using account #: "
        + this.request.getClientDetail().getAccountNumber());
    ProcessShipmentReply reply = null;
    try {
      // Initialize the service
      ShipServiceLocator service;
      ShipPortType port;

      service = new ShipServiceLocator();
      updateEndPoint(service);
      port = service.getShipServicePort();
      // This is the call to the web service passing in a RateRequest and returning a RateReply
      reply = port.processShipment(request); // Service call
    } catch (Exception e) {
      e.printStackTrace();
      logger.error("error:" + e.getMessage(), e);
    }

    return reply;

  }

  private static void updateEndPoint(ShipServiceLocator serviceLocator) {

    String endPoint;
    if (ShiplinxConstants.isTestMode())
      endPoint = TEST_URL_RATE;
    else
      endPoint = LIVE_URL_SERVICE;
    if (endPoint != null) {
      serviceLocator.setShipServicePortEndpointAddress(endPoint);
    }
  }

  public void setOrder() {

    try {

      RequestedShipment requestedShipment = new RequestedShipment();
      Calendar c = Calendar.getInstance();
      c.setTime(order.getScheduledShipDate());
      requestedShipment.setShipTimestamp(c);
      requestedShipment.setDropoffType(DropoffType.REGULAR_PICKUP);

      setServiceType(order, requestedShipment);

      if (order.getPackageTypeId().getPackageTypeId() == ShiplinxConstants.PACKAGE_TYPE_PACKAGE
          || order.getPackageTypeId().getPackageTypeId() == ShiplinxConstants.PACKAGE_TYPE_PALLET) {
        requestedShipment.setPackagingType(PackagingType.YOUR_PACKAGING);
      } else if (order.getPackageTypeId().getPackageTypeId() == ShiplinxConstants.PACKAGE_TYPE_ENVELOPE) {
        requestedShipment.setPackagingType(PackagingType.FEDEX_ENVELOPE);
      } else if (order.getPackageTypeId().getPackageTypeId() == ShiplinxConstants.PACKAGE_TYPE_PAK) {
        requestedShipment.setPackagingType(PackagingType.FEDEX_PAK);
      }
      if(order.getFromAddress().getCountryCode().equals("US")&&(order.getToAddress().getCountryCode().equals("US"))){
    	  setCurrency(FedExConstants.CURRENCY_TYPE_VALUE_USD);
		}
      else if (customerCarrier.getCountry().equalsIgnoreCase(ShiplinxConstants.CANADA))
        setCurrency(FedExConstants.CURRENCY_TYPE_VALUE_CAD);
      else if (customerCarrier.getCountry().equalsIgnoreCase(ShiplinxConstants.US))
        setCurrency(FedExConstants.CURRENCY_TYPE_VALUE_USD);

      // setting ship from info
      Party shipper = new Party();
      Contact contactShip = new Contact();
      contactShip.setCompanyName(order.getFromAddress().getAbbreviationName());
      contactShip.setPhoneNumber(order.getFromAddress().getPhoneNo());
      shipper.setContact(contactShip);
      Address shipperAddress = new Address(); // Origin information
      shipperAddress.setStateOrProvinceCode(order.getFromAddress().getProvinceCode());
      shipperAddress.setPostalCode(order.getFromAddress().getPostalCode());
      shipperAddress.setCountryCode(order.getFromAddress().getCountryCode());
      shipperAddress.setCity(order.getFromAddress().getCity());
      String[] shipperStreetLineArr = new String[2];
      shipperStreetLineArr[0] = order.getFromAddress().getAddress1();
      if(order.getFromAddress().getAddress2()!= null && !order.getFromAddress().getAddress2().isEmpty()){
      shipperStreetLineArr[1] = order.getFromAddress().getAddress2();
      }else{
    	  shipperStreetLineArr[1]="";
      }
      // shipperAddress.set
      shipperAddress.setStreetLines(shipperStreetLineArr);
      shipper.setAddress(shipperAddress);
      requestedShipment.setShipper(shipper);
      // setting ship to info
      Party recipient = new Party();
      Contact contactRecip = new Contact();
      contactRecip.setCompanyName(order.getToAddress().getAbbreviationName());
      contactRecip.setPhoneNumber(order.getToAddress().getPhoneNo());
      contactRecip.setPersonName(order.getToAddress().getContactName());
      recipient.setContact(contactRecip);
      Address recipientAddress = new Address(); // Destination information
      recipientAddress.setStateOrProvinceCode(order.getToAddress().getProvinceCode());
      recipientAddress.setPostalCode(order.getToAddress().getPostalCode());
      recipientAddress.setCountryCode(order.getToAddress().getCountryCode());
      recipientAddress.setCity(order.getToAddress().getCity());
      String[] recipientStreetLineArr = new String[2];
      recipientStreetLineArr[0] = order.getToAddress().getAddress1();
      if(order.getToAddress().getAddress2()!=null && !order.getToAddress().getAddress2().isEmpty()){
      recipientStreetLineArr[1] = order.getToAddress().getAddress2();
      }else{
    	  recipientStreetLineArr[1]="";
      }
      // shipperAddress.set
      recipientAddress.setStreetLines(recipientStreetLineArr);
      if (order.getToAddress().isResidential())
        recipientAddress.setResidential(true);

      recipient.setAddress(recipientAddress);
      requestedShipment.setRecipient(recipient);
      // setting payment type as third party
      Payment shippingChargesPayment = new Payment();

      if (!StringUtil.isEmpty(order.getBillToAccountNum())) {
        shippingChargesPayment.setPaymentType(PaymentType.THIRD_PARTY);
        shippingChargesPayment.setPayor(new Payor());
        shippingChargesPayment.getPayor().setAccountNumber(order.getBillToAccountNum());
        shippingChargesPayment.getPayor().setCountryCode(order.getBillToAccountCountry());
      } else {
        shippingChargesPayment.setPaymentType(PaymentType.SENDER);
        shippingChargesPayment.setPayor(new Payor());
        shippingChargesPayment.getPayor().setAccountNumber(getAccountNumber());
        shippingChargesPayment.getPayor().setCountryCode(customerCarrier.getCountry());
        order.setBillToAccountNum(getAccountNumber());
      }

      requestedShipment.setShippingChargesPayment(shippingChargesPayment);

      if (order.isShipmentInternational()) {
        if (order.getPackageTypeId().getPackageTypeId() == ShiplinxConstants.PACKAGE_TYPE_ENVELOPE
            || order.getPackageTypeId().getPackageTypeId() == ShiplinxConstants.PACKAGE_TYPE_PAK
            || order.getCustomsInvoice() == null || order.isCustomsInvoiceRequired() == false) {
          CustomsClearanceDetail customs = new CustomsClearanceDetail(); // International details
          customs.setDutiesPayment(new Payment());
          customs.getDutiesPayment().setPaymentType(PaymentType.SENDER);
          customs.getDutiesPayment().setPayor(new Payor());
          customs.getDutiesPayment().getPayor().setAccountNumber(getAccountNumber());
          customs.getDutiesPayment().getPayor().setCountryCode(customerCarrier.getCountry());
          customs.setCustomsValue(new Money());
          customs.getCustomsValue().setAmount(new java.math.BigDecimal(1.00));
          customs.getCustomsValue().setCurrency(getCurrency());
          customs.setDocumentContent(InternationalDocumentContentType.DOCUMENTS_ONLY);
          Commodity[] commodities = new Commodity[] { new Commodity() }; // Commodity details
          commodities[0].setNumberOfPieces(new NonNegativeInteger("1"));
          commodities[0].setDescription("Document/Pak");
          commodities[0].setCountryOfManufacture(order.getFromAddress().getCountryCode());
          commodities[0].setWeight(new Weight());
          commodities[0].getWeight().setValue(new BigDecimal(1.0));
          commodities[0].getWeight().setUnits(WeightUnits.LB);
          commodities[0].setQuantity(new NonNegativeInteger("1"));
          commodities[0].setQuantityUnits("EA");
          commodities[0].setUnitPrice(new Money());
          commodities[0].getUnitPrice().setAmount(new java.math.BigDecimal(1.000000));
          commodities[0].getUnitPrice().setCurrency(getCurrency());
          commodities[0].setCustomsValue(new Money());
          commodities[0].getCustomsValue().setAmount(new java.math.BigDecimal(1.000000));
          commodities[0].getCustomsValue().setCurrency(getCurrency());
          commodities[0].setCountryOfManufacture(order.getFromAddress().getCountryCode());
          // commodities[0].setHarmonizedCode("0000.");
          customs.setCommodities(commodities);
          requestedShipment.setCustomsClearanceDetail(customs);
        } else // this is a package and customer has entered customs invoice information
        {
          CustomsClearanceDetail customs = new CustomsClearanceDetail(); // International details
          customs.setDutiesPayment(new Payment());

          String billTo = order.getCustomsInvoice().getBillTo();

          customs.setCustomsValue(new Money());
          List<CustomsInvoiceProduct> products = order.getCustomsInvoice().getProducts();
          Commodity[] commodities = new Commodity[products.size()];
          Iterator<CustomsInvoiceProduct> i = products.iterator();

          float customsAmount = 0f;
          int current = 0;
          while (i.hasNext()) {
            commodities[current] = new Commodity();
            CustomsInvoiceProduct p = (CustomsInvoiceProduct) i.next();
            commodities[current].setNumberOfPieces(new NonNegativeInteger(String.valueOf(p
                .getProductQuantity())));
            commodities[current].setDescription(p.getProductDesc());
            commodities[current].setCountryOfManufacture(p.getProductOrigin());
            commodities[current].setWeight(new Weight());
            commodities[current].getWeight().setValue(new BigDecimal(0.1));
            commodities[current].getWeight().setUnits(WeightUnits.LB);
            commodities[current].setQuantity(new NonNegativeInteger((new Integer(p
                .getProductQuantity()).toString())));
            commodities[current].setQuantityUnits("EA");
            commodities[current].setUnitPrice(new Money());
            commodities[current].getUnitPrice().setAmount(
                new java.math.BigDecimal(p.getProductUnitPrice()));
            commodities[current].getUnitPrice()
                .setCurrency(order.getCustomsInvoice().getCurrency());
            commodities[current].setCustomsValue(new Money());
            commodities[current].getCustomsValue().setAmount(
                new java.math.BigDecimal(p.getProductUnitPrice() * p.getProductQuantity()));
            customsAmount += commodities[current].getCustomsValue().getAmount().floatValue();
            commodities[current].getCustomsValue().setCurrency(getCurrency());
            commodities[current].setHarmonizedCode(p.getProductHC());
            current++;
          }
          customs.setCommodities(commodities);
          customs.getCustomsValue().setAmount(new BigDecimal(customsAmount));
          customs.getCustomsValue().setCurrency(getCurrency());
          customs.setDocumentContent(InternationalDocumentContentType.NON_DOCUMENTS);

          requestedShipment.setCustomsClearanceDetail(customs);
        }

        if (order.getCustomsInvoice() != null) {
          if (!StringUtil.isEmpty(order.getCustomsInvoice().getExportData())) {
            requestedShipment.getCustomsClearanceDetail().setExportDetail(new ExportDetail());
            requestedShipment.getCustomsClearanceDetail().getExportDetail()
                .setExportComplianceStatement(order.getCustomsInvoice().getExportData());
          }

          if (!StringUtil.isEmpty(order.getCustomsInvoice().getBillTo())) {
            if (order.getCustomsInvoice().getBillTo()
                .equalsIgnoreCase(ShiplinxConstants.BILL_TO_SHIPPER)) {
              requestedShipment.getCustomsClearanceDetail().getDutiesPayment()
                  .setPaymentType(PaymentType.SENDER);
              requestedShipment.getCustomsClearanceDetail().getDutiesPayment()
                  .setPayor(new Payor());
              requestedShipment.getCustomsClearanceDetail().getDutiesPayment().getPayor()
                  .setAccountNumber(customerCarrier.getAccountNumber1());
              order.getCustomsInvoice().setBillToAccountNum(customerCarrier.getAccountNumber1());
              requestedShipment.getCustomsClearanceDetail().getDutiesPayment().getPayor()
                  .setCountryCode(customerCarrier.getCountry());
            } else if (!StringUtil.isEmpty(order.getCustomsInvoice().getBillToAccountNum())
                || order.getCustomsInvoice().getBillTo()
                    .equalsIgnoreCase(ShiplinxConstants.BILL_TO_THIRD_PARTY)) {
              requestedShipment.getCustomsClearanceDetail().getDutiesPayment()
                  .setPaymentType(PaymentType.THIRD_PARTY);
              requestedShipment.getCustomsClearanceDetail().getDutiesPayment()
                  .setPayor(new Payor());
              requestedShipment.getCustomsClearanceDetail().getDutiesPayment().getPayor()
                  .setAccountNumber(order.getCustomsInvoice().getBillToAccountNum());
              requestedShipment.getCustomsClearanceDetail().getDutiesPayment().getPayor()
                  .setCountryCode(order.getCustomsInvoice().getBillToAddress().getCountryCode());
            } else { // receiver
              requestedShipment.getCustomsClearanceDetail().getDutiesPayment()
                  .setPaymentType(PaymentType.RECIPIENT);
            }
          }

        }
      }

      LabelSpecification labelSpecification = new LabelSpecification(); // Label specification
      labelSpecification.setImageType(ShippingDocumentImageType.PNG);// Image types PDF, PNG, DPL,
                                                                     // ...
      labelSpecification.setLabelFormatType(LabelFormatType.COMMON2D);
      /*
       * Code to fix fedx label size, addedd by pandiyaraja.r@mitosistech.com
       */

      User user = UserUtil.getMmrUser();
      if (user != null) {
        logger.debug("Test Fedx Label");
        // logger.debug(user.getPreferredLabelSize().trim());
        logger.debug(LabelStockType.value1);
        if (user.getPreferredLabelSize() != null
            && user.getPreferredLabelSize().trim().equals(USER_VALUE_1)) {
          labelSpecification.setLabelStockType(LabelStockType.value1);
        } else {
          labelSpecification.setLabelStockType(LabelStockType.value6);
        }
      }
      /*
       * fedx label code end
       */
      requestedShipment.setLabelSpecification(labelSpecification);
      RateRequestType[] rrt = new RateRequestType[] { RateRequestType.ACCOUNT }; // Rate types
                                                                                 // requested LIST,
                                                                                 // MULTIWEIGHT, ...
      requestedShipment.setRateRequestTypes(rrt);
      requestedShipment.setPackageCount(new NonNegativeInteger(Integer.toString(order.getPackages()
          .size())));
      requestedShipment.setTotalWeight(new Weight());
      double totalWeight = 0;
      double totalCOD = 0;
      for(com.meritconinc.shiplinx.model.Package p:order.getPackages())
      {
      	totalCOD += p.getCodAmount().floatValue();
      	totalWeight=totalWeight + p.getWeight().floatValue();
      }
      requestedShipment.getTotalWeight().setUnits(WeightUnits.LB);
      requestedShipment.getTotalWeight().setValue(new BigDecimal(totalWeight));
      requestedShipment.setPackageDetail(RequestedPackageDetailType.INDIVIDUAL_PACKAGES);
      requestedShipment.setEdtRequestType(EdtRequestType.ALL);

      RequestedPackageLineItem[] rplis = new RequestedPackageLineItem[1];

      for (int rp = 0; rp < rplis.length; rp++) {

        List<com.fedex.ship.stub.PackageSpecialServiceType> psstList = new ArrayList<com.fedex.ship.stub.PackageSpecialServiceType>();

        RequestedPackageLineItem rpli = new RequestedPackageLineItem();
    	int sequenceNumber = rp+1;
    	rpli.setSequenceNumber(new org.apache.axis.types.PositiveInteger(Integer.toString(sequenceNumber)));
    	rpli.setWeight(new Weight(WeightUnits.LB, order.getPackages().get(rp).getWeight()));
	    if(order.getPackageTypeId().getPackageTypeId()==ShiplinxConstants.PACKAGE_TYPE_ENVELOPE)
	    	rpli.setWeight(new Weight(WeightUnits.LB, new BigDecimal(0.5)));
    	rpli.setInsuredValue(new Money(getCurrency(), new BigDecimal(order.getPackages().get(rp).getInsuranceAmount().doubleValue())));	
	    rpli.setDimensions(new Dimensions(new NonNegativeInteger(Integer.toString(order.getPackages().get(rp).getLength().intValue())), new NonNegativeInteger(Integer.toString(order.getPackages().get(rp).getWidth().intValue())), new NonNegativeInteger(Integer.toString(order.getPackages().get(rp).getHeight().intValue())), LinearUnits.IN));

	    
 		PackageSpecialServicesRequested pssr= new PackageSpecialServicesRequested();

        // dangerous goods
        if (order.getDangerousGoods() != 0) {
          psstList.add(PackageSpecialServiceType.DANGEROUS_GOODS);
          logger.debug("---getDangerousGoods------" + order.getDangerousGoods());
          com.fedex.ship.stub.DangerousGoodsDetail dgDetail = new com.fedex.ship.stub.DangerousGoodsDetail();
          dgDetail.setAccessibility(com.fedex.ship.stub.DangerousGoodsAccessibilityType.ACCESSIBLE);
          HazardousCommodityOptionType[] hcotype = new HazardousCommodityOptionType[1];
          // How to map the DG values to the FedEx Hazardous Commodity Option Types
          if (order.getDangerousGoods() < ShiplinxConstants.DG_FULLY_REGULATED)
            hcotype[0] = HazardousCommodityOptionType.SMALL_QUANTITY_EXCEPTION;
          else
            hcotype[0] = HazardousCommodityOptionType.REPORTABLE_QUANTITIES;
          dgDetail.setOptions(hcotype);
          pssr.setDangerousGoodsDetail(dgDetail);
        }

        // signature Required
        psstList.add(PackageSpecialServiceType.SIGNATURE_OPTION);
        SignatureOptionDetail sod = new SignatureOptionDetail();
        logger.debug("SIG OPTION IS : " + order.getSignatureRequired());
        if (order.getSignatureRequired() == 1)
          sod.setOptionType(SignatureOptionType.NO_SIGNATURE_REQUIRED);
        else if (order.getSignatureRequired() == 2)
          sod.setOptionType(SignatureOptionType.DIRECT);
        else if (order.getSignatureRequired() == 3)
          sod.setOptionType(SignatureOptionType.INDIRECT);
        else if (order.getSignatureRequired() == 4)
          sod.setOptionType(SignatureOptionType.ADULT);
        pssr.setSignatureOptionDetail(sod);

        PackageSpecialServiceType[] psst = new PackageSpecialServiceType[psstList.size()];
        pssr.setSpecialServiceTypes(psstList.toArray(psst));

        rpli.setSpecialServicesRequested(pssr);

        if (order.getReferenceCode() != null && order.getReferenceCode().length() > 0) {
          CustomerReference cr = new CustomerReference();
          cr.setCustomerReferenceType(CustomerReferenceType.CUSTOMER_REFERENCE);
          cr.setValue(order.getReferenceCode());
          CustomerReference[] crs = new CustomerReference[1];
          crs[0] = cr;
          rpli.setCustomerReferences(crs);
        }

        rplis[rp] = rpli;

      }
      requestedShipment.setRequestedPackageLineItems(rplis);

      ShipmentSpecialServicesRequested ssr = new ShipmentSpecialServicesRequested();
      List<ShipmentSpecialServiceType> sstList = new ArrayList<ShipmentSpecialServiceType>();
      logger.debug("---codValue-----------------" + totalCOD);

      if (totalCOD > 0) {
        sstList.add(ShipmentSpecialServiceType.COD);
        ssr.setCodDetail(new CodDetail());
        ssr.getCodDetail().setCodCollectionAmount(new Money("USD", new BigDecimal(totalCOD)));
        ssr.getCodDetail().setCollectionType(CodCollectionType.ANY);
      }

      logger.debug("---shippingOrder.getHoldForPickupRequired()---------"
          + order.getHoldForPickupRequired());

      if (order.getHoldForPickupRequired()) {
        logger.debug("---shippingOrder.getHoldForPickupRequired()---------"
            + order.getHoldForPickupRequired());
        sstList.add(ShipmentSpecialServiceType.HOLD_AT_LOCATION);
        HoldAtLocationDetail holdAtLocationDetail = new HoldAtLocationDetail();
        ContactAndAddress contactAndAddress = new ContactAndAddress();
        contactAndAddress.setAddress(recipientAddress);
        holdAtLocationDetail.setLocationContactAndAddress(contactAndAddress);
        holdAtLocationDetail.setPhoneNumber(order.getToAddress().getPhoneNo());
        ssr.setHoldAtLocationDetail(holdAtLocationDetail);
      }

      if (order.isToTailgate() != null && order.isToTailgate().booleanValue()) {
        sstList.add(ShipmentSpecialServiceType.LIFTGATE_DELIVERY);
      }

      if (order.isFromTailgate() != null && order.isFromTailgate().booleanValue()) {
        sstList.add(ShipmentSpecialServiceType.LIFTGATE_PICKUP);
      }

      if (order.getSatDelivery() != null && order.getSatDelivery().booleanValue()) {
        sstList.add(ShipmentSpecialServiceType.SATURDAY_DELIVERY);
      }
      // if(order.getDangerousGoods()!= null && order.getDangerousGoods()>0){
      // logger.debug("---getDangerousGoods------"+order.getDangerousGoods());
      // sstList.add(ShipmentSpecialServiceType.DANGEROUS_GOODS);
      // }
      ShipmentSpecialServiceType[] sst = new ShipmentSpecialServiceType[sstList.size()];
      ssr.setSpecialServiceTypes(sstList.toArray(sst));

      if (sstList.size() > 0)
        requestedShipment.setSpecialServicesRequested(ssr);

      // for express freight
      if (order.getService().getCode().equalsIgnoreCase(SERVICE_INTERNATIONAL_PRIORITY_FREIGHT)
          || order.getService().getCode().equalsIgnoreCase(SERVICE_INTERNATIONAL_ECONOMY_FREIGHT)) {
        ExpressFreightDetail efd = new ExpressFreightDetail();
        efd.setShippersLoadAndCount(new PositiveInteger("1"));
        requestedShipment.setExpressFreightDetail(efd);
      }

      request.setRequestedShipment(requestedShipment);

    } catch (Exception e) {
      throw new FedExException("Error setting order for request!");
    }
  }

  private void setServiceType(ShippingOrder order, RequestedShipment requestedShipment) {
    if (order.getService().getCode().equalsIgnoreCase(SERVICE_PRIORITY_OVERNIGHT)) {
      if (order.isShipmentInternational())
        requestedShipment.setServiceType(ServiceType.INTERNATIONAL_PRIORITY);
      else
        requestedShipment.setServiceType(ServiceType.PRIORITY_OVERNIGHT);
    }
    if (order.getService().getCode().equalsIgnoreCase(SERVICE_INTERNATIONAL_PRIORITY))
      requestedShipment.setServiceType(ServiceType.INTERNATIONAL_PRIORITY);
    if (order.getService().getCode().equalsIgnoreCase(SERVICE_FIRST_OVERNIGHT))
      requestedShipment.setServiceType(ServiceType.FIRST_OVERNIGHT);
    if (order.getService().getCode().equalsIgnoreCase(SERVICE_FEDEX_GROUND))
      requestedShipment.setServiceType(ServiceType.FEDEX_GROUND);
    if (order.getService().getCode().equalsIgnoreCase(SERVICE_STANDARD_OVERNIGHT))
      requestedShipment.setServiceType(ServiceType.STANDARD_OVERNIGHT);
    if (order.getService().getCode().equalsIgnoreCase(SERVICE_FEDEX_2_DAY))
      requestedShipment.setServiceType(ServiceType.FEDEX_2_DAY);
    if (order.getService().getCode().equalsIgnoreCase(SERVICE_FEDEX_EXPRESS_SAVER))
      requestedShipment.setServiceType(ServiceType.FEDEX_EXPRESS_SAVER);
    if (order.getService().getCode().equalsIgnoreCase(SERVICE_INTERNATIONAL_PRIORITY_FREIGHT))
      requestedShipment.setServiceType(ServiceType.INTERNATIONAL_PRIORITY_FREIGHT);
    if (order.getService().getCode().equalsIgnoreCase(SERVICE_INTERNATIONAL_ECONOMY_FREIGHT))
      requestedShipment.setServiceType(ServiceType.INTERNATIONAL_ECONOMY_FREIGHT);
    if (order.getService().getCode().equalsIgnoreCase(SERVICE_FEDEX_2_DAY_FREIGHT))
      requestedShipment.setServiceType(ServiceType.FEDEX_2_DAY_FREIGHT);
    if (order.getService().getCode().equalsIgnoreCase(SERVICE_INTERNATIONAL_GROUND))
      requestedShipment.setServiceType(ServiceType.INTERNATIONAL_GROUND);
    if (order.getService().getCode().equalsIgnoreCase(SERVICE_EUROPE_FIRST_INTERNATIONAL_PRIORITY))
      requestedShipment.setServiceType(ServiceType.EUROPE_FIRST_INTERNATIONAL_PRIORITY);
    if (order.getService().getCode().equalsIgnoreCase(SERVICE_GROUND_HOME_DELIVERY))
      requestedShipment.setServiceType(ServiceType.GROUND_HOME_DELIVERY);
    if (order.getService().getCode().equalsIgnoreCase(SERVICE_INTERNATIONAL_ECONOMY))
      requestedShipment.setServiceType(ServiceType.INTERNATIONAL_ECONOMY);
    if (order.getService().getCode().equalsIgnoreCase(SERVICE_INTERNATIONAL_FIRST))
      requestedShipment.setServiceType(ServiceType.INTERNATIONAL_FIRST);
    if (order.getService().getCode().equalsIgnoreCase(SERVICE_FEDEX_FREIGHT))
      requestedShipment.setServiceType(ServiceType.FEDEX_2_DAY_FREIGHT);
    if (order.getService().getCode().equalsIgnoreCase(SERVICE_FEDEX_3_DAY_FREIGHT))
      requestedShipment.setServiceType(ServiceType.FEDEX_3_DAY_FREIGHT);
    if (order.getService().getCode().equalsIgnoreCase(SERVICE_FEDEX_1_DAY_FREIGHT))
      requestedShipment.setServiceType(ServiceType.FEDEX_1_DAY_FREIGHT);
    if (order.getService().getCode().equalsIgnoreCase(SERVICE_SMART_POST))
      requestedShipment.setServiceType(ServiceType.SMART_POST);
    // if(order.getService().getCode().equalsIgnoreCase(SERVICE_FEDEX_NATIONAL_FREIGHT))
    // requestedShipment.setServiceType(ServiceType.);

  }

}
