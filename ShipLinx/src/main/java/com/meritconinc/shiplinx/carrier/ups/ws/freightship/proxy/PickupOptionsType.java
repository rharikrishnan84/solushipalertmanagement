
package com.meritconinc.shiplinx.carrier.ups.ws.freightship.proxy;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PickupOptionsType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PickupOptionsType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="HolidayPickupIndicator" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="InsidePickupIndicator" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ResidentialPickupIndicator" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="WeekendPickupIndicator" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="LiftGateRequiredIndicator" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="HoldAtAirportForPickup" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PickupFromDoorIndicator" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="LimitedAccessPickupIndicator" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PickupOptionsType", propOrder = {
    "holidayPickupIndicator",
    "insidePickupIndicator",
    "residentialPickupIndicator",
    "weekendPickupIndicator",
    "liftGateRequiredIndicator",
    "holdAtAirportForPickup",
    "pickupFromDoorIndicator",
    "limitedAccessPickupIndicator"
})
public class PickupOptionsType {

    @XmlElement(name = "HolidayPickupIndicator")
    protected String holidayPickupIndicator;
    @XmlElement(name = "InsidePickupIndicator")
    protected String insidePickupIndicator;
    @XmlElement(name = "ResidentialPickupIndicator")
    protected String residentialPickupIndicator;
    @XmlElement(name = "WeekendPickupIndicator")
    protected String weekendPickupIndicator;
    @XmlElement(name = "LiftGateRequiredIndicator")
    protected String liftGateRequiredIndicator;
    @XmlElement(name = "HoldAtAirportForPickup")
    protected String holdAtAirportForPickup;
    @XmlElement(name = "PickupFromDoorIndicator")
    protected String pickupFromDoorIndicator;
    @XmlElement(name = "LimitedAccessPickupIndicator")
    protected String limitedAccessPickupIndicator;

    /**
     * Gets the value of the holidayPickupIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHolidayPickupIndicator() {
        return holidayPickupIndicator;
    }

    /**
     * Sets the value of the holidayPickupIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHolidayPickupIndicator(String value) {
        this.holidayPickupIndicator = value;
    }

    /**
     * Gets the value of the insidePickupIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInsidePickupIndicator() {
        return insidePickupIndicator;
    }

    /**
     * Sets the value of the insidePickupIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInsidePickupIndicator(String value) {
        this.insidePickupIndicator = value;
    }

    /**
     * Gets the value of the residentialPickupIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResidentialPickupIndicator() {
        return residentialPickupIndicator;
    }

    /**
     * Sets the value of the residentialPickupIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResidentialPickupIndicator(String value) {
        this.residentialPickupIndicator = value;
    }

    /**
     * Gets the value of the weekendPickupIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWeekendPickupIndicator() {
        return weekendPickupIndicator;
    }

    /**
     * Sets the value of the weekendPickupIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWeekendPickupIndicator(String value) {
        this.weekendPickupIndicator = value;
    }

    /**
     * Gets the value of the liftGateRequiredIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLiftGateRequiredIndicator() {
        return liftGateRequiredIndicator;
    }

    /**
     * Sets the value of the liftGateRequiredIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLiftGateRequiredIndicator(String value) {
        this.liftGateRequiredIndicator = value;
    }

    /**
     * Gets the value of the holdAtAirportForPickup property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHoldAtAirportForPickup() {
        return holdAtAirportForPickup;
    }

    /**
     * Sets the value of the holdAtAirportForPickup property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHoldAtAirportForPickup(String value) {
        this.holdAtAirportForPickup = value;
    }

    /**
     * Gets the value of the pickupFromDoorIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPickupFromDoorIndicator() {
        return pickupFromDoorIndicator;
    }

    /**
     * Sets the value of the pickupFromDoorIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPickupFromDoorIndicator(String value) {
        this.pickupFromDoorIndicator = value;
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

}
