
package com.meritconinc.shiplinx.carrier.eshipplus;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="RateResult" type="{http://www.eshipplus.com/}WSShipment2" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "rateResult"
})
@XmlRootElement(name = "RateResponse")
public class RateResponse {

    @XmlElement(name = "RateResult")
    protected WSShipment2 rateResult;

    /**
     * Gets the value of the rateResult property.
     * 
     * @return
     *     possible object is
     *     {@link WSShipment2 }
     *     
     */
    public WSShipment2 getRateResult() {
        return rateResult;
    }

    /**
     * Sets the value of the rateResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link WSShipment2 }
     *     
     */
    public void setRateResult(WSShipment2 value) {
        this.rateResult = value;
    }

}
