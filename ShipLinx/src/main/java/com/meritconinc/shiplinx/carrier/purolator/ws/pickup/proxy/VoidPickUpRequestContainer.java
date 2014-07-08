
package com.meritconinc.shiplinx.carrier.purolator.ws.pickup.proxy;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * VoidPickUpRequest
 * 
 * <p>Java class for VoidPickUpRequestContainer complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="VoidPickUpRequestContainer">
 *   &lt;complexContent>
 *     &lt;extension base="{http://purolator.com/pws/datatypes/v1}RequestContainer">
 *       &lt;sequence>
 *         &lt;element name="PickUpConfirmationNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "VoidPickUpRequestContainer", propOrder = {
    "pickUpConfirmationNumber"
})
public class VoidPickUpRequestContainer
    extends RequestContainer
{

    @XmlElementRef(name = "PickUpConfirmationNumber", namespace = "http://purolator.com/pws/datatypes/v1", type = JAXBElement.class)
    protected JAXBElement<String> pickUpConfirmationNumber;

    /**
     * Gets the value of the pickUpConfirmationNumber property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getPickUpConfirmationNumber() {
        return pickUpConfirmationNumber;
    }

    /**
     * Sets the value of the pickUpConfirmationNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setPickUpConfirmationNumber(JAXBElement<String> value) {
        this.pickUpConfirmationNumber = value;
    }

}
