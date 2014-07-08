
package com.meritconinc.shiplinx.carrier.purolator.ws.shippingdocuments.proxy;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Document
 * 
 * <p>Java class for Document complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Document">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="PIN" type="{http://purolator.com/pws/datatypes/v1}PIN"/>
 *         &lt;element name="DocumentDetails" type="{http://purolator.com/pws/datatypes/v1}ArrayOfDocumentDetail"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Document", propOrder = {
    "pin",
    "documentDetails"
})
public class Document {

    @XmlElement(name = "PIN", required = true, nillable = true)
    protected PIN pin;
    @XmlElement(name = "DocumentDetails", required = true, nillable = true)
    protected ArrayOfDocumentDetail documentDetails;

    /**
     * Gets the value of the pin property.
     * 
     * @return
     *     possible object is
     *     {@link PIN }
     *     
     */
    public PIN getPIN() {
        return pin;
    }

    /**
     * Sets the value of the pin property.
     * 
     * @param value
     *     allowed object is
     *     {@link PIN }
     *     
     */
    public void setPIN(PIN value) {
        this.pin = value;
    }

    /**
     * Gets the value of the documentDetails property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfDocumentDetail }
     *     
     */
    public ArrayOfDocumentDetail getDocumentDetails() {
        return documentDetails;
    }

    /**
     * Sets the value of the documentDetails property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfDocumentDetail }
     *     
     */
    public void setDocumentDetails(ArrayOfDocumentDetail value) {
        this.documentDetails = value;
    }

}
