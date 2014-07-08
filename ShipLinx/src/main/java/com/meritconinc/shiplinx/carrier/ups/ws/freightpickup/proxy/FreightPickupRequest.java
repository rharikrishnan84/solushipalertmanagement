
package com.meritconinc.shiplinx.carrier.ups.ws.freightpickup.proxy;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.ups.com/XMLSchema/XOLTWS/Common/v1.0}Request"/>
 *         &lt;element name="PickupRequestConfirmationNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DestinationPostalCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DestinationCountryCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Requester" type="{http://www.ups.com/XMLSchema/XOLTWS/FreightPickup/v1.0}RequesterType"/>
 *         &lt;element name="ShipFrom" type="{http://www.ups.com/XMLSchema/XOLTWS/FreightPickup/v1.0}ShipFromType"/>
 *         &lt;element name="ShipTo" type="{http://www.ups.com/XMLSchema/XOLTWS/FreightPickup/v1.0}ShipToType"/>
 *         &lt;element name="PickupDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="EarliestTimeReady" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="LatestTimeReady" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ShipmentServiceOptions" type="{http://www.ups.com/XMLSchema/XOLTWS/FreightPickup/v1.0}ShipmentServiceOptionsType" minOccurs="0"/>
 *         &lt;element name="ShipmentDetail" type="{http://www.ups.com/XMLSchema/XOLTWS/FreightPickup/v1.0}ShipmentDetailType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="ExistingShipmentID" type="{http://www.ups.com/XMLSchema/XOLTWS/FreightPickup/v1.0}ExistingShipmentIDType" minOccurs="0"/>
 *         &lt;element name="POM" type="{http://www.ups.com/XMLSchema/XOLTWS/FreightPickup/v1.0}POMType" minOccurs="0"/>
 *         &lt;element name="PickupInstructions" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="AdditionalComments" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="HandlingInstructions" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SpecialInstructions" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DeliveryInstructions" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "request",
    "pickupRequestConfirmationNumber",
    "destinationPostalCode",
    "destinationCountryCode",
    "requester",
    "shipFrom",
    "shipTo",
    "pickupDate",
    "earliestTimeReady",
    "latestTimeReady",
    "shipmentServiceOptions",
    "shipmentDetail",
    "existingShipmentID",
    "pom",
    "pickupInstructions",
    "additionalComments",
    "handlingInstructions",
    "specialInstructions",
    "deliveryInstructions"
})
@XmlRootElement(name = "FreightPickupRequest")
public class FreightPickupRequest {

    @XmlElement(name = "Request", namespace = "http://www.ups.com/XMLSchema/XOLTWS/Common/v1.0", required = true)
    protected RequestType request;
    @XmlElement(name = "PickupRequestConfirmationNumber")
    protected String pickupRequestConfirmationNumber;
    @XmlElement(name = "DestinationPostalCode")
    protected String destinationPostalCode;
    @XmlElement(name = "DestinationCountryCode", required = true)
    protected String destinationCountryCode;
    @XmlElement(name = "Requester", required = true)
    protected RequesterType requester;
    @XmlElement(name = "ShipFrom", required = true)
    protected ShipFromType shipFrom;
    @XmlElement(name = "ShipTo", required = true)
    protected ShipToType shipTo;
    @XmlElement(name = "PickupDate", required = true)
    protected String pickupDate;
    @XmlElement(name = "EarliestTimeReady", required = true)
    protected String earliestTimeReady;
    @XmlElement(name = "LatestTimeReady", required = true)
    protected String latestTimeReady;
    @XmlElement(name = "ShipmentServiceOptions")
    protected ShipmentServiceOptionsType shipmentServiceOptions;
    @XmlElement(name = "ShipmentDetail")
    protected List<ShipmentDetailType> shipmentDetail;
    @XmlElement(name = "ExistingShipmentID")
    protected ExistingShipmentIDType existingShipmentID;
    @XmlElement(name = "POM")
    protected POMType pom;
    @XmlElement(name = "PickupInstructions")
    protected String pickupInstructions;
    @XmlElement(name = "AdditionalComments")
    protected String additionalComments;
    @XmlElement(name = "HandlingInstructions")
    protected String handlingInstructions;
    @XmlElement(name = "SpecialInstructions")
    protected String specialInstructions;
    @XmlElement(name = "DeliveryInstructions")
    protected String deliveryInstructions;

    /**
     * Gets the value of the request property.
     * 
     * @return
     *     possible object is
     *     {@link RequestType }
     *     
     */
    public RequestType getRequest() {
        return request;
    }

    /**
     * Sets the value of the request property.
     * 
     * @param value
     *     allowed object is
     *     {@link RequestType }
     *     
     */
    public void setRequest(RequestType value) {
        this.request = value;
    }

    /**
     * Gets the value of the pickupRequestConfirmationNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPickupRequestConfirmationNumber() {
        return pickupRequestConfirmationNumber;
    }

    /**
     * Sets the value of the pickupRequestConfirmationNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPickupRequestConfirmationNumber(String value) {
        this.pickupRequestConfirmationNumber = value;
    }

    /**
     * Gets the value of the destinationPostalCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDestinationPostalCode() {
        return destinationPostalCode;
    }

    /**
     * Sets the value of the destinationPostalCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDestinationPostalCode(String value) {
        this.destinationPostalCode = value;
    }

    /**
     * Gets the value of the destinationCountryCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDestinationCountryCode() {
        return destinationCountryCode;
    }

    /**
     * Sets the value of the destinationCountryCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDestinationCountryCode(String value) {
        this.destinationCountryCode = value;
    }

    /**
     * Gets the value of the requester property.
     * 
     * @return
     *     possible object is
     *     {@link RequesterType }
     *     
     */
    public RequesterType getRequester() {
        return requester;
    }

    /**
     * Sets the value of the requester property.
     * 
     * @param value
     *     allowed object is
     *     {@link RequesterType }
     *     
     */
    public void setRequester(RequesterType value) {
        this.requester = value;
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
     * Gets the value of the earliestTimeReady property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEarliestTimeReady() {
        return earliestTimeReady;
    }

    /**
     * Sets the value of the earliestTimeReady property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEarliestTimeReady(String value) {
        this.earliestTimeReady = value;
    }

    /**
     * Gets the value of the latestTimeReady property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLatestTimeReady() {
        return latestTimeReady;
    }

    /**
     * Sets the value of the latestTimeReady property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLatestTimeReady(String value) {
        this.latestTimeReady = value;
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
     * Gets the value of the shipmentDetail property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the shipmentDetail property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getShipmentDetail().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ShipmentDetailType }
     * 
     * 
     */
    public List<ShipmentDetailType> getShipmentDetail() {
        if (shipmentDetail == null) {
            shipmentDetail = new ArrayList<ShipmentDetailType>();
        }
        return this.shipmentDetail;
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
     * Gets the value of the pom property.
     * 
     * @return
     *     possible object is
     *     {@link POMType }
     *     
     */
    public POMType getPOM() {
        return pom;
    }

    /**
     * Sets the value of the pom property.
     * 
     * @param value
     *     allowed object is
     *     {@link POMType }
     *     
     */
    public void setPOM(POMType value) {
        this.pom = value;
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
     * Gets the value of the additionalComments property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAdditionalComments() {
        return additionalComments;
    }

    /**
     * Sets the value of the additionalComments property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAdditionalComments(String value) {
        this.additionalComments = value;
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

}
