
package com.meritconinc.shiplinx.carrier.purolator.ws.pickup.proxy;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * SchedulePickUpRequest
 * 
 * <p>Java class for SchedulePickUpRequestContainer complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SchedulePickUpRequestContainer">
 *   &lt;complexContent>
 *     &lt;extension base="{http://purolator.com/pws/datatypes/v1}RequestContainer">
 *       &lt;sequence>
 *         &lt;element name="BillingAccountNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PartnerID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PickupInstruction" type="{http://purolator.com/pws/datatypes/v1}PickupInstruction"/>
 *         &lt;element name="Address" type="{http://purolator.com/pws/datatypes/v1}Address"/>
 *         &lt;element name="ShipmentSummary" type="{http://purolator.com/pws/datatypes/v1}ShipmentSummary" minOccurs="0"/>
 *         &lt;element name="NotificationEmails" type="{http://purolator.com/pws/datatypes/v1}NotificationEmails" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SchedulePickUpRequestContainer", propOrder = {
    "billingAccountNumber",
    "partnerID",
    "pickupInstruction",
    "address",
    "shipmentSummary",
    "notificationEmails"
})
public class SchedulePickUpRequestContainer
    extends RequestContainer
{

    @XmlElement(name = "BillingAccountNumber", required = true, nillable = true)
    protected String billingAccountNumber;
    @XmlElementRef(name = "PartnerID", namespace = "http://purolator.com/pws/datatypes/v1", type = JAXBElement.class)
    protected JAXBElement<String> partnerID;
    @XmlElement(name = "PickupInstruction", required = true, nillable = true)
    protected PickupInstruction pickupInstruction;
    @XmlElement(name = "Address", required = true, nillable = true)
    protected Address address;
    @XmlElementRef(name = "ShipmentSummary", namespace = "http://purolator.com/pws/datatypes/v1", type = JAXBElement.class)
    protected JAXBElement<ShipmentSummary> shipmentSummary;
    @XmlElementRef(name = "NotificationEmails", namespace = "http://purolator.com/pws/datatypes/v1", type = JAXBElement.class)
    protected JAXBElement<NotificationEmails> notificationEmails;

    /**
     * Gets the value of the billingAccountNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBillingAccountNumber() {
        return billingAccountNumber;
    }

    /**
     * Sets the value of the billingAccountNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBillingAccountNumber(String value) {
        this.billingAccountNumber = value;
    }

    /**
     * Gets the value of the partnerID property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getPartnerID() {
        return partnerID;
    }

    /**
     * Sets the value of the partnerID property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setPartnerID(JAXBElement<String> value) {
        this.partnerID = value;
    }

    /**
     * Gets the value of the pickupInstruction property.
     * 
     * @return
     *     possible object is
     *     {@link PickupInstruction }
     *     
     */
    public PickupInstruction getPickupInstruction() {
        return pickupInstruction;
    }

    /**
     * Sets the value of the pickupInstruction property.
     * 
     * @param value
     *     allowed object is
     *     {@link PickupInstruction }
     *     
     */
    public void setPickupInstruction(PickupInstruction value) {
        this.pickupInstruction = value;
    }

    /**
     * Gets the value of the address property.
     * 
     * @return
     *     possible object is
     *     {@link Address }
     *     
     */
    public Address getAddress() {
        return address;
    }

    /**
     * Sets the value of the address property.
     * 
     * @param value
     *     allowed object is
     *     {@link Address }
     *     
     */
    public void setAddress(Address value) {
        this.address = value;
    }

    /**
     * Gets the value of the shipmentSummary property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ShipmentSummary }{@code >}
     *     
     */
    public JAXBElement<ShipmentSummary> getShipmentSummary() {
        return shipmentSummary;
    }

    /**
     * Sets the value of the shipmentSummary property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ShipmentSummary }{@code >}
     *     
     */
    public void setShipmentSummary(JAXBElement<ShipmentSummary> value) {
        this.shipmentSummary = value;
    }

    /**
     * Gets the value of the notificationEmails property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link NotificationEmails }{@code >}
     *     
     */
    public JAXBElement<NotificationEmails> getNotificationEmails() {
        return notificationEmails;
    }

    /**
     * Sets the value of the notificationEmails property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link NotificationEmails }{@code >}
     *     
     */
    public void setNotificationEmails(JAXBElement<NotificationEmails> value) {
        this.notificationEmails = value;
    }

}
