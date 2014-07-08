
package com.meritconinc.shiplinx.carrier.purolator.ws.pickup.proxy;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * VoidPickUpRespone
 * 
 * <p>Java class for VoidPickUpResponseContainer complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="VoidPickUpResponseContainer">
 *   &lt;complexContent>
 *     &lt;extension base="{http://purolator.com/pws/datatypes/v1}ResponseContainer">
 *       &lt;sequence>
 *         &lt;element name="PickUpVoided" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "VoidPickUpResponseContainer", propOrder = {
    "pickUpVoided"
})
public class VoidPickUpResponseContainer
    extends ResponseContainer
{

    @XmlElement(name = "PickUpVoided")
    protected Boolean pickUpVoided;

    /**
     * Gets the value of the pickUpVoided property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isPickUpVoided() {
        return pickUpVoided;
    }

    /**
     * Sets the value of the pickUpVoided property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setPickUpVoided(Boolean value) {
        this.pickUpVoided = value;
    }

}
