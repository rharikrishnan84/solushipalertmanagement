
package com.meritconinc.shiplinx.carrier.purolator.ws.returnsmanagement.proxy;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * CreateReturnsManagementShipmentRequestContainer
 * 
 * <p>Java class for CreateReturnsManagementShipmentRequestContainer complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CreateReturnsManagementShipmentRequestContainer">
 *   &lt;complexContent>
 *     &lt;extension base="{http://purolator.com/pws/datatypes/v1}RequestContainer">
 *       &lt;sequence>
 *         &lt;element name="RMA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ReturnsManagementShipment" type="{http://purolator.com/pws/datatypes/v1}ReturnsManagementShipment"/>
 *         &lt;element name="PrinterType" type="{http://purolator.com/pws/datatypes/v1}PrinterType"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CreateReturnsManagementShipmentRequestContainer", propOrder = {
    "rma",
    "returnsManagementShipment",
    "printerType"
})
public class CreateReturnsManagementShipmentRequestContainer
    extends RequestContainer
{

    @XmlElementRef(name = "RMA", namespace = "http://purolator.com/pws/datatypes/v1", type = JAXBElement.class)
    protected JAXBElement<String> rma;
    @XmlElement(name = "ReturnsManagementShipment", required = true, nillable = true)
    protected ReturnsManagementShipment returnsManagementShipment;
    @XmlElement(name = "PrinterType", required = true, nillable = true)
    protected PrinterType printerType;

    /**
     * Gets the value of the rma property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getRMA() {
        return rma;
    }

    /**
     * Sets the value of the rma property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setRMA(JAXBElement<String> value) {
        this.rma = value;
    }

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

    /**
     * Gets the value of the printerType property.
     * 
     * @return
     *     possible object is
     *     {@link PrinterType }
     *     
     */
    public PrinterType getPrinterType() {
        return printerType;
    }

    /**
     * Sets the value of the printerType property.
     * 
     * @param value
     *     allowed object is
     *     {@link PrinterType }
     *     
     */
    public void setPrinterType(PrinterType value) {
        this.printerType = value;
    }

}
