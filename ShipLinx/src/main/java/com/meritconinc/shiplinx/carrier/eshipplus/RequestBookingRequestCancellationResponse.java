
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
 *         &lt;element name="RequestBookingRequestCancellationResult" type="{http://www.eshipplus.com/}WSReturn" minOccurs="0"/>
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
    "requestBookingRequestCancellationResult"
})
@XmlRootElement(name = "RequestBookingRequestCancellationResponse")
public class RequestBookingRequestCancellationResponse {

    @XmlElement(name = "RequestBookingRequestCancellationResult")
    protected WSReturn requestBookingRequestCancellationResult;

    /**
     * Gets the value of the requestBookingRequestCancellationResult property.
     * 
     * @return
     *     possible object is
     *     {@link WSReturn }
     *     
     */
    public WSReturn getRequestBookingRequestCancellationResult() {
        return requestBookingRequestCancellationResult;
    }

    /**
     * Sets the value of the requestBookingRequestCancellationResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link WSReturn }
     *     
     */
    public void setRequestBookingRequestCancellationResult(WSReturn value) {
        this.requestBookingRequestCancellationResult = value;
    }

}
