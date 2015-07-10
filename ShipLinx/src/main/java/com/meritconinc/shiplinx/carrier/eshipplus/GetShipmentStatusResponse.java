
package com.meritconinc.shiplinx.carrier.eshipplus;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="GetShipmentStatusResult" type="{http://www.eshipplus.com/}WSShipmentStatus" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "getShipmentStatusResult"
})
@XmlRootElement(name = "GetShipmentStatusResponse")
public class GetShipmentStatusResponse {

    @XmlElement(name = "GetShipmentStatusResult")
    protected WSShipmentStatus getShipmentStatusResult;

    /**
     * Gets the value of the getShipmentStatusResult property.
     * 
     * @return
     *     possible object is
     *     {@link WSShipmentStatus }
     *     
     */
    public WSShipmentStatus getGetShipmentStatusResult() {
        return getShipmentStatusResult;
    }

    /**
     * Sets the value of the getShipmentStatusResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link WSShipmentStatus }
     *     
     */
    public void setGetShipmentStatusResult(WSShipmentStatus value) {
        this.getShipmentStatusResult = value;
    }

}
