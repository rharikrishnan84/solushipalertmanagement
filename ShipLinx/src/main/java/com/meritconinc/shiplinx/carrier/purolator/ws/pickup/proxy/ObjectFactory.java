
package com.meritconinc.shiplinx.carrier.purolator.ws.pickup.proxy;

import java.math.BigDecimal;
import java.math.BigInteger;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.datatype.Duration;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.meritconinc.shiplinx.carrier.purolator.ws.pickup.proxy package. 
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

    private final static QName _ValidationFaultDetails_QNAME = new QName("http://www.microsoft.com/practices/EnterpriseLibrary/2007/01/wcf/validation", "Details");
    private final static QName _AnyURI_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "anyURI");
    private final static QName _VoidPickUpResponse_QNAME = new QName("http://purolator.com/pws/datatypes/v1", "VoidPickUpResponse");
    private final static QName _Char_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "char");
    private final static QName _ArrayOfValidationDetail_QNAME = new QName("http://schemas.datacontract.org/2004/07/Microsoft.Practices.EnterpriseLibrary.Validation.Integration.WCF", "ArrayOfValidationDetail");
    private final static QName _InformationalMessage_QNAME = new QName("http://purolator.com/pws/datatypes/v1", "InformationalMessage");
    private final static QName _PickUpDetail_QNAME = new QName("http://purolator.com/pws/datatypes/v1", "PickUpDetail");
    private final static QName _Float_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "float");
    private final static QName _ValidationFault_QNAME = new QName("http://www.microsoft.com/practices/EnterpriseLibrary/2007/01/wcf/validation", "ValidationFault");
    private final static QName _Long_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "long");
    private final static QName _ShipmentSummary_QNAME = new QName("http://purolator.com/pws/datatypes/v1", "ShipmentSummary");
    private final static QName _ModifyPickUpRequest_QNAME = new QName("http://purolator.com/pws/datatypes/v1", "ModifyPickUpRequest");
    private final static QName _Base64Binary_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "base64Binary");
    private final static QName _Byte_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "byte");
    private final static QName _PhoneNumber_QNAME = new QName("http://purolator.com/pws/datatypes/v1", "PhoneNumber");
    private final static QName _NotificationEmails_QNAME = new QName("http://purolator.com/pws/datatypes/v1", "NotificationEmails");
    private final static QName _Boolean_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "boolean");
    private final static QName _VoidPickUpRequestContainer_QNAME = new QName("http://purolator.com/pws/datatypes/v1", "VoidPickUpRequestContainer");
    private final static QName _PickupInstruction_QNAME = new QName("http://purolator.com/pws/datatypes/v1", "PickupInstruction");
    private final static QName _UnsignedByte_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "unsignedByte");
    private final static QName _GetPickUpHistoryRequest_QNAME = new QName("http://purolator.com/pws/datatypes/v1", "GetPickUpHistoryRequest");
    private final static QName _AnyType_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "anyType");
    private final static QName _Int_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "int");
    private final static QName _Weight_QNAME = new QName("http://purolator.com/pws/datatypes/v1", "Weight");
    private final static QName _RequestContainer_QNAME = new QName("http://purolator.com/pws/datatypes/v1", "RequestContainer");
    private final static QName _ValidatePickUpResponse_QNAME = new QName("http://purolator.com/pws/datatypes/v1", "ValidatePickUpResponse");
    private final static QName _ArrayOfPickUpDetail_QNAME = new QName("http://purolator.com/pws/datatypes/v1", "ArrayOfPickUpDetail");
    private final static QName _Double_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "double");
    private final static QName _GetPickUpHistoryResponse_QNAME = new QName("http://purolator.com/pws/datatypes/v1", "GetPickUpHistoryResponse");
    private final static QName _ModifyPickUpRequestContainer_QNAME = new QName("http://purolator.com/pws/datatypes/v1", "ModifyPickUpRequestContainer");
    private final static QName _GetPickUpHistoryResponseContainer_QNAME = new QName("http://purolator.com/pws/datatypes/v1", "GetPickUpHistoryResponseContainer");
    private final static QName _SchedulePickUpResponse_QNAME = new QName("http://purolator.com/pws/datatypes/v1", "SchedulePickUpResponse");
    private final static QName _ResponseInformation_QNAME = new QName("http://purolator.com/pws/datatypes/v1", "ResponseInformation");
    private final static QName _ModifyPickUpResponse_QNAME = new QName("http://purolator.com/pws/datatypes/v1", "ModifyPickUpResponse");
    private final static QName _ArrayOfInformationalMessage_QNAME = new QName("http://purolator.com/pws/datatypes/v1", "ArrayOfInformationalMessage");
    private final static QName _DateTime_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "dateTime");
    private final static QName _Language_QNAME = new QName("http://purolator.com/pws/datatypes/v1", "Language");
    private final static QName _QName_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "QName");
    private final static QName _SchedulePickUpResponseContainer_QNAME = new QName("http://purolator.com/pws/datatypes/v1", "SchedulePickUpResponseContainer");
    private final static QName _UnsignedShort_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "unsignedShort");
    private final static QName _Address_QNAME = new QName("http://purolator.com/pws/datatypes/v1", "Address");
    private final static QName _ResponseContext_QNAME = new QName("http://purolator.com/pws/datatypes/v1", "ResponseContext");
    private final static QName _GetPickUpHistoryRequestContainer_QNAME = new QName("http://purolator.com/pws/datatypes/v1", "GetPickUpHistoryRequestContainer");
    private final static QName _PickUpHistorySearchCriteria_QNAME = new QName("http://purolator.com/pws/datatypes/v1", "PickUpHistorySearchCriteria");
    private final static QName _ArrayOfShipmentSummaryDetail_QNAME = new QName("http://purolator.com/pws/datatypes/v1", "ArrayOfShipmentSummaryDetail");
    private final static QName _Short_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "short");
    private final static QName _Error_QNAME = new QName("http://purolator.com/pws/datatypes/v1", "Error");
    private final static QName _ShipmentSummaryDetail_QNAME = new QName("http://purolator.com/pws/datatypes/v1", "ShipmentSummaryDetail");
    private final static QName _RequestContext_QNAME = new QName("http://purolator.com/pws/datatypes/v1", "RequestContext");
    private final static QName _SchedulePickUpRequestContainer_QNAME = new QName("http://purolator.com/pws/datatypes/v1", "SchedulePickUpRequestContainer");
    private final static QName _VoidPickUpResponseContainer_QNAME = new QName("http://purolator.com/pws/datatypes/v1", "VoidPickUpResponseContainer");
    private final static QName _ArrayOfError_QNAME = new QName("http://purolator.com/pws/datatypes/v1", "ArrayOfError");
    private final static QName _WeightUnit_QNAME = new QName("http://purolator.com/pws/datatypes/v1", "WeightUnit");
    private final static QName _ValidatePickUpRequestContainer_QNAME = new QName("http://purolator.com/pws/datatypes/v1", "ValidatePickUpRequestContainer");
    private final static QName _ValidationDetail_QNAME = new QName("http://schemas.datacontract.org/2004/07/Microsoft.Practices.EnterpriseLibrary.Validation.Integration.WCF", "ValidationDetail");
    private final static QName _SchedulePickUpRequest_QNAME = new QName("http://purolator.com/pws/datatypes/v1", "SchedulePickUpRequest");
    private final static QName _UnsignedInt_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "unsignedInt");
    private final static QName _Decimal_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "decimal");
    private final static QName _ArrayOfstring_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/Arrays", "ArrayOfstring");
    private final static QName _VoidPickUpRequest_QNAME = new QName("http://purolator.com/pws/datatypes/v1", "VoidPickUpRequest");
    private final static QName _ModifyPickUpResponseContainer_QNAME = new QName("http://purolator.com/pws/datatypes/v1", "ModifyPickUpResponseContainer");
    private final static QName _Guid_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "guid");
    private final static QName _ValidatePickUpResponseContainer_QNAME = new QName("http://purolator.com/pws/datatypes/v1", "ValidatePickUpResponseContainer");
    private final static QName _Duration_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "duration");
    private final static QName _SupplyRequestCodes_QNAME = new QName("http://purolator.com/pws/datatypes/v1", "SupplyRequestCodes");
    private final static QName _String_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "string");
    private final static QName _ResponseContainer_QNAME = new QName("http://purolator.com/pws/datatypes/v1", "ResponseContainer");
    private final static QName _UnsignedLong_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "unsignedLong");
    private final static QName _ModifyPickupInstruction_QNAME = new QName("http://purolator.com/pws/datatypes/v1", "ModifyPickupInstruction");
    private final static QName _ValidatePickUpRequest_QNAME = new QName("http://purolator.com/pws/datatypes/v1", "ValidatePickUpRequest");
    private final static QName _SchedulePickUpResponseContainerPickUpConfirmationNumber_QNAME = new QName("http://purolator.com/pws/datatypes/v1", "PickUpConfirmationNumber");
    private final static QName _ValidationDetailKey_QNAME = new QName("http://schemas.datacontract.org/2004/07/Microsoft.Practices.EnterpriseLibrary.Validation.Integration.WCF", "Key");
    private final static QName _ValidationDetailMessage_QNAME = new QName("http://schemas.datacontract.org/2004/07/Microsoft.Practices.EnterpriseLibrary.Validation.Integration.WCF", "Message");
    private final static QName _ValidationDetailTag_QNAME = new QName("http://schemas.datacontract.org/2004/07/Microsoft.Practices.EnterpriseLibrary.Validation.Integration.WCF", "Tag");
    private final static QName _ModifyPickUpRequestContainerConfirmationNumber_QNAME = new QName("http://purolator.com/pws/datatypes/v1", "ConfirmationNumber");
    private final static QName _ShipmentSummaryDetailModeOfTransport_QNAME = new QName("http://purolator.com/pws/datatypes/v1", "ModeOfTransport");
    private final static QName _SchedulePickUpRequestContainerPartnerID_QNAME = new QName("http://purolator.com/pws/datatypes/v1", "PartnerID");
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
    private final static QName _PickUpHistorySearchCriteriaSortColumn_QNAME = new QName("http://purolator.com/pws/datatypes/v1", "SortColumn");
    private final static QName _PickUpHistorySearchCriteriaSortDirection_QNAME = new QName("http://purolator.com/pws/datatypes/v1", "SortDirection");
    private final static QName _PickUpHistorySearchCriteriaStatus_QNAME = new QName("http://purolator.com/pws/datatypes/v1", "Status");
    private final static QName _PhoneNumberExtension_QNAME = new QName("http://purolator.com/pws/datatypes/v1", "Extension");
    private final static QName _GetPickUpHistoryResponseContainerPickUpDetailList_QNAME = new QName("http://purolator.com/pws/datatypes/v1", "PickUpDetailList");
    private final static QName _PickupInstructionBoxesIndicator_QNAME = new QName("http://purolator.com/pws/datatypes/v1", "BoxesIndicator");
    private final static QName _PickupInstructionNumberOfSkids_QNAME = new QName("http://purolator.com/pws/datatypes/v1", "NumberOfSkids");
    private final static QName _PickupInstructionAdditionalInstructions_QNAME = new QName("http://purolator.com/pws/datatypes/v1", "AdditionalInstructions");
    private final static QName _PickupInstructionTotalWeight_QNAME = new QName("http://purolator.com/pws/datatypes/v1", "TotalWeight");
    private final static QName _PickupInstructionPickUpLocation_QNAME = new QName("http://purolator.com/pws/datatypes/v1", "PickUpLocation");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.meritconinc.shiplinx.carrier.purolator.ws.pickup.proxy
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ArrayOfstring }
     * 
     */
    public ArrayOfstring createArrayOfstring() {
        return new ArrayOfstring();
    }

    /**
     * Create an instance of {@link ArrayOfValidationDetail }
     * 
     */
    public ArrayOfValidationDetail createArrayOfValidationDetail() {
        return new ArrayOfValidationDetail();
    }

    /**
     * Create an instance of {@link ValidationDetail }
     * 
     */
    public ValidationDetail createValidationDetail() {
        return new ValidationDetail();
    }

    /**
     * Create an instance of {@link ValidationFault }
     * 
     */
    public ValidationFault createValidationFault() {
        return new ValidationFault();
    }

    /**
     * Create an instance of {@link ModifyPickupInstruction }
     * 
     */
    public ModifyPickupInstruction createModifyPickupInstruction() {
        return new ModifyPickupInstruction();
    }

    /**
     * Create an instance of {@link ValidatePickUpRequestContainer }
     * 
     */
    public ValidatePickUpRequestContainer createValidatePickUpRequestContainer() {
        return new ValidatePickUpRequestContainer();
    }

    /**
     * Create an instance of {@link ResponseInformation }
     * 
     */
    public ResponseInformation createResponseInformation() {
        return new ResponseInformation();
    }

    /**
     * Create an instance of {@link ModifyPickUpResponseContainer }
     * 
     */
    public ModifyPickUpResponseContainer createModifyPickUpResponseContainer() {
        return new ModifyPickUpResponseContainer();
    }

    /**
     * Create an instance of {@link SchedulePickUpResponseContainer }
     * 
     */
    public SchedulePickUpResponseContainer createSchedulePickUpResponseContainer() {
        return new SchedulePickUpResponseContainer();
    }

    /**
     * Create an instance of {@link SupplyRequestCodes }
     * 
     */
    public SupplyRequestCodes createSupplyRequestCodes() {
        return new SupplyRequestCodes();
    }

    /**
     * Create an instance of {@link ResponseContainer }
     * 
     */
    public ResponseContainer createResponseContainer() {
        return new ResponseContainer();
    }

    /**
     * Create an instance of {@link ModifyPickUpRequestContainer }
     * 
     */
    public ModifyPickUpRequestContainer createModifyPickUpRequestContainer() {
        return new ModifyPickUpRequestContainer();
    }

    /**
     * Create an instance of {@link GetPickUpHistoryResponseContainer }
     * 
     */
    public GetPickUpHistoryResponseContainer createGetPickUpHistoryResponseContainer() {
        return new GetPickUpHistoryResponseContainer();
    }

    /**
     * Create an instance of {@link ValidatePickUpResponseContainer }
     * 
     */
    public ValidatePickUpResponseContainer createValidatePickUpResponseContainer() {
        return new ValidatePickUpResponseContainer();
    }

    /**
     * Create an instance of {@link ArrayOfPickUpDetail }
     * 
     */
    public ArrayOfPickUpDetail createArrayOfPickUpDetail() {
        return new ArrayOfPickUpDetail();
    }

    /**
     * Create an instance of {@link VoidPickUpRequestContainer }
     * 
     */
    public VoidPickUpRequestContainer createVoidPickUpRequestContainer() {
        return new VoidPickUpRequestContainer();
    }

    /**
     * Create an instance of {@link Weight }
     * 
     */
    public Weight createWeight() {
        return new Weight();
    }

    /**
     * Create an instance of {@link RequestContainer }
     * 
     */
    public RequestContainer createRequestContainer() {
        return new RequestContainer();
    }

    /**
     * Create an instance of {@link GetPickUpHistoryRequestContainer }
     * 
     */
    public GetPickUpHistoryRequestContainer createGetPickUpHistoryRequestContainer() {
        return new GetPickUpHistoryRequestContainer();
    }

    /**
     * Create an instance of {@link SchedulePickUpRequestContainer }
     * 
     */
    public SchedulePickUpRequestContainer createSchedulePickUpRequestContainer() {
        return new SchedulePickUpRequestContainer();
    }

    /**
     * Create an instance of {@link PickupInstruction }
     * 
     */
    public PickupInstruction createPickupInstruction() {
        return new PickupInstruction();
    }

    /**
     * Create an instance of {@link NotificationEmails }
     * 
     */
    public NotificationEmails createNotificationEmails() {
        return new NotificationEmails();
    }

    /**
     * Create an instance of {@link ArrayOfError }
     * 
     */
    public ArrayOfError createArrayOfError() {
        return new ArrayOfError();
    }

    /**
     * Create an instance of {@link VoidPickUpResponseContainer }
     * 
     */
    public VoidPickUpResponseContainer createVoidPickUpResponseContainer() {
        return new VoidPickUpResponseContainer();
    }

    /**
     * Create an instance of {@link RequestContext }
     * 
     */
    public RequestContext createRequestContext() {
        return new RequestContext();
    }

    /**
     * Create an instance of {@link PhoneNumber }
     * 
     */
    public PhoneNumber createPhoneNumber() {
        return new PhoneNumber();
    }

    /**
     * Create an instance of {@link ArrayOfShipmentSummaryDetail }
     * 
     */
    public ArrayOfShipmentSummaryDetail createArrayOfShipmentSummaryDetail() {
        return new ArrayOfShipmentSummaryDetail();
    }

    /**
     * Create an instance of {@link ShipmentSummaryDetail }
     * 
     */
    public ShipmentSummaryDetail createShipmentSummaryDetail() {
        return new ShipmentSummaryDetail();
    }

    /**
     * Create an instance of {@link Error }
     * 
     */
    public Error createError() {
        return new Error();
    }

    /**
     * Create an instance of {@link ResponseContext }
     * 
     */
    public ResponseContext createResponseContext() {
        return new ResponseContext();
    }

    /**
     * Create an instance of {@link ShipmentSummary }
     * 
     */
    public ShipmentSummary createShipmentSummary() {
        return new ShipmentSummary();
    }

    /**
     * Create an instance of {@link PickUpHistorySearchCriteria }
     * 
     */
    public PickUpHistorySearchCriteria createPickUpHistorySearchCriteria() {
        return new PickUpHistorySearchCriteria();
    }

    /**
     * Create an instance of {@link InformationalMessage }
     * 
     */
    public InformationalMessage createInformationalMessage() {
        return new InformationalMessage();
    }

    /**
     * Create an instance of {@link PickUpDetail }
     * 
     */
    public PickUpDetail createPickUpDetail() {
        return new PickUpDetail();
    }

    /**
     * Create an instance of {@link Address }
     * 
     */
    public Address createAddress() {
        return new Address();
    }

    /**
     * Create an instance of {@link ArrayOfInformationalMessage }
     * 
     */
    public ArrayOfInformationalMessage createArrayOfInformationalMessage() {
        return new ArrayOfInformationalMessage();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfValidationDetail }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.microsoft.com/practices/EnterpriseLibrary/2007/01/wcf/validation", name = "Details", scope = ValidationFault.class)
    public JAXBElement<ArrayOfValidationDetail> createValidationFaultDetails(ArrayOfValidationDetail value) {
        return new JAXBElement<ArrayOfValidationDetail>(_ValidationFaultDetails_QNAME, ArrayOfValidationDetail.class, ValidationFault.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "anyURI")
    public JAXBElement<String> createAnyURI(String value) {
        return new JAXBElement<String>(_AnyURI_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link VoidPickUpResponseContainer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://purolator.com/pws/datatypes/v1", name = "VoidPickUpResponse")
    public JAXBElement<VoidPickUpResponseContainer> createVoidPickUpResponse(VoidPickUpResponseContainer value) {
        return new JAXBElement<VoidPickUpResponseContainer>(_VoidPickUpResponse_QNAME, VoidPickUpResponseContainer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "char")
    public JAXBElement<Integer> createChar(Integer value) {
        return new JAXBElement<Integer>(_Char_QNAME, Integer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfValidationDetail }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Practices.EnterpriseLibrary.Validation.Integration.WCF", name = "ArrayOfValidationDetail")
    public JAXBElement<ArrayOfValidationDetail> createArrayOfValidationDetail(ArrayOfValidationDetail value) {
        return new JAXBElement<ArrayOfValidationDetail>(_ArrayOfValidationDetail_QNAME, ArrayOfValidationDetail.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link PickUpDetail }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://purolator.com/pws/datatypes/v1", name = "PickUpDetail")
    public JAXBElement<PickUpDetail> createPickUpDetail(PickUpDetail value) {
        return new JAXBElement<PickUpDetail>(_PickUpDetail_QNAME, PickUpDetail.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Float }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "float")
    public JAXBElement<Float> createFloat(Float value) {
        return new JAXBElement<Float>(_Float_QNAME, Float.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ValidationFault }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.microsoft.com/practices/EnterpriseLibrary/2007/01/wcf/validation", name = "ValidationFault")
    public JAXBElement<ValidationFault> createValidationFault(ValidationFault value) {
        return new JAXBElement<ValidationFault>(_ValidationFault_QNAME, ValidationFault.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Long }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "long")
    public JAXBElement<Long> createLong(Long value) {
        return new JAXBElement<Long>(_Long_QNAME, Long.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ShipmentSummary }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://purolator.com/pws/datatypes/v1", name = "ShipmentSummary")
    public JAXBElement<ShipmentSummary> createShipmentSummary(ShipmentSummary value) {
        return new JAXBElement<ShipmentSummary>(_ShipmentSummary_QNAME, ShipmentSummary.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ModifyPickUpRequestContainer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://purolator.com/pws/datatypes/v1", name = "ModifyPickUpRequest")
    public JAXBElement<ModifyPickUpRequestContainer> createModifyPickUpRequest(ModifyPickUpRequestContainer value) {
        return new JAXBElement<ModifyPickUpRequestContainer>(_ModifyPickUpRequest_QNAME, ModifyPickUpRequestContainer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "base64Binary")
    public JAXBElement<byte[]> createBase64Binary(byte[] value) {
        return new JAXBElement<byte[]>(_Base64Binary_QNAME, byte[].class, null, ((byte[]) value));
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Byte }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "byte")
    public JAXBElement<Byte> createByte(Byte value) {
        return new JAXBElement<Byte>(_Byte_QNAME, Byte.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link NotificationEmails }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://purolator.com/pws/datatypes/v1", name = "NotificationEmails")
    public JAXBElement<NotificationEmails> createNotificationEmails(NotificationEmails value) {
        return new JAXBElement<NotificationEmails>(_NotificationEmails_QNAME, NotificationEmails.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "boolean")
    public JAXBElement<Boolean> createBoolean(Boolean value) {
        return new JAXBElement<Boolean>(_Boolean_QNAME, Boolean.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link VoidPickUpRequestContainer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://purolator.com/pws/datatypes/v1", name = "VoidPickUpRequestContainer")
    public JAXBElement<VoidPickUpRequestContainer> createVoidPickUpRequestContainer(VoidPickUpRequestContainer value) {
        return new JAXBElement<VoidPickUpRequestContainer>(_VoidPickUpRequestContainer_QNAME, VoidPickUpRequestContainer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PickupInstruction }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://purolator.com/pws/datatypes/v1", name = "PickupInstruction")
    public JAXBElement<PickupInstruction> createPickupInstruction(PickupInstruction value) {
        return new JAXBElement<PickupInstruction>(_PickupInstruction_QNAME, PickupInstruction.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Short }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "unsignedByte")
    public JAXBElement<Short> createUnsignedByte(Short value) {
        return new JAXBElement<Short>(_UnsignedByte_QNAME, Short.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetPickUpHistoryRequestContainer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://purolator.com/pws/datatypes/v1", name = "GetPickUpHistoryRequest")
    public JAXBElement<GetPickUpHistoryRequestContainer> createGetPickUpHistoryRequest(GetPickUpHistoryRequestContainer value) {
        return new JAXBElement<GetPickUpHistoryRequestContainer>(_GetPickUpHistoryRequest_QNAME, GetPickUpHistoryRequestContainer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "anyType")
    public JAXBElement<Object> createAnyType(Object value) {
        return new JAXBElement<Object>(_AnyType_QNAME, Object.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "int")
    public JAXBElement<Integer> createInt(Integer value) {
        return new JAXBElement<Integer>(_Int_QNAME, Integer.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link RequestContainer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://purolator.com/pws/datatypes/v1", name = "RequestContainer")
    public JAXBElement<RequestContainer> createRequestContainer(RequestContainer value) {
        return new JAXBElement<RequestContainer>(_RequestContainer_QNAME, RequestContainer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ValidatePickUpResponseContainer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://purolator.com/pws/datatypes/v1", name = "ValidatePickUpResponse")
    public JAXBElement<ValidatePickUpResponseContainer> createValidatePickUpResponse(ValidatePickUpResponseContainer value) {
        return new JAXBElement<ValidatePickUpResponseContainer>(_ValidatePickUpResponse_QNAME, ValidatePickUpResponseContainer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfPickUpDetail }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://purolator.com/pws/datatypes/v1", name = "ArrayOfPickUpDetail")
    public JAXBElement<ArrayOfPickUpDetail> createArrayOfPickUpDetail(ArrayOfPickUpDetail value) {
        return new JAXBElement<ArrayOfPickUpDetail>(_ArrayOfPickUpDetail_QNAME, ArrayOfPickUpDetail.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Double }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "double")
    public JAXBElement<Double> createDouble(Double value) {
        return new JAXBElement<Double>(_Double_QNAME, Double.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetPickUpHistoryResponseContainer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://purolator.com/pws/datatypes/v1", name = "GetPickUpHistoryResponse")
    public JAXBElement<GetPickUpHistoryResponseContainer> createGetPickUpHistoryResponse(GetPickUpHistoryResponseContainer value) {
        return new JAXBElement<GetPickUpHistoryResponseContainer>(_GetPickUpHistoryResponse_QNAME, GetPickUpHistoryResponseContainer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ModifyPickUpRequestContainer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://purolator.com/pws/datatypes/v1", name = "ModifyPickUpRequestContainer")
    public JAXBElement<ModifyPickUpRequestContainer> createModifyPickUpRequestContainer(ModifyPickUpRequestContainer value) {
        return new JAXBElement<ModifyPickUpRequestContainer>(_ModifyPickUpRequestContainer_QNAME, ModifyPickUpRequestContainer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetPickUpHistoryResponseContainer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://purolator.com/pws/datatypes/v1", name = "GetPickUpHistoryResponseContainer")
    public JAXBElement<GetPickUpHistoryResponseContainer> createGetPickUpHistoryResponseContainer(GetPickUpHistoryResponseContainer value) {
        return new JAXBElement<GetPickUpHistoryResponseContainer>(_GetPickUpHistoryResponseContainer_QNAME, GetPickUpHistoryResponseContainer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SchedulePickUpResponseContainer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://purolator.com/pws/datatypes/v1", name = "SchedulePickUpResponse")
    public JAXBElement<SchedulePickUpResponseContainer> createSchedulePickUpResponse(SchedulePickUpResponseContainer value) {
        return new JAXBElement<SchedulePickUpResponseContainer>(_SchedulePickUpResponse_QNAME, SchedulePickUpResponseContainer.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link ModifyPickUpResponseContainer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://purolator.com/pws/datatypes/v1", name = "ModifyPickUpResponse")
    public JAXBElement<ModifyPickUpResponseContainer> createModifyPickUpResponse(ModifyPickUpResponseContainer value) {
        return new JAXBElement<ModifyPickUpResponseContainer>(_ModifyPickUpResponse_QNAME, ModifyPickUpResponseContainer.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "dateTime")
    public JAXBElement<XMLGregorianCalendar> createDateTime(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_DateTime_QNAME, XMLGregorianCalendar.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link QName }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "QName")
    public JAXBElement<QName> createQName(QName value) {
        return new JAXBElement<QName>(_QName_QNAME, QName.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SchedulePickUpResponseContainer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://purolator.com/pws/datatypes/v1", name = "SchedulePickUpResponseContainer")
    public JAXBElement<SchedulePickUpResponseContainer> createSchedulePickUpResponseContainer(SchedulePickUpResponseContainer value) {
        return new JAXBElement<SchedulePickUpResponseContainer>(_SchedulePickUpResponseContainer_QNAME, SchedulePickUpResponseContainer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "unsignedShort")
    public JAXBElement<Integer> createUnsignedShort(Integer value) {
        return new JAXBElement<Integer>(_UnsignedShort_QNAME, Integer.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link GetPickUpHistoryRequestContainer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://purolator.com/pws/datatypes/v1", name = "GetPickUpHistoryRequestContainer")
    public JAXBElement<GetPickUpHistoryRequestContainer> createGetPickUpHistoryRequestContainer(GetPickUpHistoryRequestContainer value) {
        return new JAXBElement<GetPickUpHistoryRequestContainer>(_GetPickUpHistoryRequestContainer_QNAME, GetPickUpHistoryRequestContainer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PickUpHistorySearchCriteria }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://purolator.com/pws/datatypes/v1", name = "PickUpHistorySearchCriteria")
    public JAXBElement<PickUpHistorySearchCriteria> createPickUpHistorySearchCriteria(PickUpHistorySearchCriteria value) {
        return new JAXBElement<PickUpHistorySearchCriteria>(_PickUpHistorySearchCriteria_QNAME, PickUpHistorySearchCriteria.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfShipmentSummaryDetail }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://purolator.com/pws/datatypes/v1", name = "ArrayOfShipmentSummaryDetail")
    public JAXBElement<ArrayOfShipmentSummaryDetail> createArrayOfShipmentSummaryDetail(ArrayOfShipmentSummaryDetail value) {
        return new JAXBElement<ArrayOfShipmentSummaryDetail>(_ArrayOfShipmentSummaryDetail_QNAME, ArrayOfShipmentSummaryDetail.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Short }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "short")
    public JAXBElement<Short> createShort(Short value) {
        return new JAXBElement<Short>(_Short_QNAME, Short.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link ShipmentSummaryDetail }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://purolator.com/pws/datatypes/v1", name = "ShipmentSummaryDetail")
    public JAXBElement<ShipmentSummaryDetail> createShipmentSummaryDetail(ShipmentSummaryDetail value) {
        return new JAXBElement<ShipmentSummaryDetail>(_ShipmentSummaryDetail_QNAME, ShipmentSummaryDetail.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link SchedulePickUpRequestContainer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://purolator.com/pws/datatypes/v1", name = "SchedulePickUpRequestContainer")
    public JAXBElement<SchedulePickUpRequestContainer> createSchedulePickUpRequestContainer(SchedulePickUpRequestContainer value) {
        return new JAXBElement<SchedulePickUpRequestContainer>(_SchedulePickUpRequestContainer_QNAME, SchedulePickUpRequestContainer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link VoidPickUpResponseContainer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://purolator.com/pws/datatypes/v1", name = "VoidPickUpResponseContainer")
    public JAXBElement<VoidPickUpResponseContainer> createVoidPickUpResponseContainer(VoidPickUpResponseContainer value) {
        return new JAXBElement<VoidPickUpResponseContainer>(_VoidPickUpResponseContainer_QNAME, VoidPickUpResponseContainer.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link ValidatePickUpRequestContainer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://purolator.com/pws/datatypes/v1", name = "ValidatePickUpRequestContainer")
    public JAXBElement<ValidatePickUpRequestContainer> createValidatePickUpRequestContainer(ValidatePickUpRequestContainer value) {
        return new JAXBElement<ValidatePickUpRequestContainer>(_ValidatePickUpRequestContainer_QNAME, ValidatePickUpRequestContainer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ValidationDetail }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Practices.EnterpriseLibrary.Validation.Integration.WCF", name = "ValidationDetail")
    public JAXBElement<ValidationDetail> createValidationDetail(ValidationDetail value) {
        return new JAXBElement<ValidationDetail>(_ValidationDetail_QNAME, ValidationDetail.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SchedulePickUpRequestContainer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://purolator.com/pws/datatypes/v1", name = "SchedulePickUpRequest")
    public JAXBElement<SchedulePickUpRequestContainer> createSchedulePickUpRequest(SchedulePickUpRequestContainer value) {
        return new JAXBElement<SchedulePickUpRequestContainer>(_SchedulePickUpRequest_QNAME, SchedulePickUpRequestContainer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Long }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "unsignedInt")
    public JAXBElement<Long> createUnsignedInt(Long value) {
        return new JAXBElement<Long>(_UnsignedInt_QNAME, Long.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "decimal")
    public JAXBElement<BigDecimal> createDecimal(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_Decimal_QNAME, BigDecimal.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfstring }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/Arrays", name = "ArrayOfstring")
    public JAXBElement<ArrayOfstring> createArrayOfstring(ArrayOfstring value) {
        return new JAXBElement<ArrayOfstring>(_ArrayOfstring_QNAME, ArrayOfstring.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link VoidPickUpRequestContainer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://purolator.com/pws/datatypes/v1", name = "VoidPickUpRequest")
    public JAXBElement<VoidPickUpRequestContainer> createVoidPickUpRequest(VoidPickUpRequestContainer value) {
        return new JAXBElement<VoidPickUpRequestContainer>(_VoidPickUpRequest_QNAME, VoidPickUpRequestContainer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ModifyPickUpResponseContainer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://purolator.com/pws/datatypes/v1", name = "ModifyPickUpResponseContainer")
    public JAXBElement<ModifyPickUpResponseContainer> createModifyPickUpResponseContainer(ModifyPickUpResponseContainer value) {
        return new JAXBElement<ModifyPickUpResponseContainer>(_ModifyPickUpResponseContainer_QNAME, ModifyPickUpResponseContainer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "guid")
    public JAXBElement<String> createGuid(String value) {
        return new JAXBElement<String>(_Guid_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ValidatePickUpResponseContainer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://purolator.com/pws/datatypes/v1", name = "ValidatePickUpResponseContainer")
    public JAXBElement<ValidatePickUpResponseContainer> createValidatePickUpResponseContainer(ValidatePickUpResponseContainer value) {
        return new JAXBElement<ValidatePickUpResponseContainer>(_ValidatePickUpResponseContainer_QNAME, ValidatePickUpResponseContainer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Duration }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "duration")
    public JAXBElement<Duration> createDuration(Duration value) {
        return new JAXBElement<Duration>(_Duration_QNAME, Duration.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SupplyRequestCodes }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://purolator.com/pws/datatypes/v1", name = "SupplyRequestCodes")
    public JAXBElement<SupplyRequestCodes> createSupplyRequestCodes(SupplyRequestCodes value) {
        return new JAXBElement<SupplyRequestCodes>(_SupplyRequestCodes_QNAME, SupplyRequestCodes.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "string")
    public JAXBElement<String> createString(String value) {
        return new JAXBElement<String>(_String_QNAME, String.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link BigInteger }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "unsignedLong")
    public JAXBElement<BigInteger> createUnsignedLong(BigInteger value) {
        return new JAXBElement<BigInteger>(_UnsignedLong_QNAME, BigInteger.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ModifyPickupInstruction }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://purolator.com/pws/datatypes/v1", name = "ModifyPickupInstruction")
    public JAXBElement<ModifyPickupInstruction> createModifyPickupInstruction(ModifyPickupInstruction value) {
        return new JAXBElement<ModifyPickupInstruction>(_ModifyPickupInstruction_QNAME, ModifyPickupInstruction.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ValidatePickUpRequestContainer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://purolator.com/pws/datatypes/v1", name = "ValidatePickUpRequest")
    public JAXBElement<ValidatePickUpRequestContainer> createValidatePickUpRequest(ValidatePickUpRequestContainer value) {
        return new JAXBElement<ValidatePickUpRequestContainer>(_ValidatePickUpRequest_QNAME, ValidatePickUpRequestContainer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://purolator.com/pws/datatypes/v1", name = "PickUpConfirmationNumber", scope = SchedulePickUpResponseContainer.class)
    public JAXBElement<String> createSchedulePickUpResponseContainerPickUpConfirmationNumber(String value) {
        return new JAXBElement<String>(_SchedulePickUpResponseContainerPickUpConfirmationNumber_QNAME, String.class, SchedulePickUpResponseContainer.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Practices.EnterpriseLibrary.Validation.Integration.WCF", name = "Key", scope = ValidationDetail.class)
    public JAXBElement<String> createValidationDetailKey(String value) {
        return new JAXBElement<String>(_ValidationDetailKey_QNAME, String.class, ValidationDetail.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Practices.EnterpriseLibrary.Validation.Integration.WCF", name = "Message", scope = ValidationDetail.class)
    public JAXBElement<String> createValidationDetailMessage(String value) {
        return new JAXBElement<String>(_ValidationDetailMessage_QNAME, String.class, ValidationDetail.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Practices.EnterpriseLibrary.Validation.Integration.WCF", name = "Tag", scope = ValidationDetail.class)
    public JAXBElement<String> createValidationDetailTag(String value) {
        return new JAXBElement<String>(_ValidationDetailTag_QNAME, String.class, ValidationDetail.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ShipmentSummary }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://purolator.com/pws/datatypes/v1", name = "ShipmentSummary", scope = ModifyPickUpRequestContainer.class)
    public JAXBElement<ShipmentSummary> createModifyPickUpRequestContainerShipmentSummary(ShipmentSummary value) {
        return new JAXBElement<ShipmentSummary>(_ShipmentSummary_QNAME, ShipmentSummary.class, ModifyPickUpRequestContainer.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://purolator.com/pws/datatypes/v1", name = "ConfirmationNumber", scope = ModifyPickUpRequestContainer.class)
    public JAXBElement<String> createModifyPickUpRequestContainerConfirmationNumber(String value) {
        return new JAXBElement<String>(_ModifyPickUpRequestContainerConfirmationNumber_QNAME, String.class, ModifyPickUpRequestContainer.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://purolator.com/pws/datatypes/v1", name = "ModeOfTransport", scope = ShipmentSummaryDetail.class)
    public JAXBElement<String> createShipmentSummaryDetailModeOfTransport(String value) {
        return new JAXBElement<String>(_ShipmentSummaryDetailModeOfTransport_QNAME, String.class, ShipmentSummaryDetail.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://purolator.com/pws/datatypes/v1", name = "PartnerID", scope = SchedulePickUpRequestContainer.class)
    public JAXBElement<String> createSchedulePickUpRequestContainerPartnerID(String value) {
        return new JAXBElement<String>(_SchedulePickUpRequestContainerPartnerID_QNAME, String.class, SchedulePickUpRequestContainer.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link NotificationEmails }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://purolator.com/pws/datatypes/v1", name = "NotificationEmails", scope = SchedulePickUpRequestContainer.class)
    public JAXBElement<NotificationEmails> createSchedulePickUpRequestContainerNotificationEmails(NotificationEmails value) {
        return new JAXBElement<NotificationEmails>(_NotificationEmails_QNAME, NotificationEmails.class, SchedulePickUpRequestContainer.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ShipmentSummary }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://purolator.com/pws/datatypes/v1", name = "ShipmentSummary", scope = SchedulePickUpRequestContainer.class)
    public JAXBElement<ShipmentSummary> createSchedulePickUpRequestContainerShipmentSummary(ShipmentSummary value) {
        return new JAXBElement<ShipmentSummary>(_ShipmentSummary_QNAME, ShipmentSummary.class, SchedulePickUpRequestContainer.class, value);
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
    @XmlElementDecl(namespace = "http://purolator.com/pws/datatypes/v1", name = "SortColumn", scope = PickUpHistorySearchCriteria.class)
    public JAXBElement<String> createPickUpHistorySearchCriteriaSortColumn(String value) {
        return new JAXBElement<String>(_PickUpHistorySearchCriteriaSortColumn_QNAME, String.class, PickUpHistorySearchCriteria.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://purolator.com/pws/datatypes/v1", name = "SortDirection", scope = PickUpHistorySearchCriteria.class)
    public JAXBElement<String> createPickUpHistorySearchCriteriaSortDirection(String value) {
        return new JAXBElement<String>(_PickUpHistorySearchCriteriaSortDirection_QNAME, String.class, PickUpHistorySearchCriteria.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://purolator.com/pws/datatypes/v1", name = "Status", scope = PickUpHistorySearchCriteria.class)
    public JAXBElement<String> createPickUpHistorySearchCriteriaStatus(String value) {
        return new JAXBElement<String>(_PickUpHistorySearchCriteriaStatus_QNAME, String.class, PickUpHistorySearchCriteria.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://purolator.com/pws/datatypes/v1", name = "ConfirmationNumber", scope = PickUpHistorySearchCriteria.class)
    public JAXBElement<String> createPickUpHistorySearchCriteriaConfirmationNumber(String value) {
        return new JAXBElement<String>(_ModifyPickUpRequestContainerConfirmationNumber_QNAME, String.class, PickUpHistorySearchCriteria.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://purolator.com/pws/datatypes/v1", name = "PartnerID", scope = ValidatePickUpRequestContainer.class)
    public JAXBElement<String> createValidatePickUpRequestContainerPartnerID(String value) {
        return new JAXBElement<String>(_SchedulePickUpRequestContainerPartnerID_QNAME, String.class, ValidatePickUpRequestContainer.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link NotificationEmails }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://purolator.com/pws/datatypes/v1", name = "NotificationEmails", scope = ValidatePickUpRequestContainer.class)
    public JAXBElement<NotificationEmails> createValidatePickUpRequestContainerNotificationEmails(NotificationEmails value) {
        return new JAXBElement<NotificationEmails>(_NotificationEmails_QNAME, NotificationEmails.class, ValidatePickUpRequestContainer.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ShipmentSummary }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://purolator.com/pws/datatypes/v1", name = "ShipmentSummary", scope = ValidatePickUpRequestContainer.class)
    public JAXBElement<ShipmentSummary> createValidatePickUpRequestContainerShipmentSummary(ShipmentSummary value) {
        return new JAXBElement<ShipmentSummary>(_ShipmentSummary_QNAME, ShipmentSummary.class, ValidatePickUpRequestContainer.class, value);
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
    @XmlElementDecl(namespace = "http://purolator.com/pws/datatypes/v1", name = "PickUpConfirmationNumber", scope = VoidPickUpRequestContainer.class)
    public JAXBElement<String> createVoidPickUpRequestContainerPickUpConfirmationNumber(String value) {
        return new JAXBElement<String>(_SchedulePickUpResponseContainerPickUpConfirmationNumber_QNAME, String.class, VoidPickUpRequestContainer.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfPickUpDetail }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://purolator.com/pws/datatypes/v1", name = "PickUpDetailList", scope = GetPickUpHistoryResponseContainer.class)
    public JAXBElement<ArrayOfPickUpDetail> createGetPickUpHistoryResponseContainerPickUpDetailList(ArrayOfPickUpDetail value) {
        return new JAXBElement<ArrayOfPickUpDetail>(_GetPickUpHistoryResponseContainerPickUpDetailList_QNAME, ArrayOfPickUpDetail.class, GetPickUpHistoryResponseContainer.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://purolator.com/pws/datatypes/v1", name = "PickUpConfirmationNumber", scope = ModifyPickUpResponseContainer.class)
    public JAXBElement<String> createModifyPickUpResponseContainerPickUpConfirmationNumber(String value) {
        return new JAXBElement<String>(_SchedulePickUpResponseContainerPickUpConfirmationNumber_QNAME, String.class, ModifyPickUpResponseContainer.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://purolator.com/pws/datatypes/v1", name = "BoxesIndicator", scope = PickupInstruction.class)
    public JAXBElement<String> createPickupInstructionBoxesIndicator(String value) {
        return new JAXBElement<String>(_PickupInstructionBoxesIndicator_QNAME, String.class, PickupInstruction.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SupplyRequestCodes }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://purolator.com/pws/datatypes/v1", name = "SupplyRequestCodes", scope = PickupInstruction.class)
    public JAXBElement<SupplyRequestCodes> createPickupInstructionSupplyRequestCodes(SupplyRequestCodes value) {
        return new JAXBElement<SupplyRequestCodes>(_SupplyRequestCodes_QNAME, SupplyRequestCodes.class, PickupInstruction.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://purolator.com/pws/datatypes/v1", name = "NumberOfSkids", scope = PickupInstruction.class)
    public JAXBElement<Integer> createPickupInstructionNumberOfSkids(Integer value) {
        return new JAXBElement<Integer>(_PickupInstructionNumberOfSkids_QNAME, Integer.class, PickupInstruction.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://purolator.com/pws/datatypes/v1", name = "AdditionalInstructions", scope = PickupInstruction.class)
    public JAXBElement<String> createPickupInstructionAdditionalInstructions(String value) {
        return new JAXBElement<String>(_PickupInstructionAdditionalInstructions_QNAME, String.class, PickupInstruction.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Weight }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://purolator.com/pws/datatypes/v1", name = "TotalWeight", scope = PickupInstruction.class)
    public JAXBElement<Weight> createPickupInstructionTotalWeight(Weight value) {
        return new JAXBElement<Weight>(_PickupInstructionTotalWeight_QNAME, Weight.class, PickupInstruction.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://purolator.com/pws/datatypes/v1", name = "PickUpLocation", scope = PickupInstruction.class)
    public JAXBElement<String> createPickupInstructionPickUpLocation(String value) {
        return new JAXBElement<String>(_PickupInstructionPickUpLocation_QNAME, String.class, PickupInstruction.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SupplyRequestCodes }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://purolator.com/pws/datatypes/v1", name = "SupplyRequestCodes", scope = ModifyPickupInstruction.class)
    public JAXBElement<SupplyRequestCodes> createModifyPickupInstructionSupplyRequestCodes(SupplyRequestCodes value) {
        return new JAXBElement<SupplyRequestCodes>(_SupplyRequestCodes_QNAME, SupplyRequestCodes.class, ModifyPickupInstruction.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://purolator.com/pws/datatypes/v1", name = "NumberOfSkids", scope = ModifyPickupInstruction.class)
    public JAXBElement<Integer> createModifyPickupInstructionNumberOfSkids(Integer value) {
        return new JAXBElement<Integer>(_PickupInstructionNumberOfSkids_QNAME, Integer.class, ModifyPickupInstruction.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://purolator.com/pws/datatypes/v1", name = "PickUpLocation", scope = ModifyPickupInstruction.class)
    public JAXBElement<String> createModifyPickupInstructionPickUpLocation(String value) {
        return new JAXBElement<String>(_PickupInstructionPickUpLocation_QNAME, String.class, ModifyPickupInstruction.class, value);
    }

}
