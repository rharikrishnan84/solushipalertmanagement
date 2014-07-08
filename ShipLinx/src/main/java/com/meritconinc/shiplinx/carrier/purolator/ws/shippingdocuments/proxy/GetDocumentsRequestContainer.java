
package com.meritconinc.shiplinx.carrier.purolator.ws.shippingdocuments.proxy;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * GetDocumentsRequest
 * 
 * <p>Java class for GetDocumentsRequestContainer complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GetDocumentsRequestContainer">
 *   &lt;complexContent>
 *     &lt;extension base="{http://purolator.com/pws/datatypes/v1}RequestContainer">
 *       &lt;sequence>
 *         &lt;element name="DocumentCriterium" type="{http://purolator.com/pws/datatypes/v1}ArrayOfDocumentCriteria"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetDocumentsRequestContainer", propOrder = {
    "documentCriterium"
})
public class GetDocumentsRequestContainer
    extends RequestContainer
{

    @XmlElement(name = "DocumentCriterium", required = true, nillable = true)
    protected ArrayOfDocumentCriteria documentCriterium;

    /**
     * Gets the value of the documentCriterium property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfDocumentCriteria }
     *     
     */
    public ArrayOfDocumentCriteria getDocumentCriterium() {
        return documentCriterium;
    }

    /**
     * Sets the value of the documentCriterium property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfDocumentCriteria }
     *     
     */
    public void setDocumentCriterium(ArrayOfDocumentCriteria value) {
        this.documentCriterium = value;
    }

}
