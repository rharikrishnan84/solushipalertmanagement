
package com.meritconinc.shiplinx.carrier.purolator.ws.estimating.proxy;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * GetFullEstimateResponse
 * 
 * <p>Java class for GetFullEstimateResponseContainer complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GetFullEstimateResponseContainer">
 *   &lt;complexContent>
 *     &lt;extension base="{http://purolator.com/pws/datatypes/v1}ResponseContainer">
 *       &lt;sequence>
 *         &lt;element name="ShipmentEstimates" type="{http://purolator.com/pws/datatypes/v1}ArrayOfShipmentEstimate" minOccurs="0"/>
 *         &lt;element name="ReturnShipmentEstimates" type="{http://purolator.com/pws/datatypes/v1}ArrayOfShipmentEstimate" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetFullEstimateResponseContainer", propOrder = {
    "shipmentEstimates",
    "returnShipmentEstimates"
})
public class GetFullEstimateResponseContainer
    extends ResponseContainer
{

    @XmlElementRef(name = "ShipmentEstimates", namespace = "http://purolator.com/pws/datatypes/v1", type = JAXBElement.class)
    protected JAXBElement<ArrayOfShipmentEstimate> shipmentEstimates;
    @XmlElementRef(name = "ReturnShipmentEstimates", namespace = "http://purolator.com/pws/datatypes/v1", type = JAXBElement.class)
    protected JAXBElement<ArrayOfShipmentEstimate> returnShipmentEstimates;

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

    /**
     * Gets the value of the returnShipmentEstimates property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfShipmentEstimate }{@code >}
     *     
     */
    public JAXBElement<ArrayOfShipmentEstimate> getReturnShipmentEstimates() {
        return returnShipmentEstimates;
    }

    /**
     * Sets the value of the returnShipmentEstimates property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfShipmentEstimate }{@code >}
     *     
     */
    public void setReturnShipmentEstimates(JAXBElement<ArrayOfShipmentEstimate> value) {
        this.returnShipmentEstimates = value;
    }

}
