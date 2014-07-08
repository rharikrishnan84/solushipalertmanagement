
package com.meritconinc.shiplinx.carrier.ups.ws.ratews.proxy;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ShipmentServiceOptionsType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ShipmentServiceOptionsType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SaturdayPickupIndicator" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SaturdayDeliveryIndicator" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="OnCallPickup" type="{http://www.ups.com/XMLSchema/XOLTWS/Rate/v1.1}OnCallPickupType" minOccurs="0"/>
 *         &lt;element name="COD" type="{http://www.ups.com/XMLSchema/XOLTWS/Rate/v1.1}CODType" minOccurs="0"/>
 *         &lt;element name="DeliveryConfirmation" type="{http://www.ups.com/XMLSchema/XOLTWS/Rate/v1.1}DeliveryConfirmationType" minOccurs="0"/>
 *         &lt;element name="ReturnOfDocumentIndicator" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="UPScarbonneutralIndicator" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ShipmentServiceOptionsType", propOrder = {
    "saturdayPickupIndicator",
    "saturdayDeliveryIndicator",
    "onCallPickup",
    "cod",
    "deliveryConfirmation",
    "returnOfDocumentIndicator",
    "upScarbonneutralIndicator"
})
public class ShipmentServiceOptionsType {

    @XmlElement(name = "SaturdayPickupIndicator")
    protected String saturdayPickupIndicator;
    @XmlElement(name = "SaturdayDeliveryIndicator")
    protected String saturdayDeliveryIndicator;
    @XmlElement(name = "OnCallPickup")
    protected OnCallPickupType onCallPickup;
    @XmlElement(name = "COD")
    protected CODType cod;
    @XmlElement(name = "DeliveryConfirmation")
    protected DeliveryConfirmationType deliveryConfirmation;
    @XmlElement(name = "ReturnOfDocumentIndicator")
    protected String returnOfDocumentIndicator;
    @XmlElement(name = "UPScarbonneutralIndicator")
    protected String upScarbonneutralIndicator;

    /**
     * Gets the value of the saturdayPickupIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSaturdayPickupIndicator() {
        return saturdayPickupIndicator;
    }

    /**
     * Sets the value of the saturdayPickupIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSaturdayPickupIndicator(String value) {
        this.saturdayPickupIndicator = value;
    }

    /**
     * Gets the value of the saturdayDeliveryIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSaturdayDeliveryIndicator() {
        return saturdayDeliveryIndicator;
    }

    /**
     * Sets the value of the saturdayDeliveryIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSaturdayDeliveryIndicator(String value) {
        this.saturdayDeliveryIndicator = value;
    }

    /**
     * Gets the value of the onCallPickup property.
     * 
     * @return
     *     possible object is
     *     {@link OnCallPickupType }
     *     
     */
    public OnCallPickupType getOnCallPickup() {
        return onCallPickup;
    }

    /**
     * Sets the value of the onCallPickup property.
     * 
     * @param value
     *     allowed object is
     *     {@link OnCallPickupType }
     *     
     */
    public void setOnCallPickup(OnCallPickupType value) {
        this.onCallPickup = value;
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
     * Gets the value of the deliveryConfirmation property.
     * 
     * @return
     *     possible object is
     *     {@link DeliveryConfirmationType }
     *     
     */
    public DeliveryConfirmationType getDeliveryConfirmation() {
        return deliveryConfirmation;
    }

    /**
     * Sets the value of the deliveryConfirmation property.
     * 
     * @param value
     *     allowed object is
     *     {@link DeliveryConfirmationType }
     *     
     */
    public void setDeliveryConfirmation(DeliveryConfirmationType value) {
        this.deliveryConfirmation = value;
    }

    /**
     * Gets the value of the returnOfDocumentIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReturnOfDocumentIndicator() {
        return returnOfDocumentIndicator;
    }

    /**
     * Sets the value of the returnOfDocumentIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReturnOfDocumentIndicator(String value) {
        this.returnOfDocumentIndicator = value;
    }

    /**
     * Gets the value of the upScarbonneutralIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUPScarbonneutralIndicator() {
        return upScarbonneutralIndicator;
    }

    /**
     * Sets the value of the upScarbonneutralIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUPScarbonneutralIndicator(String value) {
        this.upScarbonneutralIndicator = value;
    }

}
