
package com.meritconinc.shiplinx.carrier.purolator.ws.tracking.proxy;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * GetDeliveryDetailsResponse
 * 
 * <p>Java class for GetDeliveryDetailsResponseContainer complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GetDeliveryDetailsResponseContainer">
 *   &lt;complexContent>
 *     &lt;extension base="{http://purolator.com/pws/datatypes/v1}ResponseContainer">
 *       &lt;sequence>
 *         &lt;element name="DeliveryDetails" type="{http://purolator.com/pws/datatypes/v1}DeliveryScan" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetDeliveryDetailsResponseContainer", propOrder = {
    "deliveryDetails"
})
public class GetDeliveryDetailsResponseContainer
    extends ResponseContainer
{

    @XmlElementRef(name = "DeliveryDetails", namespace = "http://purolator.com/pws/datatypes/v1", type = JAXBElement.class)
    protected JAXBElement<DeliveryScan> deliveryDetails;

    /**
     * Gets the value of the deliveryDetails property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link DeliveryScan }{@code >}
     *     
     */
    public JAXBElement<DeliveryScan> getDeliveryDetails() {
        return deliveryDetails;
    }

    /**
     * Sets the value of the deliveryDetails property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link DeliveryScan }{@code >}
     *     
     */
    public void setDeliveryDetails(JAXBElement<DeliveryScan> value) {
        this.deliveryDetails = value;
    }

}
