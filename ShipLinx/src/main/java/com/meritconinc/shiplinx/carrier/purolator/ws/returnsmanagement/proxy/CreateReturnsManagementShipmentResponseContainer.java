
package com.meritconinc.shiplinx.carrier.purolator.ws.returnsmanagement.proxy;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * CreateReturnsManagementShipmentResponseContainer
 * 
 * <p>Java class for CreateReturnsManagementShipmentResponseContainer complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CreateReturnsManagementShipmentResponseContainer">
 *   &lt;complexContent>
 *     &lt;extension base="{http://purolator.com/pws/datatypes/v1}ResponseContainer">
 *       &lt;sequence>
 *         &lt;element name="ShipmentPIN" type="{http://purolator.com/pws/datatypes/v1}PIN"/>
 *         &lt;element name="PiecePINs" type="{http://purolator.com/pws/datatypes/v1}ArrayOfPIN" minOccurs="0"/>
 *         &lt;element name="RMA" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CreateReturnsManagementShipmentResponseContainer", propOrder = {
    "shipmentPIN",
    "piecePINs",
    "rma"
})
public class CreateReturnsManagementShipmentResponseContainer
    extends ResponseContainer
{

    @XmlElement(name = "ShipmentPIN", required = true, nillable = true)
    protected PIN shipmentPIN;
    @XmlElementRef(name = "PiecePINs", namespace = "http://purolator.com/pws/datatypes/v1", type = JAXBElement.class)
    protected JAXBElement<ArrayOfPIN> piecePINs;
    @XmlElement(name = "RMA", required = true, nillable = true)
    protected String rma;

    /**
     * Gets the value of the shipmentPIN property.
     * 
     * @return
     *     possible object is
     *     {@link PIN }
     *     
     */
    public PIN getShipmentPIN() {
        return shipmentPIN;
    }

    /**
     * Sets the value of the shipmentPIN property.
     * 
     * @param value
     *     allowed object is
     *     {@link PIN }
     *     
     */
    public void setShipmentPIN(PIN value) {
        this.shipmentPIN = value;
    }

    /**
     * Gets the value of the piecePINs property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfPIN }{@code >}
     *     
     */
    public JAXBElement<ArrayOfPIN> getPiecePINs() {
        return piecePINs;
    }

    /**
     * Sets the value of the piecePINs property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfPIN }{@code >}
     *     
     */
    public void setPiecePINs(JAXBElement<ArrayOfPIN> value) {
        this.piecePINs = value;
    }

    /**
     * Gets the value of the rma property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRMA() {
        return rma;
    }

    /**
     * Sets the value of the rma property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRMA(String value) {
        this.rma = value;
    }

}
