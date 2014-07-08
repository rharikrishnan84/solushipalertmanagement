
package com.meritconinc.shiplinx.carrier.ups.ws.pickup.proxy;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ChargeDetailType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ChargeDetailType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ChargeCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ChargeDescription" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ChargeAmount" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="TaxAmount" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ChargeDetailType", propOrder = {
    "chargeCode",
    "chargeDescription",
    "chargeAmount",
    "taxAmount"
})
public class ChargeDetailType {

    @XmlElement(name = "ChargeCode", required = true)
    protected String chargeCode;
    @XmlElement(name = "ChargeDescription")
    protected String chargeDescription;
    @XmlElement(name = "ChargeAmount", required = true)
    protected String chargeAmount;
    @XmlElement(name = "TaxAmount")
    protected String taxAmount;

    /**
     * Gets the value of the chargeCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChargeCode() {
        return chargeCode;
    }

    /**
     * Sets the value of the chargeCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChargeCode(String value) {
        this.chargeCode = value;
    }

    /**
     * Gets the value of the chargeDescription property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChargeDescription() {
        return chargeDescription;
    }

    /**
     * Sets the value of the chargeDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChargeDescription(String value) {
        this.chargeDescription = value;
    }

    /**
     * Gets the value of the chargeAmount property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChargeAmount() {
        return chargeAmount;
    }

    /**
     * Sets the value of the chargeAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChargeAmount(String value) {
        this.chargeAmount = value;
    }

    /**
     * Gets the value of the taxAmount property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTaxAmount() {
        return taxAmount;
    }

    /**
     * Sets the value of the taxAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTaxAmount(String value) {
        this.taxAmount = value;
    }

}
