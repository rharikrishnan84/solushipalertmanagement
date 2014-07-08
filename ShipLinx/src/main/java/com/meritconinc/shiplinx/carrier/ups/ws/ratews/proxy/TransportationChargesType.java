
package com.meritconinc.shiplinx.carrier.ups.ws.ratews.proxy;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TransportationChargesType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TransportationChargesType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="GrossCharge" type="{http://www.ups.com/XMLSchema/XOLTWS/Rate/v1.1}ChargesType"/>
 *         &lt;element name="DiscountAmount" type="{http://www.ups.com/XMLSchema/XOLTWS/Rate/v1.1}ChargesType"/>
 *         &lt;element name="DiscountPercentage" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="NetCharge" type="{http://www.ups.com/XMLSchema/XOLTWS/Rate/v1.1}ChargesType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TransportationChargesType", propOrder = {
    "grossCharge",
    "discountAmount",
    "discountPercentage",
    "netCharge"
})
public class TransportationChargesType {

    @XmlElement(name = "GrossCharge", required = true)
    protected ChargesType grossCharge;
    @XmlElement(name = "DiscountAmount", required = true)
    protected ChargesType discountAmount;
    @XmlElement(name = "DiscountPercentage", required = true)
    protected String discountPercentage;
    @XmlElement(name = "NetCharge", required = true)
    protected ChargesType netCharge;

    /**
     * Gets the value of the grossCharge property.
     * 
     * @return
     *     possible object is
     *     {@link ChargesType }
     *     
     */
    public ChargesType getGrossCharge() {
        return grossCharge;
    }

    /**
     * Sets the value of the grossCharge property.
     * 
     * @param value
     *     allowed object is
     *     {@link ChargesType }
     *     
     */
    public void setGrossCharge(ChargesType value) {
        this.grossCharge = value;
    }

    /**
     * Gets the value of the discountAmount property.
     * 
     * @return
     *     possible object is
     *     {@link ChargesType }
     *     
     */
    public ChargesType getDiscountAmount() {
        return discountAmount;
    }

    /**
     * Sets the value of the discountAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link ChargesType }
     *     
     */
    public void setDiscountAmount(ChargesType value) {
        this.discountAmount = value;
    }

    /**
     * Gets the value of the discountPercentage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDiscountPercentage() {
        return discountPercentage;
    }

    /**
     * Sets the value of the discountPercentage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDiscountPercentage(String value) {
        this.discountPercentage = value;
    }

    /**
     * Gets the value of the netCharge property.
     * 
     * @return
     *     possible object is
     *     {@link ChargesType }
     *     
     */
    public ChargesType getNetCharge() {
        return netCharge;
    }

    /**
     * Sets the value of the netCharge property.
     * 
     * @param value
     *     allowed object is
     *     {@link ChargesType }
     *     
     */
    public void setNetCharge(ChargesType value) {
        this.netCharge = value;
    }

}
