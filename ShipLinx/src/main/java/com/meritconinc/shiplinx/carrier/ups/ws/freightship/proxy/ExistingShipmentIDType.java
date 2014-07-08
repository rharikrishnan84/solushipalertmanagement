
package com.meritconinc.shiplinx.carrier.ups.ws.freightship.proxy;

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
 *         &lt;element name="BOLID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ConfirmationNumber" type="{http://www.ups.com/XMLSchema/XOLTWS/FreightShip/v1.0}ConfirmationNumberType" minOccurs="0"/>
 *         &lt;element name="CreationDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "bolid",
    "confirmationNumber",
    "creationDate"
})
public class ExistingShipmentIDType {

    @XmlElement(name = "ShipmentNumber", required = true)
    protected String shipmentNumber;
    @XmlElement(name = "BOLID")
    protected String bolid;
    @XmlElement(name = "ConfirmationNumber")
    protected ConfirmationNumberType confirmationNumber;
    @XmlElement(name = "CreationDate")
    protected String creationDate;

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

    /**
     * Gets the value of the confirmationNumber property.
     * 
     * @return
     *     possible object is
     *     {@link ConfirmationNumberType }
     *     
     */
    public ConfirmationNumberType getConfirmationNumber() {
        return confirmationNumber;
    }

    /**
     * Sets the value of the confirmationNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link ConfirmationNumberType }
     *     
     */
    public void setConfirmationNumber(ConfirmationNumberType value) {
        this.confirmationNumber = value;
    }

    /**
     * Gets the value of the creationDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreationDate() {
        return creationDate;
    }

    /**
     * Sets the value of the creationDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreationDate(String value) {
        this.creationDate = value;
    }

}
