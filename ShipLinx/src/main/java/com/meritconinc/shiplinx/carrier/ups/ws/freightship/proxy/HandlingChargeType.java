
package com.meritconinc.shiplinx.carrier.ups.ws.freightship.proxy;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for HandlingChargeType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="HandlingChargeType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Percentage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Amount" type="{http://www.ups.com/XMLSchema/XOLTWS/FreightShip/v1.0}HandlingChargeAmountType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "HandlingChargeType", propOrder = {
    "percentage",
    "amount"
})
public class HandlingChargeType {

    @XmlElement(name = "Percentage")
    protected String percentage;
    @XmlElement(name = "Amount")
    protected HandlingChargeAmountType amount;

    /**
     * Gets the value of the percentage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPercentage() {
        return percentage;
    }

    /**
     * Sets the value of the percentage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPercentage(String value) {
        this.percentage = value;
    }

    /**
     * Gets the value of the amount property.
     * 
     * @return
     *     possible object is
     *     {@link HandlingChargeAmountType }
     *     
     */
    public HandlingChargeAmountType getAmount() {
        return amount;
    }

    /**
     * Sets the value of the amount property.
     * 
     * @param value
     *     allowed object is
     *     {@link HandlingChargeAmountType }
     *     
     */
    public void setAmount(HandlingChargeAmountType value) {
        this.amount = value;
    }

}
