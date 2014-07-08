
package com.meritconinc.shiplinx.carrier.purolator.ws.shippingdocuments.proxy;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * DocumentCriteria
 * 
 * <p>Java class for DocumentCriteria complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DocumentCriteria">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="PIN" type="{http://purolator.com/pws/datatypes/v1}PIN"/>
 *         &lt;element name="DocumentTypes" type="{http://purolator.com/pws/datatypes/v1}DocumentTypes" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DocumentCriteria", propOrder = {
    "pin",
    "documentTypes"
})
public class DocumentCriteria {

    @XmlElement(name = "PIN", required = true, nillable = true)
    protected PIN pin;
    @XmlElementRef(name = "DocumentTypes", namespace = "http://purolator.com/pws/datatypes/v1", type = JAXBElement.class)
    protected JAXBElement<DocumentTypes> documentTypes;

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
     * Gets the value of the documentTypes property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link DocumentTypes }{@code >}
     *     
     */
    public JAXBElement<DocumentTypes> getDocumentTypes() {
        return documentTypes;
    }

    /**
     * Sets the value of the documentTypes property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link DocumentTypes }{@code >}
     *     
     */
    public void setDocumentTypes(JAXBElement<DocumentTypes> value) {
        this.documentTypes = value;
    }

}
