//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.02.01 at 06:34:59 PM EST 
//


package com.meritconinc.shiplinx.carrier.dhl.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Request" type="{http://www.dhl.com/datatypes}Request"/>
 *         &lt;element name="ConfirmationNumber">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}positiveInteger">
 *               &lt;minInclusive value="1"/>
 *               &lt;maxInclusive value="999999999"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="Requestor" type="{http://www.dhl.com/pickupdatatypes}Requestor"/>
 *         &lt;element name="Place" type="{http://www.dhl.com/pickupdatatypes}Place"/>
 *         &lt;element name="Pickup" type="{http://www.dhl.com/pickupdatatypes}Pickup"/>
 *         &lt;element name="PickupContact" type="{http://www.dhl.com/pickupdatatypes}contact"/>
 *         &lt;element name="OriginSvcArea" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="5"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "request",
    "confirmationNumber",
    "requestor",
    "place",
    "pickup",
    "pickupContact",
    "originSvcArea"
})
@XmlRootElement(name = "ModifyPickupRequest", namespace = "http://www.dhl.com")
public class ModifyPickupRequest {

    @XmlElement(name = "Request", required = true)
    protected Request request;
    @XmlElement(name = "ConfirmationNumber")
    protected int confirmationNumber;
    @XmlElement(name = "Requestor", required = true)
    protected Requestor requestor;
    @XmlElement(name = "Place", required = true)
    protected PlacePickup place;
    @XmlElement(name = "Pickup", required = true)
    protected Pickup pickup;
    @XmlElement(name = "PickupContact", required = true)
    protected ContactPickup pickupContact;
    @XmlElement(name = "OriginSvcArea")
    protected String originSvcArea;

    /**
     * Gets the value of the request property.
     * 
     * @return
     *     possible object is
     *     {@link Request }
     *     
     */
    public Request getRequest() {
        return request;
    }

    /**
     * Sets the value of the request property.
     * 
     * @param value
     *     allowed object is
     *     {@link Request }
     *     
     */
    public void setRequest(Request value) {
        this.request = value;
    }

    /**
     * Gets the value of the confirmationNumber property.
     * 
     */
    public int getConfirmationNumber() {
        return confirmationNumber;
    }

    /**
     * Sets the value of the confirmationNumber property.
     * 
     */
    public void setConfirmationNumber(int value) {
        this.confirmationNumber = value;
    }

    /**
     * Gets the value of the requestor property.
     * 
     * @return
     *     possible object is
     *     {@link Requestor }
     *     
     */
    public Requestor getRequestor() {
        return requestor;
    }

    /**
     * Sets the value of the requestor property.
     * 
     * @param value
     *     allowed object is
     *     {@link Requestor }
     *     
     */
    public void setRequestor(Requestor value) {
        this.requestor = value;
    }

    /**
     * Gets the value of the place property.
     * 
     * @return
     *     possible object is
     *     {@link PlacePickup }
     *     
     */
    public PlacePickup getPlace() {
        return place;
    }

    /**
     * Sets the value of the place property.
     * 
     * @param value
     *     allowed object is
     *     {@link PlacePickup }
     *     
     */
    public void setPlace(PlacePickup value) {
        this.place = value;
    }

    /**
     * Gets the value of the pickup property.
     * 
     * @return
     *     possible object is
     *     {@link Pickup }
     *     
     */
    public Pickup getPickup() {
        return pickup;
    }

    /**
     * Sets the value of the pickup property.
     * 
     * @param value
     *     allowed object is
     *     {@link Pickup }
     *     
     */
    public void setPickup(Pickup value) {
        this.pickup = value;
    }

    /**
     * Gets the value of the pickupContact property.
     * 
     * @return
     *     possible object is
     *     {@link ContactPickup }
     *     
     */
    public ContactPickup getPickupContact() {
        return pickupContact;
    }

    /**
     * Sets the value of the pickupContact property.
     * 
     * @param value
     *     allowed object is
     *     {@link ContactPickup }
     *     
     */
    public void setPickupContact(ContactPickup value) {
        this.pickupContact = value;
    }

    /**
     * Gets the value of the originSvcArea property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOriginSvcArea() {
        return originSvcArea;
    }

    /**
     * Sets the value of the originSvcArea property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOriginSvcArea(String value) {
        this.originSvcArea = value;
    }

}
