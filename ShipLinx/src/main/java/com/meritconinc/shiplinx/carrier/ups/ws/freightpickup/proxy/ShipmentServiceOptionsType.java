
package com.meritconinc.shiplinx.carrier.ups.ws.freightpickup.proxy;

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
 *         &lt;element name="FreezableProtectionIndicator" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="LimitedAccessPickupIndicator" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="LimitedAccessDeliveryIndicator" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ExtremeLengthIndicator" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "freezableProtectionIndicator",
    "limitedAccessPickupIndicator",
    "limitedAccessDeliveryIndicator",
    "extremeLengthIndicator"
})
public class ShipmentServiceOptionsType {

    @XmlElement(name = "FreezableProtectionIndicator")
    protected String freezableProtectionIndicator;
    @XmlElement(name = "LimitedAccessPickupIndicator")
    protected String limitedAccessPickupIndicator;
    @XmlElement(name = "LimitedAccessDeliveryIndicator")
    protected String limitedAccessDeliveryIndicator;
    @XmlElement(name = "ExtremeLengthIndicator")
    protected String extremeLengthIndicator;

    /**
     * Gets the value of the freezableProtectionIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFreezableProtectionIndicator() {
        return freezableProtectionIndicator;
    }

    /**
     * Sets the value of the freezableProtectionIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFreezableProtectionIndicator(String value) {
        this.freezableProtectionIndicator = value;
    }

    /**
     * Gets the value of the limitedAccessPickupIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLimitedAccessPickupIndicator() {
        return limitedAccessPickupIndicator;
    }

    /**
     * Sets the value of the limitedAccessPickupIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLimitedAccessPickupIndicator(String value) {
        this.limitedAccessPickupIndicator = value;
    }

    /**
     * Gets the value of the limitedAccessDeliveryIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLimitedAccessDeliveryIndicator() {
        return limitedAccessDeliveryIndicator;
    }

    /**
     * Sets the value of the limitedAccessDeliveryIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLimitedAccessDeliveryIndicator(String value) {
        this.limitedAccessDeliveryIndicator = value;
    }

    /**
     * Gets the value of the extremeLengthIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExtremeLengthIndicator() {
        return extremeLengthIndicator;
    }

    /**
     * Sets the value of the extremeLengthIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExtremeLengthIndicator(String value) {
        this.extremeLengthIndicator = value;
    }

}
