
package com.meritconinc.shiplinx.carrier.ups.ws.pickup.proxy;

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
 *         &lt;element name="RatePickupIndicator" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Shipper" type="{http://www.ups.com/XMLSchema/XOLTWS/Pickup/v1.1}ShipperType" minOccurs="0"/>
 *         &lt;element name="PickupDateInfo" type="{http://www.ups.com/XMLSchema/XOLTWS/Pickup/v1.1}PickupDateInfoType"/>
 *         &lt;element name="PickupAddress" type="{http://www.ups.com/XMLSchema/XOLTWS/Pickup/v1.1}PickupAddressType"/>
 *         &lt;element name="AlternateAddressIndicator" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PickupPiece" type="{http://www.ups.com/XMLSchema/XOLTWS/Pickup/v1.1}PickupPieceType" maxOccurs="unbounded"/>
 *         &lt;element name="TotalWeight" type="{http://www.ups.com/XMLSchema/XOLTWS/Pickup/v1.1}WeightType" minOccurs="0"/>
 *         &lt;element name="OverweightIndicator" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="TrackingData" type="{http://www.ups.com/XMLSchema/XOLTWS/Pickup/v1.1}TrackingDataType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="PaymentMethod" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="SpecialInstruction" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ReferenceNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Notification" type="{http://www.ups.com/XMLSchema/XOLTWS/Pickup/v1.1}NotificationType" minOccurs="0"/>
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
    "ratePickupIndicator",
    "shipper",
    "pickupDateInfo",
    "pickupAddress",
    "alternateAddressIndicator",
    "pickupPiece",
    "totalWeight",
    "overweightIndicator",
    "trackingData",
    "paymentMethod",
    "specialInstruction",
    "referenceNumber",
    "notification"
})
@XmlRootElement(name = "PickupCreationRequest")
public class PickupCreationRequest {

    @XmlElement(name = "Request", namespace = "http://www.ups.com/XMLSchema/XOLTWS/Common/v1.0", required = true)
    protected RequestType request;
    @XmlElement(name = "RatePickupIndicator", required = true, defaultValue = "N")
    protected String ratePickupIndicator;
    @XmlElement(name = "Shipper")
    protected ShipperType shipper;
    @XmlElement(name = "PickupDateInfo", required = true)
    protected PickupDateInfoType pickupDateInfo;
    @XmlElement(name = "PickupAddress", required = true)
    protected PickupAddressType pickupAddress;
    @XmlElement(name = "AlternateAddressIndicator", required = true, defaultValue = "N")
    protected String alternateAddressIndicator;
    @XmlElement(name = "PickupPiece", required = true)
    protected List<PickupPieceType> pickupPiece;
    @XmlElement(name = "TotalWeight")
    protected WeightType totalWeight;
    @XmlElement(name = "OverweightIndicator", required = true, defaultValue = "N")
    protected String overweightIndicator;
    @XmlElement(name = "TrackingData")
    protected List<TrackingDataType> trackingData;
    @XmlElement(name = "PaymentMethod", required = true, defaultValue = "00")
    protected String paymentMethod;
    @XmlElement(name = "SpecialInstruction")
    protected String specialInstruction;
    @XmlElement(name = "ReferenceNumber")
    protected String referenceNumber;
    @XmlElement(name = "Notification")
    protected NotificationType notification;

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
     * Gets the value of the ratePickupIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRatePickupIndicator() {
        return ratePickupIndicator;
    }

    /**
     * Sets the value of the ratePickupIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRatePickupIndicator(String value) {
        this.ratePickupIndicator = value;
    }

    /**
     * Gets the value of the shipper property.
     * 
     * @return
     *     possible object is
     *     {@link ShipperType }
     *     
     */
    public ShipperType getShipper() {
        return shipper;
    }

    /**
     * Sets the value of the shipper property.
     * 
     * @param value
     *     allowed object is
     *     {@link ShipperType }
     *     
     */
    public void setShipper(ShipperType value) {
        this.shipper = value;
    }

    /**
     * Gets the value of the pickupDateInfo property.
     * 
     * @return
     *     possible object is
     *     {@link PickupDateInfoType }
     *     
     */
    public PickupDateInfoType getPickupDateInfo() {
        return pickupDateInfo;
    }

    /**
     * Sets the value of the pickupDateInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link PickupDateInfoType }
     *     
     */
    public void setPickupDateInfo(PickupDateInfoType value) {
        this.pickupDateInfo = value;
    }

    /**
     * Gets the value of the pickupAddress property.
     * 
     * @return
     *     possible object is
     *     {@link PickupAddressType }
     *     
     */
    public PickupAddressType getPickupAddress() {
        return pickupAddress;
    }

    /**
     * Sets the value of the pickupAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link PickupAddressType }
     *     
     */
    public void setPickupAddress(PickupAddressType value) {
        this.pickupAddress = value;
    }

    /**
     * Gets the value of the alternateAddressIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAlternateAddressIndicator() {
        return alternateAddressIndicator;
    }

    /**
     * Sets the value of the alternateAddressIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAlternateAddressIndicator(String value) {
        this.alternateAddressIndicator = value;
    }

    /**
     * Gets the value of the pickupPiece property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the pickupPiece property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPickupPiece().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PickupPieceType }
     * 
     * 
     */
    public List<PickupPieceType> getPickupPiece() {
        if (pickupPiece == null) {
            pickupPiece = new ArrayList<PickupPieceType>();
        }
        return this.pickupPiece;
    }

    /**
     * Gets the value of the totalWeight property.
     * 
     * @return
     *     possible object is
     *     {@link WeightType }
     *     
     */
    public WeightType getTotalWeight() {
        return totalWeight;
    }

    /**
     * Sets the value of the totalWeight property.
     * 
     * @param value
     *     allowed object is
     *     {@link WeightType }
     *     
     */
    public void setTotalWeight(WeightType value) {
        this.totalWeight = value;
    }

    /**
     * Gets the value of the overweightIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOverweightIndicator() {
        return overweightIndicator;
    }

    /**
     * Sets the value of the overweightIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOverweightIndicator(String value) {
        this.overweightIndicator = value;
    }

    /**
     * Gets the value of the trackingData property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the trackingData property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTrackingData().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TrackingDataType }
     * 
     * 
     */
    public List<TrackingDataType> getTrackingData() {
        if (trackingData == null) {
            trackingData = new ArrayList<TrackingDataType>();
        }
        return this.trackingData;
    }

    /**
     * Gets the value of the paymentMethod property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPaymentMethod() {
        return paymentMethod;
    }

    /**
     * Sets the value of the paymentMethod property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPaymentMethod(String value) {
        this.paymentMethod = value;
    }

    /**
     * Gets the value of the specialInstruction property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSpecialInstruction() {
        return specialInstruction;
    }

    /**
     * Sets the value of the specialInstruction property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSpecialInstruction(String value) {
        this.specialInstruction = value;
    }

    /**
     * Gets the value of the referenceNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReferenceNumber() {
        return referenceNumber;
    }

    /**
     * Sets the value of the referenceNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReferenceNumber(String value) {
        this.referenceNumber = value;
    }

    /**
     * Gets the value of the notification property.
     * 
     * @return
     *     possible object is
     *     {@link NotificationType }
     *     
     */
    public NotificationType getNotification() {
        return notification;
    }

    /**
     * Sets the value of the notification property.
     * 
     * @param value
     *     allowed object is
     *     {@link NotificationType }
     *     
     */
    public void setNotification(NotificationType value) {
        this.notification = value;
    }

}
