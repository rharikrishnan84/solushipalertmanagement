
package com.meritconinc.shiplinx.carrier.purolator.ws.shipping.proxy;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * VoidShipmentRequest
 * 
 * <p>Java class for VoidShipmentRequestContainer complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="VoidShipmentRequestContainer">
 *   &lt;complexContent>
 *     &lt;extension base="{http://purolator.com/pws/datatypes/v1}RequestContainer">
 *       &lt;sequence>
 *         &lt;element name="PIN" type="{http://purolator.com/pws/datatypes/v1}PIN"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "VoidShipmentRequestContainer", propOrder = {
    "pin"
})
public class VoidShipmentRequestContainer
    extends RequestContainer
{

    @XmlElement(name = "PIN", required = true, nillable = true)
    protected PIN pin;

    /**
     * Gets the value of the pin property.
     * 
     * @return
     *     possible object is
     *     {@link PIN }
     *     
     */
    public PIN getPIN() {
        return pin;
    }

    /**
     * Sets the value of the pin property.
     * 
     * @param value
     *     allowed object is
     *     {@link PIN }
     *     
     */
    public void setPIN(PIN value) {
        this.pin = value;
    }

}
