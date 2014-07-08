
package com.meritconinc.shiplinx.carrier.ups.ws.tnt.proxy;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for EstimatedArrivalType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="EstimatedArrivalType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Arrival" type="{http://www.ups.com/XMLSchema/XOLTWS/tnt/v1.0}PickupType"/>
 *         &lt;element name="BusinessDaysInTransit" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Pickup" type="{http://www.ups.com/XMLSchema/XOLTWS/tnt/v1.0}PickupType"/>
 *         &lt;element name="DayOfWeek" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CustomerCenterCutoff" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DelayCount" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="HolidayCount" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="RestDays" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TotalTransitDays" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EstimatedArrivalType", propOrder = {
    "arrival",
    "businessDaysInTransit",
    "pickup",
    "dayOfWeek",
    "customerCenterCutoff",
    "delayCount",
    "holidayCount",
    "restDays",
    "totalTransitDays"
})
public class EstimatedArrivalType {

    @XmlElement(name = "Arrival", required = true)
    protected PickupType arrival;
    @XmlElement(name = "BusinessDaysInTransit", required = true)
    protected String businessDaysInTransit;
    @XmlElement(name = "Pickup", required = true)
    protected PickupType pickup;
    @XmlElement(name = "DayOfWeek")
    protected String dayOfWeek;
    @XmlElement(name = "CustomerCenterCutoff")
    protected String customerCenterCutoff;
    @XmlElement(name = "DelayCount")
    protected String delayCount;
    @XmlElement(name = "HolidayCount")
    protected String holidayCount;
    @XmlElement(name = "RestDays")
    protected String restDays;
    @XmlElement(name = "TotalTransitDays")
    protected String totalTransitDays;

    /**
     * Gets the value of the arrival property.
     * 
     * @return
     *     possible object is
     *     {@link PickupType }
     *     
     */
    public PickupType getArrival() {
        return arrival;
    }

    /**
     * Sets the value of the arrival property.
     * 
     * @param value
     *     allowed object is
     *     {@link PickupType }
     *     
     */
    public void setArrival(PickupType value) {
        this.arrival = value;
    }

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
     * Gets the value of the pickup property.
     * 
     * @return
     *     possible object is
     *     {@link PickupType }
     *     
     */
    public PickupType getPickup() {
        return pickup;
    }

    /**
     * Sets the value of the pickup property.
     * 
     * @param value
     *     allowed object is
     *     {@link PickupType }
     *     
     */
    public void setPickup(PickupType value) {
        this.pickup = value;
    }

    /**
     * Gets the value of the dayOfWeek property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDayOfWeek() {
        return dayOfWeek;
    }

    /**
     * Sets the value of the dayOfWeek property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDayOfWeek(String value) {
        this.dayOfWeek = value;
    }

    /**
     * Gets the value of the customerCenterCutoff property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustomerCenterCutoff() {
        return customerCenterCutoff;
    }

    /**
     * Sets the value of the customerCenterCutoff property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustomerCenterCutoff(String value) {
        this.customerCenterCutoff = value;
    }

    /**
     * Gets the value of the delayCount property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDelayCount() {
        return delayCount;
    }

    /**
     * Sets the value of the delayCount property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDelayCount(String value) {
        this.delayCount = value;
    }

    /**
     * Gets the value of the holidayCount property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHolidayCount() {
        return holidayCount;
    }

    /**
     * Sets the value of the holidayCount property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHolidayCount(String value) {
        this.holidayCount = value;
    }

    /**
     * Gets the value of the restDays property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRestDays() {
        return restDays;
    }

    /**
     * Sets the value of the restDays property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRestDays(String value) {
        this.restDays = value;
    }

    /**
     * Gets the value of the totalTransitDays property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTotalTransitDays() {
        return totalTransitDays;
    }

    /**
     * Sets the value of the totalTransitDays property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTotalTransitDays(String value) {
        this.totalTransitDays = value;
    }

}
