
package com.meritconinc.shiplinx.carrier.purolator.ws.returnsmanagement.proxy;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * ValidateReturnsManagementShipmentRequestContainer
 * 
 * <p>Java class for ValidateReturnsManagementShipmentRequestContainer complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ValidateReturnsManagementShipmentRequestContainer">
 *   &lt;complexContent>
 *     &lt;extension base="{http://purolator.com/pws/datatypes/v1}RequestContainer">
 *       &lt;sequence>
 *         &lt;element name="ReturnsManagementShipment" type="{http://purolator.com/pws/datatypes/v1}ReturnsManagementShipment"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ValidateReturnsManagementShipmentRequestContainer", propOrder = {
    "returnsManagementShipment"
})
public class ValidateReturnsManagementShipmentRequestContainer
    extends RequestContainer
{

    @XmlElement(name = "ReturnsManagementShipment", required = true, nillable = true)
    protected ReturnsManagementShipment returnsManagementShipment;

    /**
     * Gets the value of the returnsManagementShipment property.
     * 
     * @return
     *     possible object is
     *     {@link ReturnsManagementShipment }
     *     
     */
    public ReturnsManagementShipment getReturnsManagementShipment() {
        return returnsManagementShipment;
    }

    /**
     * Sets the value of the returnsManagementShipment property.
     * 
     * @param value
     *     allowed object is
     *     {@link ReturnsManagementShipment }
     *     
     */
    public void setReturnsManagementShipment(ReturnsManagementShipment value) {
        this.returnsManagementShipment = value;
    }

}
