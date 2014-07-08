
package com.meritconinc.shiplinx.carrier.purolator.ws.shipping.proxy;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * CreateShipmentRequest
 * 
 * <p>Java class for CreateShipmentRequestContainer complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CreateShipmentRequestContainer">
 *   &lt;complexContent>
 *     &lt;extension base="{http://purolator.com/pws/datatypes/v1}RequestContainer">
 *       &lt;sequence>
 *         &lt;element name="Shipment" type="{http://purolator.com/pws/datatypes/v1}Shipment"/>
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
@XmlType(name = "CreateShipmentRequestContainer", propOrder = {
    "shipment",
    "printerType"
})
public class CreateShipmentRequestContainer
    extends RequestContainer
{

    @XmlElement(name = "Shipment", required = true, nillable = true)
    protected Shipment shipment;
    @XmlElement(name = "PrinterType", required = true, nillable = true)
    protected PrinterType printerType;

    /**
     * Gets the value of the shipment property.
     * 
     * @return
     *     possible object is
     *     {@link Shipment }
     *     
     */
    public Shipment getShipment() {
        return shipment;
    }

    /**
     * Sets the value of the shipment property.
     * 
     * @param value
     *     allowed object is
     *     {@link Shipment }
     *     
     */
    public void setShipment(Shipment value) {
        this.shipment = value;
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
