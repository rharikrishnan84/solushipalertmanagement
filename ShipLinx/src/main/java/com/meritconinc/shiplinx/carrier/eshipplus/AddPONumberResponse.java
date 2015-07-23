
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
 *         &lt;element name="AddPONumberResult" type="{http://www.eshipplus.com/}WSReturn" minOccurs="0"/>
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
    "addPONumberResult"
})
@XmlRootElement(name = "AddPONumberResponse")
public class AddPONumberResponse {

    @XmlElement(name = "AddPONumberResult")
    protected WSReturn addPONumberResult;

    /**
     * Gets the value of the addPONumberResult property.
     * 
     * @return
     *     possible object is
     *     {@link WSReturn }
     *     
     */
    public WSReturn getAddPONumberResult() {
        return addPONumberResult;
    }

    /**
     * Sets the value of the addPONumberResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link WSReturn }
     *     
     */
    public void setAddPONumberResult(WSReturn value) {
        this.addPONumberResult = value;
    }

}