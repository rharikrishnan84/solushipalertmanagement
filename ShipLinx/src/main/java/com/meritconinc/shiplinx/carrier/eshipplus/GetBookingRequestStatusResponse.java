
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
 *         &lt;element name="GetBookingRequestStatusResult" type="{http://www.eshipplus.com/}WSBookingRequestStatus" minOccurs="0"/>
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
    "getBookingRequestStatusResult"
})
@XmlRootElement(name = "GetBookingRequestStatusResponse")
public class GetBookingRequestStatusResponse {

    @XmlElement(name = "GetBookingRequestStatusResult")
    protected WSBookingRequestStatus getBookingRequestStatusResult;

    /**
     * Gets the value of the getBookingRequestStatusResult property.
     * 
     * @return
     *     possible object is
     *     {@link WSBookingRequestStatus }
     *     
     */
    public WSBookingRequestStatus getGetBookingRequestStatusResult() {
        return getBookingRequestStatusResult;
    }

    /**
     * Sets the value of the getBookingRequestStatusResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link WSBookingRequestStatus }
     *     
     */
    public void setGetBookingRequestStatusResult(WSBookingRequestStatus value) {
        this.getBookingRequestStatusResult = value;
    }

}
