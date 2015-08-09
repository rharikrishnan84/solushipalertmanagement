	package com.meritconinc.shiplinx.carrier.purolatorFreight.ws.estimating.com.purolator.pws.datatypes.v1;

import java.math.BigDecimal;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each
 * Java content interface and Java element interface
 * generated in the com.purolator.pws.datatypes.v1 package.
 * <p>An ObjectFactory allows you to programatically
 * construct new instances of the Java representation
 * for XML content. The Java representation of XML
 * content can consist of schema derived interfaces
 * and classes representing the binding of schema
 * type definitions, element declarations and model
 * groups. Factory methods for each of these are
 * provided in this class.
 *
 */
@XmlRegistry
public class ObjectFactory {

 private final static QName _PaymentInformation_QNAME = new QName("http://purolator.com/pws/datatypes/v1", "PaymentInformation");
 private final static QName _InformationalMessage_QNAME = new QName("http://purolator.com/pws/datatypes/v1", "InformationalMessage");
 private final static QName _PaymentType_QNAME = new QName("http://purolator.com/pws/datatypes/v1", "PaymentType");
 private final static QName _FreightGetEstimateRequest_QNAME = new QName("http://purolator.com/pws/datatypes/v1", "FreightGetEstimateRequest");
 private final static QName _PhoneNumber_QNAME = new QName("http://purolator.com/pws/datatypes/v1", "PhoneNumber");
 private final static QName _ArrayOfLineItem_QNAME = new QName("http://purolator.com/pws/datatypes/v1", "ArrayOfLineItem");
 private final static QName _CreditCardType_QNAME = new QName("http://purolator.com/pws/datatypes/v1", "CreditCardType");
 private final static QName _EstimateInformation_QNAME = new QName("http://purolator.com/pws/datatypes/v1", "EstimateInformation");
 private final static QName _RequestContainer_QNAME = new QName("http://purolator.com/pws/datatypes/v1", "RequestContainer");
 private final static QName _Weight_QNAME = new QName("http://purolator.com/pws/datatypes/v1", "Weight");
 private final static QName _AccessorialItem_QNAME = new QName("http://purolator.com/pws/datatypes/v1", "AccessorialItem");
 private final static QName _DimensionUnit_QNAME = new QName("http://purolator.com/pws/datatypes/v1", "DimensionUnit");
 private final static QName _ResponseInformation_QNAME = new QName("http://purolator.com/pws/datatypes/v1", "ResponseInformation");
 private final static QName _LineItem_QNAME = new QName("http://purolator.com/pws/datatypes/v1", "LineItem");
 private final static QName _ArrayOfInformationalMessage_QNAME = new QName("http://purolator.com/pws/datatypes/v1", "ArrayOfInformationalMessage");
 private final static QName _ArrayOfBoolValuePair_QNAME = new QName("http://purolator.com/pws/datatypes/v1", "ArrayOfBoolValuePair");
 private final static QName _ReceiverInformation_QNAME = new QName("http://purolator.com/pws/datatypes/v1", "ReceiverInformation");
 private final static QName _Language_QNAME = new QName("http://purolator.com/pws/datatypes/v1", "Language");
 private final static QName _GetEstimateResponse_QNAME = new QName("http://purolator.com/pws/datatypes/v1", "GetEstimateResponse");
 private final static QName _Address_QNAME = new QName("http://purolator.com/pws/datatypes/v1", "Address");
 private final static QName _ResponseContext_QNAME = new QName("http://purolator.com/pws/datatypes/v1", "ResponseContext");
 private final static QName _ArrayOfTax_QNAME = new QName("http://purolator.com/pws/datatypes/v1", "ArrayOfTax");
 private final static QName _Error_QNAME = new QName("http://purolator.com/pws/datatypes/v1", "Error");
 private final static QName _Shipment_QNAME = new QName("http://purolator.com/pws/datatypes/v1", "Shipment");
 private final static QName _RequestContext_QNAME = new QName("http://purolator.com/pws/datatypes/v1", "RequestContext");
 private final static QName _Tax_QNAME = new QName("http://purolator.com/pws/datatypes/v1", "Tax");
 private final static QName _Dimension_QNAME = new QName("http://purolator.com/pws/datatypes/v1", "Dimension");
 private final static QName _ArrayOfAccessorialItem_QNAME = new QName("http://purolator.com/pws/datatypes/v1", "ArrayOfAccessorialItem");
 private final static QName _ArrayOfError_QNAME = new QName("http://purolator.com/pws/datatypes/v1", "ArrayOfError");
 private final static QName _WeightUnit_QNAME = new QName("http://purolator.com/pws/datatypes/v1", "WeightUnit");
 private final static QName _BoolValuePair_QNAME = new QName("http://purolator.com/pws/datatypes/v1", "BoolValuePair");
 private final static QName _SenderInformation_QNAME = new QName("http://purolator.com/pws/datatypes/v1", "SenderInformation");
 private final static QName _GetEstimateResponseContainer_QNAME = new QName("http://purolator.com/pws/datatypes/v1", "GetEstimateResponseContainer");
 private final static QName _CreditCardInformation_QNAME = new QName("http://purolator.com/pws/datatypes/v1", "CreditCardInformation");
 private final static QName _GetEstimateRequestContainer_QNAME = new QName("http://purolator.com/pws/datatypes/v1", "GetEstimateRequestContainer");
 private final static QName _ResponseContainer_QNAME = new QName("http://purolator.com/pws/datatypes/v1", "ResponseContainer");
 private final static QName _ReceiverInformationEmailAddress_QNAME = new QName("http://purolator.com/pws/datatypes/v1", "EmailAddress");
 private final static QName _GetEstimateResponseContainerLineItemDetails_QNAME = new QName("http://purolator.com/pws/datatypes/v1", "LineItemDetails");
 private final static QName _GetEstimateResponseContainerTariffCode_QNAME = new QName("http://purolator.com/pws/datatypes/v1", "TariffCode");
 private final static QName _GetEstimateResponseContainerAccessorialDetails_QNAME = new QName("http://purolator.com/pws/datatypes/v1", "AccessorialDetails");
 private final static QName _GetEstimateResponseContainerEstimatedDeliveryDate_QNAME = new QName("http://purolator.com/pws/datatypes/v1", "EstimatedDeliveryDate");
 private final static QName _GetEstimateResponseContainerShipmentTaxes_QNAME = new QName("http://purolator.com/pws/datatypes/v1", "ShipmentTaxes");
 private final static QName _AddressStreetAddress2_QNAME = new QName("http://purolator.com/pws/datatypes/v1", "StreetAddress2");
 private final static QName _AddressDepartment_QNAME = new QName("http://purolator.com/pws/datatypes/v1", "Department");
 private final static QName _AddressCompany_QNAME = new QName("http://purolator.com/pws/datatypes/v1", "Company");
 private final static QName _AddressFloor_QNAME = new QName("http://purolator.com/pws/datatypes/v1", "Floor");
 private final static QName _AddressStreetDirection_QNAME = new QName("http://purolator.com/pws/datatypes/v1", "StreetDirection");
 private final static QName _AddressStreetAddress3_QNAME = new QName("http://purolator.com/pws/datatypes/v1", "StreetAddress3");
 private final static QName _AddressStreetType_QNAME = new QName("http://purolator.com/pws/datatypes/v1", "StreetType");
 private final static QName _AddressStreetSuffix_QNAME = new QName("http://purolator.com/pws/datatypes/v1", "StreetSuffix");
 private final static QName _AddressFaxNumber_QNAME = new QName("http://purolator.com/pws/datatypes/v1", "FaxNumber");
 private final static QName _AddressSuite_QNAME = new QName("http://purolator.com/pws/datatypes/v1", "Suite");
 private final static QName _PhoneNumberExtension_QNAME = new QName("http://purolator.com/pws/datatypes/v1", "Extension");
 private final static QName _EstimateInformationShipmentDate_QNAME = new QName("http://purolator.com/pws/datatypes/v1", "ShipmentDate");
 private final static QName _EstimateInformationCODAmount_QNAME = new QName("http://purolator.com/pws/datatypes/v1", "CODAmount");
 private final static QName _EstimateInformationServiceTypeCode_QNAME = new QName("http://purolator.com/pws/datatypes/v1", "ServiceTypeCode");
 private final static QName _EstimateInformationAccesorialParameters_QNAME = new QName("http://purolator.com/pws/datatypes/v1", "AccesorialParameters");
 private final static QName _EstimateInformationDeclaredValue_QNAME = new QName("http://purolator.com/pws/datatypes/v1", "DeclaredValue");
 private final static QName _EstimateInformationSpecialInstructions_QNAME = new QName("http://purolator.com/pws/datatypes/v1", "SpecialInstructions");
 private final static QName _PaymentInformationBillingAccountNumber_QNAME = new QName("http://purolator.com/pws/datatypes/v1", "BillingAccountNumber");
 private final static QName _LineItemHeight_QNAME = new QName("http://purolator.com/pws/datatypes/v1", "Height");
 private final static QName _LineItemFreightClass_QNAME = new QName("http://purolator.com/pws/datatypes/v1", "FreightClass");
 private final static QName _LineItemWidth_QNAME = new QName("http://purolator.com/pws/datatypes/v1", "Width");
 private final static QName _LineItemLength_QNAME = new QName("http://purolator.com/pws/datatypes/v1", "Length");
 private final static QName _LineItemDescription_QNAME = new QName("http://purolator.com/pws/datatypes/v1", "Description");

 /**
 * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.purolator.pws.datatypes.v1
 *
 */
 public ObjectFactory() {
 }

 /**
 * Create an instance of {@link ReceiverInformation }
 *
 */
 public ReceiverInformation createReceiverInformation() {
 return new ReceiverInformation();
 }

 /**
 * Create an instance of {@link Weight }
 *
 */
 public Weight createWeight() {
 return new Weight();
 }

 /**
 * Create an instance of {@link Dimension }
 *
 */
 public Dimension createDimension() {
 return new Dimension();
 }

 /**
 * Create an instance of {@link GetEstimateResponseContainer }
 *
 */
 public GetEstimateResponseContainer createGetEstimateResponseContainer() {
 return new GetEstimateResponseContainer();
 }

 /**
 * Create an instance of {@link BoolValuePair }
 *
 */
 public BoolValuePair createBoolValuePair() {
 return new BoolValuePair();
 }

 /**
 * Create an instance of {@link CreditCardInformation }
 *
 */
 public CreditCardInformation createCreditCardInformation() {
 return new CreditCardInformation();
 }

 /**
 * Create an instance of {@link Shipment }
 *
 */
 public Shipment createShipment() {
 return new Shipment();
 }

 /**
 * Create an instance of {@link ResponseContext }
 *
 */
 public ResponseContext createResponseContext() {
 return new ResponseContext();
 }

 /**
 * Create an instance of {@link AccessorialItem }
 *
 */
 public AccessorialItem createAccessorialItem() {
 return new AccessorialItem();
 }

 /**
 * Create an instance of {@link InformationalMessage }
 *
 */
 public InformationalMessage createInformationalMessage() {
 return new InformationalMessage();
 }

 /**
 * Create an instance of {@link LineItem }
 *
 */
 public LineItem createLineItem() {
 return new LineItem();
 }

 /**
 * Create an instance of {@link ArrayOfLineItem }
 *
 */
 public ArrayOfLineItem createArrayOfLineItem() {
 return new ArrayOfLineItem();
 }

 /**
 * Create an instance of {@link ArrayOfAccessorialItem }
 *
 */
 public ArrayOfAccessorialItem createArrayOfAccessorialItem() {
 return new ArrayOfAccessorialItem();
 }

 /**
 * Create an instance of {@link GetEstimateRequestContainer }
 *
 */
 public GetEstimateRequestContainer createGetEstimateRequestContainer() {
 return new GetEstimateRequestContainer();
 }

 /**
 * Create an instance of {@link RequestContext }
 *
 */
 public RequestContext createRequestContext() {
 return new RequestContext();
 }

 /**
 * Create an instance of {@link Address }
 *
 */
 public Address createAddress() {
 return new Address();
 }

 /**
 * Create an instance of {@link ResponseContainer }
 *
 */
 public ResponseContainer createResponseContainer() {
 return new ResponseContainer();
 }

 /**
 * Create an instance of {@link ArrayOfBoolValuePair }
 *
 */
 public ArrayOfBoolValuePair createArrayOfBoolValuePair() {
 return new ArrayOfBoolValuePair();
 }

 /**
 * Create an instance of {@link RequestContainer }
 *
 */
 public RequestContainer createRequestContainer() {
 return new RequestContainer();
 }

 /**
 * Create an instance of {@link ResponseInformation }
 *
 */
 public ResponseInformation createResponseInformation() {
 return new ResponseInformation();
 }

 /**
 * Create an instance of {@link ArrayOfError }
 *
 */
 public ArrayOfError createArrayOfError() {
 return new ArrayOfError();
 }

 /**
 * Create an instance of {@link SenderInformation }
 *
 */
 public SenderInformation createSenderInformation() {
 return new SenderInformation();
 }

 /**
 * Create an instance of {@link PhoneNumber }
 *
 */
 public PhoneNumber createPhoneNumber() {
 return new PhoneNumber();
 }

 /**
 * Create an instance of {@link PaymentInformation }
 *
 */
 public PaymentInformation createPaymentInformation() {
 return new PaymentInformation();
 }

 /**
 * Create an instance of {@link Tax }
 *
 */
 public Tax createTax() {
 return new Tax();
 }

 /**
 * Create an instance of {@link ArrayOfTax }
 *
 */
 public ArrayOfTax createArrayOfTax() {
 return new ArrayOfTax();
 }

 /**
 * Create an instance of {@link Error }
 *
 */
 public Error createError() {
 return new Error();
 }

 /**
 * Create an instance of {@link EstimateInformation }
 *
 */
 public EstimateInformation createEstimateInformation() {
 return new EstimateInformation();
 }

 /**
 * Create an instance of {@link ArrayOfInformationalMessage }
 *
 */
 public ArrayOfInformationalMessage createArrayOfInformationalMessage() {
 return new ArrayOfInformationalMessage();
 }

 /**
 * Create an instance of {@link JAXBElement }{@code <}{@link PaymentInformation }{@code >}}
 *
 */
 @XmlElementDecl(namespace = "http://purolator.com/pws/datatypes/v1", name = "PaymentInformation")
 public JAXBElement<PaymentInformation> createPaymentInformation(PaymentInformation value) {
 return new JAXBElement<PaymentInformation>(_PaymentInformation_QNAME, PaymentInformation.class, null, value);
 }

 /**
 * Create an instance of {@link JAXBElement }{@code <}{@link InformationalMessage }{@code >}}
 *
 */
 @XmlElementDecl(namespace = "http://purolator.com/pws/datatypes/v1", name = "InformationalMessage")
 public JAXBElement<InformationalMessage> createInformationalMessage(InformationalMessage value) {
 return new JAXBElement<InformationalMessage>(_InformationalMessage_QNAME, InformationalMessage.class, null, value);
 }

 /**
 * Create an instance of {@link JAXBElement }{@code <}{@link PaymentType }{@code >}}
 *
 */
 @XmlElementDecl(namespace = "http://purolator.com/pws/datatypes/v1", name = "PaymentType")
 public JAXBElement<PaymentType> createPaymentType(PaymentType value) {
 return new JAXBElement<PaymentType>(_PaymentType_QNAME, PaymentType.class, null, value);
 }

 /**
 * Create an instance of {@link JAXBElement }{@code <}{@link GetEstimateRequestContainer }{@code >}}
 *
 */
 @XmlElementDecl(namespace = "http://purolator.com/pws/datatypes/v1", name = "FreightGetEstimateRequest")
 public JAXBElement<GetEstimateRequestContainer> createFreightGetEstimateRequest(GetEstimateRequestContainer value) {
 return new JAXBElement<GetEstimateRequestContainer>(_FreightGetEstimateRequest_QNAME, GetEstimateRequestContainer.class, null, value);
 }

 /**
 * Create an instance of {@link JAXBElement }{@code <}{@link PhoneNumber }{@code >}}
 *
 */
 @XmlElementDecl(namespace = "http://purolator.com/pws/datatypes/v1", name = "PhoneNumber")
 public JAXBElement<PhoneNumber> createPhoneNumber(PhoneNumber value) {
 return new JAXBElement<PhoneNumber>(_PhoneNumber_QNAME, PhoneNumber.class, null, value);
 }

 /**
 * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfLineItem }{@code >}}
 *
 */
 @XmlElementDecl(namespace = "http://purolator.com/pws/datatypes/v1", name = "ArrayOfLineItem")
 public JAXBElement<ArrayOfLineItem> createArrayOfLineItem(ArrayOfLineItem value) {
 return new JAXBElement<ArrayOfLineItem>(_ArrayOfLineItem_QNAME, ArrayOfLineItem.class, null, value);
 }

 /**
 * Create an instance of {@link JAXBElement }{@code <}{@link CreditCardType }{@code >}}
 *
 */
 @XmlElementDecl(namespace = "http://purolator.com/pws/datatypes/v1", name = "CreditCardType")
 public JAXBElement<CreditCardType> createCreditCardType(CreditCardType value) {
 return new JAXBElement<CreditCardType>(_CreditCardType_QNAME, CreditCardType.class, null, value);
 }

 /**
 * Create an instance of {@link JAXBElement }{@code <}{@link EstimateInformation }{@code >}}
 *
 */
 @XmlElementDecl(namespace = "http://purolator.com/pws/datatypes/v1", name = "EstimateInformation")
 public JAXBElement<EstimateInformation> createEstimateInformation(EstimateInformation value) {
 return new JAXBElement<EstimateInformation>(_EstimateInformation_QNAME, EstimateInformation.class, null, value);
 }

 /**
 * Create an instance of {@link JAXBElement }{@code <}{@link RequestContainer }{@code >}}
 *
 */
 @XmlElementDecl(namespace = "http://purolator.com/pws/datatypes/v1", name = "RequestContainer")
 public JAXBElement<RequestContainer> createRequestContainer(RequestContainer value) {
 return new JAXBElement<RequestContainer>(_RequestContainer_QNAME, RequestContainer.class, null, value);
 }

 /**
 * Create an instance of {@link JAXBElement }{@code <}{@link Weight }{@code >}}
 *
 */
 @XmlElementDecl(namespace = "http://purolator.com/pws/datatypes/v1", name = "Weight")
 public JAXBElement<Weight> createWeight(Weight value) {
 return new JAXBElement<Weight>(_Weight_QNAME, Weight.class, null, value);
 }

 /**
 * Create an instance of {@link JAXBElement }{@code <}{@link AccessorialItem }{@code >}}
 *
 */
 @XmlElementDecl(namespace = "http://purolator.com/pws/datatypes/v1", name = "AccessorialItem")
 public JAXBElement<AccessorialItem> createAccessorialItem(AccessorialItem value) {
 return new JAXBElement<AccessorialItem>(_AccessorialItem_QNAME, AccessorialItem.class, null, value);
 }

 /**
 * Create an instance of {@link JAXBElement }{@code <}{@link DimensionUnit }{@code >}}
 *
 */
 @XmlElementDecl(namespace = "http://purolator.com/pws/datatypes/v1", name = "DimensionUnit")
 public JAXBElement<DimensionUnit> createDimensionUnit(DimensionUnit value) {
 return new JAXBElement<DimensionUnit>(_DimensionUnit_QNAME, DimensionUnit.class, null, value);
 }

 /**
 * Create an instance of {@link JAXBElement }{@code <}{@link ResponseInformation }{@code >}}
 *
 */
 @XmlElementDecl(namespace = "http://purolator.com/pws/datatypes/v1", name = "ResponseInformation")
 public JAXBElement<ResponseInformation> createResponseInformation(ResponseInformation value) {
 return new JAXBElement<ResponseInformation>(_ResponseInformation_QNAME, ResponseInformation.class, null, value);
 }

 /**
 * Create an instance of {@link JAXBElement }{@code <}{@link LineItem }{@code >}}
 *
 */
 @XmlElementDecl(namespace = "http://purolator.com/pws/datatypes/v1", name = "LineItem")
 public JAXBElement<LineItem> createLineItem(LineItem value) {
 return new JAXBElement<LineItem>(_LineItem_QNAME, LineItem.class, null, value);
 }

 /**
 * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfInformationalMessage }{@code >}}
 *
 */
 @XmlElementDecl(namespace = "http://purolator.com/pws/datatypes/v1", name = "ArrayOfInformationalMessage")
 public JAXBElement<ArrayOfInformationalMessage> createArrayOfInformationalMessage(ArrayOfInformationalMessage value) {
 return new JAXBElement<ArrayOfInformationalMessage>(_ArrayOfInformationalMessage_QNAME, ArrayOfInformationalMessage.class, null, value);
 }

 /**
 * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfBoolValuePair }{@code >}}
 *
 */
 @XmlElementDecl(namespace = "http://purolator.com/pws/datatypes/v1", name = "ArrayOfBoolValuePair")
 public JAXBElement<ArrayOfBoolValuePair> createArrayOfBoolValuePair(ArrayOfBoolValuePair value) {
 return new JAXBElement<ArrayOfBoolValuePair>(_ArrayOfBoolValuePair_QNAME, ArrayOfBoolValuePair.class, null, value);
 }

 /**
 * Create an instance of {@link JAXBElement }{@code <}{@link ReceiverInformation }{@code >}}
 *
 */
 @XmlElementDecl(namespace = "http://purolator.com/pws/datatypes/v1", name = "ReceiverInformation")
 public JAXBElement<ReceiverInformation> createReceiverInformation(ReceiverInformation value) {
 return new JAXBElement<ReceiverInformation>(_ReceiverInformation_QNAME, ReceiverInformation.class, null, value);
 }

 /**
 * Create an instance of {@link JAXBElement }{@code <}{@link Language }{@code >}}
 *
 */
 @XmlElementDecl(namespace = "http://purolator.com/pws/datatypes/v1", name = "Language")
 public JAXBElement<Language> createLanguage(Language value) {
 return new JAXBElement<Language>(_Language_QNAME, Language.class, null, value);
 }

 /**
 * Create an instance of {@link JAXBElement }{@code <}{@link GetEstimateResponseContainer }{@code >}}
 *
 */
 @XmlElementDecl(namespace = "http://purolator.com/pws/datatypes/v1", name = "GetEstimateResponse")
 public JAXBElement<GetEstimateResponseContainer> createGetEstimateResponse(GetEstimateResponseContainer value) {
 return new JAXBElement<GetEstimateResponseContainer>(_GetEstimateResponse_QNAME, GetEstimateResponseContainer.class, null, value);
 }

 /**
 * Create an instance of {@link JAXBElement }{@code <}{@link Address }{@code >}}
 *
 */
 @XmlElementDecl(namespace = "http://purolator.com/pws/datatypes/v1", name = "Address")
 public JAXBElement<Address> createAddress(Address value) {
 return new JAXBElement<Address>(_Address_QNAME, Address.class, null, value);
 }

 /**
 * Create an instance of {@link JAXBElement }{@code <}{@link ResponseContext }{@code >}}
 *
 */
 @XmlElementDecl(namespace = "http://purolator.com/pws/datatypes/v1", name = "ResponseContext")
 public JAXBElement<ResponseContext> createResponseContext(ResponseContext value) {
 return new JAXBElement<ResponseContext>(_ResponseContext_QNAME, ResponseContext.class, null, value);
 }

 /**
 * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfTax }{@code >}}
 *
 */
 @XmlElementDecl(namespace = "http://purolator.com/pws/datatypes/v1", name = "ArrayOfTax")
 public JAXBElement<ArrayOfTax> createArrayOfTax(ArrayOfTax value) {
 return new JAXBElement<ArrayOfTax>(_ArrayOfTax_QNAME, ArrayOfTax.class, null, value);
 }

 /**
 * Create an instance of {@link JAXBElement }{@code <}{@link Error }{@code >}}
 *
 */
 @XmlElementDecl(namespace = "http://purolator.com/pws/datatypes/v1", name = "Error")
 public JAXBElement<Error> createError(Error value) {
 return new JAXBElement<Error>(_Error_QNAME, Error.class, null, value);
 }

 /**
 * Create an instance of {@link JAXBElement }{@code <}{@link Shipment }{@code >}}
 *
 */
 @XmlElementDecl(namespace = "http://purolator.com/pws/datatypes/v1", name = "Shipment")
 public JAXBElement<Shipment> createShipment(Shipment value) {
 return new JAXBElement<Shipment>(_Shipment_QNAME, Shipment.class, null, value);
 }

 /**
 * Create an instance of {@link JAXBElement }{@code <}{@link RequestContext }{@code >}}
 *
 */
 @XmlElementDecl(namespace = "http://purolator.com/pws/datatypes/v1", name = "RequestContext")
 public JAXBElement<RequestContext> createRequestContext(RequestContext value) {
 return new JAXBElement<RequestContext>(_RequestContext_QNAME, RequestContext.class, null, value);
 }

 /**
 * Create an instance of {@link JAXBElement }{@code <}{@link Tax }{@code >}}
 *
 */
 @XmlElementDecl(namespace = "http://purolator.com/pws/datatypes/v1", name = "Tax")
 public JAXBElement<Tax> createTax(Tax value) {
 return new JAXBElement<Tax>(_Tax_QNAME, Tax.class, null, value);
 }

 /**
 * Create an instance of {@link JAXBElement }{@code <}{@link Dimension }{@code >}}
 *
 */
 @XmlElementDecl(namespace = "http://purolator.com/pws/datatypes/v1", name = "Dimension")
 public JAXBElement<Dimension> createDimension(Dimension value) {
 return new JAXBElement<Dimension>(_Dimension_QNAME, Dimension.class, null, value);
 }

 /**
 * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfAccessorialItem }{@code >}}
 *
 */
 @XmlElementDecl(namespace = "http://purolator.com/pws/datatypes/v1", name = "ArrayOfAccessorialItem")
 public JAXBElement<ArrayOfAccessorialItem> createArrayOfAccessorialItem(ArrayOfAccessorialItem value) {
 return new JAXBElement<ArrayOfAccessorialItem>(_ArrayOfAccessorialItem_QNAME, ArrayOfAccessorialItem.class, null, value);
 }

 /**
 * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfError }{@code >}}
 *
 */
 @XmlElementDecl(namespace = "http://purolator.com/pws/datatypes/v1", name = "ArrayOfError")
 public JAXBElement<ArrayOfError> createArrayOfError(ArrayOfError value) {
 return new JAXBElement<ArrayOfError>(_ArrayOfError_QNAME, ArrayOfError.class, null, value);
 }

 /**
 * Create an instance of {@link JAXBElement }{@code <}{@link WeightUnit }{@code >}}
 *
 */
 @XmlElementDecl(namespace = "http://purolator.com/pws/datatypes/v1", name = "WeightUnit")
 public JAXBElement<WeightUnit> createWeightUnit(WeightUnit value) {
 return new JAXBElement<WeightUnit>(_WeightUnit_QNAME, WeightUnit.class, null, value);
 }

 /**
 * Create an instance of {@link JAXBElement }{@code <}{@link BoolValuePair }{@code >}}
 *
 */
 @XmlElementDecl(namespace = "http://purolator.com/pws/datatypes/v1", name = "BoolValuePair")
 public JAXBElement<BoolValuePair> createBoolValuePair(BoolValuePair value) {
 return new JAXBElement<BoolValuePair>(_BoolValuePair_QNAME, BoolValuePair.class, null, value);
 }

 /**
 * Create an instance of {@link JAXBElement }{@code <}{@link SenderInformation }{@code >}}
 *
 */
 @XmlElementDecl(namespace = "http://purolator.com/pws/datatypes/v1", name = "SenderInformation")
 public JAXBElement<SenderInformation> createSenderInformation(SenderInformation value) {
 return new JAXBElement<SenderInformation>(_SenderInformation_QNAME, SenderInformation.class, null, value);
 }

 /**
 * Create an instance of {@link JAXBElement }{@code <}{@link GetEstimateResponseContainer }{@code >}}
 *
 */
 @XmlElementDecl(namespace = "http://purolator.com/pws/datatypes/v1", name = "GetEstimateResponseContainer")
 public JAXBElement<GetEstimateResponseContainer> createGetEstimateResponseContainer(GetEstimateResponseContainer value) {
 return new JAXBElement<GetEstimateResponseContainer>(_GetEstimateResponseContainer_QNAME, GetEstimateResponseContainer.class, null, value);
 }

 /**
 * Create an instance of {@link JAXBElement }{@code <}{@link CreditCardInformation }{@code >}}
 *
 */
 @XmlElementDecl(namespace = "http://purolator.com/pws/datatypes/v1", name = "CreditCardInformation")
 public JAXBElement<CreditCardInformation> createCreditCardInformation(CreditCardInformation value) {
 return new JAXBElement<CreditCardInformation>(_CreditCardInformation_QNAME, CreditCardInformation.class, null, value);
 }

 /**
 * Create an instance of {@link JAXBElement }{@code <}{@link GetEstimateRequestContainer }{@code >}}
 *
 */
 @XmlElementDecl(namespace = "http://purolator.com/pws/datatypes/v1", name = "GetEstimateRequestContainer")
 public JAXBElement<GetEstimateRequestContainer> createGetEstimateRequestContainer(GetEstimateRequestContainer value) {
 return new JAXBElement<GetEstimateRequestContainer>(_GetEstimateRequestContainer_QNAME, GetEstimateRequestContainer.class, null, value);
 }

 /**
 * Create an instance of {@link JAXBElement }{@code <}{@link ResponseContainer }{@code >}}
 *
 */
 @XmlElementDecl(namespace = "http://purolator.com/pws/datatypes/v1", name = "ResponseContainer")
 public JAXBElement<ResponseContainer> createResponseContainer(ResponseContainer value) {
 return new JAXBElement<ResponseContainer>(_ResponseContainer_QNAME, ResponseContainer.class, null, value);
 }

 /**
 * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
 *
 */
 @XmlElementDecl(namespace = "http://purolator.com/pws/datatypes/v1", name = "EmailAddress", scope = ReceiverInformation.class)
 public JAXBElement<String> createReceiverInformationEmailAddress(String value) {
 return new JAXBElement<String>(_ReceiverInformationEmailAddress_QNAME, String.class, ReceiverInformation.class, value);
 }

 /**
 * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfLineItem }{@code >}}
 *
 */
 @XmlElementDecl(namespace = "http://purolator.com/pws/datatypes/v1", name = "LineItemDetails", scope = GetEstimateResponseContainer.class)
 public JAXBElement<ArrayOfLineItem> createGetEstimateResponseContainerLineItemDetails(ArrayOfLineItem value) {
 return new JAXBElement<ArrayOfLineItem>(_GetEstimateResponseContainerLineItemDetails_QNAME, ArrayOfLineItem.class, GetEstimateResponseContainer.class, value);
 }

 /**
 * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
 *
 */
 @XmlElementDecl(namespace = "http://purolator.com/pws/datatypes/v1", name = "TariffCode", scope = GetEstimateResponseContainer.class)
 public JAXBElement<String> createGetEstimateResponseContainerTariffCode(String value) {
 return new JAXBElement<String>(_GetEstimateResponseContainerTariffCode_QNAME, String.class, GetEstimateResponseContainer.class, value);
 }

 /**
 * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfAccessorialItem }{@code >}}
 *
 */
 @XmlElementDecl(namespace = "http://purolator.com/pws/datatypes/v1", name = "AccessorialDetails", scope = GetEstimateResponseContainer.class)
 public JAXBElement<ArrayOfAccessorialItem> createGetEstimateResponseContainerAccessorialDetails(ArrayOfAccessorialItem value) {
 return new JAXBElement<ArrayOfAccessorialItem>(_GetEstimateResponseContainerAccessorialDetails_QNAME, ArrayOfAccessorialItem.class, GetEstimateResponseContainer.class, value);
 }

 /**
 * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
 *
 */
 @XmlElementDecl(namespace = "http://purolator.com/pws/datatypes/v1", name = "EstimatedDeliveryDate", scope = GetEstimateResponseContainer.class)
 public JAXBElement<String> createGetEstimateResponseContainerEstimatedDeliveryDate(String value) {
 return new JAXBElement<String>(_GetEstimateResponseContainerEstimatedDeliveryDate_QNAME, String.class, GetEstimateResponseContainer.class, value);
 }

 /**
 * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfTax }{@code >}}
 *
 */
 @XmlElementDecl(namespace = "http://purolator.com/pws/datatypes/v1", name = "ShipmentTaxes", scope = GetEstimateResponseContainer.class)
 public JAXBElement<ArrayOfTax> createGetEstimateResponseContainerShipmentTaxes(ArrayOfTax value) {
 return new JAXBElement<ArrayOfTax>(_GetEstimateResponseContainerShipmentTaxes_QNAME, ArrayOfTax.class, GetEstimateResponseContainer.class, value);
 }

 /**
 * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
 *
 */
 @XmlElementDecl(namespace = "http://purolator.com/pws/datatypes/v1", name = "EmailAddress", scope = SenderInformation.class)
 public JAXBElement<String> createSenderInformationEmailAddress(String value) {
 return new JAXBElement<String>(_ReceiverInformationEmailAddress_QNAME, String.class, SenderInformation.class, value);
 }

 /**
 * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
 *
 */
 @XmlElementDecl(namespace = "http://purolator.com/pws/datatypes/v1", name = "StreetAddress2", scope = Address.class)
 public JAXBElement<String> createAddressStreetAddress2(String value) {
 return new JAXBElement<String>(_AddressStreetAddress2_QNAME, String.class, Address.class, value);
 }

 /**
 * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
 *
 */
 @XmlElementDecl(namespace = "http://purolator.com/pws/datatypes/v1", name = "Department", scope = Address.class)
 public JAXBElement<String> createAddressDepartment(String value) {
 return new JAXBElement<String>(_AddressDepartment_QNAME, String.class, Address.class, value);
 }

 /**
 * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
 *
 */
 @XmlElementDecl(namespace = "http://purolator.com/pws/datatypes/v1", name = "Company", scope = Address.class)
 public JAXBElement<String> createAddressCompany(String value) {
 return new JAXBElement<String>(_AddressCompany_QNAME, String.class, Address.class, value);
 }

 /**
 * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
 *
 */
 @XmlElementDecl(namespace = "http://purolator.com/pws/datatypes/v1", name = "Floor", scope = Address.class)
 public JAXBElement<String> createAddressFloor(String value) {
 return new JAXBElement<String>(_AddressFloor_QNAME, String.class, Address.class, value);
 }

 /**
 * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
 *
 */
 @XmlElementDecl(namespace = "http://purolator.com/pws/datatypes/v1", name = "StreetDirection", scope = Address.class)
 public JAXBElement<String> createAddressStreetDirection(String value) {
 return new JAXBElement<String>(_AddressStreetDirection_QNAME, String.class, Address.class, value);
 }

 /**
 * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
 *
 */
 @XmlElementDecl(namespace = "http://purolator.com/pws/datatypes/v1", name = "StreetAddress3", scope = Address.class)
 public JAXBElement<String> createAddressStreetAddress3(String value) {
 return new JAXBElement<String>(_AddressStreetAddress3_QNAME, String.class, Address.class, value);
 }

 /**
 * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
 *
 */
 @XmlElementDecl(namespace = "http://purolator.com/pws/datatypes/v1", name = "StreetType", scope = Address.class)
 public JAXBElement<String> createAddressStreetType(String value) {
 return new JAXBElement<String>(_AddressStreetType_QNAME, String.class, Address.class, value);
 }

 /**
 * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
 *
 */
 @XmlElementDecl(namespace = "http://purolator.com/pws/datatypes/v1", name = "StreetSuffix", scope = Address.class)
 public JAXBElement<String> createAddressStreetSuffix(String value) {
 return new JAXBElement<String>(_AddressStreetSuffix_QNAME, String.class, Address.class, value);
 }

 /**
 * Create an instance of {@link JAXBElement }{@code <}{@link PhoneNumber }{@code >}}
 *
 */
 @XmlElementDecl(namespace = "http://purolator.com/pws/datatypes/v1", name = "FaxNumber", scope = Address.class)
 public JAXBElement<PhoneNumber> createAddressFaxNumber(PhoneNumber value) {
 return new JAXBElement<PhoneNumber>(_AddressFaxNumber_QNAME, PhoneNumber.class, Address.class, value);
 }

 /**
 * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
 *
 */
 @XmlElementDecl(namespace = "http://purolator.com/pws/datatypes/v1", name = "Suite", scope = Address.class)
 public JAXBElement<String> createAddressSuite(String value) {
 return new JAXBElement<String>(_AddressSuite_QNAME, String.class, Address.class, value);
 }

 /**
 * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
 *
 */
 @XmlElementDecl(namespace = "http://purolator.com/pws/datatypes/v1", name = "Extension", scope = PhoneNumber.class)
 public JAXBElement<String> createPhoneNumberExtension(String value) {
 return new JAXBElement<String>(_PhoneNumberExtension_QNAME, String.class, PhoneNumber.class, value);
 }

 /**
 * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
 *
 */
 @XmlElementDecl(namespace = "http://purolator.com/pws/datatypes/v1", name = "ShipmentDate", scope = EstimateInformation.class)
 public JAXBElement<String> createEstimateInformationShipmentDate(String value) {
 return new JAXBElement<String>(_EstimateInformationShipmentDate_QNAME, String.class, EstimateInformation.class, value);
 }

 /**
 * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
 *
 */
 @XmlElementDecl(namespace = "http://purolator.com/pws/datatypes/v1", name = "CODAmount", scope = EstimateInformation.class)
 public JAXBElement<BigDecimal> createEstimateInformationCODAmount(BigDecimal value) {
 return new JAXBElement<BigDecimal>(_EstimateInformationCODAmount_QNAME, BigDecimal.class, EstimateInformation.class, value);
 }

 /**
 * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
 *
 */
 @XmlElementDecl(namespace = "http://purolator.com/pws/datatypes/v1", name = "ServiceTypeCode", scope = EstimateInformation.class)
 public JAXBElement<String> createEstimateInformationServiceTypeCode(String value) {
 return new JAXBElement<String>(_EstimateInformationServiceTypeCode_QNAME, String.class, EstimateInformation.class, value);
 }

 /**
 * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfBoolValuePair }{@code >}}
 *
 */
 @XmlElementDecl(namespace = "http://purolator.com/pws/datatypes/v1", name = "AccesorialParameters", scope = EstimateInformation.class)
 public JAXBElement<ArrayOfBoolValuePair> createEstimateInformationAccesorialParameters(ArrayOfBoolValuePair value) {
 return new JAXBElement<ArrayOfBoolValuePair>(_EstimateInformationAccesorialParameters_QNAME, ArrayOfBoolValuePair.class, EstimateInformation.class, value);
 }

 /**
 * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
 *
 */
 @XmlElementDecl(namespace = "http://purolator.com/pws/datatypes/v1", name = "DeclaredValue", scope = EstimateInformation.class)
 public JAXBElement<BigDecimal> createEstimateInformationDeclaredValue(BigDecimal value) {
 return new JAXBElement<BigDecimal>(_EstimateInformationDeclaredValue_QNAME, BigDecimal.class, EstimateInformation.class, value);
 }

 /**
 * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
 *
 */
 @XmlElementDecl(namespace = "http://purolator.com/pws/datatypes/v1", name = "SpecialInstructions", scope = EstimateInformation.class)
 public JAXBElement<String> createEstimateInformationSpecialInstructions(String value) {
 return new JAXBElement<String>(_EstimateInformationSpecialInstructions_QNAME, String.class, EstimateInformation.class, value);
 }

 /**
 * Create an instance of {@link JAXBElement }{@code <}{@link CreditCardInformation }{@code >}}
 *
 */
 @XmlElementDecl(namespace = "http://purolator.com/pws/datatypes/v1", name = "CreditCardInformation", scope = PaymentInformation.class)
 public JAXBElement<CreditCardInformation> createPaymentInformationCreditCardInformation(CreditCardInformation value) {
 return new JAXBElement<CreditCardInformation>(_CreditCardInformation_QNAME, CreditCardInformation.class, PaymentInformation.class, value);
 }

 /**
 * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
 *
 */
 @XmlElementDecl(namespace = "http://purolator.com/pws/datatypes/v1", name = "BillingAccountNumber", scope = PaymentInformation.class)
 public JAXBElement<String> createPaymentInformationBillingAccountNumber(String value) {
 return new JAXBElement<String>(_PaymentInformationBillingAccountNumber_QNAME, String.class, PaymentInformation.class, value);
 }

 /**
 * Create an instance of {@link JAXBElement }{@code <}{@link Dimension }{@code >}}
 *
 */
 @XmlElementDecl(namespace = "http://purolator.com/pws/datatypes/v1", name = "Height", scope = LineItem.class)
 public JAXBElement<Dimension> createLineItemHeight(Dimension value) {
 return new JAXBElement<Dimension>(_LineItemHeight_QNAME, Dimension.class, LineItem.class, value);
 }

 /**
 * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
 *
 */
 @XmlElementDecl(namespace = "http://purolator.com/pws/datatypes/v1", name = "FreightClass", scope = LineItem.class)
 public JAXBElement<String> createLineItemFreightClass(String value) {
 return new JAXBElement<String>(_LineItemFreightClass_QNAME, String.class, LineItem.class, value);
 }

 /**
 * Create an instance of {@link JAXBElement }{@code <}{@link Dimension }{@code >}}
 *
 */
 @XmlElementDecl(namespace = "http://purolator.com/pws/datatypes/v1", name = "Width", scope = LineItem.class)
 public JAXBElement<Dimension> createLineItemWidth(Dimension value) {
 return new JAXBElement<Dimension>(_LineItemWidth_QNAME, Dimension.class, LineItem.class, value);
 }

 /**
 * Create an instance of {@link JAXBElement }{@code <}{@link Dimension }{@code >}}
 *
 */
 @XmlElementDecl(namespace = "http://purolator.com/pws/datatypes/v1", name = "Length", scope = LineItem.class)
 public JAXBElement<Dimension> createLineItemLength(Dimension value) {
 return new JAXBElement<Dimension>(_LineItemLength_QNAME, Dimension.class, LineItem.class, value);
 }

 /**
 * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
 *
 */
 @XmlElementDecl(namespace = "http://purolator.com/pws/datatypes/v1", name = "Description", scope = LineItem.class)
 public JAXBElement<String> createLineItemDescription(String value) {
 return new JAXBElement<String>(_LineItemDescription_QNAME, String.class, LineItem.class, value);
 }

}