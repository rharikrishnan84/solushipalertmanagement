
package com.meritconinc.shiplinx.carrier.ups.ws.pickup.proxy;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TodayType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TodayType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="LocalDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="LocalTime" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
@XmlType(name = "TodayType", propOrder = {
    "localDate",
    "localTime",
    "callByTime",
    "earliestCloseTime",
    "leadTime",
    "gwnIndicator"
})
public class TodayType {

    @XmlElement(name = "LocalDate", required = true)
    protected String localDate;
    @XmlElement(name = "LocalTime", required = true)
    protected String localTime;
    @XmlElement(name = "CallByTime")
    protected String callByTime;
    @XmlElement(name = "EarliestCloseTime")
    protected String earliestCloseTime;
    @XmlElement(name = "LeadTime")
    protected String leadTime;
    @XmlElement(name = "GWNIndicator", defaultValue = "N")
    protected String gwnIndicator;

    /**
     * Gets the value of the localDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLocalDate() {
        return localDate;
    }

    /**
     * Sets the value of the localDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLocalDate(String value) {
        this.localDate = value;
    }

    /**
     * Gets the value of the localTime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLocalTime() {
        return localTime;
    }

    /**
     * Sets the value of the localTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLocalTime(String value) {
        this.localTime = value;
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
