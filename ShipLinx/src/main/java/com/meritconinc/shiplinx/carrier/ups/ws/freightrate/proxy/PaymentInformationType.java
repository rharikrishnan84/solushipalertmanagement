
package com.meritconinc.shiplinx.carrier.ups.ws.freightrate.proxy;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PaymentInformationType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PaymentInformationType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Payer" type="{http://www.ups.com/XMLSchema/XOLTWS/FreightRate/v1.0}PayerType"/>
 *         &lt;element name="ShipmentBillingOption" type="{http://www.ups.com/XMLSchema/XOLTWS/FreightRate/v1.0}RateCodeDescriptionType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PaymentInformationType", propOrder = {
    "payer",
    "shipmentBillingOption"
})
public class PaymentInformationType {

    @XmlElement(name = "Payer", required = true)
    protected PayerType payer;
    @XmlElement(name = "ShipmentBillingOption", required = true)
    protected RateCodeDescriptionType shipmentBillingOption;

    /**
     * Gets the value of the payer property.
     * 
     * @return
     *     possible object is
     *     {@link PayerType }
     *     
     */
    public PayerType getPayer() {
        return payer;
    }

    /**
     * Sets the value of the payer property.
     * 
     * @param value
     *     allowed object is
     *     {@link PayerType }
     *     
     */
    public void setPayer(PayerType value) {
        this.payer = value;
    }

    /**
     * Gets the value of the shipmentBillingOption property.
     * 
     * @return
     *     possible object is
     *     {@link RateCodeDescriptionType }
     *     
     */
    public RateCodeDescriptionType getShipmentBillingOption() {
        return shipmentBillingOption;
    }

    /**
     * Sets the value of the shipmentBillingOption property.
     * 
     * @param value
     *     allowed object is
     *     {@link RateCodeDescriptionType }
     *     
     */
    public void setShipmentBillingOption(RateCodeDescriptionType value) {
        this.shipmentBillingOption = value;
    }

}
