
package com.meritconinc.shiplinx.carrier.purolator.ws.tracking.proxy;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * UndeliverableScanDetails
 * 
 * <p>Java class for UndeliverableScanDetails complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="UndeliverableScanDetails">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="AttemptedDeliveryAddress" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="DeliveryCompanyName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UndeliverableScanDetails", propOrder = {
    "attemptedDeliveryAddress",
    "deliveryCompanyName"
})
public class UndeliverableScanDetails {

    @XmlElement(name = "AttemptedDeliveryAddress", required = true, nillable = true)
    protected String attemptedDeliveryAddress;
    @XmlElement(name = "DeliveryCompanyName", required = true, nillable = true)
    protected String deliveryCompanyName;

    /**
     * Gets the value of the attemptedDeliveryAddress property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAttemptedDeliveryAddress() {
        return attemptedDeliveryAddress;
    }

    /**
     * Sets the value of the attemptedDeliveryAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAttemptedDeliveryAddress(String value) {
        this.attemptedDeliveryAddress = value;
    }

    /**
     * Gets the value of the deliveryCompanyName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeliveryCompanyName() {
        return deliveryCompanyName;
    }

    /**
     * Sets the value of the deliveryCompanyName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeliveryCompanyName(String value) {
        this.deliveryCompanyName = value;
    }

}
