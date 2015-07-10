
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
 *         &lt;element name="GetAccessorialServicesResult" type="{http://www.eshipplus.com/}ArrayOfWSAccessorialService" minOccurs="0"/>
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
    "getAccessorialServicesResult"
})
@XmlRootElement(name = "GetAccessorialServicesResponse")
public class GetAccessorialServicesResponse {

    @XmlElement(name = "GetAccessorialServicesResult")
    protected ArrayOfWSAccessorialService getAccessorialServicesResult;

    /**
     * Gets the value of the getAccessorialServicesResult property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfWSAccessorialService }
     *     
     */
    public ArrayOfWSAccessorialService getGetAccessorialServicesResult() {
        return getAccessorialServicesResult;
    }

    /**
     * Sets the value of the getAccessorialServicesResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfWSAccessorialService }
     *     
     */
    public void setGetAccessorialServicesResult(ArrayOfWSAccessorialService value) {
        this.getAccessorialServicesResult = value;
    }

}
