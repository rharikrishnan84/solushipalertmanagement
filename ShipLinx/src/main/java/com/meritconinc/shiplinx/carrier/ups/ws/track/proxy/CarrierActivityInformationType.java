
package com.meritconinc.shiplinx.carrier.ups.ws.track.proxy;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CarrierActivityInformationType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CarrierActivityInformationType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CarrierId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Status" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Arrival" type="{http://www.ups.com/XMLSchema/XOLTWS/Track/v2.0}DateTimeType" minOccurs="0"/>
 *         &lt;element name="Departure" type="{http://www.ups.com/XMLSchema/XOLTWS/Track/v2.0}DateTimeType" minOccurs="0"/>
 *         &lt;element name="OriginPort" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DestinationPort" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CarrierActivityInformationType", propOrder = {
    "carrierId",
    "description",
    "status",
    "arrival",
    "departure",
    "originPort",
    "destinationPort"
})
public class CarrierActivityInformationType {

    @XmlElement(name = "CarrierId")
    protected String carrierId;
    @XmlElement(name = "Description")
    protected String description;
    @XmlElement(name = "Status")
    protected String status;
    @XmlElement(name = "Arrival")
    protected DateTimeType arrival;
    @XmlElement(name = "Departure")
    protected DateTimeType departure;
    @XmlElement(name = "OriginPort")
    protected String originPort;
    @XmlElement(name = "DestinationPort")
    protected String destinationPort;

    /**
     * Gets the value of the carrierId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCarrierId() {
        return carrierId;
    }

    /**
     * Sets the value of the carrierId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCarrierId(String value) {
        this.carrierId = value;
    }

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatus(String value) {
        this.status = value;
    }

    /**
     * Gets the value of the arrival property.
     * 
     * @return
     *     possible object is
     *     {@link DateTimeType }
     *     
     */
    public DateTimeType getArrival() {
        return arrival;
    }

    /**
     * Sets the value of the arrival property.
     * 
     * @param value
     *     allowed object is
     *     {@link DateTimeType }
     *     
     */
    public void setArrival(DateTimeType value) {
        this.arrival = value;
    }

    /**
     * Gets the value of the departure property.
     * 
     * @return
     *     possible object is
     *     {@link DateTimeType }
     *     
     */
    public DateTimeType getDeparture() {
        return departure;
    }

    /**
     * Sets the value of the departure property.
     * 
     * @param value
     *     allowed object is
     *     {@link DateTimeType }
     *     
     */
    public void setDeparture(DateTimeType value) {
        this.departure = value;
    }

    /**
     * Gets the value of the originPort property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOriginPort() {
        return originPort;
    }

    /**
     * Sets the value of the originPort property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOriginPort(String value) {
        this.originPort = value;
    }

    /**
     * Gets the value of the destinationPort property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDestinationPort() {
        return destinationPort;
    }

    /**
     * Sets the value of the destinationPort property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDestinationPort(String value) {
        this.destinationPort = value;
    }

}
