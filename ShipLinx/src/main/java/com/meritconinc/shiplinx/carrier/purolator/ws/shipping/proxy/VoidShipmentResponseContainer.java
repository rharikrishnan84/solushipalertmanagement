
package com.meritconinc.shiplinx.carrier.purolator.ws.shipping.proxy;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * VoidShipmentResponse
 * 
 * <p>Java class for VoidShipmentResponseContainer complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="VoidShipmentResponseContainer">
 *   &lt;complexContent>
 *     &lt;extension base="{http://purolator.com/pws/datatypes/v1}ResponseContainer">
 *       &lt;sequence>
 *         &lt;element name="ShipmentVoided" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "VoidShipmentResponseContainer", propOrder = {
    "shipmentVoided"
})
public class VoidShipmentResponseContainer
    extends ResponseContainer
{

    @XmlElement(name = "ShipmentVoided")
    protected Boolean shipmentVoided;

    /**
     * Gets the value of the shipmentVoided property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isShipmentVoided() {
        return shipmentVoided;
    }

    /**
     * Sets the value of the shipmentVoided property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setShipmentVoided(Boolean value) {
        this.shipmentVoided = value;
    }

}
