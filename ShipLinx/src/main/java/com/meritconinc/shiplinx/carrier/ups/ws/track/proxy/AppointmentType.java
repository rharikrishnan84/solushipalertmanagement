
package com.meritconinc.shiplinx.carrier.ups.ws.track.proxy;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AppointmentType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AppointmentType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Made" type="{http://www.ups.com/XMLSchema/XOLTWS/Track/v2.0}DateTimeType" minOccurs="0"/>
 *         &lt;element name="Requested" type="{http://www.ups.com/XMLSchema/XOLTWS/Track/v2.0}DateTimeType" minOccurs="0"/>
 *         &lt;element name="BeginTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="EndTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AppointmentType", propOrder = {
    "made",
    "requested",
    "beginTime",
    "endTime"
})
public class AppointmentType {

    @XmlElement(name = "Made")
    protected DateTimeType made;
    @XmlElement(name = "Requested")
    protected DateTimeType requested;
    @XmlElement(name = "BeginTime")
    protected String beginTime;
    @XmlElement(name = "EndTime")
    protected String endTime;

    /**
     * Gets the value of the made property.
     * 
     * @return
     *     possible object is
     *     {@link DateTimeType }
     *     
     */
    public DateTimeType getMade() {
        return made;
    }

    /**
     * Sets the value of the made property.
     * 
     * @param value
     *     allowed object is
     *     {@link DateTimeType }
     *     
     */
    public void setMade(DateTimeType value) {
        this.made = value;
    }

    /**
     * Gets the value of the requested property.
     * 
     * @return
     *     possible object is
     *     {@link DateTimeType }
     *     
     */
    public DateTimeType getRequested() {
        return requested;
    }

    /**
     * Sets the value of the requested property.
     * 
     * @param value
     *     allowed object is
     *     {@link DateTimeType }
     *     
     */
    public void setRequested(DateTimeType value) {
        this.requested = value;
    }

    /**
     * Gets the value of the beginTime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBeginTime() {
        return beginTime;
    }

    /**
     * Sets the value of the beginTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBeginTime(String value) {
        this.beginTime = value;
    }

    /**
     * Gets the value of the endTime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEndTime() {
        return endTime;
    }

    /**
     * Sets the value of the endTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEndTime(String value) {
        this.endTime = value;
    }

}
