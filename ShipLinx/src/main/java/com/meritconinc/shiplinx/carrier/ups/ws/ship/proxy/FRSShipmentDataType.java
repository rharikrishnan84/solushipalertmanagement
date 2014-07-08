
package com.meritconinc.shiplinx.carrier.ups.ws.ship.proxy;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for FRSShipmentDataType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="FRSShipmentDataType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="TransportationCharges" type="{http://www.ups.com/XMLSchema/XOLTWS/Ship/v1.0}TransportationChargeType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FRSShipmentDataType", propOrder = {
    "transportationCharges"
})
public class FRSShipmentDataType {

    @XmlElement(name = "TransportationCharges", required = true)
    protected TransportationChargeType transportationCharges;

    /**
     * Gets the value of the transportationCharges property.
     * 
     * @return
     *     possible object is
     *     {@link TransportationChargeType }
     *     
     */
    public TransportationChargeType getTransportationCharges() {
        return transportationCharges;
    }

    /**
     * Sets the value of the transportationCharges property.
     * 
     * @param value
     *     allowed object is
     *     {@link TransportationChargeType }
     *     
     */
    public void setTransportationCharges(TransportationChargeType value) {
        this.transportationCharges = value;
    }

}
