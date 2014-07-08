
package com.meritconinc.shiplinx.carrier.purolator.ws.shipping.proxy;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * ResponseContext
 * 
 * <p>Java class for ResponseContext complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ResponseContext">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ResponseReference" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ResponseContext", propOrder = {
    "responseReference"
})
public class ResponseContext {

    @XmlElement(name = "ResponseReference", required = true, nillable = true)
    protected String responseReference;

    /**
     * Gets the value of the responseReference property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResponseReference() {
        return responseReference;
    }

    /**
     * Sets the value of the responseReference property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResponseReference(String value) {
        this.responseReference = value;
    }

}
