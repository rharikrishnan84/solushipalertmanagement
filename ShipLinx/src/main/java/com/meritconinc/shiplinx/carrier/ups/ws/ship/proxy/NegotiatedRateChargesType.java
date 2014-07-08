
package com.meritconinc.shiplinx.carrier.ups.ws.ship.proxy;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for NegotiatedRateChargesType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="NegotiatedRateChargesType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="TotalCharge" type="{http://www.ups.com/XMLSchema/XOLTWS/Ship/v1.0}ShipChargeType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "NegotiatedRateChargesType", propOrder = {
    "totalCharge"
})
public class NegotiatedRateChargesType {

    @XmlElement(name = "TotalCharge")
    protected ShipChargeType totalCharge;

    /**
     * Gets the value of the totalCharge property.
     * 
     * @return
     *     possible object is
     *     {@link ShipChargeType }
     *     
     */
    public ShipChargeType getTotalCharge() {
        return totalCharge;
    }

    /**
     * Sets the value of the totalCharge property.
     * 
     * @param value
     *     allowed object is
     *     {@link ShipChargeType }
     *     
     */
    public void setTotalCharge(ShipChargeType value) {
        this.totalCharge = value;
    }

}
