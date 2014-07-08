
package com.meritconinc.shiplinx.carrier.purolator.ws.pickup.proxy;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * PickUp Detail
 * 
 * <p>Java class for PickUpDetail complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PickUpDetail">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="BillingAccountNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PartnerId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ConfirmationNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PickupStatus" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PickupType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PickupInstruction" type="{http://purolator.com/pws/datatypes/v1}PickupInstruction"/>
 *         &lt;element name="Address" type="{http://purolator.com/pws/datatypes/v1}Address"/>
 *         &lt;element name="ShipmentSummary" type="{http://purolator.com/pws/datatypes/v1}ShipmentSummary"/>
 *         &lt;element name="NotificationEmails" type="{http://schemas.microsoft.com/2003/10/Serialization/Arrays}ArrayOfstring"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PickUpDetail", propOrder = {
    "billingAccountNumber",
    "partnerId",
    "confirmationNumber",
    "pickupStatus",
    "pickupType",
    "pickupInstruction",
    "address",
    "shipmentSummary",
    "notificationEmails"
})
public class PickUpDetail {

    @XmlElement(name = "BillingAccountNumber", required = true, nillable = true)
    protected String billingAccountNumber;
    @XmlElement(name = "PartnerId", required = true, nillable = true)
    protected String partnerId;
    @XmlElement(name = "ConfirmationNumber", required = true, nillable = true)
    protected String confirmationNumber;
    @XmlElement(name = "PickupStatus", required = true, nillable = true)
    protected String pickupStatus;
    @XmlElement(name = "PickupType", required = true, nillable = true)
    protected String pickupType;
    @XmlElement(name = "PickupInstruction", required = true, nillable = true)
    protected PickupInstruction pickupInstruction;
    @XmlElement(name = "Address", required = true, nillable = true)
    protected Address address;
    @XmlElement(name = "ShipmentSummary", required = true, nillable = true)
    protected ShipmentSummary shipmentSummary;
    @XmlElement(name = "NotificationEmails", required = true, nillable = true)
    protected ArrayOfstring notificationEmails;

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
     * Gets the value of the partnerId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPartnerId() {
        return partnerId;
    }

    /**
     * Sets the value of the partnerId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPartnerId(String value) {
        this.partnerId = value;
    }

    /**
     * Gets the value of the confirmationNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConfirmationNumber() {
        return confirmationNumber;
    }

    /**
     * Sets the value of the confirmationNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConfirmationNumber(String value) {
        this.confirmationNumber = value;
    }

    /**
     * Gets the value of the pickupStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPickupStatus() {
        return pickupStatus;
    }

    /**
     * Sets the value of the pickupStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPickupStatus(String value) {
        this.pickupStatus = value;
    }

    /**
     * Gets the value of the pickupType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPickupType() {
        return pickupType;
    }

    /**
     * Sets the value of the pickupType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPickupType(String value) {
        this.pickupType = value;
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
     *     {@link ShipmentSummary }
     *     
     */
    public ShipmentSummary getShipmentSummary() {
        return shipmentSummary;
    }

    /**
     * Sets the value of the shipmentSummary property.
     * 
     * @param value
     *     allowed object is
     *     {@link ShipmentSummary }
     *     
     */
    public void setShipmentSummary(ShipmentSummary value) {
        this.shipmentSummary = value;
    }

    /**
     * Gets the value of the notificationEmails property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfstring }
     *     
     */
    public ArrayOfstring getNotificationEmails() {
        return notificationEmails;
    }

    /**
     * Sets the value of the notificationEmails property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfstring }
     *     
     */
    public void setNotificationEmails(ArrayOfstring value) {
        this.notificationEmails = value;
    }

}
