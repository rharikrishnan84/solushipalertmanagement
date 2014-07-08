
package com.meritconinc.shiplinx.carrier.purolator.ws.shipping.proxy;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * CreateShipmentResponse
 * 
 * <p>Java class for CreateShipmentResponseContainer complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CreateShipmentResponseContainer">
 *   &lt;complexContent>
 *     &lt;extension base="{http://purolator.com/pws/datatypes/v1}ResponseContainer">
 *       &lt;sequence>
 *         &lt;element name="ShipmentPIN" type="{http://purolator.com/pws/datatypes/v1}PIN" minOccurs="0"/>
 *         &lt;element name="PiecePINs" type="{http://purolator.com/pws/datatypes/v1}ArrayOfPIN" minOccurs="0"/>
 *         &lt;element name="ReturnShipmentPINs" type="{http://purolator.com/pws/datatypes/v1}ArrayOfPIN" minOccurs="0"/>
 *         &lt;element name="ExpressChequePIN" type="{http://purolator.com/pws/datatypes/v1}PIN" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CreateShipmentResponseContainer", propOrder = {
    "shipmentPIN",
    "piecePINs",
    "returnShipmentPINs",
    "expressChequePIN"
})
public class CreateShipmentResponseContainer
    extends ResponseContainer
{

    @XmlElementRef(name = "ShipmentPIN", namespace = "http://purolator.com/pws/datatypes/v1", type = JAXBElement.class)
    protected JAXBElement<PIN> shipmentPIN;
    @XmlElementRef(name = "PiecePINs", namespace = "http://purolator.com/pws/datatypes/v1", type = JAXBElement.class)
    protected JAXBElement<ArrayOfPIN> piecePINs;
    @XmlElementRef(name = "ReturnShipmentPINs", namespace = "http://purolator.com/pws/datatypes/v1", type = JAXBElement.class)
    protected JAXBElement<ArrayOfPIN> returnShipmentPINs;
    @XmlElementRef(name = "ExpressChequePIN", namespace = "http://purolator.com/pws/datatypes/v1", type = JAXBElement.class)
    protected JAXBElement<PIN> expressChequePIN;

    /**
     * Gets the value of the shipmentPIN property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link PIN }{@code >}
     *     
     */
    public JAXBElement<PIN> getShipmentPIN() {
        return shipmentPIN;
    }

    /**
     * Sets the value of the shipmentPIN property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link PIN }{@code >}
     *     
     */
    public void setShipmentPIN(JAXBElement<PIN> value) {
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
     * Gets the value of the returnShipmentPINs property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfPIN }{@code >}
     *     
     */
    public JAXBElement<ArrayOfPIN> getReturnShipmentPINs() {
        return returnShipmentPINs;
    }

    /**
     * Sets the value of the returnShipmentPINs property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfPIN }{@code >}
     *     
     */
    public void setReturnShipmentPINs(JAXBElement<ArrayOfPIN> value) {
        this.returnShipmentPINs = value;
    }

    /**
     * Gets the value of the expressChequePIN property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link PIN }{@code >}
     *     
     */
    public JAXBElement<PIN> getExpressChequePIN() {
        return expressChequePIN;
    }

    /**
     * Sets the value of the expressChequePIN property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link PIN }{@code >}
     *     
     */
    public void setExpressChequePIN(JAXBElement<PIN> value) {
        this.expressChequePIN = value;
    }

}
