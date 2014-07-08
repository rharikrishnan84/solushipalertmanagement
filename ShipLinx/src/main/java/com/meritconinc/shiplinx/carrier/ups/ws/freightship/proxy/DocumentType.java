
package com.meritconinc.shiplinx.carrier.ups.ws.freightship.proxy;

import java.util.ArrayList;
import java.util.List;
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
 *         &lt;element name="Image" type="{http://www.ups.com/XMLSchema/XOLTWS/FreightShip/v1.0}ImageFormsType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="Forms" type="{http://www.ups.com/XMLSchema/XOLTWS/FreightShip/v1.0}ImageFormsType" maxOccurs="unbounded" minOccurs="0"/>
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
    "image",
    "forms"
})
public class DocumentType {

    @XmlElement(name = "Image")
    protected List<ImageFormsType> image;
    @XmlElement(name = "Forms")
    protected List<ImageFormsType> forms;

    /**
     * Gets the value of the image property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the image property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getImage().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ImageFormsType }
     * 
     * 
     */
    public List<ImageFormsType> getImage() {
        if (image == null) {
            image = new ArrayList<ImageFormsType>();
        }
        return this.image;
    }

    /**
     * Gets the value of the forms property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the forms property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getForms().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ImageFormsType }
     * 
     * 
     */
    public List<ImageFormsType> getForms() {
        if (forms == null) {
            forms = new ArrayList<ImageFormsType>();
        }
        return this.forms;
    }

}
