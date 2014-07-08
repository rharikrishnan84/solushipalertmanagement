
package com.meritconinc.shiplinx.carrier.ups.ws.pickup.proxy;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PickupDateInfoType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PickupDateInfoType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CloseTime" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ReadyTime" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PickupDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PickupDateInfoType", propOrder = {
    "closeTime",
    "readyTime",
    "pickupDate"
})
public class PickupDateInfoType {

    @XmlElement(name = "CloseTime", required = true)
    protected String closeTime;
    @XmlElement(name = "ReadyTime", required = true)
    protected String readyTime;
    @XmlElement(name = "PickupDate", required = true)
    protected String pickupDate;

    /**
     * Gets the value of the closeTime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCloseTime() {
        return closeTime;
    }

    /**
     * Sets the value of the closeTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCloseTime(String value) {
        this.closeTime = value;
    }

    /**
     * Gets the value of the readyTime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReadyTime() {
        return readyTime;
    }

    /**
     * Sets the value of the readyTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReadyTime(String value) {
        this.readyTime = value;
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

}
