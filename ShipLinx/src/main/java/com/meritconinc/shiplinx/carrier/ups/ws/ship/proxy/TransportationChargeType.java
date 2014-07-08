
package com.meritconinc.shiplinx.carrier.ups.ws.ship.proxy;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TransportationChargeType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TransportationChargeType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="GrossCharge" type="{http://www.ups.com/XMLSchema/XOLTWS/Ship/v1.0}ShipChargeType"/>
 *         &lt;element name="DiscountAmount" type="{http://www.ups.com/XMLSchema/XOLTWS/Ship/v1.0}ShipChargeType"/>
 *         &lt;element name="DiscountPercentage" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="NetCharge" type="{http://www.ups.com/XMLSchema/XOLTWS/Ship/v1.0}ShipChargeType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TransportationChargeType", propOrder = {
    "grossCharge",
    "discountAmount",
    "discountPercentage",
    "netCharge"
})
public class TransportationChargeType {

    @XmlElement(name = "GrossCharge", required = true)
    protected ShipChargeType grossCharge;
    @XmlElement(name = "DiscountAmount", required = true)
    protected ShipChargeType discountAmount;
    @XmlElement(name = "DiscountPercentage", required = true)
    protected String discountPercentage;
    @XmlElement(name = "NetCharge", required = true)
    protected ShipChargeType netCharge;

    /**
     * Gets the value of the grossCharge property.
     * 
     * @return
     *     possible object is
     *     {@link ShipChargeType }
     *     
     */
    public ShipChargeType getGrossCharge() {
        return grossCharge;
    }

    /**
     * Sets the value of the grossCharge property.
     * 
     * @param value
     *     allowed object is
     *     {@link ShipChargeType }
     *     
     */
    public void setGrossCharge(ShipChargeType value) {
        this.grossCharge = value;
    }

    /**
     * Gets the value of the discountAmount property.
     * 
     * @return
     *     possible object is
     *     {@link ShipChargeType }
     *     
     */
    public ShipChargeType getDiscountAmount() {
        return discountAmount;
    }

    /**
     * Sets the value of the discountAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link ShipChargeType }
     *     
     */
    public void setDiscountAmount(ShipChargeType value) {
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
     *     {@link ShipChargeType }
     *     
     */
    public ShipChargeType getNetCharge() {
        return netCharge;
    }

    /**
     * Sets the value of the netCharge property.
     * 
     * @param value
     *     allowed object is
     *     {@link ShipChargeType }
     *     
     */
    public void setNetCharge(ShipChargeType value) {
        this.netCharge = value;
    }

}
