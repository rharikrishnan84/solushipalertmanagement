
package com.meritconinc.shiplinx.carrier.eshipplus;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for WSDocument complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="WSDocument">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="DocType" type="{http://www.eshipplus.com/}DocumentType"/>
 *         &lt;element name="DocImage" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/>
 *         &lt;element name="Html" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WSDocument", propOrder = {
    "docType",
    "docImage",
    "html",
    "name"
})
public class WSDocument {

    @XmlElement(name = "DocType", required = true)
    protected DocumentType docType;
    @XmlElement(name = "DocImage")
    protected byte[] docImage;
    @XmlElement(name = "Html")
    protected String html;
    @XmlElement(name = "Name")
    protected String name;

    /**
     * Gets the value of the docType property.
     * 
     * @return
     *     possible object is
     *     {@link DocumentType }
     *     
     */
    public DocumentType getDocType() {
        return docType;
    }

    /**
     * Sets the value of the docType property.
     * 
     * @param value
     *     allowed object is
     *     {@link DocumentType }
     *     
     */
    public void setDocType(DocumentType value) {
        this.docType = value;
    }

    /**
     * Gets the value of the docImage property.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getDocImage() {
        return docImage;
    }

    /**
     * Sets the value of the docImage property.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setDocImage(byte[] value) {
        this.docImage = ((byte[]) value);
    }

    /**
     * Gets the value of the html property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHtml() {
        return html;
    }

    /**
     * Sets the value of the html property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHtml(String value) {
        this.html = value;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

}
