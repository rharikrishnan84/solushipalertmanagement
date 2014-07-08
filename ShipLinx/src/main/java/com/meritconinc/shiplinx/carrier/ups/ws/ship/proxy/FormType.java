
package com.meritconinc.shiplinx.carrier.ups.ws.ship.proxy;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for FormType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="FormType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Code" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Description" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Image" type="{http://www.ups.com/XMLSchema/XOLTWS/Ship/v1.0}FormImageType" minOccurs="0"/>
 *         &lt;element name="FormGroupId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FormGroupIdName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FormType", propOrder = {
    "code",
    "description",
    "image",
    "formGroupId",
    "formGroupIdName"
})
public class FormType {

    @XmlElement(name = "Code", required = true)
    protected String code;
    @XmlElement(name = "Description", required = true)
    protected String description;
    @XmlElement(name = "Image")
    protected FormImageType image;
    @XmlElement(name = "FormGroupId")
    protected String formGroupId;
    @XmlElement(name = "FormGroupIdName")
    protected String formGroupIdName;

    /**
     * Gets the value of the code property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCode() {
        return code;
    }

    /**
     * Sets the value of the code property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCode(String value) {
        this.code = value;
    }

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Gets the value of the image property.
     * 
     * @return
     *     possible object is
     *     {@link FormImageType }
     *     
     */
    public FormImageType getImage() {
        return image;
    }

    /**
     * Sets the value of the image property.
     * 
     * @param value
     *     allowed object is
     *     {@link FormImageType }
     *     
     */
    public void setImage(FormImageType value) {
        this.image = value;
    }

    /**
     * Gets the value of the formGroupId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFormGroupId() {
        return formGroupId;
    }

    /**
     * Sets the value of the formGroupId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFormGroupId(String value) {
        this.formGroupId = value;
    }

    /**
     * Gets the value of the formGroupIdName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFormGroupIdName() {
        return formGroupIdName;
    }

    /**
     * Sets the value of the formGroupIdName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFormGroupIdName(String value) {
        this.formGroupIdName = value;
    }

}
