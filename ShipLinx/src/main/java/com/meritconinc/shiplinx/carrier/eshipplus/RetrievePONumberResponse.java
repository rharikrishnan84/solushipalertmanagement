
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
 *         &lt;element name="RetrievePONumberResult" type="{http://www.eshipplus.com/}WSCustomerPONumber" minOccurs="0"/>
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
    "retrievePONumberResult"
})
@XmlRootElement(name = "RetrievePONumberResponse")
public class RetrievePONumberResponse {

    @XmlElement(name = "RetrievePONumberResult")
    protected WSCustomerPONumber retrievePONumberResult;

    /**
     * Gets the value of the retrievePONumberResult property.
     * 
     * @return
     *     possible object is
     *     {@link WSCustomerPONumber }
     *     
     */
    public WSCustomerPONumber getRetrievePONumberResult() {
        return retrievePONumberResult;
    }

    /**
     * Sets the value of the retrievePONumberResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link WSCustomerPONumber }
     *     
     */
    public void setRetrievePONumberResult(WSCustomerPONumber value) {
        this.retrievePONumberResult = value;
    }

}
