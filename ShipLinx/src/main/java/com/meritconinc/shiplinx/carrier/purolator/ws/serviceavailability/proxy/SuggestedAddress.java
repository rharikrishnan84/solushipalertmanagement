
package com.meritconinc.shiplinx.carrier.purolator.ws.serviceavailability.proxy;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * SuggestedShortAddress
 * 
 * <p>Java class for SuggestedAddress complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SuggestedAddress">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Address" type="{http://purolator.com/pws/datatypes/v1}ShortAddress"/>
 *         &lt;element name="ResponseInformation" type="{http://purolator.com/pws/datatypes/v1}ResponseInformation"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SuggestedAddress", propOrder = {
    "address",
    "responseInformation"
})
public class SuggestedAddress {

    @XmlElement(name = "Address", required = true, nillable = true)
    protected ShortAddress address;
    @XmlElement(name = "ResponseInformation", required = true, nillable = true)
    protected ResponseInformation responseInformation;

    /**
     * Gets the value of the address property.
     * 
     * @return
     *     possible object is
     *     {@link ShortAddress }
     *     
     */
    public ShortAddress getAddress() {
        return address;
    }

    /**
     * Sets the value of the address property.
     * 
     * @param value
     *     allowed object is
     *     {@link ShortAddress }
     *     
     */
    public void setAddress(ShortAddress value) {
        this.address = value;
    }

    /**
     * Gets the value of the responseInformation property.
     * 
     * @return
     *     possible object is
     *     {@link ResponseInformation }
     *     
     */
    public ResponseInformation getResponseInformation() {
        return responseInformation;
    }

    /**
     * Sets the value of the responseInformation property.
     * 
     * @param value
     *     allowed object is
     *     {@link ResponseInformation }
     *     
     */
    public void setResponseInformation(ResponseInformation value) {
        this.responseInformation = value;
    }

}
