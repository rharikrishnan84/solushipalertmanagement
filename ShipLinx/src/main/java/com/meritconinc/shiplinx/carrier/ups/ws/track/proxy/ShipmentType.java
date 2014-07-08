
package com.meritconinc.shiplinx.carrier.ups.ws.track.proxy;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ShipmentType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ShipmentType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="InquiryNumber" type="{http://www.ups.com/XMLSchema/XOLTWS/Track/v2.0}CodeDescriptionValueType" minOccurs="0"/>
 *         &lt;element name="ShipmentType" type="{http://www.ups.com/XMLSchema/XOLTWS/Track/v2.0}RefShipmentType" minOccurs="0"/>
 *         &lt;element name="CandidateBookmark" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ShipperNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ShipmentAddress" type="{http://www.ups.com/XMLSchema/XOLTWS/Track/v2.0}ShipmentAddressType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="ShipmentWeight" type="{http://www.ups.com/XMLSchema/XOLTWS/Track/v2.0}WeightType" minOccurs="0"/>
 *         &lt;element name="Service" type="{http://www.ups.com/XMLSchema/XOLTWS/Track/v2.0}ServiceType" minOccurs="0"/>
 *         &lt;element name="ReferenceNumber" type="{http://www.ups.com/XMLSchema/XOLTWS/Track/v2.0}ShipmentReferenceNumberType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="CurrentStatus" type="{http://www.ups.com/XMLSchema/XOLTWS/Track/v2.0}CommonCodeDescriptionType" minOccurs="0"/>
 *         &lt;element name="PickupDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ServiceCenter" type="{http://www.ups.com/XMLSchema/XOLTWS/Track/v2.0}ServiceCenterType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="DeliveryDetail" type="{http://www.ups.com/XMLSchema/XOLTWS/Track/v2.0}DeliveryDetailType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="Volume" type="{http://www.ups.com/XMLSchema/XOLTWS/Track/v2.0}VolumeType" minOccurs="0"/>
 *         &lt;element name="BillToName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NumberOfPackagingUnit" type="{http://www.ups.com/XMLSchema/XOLTWS/Track/v2.0}NumberOfPackagingUnitType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="ShipmentServiceOption" type="{http://www.ups.com/XMLSchema/XOLTWS/Track/v2.0}ServiceOptionType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="COD" type="{http://www.ups.com/XMLSchema/XOLTWS/Track/v2.0}CODType" minOccurs="0"/>
 *         &lt;element name="SignedForByName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Activity" type="{http://www.ups.com/XMLSchema/XOLTWS/Track/v2.0}ShipmentActivityType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="OriginPortDetail" type="{http://www.ups.com/XMLSchema/XOLTWS/Track/v2.0}OriginPortDetailType" minOccurs="0"/>
 *         &lt;element name="DestinationPortDetail" type="{http://www.ups.com/XMLSchema/XOLTWS/Track/v2.0}DestinationPortDetailType" minOccurs="0"/>
 *         &lt;element name="DescriptionOfGoods" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CargoReady" type="{http://www.ups.com/XMLSchema/XOLTWS/Track/v2.0}DateTimeType" minOccurs="0"/>
 *         &lt;element name="Manifest" type="{http://www.ups.com/XMLSchema/XOLTWS/Track/v2.0}DateTimeType" minOccurs="0"/>
 *         &lt;element name="CarrierActivityInformation" type="{http://www.ups.com/XMLSchema/XOLTWS/Track/v2.0}CarrierActivityInformationType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="Document" type="{http://www.ups.com/XMLSchema/XOLTWS/Track/v2.0}DocumentType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="FileNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Appointment" type="{http://www.ups.com/XMLSchema/XOLTWS/Track/v2.0}AppointmentType" minOccurs="0"/>
 *         &lt;element name="Package" type="{http://www.ups.com/XMLSchema/XOLTWS/Track/v2.0}PackageType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="AdditionalAttribute" type="{http://www.ups.com/XMLSchema/XOLTWS/Track/v2.0}AdditionalCodeDescriptionValueType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ShipmentType", propOrder = {
    "inquiryNumber",
    "shipmentType",
    "candidateBookmark",
    "shipperNumber",
    "shipmentAddress",
    "shipmentWeight",
    "service",
    "referenceNumber",
    "currentStatus",
    "pickupDate",
    "serviceCenter",
    "deliveryDetail",
    "volume",
    "billToName",
    "numberOfPackagingUnit",
    "shipmentServiceOption",
    "cod",
    "signedForByName",
    "activity",
    "originPortDetail",
    "destinationPortDetail",
    "descriptionOfGoods",
    "cargoReady",
    "manifest",
    "carrierActivityInformation",
    "document",
    "fileNumber",
    "appointment",
    "_package",
    "additionalAttribute"
})
public class ShipmentType {

    @XmlElement(name = "InquiryNumber")
    protected CodeDescriptionValueType inquiryNumber;
    @XmlElement(name = "ShipmentType")
    protected RefShipmentType shipmentType;
    @XmlElement(name = "CandidateBookmark")
    protected String candidateBookmark;
    @XmlElement(name = "ShipperNumber")
    protected String shipperNumber;
    @XmlElement(name = "ShipmentAddress")
    protected List<ShipmentAddressType> shipmentAddress;
    @XmlElement(name = "ShipmentWeight")
    protected WeightType shipmentWeight;
    @XmlElement(name = "Service")
    protected ServiceType service;
    @XmlElement(name = "ReferenceNumber")
    protected List<ShipmentReferenceNumberType> referenceNumber;
    @XmlElement(name = "CurrentStatus")
    protected CommonCodeDescriptionType currentStatus;
    @XmlElement(name = "PickupDate")
    protected String pickupDate;
    @XmlElement(name = "ServiceCenter")
    protected List<ServiceCenterType> serviceCenter;
    @XmlElement(name = "DeliveryDetail")
    protected List<DeliveryDetailType> deliveryDetail;
    @XmlElement(name = "Volume")
    protected VolumeType volume;
    @XmlElement(name = "BillToName")
    protected String billToName;
    @XmlElement(name = "NumberOfPackagingUnit")
    protected List<NumberOfPackagingUnitType> numberOfPackagingUnit;
    @XmlElement(name = "ShipmentServiceOption")
    protected List<ServiceOptionType> shipmentServiceOption;
    @XmlElement(name = "COD")
    protected CODType cod;
    @XmlElement(name = "SignedForByName")
    protected String signedForByName;
    @XmlElement(name = "Activity")
    protected List<ShipmentActivityType> activity;
    @XmlElement(name = "OriginPortDetail")
    protected OriginPortDetailType originPortDetail;
    @XmlElement(name = "DestinationPortDetail")
    protected DestinationPortDetailType destinationPortDetail;
    @XmlElement(name = "DescriptionOfGoods")
    protected String descriptionOfGoods;
    @XmlElement(name = "CargoReady")
    protected DateTimeType cargoReady;
    @XmlElement(name = "Manifest")
    protected DateTimeType manifest;
    @XmlElement(name = "CarrierActivityInformation")
    protected List<CarrierActivityInformationType> carrierActivityInformation;
    @XmlElement(name = "Document")
    protected List<DocumentType> document;
    @XmlElement(name = "FileNumber")
    protected String fileNumber;
    @XmlElement(name = "Appointment")
    protected AppointmentType appointment;
    @XmlElement(name = "Package")
    protected List<PackageType> _package;
    @XmlElement(name = "AdditionalAttribute")
    protected List<AdditionalCodeDescriptionValueType> additionalAttribute;

    /**
     * Gets the value of the inquiryNumber property.
     * 
     * @return
     *     possible object is
     *     {@link CodeDescriptionValueType }
     *     
     */
    public CodeDescriptionValueType getInquiryNumber() {
        return inquiryNumber;
    }

    /**
     * Sets the value of the inquiryNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link CodeDescriptionValueType }
     *     
     */
    public void setInquiryNumber(CodeDescriptionValueType value) {
        this.inquiryNumber = value;
    }

    /**
     * Gets the value of the shipmentType property.
     * 
     * @return
     *     possible object is
     *     {@link RefShipmentType }
     *     
     */
    public RefShipmentType getShipmentType() {
        return shipmentType;
    }

    /**
     * Sets the value of the shipmentType property.
     * 
     * @param value
     *     allowed object is
     *     {@link RefShipmentType }
     *     
     */
    public void setShipmentType(RefShipmentType value) {
        this.shipmentType = value;
    }

    /**
     * Gets the value of the candidateBookmark property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCandidateBookmark() {
        return candidateBookmark;
    }

    /**
     * Sets the value of the candidateBookmark property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCandidateBookmark(String value) {
        this.candidateBookmark = value;
    }

    /**
     * Gets the value of the shipperNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShipperNumber() {
        return shipperNumber;
    }

    /**
     * Sets the value of the shipperNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShipperNumber(String value) {
        this.shipperNumber = value;
    }

    /**
     * Gets the value of the shipmentAddress property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the shipmentAddress property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getShipmentAddress().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ShipmentAddressType }
     * 
     * 
     */
    public List<ShipmentAddressType> getShipmentAddress() {
        if (shipmentAddress == null) {
            shipmentAddress = new ArrayList<ShipmentAddressType>();
        }
        return this.shipmentAddress;
    }

    /**
     * Gets the value of the shipmentWeight property.
     * 
     * @return
     *     possible object is
     *     {@link WeightType }
     *     
     */
    public WeightType getShipmentWeight() {
        return shipmentWeight;
    }

    /**
     * Sets the value of the shipmentWeight property.
     * 
     * @param value
     *     allowed object is
     *     {@link WeightType }
     *     
     */
    public void setShipmentWeight(WeightType value) {
        this.shipmentWeight = value;
    }

    /**
     * Gets the value of the service property.
     * 
     * @return
     *     possible object is
     *     {@link ServiceType }
     *     
     */
    public ServiceType getService() {
        return service;
    }

    /**
     * Sets the value of the service property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceType }
     *     
     */
    public void setService(ServiceType value) {
        this.service = value;
    }

    /**
     * Gets the value of the referenceNumber property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the referenceNumber property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getReferenceNumber().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ShipmentReferenceNumberType }
     * 
     * 
     */
    public List<ShipmentReferenceNumberType> getReferenceNumber() {
        if (referenceNumber == null) {
            referenceNumber = new ArrayList<ShipmentReferenceNumberType>();
        }
        return this.referenceNumber;
    }

    /**
     * Gets the value of the currentStatus property.
     * 
     * @return
     *     possible object is
     *     {@link CommonCodeDescriptionType }
     *     
     */
    public CommonCodeDescriptionType getCurrentStatus() {
        return currentStatus;
    }

    /**
     * Sets the value of the currentStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link CommonCodeDescriptionType }
     *     
     */
    public void setCurrentStatus(CommonCodeDescriptionType value) {
        this.currentStatus = value;
    }

    /**
     * Gets the value of the pickupDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPickupDate() {
        return pickupDate;
    }

    /**
     * Sets the value of the pickupDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPickupDate(String value) {
        this.pickupDate = value;
    }

    /**
     * Gets the value of the serviceCenter property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the serviceCenter property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getServiceCenter().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ServiceCenterType }
     * 
     * 
     */
    public List<ServiceCenterType> getServiceCenter() {
        if (serviceCenter == null) {
            serviceCenter = new ArrayList<ServiceCenterType>();
        }
        return this.serviceCenter;
    }

    /**
     * Gets the value of the deliveryDetail property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the deliveryDetail property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDeliveryDetail().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DeliveryDetailType }
     * 
     * 
     */
    public List<DeliveryDetailType> getDeliveryDetail() {
        if (deliveryDetail == null) {
            deliveryDetail = new ArrayList<DeliveryDetailType>();
        }
        return this.deliveryDetail;
    }

    /**
     * Gets the value of the volume property.
     * 
     * @return
     *     possible object is
     *     {@link VolumeType }
     *     
     */
    public VolumeType getVolume() {
        return volume;
    }

    /**
     * Sets the value of the volume property.
     * 
     * @param value
     *     allowed object is
     *     {@link VolumeType }
     *     
     */
    public void setVolume(VolumeType value) {
        this.volume = value;
    }

    /**
     * Gets the value of the billToName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBillToName() {
        return billToName;
    }

    /**
     * Sets the value of the billToName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBillToName(String value) {
        this.billToName = value;
    }

    /**
     * Gets the value of the numberOfPackagingUnit property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the numberOfPackagingUnit property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getNumberOfPackagingUnit().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link NumberOfPackagingUnitType }
     * 
     * 
     */
    public List<NumberOfPackagingUnitType> getNumberOfPackagingUnit() {
        if (numberOfPackagingUnit == null) {
            numberOfPackagingUnit = new ArrayList<NumberOfPackagingUnitType>();
        }
        return this.numberOfPackagingUnit;
    }

    /**
     * Gets the value of the shipmentServiceOption property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the shipmentServiceOption property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getShipmentServiceOption().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ServiceOptionType }
     * 
     * 
     */
    public List<ServiceOptionType> getShipmentServiceOption() {
        if (shipmentServiceOption == null) {
            shipmentServiceOption = new ArrayList<ServiceOptionType>();
        }
        return this.shipmentServiceOption;
    }

    /**
     * Gets the value of the cod property.
     * 
     * @return
     *     possible object is
     *     {@link CODType }
     *     
     */
    public CODType getCOD() {
        return cod;
    }

    /**
     * Sets the value of the cod property.
     * 
     * @param value
     *     allowed object is
     *     {@link CODType }
     *     
     */
    public void setCOD(CODType value) {
        this.cod = value;
    }

    /**
     * Gets the value of the signedForByName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSignedForByName() {
        return signedForByName;
    }

    /**
     * Sets the value of the signedForByName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSignedForByName(String value) {
        this.signedForByName = value;
    }

    /**
     * Gets the value of the activity property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the activity property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getActivity().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ShipmentActivityType }
     * 
     * 
     */
    public List<ShipmentActivityType> getActivity() {
        if (activity == null) {
            activity = new ArrayList<ShipmentActivityType>();
        }
        return this.activity;
    }

    /**
     * Gets the value of the originPortDetail property.
     * 
     * @return
     *     possible object is
     *     {@link OriginPortDetailType }
     *     
     */
    public OriginPortDetailType getOriginPortDetail() {
        return originPortDetail;
    }

    /**
     * Sets the value of the originPortDetail property.
     * 
     * @param value
     *     allowed object is
     *     {@link OriginPortDetailType }
     *     
     */
    public void setOriginPortDetail(OriginPortDetailType value) {
        this.originPortDetail = value;
    }

    /**
     * Gets the value of the destinationPortDetail property.
     * 
     * @return
     *     possible object is
     *     {@link DestinationPortDetailType }
     *     
     */
    public DestinationPortDetailType getDestinationPortDetail() {
        return destinationPortDetail;
    }

    /**
     * Sets the value of the destinationPortDetail property.
     * 
     * @param value
     *     allowed object is
     *     {@link DestinationPortDetailType }
     *     
     */
    public void setDestinationPortDetail(DestinationPortDetailType value) {
        this.destinationPortDetail = value;
    }

    /**
     * Gets the value of the descriptionOfGoods property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescriptionOfGoods() {
        return descriptionOfGoods;
    }

    /**
     * Sets the value of the descriptionOfGoods property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescriptionOfGoods(String value) {
        this.descriptionOfGoods = value;
    }

    /**
     * Gets the value of the cargoReady property.
     * 
     * @return
     *     possible object is
     *     {@link DateTimeType }
     *     
     */
    public DateTimeType getCargoReady() {
        return cargoReady;
    }

    /**
     * Sets the value of the cargoReady property.
     * 
     * @param value
     *     allowed object is
     *     {@link DateTimeType }
     *     
     */
    public void setCargoReady(DateTimeType value) {
        this.cargoReady = value;
    }

    /**
     * Gets the value of the manifest property.
     * 
     * @return
     *     possible object is
     *     {@link DateTimeType }
     *     
     */
    public DateTimeType getManifest() {
        return manifest;
    }

    /**
     * Sets the value of the manifest property.
     * 
     * @param value
     *     allowed object is
     *     {@link DateTimeType }
     *     
     */
    public void setManifest(DateTimeType value) {
        this.manifest = value;
    }

    /**
     * Gets the value of the carrierActivityInformation property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the carrierActivityInformation property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCarrierActivityInformation().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CarrierActivityInformationType }
     * 
     * 
     */
    public List<CarrierActivityInformationType> getCarrierActivityInformation() {
        if (carrierActivityInformation == null) {
            carrierActivityInformation = new ArrayList<CarrierActivityInformationType>();
        }
        return this.carrierActivityInformation;
    }

    /**
     * Gets the value of the document property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the document property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDocument().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DocumentType }
     * 
     * 
     */
    public List<DocumentType> getDocument() {
        if (document == null) {
            document = new ArrayList<DocumentType>();
        }
        return this.document;
    }

    /**
     * Gets the value of the fileNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFileNumber() {
        return fileNumber;
    }

    /**
     * Sets the value of the fileNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFileNumber(String value) {
        this.fileNumber = value;
    }

    /**
     * Gets the value of the appointment property.
     * 
     * @return
     *     possible object is
     *     {@link AppointmentType }
     *     
     */
    public AppointmentType getAppointment() {
        return appointment;
    }

    /**
     * Sets the value of the appointment property.
     * 
     * @param value
     *     allowed object is
     *     {@link AppointmentType }
     *     
     */
    public void setAppointment(AppointmentType value) {
        this.appointment = value;
    }

    /**
     * Gets the value of the package property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the package property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPackage().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PackageType }
     * 
     * 
     */
    public List<PackageType> getPackage() {
        if (_package == null) {
            _package = new ArrayList<PackageType>();
        }
        return this._package;
    }

    /**
     * Gets the value of the additionalAttribute property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the additionalAttribute property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAdditionalAttribute().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AdditionalCodeDescriptionValueType }
     * 
     * 
     */
    public List<AdditionalCodeDescriptionValueType> getAdditionalAttribute() {
        if (additionalAttribute == null) {
            additionalAttribute = new ArrayList<AdditionalCodeDescriptionValueType>();
        }
        return this.additionalAttribute;
    }

}
