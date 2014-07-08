
package com.meritconinc.shiplinx.carrier.purolator.ws.shipping.proxy;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * ValidateShipmentRequest
 * 
 * <p>Java class for ValidateShipmentRequestContainer complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ValidateShipmentRequestContainer">
 *   &lt;complexContent>
 *     &lt;extension base="{http://purolator.com/pws/datatypes/v1}RequestContainer">
 *       &lt;sequence>
 *         &lt;element name="Shipment" type="{http://purolator.com/pws/datatypes/v1}Shipment"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ValidateShipmentRequestContainer", propOrder = {
    "shipment"
})
public class ValidateShipmentRequestContainer
    extends RequestContainer
{

    @XmlElement(name = "Shipment", required = true, nillable = true)
    protected Shipment shipment;

    /**
     * Gets the value of the shipment property.
     * 
     * @return
     *     possible object is
     *     {@link Shipment }
     *     
     */
    public Shipment getShipment() {
        return shipment;
    }

    /**
     * Sets the value of the shipment property.
     * 
     * @param value
     *     allowed object is
     *     {@link Shipment }
     *     
     */
    public void setShipment(Shipment value) {
        this.shipment = value;
    }

}
