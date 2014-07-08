
package com.meritconinc.shiplinx.carrier.purolator.ws.shipping.proxy;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * PaymentInformation
 * 
 * <p>Java class for PaymentInformation complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PaymentInformation">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="PaymentType" type="{http://purolator.com/pws/datatypes/v1}PaymentType"/>
 *         &lt;element name="RegisteredAccountNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="BillingAccountNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CreditCardInformation" type="{http://purolator.com/pws/datatypes/v1}CreditCardInformation" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PaymentInformation", propOrder = {
    "paymentType",
    "registeredAccountNumber",
    "billingAccountNumber",
    "creditCardInformation"
})
public class PaymentInformation {

    @XmlElement(name = "PaymentType", required = true)
    protected PaymentType paymentType;
    @XmlElement(name = "RegisteredAccountNumber", required = true, nillable = true)
    protected String registeredAccountNumber;
    @XmlElementRef(name = "BillingAccountNumber", namespace = "http://purolator.com/pws/datatypes/v1", type = JAXBElement.class)
    protected JAXBElement<String> billingAccountNumber;
    @XmlElementRef(name = "CreditCardInformation", namespace = "http://purolator.com/pws/datatypes/v1", type = JAXBElement.class)
    protected JAXBElement<CreditCardInformation> creditCardInformation;

    /**
     * Gets the value of the paymentType property.
     * 
     * @return
     *     possible object is
     *     {@link PaymentType }
     *     
     */
    public PaymentType getPaymentType() {
        return paymentType;
    }

    /**
     * Sets the value of the paymentType property.
     * 
     * @param value
     *     allowed object is
     *     {@link PaymentType }
     *     
     */
    public void setPaymentType(PaymentType value) {
        this.paymentType = value;
    }

    /**
     * Gets the value of the registeredAccountNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRegisteredAccountNumber() {
        return registeredAccountNumber;
    }

    /**
     * Sets the value of the registeredAccountNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRegisteredAccountNumber(String value) {
        this.registeredAccountNumber = value;
    }

    /**
     * Gets the value of the billingAccountNumber property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getBillingAccountNumber() {
        return billingAccountNumber;
    }

    /**
     * Sets the value of the billingAccountNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setBillingAccountNumber(JAXBElement<String> value) {
        this.billingAccountNumber = value;
    }

    /**
     * Gets the value of the creditCardInformation property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link CreditCardInformation }{@code >}
     *     
     */
    public JAXBElement<CreditCardInformation> getCreditCardInformation() {
        return creditCardInformation;
    }

    /**
     * Sets the value of the creditCardInformation property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link CreditCardInformation }{@code >}
     *     
     */
    public void setCreditCardInformation(JAXBElement<CreditCardInformation> value) {
        this.creditCardInformation = value;
    }

}
