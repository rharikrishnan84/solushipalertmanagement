
package com.meritconinc.shiplinx.carrier.ups.ws.ship.proxy;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for LabelDeliveryType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="LabelDeliveryType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="EMail" type="{http://www.ups.com/XMLSchema/XOLTWS/Ship/v1.0}EmailDetailsType" minOccurs="0"/>
 *         &lt;element name="LabelLinksIndicator" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LabelDeliveryType", propOrder = {
    "eMail",
    "labelLinksIndicator"
})
public class LabelDeliveryType {

    @XmlElement(name = "EMail")
    protected EmailDetailsType eMail;
    @XmlElement(name = "LabelLinksIndicator")
    protected String labelLinksIndicator;

    /**
     * Gets the value of the eMail property.
     * 
     * @return
     *     possible object is
     *     {@link EmailDetailsType }
     *     
     */
    public EmailDetailsType getEMail() {
        return eMail;
    }

    /**
     * Sets the value of the eMail property.
     * 
     * @param value
     *     allowed object is
     *     {@link EmailDetailsType }
     *     
     */
    public void setEMail(EmailDetailsType value) {
        this.eMail = value;
    }

    /**
     * Gets the value of the labelLinksIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLabelLinksIndicator() {
        return labelLinksIndicator;
    }

    /**
     * Sets the value of the labelLinksIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLabelLinksIndicator(String value) {
        this.labelLinksIndicator = value;
    }

}
