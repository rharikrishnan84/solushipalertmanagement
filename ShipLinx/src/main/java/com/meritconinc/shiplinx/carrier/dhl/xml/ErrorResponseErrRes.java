//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.02.01 at 06:34:59 PM EST 
//


package com.meritconinc.shiplinx.carrier.dhl.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Generic error response root element
 * 
 * <p>Java class for ErrorResponse element declaration.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;element name="ErrorResponse">
 *   &lt;complexType>
 *     &lt;complexContent>
 *       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *         &lt;sequence>
 *           &lt;element name="Response" type="{http://www.dhl.com/datatypes}ErrorResponse"/>
 *         &lt;/sequence>
 *       &lt;/restriction>
 *     &lt;/complexContent>
 *   &lt;/complexType>
 * &lt;/element>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "response"
})
@XmlRootElement(name = "ErrorResponse", namespace = "http://www.dhl.com")
public class ErrorResponseErrRes {

    @XmlElement(name = "Response", required = true)
    protected ErrorResponse response;

    /**
     * Gets the value of the response property.
     * 
     * @return
     *     possible object is
     *     {@link ErrorResponse }
     *     
     */
    public ErrorResponse getResponse() {
        return response;
    }

    /**
     * Sets the value of the response property.
     * 
     * @param value
     *     allowed object is
     *     {@link ErrorResponse }
     *     
     */
    public void setResponse(ErrorResponse value) {
        this.response = value;
    }

}
