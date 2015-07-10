
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
 *         &lt;element name="BookResult" type="{http://www.eshipplus.com/}WSShipment2" minOccurs="0"/>
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
    "bookResult"
})
@XmlRootElement(name = "BookResponse")
public class BookResponse {

    @XmlElement(name = "BookResult")
    protected WSShipment2 bookResult;

    /**
     * Gets the value of the bookResult property.
     * 
     * @return
     *     possible object is
     *     {@link WSShipment2 }
     *     
     */
    public WSShipment2 getBookResult() {
        return bookResult;
    }

    /**
     * Sets the value of the bookResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link WSShipment2 }
     *     
     */
    public void setBookResult(WSShipment2 value) {
        this.bookResult = value;
    }

}
