
package com.meritconinc.shiplinx.carrier.purolator.ws.shippingdocuments.proxy;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfDocumentCriteria complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfDocumentCriteria">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="DocumentCriteria" type="{http://purolator.com/pws/datatypes/v1}DocumentCriteria" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfDocumentCriteria", propOrder = {
    "documentCriteria"
})
public class ArrayOfDocumentCriteria {

    @XmlElement(name = "DocumentCriteria", nillable = true)
    protected List<DocumentCriteria> documentCriteria;

    /**
     * Gets the value of the documentCriteria property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the documentCriteria property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDocumentCriteria().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DocumentCriteria }
     * 
     * 
     */
    public List<DocumentCriteria> getDocumentCriteria() {
        if (documentCriteria == null) {
            documentCriteria = new ArrayList<DocumentCriteria>();
        }
        return this.documentCriteria;
    }

}
