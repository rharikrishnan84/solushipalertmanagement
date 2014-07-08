
package com.meritconinc.shiplinx.carrier.ups.ws.pickup.proxy;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.meritconinc.shiplinx.carrier.ups.ws.pickup.proxy package. 
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
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.meritconinc.shiplinx.carrier.ups.ws.pickup.proxy
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
     * Create an instance of {@link PickupRateResponse }
     * 
     */
    public PickupRateResponse createPickupRateResponse() {
        return new PickupRateResponse();
    }

    /**
     * Create an instance of {@link RateResultType }
     * 
     */
    public RateResultType createRateResultType() {
        return new RateResultType();
    }

    /**
     * Create an instance of {@link PickupCancelResponse }
     * 
     */
    public PickupCancelResponse createPickupCancelResponse() {
        return new PickupCancelResponse();
    }

    /**
     * Create an instance of {@link StatusCodeDescriptionType }
     * 
     */
    public StatusCodeDescriptionType createStatusCodeDescriptionType() {
        return new StatusCodeDescriptionType();
    }

    /**
     * Create an instance of {@link PickupCancelRequest }
     * 
     */
    public PickupCancelRequest createPickupCancelRequest() {
        return new PickupCancelRequest();
    }

    /**
     * Create an instance of {@link PickupRateRequest }
     * 
     */
    public PickupRateRequest createPickupRateRequest() {
        return new PickupRateRequest();
    }

    /**
     * Create an instance of {@link AddressType }
     * 
     */
    public AddressType createAddressType() {
        return new AddressType();
    }

    /**
     * Create an instance of {@link PickupDateInfoType }
     * 
     */
    public PickupDateInfoType createPickupDateInfoType() {
        return new PickupDateInfoType();
    }

    /**
     * Create an instance of {@link PickupPendingStatusRequest }
     * 
     */
    public PickupPendingStatusRequest createPickupPendingStatusRequest() {
        return new PickupPendingStatusRequest();
    }

    /**
     * Create an instance of {@link PickupCreationResponse }
     * 
     */
    public PickupCreationResponse createPickupCreationResponse() {
        return new PickupCreationResponse();
    }

    /**
     * Create an instance of {@link PickupPendingStatusResponse }
     * 
     */
    public PickupPendingStatusResponse createPickupPendingStatusResponse() {
        return new PickupPendingStatusResponse();
    }

    /**
     * Create an instance of {@link PendingStatusType }
     * 
     */
    public PendingStatusType createPendingStatusType() {
        return new PendingStatusType();
    }

    /**
     * Create an instance of {@link PickupCreationRequest }
     * 
     */
    public PickupCreationRequest createPickupCreationRequest() {
        return new PickupCreationRequest();
    }

    /**
     * Create an instance of {@link ShipperType }
     * 
     */
    public ShipperType createShipperType() {
        return new ShipperType();
    }

    /**
     * Create an instance of {@link PickupAddressType }
     * 
     */
    public PickupAddressType createPickupAddressType() {
        return new PickupAddressType();
    }

    /**
     * Create an instance of {@link PickupPieceType }
     * 
     */
    public PickupPieceType createPickupPieceType() {
        return new PickupPieceType();
    }

    /**
     * Create an instance of {@link WeightType }
     * 
     */
    public WeightType createWeightType() {
        return new WeightType();
    }

    /**
     * Create an instance of {@link TrackingDataType }
     * 
     */
    public TrackingDataType createTrackingDataType() {
        return new TrackingDataType();
    }

    /**
     * Create an instance of {@link NotificationType }
     * 
     */
    public NotificationType createNotificationType() {
        return new NotificationType();
    }

    /**
     * Create an instance of {@link TodayType }
     * 
     */
    public TodayType createTodayType() {
        return new TodayType();
    }

    /**
     * Create an instance of {@link ChargeDetailType }
     * 
     */
    public ChargeDetailType createChargeDetailType() {
        return new ChargeDetailType();
    }

    /**
     * Create an instance of {@link SimpleAddressType }
     * 
     */
    public SimpleAddressType createSimpleAddressType() {
        return new SimpleAddressType();
    }

    /**
     * Create an instance of {@link CSRType }
     * 
     */
    public CSRType createCSRType() {
        return new CSRType();
    }

    /**
     * Create an instance of {@link PhoneType }
     * 
     */
    public PhoneType createPhoneType() {
        return new PhoneType();
    }

    /**
     * Create an instance of {@link ChargeCardType }
     * 
     */
    public ChargeCardType createChargeCardType() {
        return new ChargeCardType();
    }

    /**
     * Create an instance of {@link StreetAddressArtifactType }
     * 
     */
    public StreetAddressArtifactType createStreetAddressArtifactType() {
        return new StreetAddressArtifactType();
    }

    /**
     * Create an instance of {@link ChargeCardAddressType }
     * 
     */
    public ChargeCardAddressType createChargeCardAddressType() {
        return new ChargeCardAddressType();
    }

    /**
     * Create an instance of {@link FutureServiceDateType }
     * 
     */
    public FutureServiceDateType createFutureServiceDateType() {
        return new FutureServiceDateType();
    }

    /**
     * Create an instance of {@link AccountType }
     * 
     */
    public AccountType createAccountType() {
        return new AccountType();
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
