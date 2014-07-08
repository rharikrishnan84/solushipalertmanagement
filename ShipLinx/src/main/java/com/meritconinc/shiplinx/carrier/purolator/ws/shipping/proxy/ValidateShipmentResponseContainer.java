
package com.meritconinc.shiplinx.carrier.purolator.ws.shipping.proxy;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * ValidateShipmentResponse
 * 
 * <p>Java class for ValidateShipmentResponseContainer complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ValidateShipmentResponseContainer">
 *   &lt;complexContent>
 *     &lt;extension base="{http://purolator.com/pws/datatypes/v1}ResponseContainer">
 *       &lt;sequence>
 *         &lt;element name="ValidShipment" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ValidateShipmentResponseContainer", propOrder = {
    "validShipment"
})
public class ValidateShipmentResponseContainer
    extends ResponseContainer
{

    @XmlElement(name = "ValidShipment")
    protected Boolean validShipment;

    /**
     * Gets the value of the validShipment property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isValidShipment() {
        return validShipment;
    }

    /**
     * Sets the value of the validShipment property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setValidShipment(Boolean value) {
        this.validShipment = value;
    }

}
