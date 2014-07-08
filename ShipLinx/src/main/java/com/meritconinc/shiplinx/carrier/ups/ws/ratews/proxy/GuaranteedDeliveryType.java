
package com.meritconinc.shiplinx.carrier.ups.ws.ratews.proxy;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for GuaranteedDeliveryType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GuaranteedDeliveryType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="BusinessDaysInTransit" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DeliveryByTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GuaranteedDeliveryType", propOrder = {
    "businessDaysInTransit",
    "deliveryByTime"
})
public class GuaranteedDeliveryType {

    @XmlElement(name = "BusinessDaysInTransit")
    protected String businessDaysInTransit;
    @XmlElement(name = "DeliveryByTime")
    protected String deliveryByTime;

    /**
     * Gets the value of the businessDaysInTransit property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBusinessDaysInTransit() {
        return businessDaysInTransit;
    }

    /**
     * Sets the value of the businessDaysInTransit property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBusinessDaysInTransit(String value) {
        this.businessDaysInTransit = value;
    }

    /**
     * Gets the value of the deliveryByTime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeliveryByTime() {
        return deliveryByTime;
    }

    /**
     * Sets the value of the deliveryByTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeliveryByTime(String value) {
        this.deliveryByTime = value;
    }

}
