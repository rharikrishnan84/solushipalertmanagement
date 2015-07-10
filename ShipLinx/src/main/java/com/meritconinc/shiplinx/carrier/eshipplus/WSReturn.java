
package com.meritconinc.shiplinx.carrier.eshipplus;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for WSReturn complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="WSReturn">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Messages" type="{http://www.eshipplus.com/}ArrayOfWSMessage" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WSReturn", propOrder = {
    "messages"
})
@XmlSeeAlso({
    WSShipment2 .class,
    WSInvoice.class,
    WSOptions.class,
    WSCustomerPONumber.class,
    WSShipmentStatus.class,
    WSBookingRequest.class,
    WSBookingRequestStatus.class,
    WSReference.class,
    WSAccessorialService.class,
    WSInvoiceSummary.class,
    WSCountry.class,
    WSTime2 .class,
    WSBillingDetail.class,
    WSItemPackage.class,
    WSFreightClass.class
})

public class WSReturn {
    @XmlElement(name = "Messages")
    protected ArrayOfWSMessage messages;

    /**
     * Gets the value of the messages property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfWSMessage }
     *     
     */
    public ArrayOfWSMessage getMessages() {
        return messages;
    }

    /**
     * Sets the value of the messages property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfWSMessage }
     *     
     */
    public void setMessages(ArrayOfWSMessage value) {
        this.messages = value;
    }

}
