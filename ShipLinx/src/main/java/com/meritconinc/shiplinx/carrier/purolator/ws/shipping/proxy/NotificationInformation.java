
package com.meritconinc.shiplinx.carrier.purolator.ws.shipping.proxy;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * NotificationInformation
 * 
 * <p>Java class for NotificationInformation complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="NotificationInformation">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ConfirmationEmailAddress" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="AdvancedShippingNotificationEmailAddress1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="AdvancedShippingNotificationEmailAddress2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="AdvancedShippingNotificationMessage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "NotificationInformation", propOrder = {
    "confirmationEmailAddress",
    "advancedShippingNotificationEmailAddress1",
    "advancedShippingNotificationEmailAddress2",
    "advancedShippingNotificationMessage"
})
public class NotificationInformation {

    @XmlElementRef(name = "ConfirmationEmailAddress", namespace = "http://purolator.com/pws/datatypes/v1", type = JAXBElement.class)
    protected JAXBElement<String> confirmationEmailAddress;
    @XmlElementRef(name = "AdvancedShippingNotificationEmailAddress1", namespace = "http://purolator.com/pws/datatypes/v1", type = JAXBElement.class)
    protected JAXBElement<String> advancedShippingNotificationEmailAddress1;
    @XmlElementRef(name = "AdvancedShippingNotificationEmailAddress2", namespace = "http://purolator.com/pws/datatypes/v1", type = JAXBElement.class)
    protected JAXBElement<String> advancedShippingNotificationEmailAddress2;
    @XmlElementRef(name = "AdvancedShippingNotificationMessage", namespace = "http://purolator.com/pws/datatypes/v1", type = JAXBElement.class)
    protected JAXBElement<String> advancedShippingNotificationMessage;

    /**
     * Gets the value of the confirmationEmailAddress property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getConfirmationEmailAddress() {
        return confirmationEmailAddress;
    }

    /**
     * Sets the value of the confirmationEmailAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setConfirmationEmailAddress(JAXBElement<String> value) {
        this.confirmationEmailAddress = value;
    }

    /**
     * Gets the value of the advancedShippingNotificationEmailAddress1 property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getAdvancedShippingNotificationEmailAddress1() {
        return advancedShippingNotificationEmailAddress1;
    }

    /**
     * Sets the value of the advancedShippingNotificationEmailAddress1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setAdvancedShippingNotificationEmailAddress1(JAXBElement<String> value) {
        this.advancedShippingNotificationEmailAddress1 = value;
    }

    /**
     * Gets the value of the advancedShippingNotificationEmailAddress2 property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getAdvancedShippingNotificationEmailAddress2() {
        return advancedShippingNotificationEmailAddress2;
    }

    /**
     * Sets the value of the advancedShippingNotificationEmailAddress2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setAdvancedShippingNotificationEmailAddress2(JAXBElement<String> value) {
        this.advancedShippingNotificationEmailAddress2 = value;
    }

    /**
     * Gets the value of the advancedShippingNotificationMessage property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getAdvancedShippingNotificationMessage() {
        return advancedShippingNotificationMessage;
    }

    /**
     * Sets the value of the advancedShippingNotificationMessage property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setAdvancedShippingNotificationMessage(JAXBElement<String> value) {
        this.advancedShippingNotificationMessage = value;
    }

}
