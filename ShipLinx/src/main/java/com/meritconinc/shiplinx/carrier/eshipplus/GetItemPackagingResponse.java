
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
 *         &lt;element name="GetItemPackagingResult" type="{http://www.eshipplus.com/}ArrayOfWSItemPackage" minOccurs="0"/>
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
    "getItemPackagingResult"
})
@XmlRootElement(name = "GetItemPackagingResponse")
public class GetItemPackagingResponse {

    @XmlElement(name = "GetItemPackagingResult")
    protected ArrayOfWSItemPackage getItemPackagingResult;

    /**
     * Gets the value of the getItemPackagingResult property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfWSItemPackage }
     *     
     */
    public ArrayOfWSItemPackage getGetItemPackagingResult() {
        return getItemPackagingResult;
    }

    /**
     * Sets the value of the getItemPackagingResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfWSItemPackage }
     *     
     */
    public void setGetItemPackagingResult(ArrayOfWSItemPackage value) {
        this.getItemPackagingResult = value;
    }

}
