
package com.meritconinc.shiplinx.carrier.ups.ws.track.proxy;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ShipFromRequestType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ShipFromRequestType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Address" type="{http://www.ups.com/XMLSchema/XOLTWS/Track/v2.0}AddressRequestType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ShipFromRequestType", propOrder = {
    "address"
})
public class ShipFromRequestType {

    @XmlElement(name = "Address")
    protected AddressRequestType address;

    /**
     * Gets the value of the address property.
     * 
     * @return
     *     possible object is
     *     {@link AddressRequestType }
     *     
     */
    public AddressRequestType getAddress() {
        return address;
    }

    /**
     * Sets the value of the address property.
     * 
     * @param value
     *     allowed object is
     *     {@link AddressRequestType }
     *     
     */
    public void setAddress(AddressRequestType value) {
        this.address = value;
    }

}
