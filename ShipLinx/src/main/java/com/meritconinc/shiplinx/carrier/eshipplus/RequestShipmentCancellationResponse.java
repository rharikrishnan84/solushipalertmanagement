
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
 *         &lt;element name="RequestShipmentCancellationResult" type="{http://www.eshipplus.com/}WSReturn" minOccurs="0"/>
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
    "requestShipmentCancellationResult"
})
@XmlRootElement(name = "RequestShipmentCancellationResponse")
public class RequestShipmentCancellationResponse {

    @XmlElement(name = "RequestShipmentCancellationResult")
    protected WSReturn requestShipmentCancellationResult;

    /**
     * Gets the value of the requestShipmentCancellationResult property.
     * 
     * @return
     *     possible object is
     *     {@link WSReturn }
     *     
     */
    public WSReturn getRequestShipmentCancellationResult() {
        return requestShipmentCancellationResult;
    }

    /**
     * Sets the value of the requestShipmentCancellationResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link WSReturn }
     *     
     */
    public void setRequestShipmentCancellationResult(WSReturn value) {
        this.requestShipmentCancellationResult = value;
    }

}
