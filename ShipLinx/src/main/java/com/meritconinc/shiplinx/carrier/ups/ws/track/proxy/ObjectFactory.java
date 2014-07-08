
package com.meritconinc.shiplinx.carrier.ups.ws.track.proxy;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.meritconinc.shiplinx.carrier.ups.ws.track.proxy package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _ClientInformation_QNAME = new QName("http://www.ups.com/XMLSchema/XOLTWS/Common/v1.0", "ClientInformation");
    private final static QName _Response_QNAME = new QName("http://www.ups.com/XMLSchema/XOLTWS/Common/v1.0", "Response");
    private final static QName _Request_QNAME = new QName("http://www.ups.com/XMLSchema/XOLTWS/Common/v1.0", "Request");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.meritconinc.shiplinx.carrier.ups.ws.track.proxy
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link UPSSecurity }
     * 
     */
    public UPSSecurity createUPSSecurity() {
        return new UPSSecurity();
    }

    /**
     * Create an instance of {@link ClientInformationType }
     * 
     */
    public ClientInformationType createClientInformationType() {
        return new ClientInformationType();
    }

    /**
     * Create an instance of {@link UPSSecurity.UsernameToken }
     * 
     */
    public UPSSecurity.UsernameToken createUPSSecurityUsernameToken() {
        return new UPSSecurity.UsernameToken();
    }

    /**
     * Create an instance of {@link UPSSecurity.ServiceAccessToken }
     * 
     */
    public UPSSecurity.ServiceAccessToken createUPSSecurityServiceAccessToken() {
        return new UPSSecurity.ServiceAccessToken();
    }

    /**
     * Create an instance of {@link Errors }
     * 
     */
    public Errors createErrors() {
        return new Errors();
    }

    /**
     * Create an instance of {@link ErrorDetailType }
     * 
     */
    public ErrorDetailType createErrorDetailType() {
        return new ErrorDetailType();
    }

    /**
     * Create an instance of {@link LocationType }
     * 
     */
    public LocationType createLocationType() {
        return new LocationType();
    }

    /**
     * Create an instance of {@link CodeType }
     * 
     */
    public CodeType createCodeType() {
        return new CodeType();
    }

    /**
     * Create an instance of {@link AdditionalCodeDescType }
     * 
     */
    public AdditionalCodeDescType createAdditionalCodeDescType() {
        return new AdditionalCodeDescType();
    }

    /**
     * Create an instance of {@link AdditionalInfoType }
     * 
     */
    public AdditionalInfoType createAdditionalInfoType() {
        return new AdditionalInfoType();
    }

    /**
     * Create an instance of {@link ResponseType }
     * 
     */
    public ResponseType createResponseType() {
        return new ResponseType();
    }

    /**
     * Create an instance of {@link RequestType }
     * 
     */
    public RequestType createRequestType() {
        return new RequestType();
    }

    /**
     * Create an instance of {@link TransactionReferenceType }
     * 
     */
    public TransactionReferenceType createTransactionReferenceType() {
        return new TransactionReferenceType();
    }

    /**
     * Create an instance of {@link CodeDescriptionType }
     * 
     */
    public CodeDescriptionType createCodeDescriptionType() {
        return new CodeDescriptionType();
    }

    /**
     * Create an instance of {@link TrackRequest }
     * 
     */
    public TrackRequest createTrackRequest() {
        return new TrackRequest();
    }

    /**
     * Create an instance of {@link ReferenceNumberType }
     * 
     */
    public ReferenceNumberType createReferenceNumberType() {
        return new ReferenceNumberType();
    }

    /**
     * Create an instance of {@link PickupDateRangeType }
     * 
     */
    public PickupDateRangeType createPickupDateRangeType() {
        return new PickupDateRangeType();
    }

    /**
     * Create an instance of {@link ShipFromRequestType }
     * 
     */
    public ShipFromRequestType createShipFromRequestType() {
        return new ShipFromRequestType();
    }

    /**
     * Create an instance of {@link ShipToRequestType }
     * 
     */
    public ShipToRequestType createShipToRequestType() {
        return new ShipToRequestType();
    }

    /**
     * Create an instance of {@link RefShipmentType }
     * 
     */
    public RefShipmentType createRefShipmentType() {
        return new RefShipmentType();
    }

    /**
     * Create an instance of {@link ShipperAccountInfoType }
     * 
     */
    public ShipperAccountInfoType createShipperAccountInfoType() {
        return new ShipperAccountInfoType();
    }

    /**
     * Create an instance of {@link TrackResponse }
     * 
     */
    public TrackResponse createTrackResponse() {
        return new TrackResponse();
    }

    /**
     * Create an instance of {@link ShipmentType }
     * 
     */
    public ShipmentType createShipmentType() {
        return new ShipmentType();
    }

    /**
     * Create an instance of {@link CODType }
     * 
     */
    public CODType createCODType() {
        return new CODType();
    }

    /**
     * Create an instance of {@link SignatureImageType }
     * 
     */
    public SignatureImageType createSignatureImageType() {
        return new SignatureImageType();
    }

    /**
     * Create an instance of {@link StatusType }
     * 
     */
    public StatusType createStatusType() {
        return new StatusType();
    }

    /**
     * Create an instance of {@link ServiceOptionType }
     * 
     */
    public ServiceOptionType createServiceOptionType() {
        return new ServiceOptionType();
    }

    /**
     * Create an instance of {@link RequestTransactionReferenceType }
     * 
     */
    public RequestTransactionReferenceType createRequestTransactionReferenceType() {
        return new RequestTransactionReferenceType();
    }

    /**
     * Create an instance of {@link PODLetterType }
     * 
     */
    public PODLetterType createPODLetterType() {
        return new PODLetterType();
    }

    /**
     * Create an instance of {@link ServiceType }
     * 
     */
    public ServiceType createServiceType() {
        return new ServiceType();
    }

    /**
     * Create an instance of {@link ActivityType }
     * 
     */
    public ActivityType createActivityType() {
        return new ActivityType();
    }

    /**
     * Create an instance of {@link ShipmentAddressType }
     * 
     */
    public ShipmentAddressType createShipmentAddressType() {
        return new ShipmentAddressType();
    }

    /**
     * Create an instance of {@link PackageAddressType }
     * 
     */
    public PackageAddressType createPackageAddressType() {
        return new PackageAddressType();
    }

    /**
     * Create an instance of {@link AmountType }
     * 
     */
    public AmountType createAmountType() {
        return new AmountType();
    }

    /**
     * Create an instance of {@link CODStatusType }
     * 
     */
    public CODStatusType createCODStatusType() {
        return new CODStatusType();
    }

    /**
     * Create an instance of {@link DateTimeType }
     * 
     */
    public DateTimeType createDateTimeType() {
        return new DateTimeType();
    }

    /**
     * Create an instance of {@link AdditionalCodeDescriptionValueType }
     * 
     */
    public AdditionalCodeDescriptionValueType createAdditionalCodeDescriptionValueType() {
        return new AdditionalCodeDescriptionValueType();
    }

    /**
     * Create an instance of {@link PackageType }
     * 
     */
    public PackageType createPackageType() {
        return new PackageType();
    }

    /**
     * Create an instance of {@link OriginPortDetailType }
     * 
     */
    public OriginPortDetailType createOriginPortDetailType() {
        return new OriginPortDetailType();
    }

    /**
     * Create an instance of {@link ResponseTransactionReferenceType }
     * 
     */
    public ResponseTransactionReferenceType createResponseTransactionReferenceType() {
        return new ResponseTransactionReferenceType();
    }

    /**
     * Create an instance of {@link VolumeType }
     * 
     */
    public VolumeType createVolumeType() {
        return new VolumeType();
    }

    /**
     * Create an instance of {@link UnitOfMeasurementType }
     * 
     */
    public UnitOfMeasurementType createUnitOfMeasurementType() {
        return new UnitOfMeasurementType();
    }

    /**
     * Create an instance of {@link CodeDescriptionValueType }
     * 
     */
    public CodeDescriptionValueType createCodeDescriptionValueType() {
        return new CodeDescriptionValueType();
    }

    /**
     * Create an instance of {@link DocumentType }
     * 
     */
    public DocumentType createDocumentType() {
        return new DocumentType();
    }

    /**
     * Create an instance of {@link WeightType }
     * 
     */
    public WeightType createWeightType() {
        return new WeightType();
    }

    /**
     * Create an instance of {@link CommonCodeDescriptionType }
     * 
     */
    public CommonCodeDescriptionType createCommonCodeDescriptionType() {
        return new CommonCodeDescriptionType();
    }

    /**
     * Create an instance of {@link TransportFacilityType }
     * 
     */
    public TransportFacilityType createTransportFacilityType() {
        return new TransportFacilityType();
    }

    /**
     * Create an instance of {@link ActivityLocationType }
     * 
     */
    public ActivityLocationType createActivityLocationType() {
        return new ActivityLocationType();
    }

    /**
     * Create an instance of {@link NumberOfPackagingUnitType }
     * 
     */
    public NumberOfPackagingUnitType createNumberOfPackagingUnitType() {
        return new NumberOfPackagingUnitType();
    }

    /**
     * Create an instance of {@link ShipmentActivityType }
     * 
     */
    public ShipmentActivityType createShipmentActivityType() {
        return new ShipmentActivityType();
    }

    /**
     * Create an instance of {@link ServiceCenterType }
     * 
     */
    public ServiceCenterType createServiceCenterType() {
        return new ServiceCenterType();
    }

    /**
     * Create an instance of {@link ShipmentReferenceNumberType }
     * 
     */
    public ShipmentReferenceNumberType createShipmentReferenceNumberType() {
        return new ShipmentReferenceNumberType();
    }

    /**
     * Create an instance of {@link DestinationPortDetailType }
     * 
     */
    public DestinationPortDetailType createDestinationPortDetailType() {
        return new DestinationPortDetailType();
    }

    /**
     * Create an instance of {@link ProductCodeDescriptionType }
     * 
     */
    public ProductCodeDescriptionType createProductCodeDescriptionType() {
        return new ProductCodeDescriptionType();
    }

    /**
     * Create an instance of {@link AddressRequestType }
     * 
     */
    public AddressRequestType createAddressRequestType() {
        return new AddressRequestType();
    }

    /**
     * Create an instance of {@link ImageFormatType }
     * 
     */
    public ImageFormatType createImageFormatType() {
        return new ImageFormatType();
    }

    /**
     * Create an instance of {@link CarrierActivityInformationType }
     * 
     */
    public CarrierActivityInformationType createCarrierActivityInformationType() {
        return new CarrierActivityInformationType();
    }

    /**
     * Create an instance of {@link AppointmentType }
     * 
     */
    public AppointmentType createAppointmentType() {
        return new AppointmentType();
    }

    /**
     * Create an instance of {@link AddressType }
     * 
     */
    public AddressType createAddressType() {
        return new AddressType();
    }

    /**
     * Create an instance of {@link DeliveryDetailType }
     * 
     */
    public DeliveryDetailType createDeliveryDetailType() {
        return new DeliveryDetailType();
    }

    /**
     * Create an instance of {@link AlternateTrackingInfoType }
     * 
     */
    public AlternateTrackingInfoType createAlternateTrackingInfoType() {
        return new AlternateTrackingInfoType();
    }

    /**
     * Create an instance of {@link MessageType }
     * 
     */
    public MessageType createMessageType() {
        return new MessageType();
    }

    /**
     * Create an instance of {@link ClientInformationType.Property }
     * 
     */
    public ClientInformationType.Property createClientInformationTypeProperty() {
        return new ClientInformationType.Property();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ClientInformationType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.ups.com/XMLSchema/XOLTWS/Common/v1.0", name = "ClientInformation")
    public JAXBElement<ClientInformationType> createClientInformation(ClientInformationType value) {
        return new JAXBElement<ClientInformationType>(_ClientInformation_QNAME, ClientInformationType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ResponseType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.ups.com/XMLSchema/XOLTWS/Common/v1.0", name = "Response")
    public JAXBElement<ResponseType> createResponse(ResponseType value) {
        return new JAXBElement<ResponseType>(_Response_QNAME, ResponseType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RequestType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.ups.com/XMLSchema/XOLTWS/Common/v1.0", name = "Request")
    public JAXBElement<RequestType> createRequest(RequestType value) {
        return new JAXBElement<RequestType>(_Request_QNAME, RequestType.class, null, value);
    }

}
