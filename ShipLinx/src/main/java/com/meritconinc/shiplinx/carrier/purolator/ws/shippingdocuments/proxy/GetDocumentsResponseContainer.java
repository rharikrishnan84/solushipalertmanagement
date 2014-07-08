
package com.meritconinc.shiplinx.carrier.purolator.ws.shippingdocuments.proxy;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * GetDocumentsResponse
 * 
 * <p>Java class for GetDocumentsResponseContainer complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GetDocumentsResponseContainer">
 *   &lt;complexContent>
 *     &lt;extension base="{http://purolator.com/pws/datatypes/v1}ResponseContainer">
 *       &lt;sequence>
 *         &lt;element name="Documents" type="{http://purolator.com/pws/datatypes/v1}ArrayOfDocument" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetDocumentsResponseContainer", propOrder = {
    "documents"
})
public class GetDocumentsResponseContainer
    extends ResponseContainer
{

    @XmlElementRef(name = "Documents", namespace = "http://purolator.com/pws/datatypes/v1", type = JAXBElement.class)
    protected JAXBElement<ArrayOfDocument> documents;

    /**
     * Gets the value of the documents property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfDocument }{@code >}
     *     
     */
    public JAXBElement<ArrayOfDocument> getDocuments() {
        return documents;
    }

    /**
     * Sets the value of the documents property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfDocument }{@code >}
     *     
     */
    public void setDocuments(JAXBElement<ArrayOfDocument> value) {
        this.documents = value;
    }

}
