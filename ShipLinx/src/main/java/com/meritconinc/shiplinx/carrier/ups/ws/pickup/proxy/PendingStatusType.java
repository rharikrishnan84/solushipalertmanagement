
package com.meritconinc.shiplinx.carrier.ups.ws.pickup.proxy;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PendingStatusType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PendingStatusType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="PickupType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ServiceDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PRN" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="GWNStatusCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="OnCallStatusCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PickupStatusMessage" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="BillingCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ContactName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ReferenceNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PendingStatusType", propOrder = {
    "pickupType",
    "serviceDate",
    "prn",
    "gwnStatusCode",
    "onCallStatusCode",
    "pickupStatusMessage",
    "billingCode",
    "contactName",
    "referenceNumber"
})
public class PendingStatusType {

    @XmlElement(name = "PickupType", required = true)
    protected String pickupType;
    @XmlElement(name = "ServiceDate", required = true)
    protected String serviceDate;
    @XmlElement(name = "PRN", required = true)
    protected String prn;
    @XmlElement(name = "GWNStatusCode")
    protected String gwnStatusCode;
    @XmlElement(name = "OnCallStatusCode")
    protected String onCallStatusCode;
    @XmlElement(name = "PickupStatusMessage", required = true)
    protected String pickupStatusMessage;
    @XmlElement(name = "BillingCode")
    protected String billingCode;
    @XmlElement(name = "ContactName")
    protected String contactName;
    @XmlElement(name = "ReferenceNumber")
    protected String referenceNumber;

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
     * Gets the value of the prn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPRN() {
        return prn;
    }

    /**
     * Sets the value of the prn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPRN(String value) {
        this.prn = value;
    }

    /**
     * Gets the value of the gwnStatusCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGWNStatusCode() {
        return gwnStatusCode;
    }

    /**
     * Sets the value of the gwnStatusCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGWNStatusCode(String value) {
        this.gwnStatusCode = value;
    }

    /**
     * Gets the value of the onCallStatusCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOnCallStatusCode() {
        return onCallStatusCode;
    }

    /**
     * Sets the value of the onCallStatusCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOnCallStatusCode(String value) {
        this.onCallStatusCode = value;
    }

    /**
     * Gets the value of the pickupStatusMessage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPickupStatusMessage() {
        return pickupStatusMessage;
    }

    /**
     * Sets the value of the pickupStatusMessage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPickupStatusMessage(String value) {
        this.pickupStatusMessage = value;
    }

    /**
     * Gets the value of the billingCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBillingCode() {
        return billingCode;
    }

    /**
     * Sets the value of the billingCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBillingCode(String value) {
        this.billingCode = value;
    }

    /**
     * Gets the value of the contactName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContactName() {
        return contactName;
    }

    /**
     * Sets the value of the contactName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContactName(String value) {
        this.contactName = value;
    }

    /**
     * Gets the value of the referenceNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReferenceNumber() {
        return referenceNumber;
    }

    /**
     * Sets the value of the referenceNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReferenceNumber(String value) {
        this.referenceNumber = value;
    }

}
