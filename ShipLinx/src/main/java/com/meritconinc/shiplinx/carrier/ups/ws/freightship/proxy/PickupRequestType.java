
package com.meritconinc.shiplinx.carrier.ups.ws.freightship.proxy;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PickupRequestType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PickupRequestType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="AdditionalComments" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Requester" type="{http://www.ups.com/XMLSchema/XOLTWS/FreightShip/v1.0}RequesterType" minOccurs="0"/>
 *         &lt;element name="PickupDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="EarliestTimeReady" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="LatestTimeReady" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PickupTimeReady" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DropoffTimeReady" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="POM" type="{http://www.ups.com/XMLSchema/XOLTWS/FreightShip/v1.0}POMType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PickupRequestType", propOrder = {
    "additionalComments",
    "requester",
    "pickupDate",
    "earliestTimeReady",
    "latestTimeReady",
    "pickupTimeReady",
    "dropoffTimeReady",
    "pom"
})
public class PickupRequestType {

    @XmlElement(name = "AdditionalComments")
    protected String additionalComments;
    @XmlElement(name = "Requester")
    protected RequesterType requester;
    @XmlElement(name = "PickupDate", required = true)
    protected String pickupDate;
    @XmlElement(name = "EarliestTimeReady")
    protected String earliestTimeReady;
    @XmlElement(name = "LatestTimeReady")
    protected String latestTimeReady;
    @XmlElement(name = "PickupTimeReady")
    protected String pickupTimeReady;
    @XmlElement(name = "DropoffTimeReady")
    protected String dropoffTimeReady;
    @XmlElement(name = "POM")
    protected POMType pom;

    /**
     * Gets the value of the additionalComments property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAdditionalComments() {
        return additionalComments;
    }

    /**
     * Sets the value of the additionalComments property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAdditionalComments(String value) {
        this.additionalComments = value;
    }

    /**
     * Gets the value of the requester property.
     * 
     * @return
     *     possible object is
     *     {@link RequesterType }
     *     
     */
    public RequesterType getRequester() {
        return requester;
    }

    /**
     * Sets the value of the requester property.
     * 
     * @param value
     *     allowed object is
     *     {@link RequesterType }
     *     
     */
    public void setRequester(RequesterType value) {
        this.requester = value;
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

    /**
     * Gets the value of the earliestTimeReady property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEarliestTimeReady() {
        return earliestTimeReady;
    }

    /**
     * Sets the value of the earliestTimeReady property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEarliestTimeReady(String value) {
        this.earliestTimeReady = value;
    }

    /**
     * Gets the value of the latestTimeReady property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLatestTimeReady() {
        return latestTimeReady;
    }

    /**
     * Sets the value of the latestTimeReady property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLatestTimeReady(String value) {
        this.latestTimeReady = value;
    }

    /**
     * Gets the value of the pickupTimeReady property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPickupTimeReady() {
        return pickupTimeReady;
    }

    /**
     * Sets the value of the pickupTimeReady property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPickupTimeReady(String value) {
        this.pickupTimeReady = value;
    }

    /**
     * Gets the value of the dropoffTimeReady property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDropoffTimeReady() {
        return dropoffTimeReady;
    }

    /**
     * Sets the value of the dropoffTimeReady property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDropoffTimeReady(String value) {
        this.dropoffTimeReady = value;
    }

    /**
     * Gets the value of the pom property.
     * 
     * @return
     *     possible object is
     *     {@link POMType }
     *     
     */
    public POMType getPOM() {
        return pom;
    }

    /**
     * Sets the value of the pom property.
     * 
     * @param value
     *     allowed object is
     *     {@link POMType }
     *     
     */
    public void setPOM(POMType value) {
        this.pom = value;
    }

}
