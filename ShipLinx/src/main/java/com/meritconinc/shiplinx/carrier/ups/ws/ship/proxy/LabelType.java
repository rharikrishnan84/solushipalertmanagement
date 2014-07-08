
package com.meritconinc.shiplinx.carrier.ups.ws.ship.proxy;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for LabelType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="LabelType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.ups.com/XMLSchema/XOLTWS/Ship/v1.0}ImageType">
 *       &lt;sequence>
 *         &lt;element name="InternationalSignatureGraphicImage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="HTMLImage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PDF417" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LabelType", propOrder = {
    "internationalSignatureGraphicImage",
    "htmlImage",
    "pdf417"
})
public class LabelType
    extends ImageType
{

    @XmlElement(name = "InternationalSignatureGraphicImage")
    protected String internationalSignatureGraphicImage;
    @XmlElement(name = "HTMLImage")
    protected String htmlImage;
    @XmlElement(name = "PDF417")
    protected String pdf417;

    /**
     * Gets the value of the internationalSignatureGraphicImage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInternationalSignatureGraphicImage() {
        return internationalSignatureGraphicImage;
    }

    /**
     * Sets the value of the internationalSignatureGraphicImage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInternationalSignatureGraphicImage(String value) {
        this.internationalSignatureGraphicImage = value;
    }

    /**
     * Gets the value of the htmlImage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHTMLImage() {
        return htmlImage;
    }

    /**
     * Sets the value of the htmlImage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHTMLImage(String value) {
        this.htmlImage = value;
    }

    /**
     * Gets the value of the pdf417 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPDF417() {
        return pdf417;
    }

    /**
     * Sets the value of the pdf417 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPDF417(String value) {
        this.pdf417 = value;
    }

}
