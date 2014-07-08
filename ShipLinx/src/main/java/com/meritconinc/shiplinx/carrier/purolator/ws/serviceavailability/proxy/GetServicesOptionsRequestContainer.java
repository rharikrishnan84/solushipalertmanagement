
package com.meritconinc.shiplinx.carrier.purolator.ws.serviceavailability.proxy;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * GetServicesOptionsRequest
 * 
 * <p>Java class for GetServicesOptionsRequestContainer complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GetServicesOptionsRequestContainer">
 *   &lt;complexContent>
 *     &lt;extension base="{http://purolator.com/pws/datatypes/v1}RequestContainer">
 *       &lt;sequence>
 *         &lt;element name="BillingAccountNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="SenderAddress" type="{http://purolator.com/pws/datatypes/v1}ShortAddress"/>
 *         &lt;element name="ReceiverAddress" type="{http://purolator.com/pws/datatypes/v1}ShortAddress"/>
 *         &lt;element name="ShipmentDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetServicesOptionsRequestContainer", propOrder = {
    "billingAccountNumber",
    "senderAddress",
    "receiverAddress",
    "shipmentDate"
})
public class GetServicesOptionsRequestContainer
    extends RequestContainer
{

    @XmlElement(name = "BillingAccountNumber", required = true, nillable = true)
    protected String billingAccountNumber;
    @XmlElement(name = "SenderAddress", required = true, nillable = true)
    protected ShortAddress senderAddress;
    @XmlElement(name = "ReceiverAddress", required = true, nillable = true)
    protected ShortAddress receiverAddress;
    @XmlElementRef(name = "ShipmentDate", namespace = "http://purolator.com/pws/datatypes/v1", type = JAXBElement.class)
    protected JAXBElement<String> shipmentDate;

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
     * Gets the value of the senderAddress property.
     * 
     * @return
     *     possible object is
     *     {@link ShortAddress }
     *     
     */
    public ShortAddress getSenderAddress() {
        return senderAddress;
    }

    /**
     * Sets the value of the senderAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link ShortAddress }
     *     
     */
    public void setSenderAddress(ShortAddress value) {
        this.senderAddress = value;
    }

    /**
     * Gets the value of the receiverAddress property.
     * 
     * @return
     *     possible object is
     *     {@link ShortAddress }
     *     
     */
    public ShortAddress getReceiverAddress() {
        return receiverAddress;
    }

    /**
     * Sets the value of the receiverAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link ShortAddress }
     *     
     */
    public void setReceiverAddress(ShortAddress value) {
        this.receiverAddress = value;
    }

    /**
     * Gets the value of the shipmentDate property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getShipmentDate() {
        return shipmentDate;
    }

    /**
     * Sets the value of the shipmentDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setShipmentDate(JAXBElement<String> value) {
        this.shipmentDate = value;
    }

}
