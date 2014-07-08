
package com.meritconinc.shiplinx.carrier.purolator.ws.pickup.proxy;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * ModifyPickUpRequestContainer
 * 
 * <p>Java class for ModifyPickUpRequestContainer complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ModifyPickUpRequestContainer">
 *   &lt;complexContent>
 *     &lt;extension base="{http://purolator.com/pws/datatypes/v1}RequestContainer">
 *       &lt;sequence>
 *         &lt;element name="BillingAccountNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ConfirmationNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ModifyPickupInstruction" type="{http://purolator.com/pws/datatypes/v1}ModifyPickupInstruction"/>
 *         &lt;element name="ShipmentSummary" type="{http://purolator.com/pws/datatypes/v1}ShipmentSummary" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ModifyPickUpRequestContainer", propOrder = {
    "billingAccountNumber",
    "confirmationNumber",
    "modifyPickupInstruction",
    "shipmentSummary"
})
public class ModifyPickUpRequestContainer
    extends RequestContainer
{

    @XmlElement(name = "BillingAccountNumber", required = true, nillable = true)
    protected String billingAccountNumber;
    @XmlElementRef(name = "ConfirmationNumber", namespace = "http://purolator.com/pws/datatypes/v1", type = JAXBElement.class)
    protected JAXBElement<String> confirmationNumber;
    @XmlElement(name = "ModifyPickupInstruction", required = true, nillable = true)
    protected ModifyPickupInstruction modifyPickupInstruction;
    @XmlElementRef(name = "ShipmentSummary", namespace = "http://purolator.com/pws/datatypes/v1", type = JAXBElement.class)
    protected JAXBElement<ShipmentSummary> shipmentSummary;

    /**
     * Gets the value of the billingAccountNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBillingAccountNumber() {
        return billingAccountNumber;
    }

    /**
     * Sets the value of the billingAccountNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBillingAccountNumber(String value) {
        this.billingAccountNumber = value;
    }

    /**
     * Gets the value of the confirmationNumber property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getConfirmationNumber() {
        return confirmationNumber;
    }

    /**
     * Sets the value of the confirmationNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setConfirmationNumber(JAXBElement<String> value) {
        this.confirmationNumber = value;
    }

    /**
     * Gets the value of the modifyPickupInstruction property.
     * 
     * @return
     *     possible object is
     *     {@link ModifyPickupInstruction }
     *     
     */
    public ModifyPickupInstruction getModifyPickupInstruction() {
        return modifyPickupInstruction;
    }

    /**
     * Sets the value of the modifyPickupInstruction property.
     * 
     * @param value
     *     allowed object is
     *     {@link ModifyPickupInstruction }
     *     
     */
    public void setModifyPickupInstruction(ModifyPickupInstruction value) {
        this.modifyPickupInstruction = value;
    }

    /**
     * Gets the value of the shipmentSummary property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ShipmentSummary }{@code >}
     *     
     */
    public JAXBElement<ShipmentSummary> getShipmentSummary() {
        return shipmentSummary;
    }

    /**
     * Sets the value of the shipmentSummary property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ShipmentSummary }{@code >}
     *     
     */
    public void setShipmentSummary(JAXBElement<ShipmentSummary> value) {
        this.shipmentSummary = value;
    }

}
