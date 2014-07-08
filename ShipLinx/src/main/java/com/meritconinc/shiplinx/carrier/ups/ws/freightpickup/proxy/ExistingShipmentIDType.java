
package com.meritconinc.shiplinx.carrier.ups.ws.freightpickup.proxy;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ExistingShipmentIDType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ExistingShipmentIDType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ShipmentNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="BOLID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ExistingShipmentIDType", propOrder = {
    "shipmentNumber",
    "bolid"
})
public class ExistingShipmentIDType {

    @XmlElement(name = "ShipmentNumber", required = true)
    protected String shipmentNumber;
    @XmlElement(name = "BOLID", required = true)
    protected String bolid;

    /**
     * Gets the value of the shipmentNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShipmentNumber() {
        return shipmentNumber;
    }

    /**
     * Sets the value of the shipmentNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShipmentNumber(String value) {
        this.shipmentNumber = value;
    }

    /**
     * Gets the value of the bolid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBOLID() {
        return bolid;
    }

    /**
     * Sets the value of the bolid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBOLID(String value) {
        this.bolid = value;
    }

}
