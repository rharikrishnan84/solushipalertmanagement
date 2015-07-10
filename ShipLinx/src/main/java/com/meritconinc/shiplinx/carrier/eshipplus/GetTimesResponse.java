
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
 *         &lt;element name="GetTimesResult" type="{http://www.eshipplus.com/}ArrayOfWSTime2" minOccurs="0"/>
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
    "getTimesResult"
})
@XmlRootElement(name = "GetTimesResponse")
public class GetTimesResponse {

    @XmlElement(name = "GetTimesResult")
    protected ArrayOfWSTime2 getTimesResult;

    /**
     * Gets the value of the getTimesResult property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfWSTime2 }
     *     
     */
    public ArrayOfWSTime2 getGetTimesResult() {
        return getTimesResult;
    }

    /**
     * Sets the value of the getTimesResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfWSTime2 }
     *     
     */
    public void setGetTimesResult(ArrayOfWSTime2 value) {
        this.getTimesResult = value;
    }

}
