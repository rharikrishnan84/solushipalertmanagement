
package com.meritconinc.shiplinx.carrier.purolator.ws.pickup.proxy;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Shipment Summary
 * 
 * <p>Java class for ShipmentSummary complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ShipmentSummary">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ShipmentSummaryDetails" type="{http://purolator.com/pws/datatypes/v1}ArrayOfShipmentSummaryDetail"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ShipmentSummary", propOrder = {
    "shipmentSummaryDetails"
})
public class ShipmentSummary {

    @XmlElement(name = "ShipmentSummaryDetails", required = true, nillable = true)
    protected ArrayOfShipmentSummaryDetail shipmentSummaryDetails;

    /**
     * Gets the value of the shipmentSummaryDetails property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfShipmentSummaryDetail }
     *     
     */
    public ArrayOfShipmentSummaryDetail getShipmentSummaryDetails() {
        return shipmentSummaryDetails;
    }

    /**
     * Sets the value of the shipmentSummaryDetails property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfShipmentSummaryDetail }
     *     
     */
    public void setShipmentSummaryDetails(ArrayOfShipmentSummaryDetail value) {
        this.shipmentSummaryDetails = value;
    }

}
