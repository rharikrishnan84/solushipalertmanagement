
package com.meritconinc.shiplinx.carrier.purolator.ws.estimating.proxy;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * ResponseInformation
 * 
 * <p>Java class for ResponseInformation complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ResponseInformation">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Errors" type="{http://purolator.com/pws/datatypes/v1}ArrayOfError"/>
 *         &lt;element name="InformationalMessages" type="{http://purolator.com/pws/datatypes/v1}ArrayOfInformationalMessage"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ResponseInformation", propOrder = {
    "errors",
    "informationalMessages"
})
public class ResponseInformation {

    @XmlElement(name = "Errors", required = true, nillable = true)
    protected ArrayOfError errors;
    @XmlElement(name = "InformationalMessages", required = true, nillable = true)
    protected ArrayOfInformationalMessage informationalMessages;

    /**
     * Gets the value of the errors property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfError }
     *     
     */
    public ArrayOfError getErrors() {
        return errors;
    }

    /**
     * Sets the value of the errors property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfError }
     *     
     */
    public void setErrors(ArrayOfError value) {
        this.errors = value;
    }

    /**
     * Gets the value of the informationalMessages property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfInformationalMessage }
     *     
     */
    public ArrayOfInformationalMessage getInformationalMessages() {
        return informationalMessages;
    }

    /**
     * Sets the value of the informationalMessages property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfInformationalMessage }
     *     
     */
    public void setInformationalMessages(ArrayOfInformationalMessage value) {
        this.informationalMessages = value;
    }

}
