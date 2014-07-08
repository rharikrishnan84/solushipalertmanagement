
package com.meritconinc.shiplinx.carrier.ups.ws.freightship.proxy;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DangerousGoodsType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DangerousGoodsType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Phone" type="{http://www.ups.com/XMLSchema/XOLTWS/FreightShip/v1.0}FreightShipPhoneType"/>
 *         &lt;element name="TransportationMode" type="{http://www.ups.com/XMLSchema/XOLTWS/FreightShip/v1.0}ShipCodeDescriptionType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DangerousGoodsType", propOrder = {
    "name",
    "phone",
    "transportationMode"
})
public class DangerousGoodsType {

    @XmlElement(name = "Name", required = true)
    protected String name;
    @XmlElement(name = "Phone", required = true)
    protected FreightShipPhoneType phone;
    @XmlElement(name = "TransportationMode")
    protected ShipCodeDescriptionType transportationMode;

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the phone property.
     * 
     * @return
     *     possible object is
     *     {@link FreightShipPhoneType }
     *     
     */
    public FreightShipPhoneType getPhone() {
        return phone;
    }

    /**
     * Sets the value of the phone property.
     * 
     * @param value
     *     allowed object is
     *     {@link FreightShipPhoneType }
     *     
     */
    public void setPhone(FreightShipPhoneType value) {
        this.phone = value;
    }

    /**
     * Gets the value of the transportationMode property.
     * 
     * @return
     *     possible object is
     *     {@link ShipCodeDescriptionType }
     *     
     */
    public ShipCodeDescriptionType getTransportationMode() {
        return transportationMode;
    }

    /**
     * Sets the value of the transportationMode property.
     * 
     * @param value
     *     allowed object is
     *     {@link ShipCodeDescriptionType }
     *     
     */
    public void setTransportationMode(ShipCodeDescriptionType value) {
        this.transportationMode = value;
    }

}
