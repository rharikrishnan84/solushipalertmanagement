
package com.meritconinc.shiplinx.carrier.purolator.ws.estimating.proxy;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * GetQuickEstimateRespone
 * 
 * <p>Java class for GetQuickEstimateResponseContainer complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GetQuickEstimateResponseContainer">
 *   &lt;complexContent>
 *     &lt;extension base="{http://purolator.com/pws/datatypes/v1}ResponseContainer">
 *       &lt;sequence>
 *         &lt;element name="ShipmentEstimates" type="{http://purolator.com/pws/datatypes/v1}ArrayOfShipmentEstimate" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetQuickEstimateResponseContainer", propOrder = {
    "shipmentEstimates"
})
public class GetQuickEstimateResponseContainer
    extends ResponseContainer
{

    @XmlElementRef(name = "ShipmentEstimates", namespace = "http://purolator.com/pws/datatypes/v1", type = JAXBElement.class)
    protected JAXBElement<ArrayOfShipmentEstimate> shipmentEstimates;

    /**
     * Gets the value of the shipmentEstimates property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfShipmentEstimate }{@code >}
     *     
     */
    public JAXBElement<ArrayOfShipmentEstimate> getShipmentEstimates() {
        return shipmentEstimates;
    }

    /**
     * Sets the value of the shipmentEstimates property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfShipmentEstimate }{@code >}
     *     
     */
    public void setShipmentEstimates(JAXBElement<ArrayOfShipmentEstimate> value) {
        this.shipmentEstimates = value;
    }

}
