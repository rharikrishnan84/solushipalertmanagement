
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
 *         &lt;element name="GetInputOptionsResult" type="{http://www.eshipplus.com/}WSOptions" minOccurs="0"/>
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
    "getInputOptionsResult"
})
@XmlRootElement(name = "GetInputOptionsResponse")
public class GetInputOptionsResponse {

    @XmlElement(name = "GetInputOptionsResult")
    protected WSOptions getInputOptionsResult;

    /**
     * Gets the value of the getInputOptionsResult property.
     * 
     * @return
     *     possible object is
     *     {@link WSOptions }
     *     
     */
    public WSOptions getGetInputOptionsResult() {
        return getInputOptionsResult;
    }

    /**
     * Sets the value of the getInputOptionsResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link WSOptions }
     *     
     */
    public void setGetInputOptionsResult(WSOptions value) {
        this.getInputOptionsResult = value;
    }

}
