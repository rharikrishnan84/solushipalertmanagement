
package com.meritconinc.shiplinx.carrier.ups.ws.freightship.proxy;

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
 *         &lt;element name="AccountType" type="{http://www.ups.com/XMLSchema/XOLTWS/FreightShip/v1.0}ShipCodeDescriptionType" minOccurs="0"/>
 *         &lt;element name="ShipFrom" type="{http://www.ups.com/XMLSchema/XOLTWS/FreightShip/v1.0}ShipFromType"/>
 *         &lt;element name="ShipperNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ShipTo" type="{http://www.ups.com/XMLSchema/XOLTWS/FreightShip/v1.0}ShipToType"/>
 *         &lt;element name="PaymentInformation" type="{http://www.ups.com/XMLSchema/XOLTWS/FreightShip/v1.0}PaymentInformationType"/>
 *         &lt;element name="ManufactureInformation" type="{http://www.ups.com/XMLSchema/XOLTWS/FreightShip/v1.0}CountryOfManufactureType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="Service" type="{http://www.ups.com/XMLSchema/XOLTWS/FreightShip/v1.0}ShipCodeDescriptionType"/>
 *         &lt;element name="HandlingUnitOne" type="{http://www.ups.com/XMLSchema/XOLTWS/FreightShip/v1.0}HandlingUnitType" minOccurs="0"/>
 *         &lt;element name="HandlingUnitTwo" type="{http://www.ups.com/XMLSchema/XOLTWS/FreightShip/v1.0}HandlingUnitType" minOccurs="0"/>
 *         &lt;element name="ExistingShipmentID" type="{http://www.ups.com/XMLSchema/XOLTWS/FreightShip/v1.0}ExistingShipmentIDType" minOccurs="0"/>
 *         &lt;element name="HandlingInstructions" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DeliveryInstructions" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PickupInstructions" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SpecialInstructions" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ShipmentTotalWeight" type="{http://www.ups.com/XMLSchema/XOLTWS/FreightShip/v1.0}ShipmentTotalWeightType" minOccurs="0"/>
 *         &lt;element name="Commodity" type="{http://www.ups.com/XMLSchema/XOLTWS/FreightShip/v1.0}CommodityType" maxOccurs="unbounded"/>
 *         &lt;element name="Reference" type="{http://www.ups.com/XMLSchema/XOLTWS/FreightShip/v1.0}ReferenceType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="ShipmentServiceOptions" type="{http://www.ups.com/XMLSchema/XOLTWS/FreightShip/v1.0}ShipmentServiceOptionsType" minOccurs="0"/>
 *         &lt;element name="PickupRequest" type="{http://www.ups.com/XMLSchema/XOLTWS/FreightShip/v1.0}PickupRequestType" minOccurs="0"/>
 *         &lt;element name="Documents" type="{http://www.ups.com/XMLSchema/XOLTWS/FreightShip/v1.0}DocumentsType" minOccurs="0"/>
 *         &lt;element name="ITNNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TaxID" type="{http://www.ups.com/XMLSchema/XOLTWS/FreightShip/v1.0}TaxIDType" minOccurs="0"/>
 *         &lt;element name="MovementReferenceNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="EICNumberAndStatement" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "accountType",
    "shipFrom",
    "shipperNumber",
    "shipTo",
    "paymentInformation",
    "manufactureInformation",
    "service",
    "handlingUnitOne",
    "handlingUnitTwo",
    "existingShipmentID",
    "handlingInstructions",
    "deliveryInstructions",
    "pickupInstructions",
    "specialInstructions",
    "shipmentTotalWeight",
    "commodity",
    "reference",
    "shipmentServiceOptions",
    "pickupRequest",
    "documents",
    "itnNumber",
    "taxID",
    "movementReferenceNumber",
    "eicNumberAndStatement"
})
public class ShipmentType {

    @XmlElement(name = "AccountType")
    protected ShipCodeDescriptionType accountType;
    @XmlElement(name = "ShipFrom", required = true)
    protected ShipFromType shipFrom;
    @XmlElement(name = "ShipperNumber", required = true)
    protected String shipperNumber;
    @XmlElement(name = "ShipTo", required = true)
    protected ShipToType shipTo;
    @XmlElement(name = "PaymentInformation", required = true)
    protected PaymentInformationType paymentInformation;
    @XmlElement(name = "ManufactureInformation")
    protected List<CountryOfManufactureType> manufactureInformation;
    @XmlElement(name = "Service", required = true)
    protected ShipCodeDescriptionType service;
    @XmlElement(name = "HandlingUnitOne")
    protected HandlingUnitType handlingUnitOne;
    @XmlElement(name = "HandlingUnitTwo")
    protected HandlingUnitType handlingUnitTwo;
    @XmlElement(name = "ExistingShipmentID")
    protected ExistingShipmentIDType existingShipmentID;
    @XmlElement(name = "HandlingInstructions")
    protected String handlingInstructions;
    @XmlElement(name = "DeliveryInstructions")
    protected String deliveryInstructions;
    @XmlElement(name = "PickupInstructions")
    protected String pickupInstructions;
    @XmlElement(name = "SpecialInstructions")
    protected String specialInstructions;
    @XmlElement(name = "ShipmentTotalWeight")
    protected ShipmentTotalWeightType shipmentTotalWeight;
    @XmlElement(name = "Commodity", required = true)
    protected List<CommodityType> commodity;
    @XmlElement(name = "Reference")
    protected List<ReferenceType> reference;
    @XmlElement(name = "ShipmentServiceOptions")
    protected ShipmentServiceOptionsType shipmentServiceOptions;
    @XmlElement(name = "PickupRequest")
    protected PickupRequestType pickupRequest;
    @XmlElement(name = "Documents")
    protected DocumentsType documents;
    @XmlElement(name = "ITNNumber")
    protected String itnNumber;
    @XmlElement(name = "TaxID")
    protected TaxIDType taxID;
    @XmlElement(name = "MovementReferenceNumber")
    protected String movementReferenceNumber;
    @XmlElement(name = "EICNumberAndStatement")
    protected String eicNumberAndStatement;

    /**
     * Gets the value of the accountType property.
     * 
     * @return
     *     possible object is
     *     {@link ShipCodeDescriptionType }
     *     
     */
    public ShipCodeDescriptionType getAccountType() {
        return accountType;
    }

    /**
     * Sets the value of the accountType property.
     * 
     * @param value
     *     allowed object is
     *     {@link ShipCodeDescriptionType }
     *     
     */
    public void setAccountType(ShipCodeDescriptionType value) {
        this.accountType = value;
    }

    /**
     * Gets the value of the shipFrom property.
     * 
     * @return
     *     possible object is
     *     {@link ShipFromType }
     *     
     */
    public ShipFromType getShipFrom() {
        return shipFrom;
    }

    /**
     * Sets the value of the shipFrom property.
     * 
     * @param value
     *     allowed object is
     *     {@link ShipFromType }
     *     
     */
    public void setShipFrom(ShipFromType value) {
        this.shipFrom = value;
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
     * Gets the value of the shipTo property.
     * 
     * @return
     *     possible object is
     *     {@link ShipToType }
     *     
     */
    public ShipToType getShipTo() {
        return shipTo;
    }

    /**
     * Sets the value of the shipTo property.
     * 
     * @param value
     *     allowed object is
     *     {@link ShipToType }
     *     
     */
    public void setShipTo(ShipToType value) {
        this.shipTo = value;
    }

    /**
     * Gets the value of the paymentInformation property.
     * 
     * @return
     *     possible object is
     *     {@link PaymentInformationType }
     *     
     */
    public PaymentInformationType getPaymentInformation() {
        return paymentInformation;
    }

    /**
     * Sets the value of the paymentInformation property.
     * 
     * @param value
     *     allowed object is
     *     {@link PaymentInformationType }
     *     
     */
    public void setPaymentInformation(PaymentInformationType value) {
        this.paymentInformation = value;
    }

    /**
     * Gets the value of the manufactureInformation property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the manufactureInformation property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getManufactureInformation().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CountryOfManufactureType }
     * 
     * 
     */
    public List<CountryOfManufactureType> getManufactureInformation() {
        if (manufactureInformation == null) {
            manufactureInformation = new ArrayList<CountryOfManufactureType>();
        }
        return this.manufactureInformation;
    }

    /**
     * Gets the value of the service property.
     * 
     * @return
     *     possible object is
     *     {@link ShipCodeDescriptionType }
     *     
     */
    public ShipCodeDescriptionType getService() {
        return service;
    }

    /**
     * Sets the value of the service property.
     * 
     * @param value
     *     allowed object is
     *     {@link ShipCodeDescriptionType }
     *     
     */
    public void setService(ShipCodeDescriptionType value) {
        this.service = value;
    }

    /**
     * Gets the value of the handlingUnitOne property.
     * 
     * @return
     *     possible object is
     *     {@link HandlingUnitType }
     *     
     */
    public HandlingUnitType getHandlingUnitOne() {
        return handlingUnitOne;
    }

    /**
     * Sets the value of the handlingUnitOne property.
     * 
     * @param value
     *     allowed object is
     *     {@link HandlingUnitType }
     *     
     */
    public void setHandlingUnitOne(HandlingUnitType value) {
        this.handlingUnitOne = value;
    }

    /**
     * Gets the value of the handlingUnitTwo property.
     * 
     * @return
     *     possible object is
     *     {@link HandlingUnitType }
     *     
     */
    public HandlingUnitType getHandlingUnitTwo() {
        return handlingUnitTwo;
    }

    /**
     * Sets the value of the handlingUnitTwo property.
     * 
     * @param value
     *     allowed object is
     *     {@link HandlingUnitType }
     *     
     */
    public void setHandlingUnitTwo(HandlingUnitType value) {
        this.handlingUnitTwo = value;
    }

    /**
     * Gets the value of the existingShipmentID property.
     * 
     * @return
     *     possible object is
     *     {@link ExistingShipmentIDType }
     *     
     */
    public ExistingShipmentIDType getExistingShipmentID() {
        return existingShipmentID;
    }

    /**
     * Sets the value of the existingShipmentID property.
     * 
     * @param value
     *     allowed object is
     *     {@link ExistingShipmentIDType }
     *     
     */
    public void setExistingShipmentID(ExistingShipmentIDType value) {
        this.existingShipmentID = value;
    }

    /**
     * Gets the value of the handlingInstructions property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHandlingInstructions() {
        return handlingInstructions;
    }

    /**
     * Sets the value of the handlingInstructions property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHandlingInstructions(String value) {
        this.handlingInstructions = value;
    }

    /**
     * Gets the value of the deliveryInstructions property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeliveryInstructions() {
        return deliveryInstructions;
    }

    /**
     * Sets the value of the deliveryInstructions property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeliveryInstructions(String value) {
        this.deliveryInstructions = value;
    }

    /**
     * Gets the value of the pickupInstructions property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPickupInstructions() {
        return pickupInstructions;
    }

    /**
     * Sets the value of the pickupInstructions property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPickupInstructions(String value) {
        this.pickupInstructions = value;
    }

    /**
     * Gets the value of the specialInstructions property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSpecialInstructions() {
        return specialInstructions;
    }

    /**
     * Sets the value of the specialInstructions property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSpecialInstructions(String value) {
        this.specialInstructions = value;
    }

    /**
     * Gets the value of the shipmentTotalWeight property.
     * 
     * @return
     *     possible object is
     *     {@link ShipmentTotalWeightType }
     *     
     */
    public ShipmentTotalWeightType getShipmentTotalWeight() {
        return shipmentTotalWeight;
    }

    /**
     * Sets the value of the shipmentTotalWeight property.
     * 
     * @param value
     *     allowed object is
     *     {@link ShipmentTotalWeightType }
     *     
     */
    public void setShipmentTotalWeight(ShipmentTotalWeightType value) {
        this.shipmentTotalWeight = value;
    }

    /**
     * Gets the value of the commodity property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the commodity property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCommodity().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CommodityType }
     * 
     * 
     */
    public List<CommodityType> getCommodity() {
        if (commodity == null) {
            commodity = new ArrayList<CommodityType>();
        }
        return this.commodity;
    }

    /**
     * Gets the value of the reference property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the reference property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getReference().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ReferenceType }
     * 
     * 
     */
    public List<ReferenceType> getReference() {
        if (reference == null) {
            reference = new ArrayList<ReferenceType>();
        }
        return this.reference;
    }

    /**
     * Gets the value of the shipmentServiceOptions property.
     * 
     * @return
     *     possible object is
     *     {@link ShipmentServiceOptionsType }
     *     
     */
    public ShipmentServiceOptionsType getShipmentServiceOptions() {
        return shipmentServiceOptions;
    }

    /**
     * Sets the value of the shipmentServiceOptions property.
     * 
     * @param value
     *     allowed object is
     *     {@link ShipmentServiceOptionsType }
     *     
     */
    public void setShipmentServiceOptions(ShipmentServiceOptionsType value) {
        this.shipmentServiceOptions = value;
    }

    /**
     * Gets the value of the pickupRequest property.
     * 
     * @return
     *     possible object is
     *     {@link PickupRequestType }
     *     
     */
    public PickupRequestType getPickupRequest() {
        return pickupRequest;
    }

    /**
     * Sets the value of the pickupRequest property.
     * 
     * @param value
     *     allowed object is
     *     {@link PickupRequestType }
     *     
     */
    public void setPickupRequest(PickupRequestType value) {
        this.pickupRequest = value;
    }

    /**
     * Gets the value of the documents property.
     * 
     * @return
     *     possible object is
     *     {@link DocumentsType }
     *     
     */
    public DocumentsType getDocuments() {
        return documents;
    }

    /**
     * Sets the value of the documents property.
     * 
     * @param value
     *     allowed object is
     *     {@link DocumentsType }
     *     
     */
    public void setDocuments(DocumentsType value) {
        this.documents = value;
    }

    /**
     * Gets the value of the itnNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getITNNumber() {
        return itnNumber;
    }

    /**
     * Sets the value of the itnNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setITNNumber(String value) {
        this.itnNumber = value;
    }

    /**
     * Gets the value of the taxID property.
     * 
     * @return
     *     possible object is
     *     {@link TaxIDType }
     *     
     */
    public TaxIDType getTaxID() {
        return taxID;
    }

    /**
     * Sets the value of the taxID property.
     * 
     * @param value
     *     allowed object is
     *     {@link TaxIDType }
     *     
     */
    public void setTaxID(TaxIDType value) {
        this.taxID = value;
    }

    /**
     * Gets the value of the movementReferenceNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMovementReferenceNumber() {
        return movementReferenceNumber;
    }

    /**
     * Sets the value of the movementReferenceNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMovementReferenceNumber(String value) {
        this.movementReferenceNumber = value;
    }

    /**
     * Gets the value of the eicNumberAndStatement property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEICNumberAndStatement() {
        return eicNumberAndStatement;
    }

    /**
     * Sets the value of the eicNumberAndStatement property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEICNumberAndStatement(String value) {
        this.eicNumberAndStatement = value;
    }

}
