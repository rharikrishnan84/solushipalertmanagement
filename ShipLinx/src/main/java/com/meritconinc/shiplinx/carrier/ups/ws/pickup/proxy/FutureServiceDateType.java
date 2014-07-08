
package com.meritconinc.shiplinx.carrier.ups.ws.pickup.proxy;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for FutureServiceDateType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="FutureServiceDateType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ServiceDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CallByTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="EarliestCloseTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="LeadTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="GWNIndicator" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FutureServiceDateType", propOrder = {
    "serviceDate",
    "callByTime",
    "earliestCloseTime",
    "leadTime",
    "gwnIndicator"
})
public class FutureServiceDateType {

    @XmlElement(name = "ServiceDate", required = true)
    protected String serviceDate;
    @XmlElement(name = "CallByTime")
    protected String callByTime;
    @XmlElement(name = "EarliestCloseTime")
    protected String earliestCloseTime;
    @XmlElement(name = "LeadTime")
    protected String leadTime;
    @XmlElement(name = "GWNIndicator")
    protected String gwnIndicator;

    /**
     * Gets the value of the serviceDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServiceDate() {
        return serviceDate;
    }

    /**
     * Sets the value of the serviceDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServiceDate(String value) {
        this.serviceDate = value;
    }

    /**
     * Gets the value of the callByTime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCallByTime() {
        return callByTime;
    }

    /**
     * Sets the value of the callByTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCallByTime(String value) {
        this.callByTime = value;
    }

    /**
     * Gets the value of the earliestCloseTime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEarliestCloseTime() {
        return earliestCloseTime;
    }

    /**
     * Sets the value of the earliestCloseTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEarliestCloseTime(String value) {
        this.earliestCloseTime = value;
    }

    /**
     * Gets the value of the leadTime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLeadTime() {
        return leadTime;
    }

    /**
     * Sets the value of the leadTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLeadTime(String value) {
        this.leadTime = value;
    }

    /**
     * Gets the value of the gwnIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGWNIndicator() {
        return gwnIndicator;
    }

    /**
     * Sets the value of the gwnIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGWNIndicator(String value) {
        this.gwnIndicator = value;
    }

}
