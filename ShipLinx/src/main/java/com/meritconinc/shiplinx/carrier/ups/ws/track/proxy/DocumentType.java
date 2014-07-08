
package com.meritconinc.shiplinx.carrier.ups.ws.track.proxy;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DocumentType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DocumentType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Type" type="{http://www.ups.com/XMLSchema/XOLTWS/Track/v2.0}CommonCodeDescriptionType"/>
 *         &lt;element name="Content" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Format" type="{http://www.ups.com/XMLSchema/XOLTWS/Track/v2.0}CommonCodeDescriptionType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DocumentType", propOrder = {
    "type",
    "content",
    "format"
})
public class DocumentType {

    @XmlElement(name = "Type", required = true)
    protected CommonCodeDescriptionType type;
    @XmlElement(name = "Content")
    protected String content;
    @XmlElement(name = "Format")
    protected CommonCodeDescriptionType format;

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link CommonCodeDescriptionType }
     *     
     */
    public CommonCodeDescriptionType getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link CommonCodeDescriptionType }
     *     
     */
    public void setType(CommonCodeDescriptionType value) {
        this.type = value;
    }

    /**
     * Gets the value of the content property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContent() {
        return content;
    }

    /**
     * Sets the value of the content property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContent(String value) {
        this.content = value;
    }

    /**
     * Gets the value of the format property.
     * 
     * @return
     *     possible object is
     *     {@link CommonCodeDescriptionType }
     *     
     */
    public CommonCodeDescriptionType getFormat() {
        return format;
    }

    /**
     * Sets the value of the format property.
     * 
     * @param value
     *     allowed object is
     *     {@link CommonCodeDescriptionType }
     *     
     */
    public void setFormat(CommonCodeDescriptionType value) {
        this.format = value;
    }

}
